/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 */
package org.bytedeco.javacv;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;

public class AndroidFrameConverter
extends FrameConverter<Bitmap> {
    Bitmap bitmap;
    ByteBuffer buffer;
    byte[] row;

    public Frame convert(byte[] data, int width, int height) {
        if (this.frame == null || this.frame.imageWidth != width || this.frame.imageHeight != height || this.frame.imageChannels != 3) {
            if (this.frame != null) {
                this.frame.close();
            }
            this.frame = new Frame(width, height, 8, 3);
        }
        ByteBuffer out = (ByteBuffer)this.frame.image[0];
        int stride = this.frame.imageStride;
        int offset = height * width;
        for (int i2 = 0; i2 < height; ++i2) {
            for (int j2 = 0; j2 < width; ++j2) {
                int Y = data[i2 * width + j2] & 0xFF;
                int V = data[offset + i2 / 2 * width + 2 * (j2 / 2)] & 0xFF;
                int U = data[offset + i2 / 2 * width + 2 * (j2 / 2) + 1] & 0xFF;
                U -= 128;
                V -= 128;
                if ((Y -= 16) < 0) {
                    Y = 0;
                }
                int B = 1192 * Y + 2066 * U;
                int G = 1192 * Y - 833 * V - 400 * U;
                int R = 1192 * Y + 1634 * V;
                R = Math.min(262143, Math.max(0, R));
                G = Math.min(262143, Math.max(0, G));
                B = Math.min(262143, Math.max(0, B));
                R >>= 10;
                R &= 0xFF;
                G >>= 10;
                B >>= 10;
                out.put(i2 * stride + 3 * j2, (byte)(B &= 0xFF));
                out.put(i2 * stride + 3 * j2 + 1, (byte)(G &= 0xFF));
                out.put(i2 * stride + 3 * j2 + 2, (byte)R);
            }
        }
        return this.frame;
    }

    @Override
    public Frame convert(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int channels = 0;
        switch (bitmap.getConfig()) {
            case ALPHA_8: {
                channels = 1;
                break;
            }
            case RGB_565: 
            case ARGB_4444: {
                channels = 2;
                break;
            }
            case ARGB_8888: {
                channels = 4;
                break;
            }
            default: {
                assert (false);
                break;
            }
        }
        if (this.frame == null || this.frame.imageWidth != bitmap.getWidth() || this.frame.imageStride != bitmap.getRowBytes() || this.frame.imageHeight != bitmap.getHeight() || this.frame.imageChannels != channels) {
            if (this.frame != null) {
                this.frame.close();
            }
            this.frame = new Frame(bitmap.getWidth(), bitmap.getHeight(), 8, channels, bitmap.getRowBytes());
        }
        bitmap.copyPixelsToBuffer(this.frame.image[0].position(0));
        return this.frame;
    }

    ByteBuffer gray2rgba(ByteBuffer in2, int width, int height, int stride, int rowBytes) {
        if (this.buffer == null || this.buffer.capacity() < height * rowBytes) {
            this.buffer = ByteBuffer.allocate(height * rowBytes);
        }
        if (this.row == null || this.row.length != stride) {
            this.row = new byte[stride];
        }
        for (int y2 = 0; y2 < height; ++y2) {
            in2.position(y2 * stride);
            in2.get(this.row);
            for (int x2 = 0; x2 < width; ++x2) {
                byte B = this.row[x2];
                int rgba = (B & 0xFF) << 24 | (B & 0xFF) << 16 | (B & 0xFF) << 8 | 0xFF;
                this.buffer.putInt(y2 * rowBytes + 4 * x2, rgba);
            }
        }
        return this.buffer;
    }

    ByteBuffer bgr2rgba(ByteBuffer in2, int width, int height, int stride, int rowBytes) {
        if (!in2.order().equals(ByteOrder.LITTLE_ENDIAN)) {
            in2 = in2.order(ByteOrder.LITTLE_ENDIAN);
        }
        if (this.buffer == null || this.buffer.capacity() < height * rowBytes) {
            this.buffer = ByteBuffer.allocate(height * rowBytes);
        }
        for (int y2 = 0; y2 < height; ++y2) {
            for (int x2 = 0; x2 < width; ++x2) {
                int rgb;
                if (x2 < width - 1 || y2 < height - 1) {
                    rgb = in2.getInt(y2 * stride + 3 * x2);
                } else {
                    int b2 = in2.get(y2 * stride + 3 * x2) & 0xFF;
                    int g2 = in2.get(y2 * stride + 3 * x2 + 1) & 0xFF;
                    int r2 = in2.get(y2 * stride + 3 * x2 + 2) & 0xFF;
                    rgb = r2 << 16 | g2 << 8 | b2;
                }
                this.buffer.putInt(y2 * rowBytes + 4 * x2, rgb << 8 | 0xFF);
            }
        }
        return this.buffer;
    }

    @Override
    public Bitmap convert(Frame frame) {
        if (frame == null || frame.image == null) {
            return null;
        }
        Bitmap.Config config = null;
        switch (frame.imageChannels) {
            case 2: {
                config = Bitmap.Config.RGB_565;
                break;
            }
            case 1: 
            case 3: 
            case 4: {
                config = Bitmap.Config.ARGB_8888;
                break;
            }
            default: {
                assert (false);
                break;
            }
        }
        if (this.bitmap == null || this.bitmap.getWidth() != frame.imageWidth || this.bitmap.getHeight() != frame.imageHeight || this.bitmap.getConfig() != config) {
            this.bitmap = Bitmap.createBitmap((int)frame.imageWidth, (int)frame.imageHeight, (Bitmap.Config)config);
        }
        ByteBuffer in2 = (ByteBuffer)frame.image[0];
        int width = frame.imageWidth;
        int height = frame.imageHeight;
        int stride = frame.imageStride;
        int rowBytes = this.bitmap.getRowBytes();
        if (frame.imageChannels == 1) {
            this.gray2rgba(in2, width, height, stride, rowBytes);
            this.bitmap.copyPixelsFromBuffer(this.buffer.position(0));
        } else if (frame.imageChannels == 3) {
            this.bgr2rgba(in2, width, height, stride, rowBytes);
            this.bitmap.copyPixelsFromBuffer(this.buffer.position(0));
        } else {
            this.bitmap.copyPixelsFromBuffer(in2.position(0));
        }
        return this.bitmap;
    }
}

