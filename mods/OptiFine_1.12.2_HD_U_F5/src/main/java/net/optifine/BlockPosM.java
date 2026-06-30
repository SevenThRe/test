/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  et
 *  fa
 *  rk
 */
package net.optifine;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

public class BlockPosM
extends et {
    private int mx;
    private int my;
    private int mz;
    private int level;
    private BlockPosM[] facings;
    private boolean needsUpdate;

    public BlockPosM(int x, int y, int z) {
        this(x, y, z, 0);
    }

    public BlockPosM(double xIn, double yIn, double zIn) {
        this(rk.c((double)xIn), rk.c((double)yIn), rk.c((double)zIn));
    }

    public BlockPosM(int x, int y, int z, int level) {
        super(0, 0, 0);
        this.mx = x;
        this.my = y;
        this.mz = z;
        this.level = level;
    }

    public int p() {
        return this.mx;
    }

    public int q() {
        return this.my;
    }

    public int r() {
        return this.mz;
    }

    public void setXyz(int x, int y, int z) {
        this.mx = x;
        this.my = y;
        this.mz = z;
        this.needsUpdate = true;
    }

    public void setXyz(double xIn, double yIn, double zIn) {
        this.setXyz(rk.c((double)xIn), rk.c((double)yIn), rk.c((double)zIn));
    }

    public et a(fa facing) {
        int index;
        BlockPosM bpm;
        if (this.level <= 0) {
            return super.a(facing, 1).h();
        }
        if (this.facings == null) {
            this.facings = new BlockPosM[fa.n.length];
        }
        if (this.needsUpdate) {
            this.update();
        }
        if ((bpm = this.facings[index = facing.a()]) == null) {
            int nx = this.mx + facing.g();
            int ny = this.my + facing.h();
            int nz = this.mz + facing.i();
            this.facings[index] = bpm = new BlockPosM(nx, ny, nz, this.level - 1);
        }
        return bpm;
    }

    public et a(fa facing, int n) {
        if (n == 1) {
            return this.a(facing);
        }
        return super.a(facing, n).h();
    }

    private void update() {
        for (int i = 0; i < 6; ++i) {
            BlockPosM bpm = this.facings[i];
            if (bpm == null) continue;
            fa facing = fa.n[i];
            int nx = this.mx + facing.g();
            int ny = this.my + facing.h();
            int nz = this.mz + facing.i();
            bpm.setXyz(nx, ny, nz);
        }
        this.needsUpdate = false;
    }

    public et h() {
        return new et(this.mx, this.my, this.mz);
    }

    public static Iterable getAllInBoxMutable(et from, et to) {
        final et posFrom = new et(Math.min(from.p(), to.p()), Math.min(from.q(), to.q()), Math.min(from.r(), to.r()));
        final et posTo = new et(Math.max(from.p(), to.p()), Math.max(from.q(), to.q()), Math.max(from.r(), to.r()));
        return new Iterable(){

            public Iterator iterator() {
                return new AbstractIterator(){
                    private BlockPosM theBlockPosM = null;

                    protected BlockPosM computeNext0() {
                        if (this.theBlockPosM == null) {
                            this.theBlockPosM = new BlockPosM(posFrom.p(), posFrom.q(), posFrom.r(), 3);
                            return this.theBlockPosM;
                        }
                        if (this.theBlockPosM.equals(posTo)) {
                            return (BlockPosM)((Object)this.endOfData());
                        }
                        int bx = this.theBlockPosM.p();
                        int by = this.theBlockPosM.q();
                        int bz = this.theBlockPosM.r();
                        if (bx < posTo.p()) {
                            ++bx;
                        } else if (by < posTo.q()) {
                            bx = posFrom.p();
                            ++by;
                        } else if (bz < posTo.r()) {
                            bx = posFrom.p();
                            by = posFrom.q();
                            ++bz;
                        }
                        this.theBlockPosM.setXyz(bx, by, bz);
                        return this.theBlockPosM;
                    }

                    protected Object computeNext() {
                        return this.computeNext0();
                    }
                };
            }
        };
    }
}

