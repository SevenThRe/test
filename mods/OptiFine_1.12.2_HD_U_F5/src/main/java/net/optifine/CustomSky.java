/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 *  cdr
 *  cds
 *  nf
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import net.optifine.CustomSkyLayer;
import net.optifine.render.Blender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;

public class CustomSky {
    private static CustomSkyLayer[][] worldSkyLayers = null;

    public static void reset() {
        worldSkyLayers = null;
    }

    public static void update() {
        CustomSky.reset();
        if (!Config.isCustomSky()) {
            return;
        }
        worldSkyLayers = CustomSky.readCustomSkies();
    }

    private static CustomSkyLayer[][] readCustomSkies() {
        CustomSkyLayer[][] wsls = new CustomSkyLayer[10][0];
        String prefix = "mcpatcher/sky/world";
        int lastWorldId = -1;
        for (int w = 0; w < wsls.length; ++w) {
            String worldPrefix = prefix + w + "/sky";
            ArrayList<CustomSkyLayer> listSkyLayers = new ArrayList<CustomSkyLayer>();
            for (int i = 1; i < 1000; ++i) {
                String path = worldPrefix + i + ".properties";
                try {
                    nf locPath = new nf(path);
                    InputStream in = Config.getResourceStream(locPath);
                    if (in == null) break;
                    PropertiesOrdered props = new PropertiesOrdered();
                    props.load(in);
                    in.close();
                    Config.dbg("CustomSky properties: " + path);
                    String defSource = worldPrefix + i + ".png";
                    CustomSkyLayer sl = new CustomSkyLayer(props, defSource);
                    if (!sl.isValid(path)) continue;
                    nf locSource = new nf(sl.source);
                    cds tex = TextureUtils.getTexture(locSource);
                    if (tex == null) {
                        Config.log("CustomSky: Texture not found: " + locSource);
                        continue;
                    }
                    sl.textureId = tex.b();
                    listSkyLayers.add(sl);
                    in.close();
                    continue;
                }
                catch (FileNotFoundException e) {
                    break;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (listSkyLayers.size() <= 0) continue;
            CustomSkyLayer[] sls = listSkyLayers.toArray(new CustomSkyLayer[listSkyLayers.size()]);
            wsls[w] = sls;
            lastWorldId = w;
        }
        if (lastWorldId < 0) {
            return null;
        }
        int worldCount = lastWorldId + 1;
        CustomSkyLayer[][] wslsTrim = new CustomSkyLayer[worldCount][0];
        for (int i = 0; i < wslsTrim.length; ++i) {
            wslsTrim[i] = wsls[i];
        }
        return wslsTrim;
    }

    public static void renderSky(amu world, cdr re, float partialTicks) {
        if (worldSkyLayers == null) {
            return;
        }
        int dimId = world.s.q().a();
        if (dimId < 0 || dimId >= worldSkyLayers.length) {
            return;
        }
        CustomSkyLayer[] sls = worldSkyLayers[dimId];
        if (sls == null) {
            return;
        }
        long time = world.S();
        int timeOfDay = (int)(time % 24000L);
        float celestialAngle = world.c(partialTicks);
        float rainStrength = world.j(partialTicks);
        float thunderStrength = world.h(partialTicks);
        if (rainStrength > 0.0f) {
            thunderStrength /= rainStrength;
        }
        for (int i = 0; i < sls.length; ++i) {
            CustomSkyLayer sl = sls[i];
            if (!sl.isActive(world, timeOfDay)) continue;
            sl.render(world, timeOfDay, celestialAngle, rainStrength, thunderStrength);
        }
        float rainBrightness = 1.0f - rainStrength;
        Blender.clearBlend(rainBrightness);
    }

    public static boolean hasSkyLayers(amu world) {
        if (worldSkyLayers == null) {
            return false;
        }
        int dimId = world.s.q().a();
        if (dimId < 0 || dimId >= worldSkyLayers.length) {
            return false;
        }
        CustomSkyLayer[] sls = worldSkyLayers[dimId];
        if (sls == null) {
            return false;
        }
        return sls.length > 0;
    }
}

