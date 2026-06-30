/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 */
package journeymap.common.migrate;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import java.util.ArrayList;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.migrate.MigrationTask;

public class Migration {
    private final String targetPackage;

    public Migration(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public boolean performTasks() {
        boolean success = true;
        ArrayList<MigrationTask> tasks = new ArrayList<MigrationTask>();
        try {
            ImmutableSet classInfoSet = ClassPath.from((ClassLoader)Journeymap.class.getClassLoader()).getTopLevelClassesRecursive(this.targetPackage);
            for (ClassPath.ClassInfo classInfo : classInfoSet) {
                Class clazz = classInfo.load();
                if (!MigrationTask.class.isAssignableFrom(clazz)) continue;
                try {
                    MigrationTask task = (MigrationTask)clazz.newInstance();
                    if (!task.isActive(Journeymap.JM_VERSION)) continue;
                    tasks.add(task);
                }
                catch (Throwable t) {
                    Journeymap.getLogger().error("Couldn't instantiate MigrationTask " + clazz, (Object)LogFormatter.toPartialString(t));
                    success = false;
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Couldn't find MigrationTasks: " + t, (Object)LogFormatter.toPartialString(t));
            success = false;
        }
        for (MigrationTask task : tasks) {
            try {
                if (((Boolean)task.call()).booleanValue()) continue;
                success = false;
            }
            catch (Throwable t) {
                Journeymap.getLogger().fatal(LogFormatter.toString(t));
                success = false;
            }
        }
        if (!success) {
            Journeymap.getLogger().fatal("Some or all of JourneyMap migration failed! You may experience significant errors.");
        }
        return success;
    }
}

