/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.ExclusionStrategy
 *  com.google.gson.FieldAttributes
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.JsonToNBT
 *  net.minecraft.nbt.NBTException
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.TextComponentBase
 */
package eos.moe.dragoncore.renderer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eos.moe.dragoncore.data.SignData;
import eos.moe.dragoncore.interfaces.FormatCacheTextComponentBase;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.no;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentBase;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class SignRenderer {
    public static Map<String, SignData> signDataMap = new HashMap<String, SignData>();
    public static final Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[]{new ExclusionStrategy(){
        {
            1 a2;
        }

        public boolean shouldSkipField(FieldAttributes a2) {
            return false;
        }

        public boolean shouldSkipClass(Class<?> a2) {
            return a2 == ItemStack.class;
        }
    }}).create();
    protected static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[]{new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png")};

    public SignRenderer() {
        SignRenderer a2;
    }

    public static SignData getSignData(String a2) {
        if (signDataMap.containsKey(a2)) {
            return signDataMap.get(a2);
        }
        try {
            String a3 = a2.substring(0, a2.length() - 2);
            SignData a4 = (SignData)gson.fromJson(a3, SignData.class);
            signDataMap.put(a2, a4);
            return a4;
        }
        catch (Exception a5) {
            signDataMap.put(a2, null);
            return null;
        }
    }

    public static float distance(double a2, double a3, double a4) {
        return (float)Math.sqrt(SignRenderer.square(a2) + SignRenderer.square(a3) + SignRenderer.square(a4));
    }

    public static double square(double a2) {
        return a2 * a2;
    }

    public static boolean renderModel(FontRenderer a2, TileEntitySign a3, double a4, double a5, double a6, float a7, int a8, float a9, CallbackInfo a10) {
        if (a3.signText.length <= 1 || a3.signText[1] == null) {
            return false;
        }
        if (!(a3.signText[1] instanceof TextComponentBase)) {
            return false;
        }
        String a11 = FormatCacheTextComponentBase.of(a3.signText[1]).toStringCache();
        SignData a12 = SignRenderer.getSignData(a11);
        if (a12 == null || a12.getModel() == null || a12.getCheck() == null || !a12.getCheck().contains("\u00a7")) {
            return false;
        }
        a10.cancel();
        int a13 = a3.getBlockMetadata();
        float a14 = 0.0f;
        if (a13 == 2) {
            a14 = 180.0f;
        }
        if (a13 == 4) {
            a14 = 90.0f;
        }
        if (a13 == 5) {
            a14 = -90.0f;
        }
        String a15 = a12.getModel();
        BlockPos a16 = a3.getPos();
        jv a17 = no.ALLATORIxDEMO(a15);
        no.ALLATORIxDEMO(a16.getX(), a16.getY(), a16.getZ(), a15, a17, "idle");
        GlStateManager.enableDepth();
        GlStateManager.depthFunc((int)515);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableCull();
        if (a8 >= 0) {
            SignRenderer.bindTexture(DESTROY_STAGES[a8]);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)4.0f, (float)4.0f, (float)1.0f);
            GlStateManager.translate((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.matrixMode((int)5888);
        } else {
            no.ALLATORIxDEMO(a15);
        }
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        if (a8 < 0) {
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)a9);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)a4, (double)a5, (double)a6);
        GlStateManager.translate((float)0.5f, (float)0.0f, (float)0.5f);
        GlStateManager.rotate((float)(-a14), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)-1.0f);
        GlStateManager.scale((float)1.0f, (float)-1.0f, (float)-1.0f);
        a17.render(0.0625f);
        a17.clearData();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        float a18 = 1.0f;
        GlStateManager.color((float)a18, (float)a18, (float)a18, (float)a18);
        if (a8 >= 0) {
            GlStateManager.matrixMode((int)5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode((int)5888);
        }
        return true;
    }

    public static void renderText(FontRenderer a2, TileEntitySign a3, double a4, double a5, double a6) {
        if (!(a3.signText[1] instanceof TextComponentBase)) {
            return;
        }
        float a7 = SignRenderer.distance(a4, a5, a6);
        if (a7 <= 16.0f) {
            String a8 = FormatCacheTextComponentBase.of(a3.signText[1]).toStringCache();
            SignData a9 = SignRenderer.getSignData(a8);
            if (a9 == null || a9.getModel() == null || a9.getCheck() == null || !a9.getCheck().contains("\u00a7")) {
                return;
            }
            int a10 = a3.getBlockMetadata();
            float a11 = 0.0f;
            if (a10 == 2) {
                a11 = 180.0f;
            }
            if (a10 == 4) {
                a11 = 90.0f;
            }
            if (a10 == 5) {
                a11 = -90.0f;
            }
            GlStateManager.pushMatrix();
            GlStateManager.enableDepth();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translate((float)((float)a4 + 0.5f), (float)((float)a5 + 1.5f), (float)((float)a6 + 0.5f));
            GlStateManager.enableCull();
            GlStateManager.disableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.scale((float)0.75f, (float)0.75f, (float)0.75f);
            GlStateManager.rotate((float)(-a11), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)-1.45f);
            GlStateManager.translate((double)0.0, (double)a9.getOffsetY(), (double)0.0);
            GlStateManager.pushAttrib();
            GlStateManager.rotate((float)-35.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            RenderHelper.enableStandardItemLighting();
            try {
                if (a9.getItem() != null && a7 < 13.0f) {
                    RenderItem a12 = Minecraft.getMinecraft().getRenderItem();
                    if (a9.getItemStack() == null) {
                        NBTTagCompound a13 = JsonToNBT.getTagFromJson((String)a9.getItem());
                        ItemStack a14 = new ItemStack(a13);
                        a9.setItemStack(a14);
                    }
                    a12.renderItem(a9.getItemStack(), ItemCameraTransforms.TransformType.FIXED);
                }
            }
            catch (NBTException a12) {
                // empty catch block
            }
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
            GlStateManager.scale((float)1.0f, (float)-1.0f, (float)-1.0f);
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.rotate((float)(-a11), (float)0.0f, (float)1.0f, (float)0.0f);
            if (a10 == 3 || a10 == 2) {
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.45f);
            } else {
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)-0.45f);
            }
            GlStateManager.rotate((float)(-a11), (float)0.0f, (float)-1.0f, (float)0.0f);
            GlStateManager.translate((double)0.0, (double)(-a9.getOffsetY()), (double)0.0);
            float a15 = Minecraft.getMinecraft().getRenderManager().playerViewY;
            float a16 = Minecraft.getMinecraft().getRenderManager().playerViewX;
            GlStateManager.rotate((float)a15, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)a16, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.scale((double)0.015, (double)0.015, (double)0.015);
            float a17 = a7 / 7.0f + 1.0f;
            GlStateManager.scale((float)a17, (float)a17, (float)a17);
            int a18 = 0;
            int a19 = a9.getInfo().size() * 10;
            for (String a20 : a9.getInfo()) {
                a18 = Math.max(a2.getStringWidth(a20), a18);
            }
            float a21 = (float)a18 / 2.0f;
            float a22 = (float)a19 / 2.0f;
            GlStateManager.depthMask((boolean)false);
            GlStateManager.disableTexture2D();
            Tessellator a23 = Tessellator.getInstance();
            BufferBuilder a24 = a23.getBuffer();
            a24.begin(7, DefaultVertexFormats.POSITION_COLOR);
            a24.pos((double)(-a21), (double)(-a22 - 1.0f), 0.001).color(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).endVertex();
            a24.pos((double)(-a21), (double)(a22 - 1.0f), 0.001).color(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).endVertex();
            a24.pos((double)a21, (double)(a22 - 1.0f), 0.001).color(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).endVertex();
            a24.pos((double)a21, (double)(-a22 - 1.0f), 0.001).color(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).endVertex();
            a23.draw();
            GlStateManager.enableTexture2D();
            for (int a25 = 0; a25 < a9.getInfo().size(); ++a25) {
                String a26 = a9.getInfo().get(a25);
                int a27 = a2.getStringWidth(a26);
                a2.drawString(a26, -a27 / 2, (int)(-a22 + (float)(a25 * 10)), -1);
            }
            GlStateManager.depthMask((boolean)true);
            GlStateManager.disableBlend();
            GlStateManager.enableDepth();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    protected static void bindTexture(ResourceLocation a2) {
        TextureManager a3 = Minecraft.getMinecraft().getTextureManager();
        if (a3 != null) {
            a3.bindTexture(a2);
        }
    }
}

