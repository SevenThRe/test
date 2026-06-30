/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bvh
 *  bxr
 *  et
 */
package net.optifine.shaders;

import java.util.Iterator;
import net.optifine.BlockPosM;
import net.optifine.shaders.Iterator3d;

public class IteratorRenderChunks
implements Iterator<bxr> {
    private bvh viewFrustum;
    private Iterator3d Iterator3d;
    private BlockPosM posBlock = new BlockPosM(0, 0, 0);

    public IteratorRenderChunks(bvh viewFrustum, et posStart, et posEnd, int width, int height) {
        this.viewFrustum = viewFrustum;
        this.Iterator3d = new Iterator3d(posStart, posEnd, width, height);
    }

    @Override
    public boolean hasNext() {
        return this.Iterator3d.hasNext();
    }

    @Override
    public bxr next() {
        et pos = this.Iterator3d.next();
        this.posBlock.setXyz(pos.p() << 4, pos.q() << 4, pos.r() << 4);
        bxr rc = this.viewFrustum.a((et)this.posBlock);
        return rc;
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}

