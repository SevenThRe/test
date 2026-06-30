/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 */
package goblinbob.mobends.standard;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.IPreviewer;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.mutators.IMutatorFactory;
import goblinbob.mobends.standard.client.renderer.entity.mutated.PlayerRenderer;
import goblinbob.mobends.standard.data.PlayerData;
import goblinbob.mobends.standard.mutators.PlayerMutator;
import goblinbob.mobends.standard.previewer.PlayerPreviewer;
import net.minecraft.client.entity.AbstractClientPlayer;

public class PlayerBender
extends EntityBender<AbstractClientPlayer> {
    private PlayerPreviewer previewer;
    private String[] alterableParts = new String[]{"head", "body", "leftArm", "rightArm", "leftForeArm", "rightForeArm", "leftLeg", "rightLeg", "leftForeLeg", "rightForeLeg", "totalRotation", "leftItemRotation", "rightItemRotation"};

    public PlayerBender() {
        super("mobends", "player", "mobends.player", AbstractClientPlayer.class, new PlayerRenderer());
        this.previewer = new PlayerPreviewer();
    }

    @Override
    public String[] getAlterableParts() {
        return this.alterableParts;
    }

    @Override
    public IEntityDataFactory<AbstractClientPlayer> getDataFactory() {
        return PlayerData::new;
    }

    @Override
    public IMutatorFactory<AbstractClientPlayer> getMutatorFactory() {
        return PlayerMutator::new;
    }

    @Override
    public IPreviewer<?> getPreviewer() {
        return this.previewer;
    }

    @Override
    public LivingEntityData<?> getDataForPreview() {
        return PlayerPreviewer.getPreviewData();
    }
}

