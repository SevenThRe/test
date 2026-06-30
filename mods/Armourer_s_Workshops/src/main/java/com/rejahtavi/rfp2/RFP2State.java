/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.rejahtavi.rfp2;

import com.rejahtavi.rfp2.EntityPlayerDummy;
import com.rejahtavi.rfp2.RFP2;
import com.rejahtavi.rfp2.RFP2Config;
import java.util.regex.PatternSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value={Side.CLIENT})
public class RFP2State {
    EntityPlayerDummy dummy = null;
    int spawnDelay = 40;
    long checkEnableModDelay = 0L;
    long checkEnableRealArmsDelay = 0L;
    int suspendApiDelay = 0;
    boolean lastActivateCheckResult = true;
    boolean lastRealArmsCheckResult = true;
    boolean enableMod;
    boolean enableRealArms;
    boolean enableHeadTurning;
    boolean enableStatusMessages;
    boolean conflictsDetected = false;
    boolean conflictCheckDone = false;
    private int tick = 0;

    public RFP2State() {
        this.enableMod = RFP2Config.preferences.enableMod;
        this.enableRealArms = RFP2Config.preferences.enableRealArms;
        this.enableHeadTurning = RFP2Config.preferences.enableHeadTurning;
        this.enableStatusMessages = RFP2Config.preferences.enableStatusMessages;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent1(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown() && this.tick++ >= 2) {
            Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
            this.tick = 0;
        }
    }

    public void detectModConflicts(EntityPlayer player) {
        if (!this.conflictCheckDone) {
            String modConflictList = "";
            for (String conflictingID : RFP2.CONFLICT_MODIDS) {
                if (!Loader.isModLoaded((String)conflictingID)) continue;
                if (modConflictList.length() != 0) {
                    modConflictList = modConflictList + ", ";
                }
                modConflictList = modConflictList + conflictingID;
            }
            if (modConflictList.length() != 0) {
                if (RFP2Config.compatibility.disableModCompatibilityAlerts) {
                    RFP2.logger.log(RFP2.LOGGING_LEVEL_HIGH, this.getClass().getName() + ": WARNING: In-game compatibility alerts have been disabled!");
                } else {
                    RFP2.logToChatByPlayer("" + TextFormatting.BOLD + TextFormatting.GOLD + "WARNING: RFP2 has known compatibility issues with the mod(s): " + TextFormatting.RESET + TextFormatting.RED + modConflictList + ".", player);
                    RFP2.logToChatByPlayer("" + TextFormatting.BOLD + TextFormatting.GOLD + "Be aware that visual glitches may occur.", player);
                    RFP2.logToChatByPlayer("Press the hotkey (Default: Apostrophe) to use RFP2 anyway.", player);
                    RFP2.logToChatByPlayer("" + TextFormatting.RESET + TextFormatting.GRAY + "(You can disable this warning in mod options.)", player);
                    this.conflictsDetected = true;
                    this.enableMod = false;
                }
                RFP2.logger.log(RFP2.LOGGING_LEVEL_HIGH, this.getClass().getName() + ": WARNING: Detected conflicting mod(s): " + modConflictList);
            }
            this.conflictCheckDone = true;
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onEvent(RenderHandEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && RFP2.state.isModEnabled((EntityPlayer)player) && RFP2.state.isRealArmsEnabled((EntityPlayer)player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent e2) {
        if (e2.getEntity() == Minecraft.getMinecraft().player) {
            this.resetDummy();
            this.spawnDelay = 0;
        }
    }

    @SubscribeEvent
    public void onEvent(TickEvent.ClientTickEvent event) {
        try {
            EntityPlayerSP player;
            if (this.checkEnableModDelay > 0L) {
                --this.checkEnableModDelay;
            }
            if (this.checkEnableRealArmsDelay > 0L) {
                --this.checkEnableRealArmsDelay;
            }
            if (this.suspendApiDelay > 0) {
                --this.suspendApiDelay;
            }
            if ((player = Minecraft.getMinecraft().player) != null) {
                if (this.dummy == null) {
                    if (this.spawnDelay > 0) {
                        this.spawnDelay = this.enableMod ? --this.spawnDelay : 40;
                    } else {
                        this.attemptDummySpawn((EntityPlayer)player);
                    }
                } else if (this.dummy.world.provider.getDimension() != player.world.provider.getDimension()) {
                    this.resetDummy();
                    RFP2.logger.log(RFP2.LOGGING_LEVEL_DEBUG, this.getClass().getName() + ": Respawning dummy because player changed dimension.");
                } else if (this.dummy.getDistanceSq((Entity)player) > 5.0) {
                    this.resetDummy();
                    this.spawnDelay = 0;
                    RFP2.logger.log(RFP2.LOGGING_LEVEL_DEBUG, this.getClass().getName() + ": Respawning dummy because player and dummy became separated.");
                } else if (this.dummy.lastTickUpdated < player.world.getTotalWorldTime() - 20L) {
                    this.resetDummy();
                    RFP2.logger.log(RFP2.LOGGING_LEVEL_DEBUG, this.getClass().getName() + ": Respawning dummy because state became stale. (Is the server lagging?)");
                }
            }
        }
        catch (Exception e2) {
            RFP2.errorDisableMod(this.getClass().getName() + ".onEvent(TickEvent.ClientTickEvent)", e2);
        }
    }

    void attemptDummySpawn(EntityPlayer player) {
        this.detectModConflicts(player);
        try {
            if (this.dummy != null) {
                this.dummy.setDead();
            }
            this.dummy = new EntityPlayerDummy(player.world);
            this.dummy.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
            player.world.spawnEntity((Entity)this.dummy);
        }
        catch (Exception e2) {
            RFP2.logger.log(RFP2.LOGGING_LEVEL_MED, this.getClass().getName() + ": failed to spawn PlayerDummy! Will retry. Exception:", (Object)e2.toString());
            e2.printStackTrace();
            this.resetDummy();
        }
    }

    void resetDummy() {
        if (this.dummy != null) {
            this.dummy.setDead();
        }
        this.dummy = null;
        this.spawnDelay = 40;
    }

    public void setSuspendTimer(int ticks) {
        if (ticks > 0 && ticks <= 60 && ticks > this.suspendApiDelay) {
            this.suspendApiDelay = ticks;
        }
    }

    public boolean isModEnabled(EntityPlayer player) {
        if (!this.enableMod) {
            return false;
        }
        if (this.suspendApiDelay > 0) {
            return false;
        }
        if (player == null) {
            return false;
        }
        if (this.dummy == null) {
            return false;
        }
        if (this.checkEnableModDelay == 0L) {
            Entity playerMountEntity;
            this.checkEnableModDelay = 4L;
            this.lastActivateCheckResult = RFP2Config.compatibility.disableWhenSwimming && this.dummy.isSwimming() ? false : ((playerMountEntity = player.getRidingEntity()) == null ? true : !this.stringMatchesRegexList(playerMountEntity.getName().toLowerCase(), RFP2Config.compatibility.mountConflictList));
        }
        return this.lastActivateCheckResult;
    }

    public boolean isRealArmsEnabled(EntityPlayer player) {
        if (!this.enableRealArms) {
            return false;
        }
        if (player == null) {
            return false;
        }
        if (this.checkEnableRealArmsDelay == 0L) {
            this.checkEnableRealArmsDelay = 1L;
            String itemMainHand = player.inventory.getCurrentItem().getItem().getRegistryName().toString().toLowerCase();
            String itemOffHand = ((ItemStack)player.inventory.offHandInventory.get(0)).getItem().getRegistryName().toString().toLowerCase();
            this.lastRealArmsCheckResult = RFP2Config.compatibility.disableArmsWhenAnyItemHeld ? itemMainHand.equals("minecraft:air") && itemOffHand.equals("minecraft:air") : !this.stringMatchesRegexList(itemMainHand, RFP2Config.compatibility.heldItemConflictList) && !this.stringMatchesRegexList(itemOffHand, RFP2Config.compatibility.heldItemConflictList);
        }
        return this.lastRealArmsCheckResult;
    }

    public boolean isHeadRotationEnabled(EntityPlayer player) {
        return this.enableHeadTurning;
    }

    boolean stringMatchesRegexList(String string, String[] regexes) {
        for (String i2 : regexes) {
            try {
                if (!string.matches(i2)) continue;
                return true;
            }
            catch (PatternSyntaxException e2) {
                this.enableMod = false;
                RFP2.logToChat("Real First Person 2 " + TextFormatting.RED + "Warning: [ " + i2 + " ] is not a valid regex, please edit your configuration.");
                RFP2.logToChat("Real First Person 2 mod " + TextFormatting.RED + " disabled");
                return false;
            }
        }
        return false;
    }
}

