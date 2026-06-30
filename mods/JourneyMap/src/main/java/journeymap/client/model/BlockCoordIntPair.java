/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.model;

public class BlockCoordIntPair {
    public int x;
    public int z;

    public BlockCoordIntPair() {
        this.setLocation(0, 0);
    }

    public BlockCoordIntPair(int x, int z) {
        this.setLocation(x, z);
    }

    public void setLocation(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BlockCoordIntPair that = (BlockCoordIntPair)o;
        if (this.x != that.x) {
            return false;
        }
        return this.z == that.z;
    }

    public int hashCode() {
        int result = this.x;
        result = 31 * result + this.z;
        return result;
    }
}

