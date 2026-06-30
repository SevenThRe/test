/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amm
 *  anh
 *  aox
 *  awt
 *  axj
 *  bib
 *  cdp
 *  cdq
 *  nf
 */
package net.optifine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import net.optifine.ConnectedTextures;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.util.MathUtils;
import net.optifine.util.TextureUtils;

public class ConnectedProperties {
    public String name = null;
    public String basePath = null;
    public MatchBlock[] matchBlocks = null;
    public int[] metadatas = null;
    public String[] matchTiles = null;
    public int method = 0;
    public String[] tiles = null;
    public int connect = 0;
    public int faces = 63;
    public anh[] biomes = null;
    public RangeListInt heights = null;
    public int renderPass = 0;
    public boolean innerSeams = false;
    public int[] ctmTileIndexes = null;
    public int width = 0;
    public int height = 0;
    public int[] weights = null;
    public int randomLoops = 0;
    public int symmetry = 1;
    public boolean linked = false;
    public NbtTagValue nbtName = null;
    public int[] sumWeights = null;
    public int sumAllWeights = 1;
    public cdq[] matchTileIcons = null;
    public cdq[] tileIcons = null;
    public MatchBlock[] connectBlocks = null;
    public String[] connectTiles = null;
    public cdq[] connectTileIcons = null;
    public int tintIndex = -1;
    public awt tintBlockState = aox.a.t();
    public amm layer = null;
    public static final int METHOD_NONE = 0;
    public static final int METHOD_CTM = 1;
    public static final int METHOD_HORIZONTAL = 2;
    public static final int METHOD_TOP = 3;
    public static final int METHOD_RANDOM = 4;
    public static final int METHOD_REPEAT = 5;
    public static final int METHOD_VERTICAL = 6;
    public static final int METHOD_FIXED = 7;
    public static final int METHOD_HORIZONTAL_VERTICAL = 8;
    public static final int METHOD_VERTICAL_HORIZONTAL = 9;
    public static final int METHOD_CTM_COMPACT = 10;
    public static final int METHOD_OVERLAY = 11;
    public static final int METHOD_OVERLAY_FIXED = 12;
    public static final int METHOD_OVERLAY_RANDOM = 13;
    public static final int METHOD_OVERLAY_REPEAT = 14;
    public static final int METHOD_OVERLAY_CTM = 15;
    public static final int CONNECT_NONE = 0;
    public static final int CONNECT_BLOCK = 1;
    public static final int CONNECT_TILE = 2;
    public static final int CONNECT_MATERIAL = 3;
    public static final int CONNECT_UNKNOWN = 128;
    public static final int FACE_BOTTOM = 1;
    public static final int FACE_TOP = 2;
    public static final int FACE_NORTH = 4;
    public static final int FACE_SOUTH = 8;
    public static final int FACE_WEST = 16;
    public static final int FACE_EAST = 32;
    public static final int FACE_SIDES = 60;
    public static final int FACE_ALL = 63;
    public static final int FACE_UNKNOWN = 128;
    public static final int SYMMETRY_NONE = 1;
    public static final int SYMMETRY_OPPOSITE = 2;
    public static final int SYMMETRY_ALL = 6;
    public static final int SYMMETRY_UNKNOWN = 128;
    public static final String TILE_SKIP_PNG = "<skip>.png";
    public static final String TILE_DEFAULT_PNG = "<default>.png";

    public ConnectedProperties(Properties props, String path) {
        ConnectedParser cp = new ConnectedParser("ConnectedTextures");
        this.name = cp.parseName(path);
        this.basePath = cp.parseBasePath(path);
        this.matchBlocks = cp.parseMatchBlocks(props.getProperty("matchBlocks"));
        this.metadatas = cp.parseIntList(props.getProperty("metadata"));
        this.matchTiles = this.parseMatchTiles(props.getProperty("matchTiles"));
        this.method = ConnectedProperties.parseMethod(props.getProperty("method"));
        this.tiles = this.parseTileNames(props.getProperty("tiles"));
        this.connect = ConnectedProperties.parseConnect(props.getProperty("connect"));
        this.faces = ConnectedProperties.parseFaces(props.getProperty("faces"));
        this.biomes = cp.parseBiomes(props.getProperty("biomes"));
        this.heights = cp.parseRangeListInt(props.getProperty("heights"));
        if (this.heights == null) {
            int minHeight = cp.parseInt(props.getProperty("minHeight"), -1);
            int maxHeight = cp.parseInt(props.getProperty("maxHeight"), 1024);
            if (minHeight != -1 || maxHeight != 1024) {
                this.heights = new RangeListInt(new RangeInt(minHeight, maxHeight));
            }
        }
        this.renderPass = cp.parseInt(props.getProperty("renderPass"), -1);
        this.innerSeams = cp.parseBoolean(props.getProperty("innerSeams"), false);
        this.ctmTileIndexes = this.parseCtmTileIndexes(props);
        this.width = cp.parseInt(props.getProperty("width"), -1);
        this.height = cp.parseInt(props.getProperty("height"), -1);
        this.weights = cp.parseIntList(props.getProperty("weights"));
        this.randomLoops = cp.parseInt(props.getProperty("randomLoops"), 0);
        this.symmetry = ConnectedProperties.parseSymmetry(props.getProperty("symmetry"));
        this.linked = cp.parseBoolean(props.getProperty("linked"), false);
        this.nbtName = cp.parseNbtTagValue("name", props.getProperty("name"));
        this.connectBlocks = cp.parseMatchBlocks(props.getProperty("connectBlocks"));
        this.connectTiles = this.parseMatchTiles(props.getProperty("connectTiles"));
        this.tintIndex = cp.parseInt(props.getProperty("tintIndex"), -1);
        this.tintBlockState = cp.parseBlockState(props.getProperty("tintBlock"), aox.a.t());
        this.layer = cp.parseBlockRenderLayer(props.getProperty("layer"), amm.b);
    }

    private int[] parseCtmTileIndexes(Properties props) {
        if (this.tiles == null) {
            return null;
        }
        HashMap<Integer, Integer> mapTileIndexes = new HashMap<Integer, Integer>();
        Set<Object> keys = props.keySet();
        for (Object key : keys) {
            String PREFIX;
            String keyStr;
            if (!(key instanceof String) || !(keyStr = (String)key).startsWith(PREFIX = "ctm.")) continue;
            String ctmIndexStr = keyStr.substring(PREFIX.length());
            String ctmTileIndexStr = props.getProperty(keyStr);
            if (ctmTileIndexStr == null) continue;
            ctmTileIndexStr = ctmTileIndexStr.trim();
            int ctmIndex = Config.parseInt(ctmIndexStr, -1);
            if (ctmIndex < 0 || ctmIndex > 46) {
                Config.warn("Invalid CTM index: " + ctmIndexStr);
                continue;
            }
            int ctmTileIndex = Config.parseInt(ctmTileIndexStr, -1);
            if (ctmTileIndex < 0 || ctmTileIndex >= this.tiles.length) {
                Config.warn("Invalid CTM tile index: " + ctmTileIndexStr);
                continue;
            }
            mapTileIndexes.put(ctmIndex, ctmTileIndex);
        }
        if (mapTileIndexes.isEmpty()) {
            return null;
        }
        int[] tileIndexes = new int[47];
        for (int i = 0; i < tileIndexes.length; ++i) {
            tileIndexes[i] = -1;
            if (!mapTileIndexes.containsKey(i)) continue;
            tileIndexes[i] = (Integer)mapTileIndexes.get(i);
        }
        return tileIndexes;
    }

    private String[] parseMatchTiles(String str) {
        if (str == null) {
            return null;
        }
        String[] names = Config.tokenize(str, " ");
        for (int i = 0; i < names.length; ++i) {
            String iconName = names[i];
            if (iconName.endsWith(".png")) {
                iconName = iconName.substring(0, iconName.length() - 4);
            }
            names[i] = iconName = TextureUtils.fixResourcePath(iconName, this.basePath);
        }
        return names;
    }

    private static String parseName(String path) {
        int pos2;
        String str = path;
        int pos = str.lastIndexOf(47);
        if (pos >= 0) {
            str = str.substring(pos + 1);
        }
        if ((pos2 = str.lastIndexOf(46)) >= 0) {
            str = str.substring(0, pos2);
        }
        return str;
    }

    private static String parseBasePath(String path) {
        int pos = path.lastIndexOf(47);
        if (pos < 0) {
            return "";
        }
        return path.substring(0, pos);
    }

    private String[] parseTileNames(String str) {
        if (str == null) {
            return null;
        }
        ArrayList<String> list = new ArrayList<String>();
        String[] iconStrs = Config.tokenize(str, " ,");
        for (int i = 0; i < iconStrs.length; ++i) {
            String[] subStrs;
            String iconStr = iconStrs[i];
            if (iconStr.contains("-") && (subStrs = Config.tokenize(iconStr, "-")).length == 2) {
                int min = Config.parseInt(subStrs[0], -1);
                int max = Config.parseInt(subStrs[1], -1);
                if (min >= 0 && max >= 0) {
                    if (min > max) {
                        Config.warn("Invalid interval: " + iconStr + ", when parsing: " + str);
                        continue;
                    }
                    for (int n = min; n <= max; ++n) {
                        list.add(String.valueOf(n));
                    }
                    continue;
                }
            }
            list.add(iconStr);
        }
        String[] names = list.toArray(new String[list.size()]);
        for (int i = 0; i < names.length; ++i) {
            String iconName = names[i];
            if (!((iconName = TextureUtils.fixResourcePath(iconName, this.basePath)).startsWith(this.basePath) || iconName.startsWith("textures/") || iconName.startsWith("mcpatcher/"))) {
                iconName = this.basePath + "/" + iconName;
            }
            if (iconName.endsWith(".png")) {
                iconName = iconName.substring(0, iconName.length() - 4);
            }
            if (iconName.startsWith("/")) {
                iconName = iconName.substring(1);
            }
            names[i] = iconName;
        }
        return names;
    }

    private static int parseSymmetry(String str) {
        if (str == null) {
            return 1;
        }
        if ((str = str.trim()).equals("opposite")) {
            return 2;
        }
        if (str.equals("all")) {
            return 6;
        }
        Config.warn("Unknown symmetry: " + str);
        return 1;
    }

    private static int parseFaces(String str) {
        if (str == null) {
            return 63;
        }
        String[] faceStrs = Config.tokenize(str, " ,");
        int facesMask = 0;
        for (int i = 0; i < faceStrs.length; ++i) {
            String faceStr = faceStrs[i];
            int faceMask = ConnectedProperties.parseFace(faceStr);
            facesMask |= faceMask;
        }
        return facesMask;
    }

    private static int parseFace(String str) {
        if ((str = str.toLowerCase()).equals("bottom") || str.equals("down")) {
            return 1;
        }
        if (str.equals("top") || str.equals("up")) {
            return 2;
        }
        if (str.equals("north")) {
            return 4;
        }
        if (str.equals("south")) {
            return 8;
        }
        if (str.equals("east")) {
            return 32;
        }
        if (str.equals("west")) {
            return 16;
        }
        if (str.equals("sides")) {
            return 60;
        }
        if (str.equals("all")) {
            return 63;
        }
        Config.warn("Unknown face: " + str);
        return 128;
    }

    private static int parseConnect(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.trim()).equals("block")) {
            return 1;
        }
        if (str.equals("tile")) {
            return 2;
        }
        if (str.equals("material")) {
            return 3;
        }
        Config.warn("Unknown connect: " + str);
        return 128;
    }

    public static axj getProperty(String key, Collection properties) {
        for (axj prop : properties) {
            if (!key.equals(prop.a())) continue;
            return prop;
        }
        return null;
    }

    private static int parseMethod(String str) {
        if (str == null) {
            return 1;
        }
        if ((str = str.trim()).equals("ctm") || str.equals("glass")) {
            return 1;
        }
        if (str.equals("ctm_compact")) {
            return 10;
        }
        if (str.equals("horizontal") || str.equals("bookshelf")) {
            return 2;
        }
        if (str.equals("vertical")) {
            return 6;
        }
        if (str.equals("top")) {
            return 3;
        }
        if (str.equals("random")) {
            return 4;
        }
        if (str.equals("repeat")) {
            return 5;
        }
        if (str.equals("fixed")) {
            return 7;
        }
        if (str.equals("horizontal+vertical") || str.equals("h+v")) {
            return 8;
        }
        if (str.equals("vertical+horizontal") || str.equals("v+h")) {
            return 9;
        }
        if (str.equals("overlay")) {
            return 11;
        }
        if (str.equals("overlay_fixed")) {
            return 12;
        }
        if (str.equals("overlay_random")) {
            return 13;
        }
        if (str.equals("overlay_repeat")) {
            return 14;
        }
        if (str.equals("overlay_ctm")) {
            return 15;
        }
        Config.warn("Unknown method: " + str);
        return 0;
    }

    public boolean isValid(String path) {
        if (this.name == null || this.name.length() <= 0) {
            Config.warn("No name found: " + path);
            return false;
        }
        if (this.basePath == null) {
            Config.warn("No base path found: " + path);
            return false;
        }
        if (this.matchBlocks == null) {
            this.matchBlocks = this.detectMatchBlocks();
        }
        if (this.matchTiles == null && this.matchBlocks == null) {
            this.matchTiles = this.detectMatchTiles();
        }
        if (this.matchBlocks == null && this.matchTiles == null) {
            Config.warn("No matchBlocks or matchTiles specified: " + path);
            return false;
        }
        if (this.method == 0) {
            Config.warn("No method: " + path);
            return false;
        }
        if (this.tiles == null || this.tiles.length <= 0) {
            Config.warn("No tiles specified: " + path);
            return false;
        }
        if (this.connect == 0) {
            this.connect = this.detectConnect();
        }
        if (this.connect == 128) {
            Config.warn("Invalid connect in: " + path);
            return false;
        }
        if (this.renderPass > 0) {
            Config.warn("Render pass not supported: " + this.renderPass);
            return false;
        }
        if ((this.faces & 0x80) != 0) {
            Config.warn("Invalid faces in: " + path);
            return false;
        }
        if ((this.symmetry & 0x80) != 0) {
            Config.warn("Invalid symmetry in: " + path);
            return false;
        }
        switch (this.method) {
            case 1: {
                return this.isValidCtm(path);
            }
            case 10: {
                return this.isValidCtmCompact(path);
            }
            case 2: {
                return this.isValidHorizontal(path);
            }
            case 6: {
                return this.isValidVertical(path);
            }
            case 3: {
                return this.isValidTop(path);
            }
            case 4: {
                return this.isValidRandom(path);
            }
            case 5: {
                return this.isValidRepeat(path);
            }
            case 7: {
                return this.isValidFixed(path);
            }
            case 8: {
                return this.isValidHorizontalVertical(path);
            }
            case 9: {
                return this.isValidVerticalHorizontal(path);
            }
            case 11: {
                return this.isValidOverlay(path);
            }
            case 12: {
                return this.isValidOverlayFixed(path);
            }
            case 13: {
                return this.isValidOverlayRandom(path);
            }
            case 14: {
                return this.isValidOverlayRepeat(path);
            }
            case 15: {
                return this.isValidOverlayCtm(path);
            }
        }
        Config.warn("Unknown method: " + path);
        return false;
    }

    private int detectConnect() {
        if (this.matchBlocks != null) {
            return 1;
        }
        if (this.matchTiles != null) {
            return 2;
        }
        return 128;
    }

    private MatchBlock[] detectMatchBlocks() {
        int[] ids = this.detectMatchBlockIds();
        if (ids == null) {
            return null;
        }
        MatchBlock[] mbs = new MatchBlock[ids.length];
        for (int i = 0; i < mbs.length; ++i) {
            mbs[i] = new MatchBlock(ids[i]);
        }
        return mbs;
    }

    private int[] detectMatchBlockIds() {
        int startPos;
        char ch;
        int pos;
        if (!this.name.startsWith("block")) {
            return null;
        }
        for (pos = startPos = "block".length(); pos < this.name.length() && (ch = this.name.charAt(pos)) >= '0' && ch <= '9'; ++pos) {
        }
        if (pos == startPos) {
            return null;
        }
        String idStr = this.name.substring(startPos, pos);
        int id = Config.parseInt(idStr, -1);
        if (id < 0) {
            return null;
        }
        return new int[]{id};
    }

    private String[] detectMatchTiles() {
        cdq icon = ConnectedProperties.getIcon(this.name);
        if (icon == null) {
            return null;
        }
        return new String[]{this.name};
    }

    private static cdq getIcon(String iconName) {
        cdp textureMapBlocks = bib.z().R();
        cdq icon = textureMapBlocks.getSpriteSafe(iconName);
        if (icon != null) {
            return icon;
        }
        icon = textureMapBlocks.getSpriteSafe("blocks/" + iconName);
        return icon;
    }

    private boolean isValidCtm(String path) {
        if (this.tiles == null) {
            this.tiles = this.parseTileNames("0-11 16-27 32-43 48-58");
        }
        if (this.tiles.length < 47) {
            Config.warn("Invalid tiles, must be at least 47: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidCtmCompact(String path) {
        if (this.tiles == null) {
            this.tiles = this.parseTileNames("0-4");
        }
        if (this.tiles.length < 5) {
            Config.warn("Invalid tiles, must be at least 5: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidOverlay(String path) {
        if (this.tiles == null) {
            this.tiles = this.parseTileNames("0-16");
        }
        if (this.tiles.length < 17) {
            Config.warn("Invalid tiles, must be at least 17: " + path);
            return false;
        }
        if (this.layer == null || this.layer == amm.a) {
            Config.warn("Invalid overlay layer: " + this.layer);
            return false;
        }
        return true;
    }

    private boolean isValidOverlayFixed(String path) {
        if (!this.isValidFixed(path)) {
            return false;
        }
        if (this.layer == null || this.layer == amm.a) {
            Config.warn("Invalid overlay layer: " + this.layer);
            return false;
        }
        return true;
    }

    private boolean isValidOverlayRandom(String path) {
        if (!this.isValidRandom(path)) {
            return false;
        }
        if (this.layer == null || this.layer == amm.a) {
            Config.warn("Invalid overlay layer: " + this.layer);
            return false;
        }
        return true;
    }

    private boolean isValidOverlayRepeat(String path) {
        if (!this.isValidRepeat(path)) {
            return false;
        }
        if (this.layer == null || this.layer == amm.a) {
            Config.warn("Invalid overlay layer: " + this.layer);
            return false;
        }
        return true;
    }

    private boolean isValidOverlayCtm(String path) {
        if (!this.isValidCtm(path)) {
            return false;
        }
        if (this.layer == null || this.layer == amm.a) {
            Config.warn("Invalid overlay layer: " + this.layer);
            return false;
        }
        return true;
    }

    private boolean isValidHorizontal(String path) {
        if (this.tiles == null) {
            this.tiles = this.parseTileNames("12-15");
        }
        if (this.tiles.length != 4) {
            Config.warn("Invalid tiles, must be exactly 4: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidVertical(String path) {
        if (this.tiles == null) {
            Config.warn("No tiles defined for vertical: " + path);
            return false;
        }
        if (this.tiles.length != 4) {
            Config.warn("Invalid tiles, must be exactly 4: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidHorizontalVertical(String path) {
        if (this.tiles == null) {
            Config.warn("No tiles defined for horizontal+vertical: " + path);
            return false;
        }
        if (this.tiles.length != 7) {
            Config.warn("Invalid tiles, must be exactly 7: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidVerticalHorizontal(String path) {
        if (this.tiles == null) {
            Config.warn("No tiles defined for vertical+horizontal: " + path);
            return false;
        }
        if (this.tiles.length != 7) {
            Config.warn("Invalid tiles, must be exactly 7: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidRandom(String path) {
        if (this.tiles == null || this.tiles.length <= 0) {
            Config.warn("Tiles not defined: " + path);
            return false;
        }
        if (this.weights != null) {
            int[] weights2;
            if (this.weights.length > this.tiles.length) {
                Config.warn("More weights defined than tiles, trimming weights: " + path);
                weights2 = new int[this.tiles.length];
                System.arraycopy(this.weights, 0, weights2, 0, weights2.length);
                this.weights = weights2;
            }
            if (this.weights.length < this.tiles.length) {
                Config.warn("Less weights defined than tiles, expanding weights: " + path);
                weights2 = new int[this.tiles.length];
                System.arraycopy(this.weights, 0, weights2, 0, this.weights.length);
                int avgWeight = MathUtils.getAverage(this.weights);
                for (int i = this.weights.length; i < weights2.length; ++i) {
                    weights2[i] = avgWeight;
                }
                this.weights = weights2;
            }
            this.sumWeights = new int[this.weights.length];
            int sum = 0;
            for (int i = 0; i < this.weights.length; ++i) {
                this.sumWeights[i] = sum += this.weights[i];
            }
            this.sumAllWeights = sum;
            if (this.sumAllWeights <= 0) {
                Config.warn("Invalid sum of all weights: " + sum);
                this.sumAllWeights = 1;
            }
        }
        if (this.randomLoops < 0 || this.randomLoops > 9) {
            Config.warn("Invalid randomLoops: " + this.randomLoops);
            return false;
        }
        return true;
    }

    private boolean isValidRepeat(String path) {
        if (this.tiles == null) {
            Config.warn("Tiles not defined: " + path);
            return false;
        }
        if (this.width <= 0) {
            Config.warn("Invalid width: " + path);
            return false;
        }
        if (this.height <= 0) {
            Config.warn("Invalid height: " + path);
            return false;
        }
        if (this.tiles.length != this.width * this.height) {
            Config.warn("Number of tiles does not equal width x height: " + path);
            return false;
        }
        return true;
    }

    private boolean isValidFixed(String path) {
        if (this.tiles == null) {
            Config.warn("Tiles not defined: " + path);
            return false;
        }
        if (this.tiles.length != 1) {
            Config.warn("Number of tiles should be 1 for method: fixed.");
            return false;
        }
        return true;
    }

    private boolean isValidTop(String path) {
        if (this.tiles == null) {
            this.tiles = this.parseTileNames("66");
        }
        if (this.tiles.length != 1) {
            Config.warn("Invalid tiles, must be exactly 1: " + path);
            return false;
        }
        return true;
    }

    public void updateIcons(cdp textureMap) {
        if (this.matchTiles != null) {
            this.matchTileIcons = ConnectedProperties.registerIcons(this.matchTiles, textureMap, false, false);
        }
        if (this.connectTiles != null) {
            this.connectTileIcons = ConnectedProperties.registerIcons(this.connectTiles, textureMap, false, false);
        }
        if (this.tiles != null) {
            this.tileIcons = ConnectedProperties.registerIcons(this.tiles, textureMap, true, !ConnectedProperties.isMethodOverlay(this.method));
        }
    }

    private static boolean isMethodOverlay(int method) {
        switch (method) {
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                return true;
            }
        }
        return false;
    }

    private static cdq[] registerIcons(String[] tileNames, cdp textureMap, boolean skipTiles, boolean defaultTiles) {
        if (tileNames == null) {
            return null;
        }
        ArrayList<cdq> iconList = new ArrayList<cdq>();
        for (int i = 0; i < tileNames.length; ++i) {
            String prefixTextures;
            String pathSprite;
            String iconName = tileNames[i];
            nf resLoc = new nf(iconName);
            String domain = resLoc.b();
            String path = resLoc.a();
            if (!path.contains("/")) {
                path = "textures/blocks/" + path;
            }
            String filePath = path + ".png";
            if (skipTiles && filePath.endsWith(TILE_SKIP_PNG)) {
                iconList.add(null);
                continue;
            }
            if (defaultTiles && filePath.endsWith(TILE_DEFAULT_PNG)) {
                iconList.add(ConnectedTextures.SPRITE_DEFAULT);
                continue;
            }
            nf locFile = new nf(domain, filePath);
            boolean exists = Config.hasResource(locFile);
            if (!exists) {
                Config.warn("File not found: " + filePath);
            }
            if ((pathSprite = path).startsWith(prefixTextures = "textures/")) {
                pathSprite = pathSprite.substring(prefixTextures.length());
            }
            nf locSprite = new nf(domain, pathSprite);
            cdq icon = textureMap.a(locSprite);
            iconList.add(icon);
        }
        cdq[] icons = iconList.toArray(new cdq[iconList.size()]);
        return icons;
    }

    public boolean matchesBlockId(int blockId) {
        return Matches.blockId(blockId, this.matchBlocks);
    }

    public boolean matchesBlock(int blockId, int metadata) {
        if (!Matches.block(blockId, metadata, this.matchBlocks)) {
            return false;
        }
        return Matches.metadata(metadata, this.metadatas);
    }

    public boolean matchesIcon(cdq icon) {
        return Matches.sprite(icon, this.matchTileIcons);
    }

    public String toString() {
        return "CTM name: " + this.name + ", basePath: " + this.basePath + ", matchBlocks: " + Config.arrayToString(this.matchBlocks) + ", matchTiles: " + Config.arrayToString(this.matchTiles);
    }

    public boolean matchesBiome(anh biome) {
        return Matches.biome(biome, this.biomes);
    }

    public int getMetadataMax() {
        int max = -1;
        max = this.getMax(this.metadatas, max);
        if (this.matchBlocks != null) {
            for (int i = 0; i < this.matchBlocks.length; ++i) {
                MatchBlock mb = this.matchBlocks[i];
                max = this.getMax(mb.getMetadatas(), max);
            }
        }
        return max;
    }

    private int getMax(int[] mds, int max) {
        if (mds == null) {
            return max;
        }
        for (int i = 0; i < mds.length; ++i) {
            int md = mds[i];
            if (md <= max) continue;
            max = md;
        }
        return max;
    }
}

