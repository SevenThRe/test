/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiListExtended
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.renderer.Tessellator
 */
package journeymap.client.ui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.option.SlotMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;

public class ScrollListPane<T extends ISlot>
extends GuiListExtended {
    final JmUI parent;
    public SlotMetadata lastTooltipMetadata;
    public String[] lastTooltip;
    public long lastTooltipTime;
    public long hoverDelay = 800L;
    int hpad = 12;
    List<T> rootSlots;
    List<ISlot> currentSlots = new ArrayList<ISlot>(0);
    SlotMetadata lastPressed;
    int lastClickedIndex;
    int scrollbarX;
    int listWidth;
    boolean alignTop;

    public ScrollListPane(JmUI parent, Minecraft mc, int width, int height, int top, int bottom, int slotHeight) {
        super(mc, width, height, top, bottom, slotHeight);
        this.parent = parent;
        this.func_148122_a(width, height, top, bottom);
    }

    public void func_148122_a(int width, int height, int top, int bottom) {
        super.func_148122_a(width, height, top, bottom);
        this.scrollbarX = this.field_148155_a - this.hpad;
        this.listWidth = this.field_148155_a - this.hpad * 4;
    }

    protected int func_148127_b() {
        return this.currentSlots == null ? 0 : this.currentSlots.size();
    }

    public void setSlots(List<T> slots) {
        this.rootSlots = slots;
        this.updateSlots();
    }

    public List<T> getRootSlots() {
        return this.rootSlots;
    }

    public void updateSlots() {
        int sizeBefore = this.currentSlots.size();
        this.currentSlots.clear();
        int columnWidth = 0;
        for (ISlot slot : this.rootSlots) {
            columnWidth = Math.max(columnWidth, slot.getColumnWidth());
        }
        for (ISlot slot : this.rootSlots) {
            this.currentSlots.add(slot);
            List<? extends ISlot> children = slot.getChildSlots(this.listWidth, columnWidth);
            if (children == null || children.isEmpty()) continue;
            this.currentSlots.addAll(children);
        }
        int sizeAfter = this.currentSlots.size();
        if (sizeBefore < sizeAfter) {
            this.func_148145_f(-(sizeAfter * this.field_148149_f));
            this.func_148145_f(this.lastClickedIndex * this.field_148149_f);
        }
    }

    public void scrollTo(ISlot slot) {
        this.func_148145_f(-(this.currentSlots.size() * this.field_148149_f));
        this.func_148145_f(this.currentSlots.indexOf(slot) * this.field_148149_f);
    }

    public void func_178039_p() {
        super.func_178039_p();
    }

    protected void func_148144_a(int index, boolean doubleClick, int mouseX, int mouseY) {
    }

    public boolean func_148131_a(int slotIndex) {
        return false;
    }

    protected void func_148123_a() {
    }

    protected void func_192637_a(int slotIndex, int x, int y, int slotHeight, int mouseX, int mouseY, float partialTicks) {
        boolean selected = this.func_148124_c(mouseX, mouseY) == slotIndex;
        ISlot slot = this.getSlot(slotIndex);
        slot.func_192634_a(slotIndex, x, y, this.func_148139_c(), slotHeight, mouseX, mouseY, selected, 0.0f);
        SlotMetadata tooltipMetadata = slot.getCurrentTooltip();
        if (tooltipMetadata != null && !Arrays.equals(tooltipMetadata.getTooltip(), this.lastTooltip)) {
            this.lastTooltipMetadata = tooltipMetadata;
            this.lastTooltip = tooltipMetadata.getTooltip();
            this.lastTooltipTime = System.currentTimeMillis();
        }
    }

    public int func_148139_c() {
        return this.listWidth;
    }

    public boolean func_148179_a(int mouseX, int mouseY, int mouseEvent) {
        int slotIndex;
        if (this.func_148141_e(mouseY) && (slotIndex = this.func_148124_c(mouseX, mouseY)) >= 0) {
            int i1 = this.field_148152_e + this.hpad + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
            int j1 = this.field_148153_b + 4 - this.func_148148_g() + slotIndex * this.field_148149_f + this.field_148160_j;
            int relativeX = mouseX - i1;
            int relativeY = mouseY - j1;
            this.lastClickedIndex = -1;
            if (this.getSlot(slotIndex).func_148278_a(slotIndex, mouseX, mouseY, mouseEvent, relativeX, relativeY)) {
                this.func_148143_b(false);
                this.lastClickedIndex = slotIndex;
                this.lastPressed = this.getSlot(slotIndex).getLastPressed();
                this.updateSlots();
                return true;
            }
        }
        return false;
    }

    public boolean func_148181_b(int x, int y, int mouseEvent) {
        boolean result = super.func_148181_b(x, y, mouseEvent);
        this.lastPressed = null;
        return result;
    }

    public GuiListExtended.IGuiListEntry func_148180_b(int index) {
        return this.getSlot(index);
    }

    public ISlot getSlot(int index) {
        return this.currentSlots.get(index);
    }

    public SlotMetadata getLastPressed() {
        return this.lastPressed;
    }

    public void resetLastPressed() {
        this.lastPressed = null;
    }

    public ISlot getLastPressedParentSlot() {
        if (this.lastPressed != null) {
            for (ISlot slot : this.rootSlots) {
                if (!slot.contains(this.lastPressed)) continue;
                return slot;
            }
        }
        return null;
    }

    public boolean keyTyped(char c, int i) {
        for (int slotIndex = 0; slotIndex < this.func_148127_b(); ++slotIndex) {
            if (!this.getSlot(slotIndex).keyTyped(c, i)) continue;
            this.lastClickedIndex = slotIndex;
            this.lastPressed = this.getSlot(slotIndex).getLastPressed();
            this.updateSlots();
            return true;
        }
        return false;
    }

    protected int func_148137_d() {
        return this.scrollbarX;
    }

    protected void drawContainerBackground(Tessellator tessellator) {
        this.parent.func_73733_a(0, this.field_148153_b, this.field_148155_a, this.field_148153_b + this.field_148158_l, -1072689136, -804253680);
    }

    protected int func_148138_e() {
        int contentHeight = super.func_148138_e();
        if (this.alignTop) {
            contentHeight = Math.max(this.field_148154_c - this.field_148153_b - 4, contentHeight);
        }
        return contentHeight;
    }

    public void setAlignTop(boolean alignTop) {
        this.alignTop = alignTop;
    }

    public static interface ISlot
    extends GuiListExtended.IGuiListEntry {
        public Collection<SlotMetadata> getMetadata();

        public String[] mouseHover(int var1, int var2, int var3, int var4, int var5, int var6);

        public boolean keyTyped(char var1, int var2);

        public List<? extends ISlot> getChildSlots(int var1, int var2);

        public SlotMetadata getLastPressed();

        public SlotMetadata getCurrentTooltip();

        public void setEnabled(boolean var1);

        public int getColumnWidth();

        public boolean contains(SlotMetadata var1);
    }
}

