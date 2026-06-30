/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dn;
import eos.moe.dragoncore.ee;
import eos.moe.dragoncore.ff;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.jk;
import eos.moe.dragoncore.ke;
import eos.moe.dragoncore.pk;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qn;
import eos.moe.dragoncore.th;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vl;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.xg;
import eos.moe.dragoncore.zg;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class kh {
    private static final Map<String, Class<? extends jj>> k = new HashMap<String, Class<? extends jj>>();
    private static final Map<String, String> ALLATORIxDEMO = new HashMap<String, String>();

    public kh() {
        kh a2;
    }

    public static Map<String, jj> ALLATORIxDEMO(ui a2, YamlConfiguration a3) {
        boolean a4 = false;
        Map<String, jj> a5 = Collections.synchronizedMap(new jk());
        List<jj> a6 = kh.ALLATORIxDEMO(a2, a3);
        for (jj jj2 : a6) {
            a5.put(jj2.k.getName(), jj2);
            if (!(jj2 instanceof pk) && !(jj2 instanceof vl)) continue;
            a4 = true;
        }
        if (a4) {
            Map<String, jj> a8 = Collections.synchronizedMap(new jk());
            Iterator<Map.Entry<String, jj>> iterator = a5.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, jj> a13 = iterator.next();
                if (a13.getValue() instanceof pk) {
                    pk a12 = (pk)a13.getValue();
                    iterator.remove();
                    String a11 = a12.ALLATORIxDEMO;
                    v a10 = a12.k.ALLATORIxDEMO();
                    if (a10 instanceof qg) {
                        Collection<v> a9 = ((qg)a10).ALLATORIxDEMO();
                        int a15 = 0;
                        for (v a14 : a9) {
                            String a16 = a11.replace("{index}", String.valueOf(a15)).replace("{data}", a14.c());
                            YamlConfiguration a17 = YamlConfiguration.loadConfiguration(new StringReader(a16));
                            Map<String, jj> a18 = kh.ALLATORIxDEMO(a2, a17);
                            a8.putAll(a18);
                            ++a15;
                        }
                        continue;
                    }
                    for (int a19 = 0; a19 < (int)a10.ALLATORIxDEMO(); ++a19) {
                        String a20 = a11.replace("{index}", String.valueOf(a19)).replace("{data}", String.valueOf(a19));
                        YamlConfiguration a21 = YamlConfiguration.loadConfiguration(new StringReader(a20));
                        Map<String, jj> a7 = kh.ALLATORIxDEMO(a2, a21);
                        a8.putAll(a7);
                    }
                    continue;
                }
                if (a13.getValue() instanceof vl) {
                    vl a9 = (vl)a13.getValue();
                    String a10 = a9.ALLATORIxDEMO.c();
                    YamlConfiguration a11 = wi.b.ALLATORIxDEMO(a10);
                    if (a11 == null && a10.contains("\n")) {
                        a11 = YamlConfiguration.loadConfiguration(new StringReader(a10));
                    }
                    if (a11 == null) continue;
                    Map<String, jj> a12 = kh.ALLATORIxDEMO(a2, a11);
                    a8.putAll(a12);
                    continue;
                }
                a8.put(a13.getKey(), a13.getValue());
            }
            return a8;
        }
        return a5;
    }

    private static /* synthetic */ List<jj> ALLATORIxDEMO(ui a2, YamlConfiguration a3) {
        ArrayList<jj> a4 = new ArrayList<jj>();
        for (String a5 : a3.getKeys(false)) {
            Class<? extends jj> a6;
            ConfigurationSection a7 = a3.getConfigurationSection(a5);
            if (a7 != null && a7.contains("type")) {
                a6 = k.get(a7.getString("type"));
            } else {
                String[] a8 = a5.split("_");
                a6 = k.get(a8[a8.length - 1]);
            }
            if (a7 == null || a6 == null) continue;
            a4.add(kh.ALLATORIxDEMO(a2, a7, a6));
        }
        return a4;
    }

    public static jj ALLATORIxDEMO(ui a2, ConfigurationSection a3) {
        Class<? extends jj> a4;
        if (a3.contains("type")) {
            a4 = k.get(a3.getString("type"));
        } else {
            String[] a5 = a3.getName().split("_");
            a4 = k.get(a5[a5.length - 1]);
        }
        if (a4 != null) {
            return kh.ALLATORIxDEMO(a2, a3, a4);
        }
        return null;
    }

    public static boolean ALLATORIxDEMO(ConfigurationSection a2) {
        Class<? extends jj> a3;
        if (a2.contains("type")) {
            a3 = k.get(a2.getString("type"));
        } else {
            String[] a4 = a2.getName().split("_");
            a3 = k.get(a4[a4.length - 1]);
        }
        return a3 != null;
    }

    private static /* synthetic */ jj ALLATORIxDEMO(ui a2, ConfigurationSection a3, Class<? extends jj> a4) {
        try {
            return a4.getDeclaredConstructor(ui.class, ConfigurationSection.class).newInstance(a2, a3);
        }
        catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException a5) {
            System.out.println(a4 + "\u65e0\u6cd5\u521b\u5efa -> " + a3.getName());
            a5.printStackTrace();
            return null;
        }
    }

    public static String ALLATORIxDEMO(String a2) {
        return ALLATORIxDEMO.getOrDefault(a2.toLowerCase(Locale.ROOT), a2);
    }

    static {
        k.put("t", th.class);
        k.put("l", ke.class);
        k.put("ev", dn.class);
        k.put("s", qn.class);
        k.put("tb", ee.class);
        k.put("ta", ff.class);
        k.put("h", xg.class);
        k.put("f", pk.class);
        k.put("e", zg.class);
        k.put("im", vl.class);
        k.put("texture", th.class);
        k.put("label", ke.class);
        k.put("entity", dn.class);
        k.put("slot", qn.class);
        k.put("textbox", ee.class);
        k.put("textarea", ff.class);
        k.put("hit", xg.class);
        k.put("foreach", pk.class);
        k.put("embed", zg.class);
        k.put("import", vl.class);
        k.put("\u56fe\u7247", th.class);
        k.put("\u6587\u672c", ke.class);
        k.put("\u5b9e\u4f53", dn.class);
        k.put("\u69fd\u4f4d", qn.class);
        k.put("\u6587\u672c\u6846", ee.class);
        k.put("\u591a\u884c\u6587\u672c\u6846", ff.class);
        k.put("\u78b0\u649e\u68c0\u6d4b", xg.class);
        k.put("\u5faa\u73af", pk.class);
        k.put("\u5d4c\u5165", zg.class);
        k.put("\u5bfc\u5165", vl.class);
        ALLATORIxDEMO.put("x", "x");
        ALLATORIxDEMO.put("y", "y");
        ALLATORIxDEMO.put("z", "z");
        ALLATORIxDEMO.put("width", "width");
        ALLATORIxDEMO.put("height", "height");
        ALLATORIxDEMO.put("limitx", "limitX");
        ALLATORIxDEMO.put("limity", "limitY");
        ALLATORIxDEMO.put("limitwidth", "limitWidth");
        ALLATORIxDEMO.put("limitheight", "limitHeight");
        ALLATORIxDEMO.put("maxdistabcex", "maxDistanceX");
        ALLATORIxDEMO.put("maxdistancey", "maxDistanceY");
        ALLATORIxDEMO.put("mindistancex", "minDistanceX");
        ALLATORIxDEMO.put("mindistancey", "minDistanceY");
        ALLATORIxDEMO.put("enable", "enable");
        ALLATORIxDEMO.put("visible", "visible");
        ALLATORIxDEMO.put("alpha", "alpha");
        ALLATORIxDEMO.put("scale", "scale");
        ALLATORIxDEMO.put("fscale", "fscale");
        ALLATORIxDEMO.put("frotatex", "frotatex");
        ALLATORIxDEMO.put("frotatey", "frotatey");
        ALLATORIxDEMO.put("frotatez", "frotatez");
        ALLATORIxDEMO.put("fx", "fx");
        ALLATORIxDEMO.put("fy", "fy");
        ALLATORIxDEMO.put("tip", "tip");
        ALLATORIxDEMO.put("position", "position");
        ALLATORIxDEMO.put("entity", "entity");
        ALLATORIxDEMO.put("head", "head");
        ALLATORIxDEMO.put("followmouse", "followMouse");
        ALLATORIxDEMO.put("hidename", "hideName");
        ALLATORIxDEMO.put("color", "color");
        ALLATORIxDEMO.put("text", "text");
        ALLATORIxDEMO.put("texture", "texture");
        ALLATORIxDEMO.put("texturehovered", "textureHovered");
        ALLATORIxDEMO.put("font", "font");
        ALLATORIxDEMO.put("u", "u");
        ALLATORIxDEMO.put("v", "v");
        ALLATORIxDEMO.put("texturewidth", "textureWidth");
        ALLATORIxDEMO.put("textureheight", "textureHeight");
        ALLATORIxDEMO.put("center", "center");
        ALLATORIxDEMO.put("shadow", "shadow");
        ALLATORIxDEMO.put("length", "length");
        ALLATORIxDEMO.put("replacecolor", "replaceColor");
        ALLATORIxDEMO.put("linespace", "lineSpace");
        ALLATORIxDEMO.put("texts", "texts");
        ALLATORIxDEMO.put("identifier", "identifier");
        ALLATORIxDEMO.put("drawbackground", "drawBackground");
        ALLATORIxDEMO.put("focused", "focused");
    }
}

