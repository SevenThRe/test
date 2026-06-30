/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bvp
 *  cdq
 */
package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import net.optifine.ConnectedProperties;
import net.optifine.ConnectedTextures;
import net.optifine.render.RenderEnv;

public class ConnectedTexturesCompact {
    private static final int COMPACT_NONE = 0;
    private static final int COMPACT_ALL = 1;
    private static final int COMPACT_V = 2;
    private static final int COMPACT_H = 3;
    private static final int COMPACT_HV = 4;

    public static bvp[] getConnectedTextureCtmCompact(int ctmIndex, ConnectedProperties cp, int side, bvp quad, RenderEnv renderEnv) {
        int tileIndex;
        if (cp.ctmTileIndexes != null && ctmIndex >= 0 && ctmIndex < cp.ctmTileIndexes.length && (tileIndex = cp.ctmTileIndexes[ctmIndex]) >= 0 && tileIndex <= cp.tileIcons.length) {
            return ConnectedTexturesCompact.getQuadsCompact(tileIndex, cp.tileIcons, quad, renderEnv);
        }
        switch (ctmIndex) {
            case 1: {
                return ConnectedTexturesCompact.getQuadsCompactH(0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 2: {
                return ConnectedTexturesCompact.getQuadsCompact(3, cp.tileIcons, quad, renderEnv);
            }
            case 3: {
                return ConnectedTexturesCompact.getQuadsCompactH(3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 12: {
                return ConnectedTexturesCompact.getQuadsCompactV(0, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 24: {
                return ConnectedTexturesCompact.getQuadsCompact(2, cp.tileIcons, quad, renderEnv);
            }
            case 36: {
                return ConnectedTexturesCompact.getQuadsCompactV(2, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 13: {
                return ConnectedTexturesCompact.getQuadsCompact4(0, 3, 2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 14: {
                return ConnectedTexturesCompact.getQuadsCompactV(3, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 15: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 0, 1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 25: {
                return ConnectedTexturesCompact.getQuadsCompactH(2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 26: {
                return ConnectedTexturesCompact.getQuadsCompact(1, cp.tileIcons, quad, renderEnv);
            }
            case 27: {
                return ConnectedTexturesCompact.getQuadsCompactH(1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 37: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 1, 0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 38: {
                return ConnectedTexturesCompact.getQuadsCompactV(1, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 39: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 4: {
                return ConnectedTexturesCompact.getQuadsCompact4(0, 3, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 5: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 0, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 16: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 17: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 28: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 29: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 40: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 41: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 6: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 7: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 18: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 19: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 30: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 1, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 31: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 42: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 43: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 8: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 9: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 20: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 21: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 32: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 33: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 44: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 45: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 10: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 11: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 22: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 23: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 34: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 35: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 46: {
                return ConnectedTexturesCompact.getQuadsCompact(4, cp.tileIcons, quad, renderEnv);
            }
        }
        return ConnectedTexturesCompact.getQuadsCompact(0, cp.tileIcons, quad, renderEnv);
    }

    private static bvp[] getQuadsCompactH(int indexLeft, int indexRight, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        return ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, indexLeft, Dir.RIGHT, indexRight, sprites, side, quad, renderEnv);
    }

    private static bvp[] getQuadsCompactV(int indexUp, int indexDown, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        return ConnectedTexturesCompact.getQuadsCompact(Dir.UP, indexUp, Dir.DOWN, indexDown, sprites, side, quad, renderEnv);
    }

    private static bvp[] getQuadsCompact4(int upLeft, int upRight, int downLeft, int downRight, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        if (upLeft == upRight) {
            if (downLeft == downRight) {
                return ConnectedTexturesCompact.getQuadsCompact(Dir.UP, upLeft, Dir.DOWN, downLeft, sprites, side, quad, renderEnv);
            }
            return ConnectedTexturesCompact.getQuadsCompact(Dir.UP, upLeft, Dir.DOWN_LEFT, downLeft, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
        }
        if (downLeft == downRight) {
            return ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN, downLeft, sprites, side, quad, renderEnv);
        }
        if (upLeft == downLeft) {
            if (upRight == downRight) {
                return ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, upLeft, Dir.RIGHT, upRight, sprites, side, quad, renderEnv);
            }
            return ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
        }
        if (upRight == downRight) {
            return ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.DOWN_LEFT, downLeft, Dir.RIGHT, upRight, sprites, side, quad, renderEnv);
        }
        return ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN_LEFT, downLeft, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
    }

    private static bvp[] getQuadsCompact(int index, cdq[] sprites, bvp quad, RenderEnv renderEnv) {
        cdq sprite = sprites[index];
        return ConnectedTextures.getQuads(sprite, quad, renderEnv);
    }

    private static bvp[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        bvp quad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        bvp quad2 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(quad1, quad2);
    }

    private static bvp[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, Dir dir3, int index3, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        bvp quad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        bvp quad2 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        bvp quad3 = ConnectedTexturesCompact.getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(quad1, quad2, quad3);
    }

    private static bvp[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, Dir dir3, int index3, Dir dir4, int index4, cdq[] sprites, int side, bvp quad, RenderEnv renderEnv) {
        bvp quad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        bvp quad2 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        bvp quad3 = ConnectedTexturesCompact.getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
        bvp quad4 = ConnectedTexturesCompact.getQuadCompact(sprites[index4], dir4, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(quad1, quad2, quad3, quad4);
    }

    private static bvp getQuadCompact(cdq sprite, Dir dir, int side, bvp quad, RenderEnv renderEnv) {
        switch (dir) {
            case UP: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 16, 8, side, quad, renderEnv);
            }
            case UP_RIGHT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 0, 16, 8, side, quad, renderEnv);
            }
            case RIGHT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 0, 16, 16, side, quad, renderEnv);
            }
            case DOWN_RIGHT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 8, 16, 16, side, quad, renderEnv);
            }
            case DOWN: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 8, 16, 16, side, quad, renderEnv);
            }
            case DOWN_LEFT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 8, 8, 16, side, quad, renderEnv);
            }
            case LEFT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 8, 16, side, quad, renderEnv);
            }
            case UP_LEFT: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 8, 8, side, quad, renderEnv);
            }
        }
        return quad;
    }

    private static bvp getQuadCompact(cdq sprite, Dir dir, int x1, int y1, int x2, int y2, int side, bvp quadIn, RenderEnv renderEnv) {
        bvp quad;
        IdentityHashMap<bvp, bvp> quadMap;
        Map[][] spriteQuadCompactMaps = ConnectedTextures.getSpriteQuadCompactMaps();
        if (spriteQuadCompactMaps == null) {
            return quadIn;
        }
        int spriteIndex = sprite.getIndexInMap();
        if (spriteIndex < 0 || spriteIndex >= spriteQuadCompactMaps.length) {
            return quadIn;
        }
        Map[] quadMaps = spriteQuadCompactMaps[spriteIndex];
        if (quadMaps == null) {
            quadMaps = new Map[Dir.VALUES.length];
            spriteQuadCompactMaps[spriteIndex] = quadMaps;
        }
        if ((quadMap = quadMaps[dir.ordinal()]) == null) {
            quadMaps[dir.ordinal()] = quadMap = new IdentityHashMap<bvp, bvp>(1);
        }
        if ((quad = (bvp)quadMap.get(quadIn)) == null) {
            quad = ConnectedTexturesCompact.makeSpriteQuadCompact(quadIn, sprite, side, x1, y1, x2, y2);
            quadMap.put(quadIn, quad);
        }
        return quad;
    }

    private static bvp makeSpriteQuadCompact(bvp quad, cdq sprite, int side, int x1, int y1, int x2, int y2) {
        int[] data = (int[])quad.b().clone();
        cdq spriteFrom = quad.a();
        for (int i = 0; i < 4; ++i) {
            ConnectedTexturesCompact.fixVertexCompact(data, i, spriteFrom, sprite, side, x1, y1, x2, y2);
        }
        bvp bq = new bvp(data, quad.d(), quad.e(), sprite);
        return bq;
    }

    private static void fixVertexCompact(int[] data, int vertex, cdq spriteFrom, cdq spriteTo, int side, int x1, int y1, int x2, int y2) {
        float cv;
        float cu;
        int mul = data.length / 4;
        int pos = mul * vertex;
        float u = Float.intBitsToFloat(data[pos + 4]);
        float v = Float.intBitsToFloat(data[pos + 4 + 1]);
        double su16 = spriteFrom.getSpriteU16(u);
        double sv16 = spriteFrom.getSpriteV16(v);
        float x = Float.intBitsToFloat(data[pos + 0]);
        float y = Float.intBitsToFloat(data[pos + 1]);
        float z = Float.intBitsToFloat(data[pos + 2]);
        switch (side) {
            case 0: {
                cu = x;
                cv = 1.0f - z;
                break;
            }
            case 1: {
                cu = x;
                cv = z;
                break;
            }
            case 5: {
                cu = 1.0f - z;
                cv = 1.0f - y;
                break;
            }
            case 4: {
                cu = z;
                cv = 1.0f - y;
                break;
            }
            case 3: {
                cu = x;
                cv = 1.0f - y;
                break;
            }
            case 2: {
                cu = 1.0f - x;
                cv = 1.0f - y;
                break;
            }
            default: {
                return;
            }
        }
        float u16F = 15.968f;
        float v16F = 15.968f;
        if (su16 < (double)x1) {
            cu = (float)((double)cu + ((double)x1 - su16) / (double)u16F);
            su16 = x1;
        }
        if (su16 > (double)x2) {
            cu = (float)((double)cu - (su16 - (double)x2) / (double)u16F);
            su16 = x2;
        }
        if (sv16 < (double)y1) {
            cv = (float)((double)cv + ((double)y1 - sv16) / (double)v16F);
            sv16 = y1;
        }
        if (sv16 > (double)y2) {
            cv = (float)((double)cv - (sv16 - (double)y2) / (double)v16F);
            sv16 = y2;
        }
        switch (side) {
            case 0: {
                x = cu;
                z = 1.0f - cv;
                break;
            }
            case 1: {
                x = cu;
                z = cv;
                break;
            }
            case 5: {
                z = 1.0f - cu;
                y = 1.0f - cv;
                break;
            }
            case 4: {
                z = cu;
                y = 1.0f - cv;
                break;
            }
            case 3: {
                x = cu;
                y = 1.0f - cv;
                break;
            }
            case 2: {
                x = 1.0f - cu;
                y = 1.0f - cv;
                break;
            }
            default: {
                return;
            }
        }
        data[pos + 4] = Float.floatToRawIntBits(spriteTo.a(su16));
        data[pos + 4 + 1] = Float.floatToRawIntBits(spriteTo.b(sv16));
        data[pos + 0] = Float.floatToRawIntBits(x);
        data[pos + 1] = Float.floatToRawIntBits(y);
        data[pos + 2] = Float.floatToRawIntBits(z);
    }

    private static enum Dir {
        UP,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN,
        DOWN_LEFT,
        LEFT,
        UP_LEFT;

        public static final Dir[] VALUES;

        static {
            VALUES = Dir.values();
        }
    }
}

