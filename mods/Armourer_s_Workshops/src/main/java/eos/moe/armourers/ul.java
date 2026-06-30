/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import eos.moe.armourers.co;
import eos.moe.armourers.dn;
import eos.moe.armourers.gl;
import eos.moe.armourers.ig;
import eos.moe.armourers.mk;
import eos.moe.armourers.mn;
import eos.moe.armourers.oj;
import eos.moe.armourers.vk;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ul
implements RemovalListener<co, oj> {
    public static mn r = new mn();
    public ArrayList<gl>[] l;
    private int[] c;
    private int[] v;
    private int[] s;
    public int[] m;
    public LoadingCache<co, oj> j;

    public int[] getAverageDyeColour(int a2) {
        ul a3;
        int[] nArray = new int[3];
        nArray[0] = a3.s[a2];
        nArray[1] = a3.v[a2];
        nArray[2] = a3.c[a2];
        return nArray;
    }

    public void cleanUpDisplayLists() {
        ul a2;
        a2.j.invalidateAll();
    }

    public ul() {
        ul a2;
        ul ul2 = a2;
        a2.s = new int[12];
        ul2.v = new int[12];
        ul2.c = new int[12];
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.removalListener((RemovalListener)a2);
        if (vk.za > 0) {
            cacheBuilder.expireAfterAccess((long)vk.za, TimeUnit.SECONDS);
        }
        if (vk.f > 0) {
            cacheBuilder.maximumSize((long)vk.o);
        }
        a2.j = cacheBuilder.build((CacheLoader)new ig(a2, null));
    }

    public void setAverageDyeValues(int[] a2, int[] a3, int[] a4) {
        ul a5;
        ul ul2 = a5;
        a5.s = a2;
        ul2.v = a3;
        ul2.c = a4;
    }

    public oj getModelForDye(mk a2) {
        ul a3;
        a2 = new co(((dn)a2).r(), ((dn)a2).r(), ((dn)a2).r(), ((mk)a2).r());
        return (oj)a3.j.getUnchecked(a2);
    }

    public void onRemoval(RemovalNotification<co, oj> a2) {
        ((oj)a2.getValue()).r();
    }

    public void setVertexLists(ArrayList<gl>[] a2) {
        a.l = a2;
    }

    public int getModelCount() {
        ul a2;
        return (int)a2.j.size();
    }
}

