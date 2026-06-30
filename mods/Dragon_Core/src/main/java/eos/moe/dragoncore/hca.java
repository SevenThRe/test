/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.faa;
import eos.moe.dragoncore.uha;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class hca
extends uha {
    private boolean b;
    private float o;
    private String y;
    private boolean k;
    private static final Pattern ALLATORIxDEMO = Pattern.compile("[%]([^%]+)[%]");

    public hca(ConfigurationSection a2) {
        super(a2);
        hca a3;
        a3.b = a2.getBoolean("center");
        a3.o = (float)a2.getDouble("size", 1.0);
        a3.y = a2.getString("text", "\u672a\u914d\u7f6e\u6587\u672c").replace("&", "\u00a7");
        a3.k = a2.getBoolean("shadow", true);
    }

    @Override
    public void ALLATORIxDEMO() {
        hca a2;
        GlStateManager.func_179094_E();
        String a3 = a2.c();
        GlStateManager.func_179137_b((double)a2.c(), (double)a2.ALLATORIxDEMO(), (double)0.0);
        GlStateManager.func_179152_a((float)a2.o, (float)a2.o, (float)a2.o);
        if (a2.b) {
            a2.ALLATORIxDEMO().x.func_175065_a(a3, (float)(-a2.ALLATORIxDEMO().x.func_78256_a(a3)) / 2.0f, 0.0f, -1, a2.k);
        } else {
            a2.ALLATORIxDEMO().x.func_175065_a(a3, 0.0f, 0.0f, -1, a2.k);
        }
        GlStateManager.func_179121_F();
    }

    public String c() {
        hca a2;
        if (!a2.y.contains("%")) {
            return a2.y;
        }
        String a3 = a2.y;
        List<String> a4 = dj.c(a2.ALLATORIxDEMO().v);
        if ((a3 = a3.replace("%name%", a2.ALLATORIxDEMO().v.func_82833_r())).contains("%lore_") || a3.contains("%nbt_")) {
            Matcher a5 = ALLATORIxDEMO.matcher(a3);
            while (a5.find()) {
                NBTBase a6;
                String a7 = a5.group(0);
                int a8 = a7.indexOf("_");
                if (a8 == -1) continue;
                if (a7.startsWith("%lore")) {
                    int a9 = hca.ALLATORIxDEMO(a7.substring(a8 + 1, a7.length() - 1));
                    if (a9 < a4.size()) {
                        a3 = a3.replaceAll(Pattern.quote(a5.group()), Matcher.quoteReplacement(a4.get(a9)));
                        continue;
                    }
                    a3 = a3.replaceAll(Pattern.quote(a5.group()), "");
                    continue;
                }
                if (!a7.startsWith("%nbt") || a2.ALLATORIxDEMO().v.func_77978_p() == null) continue;
                String[] a10 = a7.substring(5, a7.length() - 1).split("\\.");
                NBTTagCompound a11 = a2.ALLATORIxDEMO().v.func_77978_p();
                for (int a12 = 0; a12 < a10.length - 1; ++a12) {
                    a11 = a11.func_74775_l(a10[a12]);
                }
                if (a10.length <= 0 || (a6 = a11.func_74781_a(a10[a10.length - 1])) == null) continue;
                if (a6.func_74732_a() == 8) {
                    a3 = a3.replaceAll(Pattern.quote(a5.group()), Matcher.quoteReplacement(((NBTTagString)a6).func_150285_a_()));
                    continue;
                }
                a3 = a3.replaceAll(Pattern.quote(a5.group()), Matcher.quoteReplacement(a6.toString()));
            }
        }
        a3 = faa.ALLATORIxDEMO(a3);
        return a3;
    }

    public static int ALLATORIxDEMO(String a2) {
        try {
            return Integer.parseInt(a2);
        }
        catch (Exception a3) {
            return 0;
        }
    }
}

