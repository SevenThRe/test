/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.FileNode
 *  org.bytedeco.opencv.opencv_core.FileNodeIterator
 *  org.bytedeco.opencv.opencv_core.FileStorage
 */
package org.bytedeco.javacv;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileNodeIterator;
import org.bytedeco.opencv.opencv_core.FileStorage;

public class ProjectorDevice
extends ProjectiveDevice {
    private Settings settings;
    private static ThreadLocal<CvMat> B4x3 = CvMat.createThreadLocal((int)4, (int)3);
    private static ThreadLocal<CvMat> x23x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> x34x1 = CvMat.createThreadLocal((int)4, (int)1);

    public ProjectorDevice(String name) {
        super(name);
    }

    public ProjectorDevice(String name, String filename) throws ProjectiveDevice.Exception {
        super(name, filename);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public ProjectorDevice(String name, FileStorage fs2) throws ProjectiveDevice.Exception {
        super(name, fs2);
        this.settings.setImageWidth(this.imageWidth);
        this.settings.setImageHeight(this.imageHeight);
    }

    public ProjectorDevice(Settings settings) throws ProjectiveDevice.Exception {
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
            this.settings.setName("Projector " + String.format("%2d", this.settings.getScreenNumber()));
        }
    }

    public CanvasFrame createCanvasFrame() throws CanvasFrame.Exception {
        if (this.settings.getScreenNumber() < 0) {
            return null;
        }
        DisplayMode d2 = new DisplayMode(this.settings.getImageWidth(), this.settings.getImageHeight(), this.settings.getBitDepth(), this.settings.getRefreshRate());
        CanvasFrame c2 = null;
        Throwable cause = null;
        try {
            c2 = Class.forName(CanvasFrame.class.getPackage().getName() + (this.settings.isUseOpenGL() ? ".GLCanvasFrame" : ".CanvasFrame")).asSubclass(CanvasFrame.class).getConstructor(String.class, Integer.TYPE, DisplayMode.class, Double.TYPE).newInstance(this.settings.getName(), this.settings.getScreenNumber(), d2, this.settings.getResponseGamma());
        }
        catch (ClassNotFoundException ex2) {
            cause = ex2;
        }
        catch (InstantiationException ex3) {
            cause = ex3;
        }
        catch (IllegalAccessException ex4) {
            cause = ex4;
        }
        catch (IllegalArgumentException ex5) {
            cause = ex5;
        }
        catch (NoSuchMethodException ex6) {
            cause = ex6;
        }
        catch (InvocationTargetException ex7) {
            cause = ex7.getCause();
        }
        if (cause != null) {
            if (cause instanceof CanvasFrame.Exception) {
                throw (CanvasFrame.Exception)cause;
            }
            throw new CanvasFrame.Exception("Failed to create CanvasFrame", cause);
        }
        c2.setLatency(this.settings.getLatency());
        Dimension size = c2.getCanvasSize();
        if (size.width != this.imageWidth || size.height != this.imageHeight) {
            this.rescale(size.width, size.height);
        }
        return c2;
    }

    public double getAttenuation(double x2, double y2, CvMat n2, double d2) {
        CvMat B = B4x3.get();
        CvMat x22 = x23x1.get();
        CvMat x3 = x34x1.get();
        this.getBackProjectionMatrix(n2, d2, B);
        x22.put(new double[]{x2, y2, 1.0});
        opencv_core.cvMatMul((CvArr)B, (CvArr)x22, (CvArr)x3);
        opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.T, (double)-1.0, null, (double)0.0, (CvArr)x22, (int)1);
        x3.rows(3);
        opencv_core.cvAddWeighted((CvArr)x3, (double)(1.0 / x3.get(3)), (CvArr)x22, (double)-1.0, (double)0.0, (CvArr)x22);
        double distance2 = opencv_core.cvDotProduct((CvArr)x22, (CvArr)x22);
        double distance = Math.sqrt(distance2);
        double cosangle = -Math.signum(d2) * opencv_core.cvDotProduct((CvArr)x22, (CvArr)n2) / (distance * Math.sqrt(opencv_core.cvDotProduct((CvArr)n2, (CvArr)n2)));
        double attenuation = cosangle / distance2;
        x3.rows(4);
        return attenuation;
    }

    public static ProjectorDevice[] read(String filename) throws ProjectiveDevice.Exception {
        FileStorage fs2 = new FileStorage(filename, 0);
        ProjectorDevice[] devices = ProjectorDevice.read(fs2);
        fs2.release();
        return devices;
    }

    public static ProjectorDevice[] read(FileStorage fs2) throws ProjectiveDevice.Exception {
        FileNode node = fs2.get("Projectors");
        FileNodeIterator seq = node.begin();
        int count = (int)seq.remaining();
        ProjectorDevice[] devices = new ProjectorDevice[count];
        for (int i2 = 0; i2 < count; ++i2) {
            FileNode n2 = seq.multiply();
            if (!n2.empty()) {
                String name = n2.asBytePointer().getString();
                devices[i2] = new ProjectorDevice(name, fs2);
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
        public int getScreenNumber() {
            return this.si.getScreenNumber();
        }

        @Override
        public void setScreenNumber(int screenNumber) {
            this.si.setScreenNumber(screenNumber);
        }

        @Override
        public long getLatency() {
            return this.si.getLatency();
        }

        @Override
        public void setLatency(long latency) {
            this.si.setLatency(latency);
        }

        @Override
        public String getDescription() {
            return this.si.getDescription();
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
        public int getBitDepth() {
            return this.si.getBitDepth();
        }

        @Override
        public void setBitDepth(int bitDepth) {
            this.si.setBitDepth(bitDepth);
        }

        @Override
        public int getRefreshRate() {
            return this.si.getRefreshRate();
        }

        @Override
        public void setRefreshRate(int refreshRate) {
            this.si.setRefreshRate(refreshRate);
        }

        @Override
        public boolean isUseOpenGL() {
            return this.si.isUseOpenGL();
        }

        @Override
        public void setUseOpenGL(boolean useOpenGL) {
            this.si.setUseOpenGL(useOpenGL);
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
        double brightnessBackground = 0.0;
        double brightnessForeground = 1.0;

        public CalibrationSettings() {
        }

        public CalibrationSettings(ProjectiveDevice.CalibrationSettings settings) {
            super(settings);
            if (settings instanceof CalibrationSettings) {
                CalibrationSettings s2 = (CalibrationSettings)settings;
                this.si = new SettingsImplementation(s2.si);
                this.brightnessBackground = s2.brightnessBackground;
                this.brightnessForeground = s2.brightnessForeground;
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
        public int getScreenNumber() {
            return this.si.getScreenNumber();
        }

        @Override
        public void setScreenNumber(int screenNumber) {
            this.si.setScreenNumber(screenNumber);
        }

        @Override
        public long getLatency() {
            return this.si.getLatency();
        }

        @Override
        public void setLatency(long latency) {
            this.si.setLatency(latency);
        }

        @Override
        public String getDescription() {
            return this.si.getDescription();
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
        public int getBitDepth() {
            return this.si.getBitDepth();
        }

        @Override
        public void setBitDepth(int bitDepth) {
            this.si.setBitDepth(bitDepth);
        }

        @Override
        public int getRefreshRate() {
            return this.si.getRefreshRate();
        }

        @Override
        public void setRefreshRate(int refreshRate) {
            this.si.setRefreshRate(refreshRate);
        }

        @Override
        public boolean isUseOpenGL() {
            return this.si.isUseOpenGL();
        }

        @Override
        public void setUseOpenGL(boolean useOpenGL) {
            this.si.setUseOpenGL(useOpenGL);
        }

        public double getBrightnessBackground() {
            return this.brightnessBackground;
        }

        public void setBrightnessBackground(double brightnessBackground) {
            this.brightnessBackground = brightnessBackground;
            this.firePropertyChange("brightnessBackground", this.brightnessBackground, this.brightnessBackground);
        }

        public double getBrightnessForeground() {
            return this.brightnessForeground;
        }

        public void setBrightnessForeground(double brightnessForeground) {
            this.brightnessForeground = brightnessForeground;
            this.firePropertyChange("brightnessForeground", this.brightnessForeground, this.brightnessForeground);
        }
    }

    public static class SettingsImplementation
    extends ProjectiveDevice.Settings
    implements Settings {
        int screenNumber = CanvasFrame.getScreenDevices().length > 1 ? 1 : 0;
        long latency = 200L;
        int imageWidth = 0;
        int imageHeight = 0;
        int bitDepth = 0;
        int refreshRate = 0;
        private boolean useOpenGL = false;

        public SettingsImplementation() {
            this.name = "Projector  0";
            this.setScreenNumber(this.screenNumber);
        }

        public SettingsImplementation(ProjectiveDevice.Settings settings) {
            super(settings);
            if (settings instanceof SettingsImplementation) {
                SettingsImplementation s2 = (SettingsImplementation)settings;
                this.screenNumber = s2.screenNumber;
                this.latency = s2.latency;
                this.imageWidth = s2.imageWidth;
                this.imageHeight = s2.imageHeight;
                this.bitDepth = s2.bitDepth;
                this.refreshRate = s2.refreshRate;
                this.useOpenGL = s2.useOpenGL;
            }
        }

        @Override
        public int getScreenNumber() {
            return this.screenNumber;
        }

        @Override
        public void setScreenNumber(int screenNumber) {
            DisplayMode d2 = CanvasFrame.getDisplayMode(screenNumber);
            String oldDescription = this.getDescription();
            this.screenNumber = screenNumber;
            this.firePropertyChange("screenNumber", this.screenNumber, this.screenNumber);
            this.firePropertyChange("description", oldDescription, this.getDescription());
            this.imageWidth = d2 == null ? 0 : d2.getWidth();
            this.firePropertyChange("imageWidth", this.imageWidth, this.imageWidth);
            this.imageHeight = d2 == null ? 0 : d2.getHeight();
            this.firePropertyChange("imageHeight", this.imageHeight, this.imageHeight);
            this.bitDepth = d2 == null ? 0 : d2.getBitDepth();
            this.firePropertyChange("bitDepth", this.bitDepth, this.bitDepth);
            this.refreshRate = d2 == null ? 0 : d2.getRefreshRate();
            this.firePropertyChange("refreshRate", this.refreshRate, this.refreshRate);
            this.responseGamma = CanvasFrame.getGamma(screenNumber);
            this.firePropertyChange("responseGamma", this.responseGamma, this.responseGamma);
        }

        @Override
        public long getLatency() {
            return this.latency;
        }

        @Override
        public void setLatency(long latency) {
            this.latency = latency;
        }

        @Override
        public String getDescription() {
            String[] descriptions = null;
            descriptions = CanvasFrame.getScreenDescriptions();
            if (descriptions != null && this.screenNumber >= 0 && this.screenNumber < descriptions.length) {
                return descriptions[this.screenNumber];
            }
            return "";
        }

        @Override
        public int getImageWidth() {
            return this.imageWidth;
        }

        @Override
        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
            this.firePropertyChange("imageWidth", this.imageWidth, this.imageWidth);
        }

        @Override
        public int getImageHeight() {
            return this.imageHeight;
        }

        @Override
        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
            this.firePropertyChange("imageHeight", this.imageHeight, this.imageHeight);
        }

        @Override
        public int getBitDepth() {
            return this.bitDepth;
        }

        @Override
        public void setBitDepth(int bitDepth) {
            this.bitDepth = bitDepth;
        }

        @Override
        public int getRefreshRate() {
            return this.refreshRate;
        }

        @Override
        public void setRefreshRate(int refreshRate) {
            this.refreshRate = refreshRate;
        }

        @Override
        public boolean isUseOpenGL() {
            return this.useOpenGL;
        }

        @Override
        public void setUseOpenGL(boolean useOpenGL) {
            this.useOpenGL = useOpenGL;
        }
    }

    public static interface Settings {
        public String getName();

        public void setName(String var1);

        public double getResponseGamma();

        public void setResponseGamma(double var1);

        public int getScreenNumber();

        public void setScreenNumber(int var1);

        public long getLatency();

        public void setLatency(long var1);

        public String getDescription();

        public int getImageWidth();

        public void setImageWidth(int var1);

        public int getImageHeight();

        public void setImageHeight(int var1);

        public int getBitDepth();

        public void setBitDepth(int var1);

        public int getRefreshRate();

        public void setRefreshRate(int var1);

        public boolean isUseOpenGL();

        public void setUseOpenGL(boolean var1);

        public void addPropertyChangeListener(PropertyChangeListener var1);

        public void removePropertyChangeListener(PropertyChangeListener var1);
    }
}

