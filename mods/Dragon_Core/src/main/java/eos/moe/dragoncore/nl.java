/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.wq;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class nl {
    private static final String o = "44878013";
    private static final String y = "DES";
    private static final String k = "DES/CBC/PKCS5Padding";
    private static final String ALLATORIxDEMO = "utf-8";

    public nl() {
        nl a2;
    }

    private static /* synthetic */ Key ALLATORIxDEMO() throws Exception {
        DESKeySpec a2 = new DESKeySpec(nl.ALLATORIxDEMO());
        SecretKeyFactory a3 = SecretKeyFactory.getInstance(y);
        return a3.generateSecret(a2);
    }

    private static /* synthetic */ byte[] ALLATORIxDEMO() {
        byte[] a2 = wq.k;
        if (a2.length < 8) {
            byte[] a3 = new byte[8];
            System.arraycopy(a2, 0, a3, 0, a2.length);
            a2 = a3;
        }
        return a2;
    }

    public static byte[] c(byte[] a2) throws Exception {
        if (a2 == null) {
            return null;
        }
        Key a3 = nl.ALLATORIxDEMO();
        Cipher a4 = Cipher.getInstance(k);
        IvParameterSpec a5 = new IvParameterSpec(o.getBytes(ALLATORIxDEMO));
        a4.init(1, a3, a5);
        return a4.doFinal(a2);
    }

    public static byte[] ALLATORIxDEMO(byte[] a2) throws Exception {
        if (a2 == null) {
            return null;
        }
        Key a3 = nl.ALLATORIxDEMO();
        Cipher a4 = Cipher.getInstance(k);
        IvParameterSpec a5 = new IvParameterSpec(o.getBytes(ALLATORIxDEMO));
        a4.init(2, a3, a5);
        return a4.doFinal(a2);
    }
}

