/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.task.main;

import journeymap.client.JourneymapClient;
import net.minecraft.client.Minecraft;

public interface IMainThreadTask {
    public IMainThreadTask perform(Minecraft var1, JourneymapClient var2);

    public String getName();
}

