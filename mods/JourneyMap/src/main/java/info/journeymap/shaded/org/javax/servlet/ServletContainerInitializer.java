/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import java.util.Set;

public interface ServletContainerInitializer {
    public void onStartup(Set<Class<?>> var1, ServletContext var2) throws ServletException;
}

