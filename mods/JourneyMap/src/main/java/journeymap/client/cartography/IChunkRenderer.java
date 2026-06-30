/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.cartography;

import journeymap.client.cartography.Stratum;
import journeymap.client.model.ChunkMD;
import journeymap.client.render.ComparableBufferedImage;

public interface IChunkRenderer {
    public boolean render(ComparableBufferedImage var1, ChunkMD var2, Integer var3);

    public void setStratumColors(Stratum var1, int var2, Integer var3, boolean var4, boolean var5, boolean var6);

    public float[] getAmbientColor();
}

