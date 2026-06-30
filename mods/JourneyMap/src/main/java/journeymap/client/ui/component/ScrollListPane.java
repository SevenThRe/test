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
        this.setDimensions(width, height, top, bottom);
    }

    public void setDimensions(int width, int height, int top, int bottom) {
        super.setDimensions(width, height, top, bottom);
        this.scrollbarX = this.width - this.hpad;
        this.listWidth = this.width - this.hpad * 4;
    }

    protected int getSize() {
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
            this.scrollBy(-(sizeAfter * this.slotHeight));
            this.scrollBy(this.lastClickedIndex * this.slotHeight);
        }
    }

    public void scrollTo(ISlot slot) {
        this.scrollBy(-(this.currentSlots.size() * this.slotHeight));
        this.scrollBy(this.currentSlots.indexOf(slot) * this.slotHeight);
    }

    public void handleMouseInput() {
        super.handleMouseInput();
    }

    protected void elementClicked(int index, boolean doubleClick, int mouseX, int mouseY) {
    }

    public boolean isSelected(int slotIndex) {
        return false;
    }

    protected void drawBackground() {
    }

    protected void drawSlot(int slotIndex, int x, int y, int slotHeight, int mouseX, int mouseY, float partialTicks) {
        boolean selected = this.getSlotIndexFromScreenCoords(mouseX, mouseY) == slotIndex;
        ISlot slot = this.getSlot(slotIndex);
        slot.drawEntry(slotIndex, x, y, this.getListWidth(), slotHeight, mouseX, mouseY, selected, 0.0f);
        SlotMetadata tooltipMetadata = slot.getCurrentTooltip();
        if (tooltipMetadata != null && !Arrays.equals(tooltipMetadata.getTooltip(), this.lastTooltip)) {
            this.lastTooltipMetadata = tooltipMetadata;
            this.lastTooltip = tooltipMetadata.getTooltip();
            this.lastTooltipTime = System.currentTimeMillis();
        }
    }

    public int getListWidth() {
        return this.listWidth;
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent) {
        int slotIndex;
        if (this.isMouseYWithinSlotBounds(mouseY) && (slotIndex = this.getSlotIndexFromScreenCoords(mouseX, mouseY)) >= 0) {
            int i1 = this.left + this.hpad + this.width / 2 - this.getListWidth() / 2 + 2;
            int j1 = this.top + 4 - this.getAmountScrolled() + slotIndex * this.slotHeight + this.headerPadding;
            int relativeX = mouseX - i1;
            int relativeY = mouseY - j1;
            this.lastClickedIndex = -1;
            if (this.getSlot(slotIndex).mousePressed(slotIndex, mouseX, mouseY, mouseEvent, relativeX, relativeY)) {
                this.setEnabled(false);
                this.lastClickedIndex = slotIndex;
                this.lastPressed = this.getSlot(slotIndex).getLastPressed();
                this.updateSlots();
                return true;
            }
        }
        return false;
    }

    public boolean mouseReleased(int x, int y, int mouseEvent) {
        boolean result = super.mouseReleased(x, y, mouseEvent);
        this.lastPressed = null;
        return result;
    }

    public GuiListExtended.IGuiListEntry getListEntry(int index) {
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
        for (int slotIndex = 0; slotIndex < this.getSize(); ++slotIndex) {
            if (!this.getSlot(slotIndex).keyTyped(c, i)) continue;
            this.lastClickedIndex = slotIndex;
            this.lastPressed = this.getSlot(slotIndex).getLastPressed();
            this.updateSlots();
            return true;
        }
        return false;
    }

    protected int getScrollBarX() {
        return this.scrollbarX;
    }

    protected void drawContainerBackground(Tessellator tessellator) {
        this.parent.drawGradientRect(0, this.top, this.width, this.top + this.height, -1072689136, -804253680);
    }

    protected int getContentHeight() {
        int contentHeight = super.getContentHeight();
        if (this.alignTop) {
            contentHeight = Math.max(this.bottom - this.top - 4, contentHeight);
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

