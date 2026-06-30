/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.pack.InvalidPackFormatException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ErrorReporter {
    public static TextComponentString createErrorHeader() {
        TextComponentString header = new TextComponentString("[Mo' Bends] ");
        header.func_150256_b().func_150238_a(TextFormatting.YELLOW);
        return header;
    }

    public static void showErrorToPlayer(TextComponentString textComponent) {
        if (Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        TextComponentString base = new TextComponentString("");
        base.func_150256_b().func_150238_a(TextFormatting.WHITE);
        base.func_150257_a((ITextComponent)ErrorReporter.createErrorHeader());
        base.func_150257_a((ITextComponent)textComponent);
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)base);
    }

    public static void showErrorToPlayer(String error) {
        ErrorReporter.showErrorToPlayer(new TextComponentString(error));
    }

    public static void showErrorToPlayer(InvalidPackFormatException ex) {
        TextComponentString textComponent = new TextComponentString("A pack has been disabled due to it's wrong format: ");
        TextComponentString packName = new TextComponentString(ex.getPackName());
        packName.func_150256_b().func_150227_a(Boolean.valueOf(true));
        textComponent.func_150257_a((ITextComponent)packName);
        textComponent.func_150258_a(". Check the logs for more details...");
        ErrorReporter.showErrorToPlayer(textComponent);
    }
}

