/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.io;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageLineInt;
import info.journeymap.shaded.ar.com.hjg.pngj.PngReader;
import info.journeymap.shaded.ar.com.hjg.pngj.PngWriter;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkLoadBehaviour;
import java.io.File;
import java.util.Arrays;
import journeymap.common.Journeymap;

public class PngjHelper {
    public static void mergeFiles(File[] tiles, File destFile, int tileColumns, int tileSize) {
        int ntiles = tiles.length;
        int tileRows = (ntiles + tileColumns - 1) / tileColumns;
        PngReader[] readers = new PngReader[tileColumns];
        ImageInfo destImgInfo = new ImageInfo(tileSize * tileColumns, tileSize * tileRows, 8, true);
        PngWriter pngw = new PngWriter(destFile, destImgInfo, true);
        pngw.getMetadata().setText("Author", "JourneyMap" + Journeymap.JM_VERSION);
        pngw.getMetadata().setText("Comment", "http://journeymap.info/");
        ImageLineInt destLine = new ImageLineInt(destImgInfo);
        int lineLen = tileSize * 4;
        int gridColor = 135;
        boolean showGrid = Journeymap.getClient().getFullMapProperties().showGrid.get();
        int destRow = 0;
        for (int ty = 0; ty < tileRows; ++ty) {
            int tx;
            int nTilesXcur = ty < tileRows - 1 ? tileColumns : ntiles - (tileRows - 1) * tileColumns;
            Arrays.fill(destLine.getScanline(), 0);
            for (tx = 0; tx < nTilesXcur; ++tx) {
                readers[tx] = new PngReader(tiles[tx + ty * tileColumns]);
                readers[tx].setChunkLoadBehaviour(ChunkLoadBehaviour.LOAD_CHUNK_NEVER);
            }
            int srcRow = 0;
            block4: while (srcRow < tileSize) {
                for (int tx2 = 0; tx2 < nTilesXcur; ++tx2) {
                    ImageLineInt srcLine = (ImageLineInt)readers[tx2].readRow(srcRow);
                    int[] src = srcLine.getScanline();
                    if (showGrid) {
                        int skip = srcRow % 16 == 0 ? 4 : 64;
                        for (int i = 0; i <= src.length - skip; i += skip) {
                            src[i] = (src[i] + src[i] + 135) / 3;
                            src[i + 1] = (src[i + 1] + src[i + 1] + 135) / 3;
                            src[i + 2] = (src[i + 2] + src[i + 2] + 135) / 3;
                            src[i + 3] = 255;
                        }
                    }
                    int[] dest = destLine.getScanline();
                    int destPos = lineLen * tx2;
                    try {
                        System.arraycopy(src, 0, dest, destPos, lineLen);
                        continue;
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        Journeymap.getLogger().error("Bad image data. Src len=" + src.length + ", dest len=" + dest.length + ", destPos=" + destPos);
                        break block4;
                    }
                }
                pngw.writeRow(destLine, destRow);
                ++srcRow;
                ++destRow;
            }
            for (tx = 0; tx < nTilesXcur; ++tx) {
                readers[tx].end();
            }
        }
        pngw.end();
    }
}

