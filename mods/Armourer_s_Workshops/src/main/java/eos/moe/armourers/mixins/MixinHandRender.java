/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.PlayerModelLoader
 *  javax.vecmath.Matrix4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$CullFace
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.apache.commons.lang3.tuple.Pair
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.MobendsHelper;
import eos.moe.armourers.ModelData;
import eos.moe.armourers.ae;
import eos.moe.armourers.bk;
import eos.moe.armourers.dn;
import eos.moe.armourers.fk;
import eos.moe.armourers.ih;
import eos.moe.armourers.km;
import eos.moe.armourers.mn;
import eos.moe.armourers.oh;
import eos.moe.armourers.ol;
import eos.moe.armourers.on;
import eos.moe.armourers.r;
import eos.moe.armourers.tg;
import eos.moe.armourers.um;
import eos.moe.armourers.vn;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import eos.moe.armourers.zl;
import eos.moe.dragoncore.api.PlayerModelLoader;
import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.vecmath.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemRenderer.class})
public class MixinHandRender {
    @Shadow
    @Final
    private RenderItem itemRenderer;
    private static final Matrix4f flipX = new Matrix4f();

    private /* synthetic */ void renderRightArm_transTo(RenderPlayer a2, AbstractClientPlayer a3) {
        float f2 = 1.0f;
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
        f2 = 0.0625f;
        a2 = a2.getMainModel();
        GlStateManager.enableBlend();
        RenderPlayer renderPlayer = a2;
        a2.swingProgress = 0.0f;
        renderPlayer.isSneak = false;
        a2.setRotationAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)a3);
        renderPlayer.bipedRightArm.rotateAngleX = 0.0f;
        a2.bipedRightArmwear.rotateAngleX = 0.0f;
        GlStateManager.disableBlend();
    }

    public MixinHandRender() {
        MixinHandRender a2;
    }

    @Inject(method={"renderItemSide"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_renderItemSide(EntityLivingBase a2, ItemStack a32, ItemCameraTransforms.TransformType a4, boolean a5, CallbackInfo a6) {
        r r2;
        boolean bl;
        int n2;
        Pair<? extends IBakedModel, Matrix4f> pair = new r[8];
        pair[0] = vn.z;
        pair[1] = vn.q;
        pair[2] = vn.c;
        pair[3] = vn.s;
        pair[4] = vn.p;
        pair[5] = vn.j;
        pair[6] = vn.b;
        pair[7] = vn.t;
        Object object = pair;
        um um2 = null;
        r r3 = null;
        int n3 = n2 = 0;
        while (n3 < um.values().length) {
            if (bk.r(um.values()[n2], a32.getItem())) {
                um2 = um.values()[n2];
                r3 = object[n2];
            }
            n3 = ++n2;
        }
        if (um2 == null) {
            bl = true;
            r2 = r3;
        } else {
            bl = false;
            r2 = r3;
        }
        if (bl | r2 == null && !zh.w) {
            return;
        }
        fk fk2 = fk.r((ItemStack)a32);
        if (fk2 == null) {
            r r4;
            boolean bl2;
            if (um2 == null) {
                bl2 = true;
                r4 = r3;
            } else {
                bl2 = false;
                r4 = r3;
            }
            if (!(bl2 | r4 == null) && (a32 = zg.r((Entity)a2, r3)).size() > 0) {
                fk2 = (fk)a32.get(0);
            }
        }
        if (fk2 == null) {
            return;
        }
        if (fk2.r() != null) {
            boolean bl3;
            a6.cancel();
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.enableCull();
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
            GlStateManager.translate((float)0.0f, (float)0.0625f, (float)0.0625f);
            if (a5) {
                GlStateManager.scale((float)-1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.cullFace((GlStateManager.CullFace)GlStateManager.CullFace.FRONT);
            }
            if (um2 == um.m || fk2.r().r() == vn.c) {
                MixinHandRender a7;
                int a32 = a2.getItemInUseMaxCount();
                object = km.t.j;
                bl3 = a5;
                ((ol)object).j = a7.getAnimationFrame(a32);
                ((ol)object).render((Entity)a2, fk2.r(), false, new mn(), null, false, 0.0, false);
            } else {
                ih.r(fk2, false);
                bl3 = a5;
            }
            if (bl3) {
                GlStateManager.cullFace((GlStateManager.CullFace)GlStateManager.CullFace.BACK);
            }
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
            return;
        }
        if (fk2.r() != null) {
            Object a32;
            a6.cancel();
            Object object2 = a32 = fk2.r();
            PlayerModelLoader.applyAnimation((UUID)a2.getUniqueID(), (String)((ModelData)a32).getName(), (byte[])((ModelData)object2).getAnimationBytes(), (String)"idle", (boolean)true);
            GlStateManager.pushMatrix();
            if (((ModelData)object2).getTransformBakedModel() != null && (object = ((ModelData)a32).getTransformBakedModel().handlePerspective(a4)).getRight() != null) {
                a2 = new Matrix4f((Matrix4f)object.getRight());
                if (a5) {
                    EntityLivingBase entityLivingBase = a2;
                    EntityLivingBase entityLivingBase2 = a2;
                    entityLivingBase.mul(flipX, (Matrix4f)entityLivingBase2);
                    entityLivingBase.mul((Matrix4f)entityLivingBase2, flipX);
                }
                ForgeHooksClient.multiplyCurrentGlMatrix((Matrix4f)a2);
            }
            GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
            Object object3 = a32;
            tg.r((ModelData)object3, 0.0625f);
            PlayerModelLoader.getModel((String)((ModelData)object3).getName(), (byte[])((ModelData)a32).getModelBytes()).clearData();
            GlStateManager.popMatrix();
        }
    }

    @Redirect(method={"renderArmFirstPerson"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/RenderPlayer;renderRightArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
    private /* synthetic */ void redirect_renderArmFirstPerson_renderRightArm(RenderPlayer a3, AbstractClientPlayer a4) {
        MixinHandRender a5;
        MobendsHelper.removeRenderEntity(a3);
        if (Minecraft.getMinecraft().player == null) {
            a3.renderRightArm(a4);
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        AbstractClientPlayer abstractClientPlayer = a4;
        Object object = zg.r((Entity)abstractClientPlayer, vn.a);
        Object object2 = zg.r((Entity)abstractClientPlayer, vn.u);
        List<fk> list = object2;
        arrayList.addAll((Collection<fk>)object);
        arrayList.addAll(list);
        if (list.size() == 0) {
            object2 = new ArrayList<fk>();
            object2.add((fk)fk.r((ItemStack)a4.inventory.armorInventory.get(1)));
            object2.add((fk)fk.r((ItemStack)a4.inventory.armorInventory.get(2)));
            object2.add((fk)fk.r((ItemStack)a4.inventory.armorInventory.get(3)));
            object2.add(fk.r((ItemStack)a4.inventory.armorInventory.get(0)));
            object2.removeIf(Objects::isNull);
            if (!object2.isEmpty() && object.size() > 0) {
                object2.removeIf(a2 -> "chest".equalsIgnoreCase(a2.r()));
            }
            object2.removeIf(a2 -> !"outfit".equalsIgnoreCase(a2.r()) && !"chest".equalsIgnoreCase(a2.r()));
            arrayList.addAll((Collection<fk>)object2);
        }
        if ((object2 = EntityBenderRegistry.instance.getForEntity(a4)) != null) {
            ((EntityBender)object2).resetArmWear(a3);
        }
        object = on.r(a4.getUniqueID());
        Object object3 = object2 = arrayList.iterator();
        while (object3.hasNext()) {
            fk fk2 = (fk)object2.next();
            object3 = object2;
            Object object4 = object;
            ((on)object4).r(fk2.r());
            ((on)object4).r(fk2.r());
        }
        if (a3 != null && !((on)object).e) {
            RenderPlayer renderPlayer;
            zl.s.y((Entity)a4);
            object2 = zl.m.get(a4);
            if (object2 != null && ((ae)object2).y() != null) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(zl.m.get(a4).z());
                renderPlayer = a3;
            } else {
                Minecraft.getMinecraft().getTextureManager().bindTexture(a4.getLocationSkin());
                renderPlayer = a3;
            }
            renderPlayer.renderRightArm(a4);
            zl.s.r((Entity)a4);
        }
        if (arrayList.size() == 0) {
            return;
        }
        a5.renderRightArm_transTo(a3, a4);
        for (fk fk2 : arrayList) {
            if (fk2.r() != null) {
                km.t.r(fk2.r(), new dn(0.0625f, new mn(), oh.l, 0, true, false, false, a4.getLocationSkin(), true), (Entity)a4, (ModelBiped)a3.getMainModel());
                continue;
            }
            if (fk2.r() == null) continue;
            tg.r(a3, a4, 0.0625f);
        }
    }

    private /* synthetic */ List<fk> getPlayerArmourSkins(EntityPlayer a2, String a3) {
        int n2;
        ArrayList<fk> arrayList = new ArrayList<fk>();
        int n3 = n2 = 0;
        while (n3 < 4) {
            fk fk2 = fk.r((ItemStack)a2.inventory.armorInventory.get(n2));
            if (fk2 != null && fk2.r() != null && a3.equalsIgnoreCase(fk2.r())) {
                arrayList.add(fk2);
            }
            n3 = ++n2;
        }
        return arrayList;
    }

    static {
        flipX.setIdentity();
        MixinHandRender.flipX.m00 = -1.0f;
    }

    private /* synthetic */ int getAnimationFrame(int a2) {
        if (a2 >= 18) {
            return 2;
        }
        if (a2 > 13) {
            return 1;
        }
        return 0;
    }
}

