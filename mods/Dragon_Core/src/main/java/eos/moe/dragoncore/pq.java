/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.client.resources.AbstractResourcePack
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.dragoncore;

import com.google.common.collect.Sets;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.tz;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pq
extends AbstractResourcePack {
    private static final Logger k = LogManager.getLogger();
    public static Map<String, byte[]> ALLATORIxDEMO = new HashMap<String, byte[]>();

    public static void ALLATORIxDEMO(String a2, byte[] a3) {
        ALLATORIxDEMO.put(a2, a3);
    }

    public pq() {
        super(null);
        pq a2;
    }

    public InputStream getInputStreamByName(String a2) throws IOException {
        File a3;
        a2 = a2.replace("assets/dragoncore/", "");
        if ((a2 = a2.replace(".png.png", ".png")).startsWith("/")) {
            a2 = a2.substring(1);
        }
        if ((a3 = new File(ca.s, a2)).exists()) {
            return new FileInputStream(new File(ca.s, a2));
        }
        if (!ALLATORIxDEMO.containsKey(a2)) {
            return tz.ALLATORIxDEMO(new FileInputStream(new File(ca.s, a2 + ".data")));
        }
        return new ByteArrayInputStream(ALLATORIxDEMO.get(a2));
    }

    public boolean hasResourceName(String a2) {
        a2 = a2.replace("assets/dragoncore/", "");
        if ((a2 = a2.replace(".png.png", ".png")).startsWith("/")) {
            a2 = a2.substring(1);
        }
        return new File(ca.s, a2).exists() || new File(ca.s, a2 + ".data").exists() || ALLATORIxDEMO.containsKey(a2);
    }

    public Set<String> getResourceDomains() {
        return Sets.newHashSet((Object[])new String[]{"dragoncore"});
    }

    public String getPackName() {
        return "dragoncore";
    }

    public void logNameNotLowercase(String a2) {
        k.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", (Object)a2, (Object)"dragoncore");
    }
}

