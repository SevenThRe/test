/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.DisplayMode
 */
package net.optifine.util;

import java.util.Comparator;
import org.lwjgl.opengl.DisplayMode;

public class DisplayModeComparator
implements Comparator {
    public int compare(Object o1, Object o2) {
        DisplayMode dm1 = (DisplayMode)o1;
        DisplayMode dm2 = (DisplayMode)o2;
        if (dm1.getWidth() != dm2.getWidth()) {
            return dm1.getWidth() - dm2.getWidth();
        }
        if (dm1.getHeight() != dm2.getHeight()) {
            return dm1.getHeight() - dm2.getHeight();
        }
        if (dm1.getBitsPerPixel() != dm2.getBitsPerPixel()) {
            return dm1.getBitsPerPixel() - dm2.getBitsPerPixel();
        }
        if (dm1.getFrequency() != dm2.getFrequency()) {
            return dm1.getFrequency() - dm2.getFrequency();
        }
        return 0;
    }
}

