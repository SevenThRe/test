/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.m;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gf
implements m {
    private String s;
    private Mac m;
    private int j;

    @Override
    public byte[] r(byte[] a2) {
        gf a3;
        return a3.m.doFinal(a2);
    }

    @Override
    public int r() {
        gf a2;
        return a2.j;
    }

    public void r(byte[] a22, int a3, int a4) {
        try {
            gf a5;
            a5.m.update(a22, a3, a4);
            return;
        }
        catch (IllegalStateException a22) {
            throw new RuntimeException(a22);
        }
    }

    @Override
    public void r(byte[] a22) {
        try {
            gf a3;
            a3.m.init(new SecretKeySpec(a22, a3.s));
            return;
        }
        catch (InvalidKeyException a22) {
            throw new RuntimeException(a22);
        }
    }

    public byte[] r() {
        gf a2;
        return a2.m.doFinal();
    }

    public void y(byte[] a22) {
        try {
            gf a3;
            a3.m.update(a22);
            return;
        }
        catch (IllegalStateException a22) {
            throw new RuntimeException(a22);
        }
    }

    public gf(String a22) {
        gf a3;
        a3.s = a22;
        try {
            a3.m = Mac.getInstance(a22);
            a3.j = a3.m.getMacLength();
            return;
        }
        catch (NoSuchAlgorithmException a22) {
            throw new RuntimeException(a22);
        }
    }
}

