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
        GlStateManager.pushMatrix();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a2) {
            a2.printStackTrace();
        }
        GlStateManager.popMatrix();
    }

    public static void fuck1(SignData a2, int a3, FontRenderer a4) {
        String a52;
        float a6 = 0.0f;
        for (String a52 : a2.getInfo()) {
            a6 = Math.max((float)a4.getStringWidth(a52), a6);
        }
        float a7 = a2.getInfo().size() * 10 + 5;
        a6 = (a6 + 4.0f) / 2.0f;
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a8) {
            a8.printStackTrace();
        }
        Test.trans("G:\\transs.txt");
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.scale((float)1.0f, (float)-1.0f, (float)-1.0f);
        a52 = Minecraft.getMinecraft().getRenderManager();
        Test.trans("G:\\trans.txt");
        GlStateManager.rotate((float)((RenderManager)a52).playerViewY, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-((RenderManager)a52).playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        Test.drawGradientRect(0.01, -a6, -a7 / 2.0f, a6, a7 / 2.0f, a2.getColorR(), a2.getColorG(), a2.getColorB(), a2.getColorA());
        GlStateManager.popMatrix();
        GlStateManager.glNormal3f((float)0.0f, (float)0.0f, (float)-0.010416667f);
        GlStateManager.depthMask((boolean)false);
        if (a3 < 0) {
            Test.trans("G:\\tran.txt");
            GlStateManager.rotate((float)(-((RenderManager)a52).playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)((RenderManager)a52).playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            for (int a9 = 0; a9 < a2.getInfo().size(); ++a9) {
                String a10 = a2.getInfo().get(a9);
                a4.drawString(a10, -a4.getStringWidth(a10) / 2, a9 * 10 - a2.getInfo().size() * 5, 0);
            }
        }
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a11) {
            a11.printStackTrace();
        }
    }

    public static void drawGradientRect(double a2, double a3, double a4, double a5, double a6, int a7, int a8, int a9, int a10) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator a11 = Tessellator.getInstance();
        BufferBuilder a12 = a11.getBuffer();
        a12.begin(7, DefaultVertexFormats.POSITION_COLOR);
        a12.pos(a5, a4, a2).color(a7, a8, a9, a10).endVertex();
        a12.pos(a3, a4, a2).color(a7, a8, a9, a10).endVertex();
        a12.pos(a3, a6, a2).color(a7, a8, a9, a10).endVertex();
        a12.pos(a5, a6, a2).color(a7, a8, a9, a10).endVertex();
        a11.draw();
        GlStateManager.enableTexture2D();
    }

    public static void baka() {
        GlStateManager.pushMatrix();
        pg.ALLATORIxDEMO();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\ss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a2) {
            a2.printStackTrace();
        }
        GlStateManager.disableTexture2D();
        Tessellator a3 = Tessellator.getInstance();
        BufferBuilder a4 = a3.getBuffer();
        a4.begin(7, DefaultVertexFormats.POSITION_COLOR);
        a4.pos(0.0, 0.0, 0.0).color(236, 73, 49, 255).endVertex();
        a4.pos(0.0, 10.0, 0.0).color(236, 73, 49, 255).endVertex();
        a4.pos(10.0, 10.0, 0.0).color(236, 73, 49, 255).endVertex();
        a4.pos(10.0, 0.0, 0.0).color(236, 73, 49, 255).endVertex();
        a3.draw();
        GlStateManager.enableTexture2D();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a5) {
            a5.printStackTrace();
        }
        GlStateManager.popMatrix();
    }

    public static void render(FontRenderer a2, SignData a3) {
        String a42;
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        RenderManager a5 = Minecraft.getMinecraft().getRenderManager();
        GlStateManager.disableCull();
        Collections.reverse(a3.getInfo());
        Test.trans("G:\\trans.txt");
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)0.0, (double)a3.getOffsetY(), (double)0.0);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        Test.doRender(new ItemStack(Items.GOLD_INGOT));
        GlStateManager.popMatrix();
        float a6 = 0.0f;
        for (String a42 : a3.getInfo()) {
            a6 = Math.max((float)a2.getStringWidth(a42), a6);
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
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.disableTexture2D();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a42 = Tessellator.getInstance();
        BufferBuilder a9 = a42.getBuffer();
        a9.begin(7, DefaultVertexFormats.POSITION_COLOR);
        a9.pos((double)(-a6), (double)(-a7), -0.1).color(236, 73, 49, 128).endVertex();
        a9.pos((double)a6, (double)(-a7), -0.1).color(236, 73, 49, 128).endVertex();
        a9.pos((double)a6, 10.0, -0.1).color(236, 73, 49, 128).endVertex();
        a9.pos((double)(-a6), 10.0, -0.1).color(236, 73, 49, 128).endVertex();
        a42.draw();
        GlStateManager.enableTexture2D();
        for (String a10 : a3.getInfo()) {
            a2.drawString(a10, (float)(-a2.getStringWidth(a10)) / 2.0f, 0.0f, -1, false);
            GlStateManager.translate((float)0.0f, (float)-10.0f, (float)0.0f);
        }
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableAlpha();
        try {
            pg.ALLATORIxDEMO(new String(Files.readAllBytes(Paths.get("G:\\sss.txt", new String[0])), StandardCharsets.UTF_8), new v[0]);
        }
        catch (IOException a11) {
            a11.printStackTrace();
        }
        GlStateManager.popMatrix();
    }

    public static void doRender(ItemStack a2) {
        int a3 = a2.isEmpty() ? 187 : Item.getIdFromItem((Item)a2.getItem()) + a2.getMetadata();
        boolean a4 = false;
        if (Test.bindEntityTexture()) {
            Minecraft.getMinecraft().getRenderManager().renderEngine.getTexture(Test.getEntityTexture()).setBlurMipmap(false, false);
            a4 = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        IBakedModel a5 = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(a2, (World)FMLClientHandler.instance().getWorldClient(), null);
        GlStateManager.pushMatrix();
        IBakedModel a6 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a5, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
        Minecraft.getMinecraft().getRenderItem().renderItem(a2, a6);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        Test.bindEntityTexture();
        if (a4) {
            Minecraft.getMinecraft().getRenderManager().renderEngine.getTexture(Test.getEntityTexture()).restoreLastBlurMipmap();
        }
    }

    protected static ResourceLocation getEntityTexture() {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    protected static boolean bindEntityTexture() {
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        return true;
    }

    public static void trans(String a2) {
        RenderManager a3 = Minecraft.getMinecraft().getRenderManager();
        try {
            List<String> a4 = Files.readAllLines(Paths.get(a2, new String[0]));
            for (String a5 : a4) {
                a5 = a5.replace("{x}", String.valueOf(a3.playerViewX));
                a5 = a5.replace("{y}", String.valueOf(a3.playerViewY));
                String[] a6 = a5.split(" ");
                switch (a6[0].toLowerCase(Locale.ROOT)) {
                    case "t": {
                        GlStateManager.translate((double)Test.toDouble(a6[1]), (double)Test.toDouble(a6[2]), (double)Test.toDouble(a6[3]));
                        break;
                    }
                    case "r": {
                        GlStateManager.rotate((float)((float)Test.toDouble(a6[1])), (float)((float)Test.toDouble(a6[2])), (float)((float)Test.toDouble(a6[3])), (float)((float)Test.toDouble(a6[4])));
                        break;
                    }
                    case "s": {
                        GlStateManager.scale((double)Test.toDouble(a6[1]), (double)Test.toDouble(a6[2]), (double)Test.toDouble(a6[3]));
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

