/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.expr.ExpressionFloatArrayCached;
import net.optifine.expr.ExpressionFloatCached;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionFloatArray;
import net.optifine.expr.ParseException;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Program;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ScreenShaderOptions;
import net.optifine.shaders.config.ShaderMacro;
import net.optifine.shaders.config.ShaderMacros;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionResolver;
import net.optifine.shaders.config.ShaderOptionRest;
import net.optifine.shaders.config.ShaderOptionScreen;
import net.optifine.shaders.config.ShaderOptionSwitch;
import net.optifine.shaders.config.ShaderOptionSwitchConst;
import net.optifine.shaders.config.ShaderOptionVariable;
import net.optifine.shaders.config.ShaderOptionVariableConst;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.CustomUniform;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderExpressionResolver;
import net.optifine.shaders.uniform.UniformType;
import net.optifine.util.StrUtils;

public class ShaderPackParser {
    private static final Pattern PATTERN_VERSION = Pattern.compile("^\\s*#version\\s+.*$");
    private static final Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
    private static final Set<String> setConstNames = ShaderPackParser.makeSetConstNames();
    private static final Map<String, Integer> mapAlphaFuncs = ShaderPackParser.makeMapAlphaFuncs();
    private static final Map<String, Integer> mapBlendFactors = ShaderPackParser.makeMapBlendFactors();

    public static ShaderOption[] parseShaderPackOptions(IShaderPack shaderPack, String[] programNames, List<Integer> listDimensions) {
        if (shaderPack == null) {
            return new ShaderOption[0];
        }
        HashMap<String, ShaderOption> mapOptions = new HashMap<String, ShaderOption>();
        ShaderPackParser.collectShaderOptions(shaderPack, "/shaders", programNames, mapOptions);
        for (int dimId : listDimensions) {
            String dirWorld = "/shaders/world" + dimId;
            ShaderPackParser.collectShaderOptions(shaderPack, dirWorld, programNames, mapOptions);
        }
        Collection options = mapOptions.values();
        ShaderOption[] sos = options.toArray(new ShaderOption[options.size()]);
        Comparator<ShaderOption> comp = new Comparator<ShaderOption>(){

            @Override
            public int compare(ShaderOption o1, ShaderOption o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        Arrays.sort(sos, comp);
        return sos;
    }

    private static void collectShaderOptions(IShaderPack shaderPack, String dir, String[] programNames, Map<String, ShaderOption> mapOptions) {
        for (int i = 0; i < programNames.length; ++i) {
            String programName = programNames[i];
            if (programName.equals("")) continue;
            String vsh = dir + "/" + programName + ".vsh";
            String fsh = dir + "/" + programName + ".fsh";
            ShaderPackParser.collectShaderOptions(shaderPack, vsh, mapOptions);
            ShaderPackParser.collectShaderOptions(shaderPack, fsh, mapOptions);
        }
    }

    private static void collectShaderOptions(IShaderPack sp, String path, Map<String, ShaderOption> mapOptions) {
        String[] lines = ShaderPackParser.getLines(sp, path);
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            ShaderOption so = ShaderPackParser.getShaderOption(line, path);
            if (so == null || so.getName().startsWith(ShaderMacros.getPrefixMacro()) || so.checkUsed() && !ShaderPackParser.isOptionUsed(so, lines)) continue;
            String key = so.getName();
            ShaderOption so2 = mapOptions.get(key);
            if (so2 != null) {
                if (!Config.equals(so2.getValueDefault(), so.getValueDefault())) {
                    Config.warn("Ambiguous shader option: " + so.getName());
                    Config.warn(" - in " + Config.arrayToString(so2.getPaths()) + ": " + so2.getValueDefault());
                    Config.warn(" - in " + Config.arrayToString(so.getPaths()) + ": " + so.getValueDefault());
                    so2.setEnabled(false);
                }
                if (so2.getDescription() == null || so2.getDescription().length() <= 0) {
                    so2.setDescription(so.getDescription());
                }
                so2.addPaths(so.getPaths());
                continue;
            }
            mapOptions.put(key, so);
        }
    }

    private static boolean isOptionUsed(ShaderOption so, String[] lines) {
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            if (!so.isUsedInLine(line)) continue;
            return true;
        }
        return false;
    }

    private static String[] getLines(IShaderPack sp, String path) {
        try {
            ArrayList<String> listFiles = new ArrayList<String>();
            String str = ShaderPackParser.loadFile(path, sp, 0, listFiles, 0);
            if (str == null) {
                return new String[0];
            }
            ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());
            String[] lines = Config.readLines(is);
            return lines;
        }
        catch (IOException e) {
            Config.dbg(e.getClass().getName() + ": " + e.getMessage());
            return new String[0];
        }
    }

    private static ShaderOption getShaderOption(String line, String path) {
        ShaderOption so = null;
        if (so == null) {
            so = ShaderOptionSwitch.parseOption(line, path);
        }
        if (so == null) {
            so = ShaderOptionVariable.parseOption(line, path);
        }
        if (so != null) {
            return so;
        }
        if (so == null) {
            so = ShaderOptionSwitchConst.parseOption(line, path);
        }
        if (so == null) {
            so = ShaderOptionVariableConst.parseOption(line, path);
        }
        if (so != null && setConstNames.contains(so.getName())) {
            return so;
        }
        return null;
    }

    private static Set<String> makeSetConstNames() {
        HashSet<String> set = new HashSet<String>();
        set.add("shadowMapResolution");
        set.add("shadowMapFov");
        set.add("shadowDistance");
        set.add("shadowDistanceRenderMul");
        set.add("shadowIntervalSize");
        set.add("generateShadowMipmap");
        set.add("generateShadowColorMipmap");
        set.add("shadowHardwareFiltering");
        set.add("shadowHardwareFiltering0");
        set.add("shadowHardwareFiltering1");
        set.add("shadowtex0Mipmap");
        set.add("shadowtexMipmap");
        set.add("shadowtex1Mipmap");
        set.add("shadowcolor0Mipmap");
        set.add("shadowColor0Mipmap");
        set.add("shadowcolor1Mipmap");
        set.add("shadowColor1Mipmap");
        set.add("shadowtex0Nearest");
        set.add("shadowtexNearest");
        set.add("shadow0MinMagNearest");
        set.add("shadowtex1Nearest");
        set.add("shadow1MinMagNearest");
        set.add("shadowcolor0Nearest");
        set.add("shadowColor0Nearest");
        set.add("shadowColor0MinMagNearest");
        set.add("shadowcolor1Nearest");
        set.add("shadowColor1Nearest");
        set.add("shadowColor1MinMagNearest");
        set.add("wetnessHalflife");
        set.add("drynessHalflife");
        set.add("eyeBrightnessHalflife");
        set.add("centerDepthHalflife");
        set.add("sunPathRotation");
        set.add("ambientOcclusionLevel");
        set.add("superSamplingLevel");
        set.add("noiseTextureResolution");
        return set;
    }

    public static ShaderProfile[] parseProfiles(Properties props, ShaderOption[] shaderOptions) {
        String PREFIX_PROFILE = "profile.";
        ArrayList<ShaderProfile> list = new ArrayList<ShaderProfile>();
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            if (!string.startsWith(PREFIX_PROFILE)) continue;
            String name = string.substring(PREFIX_PROFILE.length());
            String val = props.getProperty(string);
            HashSet<String> parsedProfiles = new HashSet<String>();
            ShaderProfile p = ShaderPackParser.parseProfile(name, props, parsedProfiles, shaderOptions);
            if (p == null) continue;
            list.add(p);
        }
        if (list.size() <= 0) {
            return null;
        }
        ShaderProfile[] profs = list.toArray(new ShaderProfile[list.size()]);
        return profs;
    }

    public static Map<String, IExpressionBool> parseProgramConditions(Properties props, ShaderOption[] shaderOptions) {
        String PREFIX_PROGRAM = "program.";
        Pattern pattern = Pattern.compile("program\\.([^.]+)\\.enabled");
        HashMap<String, IExpressionBool> map = new HashMap<String, IExpressionBool>();
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            Matcher matcher = pattern.matcher(string);
            if (!matcher.matches()) continue;
            String name = matcher.group(1);
            String val = props.getProperty(string).trim();
            IExpressionBool expr = ShaderPackParser.parseOptionExpression(val, shaderOptions);
            if (expr == null) {
                SMCLog.severe("Error parsing program condition: " + string);
                continue;
            }
            map.put(name, expr);
        }
        return map;
    }

    private static IExpressionBool parseOptionExpression(String val, ShaderOption[] shaderOptions) {
        try {
            ShaderOptionResolver sor = new ShaderOptionResolver(shaderOptions);
            ExpressionParser parser = new ExpressionParser(sor);
            IExpressionBool expr = parser.parseBool(val);
            return expr;
        }
        catch (ParseException e) {
            SMCLog.warning(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static Set<String> parseOptionSliders(Properties props, ShaderOption[] shaderOptions) {
        HashSet<String> sliders = new HashSet<String>();
        String value = props.getProperty("sliders");
        if (value == null) {
            return sliders;
        }
        String[] names = Config.tokenize(value, " ");
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            ShaderOption so = ShaderUtils.getShaderOption(name, shaderOptions);
            if (so == null) {
                Config.warn("Invalid shader option: " + name);
                continue;
            }
            sliders.add(name);
        }
        return sliders;
    }

    private static ShaderProfile parseProfile(String name, Properties props, Set<String> parsedProfiles, ShaderOption[] shaderOptions) {
        String PREFIX_PROFILE = "profile.";
        String key = PREFIX_PROFILE + name;
        if (parsedProfiles.contains(key)) {
            Config.warn("[Shaders] Profile already parsed: " + name);
            return null;
        }
        parsedProfiles.add(name);
        ShaderProfile prof = new ShaderProfile(name);
        String val = props.getProperty(key);
        String[] parts = Config.tokenize(val, " ");
        for (int i = 0; i < parts.length; ++i) {
            String option;
            String part = parts[i];
            if (part.startsWith(PREFIX_PROFILE)) {
                String nameParent = part.substring(PREFIX_PROFILE.length());
                ShaderProfile profParent = ShaderPackParser.parseProfile(nameParent, props, parsedProfiles, shaderOptions);
                if (prof == null) continue;
                prof.addOptionValues(profParent);
                prof.addDisabledPrograms(profParent.getDisabledPrograms());
                continue;
            }
            String[] tokens = Config.tokenize(part, ":=");
            if (tokens.length == 1) {
                String PREFIX_PROGRAM;
                option = tokens[0];
                boolean on = true;
                if (option.startsWith("!")) {
                    on = false;
                    option = option.substring(1);
                }
                if (option.startsWith(PREFIX_PROGRAM = "program.")) {
                    String program = option.substring(PREFIX_PROGRAM.length());
                    if (!Shaders.isProgramPath(program)) {
                        Config.warn("Invalid program: " + program + " in profile: " + prof.getName());
                        continue;
                    }
                    if (on) {
                        prof.removeDisabledProgram(program);
                        continue;
                    }
                    prof.addDisabledProgram(program);
                    continue;
                }
                ShaderOption so = ShaderUtils.getShaderOption(option, shaderOptions);
                if (!(so instanceof ShaderOptionSwitch)) {
                    Config.warn("[Shaders] Invalid option: " + option);
                    continue;
                }
                prof.addOptionValue(option, String.valueOf(on));
                so.setVisible(true);
                continue;
            }
            if (tokens.length != 2) {
                Config.warn("[Shaders] Invalid option value: " + part);
                continue;
            }
            option = tokens[0];
            String value = tokens[1];
            ShaderOption so = ShaderUtils.getShaderOption(option, shaderOptions);
            if (so == null) {
                Config.warn("[Shaders] Invalid option: " + part);
                continue;
            }
            if (!so.isValidValue(value)) {
                Config.warn("[Shaders] Invalid value: " + part);
                continue;
            }
            so.setVisible(true);
            prof.addOptionValue(option, value);
        }
        return prof;
    }

    public static Map<String, ScreenShaderOptions> parseGuiScreens(Properties props, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
        HashMap<String, ScreenShaderOptions> map = new HashMap<String, ScreenShaderOptions>();
        ShaderPackParser.parseGuiScreen("screen", props, map, shaderProfiles, shaderOptions);
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    private static boolean parseGuiScreen(String key, Properties props, Map<String, ScreenShaderOptions> map, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
        String val = props.getProperty(key);
        if (val == null) {
            return false;
        }
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        HashSet<String> setNames = new HashSet<String>();
        String[] opNames = Config.tokenize(val, " ");
        for (int i = 0; i < opNames.length; ++i) {
            String opName = opNames[i];
            if (opName.equals("<empty>")) {
                list.add(null);
                continue;
            }
            if (setNames.contains(opName)) {
                Config.warn("[Shaders] Duplicate option: " + opName + ", key: " + key);
                continue;
            }
            setNames.add(opName);
            if (opName.equals("<profile>")) {
                if (shaderProfiles == null) {
                    Config.warn("[Shaders] Option profile can not be used, no profiles defined: " + opName + ", key: " + key);
                    continue;
                }
                ShaderOptionProfile optionProfile = new ShaderOptionProfile(shaderProfiles, shaderOptions);
                list.add(optionProfile);
                continue;
            }
            if (opName.equals("*")) {
                ShaderOptionRest soRest = new ShaderOptionRest("<rest>");
                list.add(soRest);
                continue;
            }
            if (opName.startsWith("[") && opName.endsWith("]")) {
                String screen = StrUtils.removePrefixSuffix(opName, "[", "]");
                if (!screen.matches("^[a-zA-Z0-9_]+$")) {
                    Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
                    continue;
                }
                if (!ShaderPackParser.parseGuiScreen("screen." + screen, props, map, shaderProfiles, shaderOptions)) {
                    Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
                    continue;
                }
                ShaderOptionScreen optionScreen = new ShaderOptionScreen(screen);
                list.add(optionScreen);
                continue;
            }
            ShaderOption so = ShaderUtils.getShaderOption(opName, shaderOptions);
            if (so == null) {
                Config.warn("[Shaders] Invalid option: " + opName + ", key: " + key);
                list.add(null);
                continue;
            }
            so.setVisible(true);
            list.add(so);
        }
        ShaderOption[] scrOps = list.toArray(new ShaderOption[list.size()]);
        String colStr = props.getProperty(key + ".columns");
        int columns = Config.parseInt(colStr, 2);
        ScreenShaderOptions sso = new ScreenShaderOptions(key, scrOps, columns);
        map.put(key, sso);
        return true;
    }

    public static BufferedReader resolveIncludes(BufferedReader reader, String filePath, IShaderPack shaderPack, int fileIndex, List<String> listFiles, int includeLevel) throws IOException {
        String line;
        String fileDir = "/";
        int pos = filePath.lastIndexOf("/");
        if (pos >= 0) {
            fileDir = filePath.substring(0, pos);
        }
        CharArrayWriter caw2 = new CharArrayWriter();
        int macroInsertPosition = -1;
        LinkedHashSet<ShaderMacro> setCustomMacros = new LinkedHashSet<ShaderMacro>();
        int lineNumber = 1;
        while ((line = reader.readLine()) != null) {
            Matcher mi;
            Matcher mv;
            if (macroInsertPosition < 0 && (mv = PATTERN_VERSION.matcher(line)).matches()) {
                String strDef = ShaderMacros.getFixedMacroLines() + ShaderMacros.getOptionMacroLines();
                String lineA = line + "\n" + strDef;
                String lineB = "#line " + (lineNumber + 1) + " " + fileIndex;
                line = lineA + lineB;
                macroInsertPosition = caw2.size() + lineA.length();
            }
            if ((mi = PATTERN_INCLUDE.matcher(line)).matches()) {
                int includeFileIndex;
                String filePathInc;
                String fileInc = mi.group(1);
                boolean absolute = fileInc.startsWith("/");
                String string = filePathInc = absolute ? "/shaders" + fileInc : fileDir + "/" + fileInc;
                if (!listFiles.contains(filePathInc)) {
                    listFiles.add(filePathInc);
                }
                if ((line = ShaderPackParser.loadFile(filePathInc, shaderPack, includeFileIndex = listFiles.indexOf(filePathInc) + 1, listFiles, includeLevel)) == null) {
                    throw new IOException("Included file not found: " + filePath);
                }
                if (line.endsWith("\n")) {
                    line = line.substring(0, line.length() - 1);
                }
                String lineIncludeStart = "#line 1 " + includeFileIndex + "\n";
                if (line.startsWith("#version ")) {
                    lineIncludeStart = "";
                }
                line = lineIncludeStart + line + "\n#line " + (lineNumber + 1) + " " + fileIndex;
            }
            if (macroInsertPosition >= 0 && line.contains(ShaderMacros.getPrefixMacro())) {
                ShaderMacro[] lineExts = ShaderPackParser.findMacros(line, ShaderMacros.getExtensions());
                for (int i = 0; i < lineExts.length; ++i) {
                    ShaderMacro ext = lineExts[i];
                    setCustomMacros.add(ext);
                }
            }
            caw2.write(line);
            caw2.write("\n");
            ++lineNumber;
        }
        char[] chars = caw2.toCharArray();
        if (macroInsertPosition >= 0 && setCustomMacros.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (ShaderMacro macro : setCustomMacros) {
                sb.append("#define ");
                sb.append(macro.getName());
                sb.append(" ");
                sb.append(macro.getValue());
                sb.append("\n");
            }
            String strCustom = sb.toString();
            StringBuilder sbAll = new StringBuilder(new String(chars));
            sbAll.insert(macroInsertPosition, strCustom);
            String strAll = sbAll.toString();
            chars = strAll.toCharArray();
        }
        CharArrayReader car2 = new CharArrayReader(chars);
        return new BufferedReader(car2);
    }

    private static ShaderMacro[] findMacros(String line, ShaderMacro[] macros) {
        ArrayList<ShaderMacro> list = new ArrayList<ShaderMacro>();
        for (int i = 0; i < macros.length; ++i) {
            ShaderMacro ext = macros[i];
            if (!line.contains(ext.getName())) continue;
            list.add(ext);
        }
        ShaderMacro[] exts = list.toArray(new ShaderMacro[list.size()]);
        return exts;
    }

    private static String loadFile(String filePath, IShaderPack shaderPack, int fileIndex, List<String> listFiles, int includeLevel) throws IOException {
        String line;
        if (includeLevel >= 10) {
            throw new IOException("#include depth exceeded: " + includeLevel + ", file: " + filePath);
        }
        ++includeLevel;
        InputStream in = shaderPack.getResourceAsStream(filePath);
        if (in == null) {
            return null;
        }
        InputStreamReader isr = new InputStreamReader(in, "ASCII");
        BufferedReader br = new BufferedReader(isr);
        br = ShaderPackParser.resolveIncludes(br, filePath, shaderPack, fileIndex, listFiles, includeLevel);
        CharArrayWriter caw2 = new CharArrayWriter();
        while ((line = br.readLine()) != null) {
            caw2.write(line);
            caw2.write("\n");
        }
        return caw2.toString();
    }

    public static CustomUniforms parseCustomUniforms(Properties props) {
        String UNIFORM = "uniform";
        String VARIABLE = "variable";
        String PREFIX_UNIFORM = UNIFORM + ".";
        String PREFIX_VARIABLE = VARIABLE + ".";
        HashMap<String, IExpression> mapExpressions = new HashMap<String, IExpression>();
        ArrayList<CustomUniform> listUniforms = new ArrayList<CustomUniform>();
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String[] keyParts = Config.tokenize(string, ".");
            if (keyParts.length != 3) continue;
            String kind = keyParts[0];
            String type = keyParts[1];
            String name = keyParts[2];
            String src = props.getProperty(string).trim();
            if (mapExpressions.containsKey(name)) {
                SMCLog.warning("Expression already defined: " + name);
                continue;
            }
            if (!kind.equals(UNIFORM) && !kind.equals(VARIABLE)) continue;
            SMCLog.info("Custom " + kind + ": " + name);
            CustomUniform cu = ShaderPackParser.parseCustomUniform(kind, name, type, src, mapExpressions);
            if (cu == null) continue;
            mapExpressions.put(name, cu.getExpression());
            if (kind.equals(VARIABLE)) continue;
            listUniforms.add(cu);
        }
        if (listUniforms.size() <= 0) {
            return null;
        }
        CustomUniform[] cusArr = listUniforms.toArray(new CustomUniform[listUniforms.size()]);
        CustomUniforms customUniforms = new CustomUniforms(cusArr, mapExpressions);
        return customUniforms;
    }

    private static CustomUniform parseCustomUniform(String kind, String name, String type, String src, Map<String, IExpression> mapExpressions) {
        try {
            UniformType uniformType = UniformType.parse(type);
            if (uniformType == null) {
                SMCLog.warning("Unknown " + kind + " type: " + (Object)((Object)uniformType));
                return null;
            }
            ShaderExpressionResolver resolver = new ShaderExpressionResolver(mapExpressions);
            ExpressionParser parser = new ExpressionParser(resolver);
            IExpression expr = parser.parse(src);
            ExpressionType expressionType = expr.getExpressionType();
            if (!uniformType.matchesExpressionType(expressionType)) {
                SMCLog.warning("Expression type does not match " + kind + " type, expression: " + (Object)((Object)expressionType) + ", " + kind + ": " + (Object)((Object)uniformType) + " " + name);
                return null;
            }
            expr = ShaderPackParser.makeExpressionCached(expr);
            CustomUniform cu = new CustomUniform(name, uniformType, expr);
            return cu;
        }
        catch (ParseException e) {
            SMCLog.warning(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private static IExpression makeExpressionCached(IExpression expr) {
        if (expr instanceof IExpressionFloat) {
            return new ExpressionFloatCached((IExpressionFloat)expr);
        }
        if (expr instanceof IExpressionFloatArray) {
            return new ExpressionFloatArrayCached((IExpressionFloatArray)expr);
        }
        return expr;
    }

    public static void parseAlphaStates(Properties props) {
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String[] keyParts = Config.tokenize(string, ".");
            if (keyParts.length != 2) continue;
            String type = keyParts[0];
            String programName = keyParts[1];
            if (!type.equals("alphaTest")) continue;
            Program program = Shaders.getProgram(programName);
            if (program == null) {
                SMCLog.severe("Invalid program name: " + programName);
                continue;
            }
            String val = props.getProperty(string).trim();
            GlAlphaState state = ShaderPackParser.parseAlphaState(val);
            if (state == null) continue;
            program.setAlphaState(state);
        }
    }

    private static GlAlphaState parseAlphaState(String str) {
        String[] parts = Config.tokenize(str, " ");
        if (parts.length == 1) {
            String str0 = parts[0];
            if (str0.equals("off") || str0.equals("false")) {
                return new GlAlphaState(false);
            }
        } else if (parts.length == 2) {
            String str0 = parts[0];
            String str1 = parts[1];
            Integer func = mapAlphaFuncs.get(str0);
            float ref = Config.parseFloat(str1, -1.0f);
            if (func != null && ref >= 0.0f) {
                return new GlAlphaState(true, func, ref);
            }
        }
        SMCLog.severe("Invalid alpha test: " + str);
        return null;
    }

    public static void parseBlendStates(Properties props) {
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String[] keyParts = Config.tokenize(string, ".");
            if (keyParts.length != 2) continue;
            String type = keyParts[0];
            String programName = keyParts[1];
            if (!type.equals("blend")) continue;
            Program program = Shaders.getProgram(programName);
            if (program == null) {
                SMCLog.severe("Invalid program name: " + programName);
                continue;
            }
            String val = props.getProperty(string).trim();
            GlBlendState state = ShaderPackParser.parseBlendState(val);
            if (state == null) continue;
            program.setBlendState(state);
        }
    }

    private static GlBlendState parseBlendState(String str) {
        String[] parts = Config.tokenize(str, " ");
        if (parts.length == 1) {
            String str0 = parts[0];
            if (str0.equals("off") || str0.equals("false")) {
                return new GlBlendState(false);
            }
        } else if (parts.length == 2 || parts.length == 4) {
            String str0 = parts[0];
            String str1 = parts[1];
            String str2 = str0;
            String str3 = str1;
            if (parts.length == 4) {
                str2 = parts[2];
                str3 = parts[3];
            }
            Integer src = mapBlendFactors.get(str0);
            Integer dst = mapBlendFactors.get(str1);
            Integer srcAlpha = mapBlendFactors.get(str2);
            Integer dstAlpha = mapBlendFactors.get(str3);
            if (src != null && dst != null && srcAlpha != null && dstAlpha != null) {
                return new GlBlendState(true, src, dst, srcAlpha, dstAlpha);
            }
        }
        SMCLog.severe("Invalid blend mode: " + str);
        return null;
    }

    public static void parseRenderScales(Properties props) {
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String[] keyParts = Config.tokenize(string, ".");
            if (keyParts.length != 2) continue;
            String type = keyParts[0];
            String programName = keyParts[1];
            if (!type.equals("scale")) continue;
            Program program = Shaders.getProgram(programName);
            if (program == null) {
                SMCLog.severe("Invalid program name: " + programName);
                continue;
            }
            String val = props.getProperty(string).trim();
            RenderScale scale = ShaderPackParser.parseRenderScale(val);
            if (scale == null) continue;
            program.setRenderScale(scale);
        }
    }

    private static RenderScale parseRenderScale(String str) {
        String[] parts = Config.tokenize(str, " ");
        float scale = Config.parseFloat(parts[0], -1.0f);
        float offsetX = 0.0f;
        float offsetY = 0.0f;
        if (parts.length > 1) {
            if (parts.length != 3) {
                SMCLog.severe("Invalid render scale: " + str);
                return null;
            }
            offsetX = Config.parseFloat(parts[1], -1.0f);
            offsetY = Config.parseFloat(parts[2], -1.0f);
        }
        if (!(Config.between(scale, 0.0f, 1.0f) && Config.between(offsetX, 0.0f, 1.0f) && Config.between(offsetY, 0.0f, 1.0f))) {
            SMCLog.severe("Invalid render scale: " + str);
            return null;
        }
        return new RenderScale(scale, offsetX, offsetY);
    }

    public static void parseBuffersFlip(Properties props) {
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String[] keyParts = Config.tokenize(string, ".");
            if (keyParts.length != 3) continue;
            String type = keyParts[0];
            String programName = keyParts[1];
            String bufferName = keyParts[2];
            if (!type.equals("flip")) continue;
            Program program = Shaders.getProgram(programName);
            if (program == null) {
                SMCLog.severe("Invalid program name: " + programName);
                continue;
            }
            Boolean[] buffersFlip = program.getBuffersFlip();
            int buffer = Shaders.getBufferIndexFromString(bufferName);
            if (buffer < 0 || buffer >= buffersFlip.length) {
                SMCLog.severe("Invalid buffer name: " + bufferName);
                continue;
            }
            String valStr = props.getProperty(string).trim();
            Boolean val = Config.parseBoolean(valStr, null);
            if (val == null) {
                SMCLog.severe("Invalid boolean value: " + valStr);
                continue;
            }
            buffersFlip[buffer] = val;
        }
    }

    private static Map<String, Integer> makeMapAlphaFuncs() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("NEVER", new Integer(512));
        map.put("LESS", new Integer(513));
        map.put("EQUAL", new Integer(514));
        map.put("LEQUAL", new Integer(515));
        map.put("GREATER", new Integer(516));
        map.put("NOTEQUAL", new Integer(517));
        map.put("GEQUAL", new Integer(518));
        map.put("ALWAYS", new Integer(519));
        return Collections.unmodifiableMap(map);
    }

    private static Map<String, Integer> makeMapBlendFactors() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ZERO", new Integer(0));
        map.put("ONE", new Integer(1));
        map.put("SRC_COLOR", new Integer(768));
        map.put("ONE_MINUS_SRC_COLOR", new Integer(769));
        map.put("DST_COLOR", new Integer(774));
        map.put("ONE_MINUS_DST_COLOR", new Integer(775));
        map.put("SRC_ALPHA", new Integer(770));
        map.put("ONE_MINUS_SRC_ALPHA", new Integer(771));
        map.put("DST_ALPHA", new Integer(772));
        map.put("ONE_MINUS_DST_ALPHA", new Integer(773));
        map.put("CONSTANT_COLOR", new Integer(32769));
        map.put("ONE_MINUS_CONSTANT_COLOR", new Integer(32770));
        map.put("CONSTANT_ALPHA", new Integer(32771));
        map.put("ONE_MINUS_CONSTANT_ALPHA", new Integer(32772));
        map.put("SRC_ALPHA_SATURATE", new Integer(776));
        return Collections.unmodifiableMap(map);
    }
}

