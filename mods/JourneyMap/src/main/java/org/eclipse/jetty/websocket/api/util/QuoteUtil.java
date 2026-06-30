/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QuoteUtil {
    public static final String ABNF_REQUIRED_QUOTING = "\"'\\\n\r\t\f\b%+ ;=";
    private static final char UNICODE_TAG = '\uffff';
    private static final char[] escapes = new char[32];

    private static int dehex(byte b) {
        if (b >= 48 && b <= 57) {
            return (byte)(b - 48);
        }
        if (b >= 97 && b <= 102) {
            return (byte)(b - 97 + 10);
        }
        if (b >= 65 && b <= 70) {
            return (byte)(b - 65 + 10);
        }
        throw new IllegalArgumentException("!hex:" + Integer.toHexString(0xFF & b));
    }

    public static String dequote(String str) {
        char end;
        char start = str.charAt(0);
        if ((start == '\'' || start == '\"') && start == (end = str.charAt(str.length() - 1))) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    public static void escape(StringBuilder buf, String str) {
        for (char c : str.toCharArray()) {
            if (c >= ' ') {
                if (c == '\"' || c == '\\') {
                    buf.append('\\');
                }
                buf.append(c);
                continue;
            }
            char escaped = escapes[c];
            if (escaped == '\uffff') {
                buf.append("\\u00");
                if (c < '\u0010') {
                    buf.append('0');
                }
                buf.append(Integer.toString(c, 16));
                continue;
            }
            buf.append('\\').append(escaped);
        }
    }

    public static void quote(StringBuilder buf, String str) {
        buf.append('\"');
        QuoteUtil.escape(buf, str);
        buf.append('\"');
    }

    public static void quoteIfNeeded(StringBuilder buf, String str, String delim) {
        if (str == null) {
            return;
        }
        int len = str.length();
        if (len == 0) {
            return;
        }
        for (int i = 0; i < len; ++i) {
            int ch = str.codePointAt(i);
            if (delim.indexOf(ch) < 0) continue;
            QuoteUtil.quote(buf, str);
            return;
        }
        buf.append(str);
    }

    public static Iterator<String> splitAt(String str, String delims) {
        return new DeQuotingStringIterator(str.trim(), delims);
    }

    public static String unescape(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len <= 1) {
            return str;
        }
        StringBuilder ret = new StringBuilder(len - 2);
        boolean escaped = false;
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (escaped) {
                escaped = false;
                switch (c) {
                    case 'n': {
                        ret.append('\n');
                        break;
                    }
                    case 'r': {
                        ret.append('\r');
                        break;
                    }
                    case 't': {
                        ret.append('\t');
                        break;
                    }
                    case 'f': {
                        ret.append('\f');
                        break;
                    }
                    case 'b': {
                        ret.append('\b');
                        break;
                    }
                    case '\\': {
                        ret.append('\\');
                        break;
                    }
                    case '/': {
                        ret.append('/');
                        break;
                    }
                    case '\"': {
                        ret.append('\"');
                        break;
                    }
                    case 'u': {
                        ret.append((char)((QuoteUtil.dehex((byte)str.charAt(i++)) << 24) + (QuoteUtil.dehex((byte)str.charAt(i++)) << 16) + (QuoteUtil.dehex((byte)str.charAt(i++)) << 8) + QuoteUtil.dehex((byte)str.charAt(i++))));
                        break;
                    }
                    default: {
                        ret.append(c);
                        break;
                    }
                }
                continue;
            }
            if (c == '\\') {
                escaped = true;
                continue;
            }
            ret.append(c);
        }
        return ret.toString();
    }

    public static String join(Object[] objs, String delim) {
        if (objs == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        int len = objs.length;
        for (int i = 0; i < len; ++i) {
            if (i > 0) {
                ret.append(delim);
            }
            if (objs[i] instanceof String) {
                ret.append('\"').append(objs[i]).append('\"');
                continue;
            }
            ret.append(objs[i]);
        }
        return ret.toString();
    }

    public static String join(Collection<?> objs, String delim) {
        if (objs == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        boolean needDelim = false;
        for (Object obj : objs) {
            if (needDelim) {
                ret.append(delim);
            }
            if (obj instanceof String) {
                ret.append('\"').append(obj).append('\"');
            } else {
                ret.append(obj);
            }
            needDelim = true;
        }
        return ret.toString();
    }

    static {
        Arrays.fill(escapes, '\uffff');
        QuoteUtil.escapes[8] = 98;
        QuoteUtil.escapes[9] = 116;
        QuoteUtil.escapes[10] = 110;
        QuoteUtil.escapes[12] = 102;
        QuoteUtil.escapes[13] = 114;
    }

    private static class DeQuotingStringIterator
    implements Iterator<String> {
        private final String input;
        private final String delims;
        private StringBuilder token;
        private boolean hasToken = false;
        private int i = 0;

        public DeQuotingStringIterator(String input, String delims) {
            this.input = input;
            this.delims = delims;
            int len = input.length();
            this.token = new StringBuilder(len > 1024 ? 512 : len / 2);
        }

        private void appendToken(char c) {
            if (this.hasToken) {
                this.token.append(c);
            } else {
                if (Character.isWhitespace(c)) {
                    return;
                }
                this.token.append(c);
                this.hasToken = true;
            }
        }

        @Override
        public boolean hasNext() {
            if (this.hasToken) {
                return true;
            }
            State state = State.START;
            boolean escape = false;
            int inputLen = this.input.length();
            while (this.i < inputLen) {
                char c = this.input.charAt(this.i++);
                switch (state) {
                    case START: {
                        if (c == '\'') {
                            state = State.QUOTE_SINGLE;
                            this.appendToken(c);
                            break;
                        }
                        if (c == '\"') {
                            state = State.QUOTE_DOUBLE;
                            this.appendToken(c);
                            break;
                        }
                        this.appendToken(c);
                        state = State.TOKEN;
                        break;
                    }
                    case TOKEN: {
                        if (this.delims.indexOf(c) >= 0) {
                            return this.hasToken;
                        }
                        if (c == '\'') {
                            state = State.QUOTE_SINGLE;
                        } else if (c == '\"') {
                            state = State.QUOTE_DOUBLE;
                        }
                        this.appendToken(c);
                        break;
                    }
                    case QUOTE_SINGLE: {
                        if (escape) {
                            escape = false;
                            this.appendToken(c);
                            break;
                        }
                        if (c == '\'') {
                            this.appendToken(c);
                            state = State.TOKEN;
                            break;
                        }
                        if (c == '\\') {
                            escape = true;
                            break;
                        }
                        this.appendToken(c);
                        break;
                    }
                    case QUOTE_DOUBLE: {
                        if (escape) {
                            escape = false;
                            this.appendToken(c);
                            break;
                        }
                        if (c == '\"') {
                            this.appendToken(c);
                            state = State.TOKEN;
                            break;
                        }
                        if (c == '\\') {
                            escape = true;
                            break;
                        }
                        this.appendToken(c);
                    }
                }
            }
            return this.hasToken;
        }

        @Override
        public String next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            String ret = this.token.toString();
            this.token.setLength(0);
            this.hasToken = false;
            return QuoteUtil.dequote(ret.trim());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported with this iterator");
        }

        private static enum State {
            START,
            TOKEN,
            QUOTE_SINGLE,
            QUOTE_DOUBLE;

        }
    }
}

