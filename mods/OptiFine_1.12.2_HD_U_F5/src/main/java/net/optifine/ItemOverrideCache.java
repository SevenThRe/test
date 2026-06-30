/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aip
 *  amu
 *  bvz
 *  com.google.common.primitives.Floats
 *  nf
 *  vp
 */
package net.optifine;

import com.google.common.primitives.Floats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.optifine.ItemOverrideProperty;
import net.optifine.reflect.Reflector;
import net.optifine.util.CompoundKey;

public class ItemOverrideCache {
    private ItemOverrideProperty[] itemOverrideProperties;
    private Map<CompoundKey, nf> mapValueModels = new HashMap<CompoundKey, nf>();
    public static final nf LOCATION_NULL = new nf("");

    public ItemOverrideCache(ItemOverrideProperty[] itemOverrideProperties) {
        this.itemOverrideProperties = itemOverrideProperties;
    }

    public nf getModelLocation(aip stack, amu world, vp entity) {
        CompoundKey valueKey = this.getValueKey(stack, world, entity);
        if (valueKey == null) {
            return null;
        }
        return this.mapValueModels.get(valueKey);
    }

    public void putModelLocation(aip stack, amu world, vp entity, nf location) {
        CompoundKey valueKey = this.getValueKey(stack, world, entity);
        if (valueKey == null) {
            return;
        }
        this.mapValueModels.put(valueKey, location);
    }

    private CompoundKey getValueKey(aip stack, amu world, vp entity) {
        Object[] indexes = new Integer[this.itemOverrideProperties.length];
        for (int i = 0; i < indexes.length; ++i) {
            Integer index = this.itemOverrideProperties[i].getValueIndex(stack, world, entity);
            if (index == null) {
                return null;
            }
            indexes[i] = index;
        }
        return new CompoundKey(indexes);
    }

    public static ItemOverrideCache make(List<bvz> overrides) {
        if (overrides.isEmpty()) {
            return null;
        }
        if (!Reflector.ItemOverride_mapResourceValues.exists()) {
            return null;
        }
        LinkedHashMap<nf, HashSet<Float>> propertyValues = new LinkedHashMap<nf, HashSet<Float>>();
        for (bvz itemOverride : overrides) {
            Map mapResourceValues = (Map)Reflector.getFieldValue(itemOverride, Reflector.ItemOverride_mapResourceValues);
            Set setLocs = mapResourceValues.keySet();
            for (nf loc : setLocs) {
                Float val = (Float)mapResourceValues.get(loc);
                if (val == null) continue;
                HashSet<Float> setValues = (HashSet<Float>)propertyValues.get(loc);
                if (setValues == null) {
                    setValues = new HashSet<Float>();
                    propertyValues.put(loc, setValues);
                }
                setValues.add(val);
            }
        }
        ArrayList<ItemOverrideProperty> listProps = new ArrayList<ItemOverrideProperty>();
        Set setPropertyLocations = propertyValues.keySet();
        for (nf loc : setPropertyLocations) {
            Set setValues = (Set)propertyValues.get(loc);
            float[] values = Floats.toArray((Collection)setValues);
            ItemOverrideProperty prop = new ItemOverrideProperty(loc, values);
            listProps.add(prop);
        }
        ItemOverrideProperty[] props = listProps.toArray(new ItemOverrideProperty[listProps.size()]);
        ItemOverrideCache cache = new ItemOverrideCache(props);
        ItemOverrideCache.logCache(props, overrides);
        return cache;
    }

    private static void logCache(ItemOverrideProperty[] props, List<bvz> overrides) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < props.length; ++i) {
            ItemOverrideProperty prop = props[i];
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("" + prop.getLocation() + "=" + prop.getValues().length);
        }
        if (overrides.size() > 0) {
            sb.append(" -> " + overrides.get(0).a() + " ...");
        }
        Config.dbg("ItemOverrideCache: " + sb.toString());
    }

    public String toString() {
        return "properties: " + this.itemOverrideProperties.length + ", models: " + this.mapValueModels.size();
    }
}

