/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.Token;
import org.bytedeco.javacpp.tools.Tokenizer;

class TokenIndexer {
    boolean raw = false;
    InfoMap infoMap = null;
    Token[] array = null;
    int index = 0;
    final boolean isCFile;
    int counter = 0;

    TokenIndexer(InfoMap infoMap, Token[] array, boolean isCFile) {
        this.infoMap = infoMap;
        this.array = array;
        this.isCFile = isCFile;
    }

    Token[] filter(Token[] array, int index) {
        if (index + 1 < array.length && array[index].match(Character.valueOf('#')) && array[index + 1].match(Token.IF, Token.IFDEF, Token.IFNDEF)) {
            ArrayList<Token> tokens = new ArrayList<Token>();
            for (int i2 = 0; i2 < index; ++i2) {
                tokens.add(array[i2]);
            }
            int count = 0;
            Info info = null;
            boolean define = true;
            boolean defined = false;
            while (index < array.length) {
                String spacing = array[index].spacing;
                int n2 = spacing.lastIndexOf(10) + 1;
                Token keyword = null;
                if (array[index].match(Character.valueOf('#'))) {
                    if (array[index + 1].match(Token.IF, Token.IFDEF, Token.IFNDEF)) {
                        ++count;
                    }
                    if (count == 1 && array[index + 1].match(Token.IF, Token.IFDEF, Token.IFNDEF, Token.ELIF, Token.ELSE, Token.ENDIF)) {
                        keyword = array[index + 1];
                    }
                    if (array[index + 1].match(Token.ENDIF)) {
                        --count;
                    }
                }
                if (keyword != null) {
                    index += 2;
                    Token comment = new Token();
                    comment.type = 4;
                    comment.spacing = spacing.substring(0, n2);
                    comment.value = "// " + spacing.substring(n2) + "#" + keyword.spacing + keyword;
                    tokens.add(comment);
                    String value = "";
                    while (index < array.length && array[index].spacing.indexOf(10) < 0) {
                        if (!array[index].match(4)) {
                            value = value + array[index].spacing + array[index];
                        }
                        comment.value = comment.value + (array[index].match("\n") ? "\n// " : array[index].spacing + array[index].toString().replaceAll("\n", "\n// "));
                        ++index;
                    }
                    if (keyword.match(Token.IF, Token.IFDEF, Token.IFNDEF, Token.ELIF)) {
                        define = info == null || !defined;
                        info = this.infoMap.getFirst(value);
                        if (info != null) {
                            define = keyword.match(Token.IFNDEF) ? !info.define : info.define;
                        } else {
                            try {
                                define = Integer.decode(value.trim()) != 0;
                            }
                            catch (NumberFormatException numberFormatException) {}
                        }
                    } else if (keyword.match(Token.ELSE)) {
                        define = info == null || !define;
                    } else if (keyword.match(Token.ENDIF) && count == 0) {
                        break;
                    }
                } else if (define) {
                    tokens.add(array[index++]);
                } else {
                    ++index;
                }
                defined = define || defined;
            }
            while (index < array.length) {
                tokens.add(array[index]);
                ++index;
            }
            array = tokens.toArray(new Token[tokens.size()]);
        }
        return array;
    }

    Token[] expand(Token[] array, int index) {
        if (index < array.length && this.infoMap.containsKey(array[index].value)) {
            int startIndex = index;
            List<Info> infoList = this.infoMap.get(array[index].value);
            Info info = null;
            for (Info i2 : infoList) {
                if (i2 == null || i2.cppText == null) continue;
                info = i2;
            }
            if (info != null && info.cppText != null) {
                try {
                    int i3;
                    Tokenizer tokenizer = new Tokenizer(info.cppText, array[index].file, array[index].lineNumber);
                    if (!(tokenizer.nextToken().match(Character.valueOf('#')) && tokenizer.nextToken().match(Token.DEFINE) && tokenizer.nextToken().match(info.cppNames[0]))) {
                        return array;
                    }
                    ArrayList<Token> tokens = new ArrayList<Token>();
                    for (int i4 = 0; i4 < index; ++i4) {
                        tokens.add(array[i4]);
                    }
                    ArrayList<String> params = new ArrayList<String>();
                    List[] args = null;
                    Token token = tokenizer.nextToken();
                    if (info.cppNames[0].equals("__COUNTER__")) {
                        token.value = Integer.toString(this.counter++);
                    }
                    String name = array[index].value;
                    boolean varargs = false;
                    if (token.match(Character.valueOf('('))) {
                        token = tokenizer.nextToken();
                        while (!token.isEmpty()) {
                            if (token.match(5)) {
                                params.add(token.value);
                            } else if (token.match("...")) {
                                params.add("__VA_ARGS__");
                                varargs = true;
                            } else if (token.match(Character.valueOf(')'))) {
                                token = tokenizer.nextToken();
                                break;
                            }
                            token = tokenizer.nextToken();
                        }
                        if (!(params.size() <= 0 || ++index < array.length && array[index].match(Character.valueOf('(')))) {
                            return array;
                        }
                        name = name + array[index].spacing + array[index];
                        args = new List[params.size()];
                        int count = 0;
                        int count2 = 0;
                        ++index;
                        while (index < array.length) {
                            Token token2 = array[index];
                            name = name + token2.spacing + token2;
                            if (count2 == 0 && token2.match(Character.valueOf(')'))) break;
                            if (count2 == 0 && token2.match(Character.valueOf(',')) && (!varargs || count + 1 < args.length)) {
                                ++count;
                            } else {
                                if (token2.match(Character.valueOf('('), Character.valueOf('['), Character.valueOf('{'))) {
                                    ++count2;
                                } else if (token2.match(Character.valueOf(')'), Character.valueOf(']'), Character.valueOf('}'))) {
                                    --count2;
                                } else if (token2.match("]]")) {
                                    count2 -= 2;
                                }
                                if (count < args.length) {
                                    if (args[count] == null) {
                                        args[count] = new ArrayList();
                                    }
                                    args[count].add(token2);
                                }
                            }
                            ++index;
                        }
                        for (i3 = 0; i3 < args.length; ++i3) {
                            if (args[i3] == null) {
                                args[i3] = Arrays.asList(new Token[0]);
                                continue;
                            }
                            if (!this.infoMap.containsKey(((Token)args[i3].get((int)0)).value)) continue;
                            args[i3] = Arrays.asList(this.expand(args[i3].toArray(new Token[args[i3].size()]), 0));
                        }
                    }
                    int startToken = tokens.size();
                    info = this.infoMap.getFirst(name);
                    while (!(info != null && info.skip || token.isEmpty())) {
                        boolean foundArg = false;
                        for (i3 = 0; i3 < params.size(); ++i3) {
                            if (!((String)params.get(i3)).equals(token.value)) continue;
                            String s2 = token.spacing;
                            for (Token arg : args[i3]) {
                                Token t2 = new Token(arg);
                                if (s2 != null) {
                                    t2.spacing = t2.spacing + s2;
                                }
                                tokens.add(t2);
                                s2 = null;
                            }
                            foundArg = true;
                            break;
                        }
                        if (!foundArg) {
                            if (token.type == -1) {
                                token.type = 4;
                            }
                            tokens.add(token);
                        }
                        token = tokenizer.nextToken();
                    }
                    for (int i5 = startToken; i5 < tokens.size(); ++i5) {
                        if (!((Token)tokens.get(i5)).match("##") || i5 <= 0 || i5 + 1 >= tokens.size()) continue;
                        ((Token)tokens.get((int)(i5 - 1))).value = ((Token)tokens.get((int)(i5 - 1))).value + ((Token)tokens.get((int)(i5 + 1))).value;
                        tokens.remove(i5);
                        tokens.remove(i5);
                        --i5;
                    }
                    ++index;
                    while (index < array.length) {
                        tokens.add(array[index]);
                        ++index;
                    }
                    if (!(info != null && info.skip || startToken >= tokens.size())) {
                        ((Token)tokens.get((int)startToken)).spacing = array[startIndex].spacing;
                    }
                    array = tokens.toArray(new Token[tokens.size()]);
                }
                catch (IOException ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        return array;
    }

    int preprocess(int index, int count) {
        Token[] a2;
        while (index < this.array.length) {
            a2 = null;
            while (a2 != this.array) {
                a2 = this.array;
                this.array = this.filter(this.array, index);
                this.array = this.expand(this.array, index);
            }
            if (!this.array[index].match(4) && --count < 0) break;
            ++index;
        }
        a2 = null;
        while (a2 != this.array) {
            a2 = this.array;
            this.array = this.filter(this.array, index);
            this.array = this.expand(this.array, index);
        }
        return index;
    }

    Token get() {
        return this.get(0);
    }

    Token get(int i2) {
        int k2;
        int n2 = k2 = this.raw ? this.index + i2 : this.preprocess(this.index, i2);
        return k2 < this.array.length ? this.array[k2] : (this.array[this.array.length - 1].match(Token.EOF) ? this.array[this.array.length - 1] : Token.EOF);
    }

    Token next() {
        int n2 = this.index = this.raw ? this.index + 1 : this.preprocess(this.index, 1);
        return this.index < this.array.length ? this.array[this.index] : (this.array[this.array.length - 1].match(Token.EOF) ? this.array[this.array.length - 1] : Token.EOF);
    }
}

