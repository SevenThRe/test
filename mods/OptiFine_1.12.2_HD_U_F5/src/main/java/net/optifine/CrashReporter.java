/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  b
 *  bid
 *  c
 */
package net.optifine;

import java.util.HashMap;
import net.optifine.http.FileUploadThread;
import net.optifine.http.IFileUploadListener;
import net.optifine.shaders.Shaders;

public class CrashReporter {
    public static void onCrashReport(b crashReport, c category) {
        try {
            Throwable cause = crashReport.b();
            if (cause == null) {
                return;
            }
            if (cause.getClass().getName().contains(".fml.client.SplashProgress")) {
                return;
            }
            CrashReporter.extendCrashReport(category);
            if (cause.getClass() == Throwable.class) {
                return;
            }
            bid settings = Config.getGameSettings();
            if (settings == null) {
                return;
            }
            if (!settings.t) {
                return;
            }
            String url = "http://optifine.net/crashReport";
            String reportStr = CrashReporter.makeReport(crashReport);
            byte[] content = reportStr.getBytes("ASCII");
            IFileUploadListener listener = new IFileUploadListener(){

                @Override
                public void fileUploadFinished(String url, byte[] content, Throwable exception) {
                }
            };
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("OF-Version", Config.getVersion());
            headers.put("OF-Summary", CrashReporter.makeSummary(crashReport));
            FileUploadThread fut = new FileUploadThread(url, headers, content, listener);
            fut.setPriority(10);
            fut.start();
            Thread.sleep(1000L);
        }
        catch (Exception e) {
            Config.dbg(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static String makeReport(b crashReport) {
        StringBuffer sb = new StringBuffer();
        sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
        sb.append("Summary: " + CrashReporter.makeSummary(crashReport) + "\n");
        sb.append("\n");
        sb.append(crashReport.e());
        sb.append("\n");
        return sb.toString();
    }

    private static String makeSummary(b crashReport) {
        Throwable t = crashReport.b();
        if (t == null) {
            return "Unknown";
        }
        StackTraceElement[] traces = t.getStackTrace();
        String firstTrace = "unknown";
        if (traces.length > 0) {
            firstTrace = traces[0].toString().trim();
        }
        String sum = t.getClass().getName() + ": " + t.getMessage() + " (" + crashReport.a() + ") [" + firstTrace + "]";
        return sum;
    }

    public static void extendCrashReport(c cat) {
        cat.a("OptiFine Version", (Object)Config.getVersion());
        cat.a("OptiFine Build", (Object)Config.getBuild());
        if (Config.getGameSettings() != null) {
            cat.a("Render Distance Chunks", (Object)("" + Config.getChunkViewDistance()));
            cat.a("Mipmaps", (Object)("" + Config.getMipmapLevels()));
            cat.a("Anisotropic Filtering", (Object)("" + Config.getAnisotropicFilterLevel()));
            cat.a("Antialiasing", (Object)("" + Config.getAntialiasingLevel()));
            cat.a("Multitexture", (Object)("" + Config.isMultiTexture()));
        }
        cat.a("Shaders", (Object)("" + Shaders.getShaderPackName()));
        cat.a("OpenGlVersion", (Object)("" + Config.openGlVersion));
        cat.a("OpenGlRenderer", (Object)("" + Config.openGlRenderer));
        cat.a("OpenGlVendor", (Object)("" + Config.openGlVendor));
        cat.a("CpuCount", (Object)("" + Config.getAvailableProcessors()));
    }
}

