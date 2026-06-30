/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSessionEvent;
import java.util.EventListener;

public interface HttpSessionIdListener
extends EventListener {
    public void sessionIdChanged(HttpSessionEvent var1, String var2);
}

