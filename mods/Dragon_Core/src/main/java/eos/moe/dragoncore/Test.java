/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.data.SignData;
import eos.moe.dragoncore.pg;
import eos.moe.dragoncore.v;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Test {
    public Test() {
        Test a2;
    }

    public static void fuck() {
        GlStateManager.func_179094_E();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a2) {
            a2.printStackTrace();
        }
        GlStateManager.func_179121_F();
    }

    public static void fuck1(SignData a2, int a3, FontRenderer a4) {
        String a52;
        float a6 = 0.0f;
        for (String a52 : a2.getInfo()) {
            a6 = Math.max((float)a4.func_78256_a(a52), a6);
        }
        float a7 = a2.getInfo().size() * 10 + 5;
        a6 = (a6 + 4.0f) / 2.0f;
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a8) {
            a8.printStackTrace();
        }
        Test.trans("G:\\transs.txt");
        GlStateManager.func_179091_B();
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)-1.0f);
        a52 = Minecraft.func_71410_x().func_175598_ae();
        Test.trans("G:\\trans.txt");
        GlStateManager.func_179114_b((float)((RenderManager)a52).field_78735_i, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(-((RenderManager)a52).field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        Test.drawGradientRect(0.01, -a6, -a7 / 2.0f, a6, a7 / 2.0f, a2.getColorR(), a2.getColorG(), a2.getColorB(), a2.getColorA());
        GlStateManager.func_179121_F();
        GlStateManager.func_187432_a((float)0.0f, (float)0.0f, (float)-0.010416667f);
        GlStateManager.func_179132_a((boolean)false);
        if (a3 < 0) {
            Test.trans("G:\\tran.txt");
            GlStateManager.func_179114_b((float)(-((RenderManager)a52).field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)((RenderManager)a52).field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            for (int a9 = 0; a9 < a2.getInfo().size(); ++a9) {
                String a10 = a2.getInfo().get(a9);
                a4.func_78276_b(a10, -a4.func_78256_a(a10) / 2, a9 * 10 - a2.getInfo().size() * 5, 0);
            }
        }
        GlStateManager.func_179141_d();
        GlStateManager.func_179145_e();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a11) {
            a11.printStackTrace();
        }
    }

    public static void drawGradientRect(double a2, double a3, double a4, double a5, double a6, int a7, int a8, int a9, int a10) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j((int)7425);
        Tessellator a11 = Tessellator.func_178181_a();
        BufferBuilder a12 = a11.func_178180_c();
        a12.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        a12.func_181662_b(a5, a4, a2).func_181669_b(a7, a8, a9, a10).func_181675_d();
        a12.func_181662_b(a3, a4, a2).func_181669_b(a7, a8, a9, a10).func_181675_d();
        a12.func_181662_b(a3, a6, a2).func_181669_b(a7, a8, a9, a10).func_181675_d();
        a12.func_181662_b(a5, a6, a2).func_181669_b(a7, a8, a9, a10).func_181675_d();
        a11.func_78381_a();
        GlStateManager.func_179098_w();
    }

    public static void baka() {
        GlStateManager.func_179094_E();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a2) {
            a2.printStackTrace();
        }
        GlStateManager.func_179090_x();
        Tessellator a3 = Tessellator.func_178181_a();
        BufferBuilder a4 = a3.func_178180_c();
        a4.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        a4.func_181662_b(0.0, 0.0, 0.0).func_181669_b(236, 73, 49, 255).func_181675_d();
        a4.func_181662_b(0.0, 10.0, 0.0).func_181669_b(236, 73, 49, 255).func_181675_d();
        a4.func_181662_b(10.0, 10.0, 0.0).func_181669_b(236, 73, 49, 255).func_181675_d();
        a4.func_181662_b(10.0, 0.0, 0.0).func_181669_b(236, 73, 49, 255).func_181675_d();
        a3.func_78381_a();
        GlStateManager.func_179098_w();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a5) {
            a5.printStackTrace();
        }
        GlStateManager.func_179121_F();
    }

    public static void render(FontRenderer a2, SignData a3) {
        String a42;
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        RenderManager a5 = Minecraft.func_71410_x().func_175598_ae();
        GlStateManager.func_179129_p();
        Collections.reverse(a3.getInfo());
        Test.trans("G:\\trans.txt");
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)0.0, (double)a3.getOffsetY(), (double)0.0);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        Test.doRender(new ItemStack(Items.field_151043_k));
        GlStateManager.func_179121_F();
        float a6 = 0.0f;
        for (String a42 : a3.getInfo()) {
            a6 = Math.max((float)a2.func_78256_a(a42), a6);
        }
        float a7 = (a3.getInfo().size() - 1) * 10;
        a6 = (a6 + 4.0f) / 2.0f;
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a8) {
            a8.printStackTrace();
        }
        GlStateManager.func_179118_c();
        GlStateManager.func_179097_i();
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179090_x();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a42 = Tessellator.func_178181_a();
        BufferBuilder a9 = a42.func_178180_c();
        a9.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        a9.func_181662_b((double)(-a6), (double)(-a7), -0.1).func_181669_b(236, 73, 49, 128).func_181675_d();
        a9.func_181662_b((double)a6, (double)(-a7), -0.1).func_181669_b(236, 73, 49, 128).func_181675_d();
        a9.func_181662_b((double)a6, 10.0, -0.1).func_181669_b(236, 73, 49, 128).func_181675_d();
        a9.func_181662_b((double)(-a6), 10.0, -0.1).func_181669_b(236, 73, 49, 128).func_181675_d();
        a42.func_78381_a();
        GlStateManager.func_179098_w();
        for (String a10 : a3.getInfo()) {
            a2.func_175065_a(a10, (float)(-a2.func_78256_a(a10)) / 2.0f, 0.0f, -1, false);
            GlStateManager.func_179109_b((float)0.0f, (float)-10.0f, (float)0.0f);
        }
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
        GlStateManager.func_179141_d();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a11) {
            a11.printStackTrace();
        }
        GlStateManager.func_179121_F();
    }

    public static void doRender(ItemStack a2) {
        int a3 = a2.func_190926_b() ? 187 : Item.func_150891_b((Item)a2.func_77973_b()) + a2.func_77960_j();
        boolean a4 = false;
        if (Test.bindEntityTexture()) {
            Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110581_b(Test.getEntityTexture()).func_174936_b(false, false);
            a4 = true;
        }
        GlStateManager.func_179091_B();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
        GlStateManager.func_179147_l();
        RenderHelper.func_74519_b();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179094_E();
        IBakedModel a5 = Minecraft.func_71410_x().func_175599_af().func_184393_a(a2, (World)FMLClientHandler.instance().getWorldClient(), null);
        GlStateManager.func_179094_E();
        IBakedModel a6 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a5, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
        Minecraft.func_71410_x().func_175599_af().func_180454_a(a2, a6);
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        Test.bindEntityTexture();
        if (a4) {
            Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110581_b(Test.getEntityTexture()).func_174935_a();
        }
    }

    protected static ResourceLocation getEntityTexture() {
        return TextureMap.field_110575_b;
    }

    protected static boolean bindEntityTexture() {
        Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110577_a(TextureMap.field_110575_b);
        return true;
    }

    public static void trans(String a2) {
        RenderManager a3 = Minecraft.func_71410_x().func_175598_ae();
        try {
            List<String> a4 = Files.readAllLines(Paths.get(a2, new String[0]));
            for (String a5 : a4) {
                a5 = a5.replace("{x}", String.valueOf(a3.field_78732_j));
                a5 = a5.replace("{y}", String.valueOf(a3.field_78735_i));
                String[] a6 = a5.split(" ");
                switch (a6[0].toLowerCase(Locale.ROOT)) {
                    case "t": {
                        GlStateManager.func_179137_b((double)Test.toDouble(a6[1]), (double)Test.toDouble(a6[2]), (double)Test.toDouble(a6[3]));
                        break;
                    }
                    case "r": {
                        GlStateManager.func_179114_b((float)((float)Test.toDouble(a6[1])), (float)((float)Test.toDouble(a6[2])), (float)((float)Test.toDouble(a6[3])), (float)((float)Test.toDouble(a6[4])));
                        break;
                    }
                    case "s": {
                        GlStateManager.func_179139_a((double)Test.toDouble(a6[1]), (double)Test.toDouble(a6[2]), (double)Test.toDouble(a6[3]));
                    }
                }
            }
        }
        catch (Exception a7) {
            a7.printStackTrace();
        }
    }

    public static double toDouble(String a2) {
        if (a2.contains("--")) {
            a2 = a2.replace("--", "");
        }
        return Double.parseDouble(a2);
    }
}

