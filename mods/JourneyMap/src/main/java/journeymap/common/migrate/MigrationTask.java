/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.migrate;

import java.util.concurrent.Callable;
import journeymap.common.version.Version;

public interface MigrationTask
extends Callable<Boolean> {
    public boolean isActive(Version var1);
}

