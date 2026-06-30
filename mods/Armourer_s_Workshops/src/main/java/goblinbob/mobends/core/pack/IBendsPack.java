/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.pack;

import net.minecraft.util.ResourceLocation;

public interface IBendsPack {
    public String getKey();

    public String getDisplayName();

    public String getAuthor();

    public String getDescription();

    public ResourceLocation getThumbnail();

    public boolean canPackBeEdited();
}

