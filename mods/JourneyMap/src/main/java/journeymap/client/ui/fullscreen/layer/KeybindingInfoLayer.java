/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.Tuple
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.fullscreen.layer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.forge.event.KeyEventHandler;
import journeymap.client.io.ThemeLoader;
import journeymap.client.properties.FullMapProperties;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.fullscreen.layer.LayerDelegate;
import journeymap.client.ui.theme.Theme;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;

public class KeybindingInfoLayer
implements LayerDelegate.Layer {
    private final List<DrawStep> drawStepList = new ArrayList<DrawStep>(1);
    private FontRenderer fontRenderer;
    private final KeybindingInfoStep keybindingInfoStep;
    private FullMapProperties fullMapProperties;
    private final Fullscreen fullscreen;
    private final Minecraft mc;

    public KeybindingInfoLayer(Fullscreen fullscreen) {
        this.fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;
        this.fullMapProperties = Journeymap.getClient().getFullMapProperties();
        this.mc = FMLClientHandler.instance().getClient();
        this.fullscreen = fullscreen;
        this.keybindingInfoStep = new KeybindingInfoStep();
        this.drawStepList.add(this.keybindingInfoStep);
    }

    @Override
    public List<DrawStep> onMouseMove(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockPos, float fontScale, boolean isScrolling) {
        if (this.fullMapProperties.showKeys.get().booleanValue()) {
            if (this.keybindingInfoStep.panelRect.contains(mousePosition)) {
                this.keybindingInfoStep.hide();
            } else {
                this.keybindingInfoStep.show();
            }
            return this.drawStepList;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<DrawStep> onMouseClick(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, int button, boolean doubleClick, float fontScale) {
        return this.fullMapProperties.showKeys.get() != false ? this.drawStepList : Collections.EMPTY_LIST;
    }

    @Override
    public boolean propagateClick() {
        return true;
    }

    class KeybindingInfoStep
    implements DrawStep {
        private double screenWidth;
        private double screenHeight;
        private double fontScale;
        private int pad;
        private ArrayList<Tuple<String, String>> lines;
        private int keyNameWidth = 0;
        private int keyDescWidth = 0;
        private int lineHeight = 0;
        Rectangle2D panelRect = new Rectangle2D.Double();
        Theme theme = ThemeLoader.getCurrentTheme();
        Theme.LabelSpec statusLabelSpec;
        int bgColor;
        float fgAlphaDefault = 1.0f;
        float bgAlphaDefault = 0.7f;
        float fgAlpha = this.fgAlphaDefault;
        float bgAlpha = this.bgAlphaDefault;

        KeybindingInfoStep() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
            if (pass == DrawStep.Pass.Text) {
                if (KeybindingInfoLayer.this.fullscreen.getMenuToolbarBounds() == null) {
                    return;
                }
                this.updateLayout(gridRenderer, fontScale);
                DrawUtil.drawRectangle(this.panelRect.getX(), this.panelRect.getY(), this.panelRect.getWidth(), this.panelRect.getHeight(), this.bgColor, this.bgAlpha);
                int x = (int)this.panelRect.getX() + this.pad + this.keyNameWidth;
                int y = (int)this.panelRect.getY() + this.pad;
                int firstColor = this.theme.fullscreen.statusLabel.highlight.getColor();
                int secondColor = this.theme.fullscreen.statusLabel.foreground.getColor();
                try {
                    GlStateManager.enableBlend();
                    for (Tuple<String, String> line : this.lines) {
                        DrawUtil.drawLabel((String)line.getFirst(), x, y, DrawUtil.HAlign.Left, DrawUtil.VAlign.Middle, null, 0.0f, firstColor, this.fgAlpha, fontScale, false);
                        DrawUtil.drawLabel((String)line.getSecond(), x + this.pad, y, DrawUtil.HAlign.Right, DrawUtil.VAlign.Middle, null, 0.0f, secondColor, this.fgAlpha, fontScale, false);
                        y += this.lineHeight;
                    }
                }
                finally {
                    GlStateManager.disableBlend();
                }
            }
        }

        @Override
        public int getDisplayOrder() {
            return 0;
        }

        @Override
        public String getModId() {
            return "journeymap";
        }

        void hide() {
            this.bgAlpha = 0.2f;
            this.fgAlpha = 0.2f;
        }

        void show() {
            this.bgAlpha = this.bgAlphaDefault;
            this.fgAlpha = this.fgAlphaDefault;
        }

        private void updateLayout(GridRenderer gridRenderer, double fontScale) {
            Theme theme = ThemeLoader.getCurrentTheme();
            this.statusLabelSpec = theme.fullscreen.statusLabel;
            this.bgColor = this.statusLabelSpec.background.getColor();
            if (fontScale != this.fontScale || this.screenWidth != (double)gridRenderer.getWidth() || this.screenHeight != (double)gridRenderer.getHeight()) {
                this.screenWidth = gridRenderer.getWidth();
                this.screenHeight = gridRenderer.getHeight();
                this.fontScale = fontScale;
                this.pad = (int)(10.0 * fontScale);
                this.lineHeight = (int)(3.0 + fontScale * (double)((KeybindingInfoLayer)KeybindingInfoLayer.this).fontRenderer.FONT_HEIGHT);
                this.initLines(fontScale);
                int panelWidth = this.keyNameWidth + this.keyDescWidth + 4 * this.pad;
                int panelHeight = this.lines.size() * this.lineHeight + this.pad;
                int scaleFactor = KeybindingInfoLayer.this.fullscreen.getScreenScaleFactor();
                int panelX = (int)this.screenWidth - theme.container.toolbar.vertical.margin * scaleFactor - panelWidth;
                int panelY = (int)this.screenHeight - theme.container.toolbar.horizontal.margin * scaleFactor - panelHeight;
                this.panelRect.setRect(panelX, panelY, panelWidth, panelHeight);
                Rectangle2D.Double menuToolbarRect = KeybindingInfoLayer.this.fullscreen.getMenuToolbarBounds();
                if (menuToolbarRect != null && menuToolbarRect.intersects(this.panelRect) && (double)panelX <= menuToolbarRect.getMaxX()) {
                    panelY = (int)menuToolbarRect.getMinY() - 5 - panelHeight;
                    this.panelRect.setRect(panelX, panelY, panelWidth, panelHeight);
                }
            }
        }

        private void initLines(double fontScale) {
            this.lines = new ArrayList();
            this.keyDescWidth = 0;
            this.keyNameWidth = 0;
            this.bgAlpha = this.fgAlphaDefault;
            this.bgAlpha = this.bgAlphaDefault;
            for (KeyBinding keyBinding : KeyEventHandler.INSTANCE.getInGuiKeybindings()) {
                this.initLine(keyBinding, fontScale);
            }
            this.initLine(((KeybindingInfoLayer)KeybindingInfoLayer.this).mc.gameSettings.keyBindChat, fontScale);
        }

        private void initLine(KeyBinding keyBinding, double fontScale) {
            String keyName = keyBinding.getDisplayName();
            String keyDesc = Constants.getString(keyBinding.getKeyDescription());
            Tuple line = new Tuple((Object)keyName, (Object)keyDesc);
            this.lines.add((Tuple<String, String>)line);
            this.keyNameWidth = (int)Math.max((double)this.keyNameWidth, fontScale * (double)KeybindingInfoLayer.this.fontRenderer.getStringWidth(keyName));
            this.keyDescWidth = (int)Math.max((double)this.keyDescWidth, fontScale * (double)KeybindingInfoLayer.this.fontRenderer.getStringWidth(keyDesc));
        }
    }
}

