/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.WorldProviderHell
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import journeymap.client.data.DataCache;
import journeymap.client.log.JMLogger;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.EntityDTO;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.FMLClientHandler;

public class PlayerData
extends CacheLoader<Class, EntityDTO> {
    public static boolean playerIsUnderground(Minecraft mc, EntityPlayer player) {
        if (player.func_130014_f_().field_73011_w instanceof WorldProviderHell) {
            return true;
        }
        int posX = MathHelper.func_76128_c((double)player.field_70165_t);
        int posY = MathHelper.func_76128_c((double)player.func_174813_aQ().field_72338_b);
        int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
        boolean offset = true;
        boolean isUnderground = false;
        if (posY < 0) {
            return true;
        }
        int y = posY;
        block0: for (int x = posX - 1; x <= posX + 1; ++x) {
            for (int z = posZ - 1; z <= posZ + 1; ++z) {
                y = posY + 1;
                ChunkMD chunkMD = DataCache.INSTANCE.getChunkMD(new ChunkPos(x >> 4, z >> 4));
                if (chunkMD == null) continue;
                if (chunkMD.ceiling(x & 0xF, z & 0xF) <= y) {
                    isUnderground = false;
                    break block0;
                }
                isUnderground = true;
            }
        }
        return isUnderground;
    }

    public EntityDTO load(Class aClass) throws Exception {
        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityPlayerSP player = mc.field_71439_g;
        EntityDTO dto = DataCache.INSTANCE.getEntityDTO((EntityLivingBase)player);
        dto.update((EntityLivingBase)player, false);
        dto.biome = this.getPlayerBiome((EntityPlayer)player);
        dto.underground = PlayerData.playerIsUnderground(mc, (EntityPlayer)player);
        return dto;
    }

    private String getPlayerBiome(EntityPlayer player) {
        if (player != null) {
            try {
                Biome biome = FMLClientHandler.instance().getClient().field_71441_e.getBiomeForCoordsBody(player.func_180425_c());
                if (biome != null) {
                    return biome.func_185359_l();
                }
            }
            catch (Exception e) {
                JMLogger.logOnce("Couldn't get player biome: " + e.getMessage(), e);
            }
        }
        return "?";
    }

    public long getTTL() {
        return Journeymap.getClient().getCoreProperties().cachePlayerData.get().intValue();
    }
}

