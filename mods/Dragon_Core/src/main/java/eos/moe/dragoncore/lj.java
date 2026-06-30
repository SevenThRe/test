/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package eos.moe.dragoncore;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.annotation.Nonnull;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.Frame;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class lj {
    public lj() {
        lj a2;
    }

    public static void ALLATORIxDEMO(@Nonnull Frame frame) {
        if (frame.opaque != null) {
            Pointer[] a2;
            Pointer[] a3 = a2 = (Pointer[])frame.opaque;
            for (Pointer a4 : a2) {
                if (a4 == null || a4.isNull()) continue;
                a4.deallocate();
            }
        }
    }

    public static ByteBuffer ALLATORIxDEMO(ShortBuffer a2, float a3) {
        int a4 = a2.capacity();
        ByteBuffer a5 = ByteBuffer.allocate(a4 << 1);
        a5.order(ByteOrder.LITTLE_ENDIAN);
        for (int a6 = 0; a6 < a4; ++a6) {
            a5.putShort(a6 << 1, (short)((float)a2.get(a6) * a3));
        }
        return a5;
    }

    public static ByteBuffer ALLATORIxDEMO(FloatBuffer a2, float a3) {
        int a4 = a2.capacity();
        ByteBuffer a5 = ByteBuffer.allocate(a4 << 1);
        a5.order(ByteOrder.LITTLE_ENDIAN);
        float a6 = 32768.0f * a3;
        for (int a7 = 0; a7 < a4; ++a7) {
            float a8 = a2.get(a7) * a6;
            if (a8 > a6) {
                a8 = a6;
            }
            if (a8 < -a6) {
                a8 = a6;
            }
            a5.putShort(a7 << 1, (short)a8);
        }
        return a5;
    }

    public static AudioFormat ALLATORIxDEMO(int a2, float a3, int a4, float a5) {
        AudioFormat a6 = null;
        switch (a2) {
            case 0: {
                break;
            }
            case 1: 
            case 3: 
            case 6: 
            case 8: {
                a6 = new AudioFormat(a3, 16, a4, true, false);
                break;
            }
            case 2: 
            case 4: 
            case 5: {
                break;
            }
            case 7: {
                a6 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, a3, 32, a4, a4 * 2, a5, false);
                break;
            }
            case 9: 
            case 10: {
                break;
            }
            case 11: {
                break;
            }
            default: {
                System.out.println("no support format of audio");
            }
        }
        return a6;
    }

    public static int ALLATORIxDEMO(AudioFormat a2) {
        int a3 = -1;
        block0 : switch (a2.getChannels()) {
            case 1: {
                switch (a2.getSampleSizeInBits()) {
                    case 8: {
                        a3 = 4352;
                        break;
                    }
                    case 16: {
                        a3 = 4353;
                    }
                }
                break;
            }
            case 2: {
                switch (a2.getSampleSizeInBits()) {
                    case 8: {
                        a3 = 4354;
                        break block0;
                    }
                    case 16: {
                        a3 = 4355;
                        break block0;
                    }
                }
            }
        }
        return a3;
    }

    public static Line ALLATORIxDEMO(int a2, int a3, float a4, int a5, boolean a6) {
        AudioFormat a7 = null;
        switch (a2) {
            case 0: {
                break;
            }
            case 1: 
            case 3: 
            case 6: 
            case 8: {
                a7 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, a3, 16, a5, a5 * 2, a4, false);
                break;
            }
            case 2: 
            case 4: 
            case 5: {
                break;
            }
            case 7: {
                a7 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, a3, 32, a5, a5 * 4, a4, false);
                break;
            }
            case 9: 
            case 10: {
                break;
            }
            case 11: {
                break;
            }
            default: {
                System.out.println("no support format of audio");
            }
        }
        DataLine.Info a8 = new DataLine.Info(SourceDataLine.class, a7);
        try {
            Mixer.Info[] a9 = AudioSystem.getMixerInfo();
            Mixer a10 = AudioSystem.getMixer(a9[0]);
            if (a6) {
                return a10;
            }
            return AudioSystem.getLine(a8);
        }
        catch (Exception a11) {
            a11.printStackTrace();
            return null;
        }
    }

    public static byte[][] ALLATORIxDEMO(Buffer[] a2, float a3, int a4) {
        byte[] a5 = null;
        byte[] a6 = null;
        Buffer[] a7 = a2;
        switch (a4) {
            case 8: {
                FloatBuffer a8 = (FloatBuffer)a7[0];
                ByteBuffer a9 = lj.ALLATORIxDEMO(a8, a3);
                FloatBuffer a10 = (FloatBuffer)a7[1];
                ByteBuffer a11 = lj.ALLATORIxDEMO(a10, a3);
                a5 = a9.array();
                a6 = a11.array();
                break;
            }
            case 1: {
                ShortBuffer a12 = (ShortBuffer)a7[0];
                short[][] a13 = new short[2][a12.capacity() / 2];
                for (int a14 = 0; a14 < a13.length; ++a14) {
                    a13[0][a14] = a12.get(2 * a14);
                    a13[1][a14] = a12.get(2 * a14 + 1);
                }
                ByteBuffer a15 = lj.ALLATORIxDEMO(ShortBuffer.wrap(a13[0]), a3);
                ByteBuffer a16 = lj.ALLATORIxDEMO(ShortBuffer.wrap(a13[1]), a3);
                return new byte[][]{a15.array(), a16.array()};
            }
            case 3: {
                FloatBuffer a17 = (FloatBuffer)a7[0];
                ByteBuffer a18 = lj.ALLATORIxDEMO(a17, a3);
                a5 = a18.array();
                a6 = (byte[])a5.clone();
                break;
            }
            case 6: {
                ShortBuffer a19 = (ShortBuffer)a7[0];
                ShortBuffer a20 = (ShortBuffer)a7[1];
                ByteBuffer a21 = lj.ALLATORIxDEMO(a19, a3);
                ByteBuffer a22 = lj.ALLATORIxDEMO(a20, a3);
                a5 = a21.array();
                a6 = a22.array();
                break;
            }
        }
        return null;
    }

    public static byte[] c(Buffer[] a2, float a3, int a4) {
        Object a5 = null;
        Object a6 = null;
        Buffer[] a7 = a2;
        switch (a4) {
            case 1: {
                ShortBuffer a8 = (ShortBuffer)a7[0];
                short[] a9 = new short[a8.capacity() / 2];
                for (int a10 = 0; a10 < a9.length; ++a10) {
                    a9[a10] = (short)((a8.get(2 * a10) + a8.get(2 * a10 + 1)) / 2);
                }
                ByteBuffer a11 = lj.ALLATORIxDEMO(ShortBuffer.wrap(a9), a3);
                return a11.array();
            }
        }
        return null;
    }

    public static byte[] ALLATORIxDEMO(Buffer[] a2, float a3, int a4) {
        Buffer[] a5 = a2;
        switch (a4) {
            case 8: {
                FloatBuffer a6 = (FloatBuffer)a5[0];
                ByteBuffer a7 = lj.ALLATORIxDEMO(a6, a3);
                FloatBuffer a8 = (FloatBuffer)a5[1];
                ByteBuffer a9 = lj.ALLATORIxDEMO(a8, a3);
                byte[] a10 = a7.array();
                byte[] a11 = a9.array();
                byte[] a12 = new byte[a10.length + a11.length];
                int a13 = 0;
                for (int a14 = 0; a14 < a10.length; a14 += 2) {
                    for (int a15 = 0; a15 < 2; ++a15) {
                        a12[a15 + 4 * a13] = a10[a14 + a15];
                        a12[a15 + 2 + 4 * a13] = a11[a14 + a15];
                    }
                    ++a13;
                }
                return a12;
            }
            case 1: {
                ShortBuffer a16 = (ShortBuffer)a5[0];
                ByteBuffer a17 = lj.ALLATORIxDEMO(a16, a3);
                byte[] a18 = a17.array();
                return a18;
            }
            case 3: {
                FloatBuffer a19 = (FloatBuffer)a5[0];
                ByteBuffer a20 = lj.ALLATORIxDEMO(a19, a3);
                byte[] a21 = a20.array();
                return a21;
            }
            case 6: {
                ShortBuffer a22 = (ShortBuffer)a5[0];
                ShortBuffer a23 = (ShortBuffer)a5[1];
                ByteBuffer a24 = lj.ALLATORIxDEMO(a22, a3);
                ByteBuffer a25 = lj.ALLATORIxDEMO(a23, a3);
                byte[] a26 = a24.array();
                byte[] a27 = a25.array();
                byte[] a28 = new byte[a26.length + a27.length];
                int a29 = 0;
                for (int a30 = 0; a30 < a26.length; a30 += 2) {
                    for (int a31 = 0; a31 < 2; ++a31) {
                        a28[a31 + 4 * a29] = a26[a30 + a31];
                        a28[a31 + 2 + 4 * a29] = a27[a30 + a31];
                    }
                    ++a29;
                }
                return a28;
            }
        }
        return new byte[0];
    }

    public static void ALLATORIxDEMO(Buffer[] a2, SourceDataLine a3, float a4, int a5) {
        Buffer[] a6 = a2;
        switch (a5) {
            case 8: {
                FloatBuffer a7 = (FloatBuffer)a6[0];
                ByteBuffer a8 = lj.ALLATORIxDEMO(a7, a4);
                FloatBuffer a9 = (FloatBuffer)a6[1];
                ByteBuffer a10 = lj.ALLATORIxDEMO(a9, a4);
                byte[] a11 = a8.array();
                byte[] a12 = a10.array();
                byte[] a13 = new byte[a11.length + a12.length];
                int a14 = 0;
                for (int a15 = 0; a15 < a11.length; a15 += 2) {
                    for (int a16 = 0; a16 < 2; ++a16) {
                        a13[a16 + 4 * a14] = a11[a15 + a16];
                        a13[a16 + 2 + 4 * a14] = a12[a15 + a16];
                    }
                    ++a14;
                }
                a3.write(a13, 0, a13.length);
                break;
            }
            case 1: {
                ShortBuffer a17 = (ShortBuffer)a6[0];
                ByteBuffer a18 = lj.ALLATORIxDEMO(a17, a4);
                byte[] a19 = a18.array();
                a3.write(a19, 0, a19.length);
                break;
            }
            case 3: {
                FloatBuffer a20 = (FloatBuffer)a6[0];
                ByteBuffer a21 = lj.ALLATORIxDEMO(a20, a4);
                byte[] a22 = a21.array();
                a3.write(a22, 0, a22.length);
                break;
            }
            case 6: {
                ShortBuffer a23 = (ShortBuffer)a6[0];
                ShortBuffer a24 = (ShortBuffer)a6[1];
                ByteBuffer a25 = lj.ALLATORIxDEMO(a23, a4);
                ByteBuffer a26 = lj.ALLATORIxDEMO(a24, a4);
                byte[] a27 = a25.array();
                byte[] a28 = a26.array();
                byte[] a29 = new byte[a27.length + a28.length];
                int a30 = 0;
                for (int a31 = 0; a31 < a27.length; a31 += 2) {
                    for (int a32 = 0; a32 < 2; ++a32) {
                        a29[a32 + 4 * a30] = a27[a31 + a32];
                        a29[a32 + 2 + 4 * a30] = a28[a31 + a32];
                    }
                    ++a30;
                }
                a3.write(a29, 0, a29.length);
                break;
            }
            default: {
                JOptionPane.showMessageDialog(null, "no support audio format", "no support audio format", 0);
            }
        }
    }
}

