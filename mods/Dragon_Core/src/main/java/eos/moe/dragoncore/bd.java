/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.kh;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.pt;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class bd {
    public bd() {
        bd a2;
    }

    @i(f={"cp_create"})
    public static pe c(ui a2, v ... a3) {
        String a4 = a3[0].c();
        String a5 = a3.length >= 3 ? a3[2].c() : "";
        YamlConfiguration a6 = new YamlConfiguration();
        ConfigurationSection a7 = a6.createSection(a4);
        a7.set("type", a3[1].c());
        if (!a5.isEmpty()) {
            a7.set("extends", a5);
        }
        jj a8 = kh.ALLATORIxDEMO(a2, a7);
        return new pe(a8);
    }

    @i(f={"\u65b0\u5efa\u7ec4\u4ef6", "Component_Create"})
    public static pe ALLATORIxDEMO(ui a2, v ... a3) {
        if (a3.length == 3) {
            String a4 = a3[0].c();
            String a5 = a3[2].c();
            if (a4.isEmpty()) {
                return new pe(null);
            }
            if (!a4.contains("_")) {
                a4 = UUID.randomUUID().toString().replace("-", "_") + "_" + a4;
            }
            YamlConfiguration a6 = new YamlConfiguration();
            ConfigurationSection a7 = a6.createSection(a4);
            a7.set("type", a3[1].c());
            if (!a5.isEmpty()) {
                a7.set("extends", a5);
            }
            jj a8 = kh.ALLATORIxDEMO(a2, a7);
            return new pe(a8);
        }
        if (a3.length >= 1) {
            String a9;
            String a10 = a3[0].c();
            String string = a9 = a3.length >= 2 ? a3[1].c() : "";
            if (a10.isEmpty()) {
                return new pe(null);
            }
            if (!a10.contains("_")) {
                a10 = UUID.randomUUID().toString().replace("-", "_") + "_" + a10;
            }
            YamlConfiguration a11 = new YamlConfiguration();
            ConfigurationSection a12 = a11.createSection(a10);
            if (!a9.isEmpty()) {
                a12.set("extends", a9);
            }
            jj a13 = kh.ALLATORIxDEMO(a2, a12);
            return new pe(a13);
        }
        return new pe(null);
    }

    @i(f={"\u53d6\u7ec4\u4ef6", "Component_Find"})
    public static pe ALLATORIxDEMO(ui a2, String a3) {
        return new pe(a2.findComponent(a3));
    }

    @i(f={"\u53d6\u7ec4\u4ef6\u503c", "Component_Get"})
    public static v ALLATORIxDEMO(ui a2, v a3, String a4) {
        Object a5 = a3 instanceof pe ? a3.ALLATORIxDEMO() : a2.findComponent(a3.c());
        if (a5 != null) {
            Object a6 = a5.getValue(a4);
            if (a6 instanceof String) {
                return new xf((String)a6);
            }
            return (v)a6;
        }
        return xf.y;
    }

    @i(f={"\u53d6\u6240\u6709\u7ec4\u4ef6", "Component_FindAll"})
    public static qg ALLATORIxDEMO(ui a2) {
        return new qg(new ArrayList<jj>(a2.getComponents().values()).stream().map(pe::new).collect(Collectors.toList()));
    }

    @i(f={"\u8bbe\u7f6e\u7ec4\u4ef6\u503c", "\u7f6e\u7ec4\u4ef6\u503c", "Component_Set"})
    public static void ALLATORIxDEMO(ui a2, v a3, String a4, v ... a5) {
        Object a6 = a3 instanceof pe ? a3.ALLATORIxDEMO() : a2.findComponent(a3.c());
        if (a6 != null && a5.length >= 1) {
            bd.ALLATORIxDEMO(a2, (p)a6, a4, a5);
        }
    }

    @i(f={"\u6dfb\u52a0\u7ec4\u4ef6", "\u6dfb\u52a0\u7ec4\u4ef6\u524d", "Component_Add"})
    public static qg c(ui a2, v a3, v a4) {
        List<jj> a5 = bd.ALLATORIxDEMO(a2, a3, a4, true);
        return new qg(a5.stream().map(pe::new).collect(Collectors.toList()));
    }

    @i(f={"\u6dfb\u52a0\u7ec4\u4ef6\u540e", "Component_Add_After"})
    public static qg ALLATORIxDEMO(ui a2, v a3, v a4) {
        List<jj> a5 = bd.ALLATORIxDEMO(a2, a3, a4, false);
        return new qg(a5.stream().map(pe::new).collect(Collectors.toList()));
    }

    public static List<jj> ALLATORIxDEMO(ui a2, v a3, v a4, boolean a5) {
        String a6;
        String string = a6 = a4 instanceof pe ? a4.ALLATORIxDEMO().getSection().getName() : a4.c();
        if (a3 instanceof pe) {
            jj a7 = (jj)a3.ALLATORIxDEMO();
            if (a7 != null) {
                Map<String, jj> a8 = a2.getComponents();
                if (a6 == null || a2.findComponent(a6) == null) {
                    a8.put(a7.getSection().getName(), a7);
                } else {
                    LinkedHashMap<String, jj> a9 = new LinkedHashMap<String, jj>();
                    for (Map.Entry<String, jj> a10 : new ArrayList<Map.Entry<String, jj>>(a8.entrySet())) {
                        if (!a5) {
                            a9.put(a10.getKey(), a10.getValue());
                        }
                        if (a10.getKey().equals(a6)) {
                            a9.put(a7.getSection().getName(), a7);
                        }
                        if (!a5) continue;
                        a9.put(a10.getKey(), a10.getValue());
                    }
                    a8.clear();
                    a8.putAll(a9);
                }
                return Lists.newArrayList((Object[])new jj[]{a7});
            }
        } else if (a3 instanceof xf) {
            String a11 = a3.c();
            YamlConfiguration a12 = YamlConfiguration.loadConfiguration(new StringReader(a11));
            Map<String, jj> a13 = kh.ALLATORIxDEMO(a2, a12);
            if (a13.size() > 0) {
                Map<String, jj> a14 = a2.getComponents();
                if (a6 == null || a2.findComponent(a6) == null) {
                    a14.putAll(a13);
                } else {
                    LinkedHashMap<String, jj> a15 = new LinkedHashMap<String, jj>();
                    for (Map.Entry<String, jj> a16 : new ArrayList<Map.Entry<String, jj>>(a14.entrySet())) {
                        if (!a5) {
                            a15.put(a16.getKey(), a16.getValue());
                        }
                        if (a16.getKey().equals(a6)) {
                            a15.putAll(a13);
                        }
                        if (!a5) continue;
                        a15.put(a16.getKey(), a16.getValue());
                    }
                    a14.clear();
                    a14.putAll(a15);
                }
            }
            return new ArrayList<jj>(a13.values());
        }
        return new ArrayList<jj>();
    }

    @i(f={"\u79fb\u9664\u7ec4\u4ef6", "\u5220\u9664\u7ec4\u4ef6", "Component_Delete"})
    public static void ALLATORIxDEMO(ui a2, v a3, boolean a4) {
        if (a3 instanceof pe) {
            a2.removeComponent((p)a3.ALLATORIxDEMO());
        } else {
            a2.removeComponent(a3.c(), a4);
        }
    }

    @i(f={"\u53d6\u9f20\u6807\u60ac\u6d6e\u7ec4\u4ef6\u540d", "Component_Hovered_Name"})
    public static String ALLATORIxDEMO(ui a2) {
        return a2.d == null ? "" : a2.d.k.getName();
    }

    @i(f={"\u53d6\u9f20\u6807\u60ac\u6d6e\u7ec4\u4ef6", "\u53d6\u60ac\u6d6e\u7ec4\u4ef6", "\u60ac\u6d6e\u7ec4\u4ef6", "Component_Hovered"})
    public static pe ALLATORIxDEMO(ui a2) {
        return new pe(a2.d);
    }

    @i(f={"\u53d6\u6240\u6709\u60ac\u6d6e\u7ec4\u4ef6", "\u6240\u6709\u60ac\u6d6e\u7ec4\u4ef6", "Component_Hovered_All"})
    public static v ALLATORIxDEMO(ui a2) {
        Collection a3 = new ArrayList<jj>(a2.p).stream().map(pe::new).collect(Collectors.toList());
        return new qg(a3);
    }

    public static void ALLATORIxDEMO(ui a2, p a4, String a5, v ... a6) {
        a5 = kh.ALLATORIxDEMO(a5);
        String a7 = String.valueOf(System.currentTimeMillis());
        Object a8 = null;
        if ("texts".equalsIgnoreCase(a5) || "tip".equalsIgnoreCase(a5)) {
            List<String> a9 = pt.ALLATORIxDEMO(a6);
            a9.replaceAll(a3 -> a3.replace("%time%", a7));
            a8 = a9;
        } else {
            a8 = a6[0].c().replace("%time%", a7);
        }
        a4.setValue(a5, a8);
    }
}

