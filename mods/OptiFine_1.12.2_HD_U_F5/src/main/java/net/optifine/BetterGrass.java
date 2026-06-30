/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  aow
 *  aox
 *  apy
 *  apy$a
 *  arb
 *  arc
 *  asc
 *  awt
 *  axj
 *  cdp
 *  cdq
 *  cfy
 *  et
 *  fa
 *  nf
 */
package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.PropertiesOrdered;

public class BetterGrass {
    private static boolean betterGrass = true;
    private static boolean betterGrassPath = true;
    private static boolean betterMycelium = true;
    private static boolean betterPodzol = true;
    private static boolean betterGrassSnow = true;
    private static boolean betterMyceliumSnow = true;
    private static boolean betterPodzolSnow = true;
    private static boolean grassMultilayer = false;
    private static cdq spriteGrass = null;
    private static cdq spriteGrassSide = null;
    private static cdq spriteGrassPath = null;
    private static cdq spriteGrassPathSide = null;
    private static cdq spriteMycelium = null;
    private static cdq spritePodzol = null;
    private static cdq spriteSnow = null;
    private static boolean spritesLoaded = false;
    private static cfy modelCubeGrass = null;
    private static cfy modelGrassPath = null;
    private static cfy modelCubeGrassPath = null;
    private static cfy modelCubeMycelium = null;
    private static cfy modelCubePodzol = null;
    private static cfy modelCubeSnow = null;
    private static boolean modelsLoaded = false;
    private static final String TEXTURE_GRASS_DEFAULT = "blocks/grass_top";
    private static final String TEXTURE_GRASS_SIDE_DEFAULT = "blocks/grass_side";
    private static final String TEXTURE_GRASS_PATH_DEFAULT = "blocks/grass_path_top";
    private static final String TEXTURE_GRASS_PATH_SIDE_DEFAULT = "blocks/grass_path_side";
    private static final String TEXTURE_MYCELIUM_DEFAULT = "blocks/mycelium_top";
    private static final String TEXTURE_PODZOL_DEFAULT = "blocks/dirt_podzol_top";
    private static final String TEXTURE_SNOW_DEFAULT = "blocks/snow";

    public static void updateIcons(cdp textureMap) {
        spritesLoaded = false;
        modelsLoaded = false;
        BetterGrass.loadProperties(textureMap);
    }

    public static void update() {
        if (!spritesLoaded) {
            return;
        }
        modelCubeGrass = BlockModelUtils.makeModelCube(spriteGrass, 0);
        if (grassMultilayer) {
            cfy modelCubeGrassSide = BlockModelUtils.makeModelCube(spriteGrassSide, -1);
            modelCubeGrass = BlockModelUtils.joinModelsCube(modelCubeGrassSide, modelCubeGrass);
        }
        modelGrassPath = BlockModelUtils.makeModel("grass_path", spriteGrassPathSide, spriteGrassPath);
        modelCubeGrassPath = BlockModelUtils.makeModelCube(spriteGrassPath, -1);
        modelCubeMycelium = BlockModelUtils.makeModelCube(spriteMycelium, -1);
        modelCubePodzol = BlockModelUtils.makeModelCube(spritePodzol, 0);
        modelCubeSnow = BlockModelUtils.makeModelCube(spriteSnow, -1);
        modelsLoaded = true;
    }

    private static void loadProperties(cdp textureMap) {
        betterGrass = true;
        betterGrassPath = true;
        betterMycelium = true;
        betterPodzol = true;
        betterGrassSnow = true;
        betterMyceliumSnow = true;
        betterPodzolSnow = true;
        spriteGrass = textureMap.a(new nf(TEXTURE_GRASS_DEFAULT));
        spriteGrassSide = textureMap.a(new nf(TEXTURE_GRASS_SIDE_DEFAULT));
        spriteGrassPath = textureMap.a(new nf(TEXTURE_GRASS_PATH_DEFAULT));
        spriteGrassPathSide = textureMap.a(new nf(TEXTURE_GRASS_PATH_SIDE_DEFAULT));
        spriteMycelium = textureMap.a(new nf(TEXTURE_MYCELIUM_DEFAULT));
        spritePodzol = textureMap.a(new nf(TEXTURE_PODZOL_DEFAULT));
        spriteSnow = textureMap.a(new nf(TEXTURE_SNOW_DEFAULT));
        spritesLoaded = true;
        String name = "optifine/bettergrass.properties";
        try {
            nf locFile = new nf(name);
            if (!Config.hasResource(locFile)) {
                return;
            }
            InputStream in = Config.getResourceStream(locFile);
            if (in == null) {
                return;
            }
            boolean defaultConfig = Config.isFromDefaultResourcePack(locFile);
            if (defaultConfig) {
                Config.dbg("BetterGrass: Parsing default configuration " + name);
            } else {
                Config.dbg("BetterGrass: Parsing configuration " + name);
            }
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            betterGrass = BetterGrass.getBoolean(props, "grass", true);
            betterGrassPath = BetterGrass.getBoolean(props, "grass_path", true);
            betterMycelium = BetterGrass.getBoolean(props, "mycelium", true);
            betterPodzol = BetterGrass.getBoolean(props, "podzol", true);
            betterGrassSnow = BetterGrass.getBoolean(props, "grass.snow", true);
            betterMyceliumSnow = BetterGrass.getBoolean(props, "mycelium.snow", true);
            betterPodzolSnow = BetterGrass.getBoolean(props, "podzol.snow", true);
            grassMultilayer = BetterGrass.getBoolean(props, "grass.multilayer", false);
            spriteGrass = BetterGrass.registerSprite(props, "texture.grass", TEXTURE_GRASS_DEFAULT, textureMap);
            spriteGrassSide = BetterGrass.registerSprite(props, "texture.grass_side", TEXTURE_GRASS_SIDE_DEFAULT, textureMap);
            spriteGrassPath = BetterGrass.registerSprite(props, "texture.grass_path", TEXTURE_GRASS_PATH_DEFAULT, textureMap);
            spriteGrassPathSide = BetterGrass.registerSprite(props, "texture.grass_path_side", TEXTURE_GRASS_PATH_SIDE_DEFAULT, textureMap);
            spriteMycelium = BetterGrass.registerSprite(props, "texture.mycelium", TEXTURE_MYCELIUM_DEFAULT, textureMap);
            spritePodzol = BetterGrass.registerSprite(props, "texture.podzol", TEXTURE_PODZOL_DEFAULT, textureMap);
            spriteSnow = BetterGrass.registerSprite(props, "texture.snow", TEXTURE_SNOW_DEFAULT, textureMap);
        }
        catch (IOException e) {
            Config.warn("Error reading: " + name + ", " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static cdq registerSprite(Properties props, String key, String textureDefault, cdp textureMap) {
        nf locPng;
        String texture = props.getProperty(key);
        if (texture == null) {
            texture = textureDefault;
        }
        if (!Config.hasResource(locPng = new nf("textures/" + texture + ".png"))) {
            Config.warn("BetterGrass texture not found: " + locPng);
            texture = textureDefault;
        }
        nf locSprite = new nf(texture);
        cdq sprite = textureMap.a(locSprite);
        return sprite;
    }

    public static List getFaceQuads(amy blockAccess, awt blockState, et blockPos, fa facing, List quads) {
        if (facing == fa.b || facing == fa.a) {
            return quads;
        }
        if (!modelsLoaded) {
            return quads;
        }
        aow block = blockState.u();
        if (block instanceof asc) {
            return BetterGrass.getFaceQuadsMycelium(blockAccess, blockState, blockPos, facing, quads);
        }
        if (block instanceof arc) {
            return BetterGrass.getFaceQuadsGrassPath(blockAccess, blockState, blockPos, facing, quads);
        }
        if (block instanceof apy) {
            return BetterGrass.getFaceQuadsDirt(blockAccess, blockState, blockPos, facing, quads);
        }
        if (block instanceof arb) {
            return BetterGrass.getFaceQuadsGrass(blockAccess, blockState, blockPos, facing, quads);
        }
        return quads;
    }

    private static List getFaceQuadsMycelium(amy blockAccess, awt blockState, et blockPos, fa facing, List quads) {
        boolean snowy;
        aow blockUp = blockAccess.o(blockPos.a()).u();
        boolean bl = snowy = blockUp == aox.aJ || blockUp == aox.aH;
        if (Config.isBetterGrassFancy()) {
            if (snowy) {
                if (betterMyceliumSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == aox.aH) {
                    return modelCubeSnow.a(blockState, facing, 0L);
                }
            } else if (betterMycelium && BetterGrass.getBlockAt(blockPos.b(), facing, blockAccess) == aox.bw) {
                return modelCubeMycelium.a(blockState, facing, 0L);
            }
        } else if (snowy) {
            if (betterMyceliumSnow) {
                return modelCubeSnow.a(blockState, facing, 0L);
            }
        } else if (betterMycelium) {
            return modelCubeMycelium.a(blockState, facing, 0L);
        }
        return quads;
    }

    private static List getFaceQuadsGrassPath(amy blockAccess, awt blockState, et blockPos, fa facing, List quads) {
        if (!betterGrassPath) {
            return quads;
        }
        if (Config.isBetterGrassFancy()) {
            if (BetterGrass.getBlockAt(blockPos.b(), facing, blockAccess) == aox.da) {
                return modelGrassPath.a(blockState, facing, 0L);
            }
        } else {
            return modelGrassPath.a(blockState, facing, 0L);
        }
        return quads;
    }

    private static List getFaceQuadsDirt(amy blockAccess, awt blockState, et blockPos, fa facing, List quads) {
        aow blockTop = BetterGrass.getBlockAt(blockPos, fa.b, blockAccess);
        if (blockState.c((axj)apy.a) == apy.a.c) {
            boolean snowy;
            boolean bl = snowy = blockTop == aox.aJ || blockTop == aox.aH;
            if (Config.isBetterGrassFancy()) {
                et posSide;
                awt stateSide;
                if (snowy) {
                    if (betterPodzolSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == aox.aH) {
                        return modelCubeSnow.a(blockState, facing, 0L);
                    }
                } else if (betterPodzol && (stateSide = blockAccess.o(posSide = blockPos.b().a(facing))).u() == aox.d && stateSide.c((axj)apy.a) == apy.a.c) {
                    return modelCubePodzol.a(blockState, facing, 0L);
                }
            } else if (snowy) {
                if (betterPodzolSnow) {
                    return modelCubeSnow.a(blockState, facing, 0L);
                }
            } else if (betterPodzol) {
                return modelCubePodzol.a(blockState, facing, 0L);
            }
            return quads;
        }
        if (blockTop == aox.da) {
            if (betterGrassPath && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == aox.da) {
                return modelCubeGrassPath.a(blockState, facing, 0L);
            }
            return quads;
        }
        return quads;
    }

    private static List getFaceQuadsGrass(amy blockAccess, awt blockState, et blockPos, fa facing, List quads) {
        boolean snowy;
        aow blockUp = blockAccess.o(blockPos.a()).u();
        boolean bl = snowy = blockUp == aox.aJ || blockUp == aox.aH;
        if (Config.isBetterGrassFancy()) {
            if (snowy) {
                if (betterGrassSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == aox.aH) {
                    return modelCubeSnow.a(blockState, facing, 0L);
                }
            } else if (betterGrass && BetterGrass.getBlockAt(blockPos.b(), facing, blockAccess) == aox.c) {
                return modelCubeGrass.a(blockState, facing, 0L);
            }
        } else if (snowy) {
            if (betterGrassSnow) {
                return modelCubeSnow.a(blockState, facing, 0L);
            }
        } else if (betterGrass) {
            return modelCubeGrass.a(blockState, facing, 0L);
        }
        return quads;
    }

    private static aow getBlockAt(et blockPos, fa facing, amy blockAccess) {
        et pos = blockPos.a(facing);
        aow block = blockAccess.o(pos).u();
        return block;
    }

    private static boolean getBoolean(Properties props, String key, boolean def) {
        String str = props.getProperty(key);
        if (str == null) {
            return def;
        }
        return Boolean.parseBoolean(str);
    }
}

