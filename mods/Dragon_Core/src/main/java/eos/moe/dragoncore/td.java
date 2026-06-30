/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  org.apache.logging.log4j.Level
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aj;
import eos.moe.dragoncore.ov;
import java.io.File;
import java.io.IOException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.Level;

public class td {
    private File k;
    private aj[] ALLATORIxDEMO;

    public td(File a2) {
        td a3;
        a3.k = a2;
        a3.ALLATORIxDEMO = new aj[7];
        for (int a4 = 0; a4 < 7; ++a4) {
            a3.ALLATORIxDEMO[a4] = new aj("Slot " + (a4 + 1));
        }
    }

    public void x() throws IOException {
        td a2;
        if (a2.k.exists() && a2.k.canRead()) {
            NBTTagCompound a3 = CompressedStreamTools.func_74797_a((File)a2.k);
            for (int a4 = 0; a4 < 7; ++a4) {
                String a5 = "slot" + (a4 + 1);
                if (a3.func_74764_b(a5)) {
                    a2.ALLATORIxDEMO[a4].ALLATORIxDEMO = a3.func_74775_l(a5);
                }
                if (!a3.func_74764_b(a5 + "Name")) continue;
                a2.ALLATORIxDEMO[a4].k = a3.func_74779_i(a5 + "Name");
            }
        }
    }

    public void f() throws IOException {
        td a2;
        NBTTagCompound a3 = new NBTTagCompound();
        for (int a4 = 0; a4 < 7; ++a4) {
            a3.func_74782_a("slot" + (a4 + 1), (NBTBase)a2.ALLATORIxDEMO[a4].ALLATORIxDEMO);
            a3.func_74778_a("slot" + (a4 + 1) + "Name", a2.ALLATORIxDEMO[a4].k);
        }
        CompressedStreamTools.func_74795_b((NBTTagCompound)a3, (File)a2.k);
    }

    public void c() {
        try {
            td a2;
            a2.f();
            ov.ALLATORIxDEMO(Level.TRACE, "NBTEdit saved successfully.");
        }
        catch (IOException a3) {
            ov.ALLATORIxDEMO(Level.WARN, "Unable to write NBTEdit save.");
            ov.ALLATORIxDEMO("SaveStates", "save", a3);
        }
    }

    public void ALLATORIxDEMO() {
        try {
            td a2;
            a2.x();
            ov.ALLATORIxDEMO(Level.TRACE, "NBTEdit save loaded successfully.");
        }
        catch (IOException a3) {
            ov.ALLATORIxDEMO(Level.WARN, "Unable to read NBTEdit save.");
            ov.ALLATORIxDEMO("SaveStates", "load", a3);
        }
    }

    public aj ALLATORIxDEMO(int a2) {
        td a3;
        return a3.ALLATORIxDEMO[a2];
    }
}

