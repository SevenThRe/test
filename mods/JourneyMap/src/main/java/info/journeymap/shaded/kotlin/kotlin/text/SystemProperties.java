/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.text;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.JvmField;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/text/SystemProperties;", "", "()V", "LINE_SEPARATOR", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
final class SystemProperties {
    @JvmField
    @NotNull
    public static final String LINE_SEPARATOR;
    public static final SystemProperties INSTANCE;

    private SystemProperties() {
    }

    static {
        SystemProperties systemProperties;
        INSTANCE = systemProperties = new SystemProperties();
        String string = System.getProperty("line.separator");
        if (string == null) {
            Intrinsics.throwNpe();
        }
        LINE_SEPARATOR = string;
    }
}

