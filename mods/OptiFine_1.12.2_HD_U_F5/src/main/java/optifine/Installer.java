/*
 * Decompiled with CFR 0.152.
 */
package optifine;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import optifine.Patcher;
import optifine.ProfileIcon;
import optifine.Utils;
import optifine.json.JSONArray;
import optifine.json.JSONObject;
import optifine.json.JSONParser;
import optifine.json.JSONWriter;
import optifine.json.ParseException;

public class Installer {
    public static void main(String[] args) {
        try {
            File dirMc = Utils.getWorkingDirectory();
            Installer.doInstall(dirMc);
        }
        catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.equals("QUIET")) {
                return;
            }
            e.printStackTrace();
            String str = Utils.getExceptionStackTrace(e);
            str = str.replace("\t", "  ");
            JTextArea textArea = new JTextArea(str);
            textArea.setEditable(false);
            Font f = textArea.getFont();
            Font f2 = new Font("Monospaced", f.getStyle(), f.getSize());
            textArea.setFont(f2);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(null, scrollPane, "Error", 0);
        }
    }

    public static void doInstall(File dirMc) throws Exception {
        Utils.dbg("Dir minecraft: " + dirMc);
        File dirMcLib = new File(dirMc, "libraries");
        Utils.dbg("Dir libraries: " + dirMcLib);
        File dirMcVers = new File(dirMc, "versions");
        Utils.dbg("Dir versions: " + dirMcVers);
        String ofVer = Installer.getOptiFineVersion();
        Utils.dbg("OptiFine Version: " + ofVer);
        String[] ofVers = Utils.tokenize(ofVer, "_");
        String mcVer = ofVers[1];
        Utils.dbg("Minecraft Version: " + mcVer);
        String ofEd = Installer.getOptiFineEdition(ofVers);
        Utils.dbg("OptiFine Edition: " + ofEd);
        String mcVerOf = String.valueOf(mcVer) + "-OptiFine_" + ofEd;
        Utils.dbg("Minecraft_OptiFine Version: " + mcVerOf);
        Installer.copyMinecraftVersion(mcVer, mcVerOf, dirMcVers);
        Installer.installOptiFineLibrary(mcVer, ofEd, dirMcLib, false);
        Installer.installLaunchwrapperLibrary(mcVer, ofEd, dirMcLib);
        Installer.updateJson(dirMcVers, mcVerOf, dirMcLib, mcVer, ofEd);
        Installer.updateLauncherJson(dirMc, mcVerOf);
    }

    public static boolean doExtract(File dirMc) throws Exception {
        Utils.dbg("Dir minecraft: " + dirMc);
        File dirMcLib = new File(dirMc, "libraries");
        Utils.dbg("Dir libraries: " + dirMcLib);
        File dirMcVers = new File(dirMc, "versions");
        Utils.dbg("Dir versions: " + dirMcVers);
        String ofVer = Installer.getOptiFineVersion();
        Utils.dbg("OptiFine Version: " + ofVer);
        String[] ofVers = Utils.tokenize(ofVer, "_");
        String mcVer = ofVers[1];
        Utils.dbg("Minecraft Version: " + mcVer);
        String ofEd = Installer.getOptiFineEdition(ofVers);
        Utils.dbg("OptiFine Edition: " + ofEd);
        String mcVerOf = String.valueOf(mcVer) + "-OptiFine_" + ofEd;
        Utils.dbg("Minecraft_OptiFine Version: " + mcVerOf);
        return Installer.installOptiFineLibrary(mcVer, ofEd, dirMcLib, true);
    }

    private static void updateLauncherJson(File dirMc, String mcVerOf) throws IOException, ParseException {
        File fileJson = new File(dirMc, "launcher_profiles.json");
        if (!fileJson.exists() || !fileJson.isFile()) {
            Utils.showErrorMessage("File not found: " + fileJson);
            throw new RuntimeException("QUIET");
        }
        JSONParser jp = new JSONParser();
        String json = Utils.readFile(fileJson, "UTF-8");
        JSONObject root = (JSONObject)jp.parse(json);
        JSONObject profiles = (JSONObject)root.get("profiles");
        JSONObject prof = (JSONObject)profiles.get("OptiFine");
        if (prof == null) {
            prof = new JSONObject();
            prof.put("name", "OptiFine");
            prof.put("created", Installer.formatDateMs(new Date()));
            profiles.put("OptiFine", prof);
        }
        prof.put("type", "custom");
        prof.put("lastVersionId", mcVerOf);
        prof.put("lastUsed", Installer.formatDateMs(new Date()));
        prof.put("icon", ProfileIcon.DATA);
        root.put("selectedProfile", "OptiFine");
        FileOutputStream fosJson = new FileOutputStream(fileJson);
        OutputStreamWriter oswJson = new OutputStreamWriter((OutputStream)fosJson, "UTF-8");
        JSONWriter jw = new JSONWriter(oswJson);
        jw.writeObject(root);
        oswJson.flush();
        oswJson.close();
    }

    private static void updateJson(File dirMcVers, String mcVerOf, File dirMcLib, String mcVer, String ofEd) throws IOException, ParseException {
        File dirMcVersOf = new File(dirMcVers, mcVerOf);
        File fileJson = new File(dirMcVersOf, String.valueOf(mcVerOf) + ".json");
        String json = Utils.readFile(fileJson, "UTF-8");
        JSONParser jp = new JSONParser();
        JSONObject root = (JSONObject)jp.parse(json);
        JSONObject rootNew = new JSONObject();
        rootNew.put("id", mcVerOf);
        rootNew.put("inheritsFrom", mcVer);
        rootNew.put("time", Installer.formatDate(new Date()));
        rootNew.put("releaseTime", Installer.formatDate(new Date()));
        rootNew.put("type", "release");
        JSONArray libs = new JSONArray();
        rootNew.put("libraries", libs);
        String mainClass = (String)root.get("mainClass");
        if (!mainClass.startsWith("net.minecraft.launchwrapper.")) {
            mainClass = "net.minecraft.launchwrapper.Launch";
            rootNew.put("mainClass", mainClass);
            String mcArgs = (String)root.get("minecraftArguments");
            if (mcArgs != null) {
                mcArgs = String.valueOf(mcArgs) + "  --tweakClass optifine.OptiFineTweaker";
                rootNew.put("minecraftArguments", mcArgs);
            } else {
                rootNew.put("minimumLauncherVersion", "21");
                JSONObject args = new JSONObject();
                JSONArray argsGame = new JSONArray();
                argsGame.add("--tweakClass");
                argsGame.add("optifine.OptiFineTweaker");
                args.put("game", argsGame);
                rootNew.put("arguments", args);
            }
            JSONObject libLw = new JSONObject();
            libLw.put("name", "optifine:launchwrapper-of:" + Installer.getLaunchwrapperVersion());
            libs.add(0, libLw);
        }
        JSONObject libOf = new JSONObject();
        libOf.put("name", "optifine:OptiFine:" + mcVer + "_" + ofEd);
        libs.add(0, libOf);
        FileOutputStream fosJson = new FileOutputStream(fileJson);
        OutputStreamWriter oswJson = new OutputStreamWriter((OutputStream)fosJson, "UTF-8");
        JSONWriter jw = new JSONWriter(oswJson);
        jw.writeObject(rootNew);
        oswJson.flush();
        oswJson.close();
    }

    private static Object formatDate(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            String str = dateFormat.format(date);
            return str;
        }
        catch (Exception e) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String str = dateFormat.format(date);
            return str;
        }
    }

    private static Object formatDateMs(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'");
        String str = dateFormat.format(date);
        return str;
    }

    public static String getOptiFineEdition(String[] ofVers) {
        if (ofVers.length <= 2) {
            return "";
        }
        String ofEd = "";
        int i = 2;
        while (i < ofVers.length) {
            if (i > 2) {
                ofEd = String.valueOf(ofEd) + "_";
            }
            ofEd = String.valueOf(ofEd) + ofVers[i];
            ++i;
        }
        return ofEd;
    }

    private static boolean installOptiFineLibrary(String mcVer, String ofEd, File dirMcLib, boolean selectTarget) throws Exception {
        File fileSrc = Installer.getOptiFineZipFile();
        File dirDest = new File(dirMcLib, "optifine/OptiFine/" + mcVer + "_" + ofEd);
        File fileDest = new File(dirDest, "OptiFine-" + mcVer + "_" + ofEd + ".jar");
        if (selectTarget) {
            fileDest = new File(fileSrc.getParentFile(), "OptiFine_" + mcVer + "_" + ofEd + "_MOD.jar");
            JFileChooser jfc = new JFileChooser(fileDest.getParentFile());
            jfc.setSelectedFile(fileDest);
            int ret = jfc.showSaveDialog(null);
            if (ret != 0) {
                return false;
            }
            fileDest = jfc.getSelectedFile();
            if (fileDest.exists()) {
                JOptionPane.setDefaultLocale(Locale.ENGLISH);
                int ret2 = JOptionPane.showConfirmDialog(null, "The file \"" + fileDest.getName() + "\" already exists.\nDo you want to overwrite it?", "Save", 1);
                if (ret2 != 0) {
                    return false;
                }
            }
        }
        if (fileDest.equals(fileSrc)) {
            JOptionPane.showMessageDialog(null, "Source and target file are the same.", "Save", 0);
            return false;
        }
        Utils.dbg("Source: " + fileSrc);
        Utils.dbg("Dest: " + fileDest);
        File dirMc = dirMcLib.getParentFile();
        File fileBase = new File(dirMc, "versions/" + mcVer + "/" + mcVer + ".jar");
        if (!fileBase.exists()) {
            Installer.showMessageVersionNotFound(mcVer);
            throw new RuntimeException("QUIET");
        }
        if (fileDest.getParentFile() != null) {
            fileDest.getParentFile().mkdirs();
        }
        Patcher.process(fileBase, fileSrc, fileDest);
        return true;
    }

    private static boolean installLaunchwrapperLibrary(String mcVer, String ofEd, File dirMcLib) throws Exception {
        String ver = Installer.getLaunchwrapperVersion();
        String fileName = "launchwrapper-of-" + ver + ".jar";
        File dirDest = new File(dirMcLib, "optifine/launchwrapper-of/" + ver);
        File fileDest = new File(dirDest, fileName);
        Utils.dbg("Source: " + fileName);
        Utils.dbg("Dest: " + fileDest);
        InputStream fin = Installer.class.getResourceAsStream("/" + fileName);
        if (fin == null) {
            throw new IOException("File not found: " + fileName);
        }
        if (fileDest.getParentFile() != null) {
            fileDest.getParentFile().mkdirs();
        }
        FileOutputStream fout = new FileOutputStream(fileDest);
        Utils.copyAll(fin, fout);
        fout.flush();
        fin.close();
        fout.close();
        return true;
    }

    public static File getOptiFineZipFile() throws Exception {
        URL url = Installer.class.getProtectionDomain().getCodeSource().getLocation();
        Utils.dbg("URL: " + url);
        URI uri = url.toURI();
        File fileZip = new File(uri);
        return fileZip;
    }

    public static boolean isPatchFile() throws Exception {
        File fileZip = Installer.getOptiFineZipFile();
        try (ZipFile zipFile = new ZipFile(fileZip);){
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                if (!zipEntry.getName().startsWith("patch/")) continue;
                return true;
            }
            return false;
        }
    }

    private static void copyMinecraftVersion(String mcVer, String mcVerOf, File dirMcVer) throws IOException {
        File dirVerMc = new File(dirMcVer, mcVer);
        if (!dirVerMc.exists()) {
            Installer.showMessageVersionNotFound(mcVer);
            throw new RuntimeException("QUIET");
        }
        File dirVerMcOf = new File(dirMcVer, mcVerOf);
        dirVerMcOf.mkdirs();
        Utils.dbg("Dir version MC: " + dirVerMc);
        Utils.dbg("Dir version MC-OF: " + dirVerMcOf);
        File fileJarMc = new File(dirVerMc, String.valueOf(mcVer) + ".jar");
        File fileJarMcOf = new File(dirVerMcOf, String.valueOf(mcVerOf) + ".jar");
        if (!fileJarMc.exists()) {
            Installer.showMessageVersionNotFound(mcVer);
            throw new RuntimeException("QUIET");
        }
        Utils.copyFile(fileJarMc, fileJarMcOf);
        File fileJsonMc = new File(dirVerMc, String.valueOf(mcVer) + ".json");
        File fileJsonMcOf = new File(dirVerMcOf, String.valueOf(mcVerOf) + ".json");
        Utils.copyFile(fileJsonMc, fileJsonMcOf);
    }

    private static void showMessageVersionNotFound(String mcVer) {
        Utils.showErrorMessage("Cannot find Minecraft " + mcVer + ".\nYou must download and start Minecraft " + mcVer + " once in the official launcher.");
    }

    public static String getOptiFineVersion() throws IOException {
        InputStream in = Installer.class.getResourceAsStream("/net/optifine/Config.class");
        if (in == null) {
            in = Installer.class.getResourceAsStream("/Config.class");
        }
        if (in == null) {
            in = Installer.class.getResourceAsStream("/VersionThread.class");
        }
        if (in == null) {
            throw new IOException("OptiFine version not found");
        }
        return Installer.getOptiFineVersion(in);
    }

    public static String getOptiFineVersion(ZipFile zipFile) throws IOException {
        ZipEntry zipEntry = zipFile.getEntry("net/optifine/Config.class");
        if (zipEntry == null) {
            zipEntry = zipFile.getEntry("Config.class");
        }
        if (zipEntry == null) {
            zipEntry = zipFile.getEntry("VersionThread.class");
        }
        if (zipEntry == null) {
            throw new IOException("OptiFine version not found");
        }
        InputStream in = zipFile.getInputStream(zipEntry);
        String ofVer = Installer.getOptiFineVersion(in);
        in.close();
        return ofVer;
    }

    public static String getOptiFineVersion(InputStream in) throws IOException {
        int startPos;
        byte[] pattern;
        byte[] bytes = Utils.readAll(in);
        int pos = Utils.find(bytes, pattern = "OptiFine_".getBytes("ASCII"));
        if (pos < 0) {
            return null;
        }
        pos = startPos = pos;
        while (pos < bytes.length) {
            byte b2 = bytes[pos];
            if (b2 < 32 || b2 > 122) break;
            ++pos;
        }
        int endPos = pos;
        String ver = new String(bytes, startPos, endPos - startPos, "ASCII");
        return ver;
    }

    public static String getMinecraftVersionFromOfVersion(String ofVer) {
        if (ofVer == null) {
            return null;
        }
        String[] ofVers = Utils.tokenize(ofVer, "_");
        if (ofVers.length < 2) {
            return null;
        }
        String mcVer = ofVers[1];
        return mcVer;
    }

    private static String getLaunchwrapperVersion() throws IOException {
        String fileLibs = "/launchwrapper-of.txt";
        InputStream fin = Installer.class.getResourceAsStream(fileLibs);
        if (fin == null) {
            throw new IOException("File not found: " + fileLibs);
        }
        String str = Utils.readText(fin, "ASCII");
        if (!(str = str.trim()).matches("[0-9\\.]+")) {
            throw new IOException("Invalid launchwrapper version: " + str);
        }
        return str;
    }
}

