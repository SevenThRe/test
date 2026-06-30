/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.server;

import info.journeymap.shaded.org.javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.eclipse.jetty.http.QuotedCSV;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

public class CookieCutter {
    private static final Logger LOG = Log.getLogger(CookieCutter.class);
    private Cookie[] _cookies;
    private Cookie[] _lastCookies;
    private final List<String> _fieldList = new ArrayList<String>();
    int _fields;

    public Cookie[] getCookies() {
        if (this._cookies != null) {
            return this._cookies;
        }
        if (this._lastCookies != null && this._fields == this._fieldList.size()) {
            this._cookies = this._lastCookies;
        } else {
            this.parseFields();
        }
        this._lastCookies = this._cookies;
        return this._cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this._cookies = cookies;
        this._lastCookies = null;
        this._fieldList.clear();
        this._fields = 0;
    }

    public void reset() {
        this._cookies = null;
        this._fields = 0;
    }

    public void addCookieField(String f) {
        if (f == null) {
            return;
        }
        if ((f = f.trim()).length() == 0) {
            return;
        }
        if (this._fieldList.size() > this._fields) {
            if (f.equals(this._fieldList.get(this._fields))) {
                ++this._fields;
                return;
            }
            while (this._fieldList.size() > this._fields) {
                this._fieldList.remove(this._fields);
            }
        }
        this._cookies = null;
        this._lastCookies = null;
        this._fieldList.add(this._fields++, f);
    }

    protected void parseFields() {
        this._lastCookies = null;
        this._cookies = null;
        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
        int version = 0;
        while (this._fieldList.size() > this._fields) {
            this._fieldList.remove(this._fields);
        }
        for (String hdr : this._fieldList) {
            String name = null;
            String value = null;
            Cookie cookie = null;
            boolean invalue = false;
            boolean quoted = false;
            boolean escaped = false;
            int tokenstart = -1;
            int tokenend = -1;
            int length = hdr.length();
            int last = length - 1;
            block19: for (int i = 0; i < length; ++i) {
                block46: {
                    char c;
                    block45: {
                        c = hdr.charAt(i);
                        if (!quoted) break block45;
                        if (escaped) {
                            escaped = false;
                            continue;
                        }
                        switch (c) {
                            case '\"': {
                                tokenend = i;
                                quoted = false;
                                if (i == last) {
                                    if (invalue) {
                                        value = hdr.substring(tokenstart, tokenend + 1);
                                    } else {
                                        name = hdr.substring(tokenstart, tokenend + 1);
                                        value = "";
                                    }
                                }
                                break block46;
                            }
                            case '\\': {
                                escaped = true;
                                break;
                            }
                        }
                        continue;
                    }
                    if (invalue) {
                        switch (c) {
                            case '\t': 
                            case ' ': {
                                continue block19;
                            }
                            case '\"': {
                                if (tokenstart < 0) {
                                    quoted = true;
                                    tokenstart = i;
                                }
                                tokenend = i;
                                if (i != last) continue block19;
                                value = hdr.substring(tokenstart, tokenend + 1);
                                break;
                            }
                            case ';': {
                                value = tokenstart >= 0 ? hdr.substring(tokenstart, tokenend + 1) : "";
                                tokenstart = -1;
                                invalue = false;
                                break;
                            }
                            default: {
                                if (tokenstart < 0) {
                                    tokenstart = i;
                                }
                                tokenend = i;
                                if (i != last) continue block19;
                                value = hdr.substring(tokenstart, tokenend + 1);
                                break;
                            }
                        }
                    } else {
                        switch (c) {
                            case '\t': 
                            case ' ': {
                                continue block19;
                            }
                            case '\"': {
                                if (tokenstart < 0) {
                                    quoted = true;
                                    tokenstart = i;
                                }
                                tokenend = i;
                                if (i != last) continue block19;
                                name = hdr.substring(tokenstart, tokenend + 1);
                                value = "";
                                break;
                            }
                            case ';': {
                                if (tokenstart >= 0) {
                                    name = hdr.substring(tokenstart, tokenend + 1);
                                    value = "";
                                }
                                tokenstart = -1;
                                break;
                            }
                            case '=': {
                                if (tokenstart >= 0) {
                                    name = hdr.substring(tokenstart, tokenend + 1);
                                }
                                tokenstart = -1;
                                invalue = true;
                                continue block19;
                            }
                            default: {
                                if (tokenstart < 0) {
                                    tokenstart = i;
                                }
                                tokenend = i;
                                if (i != last) continue block19;
                                name = hdr.substring(tokenstart, tokenend + 1);
                                value = "";
                            }
                        }
                    }
                }
                if (value == null || name == null) continue;
                name = QuotedCSV.unquote(name);
                value = QuotedCSV.unquote(value);
                try {
                    if (name.startsWith("$")) {
                        String lowercaseName = name.toLowerCase(Locale.ENGLISH);
                        if ("$path".equals(lowercaseName)) {
                            if (cookie != null) {
                                cookie.setPath(value);
                            }
                        } else if ("$domain".equals(lowercaseName)) {
                            if (cookie != null) {
                                cookie.setDomain(value);
                            }
                        } else if ("$port".equals(lowercaseName)) {
                            if (cookie != null) {
                                cookie.setComment("$port=" + value);
                            }
                        } else if ("$version".equals(lowercaseName)) {
                            version = Integer.parseInt(value);
                        }
                    } else {
                        cookie = new Cookie(name, value);
                        if (version > 0) {
                            cookie.setVersion(version);
                        }
                        cookies.add(cookie);
                    }
                }
                catch (Exception e) {
                    LOG.debug(e);
                }
                name = null;
                value = null;
            }
        }
        this._cookies = cookies.toArray(new Cookie[cookies.size()]);
        this._lastCookies = this._cookies;
    }
}

