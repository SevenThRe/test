/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkHelper;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkPredicate;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSPLT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkTextVar;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ChunksList {
    public static final int CHUNK_GROUP_0_IDHR = 0;
    public static final int CHUNK_GROUP_1_AFTERIDHR = 1;
    public static final int CHUNK_GROUP_2_PLTE = 2;
    public static final int CHUNK_GROUP_3_AFTERPLTE = 3;
    public static final int CHUNK_GROUP_4_IDAT = 4;
    public static final int CHUNK_GROUP_5_AFTERIDAT = 5;
    public static final int CHUNK_GROUP_6_END = 6;
    List<PngChunk> chunks = new ArrayList<PngChunk>();
    final ImageInfo imageInfo;
    boolean withPlte = false;

    public ChunksList(ImageInfo imfinfo) {
        this.imageInfo = imfinfo;
    }

    public List<PngChunk> getChunks() {
        return this.chunks;
    }

    protected static List<PngChunk> getXById(List<PngChunk> list, final String id, final String innerid) {
        if (innerid == null) {
            return ChunkHelper.filterList(list, new ChunkPredicate(){

                public boolean match(PngChunk c) {
                    return c.id.equals(id);
                }
            });
        }
        return ChunkHelper.filterList(list, new ChunkPredicate(){

            public boolean match(PngChunk c) {
                if (!c.id.equals(id)) {
                    return false;
                }
                if (c instanceof PngChunkTextVar && !((PngChunkTextVar)c).getKey().equals(innerid)) {
                    return false;
                }
                return !(c instanceof PngChunkSPLT) || ((PngChunkSPLT)c).getPalName().equals(innerid);
            }
        });
    }

    public void appendReadChunk(PngChunk chunk, int chunkGroup) {
        chunk.setChunkGroup(chunkGroup);
        this.chunks.add(chunk);
        if (chunk.id.equals("PLTE")) {
            this.withPlte = true;
        }
    }

    public List<? extends PngChunk> getById(String id) {
        return this.getById(id, null);
    }

    public List<? extends PngChunk> getById(String id, String innerid) {
        return ChunksList.getXById(this.chunks, id, innerid);
    }

    public PngChunk getById1(String id) {
        return this.getById1(id, false);
    }

    public PngChunk getById1(String id, boolean failIfMultiple) {
        return this.getById1(id, null, failIfMultiple);
    }

    public PngChunk getById1(String id, String innerid, boolean failIfMultiple) {
        List<? extends PngChunk> list = this.getById(id, innerid);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1 && (failIfMultiple || !list.get(0).allowsMultiple())) {
            throw new PngjException("unexpected multiple chunks id=" + id);
        }
        return list.get(list.size() - 1);
    }

    public List<PngChunk> getEquivalent(final PngChunk c2) {
        return ChunkHelper.filterList(this.chunks, new ChunkPredicate(){

            public boolean match(PngChunk c) {
                return ChunkHelper.equivalent(c, c2);
            }
        });
    }

    public String toString() {
        return "ChunkList: read: " + this.chunks.size();
    }

    public String toStringFull() {
        StringBuilder sb = new StringBuilder(this.toString());
        sb.append("\n Read:\n");
        for (PngChunk chunk : this.chunks) {
            sb.append(chunk).append(" G=" + chunk.getChunkGroup() + "\n");
        }
        return sb.toString();
    }
}

