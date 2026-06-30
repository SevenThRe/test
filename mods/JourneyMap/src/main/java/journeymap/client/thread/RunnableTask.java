/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import journeymap.client.JourneymapClient;
import journeymap.client.io.FileHandler;
import journeymap.client.task.multi.ITask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;

public class RunnableTask
implements Runnable {
    static final JourneymapClient jm = Journeymap.getClient();
    static final Logger logger = Journeymap.getLogger();
    static final Minecraft mc = FMLClientHandler.instance().getClient();
    static final boolean threadLogging = jm.isThreadLogging();
    private final ExecutorService taskExecutor;
    private final Runnable innerRunnable;
    private final ITask task;
    private final int timeout;

    public RunnableTask(ExecutorService taskExecutor, ITask task) {
        this.taskExecutor = taskExecutor;
        this.task = task;
        this.timeout = task.getMaxRuntime();
        this.innerRunnable = new Inner();
    }

    @Override
    public void run() {
        try {
            this.taskExecutor.submit(this.innerRunnable);
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn("Interrupted task that ran too long:" + this.task);
        }
    }

    class Inner
    implements Runnable {
        Inner() {
        }

        @Override
        public final void run() {
            try {
                if (!jm.isMapping().booleanValue()) {
                    logger.debug("JM not mapping, aborting");
                    return;
                }
                File jmWorldDir = FileHandler.getJMWorldDir(mc);
                if (jmWorldDir == null) {
                    logger.debug("JM world dir not found, aborting");
                    return;
                }
                RunnableTask.this.task.performTask(mc, jm, jmWorldDir, threadLogging);
            }
            catch (InterruptedException e) {
                logger.debug("Task interrupted: " + LogFormatter.toPartialString(e));
            }
            catch (Throwable t) {
                String error = "Unexpected error during RunnableTask: " + LogFormatter.toString(t);
                logger.error(error);
            }
        }
    }
}

