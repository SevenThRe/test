/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 */
package journeymap.common.kotlin.extensions;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0003"}, d2={"getResourceAsStream", "Ljava/io/InputStream;", "Lnet/minecraft/util/ResourceLocation;", "journeymap"})
public final class ResourcesKt {
    @NotNull
    public static final InputStream getResourceAsStream(@NotNull ResourceLocation $this$getResourceAsStream) {
        Intrinsics.checkParameterIsNotNull($this$getResourceAsStream, "$this$getResourceAsStream");
        Minecraft minecraft = Minecraft.func_71410_x();
        Intrinsics.checkExpressionValueIsNotNull(minecraft, "Minecraft.getMinecraft()");
        IResource iResource = minecraft.func_110442_L().func_110536_a($this$getResourceAsStream);
        Intrinsics.checkExpressionValueIsNotNull(iResource, "Minecraft.getMinecraft()\u2026Manager.getResource(this)");
        InputStream inputStream = iResource.func_110527_b();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "Minecraft.getMinecraft()\u2026esource(this).inputStream");
        return inputStream;
    }
}

