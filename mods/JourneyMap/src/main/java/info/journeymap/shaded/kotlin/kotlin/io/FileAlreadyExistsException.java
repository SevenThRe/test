/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.io;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.io.FileSystemException;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.DefaultConstructorMarker;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;
import java.io.File;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/io/FileAlreadyExistsException;", "Linfo/journeymap/shaded/kotlin/kotlin/io/FileSystemException;", "file", "Ljava/io/File;", "other", "reason", "", "(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class FileAlreadyExistsException
extends FileSystemException {
    public FileAlreadyExistsException(@NotNull File file, @Nullable File other, @Nullable String reason) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        super(file, other, reason);
    }

    public /* synthetic */ FileAlreadyExistsException(File file, File file2, String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            file2 = null;
        }
        if ((n & 4) != 0) {
            string = null;
        }
        this(file, file2, string);
    }
}

