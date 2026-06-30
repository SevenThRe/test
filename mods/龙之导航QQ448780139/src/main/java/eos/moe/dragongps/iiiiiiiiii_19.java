/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragongps;

import java.util.List;

/*
 * Renamed from eos.moe.dragongps.iiiIIIiIii
 */
public class iiiiiiiiii_19 {
    private List<String> iIIIIiiIII;
    private String IIiIiIIIiI;
    private String IIiiiiiIIi;

    public String iIIiiiIIiI() {
        iiiiiiiiii_19 IIiiiiiIIi;
        return IIiiiiiIIi.IIiiiiiIIi;
    }

    public iiiiiiiiii_19(String IIiiiiiIIi2, String IIiiiiiIIi3, List<String> IIiiiiiIIi4) {
        iiiiiiiiii_19 IIiiiiiIIi5;
        IIiiiiiIIi5.IIiiiiiIIi = IIiiiiiIIi2;
        IIiiiiiIIi5.IIiIiIIIiI = IIiiiiiIIi3.replace("&", "\u00a7");
        IIiiiiiIIi5.iIIIIiiIII = IIiiiiiIIi4;
        IIiiiiiIIi5.iIIIIiiIII.replaceAll(IIiiiiiIIi -> IIiiiiiIIi.replace("&", "\u00a7"));
    }

    public String IIIiiiIiii() {
        iiiiiiiiii_19 IIiiiiiIIi;
        return IIiiiiiIIi.IIiIiIIIiI;
    }

    public List<String> IIIiiiIiii() {
        iiiiiiiiii_19 IIiiiiiIIi;
        return IIiiiiiIIi.iIIIIiiIII;
    }
}

