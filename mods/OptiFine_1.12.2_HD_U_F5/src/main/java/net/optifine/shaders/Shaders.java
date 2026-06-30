/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ahb
 *  ain
 *  aip
 *  amm
 *  amu
 *  aow
 *  avj
 *  awt
 *  bcz
 *  bhe
 *  bhv
 *  bhz
 *  bia
 *  bib
 *  bid
 *  buk
 *  buq
 *  bus
 *  bus$m
 *  bve
 *  cdp
 *  cds
 *  cdy
 *  cey
 *  cii
 *  et
 *  hh
 *  ho
 *  nf
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBGeometryShader4
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.ARBVertexShader
 *  org.lwjgl.opengl.ContextCapabilities
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 *  org.lwjgl.opengl.GLContext
 *  org.lwjgl.util.glu.GLU
 *  org.lwjgl.util.vector.Vector4f
 *  ub
 *  vb
 *  vg
 *  vp
 */
package net.optifine.shaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.GlErrors;
import net.optifine.Lang;
import net.optifine.config.ConnectedParser;
import net.optifine.expr.IExpressionBool;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.CustomTexture;
import net.optifine.shaders.CustomTextureLocation;
import net.optifine.shaders.CustomTextureRaw;
import net.optifine.shaders.EntityAliases;
import net.optifine.shaders.FlipTextures;
import net.optifine.shaders.HFNoiseTexture;
import net.optifine.shaders.ICustomTexture;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.ItemAliases;
import net.optifine.shaders.Program;
import net.optifine.shaders.ProgramStack;
import net.optifine.shaders.ProgramStage;
import net.optifine.shaders.Programs;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.SMath;
import net.optifine.shaders.ShaderPackDefault;
import net.optifine.shaders.ShaderPackFolder;
import net.optifine.shaders.ShaderPackNone;
import net.optifine.shaders.ShaderPackZip;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.SimpleShaderTexture;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ScreenShaderOptions;
import net.optifine.shaders.config.ShaderLine;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionRest;
import net.optifine.shaders.config.ShaderPackParser;
import net.optifine.shaders.config.ShaderParser;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderUniform1f;
import net.optifine.shaders.uniform.ShaderUniform1i;
import net.optifine.shaders.uniform.ShaderUniform2i;
import net.optifine.shaders.uniform.ShaderUniform3f;
import net.optifine.shaders.uniform.ShaderUniform4f;
import net.optifine.shaders.uniform.ShaderUniform4i;
import net.optifine.shaders.uniform.ShaderUniformM4;
import net.optifine.shaders.uniform.ShaderUniforms;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;

public class Shaders {
    static bib mc;
    static buq entityRenderer;
    public static boolean isInitializedOnce;
    public static boolean isShaderPackInitialized;
    public static ContextCapabilities capabilities;
    public static String glVersionString;
    public static String glVendorString;
    public static String glRendererString;
    public static boolean hasGlGenMipmap;
    public static int countResetDisplayLists;
    private static int renderDisplayWidth;
    private static int renderDisplayHeight;
    public static int renderWidth;
    public static int renderHeight;
    public static boolean isRenderingWorld;
    public static boolean isRenderingSky;
    public static boolean isCompositeRendered;
    public static boolean isRenderingDfb;
    public static boolean isShadowPass;
    public static boolean isEntitiesGlowing;
    public static boolean isSleeping;
    private static boolean isRenderingFirstPersonHand;
    private static boolean isHandRenderedMain;
    private static boolean isHandRenderedOff;
    private static boolean skipRenderHandMain;
    private static boolean skipRenderHandOff;
    public static boolean renderItemKeepDepthMask;
    public static boolean itemToRenderMainTranslucent;
    public static boolean itemToRenderOffTranslucent;
    static float[] sunPosition;
    static float[] moonPosition;
    static float[] shadowLightPosition;
    static float[] upPosition;
    static float[] shadowLightPositionVector;
    static float[] upPosModelView;
    static float[] sunPosModelView;
    static float[] moonPosModelView;
    private static float[] tempMat;
    static float clearColorR;
    static float clearColorG;
    static float clearColorB;
    static float skyColorR;
    static float skyColorG;
    static float skyColorB;
    static long worldTime;
    static long lastWorldTime;
    static long diffWorldTime;
    static float celestialAngle;
    static float sunAngle;
    static float shadowAngle;
    static int moonPhase;
    static long systemTime;
    static long lastSystemTime;
    static long diffSystemTime;
    static int frameCounter;
    static float frameTime;
    static float frameTimeCounter;
    static int systemTimeInt32;
    static float rainStrength;
    static float wetness;
    public static float wetnessHalfLife;
    public static float drynessHalfLife;
    public static float eyeBrightnessHalflife;
    static boolean usewetness;
    static int isEyeInWater;
    static int eyeBrightness;
    static float eyeBrightnessFadeX;
    static float eyeBrightnessFadeY;
    static float eyePosY;
    static float centerDepth;
    static float centerDepthSmooth;
    static float centerDepthSmoothHalflife;
    static boolean centerDepthSmoothEnabled;
    static int superSamplingLevel;
    static float nightVision;
    static float blindness;
    static boolean lightmapEnabled;
    static boolean fogEnabled;
    public static int entityAttrib;
    public static int midTexCoordAttrib;
    public static int tangentAttrib;
    public static boolean useEntityAttrib;
    public static boolean useMidTexCoordAttrib;
    public static boolean useTangentAttrib;
    public static boolean progUseEntityAttrib;
    public static boolean progUseMidTexCoordAttrib;
    public static boolean progUseTangentAttrib;
    private static boolean progArbGeometryShader4;
    private static int progMaxVerticesOut;
    private static boolean hasGeometryShaders;
    public static int atlasSizeX;
    public static int atlasSizeY;
    private static ShaderUniforms shaderUniforms;
    public static ShaderUniform4f uniform_entityColor;
    public static ShaderUniform1i uniform_entityId;
    public static ShaderUniform1i uniform_blockEntityId;
    public static ShaderUniform1i uniform_texture;
    public static ShaderUniform1i uniform_lightmap;
    public static ShaderUniform1i uniform_normals;
    public static ShaderUniform1i uniform_specular;
    public static ShaderUniform1i uniform_shadow;
    public static ShaderUniform1i uniform_watershadow;
    public static ShaderUniform1i uniform_shadowtex0;
    public static ShaderUniform1i uniform_shadowtex1;
    public static ShaderUniform1i uniform_depthtex0;
    public static ShaderUniform1i uniform_depthtex1;
    public static ShaderUniform1i uniform_shadowcolor;
    public static ShaderUniform1i uniform_shadowcolor0;
    public static ShaderUniform1i uniform_shadowcolor1;
    public static ShaderUniform1i uniform_noisetex;
    public static ShaderUniform1i uniform_gcolor;
    public static ShaderUniform1i uniform_gdepth;
    public static ShaderUniform1i uniform_gnormal;
    public static ShaderUniform1i uniform_composite;
    public static ShaderUniform1i uniform_gaux1;
    public static ShaderUniform1i uniform_gaux2;
    public static ShaderUniform1i uniform_gaux3;
    public static ShaderUniform1i uniform_gaux4;
    public static ShaderUniform1i uniform_colortex0;
    public static ShaderUniform1i uniform_colortex1;
    public static ShaderUniform1i uniform_colortex2;
    public static ShaderUniform1i uniform_colortex3;
    public static ShaderUniform1i uniform_colortex4;
    public static ShaderUniform1i uniform_colortex5;
    public static ShaderUniform1i uniform_colortex6;
    public static ShaderUniform1i uniform_colortex7;
    public static ShaderUniform1i uniform_gdepthtex;
    public static ShaderUniform1i uniform_depthtex2;
    public static ShaderUniform1i uniform_tex;
    public static ShaderUniform1i uniform_heldItemId;
    public static ShaderUniform1i uniform_heldBlockLightValue;
    public static ShaderUniform1i uniform_heldItemId2;
    public static ShaderUniform1i uniform_heldBlockLightValue2;
    public static ShaderUniform1i uniform_fogMode;
    public static ShaderUniform1f uniform_fogDensity;
    public static ShaderUniform3f uniform_fogColor;
    public static ShaderUniform3f uniform_skyColor;
    public static ShaderUniform1i uniform_worldTime;
    public static ShaderUniform1i uniform_worldDay;
    public static ShaderUniform1i uniform_moonPhase;
    public static ShaderUniform1i uniform_frameCounter;
    public static ShaderUniform1f uniform_frameTime;
    public static ShaderUniform1f uniform_frameTimeCounter;
    public static ShaderUniform1f uniform_sunAngle;
    public static ShaderUniform1f uniform_shadowAngle;
    public static ShaderUniform1f uniform_rainStrength;
    public static ShaderUniform1f uniform_aspectRatio;
    public static ShaderUniform1f uniform_viewWidth;
    public static ShaderUniform1f uniform_viewHeight;
    public static ShaderUniform1f uniform_near;
    public static ShaderUniform1f uniform_far;
    public static ShaderUniform3f uniform_sunPosition;
    public static ShaderUniform3f uniform_moonPosition;
    public static ShaderUniform3f uniform_shadowLightPosition;
    public static ShaderUniform3f uniform_upPosition;
    public static ShaderUniform3f uniform_previousCameraPosition;
    public static ShaderUniform3f uniform_cameraPosition;
    public static ShaderUniformM4 uniform_gbufferModelView;
    public static ShaderUniformM4 uniform_gbufferModelViewInverse;
    public static ShaderUniformM4 uniform_gbufferPreviousProjection;
    public static ShaderUniformM4 uniform_gbufferProjection;
    public static ShaderUniformM4 uniform_gbufferProjectionInverse;
    public static ShaderUniformM4 uniform_gbufferPreviousModelView;
    public static ShaderUniformM4 uniform_shadowProjection;
    public static ShaderUniformM4 uniform_shadowProjectionInverse;
    public static ShaderUniformM4 uniform_shadowModelView;
    public static ShaderUniformM4 uniform_shadowModelViewInverse;
    public static ShaderUniform1f uniform_wetness;
    public static ShaderUniform1f uniform_eyeAltitude;
    public static ShaderUniform2i uniform_eyeBrightness;
    public static ShaderUniform2i uniform_eyeBrightnessSmooth;
    public static ShaderUniform2i uniform_terrainTextureSize;
    public static ShaderUniform1i uniform_terrainIconSize;
    public static ShaderUniform1i uniform_isEyeInWater;
    public static ShaderUniform1f uniform_nightVision;
    public static ShaderUniform1f uniform_blindness;
    public static ShaderUniform1f uniform_screenBrightness;
    public static ShaderUniform1i uniform_hideGUI;
    public static ShaderUniform1f uniform_centerDepthSmooth;
    public static ShaderUniform2i uniform_atlasSize;
    public static ShaderUniform4i uniform_blendFunc;
    public static ShaderUniform1i uniform_instanceId;
    static double previousCameraPositionX;
    static double previousCameraPositionY;
    static double previousCameraPositionZ;
    static double cameraPositionX;
    static double cameraPositionY;
    static double cameraPositionZ;
    static int cameraOffsetX;
    static int cameraOffsetZ;
    static int shadowPassInterval;
    public static boolean needResizeShadow;
    static int shadowMapWidth;
    static int shadowMapHeight;
    static int spShadowMapWidth;
    static int spShadowMapHeight;
    static float shadowMapFOV;
    static float shadowMapHalfPlane;
    static boolean shadowMapIsOrtho;
    static float shadowDistanceRenderMul;
    static int shadowPassCounter;
    static int preShadowPassThirdPersonView;
    public static boolean shouldSkipDefaultShadow;
    static boolean waterShadowEnabled;
    static final int MaxDrawBuffers = 8;
    static final int MaxColorBuffers = 8;
    static final int MaxDepthBuffers = 3;
    static final int MaxShadowColorBuffers = 8;
    static final int MaxShadowDepthBuffers = 2;
    static int usedColorBuffers;
    static int usedDepthBuffers;
    static int usedShadowColorBuffers;
    static int usedShadowDepthBuffers;
    static int usedColorAttachs;
    static int usedDrawBuffers;
    static int dfb;
    static int sfb;
    private static int[] gbuffersFormat;
    public static boolean[] gbuffersClear;
    public static Vector4f[] gbuffersClearColor;
    private static Programs programs;
    public static final Program ProgramNone;
    public static final Program ProgramShadow;
    public static final Program ProgramShadowSolid;
    public static final Program ProgramShadowCutout;
    public static final Program ProgramBasic;
    public static final Program ProgramTextured;
    public static final Program ProgramTexturedLit;
    public static final Program ProgramSkyBasic;
    public static final Program ProgramSkyTextured;
    public static final Program ProgramClouds;
    public static final Program ProgramTerrain;
    public static final Program ProgramTerrainSolid;
    public static final Program ProgramTerrainCutoutMip;
    public static final Program ProgramTerrainCutout;
    public static final Program ProgramDamagedBlock;
    public static final Program ProgramBlock;
    public static final Program ProgramBeaconBeam;
    public static final Program ProgramItem;
    public static final Program ProgramEntities;
    public static final Program ProgramEntitiesGlowing;
    public static final Program ProgramArmorGlint;
    public static final Program ProgramSpiderEyes;
    public static final Program ProgramHand;
    public static final Program ProgramWeather;
    public static final Program ProgramDeferredPre;
    public static final Program[] ProgramsDeferred;
    public static final Program ProgramDeferred;
    public static final Program ProgramWater;
    public static final Program ProgramHandWater;
    public static final Program ProgramCompositePre;
    public static final Program[] ProgramsComposite;
    public static final Program ProgramComposite;
    public static final Program ProgramFinal;
    public static final int ProgramCount;
    public static final Program[] ProgramsAll;
    public static Program activeProgram;
    public static int activeProgramID;
    private static ProgramStack programStackLeash;
    private static boolean hasDeferredPrograms;
    static IntBuffer activeDrawBuffers;
    private static int activeCompositeMipmapSetting;
    public static Properties loadedShaders;
    public static Properties shadersConfig;
    public static cds defaultTexture;
    public static boolean[] shadowHardwareFilteringEnabled;
    public static boolean[] shadowMipmapEnabled;
    public static boolean[] shadowFilterNearest;
    public static boolean[] shadowColorMipmapEnabled;
    public static boolean[] shadowColorFilterNearest;
    public static boolean configTweakBlockDamage;
    public static boolean configCloudShadow;
    public static float configHandDepthMul;
    public static float configRenderResMul;
    public static float configShadowResMul;
    public static int configTexMinFilB;
    public static int configTexMinFilN;
    public static int configTexMinFilS;
    public static int configTexMagFilB;
    public static int configTexMagFilN;
    public static int configTexMagFilS;
    public static boolean configShadowClipFrustrum;
    public static boolean configNormalMap;
    public static boolean configSpecularMap;
    public static PropertyDefaultTrueFalse configOldLighting;
    public static PropertyDefaultTrueFalse configOldHandLight;
    public static int configAntialiasingLevel;
    public static final int texMinFilRange = 3;
    public static final int texMagFilRange = 2;
    public static final String[] texMinFilDesc;
    public static final String[] texMagFilDesc;
    public static final int[] texMinFilValue;
    public static final int[] texMagFilValue;
    private static IShaderPack shaderPack;
    public static boolean shaderPackLoaded;
    public static String currentShaderName;
    public static final String SHADER_PACK_NAME_NONE = "OFF";
    public static final String SHADER_PACK_NAME_DEFAULT = "(internal)";
    public static final String SHADER_PACKS_DIR_NAME = "shaderpacks";
    public static final String OPTIONS_FILE_NAME = "optionsshaders.txt";
    public static final File shaderPacksDir;
    static File configFile;
    private static ShaderOption[] shaderPackOptions;
    private static Set<String> shaderPackOptionSliders;
    static ShaderProfile[] shaderPackProfiles;
    static Map<String, ScreenShaderOptions> shaderPackGuiScreens;
    static Map<String, IExpressionBool> shaderPackProgramConditions;
    public static final String PATH_SHADERS_PROPERTIES = "/shaders/shaders.properties";
    public static PropertyDefaultFastFancyOff shaderPackClouds;
    public static PropertyDefaultTrueFalse shaderPackOldLighting;
    public static PropertyDefaultTrueFalse shaderPackOldHandLight;
    public static PropertyDefaultTrueFalse shaderPackDynamicHandLight;
    public static PropertyDefaultTrueFalse shaderPackShadowTranslucent;
    public static PropertyDefaultTrueFalse shaderPackUnderwaterOverlay;
    public static PropertyDefaultTrueFalse shaderPackSun;
    public static PropertyDefaultTrueFalse shaderPackMoon;
    public static PropertyDefaultTrueFalse shaderPackVignette;
    public static PropertyDefaultTrueFalse shaderPackBackFaceSolid;
    public static PropertyDefaultTrueFalse shaderPackBackFaceCutout;
    public static PropertyDefaultTrueFalse shaderPackBackFaceCutoutMipped;
    public static PropertyDefaultTrueFalse shaderPackBackFaceTranslucent;
    public static PropertyDefaultTrueFalse shaderPackRainDepth;
    public static PropertyDefaultTrueFalse shaderPackBeaconBeamDepth;
    public static PropertyDefaultTrueFalse shaderPackSeparateAo;
    public static PropertyDefaultTrueFalse shaderPackFrustumCulling;
    private static Map<String, String> shaderPackResources;
    private static amu currentWorld;
    private static List<Integer> shaderPackDimensions;
    private static ICustomTexture[] customTexturesGbuffers;
    private static ICustomTexture[] customTexturesComposite;
    private static ICustomTexture[] customTexturesDeferred;
    private static String noiseTexturePath;
    private static CustomUniforms customUniforms;
    private static final int STAGE_GBUFFERS = 0;
    private static final int STAGE_COMPOSITE = 1;
    private static final int STAGE_DEFERRED = 2;
    private static final String[] STAGE_NAMES;
    public static final boolean enableShadersOption = true;
    private static final boolean enableShadersDebug = true;
    public static final boolean saveFinalShaders;
    public static float blockLightLevel05;
    public static float blockLightLevel06;
    public static float blockLightLevel08;
    public static float aoLevel;
    public static float sunPathRotation;
    public static float shadowAngleInterval;
    public static int fogMode;
    public static float fogDensity;
    public static float fogColorR;
    public static float fogColorG;
    public static float fogColorB;
    public static float shadowIntervalSize;
    public static int terrainIconSize;
    public static int[] terrainTextureSize;
    private static ICustomTexture noiseTexture;
    private static boolean noiseTextureEnabled;
    private static int noiseTextureResolution;
    static final int[] colorTextureImageUnit;
    private static final int bigBufferSize;
    private static final ByteBuffer bigBuffer;
    static final float[] faProjection;
    static final float[] faProjectionInverse;
    static final float[] faModelView;
    static final float[] faModelViewInverse;
    static final float[] faShadowProjection;
    static final float[] faShadowProjectionInverse;
    static final float[] faShadowModelView;
    static final float[] faShadowModelViewInverse;
    static final FloatBuffer projection;
    static final FloatBuffer projectionInverse;
    static final FloatBuffer modelView;
    static final FloatBuffer modelViewInverse;
    static final FloatBuffer shadowProjection;
    static final FloatBuffer shadowProjectionInverse;
    static final FloatBuffer shadowModelView;
    static final FloatBuffer shadowModelViewInverse;
    static final FloatBuffer previousProjection;
    static final FloatBuffer previousModelView;
    static final FloatBuffer tempMatrixDirectBuffer;
    static final FloatBuffer tempDirectFloatBuffer;
    static final IntBuffer dfbColorTextures;
    static final IntBuffer dfbDepthTextures;
    static final IntBuffer sfbColorTextures;
    static final IntBuffer sfbDepthTextures;
    static final IntBuffer dfbDrawBuffers;
    static final IntBuffer sfbDrawBuffers;
    static final IntBuffer drawBuffersNone;
    static final IntBuffer drawBuffersColorAtt0;
    static final FlipTextures dfbColorTexturesFlip;
    static Map<aow, Integer> mapBlockToEntityData;
    private static final String[] formatNames;
    private static final int[] formatIds;
    private static final Pattern patternLoadEntityDataMap;
    public static int[] entityData;
    public static int entityDataIndex;

    private Shaders() {
    }

    private static ByteBuffer nextByteBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size);
        return buffer.slice();
    }

    public static IntBuffer nextIntBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size * 4);
        return buffer.asIntBuffer();
    }

    private static FloatBuffer nextFloatBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size * 4);
        return buffer.asFloatBuffer();
    }

    private static IntBuffer[] nextIntBufferArray(int count, int size) {
        IntBuffer[] aib = new IntBuffer[count];
        for (int i = 0; i < count; ++i) {
            aib[i] = Shaders.nextIntBuffer(size);
        }
        return aib;
    }

    public static void loadConfig() {
        SMCLog.info("Load shaders configuration.");
        try {
            if (!shaderPacksDir.exists()) {
                shaderPacksDir.mkdir();
            }
        }
        catch (Exception e) {
            SMCLog.severe("Failed to open the shaderpacks directory: " + shaderPacksDir);
        }
        shadersConfig = new PropertiesOrdered();
        shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
        if (configFile.exists()) {
            try {
                FileReader reader = new FileReader(configFile);
                shadersConfig.load(reader);
                reader.close();
            }
            catch (Exception reader) {
                // empty catch block
            }
        }
        if (!configFile.exists()) {
            try {
                Shaders.storeConfig();
            }
            catch (Exception reader) {
                // empty catch block
            }
        }
        EnumShaderOption[] ops = EnumShaderOption.values();
        for (int i = 0; i < ops.length; ++i) {
            EnumShaderOption op = ops[i];
            String key = op.getPropertyKey();
            String def = op.getValueDefault();
            String val = shadersConfig.getProperty(key, def);
            Shaders.setEnumShaderOption(op, val);
        }
        Shaders.loadShaderPack();
    }

    private static void setEnumShaderOption(EnumShaderOption eso, String str) {
        if (str == null) {
            str = eso.getValueDefault();
        }
        switch (eso) {
            case ANTIALIASING: {
                configAntialiasingLevel = Config.parseInt(str, 0);
                break;
            }
            case NORMAL_MAP: {
                configNormalMap = Config.parseBoolean(str, true);
                break;
            }
            case SPECULAR_MAP: {
                configSpecularMap = Config.parseBoolean(str, true);
                break;
            }
            case RENDER_RES_MUL: {
                configRenderResMul = Config.parseFloat(str, 1.0f);
                break;
            }
            case SHADOW_RES_MUL: {
                configShadowResMul = Config.parseFloat(str, 1.0f);
                break;
            }
            case HAND_DEPTH_MUL: {
                configHandDepthMul = Config.parseFloat(str, 0.125f);
                break;
            }
            case CLOUD_SHADOW: {
                configCloudShadow = Config.parseBoolean(str, true);
                break;
            }
            case OLD_HAND_LIGHT: {
                configOldHandLight.setPropertyValue(str);
                break;
            }
            case OLD_LIGHTING: {
                configOldLighting.setPropertyValue(str);
                break;
            }
            case SHADER_PACK: {
                currentShaderName = str;
                break;
            }
            case TWEAK_BLOCK_DAMAGE: {
                configTweakBlockDamage = Config.parseBoolean(str, true);
                break;
            }
            case SHADOW_CLIP_FRUSTRUM: {
                configShadowClipFrustrum = Config.parseBoolean(str, true);
                break;
            }
            case TEX_MIN_FIL_B: {
                configTexMinFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MIN_FIL_N: {
                configTexMinFilN = Config.parseInt(str, 0);
                break;
            }
            case TEX_MIN_FIL_S: {
                configTexMinFilS = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_B: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_N: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_S: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown option: " + (Object)((Object)eso));
            }
        }
    }

    public static void storeConfig() {
        SMCLog.info("Save shaders configuration.");
        if (shadersConfig == null) {
            shadersConfig = new PropertiesOrdered();
        }
        EnumShaderOption[] ops = EnumShaderOption.values();
        for (int i = 0; i < ops.length; ++i) {
            EnumShaderOption op = ops[i];
            String key = op.getPropertyKey();
            String val = Shaders.getEnumShaderOption(op);
            shadersConfig.setProperty(key, val);
        }
        try {
            FileWriter writer = new FileWriter(configFile);
            shadersConfig.store(writer, null);
            writer.close();
        }
        catch (Exception ex) {
            SMCLog.severe("Error saving configuration: " + ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public static String getEnumShaderOption(EnumShaderOption eso) {
        switch (eso) {
            case ANTIALIASING: {
                return Integer.toString(configAntialiasingLevel);
            }
            case NORMAL_MAP: {
                return Boolean.toString(configNormalMap);
            }
            case SPECULAR_MAP: {
                return Boolean.toString(configSpecularMap);
            }
            case RENDER_RES_MUL: {
                return Float.toString(configRenderResMul);
            }
            case SHADOW_RES_MUL: {
                return Float.toString(configShadowResMul);
            }
            case HAND_DEPTH_MUL: {
                return Float.toString(configHandDepthMul);
            }
            case CLOUD_SHADOW: {
                return Boolean.toString(configCloudShadow);
            }
            case OLD_HAND_LIGHT: {
                return configOldHandLight.getPropertyValue();
            }
            case OLD_LIGHTING: {
                return configOldLighting.getPropertyValue();
            }
            case SHADER_PACK: {
                return currentShaderName;
            }
            case TWEAK_BLOCK_DAMAGE: {
                return Boolean.toString(configTweakBlockDamage);
            }
            case SHADOW_CLIP_FRUSTRUM: {
                return Boolean.toString(configShadowClipFrustrum);
            }
            case TEX_MIN_FIL_B: {
                return Integer.toString(configTexMinFilB);
            }
            case TEX_MIN_FIL_N: {
                return Integer.toString(configTexMinFilN);
            }
            case TEX_MIN_FIL_S: {
                return Integer.toString(configTexMinFilS);
            }
            case TEX_MAG_FIL_B: {
                return Integer.toString(configTexMagFilB);
            }
            case TEX_MAG_FIL_N: {
                return Integer.toString(configTexMagFilB);
            }
            case TEX_MAG_FIL_S: {
                return Integer.toString(configTexMagFilB);
            }
        }
        throw new IllegalArgumentException("Unknown option: " + (Object)((Object)eso));
    }

    public static void setShaderPack(String par1name) {
        currentShaderName = par1name;
        shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), par1name);
        Shaders.loadShaderPack();
    }

    public static void loadShaderPack() {
        boolean oldLightingChanged;
        boolean shaderPackLoadedPrev = shaderPackLoaded;
        boolean oldLightingPrev = Shaders.isOldLighting();
        if (Shaders.mc.g != null) {
            Shaders.mc.g.pauseChunkUpdates();
        }
        shaderPackLoaded = false;
        if (shaderPack != null) {
            shaderPack.close();
            shaderPack = null;
            shaderPackResources.clear();
            shaderPackDimensions.clear();
            shaderPackOptions = null;
            shaderPackOptionSliders = null;
            shaderPackProfiles = null;
            shaderPackGuiScreens = null;
            shaderPackProgramConditions.clear();
            shaderPackClouds.resetValue();
            shaderPackOldHandLight.resetValue();
            shaderPackDynamicHandLight.resetValue();
            shaderPackOldLighting.resetValue();
            Shaders.resetCustomTextures();
            noiseTexturePath = null;
        }
        boolean shadersBlocked = false;
        if (Config.isAntialiasing()) {
            SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
            shadersBlocked = true;
        }
        if (Config.isAnisotropicFiltering()) {
            SMCLog.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + Config.getAnisotropicFilterLevel() + "x");
            shadersBlocked = true;
        }
        if (Config.isFastRender()) {
            SMCLog.info("Shaders can not be loaded, Fast Render is enabled.");
            shadersBlocked = true;
        }
        String packName = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), SHADER_PACK_NAME_DEFAULT);
        if (!shadersBlocked) {
            shaderPack = Shaders.getShaderPack(packName);
            boolean bl = shaderPackLoaded = shaderPack != null;
        }
        if (shaderPackLoaded) {
            SMCLog.info("Loaded shaderpack: " + Shaders.getShaderPackName());
        } else {
            SMCLog.info("No shaderpack loaded.");
            shaderPack = new ShaderPackNone();
        }
        if (saveFinalShaders) {
            Shaders.clearDirectory(new File(shaderPacksDir, "debug"));
        }
        Shaders.loadShaderPackResources();
        Shaders.loadShaderPackDimensions();
        shaderPackOptions = Shaders.loadShaderPackOptions();
        Shaders.loadShaderPackProperties();
        boolean formatChanged = shaderPackLoaded != shaderPackLoadedPrev;
        boolean bl = oldLightingChanged = Shaders.isOldLighting() != oldLightingPrev;
        if (formatChanged || oldLightingChanged) {
            cdy.updateVertexFormats();
            if (Reflector.LightUtil.exists()) {
                Reflector.LightUtil_itemConsumer.setValue(null);
                Reflector.LightUtil_tessellator.setValue(null);
            }
            Shaders.updateBlockLightLevel();
        }
        if (mc.P() != null) {
            CustomBlockLayers.update();
        }
        if (Shaders.mc.g != null) {
            Shaders.mc.g.resumeChunkUpdates();
        }
        if ((formatChanged || oldLightingChanged) && mc.O() != null) {
            mc.A();
        }
    }

    public static IShaderPack getShaderPack(String name) {
        if (name == null) {
            return null;
        }
        if ((name = name.trim()).isEmpty() || name.equals(SHADER_PACK_NAME_NONE)) {
            return null;
        }
        if (name.equals(SHADER_PACK_NAME_DEFAULT)) {
            return new ShaderPackDefault();
        }
        try {
            File packFile = new File(shaderPacksDir, name);
            if (packFile.isDirectory()) {
                return new ShaderPackFolder(name, packFile);
            }
            if (packFile.isFile() && name.toLowerCase().endsWith(".zip")) {
                return new ShaderPackZip(name, packFile);
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IShaderPack getShaderPack() {
        return shaderPack;
    }

    private static void loadShaderPackDimensions() {
        shaderPackDimensions.clear();
        for (int i = -128; i <= 128; ++i) {
            String worldDir = "/shaders/world" + i;
            if (!shaderPack.hasDirectory(worldDir)) continue;
            shaderPackDimensions.add(i);
        }
        if (shaderPackDimensions.size() > 0) {
            Object[] ids = shaderPackDimensions.toArray(new Integer[shaderPackDimensions.size()]);
            Config.dbg("[Shaders] Worlds: " + Config.arrayToString(ids));
        }
    }

    private static void loadShaderPackProperties() {
        shaderPackClouds.resetValue();
        shaderPackOldHandLight.resetValue();
        shaderPackDynamicHandLight.resetValue();
        shaderPackOldLighting.resetValue();
        shaderPackShadowTranslucent.resetValue();
        shaderPackUnderwaterOverlay.resetValue();
        shaderPackSun.resetValue();
        shaderPackMoon.resetValue();
        shaderPackVignette.resetValue();
        shaderPackBackFaceSolid.resetValue();
        shaderPackBackFaceCutout.resetValue();
        shaderPackBackFaceCutoutMipped.resetValue();
        shaderPackBackFaceTranslucent.resetValue();
        shaderPackRainDepth.resetValue();
        shaderPackBeaconBeamDepth.resetValue();
        shaderPackSeparateAo.resetValue();
        shaderPackFrustumCulling.resetValue();
        BlockAliases.reset();
        ItemAliases.reset();
        EntityAliases.reset();
        customUniforms = null;
        for (int i = 0; i < ProgramsAll.length; ++i) {
            Program p = ProgramsAll[i];
            p.resetProperties();
        }
        if (shaderPack == null) {
            return;
        }
        BlockAliases.update(shaderPack);
        ItemAliases.update(shaderPack);
        EntityAliases.update(shaderPack);
        String path = PATH_SHADERS_PROPERTIES;
        try {
            InputStream in = shaderPack.getResourceAsStream(path);
            if (in == null) {
                return;
            }
            in = MacroProcessor.process(in, path);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            shaderPackClouds.loadFrom(props);
            shaderPackOldHandLight.loadFrom(props);
            shaderPackDynamicHandLight.loadFrom(props);
            shaderPackOldLighting.loadFrom(props);
            shaderPackShadowTranslucent.loadFrom(props);
            shaderPackUnderwaterOverlay.loadFrom(props);
            shaderPackSun.loadFrom(props);
            shaderPackVignette.loadFrom(props);
            shaderPackMoon.loadFrom(props);
            shaderPackBackFaceSolid.loadFrom(props);
            shaderPackBackFaceCutout.loadFrom(props);
            shaderPackBackFaceCutoutMipped.loadFrom(props);
            shaderPackBackFaceTranslucent.loadFrom(props);
            shaderPackRainDepth.loadFrom(props);
            shaderPackBeaconBeamDepth.loadFrom(props);
            shaderPackSeparateAo.loadFrom(props);
            shaderPackFrustumCulling.loadFrom(props);
            shaderPackOptionSliders = ShaderPackParser.parseOptionSliders(props, shaderPackOptions);
            shaderPackProfiles = ShaderPackParser.parseProfiles(props, shaderPackOptions);
            shaderPackGuiScreens = ShaderPackParser.parseGuiScreens(props, shaderPackProfiles, shaderPackOptions);
            shaderPackProgramConditions = ShaderPackParser.parseProgramConditions(props, shaderPackOptions);
            customTexturesGbuffers = Shaders.loadCustomTextures(props, 0);
            customTexturesComposite = Shaders.loadCustomTextures(props, 1);
            customTexturesDeferred = Shaders.loadCustomTextures(props, 2);
            noiseTexturePath = props.getProperty("texture.noise");
            if (noiseTexturePath != null) {
                noiseTextureEnabled = true;
            }
            customUniforms = ShaderPackParser.parseCustomUniforms(props);
            ShaderPackParser.parseAlphaStates(props);
            ShaderPackParser.parseBlendStates(props);
            ShaderPackParser.parseRenderScales(props);
            ShaderPackParser.parseBuffersFlip(props);
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + path);
        }
    }

    private static ICustomTexture[] loadCustomTextures(Properties props, int stage) {
        String PREFIX_TEXTURE = "texture." + STAGE_NAMES[stage] + ".";
        Set<Object> keys = props.keySet();
        ArrayList<ICustomTexture> list = new ArrayList<ICustomTexture>();
        for (String string : keys) {
            if (!string.startsWith(PREFIX_TEXTURE)) continue;
            String name = StrUtils.removePrefix(string, PREFIX_TEXTURE);
            name = StrUtils.removeSuffix(name, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String path = props.getProperty(string).trim();
            int index = Shaders.getTextureIndex(stage, name);
            if (index < 0) {
                SMCLog.warning("Invalid texture name: " + string);
                continue;
            }
            ICustomTexture ct = Shaders.loadCustomTexture(index, path);
            if (ct == null) continue;
            SMCLog.info("Custom texture: " + string + " = " + path);
            list.add(ct);
        }
        if (list.size() <= 0) {
            return null;
        }
        ICustomTexture[] cts = list.toArray(new ICustomTexture[list.size()]);
        return cts;
    }

    private static ICustomTexture loadCustomTexture(int textureUnit, String path) {
        if (path == null) {
            return null;
        }
        if ((path = path.trim()).indexOf(58) >= 0) {
            return Shaders.loadCustomTextureLocation(textureUnit, path);
        }
        if (path.indexOf(32) >= 0) {
            return Shaders.loadCustomTextureRaw(textureUnit, path);
        }
        return Shaders.loadCustomTextureShaders(textureUnit, path);
    }

    private static ICustomTexture loadCustomTextureLocation(int textureUnit, String path) {
        String pathFull = path.trim();
        int variant = 0;
        if (pathFull.startsWith("minecraft:textures/")) {
            if ((pathFull = StrUtils.addSuffixCheck(pathFull, ".png")).endsWith("_n.png")) {
                pathFull = StrUtils.replaceSuffix(pathFull, "_n.png", ".png");
                variant = 1;
            } else if (pathFull.endsWith("_s.png")) {
                pathFull = StrUtils.replaceSuffix(pathFull, "_s.png", ".png");
                variant = 2;
            }
        }
        nf loc = new nf(pathFull);
        CustomTextureLocation ctv = new CustomTextureLocation(textureUnit, loc, variant);
        return ctv;
    }

    private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line) {
        ConnectedParser cp = new ConnectedParser("Shaders");
        String[] parts = Config.tokenize(line, " ");
        ArrayDeque<String> params = new ArrayDeque<String>(Arrays.asList(parts));
        String path = (String)params.poll();
        TextureType type = (TextureType)cp.parseEnum((String)params.poll(), TextureType.values(), "texture type");
        if (type == null) {
            SMCLog.warning("Invalid raw texture type: " + line);
            return null;
        }
        InternalFormat internalFormat = (InternalFormat)cp.parseEnum((String)params.poll(), InternalFormat.values(), "internal format");
        if (internalFormat == null) {
            SMCLog.warning("Invalid raw texture internal format: " + line);
            return null;
        }
        int width = 0;
        int height = 0;
        int depth = 0;
        switch (type) {
            case TEXTURE_1D: {
                width = cp.parseInt((String)params.poll(), -1);
                break;
            }
            case TEXTURE_2D: {
                width = cp.parseInt((String)params.poll(), -1);
                height = cp.parseInt((String)params.poll(), -1);
                break;
            }
            case TEXTURE_3D: {
                width = cp.parseInt((String)params.poll(), -1);
                height = cp.parseInt((String)params.poll(), -1);
                depth = cp.parseInt((String)params.poll(), -1);
                break;
            }
            case TEXTURE_RECTANGLE: {
                width = cp.parseInt((String)params.poll(), -1);
                height = cp.parseInt((String)params.poll(), -1);
                break;
            }
            default: {
                SMCLog.warning("Invalid raw texture type: " + (Object)((Object)type));
                return null;
            }
        }
        if (width < 0 || height < 0 || depth < 0) {
            SMCLog.warning("Invalid raw texture size: " + line);
            return null;
        }
        PixelFormat pixelFormat = (PixelFormat)cp.parseEnum((String)params.poll(), PixelFormat.values(), "pixel format");
        if (pixelFormat == null) {
            SMCLog.warning("Invalid raw texture pixel format: " + line);
            return null;
        }
        PixelType pixelType = (PixelType)cp.parseEnum((String)params.poll(), PixelType.values(), "pixel type");
        if (pixelType == null) {
            SMCLog.warning("Invalid raw texture pixel type: " + line);
            return null;
        }
        if (!params.isEmpty()) {
            SMCLog.warning("Invalid raw texture, too many parameters: " + line);
            return null;
        }
        return Shaders.loadCustomTextureRaw(textureUnit, line, path, type, internalFormat, width, height, depth, pixelFormat, pixelType);
    }

    private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line, String path, TextureType type, InternalFormat internalFormat, int width, int height, int depth, PixelFormat pixelFormat, PixelType pixelType) {
        try {
            String pathFull = "shaders/" + StrUtils.removePrefix(path, "/");
            InputStream in = shaderPack.getResourceAsStream(pathFull);
            if (in == null) {
                SMCLog.warning("Raw texture not found: " + path);
                return null;
            }
            byte[] bytes = Config.readAll(in);
            IOUtils.closeQuietly((InputStream)in);
            ByteBuffer bb = bia.c((int)bytes.length);
            bb.put(bytes);
            bb.flip();
            CustomTextureRaw ctr = new CustomTextureRaw(type, internalFormat, width, height, depth, pixelFormat, pixelType, bb, textureUnit);
            return ctr;
        }
        catch (IOException e) {
            SMCLog.warning("Error loading raw texture: " + path);
            SMCLog.warning("" + e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private static ICustomTexture loadCustomTextureShaders(int textureUnit, String path) {
        if ((path = path.trim()).indexOf(46) < 0) {
            path = path + ".png";
        }
        try {
            String pathFull = "shaders/" + StrUtils.removePrefix(path, "/");
            InputStream in = shaderPack.getResourceAsStream(pathFull);
            if (in == null) {
                SMCLog.warning("Texture not found: " + path);
                return null;
            }
            IOUtils.closeQuietly((InputStream)in);
            SimpleShaderTexture tex = new SimpleShaderTexture(pathFull);
            tex.a(mc.O());
            CustomTexture ct = new CustomTexture(textureUnit, pathFull, (cds)tex);
            return ct;
        }
        catch (IOException e) {
            SMCLog.warning("Error loading texture: " + path);
            SMCLog.warning("" + e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private static int getTextureIndex(int stage, String name) {
        if (stage == 0) {
            if (name.equals("texture")) {
                return 0;
            }
            if (name.equals("lightmap")) {
                return 1;
            }
            if (name.equals("normals")) {
                return 2;
            }
            if (name.equals("specular")) {
                return 3;
            }
            if (name.equals("shadowtex0") || name.equals("watershadow")) {
                return 4;
            }
            if (name.equals("shadow")) {
                return waterShadowEnabled ? 5 : 4;
            }
            if (name.equals("shadowtex1")) {
                return 5;
            }
            if (name.equals("depthtex0")) {
                return 6;
            }
            if (name.equals("gaux1")) {
                return 7;
            }
            if (name.equals("gaux2")) {
                return 8;
            }
            if (name.equals("gaux3")) {
                return 9;
            }
            if (name.equals("gaux4")) {
                return 10;
            }
            if (name.equals("depthtex1")) {
                return 12;
            }
            if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
                return 13;
            }
            if (name.equals("shadowcolor1")) {
                return 14;
            }
            if (name.equals("noisetex")) {
                return 15;
            }
        }
        if (stage == 1 || stage == 2) {
            if (name.equals("colortex0") || name.equals("colortex0")) {
                return 0;
            }
            if (name.equals("colortex1") || name.equals("gdepth")) {
                return 1;
            }
            if (name.equals("colortex2") || name.equals("gnormal")) {
                return 2;
            }
            if (name.equals("colortex3") || name.equals("composite")) {
                return 3;
            }
            if (name.equals("shadowtex0") || name.equals("watershadow")) {
                return 4;
            }
            if (name.equals("shadow")) {
                return waterShadowEnabled ? 5 : 4;
            }
            if (name.equals("shadowtex1")) {
                return 5;
            }
            if (name.equals("depthtex0") || name.equals("gdepthtex")) {
                return 6;
            }
            if (name.equals("colortex4") || name.equals("gaux1")) {
                return 7;
            }
            if (name.equals("colortex5") || name.equals("gaux2")) {
                return 8;
            }
            if (name.equals("colortex6") || name.equals("gaux3")) {
                return 9;
            }
            if (name.equals("colortex7") || name.equals("gaux4")) {
                return 10;
            }
            if (name.equals("depthtex1")) {
                return 11;
            }
            if (name.equals("depthtex2")) {
                return 12;
            }
            if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
                return 13;
            }
            if (name.equals("shadowcolor1")) {
                return 14;
            }
            if (name.equals("noisetex")) {
                return 15;
            }
        }
        return -1;
    }

    private static void bindCustomTextures(ICustomTexture[] cts) {
        if (cts == null) {
            return;
        }
        for (int i = 0; i < cts.length; ++i) {
            ICustomTexture ct = cts[i];
            bus.g((int)(33984 + ct.getTextureUnit()));
            int texId = ct.getTextureId();
            int target = ct.getTarget();
            if (target == 3553) {
                bus.i((int)texId);
                continue;
            }
            GL11.glBindTexture((int)target, (int)texId);
        }
    }

    private static void resetCustomTextures() {
        Shaders.deleteCustomTextures(customTexturesGbuffers);
        Shaders.deleteCustomTextures(customTexturesComposite);
        Shaders.deleteCustomTextures(customTexturesDeferred);
        customTexturesGbuffers = null;
        customTexturesComposite = null;
        customTexturesDeferred = null;
    }

    private static void deleteCustomTextures(ICustomTexture[] cts) {
        if (cts == null) {
            return;
        }
        for (int i = 0; i < cts.length; ++i) {
            ICustomTexture ct = cts[i];
            ct.deleteTexture();
        }
    }

    public static ShaderOption[] getShaderPackOptions(String screenName) {
        Object[] ops = (ShaderOption[])shaderPackOptions.clone();
        if (shaderPackGuiScreens == null) {
            if (shaderPackProfiles != null) {
                ShaderOptionProfile optionProfile = new ShaderOptionProfile(shaderPackProfiles, (ShaderOption[])ops);
                ops = (ShaderOption[])Config.addObjectToArray(ops, optionProfile, 0);
            }
            ops = Shaders.getVisibleOptions((ShaderOption[])ops);
            return ops;
        }
        String key = screenName != null ? "screen." + screenName : "screen";
        ScreenShaderOptions sso = shaderPackGuiScreens.get(key);
        if (sso == null) {
            return new ShaderOption[0];
        }
        ShaderOption[] sos = sso.getShaderOptions();
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < sos.length; ++i) {
            ShaderOption so = sos[i];
            if (so == null) {
                list.add(null);
                continue;
            }
            if (so instanceof ShaderOptionRest) {
                ShaderOption[] restOps = Shaders.getShaderOptionsRest(shaderPackGuiScreens, (ShaderOption[])ops);
                list.addAll(Arrays.asList(restOps));
                continue;
            }
            list.add(so);
        }
        ShaderOption[] sosExp = list.toArray(new ShaderOption[list.size()]);
        return sosExp;
    }

    public static int getShaderPackColumns(String screenName, int def) {
        String key;
        String string = key = screenName != null ? "screen." + screenName : "screen";
        if (shaderPackGuiScreens == null) {
            return def;
        }
        ScreenShaderOptions sso = shaderPackGuiScreens.get(key);
        if (sso == null) {
            return def;
        }
        return sso.getColumns();
    }

    private static ShaderOption[] getShaderOptionsRest(Map<String, ScreenShaderOptions> mapScreens, ShaderOption[] ops) {
        HashSet<String> setNames = new HashSet<String>();
        Set<String> keys = mapScreens.keySet();
        for (String key : keys) {
            ScreenShaderOptions sso = mapScreens.get(key);
            ShaderOption[] sos = sso.getShaderOptions();
            for (int v = 0; v < sos.length; ++v) {
                ShaderOption so = sos[v];
                if (so == null) continue;
                setNames.add(so.getName());
            }
        }
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ops.length; ++i) {
            String name;
            ShaderOption so = ops[i];
            if (!so.isVisible() || setNames.contains(name = so.getName())) continue;
            list.add(so);
        }
        ShaderOption[] sos = list.toArray(new ShaderOption[list.size()]);
        return sos;
    }

    public static ShaderOption getShaderOption(String name) {
        return ShaderUtils.getShaderOption(name, shaderPackOptions);
    }

    public static ShaderOption[] getShaderPackOptions() {
        return shaderPackOptions;
    }

    public static boolean isShaderPackOptionSlider(String name) {
        if (shaderPackOptionSliders == null) {
            return false;
        }
        return shaderPackOptionSliders.contains(name);
    }

    private static ShaderOption[] getVisibleOptions(ShaderOption[] ops) {
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ops.length; ++i) {
            ShaderOption so = ops[i];
            if (!so.isVisible()) continue;
            list.add(so);
        }
        ShaderOption[] sos = list.toArray(new ShaderOption[list.size()]);
        return sos;
    }

    public static void saveShaderPackOptions() {
        Shaders.saveShaderPackOptions(shaderPackOptions, shaderPack);
    }

    private static void saveShaderPackOptions(ShaderOption[] sos, IShaderPack sp) {
        PropertiesOrdered props = new PropertiesOrdered();
        if (shaderPackOptions != null) {
            for (int i = 0; i < sos.length; ++i) {
                ShaderOption so = sos[i];
                if (!so.isChanged() || !so.isEnabled()) continue;
                props.setProperty(so.getName(), so.getValue());
            }
        }
        try {
            Shaders.saveOptionProperties(sp, props);
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
            e.printStackTrace();
        }
    }

    private static void saveOptionProperties(IShaderPack sp, Properties props) throws IOException {
        String path = "shaderpacks/" + sp.getName() + ".txt";
        File propFile = new File(bib.z().w, path);
        if (props.isEmpty()) {
            propFile.delete();
            return;
        }
        FileOutputStream fos = new FileOutputStream(propFile);
        props.store(fos, null);
        fos.flush();
        fos.close();
    }

    private static ShaderOption[] loadShaderPackOptions() {
        try {
            String[] programNames = programs.getProgramNames();
            ShaderOption[] sos = ShaderPackParser.parseShaderPackOptions(shaderPack, programNames, shaderPackDimensions);
            Properties props = Shaders.loadOptionProperties(shaderPack);
            for (int i = 0; i < sos.length; ++i) {
                ShaderOption so = sos[i];
                String val = props.getProperty(so.getName());
                if (val == null) continue;
                so.resetValue();
                if (so.setValue(val)) continue;
                Config.warn("[Shaders] Invalid value, option: " + so.getName() + ", value: " + val);
            }
            return sos;
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadOptionProperties(IShaderPack sp) throws IOException {
        PropertiesOrdered props = new PropertiesOrdered();
        String path = "shaderpacks/" + sp.getName() + ".txt";
        File propFile = new File(bib.z().w, path);
        if (!(propFile.exists() && propFile.isFile() && propFile.canRead())) {
            return props;
        }
        FileInputStream fis = new FileInputStream(propFile);
        props.load(fis);
        fis.close();
        return props;
    }

    public static ShaderOption[] getChangedOptions(ShaderOption[] ops) {
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ops.length; ++i) {
            ShaderOption op = ops[i];
            if (!op.isEnabled() || !op.isChanged()) continue;
            list.add(op);
        }
        ShaderOption[] cops = list.toArray(new ShaderOption[list.size()]);
        return cops;
    }

    private static String applyOptions(String line, ShaderOption[] ops) {
        if (ops == null || ops.length <= 0) {
            return line;
        }
        for (int i = 0; i < ops.length; ++i) {
            ShaderOption op = ops[i];
            if (!op.matchesLine(line)) continue;
            line = op.getSourceLine();
            break;
        }
        return line;
    }

    public static ArrayList listOfShaders() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(SHADER_PACK_NAME_NONE);
        list.add(SHADER_PACK_NAME_DEFAULT);
        int countFixed = list.size();
        try {
            if (!shaderPacksDir.exists()) {
                shaderPacksDir.mkdir();
            }
            File[] listOfFiles = shaderPacksDir.listFiles();
            for (int i = 0; i < listOfFiles.length; ++i) {
                File file = listOfFiles[i];
                String name = file.getName();
                if (file.isDirectory()) {
                    File subDir;
                    if (name.equals("debug") || !(subDir = new File(file, "shaders")).exists() || !subDir.isDirectory()) continue;
                    list.add(name);
                    continue;
                }
                if (!file.isFile() || !name.toLowerCase().endsWith(".zip")) continue;
                list.add(name);
            }
        }
        catch (Exception listOfFiles) {
            // empty catch block
        }
        List sortList = list.subList(countFixed, list.size());
        Collections.sort(sortList, String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public static int checkFramebufferStatus(String location) {
        int status = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
        if (status != 36053) {
            System.err.format("FramebufferStatus 0x%04X at %s\n", status, location);
        }
        return status;
    }

    public static int checkGLError(String location) {
        int errorCode = bus.L();
        if (errorCode != 0 && GlErrors.isEnabled(errorCode)) {
            String errorText = Config.getGlErrorString(errorCode);
            String shadersInfo = Shaders.getErrorInfo(errorCode, location);
            String messageLog = String.format("OpenGL error: %s (%s)%s, at: %s", errorCode, errorText, shadersInfo, location);
            SMCLog.severe(messageLog);
            if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlErrorShaders", 10000L)) {
                String messageChat = cey.a((String)"of.message.openglError", (Object[])new Object[]{errorCode, errorText});
                Shaders.printChat(messageChat);
            }
        }
        return errorCode;
    }

    private static String getErrorInfo(int errorCode, String location) {
        String programName;
        StringBuilder sb = new StringBuilder();
        if (errorCode == 1286) {
            int statusCode = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
            String statusText = Shaders.getFramebufferStatusText(statusCode);
            String info = ", fbStatus: " + statusCode + " (" + statusText + ")";
            sb.append(info);
        }
        if ((programName = activeProgram.getName()).isEmpty()) {
            programName = "none";
        }
        sb.append(", program: " + programName);
        Program activeProgramReal = Shaders.getProgramById(activeProgramID);
        if (activeProgramReal != activeProgram) {
            String programRealName = activeProgramReal.getName();
            if (programRealName.isEmpty()) {
                programRealName = "none";
            }
            sb.append(" (" + programRealName + ")");
        }
        if (location.equals("setDrawBuffers")) {
            sb.append(", drawBuffers: " + activeProgram.getDrawBufSettings());
        }
        return sb.toString();
    }

    private static Program getProgramById(int programID) {
        for (int i = 0; i < ProgramsAll.length; ++i) {
            Program pi = ProgramsAll[i];
            if (pi.getId() != programID) continue;
            return pi;
        }
        return ProgramNone;
    }

    private static String getFramebufferStatusText(int fbStatusCode) {
        switch (fbStatusCode) {
            case 36053: {
                return "Complete";
            }
            case 33305: {
                return "Undefined";
            }
            case 36054: {
                return "Incomplete attachment";
            }
            case 36055: {
                return "Incomplete missing attachment";
            }
            case 36059: {
                return "Incomplete draw buffer";
            }
            case 36060: {
                return "Incomplete read buffer";
            }
            case 36061: {
                return "Unsupported";
            }
            case 36182: {
                return "Incomplete multisample";
            }
            case 36264: {
                return "Incomplete layer targets";
            }
        }
        return "Unknown";
    }

    private static void printChat(String str) {
        Shaders.mc.q.d().a((hh)new ho(str));
    }

    private static void printChatAndLogError(String str) {
        SMCLog.severe(str);
        Shaders.mc.q.d().a((hh)new ho(str));
    }

    public static void printIntBuffer(String title, IntBuffer buf) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
        int lim = buf.limit();
        for (int i = 0; i < lim; ++i) {
            sb.append(" ").append(buf.get(i));
        }
        sb.append("]");
        SMCLog.info(sb.toString());
    }

    public static void startup(bib mc) {
        Shaders.checkShadersModInstalled();
        Shaders.mc = mc;
        mc = bib.z();
        capabilities = GLContext.getCapabilities();
        glVersionString = GL11.glGetString((int)7938);
        glVendorString = GL11.glGetString((int)7936);
        glRendererString = GL11.glGetString((int)7937);
        SMCLog.info("OpenGL Version: " + glVersionString);
        SMCLog.info("Vendor:  " + glVendorString);
        SMCLog.info("Renderer: " + glRendererString);
        SMCLog.info("Capabilities: " + (Shaders.capabilities.OpenGL20 ? " 2.0 " : " - ") + (Shaders.capabilities.OpenGL21 ? " 2.1 " : " - ") + (Shaders.capabilities.OpenGL30 ? " 3.0 " : " - ") + (Shaders.capabilities.OpenGL32 ? " 3.2 " : " - ") + (Shaders.capabilities.OpenGL40 ? " 4.0 " : " - "));
        SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger((int)34852));
        SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger((int)36063));
        SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger((int)34930));
        hasGlGenMipmap = Shaders.capabilities.OpenGL30;
        Shaders.loadConfig();
    }

    public static void updateBlockLightLevel() {
        if (Shaders.isOldLighting()) {
            blockLightLevel05 = 0.5f;
            blockLightLevel06 = 0.6f;
            blockLightLevel08 = 0.8f;
        } else {
            blockLightLevel05 = 1.0f;
            blockLightLevel06 = 1.0f;
            blockLightLevel08 = 1.0f;
        }
    }

    public static boolean isOldHandLight() {
        if (!configOldHandLight.isDefault()) {
            return configOldHandLight.isTrue();
        }
        if (!shaderPackOldHandLight.isDefault()) {
            return shaderPackOldHandLight.isTrue();
        }
        return true;
    }

    public static boolean isDynamicHandLight() {
        if (!shaderPackDynamicHandLight.isDefault()) {
            return shaderPackDynamicHandLight.isTrue();
        }
        return true;
    }

    public static boolean isOldLighting() {
        if (!configOldLighting.isDefault()) {
            return configOldLighting.isTrue();
        }
        if (!shaderPackOldLighting.isDefault()) {
            return shaderPackOldLighting.isTrue();
        }
        return true;
    }

    public static boolean isRenderShadowTranslucent() {
        return !shaderPackShadowTranslucent.isFalse();
    }

    public static boolean isUnderwaterOverlay() {
        return !shaderPackUnderwaterOverlay.isFalse();
    }

    public static boolean isSun() {
        return !shaderPackSun.isFalse();
    }

    public static boolean isMoon() {
        return !shaderPackMoon.isFalse();
    }

    public static boolean isVignette() {
        return !shaderPackVignette.isFalse();
    }

    public static boolean isRenderBackFace(amm blockLayerIn) {
        switch (blockLayerIn) {
            case a: {
                return shaderPackBackFaceSolid.isTrue();
            }
            case c: {
                return shaderPackBackFaceCutout.isTrue();
            }
            case b: {
                return shaderPackBackFaceCutoutMipped.isTrue();
            }
            case d: {
                return shaderPackBackFaceTranslucent.isTrue();
            }
        }
        return false;
    }

    public static boolean isRainDepth() {
        return shaderPackRainDepth.isTrue();
    }

    public static boolean isBeaconBeamDepth() {
        return shaderPackBeaconBeamDepth.isTrue();
    }

    public static boolean isSeparateAo() {
        return shaderPackSeparateAo.isTrue();
    }

    public static boolean isFrustumCulling() {
        return !shaderPackFrustumCulling.isFalse();
    }

    public static void init() {
        boolean firstInit;
        if (!isInitializedOnce) {
            isInitializedOnce = true;
            firstInit = true;
        } else {
            firstInit = false;
        }
        if (!isShaderPackInitialized) {
            int i;
            int i2;
            int dimId;
            Shaders.checkGLError("Shaders.init pre");
            if (Shaders.getShaderPackName() != null) {
                // empty if block
            }
            if (!Shaders.capabilities.OpenGL20) {
                Shaders.printChatAndLogError("No OpenGL 2.0");
            }
            if (!Shaders.capabilities.GL_EXT_framebuffer_object) {
                Shaders.printChatAndLogError("No EXT_framebuffer_object");
            }
            dfbDrawBuffers.position(0).limit(8);
            dfbColorTextures.position(0).limit(16);
            dfbDepthTextures.position(0).limit(3);
            sfbDrawBuffers.position(0).limit(8);
            sfbDepthTextures.position(0).limit(2);
            sfbColorTextures.position(0).limit(8);
            usedColorBuffers = 4;
            usedDepthBuffers = 1;
            usedShadowColorBuffers = 0;
            usedShadowDepthBuffers = 0;
            usedColorAttachs = 1;
            usedDrawBuffers = 1;
            Arrays.fill(gbuffersFormat, 6408);
            Arrays.fill(gbuffersClear, true);
            Arrays.fill(gbuffersClearColor, null);
            Arrays.fill(shadowHardwareFilteringEnabled, false);
            Arrays.fill(shadowMipmapEnabled, false);
            Arrays.fill(shadowFilterNearest, false);
            Arrays.fill(shadowColorMipmapEnabled, false);
            Arrays.fill(shadowColorFilterNearest, false);
            centerDepthSmoothEnabled = false;
            noiseTextureEnabled = false;
            sunPathRotation = 0.0f;
            shadowIntervalSize = 2.0f;
            shadowMapWidth = 1024;
            shadowMapHeight = 1024;
            spShadowMapWidth = 1024;
            spShadowMapHeight = 1024;
            shadowMapFOV = 90.0f;
            shadowMapHalfPlane = 160.0f;
            shadowMapIsOrtho = true;
            shadowDistanceRenderMul = -1.0f;
            aoLevel = -1.0f;
            useEntityAttrib = false;
            useMidTexCoordAttrib = false;
            useTangentAttrib = false;
            waterShadowEnabled = false;
            hasGeometryShaders = false;
            Shaders.updateBlockLightLevel();
            Smoother.resetValues();
            shaderUniforms.reset();
            if (customUniforms != null) {
                customUniforms.reset();
            }
            ShaderProfile activeProfile = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
            String worldPrefix = "";
            if (currentWorld != null && shaderPackDimensions.contains(dimId = Shaders.currentWorld.s.q().a())) {
                worldPrefix = "world" + dimId + "/";
            }
            for (i2 = 0; i2 < ProgramsAll.length; ++i2) {
                Program p = ProgramsAll[i2];
                p.resetId();
                p.resetConfiguration();
                if (p.getProgramStage() == ProgramStage.NONE) continue;
                String programName = p.getName();
                String programPath = worldPrefix + programName;
                boolean enabled = true;
                if (shaderPackProgramConditions.containsKey(programPath)) {
                    boolean bl = enabled = enabled && shaderPackProgramConditions.get(programPath).eval();
                }
                if (activeProfile != null) {
                    boolean bl = enabled = enabled && !activeProfile.isProgramDisabled(programPath);
                }
                if (!enabled) {
                    SMCLog.info("Program disabled: " + programPath);
                    programName = "<disabled>";
                    programPath = worldPrefix + programName;
                }
                String programFullPath = "/shaders/" + programPath;
                String programFullPathVertex = programFullPath + ".vsh";
                String programFullPathGeometry = programFullPath + ".gsh";
                String programFullPathFragment = programFullPath + ".fsh";
                Shaders.setupProgram(p, programFullPathVertex, programFullPathGeometry, programFullPathFragment);
                int pr = p.getId();
                if (pr > 0) {
                    SMCLog.info("Program loaded: " + programPath);
                }
                Shaders.initDrawBuffers(p);
                Shaders.updateToggleBuffers(p);
            }
            hasDeferredPrograms = false;
            for (int cp = 0; cp < ProgramsDeferred.length; ++cp) {
                if (ProgramsDeferred[cp].getId() == 0) continue;
                hasDeferredPrograms = true;
                break;
            }
            usedColorAttachs = usedColorBuffers;
            shadowPassInterval = usedShadowDepthBuffers > 0 ? 1 : 0;
            shouldSkipDefaultShadow = usedShadowDepthBuffers > 0;
            SMCLog.info("usedColorBuffers: " + usedColorBuffers);
            SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
            SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
            SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
            SMCLog.info("usedColorAttachs: " + usedColorAttachs);
            SMCLog.info("usedDrawBuffers: " + usedDrawBuffers);
            dfbDrawBuffers.position(0).limit(usedDrawBuffers);
            dfbColorTextures.position(0).limit(usedColorBuffers * 2);
            dfbColorTexturesFlip.reset();
            for (i2 = 0; i2 < usedDrawBuffers; ++i2) {
                dfbDrawBuffers.put(i2, 36064 + i2);
            }
            int maxDrawBuffers = GL11.glGetInteger((int)34852);
            if (usedDrawBuffers > maxDrawBuffers) {
                Shaders.printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + maxDrawBuffers);
            }
            sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);
            for (i = 0; i < usedShadowColorBuffers; ++i) {
                sfbDrawBuffers.put(i, 36064 + i);
            }
            for (i = 0; i < ProgramsAll.length; ++i) {
                Program pi;
                Program pn;
                for (pn = pi = ProgramsAll[i]; pn.getId() == 0 && pn.getProgramBackup() != pn; pn = pn.getProgramBackup()) {
                }
                if (pn == pi || pi == ProgramShadow) continue;
                pi.copyFrom(pn);
            }
            Shaders.resize();
            Shaders.resizeShadow();
            if (noiseTextureEnabled) {
                Shaders.setupNoiseTexture();
            }
            if (defaultTexture == null) {
                defaultTexture = ShadersTex.createDefaultTexture();
            }
            bus.G();
            bus.b((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            Shaders.preCelestialRotate();
            Shaders.postCelestialRotate();
            bus.H();
            isShaderPackInitialized = true;
            Shaders.loadEntityDataMap();
            Shaders.resetDisplayLists();
            if (!firstInit) {
                // empty if block
            }
            Shaders.checkGLError("Shaders.init");
        }
    }

    private static void initDrawBuffers(Program p) {
        int maxDrawBuffers = GL11.glGetInteger((int)34852);
        Arrays.fill(p.getToggleColorTextures(), false);
        if (p == ProgramFinal) {
            p.setDrawBuffers(null);
            return;
        }
        if (p.getId() == 0) {
            if (p == ProgramShadow) {
                p.setDrawBuffers(drawBuffersNone);
            } else {
                p.setDrawBuffers(drawBuffersColorAtt0);
            }
            return;
        }
        String str = p.getDrawBufSettings();
        if (str == null) {
            if (p != ProgramShadow && p != ProgramShadowSolid && p != ProgramShadowCutout) {
                p.setDrawBuffers(dfbDrawBuffers);
                usedDrawBuffers = usedColorBuffers;
                Arrays.fill(p.getToggleColorTextures(), 0, usedColorBuffers, true);
            } else {
                p.setDrawBuffers(sfbDrawBuffers);
            }
            return;
        }
        IntBuffer intbuf = p.getDrawBuffersBuffer();
        int numDB = str.length();
        usedDrawBuffers = Math.max(usedDrawBuffers, numDB);
        numDB = Math.min(numDB, maxDrawBuffers);
        p.setDrawBuffers(intbuf);
        intbuf.limit(numDB);
        for (int i = 0; i < numDB; ++i) {
            int drawBuffer = Shaders.getDrawBuffer(p, str, i);
            intbuf.put(i, drawBuffer);
        }
    }

    private static int getDrawBuffer(Program p, String str, int i) {
        int drawBuffer = 0;
        if (i >= str.length()) {
            return drawBuffer;
        }
        int ca = str.charAt(i) - 48;
        if (p == ProgramShadow) {
            if (ca >= 0 && ca <= 1) {
                drawBuffer = ca + 36064;
                usedShadowColorBuffers = Math.max(usedShadowColorBuffers, ca);
            }
            return drawBuffer;
        }
        if (ca >= 0 && ca <= 7) {
            p.getToggleColorTextures()[ca] = true;
            drawBuffer = ca + 36064;
            usedColorAttachs = Math.max(usedColorAttachs, ca);
            usedColorBuffers = Math.max(usedColorBuffers, ca);
        }
        return drawBuffer;
    }

    private static void updateToggleBuffers(Program p) {
        boolean[] toggleBuffers = p.getToggleColorTextures();
        Boolean[] flipBuffers = p.getBuffersFlip();
        for (int i = 0; i < flipBuffers.length; ++i) {
            Boolean flip = flipBuffers[i];
            if (flip == null) continue;
            toggleBuffers[i] = flip;
        }
    }

    public static void resetDisplayLists() {
        SMCLog.info("Reset model renderers");
        ++countResetDisplayLists;
        SMCLog.info("Reset world renderers");
        Shaders.mc.g.a();
    }

    private static void setupProgram(Program program, String vShaderPath, String gShaderPath, String fShaderPath) {
        Shaders.checkGLError("pre setupProgram");
        int programid = ARBShaderObjects.glCreateProgramObjectARB();
        Shaders.checkGLError("create");
        if (programid != 0) {
            progUseEntityAttrib = false;
            progUseMidTexCoordAttrib = false;
            progUseTangentAttrib = false;
            int vShader = Shaders.createVertShader(program, vShaderPath);
            int gShader = Shaders.createGeomShader(program, gShaderPath);
            int fShader = Shaders.createFragShader(program, fShaderPath);
            Shaders.checkGLError("create");
            if (vShader != 0 || gShader != 0 || fShader != 0) {
                if (vShader != 0) {
                    ARBShaderObjects.glAttachObjectARB((int)programid, (int)vShader);
                    Shaders.checkGLError("attach");
                }
                if (gShader != 0) {
                    ARBShaderObjects.glAttachObjectARB((int)programid, (int)gShader);
                    Shaders.checkGLError("attach");
                    if (progArbGeometryShader4) {
                        ARBGeometryShader4.glProgramParameteriARB((int)programid, (int)36315, (int)4);
                        ARBGeometryShader4.glProgramParameteriARB((int)programid, (int)36316, (int)5);
                        ARBGeometryShader4.glProgramParameteriARB((int)programid, (int)36314, (int)progMaxVerticesOut);
                        Shaders.checkGLError("arbGeometryShader4");
                    }
                    hasGeometryShaders = true;
                }
                if (fShader != 0) {
                    ARBShaderObjects.glAttachObjectARB((int)programid, (int)fShader);
                    Shaders.checkGLError("attach");
                }
                if (progUseEntityAttrib) {
                    ARBVertexShader.glBindAttribLocationARB((int)programid, (int)entityAttrib, (CharSequence)"mc_Entity");
                    Shaders.checkGLError("mc_Entity");
                }
                if (progUseMidTexCoordAttrib) {
                    ARBVertexShader.glBindAttribLocationARB((int)programid, (int)midTexCoordAttrib, (CharSequence)"mc_midTexCoord");
                    Shaders.checkGLError("mc_midTexCoord");
                }
                if (progUseTangentAttrib) {
                    ARBVertexShader.glBindAttribLocationARB((int)programid, (int)tangentAttrib, (CharSequence)"at_tangent");
                    Shaders.checkGLError("at_tangent");
                }
                ARBShaderObjects.glLinkProgramARB((int)programid);
                if (GL20.glGetProgrami((int)programid, (int)35714) != 1) {
                    SMCLog.severe("Error linking program: " + programid + " (" + program.getName() + ")");
                }
                Shaders.printLogInfo(programid, program.getName());
                if (vShader != 0) {
                    ARBShaderObjects.glDetachObjectARB((int)programid, (int)vShader);
                    ARBShaderObjects.glDeleteObjectARB((int)vShader);
                }
                if (gShader != 0) {
                    ARBShaderObjects.glDetachObjectARB((int)programid, (int)gShader);
                    ARBShaderObjects.glDeleteObjectARB((int)gShader);
                }
                if (fShader != 0) {
                    ARBShaderObjects.glDetachObjectARB((int)programid, (int)fShader);
                    ARBShaderObjects.glDeleteObjectARB((int)fShader);
                }
                program.setId(programid);
                program.setRef(programid);
                Shaders.useProgram(program);
                ARBShaderObjects.glValidateProgramARB((int)programid);
                Shaders.useProgram(ProgramNone);
                Shaders.printLogInfo(programid, program.getName());
                int valid = GL20.glGetProgrami((int)programid, (int)35715);
                if (valid != 1) {
                    String Q = "\"";
                    Shaders.printChatAndLogError("[Shaders] Error: Invalid program " + Q + program.getName() + Q);
                    ARBShaderObjects.glDeleteObjectARB((int)programid);
                    programid = 0;
                    program.resetId();
                }
            } else {
                ARBShaderObjects.glDeleteObjectARB((int)programid);
                programid = 0;
                program.resetId();
            }
        }
    }

    private static int createVertShader(Program program, String filename) {
        int vertShader = ARBShaderObjects.glCreateShaderObjectARB((int)35633);
        if (vertShader == 0) {
            return 0;
        }
        StringBuilder vertexCode = new StringBuilder(131072);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(Shaders.getShaderReader(filename));
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB((int)vertShader);
            return 0;
        }
        ShaderOption[] activeOptions = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> listFiles = new ArrayList<String>();
        if (reader != null) {
            try {
                String line;
                reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
                MacroState macroState = new MacroState();
                while ((line = reader.readLine()) != null) {
                    ShaderLine sl;
                    line = Shaders.applyOptions(line, activeOptions);
                    vertexCode.append(line).append('\n');
                    if (!macroState.processLine(line) || (sl = ShaderParser.parseLine(line)) == null) continue;
                    if (sl.isAttribute("mc_Entity")) {
                        useEntityAttrib = true;
                        progUseEntityAttrib = true;
                    } else if (sl.isAttribute("mc_midTexCoord")) {
                        useMidTexCoordAttrib = true;
                        progUseMidTexCoordAttrib = true;
                    } else if (sl.isAttribute("at_tangent")) {
                        useTangentAttrib = true;
                        progUseTangentAttrib = true;
                    }
                    if (!sl.isConstInt("countInstances")) continue;
                    program.setCountInstances(sl.getValueInt());
                    SMCLog.info("countInstances: " + program.getCountInstances());
                }
                reader.close();
            }
            catch (Exception e) {
                SMCLog.severe("Couldn't read " + filename + "!");
                e.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)vertShader);
                return 0;
            }
        }
        if (saveFinalShaders) {
            Shaders.saveShader(filename, vertexCode.toString());
        }
        ARBShaderObjects.glShaderSourceARB((int)vertShader, (CharSequence)vertexCode);
        ARBShaderObjects.glCompileShaderARB((int)vertShader);
        if (GL20.glGetShaderi((int)vertShader, (int)35713) != 1) {
            SMCLog.severe("Error compiling vertex shader: " + filename);
        }
        Shaders.printShaderLogInfo(vertShader, filename, listFiles);
        return vertShader;
    }

    private static int createGeomShader(Program program, String filename) {
        int geomShader = ARBShaderObjects.glCreateShaderObjectARB((int)36313);
        if (geomShader == 0) {
            return 0;
        }
        StringBuilder geomCode = new StringBuilder(131072);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(Shaders.getShaderReader(filename));
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB((int)geomShader);
            return 0;
        }
        ShaderOption[] activeOptions = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> listFiles = new ArrayList<String>();
        progArbGeometryShader4 = false;
        progMaxVerticesOut = 3;
        if (reader != null) {
            try {
                String line;
                reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
                MacroState macroState = new MacroState();
                while ((line = reader.readLine()) != null) {
                    String val;
                    ShaderLine sl;
                    line = Shaders.applyOptions(line, activeOptions);
                    geomCode.append(line).append('\n');
                    if (!macroState.processLine(line) || (sl = ShaderParser.parseLine(line)) == null) continue;
                    if (sl.isExtension("GL_ARB_geometry_shader4") && ((val = Config.normalize(sl.getValue())).equals("enable") || val.equals("require") || val.equals("warn"))) {
                        progArbGeometryShader4 = true;
                    }
                    if (!sl.isConstInt("maxVerticesOut")) continue;
                    progMaxVerticesOut = sl.getValueInt();
                }
                reader.close();
            }
            catch (Exception e) {
                SMCLog.severe("Couldn't read " + filename + "!");
                e.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)geomShader);
                return 0;
            }
        }
        if (saveFinalShaders) {
            Shaders.saveShader(filename, geomCode.toString());
        }
        ARBShaderObjects.glShaderSourceARB((int)geomShader, (CharSequence)geomCode);
        ARBShaderObjects.glCompileShaderARB((int)geomShader);
        if (GL20.glGetShaderi((int)geomShader, (int)35713) != 1) {
            SMCLog.severe("Error compiling geometry shader: " + filename);
        }
        Shaders.printShaderLogInfo(geomShader, filename, listFiles);
        return geomShader;
    }

    private static int createFragShader(Program program, String filename) {
        int fragShader = ARBShaderObjects.glCreateShaderObjectARB((int)35632);
        if (fragShader == 0) {
            return 0;
        }
        StringBuilder fragCode = new StringBuilder(131072);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(Shaders.getShaderReader(filename));
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB((int)fragShader);
            return 0;
        }
        ShaderOption[] activeOptions = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> listFiles = new ArrayList<String>();
        if (reader != null) {
            try {
                String line;
                reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
                MacroState macroState = new MacroState();
                while ((line = reader.readLine()) != null) {
                    String name;
                    ShaderLine sl;
                    line = Shaders.applyOptions(line, activeOptions);
                    fragCode.append(line).append('\n');
                    if (!macroState.processLine(line) || (sl = ShaderParser.parseLine(line)) == null) continue;
                    if (sl.isUniform()) {
                        String uniform = sl.getName();
                        int index = ShaderParser.getShadowDepthIndex(uniform);
                        if (index >= 0) {
                            usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, index + 1);
                            continue;
                        }
                        index = ShaderParser.getShadowColorIndex(uniform);
                        if (index >= 0) {
                            usedShadowColorBuffers = Math.max(usedShadowColorBuffers, index + 1);
                            continue;
                        }
                        index = ShaderParser.getDepthIndex(uniform);
                        if (index >= 0) {
                            usedDepthBuffers = Math.max(usedDepthBuffers, index + 1);
                            continue;
                        }
                        if (uniform.equals("gdepth") && gbuffersFormat[1] == 6408) {
                            Shaders.gbuffersFormat[1] = 34836;
                            continue;
                        }
                        index = ShaderParser.getColorIndex(uniform);
                        if (index >= 0) {
                            usedColorBuffers = Math.max(usedColorBuffers, index + 1);
                            continue;
                        }
                        if (!uniform.equals("centerDepthSmooth")) continue;
                        centerDepthSmoothEnabled = true;
                        continue;
                    }
                    if (sl.isConstInt("shadowMapResolution") || sl.isProperty("SHADOWRES")) {
                        spShadowMapWidth = spShadowMapHeight = sl.getValueInt();
                        shadowMapWidth = shadowMapHeight = Math.round((float)spShadowMapWidth * configShadowResMul);
                        SMCLog.info("Shadow map resolution: " + spShadowMapWidth);
                        continue;
                    }
                    if (sl.isConstFloat("shadowMapFov") || sl.isProperty("SHADOWFOV")) {
                        shadowMapFOV = sl.getValueFloat();
                        shadowMapIsOrtho = false;
                        SMCLog.info("Shadow map field of view: " + shadowMapFOV);
                        continue;
                    }
                    if (sl.isConstFloat("shadowDistance") || sl.isProperty("SHADOWHPL")) {
                        shadowMapHalfPlane = sl.getValueFloat();
                        shadowMapIsOrtho = true;
                        SMCLog.info("Shadow map distance: " + shadowMapHalfPlane);
                        continue;
                    }
                    if (sl.isConstFloat("shadowDistanceRenderMul")) {
                        shadowDistanceRenderMul = sl.getValueFloat();
                        SMCLog.info("Shadow distance render mul: " + shadowDistanceRenderMul);
                        continue;
                    }
                    if (sl.isConstFloat("shadowIntervalSize")) {
                        shadowIntervalSize = sl.getValueFloat();
                        SMCLog.info("Shadow map interval size: " + shadowIntervalSize);
                        continue;
                    }
                    if (sl.isConstBool("generateShadowMipmap", true)) {
                        Arrays.fill(shadowMipmapEnabled, true);
                        SMCLog.info("Generate shadow mipmap");
                        continue;
                    }
                    if (sl.isConstBool("generateShadowColorMipmap", true)) {
                        Arrays.fill(shadowColorMipmapEnabled, true);
                        SMCLog.info("Generate shadow color mipmap");
                        continue;
                    }
                    if (sl.isConstBool("shadowHardwareFiltering", true)) {
                        Arrays.fill(shadowHardwareFilteringEnabled, true);
                        SMCLog.info("Hardware shadow filtering enabled.");
                        continue;
                    }
                    if (sl.isConstBool("shadowHardwareFiltering0", true)) {
                        Shaders.shadowHardwareFilteringEnabled[0] = true;
                        SMCLog.info("shadowHardwareFiltering0");
                        continue;
                    }
                    if (sl.isConstBool("shadowHardwareFiltering1", true)) {
                        Shaders.shadowHardwareFilteringEnabled[1] = true;
                        SMCLog.info("shadowHardwareFiltering1");
                        continue;
                    }
                    if (sl.isConstBool("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                        Shaders.shadowMipmapEnabled[0] = true;
                        SMCLog.info("shadowtex0Mipmap");
                        continue;
                    }
                    if (sl.isConstBool("shadowtex1Mipmap", true)) {
                        Shaders.shadowMipmapEnabled[1] = true;
                        SMCLog.info("shadowtex1Mipmap");
                        continue;
                    }
                    if (sl.isConstBool("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                        Shaders.shadowColorMipmapEnabled[0] = true;
                        SMCLog.info("shadowcolor0Mipmap");
                        continue;
                    }
                    if (sl.isConstBool("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                        Shaders.shadowColorMipmapEnabled[1] = true;
                        SMCLog.info("shadowcolor1Mipmap");
                        continue;
                    }
                    if (sl.isConstBool("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                        Shaders.shadowFilterNearest[0] = true;
                        SMCLog.info("shadowtex0Nearest");
                        continue;
                    }
                    if (sl.isConstBool("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                        Shaders.shadowFilterNearest[1] = true;
                        SMCLog.info("shadowtex1Nearest");
                        continue;
                    }
                    if (sl.isConstBool("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                        Shaders.shadowColorFilterNearest[0] = true;
                        SMCLog.info("shadowcolor0Nearest");
                        continue;
                    }
                    if (sl.isConstBool("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                        Shaders.shadowColorFilterNearest[1] = true;
                        SMCLog.info("shadowcolor1Nearest");
                        continue;
                    }
                    if (sl.isConstFloat("wetnessHalflife") || sl.isProperty("WETNESSHL")) {
                        wetnessHalfLife = sl.getValueFloat();
                        SMCLog.info("Wetness halflife: " + wetnessHalfLife);
                        continue;
                    }
                    if (sl.isConstFloat("drynessHalflife") || sl.isProperty("DRYNESSHL")) {
                        drynessHalfLife = sl.getValueFloat();
                        SMCLog.info("Dryness halflife: " + drynessHalfLife);
                        continue;
                    }
                    if (sl.isConstFloat("eyeBrightnessHalflife")) {
                        eyeBrightnessHalflife = sl.getValueFloat();
                        SMCLog.info("Eye brightness halflife: " + eyeBrightnessHalflife);
                        continue;
                    }
                    if (sl.isConstFloat("centerDepthHalflife")) {
                        centerDepthSmoothHalflife = sl.getValueFloat();
                        SMCLog.info("Center depth halflife: " + centerDepthSmoothHalflife);
                        continue;
                    }
                    if (sl.isConstFloat("sunPathRotation")) {
                        sunPathRotation = sl.getValueFloat();
                        SMCLog.info("Sun path rotation: " + sunPathRotation);
                        continue;
                    }
                    if (sl.isConstFloat("ambientOcclusionLevel")) {
                        aoLevel = Config.limit(sl.getValueFloat(), 0.0f, 1.0f);
                        SMCLog.info("AO Level: " + aoLevel);
                        continue;
                    }
                    if (sl.isConstInt("superSamplingLevel")) {
                        int ssaa = sl.getValueInt();
                        if (ssaa > 1) {
                            SMCLog.info("Super sampling level: " + ssaa + "x");
                            superSamplingLevel = ssaa;
                            continue;
                        }
                        superSamplingLevel = 1;
                        continue;
                    }
                    if (sl.isConstInt("noiseTextureResolution")) {
                        noiseTextureResolution = sl.getValueInt();
                        noiseTextureEnabled = true;
                        SMCLog.info("Noise texture enabled");
                        SMCLog.info("Noise texture resolution: " + noiseTextureResolution);
                        continue;
                    }
                    if (sl.isConstIntSuffix("Format")) {
                        name = StrUtils.removeSuffix(sl.getName(), "Format");
                        String value = sl.getValue();
                        int bufferindex = Shaders.getBufferIndexFromString(name);
                        int format = Shaders.getTextureFormatFromString(value);
                        if (bufferindex < 0 || format == 0) continue;
                        Shaders.gbuffersFormat[bufferindex] = format;
                        SMCLog.info("%s format: %s", name, value);
                        continue;
                    }
                    if (sl.isConstBoolSuffix("Clear", false)) {
                        int bufferindex;
                        if (!ShaderParser.isComposite(filename) && !ShaderParser.isDeferred(filename) || (bufferindex = Shaders.getBufferIndexFromString(name = StrUtils.removeSuffix(sl.getName(), "Clear"))) < 0) continue;
                        Shaders.gbuffersClear[bufferindex] = false;
                        SMCLog.info("%s clear disabled", name);
                        continue;
                    }
                    if (sl.isConstVec4Suffix("ClearColor")) {
                        int bufferindex;
                        if (!ShaderParser.isComposite(filename) && !ShaderParser.isDeferred(filename) || (bufferindex = Shaders.getBufferIndexFromString(name = StrUtils.removeSuffix(sl.getName(), "ClearColor"))) < 0) continue;
                        Vector4f col = sl.getValueVec4();
                        if (col != null) {
                            Shaders.gbuffersClearColor[bufferindex] = col;
                            SMCLog.info("%s clear color: %s %s %s %s", name, Float.valueOf(col.getX()), Float.valueOf(col.getY()), Float.valueOf(col.getZ()), Float.valueOf(col.getW()));
                            continue;
                        }
                        SMCLog.warning("Invalid color value: " + sl.getValue());
                        continue;
                    }
                    if (sl.isProperty("GAUX4FORMAT", "RGBA32F")) {
                        Shaders.gbuffersFormat[7] = 34836;
                        SMCLog.info("gaux4 format : RGB32AF");
                        continue;
                    }
                    if (sl.isProperty("GAUX4FORMAT", "RGB32F")) {
                        Shaders.gbuffersFormat[7] = 34837;
                        SMCLog.info("gaux4 format : RGB32F");
                        continue;
                    }
                    if (sl.isProperty("GAUX4FORMAT", "RGB16")) {
                        Shaders.gbuffersFormat[7] = 32852;
                        SMCLog.info("gaux4 format : RGB16");
                        continue;
                    }
                    if (sl.isConstBoolSuffix("MipmapEnabled", true)) {
                        int bufferindex;
                        if (!ShaderParser.isComposite(filename) && !ShaderParser.isDeferred(filename) && !ShaderParser.isFinal(filename) || (bufferindex = Shaders.getBufferIndexFromString(name = StrUtils.removeSuffix(sl.getName(), "MipmapEnabled"))) < 0) continue;
                        int compositeMipmapSetting = program.getCompositeMipmapSetting();
                        program.setCompositeMipmapSetting(compositeMipmapSetting |= 1 << bufferindex);
                        SMCLog.info("%s mipmap enabled", name);
                        continue;
                    }
                    if (!sl.isProperty("DRAWBUFFERS")) continue;
                    String val = sl.getValue();
                    if (ShaderParser.isValidDrawBuffers(val)) {
                        program.setDrawBufSettings(val);
                        continue;
                    }
                    SMCLog.warning("Invalid draw buffers: " + val);
                }
                reader.close();
            }
            catch (Exception e) {
                SMCLog.severe("Couldn't read " + filename + "!");
                e.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)fragShader);
                return 0;
            }
        }
        if (saveFinalShaders) {
            Shaders.saveShader(filename, fragCode.toString());
        }
        ARBShaderObjects.glShaderSourceARB((int)fragShader, (CharSequence)fragCode);
        ARBShaderObjects.glCompileShaderARB((int)fragShader);
        if (GL20.glGetShaderi((int)fragShader, (int)35713) != 1) {
            SMCLog.severe("Error compiling fragment shader: " + filename);
        }
        Shaders.printShaderLogInfo(fragShader, filename, listFiles);
        return fragShader;
    }

    private static Reader getShaderReader(String filename) {
        return new InputStreamReader(shaderPack.getResourceAsStream(filename));
    }

    public static void saveShader(String filename, String code) {
        try {
            File file = new File(shaderPacksDir, "debug/" + filename);
            file.getParentFile().mkdirs();
            Config.writeFile(file, code);
        }
        catch (IOException e) {
            Config.warn("Error saving: " + filename);
            e.printStackTrace();
        }
    }

    private static void clearDirectory(File dir) {
        if (!dir.exists()) {
            return;
        }
        if (!dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; ++i) {
            File file = files[i];
            if (file.isDirectory()) {
                Shaders.clearDirectory(file);
            }
            file.delete();
        }
    }

    private static boolean printLogInfo(int obj, String name) {
        IntBuffer iVal = BufferUtils.createIntBuffer((int)1);
        ARBShaderObjects.glGetObjectParameterARB((int)obj, (int)35716, (IntBuffer)iVal);
        int length = iVal.get();
        if (length > 1) {
            ByteBuffer infoLog = BufferUtils.createByteBuffer((int)length);
            iVal.flip();
            ARBShaderObjects.glGetInfoLogARB((int)obj, (IntBuffer)iVal, (ByteBuffer)infoLog);
            byte[] infoBytes = new byte[length];
            infoLog.get(infoBytes);
            if (infoBytes[length - 1] == 0) {
                infoBytes[length - 1] = 10;
            }
            String out = new String(infoBytes, StandardCharsets.US_ASCII);
            out = StrUtils.trim(out, " \n\r\t");
            SMCLog.info("Info log: " + name + "\n" + out);
            return false;
        }
        return true;
    }

    private static boolean printShaderLogInfo(int shader, String name, List<String> listFiles) {
        IntBuffer iVal = BufferUtils.createIntBuffer((int)1);
        int length = GL20.glGetShaderi((int)shader, (int)35716);
        if (length > 1) {
            for (int i = 0; i < listFiles.size(); ++i) {
                String path = listFiles.get(i);
                SMCLog.info("File: " + (i + 1) + " = " + path);
            }
            String log = GL20.glGetShaderInfoLog((int)shader, (int)length);
            log = StrUtils.trim(log, " \n\r\t");
            SMCLog.info("Shader info log: " + name + "\n" + log);
            return false;
        }
        return true;
    }

    public static void setDrawBuffers(IntBuffer drawBuffers) {
        if (drawBuffers == null) {
            drawBuffers = drawBuffersNone;
        }
        if (activeDrawBuffers != drawBuffers) {
            activeDrawBuffers = drawBuffers;
            GL20.glDrawBuffers((IntBuffer)drawBuffers);
            Shaders.checkGLError("setDrawBuffers");
        }
    }

    public static void useProgram(Program program) {
        int blockLight2;
        int programID;
        Shaders.checkGLError("pre-useProgram");
        if (isShadowPass) {
            program = ProgramShadow;
        } else if (isEntitiesGlowing) {
            program = ProgramEntitiesGlowing;
        }
        if (activeProgram == program) {
            return;
        }
        Shaders.updateAlphaBlend(activeProgram, program);
        activeProgram = program;
        activeProgramID = programID = program.getId();
        ARBShaderObjects.glUseProgramObjectARB((int)programID);
        if (Shaders.checkGLError("useProgram") != 0) {
            program.setId(0);
            activeProgramID = programID = program.getId();
            ARBShaderObjects.glUseProgramObjectARB((int)programID);
        }
        shaderUniforms.setProgram(programID);
        if (customUniforms != null) {
            customUniforms.setProgram(programID);
        }
        if (programID == 0) {
            return;
        }
        IntBuffer drawBuffers = program.getDrawBuffers();
        if (isRenderingDfb) {
            Shaders.setDrawBuffers(drawBuffers);
        }
        activeCompositeMipmapSetting = program.getCompositeMipmapSetting();
        switch (program.getProgramStage()) {
            case GBUFFERS: {
                Shaders.setProgramUniform1i(uniform_texture, 0);
                Shaders.setProgramUniform1i(uniform_lightmap, 1);
                Shaders.setProgramUniform1i(uniform_normals, 2);
                Shaders.setProgramUniform1i(uniform_specular, 3);
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                Shaders.setProgramUniform1i(uniform_depthtex0, 6);
                if (customTexturesGbuffers != null || hasDeferredPrograms) {
                    Shaders.setProgramUniform1i(uniform_gaux1, 7);
                    Shaders.setProgramUniform1i(uniform_gaux2, 8);
                    Shaders.setProgramUniform1i(uniform_gaux3, 9);
                    Shaders.setProgramUniform1i(uniform_gaux4, 10);
                }
                Shaders.setProgramUniform1i(uniform_depthtex1, 11);
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
                break;
            }
            case DEFERRED: 
            case COMPOSITE: {
                Shaders.setProgramUniform1i(uniform_gcolor, 0);
                Shaders.setProgramUniform1i(uniform_gdepth, 1);
                Shaders.setProgramUniform1i(uniform_gnormal, 2);
                Shaders.setProgramUniform1i(uniform_composite, 3);
                Shaders.setProgramUniform1i(uniform_gaux1, 7);
                Shaders.setProgramUniform1i(uniform_gaux2, 8);
                Shaders.setProgramUniform1i(uniform_gaux3, 9);
                Shaders.setProgramUniform1i(uniform_gaux4, 10);
                Shaders.setProgramUniform1i(uniform_colortex0, 0);
                Shaders.setProgramUniform1i(uniform_colortex1, 1);
                Shaders.setProgramUniform1i(uniform_colortex2, 2);
                Shaders.setProgramUniform1i(uniform_colortex3, 3);
                Shaders.setProgramUniform1i(uniform_colortex4, 7);
                Shaders.setProgramUniform1i(uniform_colortex5, 8);
                Shaders.setProgramUniform1i(uniform_colortex6, 9);
                Shaders.setProgramUniform1i(uniform_colortex7, 10);
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                Shaders.setProgramUniform1i(uniform_gdepthtex, 6);
                Shaders.setProgramUniform1i(uniform_depthtex0, 6);
                Shaders.setProgramUniform1i(uniform_depthtex1, 11);
                Shaders.setProgramUniform1i(uniform_depthtex2, 12);
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
                break;
            }
            case SHADOW: {
                Shaders.setProgramUniform1i(uniform_tex, 0);
                Shaders.setProgramUniform1i(uniform_texture, 0);
                Shaders.setProgramUniform1i(uniform_lightmap, 1);
                Shaders.setProgramUniform1i(uniform_normals, 2);
                Shaders.setProgramUniform1i(uniform_specular, 3);
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                if (customTexturesGbuffers != null) {
                    Shaders.setProgramUniform1i(uniform_gaux1, 7);
                    Shaders.setProgramUniform1i(uniform_gaux2, 8);
                    Shaders.setProgramUniform1i(uniform_gaux3, 9);
                    Shaders.setProgramUniform1i(uniform_gaux4, 10);
                }
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
                break;
            }
        }
        aip stack = Shaders.mc.h != null ? Shaders.mc.h.co() : null;
        ain item = stack != null ? stack.c() : null;
        int itemID = -1;
        aow block = null;
        if (item != null) {
            itemID = ain.g.a((Object)item);
            block = (aow)aow.h.a(itemID);
            itemID = ItemAliases.getItemAliasId(itemID);
        }
        int blockLight = block != null ? block.o(block.t()) : 0;
        aip stack2 = Shaders.mc.h != null ? Shaders.mc.h.cp() : null;
        ain item2 = stack2 != null ? stack2.c() : null;
        int itemID2 = -1;
        aow block2 = null;
        if (item2 != null) {
            itemID2 = ain.g.a((Object)item2);
            block2 = (aow)aow.h.a(itemID2);
            itemID2 = ItemAliases.getItemAliasId(itemID2);
        }
        int n = blockLight2 = block2 != null ? block2.o(block2.t()) : 0;
        if (Shaders.isOldHandLight() && blockLight2 > blockLight) {
            itemID = itemID2;
            blockLight = blockLight2;
        }
        Shaders.setProgramUniform1i(uniform_heldItemId, itemID);
        Shaders.setProgramUniform1i(uniform_heldBlockLightValue, blockLight);
        Shaders.setProgramUniform1i(uniform_heldItemId2, itemID2);
        Shaders.setProgramUniform1i(uniform_heldBlockLightValue2, blockLight2);
        Shaders.setProgramUniform1i(uniform_fogMode, fogEnabled ? fogMode : 0);
        Shaders.setProgramUniform1f(uniform_fogDensity, fogEnabled ? fogDensity : 0.0f);
        Shaders.setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
        Shaders.setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
        Shaders.setProgramUniform1i(uniform_worldTime, (int)(worldTime % 24000L));
        Shaders.setProgramUniform1i(uniform_worldDay, (int)(worldTime / 24000L));
        Shaders.setProgramUniform1i(uniform_moonPhase, moonPhase);
        Shaders.setProgramUniform1i(uniform_frameCounter, frameCounter);
        Shaders.setProgramUniform1f(uniform_frameTime, frameTime);
        Shaders.setProgramUniform1f(uniform_frameTimeCounter, frameTimeCounter);
        Shaders.setProgramUniform1f(uniform_sunAngle, sunAngle);
        Shaders.setProgramUniform1f(uniform_shadowAngle, shadowAngle);
        Shaders.setProgramUniform1f(uniform_rainStrength, rainStrength);
        Shaders.setProgramUniform1f(uniform_aspectRatio, (float)renderWidth / (float)renderHeight);
        Shaders.setProgramUniform1f(uniform_viewWidth, renderWidth);
        Shaders.setProgramUniform1f(uniform_viewHeight, renderHeight);
        Shaders.setProgramUniform1f(uniform_near, 0.05f);
        Shaders.setProgramUniform1f(uniform_far, Shaders.mc.t.e * 16);
        Shaders.setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
        Shaders.setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
        Shaders.setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
        Shaders.setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
        Shaders.setProgramUniform3f(uniform_previousCameraPosition, (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
        Shaders.setProgramUniform3f(uniform_cameraPosition, (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
        if (usedShadowDepthBuffers > 0) {
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
        }
        Shaders.setProgramUniform1f(uniform_wetness, wetness);
        Shaders.setProgramUniform1f(uniform_eyeAltitude, eyePosY);
        Shaders.setProgramUniform2i(uniform_eyeBrightness, eyeBrightness & 0xFFFF, eyeBrightness >> 16);
        Shaders.setProgramUniform2i(uniform_eyeBrightnessSmooth, Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
        Shaders.setProgramUniform2i(uniform_terrainTextureSize, terrainTextureSize[0], terrainTextureSize[1]);
        Shaders.setProgramUniform1i(uniform_terrainIconSize, terrainIconSize);
        Shaders.setProgramUniform1i(uniform_isEyeInWater, isEyeInWater);
        Shaders.setProgramUniform1f(uniform_nightVision, nightVision);
        Shaders.setProgramUniform1f(uniform_blindness, blindness);
        Shaders.setProgramUniform1f(uniform_screenBrightness, Shaders.mc.t.aE);
        Shaders.setProgramUniform1i(uniform_hideGUI, Shaders.mc.t.av ? 1 : 0);
        Shaders.setProgramUniform1f(uniform_centerDepthSmooth, centerDepthSmooth);
        Shaders.setProgramUniform2i(uniform_atlasSize, atlasSizeX, atlasSizeY);
        if (customUniforms != null) {
            customUniforms.update();
        }
        Shaders.checkGLError("end useProgram");
    }

    private static void updateAlphaBlend(Program programOld, Program programNew) {
        GlBlendState blendNew;
        GlAlphaState alphaNew;
        if (programOld.getAlphaState() != null) {
            bus.unlockAlpha();
        }
        if (programOld.getBlendState() != null) {
            bus.unlockBlend();
        }
        if ((alphaNew = programNew.getAlphaState()) != null) {
            bus.lockAlpha((GlAlphaState)alphaNew);
        }
        if ((blendNew = programNew.getBlendState()) != null) {
            bus.lockBlend((GlBlendState)blendNew);
        }
    }

    private static void setProgramUniform1i(ShaderUniform1i su, int value) {
        su.setValue(value);
    }

    private static void setProgramUniform2i(ShaderUniform2i su, int i0, int i1) {
        su.setValue(i0, i1);
    }

    private static void setProgramUniform1f(ShaderUniform1f su, float value) {
        su.setValue(value);
    }

    private static void setProgramUniform3f(ShaderUniform3f su, float f0, float f1, float f2) {
        su.setValue(f0, f1, f2);
    }

    private static void setProgramUniformMatrix4ARB(ShaderUniformM4 su, boolean transpose, FloatBuffer matrix) {
        su.setValue(transpose, matrix);
    }

    public static int getBufferIndexFromString(String name) {
        if (name.equals("colortex0") || name.equals("gcolor")) {
            return 0;
        }
        if (name.equals("colortex1") || name.equals("gdepth")) {
            return 1;
        }
        if (name.equals("colortex2") || name.equals("gnormal")) {
            return 2;
        }
        if (name.equals("colortex3") || name.equals("composite")) {
            return 3;
        }
        if (name.equals("colortex4") || name.equals("gaux1")) {
            return 4;
        }
        if (name.equals("colortex5") || name.equals("gaux2")) {
            return 5;
        }
        if (name.equals("colortex6") || name.equals("gaux3")) {
            return 6;
        }
        if (name.equals("colortex7") || name.equals("gaux4")) {
            return 7;
        }
        return -1;
    }

    private static int getTextureFormatFromString(String par) {
        par = par.trim();
        for (int i = 0; i < formatNames.length; ++i) {
            String name = formatNames[i];
            if (!par.equals(name)) continue;
            return formatIds[i];
        }
        return 0;
    }

    private static void setupNoiseTexture() {
        if (noiseTexture == null && noiseTexturePath != null) {
            noiseTexture = Shaders.loadCustomTexture(15, noiseTexturePath);
        }
        if (noiseTexture == null) {
            noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
        }
    }

    private static void loadEntityDataMap() {
        mapBlockToEntityData = new IdentityHashMap<aow, Integer>(300);
        if (mapBlockToEntityData.isEmpty()) {
            for (nf key : aow.h.c()) {
                aow block = (aow)aow.h.c((Object)key);
                int id = aow.h.a((Object)block);
                mapBlockToEntityData.put(block, id);
            }
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
        }
        catch (Exception key) {
            // empty catch block
        }
        if (reader != null) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher m2 = patternLoadEntityDataMap.matcher(line);
                    if (m2.matches()) {
                        String name = m2.group(1);
                        String value = m2.group(2);
                        int id = Integer.parseInt(value);
                        aow block = aow.b((String)name);
                        if (block != null) {
                            mapBlockToEntityData.put(block, id);
                            continue;
                        }
                        SMCLog.warning("Unknown block name %s", name);
                        continue;
                    }
                    SMCLog.warning("unmatched %s\n", line);
                }
            }
            catch (Exception e) {
                SMCLog.warning("Error parsing mc_Entity_x.txt");
            }
        }
        if (reader != null) {
            try {
                reader.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private static IntBuffer fillIntBufferZero(IntBuffer buf) {
        int limit = buf.limit();
        for (int i = buf.position(); i < limit; ++i) {
            buf.put(i, 0);
        }
        return buf;
    }

    public static void uninit() {
        if (isShaderPackInitialized) {
            Shaders.checkGLError("Shaders.uninit pre");
            for (int i = 0; i < ProgramsAll.length; ++i) {
                Program pi = ProgramsAll[i];
                if (pi.getRef() != 0) {
                    ARBShaderObjects.glDeleteObjectARB((int)pi.getRef());
                    Shaders.checkGLError("del programRef");
                }
                pi.setRef(0);
                pi.setId(0);
                pi.setDrawBufSettings(null);
                pi.setDrawBuffers(null);
                pi.setCompositeMipmapSetting(0);
            }
            hasDeferredPrograms = false;
            if (dfb != 0) {
                EXTFramebufferObject.glDeleteFramebuffersEXT((int)dfb);
                dfb = 0;
                Shaders.checkGLError("del dfb");
            }
            if (sfb != 0) {
                EXTFramebufferObject.glDeleteFramebuffersEXT((int)sfb);
                sfb = 0;
                Shaders.checkGLError("del sfb");
            }
            if (dfbDepthTextures != null) {
                bus.deleteTextures((IntBuffer)dfbDepthTextures);
                Shaders.fillIntBufferZero(dfbDepthTextures);
                Shaders.checkGLError("del dfbDepthTextures");
            }
            if (dfbColorTextures != null) {
                bus.deleteTextures((IntBuffer)dfbColorTextures);
                Shaders.fillIntBufferZero(dfbColorTextures);
                Shaders.checkGLError("del dfbTextures");
            }
            if (sfbDepthTextures != null) {
                bus.deleteTextures((IntBuffer)sfbDepthTextures);
                Shaders.fillIntBufferZero(sfbDepthTextures);
                Shaders.checkGLError("del shadow depth");
            }
            if (sfbColorTextures != null) {
                bus.deleteTextures((IntBuffer)sfbColorTextures);
                Shaders.fillIntBufferZero(sfbColorTextures);
                Shaders.checkGLError("del shadow color");
            }
            if (dfbDrawBuffers != null) {
                Shaders.fillIntBufferZero(dfbDrawBuffers);
            }
            if (noiseTexture != null) {
                noiseTexture.deleteTexture();
                noiseTexture = null;
            }
            SMCLog.info("Uninit");
            shadowPassInterval = 0;
            shouldSkipDefaultShadow = false;
            isShaderPackInitialized = false;
            Shaders.checkGLError("Shaders.uninit");
        }
    }

    public static void scheduleResize() {
        renderDisplayHeight = 0;
    }

    public static void scheduleResizeShadow() {
        needResizeShadow = true;
    }

    private static void resize() {
        renderDisplayWidth = Shaders.mc.d;
        renderDisplayHeight = Shaders.mc.e;
        renderWidth = Math.round((float)renderDisplayWidth * configRenderResMul);
        renderHeight = Math.round((float)renderDisplayHeight * configRenderResMul);
        Shaders.setupFrameBuffer();
    }

    private static void resizeShadow() {
        needResizeShadow = false;
        shadowMapWidth = Math.round((float)spShadowMapWidth * configShadowResMul);
        shadowMapHeight = Math.round((float)spShadowMapHeight * configShadowResMul);
        Shaders.setupShadowFrameBuffer();
    }

    private static void setupFrameBuffer() {
        int i;
        if (dfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT((int)dfb);
            bus.deleteTextures((IntBuffer)dfbDepthTextures);
            bus.deleteTextures((IntBuffer)dfbColorTextures);
        }
        dfb = EXTFramebufferObject.glGenFramebuffersEXT();
        GL11.glGenTextures((IntBuffer)((IntBuffer)dfbDepthTextures.clear().limit(usedDepthBuffers)));
        GL11.glGenTextures((IntBuffer)((IntBuffer)dfbColorTextures.clear().limit(16)));
        dfbDepthTextures.position(0);
        dfbColorTextures.position(0);
        EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)dfb);
        GL20.glDrawBuffers((int)0);
        GL11.glReadBuffer((int)0);
        for (i = 0; i < usedDepthBuffers; ++i) {
            bus.i((int)dfbDepthTextures.get(i));
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
            GL11.glTexParameteri((int)3553, (int)34891, (int)6409);
            GL11.glTexImage2D((int)3553, (int)0, (int)6402, (int)renderWidth, (int)renderHeight, (int)0, (int)6402, (int)5126, (FloatBuffer)null);
        }
        EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)dfbDepthTextures.get(0), (int)0);
        GL20.glDrawBuffers((IntBuffer)dfbDrawBuffers);
        GL11.glReadBuffer((int)0);
        Shaders.checkGLError("FT d");
        for (i = 0; i < usedColorBuffers; ++i) {
            bus.i((int)dfbColorTexturesFlip.getA(i));
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexImage2D((int)3553, (int)0, (int)gbuffersFormat[i], (int)renderWidth, (int)renderHeight, (int)0, (int)Shaders.getPixelFormat(gbuffersFormat[i]), (int)33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getA(i), (int)0);
            Shaders.checkGLError("FT c");
        }
        for (i = 0; i < usedColorBuffers; ++i) {
            bus.i((int)dfbColorTexturesFlip.getB(i));
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexImage2D((int)3553, (int)0, (int)gbuffersFormat[i], (int)renderWidth, (int)renderHeight, (int)0, (int)Shaders.getPixelFormat(gbuffersFormat[i]), (int)33639, (ByteBuffer)null);
            Shaders.checkGLError("FT ca");
        }
        int status = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
        if (status == 36058) {
            Shaders.printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");
            for (int i2 = 0; i2 < usedColorBuffers; ++i2) {
                bus.i((int)dfbColorTexturesFlip.getA(i2));
                GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)renderWidth, (int)renderHeight, (int)0, (int)32993, (int)33639, (ByteBuffer)null);
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i2), (int)3553, (int)dfbColorTexturesFlip.getA(i2), (int)0);
                Shaders.checkGLError("FT c");
            }
            status = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
            if (status == 36053) {
                SMCLog.info("complete");
            }
        }
        bus.i((int)0);
        if (status != 36053) {
            Shaders.printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + status + ")");
        } else {
            SMCLog.info("Framebuffer created.");
        }
    }

    private static int getPixelFormat(int internalFormat) {
        switch (internalFormat) {
            case 33333: 
            case 33334: 
            case 33339: 
            case 33340: 
            case 36208: 
            case 36209: 
            case 36226: 
            case 36227: {
                return 36251;
            }
        }
        return 32993;
    }

    private static void setupShadowFrameBuffer() {
        int status;
        int filter;
        int i;
        if (usedShadowDepthBuffers == 0) {
            return;
        }
        if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT((int)sfb);
            bus.deleteTextures((IntBuffer)sfbDepthTextures);
            bus.deleteTextures((IntBuffer)sfbColorTextures);
        }
        sfb = EXTFramebufferObject.glGenFramebuffersEXT();
        EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)sfb);
        GL11.glDrawBuffer((int)0);
        GL11.glReadBuffer((int)0);
        GL11.glGenTextures((IntBuffer)((IntBuffer)sfbDepthTextures.clear().limit(usedShadowDepthBuffers)));
        GL11.glGenTextures((IntBuffer)((IntBuffer)sfbColorTextures.clear().limit(usedShadowColorBuffers)));
        sfbDepthTextures.position(0);
        sfbColorTextures.position(0);
        for (i = 0; i < usedShadowDepthBuffers; ++i) {
            bus.i((int)sfbDepthTextures.get(i));
            GL11.glTexParameterf((int)3553, (int)10242, (float)33071.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)33071.0f);
            filter = shadowFilterNearest[i] ? 9728 : 9729;
            GL11.glTexParameteri((int)3553, (int)10241, (int)filter);
            GL11.glTexParameteri((int)3553, (int)10240, (int)filter);
            if (shadowHardwareFilteringEnabled[i]) {
                GL11.glTexParameteri((int)3553, (int)34892, (int)34894);
            }
            GL11.glTexImage2D((int)3553, (int)0, (int)6402, (int)shadowMapWidth, (int)shadowMapHeight, (int)0, (int)6402, (int)5126, (FloatBuffer)null);
        }
        EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)sfbDepthTextures.get(0), (int)0);
        Shaders.checkGLError("FT sd");
        for (i = 0; i < usedShadowColorBuffers; ++i) {
            bus.i((int)sfbColorTextures.get(i));
            GL11.glTexParameterf((int)3553, (int)10242, (float)33071.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)33071.0f);
            filter = shadowColorFilterNearest[i] ? 9728 : 9729;
            GL11.glTexParameteri((int)3553, (int)10241, (int)filter);
            GL11.glTexParameteri((int)3553, (int)10240, (int)filter);
            GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)shadowMapWidth, (int)shadowMapHeight, (int)0, (int)32993, (int)33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)sfbColorTextures.get(i), (int)0);
            Shaders.checkGLError("FT sc");
        }
        bus.i((int)0);
        if (usedShadowColorBuffers > 0) {
            GL20.glDrawBuffers((IntBuffer)sfbDrawBuffers);
        }
        if ((status = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160)) != 36053) {
            Shaders.printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + status + ")");
        } else {
            SMCLog.info("Shadow framebuffer created.");
        }
    }

    public static void beginRender(bib minecraft, float partialTicks, long finishTimeNano) {
        block13: {
            Shaders.checkGLError("pre beginRender");
            Shaders.checkWorldChanged((amu)Shaders.mc.f);
            mc = minecraft;
            Shaders.mc.B.a("init");
            entityRenderer = Shaders.mc.o;
            if (!isShaderPackInitialized) {
                try {
                    Shaders.init();
                }
                catch (IllegalStateException e) {
                    if (!Config.normalize(e.getMessage()).equals("Function is not supported")) break block13;
                    Shaders.printChatAndLogError("[Shaders] Error: " + e.getMessage());
                    e.printStackTrace();
                    Shaders.setShaderPack(SHADER_PACK_NAME_NONE);
                    return;
                }
            }
        }
        if (Shaders.mc.d != renderDisplayWidth || Shaders.mc.e != renderDisplayHeight) {
            Shaders.resize();
        }
        if (needResizeShadow) {
            Shaders.resizeShadow();
        }
        if ((diffWorldTime = ((worldTime = Shaders.mc.f.S()) - lastWorldTime) % 24000L) < 0L) {
            diffWorldTime += 24000L;
        }
        lastWorldTime = worldTime;
        moonPhase = Shaders.mc.f.F();
        if (++frameCounter >= 720720) {
            frameCounter = 0;
        }
        systemTime = System.currentTimeMillis();
        if (lastSystemTime == 0L) {
            lastSystemTime = systemTime;
        }
        diffSystemTime = systemTime - lastSystemTime;
        lastSystemTime = systemTime;
        frameTime = (float)diffSystemTime / 1000.0f;
        frameTimeCounter += frameTime;
        frameTimeCounter %= 3600.0f;
        rainStrength = minecraft.f.j(partialTicks);
        float fadeScalar = (float)diffSystemTime * 0.01f;
        float temp1 = (float)Math.exp(Math.log(0.5) * (double)fadeScalar / (double)(wetness < rainStrength ? drynessHalfLife : wetnessHalfLife));
        wetness = wetness * temp1 + rainStrength * (1.0f - temp1);
        vg renderViewEntity = mc.aa();
        if (renderViewEntity != null) {
            isSleeping = renderViewEntity instanceof vp && ((vp)renderViewEntity).cz();
            eyePosY = (float)renderViewEntity.q * partialTicks + (float)renderViewEntity.N * (1.0f - partialTicks);
            eyeBrightness = renderViewEntity.av();
            float fadeScalar2 = (float)diffSystemTime * 0.01f;
            float temp2 = (float)Math.exp(Math.log(0.5) * (double)fadeScalar2 / (double)eyeBrightnessHalflife);
            eyeBrightnessFadeX = eyeBrightnessFadeX * temp2 + (float)(eyeBrightness & 0xFFFF) * (1.0f - temp2);
            eyeBrightnessFadeY = eyeBrightnessFadeY * temp2 + (float)(eyeBrightness >> 16) * (1.0f - temp2);
            awt cameraBlockState = bhv.a((amu)Shaders.mc.f, (vg)renderViewEntity, (float)partialTicks);
            bcz cameraPosMaterial = cameraBlockState.a();
            isEyeInWater = cameraPosMaterial == bcz.h ? 1 : (cameraPosMaterial == bcz.i ? 2 : 0);
            if (Shaders.mc.h != null) {
                nightVision = 0.0f;
                if (Shaders.mc.h.a(vb.p)) {
                    nightVision = Config.getMinecraft().o.a((vp)Shaders.mc.h, partialTicks);
                }
                blindness = 0.0f;
                if (Shaders.mc.h.a(vb.o)) {
                    int blindnessTicks = Shaders.mc.h.b(vb.o).b();
                    blindness = Config.limit((float)blindnessTicks / 20.0f, 0.0f, 1.0f);
                }
            }
            bhe skyColorV = Shaders.mc.f.a(renderViewEntity, partialTicks);
            skyColorV = CustomColors.getWorldSkyColor(skyColorV, currentWorld, renderViewEntity, partialTicks);
            skyColorR = (float)skyColorV.b;
            skyColorG = (float)skyColorV.c;
            skyColorB = (float)skyColorV.d;
        }
        isRenderingWorld = true;
        isCompositeRendered = false;
        isShadowPass = false;
        isHandRenderedMain = false;
        isHandRenderedOff = false;
        skipRenderHandMain = false;
        skipRenderHandOff = false;
        Shaders.bindGbuffersTextures();
        previousCameraPositionX = cameraPositionX;
        previousCameraPositionY = cameraPositionY;
        previousCameraPositionZ = cameraPositionZ;
        previousProjection.position(0);
        projection.position(0);
        previousProjection.put(projection);
        previousProjection.position(0);
        projection.position(0);
        previousModelView.position(0);
        modelView.position(0);
        previousModelView.put(modelView);
        previousModelView.position(0);
        modelView.position(0);
        Shaders.checkGLError("beginRender");
        ShadersRender.renderShadowMap(entityRenderer, 0, partialTicks, finishTimeNano);
        Shaders.mc.B.b();
        EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)dfb);
        for (int i = 0; i < usedColorBuffers; ++i) {
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getA(i), (int)0);
        }
        Shaders.checkGLError("end beginRender");
    }

    private static void bindGbuffersTextures() {
        int i;
        if (usedShadowDepthBuffers >= 1) {
            bus.g((int)33988);
            bus.i((int)sfbDepthTextures.get(0));
            if (usedShadowDepthBuffers >= 2) {
                bus.g((int)33989);
                bus.i((int)sfbDepthTextures.get(1));
            }
        }
        bus.g((int)33984);
        for (i = 0; i < usedColorBuffers; ++i) {
            bus.i((int)dfbColorTexturesFlip.getA(i));
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            bus.i((int)dfbColorTexturesFlip.getB(i));
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        }
        bus.i((int)0);
        for (i = 0; i < 4 && 4 + i < usedColorBuffers; ++i) {
            bus.g((int)(33991 + i));
            bus.i((int)dfbColorTexturesFlip.getA(4 + i));
        }
        bus.g((int)33990);
        bus.i((int)dfbDepthTextures.get(0));
        if (usedDepthBuffers >= 2) {
            bus.g((int)33995);
            bus.i((int)dfbDepthTextures.get(1));
            if (usedDepthBuffers >= 3) {
                bus.g((int)33996);
                bus.i((int)dfbDepthTextures.get(2));
            }
        }
        for (i = 0; i < usedShadowColorBuffers; ++i) {
            bus.g((int)(33997 + i));
            bus.i((int)sfbColorTextures.get(i));
        }
        if (noiseTextureEnabled) {
            bus.g((int)(33984 + noiseTexture.getTextureUnit()));
            bus.i((int)noiseTexture.getTextureId());
        }
        Shaders.bindCustomTextures(customTexturesGbuffers);
        bus.g((int)33984);
    }

    public static void checkWorldChanged(amu world) {
        if (currentWorld == world) {
            return;
        }
        amu oldWorld = currentWorld;
        currentWorld = world;
        Shaders.setCameraOffset(mc.aa());
        int dimIdOld = Shaders.getDimensionId(oldWorld);
        int dimIdNew = Shaders.getDimensionId(world);
        if (dimIdNew != dimIdOld) {
            boolean dimShadersOld = shaderPackDimensions.contains(dimIdOld);
            boolean dimShadersNew = shaderPackDimensions.contains(dimIdNew);
            if (dimShadersOld || dimShadersNew) {
                Shaders.uninit();
            }
        }
        Smoother.resetValues();
    }

    private static int getDimensionId(amu world) {
        if (world == null) {
            return Integer.MIN_VALUE;
        }
        return world.s.q().a();
    }

    public static void beginRenderPass(int pass, float partialTicks, long finishTimeNano) {
        if (isShadowPass) {
            return;
        }
        EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)dfb);
        GL11.glViewport((int)0, (int)0, (int)renderWidth, (int)renderHeight);
        activeDrawBuffers = null;
        ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
        Shaders.useProgram(ProgramTextured);
        Shaders.checkGLError("end beginRenderPass");
    }

    public static void setViewport(int vx, int vy, int vw, int vh) {
        bus.a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        if (isShadowPass) {
            GL11.glViewport((int)0, (int)0, (int)shadowMapWidth, (int)shadowMapHeight);
        } else {
            GL11.glViewport((int)0, (int)0, (int)renderWidth, (int)renderHeight);
            EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)dfb);
            isRenderingDfb = true;
            bus.q();
            bus.k();
            Shaders.setDrawBuffers(drawBuffersNone);
            Shaders.useProgram(ProgramTextured);
            Shaders.checkGLError("beginRenderPass");
        }
    }

    public static void setFogMode(int value) {
        fogMode = value;
        if (fogEnabled) {
            Shaders.setProgramUniform1i(uniform_fogMode, value);
        }
    }

    public static void setFogColor(float r, float g, float b2) {
        fogColorR = r;
        fogColorG = g;
        fogColorB = b2;
        Shaders.setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
    }

    public static void setClearColor(float red, float green, float blue, float alpha) {
        bus.a((float)red, (float)green, (float)blue, (float)alpha);
        clearColorR = red;
        clearColorG = green;
        clearColorB = blue;
    }

    public static void clearRenderBuffer() {
        Vector4f col;
        if (isShadowPass) {
            Shaders.checkGLError("shadow clear pre");
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)sfbDepthTextures.get(0), (int)0);
            GL11.glClearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL20.glDrawBuffers((IntBuffer)ProgramShadow.getDrawBuffers());
            Shaders.checkFramebufferStatus("shadow clear");
            GL11.glClear((int)16640);
            Shaders.checkGLError("shadow clear");
            return;
        }
        Shaders.checkGLError("clear pre");
        if (gbuffersClear[0]) {
            col = gbuffersClearColor[0];
            if (col != null) {
                GL11.glClearColor((float)col.getX(), (float)col.getY(), (float)col.getZ(), (float)col.getW());
            }
            if (dfbColorTexturesFlip.isChanged(0)) {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36064, (int)3553, (int)dfbColorTexturesFlip.getB(0), (int)0);
                GL20.glDrawBuffers((int)36064);
                GL11.glClear((int)16384);
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36064, (int)3553, (int)dfbColorTexturesFlip.getA(0), (int)0);
            }
            GL20.glDrawBuffers((int)36064);
            GL11.glClear((int)16384);
        }
        if (gbuffersClear[1]) {
            GL11.glClearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            col = gbuffersClearColor[1];
            if (col != null) {
                GL11.glClearColor((float)col.getX(), (float)col.getY(), (float)col.getZ(), (float)col.getW());
            }
            if (dfbColorTexturesFlip.isChanged(1)) {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36065, (int)3553, (int)dfbColorTexturesFlip.getB(1), (int)0);
                GL20.glDrawBuffers((int)36065);
                GL11.glClear((int)16384);
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36065, (int)3553, (int)dfbColorTexturesFlip.getA(1), (int)0);
            }
            GL20.glDrawBuffers((int)36065);
            GL11.glClear((int)16384);
        }
        for (int i = 2; i < usedColorBuffers; ++i) {
            if (!gbuffersClear[i]) continue;
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            Vector4f col2 = gbuffersClearColor[i];
            if (col2 != null) {
                GL11.glClearColor((float)col2.getX(), (float)col2.getY(), (float)col2.getZ(), (float)col2.getW());
            }
            if (dfbColorTexturesFlip.isChanged(i)) {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getB(i), (int)0);
                GL20.glDrawBuffers((int)(36064 + i));
                GL11.glClear((int)16384);
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getA(i), (int)0);
            }
            GL20.glDrawBuffers((int)(36064 + i));
            GL11.glClear((int)16384);
        }
        Shaders.setDrawBuffers(dfbDrawBuffers);
        Shaders.checkFramebufferStatus("clear");
        Shaders.checkGLError("clear");
    }

    public static void setCamera(float partialTicks) {
        vg viewEntity = mc.aa();
        double x = viewEntity.M + (viewEntity.p - viewEntity.M) * (double)partialTicks;
        double y = viewEntity.N + (viewEntity.q - viewEntity.N) * (double)partialTicks;
        double z = viewEntity.O + (viewEntity.r - viewEntity.O) * (double)partialTicks;
        Shaders.updateCameraOffset(viewEntity);
        cameraPositionX = x - (double)cameraOffsetX;
        cameraPositionY = y;
        cameraPositionZ = z - (double)cameraOffsetZ;
        GL11.glGetFloat((int)2983, (FloatBuffer)((FloatBuffer)projection.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
        projection.position(0);
        projectionInverse.position(0);
        GL11.glGetFloat((int)2982, (FloatBuffer)((FloatBuffer)modelView.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
        modelView.position(0);
        modelViewInverse.position(0);
        Shaders.checkGLError("setCamera");
    }

    private static void updateCameraOffset(vg viewEntity) {
        double adx = Math.abs(cameraPositionX - previousCameraPositionX);
        double adz = Math.abs(cameraPositionZ - previousCameraPositionZ);
        double apx = Math.abs(cameraPositionX);
        double apz = Math.abs(cameraPositionZ);
        if (adx > 1000.0 || adz > 1000.0 || apx > 1000000.0 || apz > 1000000.0) {
            Shaders.setCameraOffset(viewEntity);
        }
    }

    private static void setCameraOffset(vg viewEntity) {
        if (viewEntity == null) {
            cameraOffsetX = 0;
            cameraOffsetZ = 0;
            return;
        }
        cameraOffsetX = (int)viewEntity.p / 1000 * 1000;
        cameraOffsetZ = (int)viewEntity.r / 1000 * 1000;
    }

    public static void setCameraShadow(float partialTicks) {
        float angleInterval;
        vg viewEntity = mc.aa();
        double x = viewEntity.M + (viewEntity.p - viewEntity.M) * (double)partialTicks;
        double y = viewEntity.N + (viewEntity.q - viewEntity.N) * (double)partialTicks;
        double z = viewEntity.O + (viewEntity.r - viewEntity.O) * (double)partialTicks;
        Shaders.updateCameraOffset(viewEntity);
        cameraPositionX = x - (double)cameraOffsetX;
        cameraPositionY = y;
        cameraPositionZ = z - (double)cameraOffsetZ;
        GL11.glGetFloat((int)2983, (FloatBuffer)((FloatBuffer)projection.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
        projection.position(0);
        projectionInverse.position(0);
        GL11.glGetFloat((int)2982, (FloatBuffer)((FloatBuffer)modelView.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
        modelView.position(0);
        modelViewInverse.position(0);
        GL11.glViewport((int)0, (int)0, (int)shadowMapWidth, (int)shadowMapHeight);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        if (shadowMapIsOrtho) {
            GL11.glOrtho((double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)0.05f, (double)256.0);
        } else {
            GLU.gluPerspective((float)shadowMapFOV, (float)((float)shadowMapWidth / (float)shadowMapHeight), (float)0.05f, (float)256.0f);
        }
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-100.0f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        celestialAngle = Shaders.mc.f.c(partialTicks);
        sunAngle = celestialAngle < 0.75f ? celestialAngle + 0.25f : celestialAngle - 0.75f;
        float angle = celestialAngle * -360.0f;
        float f = angleInterval = shadowAngleInterval > 0.0f ? angle % shadowAngleInterval - shadowAngleInterval * 0.5f : 0.0f;
        if ((double)sunAngle <= 0.5) {
            GL11.glRotatef((float)(angle - angleInterval), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)sunPathRotation, (float)1.0f, (float)0.0f, (float)0.0f);
            shadowAngle = sunAngle;
        } else {
            GL11.glRotatef((float)(angle + 180.0f - angleInterval), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)sunPathRotation, (float)1.0f, (float)0.0f, (float)0.0f);
            shadowAngle = sunAngle - 0.5f;
        }
        if (shadowMapIsOrtho) {
            float trans = shadowIntervalSize;
            float trans2 = trans / 2.0f;
            GL11.glTranslatef((float)((float)x % trans - trans2), (float)((float)y % trans - trans2), (float)((float)z % trans - trans2));
        }
        float raSun = sunAngle * ((float)Math.PI * 2);
        float x1 = (float)Math.cos(raSun);
        float y1 = (float)Math.sin(raSun);
        float raTilt = sunPathRotation * ((float)Math.PI * 2);
        float x2 = x1;
        float y2 = y1 * (float)Math.cos(raTilt);
        float z2 = y1 * (float)Math.sin(raTilt);
        if ((double)sunAngle > 0.5) {
            x2 = -x2;
            y2 = -y2;
            z2 = -z2;
        }
        Shaders.shadowLightPositionVector[0] = x2;
        Shaders.shadowLightPositionVector[1] = y2;
        Shaders.shadowLightPositionVector[2] = z2;
        Shaders.shadowLightPositionVector[3] = 0.0f;
        GL11.glGetFloat((int)2983, (FloatBuffer)((FloatBuffer)shadowProjection.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)shadowProjectionInverse.position(0), (FloatBuffer)shadowProjection.position(0), faShadowProjectionInverse, faShadowProjection);
        shadowProjection.position(0);
        shadowProjectionInverse.position(0);
        GL11.glGetFloat((int)2982, (FloatBuffer)((FloatBuffer)shadowModelView.position(0)));
        SMath.invertMat4FBFA((FloatBuffer)shadowModelViewInverse.position(0), (FloatBuffer)shadowModelView.position(0), faShadowModelViewInverse, faShadowModelView);
        shadowModelView.position(0);
        shadowModelViewInverse.position(0);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
        Shaders.mc.t.aw = 1;
        Shaders.checkGLError("setCamera");
    }

    public static void preCelestialRotate() {
        GL11.glRotatef((float)(sunPathRotation * 1.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        Shaders.checkGLError("preCelestialRotate");
    }

    public static void postCelestialRotate() {
        FloatBuffer modelView = tempMatrixDirectBuffer;
        modelView.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)modelView);
        modelView.get(tempMat, 0, 16);
        SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
        SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
        System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
        Shaders.setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
        Shaders.setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
        Shaders.setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
        if (customUniforms != null) {
            customUniforms.update();
        }
        Shaders.checkGLError("postCelestialRotate");
    }

    public static void setUpPosition() {
        FloatBuffer modelView = tempMatrixDirectBuffer;
        modelView.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)modelView);
        modelView.get(tempMat, 0, 16);
        SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
        Shaders.setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
        if (customUniforms != null) {
            customUniforms.update();
        }
    }

    public static void genCompositeMipmap() {
        if (hasGlGenMipmap) {
            for (int i = 0; i < usedColorBuffers; ++i) {
                if ((activeCompositeMipmapSetting & 1 << i) == 0) continue;
                bus.g((int)(33984 + colorTextureImageUnit[i]));
                GL11.glTexParameteri((int)3553, (int)10241, (int)9987);
                GL30.glGenerateMipmap((int)3553);
            }
            bus.g((int)33984);
        }
    }

    public static void drawComposite() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Shaders.drawCompositeQuad();
        int countInstances = activeProgram.getCountInstances();
        if (countInstances > 1) {
            for (int i = 1; i < countInstances; ++i) {
                uniform_instanceId.setValue(i);
                Shaders.drawCompositeQuad();
            }
            uniform_instanceId.setValue(0);
        }
    }

    private static void drawCompositeQuad() {
        if (!Shaders.canRenderQuads()) {
            GL11.glBegin((int)5);
            GL11.glTexCoord2f((float)0.0f, (float)0.0f);
            GL11.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glTexCoord2f((float)1.0f, (float)0.0f);
            GL11.glVertex3f((float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTexCoord2f((float)0.0f, (float)1.0f);
            GL11.glVertex3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTexCoord2f((float)1.0f, (float)1.0f);
            GL11.glVertex3f((float)1.0f, (float)1.0f, (float)0.0f);
            GL11.glEnd();
            return;
        }
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)0.0f, (float)0.0f);
        GL11.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)0.0f);
        GL11.glVertex3f((float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)1.0f);
        GL11.glVertex3f((float)1.0f, (float)1.0f, (float)0.0f);
        GL11.glTexCoord2f((float)0.0f, (float)1.0f);
        GL11.glVertex3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glEnd();
    }

    public static void renderDeferred() {
        if (isShadowPass) {
            return;
        }
        boolean buffersChanged = Shaders.checkBufferFlip(ProgramDeferredPre);
        if (hasDeferredPrograms) {
            Shaders.checkGLError("pre-render Deferred");
            Shaders.renderComposites(ProgramsDeferred, false);
            buffersChanged = true;
        }
        if (buffersChanged) {
            Shaders.bindGbuffersTextures();
            for (int i = 0; i < usedColorBuffers; ++i) {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getA(i), (int)0);
            }
            if (ProgramWater.getDrawBuffers() != null) {
                Shaders.setDrawBuffers(ProgramWater.getDrawBuffers());
            } else {
                Shaders.setDrawBuffers(dfbDrawBuffers);
            }
            bus.g((int)33984);
            mc.N().a(cdp.g);
        }
    }

    public static void renderCompositeFinal() {
        if (isShadowPass) {
            return;
        }
        Shaders.checkBufferFlip(ProgramCompositePre);
        Shaders.checkGLError("pre-render CompositeFinal");
        Shaders.renderComposites(ProgramsComposite, true);
    }

    private static boolean checkBufferFlip(Program program) {
        boolean flipped = false;
        Boolean[] buffersFlip = program.getBuffersFlip();
        for (int i = 0; i < usedColorBuffers; ++i) {
            if (!Config.isTrue(buffersFlip[i])) continue;
            dfbColorTexturesFlip.flip(i);
            flipped = true;
        }
        return flipped;
    }

    private static void renderComposites(Program[] ps, boolean renderFinal) {
        int i;
        if (isShadowPass) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)1.0, (double)0.0, (double)1.0, (double)0.0, (double)1.0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        bus.y();
        bus.d();
        bus.l();
        bus.k();
        bus.c((int)519);
        bus.a((boolean)false);
        bus.g();
        if (usedShadowDepthBuffers >= 1) {
            bus.g((int)33988);
            bus.i((int)sfbDepthTextures.get(0));
            if (usedShadowDepthBuffers >= 2) {
                bus.g((int)33989);
                bus.i((int)sfbDepthTextures.get(1));
            }
        }
        for (i = 0; i < usedColorBuffers; ++i) {
            bus.g((int)(33984 + colorTextureImageUnit[i]));
            bus.i((int)dfbColorTexturesFlip.getA(i));
        }
        bus.g((int)33990);
        bus.i((int)dfbDepthTextures.get(0));
        if (usedDepthBuffers >= 2) {
            bus.g((int)33995);
            bus.i((int)dfbDepthTextures.get(1));
            if (usedDepthBuffers >= 3) {
                bus.g((int)33996);
                bus.i((int)dfbDepthTextures.get(2));
            }
        }
        for (i = 0; i < usedShadowColorBuffers; ++i) {
            bus.g((int)(33997 + i));
            bus.i((int)sfbColorTextures.get(i));
        }
        if (noiseTextureEnabled) {
            bus.g((int)(33984 + noiseTexture.getTextureUnit()));
            bus.i((int)noiseTexture.getTextureId());
        }
        if (renderFinal) {
            Shaders.bindCustomTextures(customTexturesComposite);
        } else {
            Shaders.bindCustomTextures(customTexturesDeferred);
        }
        bus.g((int)33984);
        for (i = 0; i < usedColorBuffers; ++i) {
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i), (int)3553, (int)dfbColorTexturesFlip.getB(i), (int)0);
        }
        EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)dfbDepthTextures.get(0), (int)0);
        GL20.glDrawBuffers((IntBuffer)dfbDrawBuffers);
        Shaders.checkGLError("pre-composite");
        for (int cp = 0; cp < ps.length; ++cp) {
            Program program = ps[cp];
            if (program.getId() == 0) continue;
            Shaders.useProgram(program);
            Shaders.checkGLError(program.getName());
            if (activeCompositeMipmapSetting != 0) {
                Shaders.genCompositeMipmap();
            }
            Shaders.preDrawComposite();
            Shaders.drawComposite();
            Shaders.postDrawComposite();
            for (int i2 = 0; i2 < usedColorBuffers; ++i2) {
                if (!program.getToggleColorTextures()[i2]) continue;
                dfbColorTexturesFlip.flip(i2);
                bus.g((int)(33984 + colorTextureImageUnit[i2]));
                bus.i((int)dfbColorTexturesFlip.getA(i2));
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)(36064 + i2), (int)3553, (int)dfbColorTexturesFlip.getB(i2), (int)0);
            }
            bus.g((int)33984);
        }
        Shaders.checkGLError("composite");
        if (renderFinal) {
            Shaders.renderFinal();
            isCompositeRendered = true;
        }
        bus.f();
        bus.y();
        bus.e();
        bus.m();
        bus.c((int)515);
        bus.a((boolean)true);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        Shaders.useProgram(ProgramNone);
    }

    private static void preDrawComposite() {
        RenderScale rs = activeProgram.getRenderScale();
        if (rs != null) {
            int x = (int)((float)renderWidth * rs.getOffsetX());
            int y = (int)((float)renderHeight * rs.getOffsetY());
            int w = (int)((float)renderWidth * rs.getScale());
            int h2 = (int)((float)renderHeight * rs.getScale());
            GL11.glViewport((int)x, (int)y, (int)w, (int)h2);
        }
    }

    private static void postDrawComposite() {
        RenderScale rs = activeProgram.getRenderScale();
        if (rs != null) {
            GL11.glViewport((int)0, (int)0, (int)renderWidth, (int)renderHeight);
        }
    }

    private static void renderFinal() {
        isRenderingDfb = false;
        mc.b().a(true);
        cii.a((int)cii.c, (int)cii.e, (int)3553, (int)Shaders.mc.b().g, (int)0);
        GL11.glViewport((int)0, (int)0, (int)Shaders.mc.d, (int)Shaders.mc.e);
        if (buq.a) {
            boolean maskR = buq.b != 0;
            bus.a((boolean)maskR, (!maskR ? 1 : 0) != 0, (!maskR ? 1 : 0) != 0, (boolean)true);
        }
        bus.a((boolean)true);
        GL11.glClearColor((float)clearColorR, (float)clearColorG, (float)clearColorB, (float)1.0f);
        GL11.glClear((int)16640);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        bus.y();
        bus.d();
        bus.l();
        bus.k();
        bus.c((int)519);
        bus.a((boolean)false);
        Shaders.checkGLError("pre-final");
        Shaders.useProgram(ProgramFinal);
        Shaders.checkGLError("final");
        if (activeCompositeMipmapSetting != 0) {
            Shaders.genCompositeMipmap();
        }
        Shaders.drawComposite();
        Shaders.checkGLError("renderCompositeFinal");
    }

    public static void endRender() {
        if (isShadowPass) {
            Shaders.checkGLError("shadow endRender");
            return;
        }
        if (!isCompositeRendered) {
            Shaders.renderCompositeFinal();
        }
        isRenderingWorld = false;
        bus.a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        Shaders.useProgram(ProgramNone);
        bhz.a();
        Shaders.checkGLError("endRender end");
    }

    public static void beginSky() {
        isRenderingSky = true;
        fogEnabled = true;
        Shaders.setDrawBuffers(dfbDrawBuffers);
        Shaders.useProgram(ProgramSkyTextured);
        Shaders.pushEntity(-2, 0);
    }

    public static void setSkyColor(bhe v3color) {
        skyColorR = (float)v3color.b;
        skyColorG = (float)v3color.c;
        skyColorB = (float)v3color.d;
        Shaders.setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
    }

    public static void drawHorizon() {
        buk tess = bve.a().c();
        float farDistance = Shaders.mc.t.e * 16;
        double xzq = (double)farDistance * 0.9238;
        double xzp = (double)farDistance * 0.3826;
        double xzn = -xzp;
        double xzm = -xzq;
        double top = 16.0;
        double bot = -cameraPositionY;
        tess.a(7, cdy.e);
        tess.b(xzn, bot, xzm).d();
        tess.b(xzn, top, xzm).d();
        tess.b(xzm, top, xzn).d();
        tess.b(xzm, bot, xzn).d();
        tess.b(xzm, bot, xzn).d();
        tess.b(xzm, top, xzn).d();
        tess.b(xzm, top, xzp).d();
        tess.b(xzm, bot, xzp).d();
        tess.b(xzm, bot, xzp).d();
        tess.b(xzm, top, xzp).d();
        tess.b(xzn, top, xzq).d();
        tess.b(xzn, bot, xzq).d();
        tess.b(xzn, bot, xzq).d();
        tess.b(xzn, top, xzq).d();
        tess.b(xzp, top, xzq).d();
        tess.b(xzp, bot, xzq).d();
        tess.b(xzp, bot, xzq).d();
        tess.b(xzp, top, xzq).d();
        tess.b(xzq, top, xzp).d();
        tess.b(xzq, bot, xzp).d();
        tess.b(xzq, bot, xzp).d();
        tess.b(xzq, top, xzp).d();
        tess.b(xzq, top, xzn).d();
        tess.b(xzq, bot, xzn).d();
        tess.b(xzq, bot, xzn).d();
        tess.b(xzq, top, xzn).d();
        tess.b(xzp, top, xzm).d();
        tess.b(xzp, bot, xzm).d();
        tess.b(xzp, bot, xzm).d();
        tess.b(xzp, top, xzm).d();
        tess.b(xzn, top, xzm).d();
        tess.b(xzn, bot, xzm).d();
        bve.a().b();
    }

    public static void preSkyList() {
        Shaders.setUpPosition();
        GL11.glColor3f((float)fogColorR, (float)fogColorG, (float)fogColorB);
        Shaders.drawHorizon();
        GL11.glColor3f((float)skyColorR, (float)skyColorG, (float)skyColorB);
    }

    public static void endSky() {
        isRenderingSky = false;
        Shaders.setDrawBuffers(dfbDrawBuffers);
        Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        Shaders.popEntity();
    }

    public static void beginUpdateChunks() {
        Shaders.checkGLError("beginUpdateChunks1");
        Shaders.checkFramebufferStatus("beginUpdateChunks1");
        if (!isShadowPass) {
            Shaders.useProgram(ProgramTerrain);
        }
        Shaders.checkGLError("beginUpdateChunks2");
        Shaders.checkFramebufferStatus("beginUpdateChunks2");
    }

    public static void endUpdateChunks() {
        Shaders.checkGLError("endUpdateChunks1");
        Shaders.checkFramebufferStatus("endUpdateChunks1");
        if (!isShadowPass) {
            Shaders.useProgram(ProgramTerrain);
        }
        Shaders.checkGLError("endUpdateChunks2");
        Shaders.checkFramebufferStatus("endUpdateChunks2");
    }

    public static boolean shouldRenderClouds(bid gs) {
        if (!shaderPackLoaded) {
            return true;
        }
        Shaders.checkGLError("shouldRenderClouds");
        return isShadowPass ? configCloudShadow : gs.j > 0;
    }

    public static void beginClouds() {
        fogEnabled = true;
        Shaders.pushEntity(-3, 0);
        Shaders.useProgram(ProgramClouds);
    }

    public static void endClouds() {
        Shaders.disableFog();
        Shaders.popEntity();
        Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
    }

    public static void beginEntities() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramEntities);
        }
    }

    public static void nextEntity(vg entity) {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramEntities);
            Shaders.setEntityId(entity);
        }
    }

    public static void setEntityId(vg entity) {
        if (uniform_entityId.isDefined()) {
            int id = EntityUtils.getEntityIdByClass(entity);
            int idAlias = EntityAliases.getEntityAliasId(id);
            if (idAlias >= 0) {
                id = idAlias;
            }
            uniform_entityId.setValue(id);
        }
    }

    public static void beginSpiderEyes() {
        if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
            Shaders.useProgram(ProgramSpiderEyes);
            bus.e();
            bus.a((int)516, (float)0.0f);
            bus.b((int)770, (int)771);
        }
    }

    public static void endSpiderEyes() {
        if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
            Shaders.useProgram(ProgramEntities);
            bus.d();
        }
    }

    public static void endEntities() {
        if (isRenderingWorld) {
            Shaders.setEntityId(null);
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void beginEntitiesGlowing() {
        if (isRenderingWorld) {
            isEntitiesGlowing = true;
        }
    }

    public static void endEntitiesGlowing() {
        if (isRenderingWorld) {
            isEntitiesGlowing = false;
        }
    }

    public static void setEntityColor(float r, float g, float b2, float a2) {
        if (isRenderingWorld && !isShadowPass) {
            uniform_entityColor.setValue(r, g, b2, a2);
        }
    }

    public static void beginLivingDamage() {
        if (isRenderingWorld) {
            ShadersTex.bindTexture(defaultTexture);
            if (!isShadowPass) {
                Shaders.setDrawBuffers(drawBuffersColorAtt0);
            }
        }
    }

    public static void endLivingDamage() {
        if (isRenderingWorld && !isShadowPass) {
            Shaders.setDrawBuffers(ProgramEntities.getDrawBuffers());
        }
    }

    public static void beginBlockEntities() {
        if (isRenderingWorld) {
            Shaders.checkGLError("beginBlockEntities");
            Shaders.useProgram(ProgramBlock);
        }
    }

    public static void nextBlockEntity(avj tileEntity) {
        if (isRenderingWorld) {
            Shaders.checkGLError("nextBlockEntity");
            Shaders.useProgram(ProgramBlock);
            Shaders.setBlockEntityId(tileEntity);
        }
    }

    public static void setBlockEntityId(avj tileEntity) {
        if (uniform_blockEntityId.isDefined()) {
            int blockId = Shaders.getBlockEntityId(tileEntity);
            uniform_blockEntityId.setValue(blockId);
        }
    }

    private static int getBlockEntityId(avj tileEntity) {
        int metadata;
        if (tileEntity == null) {
            return -1;
        }
        aow block = tileEntity.x();
        if (block == null) {
            return 0;
        }
        int blockId = aow.a((aow)block);
        int blockAliasId = BlockAliases.getBlockAliasId(blockId, metadata = tileEntity.v());
        if (blockAliasId >= 0) {
            blockId = blockAliasId;
        }
        return blockId;
    }

    public static void endBlockEntities() {
        if (isRenderingWorld) {
            Shaders.checkGLError("endBlockEntities");
            Shaders.setBlockEntityId(null);
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
            ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
        }
    }

    public static void beginLitParticles() {
        Shaders.useProgram(ProgramTexturedLit);
    }

    public static void beginParticles() {
        Shaders.useProgram(ProgramTextured);
    }

    public static void endParticles() {
        Shaders.useProgram(ProgramTexturedLit);
    }

    public static void readCenterDepth() {
        if (!isShadowPass && centerDepthSmoothEnabled) {
            tempDirectFloatBuffer.clear();
            GL11.glReadPixels((int)(renderWidth / 2), (int)(renderHeight / 2), (int)1, (int)1, (int)6402, (int)5126, (FloatBuffer)tempDirectFloatBuffer);
            centerDepth = tempDirectFloatBuffer.get(0);
            float fadeScalar = (float)diffSystemTime * 0.01f;
            float fadeFactor = (float)Math.exp(Math.log(0.5) * (double)fadeScalar / (double)centerDepthSmoothHalflife);
            centerDepthSmooth = centerDepthSmooth * fadeFactor + centerDepth * (1.0f - fadeFactor);
        }
    }

    public static void beginWeather() {
        if (!isShadowPass) {
            if (usedDepthBuffers >= 3) {
                bus.g((int)33996);
                GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)renderWidth, (int)renderHeight);
                bus.g((int)33984);
            }
            bus.k();
            bus.m();
            bus.b((int)770, (int)771);
            bus.e();
            Shaders.useProgram(ProgramWeather);
        }
    }

    public static void endWeather() {
        bus.l();
        Shaders.useProgram(ProgramTexturedLit);
    }

    public static void preWater() {
        if (usedDepthBuffers >= 2) {
            bus.g((int)33995);
            Shaders.checkGLError("pre copy depth");
            GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)renderWidth, (int)renderHeight);
            Shaders.checkGLError("copy depth");
            bus.g((int)33984);
        }
        ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
    }

    public static void beginWater() {
        if (isRenderingWorld) {
            if (!isShadowPass) {
                Shaders.renderDeferred();
                Shaders.useProgram(ProgramWater);
                bus.m();
                bus.a((boolean)true);
            } else {
                bus.a((boolean)true);
            }
        }
    }

    public static void endWater() {
        if (isRenderingWorld) {
            if (isShadowPass) {
                // empty if block
            }
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void applyHandDepth() {
        if ((double)configHandDepthMul != 1.0) {
            GL11.glScaled((double)1.0, (double)1.0, (double)configHandDepthMul);
        }
    }

    public static void beginHand(boolean translucent) {
        GL11.glMatrixMode((int)5888);
        GL11.glPushMatrix();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glMatrixMode((int)5888);
        if (translucent) {
            Shaders.useProgram(ProgramHandWater);
        } else {
            Shaders.useProgram(ProgramHand);
        }
        Shaders.checkGLError("beginHand");
        Shaders.checkFramebufferStatus("beginHand");
    }

    public static void endHand() {
        Shaders.checkGLError("pre endHand");
        Shaders.checkFramebufferStatus("pre endHand");
        GL11.glMatrixMode((int)5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        bus.b((int)770, (int)771);
        Shaders.checkGLError("endHand");
    }

    public static void beginFPOverlay() {
        bus.g();
        bus.l();
    }

    public static void endFPOverlay() {
    }

    public static void glEnableWrapper(int cap2) {
        GL11.glEnable((int)cap2);
        if (cap2 == 3553) {
            Shaders.enableTexture2D();
        } else if (cap2 == 2912) {
            Shaders.enableFog();
        }
    }

    public static void glDisableWrapper(int cap2) {
        GL11.glDisable((int)cap2);
        if (cap2 == 3553) {
            Shaders.disableTexture2D();
        } else if (cap2 == 2912) {
            Shaders.disableFog();
        }
    }

    public static void sglEnableT2D(int cap2) {
        GL11.glEnable((int)cap2);
        Shaders.enableTexture2D();
    }

    public static void sglDisableT2D(int cap2) {
        GL11.glDisable((int)cap2);
        Shaders.disableTexture2D();
    }

    public static void sglEnableFog(int cap2) {
        GL11.glEnable((int)cap2);
        Shaders.enableFog();
    }

    public static void sglDisableFog(int cap2) {
        GL11.glDisable((int)cap2);
        Shaders.disableFog();
    }

    public static void enableTexture2D() {
        if (isRenderingSky) {
            Shaders.useProgram(ProgramSkyTextured);
        } else if (activeProgram == ProgramBasic) {
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void disableTexture2D() {
        if (isRenderingSky) {
            Shaders.useProgram(ProgramSkyBasic);
        } else if (activeProgram == ProgramTextured || activeProgram == ProgramTexturedLit) {
            Shaders.useProgram(ProgramBasic);
        }
    }

    public static void beginLeash() {
        programStackLeash.push(activeProgram);
        Shaders.useProgram(ProgramBasic);
    }

    public static void endLeash() {
        Shaders.useProgram(programStackLeash.pop());
    }

    public static void enableFog() {
        fogEnabled = true;
        Shaders.setProgramUniform1i(uniform_fogMode, fogMode);
        Shaders.setProgramUniform1f(uniform_fogDensity, fogDensity);
    }

    public static void disableFog() {
        fogEnabled = false;
        Shaders.setProgramUniform1i(uniform_fogMode, 0);
    }

    public static void setFogMode(bus.m fogMode) {
        Shaders.setFogMode(fogMode.d);
    }

    public static void setFogDensity(float value) {
        fogDensity = value;
        if (fogEnabled) {
            Shaders.setProgramUniform1f(uniform_fogDensity, value);
        }
    }

    public static void sglFogi(int pname, int param) {
        GL11.glFogi((int)pname, (int)param);
        if (pname == 2917) {
            fogMode = param;
            if (fogEnabled) {
                Shaders.setProgramUniform1i(uniform_fogMode, fogMode);
            }
        }
    }

    public static void enableLightmap() {
        lightmapEnabled = true;
        if (activeProgram == ProgramTextured) {
            Shaders.useProgram(ProgramTexturedLit);
        }
    }

    public static void disableLightmap() {
        lightmapEnabled = false;
        if (activeProgram == ProgramTexturedLit) {
            Shaders.useProgram(ProgramTextured);
        }
    }

    public static int getEntityData() {
        return entityData[entityDataIndex * 2];
    }

    public static int getEntityData2() {
        return entityData[entityDataIndex * 2 + 1];
    }

    public static int setEntityData1(int data1) {
        Shaders.entityData[Shaders.entityDataIndex * 2] = entityData[entityDataIndex * 2] & 0xFFFF | data1 << 16;
        return data1;
    }

    public static int setEntityData2(int data2) {
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & 0xFFFF0000 | data2 & 0xFFFF;
        return data2;
    }

    public static void pushEntity(int data0, int data1) {
        Shaders.entityData[++Shaders.entityDataIndex * 2] = data0 & 0xFFFF | data1 << 16;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void pushEntity(int data0) {
        Shaders.entityData[++Shaders.entityDataIndex * 2] = data0 & 0xFFFF;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void pushEntity(aow block) {
        int blockRenderType = block.a(block.t()).ordinal();
        Shaders.entityData[++Shaders.entityDataIndex * 2] = aow.h.a((Object)block) & 0xFFFF | blockRenderType << 16;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void popEntity() {
        Shaders.entityData[Shaders.entityDataIndex * 2] = 0;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
        --entityDataIndex;
    }

    public static void mcProfilerEndSection() {
        Shaders.mc.B.b();
    }

    public static String getShaderPackName() {
        if (shaderPack == null) {
            return null;
        }
        if (shaderPack instanceof ShaderPackNone) {
            return null;
        }
        return shaderPack.getName();
    }

    public static InputStream getShaderPackResourceStream(String path) {
        if (shaderPack == null) {
            return null;
        }
        return shaderPack.getResourceAsStream(path);
    }

    public static void nextAntialiasingLevel() {
        configAntialiasingLevel += 2;
        if ((configAntialiasingLevel = configAntialiasingLevel / 2 * 2) > 4) {
            configAntialiasingLevel = 0;
        }
        configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
    }

    public static void checkShadersModInstalled() {
        try {
            Class<?> clazz = Class.forName("shadersmod.transform.SMCClassTransformer");
        }
        catch (Throwable e) {
            return;
        }
        throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
    }

    public static void resourcesReloaded() {
        Shaders.loadShaderPackResources();
        if (shaderPackLoaded) {
            BlockAliases.resourcesReloaded();
            ItemAliases.resourcesReloaded();
            EntityAliases.resourcesReloaded();
        }
    }

    private static void loadShaderPackResources() {
        shaderPackResources = new HashMap<String, String>();
        if (!shaderPackLoaded) {
            return;
        }
        ArrayList<String> listFiles = new ArrayList<String>();
        String PREFIX = "/shaders/lang/";
        String EN_US = "en_US";
        String SUFFIX = ".lang";
        listFiles.add(PREFIX + EN_US + SUFFIX);
        if (!Config.getGameSettings().aJ.equals(EN_US)) {
            listFiles.add(PREFIX + Config.getGameSettings().aJ + SUFFIX);
        }
        try {
            for (String file : listFiles) {
                InputStream in = shaderPack.getResourceAsStream(file);
                if (in == null) continue;
                PropertiesOrdered props = new PropertiesOrdered();
                Lang.loadLocaleData(in, props);
                in.close();
                Set<Object> keys = ((Properties)props).keySet();
                for (String string : keys) {
                    String value = props.getProperty(string);
                    shaderPackResources.put(string, value);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translate(String key, String def) {
        String str = shaderPackResources.get(key);
        if (str == null) {
            return def;
        }
        return str;
    }

    public static boolean isProgramPath(String path) {
        Program p;
        if (path == null) {
            return false;
        }
        if (path.length() <= 0) {
            return false;
        }
        int pos = path.lastIndexOf("/");
        if (pos >= 0) {
            path = path.substring(pos + 1);
        }
        return (p = Shaders.getProgram(path)) != null;
    }

    public static Program getProgram(String name) {
        return programs.getProgram(name);
    }

    public static void setItemToRenderMain(aip itemToRenderMain) {
        itemToRenderMainTranslucent = Shaders.isTranslucentBlock(itemToRenderMain);
    }

    public static void setItemToRenderOff(aip itemToRenderOff) {
        itemToRenderOffTranslucent = Shaders.isTranslucentBlock(itemToRenderOff);
    }

    public static boolean isItemToRenderMainTranslucent() {
        return itemToRenderMainTranslucent;
    }

    public static boolean isItemToRenderOffTranslucent() {
        return itemToRenderOffTranslucent;
    }

    public static boolean isBothHandsRendered() {
        return isHandRenderedMain && isHandRenderedOff;
    }

    private static boolean isTranslucentBlock(aip stack) {
        if (stack == null) {
            return false;
        }
        ain item = stack.c();
        if (item == null) {
            return false;
        }
        if (!(item instanceof ahb)) {
            return false;
        }
        ahb itemBlock = (ahb)item;
        aow block = itemBlock.d();
        if (block == null) {
            return false;
        }
        amm blockRenderLayer = block.f();
        return blockRenderLayer == amm.d;
    }

    public static boolean isSkipRenderHand(ub hand) {
        if (hand == ub.a && skipRenderHandMain) {
            return true;
        }
        return hand == ub.b && skipRenderHandOff;
    }

    public static boolean isRenderBothHands() {
        return !skipRenderHandMain && !skipRenderHandOff;
    }

    public static void setSkipRenderHands(boolean skipMain, boolean skipOff) {
        skipRenderHandMain = skipMain;
        skipRenderHandOff = skipOff;
    }

    public static void setHandsRendered(boolean handMain, boolean handOff) {
        isHandRenderedMain = handMain;
        isHandRenderedOff = handOff;
    }

    public static boolean isHandRenderedMain() {
        return isHandRenderedMain;
    }

    public static boolean isHandRenderedOff() {
        return isHandRenderedOff;
    }

    public static float getShadowRenderDistance() {
        if (shadowDistanceRenderMul < 0.0f) {
            return -1.0f;
        }
        return shadowMapHalfPlane * shadowDistanceRenderMul;
    }

    public static void setRenderingFirstPersonHand(boolean flag) {
        isRenderingFirstPersonHand = flag;
    }

    public static boolean isRenderingFirstPersonHand() {
        return isRenderingFirstPersonHand;
    }

    public static void beginBeacon() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramBeaconBeam);
        }
    }

    public static void endBeacon() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramBlock);
        }
    }

    public static amu getCurrentWorld() {
        return currentWorld;
    }

    public static et getCameraPosition() {
        return new et(cameraPositionX, cameraPositionY, cameraPositionZ);
    }

    public static boolean isCustomUniforms() {
        return customUniforms != null;
    }

    public static boolean canRenderQuads() {
        if (hasGeometryShaders) {
            return Shaders.capabilities.GL_NV_geometry_shader4;
        }
        return true;
    }

    static {
        isInitializedOnce = false;
        isShaderPackInitialized = false;
        hasGlGenMipmap = false;
        countResetDisplayLists = 0;
        renderDisplayWidth = 0;
        renderDisplayHeight = 0;
        renderWidth = 0;
        renderHeight = 0;
        isRenderingWorld = false;
        isRenderingSky = false;
        isCompositeRendered = false;
        isRenderingDfb = false;
        isShadowPass = false;
        isEntitiesGlowing = false;
        renderItemKeepDepthMask = false;
        itemToRenderMainTranslucent = false;
        itemToRenderOffTranslucent = false;
        sunPosition = new float[4];
        moonPosition = new float[4];
        shadowLightPosition = new float[4];
        upPosition = new float[4];
        shadowLightPositionVector = new float[4];
        upPosModelView = new float[]{0.0f, 100.0f, 0.0f, 0.0f};
        sunPosModelView = new float[]{0.0f, 100.0f, 0.0f, 0.0f};
        moonPosModelView = new float[]{0.0f, -100.0f, 0.0f, 0.0f};
        tempMat = new float[16];
        worldTime = 0L;
        lastWorldTime = 0L;
        diffWorldTime = 0L;
        celestialAngle = 0.0f;
        sunAngle = 0.0f;
        shadowAngle = 0.0f;
        moonPhase = 0;
        systemTime = 0L;
        lastSystemTime = 0L;
        diffSystemTime = 0L;
        frameCounter = 0;
        frameTime = 0.0f;
        frameTimeCounter = 0.0f;
        systemTimeInt32 = 0;
        rainStrength = 0.0f;
        wetness = 0.0f;
        wetnessHalfLife = 600.0f;
        drynessHalfLife = 200.0f;
        eyeBrightnessHalflife = 10.0f;
        usewetness = false;
        isEyeInWater = 0;
        eyeBrightness = 0;
        eyeBrightnessFadeX = 0.0f;
        eyeBrightnessFadeY = 0.0f;
        eyePosY = 0.0f;
        centerDepth = 0.0f;
        centerDepthSmooth = 0.0f;
        centerDepthSmoothHalflife = 1.0f;
        centerDepthSmoothEnabled = false;
        superSamplingLevel = 1;
        nightVision = 0.0f;
        blindness = 0.0f;
        lightmapEnabled = false;
        fogEnabled = true;
        entityAttrib = 10;
        midTexCoordAttrib = 11;
        tangentAttrib = 12;
        useEntityAttrib = false;
        useMidTexCoordAttrib = false;
        useTangentAttrib = false;
        progUseEntityAttrib = false;
        progUseMidTexCoordAttrib = false;
        progUseTangentAttrib = false;
        progArbGeometryShader4 = false;
        progMaxVerticesOut = 3;
        hasGeometryShaders = false;
        atlasSizeX = 0;
        atlasSizeY = 0;
        shaderUniforms = new ShaderUniforms();
        uniform_entityColor = shaderUniforms.make4f("entityColor");
        uniform_entityId = shaderUniforms.make1i("entityId");
        uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
        uniform_texture = shaderUniforms.make1i("texture");
        uniform_lightmap = shaderUniforms.make1i("lightmap");
        uniform_normals = shaderUniforms.make1i("normals");
        uniform_specular = shaderUniforms.make1i("specular");
        uniform_shadow = shaderUniforms.make1i("shadow");
        uniform_watershadow = shaderUniforms.make1i("watershadow");
        uniform_shadowtex0 = shaderUniforms.make1i("shadowtex0");
        uniform_shadowtex1 = shaderUniforms.make1i("shadowtex1");
        uniform_depthtex0 = shaderUniforms.make1i("depthtex0");
        uniform_depthtex1 = shaderUniforms.make1i("depthtex1");
        uniform_shadowcolor = shaderUniforms.make1i("shadowcolor");
        uniform_shadowcolor0 = shaderUniforms.make1i("shadowcolor0");
        uniform_shadowcolor1 = shaderUniforms.make1i("shadowcolor1");
        uniform_noisetex = shaderUniforms.make1i("noisetex");
        uniform_gcolor = shaderUniforms.make1i("gcolor");
        uniform_gdepth = shaderUniforms.make1i("gdepth");
        uniform_gnormal = shaderUniforms.make1i("gnormal");
        uniform_composite = shaderUniforms.make1i("composite");
        uniform_gaux1 = shaderUniforms.make1i("gaux1");
        uniform_gaux2 = shaderUniforms.make1i("gaux2");
        uniform_gaux3 = shaderUniforms.make1i("gaux3");
        uniform_gaux4 = shaderUniforms.make1i("gaux4");
        uniform_colortex0 = shaderUniforms.make1i("colortex0");
        uniform_colortex1 = shaderUniforms.make1i("colortex1");
        uniform_colortex2 = shaderUniforms.make1i("colortex2");
        uniform_colortex3 = shaderUniforms.make1i("colortex3");
        uniform_colortex4 = shaderUniforms.make1i("colortex4");
        uniform_colortex5 = shaderUniforms.make1i("colortex5");
        uniform_colortex6 = shaderUniforms.make1i("colortex6");
        uniform_colortex7 = shaderUniforms.make1i("colortex7");
        uniform_gdepthtex = shaderUniforms.make1i("gdepthtex");
        uniform_depthtex2 = shaderUniforms.make1i("depthtex2");
        uniform_tex = shaderUniforms.make1i("tex");
        uniform_heldItemId = shaderUniforms.make1i("heldItemId");
        uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
        uniform_heldItemId2 = shaderUniforms.make1i("heldItemId2");
        uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
        uniform_fogMode = shaderUniforms.make1i("fogMode");
        uniform_fogDensity = shaderUniforms.make1f("fogDensity");
        uniform_fogColor = shaderUniforms.make3f("fogColor");
        uniform_skyColor = shaderUniforms.make3f("skyColor");
        uniform_worldTime = shaderUniforms.make1i("worldTime");
        uniform_worldDay = shaderUniforms.make1i("worldDay");
        uniform_moonPhase = shaderUniforms.make1i("moonPhase");
        uniform_frameCounter = shaderUniforms.make1i("frameCounter");
        uniform_frameTime = shaderUniforms.make1f("frameTime");
        uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
        uniform_sunAngle = shaderUniforms.make1f("sunAngle");
        uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
        uniform_rainStrength = shaderUniforms.make1f("rainStrength");
        uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
        uniform_viewWidth = shaderUniforms.make1f("viewWidth");
        uniform_viewHeight = shaderUniforms.make1f("viewHeight");
        uniform_near = shaderUniforms.make1f("near");
        uniform_far = shaderUniforms.make1f("far");
        uniform_sunPosition = shaderUniforms.make3f("sunPosition");
        uniform_moonPosition = shaderUniforms.make3f("moonPosition");
        uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
        uniform_upPosition = shaderUniforms.make3f("upPosition");
        uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
        uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
        uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
        uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
        uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
        uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
        uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
        uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
        uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
        uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
        uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
        uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
        uniform_wetness = shaderUniforms.make1f("wetness");
        uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
        uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
        uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
        uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
        uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
        uniform_isEyeInWater = shaderUniforms.make1i("isEyeInWater");
        uniform_nightVision = shaderUniforms.make1f("nightVision");
        uniform_blindness = shaderUniforms.make1f("blindness");
        uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
        uniform_hideGUI = shaderUniforms.make1i("hideGUI");
        uniform_centerDepthSmooth = shaderUniforms.make1f("centerDepthSmooth");
        uniform_atlasSize = shaderUniforms.make2i("atlasSize");
        uniform_blendFunc = shaderUniforms.make4i("blendFunc");
        uniform_instanceId = shaderUniforms.make1i("instanceId");
        shadowPassInterval = 0;
        needResizeShadow = false;
        shadowMapWidth = 1024;
        shadowMapHeight = 1024;
        spShadowMapWidth = 1024;
        spShadowMapHeight = 1024;
        shadowMapFOV = 90.0f;
        shadowMapHalfPlane = 160.0f;
        shadowMapIsOrtho = true;
        shadowDistanceRenderMul = -1.0f;
        shadowPassCounter = 0;
        shouldSkipDefaultShadow = false;
        waterShadowEnabled = false;
        usedColorBuffers = 0;
        usedDepthBuffers = 0;
        usedShadowColorBuffers = 0;
        usedShadowDepthBuffers = 0;
        usedColorAttachs = 0;
        usedDrawBuffers = 0;
        dfb = 0;
        sfb = 0;
        gbuffersFormat = new int[8];
        gbuffersClear = new boolean[8];
        gbuffersClearColor = new Vector4f[8];
        programs = new Programs();
        ProgramNone = programs.getProgramNone();
        ProgramShadow = programs.makeShadow("shadow", ProgramNone);
        ProgramShadowSolid = programs.makeShadow("shadow_solid", ProgramShadow);
        ProgramShadowCutout = programs.makeShadow("shadow_cutout", ProgramShadow);
        ProgramBasic = programs.makeGbuffers("gbuffers_basic", ProgramNone);
        ProgramTextured = programs.makeGbuffers("gbuffers_textured", ProgramBasic);
        ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
        ProgramSkyBasic = programs.makeGbuffers("gbuffers_skybasic", ProgramBasic);
        ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
        ProgramClouds = programs.makeGbuffers("gbuffers_clouds", ProgramTextured);
        ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", ProgramTexturedLit);
        ProgramTerrainSolid = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
        ProgramTerrainCutoutMip = programs.makeGbuffers("gbuffers_terrain_cutout_mip", ProgramTerrain);
        ProgramTerrainCutout = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
        ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
        ProgramBlock = programs.makeGbuffers("gbuffers_block", ProgramTerrain);
        ProgramBeaconBeam = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
        ProgramItem = programs.makeGbuffers("gbuffers_item", ProgramTexturedLit);
        ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
        ProgramEntitiesGlowing = programs.makeGbuffers("gbuffers_entities_glowing", ProgramEntities);
        ProgramArmorGlint = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
        ProgramSpiderEyes = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
        ProgramHand = programs.makeGbuffers("gbuffers_hand", ProgramTexturedLit);
        ProgramWeather = programs.makeGbuffers("gbuffers_weather", ProgramTexturedLit);
        ProgramDeferredPre = programs.makeVirtual("deferred_pre");
        ProgramsDeferred = programs.makeDeferreds("deferred", 16);
        ProgramDeferred = ProgramsDeferred[0];
        ProgramWater = programs.makeGbuffers("gbuffers_water", ProgramTerrain);
        ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
        ProgramCompositePre = programs.makeVirtual("composite_pre");
        ProgramsComposite = programs.makeComposites("composite", 16);
        ProgramComposite = ProgramsComposite[0];
        ProgramFinal = programs.makeComposite("final");
        ProgramCount = programs.getCount();
        ProgramsAll = programs.getPrograms();
        activeProgram = ProgramNone;
        activeProgramID = 0;
        programStackLeash = new ProgramStack();
        hasDeferredPrograms = false;
        activeDrawBuffers = null;
        activeCompositeMipmapSetting = 0;
        loadedShaders = null;
        shadersConfig = null;
        defaultTexture = null;
        shadowHardwareFilteringEnabled = new boolean[2];
        shadowMipmapEnabled = new boolean[2];
        shadowFilterNearest = new boolean[2];
        shadowColorMipmapEnabled = new boolean[8];
        shadowColorFilterNearest = new boolean[8];
        configTweakBlockDamage = false;
        configCloudShadow = false;
        configHandDepthMul = 0.125f;
        configRenderResMul = 1.0f;
        configShadowResMul = 1.0f;
        configTexMinFilB = 0;
        configTexMinFilN = 0;
        configTexMinFilS = 0;
        configTexMagFilB = 0;
        configTexMagFilN = 0;
        configTexMagFilS = 0;
        configShadowClipFrustrum = true;
        configNormalMap = true;
        configSpecularMap = true;
        configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
        configOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
        configAntialiasingLevel = 0;
        texMinFilDesc = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
        texMagFilDesc = new String[]{"Nearest", "Linear"};
        texMinFilValue = new int[]{9728, 9984, 9986};
        texMagFilValue = new int[]{9728, 9729};
        shaderPack = null;
        shaderPackLoaded = false;
        shaderPacksDir = new File(bib.z().w, SHADER_PACKS_DIR_NAME);
        configFile = new File(bib.z().w, OPTIONS_FILE_NAME);
        shaderPackOptions = null;
        shaderPackOptionSliders = null;
        shaderPackProfiles = null;
        shaderPackGuiScreens = null;
        shaderPackProgramConditions = new HashMap<String, IExpressionBool>();
        shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
        shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
        shaderPackOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
        shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
        shaderPackShadowTranslucent = new PropertyDefaultTrueFalse("shadowTranslucent", "Shadow Translucent", 0);
        shaderPackUnderwaterOverlay = new PropertyDefaultTrueFalse("underwaterOverlay", "Underwater Overlay", 0);
        shaderPackSun = new PropertyDefaultTrueFalse("sun", "Sun", 0);
        shaderPackMoon = new PropertyDefaultTrueFalse("moon", "Moon", 0);
        shaderPackVignette = new PropertyDefaultTrueFalse("vignette", "Vignette", 0);
        shaderPackBackFaceSolid = new PropertyDefaultTrueFalse("backFace.solid", "Back-face Solid", 0);
        shaderPackBackFaceCutout = new PropertyDefaultTrueFalse("backFace.cutout", "Back-face Cutout", 0);
        shaderPackBackFaceCutoutMipped = new PropertyDefaultTrueFalse("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
        shaderPackBackFaceTranslucent = new PropertyDefaultTrueFalse("backFace.translucent", "Back-face Translucent", 0);
        shaderPackRainDepth = new PropertyDefaultTrueFalse("rain.depth", "Rain Depth", 0);
        shaderPackBeaconBeamDepth = new PropertyDefaultTrueFalse("beacon.beam.depth", "Rain Depth", 0);
        shaderPackSeparateAo = new PropertyDefaultTrueFalse("separateAo", "Separate AO", 0);
        shaderPackFrustumCulling = new PropertyDefaultTrueFalse("frustum.culling", "Frustum Culling", 0);
        shaderPackResources = new HashMap<String, String>();
        currentWorld = null;
        shaderPackDimensions = new ArrayList<Integer>();
        customTexturesGbuffers = null;
        customTexturesComposite = null;
        customTexturesDeferred = null;
        noiseTexturePath = null;
        customUniforms = null;
        STAGE_NAMES = new String[]{"gbuffers", "composite", "deferred"};
        saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
        blockLightLevel05 = 0.5f;
        blockLightLevel06 = 0.6f;
        blockLightLevel08 = 0.8f;
        aoLevel = -1.0f;
        sunPathRotation = 0.0f;
        shadowAngleInterval = 0.0f;
        fogMode = 0;
        fogDensity = 0.0f;
        shadowIntervalSize = 2.0f;
        terrainIconSize = 16;
        terrainTextureSize = new int[2];
        noiseTextureEnabled = false;
        noiseTextureResolution = 256;
        colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
        bigBufferSize = (285 + 8 * ProgramCount) * 4;
        bigBuffer = (ByteBuffer)BufferUtils.createByteBuffer((int)bigBufferSize).limit(0);
        faProjection = new float[16];
        faProjectionInverse = new float[16];
        faModelView = new float[16];
        faModelViewInverse = new float[16];
        faShadowProjection = new float[16];
        faShadowProjectionInverse = new float[16];
        faShadowModelView = new float[16];
        faShadowModelViewInverse = new float[16];
        projection = Shaders.nextFloatBuffer(16);
        projectionInverse = Shaders.nextFloatBuffer(16);
        modelView = Shaders.nextFloatBuffer(16);
        modelViewInverse = Shaders.nextFloatBuffer(16);
        shadowProjection = Shaders.nextFloatBuffer(16);
        shadowProjectionInverse = Shaders.nextFloatBuffer(16);
        shadowModelView = Shaders.nextFloatBuffer(16);
        shadowModelViewInverse = Shaders.nextFloatBuffer(16);
        previousProjection = Shaders.nextFloatBuffer(16);
        previousModelView = Shaders.nextFloatBuffer(16);
        tempMatrixDirectBuffer = Shaders.nextFloatBuffer(16);
        tempDirectFloatBuffer = Shaders.nextFloatBuffer(16);
        dfbColorTextures = Shaders.nextIntBuffer(16);
        dfbDepthTextures = Shaders.nextIntBuffer(3);
        sfbColorTextures = Shaders.nextIntBuffer(8);
        sfbDepthTextures = Shaders.nextIntBuffer(2);
        dfbDrawBuffers = Shaders.nextIntBuffer(8);
        sfbDrawBuffers = Shaders.nextIntBuffer(8);
        drawBuffersNone = (IntBuffer)Shaders.nextIntBuffer(8).limit(0);
        drawBuffersColorAtt0 = (IntBuffer)Shaders.nextIntBuffer(8).put(36064).position(0).limit(1);
        dfbColorTexturesFlip = new FlipTextures(dfbColorTextures, 8);
        formatNames = new String[]{"R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R16F", "RG16F", "RGB16F", "RGBA16F", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI", "R3_G3_B2", "RGB5_A1", "RGB10_A2", "R11F_G11F_B10F", "RGB9_E5"};
        formatIds = new int[]{33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33325, 33327, 34843, 34842, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208, 10768, 32855, 32857, 35898, 35901};
        patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
        entityData = new int[32];
        entityDataIndex = 0;
    }
}

