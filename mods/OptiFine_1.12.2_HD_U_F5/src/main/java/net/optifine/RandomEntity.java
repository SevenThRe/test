/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  anh
 *  et
 *  vg
 *  vq
 */
package net.optifine;

import java.util.UUID;
import net.optifine.IRandomEntity;

public class RandomEntity
implements IRandomEntity {
    private vg entity;

    @Override
    public int getId() {
        UUID uuid = this.entity.bm();
        long uuidLow = uuid.getLeastSignificantBits();
        int id = (int)(uuidLow & Integer.MAX_VALUE);
        return id;
    }

    @Override
    public et getSpawnPosition() {
        return this.entity.V().spawnPosition;
    }

    @Override
    public anh getSpawnBiome() {
        return this.entity.V().spawnBiome;
    }

    @Override
    public String getName() {
        if (this.entity.n_()) {
            return this.entity.bq();
        }
        return null;
    }

    @Override
    public int getHealth() {
        if (!(this.entity instanceof vq)) {
            return 0;
        }
        vq el = (vq)this.entity;
        return (int)el.cd();
    }

    @Override
    public int getMaxHealth() {
        if (!(this.entity instanceof vq)) {
            return 0;
        }
        vq el = (vq)this.entity;
        return (int)el.cj();
    }

    public vg getEntity() {
        return this.entity;
    }

    public void setEntity(vg entity) {
        this.entity = entity;
    }
}

