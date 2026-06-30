/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package goblinbob.mobends.core.pack;

import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.pack.BendsPackData;
import goblinbob.mobends.core.pack.PackDataProvider;
import goblinbob.mobends.core.pack.PackManager;
import java.util.Collection;
import javax.annotation.Nullable;

public class BendsPackPerformer {
    public static final BendsPackPerformer INSTANCE = new BendsPackPerformer();

    public void performCurrentPack(EntityData<?> entityData, String animatedEntityKey, @Nullable Collection<String> actions) {
        BendsPackData packData = PackDataProvider.INSTANCE.getAppliedData();
        if (packData == null) {
            return;
        }
        try {
            entityData.packAnimationState.update(entityData, packData, animatedEntityKey, DataUpdateHandler.ticksPerFrame);
        }
        catch (MalformedKumoTemplateException e2) {
            e2.printStackTrace();
            PackManager.INSTANCE.resetAppliedPacks(true);
        }
    }
}

