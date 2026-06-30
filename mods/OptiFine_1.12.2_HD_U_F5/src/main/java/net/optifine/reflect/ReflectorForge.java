/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  agv
 *  ain
 *  aip
 *  aiw
 *  amu
 *  amy
 *  aow
 *  awt
 *  bev
 *  buy
 *  cfy
 *  d
 *  et
 *  nf
 *  vq
 */
package net.optifine.reflect;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.optifine.reflect.Reflector;

public class ReflectorForge {
    public static Object EVENT_RESULT_ALLOW = Reflector.getFieldValue(Reflector.Event_Result_ALLOW);
    public static Object EVENT_RESULT_DENY = Reflector.getFieldValue(Reflector.Event_Result_DENY);
    public static Object EVENT_RESULT_DEFAULT = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);

    public static void FMLClientHandler_trackBrokenTexture(nf loc, String message) {
        if (Reflector.FMLClientHandler_trackBrokenTexture.exists()) {
            return;
        }
        Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
        Reflector.call(instance, Reflector.FMLClientHandler_trackBrokenTexture, loc, message);
    }

    public static void FMLClientHandler_trackMissingTexture(nf loc) {
        if (Reflector.FMLClientHandler_trackMissingTexture.exists()) {
            return;
        }
        Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
        Reflector.call(instance, Reflector.FMLClientHandler_trackMissingTexture, loc);
    }

    public static void putLaunchBlackboard(String key, Object value) {
        Map blackboard = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);
        if (blackboard == null) {
            return;
        }
        blackboard.put(key, value);
    }

    public static boolean renderFirstPersonHand(buy renderGlobal, float partialTicks, int pass) {
        if (!Reflector.ForgeHooksClient_renderFirstPersonHand.exists()) {
            return false;
        }
        return Reflector.callBoolean(Reflector.ForgeHooksClient_renderFirstPersonHand, renderGlobal, Float.valueOf(partialTicks), pass);
    }

    public static InputStream getOptiFineResourceStream(String path) {
        byte[] bytes;
        if (!Reflector.OptiFineClassTransformer_instance.exists()) {
            return null;
        }
        Object instance = Reflector.getFieldValue(Reflector.OptiFineClassTransformer_instance);
        if (instance == null) {
            return null;
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if ((bytes = (byte[])Reflector.call(instance, Reflector.OptiFineClassTransformer_getOptiFineResource, path)) == null) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return in;
    }

    public static boolean blockHasTileEntity(awt state) {
        aow block = state.u();
        if (!Reflector.ForgeBlock_hasTileEntity.exists()) {
            return block.l();
        }
        return Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, state);
    }

    public static boolean isItemDamaged(aip stack) {
        if (!Reflector.ForgeItem_showDurabilityBar.exists()) {
            return stack.h();
        }
        return Reflector.callBoolean(stack.c(), Reflector.ForgeItem_showDurabilityBar, stack);
    }

    public static boolean armorHasOverlay(agv itemArmor, aip itemStack) {
        if (Reflector.ForgeItemArmor_hasOverlay.exists()) {
            return Reflector.callBoolean(itemArmor, Reflector.ForgeItemArmor_hasOverlay, itemStack);
        }
        int i = itemArmor.c(itemStack);
        return i != 0xFFFFFF;
    }

    public static int getLightValue(awt stateIn, amy worldIn, et posIn) {
        if (Reflector.ForgeIBlockProperties_getLightValue2.exists()) {
            return Reflector.callInt(stateIn, Reflector.ForgeIBlockProperties_getLightValue2, worldIn, posIn);
        }
        return stateIn.d();
    }

    public static bev getMapData(aiw itemMap, aip stack, amu world) {
        if (Reflector.ForgeHooksClient.exists()) {
            return ((aiw)stack.c()).a(stack, world);
        }
        return itemMap.a(stack, world);
    }

    public static String[] getForgeModIds() {
        if (!Reflector.Loader.exists()) {
            return new String[0];
        }
        Object loader = Reflector.call(Reflector.Loader_instance, new Object[0]);
        List listActiveMods = (List)Reflector.call(loader, Reflector.Loader_getActiveModList, new Object[0]);
        if (listActiveMods == null) {
            return new String[0];
        }
        ArrayList<String> listModIds = new ArrayList<String>();
        for (Object modContainer : listActiveMods) {
            String modId;
            if (!Reflector.ModContainer.isInstance(modContainer) || (modId = Reflector.callString(modContainer, Reflector.ModContainer_getModId, new Object[0])) == null) continue;
            listModIds.add(modId);
        }
        String[] modIds = listModIds.toArray(new String[listModIds.size()]);
        return modIds;
    }

    public static boolean canEntitySpawn(vq entityliving, amu world, float x, float y, float z) {
        Object canSpawn = Reflector.call(Reflector.ForgeEventFactory_canEntitySpawn, entityliving, world, Float.valueOf(x), Float.valueOf(y), Float.valueOf(z), false);
        return canSpawn == EVENT_RESULT_ALLOW || canSpawn == EVENT_RESULT_DEFAULT && entityliving.P() && entityliving.Q();
    }

    public static boolean doSpecialSpawn(vq entityliving, amu world, float x, int y, float z) {
        if (Reflector.ForgeEventFactory_doSpecialSpawn.exists()) {
            return Reflector.callBoolean(Reflector.ForgeEventFactory_doSpecialSpawn, entityliving, world, Float.valueOf(x), y, Float.valueOf(z));
        }
        return false;
    }

    public static boolean isAmbientOcclusion(cfy model, awt state) {
        if (Reflector.ForgeIBakedModel_isAmbientOcclusion2.exists()) {
            return Reflector.callBoolean(model, Reflector.ForgeIBakedModel_isAmbientOcclusion2, state);
        }
        return model.a();
    }

    public static d<String> getDetailItemRegistryName(final ain item) {
        return new d<String>(){

            public String call() throws Exception {
                Object registryName = Reflector.call(item, Reflector.IForgeRegistryEntry_Impl_getRegistryName, new Object[0]);
                return String.valueOf(registryName);
            }
        };
    }
}

