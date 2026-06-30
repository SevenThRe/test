/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acb
 *  ady
 *  agv
 *  ain
 *  amm
 *  amn
 *  amu
 *  amy
 *  and
 *  anh
 *  anh$c
 *  aow
 *  avh
 *  avj
 *  avk
 *  avr
 *  avu
 *  awa
 *  aws
 *  awt
 *  axj
 *  aym
 *  bhv
 *  bhy
 *  bib
 *  bir
 *  blk
 *  blr
 *  bmi
 *  bmk
 *  bmm
 *  bmt
 *  bmu
 *  bmv
 *  bna
 *  bpf
 *  bpg
 *  bph
 *  bpi
 *  bpk
 *  bpl
 *  bpr
 *  bps
 *  bpt
 *  bpu
 *  bpv
 *  bpw
 *  bqa
 *  bqb
 *  bqf
 *  bqg
 *  bqh
 *  bqn
 *  bqr
 *  bqs
 *  bqt
 *  bqu
 *  bqv
 *  bqy
 *  brb
 *  bre
 *  bri
 *  brj
 *  brk
 *  brm
 *  brn
 *  bro
 *  brs
 *  brx
 *  buq
 *  bvv
 *  bvz
 *  bwu
 *  bww
 *  bwz
 *  bxa
 *  bxb
 *  bxe
 *  bxf
 *  bxg
 *  byt
 *  bzb
 *  bzf
 *  bzh
 *  bzv
 *  bzy
 *  caa
 *  cad
 *  cap
 *  cbl
 *  ceb$b
 *  ceg
 *  cer
 *  ces
 *  cfy
 *  cfz
 *  cgc
 *  et
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  nf
 *  oq
 *  tv
 *  ui
 *  uk
 *  uz
 *  va
 *  vg
 *  vp
 *  vq
 *  vr
 */
package net.optifine.reflect;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import javax.vecmath.Matrix4f;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.optifine.reflect.FieldLocatorActionKeyF3;
import net.optifine.reflect.FieldLocatorTypes;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorConstructor;
import net.optifine.reflect.ReflectorField;
import net.optifine.reflect.ReflectorFields;
import net.optifine.reflect.ReflectorMethod;

public class Reflector {
    private static boolean logForge = Reflector.logEntry("*** Reflector Forge ***");
    public static ReflectorClass BetterFoliageClient = new ReflectorClass("mods.betterfoliage.client.BetterFoliageClient");
    public static ReflectorClass ChunkWatchEvent_UnWatch = new ReflectorClass("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
    public static ReflectorConstructor ChunkWatchEvent_UnWatch_Constructor = new ReflectorConstructor(ChunkWatchEvent_UnWatch, new Class[]{amn.class, oq.class});
    public static ReflectorClass CoreModManager = new ReflectorClass("net.minecraftforge.fml.relauncher.CoreModManager");
    public static ReflectorMethod CoreModManager_onCrash = new ReflectorMethod(CoreModManager, "onCrash");
    public static ReflectorClass DimensionManager = new ReflectorClass("net.minecraftforge.common.DimensionManager");
    public static ReflectorMethod DimensionManager_createProviderFor = new ReflectorMethod(DimensionManager, "createProviderFor");
    public static ReflectorMethod DimensionManager_getStaticDimensionIDs = new ReflectorMethod(DimensionManager, "getStaticDimensionIDs");
    public static ReflectorClass DrawScreenEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
    public static ReflectorConstructor DrawScreenEvent_Pre_Constructor = new ReflectorConstructor(DrawScreenEvent_Pre, new Class[]{blk.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
    public static ReflectorClass DrawScreenEvent_Post = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
    public static ReflectorConstructor DrawScreenEvent_Post_Constructor = new ReflectorConstructor(DrawScreenEvent_Post, new Class[]{blk.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
    public static ReflectorClass EntityViewRenderEvent_CameraSetup = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup");
    public static ReflectorConstructor EntityViewRenderEvent_CameraSetup_Constructor = new ReflectorConstructor(EntityViewRenderEvent_CameraSetup, new Class[]{buq.class, vg.class, awt.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
    public static ReflectorMethod EntityViewRenderEvent_CameraSetup_getRoll = new ReflectorMethod(EntityViewRenderEvent_CameraSetup, "getRoll");
    public static ReflectorMethod EntityViewRenderEvent_CameraSetup_getPitch = new ReflectorMethod(EntityViewRenderEvent_CameraSetup, "getPitch");
    public static ReflectorMethod EntityViewRenderEvent_CameraSetup_getYaw = new ReflectorMethod(EntityViewRenderEvent_CameraSetup, "getYaw");
    public static ReflectorClass EntityViewRenderEvent_FogColors = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
    public static ReflectorConstructor EntityViewRenderEvent_FogColors_Constructor = new ReflectorConstructor(EntityViewRenderEvent_FogColors, new Class[]{buq.class, vg.class, awt.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
    public static ReflectorMethod EntityViewRenderEvent_FogColors_getRed = new ReflectorMethod(EntityViewRenderEvent_FogColors, "getRed");
    public static ReflectorMethod EntityViewRenderEvent_FogColors_getGreen = new ReflectorMethod(EntityViewRenderEvent_FogColors, "getGreen");
    public static ReflectorMethod EntityViewRenderEvent_FogColors_getBlue = new ReflectorMethod(EntityViewRenderEvent_FogColors, "getBlue");
    public static ReflectorClass EntityViewRenderEvent_RenderFogEvent = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent");
    public static ReflectorConstructor EntityViewRenderEvent_RenderFogEvent_Constructor = new ReflectorConstructor(EntityViewRenderEvent_RenderFogEvent, new Class[]{buq.class, vg.class, awt.class, Double.TYPE, Integer.TYPE, Float.TYPE});
    public static ReflectorClass Event = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event");
    public static ReflectorMethod Event_isCanceled = new ReflectorMethod(Event, "isCanceled");
    public static ReflectorClass EventBus = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.EventBus");
    public static ReflectorMethod EventBus_post = new ReflectorMethod(EventBus, "post");
    public static ReflectorClass Event_Result = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event$Result");
    public static ReflectorField Event_Result_DENY = new ReflectorField(Event_Result, "DENY");
    public static ReflectorField Event_Result_ALLOW = new ReflectorField(Event_Result, "ALLOW");
    public static ReflectorField Event_Result_DEFAULT = new ReflectorField(Event_Result, "DEFAULT");
    public static ReflectorClass ExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.ExtendedBlockState");
    public static ReflectorConstructor ExtendedBlockState_Constructor = new ReflectorConstructor(ExtendedBlockState, new Class[]{aow.class, axj[].class, IUnlistedProperty[].class});
    public static ReflectorClass FMLClientHandler = new ReflectorClass("net.minecraftforge.fml.client.FMLClientHandler");
    public static ReflectorMethod FMLClientHandler_instance = new ReflectorMethod(FMLClientHandler, "instance");
    public static ReflectorMethod FMLClientHandler_handleLoadingScreen = new ReflectorMethod(FMLClientHandler, "handleLoadingScreen");
    public static ReflectorMethod FMLClientHandler_isLoading = new ReflectorMethod(FMLClientHandler, "isLoading");
    public static ReflectorMethod FMLClientHandler_refreshResources = new ReflectorMethod(FMLClientHandler, "refreshResources", new Class[]{IResourceType[].class});
    public static ReflectorMethod FMLClientHandler_renderClouds = new ReflectorMethod(FMLClientHandler, "renderClouds");
    public static ReflectorMethod FMLClientHandler_trackBrokenTexture = new ReflectorMethod(FMLClientHandler, "trackBrokenTexture");
    public static ReflectorMethod FMLClientHandler_trackMissingTexture = new ReflectorMethod(FMLClientHandler, "trackMissingTexture");
    public static ReflectorClass FMLCommonHandler = new ReflectorClass("net.minecraftforge.fml.common.FMLCommonHandler");
    public static ReflectorMethod FMLCommonHandler_callFuture = new ReflectorMethod(FMLCommonHandler, "callFuture");
    public static ReflectorMethod FMLCommonHandler_enhanceCrashReport = new ReflectorMethod(FMLCommonHandler, "enhanceCrashReport");
    public static ReflectorMethod FMLCommonHandler_getBrandings = new ReflectorMethod(FMLCommonHandler, "getBrandings");
    public static ReflectorMethod FMLCommonHandler_handleServerAboutToStart = new ReflectorMethod(FMLCommonHandler, "handleServerAboutToStart");
    public static ReflectorMethod FMLCommonHandler_handleServerStarting = new ReflectorMethod(FMLCommonHandler, "handleServerStarting");
    public static ReflectorMethod FMLCommonHandler_instance = new ReflectorMethod(FMLCommonHandler, "instance");
    public static ReflectorClass ActiveRenderInfo = new ReflectorClass(bhv.class);
    public static ReflectorMethod ActiveRenderInfo_getCameraPosition = new ReflectorMethod(ActiveRenderInfo, "getCameraPosition");
    public static ReflectorMethod ActiveRenderInfo_updateRenderInfo2 = new ReflectorMethod(ActiveRenderInfo, "updateRenderInfo", new Class[]{vg.class, Boolean.TYPE});
    public static ReflectorClass ForgeBiome = new ReflectorClass(anh.class);
    public static ReflectorMethod ForgeBiome_getWaterColorMultiplier = new ReflectorMethod(ForgeBiome, "getWaterColorMultiplier");
    public static ReflectorClass ForgeBiomeSpawnListEntry = new ReflectorClass(anh.c.class);
    public static ReflectorMethod ForgeBiomeSpawnListEntry_newInstance = new ReflectorMethod(ForgeBiomeSpawnListEntry, "newInstance");
    public static ReflectorClass ForgeBlock = new ReflectorClass(aow.class);
    public static ReflectorMethod ForgeBlock_addDestroyEffects = new ReflectorMethod(ForgeBlock, "addDestroyEffects");
    public static ReflectorMethod ForgeBlock_addHitEffects = new ReflectorMethod(ForgeBlock, "addHitEffects");
    public static ReflectorMethod ForgeBlock_canCreatureSpawn = new ReflectorMethod(ForgeBlock, "canCreatureSpawn");
    public static ReflectorMethod ForgeBlock_canRenderInLayer = new ReflectorMethod(ForgeBlock, "canRenderInLayer", new Class[]{awt.class, amm.class});
    public static ReflectorMethod ForgeBlock_doesSideBlockRendering = new ReflectorMethod(ForgeBlock, "doesSideBlockRendering");
    public static ReflectorMethod ForgeBlock_doesSideBlockChestOpening = new ReflectorMethod(ForgeBlock, "doesSideBlockChestOpening");
    public static ReflectorMethod ForgeBlock_getBedDirection = new ReflectorMethod(ForgeBlock, "getBedDirection");
    public static ReflectorMethod ForgeBlock_getExtendedState = new ReflectorMethod(ForgeBlock, "getExtendedState");
    public static ReflectorMethod ForgeBlock_getFogColor = new ReflectorMethod(ForgeBlock, "getFogColor");
    public static ReflectorMethod ForgeBlock_getLightOpacity = new ReflectorMethod(ForgeBlock, "getLightOpacity", new Class[]{awt.class, amy.class, et.class});
    public static ReflectorMethod ForgeBlock_getLightValue = new ReflectorMethod(ForgeBlock, "getLightValue", new Class[]{awt.class, amy.class, et.class});
    public static ReflectorMethod ForgeBlock_getSoundType = new ReflectorMethod(ForgeBlock, "getSoundType", new Class[]{awt.class, amu.class, et.class, vg.class});
    public static ReflectorMethod ForgeBlock_hasTileEntity = new ReflectorMethod(ForgeBlock, "hasTileEntity", new Class[]{awt.class});
    public static ReflectorMethod ForgeBlock_isAir = new ReflectorMethod(ForgeBlock, "isAir");
    public static ReflectorMethod ForgeBlock_isBed = new ReflectorMethod(ForgeBlock, "isBed");
    public static ReflectorMethod ForgeBlock_isBedFoot = new ReflectorMethod(ForgeBlock, "isBedFoot");
    public static ReflectorMethod ForgeBlock_isSideSolid = new ReflectorMethod(ForgeBlock, "isSideSolid");
    public static ReflectorClass ForgeIBakedModel = new ReflectorClass(cfy.class);
    public static ReflectorMethod ForgeIBakedModel_isAmbientOcclusion2 = new ReflectorMethod(ForgeIBakedModel, "isAmbientOcclusion", new Class[]{awt.class});
    public static ReflectorClass ForgeIBlockProperties = new ReflectorClass(aws.class);
    public static ReflectorMethod ForgeIBlockProperties_getLightValue2 = new ReflectorMethod(ForgeIBlockProperties, "getLightValue", new Class[]{amy.class, et.class});
    public static ReflectorClass ForgeChunkCache = new ReflectorClass(and.class);
    public static ReflectorMethod ForgeChunkCache_isSideSolid = new ReflectorMethod(ForgeChunkCache, "isSideSolid");
    public static ReflectorClass ForgeEntity = new ReflectorClass(vg.class);
    public static ReflectorMethod ForgeEntity_canRiderInteract = new ReflectorMethod(ForgeEntity, "canRiderInteract");
    public static ReflectorField ForgeEntity_captureDrops = new ReflectorField(ForgeEntity, "captureDrops");
    public static ReflectorField ForgeEntity_capturedDrops = new ReflectorField(ForgeEntity, "capturedDrops");
    public static ReflectorMethod ForgeEntity_shouldRenderInPass = new ReflectorMethod(ForgeEntity, "shouldRenderInPass");
    public static ReflectorMethod ForgeEntity_shouldRiderSit = new ReflectorMethod(ForgeEntity, "shouldRiderSit");
    public static ReflectorClass ForgeEventFactory = new ReflectorClass("net.minecraftforge.event.ForgeEventFactory");
    public static ReflectorMethod ForgeEventFactory_canEntitySpawn = new ReflectorMethod(ForgeEventFactory, "canEntitySpawn", new Class[]{vq.class, amu.class, Float.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE});
    public static ReflectorMethod ForgeEventFactory_canEntityDespawn = new ReflectorMethod(ForgeEventFactory, "canEntityDespawn");
    public static ReflectorMethod ForgeEventFactory_doSpecialSpawn = new ReflectorMethod(ForgeEventFactory, "doSpecialSpawn", new Class[]{vq.class, amu.class, Float.TYPE, Float.TYPE, Float.TYPE});
    public static ReflectorMethod ForgeEventFactory_getMaxSpawnPackSize = new ReflectorMethod(ForgeEventFactory, "getMaxSpawnPackSize");
    public static ReflectorMethod ForgeEventFactory_getMobGriefingEvent = new ReflectorMethod(ForgeEventFactory, "getMobGriefingEvent");
    public static ReflectorMethod ForgeEventFactory_renderBlockOverlay = new ReflectorMethod(ForgeEventFactory, "renderBlockOverlay");
    public static ReflectorMethod ForgeEventFactory_renderFireOverlay = new ReflectorMethod(ForgeEventFactory, "renderFireOverlay");
    public static ReflectorMethod ForgeEventFactory_renderWaterOverlay = new ReflectorMethod(ForgeEventFactory, "renderWaterOverlay");
    public static ReflectorClass ForgeHooks = new ReflectorClass("net.minecraftforge.common.ForgeHooks");
    public static ReflectorMethod ForgeHooks_onLivingAttack = new ReflectorMethod(ForgeHooks, "onLivingAttack");
    public static ReflectorMethod ForgeHooks_onLivingDeath = new ReflectorMethod(ForgeHooks, "onLivingDeath");
    public static ReflectorMethod ForgeHooks_onLivingDrops = new ReflectorMethod(ForgeHooks, "onLivingDrops");
    public static ReflectorMethod ForgeHooks_onLivingFall = new ReflectorMethod(ForgeHooks, "onLivingFall");
    public static ReflectorMethod ForgeHooks_onLivingHurt = new ReflectorMethod(ForgeHooks, "onLivingHurt");
    public static ReflectorMethod ForgeHooks_onLivingJump = new ReflectorMethod(ForgeHooks, "onLivingJump");
    public static ReflectorMethod ForgeHooks_onLivingSetAttackTarget = new ReflectorMethod(ForgeHooks, "onLivingSetAttackTarget");
    public static ReflectorMethod ForgeHooks_onLivingUpdate = new ReflectorMethod(ForgeHooks, "onLivingUpdate");
    public static ReflectorClass ForgeHooksClient = new ReflectorClass("net.minecraftforge.client.ForgeHooksClient");
    public static ReflectorMethod ForgeHooksClient_applyTransform_M4 = new ReflectorMethod(ForgeHooksClient, "applyTransform", new Class[]{Matrix4f.class, Optional.class});
    public static ReflectorMethod ForgeHooksClient_applyTransform_MR = new ReflectorMethod(ForgeHooksClient, "applyTransform", new Class[]{cfz.class, Optional.class});
    public static ReflectorMethod ForgeHooksClient_applyUVLock = new ReflectorMethod(ForgeHooksClient, "applyUVLock");
    public static ReflectorMethod ForgeHooksClient_dispatchRenderLast = new ReflectorMethod(ForgeHooksClient, "dispatchRenderLast");
    public static ReflectorMethod ForgeHooksClient_drawScreen = new ReflectorMethod(ForgeHooksClient, "drawScreen");
    public static ReflectorMethod ForgeHooksClient_fillNormal = new ReflectorMethod(ForgeHooksClient, "fillNormal");
    public static ReflectorMethod ForgeHooksClient_handleCameraTransforms = new ReflectorMethod(ForgeHooksClient, "handleCameraTransforms");
    public static ReflectorMethod ForgeHooksClient_getArmorModel = new ReflectorMethod(ForgeHooksClient, "getArmorModel");
    public static ReflectorMethod ForgeHooksClient_getArmorTexture = new ReflectorMethod(ForgeHooksClient, "getArmorTexture");
    public static ReflectorMethod ForgeHooksClient_getFogDensity = new ReflectorMethod(ForgeHooksClient, "getFogDensity");
    public static ReflectorMethod ForgeHooksClient_getFOVModifier = new ReflectorMethod(ForgeHooksClient, "getFOVModifier");
    public static ReflectorMethod ForgeHooksClient_getMatrix = new ReflectorMethod(ForgeHooksClient, "getMatrix", new Class[]{cfz.class});
    public static ReflectorMethod ForgeHooksClient_getOffsetFOV = new ReflectorMethod(ForgeHooksClient, "getOffsetFOV");
    public static ReflectorMethod ForgeHooksClient_loadEntityShader = new ReflectorMethod(ForgeHooksClient, "loadEntityShader");
    public static ReflectorMethod ForgeHooksClient_onDrawBlockHighlight = new ReflectorMethod(ForgeHooksClient, "onDrawBlockHighlight");
    public static ReflectorMethod ForgeHooksClient_onFogRender = new ReflectorMethod(ForgeHooksClient, "onFogRender");
    public static ReflectorMethod ForgeHooksClient_onScreenshot = new ReflectorMethod(ForgeHooksClient, "onScreenshot");
    public static ReflectorMethod ForgeHooksClient_onTextureStitchedPre = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPre");
    public static ReflectorMethod ForgeHooksClient_onTextureStitchedPost = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPost");
    public static ReflectorMethod ForgeHooksClient_orientBedCamera = new ReflectorMethod(ForgeHooksClient, "orientBedCamera");
    public static ReflectorMethod ForgeHooksClient_putQuadColor = new ReflectorMethod(ForgeHooksClient, "putQuadColor");
    public static ReflectorMethod ForgeHooksClient_renderFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderFirstPersonHand");
    public static ReflectorMethod ForgeHooksClient_renderLitItem = new ReflectorMethod(ForgeHooksClient, "renderLitItem");
    public static ReflectorMethod ForgeHooksClient_renderMainMenu = new ReflectorMethod(ForgeHooksClient, "renderMainMenu");
    public static ReflectorMethod ForgeHooksClient_renderSpecificFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderSpecificFirstPersonHand");
    public static ReflectorMethod ForgeHooksClient_setRenderLayer = new ReflectorMethod(ForgeHooksClient, "setRenderLayer");
    public static ReflectorMethod ForgeHooksClient_setRenderPass = new ReflectorMethod(ForgeHooksClient, "setRenderPass");
    public static ReflectorMethod ForgeHooksClient_shouldCauseReequipAnimation = new ReflectorMethod(ForgeHooksClient, "shouldCauseReequipAnimation");
    public static ReflectorMethod ForgeHooksClient_transform = new ReflectorMethod(ForgeHooksClient, "transform");
    public static ReflectorClass ForgeItem = new ReflectorClass(ain.class);
    public static ReflectorField ForgeItem_delegate = new ReflectorField(ForgeItem, "delegate");
    public static ReflectorMethod ForgeItem_getDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getDurabilityForDisplay");
    public static ReflectorMethod ForgeItem_getEquipmentSlot = new ReflectorMethod(ForgeItem, "getEquipmentSlot");
    public static ReflectorMethod ForgeItem_getTileEntityItemStackRenderer = new ReflectorMethod(ForgeItem, "getTileEntityItemStackRenderer");
    public static ReflectorMethod ForgeItem_getRGBDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getRGBDurabilityForDisplay");
    public static ReflectorMethod ForgeItem_isShield = new ReflectorMethod(ForgeItem, "isShield");
    public static ReflectorMethod ForgeItem_onEntitySwing = new ReflectorMethod(ForgeItem, "onEntitySwing");
    public static ReflectorMethod ForgeItem_shouldCauseReequipAnimation = new ReflectorMethod(ForgeItem, "shouldCauseReequipAnimation");
    public static ReflectorMethod ForgeItem_showDurabilityBar = new ReflectorMethod(ForgeItem, "showDurabilityBar");
    public static ReflectorClass ForgeItemArmor = new ReflectorClass(agv.class);
    public static ReflectorMethod ForgeItemArmor_hasOverlay = new ReflectorMethod(ForgeItemArmor, "hasOverlay");
    public static ReflectorClass ForgeKeyBinding = new ReflectorClass(bhy.class);
    public static ReflectorMethod ForgeKeyBinding_setKeyConflictContext = new ReflectorMethod(ForgeKeyBinding, "setKeyConflictContext");
    public static ReflectorMethod ForgeKeyBinding_setKeyModifierAndCode = new ReflectorMethod(ForgeKeyBinding, "setKeyModifierAndCode");
    public static ReflectorMethod ForgeKeyBinding_getKeyModifier = new ReflectorMethod(ForgeKeyBinding, "getKeyModifier");
    public static ReflectorClass ForgeModContainer = new ReflectorClass("net.minecraftforge.common.ForgeModContainer");
    public static ReflectorField ForgeModContainer_forgeLightPipelineEnabled = new ReflectorField(ForgeModContainer, "forgeLightPipelineEnabled");
    public static ReflectorField ForgeModContainer_allowEmissiveItems = new ReflectorField(ForgeModContainer, "allowEmissiveItems");
    public static ReflectorClass ForgeModelBlockDefinition = new ReflectorClass(bvv.class);
    public static ReflectorMethod ForgeModelBlockDefinition_parseFromReader2 = new ReflectorMethod(ForgeModelBlockDefinition, "parseFromReader", new Class[]{Reader.class, nf.class});
    public static ReflectorClass ForgePotion = new ReflectorClass(uz.class);
    public static ReflectorMethod ForgePotion_shouldRenderHUD = ForgePotion.makeMethod("shouldRenderHUD");
    public static ReflectorMethod ForgePotion_renderHUDEffect = ForgePotion.makeMethod("renderHUDEffect", new Class[]{va.class, bir.class, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE});
    public static ReflectorClass ForgePotionEffect = new ReflectorClass(va.class);
    public static ReflectorMethod ForgePotionEffect_isCurativeItem = new ReflectorMethod(ForgePotionEffect, "isCurativeItem");
    public static ReflectorClass ForgeTileEntity = new ReflectorClass(avj.class);
    public static ReflectorMethod ForgeTileEntity_canRenderBreaking = new ReflectorMethod(ForgeTileEntity, "canRenderBreaking");
    public static ReflectorMethod ForgeTileEntity_getRenderBoundingBox = new ReflectorMethod(ForgeTileEntity, "getRenderBoundingBox");
    public static ReflectorMethod ForgeTileEntity_hasFastRenderer = new ReflectorMethod(ForgeTileEntity, "hasFastRenderer");
    public static ReflectorMethod ForgeTileEntity_shouldRenderInPass = new ReflectorMethod(ForgeTileEntity, "shouldRenderInPass");
    public static ReflectorClass ForgeVertexFormatElementEnumUseage = new ReflectorClass(ceb.b.class);
    public static ReflectorMethod ForgeVertexFormatElementEnumUseage_preDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "preDraw");
    public static ReflectorMethod ForgeVertexFormatElementEnumUseage_postDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "postDraw");
    public static ReflectorClass ForgeWorld = new ReflectorClass(amu.class);
    public static ReflectorMethod ForgeWorld_countEntities = new ReflectorMethod(ForgeWorld, "countEntities", new Class[]{vr.class, Boolean.TYPE});
    public static ReflectorMethod ForgeWorld_getPerWorldStorage = new ReflectorMethod(ForgeWorld, "getPerWorldStorage");
    public static ReflectorMethod ForgeWorld_initCapabilities = new ReflectorMethod(ForgeWorld, "initCapabilities");
    public static ReflectorClass ForgeWorldProvider = new ReflectorClass(aym.class);
    public static ReflectorMethod ForgeWorldProvider_getCloudRenderer = new ReflectorMethod(ForgeWorldProvider, "getCloudRenderer");
    public static ReflectorMethod ForgeWorldProvider_getSkyRenderer = new ReflectorMethod(ForgeWorldProvider, "getSkyRenderer");
    public static ReflectorMethod ForgeWorldProvider_getWeatherRenderer = new ReflectorMethod(ForgeWorldProvider, "getWeatherRenderer");
    public static ReflectorMethod ForgeWorldProvider_getLightmapColors = new ReflectorMethod(ForgeWorldProvider, "getLightmapColors");
    public static ReflectorMethod ForgeWorldProvider_getSaveFolder = new ReflectorMethod(ForgeWorldProvider, "getSaveFolder");
    public static ReflectorClass GuiModList = new ReflectorClass("net.minecraftforge.fml.client.GuiModList");
    public static ReflectorConstructor GuiModList_Constructor = new ReflectorConstructor(GuiModList, new Class[]{blk.class});
    public static ReflectorClass IExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.IExtendedBlockState");
    public static ReflectorMethod IExtendedBlockState_getClean = new ReflectorMethod(IExtendedBlockState, "getClean");
    public static ReflectorClass IForgeRegistryEntry_Impl = new ReflectorClass("net.minecraftforge.registries.IForgeRegistryEntry$Impl");
    public static ReflectorMethod IForgeRegistryEntry_Impl_getRegistryName = new ReflectorMethod(IForgeRegistryEntry_Impl, "getRegistryName");
    public static ReflectorClass IModel = new ReflectorClass("net.minecraftforge.client.model.IModel");
    public static ReflectorMethod IModel_getTextures = new ReflectorMethod(IModel, "getTextures");
    public static ReflectorClass IRenderHandler = new ReflectorClass("net.minecraftforge.client.IRenderHandler");
    public static ReflectorMethod IRenderHandler_render = new ReflectorMethod(IRenderHandler, "render");
    public static ReflectorClass ItemModelMesherForge = new ReflectorClass("net.minecraftforge.client.ItemModelMesherForge");
    public static ReflectorConstructor ItemModelMesherForge_Constructor = new ReflectorConstructor(ItemModelMesherForge, new Class[]{cgc.class});
    public static ReflectorClass KeyConflictContext = new ReflectorClass("net.minecraftforge.client.settings.KeyConflictContext");
    public static ReflectorField KeyConflictContext_IN_GAME = new ReflectorField(KeyConflictContext, "IN_GAME");
    public static ReflectorClass KeyModifier = new ReflectorClass("net.minecraftforge.client.settings.KeyModifier");
    public static ReflectorMethod KeyModifier_valueFromString = new ReflectorMethod(KeyModifier, "valueFromString");
    public static ReflectorField KeyModifier_NONE = new ReflectorField(KeyModifier, "NONE");
    public static ReflectorClass Launch = new ReflectorClass("net.minecraft.launchwrapper.Launch");
    public static ReflectorField Launch_blackboard = new ReflectorField(Launch, "blackboard");
    public static ReflectorClass LightUtil = new ReflectorClass("net.minecraftforge.client.model.pipeline.LightUtil");
    public static ReflectorField LightUtil_itemConsumer = new ReflectorField(LightUtil, "itemConsumer");
    public static ReflectorMethod LightUtil_putBakedQuad = new ReflectorMethod(LightUtil, "putBakedQuad");
    public static ReflectorMethod LightUtil_renderQuadColor = new ReflectorMethod(LightUtil, "renderQuadColor");
    public static ReflectorField LightUtil_tessellator = new ReflectorField(LightUtil, "tessellator");
    public static ReflectorClass Loader = new ReflectorClass("net.minecraftforge.fml.common.Loader");
    public static ReflectorMethod Loader_getActiveModList = new ReflectorMethod(Loader, "getActiveModList");
    public static ReflectorMethod Loader_instance = new ReflectorMethod(Loader, "instance");
    public static ReflectorClass MinecraftForge = new ReflectorClass("net.minecraftforge.common.MinecraftForge");
    public static ReflectorField MinecraftForge_EVENT_BUS = new ReflectorField(MinecraftForge, "EVENT_BUS");
    public static ReflectorClass MinecraftForgeClient = new ReflectorClass("net.minecraftforge.client.MinecraftForgeClient");
    public static ReflectorMethod MinecraftForgeClient_getImageLayer = new ReflectorMethod(MinecraftForgeClient, "getImageLayer");
    public static ReflectorMethod MinecraftForgeClient_getRenderPass = new ReflectorMethod(MinecraftForgeClient, "getRenderPass");
    public static ReflectorMethod MinecraftForgeClient_onRebuildChunk = new ReflectorMethod(MinecraftForgeClient, "onRebuildChunk");
    public static ReflectorClass ModContainer = new ReflectorClass("net.minecraftforge.fml.common.ModContainer");
    public static ReflectorMethod ModContainer_getModId = new ReflectorMethod(ModContainer, "getModId");
    public static ReflectorClass ModelLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader");
    public static ReflectorField ModelLoader_stateModels = new ReflectorField(ModelLoader, "stateModels");
    public static ReflectorMethod ModelLoader_onRegisterItems = new ReflectorMethod(ModelLoader, "onRegisterItems");
    public static ReflectorMethod ModelLoader_getInventoryVariant = new ReflectorMethod(ModelLoader, "getInventoryVariant");
    public static ReflectorClass ModelLoader_VanillaLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader$VanillaLoader", true);
    public static ReflectorField ModelLoader_VanillaLoader_INSTANCE = new ReflectorField(ModelLoader_VanillaLoader, "INSTANCE", true);
    public static ReflectorMethod ModelLoader_VanillaLoader_loadModel = new ReflectorMethod(ModelLoader_VanillaLoader, "loadModel", null, true);
    public static ReflectorClass ModelLoaderRegistry = new ReflectorClass("net.minecraftforge.client.model.ModelLoaderRegistry", true);
    public static ReflectorField ModelLoaderRegistry_textures = new ReflectorField(ModelLoaderRegistry, "textures", true);
    public static ReflectorClass NotificationModUpdateScreen = new ReflectorClass("net.minecraftforge.client.gui.NotificationModUpdateScreen");
    public static ReflectorMethod NotificationModUpdateScreen_init = new ReflectorMethod(NotificationModUpdateScreen, "init");
    public static ReflectorClass RenderBlockOverlayEvent_OverlayType = new ReflectorClass("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
    public static ReflectorField RenderBlockOverlayEvent_OverlayType_BLOCK = new ReflectorField(RenderBlockOverlayEvent_OverlayType, "BLOCK");
    public static ReflectorClass RenderingRegistry = new ReflectorClass("net.minecraftforge.fml.client.registry.RenderingRegistry");
    public static ReflectorMethod RenderingRegistry_loadEntityRenderers = new ReflectorMethod(RenderingRegistry, "loadEntityRenderers", new Class[]{bzf.class, Map.class});
    public static ReflectorClass RenderItemInFrameEvent = new ReflectorClass("net.minecraftforge.client.event.RenderItemInFrameEvent");
    public static ReflectorConstructor RenderItemInFrameEvent_Constructor = new ReflectorConstructor(RenderItemInFrameEvent, new Class[]{acb.class, bzv.class});
    public static ReflectorClass RenderLivingEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Pre");
    public static ReflectorConstructor RenderLivingEvent_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Pre, new Class[]{vp.class, caa.class, Float.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
    public static ReflectorClass RenderLivingEvent_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Post");
    public static ReflectorConstructor RenderLivingEvent_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Post, new Class[]{vp.class, caa.class, Float.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
    public static ReflectorClass RenderLivingEvent_Specials_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
    public static ReflectorConstructor RenderLivingEvent_Specials_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Pre, new Class[]{vp.class, caa.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static ReflectorClass RenderLivingEvent_Specials_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
    public static ReflectorConstructor RenderLivingEvent_Specials_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Post, new Class[]{vp.class, caa.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static ReflectorClass ScreenshotEvent = new ReflectorClass("net.minecraftforge.client.event.ScreenshotEvent");
    public static ReflectorMethod ScreenshotEvent_getCancelMessage = new ReflectorMethod(ScreenshotEvent, "getCancelMessage");
    public static ReflectorMethod ScreenshotEvent_getScreenshotFile = new ReflectorMethod(ScreenshotEvent, "getScreenshotFile");
    public static ReflectorMethod ScreenshotEvent_getResultMessage = new ReflectorMethod(ScreenshotEvent, "getResultMessage");
    public static ReflectorClass SplashScreen = new ReflectorClass("net.minecraftforge.fml.client.SplashProgress");
    public static ReflectorClass VanillaResourceType = new ReflectorClass("net.minecraftforge.client.resource.VanillaResourceType");
    public static ReflectorField VanillaResourceType_TEXTURES = new ReflectorField(VanillaResourceType, "TEXTURES");
    public static ReflectorClass WorldEvent_Load = new ReflectorClass("net.minecraftforge.event.world.WorldEvent$Load");
    public static ReflectorConstructor WorldEvent_Load_Constructor = new ReflectorConstructor(WorldEvent_Load, new Class[]{amu.class});
    private static boolean logVanilla = Reflector.logEntry("*** Reflector Vanilla ***");
    public static ReflectorClass ChunkProviderClient = new ReflectorClass(brx.class);
    public static ReflectorField ChunkProviderClient_chunkMapping = new ReflectorField(ChunkProviderClient, Long2ObjectMap.class);
    public static ReflectorClass EntityVillager = new ReflectorClass(ady.class);
    public static ReflectorField EntityVillager_careerId = new ReflectorField(new FieldLocatorTypes(ady.class, new Class[0], Integer.TYPE, new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, uk.class}, "EntityVillager.careerId"));
    public static ReflectorField EntityVillager_careerLevel = new ReflectorField(new FieldLocatorTypes(ady.class, new Class[]{Integer.TYPE}, Integer.TYPE, new Class[]{Boolean.TYPE, Boolean.TYPE, uk.class}, "EntityVillager.careerLevel"));
    public static ReflectorClass GuiBeacon = new ReflectorClass(bmi.class);
    public static ReflectorField GuiBeacon_tileBeacon = new ReflectorField(GuiBeacon, tv.class);
    public static ReflectorClass GuiBrewingStand = new ReflectorClass(bmk.class);
    public static ReflectorField GuiBrewingStand_tileBrewingStand = new ReflectorField(GuiBrewingStand, tv.class);
    public static ReflectorClass GuiChest = new ReflectorClass(bmm.class);
    public static ReflectorField GuiChest_lowerChestInventory = new ReflectorField(GuiChest, tv.class, 1);
    public static ReflectorClass GuiEnchantment = new ReflectorClass(bmt.class);
    public static ReflectorField GuiEnchantment_nameable = new ReflectorField(GuiEnchantment, ui.class);
    public static ReflectorClass GuiFurnace = new ReflectorClass(bmu.class);
    public static ReflectorField GuiFurnace_tileFurnace = new ReflectorField(GuiFurnace, tv.class);
    public static ReflectorClass GuiHopper = new ReflectorClass(bmv.class);
    public static ReflectorField GuiHopper_hopperInventory = new ReflectorField(GuiHopper, tv.class, 1);
    public static ReflectorClass GuiMainMenu = new ReflectorClass(blr.class);
    public static ReflectorField GuiMainMenu_splashText = new ReflectorField(GuiMainMenu, String.class);
    public static ReflectorClass GuiShulkerBox = new ReflectorClass(bna.class);
    public static ReflectorField GuiShulkerBox_inventory = new ReflectorField(GuiShulkerBox, tv.class);
    public static ReflectorClass ItemOverride = new ReflectorClass(bvz.class);
    public static ReflectorField ItemOverride_mapResourceValues = new ReflectorField(ItemOverride, Map.class);
    public static ReflectorClass LegacyV2Adapter = new ReflectorClass(ces.class);
    public static ReflectorField LegacyV2Adapter_pack = new ReflectorField(LegacyV2Adapter, cer.class);
    public static ReflectorClass Minecraft = new ReflectorClass(bib.class);
    public static ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Minecraft, ceg.class);
    public static ReflectorField Minecraft_actionKeyF3 = new ReflectorField(new FieldLocatorActionKeyF3());
    public static ReflectorClass ModelHumanoidHead = new ReflectorClass(bpw.class);
    public static ReflectorField ModelHumanoidHead_head = new ReflectorField(ModelHumanoidHead, brs.class);
    public static ReflectorClass ModelBat = new ReflectorClass(bpg.class);
    public static ReflectorFields ModelBat_ModelRenderers = new ReflectorFields(ModelBat, brs.class, 6);
    public static ReflectorClass ModelBlaze = new ReflectorClass(bpi.class);
    public static ReflectorField ModelBlaze_blazeHead = new ReflectorField(ModelBlaze, brs.class);
    public static ReflectorField ModelBlaze_blazeSticks = new ReflectorField(ModelBlaze, brs[].class);
    public static ReflectorClass ModelDragon = new ReflectorClass(brn.class);
    public static ReflectorFields ModelDragon_ModelRenderers = new ReflectorFields(ModelDragon, brs.class, 12);
    public static ReflectorClass ModelEnderCrystal = new ReflectorClass(bro.class);
    public static ReflectorFields ModelEnderCrystal_ModelRenderers = new ReflectorFields(ModelEnderCrystal, brs.class, 3);
    public static ReflectorClass RenderEnderCrystal = new ReflectorClass(bzb.class);
    public static ReflectorField RenderEnderCrystal_modelEnderCrystal = new ReflectorField(RenderEnderCrystal, bqf.class, 0);
    public static ReflectorField RenderEnderCrystal_modelEnderCrystalNoBase = new ReflectorField(RenderEnderCrystal, bqf.class, 1);
    public static ReflectorClass ModelEnderMite = new ReflectorClass(bpr.class);
    public static ReflectorField ModelEnderMite_bodyParts = new ReflectorField(ModelEnderMite, brs[].class);
    public static ReflectorClass ModelEvokerFangs = new ReflectorClass(bps.class);
    public static ReflectorFields ModelEvokerFangs_ModelRenderers = new ReflectorFields(ModelEvokerFangs, brs.class, 3);
    public static ReflectorClass ModelGhast = new ReflectorClass(bpt.class);
    public static ReflectorField ModelGhast_body = new ReflectorField(ModelGhast, brs.class);
    public static ReflectorField ModelGhast_tentacles = new ReflectorField(ModelGhast, brs[].class);
    public static ReflectorClass ModelGuardian = new ReflectorClass(bpu.class);
    public static ReflectorField ModelGuardian_body = new ReflectorField(ModelGuardian, brs.class, 0);
    public static ReflectorField ModelGuardian_eye = new ReflectorField(ModelGuardian, brs.class, 1);
    public static ReflectorField ModelGuardian_spines = new ReflectorField(ModelGuardian, brs[].class, 0);
    public static ReflectorField ModelGuardian_tail = new ReflectorField(ModelGuardian, brs[].class, 1);
    public static ReflectorClass ModelDragonHead = new ReflectorClass(brm.class);
    public static ReflectorField ModelDragonHead_head = new ReflectorField(ModelDragonHead, brs.class, 0);
    public static ReflectorField ModelDragonHead_jaw = new ReflectorField(ModelDragonHead, brs.class, 1);
    public static ReflectorClass ModelHorse = new ReflectorClass(bpv.class);
    public static ReflectorFields ModelHorse_ModelRenderers = new ReflectorFields(ModelHorse, brs.class, 39);
    public static ReflectorClass RenderLeashKnot = new ReflectorClass(bzy.class);
    public static ReflectorField RenderLeashKnot_leashKnotModel = new ReflectorField(RenderLeashKnot, bqb.class);
    public static ReflectorClass ModelMagmaCube = new ReflectorClass(bqa.class);
    public static ReflectorField ModelMagmaCube_core = new ReflectorField(ModelMagmaCube, brs.class);
    public static ReflectorField ModelMagmaCube_segments = new ReflectorField(ModelMagmaCube, brs[].class);
    public static ReflectorClass ModelOcelot = new ReflectorClass(bqg.class);
    public static ReflectorFields ModelOcelot_ModelRenderers = new ReflectorFields(ModelOcelot, brs.class, 8);
    public static ReflectorClass ModelParrot = new ReflectorClass(bqh.class);
    public static ReflectorFields ModelParrot_ModelRenderers = new ReflectorFields(ModelParrot, brs.class, 11);
    public static ReflectorClass ModelRabbit = new ReflectorClass(bqn.class);
    public static ReflectorFields ModelRabbit_renderers = new ReflectorFields(ModelRabbit, brs.class, 12);
    public static ReflectorClass ModelSilverfish = new ReflectorClass(bqu.class);
    public static ReflectorField ModelSilverfish_bodyParts = new ReflectorField(ModelSilverfish, brs[].class, 0);
    public static ReflectorField ModelSilverfish_wingParts = new ReflectorField(ModelSilverfish, brs[].class, 1);
    public static ReflectorClass ModelSlime = new ReflectorClass(bqy.class);
    public static ReflectorFields ModelSlime_ModelRenderers = new ReflectorFields(ModelSlime, brs.class, 4);
    public static ReflectorClass ModelSquid = new ReflectorClass(brb.class);
    public static ReflectorField ModelSquid_body = new ReflectorField(ModelSquid, brs.class);
    public static ReflectorField ModelSquid_tentacles = new ReflectorField(ModelSquid, brs[].class);
    public static ReflectorClass ModelVex = new ReflectorClass(bre.class);
    public static ReflectorField ModelVex_leftWing = new ReflectorField(ModelVex, brs.class, 0);
    public static ReflectorField ModelVex_rightWing = new ReflectorField(ModelVex, brs.class, 1);
    public static ReflectorClass ModelWitch = new ReflectorClass(bri.class);
    public static ReflectorField ModelWitch_mole = new ReflectorField(ModelWitch, brs.class, 0);
    public static ReflectorField ModelWitch_hat = new ReflectorField(ModelWitch, brs.class, 1);
    public static ReflectorClass ModelWither = new ReflectorClass(brj.class);
    public static ReflectorField ModelWither_bodyParts = new ReflectorField(ModelWither, brs[].class, 0);
    public static ReflectorField ModelWither_heads = new ReflectorField(ModelWither, brs[].class, 1);
    public static ReflectorClass ModelWolf = new ReflectorClass(brk.class);
    public static ReflectorField ModelWolf_tail = new ReflectorField(ModelWolf, brs.class, 6);
    public static ReflectorField ModelWolf_mane = new ReflectorField(ModelWolf, brs.class, 7);
    public static ReflectorClass OptiFineClassTransformer = new ReflectorClass("optifine.OptiFineClassTransformer");
    public static ReflectorField OptiFineClassTransformer_instance = new ReflectorField(OptiFineClassTransformer, "instance");
    public static ReflectorMethod OptiFineClassTransformer_getOptiFineResource = new ReflectorMethod(OptiFineClassTransformer, "getOptiFineResource");
    public static ReflectorClass RenderBoat = new ReflectorClass(byt.class);
    public static ReflectorField RenderBoat_modelBoat = new ReflectorField(RenderBoat, bqf.class);
    public static ReflectorClass RenderEvokerFangs = new ReflectorClass(bzh.class);
    public static ReflectorField RenderEvokerFangs_model = new ReflectorField(RenderEvokerFangs, bps.class);
    public static ReflectorClass RenderMinecart = new ReflectorClass(cad.class);
    public static ReflectorField RenderMinecart_modelMinecart = new ReflectorField(RenderMinecart, bqf.class);
    public static ReflectorClass RenderShulkerBullet = new ReflectorClass(cap.class);
    public static ReflectorField RenderShulkerBullet_model = new ReflectorField(RenderShulkerBullet, bqr.class);
    public static ReflectorClass RenderWitherSkull = new ReflectorClass(cbl.class);
    public static ReflectorField RenderWitherSkull_model = new ReflectorField(RenderWitherSkull, bqv.class);
    public static ReflectorClass TileEntityBannerRenderer = new ReflectorClass(bwu.class);
    public static ReflectorField TileEntityBannerRenderer_bannerModel = new ReflectorField(TileEntityBannerRenderer, bpf.class);
    public static ReflectorClass TileEntityBedRenderer = new ReflectorClass(bww.class);
    public static ReflectorField TileEntityBedRenderer_model = new ReflectorField(TileEntityBedRenderer, bph.class);
    public static ReflectorClass TileEntityBeacon = new ReflectorClass(avh.class);
    public static ReflectorField TileEntityBeacon_customName = new ReflectorField(TileEntityBeacon, String.class);
    public static ReflectorClass TileEntityBrewingStand = new ReflectorClass(avk.class);
    public static ReflectorField TileEntityBrewingStand_customName = new ReflectorField(TileEntityBrewingStand, String.class);
    public static ReflectorClass TileEntityChestRenderer = new ReflectorClass(bwz.class);
    public static ReflectorField TileEntityChestRenderer_simpleChest = new ReflectorField(TileEntityChestRenderer, bpl.class, 0);
    public static ReflectorField TileEntityChestRenderer_largeChest = new ReflectorField(TileEntityChestRenderer, bpl.class, 1);
    public static ReflectorClass TileEntityEnchantmentTable = new ReflectorClass(avr.class);
    public static ReflectorField TileEntityEnchantmentTable_customName = new ReflectorField(TileEntityEnchantmentTable, String.class);
    public static ReflectorClass TileEntityEnchantmentTableRenderer = new ReflectorClass(bxa.class);
    public static ReflectorField TileEntityEnchantmentTableRenderer_modelBook = new ReflectorField(TileEntityEnchantmentTableRenderer, bpk.class);
    public static ReflectorClass TileEntityEnderChestRenderer = new ReflectorClass(bxb.class);
    public static ReflectorField TileEntityEnderChestRenderer_modelChest = new ReflectorField(TileEntityEnderChestRenderer, bpl.class);
    public static ReflectorClass TileEntityFurnace = new ReflectorClass(avu.class);
    public static ReflectorField TileEntityFurnace_customName = new ReflectorField(TileEntityFurnace, String.class);
    public static ReflectorClass TileEntityLockableLoot = new ReflectorClass(awa.class);
    public static ReflectorField TileEntityLockableLoot_customName = new ReflectorField(TileEntityLockableLoot, String.class);
    public static ReflectorClass TileEntityShulkerBoxRenderer = new ReflectorClass(bxe.class);
    public static ReflectorField TileEntityShulkerBoxRenderer_model = new ReflectorField(TileEntityShulkerBoxRenderer, bqs.class);
    public static ReflectorClass TileEntitySignRenderer = new ReflectorClass(bxf.class);
    public static ReflectorField TileEntitySignRenderer_model = new ReflectorField(TileEntitySignRenderer, bqt.class);
    public static ReflectorClass TileEntitySkullRenderer = new ReflectorClass(bxg.class);
    public static ReflectorField TileEntitySkullRenderer_dragonHead = new ReflectorField(TileEntitySkullRenderer, brm.class, 0);
    public static ReflectorField TileEntitySkullRenderer_skeletonHead = new ReflectorField(TileEntitySkullRenderer, bqv.class, 0);
    public static ReflectorField TileEntitySkullRenderer_humanoidHead = new ReflectorField(TileEntitySkullRenderer, bqv.class, 1);

    public static void callVoid(ReflectorMethod refMethod, Object ... params) {
        try {
            Method m2 = refMethod.getTargetMethod();
            if (m2 == null) {
                return;
            }
            m2.invoke(null, params);
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
        }
    }

    public static boolean callBoolean(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return false;
            }
            Boolean retVal = (Boolean)method.invoke(null, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return false;
        }
    }

    public static int callInt(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0;
            }
            Integer retVal = (Integer)method.invoke(null, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return 0;
        }
    }

    public static float callFloat(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0.0f;
            }
            Float retVal = (Float)method.invoke(null, params);
            return retVal.floatValue();
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return 0.0f;
        }
    }

    public static double callDouble(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0.0;
            }
            Double retVal = (Double)method.invoke(null, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return 0.0;
        }
    }

    public static String callString(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return null;
            }
            String retVal = (String)method.invoke(null, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return null;
        }
    }

    public static Object call(ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return null;
            }
            Object retVal = method.invoke(null, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, null, refMethod, params);
            return null;
        }
    }

    public static void callVoid(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            if (obj == null) {
                return;
            }
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return;
            }
            method.invoke(obj, params);
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
        }
    }

    public static boolean callBoolean(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return false;
            }
            Boolean retVal = (Boolean)method.invoke(obj, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return false;
        }
    }

    public static int callInt(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0;
            }
            Integer retVal = (Integer)method.invoke(obj, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return 0;
        }
    }

    public static float callFloat(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0.0f;
            }
            Float retVal = (Float)method.invoke(obj, params);
            return retVal.floatValue();
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return 0.0f;
        }
    }

    public static double callDouble(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return 0.0;
            }
            Double retVal = (Double)method.invoke(obj, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return 0.0;
        }
    }

    public static String callString(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return null;
            }
            String retVal = (String)method.invoke(obj, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return null;
        }
    }

    public static Object call(Object obj, ReflectorMethod refMethod, Object ... params) {
        try {
            Method method = refMethod.getTargetMethod();
            if (method == null) {
                return null;
            }
            Object retVal = method.invoke(obj, params);
            return retVal;
        }
        catch (Throwable e) {
            Reflector.handleException(e, obj, refMethod, params);
            return null;
        }
    }

    public static Object getFieldValue(ReflectorField refField) {
        return Reflector.getFieldValue(null, refField);
    }

    public static Object getFieldValue(Object obj, ReflectorField refField) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return null;
            }
            Object value = field.get(obj);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean getFieldValueBoolean(ReflectorField refField, boolean def) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return def;
            }
            boolean value = field.getBoolean(null);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return def;
        }
    }

    public static boolean getFieldValueBoolean(Object obj, ReflectorField refField, boolean def) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return def;
            }
            boolean value = field.getBoolean(obj);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return def;
        }
    }

    public static Object getFieldValue(ReflectorFields refFields, int index) {
        ReflectorField refField = refFields.getReflectorField(index);
        if (refField == null) {
            return null;
        }
        return Reflector.getFieldValue(refField);
    }

    public static Object getFieldValue(Object obj, ReflectorFields refFields, int index) {
        ReflectorField refField = refFields.getReflectorField(index);
        if (refField == null) {
            return null;
        }
        return Reflector.getFieldValue(obj, refField);
    }

    public static float getFieldValueFloat(Object obj, ReflectorField refField, float def) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return def;
            }
            float value = field.getFloat(obj);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return def;
        }
    }

    public static int getFieldValueInt(Object obj, ReflectorField refField, int def) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return def;
            }
            int value = field.getInt(obj);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return def;
        }
    }

    public static long getFieldValueLong(Object obj, ReflectorField refField, long def) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return def;
            }
            long value = field.getLong(obj);
            return value;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return def;
        }
    }

    public static boolean setFieldValue(ReflectorField refField, Object value) {
        return Reflector.setFieldValue(null, refField, value);
    }

    public static boolean setFieldValue(Object obj, ReflectorField refField, Object value) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return false;
            }
            field.set(obj, value);
            return true;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setFieldValueInt(ReflectorField refField, int value) {
        return Reflector.setFieldValueInt(null, refField, value);
    }

    public static boolean setFieldValueInt(Object obj, ReflectorField refField, int value) {
        try {
            Field field = refField.getTargetField();
            if (field == null) {
                return false;
            }
            field.setInt(obj, value);
            return true;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean postForgeBusEvent(ReflectorConstructor constr, Object ... params) {
        Object event = Reflector.newInstance(constr, params);
        if (event == null) {
            return false;
        }
        return Reflector.postForgeBusEvent(event);
    }

    public static boolean postForgeBusEvent(Object event) {
        if (event == null) {
            return false;
        }
        Object eventBus = Reflector.getFieldValue(MinecraftForge_EVENT_BUS);
        if (eventBus == null) {
            return false;
        }
        Object ret = Reflector.call(eventBus, EventBus_post, event);
        if (!(ret instanceof Boolean)) {
            return false;
        }
        Boolean retBool = (Boolean)ret;
        return retBool;
    }

    public static Object newInstance(ReflectorConstructor constr, Object ... params) {
        Constructor c2 = constr.getTargetConstructor();
        if (c2 == null) {
            return null;
        }
        try {
            Object obj = c2.newInstance(params);
            return obj;
        }
        catch (Throwable e) {
            Reflector.handleException(e, constr, params);
            return null;
        }
    }

    public static boolean matchesTypes(Class[] pTypes, Class[] cTypes) {
        if (pTypes.length != cTypes.length) {
            return false;
        }
        for (int i = 0; i < cTypes.length; ++i) {
            Class pType = pTypes[i];
            Class cType = cTypes[i];
            if (pType == cType) continue;
            return false;
        }
        return true;
    }

    private static void dbgCall(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params, Object retVal) {
        String className = refMethod.getTargetMethod().getDeclaringClass().getName();
        String methodName = refMethod.getTargetMethod().getName();
        String staticStr = "";
        if (isStatic) {
            staticStr = " static";
        }
        Config.dbg(callType + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ") => " + retVal);
    }

    private static void dbgCallVoid(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params) {
        String className = refMethod.getTargetMethod().getDeclaringClass().getName();
        String methodName = refMethod.getTargetMethod().getName();
        String staticStr = "";
        if (isStatic) {
            staticStr = " static";
        }
        Config.dbg(callType + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ")");
    }

    private static void dbgFieldValue(boolean isStatic, String accessType, ReflectorField refField, Object val) {
        String className = refField.getTargetField().getDeclaringClass().getName();
        String fieldName = refField.getTargetField().getName();
        String staticStr = "";
        if (isStatic) {
            staticStr = " static";
        }
        Config.dbg(accessType + staticStr + " " + className + "." + fieldName + " => " + val);
    }

    private static void handleException(Throwable e, Object obj, ReflectorMethod refMethod, Object[] params) {
        if (e instanceof InvocationTargetException) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                RuntimeException causeRuntime = (RuntimeException)cause;
                throw causeRuntime;
            }
            e.printStackTrace();
            return;
        }
        if (e instanceof IllegalArgumentException) {
            Config.warn("*** IllegalArgumentException ***");
            Config.warn("Method: " + refMethod.getTargetMethod());
            Config.warn("Object: " + obj);
            Config.warn("Parameter classes: " + Config.arrayToString(Reflector.getClasses(params)));
            Config.warn("Parameters: " + Config.arrayToString(params));
        }
        Config.warn("*** Exception outside of method ***");
        Config.warn("Method deactivated: " + refMethod.getTargetMethod());
        refMethod.deactivate();
        e.printStackTrace();
    }

    private static void handleException(Throwable e, ReflectorConstructor refConstr, Object[] params) {
        if (e instanceof InvocationTargetException) {
            e.printStackTrace();
            return;
        }
        if (e instanceof IllegalArgumentException) {
            Config.warn("*** IllegalArgumentException ***");
            Config.warn("Constructor: " + refConstr.getTargetConstructor());
            Config.warn("Parameter classes: " + Config.arrayToString(Reflector.getClasses(params)));
            Config.warn("Parameters: " + Config.arrayToString(params));
        }
        Config.warn("*** Exception outside of constructor ***");
        Config.warn("Constructor deactivated: " + refConstr.getTargetConstructor());
        refConstr.deactivate();
        e.printStackTrace();
    }

    private static Object[] getClasses(Object[] objs) {
        if (objs == null) {
            return new Class[0];
        }
        Object[] classes = new Class[objs.length];
        for (int i = 0; i < classes.length; ++i) {
            Object obj = objs[i];
            if (obj == null) continue;
            classes[i] = obj.getClass();
        }
        return classes;
    }

    private static ReflectorField[] getReflectorFields(ReflectorClass parentClass, Class fieldType, int count) {
        ReflectorField[] rfs = new ReflectorField[count];
        for (int i = 0; i < rfs.length; ++i) {
            rfs[i] = new ReflectorField(parentClass, fieldType, i);
        }
        return rfs;
    }

    private static boolean logEntry(String str) {
        Config.dbg(str);
        return true;
    }
}

