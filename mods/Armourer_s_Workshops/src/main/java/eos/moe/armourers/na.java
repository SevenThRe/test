/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.aa;
import eos.moe.armourers.bg;
import eos.moe.armourers.c;
import eos.moe.armourers.ca;
import eos.moe.armourers.da;
import eos.moe.armourers.dc;
import eos.moe.armourers.fa;
import eos.moe.armourers.kb;
import eos.moe.armourers.oc;
import eos.moe.armourers.pb;
import eos.moe.armourers.rb;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class na
extends da<aa> {
    private char[] m;
    private dc j;

    /*
     * Exception decompiling
     */
    public void r(aa a, rb a) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private /* synthetic */ List<pb> r(pb a2) {
        na a3;
        if (!a2.z()) {
            return Collections.singletonList(a2);
        }
        return bg.r(a3.r().r().r(), a2);
    }

    private /* synthetic */ oc r(pb a2, Charset a3) throws IOException {
        na a4;
        na na2 = a4;
        na2.j = fa.r(na2.r());
        na2.j.r(a2);
        na na3 = a4;
        return new oc(na3.j, na3.m, a3);
    }

    private /* synthetic */ String r(String a2, pb a3, pb a4) {
        if (!c.r(a2)) {
            return a2;
        }
        if (!a3.z()) {
            return a2;
        }
        String string = "/";
        if (a2.endsWith("/")) {
            string = "";
        }
        return a4.r().replaceFirst(a3.r(), new StringBuilder().insert(0, a2).append(string).toString());
    }

    public na(kb a2, char[] a3, ca a4) {
        super(a2, a4);
        na a5;
        a5.m = a3;
    }

    @Override
    public long r(aa a2) {
        na a3;
        return bg.r(a3.r(aa.r(a2)));
    }
}

