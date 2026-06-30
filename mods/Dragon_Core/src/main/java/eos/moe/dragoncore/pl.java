/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.Minecraft
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.filefilter.FalseFileFilter
 *  org.apache.commons.io.filefilter.IOFileFilter
 *  org.apache.commons.io.filefilter.SuffixFileFilter
 */
package eos.moe.dragoncore;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.nl;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.vq;
import eos.moe.dragoncore.ye;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class pl {
    public static pl b = new pl();
    private final Map<String, ye> o = new ConcurrentHashMap<String, ye>();
    private Gson y = new Gson();
    private File k = new File(ca.g, "ResourceCache");
    private ExecutorService ALLATORIxDEMO = Executors.newFixedThreadPool(1);

    public pl() {
        pl a2;
    }

    public ye ALLATORIxDEMO(String a2) {
        pl a3;
        return a3.o.get(a2);
    }

    public void f(String a2) {
        pl a3;
        String a4 = a2.replace("\\", "/");
        a3.ALLATORIxDEMO.submit(() -> {
            pl a3;
            a3.c(a4);
        });
    }

    private /* synthetic */ void c(String a2) {
        pl a3;
        ye a4 = a3.o.remove(a2.toLowerCase(Locale.ROOT));
        if (a4 != null) {
            a4.ALLATORIxDEMO().delete();
            a4.c().delete();
        }
    }

    public void c(String a2, String a3, byte[] a4) {
        pl a5;
        String a6 = a2.replace("\\", "/");
        a5.ALLATORIxDEMO.submit(() -> {
            pl a5;
            a5.c(a6);
            JsonObject a6 = new JsonObject();
            a6.addProperty("name", a6);
            a6.addProperty("md5", a3);
            String a7 = a5.y.toJson((JsonElement)a6);
            String a8 = UUID.randomUUID().toString();
            try {
                File a9 = new File(a5.k, a8 + ".data1");
                File a10 = new File(a5.k, a8 + ".data");
                FileUtils.writeByteArrayToFile((File)a10, (byte[])nl.c(a4));
                FileUtils.write((File)a9, (CharSequence)a7, (Charset)StandardCharsets.UTF_8);
                a5.o.put(a6.toLowerCase(Locale.ROOT), new ye(a9, a10, a6, a3));
            }
            catch (Exception a11) {
                a11.printStackTrace();
            }
        });
    }

    public void k() {
        pl a2;
        a2.ALLATORIxDEMO.submit(() -> {
            pl a2;
            a2.d();
            HashMap<String, String> a3 = new HashMap<String, String>();
            for (Map.Entry<String, ye> a4 : a2.o.entrySet()) {
                a3.put(a4.getValue().ALLATORIxDEMO(), a4.getValue().c());
            }
            nw.ALLATORIxDEMO(a3);
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void d() {
        pl a2;
        HashMap<String, ye> a3 = new HashMap<String, ye>();
        a2.k.mkdirs();
        Collection a4 = FileUtils.listFiles((File)a2.k, (IOFileFilter)new SuffixFileFilter(".data1"), (IOFileFilter)FalseFileFilter.FALSE);
        for (File a5 : a4) {
            File a6 = new File(a5.getParentFile(), a5.getName().replace(".data1", ".data"));
            if (!a6.exists()) continue;
            InputStreamReader a7 = null;
            try {
                a7 = new InputStreamReader((InputStream)new FileInputStream(a5), StandardCharsets.UTF_8);
                JsonObject a8 = (JsonObject)a2.y.fromJson((Reader)a7, JsonObject.class);
                if (!a8.has("name") || !a8.has("md5")) continue;
                String a9 = a8.get("name").getAsString();
                String a10 = a8.get("md5").getAsString();
                a3.put(a9.toLowerCase(Locale.ROOT), new ye(a5, a6, a9, a10));
            }
            catch (FileNotFoundException fileNotFoundException) {}
            continue;
            finally {
                try {
                    a7.close();
                }
                catch (IOException iOException) {}
            }
        }
        a2.o.clear();
        a2.o.putAll(a3);
    }

    public void x() {
        pl a2;
        a2.ALLATORIxDEMO.submit(() -> Minecraft.getMinecraft().addScheduledTask(vq::ALLATORIxDEMO));
    }

    public void f() {
        pl a2;
        a2.ALLATORIxDEMO.shutdownNow();
    }
}

