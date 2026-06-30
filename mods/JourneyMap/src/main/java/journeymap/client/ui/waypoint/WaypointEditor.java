/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.input.Keyboard
 */
package journeymap.client.ui.waypoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.cartography.color.RGB;
import journeymap.client.data.WorldData;
import journeymap.client.log.JMLogger;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.FullMapProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.component.ScrollPane;
import journeymap.client.ui.component.TextBox;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.option.LocationFormat;
import journeymap.client.ui.waypoint.WaypointManager;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;

public class WaypointEditor
extends JmUI {
    private final TextureImpl wpTexture;
    private final TextureImpl colorPickTexture;
    private final Waypoint originalWaypoint;
    private final boolean isNew;
    String labelName = Constants.getString("jm.waypoint.name");
    String locationTitle = Constants.getString("jm.waypoint.location");
    String colorTitle = Constants.getString("jm.waypoint.color");
    String dimensionsTitle = Constants.getString("jm.waypoint.dimensions");
    String labelX = Constants.getString("jm.waypoint.x");
    String labelY = Constants.getString("jm.waypoint.y");
    String labelZ = Constants.getString("jm.waypoint.z");
    String labelR = Constants.getString("jm.waypoint.red_abbreviated");
    String labelG = Constants.getString("jm.waypoint.green_abbreviated");
    String labelB = Constants.getString("jm.waypoint.blue_abbreviated");
    String currentLocation = "";
    LocationFormat.LocationFormatKeys locationFormatKeys;
    private Button buttonRandomize;
    private OnOffButton buttonEnable;
    private Button buttonRemove;
    private Button buttonReset;
    private Button buttonSave;
    private Button buttonClose;
    private TextBox fieldName;
    private TextBox fieldR;
    private TextBox fieldG;
    private TextBox fieldB;
    private TextBox fieldX;
    private TextBox fieldY;
    private TextBox fieldZ;
    private ArrayList<TextBox> fieldList = new ArrayList();
    private ArrayList<DimensionButton> dimButtonList = new ArrayList();
    private ScrollPane dimScrollPane;
    private Integer currentColor;
    private Rectangle2D.Double colorPickRect;
    private BufferedImage colorPickImg;
    private List<String> colorPickTooltip;
    private Waypoint editedWaypoint;
    private ButtonList bottomButtons;

    public WaypointEditor(Waypoint waypoint, boolean isNew, JmUI returnDisplay) {
        super(Constants.getString(isNew ? "jm.waypoint.new_title" : "jm.waypoint.edit_title"), returnDisplay);
        this.originalWaypoint = waypoint;
        this.editedWaypoint = new Waypoint(this.originalWaypoint);
        this.isNew = isNew;
        this.wpTexture = waypoint.getTexture();
        String tooltip = Constants.birthdayMessage();
        this.colorPickTooltip = tooltip == null ? null : Collections.singletonList(tooltip);
        this.colorPickTexture = tooltip == null ? TextureCache.getTexture(TextureCache.ColorPicker) : TextureCache.getTexture(TextureCache.ColorPicker2);
        try {
            this.colorPickRect = new Rectangle2D.Double(0.0, 0.0, this.colorPickTexture.getWidth(), this.colorPickTexture.getHeight());
            this.colorPickImg = this.colorPickTexture.getImage();
            Keyboard.enableRepeatEvents((boolean)true);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error during WaypointEditor ctor: " + LogFormatter.toPartialString(t));
            UIManager.INSTANCE.closeAll();
        }
    }

    @Override
    public void initGui() {
        try {
            FullMapProperties fullMapProperties = Journeymap.getClient().getFullMapProperties();
            LocationFormat locationFormat = new LocationFormat();
            this.locationFormatKeys = locationFormat.getFormatKeys(fullMapProperties.locationFormat.get());
            String pos = this.locationFormatKeys.format(fullMapProperties.locationFormatVerbose.get(), MathHelper.floor((double)this.mc.player.posX), MathHelper.floor((double)this.mc.player.posZ), MathHelper.floor((double)this.mc.player.getEntityBoundingBox().minY), MathHelper.floor((float)this.mc.player.chunkCoordY));
            this.currentLocation = Constants.getString("jm.waypoint.current_location", " " + pos);
            if (this.fieldList.isEmpty()) {
                FontRenderer fr = this.getFontRenderer();
                this.fieldName = new TextBox(this.originalWaypoint.getName(), fr, 160, 20);
                this.fieldName.setFocused(true);
                if (this.isNew) {
                    this.fieldName.setCursorPositionEnd();
                    this.fieldName.setSelectionPos(0);
                }
                this.fieldList.add(this.fieldName);
                int width9chars = this.getFontRenderer().getStringWidth("-30000000") + 10;
                int width3chars = this.getFontRenderer().getStringWidth("255") + 10;
                int h = 20;
                this.fieldX = new TextBox(this.originalWaypoint.getX(), fr, width9chars, h, true, true);
                this.fieldX.setClamp(-30000000, 30000000);
                this.fieldList.add(this.fieldX);
                this.fieldZ = new TextBox(this.originalWaypoint.getZ(), fr, width9chars, h, true, true);
                this.fieldZ.setClamp(-30000000, 30000000);
                this.fieldList.add(this.fieldZ);
                int y = this.originalWaypoint.getY();
                this.fieldY = new TextBox(y < 0 ? "" : Integer.valueOf(y), fr, width3chars, h, true, true);
                this.fieldY.setClamp(0, this.mc.world.getHeight() - 1);
                this.fieldY.setMinLength(1);
                this.fieldList.add(this.fieldY);
                this.fieldR = new TextBox("", fr, width3chars, h, true, false);
                this.fieldR.setClamp(0, 255);
                this.fieldR.setMaxStringLength(3);
                this.fieldList.add(this.fieldR);
                this.fieldG = new TextBox("", fr, width3chars, h, true, false);
                this.fieldG.setClamp(0, 255);
                this.fieldG.setMaxStringLength(3);
                this.fieldList.add(this.fieldG);
                this.fieldB = new TextBox("", fr, width3chars, h, true, false);
                this.fieldB.setClamp(0, 255);
                this.fieldB.setMaxStringLength(3);
                this.fieldList.add(this.fieldB);
                Collection<Integer> wpDims = this.originalWaypoint.getDimensions();
                for (WorldData.DimensionProvider provider : WorldData.getDimensionProviders(WaypointStore.INSTANCE.getLoadedDimensions())) {
                    int dim = provider.getDimension();
                    String dimName = Integer.toString(dim);
                    try {
                        dimName = WorldData.getSafeDimensionName(provider);
                    }
                    catch (Exception e) {
                        JMLogger.logOnce("Can't get dimension name from provider: ", e);
                    }
                    this.dimButtonList.add(new DimensionButton(0, dim, dimName, wpDims.contains(dim)));
                }
                this.dimScrollPane = new ScrollPane(this.mc, 0, 0, this.dimButtonList, this.dimButtonList.get(0).getHeight(), 4);
                this.dimScrollPane.setShowSelectionBox(false);
            }
            if (this.buttonList.isEmpty()) {
                String on = Constants.getString("jm.common.on");
                String off = Constants.getString("jm.common.off");
                String enableOn = Constants.getString("jm.waypoint.enable", on);
                String enableOff = Constants.getString("jm.waypoint.enable", off);
                this.buttonRandomize = new Button(Constants.getString("jm.waypoint.randomize"));
                this.buttonEnable = new OnOffButton(enableOn, enableOff, true);
                this.buttonEnable.setToggled(this.originalWaypoint.isEnable());
                this.buttonRemove = new Button(Constants.getString("jm.waypoint.remove"));
                this.buttonRemove.setEnabled(!this.isNew);
                this.buttonReset = new Button(Constants.getString("jm.waypoint.reset"));
                this.buttonSave = new Button(Constants.getString("jm.waypoint.save"));
                String closeLabel = this.isNew ? "jm.waypoint.cancel" : "jm.common.close";
                this.buttonClose = new Button(Constants.getString(closeLabel));
                this.buttonList.add(this.buttonEnable);
                this.buttonList.add(this.buttonRandomize);
                this.buttonList.add(this.buttonRemove);
                this.buttonList.add(this.buttonReset);
                this.buttonList.add(this.buttonSave);
                this.buttonList.add(this.buttonClose);
                this.bottomButtons = new ButtonList(this.buttonRemove, this.buttonSave, this.buttonClose);
                this.bottomButtons.equalizeWidths(this.getFontRenderer());
                this.setFormColor(this.originalWaypoint.getColor());
                this.validate();
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
            this.initGui();
            FontRenderer fr = this.getFontRenderer();
            int vpad = 5;
            int hgap = fr.getStringWidth("X") * 3;
            int vgap = this.fieldX.getHeight() + 5;
            int startY = Math.max(30, (this.height - 200) / 2);
            int dcw = fr.getStringWidth(this.dimensionsTitle);
            dcw = 8 + Math.max(dcw, this.dimScrollPane.getFitWidth(fr));
            int leftWidth = hgap * 2 + this.fieldX.getWidth() + this.fieldY.getWidth() + this.fieldZ.getWidth();
            int rightWidth = dcw;
            int totalWidth = leftWidth + 10 + rightWidth;
            int leftX = (this.width - totalWidth) / 2;
            int leftXEnd = leftX + leftWidth;
            int rightX = leftXEnd + 10;
            int rightXEnd = rightX + rightWidth;
            int leftRowY = startY;
            this.drawLabel(this.labelName, leftX, leftRowY);
            this.fieldName.setWidth(leftWidth);
            this.fieldName.setX(leftX);
            this.fieldName.setY(leftRowY += 12);
            if (!this.fieldName.isFocused()) {
                this.fieldName.setSelectionPos(this.fieldName.getText().length());
            }
            this.fieldName.drawTextBox();
            this.drawLabel(this.locationTitle, leftX, leftRowY += vgap + 5);
            this.drawLabelAndField(this.labelX, this.fieldX, leftX, leftRowY += 12);
            this.drawLabelAndField(this.labelZ, this.fieldZ, this.fieldX.getX() + this.fieldX.getWidth() + hgap, leftRowY);
            this.drawLabelAndField(this.labelY, this.fieldY, this.fieldZ.getX() + this.fieldZ.getWidth() + hgap, leftRowY);
            this.drawLabel(this.colorTitle, leftX, leftRowY += vgap + 5);
            this.drawLabelAndField(this.labelR, this.fieldR, leftX, leftRowY += 12);
            this.drawLabelAndField(this.labelG, this.fieldG, this.fieldR.getX() + this.fieldR.getWidth() + hgap, leftRowY);
            this.drawLabelAndField(this.labelB, this.fieldB, this.fieldG.getX() + this.fieldG.getWidth() + hgap, leftRowY);
            this.buttonRandomize.setWidth(4 + Math.max(this.fieldB.getX() + this.fieldB.getWidth() - this.fieldR.getX(), 10 + fr.getStringWidth(this.buttonRandomize.displayString)));
            this.buttonRandomize.setPosition(this.fieldR.getX() - 2, leftRowY += vgap);
            int cpY = this.fieldB.getY();
            int cpSize = this.buttonRandomize.getY() + this.buttonRandomize.getHeight() - cpY - 2;
            int cpHAreaX = this.fieldB.getX() + this.fieldB.getWidth();
            int cpHArea = this.fieldName.getX() + this.fieldName.getWidth() - cpHAreaX;
            int cpX = cpHAreaX + (cpHArea - cpSize);
            this.drawColorPicker(cpX, cpY, cpSize);
            int iconX = cpHAreaX + (cpX - cpHAreaX) / 2 - this.wpTexture.getWidth() / 2 + 1;
            int iconY = this.buttonRandomize.getY() - 2;
            this.drawWaypoint(iconX, iconY);
            this.buttonEnable.fitWidth(fr);
            this.buttonEnable.setWidth(Math.max(leftWidth / 2, this.buttonEnable.getWidth()));
            this.buttonEnable.setPosition(leftX - 2, leftRowY += vgap);
            this.buttonReset.setWidth(leftWidth - this.buttonEnable.getWidth() - 2);
            this.buttonReset.setPosition(leftXEnd - this.buttonReset.getWidth() + 2, leftRowY);
            int rightRow = startY;
            this.drawLabel(this.dimensionsTitle, rightX, rightRow);
            int scrollHeight = this.buttonReset.getY() + this.buttonReset.getHeight() - 2 - (rightRow += 12);
            this.dimScrollPane.setDimensions(dcw, scrollHeight, 0, 0, rightX, rightRow);
            int totalRow = Math.max(leftRowY + vgap, rightRow + vgap);
            this.bottomButtons.layoutFilledHorizontal(fr, leftX - 2, totalRow, rightXEnd + 2, 4, true);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error during WaypointEditor layout: " + LogFormatter.toPartialString(t));
            UIManager.INSTANCE.closeAll();
        }
    }

    @Override
    public void drawScreen(int x, int y, float par3) {
        try {
            this.drawBackground(0);
            this.validate();
            this.layoutButtons();
            this.dimScrollPane.drawScreen(x, y, par3);
            DrawUtil.drawLabel(this.currentLocation, this.width / 2, this.height, DrawUtil.HAlign.Center, DrawUtil.VAlign.Above, 0, 1.0f, 0xC0C0C0, 1.0f, 1.0, true);
            for (int k = 0; k < this.buttonList.size(); ++k) {
                GuiButton guibutton = (GuiButton)this.buttonList.get(k);
                guibutton.drawButton(this.mc, x, y, 0.0f);
            }
            if (this.colorPickTooltip != null && this.colorPickRect.contains(x, y)) {
                this.drawHoveringText(this.colorPickTooltip, x, y, this.getFontRenderer());
                RenderHelper.disableStandardItemLighting();
            }
            this.drawTitle();
            this.drawLogo();
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error during WaypointEditor layout: " + LogFormatter.toPartialString(t));
            UIManager.INSTANCE.closeAll();
        }
    }

    protected void drawWaypoint(int x, int y) {
        DrawUtil.drawColoredImage(this.wpTexture, this.currentColor, 1.0f, x, y - this.wpTexture.getHeight() / 2, 0.0);
    }

    protected void drawColorPicker(int x, int y, float size) {
        int sizeI = (int)size;
        WaypointEditor.drawRect((int)(x - 1), (int)(y - 1), (int)(x + sizeI + 1), (int)(y + sizeI + 1), (int)-6250336);
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
    }

    protected void drawLabelAndField(String label, TextBox field, int x, int y) {
        field.setX(x);
        field.setY(y);
        FontRenderer fr = this.getFontRenderer();
        int width = fr.getStringWidth(label) + 4;
        this.drawString(this.getFontRenderer(), label, x - width, y + (field.getHeight() - 8) / 2, Color.cyan.getRGB());
        field.drawTextBox();
    }

    protected void drawLabel(String label, int x, int y) {
        this.drawString(this.getFontRenderer(), label, x, y, Color.cyan.getRGB());
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        GuiTextField field;
        boolean done;
        switch (par2) {
            case 1: {
                this.closeAndReturn();
                return;
            }
            case 28: {
                this.save();
                return;
            }
            case 15: {
                this.validate();
                this.onTab();
                return;
            }
        }
        Iterator<TextBox> iterator2 = this.fieldList.iterator();
        while (iterator2.hasNext() && !(done = (field = (GuiTextField)iterator2.next()).textboxKeyTyped(par1, par2))) {
        }
        this.updateWaypointFromForm();
        this.validate();
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.dimScrollPane.handleMouseInput();
    }

    protected void mouseClickMove(int par1, int par2, int par3, long par4) {
        this.checkColorPicker(par1, par2);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            for (GuiTextField guiTextField : this.fieldList) {
                guiTextField.mouseClicked(mouseX, mouseY, mouseButton);
            }
            this.checkColorPicker(mouseX, mouseY);
            Button button = this.dimScrollPane.mouseClicked(mouseX, mouseY, mouseButton);
            if (button != null) {
                this.actionPerformed(button);
            }
        }
    }

    protected void checkColorPicker(int mouseX, int mouseY) {
        if (this.colorPickRect.contains(mouseX, mouseY)) {
            int x = mouseX - (int)this.colorPickRect.x;
            int y = mouseY - (int)this.colorPickRect.y;
            this.setFormColor(this.colorPickImg.getRGB(x, y));
        }
    }

    protected void setFormColor(Integer color) {
        this.currentColor = color;
        int[] c = RGB.ints(color);
        this.fieldR.setText(Integer.toString(c[0]));
        this.fieldG.setText(Integer.toString(c[1]));
        this.fieldB.setText(Integer.toString(c[2]));
        this.updateWaypointFromForm();
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (this.dimButtonList.contains(guibutton)) {
            DimensionButton dimButton = (DimensionButton)guibutton;
            dimButton.toggle();
            this.updateWaypointFromForm();
        } else {
            if (guibutton == this.buttonRandomize) {
                this.setRandomColor();
                return;
            }
            if (guibutton == this.buttonEnable) {
                this.buttonEnable.toggle();
                return;
            }
            if (guibutton == this.buttonRemove) {
                this.remove();
                return;
            }
            if (guibutton == this.buttonReset) {
                this.resetForm();
                return;
            }
            if (guibutton == this.buttonSave) {
                this.save();
                return;
            }
            if (guibutton == this.buttonClose) {
                this.refreshAndClose(this.originalWaypoint);
                return;
            }
        }
    }

    protected void setRandomColor() {
        this.editedWaypoint.setRandomColor();
        this.setFormColor(this.editedWaypoint.getColor());
    }

    protected void onTab() {
        boolean focusNext = false;
        boolean foundFocus = false;
        for (TextBox field : this.fieldList) {
            if (focusNext) {
                field.setFocused(true);
                foundFocus = true;
                break;
            }
            if (!field.isFocused()) continue;
            field.setFocused(false);
            field.clamp();
            focusNext = true;
        }
        if (!foundFocus) {
            this.fieldList.get(0).setFocused(true);
        }
    }

    protected boolean validate() {
        boolean valid = true;
        if (this.fieldName != null) {
            valid = this.fieldName.hasMinLength();
        }
        if (valid && this.fieldY != null) {
            valid = this.fieldY.hasMinLength();
        }
        if (this.buttonSave != null) {
            this.buttonSave.setEnabled(valid && (this.isNew || !this.originalWaypoint.equals(this.editedWaypoint)));
        }
        return valid;
    }

    protected void remove() {
        WaypointStore.INSTANCE.remove(this.originalWaypoint);
        this.refreshAndClose(null);
    }

    protected void save() {
        if (!this.validate()) {
            return;
        }
        this.updateWaypointFromForm();
        WaypointStore.INSTANCE.remove(this.originalWaypoint);
        WaypointStore.INSTANCE.save(this.editedWaypoint);
        this.refreshAndClose(this.editedWaypoint);
    }

    protected void resetForm() {
        this.editedWaypoint = new Waypoint(this.originalWaypoint);
        this.dimButtonList.clear();
        this.fieldList.clear();
        this.buttonList.clear();
        this.initGui();
        this.validate();
    }

    protected void updateWaypointFromForm() {
        this.currentColor = RGB.toInteger(this.getSafeColorInt(this.fieldR), this.getSafeColorInt(this.fieldG), this.getSafeColorInt(this.fieldB));
        this.editedWaypoint.setColor(this.currentColor);
        this.fieldName.setTextColor(this.editedWaypoint.getSafeColor());
        ArrayList<Integer> dims = new ArrayList<Integer>();
        for (DimensionButton db : this.dimButtonList) {
            if (!db.getToggled().booleanValue()) continue;
            dims.add(db.dimension);
        }
        this.editedWaypoint.setDimensions(dims);
        this.editedWaypoint.setEnable(this.buttonEnable.getToggled());
        this.editedWaypoint.setName(this.fieldName.getText());
        this.editedWaypoint.setLocation(this.getSafeCoordInt(this.fieldX), this.getSafeCoordInt(this.fieldY), this.getSafeCoordInt(this.fieldZ), this.mc.player.dimension);
    }

    protected int getSafeColorInt(TextBox field) {
        field.clamp();
        String text = field.getText();
        if (text == null || text.isEmpty()) {
            return 0;
        }
        int val = 0;
        try {
            val = Integer.parseInt(text);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        return Math.max(0, Math.min(255, val));
    }

    protected int getSafeCoordInt(TextBox field) {
        String text = field.getText();
        if (text == null || text.isEmpty() || text.equals("-")) {
            return 0;
        }
        int val = 0;
        try {
            val = Integer.parseInt(text);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        return val;
    }

    protected void refreshAndClose(Waypoint focusWaypoint) {
        if (this.returnDisplay != null && this.returnDisplay instanceof WaypointManager) {
            UIManager.INSTANCE.openWaypointManager(focusWaypoint, new Fullscreen());
            return;
        }
        Fullscreen.state().requireRefresh();
        this.closeAndReturn();
    }

    @Override
    protected void closeAndReturn() {
        if (this.returnDisplay == null) {
            UIManager.INSTANCE.closeAll();
        } else {
            UIManager.INSTANCE.open(this.returnDisplay);
        }
    }

    class DimensionButton
    extends OnOffButton {
        public final int dimension;

        DimensionButton(int id, int dimension, String dimensionName, boolean toggled) {
            super(id, String.format("%s: %s", dimensionName, Constants.getString("jm.common.on")), String.format("%s: %s", dimensionName, Constants.getString("jm.common.off")), toggled);
            this.dimension = dimension;
            this.setToggled(toggled);
        }
    }
}

