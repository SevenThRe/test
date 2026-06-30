/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ib;
import eos.moe.armourers.jb;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;

public class xa {
    private static final int j = 16384;

    public xa() {
        xa a2;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static long r(File a, rb a) throws IOException {
        block17: {
            block15: {
                block16: {
                    if (a == null) throw new ph("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
                    if (a.exists() == false) throw new ph("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
                    if (!a.canRead()) {
                        throw new ph("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
                    }
                    var2_3 = new byte[16384];
                    var3_4 = new CRC32();
                    var4_5 = new FileInputStream(a);
                    var5_6 = null;
                    try {
                        while ((var6_7 = var4_5.read(var2_3)) != -1) {
                            var3_4.update(var2_3, 0, var6_7);
                            if (a == null) continue;
                            v0 = a;
                            v0.r((long)var6_7);
                            if (!v0.r()) continue;
                            v1 = a;
                            v1.r(jb.s);
                            v1.r(ib.j);
                            var7_9 = 0L;
                            if (var4_5 == null) return var7_9;
                            if (var5_6 == null) break block15;
                            break block16;
                        }
                        ** GOTO lbl-1000
                    }
                    catch (Throwable var6_8) {
                        try {
                            var5_6 = var6_8;
                            throw var5_6;
                        }
                        catch (Throwable var10_13) {
                            if (var4_5 != null) {
                                if (var5_6 != null) {
                                    try {
                                        var4_5.close();
                                        v2 = var10_13;
                                        throw v2;
                                    }
                                    catch (Throwable a) {
                                        v2 = var10_13;
                                        var5_6.addSuppressed(a);
                                        throw v2;
                                    }
                                }
                                var4_5.close();
                            }
                            v2 = var10_13;
                            throw v2;
                        }
                    }
                }
                try {
                    var4_5.close();
                    return var7_9;
                }
                catch (Throwable var9_11) {
                    var5_6.addSuppressed(var9_11);
                    return var7_9;
                }
            }
            var4_5.close();
            return var7_9;
lbl-1000:
            // 1 sources

            {
                var7_10 = var3_4.getValue();
                if (var4_5 == null) return var7_10;
                if (var5_6 == null) break block17;
            }
            try {
                var4_5.close();
                return var7_10;
            }
            catch (Throwable var9_12) {
                var5_6.addSuppressed(var9_12);
                return var7_10;
            }
        }
        var4_5.close();
        return var7_10;
    }
}

