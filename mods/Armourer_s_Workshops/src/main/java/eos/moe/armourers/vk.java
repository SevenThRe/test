/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class vk {
    public static float oa;
    public static int za;
    public static String ra;
    public static double na;
    public static int o;
    public static boolean y;
    public static int x;
    public static boolean i;
    public static String k;
    public static boolean p;
    public static int n;
    public static int q;
    public static int f;
    public static boolean u;
    public static boolean d;
    public static int h;
    public static boolean a;
    public static int e;
    public static String b;
    public static boolean g;
    public static AtomicInteger z;
    public static int t;
    public static String w;
    public static Configuration r;
    public static int l;
    public static int c;
    public static float v;
    public static float s;
    public static String m;
    public static int j;

    private static /* synthetic */ void s() {
        r.setCategoryComment(w, "Setting to configure the skin preview box.");
        u = r.getBoolean("skinPreEnabled", w, true, "Enables a larger skin preview box when hovering the mouse over a skin.");
        a = r.getBoolean("skinPreDrawBackground", w, true, "Draw a background box for the skin preview.");
        s = r.getFloat("skinPreSize", w, 96.0f, 16.0f, 256.0f, "Size of the skin preview.");
        oa = r.getFloat("skinPreLocHorizontal", w, 0.0f, 0.0f, 1.0f, "Horizontal location of the skin preview: 0 = left, 1 = right.");
        v = r.getFloat("skinPreLocVertical", w, 0.5f, 0.0f, 1.0f, "Vertical location of the skin preview: 0 = top, 1 = bottom.");
        y = r.getBoolean("skinPreLocFollowMouse", w, true, "Skin preview will be rendered next to the mouse.");
    }

    private static /* synthetic */ void x() {
        r.setCategoryComment(m, "Change (memory use/IO access) ratio by category setting in this category.");
        j = r.getInt("skinCacheExpireTime", m, 600, 0, 3600, "How long in seconds the client will keep skins in it's cache.\nDefault 600 seconds is 10 minutes.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("skinCacheExpireTime").setRequiresMcRestart(true);
        o = r.getInt("skinCacheMaxSize", m, 2000, 0, 10000, "Max size the skin cache can reach before skins are removed.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("skinCacheMaxSize").setRequiresMcRestart(true);
        za = r.getInt("modelPartCacheExpireTime", m, 600, 0, 3600, "How long in seconds the client will keep model parts in it's cache.\nDefault 600 seconds is 10 minutes.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("modelPartCacheExpireTime").setRequiresMcRestart(true);
        f = r.getInt("modelPartCacheMaxSize", m, 2000, 0, 10000, "Max size the cache can reach before model parts are removed.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("modelPartCacheMaxSize").setRequiresMcRestart(true);
        x = r.getInt("textureCacheExpireTime", m, 600, 0, 3600, "How long in seconds the client will keep textures in it's cache.\nDefault 600 seconds is 10 minutes.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("textureCacheExpireTime").setRequiresMcRestart(true);
        e = r.getInt("textureCacheMaxSize", m, 1000, 0, 5000, "Max size the texture cache can reach before textures are removed.\nSetting to 0 turns off this option.");
        r.getCategory(m).get("textureCacheMaxSize").setRequiresMcRestart(true);
        h = r.getInt("maxSkinRequests", m, 10, 1, 50, "Maximum number of skin the client can request at one time.");
    }

    public static void r(File a2) {
        if (r == null) {
            r = new Configuration(a2, "1");
            vk.h();
        }
    }

    public static void h() {
        vk.y();
        vk.r();
        vk.x();
        vk.s();
        vk.z();
        if (r.hasChanged()) {
            r.save();
        }
    }

    private static /* synthetic */ void z() {
        r.setCategoryComment(b, "Debug settings.");
        t = r.getInt("texturePaintingType", b, 0, -1, 2, "Texture painting replacing the players texture with a painted version.\nTurning this off may fix issues with the players texture rendering\nincorrectly or showing the steve skin.\n\n-1 = disabled\n0 = auto\n1 = texture_replace (replaces the players texture - LEGACY)\n2 = model_replace_mc (render using a mc model - slower, more compatible - NOT IMPLEMENTED)\n3 = model_replace_aw (render using a aw model - faster, less compatible)\n");
    }

    private static /* synthetic */ void y() {
        r.setCategoryComment(k, "Miscellaneous settings.");
        i = r.getBoolean("showToolTip", "dragon", true, "show Tool Tip?");
    }

    private static /* synthetic */ void r() {
        r.setCategoryComment(ra, "Change (visual quality/performance) ratio by category setting in this category.");
        n = r.getInt("renderDistanceSkin", ra, 128, 16, 512, "The max distance in blocks that skins will render.");
        l = r.getInt("renderDistanceMannequinEquipment", ra, 64, 16, 512, "The max distance in blocks that equipment will be rendered on mannequins.");
        int n2 = MathHelper.ceil((float)((float)Runtime.getRuntime().availableProcessors() / 2.0f));
        n2 = MathHelper.clamp((int)n2, (int)1, (int)16);
        q = r.getInt("modelBakingThreadCount", ra, n2, 1, 16, "");
        r.getCategory(ra).get("modelBakingThreadCount").setComment("The maximum number of threads that will be used to bake models. [range: 1 ~ 16, default: core count / 2]");
        r.getCategory(ra).get("modelBakingThreadCount").setRequiresMcRestart(true);
        n2 = r.getInt("modelBakingUpdateRate", ra, 40, 10, 1000, "How fast models are allowed to bake. Lower values will give smoother frame rate but models will load slower.\nHas no effect if 'slowModelBaking' is turned off.");
        z.set(n2);
        g = r.getBoolean("multipassSkinRendering", ra, true, "When enabled skin will render in multiple passes to reduce visual artifacts.\nDisabling this will improve skin rendering performance at the cost of visual quality.");
        na = r.getFloat("lodDistance", ra, 32.0f, 8.0f, 128.0f, "Distance away that skins will have lod applied to them.");
        c = r.getInt("maxLodLevels", ra, 4, 0, 4, "Number of LOD models to create. Higher number should give a boost to framerate at a small cost to VRAM.");
    }

    public vk() {
        vk a2;
    }

    static {
        k = "misc";
        ra = "performance";
        m = "cache";
        w = "skin-preview";
        b = "debug";
        z = new AtomicInteger(40);
        na = 32.0;
        g = true;
        d = false;
        c = 4;
        u = false;
        a = true;
        s = 96.0f;
        oa = 1.0f;
        v = 0.5f;
        y = false;
    }
}

