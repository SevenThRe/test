/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nl;
import java.io.File;
import org.apache.commons.io.FileUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ye {
    private File o;
    private File y;
    private String k;
    private String ALLATORIxDEMO;

    public ye(File a2, File a3, String a4, String a5) {
        ye a6;
        a6.o = a2;
        a6.y = a3;
        a6.k = a4;
        a6.ALLATORIxDEMO = a5;
    }

    public File c() {
        ye a2;
        return a2.o;
    }

    public void c(File a2) {
        a.o = a2;
    }

    public File ALLATORIxDEMO() {
        ye a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(File a2) {
        a.y = a2;
    }

    public String c() {
        ye a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(String a2) {
        a.ALLATORIxDEMO = a2;
    }

    public String ALLATORIxDEMO() {
        ye a2;
        return a2.k;
    }

    public byte[] ALLATORIxDEMO() {
        ye a2;
        try {
            byte[] a3 = FileUtils.readFileToByteArray((File)a2.y);
            return nl.ALLATORIxDEMO(a3);
        }
        catch (Exception a4) {
            System.out.println("\u65e0\u6cd5\u83b7\u53d6\u6587\u4ef6\u6570\u636e,\u4e5f\u8bb8\u4f60\u4fee\u6539\u4e86config.yml\u5bc6\u7801->" + a2.k);
            a4.printStackTrace();
            return null;
        }
    }
}

