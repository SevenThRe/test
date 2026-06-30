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
    public void func_73866_w_() {
        try {
            FullMapProperties fullMapProperties = Journeymap.getClient().getFullMapProperties();
            LocationFormat locationFormat = new LocationFormat();
            this.locationFormatKeys = locationFormat.getFormatKeys(fullMapProperties.locationFormat.get());
            String pos = this.locationFormatKeys.format(fullMapProperties.locationFormatVerbose.get(), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.field_70165_t), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.field_70161_v), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.func_174813_aQ().field_72338_b), MathHelper.func_76141_d((float)this.field_146297_k.field_71439_g.field_70162_ai));
            this.currentLocation = Constants.getString("jm.waypoint.current_location", " " + pos);
            if (this.fieldList.isEmpty()) {
                FontRenderer fr = this.getFontRenderer();
                this.fieldName = new TextBox(this.originalWaypoint.getName(), fr, 160, 20);
                this.fieldName.func_146195_b(true);
                if (this.isNew) {
                    this.fieldName.func_146202_e();
                    this.fieldName.func_146199_i(0);
                }
                this.fieldList.add(this.fieldName);
                int width9chars = this.getFontRenderer().func_78256_a("-30000000") + 10;
                int width3chars = this.getFontRenderer().func_78256_a("255") + 10;
                int h = 20;
                this.fieldX = new TextBox(this.originalWaypoint.getX(), fr, width9chars, h, true, true);
                this.fieldX.setClamp(-30000000, 30000000);
                this.fieldList.add(this.fieldX);
                this.fieldZ = new TextBox(this.originalWaypoint.getZ(), fr, width9chars, h, true, true);
                this.fieldZ.setClamp(-30000000, 30000000);
                this.fieldList.add(this.fieldZ);
                int y = this.originalWaypoint.getY();
                this.fieldY = new TextBox(y < 0 ? "" : Integer.valueOf(y), fr, width3chars, h, true, true);
                this.fieldY.setClamp(0, this.field_146297_k.field_71441_e.func_72800_K() - 1);
                this.fieldY.setMinLength(1);
                this.fieldList.add(this.fieldY);
                this.fieldR = new TextBox("", fr, width3chars, h, true, false);
                this.fieldR.setClamp(0, 255);
                this.fieldR.func_146203_f(3);
                this.fieldList.add(this.fieldR);
                this.fieldG = new TextBox("", fr, width3chars, h, true, false);
                this.fieldG.setClamp(0, 255);
                this.fieldG.func_146203_f(3);
                this.fieldList.add(this.fieldG);
                this.fieldB = new TextBox("", fr, width3chars, h, true, false);
                this.fieldB.setClamp(0, 255);
                this.fieldB.func_146203_f(3);
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
                this.dimScrollPane = new ScrollPane(this.field_146297_k, 0, 0, this.dimButtonList, this.dimButtonList.get(0).getHeight(), 4);
                this.dimScrollPane.func_193651_b(false);
            }
            if (this.field_146292_n.isEmpty()) {
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
                this.field_146292_n.add(this.buttonEnable);
                this.field_146292_n.add(this.buttonRandomize);
                this.field_146292_n.add(this.buttonRemove);
                this.field_146292_n.add(this.buttonReset);
                this.field_146292_n.add(this.buttonSave);
                this.field_146292_n.add(this.buttonClose);
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
            this.func_73866_w_();
            FontRenderer fr = this.getFontRenderer();
            int vpad = 5;
            int hgap = fr.func_78256_a("X") * 3;
            int vgap = this.fieldX.getHeight() + 5;
            int startY = Math.max(30, (this.field_146295_m - 200) / 2);
            int dcw = fr.func_78256_a(this.dimensionsTitle);
            dcw = 8 + Math.max(dcw, this.dimScrollPane.getFitWidth(fr));
            int leftWidth = hgap * 2 + this.fieldX.func_146200_o() + this.fieldY.func_146200_o() + this.fieldZ.func_146200_o();
            int rightWidth = dcw;
            int totalWidth = leftWidth + 10 + rightWidth;
            int leftX = (this.field_146294_l - totalWidth) / 2;
            int leftXEnd = leftX + leftWidth;
            int rightX = leftXEnd + 10;
            int rightXEnd = rightX + rightWidth;
            int leftRowY = startY;
            this.drawLabel(this.labelName, leftX, leftRowY);
            this.fieldName.setWidth(leftWidth);
            this.fieldName.setX(leftX);
            this.fieldName.setY(leftRowY += 12);
            if (!this.fieldName.func_146206_l()) {
                this.fieldName.func_146199_i(this.fieldName.func_146179_b().length());
            }
            this.fieldName.func_146194_f();
            this.drawLabel(this.locationTitle, leftX, leftRowY += vgap + 5);
            this.drawLabelAndField(this.labelX, this.fieldX, leftX, leftRowY += 12);
            this.drawLabelAndField(this.labelZ, this.fieldZ, this.fieldX.getX() + this.fieldX.func_146200_o() + hgap, leftRowY);
            this.drawLabelAndField(this.labelY, this.fieldY, this.fieldZ.getX() + this.fieldZ.func_146200_o() + hgap, leftRowY);
            this.drawLabel(this.colorTitle, leftX, leftRowY += vgap + 5);
            this.drawLabelAndField(this.labelR, this.fieldR, leftX, leftRowY += 12);
            this.drawLabelAndField(this.labelG, this.fieldG, this.fieldR.getX() + this.fieldR.func_146200_o() + hgap, leftRowY);
            this.drawLabelAndField(this.labelB, this.fieldB, this.fieldG.getX() + this.fieldG.func_146200_o() + hgap, leftRowY);
            this.buttonRandomize.func_175211_a(4 + Math.max(this.fieldB.getX() + this.fieldB.func_146200_o() - this.fieldR.getX(), 10 + fr.func_78256_a(this.buttonRandomize.field_146126_j)));
            this.buttonRandomize.setPosition(this.fieldR.getX() - 2, leftRowY += vgap);
            int cpY = this.fieldB.getY();
            int cpSize = this.buttonRandomize.getY() + this.buttonRandomize.getHeight() - cpY - 2;
            int cpHAreaX = this.fieldB.getX() + this.fieldB.func_146200_o();
            int cpHArea = this.fieldName.getX() + this.fieldName.func_146200_o() - cpHAreaX;
            int cpX = cpHAreaX + (cpHArea - cpSize);
            this.drawColorPicker(cpX, cpY, cpSize);
            int iconX = cpHAreaX + (cpX - cpHAreaX) / 2 - this.wpTexture.getWidth() / 2 + 1;
            int iconY = this.buttonRandomize.getY() - 2;
            this.drawWaypoint(iconX, iconY);
            this.buttonEnable.fitWidth(fr);
            this.buttonEnable.func_175211_a(Math.max(leftWidth / 2, this.buttonEnable.getWidth()));
            this.buttonEnable.setPosition(leftX - 2, leftRowY += vgap);
            this.buttonReset.func_175211_a(leftWidth - this.buttonEnable.getWidth() - 2);
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
    public void func_73863_a(int x, int y, float par3) {
        try {
            this.func_146278_c(0);
            this.validate();
            this.layoutButtons();
            this.dimScrollPane.func_148128_a(x, y, par3);
            DrawUtil.drawLabel(this.currentLocation, this.field_146294_l / 2, this.field_146295_m, DrawUtil.HAlign.Center, DrawUtil.VAlign.Above, 0, 1.0f, 0xC0C0C0, 1.0f, 1.0, true);
            for (int k = 0; k < this.field_146292_n.size(); ++k) {
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(k);
                guibutton.func_191745_a(this.field_146297_k, x, y, 0.0f);
            }
            if (this.colorPickTooltip != null && this.colorPickRect.contains(x, y)) {
                this.drawHoveringText(this.colorPickTooltip, x, y, this.getFontRenderer());
                RenderHelper.func_74518_a();
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
        WaypointEditor.func_73734_a((int)(x - 1), (int)(y - 1), (int)(x + sizeI + 1), (int)(y + sizeI + 1), (int)-6250336);
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
        int width = fr.func_78256_a(label) + 4;
        this.func_73731_b(this.getFontRenderer(), label, x - width, y + (field.getHeight() - 8) / 2, Color.cyan.getRGB());
        field.func_146194_f();
    }

    protected void drawLabel(String label, int x, int y) {
        this.func_73731_b(this.getFontRenderer(), label, x, y, Color.cyan.getRGB());
    }

    @Override
    protected void func_73869_a(char par1, int par2) {
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
        while (iterator2.hasNext() && !(done = (field = (GuiTextField)iterator2.next()).func_146201_a(par1, par2))) {
        }
        this.updateWaypointFromForm();
        this.validate();
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        this.dimScrollPane.func_178039_p();
    }

    protected void func_146273_a(int par1, int par2, int par3, long par4) {
        this.checkColorPicker(par1, par2);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            for (GuiTextField guiTextField : this.fieldList) {
                guiTextField.func_146192_a(mouseX, mouseY, mouseButton);
            }
            this.checkColorPicker(mouseX, mouseY);
            Button button = this.dimScrollPane.mouseClicked(mouseX, mouseY, mouseButton);
            if (button != null) {
                this.func_146284_a(button);
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
        this.fieldR.func_146180_a(Integer.toString(c[0]));
        this.fieldG.func_146180_a(Integer.toString(c[1]));
        this.fieldB.func_146180_a(Integer.toString(c[2]));
        this.updateWaypointFromForm();
    }

    protected void func_146284_a(GuiButton guibutton) {
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
                field.func_146195_b(true);
                foundFocus = true;
                break;
            }
            if (!field.func_146206_l()) continue;
            field.func_146195_b(false);
            field.clamp();
            focusNext = true;
        }
        if (!foundFocus) {
            this.fieldList.get(0).func_146195_b(true);
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
        this.field_146292_n.clear();
        this.func_73866_w_();
        this.validate();
    }

    protected void updateWaypointFromForm() {
        this.currentColor = RGB.toInteger(this.getSafeColorInt(this.fieldR), this.getSafeColorInt(this.fieldG), this.getSafeColorInt(this.fieldB));
        this.editedWaypoint.setColor(this.currentColor);
        this.fieldName.func_146193_g(this.editedWaypoint.getSafeColor());
        ArrayList<Integer> dims = new ArrayList<Integer>();
        for (DimensionButton db : this.dimButtonList) {
            if (!db.getToggled().booleanValue()) continue;
            dims.add(db.dimension);
        }
        this.editedWaypoint.setDimensions(dims);
        this.editedWaypoint.setEnable(this.buttonEnable.getToggled());
        this.editedWaypoint.setName(this.fieldName.func_146179_b());
        this.editedWaypoint.setLocation(this.getSafeCoordInt(this.fieldX), this.getSafeCoordInt(this.fieldY), this.getSafeCoordInt(this.fieldZ), this.field_146297_k.field_71439_g.field_71093_bK);
    }

    protected int getSafeColorInt(TextBox field) {
        field.clamp();
        String text = field.func_146179_b();
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
        String text = field.func_146179_b();
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

