/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aow
 *  asg
 *  asn
 *  asr$a
 *  awt
 *  axj
 *  bvp
 *  cfy
 *  cgc
 *  cgd
 *  fa
 *  nf
 */
package net.optifine;

import java.util.ArrayList;
import java.util.List;
import net.optifine.model.ModelUtils;

public class SmartLeaves {
    private static cfy modelLeavesCullAcacia = null;
    private static cfy modelLeavesCullBirch = null;
    private static cfy modelLeavesCullDarkOak = null;
    private static cfy modelLeavesCullJungle = null;
    private static cfy modelLeavesCullOak = null;
    private static cfy modelLeavesCullSpruce = null;
    private static List generalQuadsCullAcacia = null;
    private static List generalQuadsCullBirch = null;
    private static List generalQuadsCullDarkOak = null;
    private static List generalQuadsCullJungle = null;
    private static List generalQuadsCullOak = null;
    private static List generalQuadsCullSpruce = null;
    private static cfy modelLeavesDoubleAcacia = null;
    private static cfy modelLeavesDoubleBirch = null;
    private static cfy modelLeavesDoubleDarkOak = null;
    private static cfy modelLeavesDoubleJungle = null;
    private static cfy modelLeavesDoubleOak = null;
    private static cfy modelLeavesDoubleSpruce = null;

    public static cfy getLeavesModel(cfy model, awt stateIn) {
        if (!Config.isTreesSmart()) {
            return model;
        }
        List generalQuads = model.a(stateIn, null, 0L);
        if (generalQuads == generalQuadsCullAcacia) {
            return modelLeavesDoubleAcacia;
        }
        if (generalQuads == generalQuadsCullBirch) {
            return modelLeavesDoubleBirch;
        }
        if (generalQuads == generalQuadsCullDarkOak) {
            return modelLeavesDoubleDarkOak;
        }
        if (generalQuads == generalQuadsCullJungle) {
            return modelLeavesDoubleJungle;
        }
        if (generalQuads == generalQuadsCullOak) {
            return modelLeavesDoubleOak;
        }
        if (generalQuads == generalQuadsCullSpruce) {
            return modelLeavesDoubleSpruce;
        }
        return model;
    }

    public static boolean isSameLeaves(awt state1, awt state2) {
        aow block2;
        if (state1 == state2) {
            return true;
        }
        aow block1 = state1.u();
        if (block1 != (block2 = state2.u())) {
            return false;
        }
        if (block1 instanceof asn) {
            return ((asr.a)state1.c((axj)asn.e)).equals((Object)state2.c((axj)asn.e));
        }
        if (block1 instanceof asg) {
            return ((asr.a)state1.c((axj)asg.e)).equals((Object)state2.c((axj)asg.e));
        }
        return false;
    }

    public static void updateLeavesModels() {
        ArrayList updatedTypes = new ArrayList();
        modelLeavesCullAcacia = SmartLeaves.getModelCull("acacia", updatedTypes);
        modelLeavesCullBirch = SmartLeaves.getModelCull("birch", updatedTypes);
        modelLeavesCullDarkOak = SmartLeaves.getModelCull("dark_oak", updatedTypes);
        modelLeavesCullJungle = SmartLeaves.getModelCull("jungle", updatedTypes);
        modelLeavesCullOak = SmartLeaves.getModelCull("oak", updatedTypes);
        modelLeavesCullSpruce = SmartLeaves.getModelCull("spruce", updatedTypes);
        generalQuadsCullAcacia = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullAcacia);
        generalQuadsCullBirch = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullBirch);
        generalQuadsCullDarkOak = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullDarkOak);
        generalQuadsCullJungle = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullJungle);
        generalQuadsCullOak = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullOak);
        generalQuadsCullSpruce = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullSpruce);
        modelLeavesDoubleAcacia = SmartLeaves.getModelDoubleFace(modelLeavesCullAcacia);
        modelLeavesDoubleBirch = SmartLeaves.getModelDoubleFace(modelLeavesCullBirch);
        modelLeavesDoubleDarkOak = SmartLeaves.getModelDoubleFace(modelLeavesCullDarkOak);
        modelLeavesDoubleJungle = SmartLeaves.getModelDoubleFace(modelLeavesCullJungle);
        modelLeavesDoubleOak = SmartLeaves.getModelDoubleFace(modelLeavesCullOak);
        modelLeavesDoubleSpruce = SmartLeaves.getModelDoubleFace(modelLeavesCullSpruce);
        if (updatedTypes.size() > 0) {
            Config.dbg("Enable face culling: " + Config.arrayToString(updatedTypes.toArray()));
        }
    }

    private static List getGeneralQuadsSafe(cfy model) {
        if (model == null) {
            return null;
        }
        return model.a(null, null, 0L);
    }

    static cfy getModelCull(String type, List updatedTypes) {
        cgc modelManager = Config.getModelManager();
        if (modelManager == null) {
            return null;
        }
        nf locState = new nf("blockstates/" + type + "_leaves.json");
        if (Config.getDefiningResourcePack(locState) != Config.getDefaultResourcePack()) {
            return null;
        }
        nf locModel = new nf("models/block/" + type + "_leaves.json");
        if (Config.getDefiningResourcePack(locModel) != Config.getDefaultResourcePack()) {
            return null;
        }
        cgd mrl = new cgd(type + "_leaves", "normal");
        cfy model = modelManager.a(mrl);
        if (model == null || model == modelManager.a()) {
            return null;
        }
        List listGeneral = model.a(null, null, 0L);
        if (listGeneral.size() == 0) {
            return model;
        }
        if (listGeneral.size() != 6) {
            return null;
        }
        for (bvp quad : listGeneral) {
            List listFace = model.a(null, quad.e(), 0L);
            if (listFace.size() > 0) {
                return null;
            }
            listFace.add(quad);
        }
        listGeneral.clear();
        updatedTypes.add(type + "_leaves");
        return model;
    }

    private static cfy getModelDoubleFace(cfy model) {
        if (model == null) {
            return null;
        }
        if (model.a(null, null, 0L).size() > 0) {
            Config.warn("SmartLeaves: Model is not cube, general quads: " + model.a(null, null, 0L).size() + ", model: " + model);
            return model;
        }
        fa[] faces = fa.n;
        for (int i = 0; i < faces.length; ++i) {
            fa face = faces[i];
            List quads = model.a(null, face, 0L);
            if (quads.size() == 1) continue;
            Config.warn("SmartLeaves: Model is not cube, side: " + face + ", quads: " + quads.size() + ", model: " + model);
            return model;
        }
        cfy model2 = ModelUtils.duplicateModel(model);
        List[] faceQuads = new List[faces.length];
        for (int i = 0; i < faces.length; ++i) {
            fa face = faces[i];
            List quads = model2.a(null, face, 0L);
            bvp quad = (bvp)quads.get(0);
            bvp quad2 = new bvp((int[])quad.b().clone(), quad.d(), quad.e(), quad.a());
            int[] vd = quad2.b();
            int[] vd2 = (int[])vd.clone();
            int step = vd.length / 4;
            System.arraycopy(vd, 0 * step, vd2, 3 * step, step);
            System.arraycopy(vd, 1 * step, vd2, 2 * step, step);
            System.arraycopy(vd, 2 * step, vd2, 1 * step, step);
            System.arraycopy(vd, 3 * step, vd2, 0 * step, step);
            System.arraycopy(vd2, 0, vd, 0, vd2.length);
            quads.add(quad2);
        }
        return model2;
    }
}

