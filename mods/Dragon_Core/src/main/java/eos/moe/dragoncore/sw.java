/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hp;
import eos.moe.dragoncore.sr;
import eos.moe.dragoncore.to;
import eos.moe.dragoncore.ut;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class sw {
    public sw() {
        sw a2;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ut ALLATORIxDEMO(ResourceLocation a2) throws to {
        try {
            Throwable throwable = null;
            try (IResource a3 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2);){
                InputStream a4 = a3.func_110527_b();
                ut ut2 = sw.ALLATORIxDEMO(a4);
                return ut2;
            }
            catch (Throwable a4) {
                void var3_5;
                throwable = var3_5;
                throw var3_5;
            }
        }
        catch (Throwable a5) {
            if (!(a5 instanceof FileNotFoundException)) throw new to(a5);
            throw new to("\u672a\u627e\u5230\u6587\u4ef6", a5);
        }
    }

    public static ut ALLATORIxDEMO(InputStream a2) {
        InputStreamReader a6 = new InputStreamReader(a2, StandardCharsets.UTF_8);
        YamlConfiguration a7 = YamlConfiguration.loadConfiguration(a6);
        ArrayList<hp> a8 = new ArrayList<hp>();
        ConfigurationSection a9 = a7.getConfigurationSection("Layer");
        if (a9 != null) {
            for (String string : a9.getKeys(false)) {
                ConfigurationSection a3 = a9.getConfigurationSection(string);
                if (a3 == null) continue;
                a8.add(new hp(a3));
            }
        }
        LinkedHashMap<String, String> a10 = new LinkedHashMap<String, String>();
        ConfigurationSection configurationSection = a7.getConfigurationSection("Trigger");
        if (configurationSection != null) {
            for (String string : configurationSection.getKeys(false)) {
                String a11 = configurationSection.getString(string);
                if (a11 == null) continue;
                a10.put(string, a11);
            }
        }
        ConfigurationSection a4 = a7.getConfigurationSection("HeldItemTransform");
        sr sr2 = new sr(a4);
        return new ut(a8, a10, sr2);
    }
}

