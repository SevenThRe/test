/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aow
 *  aox
 *  awt
 *  bsb
 *  buy
 *  bxo
 *  bxr
 *  et
 *  et$a
 *  fa
 *  rk
 *  vg
 */
package net.optifine;

import java.util.HashSet;
import java.util.Set;
import net.optifine.DynamicLights;

public class DynamicLight {
    private vg entity = null;
    private double offsetY = 0.0;
    private double lastPosX = -2.147483648E9;
    private double lastPosY = -2.147483648E9;
    private double lastPosZ = -2.147483648E9;
    private int lastLightLevel = 0;
    private boolean underwater = false;
    private long timeCheckMs = 0L;
    private Set<et> setLitChunkPos = new HashSet<et>();
    private et.a blockPosMutable = new et.a();

    public DynamicLight(vg entity) {
        this.entity = entity;
        this.offsetY = entity.by();
    }

    public void update(buy renderGlobal) {
        if (Config.isDynamicLightsFast()) {
            long timeNowMs = System.currentTimeMillis();
            if (timeNowMs < this.timeCheckMs + 500L) {
                return;
            }
            this.timeCheckMs = timeNowMs;
        }
        double posX = this.entity.p - 0.5;
        double posY = this.entity.q - 0.5 + this.offsetY;
        double posZ = this.entity.r - 0.5;
        int lightLevel = DynamicLights.getLightLevel(this.entity);
        double dx = posX - this.lastPosX;
        double dy = posY - this.lastPosY;
        double dz = posZ - this.lastPosZ;
        double delta = 0.1;
        if (Math.abs(dx) <= delta && Math.abs(dy) <= delta && Math.abs(dz) <= delta && this.lastLightLevel == lightLevel) {
            return;
        }
        this.lastPosX = posX;
        this.lastPosY = posY;
        this.lastPosZ = posZ;
        this.lastLightLevel = lightLevel;
        this.underwater = false;
        bsb world = renderGlobal.getWorld();
        if (world != null) {
            this.blockPosMutable.c(rk.c((double)posX), rk.c((double)posY), rk.c((double)posZ));
            awt state = world.o((et)this.blockPosMutable);
            aow block = state.u();
            this.underwater = block == aox.j;
        }
        HashSet<et> setNewPos = new HashSet<et>();
        if (lightLevel > 0) {
            fa dirX = (rk.c((double)posX) & 0xF) >= 8 ? fa.f : fa.e;
            fa dirY = (rk.c((double)posY) & 0xF) >= 8 ? fa.b : fa.a;
            fa dirZ = (rk.c((double)posZ) & 0xF) >= 8 ? fa.d : fa.c;
            et chunkPos = new et(posX, posY, posZ);
            bxr chunk = renderGlobal.getRenderChunk(chunkPos);
            et chunkPosX = this.getChunkPos(chunk, chunkPos, dirX);
            bxr chunkX = renderGlobal.getRenderChunk(chunkPosX);
            et chunkPosZ = this.getChunkPos(chunk, chunkPos, dirZ);
            bxr chunkZ = renderGlobal.getRenderChunk(chunkPosZ);
            et chunkPosXZ = this.getChunkPos(chunkX, chunkPosX, dirZ);
            bxr chunkXZ = renderGlobal.getRenderChunk(chunkPosXZ);
            et chunkPosY = this.getChunkPos(chunk, chunkPos, dirY);
            bxr chunkY = renderGlobal.getRenderChunk(chunkPosY);
            et chunkPosYX = this.getChunkPos(chunkY, chunkPosY, dirX);
            bxr chunkYX = renderGlobal.getRenderChunk(chunkPosYX);
            et chunkPosYZ = this.getChunkPos(chunkY, chunkPosY, dirZ);
            bxr chunkYZ = renderGlobal.getRenderChunk(chunkPosYZ);
            et chunkPosYXZ = this.getChunkPos(chunkYX, chunkPosYX, dirZ);
            bxr chunkYXZ = renderGlobal.getRenderChunk(chunkPosYXZ);
            this.updateChunkLight(chunk, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkX, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkZ, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkXZ, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkY, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkYX, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkYZ, this.setLitChunkPos, setNewPos);
            this.updateChunkLight(chunkYXZ, this.setLitChunkPos, setNewPos);
        }
        this.updateLitChunks(renderGlobal);
        this.setLitChunkPos = setNewPos;
    }

    private et getChunkPos(bxr renderChunk, et pos, fa facing) {
        if (renderChunk != null) {
            return renderChunk.a(facing);
        }
        return pos.a(facing, 16);
    }

    private void updateChunkLight(bxr renderChunk, Set<et> setPrevPos, Set<et> setNewPos) {
        if (renderChunk == null) {
            return;
        }
        bxo compiledChunk = renderChunk.h();
        if (compiledChunk != null && !compiledChunk.a()) {
            renderChunk.a(false);
        }
        et pos = renderChunk.k().h();
        if (setPrevPos != null) {
            setPrevPos.remove(pos);
        }
        if (setNewPos != null) {
            setNewPos.add(pos);
        }
    }

    public void updateLitChunks(buy renderGlobal) {
        for (et posOld : this.setLitChunkPos) {
            bxr chunkOld = renderGlobal.getRenderChunk(posOld);
            this.updateChunkLight(chunkOld, null, null);
        }
    }

    public vg getEntity() {
        return this.entity;
    }

    public double getLastPosX() {
        return this.lastPosX;
    }

    public double getLastPosY() {
        return this.lastPosY;
    }

    public double getLastPosZ() {
        return this.lastPosZ;
    }

    public int getLastLightLevel() {
        return this.lastLightLevel;
    }

    public boolean isUnderwater() {
        return this.underwater;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public String toString() {
        return "Entity: " + this.entity + ", offsetY: " + this.offsetY;
    }
}

