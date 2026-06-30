/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cdp
 *  cdq
 *  nf
 */
package net.optifine;

import java.util.List;
import net.optifine.reflect.ReflectorForge;

public class SpriteDependencies {
    private static int countDependenciesTotal;

    public static cdq resolveDependencies(List<cdq> listRegisteredSprites, int ix, cdp textureMap) {
        cdq sprite = listRegisteredSprites.get(ix);
        while (SpriteDependencies.resolveOne(listRegisteredSprites, ix, sprite, textureMap)) {
            sprite = listRegisteredSprites.get(ix);
        }
        sprite.isDependencyParent = false;
        return sprite;
    }

    private static boolean resolveOne(List<cdq> listRegisteredSprites, int ix, cdq sprite, cdp textureMap) {
        int countDep = 0;
        for (nf locDep : sprite.getDependencies()) {
            Config.detail("Sprite dependency: " + sprite.i() + " <- " + locDep);
            ++countDependenciesTotal;
            cdq spriteDep = textureMap.getRegisteredSprite(locDep);
            if (spriteDep == null) {
                spriteDep = textureMap.a(locDep);
            } else {
                int ixDep = listRegisteredSprites.indexOf(spriteDep);
                if (ixDep <= ix + countDep) continue;
                if (spriteDep.isDependencyParent) {
                    String error = "circular dependency: " + sprite.i() + " -> " + spriteDep.i();
                    nf locSpritePng = textureMap.a(sprite);
                    ReflectorForge.FMLClientHandler_trackBrokenTexture(locSpritePng, error);
                    break;
                }
                listRegisteredSprites.remove(ixDep);
            }
            sprite.isDependencyParent = true;
            listRegisteredSprites.add(ix + countDep, spriteDep);
            ++countDep;
        }
        return countDep > 0;
    }

    public static void reset() {
        countDependenciesTotal = 0;
    }

    public static int getCountDependencies() {
        return countDependenciesTotal;
    }
}

