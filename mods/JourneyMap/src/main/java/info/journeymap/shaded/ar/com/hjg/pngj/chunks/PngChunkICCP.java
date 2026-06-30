/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkHelper;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSingle;

public class PngChunkICCP
extends PngChunkSingle {
    public static final String ID = "iCCP";
    private String profileName;
    private byte[] compressedProfile;

    public PngChunkICCP(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.BEFORE_PLTE_AND_IDAT;
    }

    public ChunkRaw createRawChunk() {
        ChunkRaw c = this.createEmptyChunk(this.profileName.length() + this.compressedProfile.length + 2, true);
        System.arraycopy(ChunkHelper.toBytes(this.profileName), 0, c.data, 0, this.profileName.length());
        c.data[this.profileName.length()] = 0;
        c.data[this.profileName.length() + 1] = 0;
        System.arraycopy(this.compressedProfile, 0, c.data, this.profileName.length() + 2, this.compressedProfile.length);
        return c;
    }

    public void parseFromRaw(ChunkRaw chunk) {
        int pos0 = ChunkHelper.posNullByte(chunk.data);
        this.profileName = ChunkHelper.toString(chunk.data, 0, pos0);
        int comp = chunk.data[pos0 + 1] & 0xFF;
        if (comp != 0) {
            throw new PngjException("bad compression for ChunkTypeICCP");
        }
        int compdatasize = chunk.data.length - (pos0 + 2);
        this.compressedProfile = new byte[compdatasize];
        System.arraycopy(chunk.data, pos0 + 2, this.compressedProfile, 0, compdatasize);
    }

    public void setProfileNameAndContent(String name, byte[] profile) {
        this.profileName = name;
        this.compressedProfile = ChunkHelper.compressBytes(profile, true);
    }

    public void setProfileNameAndContent(String name, String profile) {
        this.setProfileNameAndContent(name, ChunkHelper.toBytes(profile));
    }

    public String getProfileName() {
        return this.profileName;
    }

    public byte[] getProfile() {
        return ChunkHelper.compressBytes(this.compressedProfile, false);
    }

    public String getProfileAsString() {
        return ChunkHelper.toString(this.getProfile());
    }
}

