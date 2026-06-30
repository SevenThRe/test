/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 */
package goblinbob.mobends.core.configuration;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public abstract class CoreConfig {
    protected Configuration configuration;

    CoreConfig(File file) {
        this.configuration = new Configuration(file);
    }

    public abstract void save();
}

