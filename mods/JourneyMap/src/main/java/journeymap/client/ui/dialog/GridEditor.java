/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 */
package journeymap.client.ui.dialog;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import journeymap.client.Constants;
import journeymap.client.io.ThemeLoader;
import journeymap.client.model.GridSpec;
import journeymap.client.model.GridSpecs;
import journeymap.client.model.MapType;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.client.ui.theme.Theme;
import journeymap.client.ui.theme.ThemeToggle;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GridEditor
extends JmUI {
    private final TextureImpl colorPickTexture;
    private final int tileSize = 128;
    private final int sampleTextureSize = 128;
    private GridSpecs gridSpecs;
    private ListPropertyButton<GridSpec.Style> buttonStyle;
    private IntSliderButton buttonOpacity;
    private CheckBox checkDay;
    private CheckBox checkNight;
    private CheckBox checkUnderground;
    private ThemeToggle buttonDay;
    private ThemeToggle buttonNight;
    private ThemeToggle buttonUnderground;
    private Integer activeColor;
    private MapType activeMapType;
    private Button buttonReset;
    private Button buttonCancel;
    private Button buttonClose;
    private Rectangle2D.Double colorPickRect;
    private BufferedImage colorPickImg;
    private ButtonList topButtons;
    private ButtonList leftButtons;
    private ButtonList leftChecks;
    private ButtonList bottomButtons;
    private ResourceLocation colorPicResource = Constants.birthdayMessage() == null ? TextureCache.ColorPicker : TextureCache.ColorPicker2;

    public GridEditor(JmUI returnDisplay) {
        super(Constants.getString("jm.common.grid_editor"), returnDisplay);
        this.colorPickImg = TextureCache.resolveImage(this.colorPicResource);
        this.colorPickTexture = TextureCache.getTexture(this.colorPicResource);
        this.colorPickRect = new Rectangle2D.Double(0.0, 0.0, this.colorPickTexture.getWidth(), this.colorPickTexture.getHeight());
        this.gridSpecs = Journeymap.getClient().getCoreProperties().gridSpecs.clone();
        this.activeMapType = MapType.day(0);
        this.activeColor = this.gridSpecs.getSpec(this.activeMapType).getColor();
        Keyboard.enableRepeatEvents((boolean)true);
    }

    @Override
    public void func_73866_w_() {
        try {
            if (this.field_146292_n.isEmpty()) {
                GridSpec spec = this.gridSpecs.getSpec(this.activeMapType);
                this.buttonStyle = new ListPropertyButton<GridSpec.Style>(EnumSet.allOf(GridSpec.Style.class), Constants.getString("jm.common.grid_style"), new EnumField<GridSpec.Style>(Category.Hidden, "", spec.style));
                this.buttonOpacity = new IntSliderButton(new IntegerField(Category.Hidden, "", 0, 100, (int)Math.ceil(spec.alpha * 100.0f)), Constants.getString("jm.common.grid_opacity") + " : ", "", 0, 100, true);
                this.topButtons = new ButtonList(this.buttonStyle, this.buttonOpacity);
                this.topButtons.equalizeWidths(this.getFontRenderer());
                this.checkDay = new CheckBox("", this.activeMapType == MapType.day(0));
                this.checkNight = new CheckBox("", this.activeMapType == MapType.night(0));
                this.checkUnderground = new CheckBox("", this.activeMapType.isUnderground());
                this.leftChecks = new ButtonList(this.checkDay, this.checkNight, this.checkUnderground);
                Theme theme = ThemeLoader.getCurrentTheme();
                this.buttonDay = new ThemeToggle(theme, "jm.fullscreen.map_day", "day");
                this.buttonDay.setToggled(this.activeMapType == MapType.day(0), false);
                this.buttonNight = new ThemeToggle(theme, "jm.fullscreen.map_night", "night");
                this.buttonNight.setToggled(this.activeMapType == MapType.night(0), false);
                this.buttonUnderground = new ThemeToggle(theme, "jm.fullscreen.map_caves", "caves");
                this.buttonUnderground.setToggled(this.activeMapType.isUnderground(), false);
                this.leftButtons = new ButtonList(this.buttonDay, this.buttonNight, this.buttonUnderground);
                this.buttonReset = new Button(Constants.getString("jm.waypoint.reset"));
                this.buttonCancel = new Button(Constants.getString("jm.waypoint.cancel"));
                this.buttonClose = new Button(Constants.getString("jm.waypoint.save"));
                this.bottomButtons = new ButtonList(this.buttonReset, this.buttonCancel, this.buttonClose);
                this.bottomButtons.equalizeWidths(this.getFontRenderer());
                this.field_146292_n.addAll(this.topButtons);
                this.field_146292_n.addAll(this.leftChecks);
                this.field_146292_n.addAll(this.leftButtons);
                this.field_146292_n.addAll(this.bottomButtons);
                this.updatePreview(this.activeMapType);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toString(t));
            UIManager.INSTANCE.closeAll();
        }
    }

    @Override
    protected void layoutButtons() {
        try {
            this.func_73866_w_();
            int hgap = 6;
            int vgap = 6;
            int startY = Math.max(40, (this.field_146295_m - 230) / 2);
            int centerX = this.field_146294_l / 2;
            int cpSize = this.topButtons.getHeight(6);
            int topRowWidth = 6 + cpSize + ((Button)this.topButtons.get(0)).getWidth();
            int topRowLeft = centerX - topRowWidth / 2;
            this.topButtons.layoutVertical(topRowLeft + 6 + cpSize, startY, true, 6);
            this.drawColorPicker(topRowLeft, this.topButtons.getTopY(), cpSize);
            int tileX = centerX - 64;
            int tileY = this.topButtons.getBottomY() + 12;
            this.drawMapTile(tileX, tileY);
            this.leftButtons.layoutVertical(tileX - ((Button)this.leftButtons.get(0)).getWidth() - 6, tileY + 6, true, 6);
            this.leftChecks.setHeights(((Button)this.leftButtons.get(0)).getHeight());
            this.leftChecks.setWidths(15);
            this.leftChecks.layoutVertical(this.leftButtons.getLeftX() - this.checkDay.getWidth(), this.leftButtons.getTopY(), true, 6);
            int bottomY = Math.min(tileY + 128 + 12, this.field_146295_m - 10 - this.buttonClose.getHeight());
            this.bottomButtons.equalizeWidths(this.getFontRenderer(), 6, ((Button)this.topButtons.get(0)).getRightX() - topRowLeft);
            this.bottomButtons.layoutCenteredHorizontal(centerX, bottomY, true, 6);
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.layoutButtons: " + LogFormatter.toString(t));
        }
    }

    @Override
    public void func_73863_a(int x, int y, float par3) {
        try {
            this.func_146278_c(0);
            this.layoutButtons();
            for (int k = 0; k < this.field_146292_n.size(); ++k) {
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(k);
                guibutton.func_191745_a(this.field_146297_k, x, y, 0.0f);
            }
            this.drawTitle();
            this.drawLogo();
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.drawScreen: " + LogFormatter.toString(t));
        }
    }

    protected void drawColorPicker(int x, int y, float size) {
        int sizeI = (int)size;
        GridEditor.func_73734_a((int)(x - 1), (int)(y - 1), (int)(x + sizeI + 1), (int)(y + sizeI + 1), (int)-6250336);
        if (this.colorPickRect.width != (double)size) {
            Image image = this.colorPickTexture.getImage().getScaledInstance(sizeI, sizeI, 2);
            this.colorPickImg = new BufferedImage(sizeI, sizeI, 1);
            Graphics2D g = this.colorPickImg.createGraphics();
            g.drawImage(image, 0, 0, sizeI, sizeI, null);
            g.dispose();
        }
        this.colorPickRect.setRect(x, y, size, size);
        float scale = size / (float)this.colorPickTexture.getWidth();
        DrawUtil.drawImage(this.colorPickTexture, x, y, false, scale, 0.0);
        GridSpec activeSpec = this.gridSpecs.getSpec(this.activeMapType);
        int colorX = activeSpec.getColorX();
        int colorY = activeSpec.getColorY();
        if (colorX > 0 && colorY > 0) {
            DrawUtil.drawRectangle((colorX += x) - 2, (colorY += y) - 2, 5.0, 5.0, Color.darkGray.getRGB(), 0.8f);
            DrawUtil.drawRectangle(colorX - 1, colorY, 3.0, 1.0, this.activeColor, 1.0f);
            DrawUtil.drawRectangle(colorX, colorY - 1, 1.0, 3.0, this.activeColor, 1.0f);
        }
    }

    protected void drawMapTile(int x, int y) {
        float scale = 1.0f;
        GridEditor.func_73734_a((int)(x - 1), (int)(y - 1), (int)(x + 128 + 1), (int)(y + 128 + 1), (int)-6250336);
        TextureImpl tileTex = this.getTileSample(this.activeMapType);
        DrawUtil.drawImage(tileTex, x, y, false, 1.0f, 0.0);
        if (scale == 2.0f) {
            DrawUtil.drawImage(tileTex, x + 128, y, true, 1.0f, 0.0);
            DrawUtil.drawImage(tileTex, x, y + 128, true, 1.0f, 180.0);
            DrawUtil.drawImage(tileTex, x + 128, y + 128, false, 1.0f, 180.0);
        }
        GridSpec gridSpec = this.gridSpecs.getSpec(this.activeMapType);
        gridSpec.beginTexture(9728, 33071, 1.0f);
        DrawUtil.drawBoundTexture(0.0, 0.0, x, y, 0.0, 0.25, 0.25, x + 128, y + 128);
        gridSpec.finishTexture();
    }

    protected void drawLabel(String label, int x, int y) {
        this.func_73731_b(this.getFontRenderer(), label, x, y, Color.cyan.getRGB());
    }

    @Override
    protected void func_73869_a(char par1, int par2) {
        try {
            switch (par2) {
                case 1: {
                    this.closeAndReturn();
                    return;
                }
                case 28: {
                    this.saveAndClose();
                    return;
                }
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.keyTyped: " + LogFormatter.toString(t));
        }
    }

    protected void func_146273_a(int par1, int par2, int par3, long par4) {
        try {
            if (this.buttonOpacity.dragging) {
                this.updateGridSpecs();
            } else {
                this.checkColorPicker(par1, par2);
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.mouseClickMove: " + LogFormatter.toString(t));
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        try {
            super.func_73864_a(mouseX, mouseY, mouseButton);
            if (mouseButton == 0) {
                this.checkColorPicker(mouseX, mouseY);
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.mouseClicked: " + LogFormatter.toString(t));
        }
    }

    protected void checkColorPicker(int mouseX, int mouseY) {
        if (this.colorPickRect.contains(mouseX, mouseY)) {
            int x = mouseX - (int)this.colorPickRect.x;
            int y = mouseY - (int)this.colorPickRect.y;
            this.activeColor = this.colorPickImg.getRGB(x, y);
            GridSpec activeSpec = this.gridSpecs.getSpec(this.activeMapType);
            activeSpec.setColorCoords(x, y);
            this.updateGridSpecs();
        }
    }

    protected void func_146284_a(GuiButton guibutton) {
        try {
            if (guibutton == this.buttonDay) {
                this.updatePreview(MapType.day(0));
            } else if (guibutton == this.buttonNight) {
                this.updatePreview(MapType.night(0));
            } else if (guibutton == this.buttonUnderground) {
                this.updatePreview(MapType.underground(0, 0));
            }
            this.updateGridSpecs();
            if (guibutton == this.buttonReset) {
                this.resetGridSpecs();
                return;
            }
            if (guibutton == this.buttonCancel) {
                this.resetGridSpecs();
                this.closeAndReturn();
                return;
            }
            if (guibutton == this.buttonClose) {
                this.saveAndClose();
                return;
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in GridEditor.actionPerformed: " + LogFormatter.toString(t));
        }
    }

    protected void updatePreview(MapType mapType) {
        this.activeMapType = mapType;
        GridSpec activeSpec = this.gridSpecs.getSpec(this.activeMapType);
        this.activeColor = activeSpec.getColor();
        this.buttonOpacity.setValue((int)(activeSpec.alpha * 100.0f));
        this.buttonStyle.setValue(activeSpec.style);
        this.checkDay.setToggled(mapType.isDay());
        this.checkNight.setToggled(mapType.isNight());
        this.checkUnderground.setToggled(mapType.isUnderground());
        this.buttonDay.setToggled(mapType.isDay());
        this.buttonNight.setToggled(mapType.isNight());
        this.buttonUnderground.setToggled(mapType.isUnderground());
    }

    protected void updateGridSpecs() {
        GridSpec activeSpec = this.gridSpecs.getSpec(this.activeMapType);
        int colorX = activeSpec.getColorX();
        int colorY = activeSpec.getColorY();
        GridSpec newSpec = new GridSpec(this.buttonStyle.getField().get(), new Color(this.activeColor), (float)this.buttonOpacity.getValue() / 100.0f).setColorCoords(colorX, colorY);
        if (this.checkDay.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.day(0), newSpec);
        }
        if (this.checkNight.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.night(0), newSpec);
        }
        if (this.checkUnderground.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.underground(0, 0), newSpec);
        }
    }

    protected void saveAndClose() {
        this.updateGridSpecs();
        Journeymap.getClient().getCoreProperties().gridSpecs.updateFrom(this.gridSpecs);
        Journeymap.getClient().getCoreProperties().save();
        this.closeAndReturn();
    }

    protected void resetGridSpecs() {
        if (this.checkDay.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.day(0), GridSpecs.DEFAULT_DAY.clone());
        }
        if (this.checkNight.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.night(0), GridSpecs.DEFAULT_NIGHT.clone());
        }
        if (this.checkUnderground.getToggled().booleanValue()) {
            this.gridSpecs.setSpec(MapType.underground(0, 0), GridSpecs.DEFAULT_UNDERGROUND.clone());
        }
        this.field_146292_n.clear();
        this.func_73866_w_();
    }

    @Override
    protected void closeAndReturn() {
        if (this.returnDisplay == null) {
            UIManager.INSTANCE.closeAll();
        } else {
            UIManager.INSTANCE.open(this.returnDisplay);
        }
    }

    public TextureImpl getTileSample(MapType mapType) {
        if (mapType.isNight()) {
            return TextureCache.getTexture(TextureCache.TileSampleNight);
        }
        if (mapType.isUnderground()) {
            return TextureCache.getTexture(TextureCache.TileSampleUnderground);
        }
        return TextureCache.getTexture(TextureCache.TileSampleDay);
    }
}

