/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class bz {
    public bz() {
        bz a2;
    }

    public static <T> T[] ALLATORIxDEMO(T[] a2, Object ... a3) {
        ArrayList<Object> a4 = new ArrayList<Object>();
        for (Object a5 : a3) {
            if (a5.getClass().isArray()) {
                a4.addAll(Arrays.asList((Object[])a5));
                continue;
            }
            a4.add(a5);
        }
        return a4.toArray(a2);
    }

    public static String ALLATORIxDEMO(InputStream a2) throws IOException {
        return bz.ALLATORIxDEMO(new InputStreamReader(a2, StandardCharsets.UTF_8));
    }

    /*
     * WARNING - void declaration
     */
    private static /* synthetic */ String ALLATORIxDEMO(Reader a2) throws IOException {
        Throwable throwable = null;
        try (BufferedReader a3 = new BufferedReader(a2);){
            String a4 = a3.readLine();
            StringBuilder a5 = new StringBuilder();
            while (a4 != null) {
                if (a5.length() != 0) {
                    a5.append("\n");
                }
                a5.append(a4);
                a4 = a3.readLine();
            }
            String string = a5.toString();
            return string;
        }
        catch (Throwable a4) {
            void var3_4;
            throwable = var3_4;
            throw var3_4;
        }
    }
}

