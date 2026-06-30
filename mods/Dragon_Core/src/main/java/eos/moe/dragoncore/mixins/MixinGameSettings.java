/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.GameSettings
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={GameSettings.class})
public class MixinGameSettings {
    public MixinGameSettings() {
        MixinGameSettings a2;
    }
}

