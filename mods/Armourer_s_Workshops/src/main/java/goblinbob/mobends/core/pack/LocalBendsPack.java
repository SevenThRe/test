/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.stream.JsonReader
 *  javax.annotation.Nullable
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.pack;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import goblinbob.mobends.core.pack.BendsPackMeta;
import goblinbob.mobends.core.pack.IBendsPack;
import goblinbob.mobends.core.pack.PackManager;
import goblinbob.mobends.core.pack.ThumbnailProvider;
import goblinbob.mobends.core.util.BendsPackHelper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class LocalBendsPack
implements IBendsPack {
    private BendsPackMeta metadata;
    private ResourceLocation thumbnailLocation = ThumbnailProvider.DEFAULT_THUMBNAIL_LOCATION;

    public LocalBendsPack(String displayName, String author, String description) {
        this.metadata = new BendsPackMeta();
        this.metadata.key = BendsPackHelper.constructPackName(displayName);
        this.metadata.displayName = displayName;
        this.metadata.author = author;
        this.metadata.description = description;
    }

    public LocalBendsPack(BendsPackMeta metadata) {
        this.metadata = metadata;
    }

    public void rename(String newName) {
        if (this.metadata.key.equals(newName)) {
            return;
        }
        this.metadata.key = newName;
    }

    public LocalBendsPack setThumbnailURL(String thumbnailURL) {
        this.thumbnailLocation = PackManager.INSTANCE.getThumbnailLocation(this.metadata.key, thumbnailURL);
        return this;
    }

    @Override
    public String getKey() {
        return this.metadata.key;
    }

    @Override
    public String getDisplayName() {
        return this.metadata.displayName;
    }

    @Override
    public String getAuthor() {
        return this.metadata.author;
    }

    @Override
    public String getDescription() {
        return this.metadata.description;
    }

    @Override
    @Nullable
    public ResourceLocation getThumbnail() {
        return this.thumbnailLocation;
    }

    @Override
    public boolean canPackBeEdited() {
        return true;
    }

    public static LocalBendsPack readFromFile(File file) throws IOException {
        JsonReader fileReader = new JsonReader((Reader)new FileReader(file));
        BendsPackMeta meta = (BendsPackMeta)new Gson().fromJson(fileReader, BendsPackMeta.class);
        return new LocalBendsPack(meta);
    }
}

