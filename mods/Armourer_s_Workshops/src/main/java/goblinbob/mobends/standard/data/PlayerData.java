/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.init.Items
 *  net.minecraft.util.EnumHand
 */
package goblinbob.mobends.standard.data;

import baka.animation.controller.CPCManager;
import eos.moe.armourers.interfaces.IRenderPlayer;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.animation.controller.PlayerController;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.main.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

public class PlayerData
extends BipedEntityData<AbstractClientPlayer> {
    protected boolean sprintJumpLeg = false;
    protected boolean sprintJumpLegSwitched = false;
    protected boolean fistPunchArm = false;
    protected int currentAttack = 0;
    protected float capeWavePhase = 0.0f;
    protected float capeWaveSpeed = 0.0f;
    private Boolean flyingStateOverride = null;
    public ModelPartTransform cape;
    private final PlayerController controller = new PlayerController();
    public boolean smallArm;

    public PlayerData(AbstractClientPlayer entity) {
        super(entity);
        CPCManager.put(entity.func_110124_au(), this.controller);
    }

    public PlayerController getController() {
        return this.controller;
    }

    public void setCapeWaveSpeed(float value) {
        this.capeWaveSpeed = value;
    }

    public float getCapeWavePhase() {
        return this.capeWavePhase;
    }

    public void overrideFlyingState(boolean flying) {
        this.flyingStateOverride = flying;
    }

    public void unsetFlyingStateOverride() {
        this.flyingStateOverride = null;
    }

    @Override
    public void onTicksRestart() {
    }

    @Override
    public void initModelPose() {
        super.initModelPose();
        Render render = Minecraft.func_71410_x().func_175598_ae().func_78713_a(this.entity);
        this.cape = new ModelPartTransform(this.body);
        this.nameToPartMap.put("cape", this.cape);
        this.cape.position.set(0.0f, 0.0f, 0.0f);
        this.smallArm = ((IRenderPlayer)render).isSmallArms();
        if (this.smallArm) {
            this.rightArm.position.set(-5.0f, -9.5f, 0.0f);
            this.leftArm.position.set(5.0f, -9.5f, 0.0f);
        }
    }

    @Override
    public void updateParts(float ticksPerFrame) {
        super.updateParts(ticksPerFrame);
        this.cape.update(ticksPerFrame);
    }

    @Override
    public void update(float partialTicks) {
        super.update(partialTicks);
        if (this.getTicksAfterAttack() > 20.0f) {
            this.currentAttack = 0;
        }
        if (this.motionY < 0.0) {
            this.sprintJumpLegSwitched = false;
        }
        if (!this.sprintJumpLegSwitched && this.motionY > 0.0) {
            this.sprintJumpLeg = !this.sprintJumpLeg;
            this.sprintJumpLegSwitched = true;
        }
        this.capeWavePhase += this.capeWaveSpeed * DataUpdateHandler.ticksPerFrame;
        if (this.capeWavePhase > 380.0f) {
            this.capeWavePhase -= 380.0f;
        }
    }

    @Override
    public void onLiftoff() {
        super.onLiftoff();
        if (!this.sprintJumpLegSwitched) {
            this.sprintJumpLeg = !this.sprintJumpLeg;
            this.sprintJumpLegSwitched = true;
        }
    }

    @Override
    public void onAttack() {
        if (((AbstractClientPlayer)this.entity).func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == Items.field_190931_a) {
            this.fistPunchArm = !this.fistPunchArm;
            this.ticksAfterAttack = 0.0f;
            return;
        }
        if (this.ticksAfterAttack <= 6.0f) {
            return;
        }
        switch (this.currentAttack) {
            case 1: {
                this.currentAttack = 2;
                break;
            }
            case 2: {
                this.currentAttack = 3;
                break;
            }
            case 3: {
                this.currentAttack = 4;
                break;
            }
            case 4: {
                this.currentAttack = !ModConfig.performSpinAttack || ((AbstractClientPlayer)this.getEntity()).func_184218_aH() ? 1 : 5;
                break;
            }
            default: {
                this.currentAttack = 1;
            }
        }
        this.ticksAfterAttack = 0.0f;
    }

    public int getCurrentAttack() {
        return this.currentAttack;
    }

    public boolean getFistPunchArm() {
        return this.fistPunchArm;
    }

    public boolean getSprintJumpLeg() {
        return this.sprintJumpLeg;
    }

    public boolean isFlying() {
        return this.flyingStateOverride != null ? this.flyingStateOverride : ((AbstractClientPlayer)this.entity).field_71075_bZ.field_75100_b;
    }
}

