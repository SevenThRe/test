/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.expr;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import net.optifine.expr.ParseException;
import net.optifine.expr.Token;
import net.optifine.expr.TokenType;

public class TokenParser {
    public static Token[] parse(String str) throws IOException, ParseException {
        int i;
        StringReader r = new StringReader(str);
        PushbackReader pr = new PushbackReader(r);
        ArrayList<Token> list = new ArrayList<Token>();
        while ((i = pr.read()) >= 0) {
            char ch = (char)i;
            if (Character.isWhitespace(ch)) continue;
            TokenType type = TokenType.getTypeByFirstChar(ch);
            if (type == null) {
                throw new ParseException("Invalid character: '" + ch + "', in: " + str);
            }
            Token token = TokenParser.readToken(ch, type, pr);
            list.add(token);
        }
        Token[] tokens = list.toArray(new Token[list.size()]);
        return tokens;
    }

    private static Token readToken(char chFirst, TokenType type, PushbackReader pr) throws IOException {
        int i;
        StringBuffer sb = new StringBuffer();
        sb.append(chFirst);
        while ((i = pr.read()) >= 0) {
            char ch = (char)i;
            if (!type.hasCharNext(ch)) {
                pr.unread(ch);
                break;
            }
            sb.append(ch);
        }
        return new Token(type, sb.toString());
    }
}

