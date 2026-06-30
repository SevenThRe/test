/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import eos.moe.dragoncore.ol;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Mouse;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sj {
    public static int b;
    public static int o;
    public static int y;
    public static int k;
    public static Map<String, Object> ALLATORIxDEMO;

    public sj() {
        sj a2;
    }

    public static Point ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.func_71410_x();
        ScaledResolution a3 = new ScaledResolution(a2);
        int a4 = a3.func_78326_a();
        int a5 = a3.func_78328_b();
        int a6 = Mouse.getX() * a4 / a2.field_71443_c;
        int a7 = a5 - Mouse.getY() * a5 / a2.field_71440_d - 1;
        return new Point(a6, a7);
    }

    public static boolean ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7) {
        return a2 >= a4 && a3 >= a5 && a2 <= a4 + a6 && a3 <= a5 + a7;
    }

    public static Color ALLATORIxDEMO(String a2) {
        try {
            if (a2.length() == 7 || a2.length() == 9 && a2.startsWith("#")) {
                ArrayList<Integer> a3 = new ArrayList<Integer>();
                for (int a4 = 1; a4 < a2.length(); a4 += 2) {
                    String a5 = a2.substring(a4, a4 + 2);
                    a3.add(Integer.parseInt(a5, 16));
                }
                if (a3.size() == 3) {
                    return new Color((Integer)a3.get(0), (Integer)a3.get(1), (Integer)a3.get(2));
                }
                return new Color((Integer)a3.get(1), (Integer)a3.get(2), (Integer)a3.get(3), (Integer)a3.get(0));
            }
        }
        catch (Exception a6) {
            a6.printStackTrace();
        }
        return Color.WHITE;
    }

    public static int ALLATORIxDEMO(Color a2) {
        return a2.getAlpha() << 24 | a2.getRed() << 16 | a2.getGreen() << 8 | a2.getBlue();
    }

    public static double ALLATORIxDEMO(double a2, double a3, double a4) {
        return Math.min(a4, Math.max(a2, a3));
    }

    public static boolean ALLATORIxDEMO(double a2, double a3, double a4) {
        return a2 >= a3 && a2 <= a4;
    }

    public static String ALLATORIxDEMO(String a2, boolean a3) {
        return !a3 && !Minecraft.func_71410_x().field_71474_y.field_74344_o ? TextFormatting.func_110646_a((String)a2) : a2;
    }

    public static List<String> ALLATORIxDEMO(String a2, int a3, String a4) {
        String a5 = a2 + "<->" + a3 + "<->" + a4;
        if (ALLATORIxDEMO.containsKey(a5)) {
            return (List)ALLATORIxDEMO.get(a5);
        }
        String a6 = a2.replace("\u7f89", "\n");
        ArrayList<String> a7 = new ArrayList<String>();
        for (String a8 : a6.split("\n")) {
            if (a3 > 0) {
                while (a8.length() > 0) {
                    String a9 = ol.ALLATORIxDEMO(a8, a4, a3, false);
                    if (a9.isEmpty()) {
                        a9 = a8;
                    }
                    a7.add(a9);
                    a8 = a8.substring(a9.length());
                }
                continue;
            }
            a7.add(a8);
        }
        if (a7.size() >= 2) {
            for (int a10 = 0; a10 < a7.size() && a10 + 1 != a7.size(); ++a10) {
                String a11 = (String)a7.get(a10);
                a7.set(a10 + 1, sj.ALLATORIxDEMO(a11) + (String)a7.get(a10 + 1));
            }
        }
        ALLATORIxDEMO.put(a5, a7);
        return a7;
    }

    public static List<ITextComponent> ALLATORIxDEMO(ITextComponent a2, int a3, String a4) {
        String a5 = a2 + "<->" + a3 + "<->" + a4;
        if (ALLATORIxDEMO.containsKey(a5)) {
            return (List)ALLATORIxDEMO.get(a5);
        }
        if (a3 <= 0) {
            a3 = 9999;
        }
        int a6 = 0;
        TextComponentString a7 = new TextComponentString("");
        ArrayList a8 = Lists.newArrayList();
        ArrayList a9 = Lists.newArrayList((Iterable)a2);
        for (int a10 = 0; a10 < a9.size(); ++a10) {
            String a11;
            String a12;
            ITextComponent a13 = (ITextComponent)a9.get(a10);
            String a14 = a13.func_150261_e();
            boolean a15 = false;
            if (a14.contains("\n")) {
                int a16 = a14.indexOf(10);
                a12 = a14.substring(a16 + 1);
                a14 = a14.substring(0, a16 + 1);
                TextComponentString a17 = new TextComponentString(a12);
                a17.func_150255_a(a13.func_150256_b().func_150232_l());
                a9.add(a10 + 1, a17);
                a15 = true;
            }
            a12 = (a11 = sj.ALLATORIxDEMO(a13.func_150256_b().func_150218_j() + a14, false)).endsWith("\n") ? a11.substring(0, a11.length() - 1) : a11;
            int a18 = ol.ALLATORIxDEMO(a12, a4, false);
            TextComponentString a19 = new TextComponentString(a12);
            a19.func_150255_a(a13.func_150256_b().func_150232_l());
            if (a6 + a18 > a3) {
                String a20;
                boolean a21 = false;
                String a22 = ol.ALLATORIxDEMO(a11, a4, a3 - a6, false);
                if (a22.isEmpty() && ol.ALLATORIxDEMO(a11.substring(0, 1), a4, false) > a3) {
                    a22 = a11.substring(0, 1);
                    a21 = true;
                }
                String string = a20 = a22.length() < a11.length() ? a11.substring(a22.length()) : null;
                if (a20 != null && !a20.isEmpty()) {
                    a20 = FontRenderer.func_78282_e((String)a22) + a20;
                    TextComponentString a23 = new TextComponentString(a20);
                    a23.func_150255_a(a13.func_150256_b().func_150232_l());
                    a9.add(a10 + 1, a23);
                }
                a18 = ol.ALLATORIxDEMO(a22, a4, false);
                a19 = new TextComponentString(a22);
                a19.func_150255_a(a13.func_150256_b().func_150232_l());
                a15 = true;
                if (a21) {
                    if (ol.ALLATORIxDEMO(a7.func_150254_d(), a4, false) > 0) {
                        a8.add(a7);
                    }
                    a7 = new TextComponentString("");
                    a6 = a18;
                    a7.func_150257_a((ITextComponent)a19);
                    continue;
                }
            }
            if (a6 + a18 <= a3) {
                a6 += a18;
                a7.func_150257_a((ITextComponent)a19);
            } else {
                a15 = true;
            }
            if (!a15) continue;
            a8.add(a7);
            a6 = 0;
            a7 = new TextComponentString("");
        }
        a8.add(a7);
        ALLATORIxDEMO.put(a5, a8);
        return a8;
    }

    public static void ALLATORIxDEMO(Runnable a2) {
        Minecraft.func_71410_x().func_152344_a(a2);
    }

    public static YamlConfiguration ALLATORIxDEMO(Collection<YamlConfiguration> a2) {
        YamlConfiguration a3 = new YamlConfiguration();
        YamlConfiguration a4 = new YamlConfiguration();
        block0: for (YamlConfiguration a5 : a2) {
            for (String a6 : a5.getKeys(false)) {
                if ("Functions".equals(a6)) {
                    ConfigurationSection a7 = a5.getConfigurationSection("Functions");
                    if (a7 == null) continue block0;
                    for (String a8 : a7.getKeys(false)) {
                        if (a4.contains(a8) || a7.getString(a8) == null) continue;
                        a4.set(a8, a7.getString(a8));
                    }
                    continue;
                }
                if (a3.contains(a6)) continue;
                a3.set(a6, a5.get(a6));
            }
        }
        a3.set("Functions", a4);
        return a3;
    }

    public static String ALLATORIxDEMO(String a2) {
        String a3 = "";
        for (int a4 = 0; a4 < a2.length(); ++a4) {
            char a5 = a2.charAt(a4);
            if (a5 != '\u00a7' || a4 + 1 >= a2.length()) continue;
            char a6 = a2.charAt(a4 + 1);
            int a7 = "0123456789abcdefklmnor".indexOf(String.valueOf(a6).toLowerCase(Locale.ROOT).charAt(0));
            if (a6 == '#' && a4 + 7 < a2.length()) {
                a3 = "\u00a7" + a2.substring(a4 + 1, a4 + 8);
            } else if (a7 != -1) {
                if (a7 < 16) {
                    a3 = "\u00a7" + a2.charAt(a4 + 1);
                } else if (a7 == 16) {
                    a3 = a3 + "\u00a7k";
                } else if (a7 == 17) {
                    a3 = a3 + "\u00a7l";
                } else if (a7 == 18) {
                    a3 = a3 + "\u00a7m";
                } else if (a7 == 19) {
                    a3 = a3 + "\u00a7n";
                } else if (a7 == 20) {
                    a3 = a3 + "\u00a7o";
                } else if (a7 == 21) {
                    a3 = a3 + "\u00a7r";
                }
            }
            ++a4;
        }
        return a3;
    }

    static {
        ALLATORIxDEMO = new ConcurrentHashMap<String, Object>();
    }
}

