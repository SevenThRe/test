/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  bib
 *  blk
 *  bmg
 *  bmh
 *  bmi
 *  bmk
 *  bmm
 *  bmn
 *  bmp
 *  bmq
 *  bmt
 *  bmu
 *  bmv
 *  bmw
 *  bmx
 *  bmy
 *  bna
 *  bsb
 *  cer
 *  et
 *  nf
 *  vg
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import net.optifine.CustomGuiProperties;
import net.optifine.override.PlayerControllerOF;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomGuis {
    private static bib mc = Config.getMinecraft();
    private static PlayerControllerOF playerControllerOF = null;
    private static CustomGuiProperties[][] guiProperties = null;
    public static boolean isChristmas = CustomGuis.isChristmas();

    public static nf getTextureLocation(nf loc) {
        vg entity;
        if (guiProperties == null) {
            return loc;
        }
        blk screen = CustomGuis.mc.m;
        if (!(screen instanceof bmg)) {
            return loc;
        }
        if (!loc.b().equals("minecraft") || !loc.a().startsWith("textures/gui/")) {
            return loc;
        }
        if (playerControllerOF == null) {
            return loc;
        }
        bsb world = CustomGuis.mc.f;
        if (world == null) {
            return loc;
        }
        if (screen instanceof bmp) {
            return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CREATIVE, CustomGuis.mc.h.c(), (amy)world, loc, screen);
        }
        if (screen instanceof bmx) {
            return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.INVENTORY, CustomGuis.mc.h.c(), (amy)world, loc, screen);
        }
        et pos = playerControllerOF.getLastClickBlockPos();
        if (pos != null) {
            if (screen instanceof bmh) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.ANVIL, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmi) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.BEACON, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmk) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.BREWING_STAND, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmm) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CHEST, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmn) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.CRAFTING, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmq) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.DISPENSER, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmt) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.ENCHANTMENT, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmu) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.FURNACE, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bmv) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.HOPPER, pos, (amy)world, loc, screen);
            }
            if (screen instanceof bna) {
                return CustomGuis.getTexturePos(CustomGuiProperties.EnumContainer.SHULKER_BOX, pos, (amy)world, loc, screen);
            }
        }
        if ((entity = playerControllerOF.getLastClickEntity()) != null) {
            if (screen instanceof bmw) {
                return CustomGuis.getTextureEntity(CustomGuiProperties.EnumContainer.HORSE, entity, (amy)world, loc);
            }
            if (screen instanceof bmy) {
                return CustomGuis.getTextureEntity(CustomGuiProperties.EnumContainer.VILLAGER, entity, (amy)world, loc);
            }
        }
        return loc;
    }

    private static nf getTexturePos(CustomGuiProperties.EnumContainer container, et pos, amy blockAccess, nf loc, blk screen) {
        CustomGuiProperties[] props = guiProperties[container.ordinal()];
        if (props == null) {
            return loc;
        }
        for (int i = 0; i < props.length; ++i) {
            CustomGuiProperties prop = props[i];
            if (!prop.matchesPos(container, pos, blockAccess, screen)) continue;
            return prop.getTextureLocation(loc);
        }
        return loc;
    }

    private static nf getTextureEntity(CustomGuiProperties.EnumContainer container, vg entity, amy blockAccess, nf loc) {
        CustomGuiProperties[] props = guiProperties[container.ordinal()];
        if (props == null) {
            return loc;
        }
        for (int i = 0; i < props.length; ++i) {
            CustomGuiProperties prop = props[i];
            if (!prop.matchesEntity(container, entity, blockAccess)) continue;
            return prop.getTextureLocation(loc);
        }
        return loc;
    }

    public static void update() {
        guiProperties = null;
        if (!Config.isCustomGuis()) {
            return;
        }
        ArrayList<List<CustomGuiProperties>> listProps = new ArrayList<List<CustomGuiProperties>>();
        cer[] rps = Config.getResourcePacks();
        for (int i = rps.length - 1; i >= 0; --i) {
            cer rp = rps[i];
            CustomGuis.update(rp, listProps);
        }
        guiProperties = CustomGuis.propertyListToArray(listProps);
    }

    private static CustomGuiProperties[][] propertyListToArray(List<List<CustomGuiProperties>> listProps) {
        if (listProps.isEmpty()) {
            return null;
        }
        CustomGuiProperties[][] cgps = new CustomGuiProperties[CustomGuiProperties.EnumContainer.values().length][];
        for (int i = 0; i < cgps.length; ++i) {
            List<CustomGuiProperties> subList;
            if (listProps.size() <= i || (subList = listProps.get(i)) == null) continue;
            CustomGuiProperties[] subArr = subList.toArray(new CustomGuiProperties[subList.size()]);
            cgps[i] = subArr;
        }
        return cgps;
    }

    private static void update(cer rp, List<List<CustomGuiProperties>> listProps) {
        Object[] paths = ResUtils.collectFiles(rp, "optifine/gui/container/", ".properties", null);
        Arrays.sort(paths);
        for (int i = 0; i < paths.length; ++i) {
            Object name = paths[i];
            Config.dbg("CustomGuis: " + (String)name);
            try {
                nf locFile = new nf((String)name);
                InputStream in = rp.a(locFile);
                if (in == null) {
                    Config.warn("CustomGuis file not found: " + (String)name);
                    continue;
                }
                PropertiesOrdered props = new PropertiesOrdered();
                props.load(in);
                in.close();
                CustomGuiProperties cgp = new CustomGuiProperties(props, (String)name);
                if (!cgp.isValid((String)name)) continue;
                CustomGuis.addToList(cgp, listProps);
                continue;
            }
            catch (FileNotFoundException e) {
                Config.warn("CustomGuis file not found: " + (String)name);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addToList(CustomGuiProperties cgp, List<List<CustomGuiProperties>> listProps) {
        if (cgp.getContainer() == null) {
            CustomGuis.warn("Invalid container: " + (Object)((Object)cgp.getContainer()));
            return;
        }
        int indexContainer = cgp.getContainer().ordinal();
        while (listProps.size() <= indexContainer) {
            listProps.add(null);
        }
        List<CustomGuiProperties> subList = listProps.get(indexContainer);
        if (subList == null) {
            subList = new ArrayList<CustomGuiProperties>();
            listProps.set(indexContainer, subList);
        }
        subList.add(cgp);
    }

    public static PlayerControllerOF getPlayerControllerOF() {
        return playerControllerOF;
    }

    public static void setPlayerControllerOF(PlayerControllerOF playerControllerOF) {
        CustomGuis.playerControllerOF = playerControllerOF;
    }

    private static boolean isChristmas() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26;
    }

    private static void warn(String str) {
        Config.warn("[CustomGuis] " + str);
    }
}

