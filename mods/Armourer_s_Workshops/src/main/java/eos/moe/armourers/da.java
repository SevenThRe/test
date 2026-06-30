/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.ca;
import eos.moe.armourers.fa;
import eos.moe.armourers.ga;
import eos.moe.armourers.kb;
import eos.moe.armourers.oa;
import eos.moe.armourers.ob;
import eos.moe.armourers.oc;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.qb;
import eos.moe.armourers.rb;
import eos.moe.armourers.wa;
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
public abstract class da<T>
extends oa<T> {
    private kb m;
    private byte[] j;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private /* synthetic */ void y(oc a222, pb a3, File a4, rb a52) throws IOException {
        da a6;
        a222 = new String(a6.r((oc)a222, (pb)a3, (rb)a52));
        if (!((File)a4).getParentFile().exists() && !((File)a4).getParentFile().mkdirs()) {
            throw new ph("Could not create parent directories");
        }
        try {
            a52 = Paths.get((String)a222, new String[0]);
            Files.createSymbolicLink(((File)a4).toPath(), (Path)a52, new FileAttribute[0]);
            fa.r((pb)a3, (File)a4);
            return;
        }
        catch (NoSuchMethodError a52) {
            block13: {
                a3 = new FileOutputStream((File)a4);
                a4 = null;
                try {
                    ((OutputStream)a3).write(((String)a222).getBytes());
                    if (a3 == null) return;
                    if (a4 == null) break block13;
                }
                catch (Throwable a222) {
                    try {
                        a4 = a222;
                        throw a4;
                    }
                    catch (Throwable throwable) {
                        Throwable throwable2;
                        if (a3 != null) {
                            if (a4 != null) {
                                try {
                                    ((OutputStream)a3).close();
                                    throwable2 = throwable;
                                    throw throwable2;
                                }
                                catch (Throwable a222) {
                                    throwable2 = throwable;
                                    ((Throwable)a4).addSuppressed(a222);
                                    throw throwable2;
                                }
                            }
                            ((OutputStream)a3).close();
                        }
                        throwable2 = throwable;
                        throw throwable2;
                    }
                }
                try {
                    ((OutputStream)a3).close();
                    return;
                }
                catch (Throwable a222) {
                    ((Throwable)a4).addSuppressed(a222);
                    return;
                }
            }
            ((OutputStream)a3).close();
            return;
        }
    }

    public da(kb a2, ca a3) {
        da a4;
        da da2 = a4;
        super(a3);
        da2.j = new byte[4096];
        da2.m = a2;
    }

    private /* synthetic */ void r(oc a2, pb a3) throws IOException {
        if (wa.r(a3.y()[0], 6)) {
            throw new ph(new StringBuilder().insert(0, "Entry with name ").append(a3.r()).append(" is encrypted with Strong Encryption. Zip4j does not support Strong Encryption, as this is patented.").toString());
        }
        if ((a2 = ((oc)a2).r(a3)) == null) {
            throw new ph(new StringBuilder().insert(0, "Could not read corresponding local file header for file header: ").append(a3.r()).toString());
        }
        if (!a3.r().equals(((qb)a2).r())) {
            throw new ph("File header and local file header mismatch");
        }
    }

    private /* synthetic */ File r(pb a2, String a3, String a4) {
        da a5;
        String string = c.r(a4) ? a4 : a5.r(a2.r());
        return new File(new StringBuilder().insert(0, a3).append(ga.e).append(string).toString());
    }

    @Override
    public ob r() {
        return ob.c;
    }

    public void r(oc a2, pb a3, String a4, String a5, rb a6) throws IOException {
        da a7;
        if (!a4.endsWith(ga.e)) {
            a4 = new StringBuilder().insert(0, a4).append(ga.e).toString();
        }
        a5 = a7.r(a3, a4, (String)a5);
        a6.r(((File)a5).getAbsolutePath());
        a4 = new StringBuilder().insert(0, new File(a4).getCanonicalPath()).append(File.separator).toString();
        if (!((File)a5).getCanonicalPath().startsWith(a4)) {
            throw new ph(new StringBuilder().insert(0, "illegal file name that breaks out of the target directory: ").append(a3.r()).toString());
        }
        a7.r(a2, a3);
        if (a3.z()) {
            if (!((File)a5).exists() && !((File)a5).mkdirs()) {
                throw new ph(new StringBuilder().insert(0, "Could not create directory: ").append(a5).toString());
            }
        } else {
            if (a7.r(a3)) {
                a7.y(a2, a3, (File)a5, a6);
                return;
            }
            a7.r((File)a5);
            a7.r(a2, a3, (File)a5, a6);
        }
    }

    private /* synthetic */ byte[] r(oc a2, pb a3, rb a4) throws IOException {
        if (a2.read((byte[])(a3 = (Object)new byte[(int)((qb)a3).r()])) != ((Object)a3).length) {
            throw new ph("Could not read complete entry");
        }
        a4.r((long)((Object)a3).length);
        return a3;
    }

    @Override
    private /* synthetic */ void r(File a2) throws ph {
        if (!a2.getParentFile().exists() && !a2.getParentFile().mkdirs()) {
            throw new ph(new StringBuilder().insert(0, "Unable to create parent directories: ").append(a2.getParentFile()).toString());
        }
    }

    public kb r() {
        da a2;
        return a2.m;
    }

    private /* synthetic */ boolean r(pb a2) {
        byte[] byArray = ((pb)a2).z();
        a2 = byArray;
        if (byArray == null || ((Object)a2).length < 4) {
            return false;
        }
        return wa.r((byte)a2[3], 5);
    }

    private /* synthetic */ String r(String a2) {
        return a2.replaceAll("[/\\\\]", Matcher.quoteReplacement(ga.e));
    }

    /*
     * Exception decompiling
     */
    private /* synthetic */ void r(oc a, pb a, File a, rb a) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [7[CATCHBLOCK], 2[TRYBLOCK]], but top level block is 4[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }
}

