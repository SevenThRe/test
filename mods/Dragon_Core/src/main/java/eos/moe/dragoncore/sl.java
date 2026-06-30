/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.j;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sl
implements j {
    private Mac y;
    private int k;
    private String ALLATORIxDEMO;

    public sl(String a2) {
        sl a3;
        a3.ALLATORIxDEMO = a2;
        try {
            a3.y = Mac.getInstance(a2);
            a3.k = a3.y.getMacLength();
        }
        catch (NoSuchAlgorithmException a4) {
            throw new RuntimeException(a4);
        }
    }

    @Override
    public byte[] ALLATORIxDEMO(byte[] a2) {
        sl a3;
        return a3.y.doFinal(a2);
    }

    public byte[] ALLATORIxDEMO() {
        sl a2;
        return a2.y.doFinal();
    }

    @Override
    public int ALLATORIxDEMO() {
        sl a2;
        return a2.k;
    }

    @Override
    public void ALLATORIxDEMO(byte[] a2) {
        try {
            sl a3;
            a3.y.init(new SecretKeySpec(a2, a3.ALLATORIxDEMO));
        }
        catch (InvalidKeyException a4) {
            throw new RuntimeException(a4);
        }
    }

    public void c(byte[] a2) {
        try {
            sl a3;
            a3.y.update(a2);
        }
        catch (IllegalStateException a4) {
            throw new RuntimeException(a4);
        }
    }

    public void ALLATORIxDEMO(byte[] a2, int a3, int a4) {
        try {
            sl a5;
            a5.y.update(a2, a3, a4);
        }
        catch (IllegalStateException a6) {
            throw new RuntimeException(a6);
        }
    }
}

