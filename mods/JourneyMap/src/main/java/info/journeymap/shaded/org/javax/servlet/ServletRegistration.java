/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.MultipartConfigElement;
import info.journeymap.shaded.org.javax.servlet.Registration;
import info.journeymap.shaded.org.javax.servlet.ServletSecurityElement;
import java.util.Collection;
import java.util.Set;

public interface ServletRegistration
extends Registration {
    public Set<String> addMapping(String ... var1);

    public Collection<String> getMappings();

    public String getRunAsRole();

    public static interface Dynamic
    extends ServletRegistration,
    Registration.Dynamic {
        public void setLoadOnStartup(int var1);

        public Set<String> setServletSecurity(ServletSecurityElement var1);

        public void setMultipartConfig(MultipartConfigElement var1);

        public void setRunAsRole(String var1);
    }
}

