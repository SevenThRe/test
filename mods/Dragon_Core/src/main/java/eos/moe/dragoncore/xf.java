/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  net.minecraft.util.text.ITextComponent
 */
package eos.moe.dragoncore;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import eos.moe.dragoncore.kg;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.text.ITextComponent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xf
implements v {
    public static final xf y = new xf("");
    private final String k;
    private static LoadingCache<String, Double> ALLATORIxDEMO = CacheBuilder.newBuilder().expireAfterAccess(2L, TimeUnit.SECONDS).build((CacheLoader)new kg());

    public xf(ITextComponent a2) {
        xf a3;
        if (a2 == null) {
            throw new RuntimeException("value is null");
        }
        a3.k = a2.getFormattedText();
    }

    public xf(String a2) {
        xf a3;
        if (a2 == null) {
            throw new RuntimeException("value is null");
        }
        a3.k = a2;
    }

    public String f() {
        xf a2;
        return a2.c();
    }

    @Override
    public String c() {
        xf a2;
        return a2.k;
    }

    @Override
    public double ALLATORIxDEMO() {
        xf a2;
        String a3 = a2.c();
        if ("true".equalsIgnoreCase(a3)) {
            return 1.0;
        }
        if ("false".equalsIgnoreCase(a3)) {
            return 0.0;
        }
        try {
            return (Double)ALLATORIxDEMO.get((Object)a3);
        }
        catch (ExecutionException a4) {
            throw new RuntimeException(a4);
        }
    }

    public static xf ALLATORIxDEMO(String a2) {
        return new xf(a2);
    }

    public boolean equals(Object a2) {
        xf a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a2 instanceof pf) {
            pf a4 = (pf)a2;
            return Double.compare(a4.ALLATORIxDEMO(), a3.ALLATORIxDEMO()) == 0;
        }
        if (a2 instanceof xf) {
            xf a5 = (xf)a2;
            return Objects.equals(a3.c(), a5.c());
        }
        return false;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "string";
    }
}

