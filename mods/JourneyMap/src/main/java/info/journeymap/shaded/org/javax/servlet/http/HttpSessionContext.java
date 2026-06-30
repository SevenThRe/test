/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Deprecated
public interface HttpSessionContext {
    @Deprecated
    public HttpSession getSession(String var1);

    @Deprecated
    public Enumeration<String> getIds();
}

