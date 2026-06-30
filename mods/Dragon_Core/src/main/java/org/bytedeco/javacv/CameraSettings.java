/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.beans.PropertyVetoException;
import org.bytedeco.javacv.BaseSettings;
import org.bytedeco.javacv.CameraDevice;
import org.bytedeco.javacv.FrameGrabber;

public class CameraSettings
extends BaseSettings {
    boolean calibrated = false;
    double monitorWindowsScale = 1.0;
    Class<? extends FrameGrabber> frameGrabber = null;

    public CameraSettings() {
        this(false);
    }

    public CameraSettings(boolean calibrated) {
        this.calibrated = calibrated;
    }

    public int getQuantity() {
        return this.size();
    }

    public void setQuantity(int quantity) throws PropertyVetoException {
        int i2;
        quantity = Math.max(1, quantity);
        CameraDevice.Settings[] a2 = this.toArray();
        for (i2 = a2.length; i2 > quantity; --i2) {
            this.remove(a2[i2 - 1]);
        }
        while (i2 < quantity) {
            CameraDevice.Settings c2 = (CameraDevice.Settings)((Object)(this.calibrated ? new CameraDevice.CalibratedSettings() : new CameraDevice.CalibrationSettings()));
            c2.setName("Camera " + String.format("%2d", i2));
            c2.setDeviceNumber(i2);
            c2.setFrameGrabber(this.frameGrabber);
            this.add(c2);
            ++i2;
        }
        this.pcSupport.firePropertyChange("quantity", a2.length, quantity);
    }

    public double getMonitorWindowsScale() {
        return this.monitorWindowsScale;
    }

    public void setMonitorWindowsScale(double monitorWindowsScale) {
        this.monitorWindowsScale = monitorWindowsScale;
    }

    public Class<? extends FrameGrabber> getFrameGrabber() {
        return this.frameGrabber;
    }

    public void setFrameGrabber(Class<? extends FrameGrabber> frameGrabber) {
        this.frameGrabber = frameGrabber;
        this.pcSupport.firePropertyChange("frameGrabber", this.frameGrabber, this.frameGrabber);
    }

    public CameraDevice.Settings[] toArray() {
        return (CameraDevice.Settings[])this.toArray(new CameraDevice.Settings[this.size()]);
    }
}

