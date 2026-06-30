/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ba;
import eos.moe.armourers.bg;
import eos.moe.armourers.ca;
import eos.moe.armourers.kb;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.ta;
import eos.moe.armourers.vl;
import java.io.IOException;
import java.nio.charset.Charset;

public class ua
extends ta<ba> {
    private /* synthetic */ void r(kb a2, Charset a3, String a4, rb a5) throws ph {
        if ((a2 = bg.y((kb)a2, a4)) != null) {
            ua a6;
            a6.r((pb)a2, a5, a3);
        }
    }

    @Override
    public long r(ba a2) {
        return 0L;
    }

    /*
     * Exception decompiling
     */
    public void r(ba a, rb a) throws IOException {
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

    public ua(kb a2, char[] a3, vl a4, ca a5) {
        super(a2, a3, a4, a5);
        ua a6;
    }
}

