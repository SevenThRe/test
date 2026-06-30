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
import eos.moe.dragoncore.pl;
import eos.moe.dragoncore.ye;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kx
extends AbstractResourcePack {
    public static kx k = new kx();
    private static final Logger ALLATORIxDEMO = LogManager.getLogger();

    public kx() {
        super(null);
        kx a2;
    }

    public InputStream getInputStreamByName(String a2) throws IOException {
        a2 = a2.replace("assets/dragoncore/", "");
        if ((a2 = a2.replace(".png.png", ".png")).startsWith("/")) {
            a2 = a2.substring(1);
        }
        return new ByteArrayInputStream(pl.b.ALLATORIxDEMO(a2).ALLATORIxDEMO());
    }

    public boolean hasResourceName(String a2) {
        ye a3;
        a2 = a2.replace("assets/dragoncore/", "");
        if ((a2 = a2.replace(".png.png", ".png")).startsWith("/")) {
            a2 = a2.substring(1);
        }
        return (a3 = pl.b.ALLATORIxDEMO(a2)) != null && a3.ALLATORIxDEMO() != null;
    }

    public Set<String> getResourceDomains() {
        return Sets.newHashSet((Object[])new String[]{"dragoncore"});
    }

    public String getPackName() {
        return "dragoncore";
    }

    public void logNameNotLowercase(String a2) {
        ALLATORIxDEMO.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", (Object)a2, (Object)"dragoncore");
    }
}

