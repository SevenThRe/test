/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.util.text.translation.I18n
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore.mixins;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import eos.moe.dragoncore.efa;
import eos.moe.dragoncore.iga;
import eos.moe.dragoncore.interfaces.IItemStack;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemStack.class})
public abstract class MixinItemStack
implements IItemStack {
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    @Shadow
    private NBTTagCompound stackTagCompound;
    @Shadow
    @Final
    private Item item;
    @Shadow
    private int itemDamage;
    @Shadow
    @Final
    public static DecimalFormat DECIMALFORMAT;
    private Map<String, Object> meta = new HashMap<String, Object>();

    public MixinItemStack() {
        MixinItemStack a2;
    }

    @Shadow
    public abstract boolean hasTagCompound();

    @Shadow
    public abstract boolean hasDisplayName();

    @Shadow
    @Nullable
    public abstract NBTTagCompound getSubCompound(String var1);

    @Shadow
    public abstract String getDisplayName();

    @Shadow
    public abstract boolean getHasSubtypes();

    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract NBTTagList getEnchantmentTagList();

    @Shadow
    public abstract Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot var1);

    @Shadow
    @Nullable
    public abstract NBTTagCompound getTagCompound();

    @Shadow
    public abstract boolean isItemDamaged();

    @Shadow
    public abstract int getMaxDamage();

    @Shadow
    public abstract int getItemDamage();

    private /* synthetic */ boolean hasLore() {
        MixinItemStack a2;
        NBTTagCompound a3 = a2.getSubCompound("display");
        return a3 != null && a3.hasKey("Lore", 9);
    }

    @Override
    public Object getMeta(String a2) {
        MixinItemStack a3;
        return a3.meta.get(a2);
    }

    @Override
    public Object getMeta(String a2, Object a3) {
        MixinItemStack a4;
        return a4.meta.getOrDefault(a2, a3);
    }

    @Override
    public void setMeta(String a2, Object a3) {
        MixinItemStack a4;
        a4.meta.put(a2, a3);
    }

    @SideOnly(value=Side.CLIENT)
    @Inject(method={"getTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    public void getTooltip(EntityPlayer a2, ITooltipFlag a3, CallbackInfoReturnable<List<String>> a4) {
        NBTTagCompound a5;
        NBTTagList a6;
        int a7;
        String a8;
        MixinItemStack a9;
        if (a9.hasDisplayName() || a9.hasLore()) {
            return;
        }
        iga a10 = efa.k.ALLATORIxDEMO((ItemStack)a9);
        if (a10 == null) {
            return;
        }
        ArrayList a11 = Lists.newArrayList();
        if (!a10.c().isEmpty()) {
            a8 = TextFormatting.ITALIC + a10.c();
        } else {
            a8 = a9.getDisplayName();
            if (a9.hasDisplayName()) {
                a8 = TextFormatting.ITALIC + a8;
            }
        }
        a8 = a8 + TextFormatting.RESET;
        if (a3.isAdvanced()) {
            String a12 = "";
            if (!a8.isEmpty()) {
                a8 = a8 + " (";
                a12 = ")";
            }
            a7 = Item.getIdFromItem((Item)a9.item);
            a8 = a9.getHasSubtypes() ? a8 + String.format("#%04d/%d%s", a7, a9.itemDamage, a12) : a8 + String.format("#%04d%s", a7, a12);
        } else if (!a9.hasDisplayName() && a9.item == Items.FILLED_MAP) {
            a8 = a8 + " #" + a9.itemDamage;
        }
        a11.add(a8);
        int a13 = 0;
        if (a9.hasTagCompound() && a9.stackTagCompound.hasKey("HideFlags", 99)) {
            a13 = a9.stackTagCompound.getInteger("HideFlags");
        }
        if ((a13 & 0x20) == 0) {
            a9.getItem().addInformation((ItemStack)a9, a2 == null ? null : a2.world, (List)a11, a3);
        }
        if (a9.hasTagCompound()) {
            if ((a13 & 1) == 0) {
                a6 = a9.getEnchantmentTagList();
                for (a7 = 0; a7 < a6.tagCount(); ++a7) {
                    a5 = a6.getCompoundTagAt(a7);
                    short a14 = a5.getShort("id");
                    short a15 = a5.getShort("lvl");
                    Enchantment a16 = Enchantment.getEnchantmentByID((int)a14);
                    if (a16 == null) continue;
                    a11.add(a16.getTranslatedName((int)a15));
                }
            }
            if (a9.stackTagCompound.hasKey("display", 10) && (a5 = a9.stackTagCompound.getCompoundTag("display")).hasKey("color", 3)) {
                if (a3.isAdvanced()) {
                    a11.add(I18n.translateToLocalFormatted((String)"item.color", (Object[])new Object[]{String.format("#%06X", a5.getInteger("color"))}));
                } else {
                    a11.add(TextFormatting.ITALIC + I18n.translateToLocal((String)"item.dyed"));
                }
            }
        }
        for (String a17 : a10.ALLATORIxDEMO()) {
            a11.add(TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC + a17);
        }
        a5 = EntityEquipmentSlot.values();
        a7 = ((EntityEquipmentSlot[])a5).length;
        for (int a18 = 0; a18 < a7; ++a18) {
            NBTTagCompound a19 = a5[a18];
            Multimap<String, AttributeModifier> a20 = a9.getAttributeModifiers((EntityEquipmentSlot)a19);
            if (a20.isEmpty() || (a13 & 2) != 0) continue;
            a11.add("");
            a11.add(I18n.translateToLocal((String)("item.modifiers." + a19.getName())));
            for (Map.Entry a21 : a20.entries()) {
                AttributeModifier a22 = (AttributeModifier)a21.getValue();
                double a23 = a22.getAmount();
                boolean a24 = false;
                if (a2 != null) {
                    if (a22.getID() == ATTACK_DAMAGE_MODIFIER) {
                        a23 += a2.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
                        a23 += (double)EnchantmentHelper.getModifierForCreature((ItemStack)((ItemStack)a9), (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED);
                        a24 = true;
                    } else if (a22.getID() == ATTACK_SPEED_MODIFIER) {
                        a23 += a2.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
                        a24 = true;
                    }
                }
                double a25 = a22.getOperation() != 1 && a22.getOperation() != 2 ? a23 : a23 * 100.0;
                if (a24) {
                    a11.add(" " + I18n.translateToLocalFormatted((String)("attribute.modifier.equals." + a22.getOperation()), (Object[])new Object[]{DECIMALFORMAT.format(a25), I18n.translateToLocal((String)("attribute.name." + (String)a21.getKey()))}));
                    continue;
                }
                if (a23 > 0.0) {
                    a11.add(TextFormatting.BLUE + " " + I18n.translateToLocalFormatted((String)("attribute.modifier.plus." + a22.getOperation()), (Object[])new Object[]{DECIMALFORMAT.format(a25), I18n.translateToLocal((String)("attribute.name." + (String)a21.getKey()))}));
                    continue;
                }
                if (!(a23 < 0.0)) continue;
                a11.add(TextFormatting.RED + " " + I18n.translateToLocalFormatted((String)("attribute.modifier.take." + a22.getOperation()), (Object[])new Object[]{DECIMALFORMAT.format(a25 *= -1.0), I18n.translateToLocal((String)("attribute.name." + (String)a21.getKey()))}));
            }
        }
        if (a9.hasTagCompound() && a9.getTagCompound().getBoolean("Unbreakable") && (a13 & 4) == 0) {
            a11.add(TextFormatting.BLUE + I18n.translateToLocal((String)"item.unbreakable"));
        }
        if (a9.hasTagCompound() && a9.stackTagCompound.hasKey("CanDestroy", 9) && (a13 & 8) == 0 && !(a6 = a9.stackTagCompound.getTagList("CanDestroy", 8)).isEmpty()) {
            a11.add("");
            a11.add(TextFormatting.GRAY + I18n.translateToLocal((String)"item.canBreak"));
            for (a7 = 0; a7 < a6.tagCount(); ++a7) {
                Block a26 = Block.getBlockFromName((String)a6.getStringTagAt(a7));
                if (a26 != null) {
                    a11.add(TextFormatting.DARK_GRAY + a26.getLocalizedName());
                    continue;
                }
                a11.add(TextFormatting.DARK_GRAY + "missingno");
            }
        }
        if (a9.hasTagCompound() && a9.stackTagCompound.hasKey("CanPlaceOn", 9) && (a13 & 0x10) == 0 && !(a6 = a9.stackTagCompound.getTagList("CanPlaceOn", 8)).isEmpty()) {
            a11.add("");
            a11.add(TextFormatting.GRAY + I18n.translateToLocal((String)"item.canPlace"));
            for (a7 = 0; a7 < a6.tagCount(); ++a7) {
                Block a27 = Block.getBlockFromName((String)a6.getStringTagAt(a7));
                if (a27 != null) {
                    a11.add(TextFormatting.DARK_GRAY + a27.getLocalizedName());
                    continue;
                }
                a11.add(TextFormatting.DARK_GRAY + "missingno");
            }
        }
        if (a3.isAdvanced()) {
            if (a9.isItemDamaged()) {
                a11.add(I18n.translateToLocalFormatted((String)"item.durability", (Object[])new Object[]{a9.getMaxDamage() - a9.getItemDamage(), a9.getMaxDamage()}));
            }
            a11.add(TextFormatting.DARK_GRAY + ((ResourceLocation)Item.REGISTRY.getNameForObject((Object)a9.item)).toString());
            if (a9.hasTagCompound()) {
                a11.add(TextFormatting.DARK_GRAY + I18n.translateToLocalFormatted((String)"item.nbt_tags", (Object[])new Object[]{a9.getTagCompound().getKeySet().size()}));
            }
        }
        ForgeEventFactory.onItemTooltip((ItemStack)((ItemStack)a9), (EntityPlayer)a2, (List)a11, (ITooltipFlag)a3);
        a4.setReturnValue(a11);
    }
}

