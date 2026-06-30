/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksConfigSortingRuleType;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeItem;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.ContainerSectionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksHandlerAutoRefill
extends InvTweaksObfuscation {
    private static final Logger log = InvTweaks.log;
    @NotNull
    private InvTweaksConfig config;

    public InvTweaksHandlerAutoRefill(Minecraft mc_, @NotNull InvTweaksConfig config_) {
        super(mc_);
        this.config = config_;
    }

    public void setConfig(@NotNull InvTweaksConfig config_) {
        this.config = config_;
    }

    public void autoRefillSlot(int slot, @NotNull String wantedId, int wantedDamage) throws Exception {
        ContainerSectionManager container = new ContainerSectionManager(ContainerSection.INVENTORY);
        ItemStack replacementStack = ItemStack.field_190927_a;
        int replacementStackSlot = -1;
        boolean refillBeforeBreak = this.config.getProperty("autoRefillBeforeBreak").equals("true");
        boolean hasSubtypes = false;
        Item original = (Item)Item.field_150901_e.func_82594_a((Object)new ResourceLocation(wantedId));
        if (original != null) {
            hasSubtypes = original.func_77614_k();
        }
        ArrayList<InvTweaksConfigSortingRule> matchingRules = new ArrayList<InvTweaksConfigSortingRule>();
        List<InvTweaksConfigSortingRule> rules = this.config.getRules();
        InvTweaksItemTree tree = this.config.getTree();
        if (!tree.isItemUnknown(wantedId, wantedDamage)) {
            List<IItemTreeItem> items = tree.getItems(wantedId, wantedDamage);
            for (IItemTreeItem item : items) {
                if (item.getDamage() != wantedDamage && (hasSubtypes || item.getDamage() != Short.MAX_VALUE)) continue;
                matchingRules.add(new InvTweaksConfigSortingRule(tree, "D" + (slot - 26), item.getName(), 36, 9));
            }
            if (matchingRules.isEmpty()) {
                for (IItemTreeItem item : items) {
                    if (item.getDamage() != Short.MAX_VALUE) continue;
                    matchingRules.add(new InvTweaksConfigSortingRule(tree, "D" + (slot - 26), item.getName(), 36, 9));
                }
            }
            block2: for (InvTweaksConfigSortingRule rule : rules) {
                if (rule.getType() != InvTweaksConfigSortingRuleType.SLOT && rule.getType() != InvTweaksConfigSortingRuleType.COLUMN) continue;
                for (int preferredSlot : rule.getPreferredSlots()) {
                    if (slot != preferredSlot) continue;
                    matchingRules.add(rule);
                    continue block2;
                }
            }
            for (InvTweaksConfigSortingRule rule : matchingRules) {
                for (int i = 0; i < 36; ++i) {
                    List<IItemTreeItem> candidateItems;
                    ItemStack candidateStack = container.getItemStack(i);
                    if (candidateStack.func_190926_b() || !tree.matches(candidateItems = tree.getItems(candidateStack.func_77973_b().getRegistryName().toString(), candidateStack.func_77952_i()), rule.getKeyword())) continue;
                    if (candidateStack.func_77976_d() == 1) {
                        if (!replacementStack.func_190926_b() && candidateStack.func_77952_i() <= replacementStack.func_77952_i() || refillBeforeBreak && candidateStack.func_77958_k() - candidateStack.func_77952_i() <= this.config.getIntProperty("autoRefillDamageThreshhold")) continue;
                        replacementStack = candidateStack;
                        replacementStackSlot = i;
                        continue;
                    }
                    if (!replacementStack.func_190926_b() && candidateStack.func_190916_E() >= replacementStack.func_190916_E()) continue;
                    replacementStack = candidateStack;
                    replacementStackSlot = i;
                }
            }
        } else {
            for (int i = 0; i < 36; ++i) {
                ItemStack candidateStack = container.getItemStack(i);
                if (candidateStack.func_190926_b() || !Objects.equals(candidateStack.func_77973_b().getRegistryName().toString(), wantedId) || candidateStack.func_77952_i() != wantedDamage) continue;
                replacementStack = candidateStack;
                replacementStackSlot = i;
                break;
            }
        }
        if (!replacementStack.func_190926_b() || refillBeforeBreak && !container.getSlot(slot).func_75211_c().func_190926_b()) {
            log.info("Automatic stack replacement.");
            InvTweaks.getInstance().addScheduledTask(new Runnable(){
                private ContainerSectionManager containerMgr;
                private int targetedSlot;
                private int i;
                @Nullable
                private String expectedItemId;
                private boolean refillBeforeBreak;

                @NotNull
                public Runnable init(int i_, int currentItem, boolean refillBeforeBreak_) throws Exception {
                    this.containerMgr = new ContainerSectionManager(ContainerSection.INVENTORY);
                    this.targetedSlot = currentItem;
                    if (i_ != -1) {
                        this.i = i_;
                        this.expectedItemId = this.containerMgr.getItemStack(this.i).func_77973_b().getRegistryName().toString();
                    } else {
                        this.i = this.containerMgr.getFirstEmptyIndex();
                        this.expectedItemId = null;
                    }
                    this.refillBeforeBreak = refillBeforeBreak_;
                    return this;
                }

                @Override
                public void run() {
                    if (this.i < 0 || this.targetedSlot < 0) {
                        return;
                    }
                    ItemStack stack = this.containerMgr.getItemStack(this.i);
                    if (!stack.func_190926_b() && StringUtils.equals((CharSequence)stack.func_77973_b().getRegistryName().toString(), (CharSequence)this.expectedItemId) || this.refillBeforeBreak) {
                        if (this.containerMgr.move(this.targetedSlot, this.i) || this.containerMgr.move(this.i, this.targetedSlot)) {
                            if (!InvTweaksHandlerAutoRefill.this.config.getProperty("enableSounds").equals("false")) {
                                InvTweaksHandlerAutoRefill.this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187665_Y, (float)1.0f));
                            }
                            if (!this.containerMgr.getItemStack(this.i).func_190926_b() && this.i >= 27) {
                                for (int j = 0; j < 36; ++j) {
                                    if (!this.containerMgr.getItemStack(j).func_190926_b()) continue;
                                    this.containerMgr.move(this.i, j);
                                    break;
                                }
                            }
                            this.containerMgr.applyChanges();
                        } else {
                            log.warn("Failed to move stack for autoreplace, despite of prior tests.");
                        }
                    }
                }
            }.init(replacementStackSlot, slot, refillBeforeBreak));
        }
    }
}

