/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 */
package journeymap.client.ui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import journeymap.client.ui.component.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class ButtonList
extends ArrayList<Button> {
    static EnumSet<Layout> VerticalLayouts = EnumSet.of(Layout.Vertical, Layout.CenteredVertical);
    static EnumSet<Layout> HorizontalLayouts = EnumSet.of(Layout.Horizontal, Layout.CenteredHorizontal, Layout.DistributedHorizontal, Layout.FilledHorizontal);
    private Layout layout = Layout.Horizontal;
    private Direction direction = Direction.LeftToRight;
    private String label;
    private int hgap = 0;

    public ButtonList() {
    }

    public ButtonList(String label) {
        this.label = label;
    }

    public ButtonList(List<GuiButton> buttons) {
        for (GuiButton button : buttons) {
            if (!(button instanceof Button)) continue;
            this.add((Button)button);
        }
    }

    public ButtonList(Button ... buttons) {
        super(Arrays.asList(buttons));
    }

    public int getWidth() {
        return this.getWidth(-1, this.hgap);
    }

    public int getWidth(int hgap) {
        return this.getWidth(-1, hgap);
    }

    private int getWidth(int buttonWidth, int hgap) {
        if (this.isEmpty()) {
            return 0;
        }
        int total = 0;
        if (HorizontalLayouts.contains((Object)this.layout)) {
            int visible = 0;
            for (Button button : this) {
                if (!button.isVisible()) continue;
                total = buttonWidth > 0 ? (total += buttonWidth) : (total += button.getWidth());
                ++visible;
            }
            if (visible > 1) {
                total += hgap * (visible - 1);
            }
        } else {
            if (buttonWidth > 0) {
                total = buttonWidth;
            }
            for (Button button : this) {
                if (!button.isVisible()) continue;
                total = Math.max(total, button.getWidth());
            }
        }
        return total;
    }

    public int getHeight() {
        return this.getHeight(0);
    }

    public int getHeight(int vgap) {
        if (this.isEmpty()) {
            return 0;
        }
        int total = 0;
        if (VerticalLayouts.contains((Object)this.layout)) {
            int visible = 0;
            for (Button button : this) {
                if (!button.isVisible()) continue;
                total += button.getHeight();
                ++visible;
            }
            if (visible > 1) {
                total += vgap * (visible - 1);
            }
        } else {
            for (Button button : this) {
                if (!button.isVisible()) continue;
                total = Math.max(total, button.getHeight() + vgap);
            }
        }
        return total;
    }

    public int getLeftX() {
        int left = Integer.MAX_VALUE;
        for (Button button : this) {
            if (!button.isVisible()) continue;
            left = Math.min(left, button.getX());
        }
        if (left == Integer.MAX_VALUE) {
            left = 0;
        }
        return left;
    }

    public int getTopY() {
        int top = Integer.MAX_VALUE;
        for (Button button : this) {
            if (!button.isVisible()) continue;
            top = Math.min(top, button.getY());
        }
        if (top == Integer.MAX_VALUE) {
            top = 0;
        }
        return top;
    }

    public int getBottomY() {
        int bottom = Integer.MIN_VALUE;
        for (Button button : this) {
            if (!button.isVisible()) continue;
            bottom = Math.max(bottom, button.getY() + button.getHeight());
        }
        if (bottom == Integer.MIN_VALUE) {
            bottom = 0;
        }
        return bottom;
    }

    public int getRightX() {
        int right = 0;
        for (Button button : this) {
            if (!button.isVisible()) continue;
            right = Math.max(right, button.getX() + button.getWidth());
        }
        return right;
    }

    public Button findButton(int id) {
        for (Button button : this) {
            if (button.field_146127_k != id) continue;
            return button;
        }
        return null;
    }

    public void setLayout(Layout layout, Direction direction) {
        this.layout = layout;
        this.direction = direction;
    }

    public ButtonList layoutHorizontal(int startX, int y, boolean leftToRight, int hgap) {
        return this.layoutHorizontal(startX, y, leftToRight, hgap, false);
    }

    public ButtonList layoutHorizontal(int startX, int y, boolean leftToRight, int hgap, boolean alignCenter) {
        this.layout = Layout.Horizontal;
        this.direction = leftToRight ? Direction.LeftToRight : Direction.RightToLeft;
        this.hgap = hgap;
        Button last = null;
        for (Button button2 : this) {
            if (!button2.field_146125_m) continue;
            if (last == null) {
                if (leftToRight) {
                    button2.rightOf(startX).setY(y);
                } else {
                    button2.leftOf(startX).setY(y);
                }
            } else if (leftToRight) {
                button2.rightOf(last, hgap).setY(y);
            } else {
                button2.leftOf(last, hgap).setY(y);
            }
            last = button2;
        }
        if (alignCenter && !this.isEmpty()) {
            int maxButtonHeight = this.stream().max(Comparator.comparing(Button::getHeight)).get().getHeight();
            this.forEach((? super E button) -> {
                if (button.getHeight() < maxButtonHeight) {
                    int buttonHeight = button.getHeight();
                    button.setY((maxButtonHeight - buttonHeight) / 2 + button.getY());
                }
            });
        }
        this.layout = Layout.Horizontal;
        return this;
    }

    public ButtonList layoutVertical(int x, int startY, boolean leftToRight, int vgap) {
        this.layout = Layout.Vertical;
        this.direction = leftToRight ? Direction.LeftToRight : Direction.RightToLeft;
        Button last = null;
        for (Button button : this) {
            if (last == null) {
                if (leftToRight) {
                    button.rightOf(x).setY(startY);
                } else {
                    button.leftOf(x).setY(startY);
                }
            } else if (leftToRight) {
                button.rightOf(x).below(last, vgap);
            } else {
                button.leftOf(x).below(last, vgap);
            }
            last = button;
        }
        this.layout = Layout.Vertical;
        return this;
    }

    public ButtonList layoutCenteredVertical(int x, int centerY, boolean leftToRight, int vgap) {
        this.layout = Layout.CenteredVertical;
        int height = this.getHeight(vgap);
        this.layoutVertical(x, centerY - height / 2, leftToRight, vgap);
        return this;
    }

    public ButtonList layoutCenteredHorizontal(int centerX, int y, boolean leftToRight, int hgap) {
        return this.layoutCenteredHorizontal(centerX, y, leftToRight, hgap, false);
    }

    public ButtonList layoutCenteredHorizontal(int centerX, int y, boolean leftToRight, int hgap, boolean alignCenter) {
        this.layout = Layout.CenteredHorizontal;
        int width = this.getWidth(hgap);
        this.layoutHorizontal(centerX - width / 2, y, leftToRight, hgap, alignCenter);
        return this;
    }

    public ButtonList layoutDistributedHorizontal(int leftX, int y, int rightX, boolean leftToRight) {
        int hgap;
        this.layout = Layout.DistributedHorizontal;
        if (this.size() == 0) {
            return this;
        }
        int width = this.getWidth(0);
        int filler = rightX - leftX - width;
        int gaps = this.size() - 1;
        int n = gaps == 0 ? 0 : (hgap = filler >= gaps ? filler / gaps : 0);
        if (leftToRight) {
            this.layoutHorizontal(leftX, y, true, hgap);
        } else {
            this.layoutHorizontal(rightX, y, false, hgap);
        }
        this.layout = Layout.DistributedHorizontal;
        return this;
    }

    public ButtonList layoutFilledHorizontal(FontRenderer fr, int leftX, int y, int rightX, int hgap, boolean leftToRight) {
        this.hgap = hgap;
        this.layout = Layout.FilledHorizontal;
        if (this.size() == 0) {
            return this;
        }
        this.equalizeWidths(fr);
        int width = this.getWidth(hgap);
        int remaining = rightX - leftX - width;
        if (remaining > this.size()) {
            int gaps = hgap * this.size();
            int area = rightX - leftX - gaps;
            int wider = area / this.size();
            this.setWidths(wider);
            this.layoutDistributedHorizontal(leftX, y, rightX, leftToRight);
        } else {
            this.layoutCenteredHorizontal((rightX - leftX) / 2, y, leftToRight, hgap);
        }
        this.layout = Layout.FilledHorizontal;
        return this;
    }

    public void setFitWidths(FontRenderer fr) {
        this.fitWidths(fr);
    }

    public boolean isHorizontal() {
        return this.layout != Layout.Vertical && this.layout != Layout.CenteredVertical;
    }

    public ButtonList setEnabled(boolean enabled) {
        for (Button button : this) {
            button.setEnabled(enabled);
        }
        return this;
    }

    public ButtonList setVisible(boolean visible) {
        for (Button button : this) {
            button.setVisible(visible);
        }
        return this;
    }

    public ButtonList setOptions(boolean enabled, boolean drawBackground, boolean drawFrame) {
        for (Button button : this) {
            button.setEnabled(enabled);
            button.setDrawFrame(drawFrame);
            button.setDrawBackground(drawBackground);
        }
        return this;
    }

    public ButtonList setDefaultStyle(boolean defaultStyle) {
        for (Button button : this) {
            button.setDefaultStyle(defaultStyle);
        }
        return this;
    }

    public ButtonList draw(Minecraft minecraft, int mouseX, int mouseY) {
        for (Button button : this) {
            button.func_191745_a(minecraft, mouseX, mouseY, 0.0f);
        }
        return this;
    }

    public void setHeights(int height) {
        for (Button button : this) {
            button.setHeight(height);
        }
    }

    public void setWidths(int width) {
        for (Button button : this) {
            button.func_175211_a(width);
        }
    }

    public void fitWidths(FontRenderer fr) {
        for (Button button : this) {
            button.fitWidth(fr);
        }
    }

    public void setDrawButtons(boolean draw) {
        for (Button button : this) {
            button.setDrawButton(draw);
        }
    }

    public void equalizeWidths(FontRenderer fr) {
        int maxWidth = 0;
        for (Button button : this) {
            if (!button.isVisible()) continue;
            button.fitWidth(fr);
            maxWidth = Math.max(maxWidth, button.getWidth());
        }
        this.setWidths(maxWidth);
    }

    public void equalizeWidths(FontRenderer fr, int hgap, int maxTotalWidth) {
        int pad;
        this.hgap = hgap;
        int maxWidth = 0;
        for (Button button : this) {
            button.fitWidth(fr);
            maxWidth = Math.max(maxWidth, button.getWidth());
        }
        int totalWidth = this.getWidth(maxWidth, hgap);
        if (totalWidth <= maxTotalWidth) {
            this.setWidths(maxWidth);
        } else {
            totalWidth = this.getWidth(hgap);
        }
        if (totalWidth < maxTotalWidth && (pad = (maxTotalWidth - totalWidth) / this.size()) > 0) {
            for (Button button : this) {
                button.func_175211_a(button.getWidth() + pad);
            }
        }
    }

    public int getVisibleButtonCount() {
        int count = 0;
        for (Button button : this) {
            if (!button.field_146125_m) continue;
            ++count;
        }
        return count;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ButtonList reverse() {
        Collections.reverse(this);
        return this;
    }

    public static enum Direction {
        LeftToRight,
        RightToLeft;

    }

    public static enum Layout {
        Horizontal,
        Vertical,
        CenteredHorizontal,
        CenteredVertical,
        DistributedHorizontal,
        FilledHorizontal;

    }
}

