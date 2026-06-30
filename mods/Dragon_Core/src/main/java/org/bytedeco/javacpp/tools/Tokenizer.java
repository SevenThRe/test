/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import org.bytedeco.javacpp.tools.Token;

class Tokenizer
implements Closeable {
    File file = null;
    String text = null;
    Reader reader = null;
    String lineSeparator = null;
    int lastChar = -1;
    int lineNumber = 1;
    StringBuilder buffer = new StringBuilder();

    Tokenizer(Reader reader, File file, int lineNumber) {
        this.reader = reader;
        this.file = file;
        this.lineNumber = lineNumber;
    }

    Tokenizer(String text, File file, int lineNumber) {
        this.text = text;
        this.reader = new StringReader(text);
        this.file = file;
        this.lineNumber = lineNumber;
    }

    Tokenizer(File file) throws IOException {
        this(file, null);
    }

    Tokenizer(File file, String encoding) throws IOException {
        this.file = file;
        FileInputStream fis = new FileInputStream(file);
        this.reader = new BufferedReader(encoding != null ? new InputStreamReader((InputStream)fis, encoding) : new InputStreamReader(fis));
    }

    public void filterLines(String[] patterns, boolean skip) throws IOException {
        String line;
        BufferedReader lineReader;
        if (patterns == null) {
            return;
        }
        StringBuilder lines = new StringBuilder();
        BufferedReader bufferedReader = lineReader = this.reader instanceof BufferedReader ? (BufferedReader)this.reader : new BufferedReader(this.reader);
        block0: while ((line = lineReader.readLine()) != null) {
            int i2;
            for (i2 = 0; i2 < patterns.length && !line.matches(patterns[i2]); i2 += 2) {
            }
            if (i2 < patterns.length) {
                if (!skip) {
                    lines.append(line + "\n");
                }
                while (i2 + 1 < patterns.length && (line = lineReader.readLine()) != null) {
                    if (!skip) {
                        lines.append(line + "\n");
                    }
                    if (!line.matches(patterns[i2 + 1])) continue;
                    continue block0;
                }
                continue;
            }
            if (!skip) continue;
            lines.append(line + "\n");
        }
        this.reader.close();
        this.reader = new StringReader(lines.toString());
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }

    int readChar() throws IOException {
        if (this.lastChar != -1) {
            int c2 = this.lastChar;
            this.lastChar = -1;
            return c2;
        }
        int c3 = this.reader.read();
        if (c3 == 13 || c3 == 10) {
            int c2;
            if (this.text == null) {
                ++this.lineNumber;
            }
            int n2 = c2 = c3 == 13 ? this.reader.read() : -1;
            if (this.lineSeparator == null) {
                String string = c3 == 13 && c2 == 10 ? "\r\n" : (this.lineSeparator = c3 == 13 ? "\r" : "\n");
            }
            if (c2 != 10) {
                this.lastChar = c2;
            }
            c3 = 10;
        }
        return c3;
    }

    public Token nextToken() throws IOException {
        Token token = new Token();
        int c2 = this.readChar();
        this.buffer.setLength(0);
        if (Character.isWhitespace(c2)) {
            this.buffer.append((char)c2);
            while ((c2 = this.readChar()) != -1 && Character.isWhitespace(c2)) {
                this.buffer.append((char)c2);
            }
        }
        token.file = this.file;
        token.text = this.text;
        token.lineNumber = this.lineNumber;
        token.spacing = this.buffer.toString();
        this.buffer.setLength(0);
        if (Character.isLetter(c2) || c2 == 95) {
            token.type = 5;
            this.buffer.append((char)c2);
            while ((c2 = this.readChar()) != -1 && (Character.isDigit(c2) || Character.isLetter(c2) || c2 == 95)) {
                this.buffer.append((char)c2);
            }
            token.value = this.buffer.toString();
            this.lastChar = c2;
        } else if (Character.isDigit(c2) || c2 == 46 || c2 == 45 || c2 == 43) {
            boolean small;
            boolean hex;
            boolean unsigned;
            boolean large;
            block73: {
                int c22;
                if (c2 == 46) {
                    c22 = this.readChar();
                    if (c22 == 46) {
                        int c3 = this.readChar();
                        if (c3 == 46) {
                            token.type = 6;
                            token.value = "...";
                            return token;
                        }
                        this.lastChar = c3;
                    } else {
                        this.lastChar = c22;
                    }
                } else if (c2 == 45) {
                    c22 = this.readChar();
                    if (c22 == 62) {
                        token.type = 6;
                        token.value = "->";
                        return token;
                    }
                    this.lastChar = c22;
                }
                token.type = c2 == 46 ? 2 : 1;
                this.buffer.append((char)c2);
                int prevc = 0;
                boolean exp = false;
                large = false;
                unsigned = false;
                hex = false;
                small = false;
                while ((c2 = this.readChar()) != -1 && (Character.isDigit(c2) || c2 == 46 || c2 == 45 || c2 == 43 || c2 >= 97 && c2 <= 102 || c2 == 105 || c2 == 108 || c2 == 117 || c2 == 120 || c2 >= 65 && c2 <= 70 || c2 == 73 || c2 == 76 || c2 == 85 || c2 == 88)) {
                    switch (c2) {
                        case 46: {
                            token.type = 2;
                            break;
                        }
                        case 69: 
                        case 101: {
                            exp = true;
                            break;
                        }
                        case 76: 
                        case 108: {
                            large = true;
                            break;
                        }
                        case 85: 
                        case 117: {
                            unsigned = true;
                            break;
                        }
                        case 88: 
                        case 120: {
                            hex = true;
                        }
                    }
                    if (c2 != 108 && c2 != 76 && c2 != 117 && c2 != 85) {
                        this.buffer.append((char)c2);
                    }
                    prevc = c2;
                }
                if (!hex && (exp || prevc == 102 || prevc == 70)) {
                    token.type = 2;
                }
                if (token.type == 1 && !large) {
                    try {
                        long high = Long.decode(this.buffer.toString()) >> 16;
                        small = high == 0L || high == -1L;
                        large = (high >>= 16) != 0L && high != -1L;
                    }
                    catch (NumberFormatException e2) {
                        if (this.buffer.length() < 16) break block73;
                        large = true;
                    }
                }
            }
            if (this.buffer.toString().endsWith("i64")) {
                this.buffer.setLength(this.buffer.length() - 3);
                large = true;
            }
            if (token.type == 1 && (large || unsigned && !hex && !small)) {
                this.buffer.append('L');
            }
            token.value = this.buffer.toString();
            this.lastChar = c2;
        } else if (c2 == 39) {
            token.type = 1;
            this.buffer.append('\'');
            while ((c2 = this.readChar()) != -1 && c2 != 39) {
                this.buffer.append((char)c2);
                if (c2 != 92) continue;
                c2 = this.readChar();
                this.buffer.append((char)c2);
            }
            this.buffer.append('\'');
            token.value = this.buffer.toString();
        } else if (c2 == 34) {
            token.type = 3;
            this.buffer.append('\"');
            while ((c2 = this.readChar()) != -1 && c2 != 34) {
                this.buffer.append((char)c2);
                if (c2 != 92) continue;
                c2 = this.readChar();
                this.buffer.append((char)c2);
            }
            this.buffer.append('\"');
            token.value = this.buffer.toString();
        } else if (c2 == 47) {
            c2 = this.readChar();
            if (c2 == 47) {
                token.type = 4;
                this.buffer.append('/').append('/');
                int prevc = 0;
                while ((c2 = this.readChar()) != -1 && (prevc == 92 || c2 != 10)) {
                    this.buffer.append((char)c2);
                    prevc = c2;
                }
                token.value = this.buffer.toString();
                this.lastChar = c2;
            } else if (c2 == 42) {
                token.type = 4;
                this.buffer.append('/').append('*');
                int prevc = 0;
                while ((c2 = this.readChar()) != -1 && (prevc != 42 || c2 != 47)) {
                    this.buffer.append((char)c2);
                    prevc = c2;
                }
                this.buffer.append('/');
                token.value = this.buffer.toString();
            } else {
                this.lastChar = c2;
                token.type = 47;
            }
        } else if (c2 == 58) {
            int c23 = this.readChar();
            if (c23 == 58) {
                token.type = 6;
                token.value = "::";
            } else {
                token.type = c2;
                this.lastChar = c23;
            }
        } else if (c2 == 38) {
            int c24 = this.readChar();
            if (c24 == 38) {
                token.type = 6;
                token.value = "&&";
            } else {
                token.type = c2;
                this.lastChar = c24;
            }
        } else if (c2 == 35) {
            int c25 = this.readChar();
            if (c25 == 35) {
                token.type = 6;
                token.value = "##";
            } else {
                token.type = c2;
                this.lastChar = c25;
            }
        } else if (c2 == 91) {
            int c26 = this.readChar();
            if (c26 == 91) {
                token.type = 6;
                token.value = "[[";
            } else {
                token.type = c2;
                this.lastChar = c26;
            }
        } else if (c2 == 93) {
            int c27 = this.readChar();
            if (c27 == 93) {
                token.type = 6;
                token.value = "]]";
            } else {
                token.type = c2;
                this.lastChar = c27;
            }
        } else if (c2 == 60) {
            int c28 = this.readChar();
            if (c28 == 61) {
                token.type = 6;
                token.value = "<=";
            } else {
                token.type = c2;
                this.lastChar = c28;
            }
        } else if (c2 == 62) {
            int c29 = this.readChar();
            if (c29 == 61) {
                token.type = 6;
                token.value = ">=";
            } else {
                token.type = c2;
                this.lastChar = c29;
            }
        } else {
            if (c2 == 92) {
                int c210 = this.readChar();
                if (c210 == 10) {
                    token.type = 4;
                    token.value = "\n";
                    return token;
                }
                this.lastChar = c210;
            }
            token.type = c2;
        }
        return token;
    }

    Token[] tokenize() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        try {
            Token token;
            while (!(token = this.nextToken()).isEmpty()) {
                tokens.add(token);
            }
        }
        catch (IOException ex2) {
            throw new RuntimeException(ex2);
        }
        return tokens.toArray(new Token[tokens.size()]);
    }
}

