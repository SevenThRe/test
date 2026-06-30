/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.configuration;

import goblinbob.mobends.core.configuration.CoreConfig;
import goblinbob.mobends.core.network.NetworkConfiguration;
import goblinbob.mobends.core.network.SharedProperty;
import java.io.File;

public class CoreServerConfig
extends CoreConfig {
    private static final String CATEGORY_SERVER = "Server";

    public CoreServerConfig(File file) {
        super(file);
        Iterable<SharedProperty<?>> props = NetworkConfiguration.instance.getSharedConfig().getProperties();
        for (SharedProperty<?> prop : props) {
            prop.updateWithConfig(this.configuration, CATEGORY_SERVER);
        }
        this.configuration.save();
    }

    @Override
    public void save() {
    }
}

