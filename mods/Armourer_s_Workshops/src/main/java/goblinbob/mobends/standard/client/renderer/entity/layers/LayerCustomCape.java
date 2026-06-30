/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EnumPlayerModelParts
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity.layers;

import goblinbob.mobends.core.util.BenderHelper;
import goblinbob.mobends.standard.client.renderer.entity.BendsCapeRenderer;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerCustomCape
implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer playerRenderer;
    private final BendsCapeRenderer capeRenderer;

    public LayerCustomCape(RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
        this.capeRenderer = new BendsCapeRenderer();
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack;
        PlayerData data = (PlayerData)BenderHelper.getData(player, (RenderLivingBase<? extends EntityLivingBase>)this.playerRenderer);
        assert (data != null);
        if (player.func_152122_n() && !player.func_82150_aj() && player.func_175148_a(EnumPlayerModelParts.CAPE) && player.func_110303_q() != null && (itemstack = player.func_184582_a(EntityEquipmentSlot.CHEST)).func_77973_b() != Items.field_185160_cR) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.playerRenderer.func_110776_a(player.func_110303_q());
            GlStateManager.func_179094_E();
            if (player.func_70093_af()) {
                if (player.field_71075_bZ.field_75100_b) {
                    GlStateManager.func_179109_b((float)0.0f, (float)(4.0f * scale), (float)0.0f);
                } else {
                    GlStateManager.func_179109_b((float)0.0f, (float)(4.0f * scale), (float)0.0f);
                }
            }
            data.body.applyLocalTransform(0.0625f);
            GlStateManager.func_179109_b((float)0.0f, (float)(-12.0f * scale), (float)(2.2f * scale));
            data.cape.applyLocalTransform(0.0625f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.capeRenderer.applyAnimation(data);
            this.capeRenderer.render(0.0625f);
            GlStateManager.func_179121_F();
        }
    }

    public boolean func_177142_b() {
        return false;
    }
}

