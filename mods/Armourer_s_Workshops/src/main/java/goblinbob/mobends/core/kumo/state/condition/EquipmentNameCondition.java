/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.kumo.state.condition.ITriggerCondition;
import goblinbob.mobends.core.kumo.state.condition.ITriggerConditionContext;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EquipmentNameCondition
implements ITriggerCondition {
    private String namePattern;
    private EntityEquipmentSlot slot;

    public EquipmentNameCondition(Template template) {
        this.namePattern = template.namePattern;
        this.slot = template.slot;
    }

    @Override
    public boolean isConditionMet(ITriggerConditionContext context) {
        Object entity = context.getEntityData().getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            ItemStack itemStack = player.getItemStackFromSlot(this.slot);
            return itemStack.getDisplayName().matches(this.namePattern);
        }
        return false;
    }

    public static class Template
    extends TriggerConditionTemplate {
        public String namePattern;
        public EntityEquipmentSlot slot;
    }
}

