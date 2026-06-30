/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.scene.image.Image
 *  javafx.scene.image.PixelFormat
 *  javafx.scene.image.PixelReader
 *  javafx.scene.image.WritableImage
 *  javafx.scene.image.WritablePixelFormat
 *  javafx.scene.paint.Color
 */
package org.bytedeco.javacv;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;

public class JavaFXFrameConverter
extends FrameConverter<Image> {
    @Override
    public Frame convert(Image f2) {
        throw new UnsupportedOperationException("conversion from Image to Frame not supported yet.");
    }

    @Override
    public Image convert(Frame frame) {
        int iw2 = frame.imageWidth;
        int ih2 = frame.imageHeight;
        FramePixelReader pr2 = new FramePixelReader(frame);
        WritableImage answer = new WritableImage((PixelReader)pr2, iw2, ih2);
        return answer;
    }

    class FramePixelReader
    implements PixelReader {
        Frame frame;

        FramePixelReader(Frame f2) {
            this.frame = f2;
        }

        public PixelFormat getPixelFormat() {
            throw new UnsupportedOperationException("getPixelFormat not supported yet.");
        }

        public int getArgb(int x2, int y2) {
            throw new UnsupportedOperationException("getArgb not supported yet.");
        }

        public Color getColor(int x2, int y2) {
            throw new UnsupportedOperationException("getColor not supported yet.");
        }

        public <T extends Buffer> void getPixels(int x2, int y2, int w2, int h2, WritablePixelFormat<T> pixelformat, T buffer, int scanlineStride) {
            int fss = this.frame.imageStride;
            if (this.frame.imageChannels != 3) {
                throw new UnsupportedOperationException("We only support frames with imageChannels = 3 (BGR)");
            }
            if (buffer instanceof ByteBuffer) {
                ByteBuffer bb2 = (ByteBuffer)buffer;
                ByteBuffer b2 = (ByteBuffer)this.frame.image[0];
                for (int i2 = y2; i2 < y2 + h2; ++i2) {
                    for (int j2 = x2; j2 < x2 + w2; ++j2) {
                        int base = 3 * j2;
                        bb2.put(b2.get(fss * i2 + base));
                        bb2.put(b2.get(fss * i2 + base + 1));
                        bb2.put(b2.get(fss * i2 + base + 2));
                        bb2.put((byte)-1);
                    }
                }
            } else {
                throw new UnsupportedOperationException("We only support bytebuffers at the moment");
            }
        }

        public void getPixels(int x2, int y2, int w2, int h2, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer, int offset, int scanlineStride) {
            throw new UnsupportedOperationException("getPixels<ByteBuffer> Not supported yet.");
        }

        public void getPixels(int x2, int y2, int w2, int h2, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer, int offset, int scanlineStride) {
            throw new UnsupportedOperationException("getPixels<IntBuffer>Not supported yet.");
        }
    }
}

