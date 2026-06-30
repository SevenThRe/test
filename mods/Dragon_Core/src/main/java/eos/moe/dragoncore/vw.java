/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.ITextureMapPopulator
 *  net.minecraft.client.renderer.texture.Stitcher
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.crash.ICrashReportDetail
 *  net.minecraft.util.ReportedException
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.ProgressManager
 *  net.minecraftforge.fml.common.ProgressManager$ProgressBar
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import eos.moe.dragoncore.cr;
import eos.moe.dragoncore.jt;
import eos.moe.dragoncore.qo;
import eos.moe.dragoncore.uu;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureMapPopulator;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.ProgressManager;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class vw
extends TextureMap {
    private static final Logger t = LogManager.getLogger();
    public static final ResourceLocation r = new ResourceLocation("missingno");
    public static final ResourceLocation x = new ResourceLocation("textures/atlas/blocks.png");
    private final List<TextureAtlasSprite> v;
    private final Map<String, TextureAtlasSprite> m;
    private final Map<String, TextureAtlasSprite> c;
    private final String q;
    private final ITextureMapPopulator b;
    private int o;
    public boolean y;
    private final Deque<ResourceLocation> k = new ArrayDeque<ResourceLocation>();
    private final Set<ResourceLocation> ALLATORIxDEMO = new HashSet<ResourceLocation>();

    public vw(String a2) {
        a3(a2, null);
        vw a3;
    }

    public vw(String basePathIn, @Nullable ITextureMapPopulator a2) {
        this(basePathIn, a2, false);
    }

    public vw(String a2, boolean a3) {
        a4(a2, null, a3);
        vw a4;
    }

    public vw(String basePathIn, @Nullable ITextureMapPopulator iconCreatorIn, boolean a2) {
        super("");
        this.v = Lists.newArrayList();
        this.m = Maps.newHashMap();
        this.c = Maps.newHashMap();
        this.q = basePathIn;
        this.b = iconCreatorIn;
    }

    public void func_110551_a(IResourceManager a2) throws IOException {
        vw a3;
        if (a3.b != null) {
            a3.func_174943_a(a2, a3.b);
        }
    }

    public void func_174943_a(IResourceManager a2, ITextureMapPopulator a3) {
        vw a4;
        a4.y = false;
        a4.m.clear();
        a4.func_147631_c();
        a3.func_177059_a((TextureMap)a4);
        a4.func_110571_b(a2);
        a4.y = true;
    }

    public void func_110571_b(IResourceManager a2) {
        vw a3;
        int a4 = Minecraft.func_71369_N();
        Stitcher a5 = new Stitcher(a4, a4, 0, a3.o);
        a3.c.clear();
        a3.v.clear();
        int a6 = Integer.MAX_VALUE;
        int a7 = 1 << a3.o;
        ProgressManager.ProgressBar a8 = ProgressManager.push((String)"Texture stitching", (int)a3.m.size());
        a3.ALLATORIxDEMO.clear();
        for (Map.Entry a9 : Maps.newHashMap(a3.m).entrySet()) {
            ResourceLocation a10 = new ResourceLocation((String)a9.getKey());
            a8.step(a10.toString());
            a6 = a3.ALLATORIxDEMO(a5, a2, a10, (TextureAtlasSprite)a9.getValue(), a8, a6, a7);
        }
        a3.ALLATORIxDEMO(a5, a8, a6, a7);
    }

    /*
     * Exception decompiling
     */
    private /* synthetic */ int ALLATORIxDEMO(Stitcher a, IResourceManager a, ResourceLocation a, TextureAtlasSprite a, ProgressManager.ProgressBar a, int a, int a) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private /* synthetic */ void ALLATORIxDEMO(Stitcher a2, ProgressManager.ProgressBar a3, int a4, int a5) {
        vw a6;
        ProgressManager.pop((ProgressManager.ProgressBar)a3);
        int a7 = Math.min(a4, a5);
        int a8 = MathHelper.func_151239_c((int)a7);
        a3 = ProgressManager.push((String)"Texture creation", (int)2);
        a3.step("Stitching");
        a2.func_94305_f();
        a3.step("Allocating GL texture");
        TextureUtil.func_180600_a((int)a6.func_110552_b(), (int)a6.o, (int)a2.func_110935_a(), (int)a2.func_110936_b());
        HashMap a9 = Maps.newHashMap(a6.m);
        ProgressManager.pop((ProgressManager.ProgressBar)a3);
        a3 = ProgressManager.push((String)"Texture mipmap and upload", (int)a2.func_94309_g().size());
        for (TextureAtlasSprite a10 : a2.func_94309_g()) {
            a3.step(a10.func_94215_i());
            String a11 = a10.func_94215_i();
            a9.remove(a11);
            a6.c.put(a11, a10);
            try {
                TextureUtil.func_147955_a((int[][])a10.func_147965_a(0), (int)a10.func_94211_a(), (int)a10.func_94216_b(), (int)a10.func_130010_a(), (int)a10.func_110967_i(), (boolean)false, (boolean)false);
            }
            catch (Throwable a12) {
                CrashReport a13 = CrashReport.func_85055_a((Throwable)a12, (String)"Stitching texture atlas");
                CrashReportCategory a14 = a13.func_85058_a("Texture being stitched together");
                a14.func_71507_a("Atlas path", (Object)a6.q);
                a14.func_71507_a("Sprite", (Object)a10);
                throw new ReportedException(a13);
            }
            if (!a10.func_130098_m()) continue;
            a6.v.add(a10);
        }
        for (TextureAtlasSprite a10 : a9.values()) {
            a10.func_94217_a(Minecraft.func_71410_x().func_147117_R().func_174944_f());
        }
        ProgressManager.pop((ProgressManager.ProgressBar)a3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ boolean ALLATORIxDEMO(IResourceManager a2, TextureAtlasSprite a3) {
        vw a4;
        block10: {
            ResourceLocation a5 = a4.ALLATORIxDEMO(a3);
            IResource a6 = null;
            if (!a3.hasCustomLoader(a2, a5)) {
                boolean a7;
                try {
                    a6 = a2.func_110536_a(a5);
                    a3.func_188539_a(a6, a4.o + 1);
                    break block10;
                }
                catch (RuntimeException a8) {
                    t.error("Unable to parse metadata from {}", (Object)a5, (Object)a8);
                    a7 = false;
                }
                catch (IOException a9) {
                    boolean a10;
                    t.error("Using missing texture, unable to load {}", (Object)a5, (Object)a9);
                    boolean bl2 = a10 = false;
                    return bl2;
                }
                finally {
                    IOUtils.closeQuietly((Closeable)a6);
                }
                return a7;
            }
        }
        try {
            a3.func_147963_d(a4.o);
            return true;
        }
        catch (Throwable a11) {
            CrashReport a12 = CrashReport.func_85055_a((Throwable)a11, (String)"Applying mipmap");
            CrashReportCategory a13 = a12.func_85058_a("Sprite being mipmapped");
            a13.func_189529_a("Sprite name", (ICrashReportDetail)new qo(a4, a3));
            a13.func_189529_a("Sprite size", (ICrashReportDetail)new uu(a4, a3));
            a13.func_189529_a("Sprite frames", (ICrashReportDetail)new cr(a4, a3));
            a13.func_71507_a("Mipmap levels", (Object)a4.o);
            throw new ReportedException(a12);
        }
    }

    private /* synthetic */ ResourceLocation ALLATORIxDEMO(TextureAtlasSprite a2) {
        ResourceLocation a3 = new ResourceLocation(a2.func_94215_i());
        return new ResourceLocation(a3.func_110624_b(), String.format("%s%s", a3.func_110623_a(), ".png"));
    }

    public TextureAtlasSprite func_110572_b(String a2) {
        vw a3;
        TextureAtlasSprite a4 = a3.c.get(a2);
        if (a4 == null) {
            return Minecraft.func_71410_x().func_147117_R().func_174944_f();
        }
        return a4;
    }

    public void func_94248_c() {
        vw a2;
        GlStateManager.func_179144_i((int)a2.func_110552_b());
        for (TextureAtlasSprite a3 : a2.v) {
            a3.func_94219_l();
        }
    }

    public TextureAtlasSprite func_174942_a(ResourceLocation a2) {
        vw a3;
        if (a2 == null) {
            throw new IllegalArgumentException("Location cannot be null!");
        }
        TextureAtlasSprite a4 = a3.m.get(a2.toString());
        if (a4 == null) {
            jt a5 = new jt(a2.toString());
            a3.m.put(a2.toString(), a5);
            a3.ALLATORIxDEMO(a2, a5);
            return a5;
        }
        return a4;
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a2, jt a3) {
        if (a2.func_110623_a().endsWith("_e")) {
            return;
        }
        ResourceLocation a4 = new ResourceLocation(a2.func_110624_b(), a2.func_110623_a() + "_e");
        ResourceLocation a5 = new ResourceLocation(a2.func_110624_b(), a2.func_110623_a() + "_e.png");
        try {
            vw a6;
            Minecraft.func_71410_x().func_110442_L().func_110536_a(a5);
            jt a7 = (jt)a6.func_174942_a(a4);
            a7.k = true;
            a3.ALLATORIxDEMO = a7;
        }
        catch (Exception a7) {
            // empty catch block
        }
    }

    public void func_110550_d() {
        vw a2;
        a2.func_94248_c();
    }

    public void func_147633_a(int a2) {
        a.o = a2;
    }

    @Nullable
    public TextureAtlasSprite getTextureExtry(String a2) {
        vw a3;
        return a3.m.get(a2);
    }

    public boolean setTextureEntry(TextureAtlasSprite a2) {
        vw a3;
        String a4 = a2.func_94215_i();
        if (!a3.m.containsKey(a4)) {
            a3.m.put(a4, a2);
            return true;
        }
        return false;
    }

    public String getBasePath() {
        vw a2;
        return a2.q;
    }

    public int getMipmapLevels() {
        vw a2;
        return a2.o;
    }

    private /* synthetic */ TextureAtlasSprite ALLATORIxDEMO(ResourceLocation a2) {
        vw a3;
        return a3.m.get(a2.toString());
    }
}

