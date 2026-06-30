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
import eos.moe.dragoncore.eg;
import eos.moe.dragoncore.gm;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.u;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nn
extends gm {
    public u k;
    public boolean ALLATORIxDEMO;

    public nn(nd a2, u a3) {
        super(a2);
        nn a4;
        a4.k = a3;
    }

    public gm ALLATORIxDEMO() {
        nn a2;
        a2.ALLATORIxDEMO = true;
        return a2;
    }

    @Override
    public double ALLATORIxDEMO() {
        nn a2;
        return a2.k.ALLATORIxDEMO();
    }

    public String toString() {
        nn a2;
        return (a2.ALLATORIxDEMO ? "return " : "") + a2.k.toString();
    }

    @Override
    public JsonElement ALLATORIxDEMO() {
        nn a2;
        if (a2.k instanceof eg) {
            return new JsonPrimitive((Number)a2.k.ALLATORIxDEMO());
        }
        return super.ALLATORIxDEMO();
    }
}

