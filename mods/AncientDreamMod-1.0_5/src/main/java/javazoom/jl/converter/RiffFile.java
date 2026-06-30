/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RiffFile {
    public static final int DDC_SUCCESS = 0;
    public static final int DDC_FAILURE = 1;
    public static final int DDC_OUT_OF_MEMORY = 2;
    public static final int DDC_FILE_ERROR = 3;
    public static final int DDC_INVALID_CALL = 4;
    public static final int DDC_USER_ABORT = 5;
    public static final int DDC_INVALID_FILE = 6;
    public static final int RFM_UNKNOWN = 0;
    public static final int RFM_WRITE = 1;
    public static final int RFM_READ = 2;
    private RiffChunkHeader riff_header = new RiffChunkHeader();
    protected int fmode = 0;
    protected RandomAccessFile file = null;

    public RiffFile() {
        this.riff_header.ckID = RiffFile.FourCC("RIFF");
        this.riff_header.ckSize = 0;
    }

    public int CurrentFileMode() {
        return this.fmode;
    }

    public int Open(String string, int n) {
        int n2 = 0;
        if (this.fmode != 0) {
            n2 = this.Close();
        }
        if (n2 == 0) {
            switch (n) {
                case 1: {
                    try {
                        this.file = new RandomAccessFile(string, "rw");
                        try {
                            byte by;
                            byte[] byArray = new byte[8];
                            byArray[0] = (byte)(this.riff_header.ckID >>> 24 & 0xFF);
                            byArray[1] = (byte)(this.riff_header.ckID >>> 16 & 0xFF);
                            byArray[2] = (byte)(this.riff_header.ckID >>> 8 & 0xFF);
                            byArray[3] = (byte)(this.riff_header.ckID & 0xFF);
                            byte by2 = (byte)(this.riff_header.ckSize >>> 24 & 0xFF);
                            byte by3 = (byte)(this.riff_header.ckSize >>> 16 & 0xFF);
                            byte by4 = (byte)(this.riff_header.ckSize >>> 8 & 0xFF);
                            byArray[4] = by = (byte)(this.riff_header.ckSize & 0xFF);
                            byArray[5] = by4;
                            byArray[6] = by3;
                            byArray[7] = by2;
                            this.file.write(byArray, 0, 8);
                            this.fmode = 1;
                        }
                        catch (IOException iOException) {
                            this.file.close();
                            this.fmode = 0;
                        }
                    }
                    catch (IOException iOException) {
                        this.fmode = 0;
                        n2 = 3;
                    }
                    break;
                }
                case 2: {
                    try {
                        this.file = new RandomAccessFile(string, "r");
                        try {
                            byte[] byArray = new byte[8];
                            this.file.read(byArray, 0, 8);
                            this.fmode = 2;
                            this.riff_header.ckID = byArray[0] << 24 & 0xFF000000 | byArray[1] << 16 & 0xFF0000 | byArray[2] << 8 & 0xFF00 | byArray[3] & 0xFF;
                            this.riff_header.ckSize = byArray[4] << 24 & 0xFF000000 | byArray[5] << 16 & 0xFF0000 | byArray[6] << 8 & 0xFF00 | byArray[7] & 0xFF;
                        }
                        catch (IOException iOException) {
                            this.file.close();
                            this.fmode = 0;
                        }
                    }
                    catch (IOException iOException) {
                        this.fmode = 0;
                        n2 = 3;
                    }
                    break;
                }
                default: {
                    n2 = 4;
                }
            }
        }
        return n2;
    }

    public int Write(byte[] byArray, int n) {
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(byArray, 0, n);
            this.fmode = 1;
        }
        catch (IOException iOException) {
            return 3;
        }
        this.riff_header.ckSize += n;
        return 0;
    }

    public int Write(short[] sArray, int n) {
        byte[] byArray = new byte[n];
        int n2 = 0;
        for (int i = 0; i < n; i += 2) {
            byArray[i] = (byte)(sArray[n2] & 0xFF);
            byArray[i + 1] = (byte)(sArray[n2++] >>> 8 & 0xFF);
        }
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(byArray, 0, n);
            this.fmode = 1;
        }
        catch (IOException iOException) {
            return 3;
        }
        this.riff_header.ckSize += n;
        return 0;
    }

    public int Write(RiffChunkHeader riffChunkHeader, int n) {
        byte by;
        byte[] byArray = new byte[8];
        byArray[0] = (byte)(riffChunkHeader.ckID >>> 24 & 0xFF);
        byArray[1] = (byte)(riffChunkHeader.ckID >>> 16 & 0xFF);
        byArray[2] = (byte)(riffChunkHeader.ckID >>> 8 & 0xFF);
        byArray[3] = (byte)(riffChunkHeader.ckID & 0xFF);
        byte by2 = (byte)(riffChunkHeader.ckSize >>> 24 & 0xFF);
        byte by3 = (byte)(riffChunkHeader.ckSize >>> 16 & 0xFF);
        byte by4 = (byte)(riffChunkHeader.ckSize >>> 8 & 0xFF);
        byArray[4] = by = (byte)(riffChunkHeader.ckSize & 0xFF);
        byArray[5] = by4;
        byArray[6] = by3;
        byArray[7] = by2;
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(byArray, 0, n);
            this.fmode = 1;
        }
        catch (IOException iOException) {
            return 3;
        }
        this.riff_header.ckSize += n;
        return 0;
    }

    public int Write(short s, int n) {
        short s2 = (short)(s >>> 8 & 0xFF | s << 8 & 0xFF00);
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.writeShort(s2);
            this.fmode = 1;
        }
        catch (IOException iOException) {
            return 3;
        }
        this.riff_header.ckSize += n;
        return 0;
    }

    public int Write(int n, int n2) {
        short s = (short)(n >>> 16 & 0xFFFF);
        short s2 = (short)(n & 0xFFFF);
        short s3 = (short)(s >>> 8 & 0xFF | s << 8 & 0xFF00);
        short s4 = (short)(s2 >>> 8 & 0xFF | s2 << 8 & 0xFF00);
        int n3 = s4 << 16 & 0xFFFF0000 | s3 & 0xFFFF;
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.writeInt(n3);
            this.fmode = 1;
        }
        catch (IOException iOException) {
            return 3;
        }
        this.riff_header.ckSize += n2;
        return 0;
    }

    public int Read(byte[] byArray, int n) {
        int n2 = 0;
        try {
            this.file.read(byArray, 0, n);
        }
        catch (IOException iOException) {
            n2 = 3;
        }
        return n2;
    }

    public int Expect(String string, int n) {
        byte by = 0;
        int n2 = 0;
        try {
            while (n-- != 0) {
                by = this.file.readByte();
                if (by == string.charAt(n2++)) continue;
                return 3;
            }
        }
        catch (IOException iOException) {
            return 3;
        }
        return 0;
    }

    public int Close() {
        int n = 0;
        switch (this.fmode) {
            case 1: {
                try {
                    this.file.seek(0L);
                    try {
                        byte[] byArray = new byte[8];
                        byArray[0] = (byte)(this.riff_header.ckID >>> 24 & 0xFF);
                        byArray[1] = (byte)(this.riff_header.ckID >>> 16 & 0xFF);
                        byArray[2] = (byte)(this.riff_header.ckID >>> 8 & 0xFF);
                        byArray[3] = (byte)(this.riff_header.ckID & 0xFF);
                        byArray[7] = (byte)(this.riff_header.ckSize >>> 24 & 0xFF);
                        byArray[6] = (byte)(this.riff_header.ckSize >>> 16 & 0xFF);
                        byArray[5] = (byte)(this.riff_header.ckSize >>> 8 & 0xFF);
                        byArray[4] = (byte)(this.riff_header.ckSize & 0xFF);
                        this.file.write(byArray, 0, 8);
                        this.file.close();
                    }
                    catch (IOException iOException) {
                        n = 3;
                    }
                }
                catch (IOException iOException) {
                    n = 3;
                }
                break;
            }
            case 2: {
                try {
                    this.file.close();
                    break;
                }
                catch (IOException iOException) {
                    n = 3;
                }
            }
        }
        this.file = null;
        this.fmode = 0;
        return n;
    }

    public long CurrentFilePosition() {
        long l;
        try {
            l = this.file.getFilePointer();
        }
        catch (IOException iOException) {
            l = -1L;
        }
        return l;
    }

    public int Backpatch(long l, RiffChunkHeader riffChunkHeader, int n) {
        if (this.file == null) {
            return 4;
        }
        try {
            this.file.seek(l);
        }
        catch (IOException iOException) {
            return 3;
        }
        return this.Write(riffChunkHeader, n);
    }

    public int Backpatch(long l, byte[] byArray, int n) {
        if (this.file == null) {
            return 4;
        }
        try {
            this.file.seek(l);
        }
        catch (IOException iOException) {
            return 3;
        }
        return this.Write(byArray, n);
    }

    protected int Seek(long l) {
        int n;
        try {
            this.file.seek(l);
            n = 0;
        }
        catch (IOException iOException) {
            n = 3;
        }
        return n;
    }

    private String DDCRET_String(int n) {
        switch (n) {
            case 0: {
                return "DDC_SUCCESS";
            }
            case 1: {
                return "DDC_FAILURE";
            }
            case 2: {
                return "DDC_OUT_OF_MEMORY";
            }
            case 3: {
                return "DDC_FILE_ERROR";
            }
            case 4: {
                return "DDC_INVALID_CALL";
            }
            case 5: {
                return "DDC_USER_ABORT";
            }
            case 6: {
                return "DDC_INVALID_FILE";
            }
        }
        return "Unknown Error";
    }

    public static int FourCC(String string) {
        byte[] byArray = new byte[]{32, 32, 32, 32};
        string.getBytes(0, 4, byArray, 0);
        int n = byArray[0] << 24 & 0xFF000000 | byArray[1] << 16 & 0xFF0000 | byArray[2] << 8 & 0xFF00 | byArray[3] & 0xFF;
        return n;
    }

    class RiffChunkHeader {
        public int ckID = 0;
        public int ckSize = 0;
    }
}

