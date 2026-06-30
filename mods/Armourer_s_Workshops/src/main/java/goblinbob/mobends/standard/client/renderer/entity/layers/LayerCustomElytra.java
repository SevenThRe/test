/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelElytra
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerArmorBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EnumPlayerModelParts
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity.layers;

import goblinbob.mobends.core.util.BenderHelper;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerCustomElytra
implements LayerRenderer<AbstractClientPlayer> {
    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
    protected final RenderPlayer renderPlayer;
    private final ModelElytra modelElytra = new ModelElytra();

    public LayerCustomElytra(RenderPlayer p_i47185_1_) {
        this.renderPlayer = p_i47185_1_;
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        PlayerData data = (PlayerData)BenderHelper.getData(player, (RenderLivingBase<? extends EntityLivingBase>)this.renderPlayer);
        assert (data != null);
        ItemStack itemstack = player.func_184582_a(EntityEquipmentSlot.CHEST);
        if (itemstack.func_77973_b() == Items.field_185160_cR) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            if (player.func_184833_s() && player.func_184834_t() != null) {
                this.renderPlayer.func_110776_a(player.func_184834_t());
            } else if (player.func_152122_n() && player.func_110303_q() != null && player.func_175148_a(EnumPlayerModelParts.CAPE)) {
                this.renderPlayer.func_110776_a(player.func_110303_q());
            } else {
                this.renderPlayer.func_110776_a(TEXTURE_ELYTRA);
            }
            GlStateManager.func_179094_E();
            data.body.applyCharacterTransform(0.0625f);
            GlStateManager.func_179109_b((float)0.0f, (float)(-12.0f * scale), (float)0.0f);
            this.modelElytra.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, (Entity)player);
            this.modelElytra.func_78088_a((Entity)player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            if (itemstack.func_77948_v()) {
                LayerArmorBase.func_188364_a((RenderLivingBase)this.renderPlayer, (EntityLivingBase)player, (ModelBase)this.modelElytra, (float)limbSwing, (float)limbSwingAmount, (float)partialTicks, (float)ageInTicks, (float)netHeadYaw, (float)headPitch, (float)scale);
            }
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }

    public boolean func_177142_b() {
        return false;
    }
}

