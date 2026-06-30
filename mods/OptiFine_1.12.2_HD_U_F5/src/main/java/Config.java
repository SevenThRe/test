/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bia
 *  bib
 *  bid
 *  bie
 *  bit
 *  blk
 *  bus
 *  buy
 *  cdg
 *  cdp
 *  cdr
 *  ceg
 *  ceo
 *  cep
 *  cer
 *  ceu
 *  ceu$a
 *  cey
 *  cgc
 *  cii
 *  et
 *  hh
 *  ho
 *  nf
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.Sys
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL30
 *  org.lwjgl.opengl.GLContext
 *  org.lwjgl.opengl.PixelFormat
 *  rb
 *  rk
 */
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.optifine.DynamicLights;
import net.optifine.GlErrors;
import net.optifine.VersionCheckThread;
import net.optifine.config.GlVersion;
import net.optifine.gui.GuiMessage;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;
import net.optifine.util.DisplayModeComparator;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

public class Config {
    public static final String OF_NAME = "OptiFine";
    public static final String MC_VERSION = "1.12.2";
    public static final String OF_EDITION = "HD_U";
    public static final String OF_RELEASE = "F5";
    public static final String VERSION = "OptiFine_1.12.2_HD_U_F5";
    private static String build = null;
    private static String newRelease = null;
    private static boolean notify64BitJava = false;
    public static String openGlVersion = null;
    public static String openGlRenderer = null;
    public static String openGlVendor = null;
    public static String[] openGlExtensions = null;
    public static GlVersion glVersion = null;
    public static GlVersion glslVersion = null;
    public static int minecraftVersionInt = -1;
    public static boolean fancyFogAvailable = false;
    public static boolean occlusionAvailable = false;
    private static bid gameSettings = null;
    private static bib minecraft = bib.z();
    private static boolean initialized = false;
    private static Thread minecraftThread = null;
    private static DisplayMode desktopDisplayMode = null;
    private static DisplayMode[] displayModes = null;
    private static int antialiasingLevel = 0;
    private static int availableProcessors = 0;
    public static boolean zoomMode = false;
    private static int texturePackClouds = 0;
    public static boolean waterOpacityChanged = false;
    private static boolean fullscreenModeChecked = false;
    private static boolean desktopModeChecked = false;
    private static ceg defaultResourcePackLazy = null;
    public static final Float DEF_ALPHA_FUNC_LEVEL = Float.valueOf(0.1f);
    private static final Logger LOGGER = LogManager.getLogger();
    public static final boolean logDetail = System.getProperty("log.detail", "false").equals("true");
    private static String mcDebugLast = null;
    private static int fpsMinLast = 0;

    private Config() {
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getVersionDebug() {
        StringBuffer sb = new StringBuffer(32);
        if (Config.isDynamicLights()) {
            sb.append("DL: ");
            sb.append(String.valueOf(DynamicLights.getCount()));
            sb.append(", ");
        }
        sb.append(VERSION);
        String shaderPack = Shaders.getShaderPackName();
        if (shaderPack != null) {
            sb.append(", ");
            sb.append(shaderPack);
        }
        return sb.toString();
    }

    public static void initGameSettings(bid settings) {
        if (gameSettings != null) {
            return;
        }
        gameSettings = settings;
        desktopDisplayMode = Display.getDesktopDisplayMode();
        Config.updateAvailableProcessors();
        ReflectorForge.putLaunchBlackboard("optifine.ForgeSplashCompatible", Boolean.TRUE);
    }

    public static void initDisplay() {
        Config.checkInitialized();
        antialiasingLevel = Config.gameSettings.ofAaLevel;
        Config.checkDisplaySettings();
        Config.checkDisplayMode();
        minecraftThread = Thread.currentThread();
        Config.updateThreadPriorities();
        Shaders.startup(bib.z());
    }

    public static void checkInitialized() {
        if (initialized) {
            return;
        }
        if (!Display.isCreated()) {
            return;
        }
        initialized = true;
        Config.checkOpenGlCaps();
        Config.startVersionCheckThread();
    }

    private static void checkOpenGlCaps() {
        Config.log("");
        Config.log(Config.getVersion());
        Config.log("Build: " + Config.getBuild());
        Config.log("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
        Config.log("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
        Config.log("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
        Config.log("LWJGL: " + Sys.getVersion());
        openGlVersion = GL11.glGetString((int)7938);
        openGlRenderer = GL11.glGetString((int)7937);
        openGlVendor = GL11.glGetString((int)7936);
        Config.log("OpenGL: " + openGlRenderer + ", version " + openGlVersion + ", " + openGlVendor);
        Config.log("OpenGL Version: " + Config.getOpenGlVersionString());
        if (!GLContext.getCapabilities().OpenGL12) {
            Config.log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
        }
        if (!(fancyFogAvailable = GLContext.getCapabilities().GL_NV_fog_distance)) {
            Config.log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
        }
        if (!(occlusionAvailable = GLContext.getCapabilities().GL_ARB_occlusion_query)) {
            Config.log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
        }
        int maxTexSize = TextureUtils.getGLMaximumTextureSize();
        Config.dbg("Maximum texture size: " + maxTexSize + "x" + maxTexSize);
    }

    public static String getBuild() {
        if (build == null) {
            try {
                InputStream in = Config.class.getResourceAsStream("/buildof.txt");
                if (in == null) {
                    return null;
                }
                build = Config.readLines(in)[0];
            }
            catch (Exception e) {
                Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
                build = "";
            }
        }
        return build;
    }

    public static boolean isFancyFogAvailable() {
        return fancyFogAvailable;
    }

    public static boolean isOcclusionAvailable() {
        return occlusionAvailable;
    }

    public static int getMinecraftVersionInt() {
        if (minecraftVersionInt < 0) {
            String[] verStrs = Config.tokenize(MC_VERSION, ".");
            int ver = 0;
            if (verStrs.length > 0) {
                ver += 10000 * Config.parseInt(verStrs[0], 0);
            }
            if (verStrs.length > 1) {
                ver += 100 * Config.parseInt(verStrs[1], 0);
            }
            if (verStrs.length > 2) {
                ver += 1 * Config.parseInt(verStrs[2], 0);
            }
            minecraftVersionInt = ver;
        }
        return minecraftVersionInt;
    }

    public static String getOpenGlVersionString() {
        GlVersion ver = Config.getGlVersion();
        String verStr = "" + ver.getMajor() + "." + ver.getMinor() + "." + ver.getRelease();
        return verStr;
    }

    private static GlVersion getGlVersionLwjgl() {
        if (GLContext.getCapabilities().OpenGL44) {
            return new GlVersion(4, 4);
        }
        if (GLContext.getCapabilities().OpenGL43) {
            return new GlVersion(4, 3);
        }
        if (GLContext.getCapabilities().OpenGL42) {
            return new GlVersion(4, 2);
        }
        if (GLContext.getCapabilities().OpenGL41) {
            return new GlVersion(4, 1);
        }
        if (GLContext.getCapabilities().OpenGL40) {
            return new GlVersion(4, 0);
        }
        if (GLContext.getCapabilities().OpenGL33) {
            return new GlVersion(3, 3);
        }
        if (GLContext.getCapabilities().OpenGL32) {
            return new GlVersion(3, 2);
        }
        if (GLContext.getCapabilities().OpenGL31) {
            return new GlVersion(3, 1);
        }
        if (GLContext.getCapabilities().OpenGL30) {
            return new GlVersion(3, 0);
        }
        if (GLContext.getCapabilities().OpenGL21) {
            return new GlVersion(2, 1);
        }
        if (GLContext.getCapabilities().OpenGL20) {
            return new GlVersion(2, 0);
        }
        if (GLContext.getCapabilities().OpenGL15) {
            return new GlVersion(1, 5);
        }
        if (GLContext.getCapabilities().OpenGL14) {
            return new GlVersion(1, 4);
        }
        if (GLContext.getCapabilities().OpenGL13) {
            return new GlVersion(1, 3);
        }
        if (GLContext.getCapabilities().OpenGL12) {
            return new GlVersion(1, 2);
        }
        if (GLContext.getCapabilities().OpenGL11) {
            return new GlVersion(1, 1);
        }
        return new GlVersion(1, 0);
    }

    public static GlVersion getGlVersion() {
        if (glVersion == null) {
            String verStr = GL11.glGetString((int)7938);
            glVersion = Config.parseGlVersion(verStr, null);
            if (glVersion == null) {
                glVersion = Config.getGlVersionLwjgl();
            }
            if (glVersion == null) {
                glVersion = new GlVersion(1, 0);
            }
        }
        return glVersion;
    }

    public static GlVersion getGlslVersion() {
        String verStr;
        if (glslVersion == null && (glslVersion = Config.parseGlVersion(verStr = GL11.glGetString((int)35724), null)) == null) {
            glslVersion = new GlVersion(1, 10);
        }
        return glslVersion;
    }

    public static GlVersion parseGlVersion(String versionString, GlVersion def) {
        try {
            if (versionString == null) {
                return def;
            }
            Pattern REGEXP_VERSION = Pattern.compile("([0-9]+)\\.([0-9]+)(\\.([0-9]+))?(.+)?");
            Matcher matcher = REGEXP_VERSION.matcher(versionString);
            if (!matcher.matches()) {
                return def;
            }
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int release = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
            String suffix = matcher.group(5);
            return new GlVersion(major, minor, release, suffix);
        }
        catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }

    public static String[] getOpenGlExtensions() {
        if (openGlExtensions == null) {
            openGlExtensions = Config.detectOpenGlExtensions();
        }
        return openGlExtensions;
    }

    private static String[] detectOpenGlExtensions() {
        try {
            int countExt;
            GlVersion ver = Config.getGlVersion();
            if (ver.getMajor() >= 3 && (countExt = GL11.glGetInteger((int)33309)) > 0) {
                String[] exts = new String[countExt];
                for (int i = 0; i < countExt; ++i) {
                    exts[i] = GL30.glGetStringi((int)7939, (int)i);
                }
                return exts;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String extStr = GL11.glGetString((int)7939);
            String[] exts = extStr.split(" ");
            return exts;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static void updateThreadPriorities() {
        Config.updateAvailableProcessors();
        int ELEVATED_PRIORITY = 8;
        if (Config.isSingleProcessor()) {
            if (Config.isSmoothWorld()) {
                minecraftThread.setPriority(10);
                Config.setThreadPriority("Server thread", 1);
            } else {
                minecraftThread.setPriority(5);
                Config.setThreadPriority("Server thread", 5);
            }
        } else {
            minecraftThread.setPriority(10);
            Config.setThreadPriority("Server thread", 5);
        }
    }

    private static void setThreadPriority(String prefix, int priority) {
        try {
            ThreadGroup tg = Thread.currentThread().getThreadGroup();
            if (tg == null) {
                return;
            }
            int num = (tg.activeCount() + 10) * 2;
            Thread[] ts = new Thread[num];
            tg.enumerate(ts, false);
            for (int i = 0; i < ts.length; ++i) {
                Thread t = ts[i];
                if (t == null || !t.getName().startsWith(prefix)) continue;
                t.setPriority(priority);
            }
        }
        catch (Throwable e) {
            Config.warn(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static boolean isMinecraftThread() {
        return Thread.currentThread() == minecraftThread;
    }

    private static void startVersionCheckThread() {
        VersionCheckThread vct = new VersionCheckThread();
        vct.start();
    }

    public static boolean isMipmaps() {
        return Config.gameSettings.K > 0;
    }

    public static int getMipmapLevels() {
        return Config.gameSettings.K;
    }

    public static int getMipmapType() {
        switch (Config.gameSettings.ofMipmapType) {
            case 0: {
                return 9986;
            }
            case 1: {
                return 9986;
            }
            case 2: {
                if (Config.isMultiTexture()) {
                    return 9985;
                }
                return 9986;
            }
            case 3: {
                if (Config.isMultiTexture()) {
                    return 9987;
                }
                return 9986;
            }
        }
        return 9986;
    }

    public static boolean isUseAlphaFunc() {
        float alphaFuncLevel = Config.getAlphaFuncLevel();
        return alphaFuncLevel > DEF_ALPHA_FUNC_LEVEL.floatValue() + 1.0E-5f;
    }

    public static float getAlphaFuncLevel() {
        return DEF_ALPHA_FUNC_LEVEL.floatValue();
    }

    public static boolean isFogFancy() {
        if (!Config.isFancyFogAvailable()) {
            return false;
        }
        return Config.gameSettings.ofFogType == 2;
    }

    public static boolean isFogFast() {
        return Config.gameSettings.ofFogType == 1;
    }

    public static boolean isFogOff() {
        return Config.gameSettings.ofFogType == 3;
    }

    public static boolean isFogOn() {
        return Config.gameSettings.ofFogType != 3;
    }

    public static float getFogStart() {
        return Config.gameSettings.ofFogStart;
    }

    public static void detail(String s) {
        if (logDetail) {
            LOGGER.info("[OptiFine] " + s);
        }
    }

    public static void dbg(String s) {
        LOGGER.info("[OptiFine] " + s);
    }

    public static void warn(String s) {
        LOGGER.warn("[OptiFine] " + s);
    }

    public static void error(String s) {
        LOGGER.error("[OptiFine] " + s);
    }

    public static void log(String s) {
        Config.dbg(s);
    }

    public static int getUpdatesPerFrame() {
        return Config.gameSettings.ofChunkUpdates;
    }

    public static boolean isDynamicUpdates() {
        return Config.gameSettings.ofChunkUpdatesDynamic;
    }

    public static boolean isRainFancy() {
        if (Config.gameSettings.ofRain == 0) {
            return Config.gameSettings.k;
        }
        return Config.gameSettings.ofRain == 2;
    }

    public static boolean isRainOff() {
        return Config.gameSettings.ofRain == 3;
    }

    public static boolean isCloudsFancy() {
        if (Config.gameSettings.ofClouds != 0) {
            return Config.gameSettings.ofClouds == 2;
        }
        if (Config.isShaders() && !Shaders.shaderPackClouds.isDefault()) {
            return Shaders.shaderPackClouds.isFancy();
        }
        if (texturePackClouds != 0) {
            return texturePackClouds == 2;
        }
        return Config.gameSettings.k;
    }

    public static boolean isCloudsOff() {
        if (Config.gameSettings.ofClouds != 0) {
            return Config.gameSettings.ofClouds == 3;
        }
        if (Config.isShaders() && !Shaders.shaderPackClouds.isDefault()) {
            return Shaders.shaderPackClouds.isOff();
        }
        if (texturePackClouds != 0) {
            return texturePackClouds == 3;
        }
        return false;
    }

    public static void updateTexturePackClouds() {
        texturePackClouds = 0;
        cep rm = Config.getResourceManager();
        if (rm == null) {
            return;
        }
        try {
            InputStream in = rm.a(new nf("mcpatcher/color.properties")).b();
            if (in == null) {
                return;
            }
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            String cloudStr = props.getProperty("clouds");
            if (cloudStr == null) {
                return;
            }
            Config.dbg("Texture pack clouds: " + cloudStr);
            cloudStr = cloudStr.toLowerCase();
            if (cloudStr.equals("fast")) {
                texturePackClouds = 1;
            }
            if (cloudStr.equals("fancy")) {
                texturePackClouds = 2;
            }
            if (cloudStr.equals("off")) {
                texturePackClouds = 3;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static cgc getModelManager() {
        return Config.minecraft.ad().modelManager;
    }

    public static boolean isTreesFancy() {
        if (Config.gameSettings.ofTrees == 0) {
            return Config.gameSettings.k;
        }
        return Config.gameSettings.ofTrees != 1;
    }

    public static boolean isTreesSmart() {
        return Config.gameSettings.ofTrees == 4;
    }

    public static boolean isCullFacesLeaves() {
        if (Config.gameSettings.ofTrees == 0) {
            return !Config.gameSettings.k;
        }
        return Config.gameSettings.ofTrees == 4;
    }

    public static boolean isDroppedItemsFancy() {
        if (Config.gameSettings.ofDroppedItems == 0) {
            return Config.gameSettings.k;
        }
        return Config.gameSettings.ofDroppedItems == 2;
    }

    public static int limit(int val, int min, int max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public static float limit(float val, float min, float max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public static double limit(double val, double min, double max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public static float limitTo1(float val) {
        if (val < 0.0f) {
            return 0.0f;
        }
        if (val > 1.0f) {
            return 1.0f;
        }
        return val;
    }

    public static boolean isAnimatedWater() {
        return Config.gameSettings.ofAnimatedWater != 2;
    }

    public static boolean isGeneratedWater() {
        return Config.gameSettings.ofAnimatedWater == 1;
    }

    public static boolean isAnimatedPortal() {
        return Config.gameSettings.ofAnimatedPortal;
    }

    public static boolean isAnimatedLava() {
        return Config.gameSettings.ofAnimatedLava != 2;
    }

    public static boolean isGeneratedLava() {
        return Config.gameSettings.ofAnimatedLava == 1;
    }

    public static boolean isAnimatedFire() {
        return Config.gameSettings.ofAnimatedFire;
    }

    public static boolean isAnimatedRedstone() {
        return Config.gameSettings.ofAnimatedRedstone;
    }

    public static boolean isAnimatedExplosion() {
        return Config.gameSettings.ofAnimatedExplosion;
    }

    public static boolean isAnimatedFlame() {
        return Config.gameSettings.ofAnimatedFlame;
    }

    public static boolean isAnimatedSmoke() {
        return Config.gameSettings.ofAnimatedSmoke;
    }

    public static boolean isVoidParticles() {
        return Config.gameSettings.ofVoidParticles;
    }

    public static boolean isWaterParticles() {
        return Config.gameSettings.ofWaterParticles;
    }

    public static boolean isRainSplash() {
        return Config.gameSettings.ofRainSplash;
    }

    public static boolean isPortalParticles() {
        return Config.gameSettings.ofPortalParticles;
    }

    public static boolean isPotionParticles() {
        return Config.gameSettings.ofPotionParticles;
    }

    public static boolean isFireworkParticles() {
        return Config.gameSettings.ofFireworkParticles;
    }

    public static float getAmbientOcclusionLevel() {
        if (Config.isShaders() && Shaders.aoLevel >= 0.0f) {
            return Shaders.aoLevel;
        }
        return Config.gameSettings.ofAoLevel;
    }

    public static String listToString(List list) {
        return Config.listToString(list, ", ");
    }

    public static String listToString(List list, String separator) {
        if (list == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer(list.size() * 5);
        for (int i = 0; i < list.size(); ++i) {
            Object obj = list.get(i);
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(String.valueOf(obj));
        }
        return buf.toString();
    }

    public static String arrayToString(Object[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(Object[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            Object obj = arr[i];
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(String.valueOf(obj));
        }
        return buf.toString();
    }

    public static String arrayToString(int[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(int[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            int x = arr[i];
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(String.valueOf(x));
        }
        return buf.toString();
    }

    public static String arrayToString(float[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(float[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            float x = arr[i];
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(String.valueOf(x));
        }
        return buf.toString();
    }

    public static bib getMinecraft() {
        return minecraft;
    }

    public static cdr getTextureManager() {
        return minecraft.N();
    }

    public static cep getResourceManager() {
        return minecraft.O();
    }

    public static InputStream getResourceStream(nf location) throws IOException {
        return Config.getResourceStream(minecraft.O(), location);
    }

    public static InputStream getResourceStream(cep resourceManager, nf location) throws IOException {
        ceo res = resourceManager.a(location);
        if (res == null) {
            return null;
        }
        return res.b();
    }

    public static ceo getResource(nf location) throws IOException {
        return minecraft.O().a(location);
    }

    public static boolean hasResource(nf location) {
        if (location == null) {
            return false;
        }
        cer rp = Config.getDefiningResourcePack(location);
        return rp != null;
    }

    public static boolean hasResource(cep resourceManager, nf location) {
        try {
            ceo res = resourceManager.a(location);
            return res != null;
        }
        catch (IOException e) {
            return false;
        }
    }

    public static cer[] getResourcePacks() {
        ceu rep = minecraft.P();
        List entries = rep.e();
        ArrayList<cer> list = new ArrayList<cer>();
        for (ceu.a entry : entries) {
            list.add(entry.c());
        }
        if (rep.g() != null) {
            list.add(rep.g());
        }
        cer[] rps = list.toArray(new cer[list.size()]);
        return rps;
    }

    public static String getResourcePackNames() {
        if (minecraft.P() == null) {
            return "";
        }
        cer[] rps = Config.getResourcePacks();
        if (rps.length <= 0) {
            return Config.getDefaultResourcePack().b();
        }
        Object[] names = new String[rps.length];
        for (int i = 0; i < rps.length; ++i) {
            names[i] = rps[i].b();
        }
        String nameStr = Config.arrayToString(names);
        return nameStr;
    }

    public static ceg getDefaultResourcePack() {
        ceu repository;
        bib mc;
        if (defaultResourcePackLazy == null && (defaultResourcePackLazy = (ceg)Reflector.getFieldValue(mc = bib.z(), Reflector.Minecraft_defaultResourcePack)) == null && (repository = mc.P()) != null) {
            defaultResourcePackLazy = (ceg)repository.a;
        }
        return defaultResourcePackLazy;
    }

    public static boolean isFromDefaultResourcePack(nf loc) {
        cer rp = Config.getDefiningResourcePack(loc);
        return rp == Config.getDefaultResourcePack();
    }

    public static cer getDefiningResourcePack(nf location) {
        ceu rep = minecraft.P();
        cer serverRp = rep.g();
        if (serverRp != null && serverRp.b(location)) {
            return serverRp;
        }
        List entries = rep.m;
        for (int i = entries.size() - 1; i >= 0; --i) {
            ceu.a entry = (ceu.a)entries.get(i);
            cer rp = entry.c();
            if (!rp.b(location)) continue;
            return rp;
        }
        if (Config.getDefaultResourcePack().b(location)) {
            return Config.getDefaultResourcePack();
        }
        return null;
    }

    public static buy getRenderGlobal() {
        return Config.minecraft.g;
    }

    public static boolean isBetterGrass() {
        return Config.gameSettings.ofBetterGrass != 3;
    }

    public static boolean isBetterGrassFancy() {
        return Config.gameSettings.ofBetterGrass == 2;
    }

    public static boolean isWeatherEnabled() {
        return Config.gameSettings.ofWeather;
    }

    public static boolean isSkyEnabled() {
        return Config.gameSettings.ofSky;
    }

    public static boolean isSunMoonEnabled() {
        return Config.gameSettings.ofSunMoon;
    }

    public static boolean isSunTexture() {
        if (!Config.isSunMoonEnabled()) {
            return false;
        }
        return !Config.isShaders() || Shaders.isSun();
    }

    public static boolean isMoonTexture() {
        if (!Config.isSunMoonEnabled()) {
            return false;
        }
        return !Config.isShaders() || Shaders.isMoon();
    }

    public static boolean isVignetteEnabled() {
        if (Config.isShaders() && !Shaders.isVignette()) {
            return false;
        }
        if (Config.gameSettings.ofVignette == 0) {
            return Config.gameSettings.k;
        }
        return Config.gameSettings.ofVignette == 2;
    }

    public static boolean isStarsEnabled() {
        return Config.gameSettings.ofStars;
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTimeDayOnly() {
        return Config.gameSettings.ofTime == 1;
    }

    public static boolean isTimeDefault() {
        return Config.gameSettings.ofTime == 0;
    }

    public static boolean isTimeNightOnly() {
        return Config.gameSettings.ofTime == 2;
    }

    public static boolean isClearWater() {
        return Config.gameSettings.ofClearWater;
    }

    public static int getAnisotropicFilterLevel() {
        return Config.gameSettings.ofAfLevel;
    }

    public static boolean isAnisotropicFiltering() {
        return Config.getAnisotropicFilterLevel() > 1;
    }

    public static int getAntialiasingLevel() {
        return antialiasingLevel;
    }

    public static boolean isAntialiasing() {
        return Config.getAntialiasingLevel() > 0;
    }

    public static boolean isAntialiasingConfigured() {
        return Config.getGameSettings().ofAaLevel > 0;
    }

    public static boolean isMultiTexture() {
        if (Config.getAnisotropicFilterLevel() > 1) {
            return true;
        }
        return Config.getAntialiasingLevel() > 0;
    }

    public static boolean between(int val, int min, int max) {
        return val >= min && val <= max;
    }

    public static boolean between(float val, float min, float max) {
        return val >= min && val <= max;
    }

    public static boolean isDrippingWaterLava() {
        return Config.gameSettings.ofDrippingWaterLava;
    }

    public static boolean isBetterSnow() {
        return Config.gameSettings.ofBetterSnow;
    }

    public static Dimension getFullscreenDimension() {
        if (desktopDisplayMode == null) {
            return null;
        }
        if (gameSettings == null) {
            return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
        }
        String dimStr = Config.gameSettings.ofFullscreenMode;
        if (dimStr.equals("Default")) {
            return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
        }
        String[] dimStrs = Config.tokenize(dimStr, " x");
        if (dimStrs.length < 2) {
            return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
        }
        return new Dimension(Config.parseInt(dimStrs[0], -1), Config.parseInt(dimStrs[1], -1));
    }

    public static int parseInt(String str, int defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static float parseFloat(String str, float defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Float.parseFloat(str);
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static boolean parseBoolean(String str, boolean defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Boolean.parseBoolean(str);
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static Boolean parseBoolean(String str, Boolean defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            if ((str = str.trim().toLowerCase()).equals("true")) {
                return Boolean.TRUE;
            }
            if (str.equals("false")) {
                return Boolean.FALSE;
            }
            return defVal;
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static String[] tokenize(String str, String delim) {
        StringTokenizer tok = new StringTokenizer(str, delim);
        ArrayList<String> list = new ArrayList<String>();
        while (tok.hasMoreTokens()) {
            String token = tok.nextToken();
            list.add(token);
        }
        String[] strs = list.toArray(new String[list.size()]);
        return strs;
    }

    public static DisplayMode getDesktopDisplayMode() {
        return desktopDisplayMode;
    }

    public static DisplayMode[] getDisplayModes() {
        if (displayModes == null) {
            try {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                Set<Dimension> setDimensions = Config.getDisplayModeDimensions(modes);
                ArrayList<DisplayMode> list = new ArrayList<DisplayMode>();
                for (Dimension dim : setDimensions) {
                    DisplayMode[] dimModes = Config.getDisplayModes(modes, dim);
                    DisplayMode dm = Config.getDisplayMode(dimModes, desktopDisplayMode);
                    if (dm == null) continue;
                    list.add(dm);
                }
                DisplayMode[] fsModes = list.toArray(new DisplayMode[list.size()]);
                Arrays.sort(fsModes, new DisplayModeComparator());
                return fsModes;
            }
            catch (Exception e) {
                e.printStackTrace();
                displayModes = new DisplayMode[]{desktopDisplayMode};
            }
        }
        return displayModes;
    }

    public static DisplayMode getLargestDisplayMode() {
        DisplayMode[] modes = Config.getDisplayModes();
        if (modes == null || modes.length < 1) {
            return desktopDisplayMode;
        }
        DisplayMode mode = modes[modes.length - 1];
        if (desktopDisplayMode.getWidth() > mode.getWidth()) {
            return desktopDisplayMode;
        }
        if (desktopDisplayMode.getWidth() == mode.getWidth() && desktopDisplayMode.getHeight() > mode.getHeight()) {
            return desktopDisplayMode;
        }
        return mode;
    }

    private static Set<Dimension> getDisplayModeDimensions(DisplayMode[] modes) {
        HashSet<Dimension> set = new HashSet<Dimension>();
        for (int i = 0; i < modes.length; ++i) {
            DisplayMode mode = modes[i];
            Dimension dim = new Dimension(mode.getWidth(), mode.getHeight());
            set.add(dim);
        }
        return set;
    }

    private static DisplayMode[] getDisplayModes(DisplayMode[] modes, Dimension dim) {
        ArrayList<DisplayMode> list = new ArrayList<DisplayMode>();
        for (int i = 0; i < modes.length; ++i) {
            DisplayMode mode = modes[i];
            if ((double)mode.getWidth() != dim.getWidth() || (double)mode.getHeight() != dim.getHeight()) continue;
            list.add(mode);
        }
        DisplayMode[] dimModes = list.toArray(new DisplayMode[list.size()]);
        return dimModes;
    }

    private static DisplayMode getDisplayMode(DisplayMode[] modes, DisplayMode desktopMode) {
        if (desktopMode != null) {
            for (int i = 0; i < modes.length; ++i) {
                DisplayMode mode = modes[i];
                if (mode.getBitsPerPixel() != desktopMode.getBitsPerPixel() || mode.getFrequency() != desktopMode.getFrequency()) continue;
                return mode;
            }
        }
        if (modes.length <= 0) {
            return null;
        }
        Arrays.sort(modes, new DisplayModeComparator());
        return modes[modes.length - 1];
    }

    public static String[] getDisplayModeNames() {
        DisplayMode[] modes = Config.getDisplayModes();
        String[] names = new String[modes.length];
        for (int i = 0; i < modes.length; ++i) {
            String name;
            DisplayMode mode = modes[i];
            names[i] = name = "" + mode.getWidth() + "x" + mode.getHeight();
        }
        return names;
    }

    public static DisplayMode getDisplayMode(Dimension dim) throws LWJGLException {
        DisplayMode[] modes = Config.getDisplayModes();
        for (int i = 0; i < modes.length; ++i) {
            DisplayMode dm = modes[i];
            if (dm.getWidth() != dim.width || dm.getHeight() != dim.height) continue;
            return dm;
        }
        return desktopDisplayMode;
    }

    public static boolean isAnimatedTerrain() {
        return Config.gameSettings.ofAnimatedTerrain;
    }

    public static boolean isAnimatedTextures() {
        return Config.gameSettings.ofAnimatedTextures;
    }

    public static boolean isSwampColors() {
        return Config.gameSettings.ofSwampColors;
    }

    public static boolean isRandomEntities() {
        return Config.gameSettings.ofRandomEntities;
    }

    public static void checkGlError(String loc) {
        int errorCode = bus.L();
        if (errorCode != 0 && GlErrors.isEnabled(errorCode)) {
            String errorText = Config.getGlErrorString(errorCode);
            String messageLog = String.format("OpenGL error: %s (%s), at: %s", errorCode, errorText, loc);
            Config.error(messageLog);
            if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlError", 10000L)) {
                String message = cey.a((String)"of.message.openglError", (Object[])new Object[]{errorCode, errorText});
                Config.minecraft.q.d().a((hh)new ho(message));
            }
        }
    }

    public static boolean isSmoothBiomes() {
        return Config.gameSettings.ofSmoothBiomes;
    }

    public static boolean isCustomColors() {
        return Config.gameSettings.ofCustomColors;
    }

    public static boolean isCustomSky() {
        return Config.gameSettings.ofCustomSky;
    }

    public static boolean isCustomFonts() {
        return Config.gameSettings.ofCustomFonts;
    }

    public static boolean isShowCapes() {
        return Config.gameSettings.ofShowCapes;
    }

    public static boolean isConnectedTextures() {
        return Config.gameSettings.ofConnectedTextures != 3;
    }

    public static boolean isNaturalTextures() {
        return Config.gameSettings.ofNaturalTextures;
    }

    public static boolean isEmissiveTextures() {
        return Config.gameSettings.ofEmissiveTextures;
    }

    public static boolean isConnectedTexturesFancy() {
        return Config.gameSettings.ofConnectedTextures == 2;
    }

    public static boolean isFastRender() {
        return Config.gameSettings.ofFastRender;
    }

    public static boolean isTranslucentBlocksFancy() {
        if (Config.gameSettings.ofTranslucentBlocks == 0) {
            return Config.gameSettings.k;
        }
        return Config.gameSettings.ofTranslucentBlocks == 2;
    }

    public static boolean isShaders() {
        return Shaders.shaderPackLoaded;
    }

    public static String[] readLines(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        return Config.readLines(fis);
    }

    public static String[] readLines(InputStream is) throws IOException {
        String line;
        ArrayList<String> list = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(is, "ASCII");
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        String[] lines = list.toArray(new String[list.size()]);
        return lines;
    }

    public static String readFile(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        return Config.readInputStream(fin, "ASCII");
    }

    public static String readInputStream(InputStream in) throws IOException {
        return Config.readInputStream(in, "ASCII");
    }

    public static String readInputStream(InputStream in, String encoding) throws IOException {
        String line;
        InputStreamReader inr = new InputStreamReader(in, encoding);
        BufferedReader br = new BufferedReader(inr);
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static byte[] readAll(InputStream is) throws IOException {
        int len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) >= 0) {
            baos.write(buf, 0, len);
        }
        is.close();
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static bid getGameSettings() {
        return gameSettings;
    }

    public static String getNewRelease() {
        return newRelease;
    }

    public static void setNewRelease(String newRelease) {
        Config.newRelease = newRelease;
    }

    public static int compareRelease(String rel1, String rel2) {
        int rev2;
        String[] rels2;
        String branch2;
        String[] rels1 = Config.splitRelease(rel1);
        String branch1 = rels1[0];
        if (!branch1.equals(branch2 = (rels2 = Config.splitRelease(rel2))[0])) {
            return branch1.compareTo(branch2);
        }
        int rev1 = Config.parseInt(rels1[1], -1);
        if (rev1 != (rev2 = Config.parseInt(rels2[1], -1))) {
            return rev1 - rev2;
        }
        String suf1 = rels1[2];
        String suf2 = rels2[2];
        if (!suf1.equals(suf2)) {
            if (suf1.isEmpty()) {
                return 1;
            }
            if (suf2.isEmpty()) {
                return -1;
            }
        }
        return suf1.compareTo(suf2);
    }

    private static String[] splitRelease(String relStr) {
        if (relStr == null || relStr.length() <= 0) {
            return new String[]{"", "", ""};
        }
        Pattern p = Pattern.compile("([A-Z])([0-9]+)(.*)");
        Matcher m2 = p.matcher(relStr);
        if (!m2.matches()) {
            return new String[]{"", "", ""};
        }
        String branch = Config.normalize(m2.group(1));
        String revision = Config.normalize(m2.group(2));
        String suffix = Config.normalize(m2.group(3));
        return new String[]{branch, revision, suffix};
    }

    public static int intHash(int x) {
        x = x ^ 0x3D ^ x >> 16;
        x += x << 3;
        x ^= x >> 4;
        x *= 668265261;
        x ^= x >> 15;
        return x;
    }

    public static int getRandom(et blockPos, int face) {
        int rand = Config.intHash(face + 37);
        rand = Config.intHash(rand + blockPos.p());
        rand = Config.intHash(rand + blockPos.r());
        rand = Config.intHash(rand + blockPos.q());
        return rand;
    }

    public static int getAvailableProcessors() {
        return availableProcessors;
    }

    public static void updateAvailableProcessors() {
        availableProcessors = Runtime.getRuntime().availableProcessors();
    }

    public static boolean isSingleProcessor() {
        return Config.getAvailableProcessors() <= 1;
    }

    public static boolean isSmoothWorld() {
        return Config.gameSettings.ofSmoothWorld;
    }

    public static boolean isLazyChunkLoading() {
        return Config.gameSettings.ofLazyChunkLoading;
    }

    public static boolean isDynamicFov() {
        return Config.gameSettings.ofDynamicFov;
    }

    public static boolean isAlternateBlocks() {
        return Config.gameSettings.ofAlternateBlocks;
    }

    public static int getChunkViewDistance() {
        if (gameSettings == null) {
            return 10;
        }
        int chunkDistance = Config.gameSettings.e;
        return chunkDistance;
    }

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null) {
            return false;
        }
        return o1.equals(o2);
    }

    public static boolean equalsOne(Object a2, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object b2 = bs[i];
            if (!Config.equals(a2, b2)) continue;
            return true;
        }
        return false;
    }

    public static boolean equalsOne(int val, int[] vals) {
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i] != val) continue;
            return true;
        }
        return false;
    }

    public static boolean isSameOne(Object a2, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object b2 = bs[i];
            if (a2 != b2) continue;
            return true;
        }
        return false;
    }

    public static String normalize(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void checkDisplaySettings() {
        int samples = Config.getAntialiasingLevel();
        if (samples > 0) {
            DisplayMode displayMode = Display.getDisplayMode();
            Config.dbg("FSAA Samples: " + samples);
            try {
                Display.destroy();
                Display.setDisplayMode((DisplayMode)displayMode);
                Display.create((PixelFormat)new PixelFormat().withDepthBits(24).withSamples(samples));
                Display.setResizable((boolean)false);
                Display.setResizable((boolean)true);
            }
            catch (LWJGLException e) {
                Config.warn("Error setting FSAA: " + samples + "x");
                e.printStackTrace();
                try {
                    Display.setDisplayMode((DisplayMode)displayMode);
                    Display.create((PixelFormat)new PixelFormat().withDepthBits(24));
                    Display.setResizable((boolean)false);
                    Display.setResizable((boolean)true);
                }
                catch (LWJGLException e2) {
                    e2.printStackTrace();
                    try {
                        Display.setDisplayMode((DisplayMode)displayMode);
                        Display.create();
                        Display.setResizable((boolean)false);
                        Display.setResizable((boolean)true);
                    }
                    catch (LWJGLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            if (!bib.a && Config.getDefaultResourcePack() != null) {
                InputStream var3;
                InputStream var2;
                block12: {
                    var2 = null;
                    var3 = null;
                    try {
                        var2 = Config.getDefaultResourcePack().c(new nf("icons/icon_16x16.png"));
                        var3 = Config.getDefaultResourcePack().c(new nf("icons/icon_32x32.png"));
                        if (var2 == null || var3 == null) break block12;
                        Display.setIcon((ByteBuffer[])new ByteBuffer[]{Config.readIconImage(var2), Config.readIconImage(var3)});
                    }
                    catch (IOException var8) {
                        try {
                            Config.warn("Error setting window icon: " + var8.getClass().getName() + ": " + var8.getMessage());
                        }
                        catch (Throwable throwable) {
                            IOUtils.closeQuietly(var2);
                            IOUtils.closeQuietly(var3);
                            throw throwable;
                        }
                        IOUtils.closeQuietly((InputStream)var2);
                        IOUtils.closeQuietly((InputStream)var3);
                    }
                }
                IOUtils.closeQuietly((InputStream)var2);
                IOUtils.closeQuietly((InputStream)var3);
            }
        }
    }

    private static ByteBuffer readIconImage(InputStream is) throws IOException {
        BufferedImage var2 = ImageIO.read(is);
        int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), null, 0, var2.getWidth());
        ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
        int[] var5 = var3;
        int var6 = var3.length;
        for (int var7 = 0; var7 < var6; ++var7) {
            int var8 = var5[var7];
            var4.putInt(var8 << 8 | var8 >> 24 & 0xFF);
        }
        var4.flip();
        return var4;
    }

    public static void checkDisplayMode() {
        try {
            if (minecraft.J()) {
                if (fullscreenModeChecked) {
                    return;
                }
                fullscreenModeChecked = true;
                desktopModeChecked = false;
                DisplayMode mode = Display.getDisplayMode();
                Dimension dim = Config.getFullscreenDimension();
                if (dim == null) {
                    return;
                }
                if (mode.getWidth() == dim.width && mode.getHeight() == dim.height) {
                    return;
                }
                DisplayMode newMode = Config.getDisplayMode(dim);
                if (newMode == null) {
                    return;
                }
                Display.setDisplayMode((DisplayMode)newMode);
                Config.minecraft.d = Display.getDisplayMode().getWidth();
                Config.minecraft.e = Display.getDisplayMode().getHeight();
                if (Config.minecraft.d <= 0) {
                    Config.minecraft.d = 1;
                }
                if (Config.minecraft.e <= 0) {
                    Config.minecraft.e = 1;
                }
                if (Config.minecraft.m != null) {
                    bit sr = new bit(minecraft);
                    int sw = sr.a();
                    int sh = sr.b();
                    Config.minecraft.m.a(minecraft, sw, sh);
                }
                Config.updateFramebufferSize();
                Display.setFullscreen((boolean)true);
                Config.minecraft.t.updateVSync();
                bus.y();
            } else {
                if (desktopModeChecked) {
                    return;
                }
                desktopModeChecked = true;
                fullscreenModeChecked = false;
                Config.minecraft.t.updateVSync();
                Display.update();
                bus.y();
                Display.setResizable((boolean)false);
                Display.setResizable((boolean)true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Config.gameSettings.ofFullscreenMode = "Default";
            gameSettings.saveOfOptions();
        }
    }

    public static void updateFramebufferSize() {
        minecraft.b().a(Config.minecraft.d, Config.minecraft.e);
        if (Config.minecraft.o != null) {
            Config.minecraft.o.a(Config.minecraft.d, Config.minecraft.e);
        }
        Config.minecraft.n = new bie(minecraft);
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        int arrLen = arr.length;
        int newLen = arrLen + 1;
        Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), newLen);
        System.arraycopy(arr, 0, newArr, 0, arrLen);
        newArr[arrLen] = obj;
        return newArr;
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj, int index) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.add(index, obj);
        Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), list.size());
        return list.toArray(newArr);
    }

    public static Object[] addObjectsToArray(Object[] arr, Object[] objs) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        if (objs.length == 0) {
            return arr;
        }
        int arrLen = arr.length;
        int newLen = arrLen + objs.length;
        Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), newLen);
        System.arraycopy(arr, 0, newArr, 0, arrLen);
        System.arraycopy(objs, 0, newArr, arrLen, objs.length);
        return newArr;
    }

    public static Object[] removeObjectFromArray(Object[] arr, Object obj) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.remove(obj);
        Object[] newArr = Config.collectionToArray(list, arr.getClass().getComponentType());
        return newArr;
    }

    public static Object[] collectionToArray(Collection coll, Class elementClass) {
        if (coll == null) {
            return null;
        }
        if (elementClass == null) {
            return null;
        }
        if (elementClass.isPrimitive()) {
            throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + elementClass);
        }
        Object[] array = (Object[])Array.newInstance(elementClass, coll.size());
        return coll.toArray(array);
    }

    public static boolean isCustomItems() {
        return Config.gameSettings.ofCustomItems;
    }

    public static void drawFps() {
        int fps = bib.af();
        String updates = Config.getUpdates(Config.minecraft.D);
        int renderersActive = Config.minecraft.g.getCountActiveRenderers();
        int entities = Config.minecraft.g.getCountEntitiesRendered();
        int tileEntities = Config.minecraft.g.getCountTileEntitiesRendered();
        String fpsStr = "" + fps + "/" + Config.getFpsMin() + " fps, C: " + renderersActive + ", E: " + entities + "+" + tileEntities + ", U: " + updates;
        Config.minecraft.k.a(fpsStr, 2, 2, -2039584);
    }

    public static int getFpsMin() {
        long timeAvgNs;
        int indexEnd;
        if (Config.minecraft.D == mcDebugLast) {
            return fpsMinLast;
        }
        mcDebugLast = Config.minecraft.D;
        rb ft = minecraft.ag();
        long[] frames = ft.c();
        int index = ft.b();
        if (index == (indexEnd = ft.a())) {
            return fpsMinLast;
        }
        int fps = bib.af();
        if (fps <= 0) {
            fps = 1;
        }
        long timeMaxNs = timeAvgNs = (long)(1.0 / (double)fps * 1.0E9);
        long timeTotalNs = 0L;
        int ix = rk.b((int)(index - 1), (int)frames.length);
        while (ix != indexEnd && (double)timeTotalNs < 1.0E9) {
            long timeNs = frames[ix];
            if (timeNs > timeMaxNs) {
                timeMaxNs = timeNs;
            }
            timeTotalNs += timeNs;
            ix = rk.b((int)(ix - 1), (int)frames.length);
        }
        double timeMaxSec = (double)timeMaxNs / 1.0E9;
        fpsMinLast = (int)(1.0 / timeMaxSec);
        return fpsMinLast;
    }

    private static String getUpdates(String str) {
        int pos1 = str.indexOf(40);
        if (pos1 < 0) {
            return "";
        }
        int pos2 = str.indexOf(32, pos1);
        if (pos2 < 0) {
            return "";
        }
        return str.substring(pos1 + 1, pos2);
    }

    public static int getBitsOs() {
        String progFiles86 = System.getenv("ProgramFiles(X86)");
        if (progFiles86 != null) {
            return 64;
        }
        return 32;
    }

    public static int getBitsJre() {
        String[] propNames = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
        for (int i = 0; i < propNames.length; ++i) {
            String propName = propNames[i];
            String propVal = System.getProperty(propName);
            if (propVal == null || !propVal.contains("64")) continue;
            return 64;
        }
        return 32;
    }

    public static boolean isNotify64BitJava() {
        return notify64BitJava;
    }

    public static void setNotify64BitJava(boolean flag) {
        notify64BitJava = flag;
    }

    public static boolean isConnectedModels() {
        return false;
    }

    public static void showGuiMessage(String line1, String line2) {
        GuiMessage gui = new GuiMessage(Config.minecraft.m, line1, line2);
        minecraft.a((blk)gui);
    }

    public static int[] addIntToArray(int[] intArray, int intValue) {
        return Config.addIntsToArray(intArray, new int[]{intValue});
    }

    public static int[] addIntsToArray(int[] intArray, int[] copyFrom) {
        if (intArray == null || copyFrom == null) {
            throw new NullPointerException("The given array is NULL");
        }
        int arrLen = intArray.length;
        int newLen = arrLen + copyFrom.length;
        int[] newArray = new int[newLen];
        System.arraycopy(intArray, 0, newArray, 0, arrLen);
        for (int index = 0; index < copyFrom.length; ++index) {
            newArray[index + arrLen] = copyFrom[index];
        }
        return newArray;
    }

    public static cdg getMojangLogoTexture(cdg texDefault) {
        try {
            nf locationMojangPng = new nf("textures/gui/title/mojang.png");
            InputStream in = Config.getResourceStream(locationMojangPng);
            if (in == null) {
                return texDefault;
            }
            BufferedImage bi = ImageIO.read(in);
            if (bi == null) {
                return texDefault;
            }
            cdg dt = new cdg(bi);
            return dt;
        }
        catch (Exception e) {
            Config.warn(e.getClass().getName() + ": " + e.getMessage());
            return texDefault;
        }
    }

    public static void writeFile(File file, String str) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = str.getBytes("ASCII");
        fos.write(bytes);
        fos.close();
    }

    public static cdp getTextureMap() {
        return Config.getMinecraft().R();
    }

    public static boolean isDynamicLights() {
        return Config.gameSettings.ofDynamicLights != 3;
    }

    public static boolean isDynamicLightsFast() {
        return Config.gameSettings.ofDynamicLights == 1;
    }

    public static boolean isDynamicHandLight() {
        if (!Config.isDynamicLights()) {
            return false;
        }
        if (Config.isShaders()) {
            return Shaders.isDynamicHandLight();
        }
        return true;
    }

    public static boolean isCustomEntityModels() {
        return Config.gameSettings.ofCustomEntityModels;
    }

    public static boolean isCustomGuis() {
        return Config.gameSettings.ofCustomGuis;
    }

    public static int getScreenshotSize() {
        return Config.gameSettings.ofScreenshotSize;
    }

    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[arr.length];
        for (int i = 0; i < intArr.length; ++i) {
            intArr[i] = arr[i];
        }
        return intArr;
    }

    public static boolean isRenderRegions() {
        return Config.gameSettings.ofRenderRegions;
    }

    public static boolean isVbo() {
        return cii.f();
    }

    public static boolean isSmoothFps() {
        return Config.gameSettings.ofSmoothFps;
    }

    public static boolean openWebLink(URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
            return true;
        }
        catch (Exception e) {
            Config.warn("Error opening link: " + uri);
            Config.warn(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean isShowGlErrors() {
        return Config.gameSettings.ofShowGlErrors;
    }

    public static String arrayToString(boolean[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            boolean x = arr[i];
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(String.valueOf(x));
        }
        return buf.toString();
    }

    public static boolean isIntegratedServerRunning() {
        if (minecraft.F() == null) {
            return false;
        }
        return minecraft.D();
    }

    public static IntBuffer createDirectIntBuffer(int capacity) {
        return bia.c((int)(capacity << 2)).asIntBuffer();
    }

    public static String getGlErrorString(int err) {
        switch (err) {
            case 0: {
                return "No error";
            }
            case 1280: {
                return "Invalid enum";
            }
            case 1281: {
                return "Invalid value";
            }
            case 1282: {
                return "Invalid operation";
            }
            case 1286: {
                return "Invalid framebuffer operation";
            }
            case 1285: {
                return "Out of memory";
            }
            case 1284: {
                return "Stack underflow";
            }
            case 1283: {
                return "Stack overflow";
            }
        }
        return "Unknown";
    }

    public static boolean isTrue(Boolean val) {
        return val != null && val != false;
    }

    public static boolean isQuadsToTriangles() {
        if (!Config.isShaders()) {
            return false;
        }
        return !Shaders.canRenderQuads();
    }
}

