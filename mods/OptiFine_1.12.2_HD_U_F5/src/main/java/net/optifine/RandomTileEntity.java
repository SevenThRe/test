/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  anh
 *  avj
 *  et
 */
package net.optifine;

import net.optifine.IRandomEntity;
import net.optifine.util.TileEntityUtils;

public class RandomTileEntity
implements IRandomEntity {
    private avj tileEntity;

    @Override
    public int getId() {
        return Config.getRandom(this.tileEntity.w(), this.tileEntity.v());
    }

    @Override
    public et getSpawnPosition() {
        return this.tileEntity.w();
    }

    @Override
    public String getName() {
        String name = TileEntityUtils.getTileEntityName(this.tileEntity);
        return name;
    }

    @Override
    public anh getSpawnBiome() {
        return this.tileEntity.D().b(this.tileEntity.w());
    }

    @Override
    public int getHealth() {
        return -1;
    }

    @Override
    public int getMaxHealth() {
        return -1;
    }

    public avj getTileEntity() {
        return this.tileEntity;
    }

    public void setTileEntity(avj tileEntity) {
        this.tileEntity = tileEntity;
    }
}

