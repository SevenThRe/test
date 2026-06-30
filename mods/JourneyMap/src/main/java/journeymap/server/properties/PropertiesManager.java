/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.DimensionManager
 */
package journeymap.server.properties;

import java.util.HashMap;
import java.util.Map;
import journeymap.server.properties.DefaultDimensionProperties;
import journeymap.server.properties.DimensionProperties;
import journeymap.server.properties.GlobalProperties;
import net.minecraftforge.common.DimensionManager;

public class PropertiesManager {
    private static PropertiesManager INSTANCE;
    private Map<Integer, DimensionProperties> dimensionProperties;
    private GlobalProperties globalProperties;
    private DefaultDimensionProperties defaultDimensionProperties;

    public static PropertiesManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertiesManager();
            INSTANCE.loadConfigs();
        }
        return INSTANCE;
    }

    private void loadConfigs() {
        this.dimensionProperties = new HashMap<Integer, DimensionProperties>();
        this.globalProperties = new GlobalProperties();
        this.globalProperties.load();
        this.defaultDimensionProperties = new DefaultDimensionProperties();
        this.defaultDimensionProperties.load();
        for (Integer dim : DimensionManager.getIDs()) {
            this.genConfig(dim);
        }
    }

    public DimensionProperties getDimProperties(int dim) {
        if (this.dimensionProperties.get(dim) == null) {
            this.genConfig(dim);
        }
        return this.dimensionProperties.get(dim);
    }

    public DefaultDimensionProperties getDefaultDimensionProperties() {
        return this.defaultDimensionProperties;
    }

    public GlobalProperties getGlobalProperties() {
        return this.globalProperties;
    }

    private void genConfig(int dim) {
        DimensionProperties prop = new DimensionProperties(dim);
        this.dimensionProperties.put(dim, prop);
        if (!prop.getFile().exists()) {
            prop.build();
        }
        prop.load();
    }
}

