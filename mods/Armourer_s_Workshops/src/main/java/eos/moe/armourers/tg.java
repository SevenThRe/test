/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.PlayerModelLoader
 *  eos.moe.dragoncore.api.model.IModel
 *  eos.moe.dragoncore.api.model.IModelPiece
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.armourers;

import com.rejahtavi.rfp2.RenderPlayerDummy;
import eos.moe.armourers.ModelData;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import eos.moe.armourers.zj;
import eos.moe.dragoncore.api.PlayerModelLoader;
import eos.moe.dragoncore.api.model.IModel;
import eos.moe.dragoncore.api.model.IModelPiece;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.core.util.BenderHelper;
import goblinbob.mobends.standard.data.PlayerData;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class tg
implements LayerRenderer<AbstractClientPlayer> {
    public static float s;
    public static float m;
    private final RenderPlayer j;

    private static /* synthetic */ void y() {
        s = OpenGlHelper.lastBrightnessX;
        m = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)m);
    }

    private static /* synthetic */ void r(String a2, ModelData.Texture a3) {
        ResourceLocation resourceLocation;
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        Object object = textureManager.func_110581_b(resourceLocation = new ResourceLocation("dragonarmourers", new StringBuilder().insert(0, "models/player/").append(a2).append("/").append(a3.getName()).toString()));
        if (object == null) {
            object = new zj(a3);
            textureManager.func_110579_a(resourceLocation, object);
        }
        textureManager.func_110577_a(resourceLocation);
    }

    public static void r(RenderPlayer a2, AbstractClientPlayer a3, float a4) {
        Object object = zg.r((Entity)a3);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179108_z();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        Object object2 = object = object.iterator();
        while (object2.hasNext()) {
            ModelData modelData = (ModelData)object.next();
            object2 = object;
            tg.y(a2, modelData, a3, a4);
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179133_A();
    }

    public boolean func_177142_b() {
        return false;
    }

    private static /* synthetic */ void y(RenderPlayer a2, ModelData a3, AbstractClientPlayer a4, float a5) {
        IModel iModel = PlayerModelLoader.getModel((String)a3.getName(), (byte[])a3.getModelBytes());
        if (iModel == null) {
            return;
        }
        if (iModel.getModelPieces() == null || iModel.getModelPieces().size() == 0) {
            return;
        }
        if ((a4 = (PlayerData)BenderHelper.getData(a4, (RenderLivingBase<? extends EntityLivingBase>)a2)) == null) {
            return;
        }
        GlStateManager.func_179094_E();
        ModelData modelData = a3;
        tg.r(modelData.getName(), a3.getTexture());
        tg.r(a2, iModel, (PlayerData)a4, a5, a3);
        if (modelData.getGlowTexture() != null) {
            tg.r(a3.getName(), a3.getGlowTexture());
            tg.y();
            tg.r(a2, iModel, (PlayerData)a4, a5, a3);
            tg.r();
        }
        iModel.clearData();
        GlStateManager.func_179121_F();
    }

    private static /* synthetic */ void r() {
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)s, (float)m);
    }

    public void doRenderLayer(AbstractClientPlayer a2, float a3, float a42, float a5, float a6, float a7, float a8, float a9) {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179108_z();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        Iterator<ModelData> iterator = a3 = zg.r((Entity)a2).iterator();
        while (iterator.hasNext()) {
            tg a10;
            ModelData a42 = a3.next();
            iterator = a3;
            tg.r(a10.j, a42, a2, a9);
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179133_A();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static /* synthetic */ void r(IModel a2, PlayerData a3, float a4, ModelData a5) {
        Iterator iterator;
        float f2 = a3.smallArm ? 5.0f : 6.0f;
        Iterator iterator2 = iterator = a2.getModelPieces().iterator();
        while (true) {
            int n2;
            IModelPiece iModelPiece;
            block26: {
                if (!iterator2.hasNext()) {
                    return;
                }
                iModelPiece = (IModelPiece)iterator.next();
                String string = iModelPiece.getName().toLowerCase(Locale.ROOT);
                GlStateManager.func_179094_E();
                int n3 = -1;
                switch (string.hashCode()) {
                    case 3029410: {
                        if (!string.equals("body")) break;
                        n2 = n3 = 0;
                        break block26;
                    }
                    case 3198432: {
                        if (!string.equals("head")) break;
                        n2 = n3 = 1;
                        break block26;
                    }
                    case 55445749: {
                        if (!string.equals("leftarm")) break;
                        n2 = n3 = 2;
                        break block26;
                    }
                    case -1436077376: {
                        if (!string.equals("rightarm")) break;
                        n2 = n3 = 3;
                        break block26;
                    }
                    case 777846329: {
                        if (!string.equals("leftforearm")) break;
                        n2 = n3 = 4;
                        break block26;
                    }
                    case -303674748: {
                        if (!string.equals("rightforearm")) break;
                        n2 = n3 = 5;
                        break block26;
                    }
                    case 55455911: {
                        if (!string.equals("leftleg")) break;
                        n2 = n3 = 6;
                        break block26;
                    }
                    case -1436067214: {
                        if (!string.equals("rightleg")) break;
                        n2 = n3 = 7;
                        break block26;
                    }
                    case 777856491: {
                        if (!string.equals("leftforeleg")) break;
                        n2 = n3 = 8;
                        break block26;
                    }
                    case -303664586: {
                        if (!string.equals("rightforeleg")) break;
                        n3 = 9;
                    }
                }
                n2 = n3;
            }
            switch (n2) {
                case 0: {
                    a3.body.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)0.0f, (float)0.75f, (float)0.0f);
                    iModelPiece.render(a4, !a5.getSetting("hideChest"));
                    break;
                }
                case 1: {
                    a3.head.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)0.0f, (float)1.5f, (float)0.0f);
                    if (RenderPlayerDummy.renderFirstPerson) break;
                    iModelPiece.render(a4, !a5.getSetting("hideHead"));
                    break;
                }
                case 2: {
                    a3.leftArm.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)(-f2 * 0.0625f), (float)1.375f, (float)0.0f);
                    iModelPiece.render(a4, !a5.getSetting("hideArmLeft"));
                    break;
                }
                case 3: {
                    a3.rightArm.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)(f2 * 0.0625f), (float)1.375f, (float)0.0f);
                    iModelPiece.render(a4, !a5.getSetting("hideArmRight"));
                    break;
                }
                case 4: {
                    a3.leftForeArm.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)(-f2 * 0.0625f), (float)1.125f, (float)-0.125f);
                    iModelPiece.render(a4, !a5.getSetting("hideArmLeft"));
                    break;
                }
                case 5: {
                    a3.rightForeArm.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179109_b((float)(f2 * 0.0625f), (float)1.125f, (float)-0.125f);
                    iModelPiece.render(a4, !a5.getSetting("hideArmRight"));
                    break;
                }
                case 6: {
                    a3.leftLeg.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179137_b((double)-0.125, (double)0.75, (double)0.0);
                    iModelPiece.render(a4, !a5.getSetting("hideLegLeft"));
                    break;
                }
                case 7: {
                    a3.rightLeg.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179137_b((double)0.125, (double)0.75, (double)0.0);
                    iModelPiece.render(a4, !a5.getSetting("hideLegRight"));
                    break;
                }
                case 8: {
                    a3.leftForeLeg.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179137_b((double)-0.125, (double)0.375, (double)0.125);
                    iModelPiece.render(a4, !a5.getSetting("hideLegLeft"));
                    break;
                }
                case 9: {
                    a3.rightForeLeg.applyCharacterTransform(0.0625f);
                    GlStateManager.func_179137_b((double)0.125, (double)0.375, (double)0.125);
                    iModelPiece.render(a4, !a5.getSetting("hideLegRight"));
                    break;
                }
                default: {
                    iModelPiece.render(a4, false);
                }
            }
            GlStateManager.func_179121_F();
            iterator2 = iterator;
        }
    }

    private static /* synthetic */ void r(RenderPlayer a2, ModelData a3, AbstractClientPlayer a4, float a5) {
        if (!zh.g && a4 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        PlayerData playerData = (PlayerData)BenderHelper.getData(a4, (RenderLivingBase<? extends EntityLivingBase>)a2);
        if (playerData == null) {
            return;
        }
        IModel iModel = PlayerModelLoader.getModel((String)a3.getName(), (byte[])a3.getModelBytes());
        if (iModel == null) {
            return;
        }
        if (iModel.getModelPieces() == null) {
            return;
        }
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0 && ModelPart.cancelRenderSkin) {
            return;
        }
        AbstractClientPlayer abstractClientPlayer = a4;
        PlayerModelLoader.applyAnimation((UUID)abstractClientPlayer.func_110124_au(), (String)a3.getName(), (byte[])a3.getAnimationBytes(), (String)"idle", (boolean)true);
        GlStateManager.func_179094_E();
        if (abstractClientPlayer.func_70093_af()) {
            GlStateManager.func_179137_b((double)0.0, (double)0.2, (double)0.0);
        }
        tg.r(a3.getName(), a3.getTexture());
        tg.r(iModel, playerData, a5, a3);
        if (a3.getGlowTexture() != null) {
            tg.r(a3.getName(), a3.getGlowTexture());
            tg.y();
            tg.r(iModel, playerData, a5, a3);
            tg.r();
        }
        iModel.clearData();
        GlStateManager.func_179121_F();
    }

    public tg(RenderPlayer a2) {
        tg a3;
        a3.j = a2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static /* synthetic */ void r(RenderPlayer a2, IModel a3, PlayerData a4, float a5, ModelData a6) {
        ModelRenderer modelRenderer = a2.func_177087_b().field_178723_h;
        a3 = a3.getModelPieces().iterator();
        Iterator iterator = a3;
        while (true) {
            int n2;
            IModelPiece iModelPiece;
            block9: {
                if (!iterator.hasNext()) {
                    return;
                }
                iModelPiece = (IModelPiece)a3.next();
                String string = iModelPiece.getName().toLowerCase(Locale.ROOT);
                GlStateManager.func_179094_E();
                int n3 = -1;
                switch (string.hashCode()) {
                    case -1436077376: {
                        if (!string.equals("rightarm")) break;
                        n2 = n3 = 0;
                        break block9;
                    }
                    case -303674748: {
                        if (!string.equals("rightforearm")) break;
                        n3 = 1;
                    }
                }
                n2 = n3;
            }
            switch (n2) {
                case 0: 
                case 1: {
                    PlayerData playerData = a4;
                    playerData.rightArm.setApplyAnimation(true);
                    ModelRenderer modelRenderer2 = modelRenderer;
                    playerData.rightArm.setRotateAngle(modelRenderer2.field_78795_f, modelRenderer2.field_78796_g, modelRenderer.field_78808_h);
                    playerData.rightArm.applyLocalTransform(0.0625f);
                    GlStateManager.func_179137_b((double)0.3125, (double)2.125, (double)0.0);
                    iModelPiece.render(a5, !a6.getSetting("hideArmRight"));
                    PlayerData playerData2 = a4;
                    playerData2.rightArm.setApplyAnimation(false);
                    playerData2.rightArm.setRotateAngle(0.0f, 0.0f, 0.0f);
                    break;
                }
            }
            GlStateManager.func_179121_F();
            iterator = a3;
        }
    }

    public static void r(ModelData a2, float a3) {
        IModelPiece iModelPiece;
        Iterator iterator;
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179108_z();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179094_E();
        ModelData modelData = a2;
        IModel iModel = PlayerModelLoader.getModel((String)a2.getName(), (byte[])modelData.getModelBytes());
        tg.r(modelData.getName(), a2.getTexture());
        Iterator iterator2 = iterator = iModel.getModelPieces().iterator();
        while (iterator2.hasNext()) {
            iModelPiece = (IModelPiece)iterator.next();
            GlStateManager.func_179094_E();
            iterator2 = iterator;
            iModelPiece.render(a3, false);
            GlStateManager.func_179121_F();
        }
        if (a2.getGlowTexture() != null) {
            tg.r(a2.getName(), a2.getGlowTexture());
            tg.y();
            iterator = iModel.getModelPieces().iterator();
            Iterator iterator3 = iterator;
            while (iterator3.hasNext()) {
                iModelPiece = (IModelPiece)iterator.next();
                GlStateManager.func_179094_E();
                iterator3 = iterator;
                iModelPiece.render(a3, false);
                GlStateManager.func_179121_F();
            }
            tg.r();
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179133_A();
    }
}

