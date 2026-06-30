/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import javazoom.jl.converter.RiffFile;

public class WaveFile
extends RiffFile {
    public static final int MAX_WAVE_CHANNELS = 2;
    private WaveFormat_Chunk wave_format;
    private RiffFile.RiffChunkHeader pcm_data = new RiffFile.RiffChunkHeader(this);
    private long pcm_data_offset = 0L;
    private int num_samples = 0;

    public WaveFile() {
        this.wave_format = new WaveFormat_Chunk();
        this.pcm_data.ckID = WaveFile.FourCC("data");
        this.pcm_data.ckSize = 0;
        this.num_samples = 0;
    }

    public int OpenForWrite(String string, int n, short s, short s2) {
        byte[] byArray;
        if (string == null || s != 8 && s != 16 || s2 < 1 || s2 > 2) {
            return 4;
        }
        this.wave_format.data.Config(n, s, s2);
        int n2 = this.Open(string, 1);
        if (n2 == 0 && (n2 = this.Write(byArray = new byte[]{87, 65, 86, 69}, 4)) == 0) {
            n2 = this.Write(this.wave_format.header, 8);
            n2 = this.Write(this.wave_format.data.wFormatTag, 2);
            n2 = this.Write(this.wave_format.data.nChannels, 2);
            n2 = this.Write(this.wave_format.data.nSamplesPerSec, 4);
            n2 = this.Write(this.wave_format.data.nAvgBytesPerSec, 4);
            n2 = this.Write(this.wave_format.data.nBlockAlign, 2);
            n2 = this.Write(this.wave_format.data.nBitsPerSample, 2);
            if (n2 == 0) {
                this.pcm_data_offset = this.CurrentFilePosition();
                n2 = this.Write(this.pcm_data, 8);
            }
        }
        return n2;
    }

    public int WriteData(short[] sArray, int n) {
        int n2 = n * 2;
        this.pcm_data.ckSize += n2;
        return super.Write(sArray, n2);
    }

    public int Close() {
        int n = 0;
        if (this.fmode == 1) {
            n = this.Backpatch(this.pcm_data_offset, this.pcm_data, 8);
        }
        if (n == 0) {
            n = super.Close();
        }
        return n;
    }

    public int SamplingRate() {
        return this.wave_format.data.nSamplesPerSec;
    }

    public short BitsPerSample() {
        return this.wave_format.data.nBitsPerSample;
    }

    public short NumChannels() {
        return this.wave_format.data.nChannels;
    }

    public int NumSamples() {
        return this.num_samples;
    }

    public int OpenForWrite(String string, WaveFile waveFile) {
        return this.OpenForWrite(string, waveFile.SamplingRate(), waveFile.BitsPerSample(), waveFile.NumChannels());
    }

    public long CurrentFilePosition() {
        return super.CurrentFilePosition();
    }

    public class WaveFileSample {
        public short[] chan = new short[2];
    }

    class WaveFormat_Chunk {
        public RiffFile.RiffChunkHeader header;
        public WaveFormat_ChunkData data;

        public WaveFormat_Chunk() {
            this.header = new RiffFile.RiffChunkHeader(WaveFile.this);
            this.data = new WaveFormat_ChunkData();
            this.header.ckID = RiffFile.FourCC("fmt ");
            this.header.ckSize = 16;
        }

        public int VerifyValidity() {
            boolean bl;
            boolean bl2 = bl = this.header.ckID == RiffFile.FourCC("fmt ") && (this.data.nChannels == 1 || this.data.nChannels == 2) && this.data.nAvgBytesPerSec == this.data.nChannels * this.data.nSamplesPerSec * this.data.nBitsPerSample / 8 && this.data.nBlockAlign == this.data.nChannels * this.data.nBitsPerSample / 8;
            if (bl) {
                return 1;
            }
            return 0;
        }
    }

    class WaveFormat_ChunkData {
        public short wFormatTag = 1;
        public short nChannels = 0;
        public int nSamplesPerSec = 0;
        public int nAvgBytesPerSec = 0;
        public short nBlockAlign = 0;
        public short nBitsPerSample = 0;

        public WaveFormat_ChunkData() {
            this.Config(44100, (short)16, (short)1);
        }

        public void Config(int n, short s, short s2) {
            this.nSamplesPerSec = n;
            this.nChannels = s2;
            this.nBitsPerSample = s;
            this.nAvgBytesPerSec = this.nChannels * this.nSamplesPerSec * this.nBitsPerSample / 8;
            this.nBlockAlign = (short)(this.nChannels * this.nBitsPerSample / 8);
        }
    }
}

