/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.task.multi;

import java.io.File;
import java.util.List;
import journeymap.client.JourneymapClient;
import journeymap.client.model.ChunkMD;
import journeymap.client.task.multi.ITask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;

public class TaskBatch
implements ITask {
    final List<ITask> taskList;
    final int timeout;
    protected long startNs;
    protected long elapsedNs;

    public TaskBatch(List<ITask> tasks) {
        this.taskList = tasks;
        int timeout = 0;
        for (ITask task : tasks) {
            timeout += task.getMaxRuntime();
        }
        this.timeout = timeout;
    }

    @Override
    public int getMaxRuntime() {
        return this.timeout;
    }

    @Override
    public void performTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
        if (this.startNs == 0L) {
            this.startNs = System.nanoTime();
        }
        if (threadLogging) {
            Journeymap.getLogger().debug("START batching tasks");
        }
        while (!this.taskList.isEmpty()) {
            if (Thread.interrupted()) {
                Journeymap.getLogger().warn("TaskBatch thread interrupted: " + this);
                throw new InterruptedException();
            }
            ITask task = this.taskList.remove(0);
            try {
                if (threadLogging) {
                    Journeymap.getLogger().debug("Batching task: " + task);
                }
                task.performTask(mc, jm, jmWorldDir, threadLogging);
            }
            catch (ChunkMD.ChunkMissingException e) {
                Journeymap.getLogger().warn(e.getMessage());
            }
            catch (Throwable t) {
                Journeymap.getLogger().error(String.format("Unexpected error during task batch: %s", LogFormatter.toString(t)));
            }
        }
        if (threadLogging) {
            Journeymap.getLogger().debug("DONE batching tasks");
        }
        this.elapsedNs = System.nanoTime() - this.startNs;
    }
}

