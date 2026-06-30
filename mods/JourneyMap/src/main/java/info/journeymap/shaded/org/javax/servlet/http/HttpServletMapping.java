/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.MappingMatch;

public interface HttpServletMapping {
    public String getMatchValue();

    public String getPattern();

    public String getServletName();

    public MappingMatch getMappingMatch();
}

