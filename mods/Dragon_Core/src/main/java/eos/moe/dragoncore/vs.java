/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ChatAllowedCharacters
 */
package eos.moe.dragoncore;

import net.minecraft.util.ChatAllowedCharacters;

public class vs {
    public vs() {
        vs a2;
    }

    public static String ALLATORIxDEMO(String a2, boolean a3) {
        char[] a4;
        StringBuilder a5 = new StringBuilder();
        for (char a6 : a4 = a2.toCharArray()) {
            if (!ChatAllowedCharacters.isAllowedCharacter((char)a6) && (!a3 || a6 != '\u00a7' && a6 != '\n')) continue;
            a5.append(a6);
        }
        return a5.toString();
    }
}

