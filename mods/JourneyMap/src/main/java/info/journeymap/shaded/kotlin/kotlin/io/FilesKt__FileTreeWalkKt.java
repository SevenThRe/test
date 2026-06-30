/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.io;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.io.FileTreeWalk;
import info.journeymap.shaded.kotlin.kotlin.io.FileWalkDirection;
import info.journeymap.shaded.kotlin.kotlin.io.FilesKt;
import info.journeymap.shaded.kotlin.kotlin.io.FilesKt__FileReadWriteKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.File;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0007"}, d2={"walk", "Linfo/journeymap/shaded/kotlin/kotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Linfo/journeymap/shaded/kotlin/kotlin/io/FileWalkDirection;", "walkBottomUp", "walkTopDown", "info.journeymap.shaded.kotlin.kotlin-stdlib"}, xs="info/journeymap/shaded/kotlin/kotlin/io/FilesKt")
class FilesKt__FileTreeWalkKt
extends FilesKt__FileReadWriteKt {
    @NotNull
    public static final FileTreeWalk walk(@NotNull File $this$walk, @NotNull FileWalkDirection direction) {
        Intrinsics.checkParameterIsNotNull($this$walk, "$this$walk");
        Intrinsics.checkParameterIsNotNull((Object)direction, "direction");
        return new FileTreeWalk($this$walk, direction);
    }

    public static /* synthetic */ FileTreeWalk walk$default(File file, FileWalkDirection fileWalkDirection, int n, Object object) {
        if ((n & 1) != 0) {
            fileWalkDirection = FileWalkDirection.TOP_DOWN;
        }
        return FilesKt.walk(file, fileWalkDirection);
    }

    @NotNull
    public static final FileTreeWalk walkTopDown(@NotNull File $this$walkTopDown) {
        Intrinsics.checkParameterIsNotNull($this$walkTopDown, "$this$walkTopDown");
        return FilesKt.walk($this$walkTopDown, FileWalkDirection.TOP_DOWN);
    }

    @NotNull
    public static final FileTreeWalk walkBottomUp(@NotNull File $this$walkBottomUp) {
        Intrinsics.checkParameterIsNotNull($this$walkBottomUp, "$this$walkBottomUp");
        return FilesKt.walk($this$walkBottomUp, FileWalkDirection.BOTTOM_UP);
    }
}

