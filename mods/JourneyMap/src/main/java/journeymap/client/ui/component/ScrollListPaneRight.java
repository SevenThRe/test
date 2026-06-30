/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiListExtended
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
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
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ScrollListPaneRight<T extends ISlot>
extends GuiListExtended {
    JmUI parent;
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

    public ScrollListPaneRight(JmUI parent, Minecraft mc, int width, int height, int top, int bottom, int slotHeight) {
        super(mc, width, height, top, bottom, slotHeight);
        this.parent = parent;
        this.setDimensions(width, height, top, bottom);
    }

    public void setDimensions(int width, int height, int top, int bottom) {
        super.setDimensions(width, height, top, bottom);
        this.scrollbarX = this.width - this.hpad;
        this.listWidth = 150;
        this.left = this.width - 170;
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
        int j1 = this.getMaxScroll();
        slot.drawEntry(slotIndex, x, y, this.getListWidth() + (j1 > 0 ? 0 : 15), slotHeight, mouseX, mouseY, selected, 0.0f);
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
            int i1 = this.left;
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

    public int getSlotIndexFromScreenCoords(int posX, int posY) {
        int i = this.left;
        int j = this.left + this.getListWidth();
        int k = posY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
        int l = k / this.slotHeight;
        return posX < this.getScrollBarX() && posX >= i && posX <= j && l >= 0 && k >= 0 && l < this.getSize() ? l : -1;
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

    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
        if (this.visible) {
            this.mouseX = mouseXIn;
            this.mouseY = mouseYIn;
            this.drawBackground();
            int i = this.getScrollBarX();
            int j = i + 6;
            this.bindAmountScrolled();
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            int k = this.left;
            int l = this.top + 4 - (int)this.amountScrolled;
            if (this.hasListHeader) {
                this.drawListHeader(k, l, tessellator);
            }
            this.drawSelectionBox(k, l, mouseXIn, mouseYIn, partialTicks);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ZERO, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel((int)7425);
            GlStateManager.disableTexture2D();
            int i1 = 4;
            int j1 = this.getMaxScroll();
            if (j1 > 0) {
                int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                int l1 = (int)this.amountScrolled * (this.bottom - this.top - (k1 = MathHelper.clamp((int)k1, (int)32, (int)(this.bottom - this.top - 8)))) / j1 + this.top;
                if (l1 < this.top) {
                    l1 = this.top;
                }
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos((double)i, (double)this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)j, (double)this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)j, (double)this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)i, (double)this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos((double)i, (double)(l1 + k1), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)j, (double)(l1 + k1), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)j, (double)l1, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)i, (double)l1, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
                tessellator.draw();
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos((double)i, (double)(l1 + k1 - 1), 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos((double)(j - 1), (double)(l1 + k1 - 1), 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos((double)(j - 1), (double)l1, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos((double)i, (double)l1, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
                tessellator.draw();
            }
            this.renderDecorations(mouseXIn, mouseYIn);
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel((int)7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }

    protected void drawSelectionBox(int insideLeft, int insideTop, int mouseXIn, int mouseYIn, float partialTicks) {
        int i = this.getSize();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        int h = this.bottom - 38;
        GL11.glEnable((int)3089);
        int yend = 38 + h;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int factor = sr.getScaleFactor();
        int bottomY = sr.getScaledHeight() - yend;
        GL11.glScissor((int)0, (int)(bottomY * factor), (int)9999, (int)(h * factor));
        for (int j = 0; j < i; ++j) {
            int k = insideTop + j * this.slotHeight + this.headerPadding;
            int l = this.slotHeight - 4;
            if (k > this.bottom || k + l < this.top) {
                this.updateItemPos(j, insideLeft, k, partialTicks);
            }
            if (this.showSelectionBox && this.isSelected(j)) {
                int i1 = this.left + (this.width / 2 - this.getListWidth() / 2);
                int j1 = this.left + this.width / 2 + this.getListWidth() / 2;
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.disableTexture2D();
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos((double)i1, (double)(k + l + 2), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)j1, (double)(k + l + 2), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)j1, (double)(k - 2), 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)i1, (double)(k - 2), 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos((double)(i1 + 1), (double)(k + l + 1), 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)(j1 - 1), (double)(k + l + 1), 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)(j1 - 1), (double)(k - 1), 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos((double)(i1 + 1), (double)(k - 1), 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
            }
            this.drawSlot(j, insideLeft, k, l, mouseXIn, mouseYIn, partialTicks);
        }
        GL11.glDisable((int)3089);
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

