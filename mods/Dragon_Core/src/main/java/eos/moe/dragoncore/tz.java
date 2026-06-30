/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.wq;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class tz {
    private static final String q = "48780139";
    private static final String b = "DES";
    private static final String o = "DES/CBC/PKCS5Padding";
    private static final String y = "utf-8";
    private static Key k;
    private static byte[] ALLATORIxDEMO;

    public tz() {
        tz a2;
    }

    private static /* synthetic */ Key ALLATORIxDEMO() throws Exception {
        byte[] a2 = wq.k;
        if (k == null || !Arrays.equals(a2, ALLATORIxDEMO)) {
            DESKeySpec a3 = new DESKeySpec(tz.ALLATORIxDEMO());
            SecretKeyFactory a4 = SecretKeyFactory.getInstance(b);
            k = a4.generateSecret(a3);
            ALLATORIxDEMO = a2;
        }
        return k;
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
        Key a3 = tz.ALLATORIxDEMO();
        Cipher a4 = Cipher.getInstance(o);
        IvParameterSpec a5 = new IvParameterSpec(q.getBytes(y));
        a4.init(2, a3, a5);
        return a4.doFinal(a2);
    }

    public static byte[] ALLATORIxDEMO(byte[] a2) throws Exception {
        if (a2 == null) {
            return null;
        }
        Key a3 = tz.ALLATORIxDEMO();
        Cipher a4 = Cipher.getInstance(o);
        IvParameterSpec a5 = new IvParameterSpec(q.getBytes(y));
        a4.init(1, a3, a5);
        return a4.doFinal(a2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static InputStream ALLATORIxDEMO(InputStream a2) {
        try {
            int a3;
            ByteArrayOutputStream a4 = new ByteArrayOutputStream();
            byte[] a5 = new byte[10240];
            while ((a3 = a2.read(a5)) > 0) {
                a4.write(a5, 0, a3);
            }
            byte[] a6 = a4.toByteArray();
            a4.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tz.c(a6));
            return byteArrayInputStream;
        }
        catch (Exception a7) {
            a7.printStackTrace();
            InputStream inputStream = null;
            return inputStream;
        }
        finally {
            try {
                a2.close();
            }
            catch (IOException iOException) {}
        }
    }
}

