/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 */
package eos.moe.armourers;

import eos.moe.armourers.uk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;

public class ng
implements IResourceManagerReloadListener {
    public void onResourceManagerReload(IResourceManager a2) {
        if (a2 instanceof SimpleReloadableResourceManager) {
            ((SimpleReloadableResourceManager)a2).reloadResourcePack((IResourcePack)new uk());
        }
    }

    public ng() {
        ng a2;
        IResourceManager iResourceManager = Minecraft.getMinecraft().getResourceManager();
        if (iResourceManager instanceof SimpleReloadableResourceManager) {
            ((SimpleReloadableResourceManager)iResourceManager).registerReloadListener((IResourceManagerReloadListener)a2);
        }
    }
}

