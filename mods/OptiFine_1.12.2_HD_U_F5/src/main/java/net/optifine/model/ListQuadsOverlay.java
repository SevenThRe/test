/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aox
 *  awt
 *  bvp
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListQuadsOverlay {
    private List<bvp> listQuads = new ArrayList<bvp>();
    private List<awt> listBlockStates = new ArrayList<awt>();
    private List<bvp> listQuadsSingle = Arrays.asList(new bvp[1]);

    public void addQuad(bvp quad, awt blockState) {
        if (quad == null) {
            return;
        }
        this.listQuads.add(quad);
        this.listBlockStates.add(blockState);
    }

    public int size() {
        return this.listQuads.size();
    }

    public bvp getQuad(int index) {
        return this.listQuads.get(index);
    }

    public awt getBlockState(int index) {
        if (index < 0 || index >= this.listBlockStates.size()) {
            return aox.a.t();
        }
        return this.listBlockStates.get(index);
    }

    public List<bvp> getListQuadsSingle(bvp quad) {
        this.listQuadsSingle.set(0, quad);
        return this.listQuadsSingle;
    }

    public void clear() {
        this.listQuads.clear();
        this.listBlockStates.clear();
    }
}

