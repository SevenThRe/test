/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 */
package eos.moe.dragoncore;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eos.moe.dragoncore.er;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class vu {
    private static Field ALLATORIxDEMO;

    public vu() {
        vu a2;
    }

    public static String f(GameProfile a2) {
        if (a2 == null) {
            return null;
        }
        if (ALLATORIxDEMO == null) {
            try {
                ALLATORIxDEMO = GameProfile.class.getDeclaredField("name");
                ALLATORIxDEMO.setAccessible(true);
            }
            catch (NoSuchFieldException a3) {
                a3.printStackTrace();
            }
        }
        try {
            return (String)ALLATORIxDEMO.get(a2);
        }
        catch (IllegalAccessException a4) {
            return "";
        }
    }

    public static er ALLATORIxDEMO(TileEntity a2) {
        if (a2 instanceof TileEntitySkull) {
            TileEntitySkull a3 = (TileEntitySkull)a2;
            return vu.ALLATORIxDEMO(a3.getPlayerProfile());
        }
        return null;
    }

    public static er ALLATORIxDEMO(GameProfile a2) {
        if (a2 != null) {
            String a3 = vu.c(a2);
            return er.ALLATORIxDEMO(a3);
        }
        return null;
    }

    public static int ALLATORIxDEMO(TileEntity a2) {
        String a3 = vu.c(a2);
        if (a3 != null && a3.startsWith("none")) {
            return 0;
        }
        er a4 = vu.ALLATORIxDEMO(a2);
        if (a4 != null) {
            return a4.k;
        }
        return 0;
    }

    public static String c(TileEntity a2) {
        if (a2 instanceof TileEntitySkull) {
            TileEntitySkull a3 = (TileEntitySkull)a2;
            return vu.c(a3.getPlayerProfile());
        }
        return null;
    }

    public static String c(GameProfile a2) {
        String a3 = vu.f(a2);
        if (a3 != null) {
            Collection a4 = a2.getProperties().get((Object)"model");
            if (a4 != null && a4.size() > 0) {
                Property a5 = (Property)a4.stream().findFirst().get();
                return String.valueOf(a5.getValue());
            }
            return a3;
        }
        return null;
    }

    public static String ALLATORIxDEMO(TileEntity a2) {
        if (a2 instanceof TileEntitySkull) {
            GameProfile a3 = ((TileEntitySkull)a2).getPlayerProfile();
            return vu.ALLATORIxDEMO(a3);
        }
        return null;
    }

    public static String ALLATORIxDEMO(GameProfile a2) {
        Optional a3;
        if (a2 != null && a2.getProperties().containsKey((Object)"textures") && (a3 = a2.getProperties().get((Object)"textures").stream().findFirst()).isPresent()) {
            return ((Property)a3.get()).getValue();
        }
        return null;
    }

    public static boolean ALLATORIxDEMO(TileEntity a2) {
        GameProfile a3 = ((TileEntitySkull)a2).getPlayerProfile();
        return a3 != null && a3.getProperties().containsKey((Object)"hideBlock");
    }
}

