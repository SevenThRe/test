/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import journeymap.client.properties.ClientCategory;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.option.ButtonListSlot;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.common.properties.Category;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;

public class CategorySlot
implements ScrollListPane.ISlot,
Comparable<CategorySlot> {
    Minecraft mc = FMLClientHandler.instance().getClient();
    SlotMetadata metadata;
    Category category;
    int currentSlotIndex;
    Button button;
    int currentListWidth;
    int currentColumns;
    int currentColumnWidth;
    SlotMetadata masterSlot;
    SlotMetadata currentTooltip;
    LinkedList<SlotMetadata> childMetadataList = new LinkedList();
    List<ScrollListPane.ISlot> childSlots = new ArrayList<ScrollListPane.ISlot>();
    String glyphClosed = "\u25b6";
    String glyphOpen = "\u25bc";
    private boolean selected;

    public CategorySlot(Category category) {
        this.category = category;
        boolean advanced = category == ClientCategory.Advanced;
        this.button = new Button(category.getLabel());
        this.metadata = new SlotMetadata(this.button, category.getLabel(), category.getTooltip(), advanced);
        this.updateButtonLabel();
    }

    public CategorySlot add(ScrollListPane.ISlot slot) {
        this.childSlots.add(slot);
        this.childMetadataList.addAll(slot.getMetadata());
        for (SlotMetadata slotMetadata : slot.getMetadata()) {
            if (!slotMetadata.isMasterPropertyForCategory()) continue;
            this.masterSlot = slotMetadata;
        }
        return this;
    }

    public void clear() {
        this.childSlots.clear();
    }

    public int size() {
        return this.childSlots.size();
    }

    public void sort() {
        Collections.sort(this.childMetadataList);
    }

    @Override
    public int getColumnWidth() {
        int columnWidth = 100;
        for (ScrollListPane.ISlot slot : this.childSlots) {
            columnWidth = Math.max(columnWidth, slot.getColumnWidth());
        }
        return columnWidth;
    }

    public List<ScrollListPane.ISlot> getChildSlots(int listWidth, int columnWidth) {
        if (!this.selected) {
            return Collections.EMPTY_LIST;
        }
        int columns = listWidth / (columnWidth + ButtonListSlot.hgap);
        if (columnWidth == this.currentColumnWidth && columns == this.currentColumns) {
            return this.childSlots;
        }
        this.currentListWidth = listWidth;
        this.currentColumnWidth = columnWidth;
        this.currentColumns = columns;
        this.childSlots.clear();
        this.sort();
        ArrayList<SlotMetadata> remaining = new ArrayList<SlotMetadata>(this.childMetadataList);
        while (!remaining.isEmpty()) {
            ButtonListSlot row = new ButtonListSlot(this);
            SlotMetadata.ValueType lastType = null;
            for (int i = 0; i < columns && !remaining.isEmpty(); ++i) {
                SlotMetadata.ValueType thisType = remaining.get((int)0).valueType;
                if (lastType == null && thisType == SlotMetadata.ValueType.Toolbar) {
                    row.addAll(remaining);
                    remaining.clear();
                    break;
                }
                if (lastType != null && lastType != thisType && (thisType == SlotMetadata.ValueType.Toolbar || lastType == SlotMetadata.ValueType.Boolean && remaining.size() > columns - i)) break;
                SlotMetadata column = remaining.remove(0);
                lastType = column.valueType;
                row.add(column);
            }
            row.buttons.setWidths(columnWidth);
            this.childSlots.add(row);
        }
        return this.childSlots;
    }

    @Override
    public Collection<SlotMetadata> getMetadata() {
        return Arrays.asList(this.metadata);
    }

    public List<SlotMetadata> getAllChildMetadata() {
        return this.childMetadataList;
    }

    public int getCurrentColumns() {
        return this.currentColumns;
    }

    public int getCurrentColumnWidth() {
        return this.currentColumnWidth;
    }

    public void updatePosition(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_) {
    }

    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        this.currentSlotIndex = slotIndex;
        this.button.setWidth(listWidth);
        this.button.setPosition(x, y);
        this.button.setHeight(slotHeight);
        this.button.drawButton(this.mc, mouseX, mouseY, 0.0f);
        DrawUtil.drawRectangle(this.button.getX() + 4, this.button.getMiddleY() - 5, 11.0, 10.0, 0, 0.2f);
        DrawUtil.drawLabel(this.selected ? this.glyphOpen : this.glyphClosed, this.button.getX() + 12, this.button.getMiddleY(), DrawUtil.HAlign.Left, DrawUtil.VAlign.Middle, 0, 0.0f, this.button.getLabelColor(), 1.0f, 1.0, true);
        if (this.masterSlot != null && this.selected) {
            boolean enabled = this.masterSlot.button.isActive();
            for (ScrollListPane.ISlot slot : this.childSlots) {
                slot.setEnabled(enabled);
            }
        }
        if (this.button.mouseOver(mouseX, mouseY)) {
            this.currentTooltip = this.metadata;
        }
        this.currentTooltip = null;
    }

    private void updateButtonLabel() {
        this.button.displayString = this.category.getLabel();
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean mousePressed(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        if (mouseEvent == 0) {
            boolean pressed = this.button.mousePressed(this.mc, x, y);
            if (pressed) {
                this.selected = !this.selected;
                this.updateButtonLabel();
            }
            return pressed;
        }
        return false;
    }

    @Override
    public String[] mouseHover(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        if (this.button.mouseOver(x, y)) {
            return this.metadata.getTooltip();
        }
        return new String[0];
    }

    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        this.button.mouseReleased(x, y);
    }

    @Override
    public boolean keyTyped(char c, int i) {
        return false;
    }

    @Override
    public int compareTo(CategorySlot other) {
        return this.category.compareTo(other.category);
    }

    @Override
    public void setEnabled(boolean enabled) {
    }

    @Override
    public SlotMetadata getLastPressed() {
        return null;
    }

    @Override
    public SlotMetadata getCurrentTooltip() {
        return this.currentTooltip;
    }

    @Override
    public boolean contains(SlotMetadata slotMetadata) {
        return this.childMetadataList.contains(slotMetadata);
    }

    public Category getCategory() {
        return this.category;
    }
}

