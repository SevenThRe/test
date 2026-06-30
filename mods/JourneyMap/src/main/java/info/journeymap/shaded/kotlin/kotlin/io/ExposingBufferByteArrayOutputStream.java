/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.io;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.ByteArrayOutputStream;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/io/ExposingBufferByteArrayOutputStream;", "Ljava/io/ByteArrayOutputStream;", "size", "", "(I)V", "buffer", "", "getBuffer", "()[B", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
final class ExposingBufferByteArrayOutputStream
extends ByteArrayOutputStream {
    @NotNull
    public final byte[] getBuffer() {
        Intrinsics.checkExpressionValueIsNotNull(this.buf, "buf");
        return this.buf;
    }

    public ExposingBufferByteArrayOutputStream(int size) {
        super(size);
    }
}

