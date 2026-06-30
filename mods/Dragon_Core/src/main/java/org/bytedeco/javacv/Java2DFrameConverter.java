/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameConverter;

public class Java2DFrameConverter
extends FrameConverter<BufferedImage> {
    public static final byte[] gamma22 = new byte[256];
    public static final byte[] gamma22inv = new byte[256];
    protected BufferedImage bufferedImage = null;

    @Override
    public Frame convert(BufferedImage img) {
        return this.getFrame(img);
    }

    @Override
    public BufferedImage convert(Frame frame) {
        return this.getBufferedImage(frame);
    }

    public static BufferedImage cloneBufferedImage(BufferedImage source) {
        if (source == null) {
            return null;
        }
        int type = source.getType();
        if (type == 0) {
            return new BufferedImage(source.getColorModel(), source.copyData(null), source.isAlphaPremultiplied(), null);
        }
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), type);
        Graphics g2 = copy.getGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();
        return copy;
    }

    public static int decodeGamma22(int value) {
        return gamma22[value & 0xFF] & 0xFF;
    }

    public static int encodeGamma22(int value) {
        return gamma22inv[value & 0xFF] & 0xFF;
    }

    public static void flipCopyWithGamma(ByteBuffer srcBuf, int srcBufferIndex, int srcStep, ByteBuffer dstBuf, int dstBufferIndex, int dstStep, boolean signed, double gamma, boolean flip, int channels) {
        assert (srcBuf != dstBuf);
        int w2 = Math.min(srcStep, dstStep);
        int srcLine = srcBufferIndex;
        byte[] buffer = new byte[channels];
        for (int dstLine = dstBufferIndex; srcLine < srcBuf.capacity() && dstLine < dstBuf.capacity(); srcLine += srcStep, dstLine += dstStep) {
            byte out;
            int in2;
            int z2;
            int x2;
            srcBufferIndex = flip ? srcBuf.capacity() - srcLine - srcStep : srcLine;
            dstBufferIndex = dstLine;
            w2 = Math.min(Math.min(w2, srcBuf.capacity() - srcBufferIndex), dstBuf.capacity() - dstBufferIndex);
            if (signed) {
                if (channels > 1) {
                    for (x2 = 0; x2 < w2; x2 += channels) {
                        for (z2 = 0; z2 < channels; ++z2) {
                            in2 = srcBuf.get(srcBufferIndex++);
                            byte out2 = gamma == 1.0 ? (byte)in2 : (byte)Math.round(Math.pow((double)in2 / 127.0, gamma) * 127.0);
                            buffer[z2] = out2;
                        }
                        for (z2 = channels - 1; z2 >= 0; --z2) {
                            dstBuf.put(dstBufferIndex++, buffer[z2]);
                        }
                    }
                    continue;
                }
                for (x2 = 0; x2 < w2; ++x2) {
                    byte in3 = srcBuf.get(srcBufferIndex++);
                    out = gamma == 1.0 ? (byte)in3 : (byte)Math.round(Math.pow((double)in3 / 127.0, gamma) * 127.0);
                    dstBuf.put(dstBufferIndex++, out);
                }
                continue;
            }
            if (channels > 1) {
                for (x2 = 0; x2 < w2; x2 += channels) {
                    for (z2 = 0; z2 < channels; ++z2) {
                        int in4 = srcBuf.get(srcBufferIndex++) & 0xFF;
                        out = gamma == 1.0 ? (byte)in4 : (gamma == 2.2 ? gamma22[in4] : (gamma == 0.45454545454545453 ? gamma22inv[in4] : (byte)Math.round(Math.pow((double)in4 / 255.0, gamma) * 255.0)));
                        buffer[z2] = out;
                    }
                    for (z2 = channels - 1; z2 >= 0; --z2) {
                        dstBuf.put(dstBufferIndex++, buffer[z2]);
                    }
                }
                continue;
            }
            for (x2 = 0; x2 < w2; ++x2) {
                in2 = srcBuf.get(srcBufferIndex++) & 0xFF;
                byte out3 = gamma == 1.0 ? (byte)in2 : (gamma == 2.2 ? gamma22[in2] : (gamma == 0.45454545454545453 ? gamma22inv[in2] : (byte)Math.round(Math.pow((double)in2 / 255.0, gamma) * 255.0)));
                dstBuf.put(dstBufferIndex++, out3);
            }
        }
    }

    public static void flipCopyWithGamma(ShortBuffer srcBuf, int srcBufferIndex, int srcStep, ShortBuffer dstBuf, int dstBufferIndex, int dstStep, boolean signed, double gamma, boolean flip, int channels) {
        assert (srcBuf != dstBuf);
        int w2 = Math.min(srcStep, dstStep);
        int srcLine = srcBufferIndex;
        short[] buffer = new short[channels];
        for (int dstLine = dstBufferIndex; srcLine < srcBuf.capacity() && dstLine < dstBuf.capacity(); srcLine += srcStep, dstLine += dstStep) {
            short out;
            int in2;
            short out2;
            short in3;
            int z2;
            int x2;
            srcBufferIndex = flip ? srcBuf.capacity() - srcLine - srcStep : srcLine;
            dstBufferIndex = dstLine;
            w2 = Math.min(Math.min(w2, srcBuf.capacity() - srcBufferIndex), dstBuf.capacity() - dstBufferIndex);
            if (signed) {
                if (channels > 1) {
                    for (x2 = 0; x2 < w2; x2 += channels) {
                        for (z2 = 0; z2 < channels; ++z2) {
                            in3 = srcBuf.get(srcBufferIndex++);
                            out2 = gamma == 1.0 ? (short)in3 : (short)Math.round(Math.pow((double)in3 / 32767.0, gamma) * 32767.0);
                            buffer[z2] = out2;
                        }
                        for (z2 = channels - 1; z2 >= 0; --z2) {
                            dstBuf.put(dstBufferIndex++, buffer[z2]);
                        }
                    }
                    continue;
                }
                for (x2 = 0; x2 < w2; ++x2) {
                    in2 = srcBuf.get(srcBufferIndex++);
                    out = gamma == 1.0 ? (short)in2 : (short)Math.round(Math.pow((double)in2 / 32767.0, gamma) * 32767.0);
                    dstBuf.put(dstBufferIndex++, out);
                }
                continue;
            }
            if (channels > 1) {
                for (x2 = 0; x2 < w2; x2 += channels) {
                    for (z2 = 0; z2 < channels; ++z2) {
                        in3 = srcBuf.get(srcBufferIndex++);
                        out2 = gamma == 1.0 ? (short)in3 : (short)Math.round(Math.pow((double)in3 / 65535.0, gamma) * 65535.0);
                        buffer[z2] = out2;
                    }
                    for (z2 = channels - 1; z2 >= 0; --z2) {
                        dstBuf.put(dstBufferIndex++, buffer[z2]);
                    }
                }
                continue;
            }
            for (x2 = 0; x2 < w2; ++x2) {
                in2 = srcBuf.get(srcBufferIndex++) & 0xFFFF;
                out = gamma == 1.0 ? (short)in2 : (short)Math.round(Math.pow((double)in2 / 65535.0, gamma) * 65535.0);
                dstBuf.put(dstBufferIndex++, out);
            }
        }
    }

    public static void flipCopyWithGamma(IntBuffer srcBuf, int srcBufferIndex, int srcStep, IntBuffer dstBuf, int dstBufferIndex, int dstStep, double gamma, boolean flip, int channels) {
        assert (srcBuf != dstBuf);
        int w2 = Math.min(srcStep, dstStep);
        int srcLine = srcBufferIndex;
        int[] buffer = new int[channels];
        for (int dstLine = dstBufferIndex; srcLine < srcBuf.capacity() && dstLine < dstBuf.capacity(); srcLine += srcStep, dstLine += dstStep) {
            int x2;
            srcBufferIndex = flip ? srcBuf.capacity() - srcLine - srcStep : srcLine;
            dstBufferIndex = dstLine;
            w2 = Math.min(Math.min(w2, srcBuf.capacity() - srcBufferIndex), dstBuf.capacity() - dstBufferIndex);
            if (channels > 1) {
                for (x2 = 0; x2 < w2; x2 += channels) {
                    int z2;
                    for (z2 = 0; z2 < channels; ++z2) {
                        int in2 = srcBuf.get(srcBufferIndex++);
                        int out = gamma == 1.0 ? in2 : (int)Math.round(Math.pow((double)in2 / 2.147483647E9, gamma) * 2.147483647E9);
                        buffer[z2] = out;
                    }
                    for (z2 = channels - 1; z2 >= 0; --z2) {
                        dstBuf.put(dstBufferIndex++, buffer[z2]);
                    }
                }
                continue;
            }
            for (x2 = 0; x2 < w2; ++x2) {
                int in3 = srcBuf.get(srcBufferIndex++);
                int out = gamma == 1.0 ? in3 : (int)Math.round(Math.pow((double)in3 / 2.147483647E9, gamma) * 2.147483647E9);
                dstBuf.put(dstBufferIndex++, out);
            }
        }
    }

    public static void flipCopyWithGamma(FloatBuffer srcBuf, int srcBufferIndex, int srcStep, FloatBuffer dstBuf, int dstBufferIndex, int dstStep, double gamma, boolean flip, int channels) {
        assert (srcBuf != dstBuf);
        int w2 = Math.min(srcStep, dstStep);
        int srcLine = srcBufferIndex;
        float[] buffer = new float[channels];
        for (int dstLine = dstBufferIndex; srcLine < srcBuf.capacity() && dstLine < dstBuf.capacity(); srcLine += srcStep, dstLine += dstStep) {
            int x2;
            srcBufferIndex = flip ? srcBuf.capacity() - srcLine - srcStep : srcLine;
            dstBufferIndex = dstLine;
            w2 = Math.min(Math.min(w2, srcBuf.capacity() - srcBufferIndex), dstBuf.capacity() - dstBufferIndex);
            if (channels > 1) {
                for (x2 = 0; x2 < w2; x2 += channels) {
                    int z2;
                    for (z2 = 0; z2 < channels; ++z2) {
                        float in2 = srcBuf.get(srcBufferIndex++);
                        float out = gamma == 1.0 ? in2 : (float)Math.pow(in2, gamma);
                        buffer[z2] = out;
                    }
                    for (z2 = channels - 1; z2 >= 0; --z2) {
                        dstBuf.put(dstBufferIndex++, buffer[z2]);
                    }
                }
                continue;
            }
            for (x2 = 0; x2 < w2; ++x2) {
                float in3 = srcBuf.get(srcBufferIndex++);
                float out = gamma == 1.0 ? in3 : (float)Math.pow(in3, gamma);
                dstBuf.put(dstBufferIndex++, out);
            }
        }
    }

    public static void flipCopyWithGamma(DoubleBuffer srcBuf, int srcBufferIndex, int srcStep, DoubleBuffer dstBuf, int dstBufferIndex, int dstStep, double gamma, boolean flip, int channels) {
        assert (srcBuf != dstBuf);
        int w2 = Math.min(srcStep, dstStep);
        int srcLine = srcBufferIndex;
        double[] buffer = new double[channels];
        for (int dstLine = dstBufferIndex; srcLine < srcBuf.capacity() && dstLine < dstBuf.capacity(); srcLine += srcStep, dstLine += dstStep) {
            int x2;
            srcBufferIndex = flip ? srcBuf.capacity() - srcLine - srcStep : srcLine;
            dstBufferIndex = dstLine;
            w2 = Math.min(Math.min(w2, srcBuf.capacity() - srcBufferIndex), dstBuf.capacity() - dstBufferIndex);
            if (channels > 1) {
                for (x2 = 0; x2 < w2; x2 += channels) {
                    int z2;
                    for (z2 = 0; z2 < channels; ++z2) {
                        double in2 = srcBuf.get(srcBufferIndex++);
                        double out = gamma == 1.0 ? in2 : Math.pow(in2, gamma);
                        buffer[z2] = out;
                    }
                    for (z2 = channels - 1; z2 >= 0; --z2) {
                        dstBuf.put(dstBufferIndex++, buffer[z2]);
                    }
                }
                continue;
            }
            for (x2 = 0; x2 < w2; ++x2) {
                double in3 = srcBuf.get(srcBufferIndex++);
                double out = gamma == 1.0 ? in3 : Math.pow(in3, gamma);
                dstBuf.put(dstBufferIndex++, out);
            }
        }
    }

    public static void applyGamma(Frame frame, double gamma) {
        Java2DFrameConverter.applyGamma(frame.image[0], frame.imageDepth, frame.imageStride, gamma);
    }

    public static void applyGamma(Buffer buffer, int depth, int stride, double gamma) {
        if (gamma == 1.0) {
            return;
        }
        switch (depth) {
            case 8: {
                Java2DFrameConverter.flipCopyWithGamma(((ByteBuffer)buffer).asReadOnlyBuffer(), 0, stride, (ByteBuffer)buffer, 0, stride, false, gamma, false, 0);
                break;
            }
            case -8: {
                Java2DFrameConverter.flipCopyWithGamma(((ByteBuffer)buffer).asReadOnlyBuffer(), 0, stride, (ByteBuffer)buffer, 0, stride, true, gamma, false, 0);
                break;
            }
            case 16: {
                Java2DFrameConverter.flipCopyWithGamma(((ShortBuffer)buffer).asReadOnlyBuffer(), 0, stride, (ShortBuffer)buffer, 0, stride, false, gamma, false, 0);
                break;
            }
            case -16: {
                Java2DFrameConverter.flipCopyWithGamma(((ShortBuffer)buffer).asReadOnlyBuffer(), 0, stride, (ShortBuffer)buffer, 0, stride, true, gamma, false, 0);
                break;
            }
            case -32: {
                Java2DFrameConverter.flipCopyWithGamma(((IntBuffer)buffer).asReadOnlyBuffer(), 0, stride, (IntBuffer)buffer, 0, stride, gamma, false, 0);
                break;
            }
            case 32: {
                Java2DFrameConverter.flipCopyWithGamma(((FloatBuffer)buffer).asReadOnlyBuffer(), 0, stride, (FloatBuffer)buffer, 0, stride, gamma, false, 0);
                break;
            }
            case 64: {
                Java2DFrameConverter.flipCopyWithGamma(((DoubleBuffer)buffer).asReadOnlyBuffer(), 0, stride, (DoubleBuffer)buffer, 0, stride, gamma, false, 0);
                break;
            }
            default: {
                assert (false);
                break;
            }
        }
    }

    public static void copy(Frame frame, BufferedImage bufferedImage) {
        Java2DFrameConverter.copy(frame, bufferedImage, 1.0);
    }

    public static void copy(Frame frame, BufferedImage bufferedImage, double gamma) {
        Java2DFrameConverter.copy(frame, bufferedImage, gamma, false, null);
    }

    public static void copy(Frame frame, BufferedImage bufferedImage, double gamma, boolean flipChannels, Rectangle roi) {
        Buffer in2 = frame.image[0];
        int bufferIndex = roi == null ? 0 : roi.y * frame.imageStride + roi.x * frame.imageChannels;
        SampleModel sm2 = bufferedImage.getSampleModel();
        WritableRaster r2 = bufferedImage.getRaster();
        DataBuffer out = r2.getDataBuffer();
        int x2 = -r2.getSampleModelTranslateX();
        int y2 = -r2.getSampleModelTranslateY();
        int step = sm2.getWidth() * sm2.getNumBands();
        int channels = sm2.getNumBands();
        if (sm2 instanceof ComponentSampleModel) {
            step = ((ComponentSampleModel)sm2).getScanlineStride();
            channels = ((ComponentSampleModel)sm2).getPixelStride();
        } else if (sm2 instanceof SinglePixelPackedSampleModel) {
            step = ((SinglePixelPackedSampleModel)sm2).getScanlineStride();
            channels = 1;
        } else if (sm2 instanceof MultiPixelPackedSampleModel) {
            step = ((MultiPixelPackedSampleModel)sm2).getScanlineStride();
            channels = ((MultiPixelPackedSampleModel)sm2).getPixelBitStride() / 8;
        }
        int start = y2 * step + x2 * channels;
        if (out instanceof DataBufferByte) {
            byte[] a2 = ((DataBufferByte)out).getData();
            Java2DFrameConverter.flipCopyWithGamma((ByteBuffer)in2, bufferIndex, frame.imageStride, ByteBuffer.wrap(a2), start, step, false, gamma, false, flipChannels ? channels : 0);
        } else if (out instanceof DataBufferDouble) {
            double[] a3 = ((DataBufferDouble)out).getData();
            Java2DFrameConverter.flipCopyWithGamma((DoubleBuffer)in2, bufferIndex, frame.imageStride, DoubleBuffer.wrap(a3), start, step, gamma, false, flipChannels ? channels : 0);
        } else if (out instanceof DataBufferFloat) {
            float[] a4 = ((DataBufferFloat)out).getData();
            Java2DFrameConverter.flipCopyWithGamma((FloatBuffer)in2, bufferIndex, frame.imageStride, FloatBuffer.wrap(a4), start, step, gamma, false, flipChannels ? channels : 0);
        } else if (out instanceof DataBufferInt) {
            int[] a5 = ((DataBufferInt)out).getData();
            int stride = frame.imageStride;
            if (in2 instanceof ByteBuffer) {
                in2 = ((ByteBuffer)in2).order(flipChannels ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).asIntBuffer();
                stride /= 4;
            }
            Java2DFrameConverter.flipCopyWithGamma((IntBuffer)in2, bufferIndex, stride, IntBuffer.wrap(a5), start, step, gamma, false, flipChannels ? channels : 0);
        } else if (out instanceof DataBufferShort) {
            short[] a6 = ((DataBufferShort)out).getData();
            Java2DFrameConverter.flipCopyWithGamma((ShortBuffer)in2, bufferIndex, frame.imageStride, ShortBuffer.wrap(a6), start, step, true, gamma, false, flipChannels ? channels : 0);
        } else if (out instanceof DataBufferUShort) {
            short[] a7 = ((DataBufferUShort)out).getData();
            Java2DFrameConverter.flipCopyWithGamma((ShortBuffer)in2, bufferIndex, frame.imageStride, ShortBuffer.wrap(a7), start, step, false, gamma, false, flipChannels ? channels : 0);
        } else assert (false);
    }

    public static void copy(BufferedImage image, Frame frame) {
        Java2DFrameConverter.copy(image, frame, 1.0);
    }

    public static void copy(BufferedImage image, Frame frame, double gamma) {
        Java2DFrameConverter.copy(image, frame, gamma, false, null);
    }

    public static void copy(BufferedImage image, Frame frame, double gamma, boolean flipChannels, Rectangle roi) {
        Buffer out = frame.image[0];
        int bufferIndex = roi == null ? 0 : roi.y * frame.imageStride + roi.x * frame.imageChannels;
        SampleModel sm2 = image.getSampleModel();
        WritableRaster r2 = image.getRaster();
        DataBuffer in2 = r2.getDataBuffer();
        int x2 = -r2.getSampleModelTranslateX();
        int y2 = -r2.getSampleModelTranslateY();
        int step = sm2.getWidth() * sm2.getNumBands();
        int channels = sm2.getNumBands();
        if (sm2 instanceof ComponentSampleModel) {
            step = ((ComponentSampleModel)sm2).getScanlineStride();
            channels = ((ComponentSampleModel)sm2).getPixelStride();
        } else if (sm2 instanceof SinglePixelPackedSampleModel) {
            step = ((SinglePixelPackedSampleModel)sm2).getScanlineStride();
            channels = 1;
        } else if (sm2 instanceof MultiPixelPackedSampleModel) {
            step = ((MultiPixelPackedSampleModel)sm2).getScanlineStride();
            channels = ((MultiPixelPackedSampleModel)sm2).getPixelBitStride() / 8;
        }
        int start = y2 * step + x2 * channels;
        if (in2 instanceof DataBufferByte) {
            byte[] a2 = ((DataBufferByte)in2).getData();
            Java2DFrameConverter.flipCopyWithGamma(ByteBuffer.wrap(a2), start, step, (ByteBuffer)out, bufferIndex, frame.imageStride, false, gamma, false, flipChannels ? channels : 0);
        } else if (in2 instanceof DataBufferDouble) {
            double[] a3 = ((DataBufferDouble)in2).getData();
            Java2DFrameConverter.flipCopyWithGamma(DoubleBuffer.wrap(a3), start, step, (DoubleBuffer)out, bufferIndex, frame.imageStride, gamma, false, flipChannels ? channels : 0);
        } else if (in2 instanceof DataBufferFloat) {
            float[] a4 = ((DataBufferFloat)in2).getData();
            Java2DFrameConverter.flipCopyWithGamma(FloatBuffer.wrap(a4), start, step, (FloatBuffer)out, bufferIndex, frame.imageStride, gamma, false, flipChannels ? channels : 0);
        } else if (in2 instanceof DataBufferInt) {
            int[] a5 = ((DataBufferInt)in2).getData();
            int stride = frame.imageStride;
            if (out instanceof ByteBuffer) {
                out = ((ByteBuffer)out).order(flipChannels ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).asIntBuffer();
                stride /= 4;
            }
            Java2DFrameConverter.flipCopyWithGamma(IntBuffer.wrap(a5), start, step, (IntBuffer)out, bufferIndex, stride, gamma, false, flipChannels ? channels : 0);
        } else if (in2 instanceof DataBufferShort) {
            short[] a6 = ((DataBufferShort)in2).getData();
            Java2DFrameConverter.flipCopyWithGamma(ShortBuffer.wrap(a6), start, step, (ShortBuffer)out, bufferIndex, frame.imageStride, true, gamma, false, flipChannels ? channels : 0);
        } else if (in2 instanceof DataBufferUShort) {
            short[] a7 = ((DataBufferUShort)in2).getData();
            Java2DFrameConverter.flipCopyWithGamma(ShortBuffer.wrap(a7), start, step, (ShortBuffer)out, bufferIndex, frame.imageStride, false, gamma, false, flipChannels ? channels : 0);
        } else assert (false);
    }

    public static int getBufferedImageType(Frame frame) {
        int type = 0;
        if (frame.imageChannels == 1) {
            if (frame.imageDepth == 8 || frame.imageDepth == -8) {
                type = 10;
            } else if (frame.imageDepth == 16) {
                type = 11;
            }
        } else if (frame.imageChannels == 3) {
            if (frame.imageDepth == 8 || frame.imageDepth == -8) {
                type = 5;
            }
        } else if (frame.imageChannels == 4 && (frame.imageDepth == 8 || frame.imageDepth == -8)) {
            type = 6;
        }
        return type;
    }

    public BufferedImage getBufferedImage(Frame frame) {
        return this.getBufferedImage(frame, 1.0);
    }

    public BufferedImage getBufferedImage(Frame frame, double gamma) {
        return this.getBufferedImage(frame, gamma, false, null);
    }

    public BufferedImage getBufferedImage(Frame frame, double gamma, boolean flipChannels, ColorSpace cs2) {
        if (frame == null || frame.image == null) {
            return null;
        }
        int type = Java2DFrameConverter.getBufferedImageType(frame);
        if (this.bufferedImage == null || this.bufferedImage.getWidth() != frame.imageWidth || this.bufferedImage.getHeight() != frame.imageHeight || this.bufferedImage.getType() != type) {
            BufferedImage bufferedImage = this.bufferedImage = type == 0 || cs2 != null ? null : new BufferedImage(frame.imageWidth, frame.imageHeight, type);
        }
        if (this.bufferedImage == null) {
            boolean alpha = false;
            int[] offsets = null;
            if (frame.imageChannels == 1) {
                alpha = false;
                if (cs2 == null) {
                    cs2 = ColorSpace.getInstance(1003);
                }
                offsets = new int[]{0};
            } else if (frame.imageChannels == 3) {
                alpha = false;
                if (cs2 == null) {
                    cs2 = ColorSpace.getInstance(1004);
                }
                offsets = new int[]{2, 1, 0};
            } else if (frame.imageChannels == 4) {
                alpha = true;
                if (cs2 == null) {
                    cs2 = ColorSpace.getInstance(1004);
                }
                offsets = new int[]{0, 1, 2, 3};
            } else assert (false);
            ComponentColorModel cm2 = null;
            WritableRaster wr2 = null;
            if (frame.imageDepth == 8 || frame.imageDepth == -8) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 0);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(0, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else if (frame.imageDepth == 16) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 1);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(1, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else if (frame.imageDepth == -16) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 2);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(2, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else if (frame.imageDepth == -32) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 3);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(3, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else if (frame.imageDepth == 32) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 4);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(4, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else if (frame.imageDepth == 64) {
                cm2 = new ComponentColorModel(cs2, alpha, false, 1, 5);
                wr2 = Raster.createWritableRaster(new ComponentSampleModel(5, frame.imageWidth, frame.imageHeight, frame.imageChannels, frame.imageStride, offsets), null);
            } else assert (false);
            this.bufferedImage = new BufferedImage(cm2, wr2, false, null);
        }
        if (this.bufferedImage != null) {
            Java2DFrameConverter.copy(frame, this.bufferedImage, gamma, flipChannels, null);
        }
        return this.bufferedImage;
    }

    public Frame getFrame(BufferedImage image) {
        return this.getFrame(image, 1.0);
    }

    public Frame getFrame(BufferedImage image, double gamma) {
        return this.getFrame(image, gamma, false);
    }

    public Frame getFrame(BufferedImage image, double gamma, boolean flipChannels) {
        if (image == null) {
            return null;
        }
        SampleModel sm2 = image.getSampleModel();
        int depth = 0;
        int numChannels = sm2.getNumBands();
        switch (image.getType()) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                depth = 8;
                numChannels = 4;
            }
        }
        if (depth == 0 || numChannels == 0) {
            switch (sm2.getDataType()) {
                case 0: {
                    depth = 8;
                    break;
                }
                case 1: {
                    depth = 16;
                    break;
                }
                case 2: {
                    depth = -16;
                    break;
                }
                case 3: {
                    depth = -32;
                    break;
                }
                case 4: {
                    depth = 32;
                    break;
                }
                case 5: {
                    depth = 64;
                    break;
                }
                default: {
                    assert (false);
                    break;
                }
            }
        }
        if (this.frame == null || this.frame.imageWidth != image.getWidth() || this.frame.imageHeight != image.getHeight() || this.frame.imageDepth != depth || this.frame.imageChannels != numChannels) {
            if (this.frame != null) {
                this.frame.close();
            }
            this.frame = new Frame(image.getWidth(), image.getHeight(), depth, numChannels);
        }
        Java2DFrameConverter.copy(image, this.frame, gamma, flipChannels, null);
        return this.frame;
    }

    static {
        for (int i2 = 0; i2 < 256; ++i2) {
            Java2DFrameConverter.gamma22[i2] = (byte)Math.round(Math.pow((double)i2 / 255.0, 2.2) * 255.0);
            Java2DFrameConverter.gamma22inv[i2] = (byte)Math.round(Math.pow((double)i2 / 255.0, 0.45454545454545453) * 255.0);
        }
    }
}

