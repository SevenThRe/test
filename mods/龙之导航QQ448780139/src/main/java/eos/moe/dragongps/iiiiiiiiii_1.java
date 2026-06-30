/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragongps;

import java.awt.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

/*
 * Renamed from eos.moe.dragongps.IiIIiiIiIi
 */
public class iiiiiiiiii_1 {
    public iiiiiiiiii_1() {
        iiiiiiiiii_1 IIiiiiiIIi;
    }

    public static Rectangle IIIiiiIiii() {
        Minecraft IIiiiiiIIi = Minecraft.func_71410_x();
        ScaledResolution IIiiiiiIIi2 = new ScaledResolution(IIiiiiiIIi);
        int IIiiiiiIIi3 = IIiiiiiIIi2.func_78326_a();
        int IIiiiiiIIi4 = IIiiiiiIIi2.func_78328_b();
        int IIiiiiiIIi5 = Mouse.getEventX() * IIiiiiiIIi3 / IIiiiiiIIi.field_71443_c;
        int IIiiiiiIIi6 = IIiiiiiIIi4 - Mouse.getEventY() * IIiiiiiIIi4 / IIiiiiiIIi.field_71440_d - 1;
        return new Rectangle(IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi3, IIiiiiiIIi4);
    }
}

