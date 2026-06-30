/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package eos.moe.dragoncore;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.bm;
import eos.moe.dragoncore.bo;
import eos.moe.dragoncore.dk;
import eos.moe.dragoncore.eg;
import eos.moe.dragoncore.gm;
import eos.moe.dragoncore.hk;
import eos.moe.dragoncore.mm;
import eos.moe.dragoncore.nn;
import eos.moe.dragoncore.u;
import eos.moe.dragoncore.wl;
import eos.moe.dragoncore.zi;
import java.util.ArrayList;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nd
extends dk {
    public static gm b = new nn(null, new eg(0.0));
    public static gm o = new nn(null, new eg(1.0));
    public static String y = "return ";
    private bo k;
    private final Object ALLATORIxDEMO = new Object();

    public nd() {
        nd a2;
        a2.ALLATORIxDEMO.put("cos", wl.class);
        a2.ALLATORIxDEMO.put("sin", mm.class);
        a2.ALLATORIxDEMO("abs", "math.abs");
        a2.ALLATORIxDEMO("ceil", "math.ceil");
        a2.ALLATORIxDEMO("clamp", "math.clamp");
        a2.ALLATORIxDEMO("cos", "math.cos");
        a2.ALLATORIxDEMO("exp", "math.exp");
        a2.ALLATORIxDEMO("floor", "math.floor");
        a2.ALLATORIxDEMO("lerp", "math.lerp");
        a2.ALLATORIxDEMO("lerprotate", "math.lerprotate");
        a2.ALLATORIxDEMO("ln", "math.ln");
        a2.ALLATORIxDEMO("max", "math.max");
        a2.ALLATORIxDEMO("min", "math.min");
        a2.ALLATORIxDEMO("mod", "math.mod");
        a2.ALLATORIxDEMO("pow", "math.pow");
        a2.ALLATORIxDEMO("random", "math.random");
        a2.ALLATORIxDEMO("round", "math.round");
        a2.ALLATORIxDEMO("sin", "math.sin");
        a2.ALLATORIxDEMO("sqrt", "math.sqrt");
        a2.ALLATORIxDEMO("trunc", "math.trunc");
    }

    public void ALLATORIxDEMO(String a2, String a3) {
        nd a4;
        a4.ALLATORIxDEMO.put(a3, a4.ALLATORIxDEMO.remove(a2));
    }

    public void ALLATORIxDEMO(String a2, double a3) {
        nd a4;
        zi a5 = a4.ALLATORIxDEMO(a2);
        if (a5 != null) {
            a5.ALLATORIxDEMO(a3);
        }
    }

    public void ALLATORIxDEMO() {
        nd a2;
        for (zi a3 : a2.k.values()) {
            a3.ALLATORIxDEMO(0.0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public zi ALLATORIxDEMO(String a2) {
        nd a3;
        Object object = a3.ALLATORIxDEMO;
        synchronized (object) {
            u a4;
            zi zi2 = a4 = a3.k == null ? null : a3.k.ALLATORIxDEMO.get(a2);
            if (a4 == null) {
                a4 = super.ALLATORIxDEMO(a2);
            }
            if (a4 == null) {
                a4 = new zi(a2, 0.0);
                a3.ALLATORIxDEMO((zi)a4);
            }
            return a4;
        }
    }

    public gm ALLATORIxDEMO(JsonElement a2) throws hk {
        if (a2.isJsonPrimitive()) {
            nd a3;
            JsonPrimitive a4 = a2.getAsJsonPrimitive();
            if (a4.isString()) {
                try {
                    return new nn(a3, new eg(Float.parseFloat(a4.getAsString())));
                }
                catch (Exception a5) {
                    return a3.c(a4.getAsString());
                }
            }
            return new nn(a3, new eg(a4.getAsDouble()));
        }
        return b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public gm c(String a2) throws hk {
        nd a3;
        ArrayList<String> a4 = new ArrayList<String>();
        for (String a5 : a2.toLowerCase().trim().split(";")) {
            if (a5.trim().isEmpty()) continue;
            a4.add(a5);
        }
        if (a4.size() == 0) {
            return b;
        }
        Object object = a3.ALLATORIxDEMO;
        synchronized (object) {
            bo a6;
            a3.k = a6 = new bo(a3);
            try {
                for (String a5 : a4) {
                    a6.k.add(a3.ALLATORIxDEMO(a5));
                }
            }
            catch (Exception a7) {
                a3.k = null;
                throw a7;
            }
            a3.k = null;
            return a6;
        }
    }

    @Override
    public gm ALLATORIxDEMO(String a2) throws hk {
        nd a3;
        if ((a2 = a2.trim()).startsWith("return ")) {
            try {
                return new nn(a3, a3.ALLATORIxDEMO(a2.substring("return ".length()))).ALLATORIxDEMO();
            }
            catch (Exception a4) {
                throw new hk("Couldn't parse return '" + a2 + "' expression!");
            }
        }
        try {
            List<Object> a5 = a3.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
            if (a5.size() >= 3 && a5.get(0) instanceof String && a3.c(a5.get(0)) && a5.get(1).equals("=")) {
                String a6 = (String)a5.get(0);
                a5 = a5.subList(2, a5.size());
                zi a7 = null;
                if (!a3.k.containsKey(a6) && !a3.k.ALLATORIxDEMO.containsKey(a6)) {
                    a7 = new zi(a6, 0.0);
                    a3.k.ALLATORIxDEMO.put(a6, a7);
                } else {
                    a7 = a3.ALLATORIxDEMO(a6);
                }
                return new bm(a3, a7, a3.f(a5));
            }
            return new nn(a3, a3.f(a5));
        }
        catch (Exception a8) {
            throw new hk("Couldn't parse '" + a2 + "' expression!");
        }
    }

    private /* synthetic */ u f(List<Object> a2) throws hk {
        try {
            nd a3;
            return a3.c(a2);
        }
        catch (Exception a4) {
            a4.printStackTrace();
            throw new hk("Couldn't parse an expression!");
        }
    }

    @Override
    public boolean c(String a2) {
        nd a3;
        return super.c(a2) || a2.equals("=");
    }
}

