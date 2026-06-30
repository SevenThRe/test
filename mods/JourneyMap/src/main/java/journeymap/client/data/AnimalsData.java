/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  net.minecraft.entity.EntityLivingBase
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.EntityHelper;
import journeymap.common.Journeymap;
import net.minecraft.entity.EntityLivingBase;

public class AnimalsData
extends CacheLoader<Class, Map<String, EntityDTO>> {
    public Map<String, EntityDTO> load(Class aClass) throws Exception {
        if (!FeatureManager.isAllowed(Feature.RadarAnimals)) {
            return new HashMap<String, EntityDTO>();
        }
        List<EntityDTO> list = EntityHelper.getAnimalsNearby();
        ArrayList<EntityDTO> finalList = new ArrayList<EntityDTO>(list);
        for (EntityDTO entityDTO : list) {
            EntityLivingBase entityLiving = (EntityLivingBase)entityDTO.entityLivingRef.get();
            if (entityLiving == null) {
                finalList.remove(entityDTO);
                continue;
            }
            if (!entityLiving.func_184207_aI()) continue;
            finalList.remove(entityDTO);
        }
        return EntityHelper.buildEntityIdMap(finalList, true);
    }

    public long getTTL() {
        return Math.max(1000, Journeymap.getClient().getCoreProperties().cacheAnimalsData.get());
    }
}

