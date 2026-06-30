/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Crc16;

public final class Header {
    public static final int[][] frequencies = new int[][]{{22050, 24000, 16000, 1}, {44100, 48000, 32000, 1}, {11025, 12000, 8000, 1}};
    public static final int MPEG2_LSF = 0;
    public static final int MPEG25_LSF = 2;
    public static final int MPEG1 = 1;
    public static final int STEREO = 0;
    public static final int JOINT_STEREO = 1;
    public static final int DUAL_CHANNEL = 2;
    public static final int SINGLE_CHANNEL = 3;
    public static final int FOURTYFOUR_POINT_ONE = 0;
    public static final int FOURTYEIGHT = 1;
    public static final int THIRTYTWO = 2;
    private int h_layer;
    private int h_protection_bit;
    private int h_bitrate_index;
    private int h_padding_bit;
    private int h_mode_extension;
    private int h_version;
    private int h_mode;
    private int h_sample_frequency;
    private int h_number_of_subbands;
    private int h_intensity_stereo_bound;
    private boolean h_copyright;
    private boolean h_original;
    private double[] h_vbr_time_per_frame = new double[]{-1.0, 384.0, 1152.0, 1152.0};
    private boolean h_vbr;
    private int h_vbr_frames;
    private int h_vbr_scale;
    private int h_vbr_bytes;
    private byte[] h_vbr_toc;
    private byte syncmode = Bitstream.INITIAL_SYNC;
    private Crc16 crc;
    public short checksum;
    public int framesize;
    public int nSlots;
    private int _headerstring = -1;
    public static final int[][][] bitrates = new int[][][]{new int[][]{{0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0}, {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}, {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}}, new int[][]{{0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0}, {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0}, {0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0}}, new int[][]{{0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0}, {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}, {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}}};
    public static final String[][][] bitrate_str = new String[][][]{{{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden"}, {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}, {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}}, {{"free format", "32 kbit/s", "64 kbit/s", "96 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "288 kbit/s", "320 kbit/s", "352 kbit/s", "384 kbit/s", "416 kbit/s", "448 kbit/s", "forbidden"}, {"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "384 kbit/s", "forbidden"}, {"free format", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "forbidden"}}, {{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden"}, {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}, {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}}};

    Header() {
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("Layer ");
        stringBuffer.append(this.layer_string());
        stringBuffer.append(" frame ");
        stringBuffer.append(this.mode_string());
        stringBuffer.append(' ');
        stringBuffer.append(this.version_string());
        if (!this.checksums()) {
            stringBuffer.append(" no");
        }
        stringBuffer.append(" checksums");
        stringBuffer.append(' ');
        stringBuffer.append(this.sample_frequency_string());
        stringBuffer.append(',');
        stringBuffer.append(' ');
        stringBuffer.append(this.bitrate_string());
        String string = stringBuffer.toString();
        return string;
    }

    void read_header(Bitstream bitstream, Crc16[] crc16Array) throws BitstreamException {
        int n;
        boolean bl = false;
        do {
            this._headerstring = n = bitstream.syncHeader(this.syncmode);
            if (this.syncmode == Bitstream.INITIAL_SYNC) {
                this.h_version = n >>> 19 & 1;
                if ((n >>> 20 & 1) == 0) {
                    if (this.h_version == 0) {
                        this.h_version = 2;
                    } else {
                        throw bitstream.newBitstreamException(256);
                    }
                }
                if ((this.h_sample_frequency = n >>> 10 & 3) == 3) {
                    throw bitstream.newBitstreamException(256);
                }
            }
            this.h_layer = 4 - (n >>> 17) & 3;
            this.h_protection_bit = n >>> 16 & 1;
            this.h_bitrate_index = n >>> 12 & 0xF;
            this.h_padding_bit = n >>> 9 & 1;
            this.h_mode = n >>> 6 & 3;
            this.h_mode_extension = n >>> 4 & 3;
            this.h_intensity_stereo_bound = this.h_mode == 1 ? (this.h_mode_extension << 2) + 4 : 0;
            if ((n >>> 3 & 1) == 1) {
                this.h_copyright = true;
            }
            if ((n >>> 2 & 1) == 1) {
                this.h_original = true;
            }
            if (this.h_layer == 1) {
                this.h_number_of_subbands = 32;
            } else {
                int n2 = this.h_bitrate_index;
                if (this.h_mode != 3) {
                    n2 = n2 == 4 ? 1 : (n2 -= 4);
                }
                this.h_number_of_subbands = n2 == 1 || n2 == 2 ? (this.h_sample_frequency == 2 ? 12 : 8) : (this.h_sample_frequency == 1 || n2 >= 3 && n2 <= 5 ? 27 : 30);
            }
            if (this.h_intensity_stereo_bound > this.h_number_of_subbands) {
                this.h_intensity_stereo_bound = this.h_number_of_subbands;
            }
            this.calculate_framesize();
            int n3 = bitstream.read_frame_data(this.framesize);
            if (this.framesize >= 0 && n3 != this.framesize) {
                throw bitstream.newBitstreamException(261);
            }
            if (bitstream.isSyncCurrentPosition(this.syncmode)) {
                if (this.syncmode == Bitstream.INITIAL_SYNC) {
                    this.syncmode = Bitstream.STRICT_SYNC;
                    bitstream.set_syncword(n & 0xFFF80CC0);
                }
                bl = true;
                continue;
            }
            bitstream.unreadFrame();
        } while (!bl);
        bitstream.parse_frame();
        if (this.h_protection_bit == 0) {
            this.checksum = (short)bitstream.get_bits(16);
            if (this.crc == null) {
                this.crc = new Crc16();
            }
            this.crc.add_bits(n, 16);
            crc16Array[0] = this.crc;
        } else {
            crc16Array[0] = null;
        }
        if (this.h_sample_frequency == 0) {
            // empty if block
        }
    }

    void parseVBR(byte[] byArray) throws BitstreamException {
        String string = "Xing";
        byte[] byArray2 = new byte[4];
        int n = 0;
        n = this.h_version == 1 ? (this.h_mode == 3 ? 17 : 32) : (this.h_mode == 3 ? 9 : 17);
        try {
            System.arraycopy(byArray, n, byArray2, 0, 4);
            if (string.equals(new String(byArray2))) {
                this.h_vbr = true;
                this.h_vbr_frames = -1;
                this.h_vbr_bytes = -1;
                this.h_vbr_scale = -1;
                this.h_vbr_toc = new byte[100];
                int n2 = 4;
                byte[] byArray3 = new byte[4];
                System.arraycopy(byArray, n + n2, byArray3, 0, byArray3.length);
                n2 += byArray3.length;
                if ((byArray3[3] & 1) != 0) {
                    System.arraycopy(byArray, n + n2, byArray2, 0, byArray2.length);
                    this.h_vbr_frames = byArray2[0] << 24 & 0xFF000000 | byArray2[1] << 16 & 0xFF0000 | byArray2[2] << 8 & 0xFF00 | byArray2[3] & 0xFF;
                    n2 += 4;
                }
                if ((byArray3[3] & 2) != 0) {
                    System.arraycopy(byArray, n + n2, byArray2, 0, byArray2.length);
                    this.h_vbr_bytes = byArray2[0] << 24 & 0xFF000000 | byArray2[1] << 16 & 0xFF0000 | byArray2[2] << 8 & 0xFF00 | byArray2[3] & 0xFF;
                    n2 += 4;
                }
                if ((byArray3[3] & 4) != 0) {
                    System.arraycopy(byArray, n + n2, this.h_vbr_toc, 0, this.h_vbr_toc.length);
                    n2 += this.h_vbr_toc.length;
                }
                if ((byArray3[3] & 8) != 0) {
                    System.arraycopy(byArray, n + n2, byArray2, 0, byArray2.length);
                    this.h_vbr_scale = byArray2[0] << 24 & 0xFF000000 | byArray2[1] << 16 & 0xFF0000 | byArray2[2] << 8 & 0xFF00 | byArray2[3] & 0xFF;
                    n2 += 4;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new BitstreamException("XingVBRHeader Corrupted", (Throwable)arrayIndexOutOfBoundsException);
        }
        String string2 = "VBRI";
        n = 32;
        try {
            System.arraycopy(byArray, n, byArray2, 0, 4);
            if (string2.equals(new String(byArray2))) {
                this.h_vbr = true;
                this.h_vbr_frames = -1;
                this.h_vbr_bytes = -1;
                this.h_vbr_scale = -1;
                this.h_vbr_toc = new byte[100];
                int n3 = 10;
                System.arraycopy(byArray, n + n3, byArray2, 0, byArray2.length);
                this.h_vbr_bytes = byArray2[0] << 24 & 0xFF000000 | byArray2[1] << 16 & 0xFF0000 | byArray2[2] << 8 & 0xFF00 | byArray2[3] & 0xFF;
                System.arraycopy(byArray, n + (n3 += 4), byArray2, 0, byArray2.length);
                this.h_vbr_frames = byArray2[0] << 24 & 0xFF000000 | byArray2[1] << 16 & 0xFF0000 | byArray2[2] << 8 & 0xFF00 | byArray2[3] & 0xFF;
                n3 += 4;
            }
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new BitstreamException("VBRIVBRHeader Corrupted", (Throwable)arrayIndexOutOfBoundsException);
        }
    }

    public int version() {
        return this.h_version;
    }

    public int layer() {
        return this.h_layer;
    }

    public int bitrate_index() {
        return this.h_bitrate_index;
    }

    public int sample_frequency() {
        return this.h_sample_frequency;
    }

    public int frequency() {
        return frequencies[this.h_version][this.h_sample_frequency];
    }

    public int mode() {
        return this.h_mode;
    }

    public boolean checksums() {
        return this.h_protection_bit == 0;
    }

    public boolean copyright() {
        return this.h_copyright;
    }

    public boolean original() {
        return this.h_original;
    }

    public boolean vbr() {
        return this.h_vbr;
    }

    public int vbr_scale() {
        return this.h_vbr_scale;
    }

    public byte[] vbr_toc() {
        return this.h_vbr_toc;
    }

    public boolean checksum_ok() {
        return this.checksum == this.crc.checksum();
    }

    public boolean padding() {
        return this.h_padding_bit != 0;
    }

    public int slots() {
        return this.nSlots;
    }

    public int mode_extension() {
        return this.h_mode_extension;
    }

    public int calculate_framesize() {
        if (this.h_layer == 1) {
            this.framesize = 12 * bitrates[this.h_version][0][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
            if (this.h_padding_bit != 0) {
                ++this.framesize;
            }
            this.framesize <<= 2;
            this.nSlots = 0;
        } else {
            this.framesize = 144 * bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
            if (this.h_version == 0 || this.h_version == 2) {
                this.framesize >>= 1;
            }
            if (this.h_padding_bit != 0) {
                ++this.framesize;
            }
            this.nSlots = this.h_layer == 3 ? (this.h_version == 1 ? this.framesize - (this.h_mode == 3 ? 17 : 32) - (this.h_protection_bit != 0 ? 0 : 2) - 4 : this.framesize - (this.h_mode == 3 ? 9 : 17) - (this.h_protection_bit != 0 ? 0 : 2) - 4) : 0;
        }
        this.framesize -= 4;
        return this.framesize;
    }

    public int max_number_of_frames(int n) {
        if (this.h_vbr) {
            return this.h_vbr_frames;
        }
        if (this.framesize + 4 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 4 - this.h_padding_bit);
    }

    public int min_number_of_frames(int n) {
        if (this.h_vbr) {
            return this.h_vbr_frames;
        }
        if (this.framesize + 5 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 5 - this.h_padding_bit);
    }

    public float ms_per_frame() {
        if (this.h_vbr) {
            double d = this.h_vbr_time_per_frame[this.layer()] / (double)this.frequency();
            if (this.h_version == 0 || this.h_version == 2) {
                d /= 2.0;
            }
            return (float)(d * 1000.0);
        }
        float[][] fArrayArray = new float[][]{{8.707483f, 8.0f, 12.0f}, {26.12245f, 24.0f, 36.0f}, {26.12245f, 24.0f, 36.0f}};
        return fArrayArray[this.h_layer - 1][this.h_sample_frequency];
    }

    public float total_ms(int n) {
        return (float)this.max_number_of_frames(n) * this.ms_per_frame();
    }

    public int getSyncHeader() {
        return this._headerstring;
    }

    public String layer_string() {
        switch (this.h_layer) {
            case 1: {
                return "I";
            }
            case 2: {
                return "II";
            }
            case 3: {
                return "III";
            }
        }
        return null;
    }

    public String bitrate_string() {
        if (this.h_vbr) {
            return Integer.toString(this.bitrate() / 1000) + " kb/s";
        }
        return bitrate_str[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }

    public int bitrate() {
        if (this.h_vbr) {
            return (int)((float)(this.h_vbr_bytes * 8) / (this.ms_per_frame() * (float)this.h_vbr_frames)) * 1000;
        }
        return bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }

    public int bitrate_instant() {
        return bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }

    public String sample_frequency_string() {
        switch (this.h_sample_frequency) {
            case 2: {
                if (this.h_version == 1) {
                    return "32 kHz";
                }
                if (this.h_version == 0) {
                    return "16 kHz";
                }
                return "8 kHz";
            }
            case 0: {
                if (this.h_version == 1) {
                    return "44.1 kHz";
                }
                if (this.h_version == 0) {
                    return "22.05 kHz";
                }
                return "11.025 kHz";
            }
            case 1: {
                if (this.h_version == 1) {
                    return "48 kHz";
                }
                if (this.h_version == 0) {
                    return "24 kHz";
                }
                return "12 kHz";
            }
        }
        return null;
    }

    public String mode_string() {
        switch (this.h_mode) {
            case 0: {
                return "Stereo";
            }
            case 1: {
                return "Joint stereo";
            }
            case 2: {
                return "Dual channel";
            }
            case 3: {
                return "Single channel";
            }
        }
        return null;
    }

    public String version_string() {
        switch (this.h_version) {
            case 1: {
                return "MPEG-1";
            }
            case 0: {
                return "MPEG-2 LSF";
            }
            case 2: {
                return "MPEG-2.5 LSF";
            }
        }
        return null;
    }

    public int number_of_subbands() {
        return this.h_number_of_subbands;
    }

    public int intensity_stereo_bound() {
        return this.h_intensity_stereo_bound;
    }
}

