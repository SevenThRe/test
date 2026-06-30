/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  anh
 *  aow
 *  aox
 *  aqy
 *  ata
 *  atl
 *  aua
 *  aub
 *  auo
 *  awp
 *  awt
 *  axj
 *  bib
 *  bvp
 *  cdp
 *  cdq
 *  cer
 *  cfy
 *  et
 *  fa
 *  nf
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import net.optifine.BetterGrass;
import net.optifine.BlockDir;
import net.optifine.ConnectedProperties;
import net.optifine.ConnectedTexturesCompact;
import net.optifine.config.Matches;
import net.optifine.model.BlockModelUtils;
import net.optifine.model.ListQuadsOverlay;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TileEntityUtils;

public class ConnectedTextures {
    private static Map[] spriteQuadMaps = null;
    private static Map[] spriteQuadFullMaps = null;
    private static Map[][] spriteQuadCompactMaps = null;
    private static ConnectedProperties[][] blockProperties = null;
    private static ConnectedProperties[][] tileProperties = null;
    private static boolean multipass = false;
    protected static final int UNKNOWN = -1;
    protected static final int Y_NEG_DOWN = 0;
    protected static final int Y_POS_UP = 1;
    protected static final int Z_NEG_NORTH = 2;
    protected static final int Z_POS_SOUTH = 3;
    protected static final int X_NEG_WEST = 4;
    protected static final int X_POS_EAST = 5;
    private static final int Y_AXIS = 0;
    private static final int Z_AXIS = 1;
    private static final int X_AXIS = 2;
    public static final awt AIR_DEFAULT_STATE = aox.a.t();
    private static cdq emptySprite = null;
    private static final BlockDir[] SIDES_Y_NEG_DOWN = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.NORTH, BlockDir.SOUTH};
    private static final BlockDir[] SIDES_Y_POS_UP = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.SOUTH, BlockDir.NORTH};
    private static final BlockDir[] SIDES_Z_NEG_NORTH = new BlockDir[]{BlockDir.EAST, BlockDir.WEST, BlockDir.DOWN, BlockDir.UP};
    private static final BlockDir[] SIDES_Z_POS_SOUTH = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.DOWN, BlockDir.UP};
    private static final BlockDir[] SIDES_X_NEG_WEST = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.DOWN, BlockDir.UP};
    private static final BlockDir[] SIDES_X_POS_EAST = new BlockDir[]{BlockDir.SOUTH, BlockDir.NORTH, BlockDir.DOWN, BlockDir.UP};
    private static final BlockDir[] SIDES_Z_NEG_NORTH_Z_AXIS = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.UP, BlockDir.DOWN};
    private static final BlockDir[] SIDES_X_POS_EAST_X_AXIS = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.UP, BlockDir.DOWN};
    private static final BlockDir[] EDGES_Y_NEG_DOWN = new BlockDir[]{BlockDir.NORTH_EAST, BlockDir.NORTH_WEST, BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST};
    private static final BlockDir[] EDGES_Y_POS_UP = new BlockDir[]{BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST, BlockDir.NORTH_EAST, BlockDir.NORTH_WEST};
    private static final BlockDir[] EDGES_Z_NEG_NORTH = new BlockDir[]{BlockDir.DOWN_WEST, BlockDir.DOWN_EAST, BlockDir.UP_WEST, BlockDir.UP_EAST};
    private static final BlockDir[] EDGES_Z_POS_SOUTH = new BlockDir[]{BlockDir.DOWN_EAST, BlockDir.DOWN_WEST, BlockDir.UP_EAST, BlockDir.UP_WEST};
    private static final BlockDir[] EDGES_X_NEG_WEST = new BlockDir[]{BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH, BlockDir.UP_SOUTH, BlockDir.UP_NORTH};
    private static final BlockDir[] EDGES_X_POS_EAST = new BlockDir[]{BlockDir.DOWN_NORTH, BlockDir.DOWN_SOUTH, BlockDir.UP_NORTH, BlockDir.UP_SOUTH};
    private static final BlockDir[] EDGES_Z_NEG_NORTH_Z_AXIS = new BlockDir[]{BlockDir.UP_EAST, BlockDir.UP_WEST, BlockDir.DOWN_EAST, BlockDir.DOWN_WEST};
    private static final BlockDir[] EDGES_X_POS_EAST_X_AXIS = new BlockDir[]{BlockDir.UP_SOUTH, BlockDir.UP_NORTH, BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH};
    public static final cdq SPRITE_DEFAULT = new cdq("<default>");

    public static bvp[] getConnectedTexture(amy blockAccess, awt blockState, et blockPos, bvp quad, RenderEnv renderEnv) {
        cdq spriteIn = quad.a();
        if (spriteIn == null) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        aow block = blockState.u();
        if (ConnectedTextures.skipConnectedTexture(blockAccess, blockState, blockPos, quad, renderEnv)) {
            quad = ConnectedTextures.getQuad(emptySprite, quad);
            return renderEnv.getArrayQuadsCtm(quad);
        }
        fa side = quad.e();
        bvp[] quads = ConnectedTextures.getConnectedTextureMultiPass(blockAccess, blockState, blockPos, side, quad, renderEnv);
        return quads;
    }

    private static boolean skipConnectedTexture(amy blockAccess, awt blockState, et blockPos, bvp quad, RenderEnv renderEnv) {
        aow block = blockState.u();
        if (block instanceof auo) {
            fa face = quad.e();
            if (face != fa.b && face != fa.a) {
                return false;
            }
            if (!quad.isFaceQuad()) {
                return false;
            }
            et posNeighbour = blockPos.a(quad.e());
            awt stateNeighbour = blockAccess.o(posNeighbour);
            if (stateNeighbour.u() != block) {
                return false;
            }
            if (block == aox.cH && stateNeighbour.c((axj)aub.a) != blockState.c((axj)aub.a)) {
                return false;
            }
            stateNeighbour = stateNeighbour.c(blockAccess, posNeighbour);
            double midX = quad.getMidX();
            if (midX < 0.4) {
                if (((Boolean)stateNeighbour.c((axj)auo.e)).booleanValue()) {
                    return true;
                }
            } else if (midX > 0.6) {
                if (((Boolean)stateNeighbour.c((axj)auo.c)).booleanValue()) {
                    return true;
                }
            } else {
                double midZ = quad.getMidZ();
                if (midZ < 0.4) {
                    if (((Boolean)stateNeighbour.c((axj)auo.b)).booleanValue()) {
                        return true;
                    }
                } else if (midZ > 0.6) {
                    if (((Boolean)stateNeighbour.c((axj)auo.d)).booleanValue()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    protected static bvp[] getQuads(cdq sprite, bvp quadIn, RenderEnv renderEnv) {
        if (sprite == null) {
            return null;
        }
        if (sprite == SPRITE_DEFAULT) {
            return renderEnv.getArrayQuadsCtm(quadIn);
        }
        bvp quad = ConnectedTextures.getQuad(sprite, quadIn);
        bvp[] quads = renderEnv.getArrayQuadsCtm(quad);
        return quads;
    }

    private static synchronized bvp getQuad(cdq sprite, bvp quadIn) {
        bvp quad;
        if (spriteQuadMaps == null) {
            return quadIn;
        }
        int spriteIndex = sprite.getIndexInMap();
        if (spriteIndex < 0 || spriteIndex >= spriteQuadMaps.length) {
            return quadIn;
        }
        IdentityHashMap<bvp, bvp> quadMap = spriteQuadMaps[spriteIndex];
        if (quadMap == null) {
            ConnectedTextures.spriteQuadMaps[spriteIndex] = quadMap = new IdentityHashMap<bvp, bvp>(1);
        }
        if ((quad = (bvp)quadMap.get(quadIn)) == null) {
            quad = ConnectedTextures.makeSpriteQuad(quadIn, sprite);
            quadMap.put(quadIn, quad);
        }
        return quad;
    }

    private static synchronized bvp getQuadFull(cdq sprite, bvp quadIn, int tintIndex) {
        fa face;
        bvp quad;
        if (spriteQuadFullMaps == null) {
            return null;
        }
        if (sprite == null) {
            return null;
        }
        int spriteIndex = sprite.getIndexInMap();
        if (spriteIndex < 0 || spriteIndex >= spriteQuadFullMaps.length) {
            return null;
        }
        EnumMap<fa, bvp> quadMap = spriteQuadFullMaps[spriteIndex];
        if (quadMap == null) {
            ConnectedTextures.spriteQuadFullMaps[spriteIndex] = quadMap = new EnumMap<fa, bvp>(fa.class);
        }
        if ((quad = (bvp)quadMap.get(face = quadIn.e())) == null) {
            quad = BlockModelUtils.makeBakedQuad(face, sprite, tintIndex);
            quadMap.put(face, quad);
        }
        return quad;
    }

    private static bvp makeSpriteQuad(bvp quad, cdq sprite) {
        int[] data = (int[])quad.b().clone();
        cdq spriteFrom = quad.a();
        for (int i = 0; i < 4; ++i) {
            ConnectedTextures.fixVertex(data, i, spriteFrom, sprite);
        }
        bvp bq = new bvp(data, quad.d(), quad.e(), sprite);
        return bq;
    }

    private static void fixVertex(int[] data, int vertex, cdq spriteFrom, cdq spriteTo) {
        int mul = data.length / 4;
        int pos = mul * vertex;
        float u = Float.intBitsToFloat(data[pos + 4]);
        float v = Float.intBitsToFloat(data[pos + 4 + 1]);
        double su16 = spriteFrom.getSpriteU16(u);
        double sv16 = spriteFrom.getSpriteV16(v);
        data[pos + 4] = Float.floatToRawIntBits(spriteTo.a(su16));
        data[pos + 4 + 1] = Float.floatToRawIntBits(spriteTo.b(sv16));
    }

    private static bvp[] getConnectedTextureMultiPass(amy blockAccess, awt blockState, et blockPos, fa side, bvp quad, RenderEnv renderEnv) {
        bvp[] quads = ConnectedTextures.getConnectedTextureSingle(blockAccess, blockState, blockPos, side, quad, true, 0, renderEnv);
        if (!multipass) {
            return quads;
        }
        if (quads.length == 1 && quads[0] == quad) {
            return quads;
        }
        List<bvp> listQuads = renderEnv.getListQuadsCtmMultipass(quads);
        for (int q = 0; q < listQuads.size(); ++q) {
            bvp[] newMpQuads;
            bvp newQuad;
            bvp mpQuad = newQuad = listQuads.get(q);
            for (int i = 0; i < 3 && (newMpQuads = ConnectedTextures.getConnectedTextureSingle(blockAccess, blockState, blockPos, side, mpQuad, false, i + 1, renderEnv)).length == 1 && newMpQuads[0] != mpQuad; ++i) {
                mpQuad = newMpQuads[0];
            }
            listQuads.set(q, mpQuad);
        }
        for (int i = 0; i < quads.length; ++i) {
            quads[i] = listQuads.get(i);
        }
        return quads;
    }

    public static bvp[] getConnectedTextureSingle(amy blockAccess, awt blockState, et blockPos, fa facing, bvp quad, boolean checkBlocks, int pass, RenderEnv renderEnv) {
        int blockId;
        bvp[] newQuads;
        ConnectedProperties cp;
        int i;
        int side;
        ConnectedProperties[] cps;
        int iconId;
        aow block = blockState.u();
        if (!(blockState instanceof awp)) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        awp blockStateBase = (awp)blockState;
        cdq icon = quad.a();
        if (tileProperties != null && (iconId = icon.getIndexInMap()) >= 0 && iconId < tileProperties.length && (cps = tileProperties[iconId]) != null) {
            side = ConnectedTextures.getSide(facing);
            for (i = 0; i < cps.length; ++i) {
                cp = cps[i];
                if (cp == null || !cp.matchesBlockId(blockStateBase.getBlockId()) || (newQuads = ConnectedTextures.getConnectedTexture(cp, blockAccess, blockStateBase, blockPos, side, quad, pass, renderEnv)) == null) continue;
                return newQuads;
            }
        }
        if (blockProperties != null && checkBlocks && (blockId = renderEnv.getBlockId()) >= 0 && blockId < blockProperties.length && (cps = blockProperties[blockId]) != null) {
            side = ConnectedTextures.getSide(facing);
            for (i = 0; i < cps.length; ++i) {
                cp = cps[i];
                if (cp == null || !cp.matchesIcon(icon) || (newQuads = ConnectedTextures.getConnectedTexture(cp, blockAccess, blockStateBase, blockPos, side, quad, pass, renderEnv)) == null) continue;
                return newQuads;
            }
        }
        return renderEnv.getArrayQuadsCtm(quad);
    }

    public static int getSide(fa facing) {
        if (facing == null) {
            return -1;
        }
        switch (facing) {
            case a: {
                return 0;
            }
            case b: {
                return 1;
            }
            case f: {
                return 5;
            }
            case e: {
                return 4;
            }
            case c: {
                return 2;
            }
            case d: {
                return 3;
            }
        }
        return -1;
    }

    private static fa getFacing(int side) {
        switch (side) {
            case 0: {
                return fa.a;
            }
            case 1: {
                return fa.b;
            }
            case 5: {
                return fa.f;
            }
            case 4: {
                return fa.e;
            }
            case 2: {
                return fa.c;
            }
            case 3: {
                return fa.d;
            }
        }
        return fa.b;
    }

    private static bvp[] getConnectedTexture(ConnectedProperties cp, amy blockAccess, awp blockState, et blockPos, int side, bvp quad, int pass, RenderEnv renderEnv) {
        String name;
        anh blockBiome;
        int metadata;
        int vertAxis = 0;
        int metadataCheck = metadata = blockState.getMetadata();
        aow block = blockState.u();
        if (block instanceof atl) {
            vertAxis = ConnectedTextures.getWoodAxis(side, metadata);
            if (cp.getMetadataMax() <= 3) {
                metadataCheck &= 3;
            }
        }
        if (block instanceof ata) {
            vertAxis = ConnectedTextures.getQuartzAxis(side, metadata);
            if (cp.getMetadataMax() <= 2 && metadataCheck > 2) {
                metadataCheck = 2;
            }
        }
        if (!cp.matchesBlock(blockState.getBlockId(), metadataCheck)) {
            return null;
        }
        if (side >= 0 && cp.faces != 63) {
            int sideCheck = side;
            if (vertAxis != 0) {
                sideCheck = ConnectedTextures.fixSideByAxis(side, vertAxis);
            }
            if ((1 << sideCheck & cp.faces) == 0) {
                return null;
            }
        }
        int y = blockPos.q();
        if (cp.heights != null && !cp.heights.isInRange(y)) {
            return null;
        }
        if (cp.biomes != null && !cp.matchesBiome(blockBiome = blockAccess.b(blockPos))) {
            return null;
        }
        if (cp.nbtName != null && !cp.nbtName.matchesValue(name = TileEntityUtils.getTileEntityName(blockAccess, blockPos))) {
            return null;
        }
        cdq icon = quad.a();
        switch (cp.method) {
            case 10: {
                if (pass != 0) break;
                return ConnectedTextures.getConnectedTextureCtmCompact(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, quad, metadata, renderEnv);
            }
            case 1: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureCtm(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata, renderEnv), quad, renderEnv);
            }
            case 2: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureHorizontal(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata), quad, renderEnv);
            }
            case 6: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureVertical(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata), quad, renderEnv);
            }
            case 3: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureTop(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata), quad, renderEnv);
            }
            case 4: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureRandom(cp, blockAccess, blockState, blockPos, side), quad, renderEnv);
            }
            case 5: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureRepeat(cp, blockPos, side), quad, renderEnv);
            }
            case 7: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureFixed(cp), quad, renderEnv);
            }
            case 8: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureHorizontalVertical(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata), quad, renderEnv);
            }
            case 9: {
                return ConnectedTextures.getQuads(ConnectedTextures.getConnectedTextureVerticalHorizontal(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, icon, metadata), quad, renderEnv);
            }
            case 11: {
                return ConnectedTextures.getConnectedTextureOverlay(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, quad, metadata, renderEnv);
            }
            case 12: {
                return ConnectedTextures.getConnectedTextureOverlayFixed(cp, quad, renderEnv);
            }
            case 13: {
                return ConnectedTextures.getConnectedTextureOverlayRandom(cp, blockAccess, blockState, blockPos, side, quad, renderEnv);
            }
            case 14: {
                return ConnectedTextures.getConnectedTextureOverlayRepeat(cp, blockPos, side, quad, renderEnv);
            }
            case 15: {
                return ConnectedTextures.getConnectedTextureOverlayCtm(cp, blockAccess, (awt)blockState, blockPos, vertAxis, side, quad, metadata, renderEnv);
            }
        }
        return null;
    }

    private static int fixSideByAxis(int side, int vertAxis) {
        switch (vertAxis) {
            case 0: {
                return side;
            }
            case 1: {
                switch (side) {
                    case 0: {
                        return 2;
                    }
                    case 1: {
                        return 3;
                    }
                    case 2: {
                        return 1;
                    }
                    case 3: {
                        return 0;
                    }
                }
                return side;
            }
            case 2: {
                switch (side) {
                    case 0: {
                        return 4;
                    }
                    case 1: {
                        return 5;
                    }
                    case 4: {
                        return 1;
                    }
                    case 5: {
                        return 0;
                    }
                }
                return side;
            }
        }
        return side;
    }

    private static int getWoodAxis(int side, int metadata) {
        int orient = (metadata & 0xC) >> 2;
        switch (orient) {
            case 1: {
                return 2;
            }
            case 2: {
                return 1;
            }
        }
        return 0;
    }

    private static int getQuartzAxis(int side, int metadata) {
        switch (metadata) {
            case 3: {
                return 2;
            }
            case 4: {
                return 1;
            }
        }
        return 0;
    }

    private static cdq getConnectedTextureRandom(ConnectedProperties cp, amy blockAccess, awp blockState, et blockPos, int side) {
        if (cp.tileIcons.length == 1) {
            return cp.tileIcons[0];
        }
        int face = side / cp.symmetry * cp.symmetry;
        if (cp.linked) {
            et posDown = blockPos.b();
            awt bsDown = blockAccess.o(posDown);
            while (bsDown.u() == blockState.u() && (posDown = (blockPos = posDown).b()).q() >= 0) {
                bsDown = blockAccess.o(posDown);
            }
        }
        int rand = Config.getRandom(blockPos, face) & Integer.MAX_VALUE;
        for (int i = 0; i < cp.randomLoops; ++i) {
            rand = Config.intHash(rand);
        }
        int index = 0;
        if (cp.weights == null) {
            index = rand % cp.tileIcons.length;
        } else {
            int randWeight = rand % cp.sumAllWeights;
            int[] sumWeights = cp.sumWeights;
            for (int i = 0; i < sumWeights.length; ++i) {
                if (randWeight >= sumWeights[i]) continue;
                index = i;
                break;
            }
        }
        return cp.tileIcons[index];
    }

    private static cdq getConnectedTextureFixed(ConnectedProperties cp) {
        return cp.tileIcons[0];
    }

    private static cdq getConnectedTextureRepeat(ConnectedProperties cp, et blockPos, int side) {
        if (cp.tileIcons.length == 1) {
            return cp.tileIcons[0];
        }
        int x = blockPos.p();
        int y = blockPos.q();
        int z = blockPos.r();
        int nx = 0;
        int ny = 0;
        switch (side) {
            case 0: {
                nx = x;
                ny = -z - 1;
                break;
            }
            case 1: {
                nx = x;
                ny = z;
                break;
            }
            case 2: {
                nx = -x - 1;
                ny = -y;
                break;
            }
            case 3: {
                nx = x;
                ny = -y;
                break;
            }
            case 4: {
                nx = z;
                ny = -y;
                break;
            }
            case 5: {
                nx = -z - 1;
                ny = -y;
            }
        }
        ny %= cp.height;
        if ((nx %= cp.width) < 0) {
            nx += cp.width;
        }
        if (ny < 0) {
            ny += cp.height;
        }
        int index = ny * cp.width + nx;
        return cp.tileIcons[index];
    }

    private static cdq getConnectedTextureCtm(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata, RenderEnv renderEnv) {
        int index = ConnectedTextures.getConnectedTextureCtmIndex(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata, renderEnv);
        return cp.tileIcons[index];
    }

    private static synchronized bvp[] getConnectedTextureCtmCompact(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, bvp quad, int metadata, RenderEnv renderEnv) {
        cdq icon = quad.a();
        int index = ConnectedTextures.getConnectedTextureCtmIndex(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata, renderEnv);
        return ConnectedTexturesCompact.getConnectedTextureCtmCompact(index, cp, side, quad, renderEnv);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static bvp[] getConnectedTextureOverlay(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, bvp quad, int metadata, RenderEnv renderEnv) {
        if (!quad.isFullQuad()) {
            return null;
        }
        cdq icon = quad.a();
        BlockDir[] dirSides = ConnectedTextures.getSideDirections(side, vertAxis);
        boolean[] sides = renderEnv.getBorderFlags();
        for (int i = 0; i < 4; ++i) {
            sides[i] = ConnectedTextures.isNeighbourOverlay(cp, blockAccess, blockState, dirSides[i].offset(blockPos), side, icon, metadata);
        }
        ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(cp.layer);
        try {
            if (sides[0] && sides[1] && sides[2] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[8], quad, cp.tintIndex), cp.tintBlockState);
                bvp[] bvpArray = null;
                return bvpArray;
            }
            if (sides[0] && sides[1] && sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[5], quad, cp.tintIndex), cp.tintBlockState);
                bvp[] bvpArray = null;
                return bvpArray;
            }
            if (sides[0] && sides[2] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[6], quad, cp.tintIndex), cp.tintBlockState);
                bvp[] bvpArray = null;
                return bvpArray;
            }
            if (sides[1] && sides[2] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[12], quad, cp.tintIndex), cp.tintBlockState);
                bvp[] bvpArray = null;
                return bvpArray;
            }
            if (sides[0] && sides[1] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[13], quad, cp.tintIndex), cp.tintBlockState);
                bvp[] bvpArray = null;
                return bvpArray;
            }
            BlockDir[] dirEdges = ConnectedTextures.getEdgeDirections(side, vertAxis);
            boolean[] edges = renderEnv.getBorderFlags2();
            for (int i = 0; i < 4; ++i) {
                edges[i] = ConnectedTextures.isNeighbourOverlay(cp, blockAccess, blockState, dirEdges[i].offset(blockPos), side, icon, metadata);
            }
            if (sides[1] && sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[3], quad, cp.tintIndex), cp.tintBlockState);
                if (edges[3]) {
                    listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[16], quad, cp.tintIndex), cp.tintBlockState);
                }
                bvp[] i = null;
                return i;
            }
            if (sides[0] && sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[4], quad, cp.tintIndex), cp.tintBlockState);
                if (edges[2]) {
                    listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[14], quad, cp.tintIndex), cp.tintBlockState);
                }
                bvp[] i = null;
                return i;
            }
            if (sides[1] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[10], quad, cp.tintIndex), cp.tintBlockState);
                if (edges[1]) {
                    listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[2], quad, cp.tintIndex), cp.tintBlockState);
                }
                bvp[] i = null;
                return i;
            }
            if (sides[0] && sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[11], quad, cp.tintIndex), cp.tintBlockState);
                if (edges[0]) {
                    listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[0], quad, cp.tintIndex), cp.tintBlockState);
                }
                bvp[] i = null;
                return i;
            }
            boolean[] sidesMatch = renderEnv.getBorderFlags3();
            for (int i = 0; i < 4; ++i) {
                sidesMatch[i] = ConnectedTextures.isNeighbourMatching(cp, blockAccess, blockState, dirSides[i].offset(blockPos), side, icon, metadata);
            }
            if (sides[0]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[9], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (sides[1]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[7], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[1], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[15], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (edges[0] && (sidesMatch[1] || sidesMatch[2]) && !sides[1] && !sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[0], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (edges[1] && (sidesMatch[0] || sidesMatch[2]) && !sides[0] && !sides[2]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[2], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (edges[2] && (sidesMatch[1] || sidesMatch[3]) && !sides[1] && !sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[14], quad, cp.tintIndex), cp.tintBlockState);
            }
            if (edges[3] && (sidesMatch[0] || sidesMatch[3]) && !sides[0] && !sides[3]) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(cp.tileIcons[16], quad, cp.tintIndex), cp.tintBlockState);
            }
            bvp[] bvpArray = null;
            return bvpArray;
        }
        finally {
            if (listQuadsOverlay.size() > 0) {
                renderEnv.setOverlaysRendered(true);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static bvp[] getConnectedTextureOverlayFixed(ConnectedProperties cp, bvp quad, RenderEnv renderEnv) {
        if (!quad.isFullQuad()) {
            return null;
        }
        ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(cp.layer);
        try {
            cdq sprite = ConnectedTextures.getConnectedTextureFixed(cp);
            if (sprite != null) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(sprite, quad, cp.tintIndex), cp.tintBlockState);
            }
            bvp[] bvpArray = null;
            return bvpArray;
        }
        finally {
            if (listQuadsOverlay.size() > 0) {
                renderEnv.setOverlaysRendered(true);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static bvp[] getConnectedTextureOverlayRandom(ConnectedProperties cp, amy blockAccess, awp blockState, et blockPos, int side, bvp quad, RenderEnv renderEnv) {
        if (!quad.isFullQuad()) {
            return null;
        }
        ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(cp.layer);
        try {
            cdq sprite = ConnectedTextures.getConnectedTextureRandom(cp, blockAccess, blockState, blockPos, side);
            if (sprite != null) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(sprite, quad, cp.tintIndex), cp.tintBlockState);
            }
            bvp[] bvpArray = null;
            return bvpArray;
        }
        finally {
            if (listQuadsOverlay.size() > 0) {
                renderEnv.setOverlaysRendered(true);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static bvp[] getConnectedTextureOverlayRepeat(ConnectedProperties cp, et blockPos, int side, bvp quad, RenderEnv renderEnv) {
        if (!quad.isFullQuad()) {
            return null;
        }
        ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(cp.layer);
        try {
            cdq sprite = ConnectedTextures.getConnectedTextureRepeat(cp, blockPos, side);
            if (sprite != null) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(sprite, quad, cp.tintIndex), cp.tintBlockState);
            }
            bvp[] bvpArray = null;
            return bvpArray;
        }
        finally {
            if (listQuadsOverlay.size() > 0) {
                renderEnv.setOverlaysRendered(true);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static bvp[] getConnectedTextureOverlayCtm(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, bvp quad, int metadata, RenderEnv renderEnv) {
        if (!quad.isFullQuad()) {
            return null;
        }
        ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(cp.layer);
        try {
            cdq sprite = ConnectedTextures.getConnectedTextureCtm(cp, blockAccess, blockState, blockPos, vertAxis, side, quad.a(), metadata, renderEnv);
            if (sprite != null) {
                listQuadsOverlay.addQuad(ConnectedTextures.getQuadFull(sprite, quad, cp.tintIndex), cp.tintBlockState);
            }
            bvp[] bvpArray = null;
            return bvpArray;
        }
        finally {
            if (listQuadsOverlay.size() > 0) {
                renderEnv.setOverlaysRendered(true);
            }
        }
    }

    private static BlockDir[] getSideDirections(int side, int vertAxis) {
        switch (side) {
            case 0: {
                return SIDES_Y_NEG_DOWN;
            }
            case 1: {
                return SIDES_Y_POS_UP;
            }
            case 2: {
                if (vertAxis == 1) {
                    return SIDES_Z_NEG_NORTH_Z_AXIS;
                }
                return SIDES_Z_NEG_NORTH;
            }
            case 3: {
                return SIDES_Z_POS_SOUTH;
            }
            case 4: {
                return SIDES_X_NEG_WEST;
            }
            case 5: {
                if (vertAxis == 2) {
                    return SIDES_X_POS_EAST_X_AXIS;
                }
                return SIDES_X_POS_EAST;
            }
        }
        throw new IllegalArgumentException("Unknown side: " + side);
    }

    private static BlockDir[] getEdgeDirections(int side, int vertAxis) {
        switch (side) {
            case 0: {
                return EDGES_Y_NEG_DOWN;
            }
            case 1: {
                return EDGES_Y_POS_UP;
            }
            case 2: {
                if (vertAxis == 1) {
                    return EDGES_Z_NEG_NORTH_Z_AXIS;
                }
                return EDGES_Z_NEG_NORTH;
            }
            case 3: {
                return EDGES_Z_POS_SOUTH;
            }
            case 4: {
                return EDGES_X_NEG_WEST;
            }
            case 5: {
                if (vertAxis == 2) {
                    return EDGES_X_POS_EAST_X_AXIS;
                }
                return EDGES_X_POS_EAST;
            }
        }
        throw new IllegalArgumentException("Unknown side: " + side);
    }

    protected static Map[][] getSpriteQuadCompactMaps() {
        return spriteQuadCompactMaps;
    }

    private static int getConnectedTextureCtmIndex(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata, RenderEnv renderEnv) {
        boolean[] borders = renderEnv.getBorderFlags();
        switch (side) {
            case 0: {
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.b();
                borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e(), side, icon, metadata);
                borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f(), side, icon, metadata);
                borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.c(), side, icon, metadata);
                borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.d(), side, icon, metadata);
                break;
            }
            case 1: {
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.a();
                borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e(), side, icon, metadata);
                borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f(), side, icon, metadata);
                borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.d(), side, icon, metadata);
                borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.c(), side, icon, metadata);
                break;
            }
            case 2: {
                et posFront;
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                if (cp.innerSeams) {
                    posFront = blockPos.c();
                    borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f(), side, icon, metadata);
                    borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e(), side, icon, metadata);
                    borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b(), side, icon, metadata);
                    boolean bl = borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a(), side, icon, metadata);
                }
                if (vertAxis != 1) break;
                ConnectedTextures.switchValues(0, 1, borders);
                ConnectedTextures.switchValues(2, 3, borders);
                break;
            }
            case 3: {
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.d();
                borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e(), side, icon, metadata);
                borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f(), side, icon, metadata);
                borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b(), side, icon, metadata);
                borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a(), side, icon, metadata);
                break;
            }
            case 4: {
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.e();
                borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.c(), side, icon, metadata);
                borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.d(), side, icon, metadata);
                borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b(), side, icon, metadata);
                borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a(), side, icon, metadata);
                break;
            }
            case 5: {
                et posFront;
                borders[0] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                borders[1] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                borders[2] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                borders[3] = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                if (cp.innerSeams) {
                    posFront = blockPos.f();
                    borders[0] = borders[0] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.d(), side, icon, metadata);
                    borders[1] = borders[1] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.c(), side, icon, metadata);
                    borders[2] = borders[2] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b(), side, icon, metadata);
                    boolean bl = borders[3] = borders[3] && !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a(), side, icon, metadata);
                }
                if (vertAxis != 2) break;
                ConnectedTextures.switchValues(0, 1, borders);
                ConnectedTextures.switchValues(2, 3, borders);
            }
        }
        int index = 0;
        if (borders[0] & !borders[1] & !borders[2] & !borders[3]) {
            index = 3;
        } else if (!borders[0] & borders[1] & !borders[2] & !borders[3]) {
            index = 1;
        } else if (!borders[0] & !borders[1] & borders[2] & !borders[3]) {
            index = 12;
        } else if (!borders[0] & !borders[1] & !borders[2] & borders[3]) {
            index = 36;
        } else if (borders[0] & borders[1] & !borders[2] & !borders[3]) {
            index = 2;
        } else if (!borders[0] & !borders[1] & borders[2] & borders[3]) {
            index = 24;
        } else if (borders[0] & !borders[1] & borders[2] & !borders[3]) {
            index = 15;
        } else if (borders[0] & !borders[1] & !borders[2] & borders[3]) {
            index = 39;
        } else if (!borders[0] & borders[1] & borders[2] & !borders[3]) {
            index = 13;
        } else if (!borders[0] & borders[1] & !borders[2] & borders[3]) {
            index = 37;
        } else if (!borders[0] & borders[1] & borders[2] & borders[3]) {
            index = 25;
        } else if (borders[0] & !borders[1] & borders[2] & borders[3]) {
            index = 27;
        } else if (borders[0] & borders[1] & !borders[2] & borders[3]) {
            index = 38;
        } else if (borders[0] & borders[1] & borders[2] & !borders[3]) {
            index = 14;
        } else if (borders[0] & borders[1] & borders[2] & borders[3]) {
            index = 26;
        }
        if (index == 0) {
            return index;
        }
        if (!Config.isConnectedTexturesFancy()) {
            return index;
        }
        boolean[] edges = borders;
        switch (side) {
            case 0: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().c(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().c(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().d(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().d(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.b();
                edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().c(), side, icon, metadata);
                edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().c(), side, icon, metadata);
                edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().d(), side, icon, metadata);
                edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().d(), side, icon, metadata);
                break;
            }
            case 1: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().d(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().d(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().c(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().c(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.a();
                edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().d(), side, icon, metadata);
                edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().d(), side, icon, metadata);
                edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().c(), side, icon, metadata);
                edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().c(), side, icon, metadata);
                break;
            }
            case 2: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().b(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().b(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().a(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().a(), side, icon, metadata);
                if (cp.innerSeams) {
                    et posFront = blockPos.c();
                    edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().b(), side, icon, metadata);
                    edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().b(), side, icon, metadata);
                    edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().a(), side, icon, metadata);
                    boolean bl2 = edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().a(), side, icon, metadata);
                }
                if (vertAxis != 1) break;
                ConnectedTextures.switchValues(0, 3, borders);
                ConnectedTextures.switchValues(1, 2, borders);
                break;
            }
            case 3: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().b(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().b(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f().a(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e().a(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.d();
                edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().b(), side, icon, metadata);
                edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().b(), side, icon, metadata);
                edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.f().a(), side, icon, metadata);
                edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.e().a(), side, icon, metadata);
                break;
            }
            case 4: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b().d(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b().c(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a().d(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a().c(), side, icon, metadata);
                if (!cp.innerSeams) break;
                et posFront = blockPos.e();
                edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b().d(), side, icon, metadata);
                edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b().c(), side, icon, metadata);
                edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a().d(), side, icon, metadata);
                edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a().c(), side, icon, metadata);
                break;
            }
            case 5: {
                edges[0] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b().c(), side, icon, metadata);
                edges[1] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b().d(), side, icon, metadata);
                edges[2] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a().c(), side, icon, metadata);
                boolean bl = edges[3] = !ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a().d(), side, icon, metadata);
                if (cp.innerSeams) {
                    et posFront = blockPos.f();
                    edges[0] = edges[0] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b().c(), side, icon, metadata);
                    edges[1] = edges[1] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.b().d(), side, icon, metadata);
                    edges[2] = edges[2] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a().c(), side, icon, metadata);
                    boolean bl3 = edges[3] = edges[3] || ConnectedTextures.isNeighbour(cp, blockAccess, blockState, posFront.a().d(), side, icon, metadata);
                }
                if (vertAxis != 2) break;
                ConnectedTextures.switchValues(0, 3, borders);
                ConnectedTextures.switchValues(1, 2, borders);
            }
        }
        if (index == 13 && edges[0]) {
            index = 4;
        } else if (index == 15 && edges[1]) {
            index = 5;
        } else if (index == 37 && edges[2]) {
            index = 16;
        } else if (index == 39 && edges[3]) {
            index = 17;
        } else if (index == 14 && edges[0] && edges[1]) {
            index = 7;
        } else if (index == 25 && edges[0] && edges[2]) {
            index = 6;
        } else if (index == 27 && edges[3] && edges[1]) {
            index = 19;
        } else if (index == 38 && edges[3] && edges[2]) {
            index = 18;
        } else if (index == 14 && !edges[0] && edges[1]) {
            index = 31;
        } else if (index == 25 && edges[0] && !edges[2]) {
            index = 30;
        } else if (index == 27 && !edges[3] && edges[1]) {
            index = 41;
        } else if (index == 38 && edges[3] && !edges[2]) {
            index = 40;
        } else if (index == 14 && edges[0] && !edges[1]) {
            index = 29;
        } else if (index == 25 && !edges[0] && edges[2]) {
            index = 28;
        } else if (index == 27 && edges[3] && !edges[1]) {
            index = 43;
        } else if (index == 38 && !edges[3] && edges[2]) {
            index = 42;
        } else if (index == 26 && edges[0] && edges[1] && edges[2] && edges[3]) {
            index = 46;
        } else if (index == 26 && !edges[0] && edges[1] && edges[2] && edges[3]) {
            index = 9;
        } else if (index == 26 && edges[0] && !edges[1] && edges[2] && edges[3]) {
            index = 21;
        } else if (index == 26 && edges[0] && edges[1] && !edges[2] && edges[3]) {
            index = 8;
        } else if (index == 26 && edges[0] && edges[1] && edges[2] && !edges[3]) {
            index = 20;
        } else if (index == 26 && edges[0] && edges[1] && !edges[2] && !edges[3]) {
            index = 11;
        } else if (index == 26 && !edges[0] && !edges[1] && edges[2] && edges[3]) {
            index = 22;
        } else if (index == 26 && !edges[0] && edges[1] && !edges[2] && edges[3]) {
            index = 23;
        } else if (index == 26 && edges[0] && !edges[1] && edges[2] && !edges[3]) {
            index = 10;
        } else if (index == 26 && edges[0] && !edges[1] && !edges[2] && edges[3]) {
            index = 34;
        } else if (index == 26 && !edges[0] && edges[1] && edges[2] && !edges[3]) {
            index = 35;
        } else if (index == 26 && edges[0] && !edges[1] && !edges[2] && !edges[3]) {
            index = 32;
        } else if (index == 26 && !edges[0] && edges[1] && !edges[2] && !edges[3]) {
            index = 33;
        } else if (index == 26 && !edges[0] && !edges[1] && edges[2] && !edges[3]) {
            index = 44;
        } else if (index == 26 && !edges[0] && !edges[1] && !edges[2] && edges[3]) {
            index = 45;
        }
        return index;
    }

    private static void switchValues(int ix1, int ix2, boolean[] arr) {
        boolean prev1 = arr[ix1];
        arr[ix1] = arr[ix2];
        arr[ix2] = prev1;
    }

    private static boolean isNeighbourOverlay(ConnectedProperties cp, amy iblockaccess, awt blockState, et blockPos, int side, cdq icon, int metadata) {
        cdq neighbourIcon;
        awp neighbourStateBase;
        awt neighbourState = iblockaccess.o(blockPos);
        if (!ConnectedTextures.isFullCubeModel(neighbourState)) {
            return false;
        }
        if (cp.connectBlocks != null && !Matches.block((neighbourStateBase = (awp)neighbourState).getBlockId(), neighbourStateBase.getMetadata(), cp.connectBlocks)) {
            return false;
        }
        if (cp.connectTileIcons != null && !Config.isSameOne(neighbourIcon = ConnectedTextures.getNeighbourIcon(iblockaccess, blockState, blockPos, neighbourState, side), cp.connectTileIcons)) {
            return false;
        }
        awt neighbourStateAbove = iblockaccess.o(blockPos.a(ConnectedTextures.getFacing(side)));
        if (neighbourStateAbove.p()) {
            return false;
        }
        if (side == 1 && neighbourStateAbove.u() == aox.aH) {
            return false;
        }
        return !ConnectedTextures.isNeighbour(cp, iblockaccess, blockState, blockPos, neighbourState, side, icon, metadata);
    }

    private static boolean isFullCubeModel(awt state) {
        if (state.g()) {
            return true;
        }
        aow block = state.u();
        if (block instanceof aqy) {
            return true;
        }
        return block instanceof aua;
    }

    private static boolean isNeighbourMatching(ConnectedProperties cp, amy iblockaccess, awt blockState, et blockPos, int side, cdq icon, int metadata) {
        cdq neighbourIcon;
        awp neighbourStateBase;
        awt neighbourState = iblockaccess.o(blockPos);
        if (neighbourState == AIR_DEFAULT_STATE) {
            return false;
        }
        if (cp.matchBlocks != null && neighbourState instanceof awp && !cp.matchesBlock((neighbourStateBase = (awp)neighbourState).getBlockId(), neighbourStateBase.getMetadata())) {
            return false;
        }
        if (cp.matchTileIcons != null && (neighbourIcon = ConnectedTextures.getNeighbourIcon(iblockaccess, blockState, blockPos, neighbourState, side)) != icon) {
            return false;
        }
        awt neighbourStateAbove = iblockaccess.o(blockPos.a(ConnectedTextures.getFacing(side)));
        if (neighbourStateAbove.p()) {
            return false;
        }
        return side != 1 || neighbourStateAbove.u() != aox.aH;
    }

    private static boolean isNeighbour(ConnectedProperties cp, amy iblockaccess, awt blockState, et blockPos, int side, cdq icon, int metadata) {
        awt neighbourState = iblockaccess.o(blockPos);
        return ConnectedTextures.isNeighbour(cp, iblockaccess, blockState, blockPos, neighbourState, side, icon, metadata);
    }

    private static boolean isNeighbour(ConnectedProperties cp, amy iblockaccess, awt blockState, et blockPos, awt neighbourState, int side, cdq icon, int metadata) {
        if (blockState == neighbourState) {
            return true;
        }
        if (cp.connect == 2) {
            if (neighbourState == null) {
                return false;
            }
            if (neighbourState == AIR_DEFAULT_STATE) {
                return false;
            }
            cdq neighbourIcon = ConnectedTextures.getNeighbourIcon(iblockaccess, blockState, blockPos, neighbourState, side);
            return neighbourIcon == icon;
        }
        if (cp.connect == 3) {
            if (neighbourState == null) {
                return false;
            }
            if (neighbourState == AIR_DEFAULT_STATE) {
                return false;
            }
            return neighbourState.a() == blockState.a();
        }
        if (neighbourState instanceof awp) {
            awp neighbourStateBase = (awp)neighbourState;
            aow neighbourBlock = neighbourStateBase.u();
            int neighbourMetadata = neighbourStateBase.getMetadata();
            return neighbourBlock == blockState.u() && neighbourMetadata == metadata;
        }
        return false;
    }

    private static cdq getNeighbourIcon(amy iblockaccess, awt blockState, et blockPos, awt neighbourState, int side) {
        fa facing;
        List quads;
        neighbourState = neighbourState.u().d(neighbourState, iblockaccess, blockPos);
        cfy model = bib.z().ab().a().b(neighbourState);
        if (model == null) {
            return null;
        }
        if (Reflector.ForgeBlock_getExtendedState.exists()) {
            neighbourState = (awt)Reflector.call(neighbourState.u(), Reflector.ForgeBlock_getExtendedState, neighbourState, iblockaccess, blockPos);
        }
        if ((quads = model.a(neighbourState, facing = ConnectedTextures.getFacing(side), 0L)) == null) {
            return null;
        }
        if (Config.isBetterGrass()) {
            quads = BetterGrass.getFaceQuads(iblockaccess, neighbourState, blockPos, facing, quads);
        }
        if (quads.size() > 0) {
            bvp quad = (bvp)quads.get(0);
            return quad.a();
        }
        List quadsGeneral = model.a(neighbourState, null, 0L);
        if (quadsGeneral == null) {
            return null;
        }
        for (int i = 0; i < quadsGeneral.size(); ++i) {
            bvp quad = (bvp)quadsGeneral.get(i);
            if (quad.e() != facing) continue;
            return quad.a();
        }
        return null;
    }

    private static cdq getConnectedTextureHorizontal(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata) {
        boolean left = false;
        boolean right = false;
        block0 : switch (vertAxis) {
            case 0: {
                switch (side) {
                    case 0: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 1: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 2: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        break;
                    }
                    case 3: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 4: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                        break;
                    }
                    case 5: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                    }
                }
                break;
            }
            case 1: {
                switch (side) {
                    case 2: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 3: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 0: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        break;
                    }
                    case 1: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
                        break;
                    }
                    case 4: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                        break;
                    }
                    case 5: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                    }
                }
                break;
            }
            case 2: {
                switch (side) {
                    case 4: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                        break block0;
                    }
                    case 5: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                        break block0;
                    }
                    case 2: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                        break block0;
                    }
                    case 3: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                        break block0;
                    }
                    case 0: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                        break block0;
                    }
                    case 1: {
                        left = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                        right = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                    }
                }
            }
        }
        int index = 3;
        index = left ? (right ? 1 : 2) : (right ? 0 : 3);
        return cp.tileIcons[index];
    }

    private static cdq getConnectedTextureVertical(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata) {
        boolean bottom = false;
        boolean top = false;
        switch (vertAxis) {
            case 0: {
                if (side == 1) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                    break;
                }
                if (side == 0) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                    break;
                }
                bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                break;
            }
            case 1: {
                if (side == 3) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                    break;
                }
                if (side == 2) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                    break;
                }
                bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
                break;
            }
            case 2: {
                if (side == 5) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                    break;
                }
                if (side == 4) {
                    bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
                    top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                    break;
                }
                bottom = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
            }
        }
        int index = 3;
        index = bottom ? (top ? 1 : 2) : (top ? 0 : 3);
        return cp.tileIcons[index];
    }

    private static cdq getConnectedTextureHorizontalVertical(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata) {
        cdq[] tileIcons = cp.tileIcons;
        cdq iconH = ConnectedTextures.getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
        if (iconH != null && iconH != icon && iconH != tileIcons[3]) {
            return iconH;
        }
        cdq iconV = ConnectedTextures.getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
        if (iconV == tileIcons[0]) {
            return tileIcons[4];
        }
        if (iconV == tileIcons[1]) {
            return tileIcons[5];
        }
        if (iconV == tileIcons[2]) {
            return tileIcons[6];
        }
        return iconV;
    }

    private static cdq getConnectedTextureVerticalHorizontal(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata) {
        cdq[] tileIcons = cp.tileIcons;
        cdq iconV = ConnectedTextures.getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
        if (iconV != null && iconV != icon && iconV != tileIcons[3]) {
            return iconV;
        }
        cdq iconH = ConnectedTextures.getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
        if (iconH == tileIcons[0]) {
            return tileIcons[4];
        }
        if (iconH == tileIcons[1]) {
            return tileIcons[5];
        }
        if (iconH == tileIcons[2]) {
            return tileIcons[6];
        }
        return iconH;
    }

    private static cdq getConnectedTextureTop(ConnectedProperties cp, amy blockAccess, awt blockState, et blockPos, int vertAxis, int side, cdq icon, int metadata) {
        boolean top = false;
        switch (vertAxis) {
            case 0: {
                if (side == 1 || side == 0) {
                    return null;
                }
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
                break;
            }
            case 1: {
                if (side == 3 || side == 2) {
                    return null;
                }
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
                break;
            }
            case 2: {
                if (side == 5 || side == 4) {
                    return null;
                }
                top = ConnectedTextures.isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
            }
        }
        if (top) {
            return cp.tileIcons[0];
        }
        return null;
    }

    public static void updateIcons(cdp textureMap) {
        blockProperties = null;
        tileProperties = null;
        spriteQuadMaps = null;
        spriteQuadCompactMaps = null;
        if (!Config.isConnectedTextures()) {
            return;
        }
        cer[] rps = Config.getResourcePacks();
        for (int i = rps.length - 1; i >= 0; --i) {
            cer rp = rps[i];
            ConnectedTextures.updateIcons(textureMap, rp);
        }
        ConnectedTextures.updateIcons(textureMap, (cer)Config.getDefaultResourcePack());
        nf locEmpty = new nf("mcpatcher/ctm/default/empty");
        emptySprite = textureMap.a(locEmpty);
        spriteQuadMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
        spriteQuadFullMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
        spriteQuadCompactMaps = new Map[textureMap.getCountRegisteredSprites() + 1][];
        if (blockProperties.length <= 0) {
            blockProperties = null;
        }
        if (tileProperties.length <= 0) {
            tileProperties = null;
        }
    }

    private static void updateIconEmpty(cdp textureMap) {
    }

    public static void updateIcons(cdp textureMap, cer rp) {
        Object[] names = ResUtils.collectFiles(rp, "mcpatcher/ctm/", ".properties", ConnectedTextures.getDefaultCtmPaths());
        Arrays.sort(names);
        List tileList = ConnectedTextures.makePropertyList(tileProperties);
        List blockList = ConnectedTextures.makePropertyList(blockProperties);
        for (int i = 0; i < names.length; ++i) {
            Object name = names[i];
            Config.dbg("ConnectedTextures: " + (String)name);
            try {
                nf locFile = new nf((String)name);
                InputStream in = rp.a(locFile);
                if (in == null) {
                    Config.warn("ConnectedTextures file not found: " + (String)name);
                    continue;
                }
                PropertiesOrdered props = new PropertiesOrdered();
                props.load(in);
                ConnectedProperties cp = new ConnectedProperties(props, (String)name);
                if (!cp.isValid((String)name)) continue;
                cp.updateIcons(textureMap);
                ConnectedTextures.addToTileList(cp, tileList);
                ConnectedTextures.addToBlockList(cp, blockList);
                continue;
            }
            catch (FileNotFoundException e) {
                Config.warn("ConnectedTextures file not found: " + (String)name);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        blockProperties = ConnectedTextures.propertyListToArray(blockList);
        tileProperties = ConnectedTextures.propertyListToArray(tileList);
        multipass = ConnectedTextures.detectMultipass();
        Config.dbg("Multipass connected textures: " + multipass);
    }

    private static List makePropertyList(ConnectedProperties[][] propsArr) {
        ArrayList<ArrayList<ConnectedProperties>> list = new ArrayList<ArrayList<ConnectedProperties>>();
        if (propsArr != null) {
            for (int i = 0; i < propsArr.length; ++i) {
                ConnectedProperties[] props = propsArr[i];
                ArrayList<ConnectedProperties> propList = null;
                if (props != null) {
                    propList = new ArrayList<ConnectedProperties>(Arrays.asList(props));
                }
                list.add(propList);
            }
        }
        return list;
    }

    private static boolean detectMultipass() {
        ConnectedProperties[] cps;
        int i;
        ArrayList<ConnectedProperties> propList = new ArrayList<ConnectedProperties>();
        for (i = 0; i < tileProperties.length; ++i) {
            cps = tileProperties[i];
            if (cps == null) continue;
            propList.addAll(Arrays.asList(cps));
        }
        for (i = 0; i < blockProperties.length; ++i) {
            cps = blockProperties[i];
            if (cps == null) continue;
            propList.addAll(Arrays.asList(cps));
        }
        ConnectedProperties[] props = propList.toArray(new ConnectedProperties[propList.size()]);
        HashSet<cdq> matchIconSet = new HashSet<cdq>();
        HashSet<cdq> tileIconSet = new HashSet<cdq>();
        for (int i2 = 0; i2 < props.length; ++i2) {
            ConnectedProperties cp = props[i2];
            if (cp.matchTileIcons != null) {
                matchIconSet.addAll(Arrays.asList(cp.matchTileIcons));
            }
            if (cp.tileIcons == null) continue;
            tileIconSet.addAll(Arrays.asList(cp.tileIcons));
        }
        matchIconSet.retainAll(tileIconSet);
        return !matchIconSet.isEmpty();
    }

    private static ConnectedProperties[][] propertyListToArray(List list) {
        ConnectedProperties[][] propArr = new ConnectedProperties[list.size()][];
        for (int i = 0; i < list.size(); ++i) {
            List subList = (List)list.get(i);
            if (subList == null) continue;
            ConnectedProperties[] subArr = subList.toArray(new ConnectedProperties[subList.size()]);
            propArr[i] = subArr;
        }
        return propArr;
    }

    private static void addToTileList(ConnectedProperties cp, List tileList) {
        if (cp.matchTileIcons == null) {
            return;
        }
        for (int i = 0; i < cp.matchTileIcons.length; ++i) {
            cdq icon = cp.matchTileIcons[i];
            if (!(icon instanceof cdq)) {
                Config.warn("TextureAtlasSprite is not TextureAtlasSprite: " + icon + ", name: " + icon.i());
                continue;
            }
            cdq ts = icon;
            int tileId = ts.getIndexInMap();
            if (tileId < 0) {
                Config.warn("Invalid tile ID: " + tileId + ", icon: " + ts.i());
                continue;
            }
            ConnectedTextures.addToList(cp, tileList, tileId);
        }
    }

    private static void addToBlockList(ConnectedProperties cp, List blockList) {
        if (cp.matchBlocks == null) {
            return;
        }
        for (int i = 0; i < cp.matchBlocks.length; ++i) {
            int blockId = cp.matchBlocks[i].getBlockId();
            if (blockId < 0) {
                Config.warn("Invalid block ID: " + blockId);
                continue;
            }
            ConnectedTextures.addToList(cp, blockList, blockId);
        }
    }

    private static void addToList(ConnectedProperties cp, List list, int id) {
        while (id >= list.size()) {
            list.add(null);
        }
        ArrayList<ConnectedProperties> subList = (ArrayList<ConnectedProperties>)list.get(id);
        if (subList == null) {
            subList = new ArrayList<ConnectedProperties>();
            list.set(id, subList);
        }
        subList.add(cp);
    }

    private static String[] getDefaultCtmPaths() {
        ArrayList<String> list = new ArrayList<String>();
        String defPath = "mcpatcher/ctm/default/";
        if (Config.isFromDefaultResourcePack(new nf("textures/blocks/glass.png"))) {
            list.add(defPath + "glass.properties");
            list.add(defPath + "glasspane.properties");
        }
        if (Config.isFromDefaultResourcePack(new nf("textures/blocks/bookshelf.png"))) {
            list.add(defPath + "bookshelf.properties");
        }
        if (Config.isFromDefaultResourcePack(new nf("textures/blocks/sandstone_normal.png"))) {
            list.add(defPath + "sandstone.properties");
        }
        String[] colors = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (int i = 0; i < colors.length; ++i) {
            String color = colors[i];
            if (!Config.isFromDefaultResourcePack(new nf("textures/blocks/glass_" + color + ".png"))) continue;
            list.add(defPath + i + "_glass_" + color + "/glass_" + color + ".properties");
            list.add(defPath + i + "_glass_" + color + "/glass_pane_" + color + ".properties");
        }
        String[] paths = list.toArray(new String[list.size()]);
        return paths;
    }
}

