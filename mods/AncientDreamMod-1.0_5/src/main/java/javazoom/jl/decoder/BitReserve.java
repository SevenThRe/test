/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

final class BitReserve {
    private static final int BUFSIZE = 32768;
    private static final int BUFSIZE_MASK = Short.MAX_VALUE;
    private int offset = 0;
    private int totbit = 0;
    private int buf_byte_idx = 0;
    private final int[] buf = new int[32768];
    private int buf_bit_idx;

    BitReserve() {
    }

    public int hsstell() {
        return this.totbit;
    }

    public int hgetbits(int n) {
        this.totbit += n;
        int n2 = 0;
        int n3 = this.buf_byte_idx;
        if (n3 + n < 32768) {
            while (n-- > 0) {
                n2 <<= 1;
                n2 |= this.buf[n3++] != 0 ? 1 : 0;
            }
        } else {
            while (n-- > 0) {
                n2 <<= 1;
                n2 |= this.buf[n3] != 0 ? 1 : 0;
                n3 = n3 + 1 & Short.MAX_VALUE;
            }
        }
        this.buf_byte_idx = n3;
        return n2;
    }

    public int hget1bit() {
        ++this.totbit;
        int n = this.buf[this.buf_byte_idx];
        this.buf_byte_idx = this.buf_byte_idx + 1 & Short.MAX_VALUE;
        return n;
    }

    public void hputbuf(int n) {
        int n2 = this.offset;
        this.buf[n2++] = n & 0x80;
        this.buf[n2++] = n & 0x40;
        this.buf[n2++] = n & 0x20;
        this.buf[n2++] = n & 0x10;
        this.buf[n2++] = n & 8;
        this.buf[n2++] = n & 4;
        this.buf[n2++] = n & 2;
        this.buf[n2++] = n & 1;
        this.offset = n2 == 32768 ? 0 : n2;
    }

    public void rewindNbits(int n) {
        this.totbit -= n;
        this.buf_byte_idx -= n;
        if (this.buf_byte_idx < 0) {
            this.buf_byte_idx += 32768;
        }
    }

    public void rewindNbytes(int n) {
        int n2 = n << 3;
        this.totbit -= n2;
        this.buf_byte_idx -= n2;
        if (this.buf_byte_idx < 0) {
            this.buf_byte_idx += 32768;
        }
    }
}

