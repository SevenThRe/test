/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiUtilRenderComponents
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.dragoncore.mixins;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={GuiUtilRenderComponents.class})
public abstract class MixinGuiUtilRenderComponents {
    public MixinGuiUtilRenderComponents() {
        MixinGuiUtilRenderComponents a2;
    }

    @Shadow
    public static String func_178909_a(String a2, boolean a3) {
        return null;
    }

    @Overwrite
    public static List<ITextComponent> func_178908_a(ITextComponent a2, int a3, FontRenderer a4, boolean a5, boolean a6) {
        int a7 = 0;
        TextComponentString a8 = new TextComponentString("");
        ArrayList a9 = Lists.newArrayList();
        ArrayList a10 = Lists.newArrayList((Iterable)a2);
        for (int a11 = 0; a11 < a10.size(); ++a11) {
            String a12;
            String a13;
            ITextComponent a14 = (ITextComponent)a10.get(a11);
            String a15 = a14.func_150261_e();
            boolean a16 = false;
            if (a15.contains("\n")) {
                int a17 = a15.indexOf(10);
                a13 = a15.substring(a17 + 1);
                a15 = a15.substring(0, a17 + 1);
                TextComponentString a18 = new TextComponentString(a13);
                a18.func_150255_a(a14.func_150256_b().func_150232_l());
                a10.add(a11 + 1, a18);
                a16 = true;
            }
            a13 = (a12 = MixinGuiUtilRenderComponents.func_178909_a(a14.func_150256_b().func_150218_j() + a15, a6)).endsWith("\n") ? a12.substring(0, a12.length() - 1) : a12;
            int a19 = a4.func_78256_a(a13);
            TextComponentString a20 = new TextComponentString(a13);
            a20.func_150255_a(a14.func_150256_b().func_150232_l());
            if (a7 + a19 > a3) {
                String a21;
                boolean a22 = false;
                String a23 = a4.func_78262_a(a12, a3 - a7, false);
                if (a23.isEmpty() && a4.func_78256_a(a12.substring(0, 1)) > a3) {
                    a23 = a12.substring(0, 1);
                    a22 = true;
                }
                String string = a21 = a23.length() < a12.length() ? a12.substring(a23.length()) : null;
                if (a21 != null && !a21.isEmpty()) {
                    int a24 = a23.lastIndexOf(32);
                    if (a24 >= 0 && a4.func_78256_a(a12.substring(0, a24)) > 0) {
                        a23 = a12.substring(0, a24);
                        if (a5) {
                            ++a24;
                        }
                        a21 = a12.substring(a24);
                    } else if (a7 > 0 && !a12.contains(" ")) {
                        a23 = "";
                        a21 = a12;
                    }
                    a21 = FontRenderer.func_78282_e((String)a23) + a21;
                    TextComponentString a25 = new TextComponentString(a21);
                    a25.func_150255_a(a14.func_150256_b().func_150232_l());
                    a10.add(a11 + 1, a25);
                }
                a19 = a4.func_78256_a(a23);
                a20 = new TextComponentString(a23);
                a20.func_150255_a(a14.func_150256_b().func_150232_l());
                a16 = true;
                if (a22) {
                    if (a4.func_78256_a(a8.func_150254_d()) > 0) {
                        a9.add(a8);
                    }
                    a8 = new TextComponentString("");
                    a7 = a19;
                    a8.func_150257_a((ITextComponent)a20);
                    continue;
                }
            }
            if (a7 + a19 <= a3) {
                a7 += a19;
                a8.func_150257_a((ITextComponent)a20);
            } else {
                a16 = true;
            }
            if (!a16) continue;
            a9.add(a8);
            a7 = 0;
            a8 = new TextComponentString("");
        }
        a9.add(a8);
        return a9;
    }
}

