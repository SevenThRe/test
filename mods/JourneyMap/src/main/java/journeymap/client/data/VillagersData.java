/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.EntityHelper;
import journeymap.common.Journeymap;

public class VillagersData
extends CacheLoader<Class, Map<String, EntityDTO>> {
    public Map<String, EntityDTO> load(Class aClass) throws Exception {
        if (!FeatureManager.isAllowed(Feature.RadarVillagers)) {
            return new HashMap<String, EntityDTO>();
        }
        List<EntityDTO> list = EntityHelper.getVillagersNearby();
        return EntityHelper.buildEntityIdMap(list, true);
    }

    public long getTTL() {
        return Journeymap.getClient().getCoreProperties().cacheVillagersData.get().intValue();
    }
}

