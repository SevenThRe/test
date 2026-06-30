/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 */
package eos.moe.dragoncore.mixins;

import javax.annotation.Nullable;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Scoreboard.class})
public abstract class MixinScoreboard {
    public MixinScoreboard() {
        MixinScoreboard a2;
    }

    @Shadow
    @Nullable
    public abstract ScorePlayerTeam func_96509_i(String var1);

    @Inject(method={"removePlayerFromTeam"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_fix_removePlayerFromTeam(String a2, ScorePlayerTeam a3, CallbackInfo a4) {
        MixinScoreboard a5;
        if (a5.func_96509_i(a2) != a3) {
            a4.cancel();
        }
    }
}

