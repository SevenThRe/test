/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

public final class Crc16 {
    private static short polynomial = (short)-32763;
    private short crc = (short)-1;

    public void add_bits(int n, int n2) {
        int n3 = 1 << n2 - 1;
        do {
            if ((this.crc & 0x8000) == 0 ^ (n & n3) == 0) {
                this.crc = (short)(this.crc << 1);
                this.crc = (short)(this.crc ^ polynomial);
                continue;
            }
            this.crc = (short)(this.crc << 1);
        } while ((n3 >>>= 1) != 0);
    }

    public short checksum() {
        short s = this.crc;
        this.crc = (short)-1;
        return s;
    }
}

