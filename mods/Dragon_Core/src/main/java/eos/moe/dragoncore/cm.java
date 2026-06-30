/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.translation.I18n
 */
package eos.moe.dragoncore;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import net.minecraft.util.text.translation.I18n;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class cm {
    public cm() {
        cm a2;
    }

    public static String ALLATORIxDEMO(String a2) {
        int a3;
        StringBuilder a4 = new StringBuilder(a2.length());
        block3: for (int a5 = 0; a5 < a2.length(); a5 += Character.charCount(a3)) {
            a3 = a2.codePointAt(a5);
            switch (Character.getType(a3)) {
                case 0: 
                case 16: 
                case 18: 
                case 19: {
                    continue block3;
                }
            }
            a4.append(Character.toChars(a3));
        }
        return a4.toString();
    }

    public static void ALLATORIxDEMO(String a4) {
        StringSelection a5 = new StringSelection(a4);
        Clipboard a6 = Toolkit.getDefaultToolkit().getSystemClipboard();
        a6.setContents(a5, (a2, a3) -> {});
    }

    public static String ALLATORIxDEMO() {
        boolean a2;
        String a3 = "";
        Clipboard a4 = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable a5 = a4.getContents(null);
        boolean bl2 = a2 = a5 != null && a5.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (a2) {
            try {
                a3 = (String)a5.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception a6) {
                a6.printStackTrace();
            }
        }
        return cm.ALLATORIxDEMO(a3);
    }

    public static String ALLATORIxDEMO(Object ... a2) {
        String a3 = "";
        for (Object a4 : a2) {
            a3 = a3 + I18n.translateToLocal((String)a4.toString());
        }
        return a3;
    }

    public static int ALLATORIxDEMO(String a2, int a3) {
        try {
            return Integer.parseInt(a2);
        }
        catch (NumberFormatException a4) {
            return a3;
        }
    }
}

