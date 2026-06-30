/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.task.multi;

import java.io.File;
import journeymap.client.JourneymapClient;
import net.minecraft.client.Minecraft;

public interface ITask {
    public int getMaxRuntime();

    public void performTask(Minecraft var1, JourneymapClient var2, File var3, boolean var4) throws InterruptedException;
}

