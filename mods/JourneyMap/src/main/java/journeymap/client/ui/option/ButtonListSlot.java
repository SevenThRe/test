/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.option;

import com.google.common.base.Strings;
import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.option.CategorySlot;
import journeymap.client.ui.option.SlotMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ButtonListSlot
implements ScrollListPane.ISlot,
Comparable<ButtonListSlot> {
    static int hgap = 8;
    Minecraft mc = FMLClientHandler.instance().getClient();
    FontRenderer fontRenderer;
    ButtonList buttons;
    HashMap<Button, SlotMetadata> buttonOptionMetadata;
    CategorySlot parent;
    SlotMetadata lastPressed;
    SlotMetadata currentToolTip;
    Integer colorToolbarBgStart;
    Integer colorToolbarBgEnd;

    public ButtonListSlot(CategorySlot parent) {
        this.fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;
        this.buttons = new ButtonList();
        this.buttonOptionMetadata = new HashMap();
        this.lastPressed = null;
        this.currentToolTip = null;
        this.colorToolbarBgStart = new Color(0, 0, 100).getRGB();
        this.colorToolbarBgEnd = new Color(0, 0, 100).getRGB();
        this.parent = parent;
    }

    public ButtonListSlot add(SlotMetadata slotMetadata) {
        this.buttons.add(slotMetadata.getButton());
        this.buttonOptionMetadata.put(slotMetadata.getButton(), slotMetadata);
        return this;
    }

    public ButtonListSlot addAll(Collection<SlotMetadata> slotMetadataCollection) {
        for (SlotMetadata slotMetadata : slotMetadataCollection) {
            this.add(slotMetadata);
        }
        return this;
    }

    public ButtonListSlot merge(ButtonListSlot other) {
        for (SlotMetadata otherSlot : other.buttonOptionMetadata.values()) {
            this.add(otherSlot);
        }
        return this;
    }

    public void clear() {
        this.buttons.clear();
        this.buttonOptionMetadata.clear();
    }

    @Override
    public Collection<SlotMetadata> getMetadata() {
        return this.buttonOptionMetadata.values();
    }

    public void updatePosition(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_) {
    }

    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        int margin = 0;
        if (this.parent.getCurrentColumnWidth() > 0) {
            int cols = listWidth / this.parent.currentColumnWidth;
            margin = (listWidth - (hgap * cols - 1 + cols * this.parent.getCurrentColumnWidth())) / 2;
            x += margin;
            listWidth -= margin * 2;
        }
        SlotMetadata tooltipMetadata = null;
        if (this.buttons.size() > 0) {
            this.buttons.setHeights(slotHeight);
            if (this.buttonOptionMetadata.get(this.buttons.get(0)).isToolbar()) {
                this.buttons.fitWidths(this.fontRenderer);
                this.buttons.layoutHorizontal(x + listWidth - hgap, y, false, hgap);
                DrawUtil.drawGradientRect(x, y, listWidth, slotHeight, this.colorToolbarBgStart, 0.15f, this.colorToolbarBgEnd, 0.6f);
            } else {
                this.buttons.setWidths(this.parent.currentColumnWidth);
                this.buttons.layoutHorizontal(x, y, true, hgap);
            }
            for (Button button : this.buttons) {
                button.drawButton(this.mc, mouseX, mouseY, 0.0f);
                if (tooltipMetadata != null || !button.mouseOver(mouseX, mouseY)) continue;
                tooltipMetadata = this.buttonOptionMetadata.get(button);
            }
        }
        this.currentToolTip = tooltipMetadata;
    }

    public boolean mousePressed(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        if (mouseEvent == 0) {
            for (Button button : this.buttons) {
                if (!button.mousePressed(this.mc, x, y)) continue;
                this.lastPressed = this.buttonOptionMetadata.get(button);
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] mouseHover(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        for (Button button : this.buttons) {
            if (!button.mouseOver(x, y)) continue;
            return this.buttonOptionMetadata.get(button).getTooltip();
        }
        return new String[0];
    }

    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        for (Button button : this.buttons) {
            button.mouseReleased(x, y);
        }
    }

    @Override
    public boolean keyTyped(char c, int i) {
        for (SlotMetadata slot : this.buttonOptionMetadata.values()) {
            if (!slot.button.isMouseOver() || !slot.button.keyTyped(c, i)) continue;
            this.lastPressed = slot;
            return true;
        }
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {
        for (SlotMetadata slot : this.buttonOptionMetadata.values()) {
            if (slot.isMasterPropertyForCategory()) continue;
            slot.button.setEnabled(enabled);
        }
    }

    public List<ScrollListPane.ISlot> getChildSlots(int listWidth, int columnWidth) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public SlotMetadata getLastPressed() {
        return this.lastPressed;
    }

    @Override
    public SlotMetadata getCurrentTooltip() {
        return this.currentToolTip;
    }

    @Override
    public int getColumnWidth() {
        this.buttons.equalizeWidths(this.fontRenderer);
        return ((Button)this.buttons.get(0)).getWidth();
    }

    @Override
    public boolean contains(SlotMetadata slotMetadata) {
        return this.buttonOptionMetadata.values().contains(slotMetadata);
    }

    protected String getFirstButtonString() {
        if (this.buttons.size() > 0) {
            return ((Button)this.buttons.get((int)0)).displayString;
        }
        return null;
    }

    @Override
    public int compareTo(ButtonListSlot o) {
        String buttonString = this.getFirstButtonString();
        String otherButtonString = o.getFirstButtonString();
        if (!Strings.isNullOrEmpty((String)buttonString)) {
            return buttonString.compareTo(otherButtonString);
        }
        if (!Strings.isNullOrEmpty((String)otherButtonString)) {
            return 1;
        }
        return 0;
    }
}

