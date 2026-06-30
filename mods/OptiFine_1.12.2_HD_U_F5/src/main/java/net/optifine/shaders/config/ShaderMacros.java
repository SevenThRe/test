/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  h
 *  h$a
 */
package net.optifine.shaders.config;

import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderMacro;

public class ShaderMacros {
    private static String PREFIX_MACRO = "MC_";
    public static final String MC_VERSION = "MC_VERSION";
    public static final String MC_GL_VERSION = "MC_GL_VERSION";
    public static final String MC_GLSL_VERSION = "MC_GLSL_VERSION";
    public static final String MC_OS_WINDOWS = "MC_OS_WINDOWS";
    public static final String MC_OS_MAC = "MC_OS_MAC";
    public static final String MC_OS_LINUX = "MC_OS_LINUX";
    public static final String MC_OS_OTHER = "MC_OS_OTHER";
    public static final String MC_GL_VENDOR_ATI = "MC_GL_VENDOR_ATI";
    public static final String MC_GL_VENDOR_INTEL = "MC_GL_VENDOR_INTEL";
    public static final String MC_GL_VENDOR_NVIDIA = "MC_GL_VENDOR_NVIDIA";
    public static final String MC_GL_VENDOR_XORG = "MC_GL_VENDOR_XORG";
    public static final String MC_GL_VENDOR_OTHER = "MC_GL_VENDOR_OTHER";
    public static final String MC_GL_RENDERER_RADEON = "MC_GL_RENDERER_RADEON";
    public static final String MC_GL_RENDERER_GEFORCE = "MC_GL_RENDERER_GEFORCE";
    public static final String MC_GL_RENDERER_QUADRO = "MC_GL_RENDERER_QUADRO";
    public static final String MC_GL_RENDERER_INTEL = "MC_GL_RENDERER_INTEL";
    public static final String MC_GL_RENDERER_GALLIUM = "MC_GL_RENDERER_GALLIUM";
    public static final String MC_GL_RENDERER_MESA = "MC_GL_RENDERER_MESA";
    public static final String MC_GL_RENDERER_OTHER = "MC_GL_RENDERER_OTHER";
    public static final String MC_FXAA_LEVEL = "MC_FXAA_LEVEL";
    public static final String MC_NORMAL_MAP = "MC_NORMAL_MAP";
    public static final String MC_SPECULAR_MAP = "MC_SPECULAR_MAP";
    public static final String MC_RENDER_QUALITY = "MC_RENDER_QUALITY";
    public static final String MC_SHADOW_QUALITY = "MC_SHADOW_QUALITY";
    public static final String MC_HAND_DEPTH = "MC_HAND_DEPTH";
    public static final String MC_OLD_HAND_LIGHT = "MC_OLD_HAND_LIGHT";
    public static final String MC_OLD_LIGHTING = "MC_OLD_LIGHTING";
    private static ShaderMacro[] extensionMacros;

    public static String getOs() {
        h.a os = h.a();
        switch (os) {
            case c: {
                return MC_OS_WINDOWS;
            }
            case d: {
                return MC_OS_MAC;
            }
            case a: {
                return MC_OS_LINUX;
            }
        }
        return MC_OS_OTHER;
    }

    public static String getVendor() {
        String vendor = Config.openGlVendor;
        if (vendor == null) {
            return MC_GL_VENDOR_OTHER;
        }
        if ((vendor = vendor.toLowerCase()).startsWith("ati")) {
            return MC_GL_VENDOR_ATI;
        }
        if (vendor.startsWith("intel")) {
            return MC_GL_VENDOR_INTEL;
        }
        if (vendor.startsWith("nvidia")) {
            return MC_GL_VENDOR_NVIDIA;
        }
        if (vendor.startsWith("x.org")) {
            return MC_GL_VENDOR_XORG;
        }
        return MC_GL_VENDOR_OTHER;
    }

    public static String getRenderer() {
        String renderer = Config.openGlRenderer;
        if (renderer == null) {
            return MC_GL_RENDERER_OTHER;
        }
        if ((renderer = renderer.toLowerCase()).startsWith("amd")) {
            return MC_GL_RENDERER_RADEON;
        }
        if (renderer.startsWith("ati")) {
            return MC_GL_RENDERER_RADEON;
        }
        if (renderer.startsWith("radeon")) {
            return MC_GL_RENDERER_RADEON;
        }
        if (renderer.startsWith("gallium")) {
            return MC_GL_RENDERER_GALLIUM;
        }
        if (renderer.startsWith("intel")) {
            return MC_GL_RENDERER_INTEL;
        }
        if (renderer.startsWith("geforce")) {
            return MC_GL_RENDERER_GEFORCE;
        }
        if (renderer.startsWith("nvidia")) {
            return MC_GL_RENDERER_GEFORCE;
        }
        if (renderer.startsWith("quadro")) {
            return MC_GL_RENDERER_QUADRO;
        }
        if (renderer.startsWith("nvs")) {
            return MC_GL_RENDERER_QUADRO;
        }
        if (renderer.startsWith("mesa")) {
            return MC_GL_RENDERER_MESA;
        }
        return MC_GL_RENDERER_OTHER;
    }

    public static String getPrefixMacro() {
        return PREFIX_MACRO;
    }

    public static ShaderMacro[] getExtensions() {
        if (extensionMacros == null) {
            String[] exts = Config.getOpenGlExtensions();
            ShaderMacro[] extMacros = new ShaderMacro[exts.length];
            for (int i = 0; i < exts.length; ++i) {
                extMacros[i] = new ShaderMacro(PREFIX_MACRO + exts[i], "");
            }
            extensionMacros = extMacros;
        }
        return extensionMacros;
    }

    public static String getFixedMacroLines() {
        StringBuilder sb = new StringBuilder();
        ShaderMacros.addMacroLine(sb, MC_VERSION, Config.getMinecraftVersionInt());
        ShaderMacros.addMacroLine(sb, "MC_GL_VERSION " + Config.getGlVersion().toInt());
        ShaderMacros.addMacroLine(sb, "MC_GLSL_VERSION " + Config.getGlslVersion().toInt());
        ShaderMacros.addMacroLine(sb, ShaderMacros.getOs());
        ShaderMacros.addMacroLine(sb, ShaderMacros.getVendor());
        ShaderMacros.addMacroLine(sb, ShaderMacros.getRenderer());
        return sb.toString();
    }

    public static String getOptionMacroLines() {
        StringBuilder sb = new StringBuilder();
        if (Shaders.configAntialiasingLevel > 0) {
            ShaderMacros.addMacroLine(sb, MC_FXAA_LEVEL, Shaders.configAntialiasingLevel);
        }
        if (Shaders.configNormalMap) {
            ShaderMacros.addMacroLine(sb, MC_NORMAL_MAP);
        }
        if (Shaders.configSpecularMap) {
            ShaderMacros.addMacroLine(sb, MC_SPECULAR_MAP);
        }
        ShaderMacros.addMacroLine(sb, MC_RENDER_QUALITY, Shaders.configRenderResMul);
        ShaderMacros.addMacroLine(sb, MC_SHADOW_QUALITY, Shaders.configShadowResMul);
        ShaderMacros.addMacroLine(sb, MC_HAND_DEPTH, Shaders.configHandDepthMul);
        if (Shaders.isOldHandLight()) {
            ShaderMacros.addMacroLine(sb, MC_OLD_HAND_LIGHT);
        }
        if (Shaders.isOldLighting()) {
            ShaderMacros.addMacroLine(sb, MC_OLD_LIGHTING);
        }
        return sb.toString();
    }

    private static void addMacroLine(StringBuilder sb, String name, int value) {
        sb.append("#define ");
        sb.append(name);
        sb.append(" ");
        sb.append(value);
        sb.append("\n");
    }

    private static void addMacroLine(StringBuilder sb, String name, float value) {
        sb.append("#define ");
        sb.append(name);
        sb.append(" ");
        sb.append(value);
        sb.append("\n");
    }

    private static void addMacroLine(StringBuilder sb, String name) {
        sb.append("#define ");
        sb.append(name);
        sb.append("\n");
    }
}

