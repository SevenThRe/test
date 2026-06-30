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
    private NBTTagCompound field_77990_d;
    @Shadow
    @Final
    private Item field_151002_e;
    @Shadow
    private int field_77991_e;
    @Shadow
    @Final
    public static DecimalFormat field_111284_a;
    private Map<String, Object> meta = new HashMap<String, Object>();

    public MixinItemStack() {
        MixinItemStack a2;
    }

    @Shadow
    public abstract boolean func_77942_o();

    @Shadow
    public abstract boolean func_82837_s();

    @Shadow
    @Nullable
    public abstract NBTTagCompound func_179543_a(String var1);

    @Shadow
    public abstract String func_82833_r();

    @Shadow
    public abstract boolean func_77981_g();

    @Shadow
    public abstract Item func_77973_b();

    @Shadow
    public abstract NBTTagList func_77986_q();

    @Shadow
    public abstract Multimap<String, AttributeModifier> func_111283_C(EntityEquipmentSlot var1);

    @Shadow
    @Nullable
    public abstract NBTTagCompound func_77978_p();

    @Shadow
    public abstract boolean func_77951_h();

    @Shadow
    public abstract int func_77958_k();

    @Shadow
    public abstract int func_77952_i();

    private /* synthetic */ boolean hasLore() {
        MixinItemStack a2;
        NBTTagCompound a3 = a2.func_179543_a("display");
        return a3 != null && a3.func_150297_b("Lore", 9);
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
        if (a9.func_82837_s() || a9.hasLore()) {
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
            a8 = a9.func_82833_r();
            if (a9.func_82837_s()) {
                a8 = TextFormatting.ITALIC + a8;
            }
        }
        a8 = a8 + TextFormatting.RESET;
        if (a3.func_194127_a()) {
            String a12 = "";
            if (!a8.isEmpty()) {
                a8 = a8 + " (";
                a12 = ")";
            }
            a7 = Item.func_150891_b((Item)a9.field_151002_e);
            a8 = a9.func_77981_g() ? a8 + String.format("#%04d/%d%s", a7, a9.field_77991_e, a12) : a8 + String.format("#%04d%s", a7, a12);
        } else if (!a9.func_82837_s() && a9.field_151002_e == Items.field_151098_aY) {
            a8 = a8 + " #" + a9.field_77991_e;
        }
        a11.add(a8);
        int a13 = 0;
        if (a9.func_77942_o() && a9.field_77990_d.func_150297_b("HideFlags", 99)) {
            a13 = a9.field_77990_d.func_74762_e("HideFlags");
        }
        if ((a13 & 0x20) == 0) {
            a9.func_77973_b().func_77624_a((ItemStack)a9, a2 == null ? null : a2.field_70170_p, (List)a11, a3);
        }
        if (a9.func_77942_o()) {
            if ((a13 & 1) == 0) {
                a6 = a9.func_77986_q();
                for (a7 = 0; a7 < a6.func_74745_c(); ++a7) {
                    a5 = a6.func_150305_b(a7);
                    short a14 = a5.func_74765_d("id");
                    short a15 = a5.func_74765_d("lvl");
                    Enchantment a16 = Enchantment.func_185262_c((int)a14);
                    if (a16 == null) continue;
                    a11.add(a16.func_77316_c((int)a15));
                }
            }
            if (a9.field_77990_d.func_150297_b("display", 10) && (a5 = a9.field_77990_d.func_74775_l("display")).func_150297_b("color", 3)) {
                if (a3.func_194127_a()) {
                    a11.add(I18n.func_74837_a((String)"item.color", (Object[])new Object[]{String.format("#%06X", a5.func_74762_e("color"))}));
                } else {
                    a11.add(TextFormatting.ITALIC + I18n.func_74838_a((String)"item.dyed"));
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
            Multimap<String, AttributeModifier> a20 = a9.func_111283_C((EntityEquipmentSlot)a19);
            if (a20.isEmpty() || (a13 & 2) != 0) continue;
            a11.add("");
            a11.add(I18n.func_74838_a((String)("item.modifiers." + a19.func_188450_d())));
            for (Map.Entry a21 : a20.entries()) {
                AttributeModifier a22 = (AttributeModifier)a21.getValue();
                double a23 = a22.func_111164_d();
                boolean a24 = false;
                if (a2 != null) {
                    if (a22.func_111167_a() == ATTACK_DAMAGE_MODIFIER) {
                        a23 += a2.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
                        a23 += (double)EnchantmentHelper.func_152377_a((ItemStack)((ItemStack)a9), (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED);
                        a24 = true;
                    } else if (a22.func_111167_a() == ATTACK_SPEED_MODIFIER) {
                        a23 += a2.func_110148_a(SharedMonsterAttributes.field_188790_f).func_111125_b();
                        a24 = true;
                    }
                }
                double a25 = a22.func_111169_c() != 1 && a22.func_111169_c() != 2 ? a23 : a23 * 100.0;
                if (a24) {
                    a11.add(" " + I18n.func_74837_a((String)("attribute.modifier.equals." + a22.func_111169_c()), (Object[])new Object[]{field_111284_a.format(a25), I18n.func_74838_a((String)("attribute.name." + (String)a21.getKey()))}));
                    continue;
                }
                if (a23 > 0.0) {
                    a11.add(TextFormatting.BLUE + " " + I18n.func_74837_a((String)("attribute.modifier.plus." + a22.func_111169_c()), (Object[])new Object[]{field_111284_a.format(a25), I18n.func_74838_a((String)("attribute.name." + (String)a21.getKey()))}));
                    continue;
                }
                if (!(a23 < 0.0)) continue;
                a11.add(TextFormatting.RED + " " + I18n.func_74837_a((String)("attribute.modifier.take." + a22.func_111169_c()), (Object[])new Object[]{field_111284_a.format(a25 *= -1.0), I18n.func_74838_a((String)("attribute.name." + (String)a21.getKey()))}));
            }
        }
        if (a9.func_77942_o() && a9.func_77978_p().func_74767_n("Unbreakable") && (a13 & 4) == 0) {
            a11.add(TextFormatting.BLUE + I18n.func_74838_a((String)"item.unbreakable"));
        }
        if (a9.func_77942_o() && a9.field_77990_d.func_150297_b("CanDestroy", 9) && (a13 & 8) == 0 && !(a6 = a9.field_77990_d.func_150295_c("CanDestroy", 8)).func_82582_d()) {
            a11.add("");
            a11.add(TextFormatting.GRAY + I18n.func_74838_a((String)"item.canBreak"));
            for (a7 = 0; a7 < a6.func_74745_c(); ++a7) {
                Block a26 = Block.func_149684_b((String)a6.func_150307_f(a7));
                if (a26 != null) {
                    a11.add(TextFormatting.DARK_GRAY + a26.func_149732_F());
                    continue;
                }
                a11.add(TextFormatting.DARK_GRAY + "missingno");
            }
        }
        if (a9.func_77942_o() && a9.field_77990_d.func_150297_b("CanPlaceOn", 9) && (a13 & 0x10) == 0 && !(a6 = a9.field_77990_d.func_150295_c("CanPlaceOn", 8)).func_82582_d()) {
            a11.add("");
            a11.add(TextFormatting.GRAY + I18n.func_74838_a((String)"item.canPlace"));
            for (a7 = 0; a7 < a6.func_74745_c(); ++a7) {
                Block a27 = Block.func_149684_b((String)a6.func_150307_f(a7));
                if (a27 != null) {
                    a11.add(TextFormatting.DARK_GRAY + a27.func_149732_F());
                    continue;
                }
                a11.add(TextFormatting.DARK_GRAY + "missingno");
            }
        }
        if (a3.func_194127_a()) {
            if (a9.func_77951_h()) {
                a11.add(I18n.func_74837_a((String)"item.durability", (Object[])new Object[]{a9.func_77958_k() - a9.func_77952_i(), a9.func_77958_k()}));
            }
            a11.add(TextFormatting.DARK_GRAY + ((ResourceLocation)Item.field_150901_e.func_177774_c((Object)a9.field_151002_e)).toString());
            if (a9.func_77942_o()) {
                a11.add(TextFormatting.DARK_GRAY + I18n.func_74837_a((String)"item.nbt_tags", (Object[])new Object[]{a9.func_77978_p().func_150296_c().size()}));
            }
        }
        ForgeEventFactory.onItemTooltip((ItemStack)((ItemStack)a9), (EntityPlayer)a2, (List)a11, (ITooltipFlag)a3);
        a4.setReturnValue(a11);
    }
}

