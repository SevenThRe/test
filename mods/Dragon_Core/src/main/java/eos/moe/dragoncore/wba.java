/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class wba {
    public Map<String, Integer> y = new HashMap<String, Integer>();
    public int k;
    public int ALLATORIxDEMO;

    public wba(ConfigurationSection a2) throws IOException {
        wba a3;
        ArrayList<BufferedImage> a4 = new ArrayList<BufferedImage>();
        ConfigurationSection a5 = a2.getConfigurationSection("texture");
        for (String a6 : a5.getKeys(false)) {
            IResource a7 = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("dragoncore", a6));
            BufferedImage a8 = ImageIO.read(a7.func_110527_b());
            a4.add(a8);
            a3.y.put(a6, a3.k);
            a3.ALLATORIxDEMO = a8.getHeight();
            a3.k += a8.getWidth();
        }
    }
}

