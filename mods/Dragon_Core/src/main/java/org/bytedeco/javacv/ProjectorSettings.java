/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.beans.PropertyChangeListener;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.BaseSettings;
import org.bytedeco.javacv.ProjectorDevice;

public class ProjectorSettings
extends BaseSettings {
    boolean calibrated = false;

    public ProjectorSettings() {
        this(false);
    }

    public ProjectorSettings(boolean calibrated) {
        this.calibrated = calibrated;
    }

    public int getQuantity() {
        return this.size();
    }

    public void setQuantity(int quantity) {
        int i2;
        ProjectorDevice.Settings[] a2 = this.toArray();
        for (i2 = a2.length; i2 > quantity; --i2) {
            this.remove(a2[i2 - 1]);
        }
        while (i2 < quantity) {
            ProjectorDevice.Settings c2 = (ProjectorDevice.Settings)((Object)(this.calibrated ? new ProjectorDevice.CalibratedSettings() : new ProjectorDevice.CalibrationSettings()));
            c2.setName("Projector " + String.format("%2d", i2));
            c2.setScreenNumber(c2.getScreenNumber() + i2);
            this.add(c2);
            for (PropertyChangeListener l2 : this.pcSupport.getPropertyChangeListeners()) {
                ((BaseChildSettings)((Object)c2)).addPropertyChangeListener(l2);
            }
            ++i2;
        }
        this.pcSupport.firePropertyChange("quantity", a2.length, quantity);
    }

    public ProjectorDevice.Settings[] toArray() {
        return (ProjectorDevice.Settings[])this.toArray(new ProjectorDevice.Settings[this.size()]);
    }
}

