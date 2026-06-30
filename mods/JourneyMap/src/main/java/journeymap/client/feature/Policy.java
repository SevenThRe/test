/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.feature;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import journeymap.client.feature.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Policy {
    static Minecraft mc = FMLClientHandler.instance().getClient();
    final Feature feature;
    final boolean allowInSingleplayer;
    final boolean allowInMultiplayer;

    public Policy(Feature feature, boolean allowInSingleplayer, boolean allowInMultiplayer) {
        this.feature = feature;
        this.allowInSingleplayer = allowInSingleplayer;
        this.allowInMultiplayer = allowInMultiplayer;
    }

    public static Set<Policy> bulkCreate(boolean allowInSingleplayer, boolean allowInMultiplayer) {
        return Policy.bulkCreate(Feature.all(), allowInSingleplayer, allowInMultiplayer);
    }

    public static Set<Policy> bulkCreate(EnumSet<Feature> features, boolean allowInSingleplayer, boolean allowInMultiplayer) {
        HashSet<Policy> policies = new HashSet<Policy>();
        for (Feature feature : features) {
            policies.add(new Policy(feature, allowInSingleplayer, allowInMultiplayer));
        }
        return policies;
    }

    public boolean isCurrentlyAllowed() {
        boolean isSinglePlayer;
        if (this.allowInSingleplayer == this.allowInMultiplayer) {
            return this.allowInSingleplayer;
        }
        IntegratedServer server = mc.getIntegratedServer();
        boolean bl = isSinglePlayer = server != null && !server.getPublic();
        if (this.allowInSingleplayer && isSinglePlayer) {
            return true;
        }
        return this.allowInMultiplayer && !isSinglePlayer;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Policy policy = (Policy)o;
        if (this.allowInMultiplayer != policy.allowInMultiplayer) {
            return false;
        }
        if (this.allowInSingleplayer != policy.allowInSingleplayer) {
            return false;
        }
        return this.feature == policy.feature;
    }

    public int hashCode() {
        int result = this.feature.hashCode();
        result = 31 * result + (this.allowInSingleplayer ? 1 : 0);
        result = 31 * result + (this.allowInMultiplayer ? 1 : 0);
        return result;
    }
}

