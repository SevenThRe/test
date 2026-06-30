/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.BitReserve;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.FrameDecoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;
import javazoom.jl.decoder.SynthesisFilter;
import javazoom.jl.decoder.huffcodetab;

final class LayerIIIDecoder
implements FrameDecoder {
    final double d43 = 1.3333333333333333;
    public int[] scalefac_buffer;
    private int CheckSumHuff = 0;
    private int[] is_1d;
    private float[][][] ro;
    private float[][][] lr;
    private float[] out_1d;
    private float[][] prevblck;
    private float[][] k;
    private int[] nonzero;
    private Bitstream stream;
    private Header header;
    private SynthesisFilter filter1;
    private SynthesisFilter filter2;
    private Obuffer buffer;
    private int which_channels;
    private BitReserve br;
    private III_side_info_t si;
    private temporaire2[] III_scalefac_t;
    private temporaire2[] scalefac;
    private int max_gr;
    private int frame_start;
    private int part2_start;
    private int channels;
    private int first_channel;
    private int last_channel;
    private int sfreq;
    private float[] samples1 = new float[32];
    private float[] samples2 = new float[32];
    private final int[] new_slen = new int[4];
    int[] x = new int[]{0};
    int[] y = new int[]{0};
    int[] v = new int[]{0};
    int[] w = new int[]{0};
    int[] is_pos = new int[576];
    float[] is_ratio = new float[576];
    float[] tsOutCopy = new float[18];
    float[] rawout = new float[36];
    private int counter = 0;
    private static final int SSLIMIT = 18;
    private static final int SBLIMIT = 32;
    private static final int[][] slen = new int[][]{{0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4}, {0, 1, 2, 3, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2, 3}};
    public static final int[] pretab = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 0};
    private SBI[] sfBandIndex;
    public static final float[] two_to_negative_half_pow = new float[]{1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f, 1.5258789E-5f, 1.0789593E-5f, 7.6293945E-6f, 5.3947965E-6f, 3.8146973E-6f, 2.6973983E-6f, 1.9073486E-6f, 1.3486991E-6f, 9.536743E-7f, 6.7434956E-7f, 4.7683716E-7f, 3.3717478E-7f, 2.3841858E-7f, 1.6858739E-7f, 1.1920929E-7f, 8.4293696E-8f, 5.9604645E-8f, 4.2146848E-8f, 2.9802322E-8f, 2.1073424E-8f, 1.4901161E-8f, 1.0536712E-8f, 7.450581E-9f, 5.268356E-9f, 3.7252903E-9f, 2.634178E-9f, 1.8626451E-9f, 1.317089E-9f, 9.313226E-10f, 6.585445E-10f, 4.656613E-10f, 3.2927225E-10f};
    public static final float[] t_43 = LayerIIIDecoder.create_t_43();
    public static final float[][] io = new float[][]{{1.0f, 0.8408964f, 0.70710677f, 0.59460354f, 0.5f, 0.4204482f, 0.35355338f, 0.29730177f, 0.25f, 0.2102241f, 0.17677669f, 0.14865088f, 0.125f, 0.10511205f, 0.088388346f, 0.07432544f, 0.0625f, 0.052556027f, 0.044194173f, 0.03716272f, 0.03125f, 0.026278013f, 0.022097087f, 0.01858136f, 0.015625f, 0.013139007f, 0.011048543f, 0.00929068f, 0.0078125f, 0.0065695033f, 0.0055242716f, 0.00464534f}, {1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f}};
    public static final float[] TAN12 = new float[]{0.0f, 0.2679492f, 0.57735026f, 1.0f, 1.7320508f, 3.732051f, 1.0E11f, -3.732051f, -1.7320508f, -1.0f, -0.57735026f, -0.2679492f, 0.0f, 0.2679492f, 0.57735026f, 1.0f};
    private static int[][] reorder_table;
    private static final float[] cs;
    private static final float[] ca;
    public static final float[][] win;
    public Sftable sftable;
    public static final int[][][] nr_of_sfb_block;

    public LayerIIIDecoder(Bitstream bitstream, Header header, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2, Obuffer obuffer, int n) {
        huffcodetab.inithuff();
        this.is_1d = new int[580];
        this.ro = new float[2][32][18];
        this.lr = new float[2][32][18];
        this.out_1d = new float[576];
        this.prevblck = new float[2][576];
        this.k = new float[2][576];
        this.nonzero = new int[2];
        this.III_scalefac_t = new temporaire2[2];
        this.III_scalefac_t[0] = new temporaire2();
        this.III_scalefac_t[1] = new temporaire2();
        this.scalefac = this.III_scalefac_t;
        this.sfBandIndex = new SBI[9];
        int[] nArray = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
        int[] nArray2 = new int[]{0, 4, 8, 12, 18, 24, 32, 42, 56, 74, 100, 132, 174, 192};
        int[] nArray3 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 114, 136, 162, 194, 232, 278, 330, 394, 464, 540, 576};
        int[] nArray4 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 136, 180, 192};
        int[] nArray5 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
        int[] nArray6 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
        int[] nArray7 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 52, 62, 74, 90, 110, 134, 162, 196, 238, 288, 342, 418, 576};
        int[] nArray8 = new int[]{0, 4, 8, 12, 16, 22, 30, 40, 52, 66, 84, 106, 136, 192};
        int[] nArray9 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 42, 50, 60, 72, 88, 106, 128, 156, 190, 230, 276, 330, 384, 576};
        int[] nArray10 = new int[]{0, 4, 8, 12, 16, 22, 28, 38, 50, 64, 80, 100, 126, 192};
        int[] nArray11 = new int[]{0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 54, 66, 82, 102, 126, 156, 194, 240, 296, 364, 448, 550, 576};
        int[] nArray12 = new int[]{0, 4, 8, 12, 16, 22, 30, 42, 58, 78, 104, 138, 180, 192};
        int[] nArray13 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
        int[] nArray14 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
        int[] nArray15 = new int[]{0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576};
        int[] nArray16 = new int[]{0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192};
        int[] nArray17 = new int[]{0, 12, 24, 36, 48, 60, 72, 88, 108, 132, 160, 192, 232, 280, 336, 400, 476, 566, 568, 570, 572, 574, 576};
        int[] nArray18 = new int[]{0, 8, 16, 24, 36, 52, 72, 96, 124, 160, 162, 164, 166, 192};
        this.sfBandIndex[0] = new SBI(nArray, nArray2);
        this.sfBandIndex[1] = new SBI(nArray3, nArray4);
        this.sfBandIndex[2] = new SBI(nArray5, nArray6);
        this.sfBandIndex[3] = new SBI(nArray7, nArray8);
        this.sfBandIndex[4] = new SBI(nArray9, nArray10);
        this.sfBandIndex[5] = new SBI(nArray11, nArray12);
        this.sfBandIndex[6] = new SBI(nArray13, nArray14);
        this.sfBandIndex[7] = new SBI(nArray15, nArray16);
        this.sfBandIndex[8] = new SBI(nArray17, nArray18);
        if (reorder_table == null) {
            reorder_table = new int[9][];
            for (int i = 0; i < 9; ++i) {
                LayerIIIDecoder.reorder_table[i] = LayerIIIDecoder.reorder(this.sfBandIndex[i].s);
            }
        }
        int[] nArray19 = new int[]{0, 6, 11, 16, 21};
        int[] nArray20 = new int[]{0, 6, 12};
        this.sftable = new Sftable(nArray19, nArray20);
        this.scalefac_buffer = new int[54];
        this.stream = bitstream;
        this.header = header;
        this.filter1 = synthesisFilter;
        this.filter2 = synthesisFilter2;
        this.buffer = obuffer;
        this.which_channels = n;
        this.frame_start = 0;
        this.channels = this.header.mode() == 3 ? 1 : 2;
        this.max_gr = this.header.version() == 1 ? 2 : 1;
        this.sfreq = this.header.sample_frequency() + (this.header.version() == 1 ? 3 : (this.header.version() == 2 ? 6 : 0));
        if (this.channels == 2) {
            switch (this.which_channels) {
                case 1: 
                case 3: {
                    this.last_channel = 0;
                    this.first_channel = 0;
                    break;
                }
                case 2: {
                    this.last_channel = 1;
                    this.first_channel = 1;
                    break;
                }
                default: {
                    this.first_channel = 0;
                    this.last_channel = 1;
                    break;
                }
            }
        } else {
            this.last_channel = 0;
            this.first_channel = 0;
        }
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 576; ++j) {
                this.prevblck[i][j] = 0.0f;
            }
        }
        this.nonzero[1] = 576;
        this.nonzero[0] = 576;
        this.br = new BitReserve();
        this.si = new III_side_info_t();
    }

    public void seek_notify() {
        this.frame_start = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 576; ++j) {
                this.prevblck[i][j] = 0.0f;
            }
        }
        this.br = new BitReserve();
    }

    public void decodeFrame() {
        this.decode();
    }

    public void decode() {
        int n = this.header.slots();
        this.get_side_info();
        for (int i = 0; i < n; ++i) {
            this.br.hputbuf(this.stream.get_bits(8));
        }
        int n2 = this.br.hsstell() >>> 3;
        int n3 = this.br.hsstell() & 7;
        if (n3 != 0) {
            this.br.hgetbits(8 - n3);
            ++n2;
        }
        int n4 = this.frame_start - n2 - this.si.main_data_begin;
        this.frame_start += n;
        if (n4 < 0) {
            return;
        }
        if (n2 > 4096) {
            this.frame_start -= 4096;
            this.br.rewindNbytes(4096);
        }
        while (n4 > 0) {
            this.br.hgetbits(8);
            --n4;
        }
        for (int i = 0; i < this.max_gr; ++i) {
            int n5;
            for (n5 = 0; n5 < this.channels; ++n5) {
                this.part2_start = this.br.hsstell();
                if (this.header.version() == 1) {
                    this.get_scale_factors(n5, i);
                } else {
                    this.get_LSF_scale_factors(n5, i);
                }
                this.huffman_decode(n5, i);
                this.dequantize_sample(this.ro[n5], n5, i);
            }
            this.stereo(i);
            if (this.which_channels == 3 && this.channels > 1) {
                this.do_downmix();
            }
            for (n5 = this.first_channel; n5 <= this.last_channel; ++n5) {
                int n6;
                int n7;
                int n8;
                this.reorder(this.lr[n5], n5, i);
                this.antialias(n5, i);
                this.hybrid(n5, i);
                for (n8 = 18; n8 < 576; n8 += 36) {
                    for (n7 = 1; n7 < 18; n7 += 2) {
                        this.out_1d[n8 + n7] = -this.out_1d[n8 + n7];
                    }
                }
                if (n5 == 0 || this.which_channels == 2) {
                    for (n7 = 0; n7 < 18; ++n7) {
                        n6 = 0;
                        for (n8 = 0; n8 < 576; n8 += 18) {
                            this.samples1[n6] = this.out_1d[n8 + n7];
                            ++n6;
                        }
                        this.filter1.input_samples(this.samples1);
                        this.filter1.calculate_pcm_samples(this.buffer);
                    }
                    continue;
                }
                for (n7 = 0; n7 < 18; ++n7) {
                    n6 = 0;
                    for (n8 = 0; n8 < 576; n8 += 18) {
                        this.samples2[n6] = this.out_1d[n8 + n7];
                        ++n6;
                    }
                    this.filter2.input_samples(this.samples2);
                    this.filter2.calculate_pcm_samples(this.buffer);
                }
            }
        }
        ++this.counter;
        this.buffer.write_buffer(1);
    }

    private boolean get_side_info() {
        if (this.header.version() == 1) {
            int n;
            this.si.main_data_begin = this.stream.get_bits(9);
            this.si.private_bits = this.channels == 1 ? this.stream.get_bits(5) : this.stream.get_bits(3);
            for (n = 0; n < this.channels; ++n) {
                this.si.ch[n].scfsi[0] = this.stream.get_bits(1);
                this.si.ch[n].scfsi[1] = this.stream.get_bits(1);
                this.si.ch[n].scfsi[2] = this.stream.get_bits(1);
                this.si.ch[n].scfsi[3] = this.stream.get_bits(1);
            }
            for (int i = 0; i < 2; ++i) {
                for (n = 0; n < this.channels; ++n) {
                    this.si.ch[n].gr[i].part2_3_length = this.stream.get_bits(12);
                    this.si.ch[n].gr[i].big_values = this.stream.get_bits(9);
                    this.si.ch[n].gr[i].global_gain = this.stream.get_bits(8);
                    this.si.ch[n].gr[i].scalefac_compress = this.stream.get_bits(4);
                    this.si.ch[n].gr[i].window_switching_flag = this.stream.get_bits(1);
                    if (this.si.ch[n].gr[i].window_switching_flag != 0) {
                        this.si.ch[n].gr[i].block_type = this.stream.get_bits(2);
                        this.si.ch[n].gr[i].mixed_block_flag = this.stream.get_bits(1);
                        this.si.ch[n].gr[i].table_select[0] = this.stream.get_bits(5);
                        this.si.ch[n].gr[i].table_select[1] = this.stream.get_bits(5);
                        this.si.ch[n].gr[i].subblock_gain[0] = this.stream.get_bits(3);
                        this.si.ch[n].gr[i].subblock_gain[1] = this.stream.get_bits(3);
                        this.si.ch[n].gr[i].subblock_gain[2] = this.stream.get_bits(3);
                        if (this.si.ch[n].gr[i].block_type == 0) {
                            return false;
                        }
                        this.si.ch[n].gr[i].region0_count = this.si.ch[n].gr[i].block_type == 2 && this.si.ch[n].gr[i].mixed_block_flag == 0 ? 8 : 7;
                        this.si.ch[n].gr[i].region1_count = 20 - this.si.ch[n].gr[i].region0_count;
                    } else {
                        this.si.ch[n].gr[i].table_select[0] = this.stream.get_bits(5);
                        this.si.ch[n].gr[i].table_select[1] = this.stream.get_bits(5);
                        this.si.ch[n].gr[i].table_select[2] = this.stream.get_bits(5);
                        this.si.ch[n].gr[i].region0_count = this.stream.get_bits(4);
                        this.si.ch[n].gr[i].region1_count = this.stream.get_bits(3);
                        this.si.ch[n].gr[i].block_type = 0;
                    }
                    this.si.ch[n].gr[i].preflag = this.stream.get_bits(1);
                    this.si.ch[n].gr[i].scalefac_scale = this.stream.get_bits(1);
                    this.si.ch[n].gr[i].count1table_select = this.stream.get_bits(1);
                }
            }
        } else {
            this.si.main_data_begin = this.stream.get_bits(8);
            this.si.private_bits = this.channels == 1 ? this.stream.get_bits(1) : this.stream.get_bits(2);
            for (int i = 0; i < this.channels; ++i) {
                this.si.ch[i].gr[0].part2_3_length = this.stream.get_bits(12);
                this.si.ch[i].gr[0].big_values = this.stream.get_bits(9);
                this.si.ch[i].gr[0].global_gain = this.stream.get_bits(8);
                this.si.ch[i].gr[0].scalefac_compress = this.stream.get_bits(9);
                this.si.ch[i].gr[0].window_switching_flag = this.stream.get_bits(1);
                if (this.si.ch[i].gr[0].window_switching_flag != 0) {
                    this.si.ch[i].gr[0].block_type = this.stream.get_bits(2);
                    this.si.ch[i].gr[0].mixed_block_flag = this.stream.get_bits(1);
                    this.si.ch[i].gr[0].table_select[0] = this.stream.get_bits(5);
                    this.si.ch[i].gr[0].table_select[1] = this.stream.get_bits(5);
                    this.si.ch[i].gr[0].subblock_gain[0] = this.stream.get_bits(3);
                    this.si.ch[i].gr[0].subblock_gain[1] = this.stream.get_bits(3);
                    this.si.ch[i].gr[0].subblock_gain[2] = this.stream.get_bits(3);
                    if (this.si.ch[i].gr[0].block_type == 0) {
                        return false;
                    }
                    if (this.si.ch[i].gr[0].block_type == 2 && this.si.ch[i].gr[0].mixed_block_flag == 0) {
                        this.si.ch[i].gr[0].region0_count = 8;
                    } else {
                        this.si.ch[i].gr[0].region0_count = 7;
                        this.si.ch[i].gr[0].region1_count = 20 - this.si.ch[i].gr[0].region0_count;
                    }
                } else {
                    this.si.ch[i].gr[0].table_select[0] = this.stream.get_bits(5);
                    this.si.ch[i].gr[0].table_select[1] = this.stream.get_bits(5);
                    this.si.ch[i].gr[0].table_select[2] = this.stream.get_bits(5);
                    this.si.ch[i].gr[0].region0_count = this.stream.get_bits(4);
                    this.si.ch[i].gr[0].region1_count = this.stream.get_bits(3);
                    this.si.ch[i].gr[0].block_type = 0;
                }
                this.si.ch[i].gr[0].scalefac_scale = this.stream.get_bits(1);
                this.si.ch[i].gr[0].count1table_select = this.stream.get_bits(1);
            }
        }
        return true;
    }

    private void get_scale_factors(int n, int n2) {
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        int n3 = gr_info_s2.scalefac_compress;
        int n4 = slen[0][n3];
        int n5 = slen[1][n3];
        if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
            if (gr_info_s2.mixed_block_flag != 0) {
                int n6;
                int n7;
                for (n7 = 0; n7 < 8; ++n7) {
                    this.scalefac[n].l[n7] = this.br.hgetbits(slen[0][gr_info_s2.scalefac_compress]);
                }
                for (n7 = 3; n7 < 6; ++n7) {
                    for (n6 = 0; n6 < 3; ++n6) {
                        this.scalefac[n].s[n6][n7] = this.br.hgetbits(slen[0][gr_info_s2.scalefac_compress]);
                    }
                }
                for (n7 = 6; n7 < 12; ++n7) {
                    for (n6 = 0; n6 < 3; ++n6) {
                        this.scalefac[n].s[n6][n7] = this.br.hgetbits(slen[1][gr_info_s2.scalefac_compress]);
                    }
                }
                n7 = 12;
                for (n6 = 0; n6 < 3; ++n6) {
                    this.scalefac[n].s[n6][n7] = 0;
                }
            } else {
                this.scalefac[n].s[0][0] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][0] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][0] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][1] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][1] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][1] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][2] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][2] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][2] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][3] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][3] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][3] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][4] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][4] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][4] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][5] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][5] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][5] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][6] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][6] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][6] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][7] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][7] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][7] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][8] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][8] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][8] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][9] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][9] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][9] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][10] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][10] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][10] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][11] = this.br.hgetbits(n5);
                this.scalefac[n].s[1][11] = this.br.hgetbits(n5);
                this.scalefac[n].s[2][11] = this.br.hgetbits(n5);
                this.scalefac[n].s[0][12] = 0;
                this.scalefac[n].s[1][12] = 0;
                this.scalefac[n].s[2][12] = 0;
            }
        } else {
            if (this.si.ch[n].scfsi[0] == 0 || n2 == 0) {
                this.scalefac[n].l[0] = this.br.hgetbits(n4);
                this.scalefac[n].l[1] = this.br.hgetbits(n4);
                this.scalefac[n].l[2] = this.br.hgetbits(n4);
                this.scalefac[n].l[3] = this.br.hgetbits(n4);
                this.scalefac[n].l[4] = this.br.hgetbits(n4);
                this.scalefac[n].l[5] = this.br.hgetbits(n4);
            }
            if (this.si.ch[n].scfsi[1] == 0 || n2 == 0) {
                this.scalefac[n].l[6] = this.br.hgetbits(n4);
                this.scalefac[n].l[7] = this.br.hgetbits(n4);
                this.scalefac[n].l[8] = this.br.hgetbits(n4);
                this.scalefac[n].l[9] = this.br.hgetbits(n4);
                this.scalefac[n].l[10] = this.br.hgetbits(n4);
            }
            if (this.si.ch[n].scfsi[2] == 0 || n2 == 0) {
                this.scalefac[n].l[11] = this.br.hgetbits(n5);
                this.scalefac[n].l[12] = this.br.hgetbits(n5);
                this.scalefac[n].l[13] = this.br.hgetbits(n5);
                this.scalefac[n].l[14] = this.br.hgetbits(n5);
                this.scalefac[n].l[15] = this.br.hgetbits(n5);
            }
            if (this.si.ch[n].scfsi[3] == 0 || n2 == 0) {
                this.scalefac[n].l[16] = this.br.hgetbits(n5);
                this.scalefac[n].l[17] = this.br.hgetbits(n5);
                this.scalefac[n].l[18] = this.br.hgetbits(n5);
                this.scalefac[n].l[19] = this.br.hgetbits(n5);
                this.scalefac[n].l[20] = this.br.hgetbits(n5);
            }
            this.scalefac[n].l[21] = 0;
            this.scalefac[n].l[22] = 0;
        }
    }

    private void get_LSF_scale_data(int n, int n2) {
        int n3;
        int n4 = this.header.mode_extension();
        int n5 = 0;
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        int n6 = gr_info_s2.scalefac_compress;
        int n7 = gr_info_s2.block_type == 2 ? (gr_info_s2.mixed_block_flag == 0 ? 1 : (gr_info_s2.mixed_block_flag == 1 ? 2 : 0)) : 0;
        if (n4 != 1 && n4 != 3 || n != 1) {
            if (n6 < 400) {
                this.new_slen[0] = (n6 >>> 4) / 5;
                this.new_slen[1] = (n6 >>> 4) % 5;
                this.new_slen[2] = (n6 & 0xF) >>> 2;
                this.new_slen[3] = n6 & 3;
                this.si.ch[n].gr[n2].preflag = 0;
                n5 = 0;
            } else if (n6 < 500) {
                this.new_slen[0] = (n6 - 400 >>> 2) / 5;
                this.new_slen[1] = (n6 - 400 >>> 2) % 5;
                this.new_slen[2] = n6 - 400 & 3;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n5 = 1;
            } else if (n6 < 512) {
                this.new_slen[0] = (n6 - 500) / 3;
                this.new_slen[1] = (n6 - 500) % 3;
                this.new_slen[2] = 0;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 1;
                n5 = 2;
            }
        }
        if ((n4 == 1 || n4 == 3) && n == 1) {
            int n8 = n6 >>> 1;
            if (n8 < 180) {
                this.new_slen[0] = n8 / 36;
                this.new_slen[1] = n8 % 36 / 6;
                this.new_slen[2] = n8 % 36 % 6;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n5 = 3;
            } else if (n8 < 244) {
                this.new_slen[0] = (n8 - 180 & 0x3F) >>> 4;
                this.new_slen[1] = (n8 - 180 & 0xF) >>> 2;
                this.new_slen[2] = n8 - 180 & 3;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n5 = 4;
            } else if (n8 < 255) {
                this.new_slen[0] = (n8 - 244) / 3;
                this.new_slen[1] = (n8 - 244) % 3;
                this.new_slen[2] = 0;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n5 = 5;
            }
        }
        for (n3 = 0; n3 < 45; ++n3) {
            this.scalefac_buffer[n3] = 0;
        }
        int n9 = 0;
        for (n3 = 0; n3 < 4; ++n3) {
            for (int i = 0; i < nr_of_sfb_block[n5][n7][n3]; ++i) {
                this.scalefac_buffer[n9] = this.new_slen[n3] == 0 ? 0 : this.br.hgetbits(this.new_slen[n3]);
                ++n9;
            }
        }
    }

    private void get_LSF_scale_factors(int n, int n2) {
        int n3 = 0;
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        this.get_LSF_scale_data(n, n2);
        if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
            if (gr_info_s2.mixed_block_flag != 0) {
                int n4;
                int n5;
                for (n5 = 0; n5 < 8; ++n5) {
                    this.scalefac[n].l[n5] = this.scalefac_buffer[n3];
                    ++n3;
                }
                for (n5 = 3; n5 < 12; ++n5) {
                    for (n4 = 0; n4 < 3; ++n4) {
                        this.scalefac[n].s[n4][n5] = this.scalefac_buffer[n3];
                        ++n3;
                    }
                }
                for (n4 = 0; n4 < 3; ++n4) {
                    this.scalefac[n].s[n4][12] = 0;
                }
            } else {
                int n6;
                for (int i = 0; i < 12; ++i) {
                    for (n6 = 0; n6 < 3; ++n6) {
                        this.scalefac[n].s[n6][i] = this.scalefac_buffer[n3];
                        ++n3;
                    }
                }
                for (n6 = 0; n6 < 3; ++n6) {
                    this.scalefac[n].s[n6][12] = 0;
                }
            }
        } else {
            for (int i = 0; i < 21; ++i) {
                this.scalefac[n].l[i] = this.scalefac_buffer[n3];
                ++n3;
            }
            this.scalefac[n].l[21] = 0;
            this.scalefac[n].l[22] = 0;
        }
    }

    private void huffman_decode(int n, int n2) {
        huffcodetab huffcodetab2;
        int n3;
        int n4;
        this.x[0] = 0;
        this.y[0] = 0;
        this.v[0] = 0;
        this.w[0] = 0;
        int n5 = this.part2_start + this.si.ch[n].gr[n2].part2_3_length;
        if (this.si.ch[n].gr[n2].window_switching_flag != 0 && this.si.ch[n].gr[n2].block_type == 2) {
            n4 = this.sfreq == 8 ? 72 : 36;
            n3 = 576;
        } else {
            int n6 = this.si.ch[n].gr[n2].region0_count + 1;
            int n7 = n6 + this.si.ch[n].gr[n2].region1_count + 1;
            if (n7 > this.sfBandIndex[this.sfreq].l.length - 1) {
                n7 = this.sfBandIndex[this.sfreq].l.length - 1;
            }
            n4 = this.sfBandIndex[this.sfreq].l[n6];
            n3 = this.sfBandIndex[this.sfreq].l[n7];
        }
        int n8 = 0;
        for (int i = 0; i < this.si.ch[n].gr[n2].big_values << 1; i += 2) {
            huffcodetab2 = i < n4 ? huffcodetab.ht[this.si.ch[n].gr[n2].table_select[0]] : (i < n3 ? huffcodetab.ht[this.si.ch[n].gr[n2].table_select[1]] : huffcodetab.ht[this.si.ch[n].gr[n2].table_select[2]]);
            huffcodetab.huffman_decoder(huffcodetab2, this.x, this.y, this.v, this.w, this.br);
            this.is_1d[n8++] = this.x[0];
            this.is_1d[n8++] = this.y[0];
            this.CheckSumHuff = this.CheckSumHuff + this.x[0] + this.y[0];
        }
        huffcodetab2 = huffcodetab.ht[this.si.ch[n].gr[n2].count1table_select + 32];
        int n9 = this.br.hsstell();
        while (n9 < n5 && n8 < 576) {
            huffcodetab.huffman_decoder(huffcodetab2, this.x, this.y, this.v, this.w, this.br);
            this.is_1d[n8++] = this.v[0];
            this.is_1d[n8++] = this.w[0];
            this.is_1d[n8++] = this.x[0];
            this.is_1d[n8++] = this.y[0];
            this.CheckSumHuff = this.CheckSumHuff + this.v[0] + this.w[0] + this.x[0] + this.y[0];
            n9 = this.br.hsstell();
        }
        if (n9 > n5) {
            this.br.rewindNbits(n9 - n5);
            n8 -= 4;
        }
        if ((n9 = this.br.hsstell()) < n5) {
            this.br.hgetbits(n5 - n9);
        }
        this.nonzero[n] = n8 < 576 ? n8 : 576;
        if (n8 < 0) {
            n8 = 0;
        }
        while (n8 < 576) {
            this.is_1d[n8] = 0;
            ++n8;
        }
    }

    private void i_stereo_k_values(int n, int n2, int n3) {
        if (n == 0) {
            this.k[0][n3] = 1.0f;
            this.k[1][n3] = 1.0f;
        } else if ((n & 1) != 0) {
            this.k[0][n3] = io[n2][n + 1 >>> 1];
            this.k[1][n3] = 1.0f;
        } else {
            this.k[0][n3] = 1.0f;
            this.k[1][n3] = io[n2][n >>> 1];
        }
    }

    private void dequantize_sample(float[][] fArray, int n, int n2) {
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        float[][] fArray2 = fArray;
        if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
            if (gr_info_s2.mixed_block_flag != 0) {
                n7 = this.sfBandIndex[this.sfreq].l[1];
            } else {
                n10 = this.sfBandIndex[this.sfreq].s[1];
                n7 = (n10 << 2) - n10;
                n9 = 0;
            }
        } else {
            n7 = this.sfBandIndex[this.sfreq].l[1];
        }
        float f = (float)Math.pow(2.0, 0.25 * ((double)gr_info_s2.global_gain - 210.0));
        for (n6 = 0; n6 < this.nonzero[n]; ++n6) {
            n5 = n6 % 18;
            n4 = (n6 - n5) / 18;
            if (this.is_1d[n6] == 0) {
                fArray2[n4][n5] = 0.0f;
                continue;
            }
            n3 = this.is_1d[n6];
            if (n3 < t_43.length) {
                if (this.is_1d[n6] > 0) {
                    fArray2[n4][n5] = f * t_43[n3];
                    continue;
                }
                if (-n3 < t_43.length) {
                    fArray2[n4][n5] = -f * t_43[-n3];
                    continue;
                }
                fArray2[n4][n5] = -f * (float)Math.pow(-n3, 1.3333333333333333);
                continue;
            }
            fArray2[n4][n5] = this.is_1d[n6] > 0 ? f * (float)Math.pow(n3, 1.3333333333333333) : -f * (float)Math.pow(-n3, 1.3333333333333333);
        }
        for (n6 = 0; n6 < this.nonzero[n]; ++n6) {
            n5 = n6 % 18;
            n4 = (n6 - n5) / 18;
            if (n11 == n7) {
                if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
                    if (gr_info_s2.mixed_block_flag != 0) {
                        if (n11 == this.sfBandIndex[this.sfreq].l[8]) {
                            n7 = this.sfBandIndex[this.sfreq].s[4];
                            n7 = (n7 << 2) - n7;
                            n8 = 3;
                            n10 = this.sfBandIndex[this.sfreq].s[4] - this.sfBandIndex[this.sfreq].s[3];
                            n9 = this.sfBandIndex[this.sfreq].s[3];
                            n9 = (n9 << 2) - n9;
                        } else if (n11 < this.sfBandIndex[this.sfreq].l[8]) {
                            n7 = this.sfBandIndex[this.sfreq].l[++n8 + 1];
                        } else {
                            n7 = this.sfBandIndex[this.sfreq].s[++n8 + 1];
                            n7 = (n7 << 2) - n7;
                            n9 = this.sfBandIndex[this.sfreq].s[n8];
                            n10 = this.sfBandIndex[this.sfreq].s[n8 + 1] - n9;
                            n9 = (n9 << 2) - n9;
                        }
                    } else {
                        n7 = this.sfBandIndex[this.sfreq].s[++n8 + 1];
                        n7 = (n7 << 2) - n7;
                        n9 = this.sfBandIndex[this.sfreq].s[n8];
                        n10 = this.sfBandIndex[this.sfreq].s[n8 + 1] - n9;
                        n9 = (n9 << 2) - n9;
                    }
                } else {
                    n7 = this.sfBandIndex[this.sfreq].l[++n8 + 1];
                }
            }
            if (gr_info_s2.window_switching_flag != 0 && (gr_info_s2.block_type == 2 && gr_info_s2.mixed_block_flag == 0 || gr_info_s2.block_type == 2 && gr_info_s2.mixed_block_flag != 0 && n6 >= 36)) {
                int n12 = (n11 - n9) / n10;
                n3 = this.scalefac[n].s[n12][n8] << gr_info_s2.scalefac_scale;
                float[] fArray3 = fArray2[n4];
                int n13 = n5;
                fArray3[n13] = fArray3[n13] * two_to_negative_half_pow[n3 += gr_info_s2.subblock_gain[n12] << 2];
            } else {
                n3 = this.scalefac[n].l[n8];
                if (gr_info_s2.preflag != 0) {
                    n3 += pretab[n8];
                }
                float[] fArray4 = fArray2[n4];
                int n14 = n5;
                fArray4[n14] = fArray4[n14] * two_to_negative_half_pow[n3 <<= gr_info_s2.scalefac_scale];
            }
            ++n11;
        }
        for (n6 = this.nonzero[n]; n6 < 576; ++n6) {
            n5 = n6 % 18;
            n4 = (n6 - n5) / 18;
            if (n5 < 0) {
                n5 = 0;
            }
            if (n4 < 0) {
                n4 = 0;
            }
            fArray2[n4][n5] = 0.0f;
        }
    }

    private void reorder(float[][] fArray, int n, int n2) {
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        float[][] fArray2 = fArray;
        if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
            int n3;
            for (n3 = 0; n3 < 576; ++n3) {
                this.out_1d[n3] = 0.0f;
            }
            if (gr_info_s2.mixed_block_flag != 0) {
                int n4;
                int n5;
                for (n3 = 0; n3 < 36; ++n3) {
                    n5 = n3 % 18;
                    n4 = (n3 - n5) / 18;
                    this.out_1d[n3] = fArray2[n4][n5];
                }
                for (int i = 3; i < 13; ++i) {
                    int n6 = this.sfBandIndex[this.sfreq].s[i];
                    int n7 = this.sfBandIndex[this.sfreq].s[i + 1] - n6;
                    n5 = (n6 << 2) - n6;
                    int n8 = 0;
                    int n9 = 0;
                    while (n8 < n7) {
                        int n10 = n5 + n8;
                        int n11 = n5 + n9;
                        n4 = n10 % 18;
                        int n12 = (n10 - n4) / 18;
                        this.out_1d[n11] = fArray2[n12][n4];
                        n4 = (n10 += n7) % 18;
                        n12 = (n10 - n4) / 18;
                        this.out_1d[++n11] = fArray2[n12][n4];
                        n4 = (n10 += n7) % 18;
                        n12 = (n10 - n4) / 18;
                        this.out_1d[++n11] = fArray2[n12][n4];
                        ++n8;
                        n9 += 3;
                    }
                }
            } else {
                for (n3 = 0; n3 < 576; ++n3) {
                    int n13 = reorder_table[this.sfreq][n3];
                    int n14 = n13 % 18;
                    int n15 = (n13 - n14) / 18;
                    this.out_1d[n3] = fArray2[n15][n14];
                }
            }
        } else {
            for (int i = 0; i < 576; ++i) {
                int n16 = i % 18;
                int n17 = (i - n16) / 18;
                this.out_1d[i] = fArray2[n17][n16];
            }
        }
    }

    private void stereo(int n) {
        if (this.channels == 1) {
            for (int i = 0; i < 32; ++i) {
                for (int j = 0; j < 18; j += 3) {
                    this.lr[0][i][j] = this.ro[0][i][j];
                    this.lr[0][i][j + 1] = this.ro[0][i][j + 1];
                    this.lr[0][i][j + 2] = this.ro[0][i][j + 2];
                }
            }
        } else {
            int n2;
            int n3;
            int n4;
            gr_info_s gr_info_s2 = this.si.ch[0].gr[n];
            int n5 = this.header.mode_extension();
            boolean bl = this.header.mode() == 1 && (n5 & 2) != 0;
            boolean bl2 = this.header.mode() == 1 && (n5 & 1) != 0;
            boolean bl3 = this.header.version() == 0 || this.header.version() == 2;
            int n6 = gr_info_s2.scalefac_compress & 1;
            for (n4 = 0; n4 < 576; ++n4) {
                this.is_pos[n4] = 7;
                this.is_ratio[n4] = 0.0f;
            }
            if (bl2) {
                int n7;
                if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2) {
                    int n8;
                    int n9;
                    int n10;
                    int n11;
                    if (gr_info_s2.mixed_block_flag != 0) {
                        n11 = 0;
                        for (n10 = 0; n10 < 3; ++n10) {
                            int n12 = 2;
                            for (n7 = 12; n7 >= 3; --n7) {
                                n4 = this.sfBandIndex[this.sfreq].s[n7];
                                n9 = this.sfBandIndex[this.sfreq].s[n7 + 1] - n4;
                                n4 = (n4 << 2) - n4 + (n10 + 1) * n9 - 1;
                                while (n9 > 0) {
                                    if (this.ro[1][n4 / 18][n4 % 18] != 0.0f) {
                                        n12 = n7;
                                        n7 = -10;
                                        n9 = -10;
                                    }
                                    --n9;
                                    --n4;
                                }
                            }
                            n7 = n12 + 1;
                            if (n7 > n11) {
                                n11 = n7;
                            }
                            while (n7 < 12) {
                                n8 = this.sfBandIndex[this.sfreq].s[n7];
                                n4 = (n8 << 2) - n8 + n10 * n3;
                                for (n3 = this.sfBandIndex[this.sfreq].s[n7 + 1] - n8; n3 > 0; --n3) {
                                    this.is_pos[n4] = this.scalefac[1].s[n10][n7];
                                    if (this.is_pos[n4] != 7) {
                                        if (bl3) {
                                            this.i_stereo_k_values(this.is_pos[n4], n6, n4);
                                        } else {
                                            this.is_ratio[n4] = TAN12[this.is_pos[n4]];
                                        }
                                    }
                                    ++n4;
                                }
                                ++n7;
                            }
                            n7 = this.sfBandIndex[this.sfreq].s[10];
                            n3 = this.sfBandIndex[this.sfreq].s[11] - n7;
                            n7 = (n7 << 2) - n7 + n10 * n3;
                            n8 = this.sfBandIndex[this.sfreq].s[11];
                            n4 = (n8 << 2) - n8 + n10 * n3;
                            for (n3 = this.sfBandIndex[this.sfreq].s[12] - n8; n3 > 0; --n3) {
                                this.is_pos[n4] = this.is_pos[n7];
                                if (bl3) {
                                    this.k[0][n4] = this.k[0][n7];
                                    this.k[1][n4] = this.k[1][n7];
                                } else {
                                    this.is_ratio[n4] = this.is_ratio[n7];
                                }
                                ++n4;
                            }
                        }
                        if (n11 <= 3) {
                            n4 = 2;
                            n2 = 17;
                            n3 = -1;
                            while (n4 >= 0) {
                                if (this.ro[1][n4][n2] != 0.0f) {
                                    n3 = (n4 << 4) + (n4 << 1) + n2;
                                    n4 = -1;
                                    continue;
                                }
                                if (--n2 >= 0) continue;
                                --n4;
                                n2 = 17;
                            }
                            n4 = 0;
                            while (this.sfBandIndex[this.sfreq].l[n4] <= n3) {
                                ++n4;
                            }
                            n7 = n4;
                            n4 = this.sfBandIndex[this.sfreq].l[n4];
                            while (n7 < 8) {
                                for (n3 = this.sfBandIndex[this.sfreq].l[n7 + 1] - this.sfBandIndex[this.sfreq].l[n7]; n3 > 0; --n3) {
                                    this.is_pos[n4] = this.scalefac[1].l[n7];
                                    if (this.is_pos[n4] != 7) {
                                        if (bl3) {
                                            this.i_stereo_k_values(this.is_pos[n4], n6, n4);
                                        } else {
                                            this.is_ratio[n4] = TAN12[this.is_pos[n4]];
                                        }
                                    }
                                    ++n4;
                                }
                                ++n7;
                            }
                        }
                    } else {
                        for (n11 = 0; n11 < 3; ++n11) {
                            n10 = -1;
                            for (n7 = 12; n7 >= 0; --n7) {
                                n8 = this.sfBandIndex[this.sfreq].s[n7];
                                n9 = this.sfBandIndex[this.sfreq].s[n7 + 1] - n8;
                                n4 = (n8 << 2) - n8 + (n11 + 1) * n9 - 1;
                                while (n9 > 0) {
                                    if (this.ro[1][n4 / 18][n4 % 18] != 0.0f) {
                                        n10 = n7;
                                        n7 = -10;
                                        n9 = -10;
                                    }
                                    --n9;
                                    --n4;
                                }
                            }
                            for (n7 = n10 + 1; n7 < 12; ++n7) {
                                n8 = this.sfBandIndex[this.sfreq].s[n7];
                                n4 = (n8 << 2) - n8 + n11 * n3;
                                for (n3 = this.sfBandIndex[this.sfreq].s[n7 + 1] - n8; n3 > 0; --n3) {
                                    this.is_pos[n4] = this.scalefac[1].s[n11][n7];
                                    if (this.is_pos[n4] != 7) {
                                        if (bl3) {
                                            this.i_stereo_k_values(this.is_pos[n4], n6, n4);
                                        } else {
                                            this.is_ratio[n4] = TAN12[this.is_pos[n4]];
                                        }
                                    }
                                    ++n4;
                                }
                            }
                            n8 = this.sfBandIndex[this.sfreq].s[10];
                            int n13 = this.sfBandIndex[this.sfreq].s[11];
                            n3 = n13 - n8;
                            n7 = (n8 << 2) - n8 + n11 * n3;
                            n4 = (n13 << 2) - n13 + n11 * n3;
                            for (n3 = this.sfBandIndex[this.sfreq].s[12] - n13; n3 > 0; --n3) {
                                this.is_pos[n4] = this.is_pos[n7];
                                if (bl3) {
                                    this.k[0][n4] = this.k[0][n7];
                                    this.k[1][n4] = this.k[1][n7];
                                } else {
                                    this.is_ratio[n4] = this.is_ratio[n7];
                                }
                                ++n4;
                            }
                        }
                    }
                } else {
                    n4 = 31;
                    n2 = 17;
                    n3 = 0;
                    while (n4 >= 0) {
                        if (this.ro[1][n4][n2] != 0.0f) {
                            n3 = (n4 << 4) + (n4 << 1) + n2;
                            n4 = -1;
                            continue;
                        }
                        if (--n2 >= 0) continue;
                        --n4;
                        n2 = 17;
                    }
                    n4 = 0;
                    while (this.sfBandIndex[this.sfreq].l[n4] <= n3) {
                        ++n4;
                    }
                    n7 = n4;
                    n4 = this.sfBandIndex[this.sfreq].l[n4];
                    while (n7 < 21) {
                        for (n3 = this.sfBandIndex[this.sfreq].l[n7 + 1] - this.sfBandIndex[this.sfreq].l[n7]; n3 > 0; --n3) {
                            this.is_pos[n4] = this.scalefac[1].l[n7];
                            if (this.is_pos[n4] != 7) {
                                if (bl3) {
                                    this.i_stereo_k_values(this.is_pos[n4], n6, n4);
                                } else {
                                    this.is_ratio[n4] = TAN12[this.is_pos[n4]];
                                }
                            }
                            ++n4;
                        }
                        ++n7;
                    }
                    n7 = this.sfBandIndex[this.sfreq].l[20];
                    for (n3 = 576 - this.sfBandIndex[this.sfreq].l[21]; n3 > 0 && n4 < 576; ++n4, --n3) {
                        this.is_pos[n4] = this.is_pos[n7];
                        if (bl3) {
                            this.k[0][n4] = this.k[0][n7];
                            this.k[1][n4] = this.k[1][n7];
                            continue;
                        }
                        this.is_ratio[n4] = this.is_ratio[n7];
                    }
                }
            }
            n4 = 0;
            for (n3 = 0; n3 < 32; ++n3) {
                for (n2 = 0; n2 < 18; ++n2) {
                    if (this.is_pos[n4] == 7) {
                        if (bl) {
                            this.lr[0][n3][n2] = (this.ro[0][n3][n2] + this.ro[1][n3][n2]) * 0.70710677f;
                            this.lr[1][n3][n2] = (this.ro[0][n3][n2] - this.ro[1][n3][n2]) * 0.70710677f;
                        } else {
                            this.lr[0][n3][n2] = this.ro[0][n3][n2];
                            this.lr[1][n3][n2] = this.ro[1][n3][n2];
                        }
                    } else if (bl2) {
                        if (bl3) {
                            this.lr[0][n3][n2] = this.ro[0][n3][n2] * this.k[0][n4];
                            this.lr[1][n3][n2] = this.ro[0][n3][n2] * this.k[1][n4];
                        } else {
                            this.lr[1][n3][n2] = this.ro[0][n3][n2] / (1.0f + this.is_ratio[n4]);
                            this.lr[0][n3][n2] = this.lr[1][n3][n2] * this.is_ratio[n4];
                        }
                    }
                    ++n4;
                }
            }
        }
    }

    private void antialias(int n, int n2) {
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        if (gr_info_s2.window_switching_flag != 0 && gr_info_s2.block_type == 2 && gr_info_s2.mixed_block_flag == 0) {
            return;
        }
        int n3 = gr_info_s2.window_switching_flag != 0 && gr_info_s2.mixed_block_flag != 0 && gr_info_s2.block_type == 2 ? 18 : 558;
        for (int i = 0; i < n3; i += 18) {
            for (int j = 0; j < 8; ++j) {
                int n4 = i + 17 - j;
                int n5 = i + 18 + j;
                float f = this.out_1d[n4];
                float f2 = this.out_1d[n5];
                this.out_1d[n4] = f * cs[j] - f2 * ca[j];
                this.out_1d[n5] = f2 * cs[j] + f * ca[j];
            }
        }
    }

    private void hybrid(int n, int n2) {
        gr_info_s gr_info_s2 = this.si.ch[n].gr[n2];
        for (int i = 0; i < 576; i += 18) {
            int n3;
            int n4 = gr_info_s2.window_switching_flag != 0 && gr_info_s2.mixed_block_flag != 0 && i < 36 ? 0 : gr_info_s2.block_type;
            float[] fArray = this.out_1d;
            for (n3 = 0; n3 < 18; ++n3) {
                this.tsOutCopy[n3] = fArray[n3 + i];
            }
            this.inv_mdct(this.tsOutCopy, this.rawout, n4);
            for (n3 = 0; n3 < 18; ++n3) {
                fArray[n3 + i] = this.tsOutCopy[n3];
            }
            float[][] fArray2 = this.prevblck;
            fArray[0 + i] = this.rawout[0] + fArray2[n][i + 0];
            fArray2[n][i + 0] = this.rawout[18];
            fArray[1 + i] = this.rawout[1] + fArray2[n][i + 1];
            fArray2[n][i + 1] = this.rawout[19];
            fArray[2 + i] = this.rawout[2] + fArray2[n][i + 2];
            fArray2[n][i + 2] = this.rawout[20];
            fArray[3 + i] = this.rawout[3] + fArray2[n][i + 3];
            fArray2[n][i + 3] = this.rawout[21];
            fArray[4 + i] = this.rawout[4] + fArray2[n][i + 4];
            fArray2[n][i + 4] = this.rawout[22];
            fArray[5 + i] = this.rawout[5] + fArray2[n][i + 5];
            fArray2[n][i + 5] = this.rawout[23];
            fArray[6 + i] = this.rawout[6] + fArray2[n][i + 6];
            fArray2[n][i + 6] = this.rawout[24];
            fArray[7 + i] = this.rawout[7] + fArray2[n][i + 7];
            fArray2[n][i + 7] = this.rawout[25];
            fArray[8 + i] = this.rawout[8] + fArray2[n][i + 8];
            fArray2[n][i + 8] = this.rawout[26];
            fArray[9 + i] = this.rawout[9] + fArray2[n][i + 9];
            fArray2[n][i + 9] = this.rawout[27];
            fArray[10 + i] = this.rawout[10] + fArray2[n][i + 10];
            fArray2[n][i + 10] = this.rawout[28];
            fArray[11 + i] = this.rawout[11] + fArray2[n][i + 11];
            fArray2[n][i + 11] = this.rawout[29];
            fArray[12 + i] = this.rawout[12] + fArray2[n][i + 12];
            fArray2[n][i + 12] = this.rawout[30];
            fArray[13 + i] = this.rawout[13] + fArray2[n][i + 13];
            fArray2[n][i + 13] = this.rawout[31];
            fArray[14 + i] = this.rawout[14] + fArray2[n][i + 14];
            fArray2[n][i + 14] = this.rawout[32];
            fArray[15 + i] = this.rawout[15] + fArray2[n][i + 15];
            fArray2[n][i + 15] = this.rawout[33];
            fArray[16 + i] = this.rawout[16] + fArray2[n][i + 16];
            fArray2[n][i + 16] = this.rawout[34];
            fArray[17 + i] = this.rawout[17] + fArray2[n][i + 17];
            fArray2[n][i + 17] = this.rawout[35];
        }
    }

    private void do_downmix() {
        for (int i = 0; i < 18; ++i) {
            for (int j = 0; j < 18; j += 3) {
                this.lr[0][i][j] = (this.lr[0][i][j] + this.lr[1][i][j]) * 0.5f;
                this.lr[0][i][j + 1] = (this.lr[0][i][j + 1] + this.lr[1][i][j + 1]) * 0.5f;
                this.lr[0][i][j + 2] = (this.lr[0][i][j + 2] + this.lr[1][i][j + 2]) * 0.5f;
            }
        }
    }

    public void inv_mdct(float[] fArray, float[] fArray2, int n) {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        float f17 = 0.0f;
        float f18 = 0.0f;
        if (n == 2) {
            fArray2[0] = 0.0f;
            fArray2[1] = 0.0f;
            fArray2[2] = 0.0f;
            fArray2[3] = 0.0f;
            fArray2[4] = 0.0f;
            fArray2[5] = 0.0f;
            fArray2[6] = 0.0f;
            fArray2[7] = 0.0f;
            fArray2[8] = 0.0f;
            fArray2[9] = 0.0f;
            fArray2[10] = 0.0f;
            fArray2[11] = 0.0f;
            fArray2[12] = 0.0f;
            fArray2[13] = 0.0f;
            fArray2[14] = 0.0f;
            fArray2[15] = 0.0f;
            fArray2[16] = 0.0f;
            fArray2[17] = 0.0f;
            fArray2[18] = 0.0f;
            fArray2[19] = 0.0f;
            fArray2[20] = 0.0f;
            fArray2[21] = 0.0f;
            fArray2[22] = 0.0f;
            fArray2[23] = 0.0f;
            fArray2[24] = 0.0f;
            fArray2[25] = 0.0f;
            fArray2[26] = 0.0f;
            fArray2[27] = 0.0f;
            fArray2[28] = 0.0f;
            fArray2[29] = 0.0f;
            fArray2[30] = 0.0f;
            fArray2[31] = 0.0f;
            fArray2[32] = 0.0f;
            fArray2[33] = 0.0f;
            fArray2[34] = 0.0f;
            fArray2[35] = 0.0f;
            int n2 = 0;
            for (int i = 0; i < 3; ++i) {
                int n3 = 15 + i;
                fArray[n3] = fArray[n3] + fArray[12 + i];
                int n4 = 12 + i;
                fArray[n4] = fArray[n4] + fArray[9 + i];
                int n5 = 9 + i;
                fArray[n5] = fArray[n5] + fArray[6 + i];
                int n6 = 6 + i;
                fArray[n6] = fArray[n6] + fArray[3 + i];
                int n7 = 3 + i;
                fArray[n7] = fArray[n7] + fArray[0 + i];
                int n8 = 15 + i;
                fArray[n8] = fArray[n8] + fArray[9 + i];
                int n9 = 9 + i;
                fArray[n9] = fArray[n9] + fArray[3 + i];
                float f19 = fArray[12 + i] * 0.5f;
                float f20 = fArray[6 + i] * 0.8660254f;
                float f21 = fArray[0 + i] + f19;
                f17 = fArray[0 + i] - fArray[12 + i];
                f18 = f21 + f20;
                f16 = f21 - f20;
                f19 = fArray[15 + i] * 0.5f;
                f20 = fArray[9 + i] * 0.8660254f;
                f21 = fArray[3 + i] + f19;
                f14 = fArray[3 + i] - fArray[15 + i];
                f13 = f21 + f20;
                f15 = f21 - f20;
                f15 *= 1.9318516f;
                float f22 = f18;
                f18 += (f13 *= 0.5176381f);
                f13 = f22 - f13;
                f22 = f17;
                f17 += (f14 *= 0.70710677f);
                f14 = f22 - f14;
                f22 = f16;
                f16 += f15;
                f15 = f22 - f15;
                f18 *= 0.5043145f;
                f17 *= 0.5411961f;
                f16 *= 0.6302362f;
                f15 *= 0.8213398f;
                f14 *= 1.306563f;
                f13 *= 3.830649f;
                f10 = -f18 * 0.7933533f;
                f9 = -f18 * 0.6087614f;
                f11 = -f17 * 0.9238795f;
                f8 = -f17 * 0.38268343f;
                f12 = -f16 * 0.9914449f;
                f7 = -f16 * 0.13052619f;
                f18 = f15;
                f17 = f14 * 0.38268343f;
                f16 = f13 * 0.6087614f;
                f15 = -f13 * 0.7933533f;
                f14 = -f14 * 0.9238795f;
                f13 = -f18 * 0.9914449f;
                int n10 = n2 + 6;
                fArray2[n10] = fArray2[n10] + (f18 *= 0.13052619f);
                int n11 = n2 + 7;
                fArray2[n11] = fArray2[n11] + f17;
                int n12 = n2 + 8;
                fArray2[n12] = fArray2[n12] + f16;
                int n13 = n2 + 9;
                fArray2[n13] = fArray2[n13] + f15;
                int n14 = n2 + 10;
                fArray2[n14] = fArray2[n14] + f14;
                int n15 = n2 + 11;
                fArray2[n15] = fArray2[n15] + f13;
                int n16 = n2 + 12;
                fArray2[n16] = fArray2[n16] + f12;
                int n17 = n2 + 13;
                fArray2[n17] = fArray2[n17] + f11;
                int n18 = n2 + 14;
                fArray2[n18] = fArray2[n18] + f10;
                int n19 = n2 + 15;
                fArray2[n19] = fArray2[n19] + f9;
                int n20 = n2 + 16;
                fArray2[n20] = fArray2[n20] + f8;
                int n21 = n2 + 17;
                fArray2[n21] = fArray2[n21] + f7;
                n2 += 6;
            }
        } else {
            fArray[17] = fArray[17] + fArray[16];
            fArray[16] = fArray[16] + fArray[15];
            fArray[15] = fArray[15] + fArray[14];
            fArray[14] = fArray[14] + fArray[13];
            fArray[13] = fArray[13] + fArray[12];
            fArray[12] = fArray[12] + fArray[11];
            fArray[11] = fArray[11] + fArray[10];
            fArray[10] = fArray[10] + fArray[9];
            fArray[9] = fArray[9] + fArray[8];
            fArray[8] = fArray[8] + fArray[7];
            fArray[7] = fArray[7] + fArray[6];
            fArray[6] = fArray[6] + fArray[5];
            fArray[5] = fArray[5] + fArray[4];
            fArray[4] = fArray[4] + fArray[3];
            fArray[3] = fArray[3] + fArray[2];
            fArray[2] = fArray[2] + fArray[1];
            fArray[1] = fArray[1] + fArray[0];
            fArray[17] = fArray[17] + fArray[15];
            fArray[15] = fArray[15] + fArray[13];
            fArray[13] = fArray[13] + fArray[11];
            fArray[11] = fArray[11] + fArray[9];
            fArray[9] = fArray[9] + fArray[7];
            fArray[7] = fArray[7] + fArray[5];
            fArray[5] = fArray[5] + fArray[3];
            fArray[3] = fArray[3] + fArray[1];
            float f23 = fArray[0] + fArray[0];
            float f24 = f23 + fArray[12];
            float f25 = f24 + fArray[4] * 1.8793852f + fArray[8] * 1.5320889f + fArray[16] * 0.34729636f;
            float f26 = f23 + fArray[4] - fArray[8] - fArray[12] - fArray[12] - fArray[16];
            float f27 = f24 - fArray[4] * 0.34729636f - fArray[8] * 1.8793852f + fArray[16] * 1.5320889f;
            float f28 = f24 - fArray[4] * 1.5320889f + fArray[8] * 0.34729636f - fArray[16] * 1.8793852f;
            float f29 = fArray[0] - fArray[4] + fArray[8] - fArray[12] + fArray[16];
            float f30 = fArray[6] * 1.7320508f;
            float f31 = fArray[2] * 1.9696155f + f30 + fArray[10] * 1.2855753f + fArray[14] * 0.6840403f;
            float f32 = (fArray[2] - fArray[10] - fArray[14]) * 1.7320508f;
            float f33 = fArray[2] * 1.2855753f - f30 - fArray[10] * 0.6840403f + fArray[14] * 1.9696155f;
            float f34 = fArray[2] * 0.6840403f - f30 + fArray[10] * 1.9696155f - fArray[14] * 1.2855753f;
            float f35 = fArray[1] + fArray[1];
            float f36 = f35 + fArray[13];
            float f37 = f36 + fArray[5] * 1.8793852f + fArray[9] * 1.5320889f + fArray[17] * 0.34729636f;
            float f38 = f35 + fArray[5] - fArray[9] - fArray[13] - fArray[13] - fArray[17];
            float f39 = f36 - fArray[5] * 0.34729636f - fArray[9] * 1.8793852f + fArray[17] * 1.5320889f;
            float f40 = f36 - fArray[5] * 1.5320889f + fArray[9] * 0.34729636f - fArray[17] * 1.8793852f;
            float f41 = (fArray[1] - fArray[5] + fArray[9] - fArray[13] + fArray[17]) * 0.70710677f;
            float f42 = fArray[7] * 1.7320508f;
            float f43 = fArray[3] * 1.9696155f + f42 + fArray[11] * 1.2855753f + fArray[15] * 0.6840403f;
            float f44 = (fArray[3] - fArray[11] - fArray[15]) * 1.7320508f;
            float f45 = fArray[3] * 1.2855753f - f42 - fArray[11] * 0.6840403f + fArray[15] * 1.9696155f;
            float f46 = fArray[3] * 0.6840403f - f42 + fArray[11] * 1.9696155f - fArray[15] * 1.2855753f;
            float f47 = f25 + f31;
            float f48 = (f37 + f43) * 0.5019099f;
            f18 = f47 + f48;
            f = f47 - f48;
            f47 = f26 + f32;
            f48 = (f38 + f44) * 0.5176381f;
            f17 = f47 + f48;
            f2 = f47 - f48;
            f47 = f27 + f33;
            f48 = (f39 + f45) * 0.55168897f;
            f16 = f47 + f48;
            f3 = f47 - f48;
            f47 = f28 + f34;
            f48 = (f40 + f46) * 0.61038727f;
            f15 = f47 + f48;
            f4 = f47 - f48;
            f14 = f29 + f41;
            f5 = f29 - f41;
            f47 = f28 - f34;
            f48 = (f40 - f46) * 0.8717234f;
            f13 = f47 + f48;
            f6 = f47 - f48;
            f47 = f27 - f33;
            f48 = (f39 - f45) * 1.1831008f;
            f12 = f47 + f48;
            f7 = f47 - f48;
            f47 = f26 - f32;
            f48 = (f38 - f44) * 1.9318516f;
            f11 = f47 + f48;
            f8 = f47 - f48;
            f47 = f25 - f31;
            f48 = (f37 - f43) * 5.7368565f;
            f10 = f47 + f48;
            f9 = f47 - f48;
            float[] fArray3 = win[n];
            fArray2[0] = -f9 * fArray3[0];
            fArray2[1] = -f8 * fArray3[1];
            fArray2[2] = -f7 * fArray3[2];
            fArray2[3] = -f6 * fArray3[3];
            fArray2[4] = -f5 * fArray3[4];
            fArray2[5] = -f4 * fArray3[5];
            fArray2[6] = -f3 * fArray3[6];
            fArray2[7] = -f2 * fArray3[7];
            fArray2[8] = -f * fArray3[8];
            fArray2[9] = f * fArray3[9];
            fArray2[10] = f2 * fArray3[10];
            fArray2[11] = f3 * fArray3[11];
            fArray2[12] = f4 * fArray3[12];
            fArray2[13] = f5 * fArray3[13];
            fArray2[14] = f6 * fArray3[14];
            fArray2[15] = f7 * fArray3[15];
            fArray2[16] = f8 * fArray3[16];
            fArray2[17] = f9 * fArray3[17];
            fArray2[18] = f10 * fArray3[18];
            fArray2[19] = f11 * fArray3[19];
            fArray2[20] = f12 * fArray3[20];
            fArray2[21] = f13 * fArray3[21];
            fArray2[22] = f14 * fArray3[22];
            fArray2[23] = f15 * fArray3[23];
            fArray2[24] = f16 * fArray3[24];
            fArray2[25] = f17 * fArray3[25];
            fArray2[26] = f18 * fArray3[26];
            fArray2[27] = f18 * fArray3[27];
            fArray2[28] = f17 * fArray3[28];
            fArray2[29] = f16 * fArray3[29];
            fArray2[30] = f15 * fArray3[30];
            fArray2[31] = f14 * fArray3[31];
            fArray2[32] = f13 * fArray3[32];
            fArray2[33] = f12 * fArray3[33];
            fArray2[34] = f11 * fArray3[34];
            fArray2[35] = f10 * fArray3[35];
        }
    }

    private static float[] create_t_43() {
        float[] fArray = new float[8192];
        for (int i = 0; i < 8192; ++i) {
            fArray[i] = (float)Math.pow(i, 1.3333333333333333);
        }
        return fArray;
    }

    static int[] reorder(int[] nArray) {
        int n = 0;
        int[] nArray2 = new int[576];
        for (int i = 0; i < 13; ++i) {
            int n2 = nArray[i];
            int n3 = nArray[i + 1];
            for (int j = 0; j < 3; ++j) {
                for (int k = n2; k < n3; ++k) {
                    nArray2[3 * k + j] = n++;
                }
            }
        }
        return nArray2;
    }

    static {
        cs = new float[]{0.8574929f, 0.881742f, 0.94962865f, 0.9833146f, 0.9955178f, 0.9991606f, 0.9998992f, 0.99999315f};
        ca = new float[]{-0.51449573f, -0.47173196f, -0.31337744f, -0.1819132f, -0.09457419f, -0.040965583f, -0.014198569f, -0.0036999746f};
        win = new float[][]{{-0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f}, {-0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.33908543f, -0.3151181f, -0.29642227f, -0.28184548f, -0.5411961f, -0.2621323f, -0.25387916f, -0.2329629f, -0.19852729f, -0.15233535f, -0.0964964f, -0.03342383f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, {-0.0483008f, -0.15715657f, -0.28325045f, -0.42953748f, -1.2071068f, -0.8242648f, -1.1451749f, -1.769529f, -4.5470223f, -3.489053f, -0.7329629f, -0.15076515f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.15076514f, -0.7329629f, -3.489053f, -4.5470223f, -1.769529f, -1.1451749f, -0.8313774f, -1.306563f, -0.54142016f, -0.46528974f, -0.4106699f, -0.3700468f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f}};
        nr_of_sfb_block = new int[][][]{new int[][]{{6, 5, 5, 5}, {9, 9, 9, 9}, {6, 9, 9, 9}}, new int[][]{{6, 5, 7, 3}, {9, 9, 12, 6}, {6, 9, 12, 6}}, new int[][]{{11, 10, 0, 0}, {18, 18, 0, 0}, {15, 18, 0, 0}}, new int[][]{{7, 7, 7, 0}, {12, 12, 12, 0}, {6, 15, 12, 0}}, new int[][]{{6, 6, 6, 3}, {12, 9, 9, 6}, {6, 12, 9, 6}}, new int[][]{{8, 8, 5, 0}, {15, 12, 9, 0}, {6, 18, 9, 0}}};
    }

    class Sftable {
        public int[] l;
        public int[] s;

        public Sftable() {
            this.l = new int[5];
            this.s = new int[3];
        }

        public Sftable(int[] nArray, int[] nArray2) {
            this.l = nArray;
            this.s = nArray2;
        }
    }

    static class temporaire2 {
        public int[] l = new int[23];
        public int[][] s = new int[3][13];
    }

    static class III_side_info_t {
        public int main_data_begin = 0;
        public int private_bits = 0;
        public temporaire[] ch = new temporaire[2];

        public III_side_info_t() {
            this.ch[0] = new temporaire();
            this.ch[1] = new temporaire();
        }
    }

    static class temporaire {
        public int[] scfsi = new int[4];
        public gr_info_s[] gr = new gr_info_s[2];

        public temporaire() {
            this.gr[0] = new gr_info_s();
            this.gr[1] = new gr_info_s();
        }
    }

    static class gr_info_s {
        public int part2_3_length = 0;
        public int big_values = 0;
        public int global_gain = 0;
        public int scalefac_compress = 0;
        public int window_switching_flag = 0;
        public int block_type = 0;
        public int mixed_block_flag = 0;
        public int[] table_select = new int[3];
        public int[] subblock_gain = new int[3];
        public int region0_count = 0;
        public int region1_count = 0;
        public int preflag = 0;
        public int scalefac_scale = 0;
        public int count1table_select = 0;
    }

    static class SBI {
        public int[] l;
        public int[] s;

        public SBI() {
            this.l = new int[23];
            this.s = new int[14];
        }

        public SBI(int[] nArray, int[] nArray2) {
            this.l = nArray;
            this.s = nArray2;
        }
    }
}

