/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.render.map;

public class TilePos
implements Comparable<TilePos> {
    public final int deltaX;
    public final int deltaZ;
    final double startX;
    final double startZ;
    final double endX;
    final double endZ;

    TilePos(int deltaX, int deltaZ) {
        this.deltaX = deltaX;
        this.deltaZ = deltaZ;
        this.startX = deltaX * 512;
        this.startZ = deltaZ * 512;
        this.endX = this.startX + 512.0;
        this.endZ = this.startZ + 512.0;
    }

    public int hashCode() {
        int prime = 37;
        int result = 1;
        result = 37 * result + this.deltaX;
        result = 37 * result + this.deltaZ;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        TilePos other = (TilePos)obj;
        if (this.deltaX != other.deltaX) {
            return false;
        }
        return this.deltaZ == other.deltaZ;
    }

    public String toString() {
        return "TilePos [" + this.deltaX + "," + this.deltaZ + "]";
    }

    @Override
    public int compareTo(TilePos o) {
        int result = new Integer(this.deltaZ).compareTo(o.deltaZ);
        if (result == 0) {
            result = new Integer(this.deltaX).compareTo(o.deltaX);
        }
        return result;
    }
}

