/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package goblinbob.mobends.core.network;

import goblinbob.mobends.core.network.SharedBooleanProp;
import goblinbob.mobends.core.network.SharedConfig;
import goblinbob.mobends.core.network.SharedProperty;
import net.minecraft.client.Minecraft;

public class NetworkConfiguration {
    public static NetworkConfiguration instance = new NetworkConfiguration();
    private final SharedConfig sharedConfig = new SharedConfig();
    private final SharedProperty<Boolean> modelScalingAllowed = new SharedBooleanProp("modelScalingAllowed", false, "Does the server allow scaling of the player model more than the normal size?");
    private final SharedProperty<Boolean> bendsPacksAllowed;
    private final SharedProperty<Boolean> movementLimited;

    public NetworkConfiguration() {
        this.sharedConfig.addProperty(this.modelScalingAllowed);
        this.bendsPacksAllowed = new SharedBooleanProp("bendsPacksAllowed", true, "Does the server allow the use of bends packs?");
        this.sharedConfig.addProperty(this.bendsPacksAllowed);
        this.movementLimited = new SharedBooleanProp("movementLimited", true, "Does the server limit excessive bends pack transformation?");
        this.sharedConfig.addProperty(this.movementLimited);
    }

    public void onWorldJoin() {
        this.modelScalingAllowed.setValue(Minecraft.func_71410_x().func_71356_B());
        this.bendsPacksAllowed.setValue(true);
        this.movementLimited.setValue(!Minecraft.func_71410_x().func_71356_B());
    }

    public SharedConfig getSharedConfig() {
        return this.sharedConfig;
    }

    public boolean isModelScalingAllowed() {
        return this.modelScalingAllowed.getValue();
    }

    public boolean areBendsPacksAllowed() {
        return this.bendsPacksAllowed.getValue();
    }

    public boolean isMovementLimited() {
        return this.movementLimited.getValue();
    }
}

