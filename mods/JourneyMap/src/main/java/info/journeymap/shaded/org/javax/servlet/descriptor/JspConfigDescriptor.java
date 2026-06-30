/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.descriptor;

import info.journeymap.shaded.org.javax.servlet.descriptor.JspPropertyGroupDescriptor;
import info.journeymap.shaded.org.javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

public interface JspConfigDescriptor {
    public Collection<TaglibDescriptor> getTaglibs();

    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups();
}

