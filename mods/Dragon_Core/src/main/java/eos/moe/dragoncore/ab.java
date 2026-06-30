/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.ob;
import eos.moe.dragoncore.qa;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.tf;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.xb;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ab<T>
extends ob<T> {
    private dc k;
    private byte[] ALLATORIxDEMO = new byte[4096];

    public ab(dc a2, jb a3) {
        super(a3);
        ab a4;
        a4.k = a2;
    }

    public void ALLATORIxDEMO(tf a2, ec a3, String a4, String a5, uc a6) throws IOException {
        ab a7;
        if (!a4.endsWith(la.b)) {
            a4 = a4 + la.b;
        }
        File a8 = a7.ALLATORIxDEMO(a3, a4, a5);
        a6.ALLATORIxDEMO(a8.getAbsolutePath());
        String a9 = new File(a4).getCanonicalPath() + File.separator;
        if (!a8.getCanonicalPath().startsWith(a9)) {
            throw new yk("illegal file name that breaks out of the target directory: " + a3.ALLATORIxDEMO());
        }
        a7.ALLATORIxDEMO(a2, a3);
        if (a3.ALLATORIxDEMO()) {
            if (!a8.exists() && !a8.mkdirs()) {
                throw new yk("Could not create directory: " + a8);
            }
        } else if (a7.ALLATORIxDEMO(a3)) {
            a7.ALLATORIxDEMO(a2, a3, a8, a6);
        } else {
            a7.ALLATORIxDEMO(a8);
            a7.c(a2, a3, a8, a6);
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ec a2) {
        byte[] a3 = a2.f();
        if (a3 == null || a3.length < 4) {
            return false;
        }
        return xb.ALLATORIxDEMO(a3[3], 5);
    }

    private /* synthetic */ void c(tf a2, ec a3, File a4, uc a5) throws IOException {
        try (FileOutputStream a6 = new FileOutputStream(a4);){
            ab a7;
            int a8;
            while ((a8 = a2.read(a7.ALLATORIxDEMO)) != -1) {
                ((OutputStream)a6).write(a7.ALLATORIxDEMO, 0, a8);
                a5.c(a8);
                a7.ALLATORIxDEMO();
            }
        }
        catch (Exception a9) {
            if (a4.exists()) {
                a4.delete();
            }
            throw a9;
        }
        qa.ALLATORIxDEMO(a3, a4);
    }

    private /* synthetic */ void ALLATORIxDEMO(tf a2, ec a3, File a4, uc a5) throws IOException {
        ab a6;
        String a7 = new String(a6.ALLATORIxDEMO(a2, a3, a5));
        if (!a4.getParentFile().exists() && !a4.getParentFile().mkdirs()) {
            throw new yk("Could not create parent directories");
        }
        try {
            Path a8 = Paths.get(a7, new String[0]);
            Files.createSymbolicLink(a4.toPath(), a8, new FileAttribute[0]);
            qa.ALLATORIxDEMO(a3, a4);
        }
        catch (NoSuchMethodError a9) {
            try (FileOutputStream a10 = new FileOutputStream(a4);){
                ((OutputStream)a10).write(a7.getBytes());
            }
        }
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(tf a2, ec a3, uc a4) throws IOException {
        byte[] a5 = new byte[(int)a3.ALLATORIxDEMO()];
        int a6 = a2.read(a5);
        if (a6 != a5.length) {
            throw new yk("Could not read complete entry");
        }
        a4.c(a5.length);
        return a5;
    }

    private /* synthetic */ void ALLATORIxDEMO(tf a2, ec a3) throws IOException {
        if (xb.ALLATORIxDEMO(a3.c()[0], 6)) {
            throw new yk("Entry with name " + a3.ALLATORIxDEMO() + " is encrypted with Strong Encryption. Zip4j does not support Strong Encryption, as this is patented.");
        }
        hc a4 = a2.ALLATORIxDEMO(a3);
        if (a4 == null) {
            throw new yk("Could not read corresponding local file header for file header: " + a3.ALLATORIxDEMO());
        }
        if (!a3.ALLATORIxDEMO().equals(a4.ALLATORIxDEMO())) {
            throw new yk("File header and local file header mismatch");
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(File a2) throws yk {
        if (!a2.getParentFile().exists() && !a2.getParentFile().mkdirs()) {
            throw new yk("Unable to create parent directories: " + a2.getParentFile());
        }
    }

    private /* synthetic */ File ALLATORIxDEMO(ec a2, String a3, String a4) {
        ab a5;
        String a6 = ta.ALLATORIxDEMO(a4) ? a4 : a5.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        return new File(a3 + la.b + a6);
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2) {
        return a2.replaceAll("[/\\\\]", Matcher.quoteReplacement(la.b));
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.b;
    }

    public dc ALLATORIxDEMO() {
        ab a2;
        return a2.k;
    }
}

