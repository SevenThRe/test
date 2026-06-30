/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.opencv_core.FileNode
 *  org.bytedeco.opencv.opencv_core.FileNodeIterator
 *  org.bytedeco.opencv.opencv_core.FileStorage
 */
package org.bytedeco.javacv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileNodeIterator;
import org.bytedeco.opencv.opencv_core.FileStorage;

public class CameraDevice
extends ProjectiveDevice {
    private Settings settings;

    public CameraDevice(String name) {
        super(name);
    }

    public CameraDevice(String name, String filename) throws ProjectiveDevice.Exception {
        super(name, filename);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public CameraDevice(String name, FileStorage fs2) throws ProjectiveDevice.Exception {
        super(name, fs2);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public CameraDevice(Settings settings) throws ProjectiveDevice.Exception {
        super((ProjectiveDevice.Settings)((Object)settings));
    }

    @Override
    public ProjectiveDevice.Settings getSettings() {
        return (ProjectiveDevice.Settings)((Object)this.settings);
    }

    public void setSettings(Settings settings) {
        this.setSettings((ProjectiveDevice.Settings)((Object)settings));
    }

    @Override
    public void setSettings(ProjectiveDevice.Settings settings) {
        super.setSettings(settings);
        this.settings = settings instanceof ProjectiveDevice.CalibrationSettings ? new CalibrationSettings((ProjectiveDevice.CalibrationSettings)settings) : (settings instanceof ProjectiveDevice.CalibratedSettings ? new CalibratedSettings((ProjectiveDevice.CalibratedSettings)settings) : new SettingsImplementation(settings));
        if (this.settings.getName() == null || this.settings.getName().length() == 0) {
            this.settings.setName("Camera " + String.format("%2d", this.settings.getDeviceNumber()));
        }
    }

    public FrameGrabber createFrameGrabber() throws FrameGrabber.Exception {
        try {
            FrameGrabber f2;
            this.settings.getFrameGrabber().getMethod("tryLoad", new Class[0]).invoke(null, new Object[0]);
            if (this.settings.getDeviceFile() != null) {
                f2 = this.settings.getFrameGrabber().getConstructor(File.class).newInstance(this.settings.getDeviceFile());
            } else if (this.settings.getDevicePath() != null && this.settings.getDevicePath().length() > 0) {
                f2 = this.settings.getFrameGrabber().getConstructor(String.class).newInstance(this.settings.getDevicePath());
            } else {
                int number = this.settings.getDeviceNumber() == null ? 0 : this.settings.getDeviceNumber();
                try {
                    f2 = this.settings.getFrameGrabber().getConstructor(Integer.TYPE).newInstance(number);
                }
                catch (NoSuchMethodException e2) {
                    f2 = this.settings.getFrameGrabber().getConstructor(Integer.class).newInstance(number);
                }
            }
            f2.setFormat(this.settings.getFormat());
            f2.setImageWidth(this.settings.getImageWidth());
            f2.setImageHeight(this.settings.getImageHeight());
            f2.setFrameRate(this.settings.getFrameRate());
            f2.setTriggerMode(this.settings.isTriggerMode());
            f2.setBitsPerPixel(this.settings.getBitsPerPixel());
            f2.setImageMode(this.settings.getImageMode());
            f2.setTimeout(this.settings.getTimeout());
            f2.setNumBuffers(this.settings.getNumBuffers());
            f2.setGamma(this.settings.getResponseGamma());
            f2.setDeinterlace(this.settings.isDeinterlace());
            return f2;
        }
        catch (Throwable t2) {
            if (t2 instanceof InvocationTargetException) {
                t2 = ((InvocationTargetException)t2).getCause();
            }
            if (t2 instanceof FrameGrabber.Exception) {
                throw (FrameGrabber.Exception)t2;
            }
            throw new FrameGrabber.Exception("Failed to create " + this.settings.getFrameGrabber(), t2);
        }
    }

    public static CameraDevice[] read(String filename) throws ProjectiveDevice.Exception {
        FileStorage fs2 = new FileStorage(filename, 0);
        CameraDevice[] devices = CameraDevice.read(fs2);
        fs2.release();
        return devices;
    }

    public static CameraDevice[] read(FileStorage fs2) throws ProjectiveDevice.Exception {
        FileNode node = fs2.get("Cameras");
        FileNodeIterator seq = node.begin();
        int count = (int)seq.remaining();
        CameraDevice[] devices = new CameraDevice[count];
        for (int i2 = 0; i2 < count; ++i2) {
            FileNode n2 = seq.multiply();
            if (!n2.empty()) {
                String name = n2.asBytePointer().getString();
                devices[i2] = new CameraDevice(name, fs2);
            }
            seq.increment();
        }
        return devices;
    }

    public static class CalibratedSettings
    extends ProjectiveDevice.CalibratedSettings
    implements Settings {
        SettingsImplementation si = new SettingsImplementation(){

            @Override
            public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
                CalibratedSettings.this.firePropertyChange(propertyName, oldValue, newValue);
            }
        };

        public CalibratedSettings() {
        }

        public CalibratedSettings(ProjectiveDevice.CalibratedSettings settings) {
            super(settings);
            if (settings instanceof CalibratedSettings) {
                this.si = new SettingsImplementation(((CalibratedSettings)settings).si);
            }
        }

        @Override
        public String getName() {
            return this.si.getName();
        }

        @Override
        public void setName(String name) {
            this.si.setName(name);
        }

        @Override
        public double getResponseGamma() {
            return this.si.getResponseGamma();
        }

        @Override
        public void setResponseGamma(double responseGamma) {
            this.si.setResponseGamma(responseGamma);
        }

        @Override
        public Integer getDeviceNumber() {
            return this.si.getDeviceNumber();
        }

        @Override
        public void setDeviceNumber(Integer deviceNumber) throws PropertyVetoException {
            this.si.setDeviceNumber(deviceNumber);
        }

        @Override
        public File getDeviceFile() {
            return this.si.getDeviceFile();
        }

        @Override
        public void setDeviceFile(File deviceFile) throws PropertyVetoException {
            this.si.setDeviceFile(deviceFile);
        }

        @Override
        public String getDeviceFilename() {
            return this.si.getDeviceFilename();
        }

        @Override
        public void setDeviceFilename(String deviceFilename) throws PropertyVetoException {
            this.si.setDeviceFilename(deviceFilename);
        }

        @Override
        public String getDevicePath() {
            return this.si.getDevicePath();
        }

        @Override
        public void setDevicePath(String devicePath) throws PropertyVetoException {
            this.si.setDevicePath(devicePath);
        }

        @Override
        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.si.getFrameGrabber();
        }

        @Override
        public void setFrameGrabber(Class<? extends FrameGrabber> frameGrabber) {
            this.si.setFrameGrabber(frameGrabber);
        }

        @Override
        public String getDescription() {
            return this.si.getDescription();
        }

        @Override
        public String getFormat() {
            return this.si.getFormat();
        }

        @Override
        public void setFormat(String format) {
            this.si.setFormat(format);
        }

        @Override
        public int getImageWidth() {
            return this.si.getImageWidth();
        }

        @Override
        public void setImageWidth(int imageWidth) {
            this.si.setImageWidth(imageWidth);
        }

        @Override
        public int getImageHeight() {
            return this.si.getImageHeight();
        }

        @Override
        public void setImageHeight(int imageHeight) {
            this.si.setImageHeight(imageHeight);
        }

        @Override
        public double getFrameRate() {
            return this.si.getFrameRate();
        }

        @Override
        public void setFrameRate(double frameRate) {
            this.si.setFrameRate(frameRate);
        }

        @Override
        public boolean isTriggerMode() {
            return this.si.isTriggerMode();
        }

        @Override
        public void setTriggerMode(boolean triggerMode) {
            this.si.setTriggerMode(triggerMode);
        }

        @Override
        public int getBitsPerPixel() {
            return this.si.getBitsPerPixel();
        }

        @Override
        public void setBitsPerPixel(int bitsPerPixel) {
            this.si.setBitsPerPixel(bitsPerPixel);
        }

        @Override
        public FrameGrabber.ImageMode getImageMode() {
            return this.si.getImageMode();
        }

        @Override
        public void setImageMode(FrameGrabber.ImageMode imageMode) {
            this.si.setImageMode(imageMode);
        }

        @Override
        public int getTimeout() {
            return this.si.getTimeout();
        }

        @Override
        public void setTimeout(int timeout) {
            this.si.setTimeout(timeout);
        }

        @Override
        public int getNumBuffers() {
            return this.si.getNumBuffers();
        }

        @Override
        public void setNumBuffers(int numBuffers) {
            this.si.setNumBuffers(numBuffers);
        }

        @Override
        public boolean isDeinterlace() {
            return this.si.isDeinterlace();
        }

        @Override
        public void setDeinterlace(boolean deinterlace) {
            this.si.setDeinterlace(deinterlace);
        }
    }

    public static class CalibrationSettings
    extends ProjectiveDevice.CalibrationSettings
    implements Settings {
        SettingsImplementation si = new SettingsImplementation(){

            @Override
            public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
                CalibrationSettings.this.firePropertyChange(propertyName, oldValue, newValue);
            }
        };

        public CalibrationSettings() {
        }

        public CalibrationSettings(ProjectiveDevice.CalibrationSettings settings) {
            super(settings);
            if (settings instanceof CalibrationSettings) {
                this.si = new SettingsImplementation(((CalibrationSettings)settings).si);
            }
        }

        @Override
        public String getName() {
            return this.si.getName();
        }

        @Override
        public void setName(String name) {
            this.si.setName(name);
        }

        @Override
        public double getResponseGamma() {
            return this.si.getResponseGamma();
        }

        @Override
        public void setResponseGamma(double responseGamma) {
            this.si.setResponseGamma(responseGamma);
        }

        @Override
        public Integer getDeviceNumber() {
            return this.si.getDeviceNumber();
        }

        @Override
        public void setDeviceNumber(Integer deviceNumber) throws PropertyVetoException {
            this.si.setDeviceNumber(deviceNumber);
        }

        @Override
        public File getDeviceFile() {
            return this.si.getDeviceFile();
        }

        @Override
        public void setDeviceFile(File deviceFile) throws PropertyVetoException {
            this.si.setDeviceFile(deviceFile);
        }

        @Override
        public String getDeviceFilename() {
            return this.si.getDeviceFilename();
        }

        @Override
        public void setDeviceFilename(String deviceFilename) throws PropertyVetoException {
            this.si.setDeviceFilename(deviceFilename);
        }

        @Override
        public String getDevicePath() {
            return this.si.getDevicePath();
        }

        @Override
        public void setDevicePath(String devicePath) throws PropertyVetoException {
            this.si.setDevicePath(devicePath);
        }

        @Override
        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.si.getFrameGrabber();
        }

        @Override
        public void setFrameGrabber(Class<? extends FrameGrabber> frameGrabber) {
            this.si.setFrameGrabber(frameGrabber);
        }

        @Override
        public String getDescription() {
            return this.si.getDescription();
        }

        @Override
        public String getFormat() {
            return this.si.getFormat();
        }

        @Override
        public void setFormat(String format) {
            this.si.setFormat(format);
        }

        @Override
        public int getImageWidth() {
            return this.si.getImageWidth();
        }

        @Override
        public void setImageWidth(int imageWidth) {
            this.si.setImageWidth(imageWidth);
        }

        @Override
        public int getImageHeight() {
            return this.si.getImageHeight();
        }

        @Override
        public void setImageHeight(int imageHeight) {
            this.si.setImageHeight(imageHeight);
        }

        @Override
        public double getFrameRate() {
            return this.si.getFrameRate();
        }

        @Override
        public void setFrameRate(double frameRate) {
            this.si.setFrameRate(frameRate);
        }

        @Override
        public boolean isTriggerMode() {
            return this.si.isTriggerMode();
        }

        @Override
        public void setTriggerMode(boolean triggerMode) {
            this.si.setTriggerMode(triggerMode);
        }

        @Override
        public int getBitsPerPixel() {
            return this.si.getBitsPerPixel();
        }

        @Override
        public void setBitsPerPixel(int bitsPerPixel) {
            this.si.setBitsPerPixel(bitsPerPixel);
        }

        @Override
        public FrameGrabber.ImageMode getImageMode() {
            return this.si.getImageMode();
        }

        @Override
        public void setImageMode(FrameGrabber.ImageMode imageMode) {
            this.si.setImageMode(imageMode);
        }

        @Override
        public int getTimeout() {
            return this.si.getTimeout();
        }

        @Override
        public void setTimeout(int timeout) {
            this.si.setTimeout(timeout);
        }

        @Override
        public int getNumBuffers() {
            return this.si.getNumBuffers();
        }

        @Override
        public void setNumBuffers(int numBuffers) {
            this.si.setNumBuffers(numBuffers);
        }

        @Override
        public boolean isDeinterlace() {
            return this.si.isDeinterlace();
        }

        @Override
        public void setDeinterlace(boolean deinterlace) {
            this.si.setDeinterlace(deinterlace);
        }
    }

    public static class SettingsImplementation
    extends ProjectiveDevice.Settings
    implements Settings {
        Integer deviceNumber = null;
        File deviceFile = null;
        String devicePath = null;
        Class<? extends FrameGrabber> frameGrabber = null;
        String format = "";
        int imageWidth = 0;
        int imageHeight = 0;
        double frameRate = 0.0;
        boolean triggerMode = false;
        int bpp = 0;
        FrameGrabber.ImageMode imageMode = FrameGrabber.ImageMode.COLOR;
        int timeout = 10000;
        int numBuffers = 4;
        boolean deinterlace = false;

        public SettingsImplementation() {
            this.name = "Camera  0";
        }

        public SettingsImplementation(ProjectiveDevice.Settings settings) {
            super(settings);
            if (settings instanceof SettingsImplementation) {
                SettingsImplementation s2 = (SettingsImplementation)settings;
                this.deviceNumber = s2.deviceNumber;
                this.deviceFile = s2.deviceFile;
                this.devicePath = s2.devicePath;
                this.frameGrabber = s2.frameGrabber;
                this.format = s2.format;
                this.imageWidth = s2.imageWidth;
                this.imageHeight = s2.imageHeight;
                this.frameRate = s2.frameRate;
                this.triggerMode = s2.triggerMode;
                this.bpp = s2.bpp;
                this.imageMode = s2.imageMode;
                this.timeout = s2.timeout;
                this.numBuffers = s2.numBuffers;
                this.deinterlace = s2.deinterlace;
            }
        }

        @Override
        public Integer getDeviceNumber() {
            return this.deviceNumber;
        }

        @Override
        public void setDeviceNumber(Integer deviceNumber) throws PropertyVetoException {
            if (deviceNumber != null) {
                try {
                    if (this.frameGrabber != null) {
                        try {
                            this.frameGrabber.getConstructor(Integer.TYPE);
                        }
                        catch (NoSuchMethodException e2) {
                            this.frameGrabber.getConstructor(Integer.class);
                        }
                    }
                    this.setDevicePath(null);
                    this.setDeviceFile(null);
                }
                catch (NoSuchMethodException e3) {
                    this.deviceNumber = null;
                    throw new BaseChildSettings.PropertyVetoExceptionThatNetBeansLikes(this.frameGrabber.getSimpleName() + " does not accept a deviceNumber.", new PropertyChangeEvent(this, "deviceNumber", this.deviceNumber, null));
                }
            }
            String oldDescription = this.getDescription();
            this.deviceNumber = deviceNumber;
            this.firePropertyChange("deviceNumber", this.deviceNumber, this.deviceNumber);
            this.firePropertyChange("description", oldDescription, this.getDescription());
        }

        @Override
        public File getDeviceFile() {
            return this.deviceFile;
        }

        @Override
        public void setDeviceFile(File deviceFile) throws PropertyVetoException {
            if (deviceFile != null) {
                try {
                    if (this.frameGrabber != null) {
                        this.frameGrabber.getConstructor(File.class);
                    }
                    this.setDeviceNumber(null);
                    this.setDevicePath(null);
                }
                catch (NoSuchMethodException e2) {
                    deviceFile = null;
                    this.deviceFile = null;
                    throw new BaseChildSettings.PropertyVetoExceptionThatNetBeansLikes(this.frameGrabber.getSimpleName() + " does not accept a deviceFile.", new PropertyChangeEvent(this, "deviceFile", this.deviceFile, null));
                }
            }
            String oldDescription = this.getDescription();
            this.deviceFile = deviceFile;
            this.firePropertyChange("deviceFile", this.deviceFile, this.deviceFile);
            this.firePropertyChange("description", oldDescription, this.getDescription());
        }

        @Override
        public String getDeviceFilename() {
            return this.getDeviceFile() == null ? "" : this.getDeviceFile().getPath();
        }

        @Override
        public void setDeviceFilename(String deviceFilename) throws PropertyVetoException {
            this.setDeviceFile(deviceFilename == null || deviceFilename.length() == 0 ? null : new File(deviceFilename));
        }

        @Override
        public String getDevicePath() {
            return this.devicePath;
        }

        @Override
        public void setDevicePath(String devicePath) throws PropertyVetoException {
            if (devicePath != null) {
                try {
                    if (this.frameGrabber != null) {
                        this.frameGrabber.getConstructor(String.class);
                    }
                    this.setDeviceNumber(null);
                    this.setDeviceFile(null);
                }
                catch (NoSuchMethodException e2) {
                    devicePath = "";
                    this.devicePath = null;
                    throw new BaseChildSettings.PropertyVetoExceptionThatNetBeansLikes(this.frameGrabber.getSimpleName() + " does not accept a devicePath.", new PropertyChangeEvent(this, "devicePath", this.devicePath, null));
                }
            }
            String oldDescription = this.getDescription();
            this.devicePath = devicePath;
            this.firePropertyChange("devicePath", this.devicePath, this.devicePath);
            this.firePropertyChange("description", oldDescription, this.getDescription());
        }

        @Override
        public Class<? extends FrameGrabber> getFrameGrabber() {
            return this.frameGrabber;
        }

        @Override
        public void setFrameGrabber(Class<? extends FrameGrabber> frameGrabber) {
            String oldDescription = this.getDescription();
            this.frameGrabber = frameGrabber;
            this.firePropertyChange("frameGrabber", this.frameGrabber, this.frameGrabber);
            this.firePropertyChange("description", oldDescription, this.getDescription());
            if (frameGrabber == null) {
                this.deviceNumber = null;
                this.firePropertyChange("deviceNumber", this.deviceNumber, null);
                this.deviceFile = null;
                this.firePropertyChange("deviceFile", this.deviceFile, null);
                this.devicePath = null;
                this.firePropertyChange("devicePath", this.devicePath, null);
                return;
            }
            boolean hasDeviceNumber = false;
            try {
                frameGrabber.getConstructor(Integer.TYPE);
                hasDeviceNumber = true;
            }
            catch (NoSuchMethodException e2) {
                try {
                    frameGrabber.getConstructor(Integer.class);
                    hasDeviceNumber = true;
                }
                catch (NoSuchMethodException e22) {
                    this.deviceNumber = null;
                    this.firePropertyChange("deviceNumber", this.deviceNumber, null);
                }
            }
            try {
                frameGrabber.getConstructor(File.class);
            }
            catch (NoSuchMethodException e3) {
                this.deviceFile = null;
                this.firePropertyChange("deviceFile", this.deviceFile, null);
            }
            try {
                frameGrabber.getConstructor(String.class);
            }
            catch (NoSuchMethodException e4) {
                this.devicePath = null;
                this.firePropertyChange("devicePath", this.devicePath, null);
            }
            if (hasDeviceNumber && this.deviceNumber == null && this.deviceFile == null && this.devicePath == null) {
                try {
                    this.setDeviceNumber(0);
                }
                catch (PropertyVetoException propertyVetoException) {
                    // empty catch block
                }
            }
        }

        @Override
        public String getDescription() {
            String[] descriptions = null;
            try {
                Method m2 = this.frameGrabber.getMethod("getDeviceDescriptions", new Class[0]);
                descriptions = (String[])m2.invoke(null, new Object[0]);
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (descriptions != null && this.deviceNumber != null && this.deviceNumber < descriptions.length) {
                return descriptions[this.deviceNumber];
            }
            return "";
        }

        @Override
        public String getFormat() {
            return this.format;
        }

        @Override
        public void setFormat(String format) {
            this.format = format;
        }

        @Override
        public int getImageWidth() {
            return this.imageWidth;
        }

        @Override
        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        @Override
        public int getImageHeight() {
            return this.imageHeight;
        }

        @Override
        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        @Override
        public double getFrameRate() {
            return this.frameRate;
        }

        @Override
        public void setFrameRate(double frameRate) {
            this.frameRate = frameRate;
        }

        @Override
        public boolean isTriggerMode() {
            return this.triggerMode;
        }

        @Override
        public void setTriggerMode(boolean triggerMode) {
            this.triggerMode = triggerMode;
        }

        @Override
        public int getBitsPerPixel() {
            return this.bpp;
        }

        @Override
        public void setBitsPerPixel(int bitsPerPixel) {
            this.bpp = bitsPerPixel;
        }

        @Override
        public FrameGrabber.ImageMode getImageMode() {
            return this.imageMode;
        }

        @Override
        public void setImageMode(FrameGrabber.ImageMode imageMode) {
            this.imageMode = imageMode;
        }

        @Override
        public int getTimeout() {
            return this.timeout;
        }

        @Override
        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        @Override
        public int getNumBuffers() {
            return this.numBuffers;
        }

        @Override
        public void setNumBuffers(int numBuffers) {
            this.numBuffers = numBuffers;
        }

        @Override
        public boolean isDeinterlace() {
            return this.deinterlace;
        }

        @Override
        public void setDeinterlace(boolean deinterlace) {
            this.deinterlace = deinterlace;
        }
    }

    public static interface Settings {
        public String getName();

        public void setName(String var1);

        public double getResponseGamma();

        public void setResponseGamma(double var1);

        public Integer getDeviceNumber();

        public void setDeviceNumber(Integer var1) throws PropertyVetoException;

        public File getDeviceFile();

        public void setDeviceFile(File var1) throws PropertyVetoException;

        public String getDeviceFilename();

        public void setDeviceFilename(String var1) throws PropertyVetoException;

        public String getDevicePath();

        public void setDevicePath(String var1) throws PropertyVetoException;

        public Class<? extends FrameGrabber> getFrameGrabber();

        public void setFrameGrabber(Class<? extends FrameGrabber> var1);

        public String getDescription();

        public String getFormat();

        public void setFormat(String var1);

        public int getImageWidth();

        public void setImageWidth(int var1);

        public int getImageHeight();

        public void setImageHeight(int var1);

        public double getFrameRate();

        public void setFrameRate(double var1);

        public boolean isTriggerMode();

        public void setTriggerMode(boolean var1);

        public int getBitsPerPixel();

        public void setBitsPerPixel(int var1);

        public FrameGrabber.ImageMode getImageMode();

        public void setImageMode(FrameGrabber.ImageMode var1);

        public int getTimeout();

        public void setTimeout(int var1);

        public int getNumBuffers();

        public void setNumBuffers(int var1);

        public boolean isDeinterlace();

        public void setDeinterlace(boolean var1);

        public void addPropertyChangeListener(PropertyChangeListener var1);

        public void removePropertyChangeListener(PropertyChangeListener var1);
    }
}

