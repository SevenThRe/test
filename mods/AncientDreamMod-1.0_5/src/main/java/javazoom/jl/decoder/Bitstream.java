/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import javazoom.jl.decoder.BitstreamErrors;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Crc16;
import javazoom.jl.decoder.Header;

public final class Bitstream
implements BitstreamErrors {
    static byte INITIAL_SYNC = 0;
    static byte STRICT_SYNC = 1;
    private static final int BUFFER_INT_SIZE = 433;
    private final int[] framebuffer = new int[433];
    private int framesize;
    private byte[] frame_bytes = new byte[1732];
    private int wordpointer;
    private int bitindex;
    private int syncword;
    private int header_pos = 0;
    private boolean single_ch_mode;
    private final int[] bitmask = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, Short.MAX_VALUE, 65535, 131071};
    private final PushbackInputStream source;
    private final Header header = new Header();
    private final byte[] syncbuf = new byte[4];
    private Crc16[] crc = new Crc16[1];
    private byte[] rawid3v2 = null;
    private boolean firstframe = true;

    public Bitstream(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        inputStream = new BufferedInputStream(inputStream);
        this.loadID3v2(inputStream);
        this.firstframe = true;
        this.source = new PushbackInputStream(inputStream, 1732);
        this.closeFrame();
    }

    public int header_pos() {
        return this.header_pos;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void loadID3v2(InputStream inputStream) {
        int n = -1;
        try {
            inputStream.mark(10);
            this.header_pos = n = this.readID3v2Header(inputStream);
        }
        catch (IOException iOException) {
        }
        finally {
            try {
                inputStream.reset();
            }
            catch (IOException iOException) {}
        }
        try {
            if (n > 0) {
                this.rawid3v2 = new byte[n];
                inputStream.read(this.rawid3v2, 0, this.rawid3v2.length);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private int readID3v2Header(InputStream inputStream) throws IOException {
        byte[] byArray = new byte[4];
        int n = -10;
        inputStream.read(byArray, 0, 3);
        if (byArray[0] == 73 && byArray[1] == 68 && byArray[2] == 51) {
            inputStream.read(byArray, 0, 3);
            byte by = byArray[0];
            byte by2 = byArray[1];
            inputStream.read(byArray, 0, 4);
            n = (byArray[0] << 21) + (byArray[1] << 14) + (byArray[2] << 7) + byArray[3];
        }
        return n + 10;
    }

    public InputStream getRawID3v2() {
        if (this.rawid3v2 == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawid3v2);
        return byteArrayInputStream;
    }

    public void close() throws BitstreamException {
        try {
            this.source.close();
        }
        catch (IOException iOException) {
            throw this.newBitstreamException(258, iOException);
        }
    }

    public Header readFrame() throws BitstreamException {
        Header header;
        block7: {
            header = null;
            try {
                header = this.readNextFrame();
                if (this.firstframe) {
                    header.parseVBR(this.frame_bytes);
                    this.firstframe = false;
                }
            }
            catch (BitstreamException bitstreamException) {
                if (bitstreamException.getErrorCode() == 261) {
                    try {
                        this.closeFrame();
                        header = this.readNextFrame();
                    }
                    catch (BitstreamException bitstreamException2) {
                        if (bitstreamException2.getErrorCode() != 260) {
                            throw this.newBitstreamException(bitstreamException2.getErrorCode(), bitstreamException2);
                        }
                        break block7;
                    }
                }
                if (bitstreamException.getErrorCode() == 260) break block7;
                throw this.newBitstreamException(bitstreamException.getErrorCode(), bitstreamException);
            }
        }
        return header;
    }

    private Header readNextFrame() throws BitstreamException {
        if (this.framesize == -1) {
            this.nextFrame();
        }
        return this.header;
    }

    private void nextFrame() throws BitstreamException {
        this.header.read_header(this, this.crc);
    }

    public void unreadFrame() throws BitstreamException {
        if (this.wordpointer == -1 && this.bitindex == -1 && this.framesize > 0) {
            try {
                this.source.unread(this.frame_bytes, 0, this.framesize);
            }
            catch (IOException iOException) {
                throw this.newBitstreamException(258);
            }
        }
    }

    public void closeFrame() {
        this.framesize = -1;
        this.wordpointer = -1;
        this.bitindex = -1;
    }

    public boolean isSyncCurrentPosition(int n) throws BitstreamException {
        int n2 = this.readBytes(this.syncbuf, 0, 4);
        int n3 = this.syncbuf[0] << 24 & 0xFF000000 | this.syncbuf[1] << 16 & 0xFF0000 | this.syncbuf[2] << 8 & 0xFF00 | this.syncbuf[3] << 0 & 0xFF;
        try {
            this.source.unread(this.syncbuf, 0, n2);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        boolean bl = false;
        switch (n2) {
            case 0: {
                bl = true;
                break;
            }
            case 4: {
                bl = this.isSyncMark(n3, n, this.syncword);
            }
        }
        return bl;
    }

    public int readBits(int n) {
        return this.get_bits(n);
    }

    public int readCheckedBits(int n) {
        return this.get_bits(n);
    }

    protected BitstreamException newBitstreamException(int n) {
        return new BitstreamException(n, null);
    }

    protected BitstreamException newBitstreamException(int n, Throwable throwable) {
        return new BitstreamException(n, throwable);
    }

    int syncHeader(byte by) throws BitstreamException {
        boolean bl;
        int n = this.readBytes(this.syncbuf, 0, 3);
        if (n != 3) {
            throw this.newBitstreamException(260, null);
        }
        int n2 = this.syncbuf[0] << 16 & 0xFF0000 | this.syncbuf[1] << 8 & 0xFF00 | this.syncbuf[2] << 0 & 0xFF;
        do {
            n2 <<= 8;
            if (this.readBytes(this.syncbuf, 3, 1) == 1) continue;
            throw this.newBitstreamException(260, null);
        } while (!(bl = this.isSyncMark(n2 |= this.syncbuf[3] & 0xFF, by, this.syncword)));
        return n2;
    }

    public boolean isSyncMark(int n, int n2, int n3) {
        boolean bl = false;
        if (n2 == INITIAL_SYNC) {
            bl = (n & 0xFFE00000) == -2097152;
        } else {
            boolean bl2 = (n & 0xFFF80C00) == n3 && (n & 0xC0) == 192 == this.single_ch_mode ? true : (bl = false);
        }
        if (bl) {
            boolean bl3 = bl = (n >>> 10 & 3) != 3;
        }
        if (bl) {
            boolean bl4 = bl = (n >>> 17 & 3) != 0;
        }
        if (bl) {
            bl = (n >>> 19 & 3) != 1;
        }
        return bl;
    }

    int read_frame_data(int n) throws BitstreamException {
        int n2 = 0;
        n2 = this.readFully(this.frame_bytes, 0, n);
        this.framesize = n;
        this.wordpointer = -1;
        this.bitindex = -1;
        return n2;
    }

    void parse_frame() throws BitstreamException {
        int n = 0;
        byte[] byArray = this.frame_bytes;
        int n2 = this.framesize;
        for (int i = 0; i < n2; i += 4) {
            boolean bl = false;
            byte by = 0;
            byte by2 = 0;
            byte by3 = 0;
            byte by4 = 0;
            by = byArray[i];
            if (i + 1 < n2) {
                by2 = byArray[i + 1];
            }
            if (i + 2 < n2) {
                by3 = byArray[i + 2];
            }
            if (i + 3 < n2) {
                by4 = byArray[i + 3];
            }
            this.framebuffer[n++] = by << 24 & 0xFF000000 | by2 << 16 & 0xFF0000 | by3 << 8 & 0xFF00 | by4 & 0xFF;
        }
        this.wordpointer = 0;
        this.bitindex = 0;
    }

    public int get_bits(int n) {
        int n2 = 0;
        int n3 = this.bitindex + n;
        if (this.wordpointer < 0) {
            this.wordpointer = 0;
        }
        if (n3 <= 32) {
            n2 = this.framebuffer[this.wordpointer] >>> 32 - n3 & this.bitmask[n];
            if ((this.bitindex += n) == 32) {
                this.bitindex = 0;
                ++this.wordpointer;
            }
            return n2;
        }
        int n4 = this.framebuffer[this.wordpointer] & 0xFFFF;
        ++this.wordpointer;
        int n5 = this.framebuffer[this.wordpointer] & 0xFFFF0000;
        n2 = n4 << 16 & 0xFFFF0000 | n5 >>> 16 & 0xFFFF;
        n2 >>>= 48 - n3;
        this.bitindex = n3 - 32;
        return n2 &= this.bitmask[n];
    }

    void set_syncword(int n) {
        this.syncword = n & 0xFFFFFF3F;
        this.single_ch_mode = (n & 0xC0) == 192;
    }

    private int readFully(byte[] byArray, int n, int n2) throws BitstreamException {
        int n3 = 0;
        try {
            while (n2 > 0) {
                int n4 = this.source.read(byArray, n, n2);
                if (n4 == -1) {
                    while (n2-- > 0) {
                        byArray[n++] = 0;
                    }
                    break;
                }
                n3 += n4;
                n += n4;
                n2 -= n4;
            }
        }
        catch (IOException iOException) {
            throw this.newBitstreamException(258, iOException);
        }
        return n3;
    }

    private int readBytes(byte[] byArray, int n, int n2) throws BitstreamException {
        int n3 = 0;
        try {
            int n4;
            while (n2 > 0 && (n4 = this.source.read(byArray, n, n2)) != -1) {
                n3 += n4;
                n += n4;
                n2 -= n4;
            }
        }
        catch (IOException iOException) {
            throw this.newBitstreamException(258, iOException);
        }
        return n3;
    }
}

