/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.task.multi;

import journeymap.client.task.multi.ITask;
import net.minecraft.client.Minecraft;

public interface ITaskManager {
    public Class<? extends ITask> getTaskClass();

    public boolean enableTask(Minecraft var1, Object var2);

    public boolean isEnabled(Minecraft var1);

    public ITask getTask(Minecraft var1);

    public void taskAccepted(ITask var1, boolean var2);

    public void disableTask(Minecraft var1);
}

