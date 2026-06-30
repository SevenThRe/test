/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.vy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class po
extends vy {
    public List<ju> y;
    private Map<String, ju> k;
    private List<ju> ALLATORIxDEMO = new ArrayList<ju>();

    public po() {
        po a2;
        a2.k = new HashMap<String, ju>();
    }

    @Override
    public List<ju> ALLATORIxDEMO() {
        po a2;
        return a2.y;
    }

    @Override
    public AnimationModelRenderer getPiece(String a2) {
        po a3;
        return a3.k.get(a2);
    }

    public void ALLATORIxDEMO(List<ju> a2) {
        po a3;
        a3.y = a2;
        for (ju a4 : a3.y) {
            a3.c(a4);
        }
    }

    private /* synthetic */ void c(ju a2) {
        po a3;
        a3.k.put(a2.ALLATORIxDEMO(), a2);
        List<ju> a4 = a2.b;
        if (a4 != null) {
            for (ju a5 : a4) {
                if (a5 == null) continue;
                a3.c(a5);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(float a2) {
        po a3;
        a3.c();
        for (ju a4 : a3.y) {
            a4.d(a2);
        }
    }

    @Override
    public void ALLATORIxDEMO() throws ConcurrentModificationException {
        po a2;
        try {
            for (ju a3 : a2.ALLATORIxDEMO) {
                a2.y.remove(a3);
            }
        }
        catch (ConcurrentModificationException a4) {
            throw new ConcurrentModificationException(a4.getMessage());
        }
        a2.ALLATORIxDEMO.clear();
    }

    @Override
    public boolean ALLATORIxDEMO() {
        po a2;
        return !a2.ALLATORIxDEMO.isEmpty();
    }

    private /* synthetic */ String[] ALLATORIxDEMO() {
        po a2;
        String[] a3 = new String[a2.ALLATORIxDEMO.size()];
        for (int a4 = 0; a4 < a2.ALLATORIxDEMO.size(); ++a4) {
            a3[a4] = a2.ALLATORIxDEMO.get(a4).ALLATORIxDEMO();
        }
        return a3;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(float a2, String ... a3) {
        po a4;
        a4.c();
        for (ju a5 : a4.y) {
            for (String a6 : a3) {
                if (!a6.equalsIgnoreCase(a5.ALLATORIxDEMO())) continue;
                a5.d(a2);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void c(float a2, ju ... a3) {
        po a4;
        a4.c();
        for (ju a5 : a4.y) {
            for (ju a6 : a3) {
                if (!a5.equals(a6)) continue;
                a5.d(a2);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(float a2, String a3) {
        po a4;
        a4.c();
        for (ju a5 : a4.y) {
            if (!a3.equalsIgnoreCase(a5.ALLATORIxDEMO())) continue;
            a5.d(a2);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(float a2, ju a3) {
        po a4;
        a4.c();
        for (ju a5 : a4.y) {
            if (!a5.equals(a3)) continue;
            a5.d(a2);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(float a2, ju ... a3) {
        po a4;
        a4.c();
        for (ju a5 : a4.y) {
            boolean a6 = a4.ALLATORIxDEMO(a5, a3);
            if (a6) continue;
            a5.d(a2);
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ju a2, ju[] a3) {
        for (ju a4 : a3) {
            if (!a2.equals(a4)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void ALLATORIxDEMO(ju a2) {
        po a3;
        a3.ALLATORIxDEMO.add(a2);
    }

    private /* synthetic */ void c() {
        po a2;
        if (a2.ALLATORIxDEMO()) {
            ca.l.d("=============================================================");
            ca.l.d("Duplications:");
            for (String a3 : a2.ALLATORIxDEMO()) {
                ca.l.d(a3);
            }
            ca.l.d("=============================================================");
        }
    }
}

