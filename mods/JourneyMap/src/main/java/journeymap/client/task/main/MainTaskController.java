/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.task.main;

import com.google.common.collect.Queues;
import java.util.concurrent.ConcurrentLinkedQueue;
import journeymap.client.JourneymapClient;
import journeymap.client.log.StatTimer;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.task.main.MappingMonitorTask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;

public class MainTaskController {
    private final ConcurrentLinkedQueue<IMainThreadTask> currentQueue = Queues.newConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue<IMainThreadTask> deferredQueue = Queues.newConcurrentLinkedQueue();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addTask(IMainThreadTask task) {
        ConcurrentLinkedQueue<IMainThreadTask> concurrentLinkedQueue = this.currentQueue;
        synchronized (concurrentLinkedQueue) {
            this.currentQueue.add(task);
        }
    }

    public boolean isActive() {
        if (this.currentQueue.isEmpty()) {
            return false;
        }
        return this.currentQueue.size() != 1 || !(this.currentQueue.peek() instanceof MappingMonitorTask);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void performTasks() {
        try {
            ConcurrentLinkedQueue<IMainThreadTask> concurrentLinkedQueue = this.currentQueue;
            synchronized (concurrentLinkedQueue) {
                if (this.currentQueue.isEmpty()) {
                    this.currentQueue.add(new MappingMonitorTask());
                }
                Minecraft minecraft = FMLClientHandler.instance().getClient();
                JourneymapClient journeymapClient = Journeymap.getClient();
                while (!this.currentQueue.isEmpty()) {
                    IMainThreadTask task = this.currentQueue.poll();
                    if (task == null) continue;
                    StatTimer timer = StatTimer.get(task.getName());
                    timer.start();
                    IMainThreadTask deferred = task.perform(minecraft, journeymapClient);
                    timer.stop();
                    if (deferred == null) continue;
                    this.deferredQueue.add(deferred);
                }
                this.currentQueue.addAll(this.deferredQueue);
                this.deferredQueue.clear();
            }
        }
        catch (Throwable t) {
            String error = "Error in TickTaskController.performMainThreadTasks(): " + t.getMessage();
            Journeymap.getLogger().error(error);
            Journeymap.getLogger().error(LogFormatter.toString(t));
        }
    }
}

