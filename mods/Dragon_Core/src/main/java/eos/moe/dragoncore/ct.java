/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.ICustomModelLoader
 *  net.minecraftforge.client.model.IModel
 *  net.minecraftforge.client.model.ModelLoaderRegistry
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.ks;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ct
implements ICustomModelLoader {
    public static final ct ALLATORIxDEMO = new ct();

    public ct() {
        ct a2;
        ModelLoaderRegistry.registerLoader((ICustomModelLoader)a2);
    }

    public void onResourceManagerReload(IResourceManager a2) {
    }

    public boolean accepts(ResourceLocation a2) {
        return a2.getPath().endsWith(".pqc");
    }

    public IModel loadModel(ResourceLocation a2, boolean a3) {
        try {
            return new hq(a2, a3);
        }
        catch (ks a4) {
            a4.printStackTrace();
            return null;
        }
    }

    public IModel loadModel(ResourceLocation a2) {
        try {
            return new hq(a2);
        }
        catch (ks a3) {
            a3.printStackTrace();
            return null;
        }
    }
}

