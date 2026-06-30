/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.client.resources.AbstractResourcePack
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.armourers;

import com.google.common.collect.Sets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class uk
extends AbstractResourcePack {
    private static final Logger m;
    public static Map<String, byte[]> j;

    public uk() {
        super(null);
        uk a2;
    }

    public InputStream getInputStreamByName(String a2) throws IOException {
        a2 = a2.replace("assets/dragonarmourers/", "");
        return new ByteArrayInputStream(j.get(a2));
    }

    public boolean hasResourceName(String a2) {
        a2 = a2.replace("assets/dragonarmourers/", "");
        return j.containsKey(a2);
    }

    static {
        j = new HashMap<String, byte[]>();
        m = LogManager.getLogger();
    }

    public String getPackName() {
        return "dragonarmourers";
    }

    public Set<String> getResourceDomains() {
        Object[] objectArray = new String[1];
        objectArray[0] = "dragonarmourers";
        return Sets.newHashSet((Object[])objectArray);
    }

    public void logNameNotLowercase(String a2) {
        m.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", (Object)a2, (Object)"dragonarmourers");
    }
}

