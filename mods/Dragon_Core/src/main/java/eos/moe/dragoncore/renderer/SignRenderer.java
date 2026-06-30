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
        if (a3.field_145915_a.length <= 1 || a3.field_145915_a[1] == null) {
            return false;
        }
        if (!(a3.field_145915_a[1] instanceof TextComponentBase)) {
            return false;
        }
        String a11 = FormatCacheTextComponentBase.of(a3.field_145915_a[1]).toStringCache();
        SignData a12 = SignRenderer.getSignData(a11);
        if (a12 == null || a12.getModel() == null || a12.getCheck() == null || !a12.getCheck().contains("\u00a7")) {
            return false;
        }
        a10.cancel();
        int a13 = a3.func_145832_p();
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
        BlockPos a16 = a3.func_174877_v();
        jv a17 = no.ALLATORIxDEMO(a15);
        no.ALLATORIxDEMO(a16.func_177958_n(), a16.func_177956_o(), a16.func_177952_p(), a15, a17, "idle");
        GlStateManager.func_179126_j();
        GlStateManager.func_179143_c((int)515);
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179129_p();
        if (a8 >= 0) {
            SignRenderer.bindTexture(DESTROY_STAGES[a8]);
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)4.0f, (float)4.0f, (float)1.0f);
            GlStateManager.func_179109_b((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.func_179128_n((int)5888);
        } else {
            no.ALLATORIxDEMO(a15);
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179091_B();
        if (a8 < 0) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a9);
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)a4, (double)a5, (double)a6);
        GlStateManager.func_179109_b((float)0.5f, (float)0.0f, (float)0.5f);
        GlStateManager.func_179114_b((float)(-a14), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)-1.0f);
        GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)-1.0f);
        a17.render(0.0625f);
        a17.clearData();
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
        float a18 = 1.0f;
        GlStateManager.func_179131_c((float)a18, (float)a18, (float)a18, (float)a18);
        if (a8 >= 0) {
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n((int)5888);
        }
        return true;
    }

    public static void renderText(FontRenderer a2, TileEntitySign a3, double a4, double a5, double a6) {
        if (!(a3.field_145915_a[1] instanceof TextComponentBase)) {
            return;
        }
        float a7 = SignRenderer.distance(a4, a5, a6);
        if (a7 <= 16.0f) {
            String a8 = FormatCacheTextComponentBase.of(a3.field_145915_a[1]).toStringCache();
            SignData a9 = SignRenderer.getSignData(a8);
            if (a9 == null || a9.getModel() == null || a9.getCheck() == null || !a9.getCheck().contains("\u00a7")) {
                return;
            }
            int a10 = a3.func_145832_p();
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
            GlStateManager.func_179094_E();
            GlStateManager.func_179126_j();
            GlStateManager.func_179091_B();
            GlStateManager.func_179109_b((float)((float)a4 + 0.5f), (float)((float)a5 + 1.5f), (float)((float)a6 + 0.5f));
            GlStateManager.func_179089_o();
            GlStateManager.func_179101_C();
            GlStateManager.func_179094_E();
            GlStateManager.func_179140_f();
            GlStateManager.func_179152_a((float)0.75f, (float)0.75f, (float)0.75f);
            GlStateManager.func_179114_b((float)(-a11), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)-1.45f);
            GlStateManager.func_179137_b((double)0.0, (double)a9.getOffsetY(), (double)0.0);
            GlStateManager.func_179123_a();
            GlStateManager.func_179114_b((float)-35.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            RenderHelper.func_74519_b();
            try {
                if (a9.getItem() != null && a7 < 13.0f) {
                    RenderItem a12 = Minecraft.func_71410_x().func_175599_af();
                    if (a9.getItemStack() == null) {
                        NBTTagCompound a13 = JsonToNBT.func_180713_a((String)a9.getItem());
                        ItemStack a14 = new ItemStack(a13);
                        a9.setItemStack(a14);
                    }
                    a12.func_181564_a(a9.getItemStack(), ItemCameraTransforms.TransformType.FIXED);
                }
            }
            catch (NBTException a12) {
                // empty catch block
            }
            RenderHelper.func_74518_a();
            GlStateManager.func_179099_b();
            GlStateManager.func_179121_F();
            GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)-1.0f);
            GlStateManager.func_179091_B();
            GlStateManager.func_179094_E();
            GlStateManager.func_179129_p();
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.func_179114_b((float)(-a11), (float)0.0f, (float)1.0f, (float)0.0f);
            if (a10 == 3 || a10 == 2) {
                GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.45f);
            } else {
                GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)-0.45f);
            }
            GlStateManager.func_179114_b((float)(-a11), (float)0.0f, (float)-1.0f, (float)0.0f);
            GlStateManager.func_179137_b((double)0.0, (double)(-a9.getOffsetY()), (double)0.0);
            float a15 = Minecraft.func_71410_x().func_175598_ae().field_78735_i;
            float a16 = Minecraft.func_71410_x().func_175598_ae().field_78732_j;
            GlStateManager.func_179114_b((float)a15, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)a16, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179139_a((double)0.015, (double)0.015, (double)0.015);
            float a17 = a7 / 7.0f + 1.0f;
            GlStateManager.func_179152_a((float)a17, (float)a17, (float)a17);
            int a18 = 0;
            int a19 = a9.getInfo().size() * 10;
            for (String a20 : a9.getInfo()) {
                a18 = Math.max(a2.func_78256_a(a20), a18);
            }
            float a21 = (float)a18 / 2.0f;
            float a22 = (float)a19 / 2.0f;
            GlStateManager.func_179132_a((boolean)false);
            GlStateManager.func_179090_x();
            Tessellator a23 = Tessellator.func_178181_a();
            BufferBuilder a24 = a23.func_178180_c();
            a24.func_181668_a(7, DefaultVertexFormats.field_181706_f);
            a24.func_181662_b((double)(-a21), (double)(-a22 - 1.0f), 0.001).func_181669_b(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).func_181675_d();
            a24.func_181662_b((double)(-a21), (double)(a22 - 1.0f), 0.001).func_181669_b(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).func_181675_d();
            a24.func_181662_b((double)a21, (double)(a22 - 1.0f), 0.001).func_181669_b(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).func_181675_d();
            a24.func_181662_b((double)a21, (double)(-a22 - 1.0f), 0.001).func_181669_b(a9.getColorR(), a9.getColorG(), a9.getColorB(), a9.getColorA()).func_181675_d();
            a23.func_78381_a();
            GlStateManager.func_179098_w();
            for (int a25 = 0; a25 < a9.getInfo().size(); ++a25) {
                String a26 = a9.getInfo().get(a25);
                int a27 = a2.func_78256_a(a26);
                a2.func_78276_b(a26, -a27 / 2, (int)(-a22 + (float)(a25 * 10)), -1);
            }
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179084_k();
            GlStateManager.func_179126_j();
            GlStateManager.func_179145_e();
            GlStateManager.func_179121_F();
            GlStateManager.func_179121_F();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    protected static void bindTexture(ResourceLocation a2) {
        TextureManager a3 = Minecraft.func_71410_x().func_110434_K();
        if (a3 != null) {
            a3.func_110577_a(a2);
        }
    }
}

