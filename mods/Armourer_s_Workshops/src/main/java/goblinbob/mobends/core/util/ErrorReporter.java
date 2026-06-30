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
        header.getStyle().setColor(TextFormatting.YELLOW);
        return header;
    }

    public static void showErrorToPlayer(TextComponentString textComponent) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        TextComponentString base = new TextComponentString("");
        base.getStyle().setColor(TextFormatting.WHITE);
        base.appendSibling((ITextComponent)ErrorReporter.createErrorHeader());
        base.appendSibling((ITextComponent)textComponent);
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)base);
    }

    public static void showErrorToPlayer(String error) {
        ErrorReporter.showErrorToPlayer(new TextComponentString(error));
    }

    public static void showErrorToPlayer(InvalidPackFormatException ex) {
        TextComponentString textComponent = new TextComponentString("A pack has been disabled due to it's wrong format: ");
        TextComponentString packName = new TextComponentString(ex.getPackName());
        packName.getStyle().setBold(Boolean.valueOf(true));
        textComponent.appendSibling((ITextComponent)packName);
        textComponent.appendText(". Check the logs for more details...");
        ErrorReporter.showErrorToPlayer(textComponent);
    }
}

