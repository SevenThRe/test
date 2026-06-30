/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import java.io.IOException;
import javazoom.jl.decoder.JavaLayerUtils;
import javazoom.jl.decoder.Obuffer;

final class SynthesisFilter {
    private float[] v1;
    private float[] v2;
    private float[] actual_v;
    private int actual_write_pos;
    private float[] samples;
    private int channel;
    private float scalefactor;
    private float[] eq;
    private float[] _tmpOut = new float[32];
    private static final double MY_PI = Math.PI;
    private static final float cos1_64 = (float)(1.0 / (2.0 * Math.cos(0.04908738521234052)));
    private static final float cos3_64 = (float)(1.0 / (2.0 * Math.cos(0.14726215563702155)));
    private static final float cos5_64 = (float)(1.0 / (2.0 * Math.cos(0.2454369260617026)));
    private static final float cos7_64 = (float)(1.0 / (2.0 * Math.cos(0.3436116964863836)));
    private static final float cos9_64 = (float)(1.0 / (2.0 * Math.cos(0.44178646691106466)));
    private static final float cos11_64 = (float)(1.0 / (2.0 * Math.cos(0.5399612373357456)));
    private static final float cos13_64 = (float)(1.0 / (2.0 * Math.cos(0.6381360077604268)));
    private static final float cos15_64 = (float)(1.0 / (2.0 * Math.cos(0.7363107781851077)));
    private static final float cos17_64 = (float)(1.0 / (2.0 * Math.cos(0.8344855486097889)));
    private static final float cos19_64 = (float)(1.0 / (2.0 * Math.cos(0.9326603190344698)));
    private static final float cos21_64 = (float)(1.0 / (2.0 * Math.cos(1.030835089459151)));
    private static final float cos23_64 = (float)(1.0 / (2.0 * Math.cos(1.1290098598838318)));
    private static final float cos25_64 = (float)(1.0 / (2.0 * Math.cos(1.227184630308513)));
    private static final float cos27_64 = (float)(1.0 / (2.0 * Math.cos(1.325359400733194)));
    private static final float cos29_64 = (float)(1.0 / (2.0 * Math.cos(1.423534171157875)));
    private static final float cos31_64 = (float)(1.0 / (2.0 * Math.cos(1.521708941582556)));
    private static final float cos1_32 = (float)(1.0 / (2.0 * Math.cos(0.09817477042468103)));
    private static final float cos3_32 = (float)(1.0 / (2.0 * Math.cos(0.2945243112740431)));
    private static final float cos5_32 = (float)(1.0 / (2.0 * Math.cos(0.4908738521234052)));
    private static final float cos7_32 = (float)(1.0 / (2.0 * Math.cos(0.6872233929727672)));
    private static final float cos9_32 = (float)(1.0 / (2.0 * Math.cos(0.8835729338221293)));
    private static final float cos11_32 = (float)(1.0 / (2.0 * Math.cos(1.0799224746714913)));
    private static final float cos13_32 = (float)(1.0 / (2.0 * Math.cos(1.2762720155208536)));
    private static final float cos15_32 = (float)(1.0 / (2.0 * Math.cos(1.4726215563702154)));
    private static final float cos1_16 = (float)(1.0 / (2.0 * Math.cos(0.19634954084936207)));
    private static final float cos3_16 = (float)(1.0 / (2.0 * Math.cos(0.5890486225480862)));
    private static final float cos5_16 = (float)(1.0 / (2.0 * Math.cos(0.9817477042468103)));
    private static final float cos7_16 = (float)(1.0 / (2.0 * Math.cos(1.3744467859455345)));
    private static final float cos1_8 = (float)(1.0 / (2.0 * Math.cos(0.39269908169872414)));
    private static final float cos3_8 = (float)(1.0 / (2.0 * Math.cos(1.1780972450961724)));
    private static final float cos1_4 = (float)(1.0 / (2.0 * Math.cos(0.7853981633974483)));
    private static float[] d = null;
    private static float[][] d16 = null;

    public SynthesisFilter(int n, float f, float[] fArray) {
        if (d == null) {
            d = SynthesisFilter.load_d();
            d16 = SynthesisFilter.splitArray(d, 16);
        }
        this.v1 = new float[512];
        this.v2 = new float[512];
        this.samples = new float[32];
        this.channel = n;
        this.scalefactor = f;
        this.setEQ(this.eq);
        this.reset();
    }

    public void setEQ(float[] fArray) {
        this.eq = fArray;
        if (this.eq == null) {
            this.eq = new float[32];
            for (int i = 0; i < 32; ++i) {
                this.eq[i] = 1.0f;
            }
        }
        if (this.eq.length < 32) {
            throw new IllegalArgumentException("eq0");
        }
    }

    public void reset() {
        int n;
        for (n = 0; n < 512; ++n) {
            this.v2[n] = 0.0f;
            this.v1[n] = 0.0f;
        }
        for (n = 0; n < 32; ++n) {
            this.samples[n] = 0.0f;
        }
        this.actual_v = this.v1;
        this.actual_write_pos = 15;
    }

    public void input_sample(float f, int n) {
        this.samples[n] = this.eq[n] * f;
    }

    public void input_samples(float[] fArray) {
        for (int i = 31; i >= 0; --i) {
            this.samples[i] = fArray[i] * this.eq[i];
        }
    }

    private void compute_new_v() {
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
        float f19 = 0.0f;
        float f20 = 0.0f;
        float f21 = 0.0f;
        float f22 = 0.0f;
        float f23 = 0.0f;
        float f24 = 0.0f;
        float f25 = 0.0f;
        float f26 = 0.0f;
        float f27 = 0.0f;
        float f28 = 0.0f;
        float f29 = 0.0f;
        float f30 = 0.0f;
        float f31 = 0.0f;
        float f32 = 0.0f;
        float[] fArray = this.samples;
        float f33 = fArray[0];
        float f34 = fArray[1];
        float f35 = fArray[2];
        float f36 = fArray[3];
        float f37 = fArray[4];
        float f38 = fArray[5];
        float f39 = fArray[6];
        float f40 = fArray[7];
        float f41 = fArray[8];
        float f42 = fArray[9];
        float f43 = fArray[10];
        float f44 = fArray[11];
        float f45 = fArray[12];
        float f46 = fArray[13];
        float f47 = fArray[14];
        float f48 = fArray[15];
        float f49 = fArray[16];
        float f50 = fArray[17];
        float f51 = fArray[18];
        float f52 = fArray[19];
        float f53 = fArray[20];
        float f54 = fArray[21];
        float f55 = fArray[22];
        float f56 = fArray[23];
        float f57 = fArray[24];
        float f58 = fArray[25];
        float f59 = fArray[26];
        float f60 = fArray[27];
        float f61 = fArray[28];
        float f62 = fArray[29];
        float f63 = fArray[30];
        float f64 = fArray[31];
        float f65 = f33 + f64;
        float f66 = f34 + f63;
        float f67 = f35 + f62;
        float f68 = f36 + f61;
        float f69 = f37 + f60;
        float f70 = f38 + f59;
        float f71 = f39 + f58;
        float f72 = f40 + f57;
        float f73 = f41 + f56;
        float f74 = f42 + f55;
        float f75 = f43 + f54;
        float f76 = f44 + f53;
        float f77 = f45 + f52;
        float f78 = f46 + f51;
        float f79 = f47 + f50;
        float f80 = f48 + f49;
        float f81 = f65 + f80;
        float f82 = f66 + f79;
        float f83 = f67 + f78;
        float f84 = f68 + f77;
        float f85 = f69 + f76;
        float f86 = f70 + f75;
        float f87 = f71 + f74;
        float f88 = f72 + f73;
        float f89 = (f65 - f80) * cos1_32;
        float f90 = (f66 - f79) * cos3_32;
        float f91 = (f67 - f78) * cos5_32;
        float f92 = (f68 - f77) * cos7_32;
        float f93 = (f69 - f76) * cos9_32;
        float f94 = (f70 - f75) * cos11_32;
        float f95 = (f71 - f74) * cos13_32;
        float f96 = (f72 - f73) * cos15_32;
        f65 = f81 + f88;
        f66 = f82 + f87;
        f67 = f83 + f86;
        f68 = f84 + f85;
        f69 = (f81 - f88) * cos1_16;
        f70 = (f82 - f87) * cos3_16;
        f71 = (f83 - f86) * cos5_16;
        f72 = (f84 - f85) * cos7_16;
        f73 = f89 + f96;
        f74 = f90 + f95;
        f75 = f91 + f94;
        f76 = f92 + f93;
        f77 = (f89 - f96) * cos1_16;
        f78 = (f90 - f95) * cos3_16;
        f79 = (f91 - f94) * cos5_16;
        f80 = (f92 - f93) * cos7_16;
        f81 = f65 + f68;
        f82 = f66 + f67;
        f83 = (f65 - f68) * cos1_8;
        f84 = (f66 - f67) * cos3_8;
        f85 = f69 + f72;
        f86 = f70 + f71;
        f87 = (f69 - f72) * cos1_8;
        f88 = (f70 - f71) * cos3_8;
        f89 = f73 + f76;
        f90 = f74 + f75;
        f91 = (f73 - f76) * cos1_8;
        f92 = (f74 - f75) * cos3_8;
        f93 = f77 + f80;
        f94 = f78 + f79;
        f95 = (f77 - f80) * cos1_8;
        f96 = (f78 - f79) * cos3_8;
        f65 = f81 + f82;
        f66 = (f81 - f82) * cos1_4;
        f67 = f83 + f84;
        f68 = (f83 - f84) * cos1_4;
        f69 = f85 + f86;
        f70 = (f85 - f86) * cos1_4;
        f71 = f87 + f88;
        f72 = (f87 - f88) * cos1_4;
        f73 = f89 + f90;
        f74 = (f89 - f90) * cos1_4;
        f75 = f91 + f92;
        f76 = (f91 - f92) * cos1_4;
        f77 = f93 + f94;
        f78 = (f93 - f94) * cos1_4;
        f79 = f95 + f96;
        f80 = (f95 - f96) * cos1_4;
        f20 = f72;
        f28 = f20 + f70;
        f13 = -f28 - f71;
        f5 = -f71 - f72 - f69;
        f18 = f80;
        f22 = f18 + f76;
        f26 = f22 + f78;
        f30 = f80 + f78 + f74;
        f15 = -f30 - f79;
        float f97 = -f79 - f80 - f75 - f76;
        f11 = f97 - f78;
        f3 = -f79 - f80 - f77 - f73;
        f7 = f97 - f77;
        f = -f65;
        f32 = f66;
        f24 = f68;
        f9 = -f24 - f67;
        f65 = (f33 - f64) * cos1_64;
        f66 = (f34 - f63) * cos3_64;
        f67 = (f35 - f62) * cos5_64;
        f68 = (f36 - f61) * cos7_64;
        f69 = (f37 - f60) * cos9_64;
        f70 = (f38 - f59) * cos11_64;
        f71 = (f39 - f58) * cos13_64;
        f72 = (f40 - f57) * cos15_64;
        f73 = (f41 - f56) * cos17_64;
        f74 = (f42 - f55) * cos19_64;
        f75 = (f43 - f54) * cos21_64;
        f76 = (f44 - f53) * cos23_64;
        f77 = (f45 - f52) * cos25_64;
        f78 = (f46 - f51) * cos27_64;
        f79 = (f47 - f50) * cos29_64;
        f80 = (f48 - f49) * cos31_64;
        f81 = f65 + f80;
        f82 = f66 + f79;
        f83 = f67 + f78;
        f84 = f68 + f77;
        f85 = f69 + f76;
        f86 = f70 + f75;
        f87 = f71 + f74;
        f88 = f72 + f73;
        f89 = (f65 - f80) * cos1_32;
        f90 = (f66 - f79) * cos3_32;
        f91 = (f67 - f78) * cos5_32;
        f92 = (f68 - f77) * cos7_32;
        f93 = (f69 - f76) * cos9_32;
        f94 = (f70 - f75) * cos11_32;
        f95 = (f71 - f74) * cos13_32;
        f96 = (f72 - f73) * cos15_32;
        f65 = f81 + f88;
        f66 = f82 + f87;
        f67 = f83 + f86;
        f68 = f84 + f85;
        f69 = (f81 - f88) * cos1_16;
        f70 = (f82 - f87) * cos3_16;
        f71 = (f83 - f86) * cos5_16;
        f72 = (f84 - f85) * cos7_16;
        f73 = f89 + f96;
        f74 = f90 + f95;
        f75 = f91 + f94;
        f76 = f92 + f93;
        f77 = (f89 - f96) * cos1_16;
        f78 = (f90 - f95) * cos3_16;
        f79 = (f91 - f94) * cos5_16;
        f80 = (f92 - f93) * cos7_16;
        f81 = f65 + f68;
        f82 = f66 + f67;
        f83 = (f65 - f68) * cos1_8;
        f84 = (f66 - f67) * cos3_8;
        f85 = f69 + f72;
        f86 = f70 + f71;
        f87 = (f69 - f72) * cos1_8;
        f88 = (f70 - f71) * cos3_8;
        f89 = f73 + f76;
        f90 = f74 + f75;
        f91 = (f73 - f76) * cos1_8;
        f92 = (f74 - f75) * cos3_8;
        f93 = f77 + f80;
        f94 = f78 + f79;
        f95 = (f77 - f80) * cos1_8;
        f96 = (f78 - f79) * cos3_8;
        f65 = f81 + f82;
        f66 = (f81 - f82) * cos1_4;
        f67 = f83 + f84;
        f68 = (f83 - f84) * cos1_4;
        f69 = f85 + f86;
        f70 = (f85 - f86) * cos1_4;
        f71 = f87 + f88;
        f72 = (f87 - f88) * cos1_4;
        f73 = f89 + f90;
        f74 = (f89 - f90) * cos1_4;
        f75 = f91 + f92;
        f76 = (f91 - f92) * cos1_4;
        f77 = f93 + f94;
        f78 = (f93 - f94) * cos1_4;
        f79 = f95 + f96;
        f17 = f80 = (f95 - f96) * cos1_4;
        f19 = f17 + f72;
        f21 = f19 + f76;
        f27 = f21 + f70 + f78;
        f23 = f80 + f76 + f68;
        f25 = f23 + f78;
        f97 = f78 + f80 + f74;
        f31 = f97 + f66;
        f16 = -f31 - f79;
        f29 = f97 + f70 + f72;
        f14 = -f29 - f71 - f79;
        f97 = -f75 - f76 - f79 - f80;
        f10 = f97 - f78 - f67 - f68;
        f12 = f97 - f78 - f70 - f71 - f72;
        f8 = f97 - f77 - f67 - f68;
        float f98 = f69 + f71 + f72;
        f6 = f97 - f77 - f98;
        f97 = -f73 - f77 - f79 - f80;
        f2 = f97 - f65;
        f4 = f97 - f98;
        float[] fArray2 = this.actual_v;
        int n = this.actual_write_pos;
        fArray2[0 + n] = f32;
        fArray2[16 + n] = f31;
        fArray2[32 + n] = f30;
        fArray2[48 + n] = f29;
        fArray2[64 + n] = f28;
        fArray2[80 + n] = f27;
        fArray2[96 + n] = f26;
        fArray2[112 + n] = f25;
        fArray2[128 + n] = f24;
        fArray2[144 + n] = f23;
        fArray2[160 + n] = f22;
        fArray2[176 + n] = f21;
        fArray2[192 + n] = f20;
        fArray2[208 + n] = f19;
        fArray2[224 + n] = f18;
        fArray2[240 + n] = f17;
        fArray2[256 + n] = 0.0f;
        fArray2[272 + n] = -f17;
        fArray2[288 + n] = -f18;
        fArray2[304 + n] = -f19;
        fArray2[320 + n] = -f20;
        fArray2[336 + n] = -f21;
        fArray2[352 + n] = -f22;
        fArray2[368 + n] = -f23;
        fArray2[384 + n] = -f24;
        fArray2[400 + n] = -f25;
        fArray2[416 + n] = -f26;
        fArray2[432 + n] = -f27;
        fArray2[448 + n] = -f28;
        fArray2[464 + n] = -f29;
        fArray2[480 + n] = -f30;
        fArray2[496 + n] = -f31;
        fArray2 = this.actual_v == this.v1 ? this.v2 : this.v1;
        fArray2[0 + n] = -f32;
        fArray2[16 + n] = f16;
        fArray2[32 + n] = f15;
        fArray2[48 + n] = f14;
        fArray2[64 + n] = f13;
        fArray2[80 + n] = f12;
        fArray2[96 + n] = f11;
        fArray2[112 + n] = f10;
        fArray2[128 + n] = f9;
        fArray2[144 + n] = f8;
        fArray2[160 + n] = f7;
        fArray2[176 + n] = f6;
        fArray2[192 + n] = f5;
        fArray2[208 + n] = f4;
        fArray2[224 + n] = f3;
        fArray2[240 + n] = f2;
        fArray2[256 + n] = f;
        fArray2[272 + n] = f2;
        fArray2[288 + n] = f3;
        fArray2[304 + n] = f4;
        fArray2[320 + n] = f5;
        fArray2[336 + n] = f6;
        fArray2[352 + n] = f7;
        fArray2[368 + n] = f8;
        fArray2[384 + n] = f9;
        fArray2[400 + n] = f10;
        fArray2[416 + n] = f11;
        fArray2[432 + n] = f12;
        fArray2[448 + n] = f13;
        fArray2[464 + n] = f14;
        fArray2[480 + n] = f15;
        fArray2[496 + n] = f16;
    }

    private void compute_new_v_old() {
        float[] fArray = new float[32];
        float[] fArray2 = new float[16];
        float[] fArray3 = new float[16];
        for (int i = 31; i >= 0; --i) {
            fArray[i] = 0.0f;
        }
        float[] fArray4 = this.samples;
        fArray2[0] = fArray4[0] + fArray4[31];
        fArray2[1] = fArray4[1] + fArray4[30];
        fArray2[2] = fArray4[2] + fArray4[29];
        fArray2[3] = fArray4[3] + fArray4[28];
        fArray2[4] = fArray4[4] + fArray4[27];
        fArray2[5] = fArray4[5] + fArray4[26];
        fArray2[6] = fArray4[6] + fArray4[25];
        fArray2[7] = fArray4[7] + fArray4[24];
        fArray2[8] = fArray4[8] + fArray4[23];
        fArray2[9] = fArray4[9] + fArray4[22];
        fArray2[10] = fArray4[10] + fArray4[21];
        fArray2[11] = fArray4[11] + fArray4[20];
        fArray2[12] = fArray4[12] + fArray4[19];
        fArray2[13] = fArray4[13] + fArray4[18];
        fArray2[14] = fArray4[14] + fArray4[17];
        fArray2[15] = fArray4[15] + fArray4[16];
        fArray3[0] = fArray2[0] + fArray2[15];
        fArray3[1] = fArray2[1] + fArray2[14];
        fArray3[2] = fArray2[2] + fArray2[13];
        fArray3[3] = fArray2[3] + fArray2[12];
        fArray3[4] = fArray2[4] + fArray2[11];
        fArray3[5] = fArray2[5] + fArray2[10];
        fArray3[6] = fArray2[6] + fArray2[9];
        fArray3[7] = fArray2[7] + fArray2[8];
        fArray3[8] = (fArray2[0] - fArray2[15]) * cos1_32;
        fArray3[9] = (fArray2[1] - fArray2[14]) * cos3_32;
        fArray3[10] = (fArray2[2] - fArray2[13]) * cos5_32;
        fArray3[11] = (fArray2[3] - fArray2[12]) * cos7_32;
        fArray3[12] = (fArray2[4] - fArray2[11]) * cos9_32;
        fArray3[13] = (fArray2[5] - fArray2[10]) * cos11_32;
        fArray3[14] = (fArray2[6] - fArray2[9]) * cos13_32;
        fArray3[15] = (fArray2[7] - fArray2[8]) * cos15_32;
        fArray2[0] = fArray3[0] + fArray3[7];
        fArray2[1] = fArray3[1] + fArray3[6];
        fArray2[2] = fArray3[2] + fArray3[5];
        fArray2[3] = fArray3[3] + fArray3[4];
        fArray2[4] = (fArray3[0] - fArray3[7]) * cos1_16;
        fArray2[5] = (fArray3[1] - fArray3[6]) * cos3_16;
        fArray2[6] = (fArray3[2] - fArray3[5]) * cos5_16;
        fArray2[7] = (fArray3[3] - fArray3[4]) * cos7_16;
        fArray2[8] = fArray3[8] + fArray3[15];
        fArray2[9] = fArray3[9] + fArray3[14];
        fArray2[10] = fArray3[10] + fArray3[13];
        fArray2[11] = fArray3[11] + fArray3[12];
        fArray2[12] = (fArray3[8] - fArray3[15]) * cos1_16;
        fArray2[13] = (fArray3[9] - fArray3[14]) * cos3_16;
        fArray2[14] = (fArray3[10] - fArray3[13]) * cos5_16;
        fArray2[15] = (fArray3[11] - fArray3[12]) * cos7_16;
        fArray3[0] = fArray2[0] + fArray2[3];
        fArray3[1] = fArray2[1] + fArray2[2];
        fArray3[2] = (fArray2[0] - fArray2[3]) * cos1_8;
        fArray3[3] = (fArray2[1] - fArray2[2]) * cos3_8;
        fArray3[4] = fArray2[4] + fArray2[7];
        fArray3[5] = fArray2[5] + fArray2[6];
        fArray3[6] = (fArray2[4] - fArray2[7]) * cos1_8;
        fArray3[7] = (fArray2[5] - fArray2[6]) * cos3_8;
        fArray3[8] = fArray2[8] + fArray2[11];
        fArray3[9] = fArray2[9] + fArray2[10];
        fArray3[10] = (fArray2[8] - fArray2[11]) * cos1_8;
        fArray3[11] = (fArray2[9] - fArray2[10]) * cos3_8;
        fArray3[12] = fArray2[12] + fArray2[15];
        fArray3[13] = fArray2[13] + fArray2[14];
        fArray3[14] = (fArray2[12] - fArray2[15]) * cos1_8;
        fArray3[15] = (fArray2[13] - fArray2[14]) * cos3_8;
        fArray2[0] = fArray3[0] + fArray3[1];
        fArray2[1] = (fArray3[0] - fArray3[1]) * cos1_4;
        fArray2[2] = fArray3[2] + fArray3[3];
        fArray2[3] = (fArray3[2] - fArray3[3]) * cos1_4;
        fArray2[4] = fArray3[4] + fArray3[5];
        fArray2[5] = (fArray3[4] - fArray3[5]) * cos1_4;
        fArray2[6] = fArray3[6] + fArray3[7];
        fArray2[7] = (fArray3[6] - fArray3[7]) * cos1_4;
        fArray2[8] = fArray3[8] + fArray3[9];
        fArray2[9] = (fArray3[8] - fArray3[9]) * cos1_4;
        fArray2[10] = fArray3[10] + fArray3[11];
        fArray2[11] = (fArray3[10] - fArray3[11]) * cos1_4;
        fArray2[12] = fArray3[12] + fArray3[13];
        fArray2[13] = (fArray3[12] - fArray3[13]) * cos1_4;
        fArray2[14] = fArray3[14] + fArray3[15];
        fArray2[15] = (fArray3[14] - fArray3[15]) * cos1_4;
        fArray[12] = fArray2[7];
        fArray[4] = fArray[12] + fArray2[5];
        fArray[19] = -fArray[4] - fArray2[6];
        fArray[27] = -fArray2[6] - fArray2[7] - fArray2[4];
        fArray[14] = fArray2[15];
        fArray[10] = fArray[14] + fArray2[11];
        fArray[6] = fArray[10] + fArray2[13];
        fArray[2] = fArray2[15] + fArray2[13] + fArray2[9];
        fArray[17] = -fArray[2] - fArray2[14];
        float f = -fArray2[14] - fArray2[15] - fArray2[10] - fArray2[11];
        fArray[21] = f - fArray2[13];
        fArray[29] = -fArray2[14] - fArray2[15] - fArray2[12] - fArray2[8];
        fArray[25] = f - fArray2[12];
        fArray[31] = -fArray2[0];
        fArray[0] = fArray2[1];
        fArray[8] = fArray2[3];
        fArray[23] = -fArray[8] - fArray2[2];
        fArray2[0] = (fArray4[0] - fArray4[31]) * cos1_64;
        fArray2[1] = (fArray4[1] - fArray4[30]) * cos3_64;
        fArray2[2] = (fArray4[2] - fArray4[29]) * cos5_64;
        fArray2[3] = (fArray4[3] - fArray4[28]) * cos7_64;
        fArray2[4] = (fArray4[4] - fArray4[27]) * cos9_64;
        fArray2[5] = (fArray4[5] - fArray4[26]) * cos11_64;
        fArray2[6] = (fArray4[6] - fArray4[25]) * cos13_64;
        fArray2[7] = (fArray4[7] - fArray4[24]) * cos15_64;
        fArray2[8] = (fArray4[8] - fArray4[23]) * cos17_64;
        fArray2[9] = (fArray4[9] - fArray4[22]) * cos19_64;
        fArray2[10] = (fArray4[10] - fArray4[21]) * cos21_64;
        fArray2[11] = (fArray4[11] - fArray4[20]) * cos23_64;
        fArray2[12] = (fArray4[12] - fArray4[19]) * cos25_64;
        fArray2[13] = (fArray4[13] - fArray4[18]) * cos27_64;
        fArray2[14] = (fArray4[14] - fArray4[17]) * cos29_64;
        fArray2[15] = (fArray4[15] - fArray4[16]) * cos31_64;
        fArray3[0] = fArray2[0] + fArray2[15];
        fArray3[1] = fArray2[1] + fArray2[14];
        fArray3[2] = fArray2[2] + fArray2[13];
        fArray3[3] = fArray2[3] + fArray2[12];
        fArray3[4] = fArray2[4] + fArray2[11];
        fArray3[5] = fArray2[5] + fArray2[10];
        fArray3[6] = fArray2[6] + fArray2[9];
        fArray3[7] = fArray2[7] + fArray2[8];
        fArray3[8] = (fArray2[0] - fArray2[15]) * cos1_32;
        fArray3[9] = (fArray2[1] - fArray2[14]) * cos3_32;
        fArray3[10] = (fArray2[2] - fArray2[13]) * cos5_32;
        fArray3[11] = (fArray2[3] - fArray2[12]) * cos7_32;
        fArray3[12] = (fArray2[4] - fArray2[11]) * cos9_32;
        fArray3[13] = (fArray2[5] - fArray2[10]) * cos11_32;
        fArray3[14] = (fArray2[6] - fArray2[9]) * cos13_32;
        fArray3[15] = (fArray2[7] - fArray2[8]) * cos15_32;
        fArray2[0] = fArray3[0] + fArray3[7];
        fArray2[1] = fArray3[1] + fArray3[6];
        fArray2[2] = fArray3[2] + fArray3[5];
        fArray2[3] = fArray3[3] + fArray3[4];
        fArray2[4] = (fArray3[0] - fArray3[7]) * cos1_16;
        fArray2[5] = (fArray3[1] - fArray3[6]) * cos3_16;
        fArray2[6] = (fArray3[2] - fArray3[5]) * cos5_16;
        fArray2[7] = (fArray3[3] - fArray3[4]) * cos7_16;
        fArray2[8] = fArray3[8] + fArray3[15];
        fArray2[9] = fArray3[9] + fArray3[14];
        fArray2[10] = fArray3[10] + fArray3[13];
        fArray2[11] = fArray3[11] + fArray3[12];
        fArray2[12] = (fArray3[8] - fArray3[15]) * cos1_16;
        fArray2[13] = (fArray3[9] - fArray3[14]) * cos3_16;
        fArray2[14] = (fArray3[10] - fArray3[13]) * cos5_16;
        fArray2[15] = (fArray3[11] - fArray3[12]) * cos7_16;
        fArray3[0] = fArray2[0] + fArray2[3];
        fArray3[1] = fArray2[1] + fArray2[2];
        fArray3[2] = (fArray2[0] - fArray2[3]) * cos1_8;
        fArray3[3] = (fArray2[1] - fArray2[2]) * cos3_8;
        fArray3[4] = fArray2[4] + fArray2[7];
        fArray3[5] = fArray2[5] + fArray2[6];
        fArray3[6] = (fArray2[4] - fArray2[7]) * cos1_8;
        fArray3[7] = (fArray2[5] - fArray2[6]) * cos3_8;
        fArray3[8] = fArray2[8] + fArray2[11];
        fArray3[9] = fArray2[9] + fArray2[10];
        fArray3[10] = (fArray2[8] - fArray2[11]) * cos1_8;
        fArray3[11] = (fArray2[9] - fArray2[10]) * cos3_8;
        fArray3[12] = fArray2[12] + fArray2[15];
        fArray3[13] = fArray2[13] + fArray2[14];
        fArray3[14] = (fArray2[12] - fArray2[15]) * cos1_8;
        fArray3[15] = (fArray2[13] - fArray2[14]) * cos3_8;
        fArray2[0] = fArray3[0] + fArray3[1];
        fArray2[1] = (fArray3[0] - fArray3[1]) * cos1_4;
        fArray2[2] = fArray3[2] + fArray3[3];
        fArray2[3] = (fArray3[2] - fArray3[3]) * cos1_4;
        fArray2[4] = fArray3[4] + fArray3[5];
        fArray2[5] = (fArray3[4] - fArray3[5]) * cos1_4;
        fArray2[6] = fArray3[6] + fArray3[7];
        fArray2[7] = (fArray3[6] - fArray3[7]) * cos1_4;
        fArray2[8] = fArray3[8] + fArray3[9];
        fArray2[9] = (fArray3[8] - fArray3[9]) * cos1_4;
        fArray2[10] = fArray3[10] + fArray3[11];
        fArray2[11] = (fArray3[10] - fArray3[11]) * cos1_4;
        fArray2[12] = fArray3[12] + fArray3[13];
        fArray2[13] = (fArray3[12] - fArray3[13]) * cos1_4;
        fArray2[14] = fArray3[14] + fArray3[15];
        fArray2[15] = (fArray3[14] - fArray3[15]) * cos1_4;
        fArray[15] = fArray2[15];
        fArray[13] = fArray[15] + fArray2[7];
        fArray[11] = fArray[13] + fArray2[11];
        fArray[5] = fArray[11] + fArray2[5] + fArray2[13];
        fArray[9] = fArray2[15] + fArray2[11] + fArray2[3];
        fArray[7] = fArray[9] + fArray2[13];
        f = fArray2[13] + fArray2[15] + fArray2[9];
        fArray[1] = f + fArray2[1];
        fArray[16] = -fArray[1] - fArray2[14];
        fArray[3] = f + fArray2[5] + fArray2[7];
        fArray[18] = -fArray[3] - fArray2[6] - fArray2[14];
        f = -fArray2[10] - fArray2[11] - fArray2[14] - fArray2[15];
        fArray[22] = f - fArray2[13] - fArray2[2] - fArray2[3];
        fArray[20] = f - fArray2[13] - fArray2[5] - fArray2[6] - fArray2[7];
        fArray[24] = f - fArray2[12] - fArray2[2] - fArray2[3];
        float f2 = fArray2[4] + fArray2[6] + fArray2[7];
        fArray[26] = f - fArray2[12] - f2;
        f = -fArray2[8] - fArray2[12] - fArray2[14] - fArray2[15];
        fArray[30] = f - fArray2[0];
        fArray[28] = f - f2;
        fArray4 = fArray;
        float[] fArray5 = this.actual_v;
        fArray5[0 + this.actual_write_pos] = fArray4[0];
        fArray5[16 + this.actual_write_pos] = fArray4[1];
        fArray5[32 + this.actual_write_pos] = fArray4[2];
        fArray5[48 + this.actual_write_pos] = fArray4[3];
        fArray5[64 + this.actual_write_pos] = fArray4[4];
        fArray5[80 + this.actual_write_pos] = fArray4[5];
        fArray5[96 + this.actual_write_pos] = fArray4[6];
        fArray5[112 + this.actual_write_pos] = fArray4[7];
        fArray5[128 + this.actual_write_pos] = fArray4[8];
        fArray5[144 + this.actual_write_pos] = fArray4[9];
        fArray5[160 + this.actual_write_pos] = fArray4[10];
        fArray5[176 + this.actual_write_pos] = fArray4[11];
        fArray5[192 + this.actual_write_pos] = fArray4[12];
        fArray5[208 + this.actual_write_pos] = fArray4[13];
        fArray5[224 + this.actual_write_pos] = fArray4[14];
        fArray5[240 + this.actual_write_pos] = fArray4[15];
        fArray5[256 + this.actual_write_pos] = 0.0f;
        fArray5[272 + this.actual_write_pos] = -fArray4[15];
        fArray5[288 + this.actual_write_pos] = -fArray4[14];
        fArray5[304 + this.actual_write_pos] = -fArray4[13];
        fArray5[320 + this.actual_write_pos] = -fArray4[12];
        fArray5[336 + this.actual_write_pos] = -fArray4[11];
        fArray5[352 + this.actual_write_pos] = -fArray4[10];
        fArray5[368 + this.actual_write_pos] = -fArray4[9];
        fArray5[384 + this.actual_write_pos] = -fArray4[8];
        fArray5[400 + this.actual_write_pos] = -fArray4[7];
        fArray5[416 + this.actual_write_pos] = -fArray4[6];
        fArray5[432 + this.actual_write_pos] = -fArray4[5];
        fArray5[448 + this.actual_write_pos] = -fArray4[4];
        fArray5[464 + this.actual_write_pos] = -fArray4[3];
        fArray5[480 + this.actual_write_pos] = -fArray4[2];
        fArray5[496 + this.actual_write_pos] = -fArray4[1];
    }

    private void compute_pcm_samples0(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[0 + n] * fArray3[0] + fArray[15 + n] * fArray3[1] + fArray[14 + n] * fArray3[2] + fArray[13 + n] * fArray3[3] + fArray[12 + n] * fArray3[4] + fArray[11 + n] * fArray3[5] + fArray[10 + n] * fArray3[6] + fArray[9 + n] * fArray3[7] + fArray[8 + n] * fArray3[8] + fArray[7 + n] * fArray3[9] + fArray[6 + n] * fArray3[10] + fArray[5 + n] * fArray3[11] + fArray[4 + n] * fArray3[12] + fArray[3 + n] * fArray3[13] + fArray[2 + n] * fArray3[14] + fArray[1 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples1(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[1 + n] * fArray3[0] + fArray[0 + n] * fArray3[1] + fArray[15 + n] * fArray3[2] + fArray[14 + n] * fArray3[3] + fArray[13 + n] * fArray3[4] + fArray[12 + n] * fArray3[5] + fArray[11 + n] * fArray3[6] + fArray[10 + n] * fArray3[7] + fArray[9 + n] * fArray3[8] + fArray[8 + n] * fArray3[9] + fArray[7 + n] * fArray3[10] + fArray[6 + n] * fArray3[11] + fArray[5 + n] * fArray3[12] + fArray[4 + n] * fArray3[13] + fArray[3 + n] * fArray3[14] + fArray[2 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples2(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[2 + n] * fArray3[0] + fArray[1 + n] * fArray3[1] + fArray[0 + n] * fArray3[2] + fArray[15 + n] * fArray3[3] + fArray[14 + n] * fArray3[4] + fArray[13 + n] * fArray3[5] + fArray[12 + n] * fArray3[6] + fArray[11 + n] * fArray3[7] + fArray[10 + n] * fArray3[8] + fArray[9 + n] * fArray3[9] + fArray[8 + n] * fArray3[10] + fArray[7 + n] * fArray3[11] + fArray[6 + n] * fArray3[12] + fArray[5 + n] * fArray3[13] + fArray[4 + n] * fArray3[14] + fArray[3 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples3(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        boolean bl = false;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[3 + n] * fArray3[0] + fArray[2 + n] * fArray3[1] + fArray[1 + n] * fArray3[2] + fArray[0 + n] * fArray3[3] + fArray[15 + n] * fArray3[4] + fArray[14 + n] * fArray3[5] + fArray[13 + n] * fArray3[6] + fArray[12 + n] * fArray3[7] + fArray[11 + n] * fArray3[8] + fArray[10 + n] * fArray3[9] + fArray[9 + n] * fArray3[10] + fArray[8 + n] * fArray3[11] + fArray[7 + n] * fArray3[12] + fArray[6 + n] * fArray3[13] + fArray[5 + n] * fArray3[14] + fArray[4 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples4(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[4 + n] * fArray3[0] + fArray[3 + n] * fArray3[1] + fArray[2 + n] * fArray3[2] + fArray[1 + n] * fArray3[3] + fArray[0 + n] * fArray3[4] + fArray[15 + n] * fArray3[5] + fArray[14 + n] * fArray3[6] + fArray[13 + n] * fArray3[7] + fArray[12 + n] * fArray3[8] + fArray[11 + n] * fArray3[9] + fArray[10 + n] * fArray3[10] + fArray[9 + n] * fArray3[11] + fArray[8 + n] * fArray3[12] + fArray[7 + n] * fArray3[13] + fArray[6 + n] * fArray3[14] + fArray[5 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples5(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[5 + n] * fArray3[0] + fArray[4 + n] * fArray3[1] + fArray[3 + n] * fArray3[2] + fArray[2 + n] * fArray3[3] + fArray[1 + n] * fArray3[4] + fArray[0 + n] * fArray3[5] + fArray[15 + n] * fArray3[6] + fArray[14 + n] * fArray3[7] + fArray[13 + n] * fArray3[8] + fArray[12 + n] * fArray3[9] + fArray[11 + n] * fArray3[10] + fArray[10 + n] * fArray3[11] + fArray[9 + n] * fArray3[12] + fArray[8 + n] * fArray3[13] + fArray[7 + n] * fArray3[14] + fArray[6 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples6(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[6 + n] * fArray3[0] + fArray[5 + n] * fArray3[1] + fArray[4 + n] * fArray3[2] + fArray[3 + n] * fArray3[3] + fArray[2 + n] * fArray3[4] + fArray[1 + n] * fArray3[5] + fArray[0 + n] * fArray3[6] + fArray[15 + n] * fArray3[7] + fArray[14 + n] * fArray3[8] + fArray[13 + n] * fArray3[9] + fArray[12 + n] * fArray3[10] + fArray[11 + n] * fArray3[11] + fArray[10 + n] * fArray3[12] + fArray[9 + n] * fArray3[13] + fArray[8 + n] * fArray3[14] + fArray[7 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples7(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[7 + n] * fArray3[0] + fArray[6 + n] * fArray3[1] + fArray[5 + n] * fArray3[2] + fArray[4 + n] * fArray3[3] + fArray[3 + n] * fArray3[4] + fArray[2 + n] * fArray3[5] + fArray[1 + n] * fArray3[6] + fArray[0 + n] * fArray3[7] + fArray[15 + n] * fArray3[8] + fArray[14 + n] * fArray3[9] + fArray[13 + n] * fArray3[10] + fArray[12 + n] * fArray3[11] + fArray[11 + n] * fArray3[12] + fArray[10 + n] * fArray3[13] + fArray[9 + n] * fArray3[14] + fArray[8 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples8(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[8 + n] * fArray3[0] + fArray[7 + n] * fArray3[1] + fArray[6 + n] * fArray3[2] + fArray[5 + n] * fArray3[3] + fArray[4 + n] * fArray3[4] + fArray[3 + n] * fArray3[5] + fArray[2 + n] * fArray3[6] + fArray[1 + n] * fArray3[7] + fArray[0 + n] * fArray3[8] + fArray[15 + n] * fArray3[9] + fArray[14 + n] * fArray3[10] + fArray[13 + n] * fArray3[11] + fArray[12 + n] * fArray3[12] + fArray[11 + n] * fArray3[13] + fArray[10 + n] * fArray3[14] + fArray[9 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples9(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[9 + n] * fArray3[0] + fArray[8 + n] * fArray3[1] + fArray[7 + n] * fArray3[2] + fArray[6 + n] * fArray3[3] + fArray[5 + n] * fArray3[4] + fArray[4 + n] * fArray3[5] + fArray[3 + n] * fArray3[6] + fArray[2 + n] * fArray3[7] + fArray[1 + n] * fArray3[8] + fArray[0 + n] * fArray3[9] + fArray[15 + n] * fArray3[10] + fArray[14 + n] * fArray3[11] + fArray[13 + n] * fArray3[12] + fArray[12 + n] * fArray3[13] + fArray[11 + n] * fArray3[14] + fArray[10 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples10(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[10 + n] * fArray3[0] + fArray[9 + n] * fArray3[1] + fArray[8 + n] * fArray3[2] + fArray[7 + n] * fArray3[3] + fArray[6 + n] * fArray3[4] + fArray[5 + n] * fArray3[5] + fArray[4 + n] * fArray3[6] + fArray[3 + n] * fArray3[7] + fArray[2 + n] * fArray3[8] + fArray[1 + n] * fArray3[9] + fArray[0 + n] * fArray3[10] + fArray[15 + n] * fArray3[11] + fArray[14 + n] * fArray3[12] + fArray[13 + n] * fArray3[13] + fArray[12 + n] * fArray3[14] + fArray[11 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples11(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[11 + n] * fArray3[0] + fArray[10 + n] * fArray3[1] + fArray[9 + n] * fArray3[2] + fArray[8 + n] * fArray3[3] + fArray[7 + n] * fArray3[4] + fArray[6 + n] * fArray3[5] + fArray[5 + n] * fArray3[6] + fArray[4 + n] * fArray3[7] + fArray[3 + n] * fArray3[8] + fArray[2 + n] * fArray3[9] + fArray[1 + n] * fArray3[10] + fArray[0 + n] * fArray3[11] + fArray[15 + n] * fArray3[12] + fArray[14 + n] * fArray3[13] + fArray[13 + n] * fArray3[14] + fArray[12 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples12(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[12 + n] * fArray3[0] + fArray[11 + n] * fArray3[1] + fArray[10 + n] * fArray3[2] + fArray[9 + n] * fArray3[3] + fArray[8 + n] * fArray3[4] + fArray[7 + n] * fArray3[5] + fArray[6 + n] * fArray3[6] + fArray[5 + n] * fArray3[7] + fArray[4 + n] * fArray3[8] + fArray[3 + n] * fArray3[9] + fArray[2 + n] * fArray3[10] + fArray[1 + n] * fArray3[11] + fArray[0 + n] * fArray3[12] + fArray[15 + n] * fArray3[13] + fArray[14 + n] * fArray3[14] + fArray[13 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples13(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[13 + n] * fArray3[0] + fArray[12 + n] * fArray3[1] + fArray[11 + n] * fArray3[2] + fArray[10 + n] * fArray3[3] + fArray[9 + n] * fArray3[4] + fArray[8 + n] * fArray3[5] + fArray[7 + n] * fArray3[6] + fArray[6 + n] * fArray3[7] + fArray[5 + n] * fArray3[8] + fArray[4 + n] * fArray3[9] + fArray[3 + n] * fArray3[10] + fArray[2 + n] * fArray3[11] + fArray[1 + n] * fArray3[12] + fArray[0 + n] * fArray3[13] + fArray[15 + n] * fArray3[14] + fArray[14 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples14(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[14 + n] * fArray3[0] + fArray[13 + n] * fArray3[1] + fArray[12 + n] * fArray3[2] + fArray[11 + n] * fArray3[3] + fArray[10 + n] * fArray3[4] + fArray[9 + n] * fArray3[5] + fArray[8 + n] * fArray3[6] + fArray[7 + n] * fArray3[7] + fArray[6 + n] * fArray3[8] + fArray[5 + n] * fArray3[9] + fArray[4 + n] * fArray3[10] + fArray[3 + n] * fArray3[11] + fArray[2 + n] * fArray3[12] + fArray[1 + n] * fArray3[13] + fArray[0 + n] * fArray3[14] + fArray[15 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples15(Obuffer obuffer) {
        float[] fArray = this.actual_v;
        float[] fArray2 = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            float f;
            float[] fArray3 = d16[i];
            fArray2[i] = f = (fArray[15 + n] * fArray3[0] + fArray[14 + n] * fArray3[1] + fArray[13 + n] * fArray3[2] + fArray[12 + n] * fArray3[3] + fArray[11 + n] * fArray3[4] + fArray[10 + n] * fArray3[5] + fArray[9 + n] * fArray3[6] + fArray[8 + n] * fArray3[7] + fArray[7 + n] * fArray3[8] + fArray[6 + n] * fArray3[9] + fArray[5 + n] * fArray3[10] + fArray[4 + n] * fArray3[11] + fArray[3 + n] * fArray3[12] + fArray[2 + n] * fArray3[13] + fArray[1 + n] * fArray3[14] + fArray[0 + n] * fArray3[15]) * this.scalefactor;
            n += 16;
        }
    }

    private void compute_pcm_samples(Obuffer obuffer) {
        switch (this.actual_write_pos) {
            case 0: {
                this.compute_pcm_samples0(obuffer);
                break;
            }
            case 1: {
                this.compute_pcm_samples1(obuffer);
                break;
            }
            case 2: {
                this.compute_pcm_samples2(obuffer);
                break;
            }
            case 3: {
                this.compute_pcm_samples3(obuffer);
                break;
            }
            case 4: {
                this.compute_pcm_samples4(obuffer);
                break;
            }
            case 5: {
                this.compute_pcm_samples5(obuffer);
                break;
            }
            case 6: {
                this.compute_pcm_samples6(obuffer);
                break;
            }
            case 7: {
                this.compute_pcm_samples7(obuffer);
                break;
            }
            case 8: {
                this.compute_pcm_samples8(obuffer);
                break;
            }
            case 9: {
                this.compute_pcm_samples9(obuffer);
                break;
            }
            case 10: {
                this.compute_pcm_samples10(obuffer);
                break;
            }
            case 11: {
                this.compute_pcm_samples11(obuffer);
                break;
            }
            case 12: {
                this.compute_pcm_samples12(obuffer);
                break;
            }
            case 13: {
                this.compute_pcm_samples13(obuffer);
                break;
            }
            case 14: {
                this.compute_pcm_samples14(obuffer);
                break;
            }
            case 15: {
                this.compute_pcm_samples15(obuffer);
            }
        }
        if (obuffer != null) {
            obuffer.appendSamples(this.channel, this._tmpOut);
        }
    }

    public void calculate_pcm_samples(Obuffer obuffer) {
        this.compute_new_v();
        this.compute_pcm_samples(obuffer);
        this.actual_write_pos = this.actual_write_pos + 1 & 0xF;
        this.actual_v = this.actual_v == this.v1 ? this.v2 : this.v1;
        for (int i = 0; i < 32; ++i) {
            this.samples[i] = 0.0f;
        }
    }

    private static float[] load_d() {
        try {
            Class<Float> clazz = Float.TYPE;
            Object object = JavaLayerUtils.deserializeArrayResource("sfd.ser", clazz, 512);
            return (float[])object;
        }
        catch (IOException iOException) {
            throw new ExceptionInInitializerError(iOException);
        }
    }

    private static float[][] splitArray(float[] fArray, int n) {
        int n2 = fArray.length / n;
        float[][] fArrayArray = new float[n2][];
        for (int i = 0; i < n2; ++i) {
            fArrayArray[i] = SynthesisFilter.subArray(fArray, i * n, n);
        }
        return fArrayArray;
    }

    private static float[] subArray(float[] fArray, int n, int n2) {
        if (n + n2 > fArray.length) {
            n2 = fArray.length - n;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        float[] fArray2 = new float[n2];
        for (int i = 0; i < n2; ++i) {
            fArray2[i] = fArray[n + i];
        }
        return fArray2;
    }
}

