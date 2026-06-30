/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  avh
 *  avj
 *  avk
 *  avr
 *  avu
 *  awa
 *  et
 *  ui
 */
package net.optifine.util;

import net.optifine.reflect.Reflector;
import net.optifine.util.IntegratedServerUtils;

public class TileEntityUtils {
    public static String getTileEntityName(amy blockAccess, et blockPos) {
        avj te = blockAccess.r(blockPos);
        return TileEntityUtils.getTileEntityName(te);
    }

    public static String getTileEntityName(avj te) {
        if (!(te instanceof ui)) {
            return null;
        }
        ui iwn = (ui)te;
        TileEntityUtils.updateTileEntityName(te);
        if (!iwn.n_()) {
            return null;
        }
        return iwn.h_();
    }

    public static void updateTileEntityName(avj te) {
        et pos = te.w();
        String name = TileEntityUtils.getTileEntityRawName(te);
        if (name != null) {
            return;
        }
        String nameServer = TileEntityUtils.getServerTileEntityRawName(pos);
        nameServer = Config.normalize(nameServer);
        TileEntityUtils.setTileEntityRawName(te, nameServer);
    }

    public static String getServerTileEntityRawName(et blockPos) {
        avj tes = IntegratedServerUtils.getTileEntity(blockPos);
        if (tes == null) {
            return null;
        }
        return TileEntityUtils.getTileEntityRawName(tes);
    }

    public static String getTileEntityRawName(avj te) {
        if (te instanceof avh) {
            return (String)Reflector.getFieldValue(te, Reflector.TileEntityBeacon_customName);
        }
        if (te instanceof avk) {
            return (String)Reflector.getFieldValue(te, Reflector.TileEntityBrewingStand_customName);
        }
        if (te instanceof avr) {
            return (String)Reflector.getFieldValue(te, Reflector.TileEntityEnchantmentTable_customName);
        }
        if (te instanceof avu) {
            return (String)Reflector.getFieldValue(te, Reflector.TileEntityFurnace_customName);
        }
        if (te instanceof awa) {
            return (String)Reflector.getFieldValue(te, Reflector.TileEntityLockableLoot_customName);
        }
        return null;
    }

    public static boolean setTileEntityRawName(avj te, String name) {
        if (te instanceof avh) {
            return Reflector.setFieldValue(te, Reflector.TileEntityBeacon_customName, name);
        }
        if (te instanceof avk) {
            return Reflector.setFieldValue(te, Reflector.TileEntityBrewingStand_customName, name);
        }
        if (te instanceof avr) {
            return Reflector.setFieldValue(te, Reflector.TileEntityEnchantmentTable_customName, name);
        }
        if (te instanceof avu) {
            return Reflector.setFieldValue(te, Reflector.TileEntityFurnace_customName, name);
        }
        if (te instanceof awa) {
            return Reflector.setFieldValue(te, Reflector.TileEntityLockableLoot_customName, name);
        }
        return false;
    }
}

