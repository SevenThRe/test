/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ya {
    public ya() {
        ya a2;
    }

    public static IResource ALLATORIxDEMO(String a2) throws IOException {
        return ya.ALLATORIxDEMO(new ResourceLocation("dragoncore", a2));
    }

    public static IResource ALLATORIxDEMO(ResourceLocation a2) throws IOException {
        return Minecraft.getMinecraft().getResourceManager().getResource(a2);
    }
}

