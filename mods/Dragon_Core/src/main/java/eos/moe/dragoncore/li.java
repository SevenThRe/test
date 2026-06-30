/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.dragoncore;

import blockbuster.BedrockScheme;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class li {
    public static li k = new li();
    private Map<String, BedrockScheme> ALLATORIxDEMO = new HashMap<String, BedrockScheme>();

    public li() {
        li a2;
    }

    public void ALLATORIxDEMO() {
        li a2;
        a2.ALLATORIxDEMO.clear();
    }

    public BedrockScheme ALLATORIxDEMO(String a2) {
        li a3;
        if (!a3.ALLATORIxDEMO.containsKey(a2)) {
            try {
                IResource a4 = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("dragoncore", "models/particle/" + a2 + ".json"));
                BedrockScheme a5 = a3.ALLATORIxDEMO(a4.func_110527_b());
                a3.ALLATORIxDEMO.put(a2, a5);
            }
            catch (FileNotFoundException a6) {
                a3.ALLATORIxDEMO.put(a2, null);
                System.out.println("\u7f3a\u5931\u6587\u4ef6: " + a2);
            }
            catch (Exception a7) {
                a3.ALLATORIxDEMO.put(a2, null);
                System.out.println("\u65e0\u6cd5\u8bfb\u5165\u6587\u4ef6: " + a2);
                a7.printStackTrace();
            }
        }
        return a3.ALLATORIxDEMO.get(a2);
    }

    private /* synthetic */ BedrockScheme ALLATORIxDEMO(InputStream a2) throws IOException {
        String a3 = IOUtils.toString((InputStream)a2, (Charset)StandardCharsets.UTF_8);
        try {
            return BedrockScheme.parse(a3);
        }
        catch (Exception a4) {
            a4.printStackTrace();
            return null;
        }
    }
}

