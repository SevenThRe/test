/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;

public abstract class PngChunkSingle
extends PngChunk {
    protected PngChunkSingle(String id, ImageInfo imgInfo) {
        super(id, imgInfo);
    }

    public final boolean allowsMultiple() {
        return false;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
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
        PngChunkSingle other = (PngChunkSingle)obj;
        return !(this.id == null ? other.id != null : !this.id.equals(other.id));
    }
}

