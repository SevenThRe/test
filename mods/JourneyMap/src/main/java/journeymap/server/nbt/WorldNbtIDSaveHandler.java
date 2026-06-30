/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldSavedData
 */
package journeymap.server.nbt;

import java.util.UUID;
import journeymap.common.Journeymap;
import journeymap.server.Constants;
import journeymap.server.nbt.NBTWorldSaveDataHandler;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class WorldNbtIDSaveHandler {
    private static final String DAT_FILE = "WorldUUID";
    private static final String WORLD_ID_KEY = "world_uuid";
    private NBTWorldSaveDataHandler data;
    private World world;

    public WorldNbtIDSaveHandler() {
        try {
            this.world = Constants.SERVER.getEntityWorld();
            this.data = (NBTWorldSaveDataHandler)this.world.getPerWorldStorage().getOrLoadData(NBTWorldSaveDataHandler.class, DAT_FILE);
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Error in worldID handler", (Throwable)e);
        }
    }

    public String getWorldID() {
        return this.getNBTWorldID();
    }

    private String getNBTWorldID() {
        if (this.data == null) {
            return this.createNewWorldID();
        }
        if (this.data.getData().hasKey(WORLD_ID_KEY)) {
            return this.data.getData().getString(WORLD_ID_KEY);
        }
        return "noWorldIDFound";
    }

    private String createNewWorldID() {
        String worldID = UUID.randomUUID().toString();
        this.data = new NBTWorldSaveDataHandler(DAT_FILE);
        this.world.getPerWorldStorage().setData(WORLD_ID_KEY, (WorldSavedData)this.data);
        this.saveWorldID(worldID);
        return worldID;
    }

    private void saveWorldID(String worldID) {
        if (this.data != null) {
            this.data.getData().setString(WORLD_ID_KEY, worldID);
            this.data.markDirty();
        }
    }
}

