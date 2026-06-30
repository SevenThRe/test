/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_highgui
 *  org.bytedeco.opencv.global.opencv_imgcodecs
 *  org.bytedeco.opencv.opencv_core.Mat
 */
package org.bytedeco.javacv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public class IPCameraFrameGrabber
extends FrameGrabber {
    private static FrameGrabber.Exception loadingException = null;
    private final FrameConverter converter = new OpenCVFrameConverter.ToMat();
    private final URL url;
    private final int connectionTimeout;
    private final int readTimeout;
    private DataInputStream input;
    private byte[] pixelBuffer = new byte[1024];
    private Mat decoded = null;

    public static void tryLoad() throws FrameGrabber.Exception {
        if (loadingException != null) {
            throw loadingException;
        }
        try {
            Loader.load(opencv_highgui.class);
        }
        catch (Throwable t2) {
            loadingException = new FrameGrabber.Exception("Failed to load " + IPCameraFrameGrabber.class, t2);
            throw loadingException;
        }
    }

    public IPCameraFrameGrabber(URL url, int startTimeout, int grabTimeout, TimeUnit timeUnit) {
        if (url == null) {
            throw new IllegalArgumentException("URL can not be null");
        }
        this.url = url;
        if (timeUnit != null) {
            this.connectionTimeout = IPCameraFrameGrabber.toIntExact(TimeUnit.MILLISECONDS.convert(startTimeout, timeUnit));
            this.readTimeout = IPCameraFrameGrabber.toIntExact(TimeUnit.MILLISECONDS.convert(grabTimeout, timeUnit));
        } else {
            this.connectionTimeout = -1;
            this.readTimeout = -1;
        }
    }

    public IPCameraFrameGrabber(String urlstr, int connectionTimeout, int readTimeout, TimeUnit timeUnit) throws MalformedURLException {
        this(new URL(urlstr), connectionTimeout, readTimeout, timeUnit);
    }

    @Deprecated
    public IPCameraFrameGrabber(String urlstr) throws MalformedURLException {
        this(new URL(urlstr), -1, -1, null);
    }

    @Override
    public void start() throws FrameGrabber.Exception {
        try {
            URLConnection connection = this.url.openConnection();
            if (this.connectionTimeout >= 0) {
                connection.setConnectTimeout(this.connectionTimeout);
            }
            if (this.readTimeout >= 0) {
                connection.setReadTimeout(this.readTimeout);
            }
            this.input = new DataInputStream(connection.getInputStream());
        }
        catch (IOException e2) {
            throw new FrameGrabber.Exception(e2.getMessage(), e2);
        }
    }

    @Override
    public void stop() throws FrameGrabber.Exception {
        if (this.input != null) {
            try {
                this.input.close();
            }
            catch (IOException e2) {
                throw new FrameGrabber.Exception(e2.getMessage(), e2);
            }
            finally {
                this.input = null;
                this.releaseDecoded();
            }
        }
    }

    @Override
    public void trigger() throws FrameGrabber.Exception {
    }

    @Override
    public Frame grab() throws FrameGrabber.Exception {
        try {
            byte[] b2 = this.readImage();
            Mat mat = new Mat(1, b2.length, opencv_core.CV_8UC1, (Pointer)new BytePointer(b2));
            this.releaseDecoded();
            this.decoded = opencv_imgcodecs.imdecode((Mat)mat, (int)1);
            return this.converter.convert(this.decoded);
        }
        catch (IOException e2) {
            throw new FrameGrabber.Exception(e2.getMessage(), e2);
        }
    }

    public BufferedImage grabBufferedImage() throws IOException {
        BufferedImage bi2 = ImageIO.read(new ByteArrayInputStream(this.readImage()));
        return bi2;
    }

    private void releaseDecoded() {
        if (this.decoded != null) {
            this.decoded.release();
            this.decoded = null;
        }
    }

    private byte[] readImage() throws IOException {
        int c2;
        StringBuffer sb2 = new StringBuffer();
        while ((c2 = this.input.read()) != -1) {
            if (c2 <= 0) continue;
            sb2.append((char)c2);
            if (c2 != 13) continue;
            sb2.append((char)this.input.read());
            c2 = this.input.read();
            sb2.append((char)c2);
            if (c2 != 13) continue;
            sb2.append((char)this.input.read());
            break;
        }
        String subheader = sb2.toString().toLowerCase();
        int c0 = subheader.indexOf("content-length: ");
        int c1 = subheader.indexOf(13, c0);
        if (c0 < 0) {
            throw new EOFException("The camera stream ended unexpectedly");
        }
        int contentLength = Integer.parseInt(subheader.substring(c0 += 16, c1).trim());
        this.ensureBufferCapacity(contentLength);
        this.input.readFully(this.pixelBuffer, 0, contentLength);
        this.input.read();
        this.input.read();
        this.input.read();
        this.input.read();
        return this.pixelBuffer;
    }

    @Override
    public void release() throws FrameGrabber.Exception {
    }

    private void ensureBufferCapacity(int desiredCapacity) {
        int capacity;
        for (capacity = this.pixelBuffer.length; capacity < desiredCapacity; capacity *= 2) {
        }
        if (capacity > this.pixelBuffer.length) {
            this.pixelBuffer = new byte[capacity];
        }
    }

    private static int toIntExact(long value) {
        if ((long)((int)value) != value) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)value;
    }
}

