/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amm
 *  amy
 *  awt
 *  bvp
 *  cfy
 *  com.google.common.collect.ImmutableList
 *  et
 *  fa
 */
package net.optifine.model;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalTextures;
import net.optifine.SmartLeaves;
import net.optifine.render.RenderEnv;

public class BlockModelCustomizer {
    private static final List<bvp> NO_QUADS = ImmutableList.of();

    public static cfy getRenderModel(cfy modelIn, awt stateIn, RenderEnv renderEnv) {
        if (renderEnv.isSmartLeaves()) {
            modelIn = SmartLeaves.getLeavesModel(modelIn, stateIn);
        }
        return modelIn;
    }

    public static List<bvp> getRenderQuads(List<bvp> quads, amy worldIn, awt stateIn, et posIn, fa enumfacing, amm layer, long rand, RenderEnv renderEnv) {
        if (enumfacing != null) {
            if (renderEnv.isSmartLeaves() && SmartLeaves.isSameLeaves(worldIn.o(posIn.a(enumfacing)), stateIn)) {
                return NO_QUADS;
            }
            if (!renderEnv.isBreakingAnimation(quads) && Config.isBetterGrass()) {
                quads = BetterGrass.getFaceQuads(worldIn, stateIn, posIn, enumfacing, quads);
            }
        }
        List<bvp> quadsNew = renderEnv.getListQuadsCustomizer();
        quadsNew.clear();
        for (int i = 0; i < quads.size(); ++i) {
            bvp quad = quads.get(i);
            bvp[] quadArr = BlockModelCustomizer.getRenderQuads(quad, worldIn, stateIn, posIn, enumfacing, rand, renderEnv);
            if (i == 0 && quads.size() == 1 && quadArr.length == 1 && quadArr[0] == quad && quad.getQuadEmissive() == null) {
                return quads;
            }
            for (int q = 0; q < quadArr.length; ++q) {
                bvp quadSingle = quadArr[q];
                quadsNew.add(quadSingle);
                if (quadSingle.getQuadEmissive() == null) continue;
                renderEnv.getListQuadsOverlay(BlockModelCustomizer.getEmissiveLayer(layer)).addQuad(quadSingle.getQuadEmissive(), stateIn);
                renderEnv.setOverlaysRendered(true);
            }
        }
        return quadsNew;
    }

    private static amm getEmissiveLayer(amm layer) {
        if (layer == null || layer == amm.a) {
            return amm.b;
        }
        return layer;
    }

    private static bvp[] getRenderQuads(bvp quad, amy worldIn, awt stateIn, et posIn, fa enumfacing, long rand, RenderEnv renderEnv) {
        bvp[] quads;
        if (renderEnv.isBreakingAnimation(quad)) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        bvp quadOriginal = quad;
        if (Config.isConnectedTextures() && ((quads = ConnectedTextures.getConnectedTexture(worldIn, stateIn, posIn, quad, renderEnv)).length != 1 || quads[0] != quad)) {
            return quads;
        }
        if (Config.isNaturalTextures() && (quad = NaturalTextures.getNaturalTexture(posIn, quad)) != quadOriginal) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        return renderEnv.getArrayQuadsCtm(quad);
    }
}

