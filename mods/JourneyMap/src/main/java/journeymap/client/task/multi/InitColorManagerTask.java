/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.task.multi;

import java.io.File;
import journeymap.client.JourneymapClient;
import journeymap.client.cartography.color.ColorManager;
import journeymap.client.task.multi.ITask;
import journeymap.client.task.multi.ITaskManager;
import net.minecraft.client.Minecraft;

public class InitColorManagerTask
implements ITask {
    @Override
    public int getMaxRuntime() {
        return 5000;
    }

    @Override
    public void performTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
        ColorManager.INSTANCE.ensureCurrent(false);
    }

    public static class Manager
    implements ITaskManager {
        static boolean enabled = false;

        @Override
        public Class<? extends ITask> getTaskClass() {
            return InitColorManagerTask.class;
        }

        @Override
        public boolean enableTask(Minecraft minecraft, Object params) {
            enabled = true;
            return true;
        }

        @Override
        public boolean isEnabled(Minecraft minecraft) {
            return enabled;
        }

        @Override
        public ITask getTask(Minecraft minecraft) {
            if (enabled) {
                return new InitColorManagerTask();
            }
            return null;
        }

        @Override
        public void taskAccepted(ITask task, boolean accepted) {
            enabled = false;
        }

        @Override
        public void disableTask(Minecraft minecraft) {
            enabled = false;
        }
    }
}

