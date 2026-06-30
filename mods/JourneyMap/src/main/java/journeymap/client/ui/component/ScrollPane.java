/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiSlot
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.input.Mouse
 */
package journeymap.client.ui.component;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;

public class ScrollPane
extends GuiSlot {
    public int paneWidth = 0;
    public int paneHeight = 0;
    public Point2D.Double origin = new Point2D.Double();
    protected Scrollable selected = null;
    private Integer frameColor = new Color(-6250336).getRGB();
    private List<? extends Scrollable> items;
    private Minecraft mc;
    private int _mouseX;
    private int _mouseY;
    private boolean showFrame = true;
    private int firstVisibleIndex;
    private int lastVisibleIndex;

    public ScrollPane(Minecraft mc, int width, int height, List<? extends Scrollable> items, int itemHeight, int itemGap) {
        super(mc, width, height, 16, height, itemHeight + itemGap);
        this.items = items;
        this.paneWidth = width;
        this.paneHeight = height;
        this.mc = mc;
    }

    public int getX() {
        return (int)this.origin.getX();
    }

    public int getY() {
        return (int)this.origin.getY();
    }

    public int getSlotHeight() {
        return this.slotHeight;
    }

    public void setDimensions(int width, int height, int marginTop, int marginBottom, int x, int y) {
        super.setDimensions(width, height, marginTop, height - marginBottom);
        this.paneWidth = width;
        this.paneHeight = height;
        this.origin.setLocation(x, y);
    }

    protected int getSize() {
        return this.items.size();
    }

    protected void elementClicked(int i, boolean flag, int p1, int p2) {
        this.selected = this.items.get(i);
    }

    protected boolean isSelected(int i) {
        return this.items.get(i) == this.selected;
    }

    public boolean isSelected(Scrollable item) {
        return item == this.selected;
    }

    public void select(Scrollable item) {
        this.selected = item;
    }

    protected void drawBackground() {
    }

    public Button mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            ArrayList<? extends Scrollable> itemsCopy = new ArrayList<Scrollable>(this.items);
            for (Scrollable scrollable : itemsCopy) {
                if (scrollable == null || !this.inFullView(scrollable)) continue;
                if (scrollable instanceof Button) {
                    Button button = (Button)scrollable;
                    if (!button.mousePressed(this.mc, mouseX, mouseY)) continue;
                    this.actionPerformed(button);
                    return button;
                }
                scrollable.clickScrollable(this.mc, mouseX, mouseY);
            }
        }
        return null;
    }

    public void drawScreen(int mX, int mY, float f) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.getX(), (float)this.getY(), (float)0.0f);
        this._mouseX = mX;
        this._mouseY = mY;
        if (this.selected == null || Mouse.isButtonDown((int)0) || Mouse.getDWheel() != 0 || !Mouse.next() || Mouse.getEventButtonState()) {
            // empty if block
        }
        this.firstVisibleIndex = -1;
        this.lastVisibleIndex = -1;
        super.drawScreen(mX - this.getX(), mY - this.getY(), f);
        GlStateManager.popMatrix();
    }

    protected void drawSlot(int index, int xPosition, int y, int l, int var6, int var7, float f) {
        if (this.firstVisibleIndex == -1) {
            this.firstVisibleIndex = index;
        }
        this.lastVisibleIndex = Math.max(this.lastVisibleIndex, index);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(-this.getX()), (float)(-this.getY()), (float)0.0f);
        int margin = 4;
        int itemX = this.getX() + 2;
        int itemY = y + this.getY();
        Scrollable item = this.items.get(index);
        item.setPosition(itemX, itemY);
        item.setScrollableWidth(this.paneWidth - 4);
        if (this.inFullView(item)) {
            item.drawScrollable(this.mc, this._mouseX, this._mouseY);
        } else {
            int paneBottomY = this.getY() + this.paneHeight;
            int itemBottomY = itemY + item.getHeight();
            Integer drawY = null;
            int yDiff = 0;
            if (itemY < this.getY() && itemBottomY > this.getY()) {
                drawY = this.getY();
                yDiff = drawY - itemY;
            } else if (itemY < paneBottomY && itemBottomY > paneBottomY) {
                drawY = itemY;
                yDiff = itemBottomY - paneBottomY;
            }
            if (drawY != null) {
                item.drawPartialScrollable(this.mc, itemX, drawY, item.getWidth(), item.getHeight() - yDiff);
            }
        }
        GlStateManager.popMatrix();
    }

    public boolean inFullView(Scrollable item) {
        return item.getY() >= this.getY() && item.getY() + item.getHeight() <= this.getY() + this.paneHeight;
    }

    protected int getScrollBarX() {
        return this.paneWidth;
    }

    public int getWidth() {
        boolean scrollVisible = 0 < this.getAmountScrolled();
        return this.paneWidth + (scrollVisible ? 5 : 0);
    }

    public int getFitWidth(FontRenderer fr) {
        int fit = 0;
        for (Scrollable scrollable : this.items) {
            fit = Math.max(fit, scrollable.getFitWidth(fr));
        }
        return fit;
    }

    public void setShowFrame(boolean showFrame) {
        this.showFrame = showFrame;
    }

    protected void drawContainerBackground(Tessellator tess) {
        int width = this.getWidth();
        float alpha = 0.4f;
        DrawUtil.drawRectangle(0.0, this.top, width, this.paneHeight, Color.BLACK.getRGB(), alpha);
        DrawUtil.drawRectangle(width - 6, this.top, 5.0, this.paneHeight, Color.BLACK.getRGB(), alpha);
        if (this.showFrame) {
            alpha = 1.0f;
            DrawUtil.drawRectangle(-1.0, -1.0, width + 2, 1.0, this.frameColor, alpha);
            DrawUtil.drawRectangle(-1.0, this.paneHeight, width + 2, 1.0, this.frameColor, alpha);
            DrawUtil.drawRectangle(-1.0, -1.0, 1.0, this.paneHeight + 1, this.frameColor, alpha);
            DrawUtil.drawRectangle(width + 1, -1.0, 1.0, this.paneHeight + 2, this.frameColor, alpha);
        }
    }

    public void handleMouseInput() {
        super.handleMouseInput();
    }

    public int getFirstVisibleIndex() {
        return this.firstVisibleIndex;
    }

    public int getLastVisibleIndex() {
        return this.lastVisibleIndex;
    }

    public static interface Scrollable {
        public void setPosition(int var1, int var2);

        public int getX();

        public int getY();

        public int getWidth();

        public void setScrollableWidth(int var1);

        public int getFitWidth(FontRenderer var1);

        public int getHeight();

        public void drawScrollable(Minecraft var1, int var2, int var3);

        public void drawPartialScrollable(Minecraft var1, int var2, int var3, int var4, int var5);

        public void clickScrollable(Minecraft var1, int var2, int var3);
    }
}

