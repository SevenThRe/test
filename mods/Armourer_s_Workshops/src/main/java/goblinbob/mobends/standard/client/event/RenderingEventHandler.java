/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package goblinbob.mobends.standard.client.event;

import goblinbob.mobends.core.util.BenderHelper;
import goblinbob.mobends.standard.mutators.PlayerMutator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderingEventHandler {
    @SubscribeEvent
    public void beforeHandRender(RenderHandEvent event) {
        Minecraft mc2 = Minecraft.getMinecraft();
        Entity viewEntity = mc2.getRenderViewEntity();
        if (!(viewEntity instanceof AbstractClientPlayer)) {
            return;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)viewEntity;
        if (!BenderHelper.isEntityAnimated((EntityLivingBase)player)) {
            return;
        }
        RenderPlayer renderPlayer = (RenderPlayer)mc2.getRenderManager().getEntityRenderObject((Entity)player);
        PlayerMutator mutator = (PlayerMutator)BenderHelper.getMutatorForRenderer(AbstractClientPlayer.class, renderPlayer);
        if (mutator != null) {
            mutator.poseForFirstPersonView();
        }
    }
}

