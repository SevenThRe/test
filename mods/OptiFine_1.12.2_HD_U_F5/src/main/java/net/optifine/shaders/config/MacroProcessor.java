/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.ShaderMacro;
import net.optifine.shaders.config.ShaderMacros;
import net.optifine.shaders.config.ShaderOption;

public class MacroProcessor {
    public static InputStream process(InputStream in, String path) throws IOException {
        String filePath;
        String str = Config.readInputStream(in, "ASCII");
        String strMacroHeader = MacroProcessor.getMacroHeader(str);
        if (!strMacroHeader.isEmpty()) {
            str = strMacroHeader + str;
            if (Shaders.saveFinalShaders) {
                filePath = path.replace(':', '/') + ".pre";
                Shaders.saveShader(filePath, str);
            }
            str = MacroProcessor.process(str);
        }
        if (Shaders.saveFinalShaders) {
            filePath = path.replace(':', '/');
            Shaders.saveShader(filePath, str);
        }
        byte[] bytes = str.getBytes("ASCII");
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return bais;
    }

    public static String process(String strIn) throws IOException {
        String line;
        StringReader sr = new StringReader(strIn);
        BufferedReader br = new BufferedReader(sr);
        MacroState macroState = new MacroState();
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (!macroState.processLine(line) || MacroState.isMacroLine(line)) continue;
            sb.append(line);
            sb.append("\n");
        }
        String strOut = sb.toString();
        return strOut;
    }

    private static String getMacroHeader(String str) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        Object sos = null;
        ArrayList<ShaderMacro> sms = null;
        StringReader sr = new StringReader(str);
        BufferedReader br = new BufferedReader(sr);
        while ((line = br.readLine()) != null) {
            if (!MacroState.isMacroLine(line)) continue;
            if (sb.length() == 0) {
                sb.append(ShaderMacros.getFixedMacroLines());
            }
            if (sms == null) {
                sms = new ArrayList<ShaderMacro>(Arrays.asList(ShaderMacros.getExtensions()));
            }
            Iterator it = sms.iterator();
            while (it.hasNext()) {
                ShaderMacro sm = (ShaderMacro)it.next();
                if (!line.contains(sm.getName())) continue;
                sb.append(sm.getSourceLine());
                sb.append("\n");
                it.remove();
            }
        }
        return sb.toString();
    }

    private static List<ShaderOption> getMacroOptions() {
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        ShaderOption[] sos = Shaders.getShaderPackOptions();
        for (int i = 0; i < sos.length; ++i) {
            ShaderOption so = sos[i];
            String sourceLine = so.getSourceLine();
            if (sourceLine == null || !sourceLine.startsWith("#")) continue;
            list.add(so);
        }
        return list;
    }
}

