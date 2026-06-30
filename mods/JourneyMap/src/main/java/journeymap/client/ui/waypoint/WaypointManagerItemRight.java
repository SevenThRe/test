/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.waypoint;

import java.awt.Color;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.model.Waypoint;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.component.ScrollListPaneRight;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.client.ui.waypoint.WaypointManagerRight;
import journeymap.client.waypoint.WaypointStore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

public class WaypointManagerItemRight
implements ScrollListPaneRight.ISlot {
    static Integer background = new Color(20, 20, 20).getRGB();
    static Integer backgroundHover = new Color(40, 40, 40).getRGB();
    FontRenderer fontRenderer;
    WaypointManagerRight manager;
    int x;
    int y;
    int width;
    int internalWidth;
    Integer distance;
    Waypoint waypoint;
    OnOffButton buttonEnable;
    Button buttonRemove;
    Button buttonEdit;
    Button buttonFind;
    Button buttonTeleport;
    Button buttonChat;
    int hgap = 4;
    ButtonList buttonListLeft;
    ButtonList buttonListRight;
    int slotIndex;
    SlotMetadata<Waypoint> slotMetadata;

    public WaypointManagerItemRight(Waypoint waypoint, FontRenderer fontRenderer, WaypointManagerRight manager) {
        boolean id = false;
        this.waypoint = waypoint;
        this.fontRenderer = fontRenderer;
        this.manager = manager;
        SlotMetadata slotMetadata = new SlotMetadata(null, null, null, false);
        String on = Constants.getString("jm.common.on");
        String off = Constants.getString("jm.common.off");
        this.buttonEnable = new OnOffButton(on, off, true);
        this.buttonEnable.setToggled(waypoint.isEnable());
        this.buttonFind = new Button(Constants.getString("jm.waypoint.find"));
        this.buttonTeleport = new Button(Constants.getString("jm.waypoint.teleport"));
        this.buttonTeleport.setDrawButton(manager.canUserTeleport);
        this.buttonTeleport.setEnabled(manager.canUserTeleport);
        this.buttonListLeft = new ButtonList(this.buttonEnable, this.buttonFind);
        this.buttonListLeft.setHeights(manager.rowHeight);
        this.buttonListLeft.fitWidths(fontRenderer);
        this.buttonEdit = new Button(Constants.getString("jm.waypoint.edit"));
        this.buttonRemove = new Button(Constants.getString("jm.waypoint.remove"));
        this.buttonChat = new Button(Constants.getString("jm.waypoint.chat"));
        this.buttonChat.setTooltip(Constants.getString("jm.waypoint.chat.tooltip"));
        if (waypoint.getName().endsWith("\u00a7b\u00a7b")) {
            this.buttonListRight = new ButtonList();
            this.buttonListRight.setHeights(manager.rowHeight);
        } else {
            this.buttonListRight = new ButtonList();
            this.buttonListRight.setHeights(manager.rowHeight);
        }
        this.buttonListRight.fitWidths(fontRenderer);
        this.internalWidth = fontRenderer.func_78263_a('X') * 32;
        this.internalWidth += Math.max(manager.colLocation, manager.colName);
        this.internalWidth += this.buttonListLeft.getWidth(this.hgap);
        this.internalWidth += this.buttonListRight.getWidth(this.hgap);
        this.internalWidth += 10;
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFitWidth(FontRenderer fr) {
        return this.width;
    }

    public int getHeight() {
        return this.manager.rowHeight;
    }

    public void drawPartialScrollable(Minecraft mc, int x, int y, int width, int height) {
        DrawUtil.drawRectangle(this.x, this.y, this.width, this.manager.rowHeight, background, 0.4f);
    }

    protected int drawLabels(Minecraft mc, int x, int y, Integer color) {
        boolean waypointValid;
        if (this.waypoint == null) {
            return 0;
        }
        boolean bl = waypointValid = this.waypoint.isEnable() && this.waypoint.isInPlayerDimension();
        if (color == null) {
            color = waypointValid ? this.waypoint.getSafeColor() : 0x808080;
        }
        FontRenderer fr = FMLClientHandler.instance().getClient().field_71466_p;
        int yOffset = 1 + (this.manager.rowHeight - fr.field_78288_b) / 2;
        fr.func_175063_a(String.format("%sm", this.getDistance()), (float)(x + this.manager.colLocation), (float)(y + yOffset), color.intValue());
        String name = waypointValid ? this.waypoint.getName() : TextFormatting.STRIKETHROUGH + this.waypoint.getName();
        return fr.func_175063_a(name, (float)this.manager.colName, (float)(y + yOffset), color.intValue());
    }

    protected void drawWaypoint(int x, int y) {
        boolean waypointValid = this.waypoint.isEnable() && this.waypoint.isInPlayerDimension();
        int color = waypointValid ? this.waypoint.getColor() : 0x808080;
        TextureImpl wpTexture = this.waypoint.getTexture();
        DrawUtil.drawColoredImage(wpTexture, color, 1.0f, x, y - wpTexture.getHeight() / 2, 0.0);
    }

    protected void enableWaypoint(boolean enable) {
        this.buttonEnable.setToggled(enable);
        this.waypoint.setEnable(enable);
    }

    protected int getButtonEnableCenterX() {
        return this.buttonEnable.getCenterX();
    }

    protected int getNameLeftX() {
        return this.x + this.manager.getMargin() + this.manager.colName;
    }

    protected int getLocationLeftX() {
        return this.x + this.manager.getMargin() + this.manager.colLocation;
    }

    public boolean clickScrollable(int mouseX, int mouseY) {
        boolean mouseOver = false;
        if (this.waypoint == null) {
            return false;
        }
        this.buttonEnable.toggle();
        this.waypoint.setEnable(this.buttonEnable.getToggled());
        if (this.waypoint.isDirty()) {
            WaypointStore.INSTANCE.save(this.waypoint);
        }
        mouseOver = true;
        return mouseOver;
    }

    public int getDistance() {
        return this.distance == null ? 0 : this.distance;
    }

    public int getDistanceTo(EntityPlayer player) {
        if (this.distance == null) {
            this.distance = (int)player.func_174791_d().func_72438_d(this.waypoint.getPosition());
        }
        return this.distance;
    }

    @Override
    public Collection<SlotMetadata> getMetadata() {
        return null;
    }

    public void func_192633_a(int slotIndex, int x, int y, float partialTicks) {
    }

    public void func_192634_a(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        Minecraft mc = this.manager.getMinecraft();
        this.width = listWidth;
        this.setPosition(x, y);
        if (this.waypoint == null) {
            return;
        }
        int margin = this.manager.getMargin();
        boolean hover = this.manager.isSelected(this) || mouseX >= this.x + margin && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.manager.rowHeight;
        this.buttonListLeft.setOptions(true, hover, true);
        this.buttonListRight.setOptions(true, hover, true);
        Integer color = hover ? backgroundHover : background;
        float alpha = hover ? 1.0f : 0.4f;
        DrawUtil.drawRectangle(this.x + margin, this.y, this.width, this.manager.rowHeight, color, alpha);
        this.drawWaypoint(this.x + margin + this.manager.colWaypoint, this.y + this.manager.rowHeight / 2);
        this.drawLabels(mc, this.x + margin, this.y, null);
        this.buttonFind.setEnabled(this.waypoint.isInPlayerDimension());
        this.buttonTeleport.setEnabled(this.waypoint.isTeleportReady());
        this.buttonListRight.layoutHorizontal(x + this.width - margin, y, false, this.hgap).draw(mc, mouseX, mouseY);
        this.buttonListLeft.layoutHorizontal(this.buttonListRight.getLeftX() - this.hgap * 2, y, false, this.hgap).draw(mc, mouseX, mouseY);
    }

    public boolean func_148278_a(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        return this.clickScrollable(x, y);
    }

    @Override
    public String[] mouseHover(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        for (Button button : this.buttonListLeft) {
            if (!button.func_146115_a()) continue;
            this.manager.drawHoveringText(button.getTooltip(), x, y, FMLClientHandler.instance().getClient().field_71466_p);
        }
        return new String[0];
    }

    public void func_148277_b(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
    }

    @Override
    public boolean keyTyped(char c, int i) {
        return false;
    }

    public List<ScrollListPaneRight.ISlot> getChildSlots(int listWidth, int columnWidth) {
        return null;
    }

    @Override
    public SlotMetadata getLastPressed() {
        return null;
    }

    @Override
    public SlotMetadata getCurrentTooltip() {
        return null;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.buttonEnable.setToggled(this.waypoint.isEnable());
    }

    @Override
    public int getColumnWidth() {
        return this.width;
    }

    @Override
    public boolean contains(SlotMetadata slotMetadata) {
        return false;
    }

    static class DistanceComparator
    extends Sort {
        EntityPlayer player;

        public DistanceComparator(EntityPlayer player, boolean ascending) {
            super(ascending);
            this.player = player;
        }

        @Override
        public int compare(WaypointManagerItemRight o1, WaypointManagerItemRight o2) {
            double dist1 = o1.getDistanceTo(this.player);
            double dist2 = o2.getDistanceTo(this.player);
            if (this.ascending) {
                return Double.compare(dist1, dist2);
            }
            return Double.compare(dist2, dist1);
        }
    }

    static class NameComparator
    extends Sort {
        public NameComparator(boolean ascending) {
            super(ascending);
        }

        @Override
        public int compare(WaypointManagerItemRight o1, WaypointManagerItemRight o2) {
            if (this.ascending) {
                return o1.waypoint.getName().compareToIgnoreCase(o2.waypoint.getName());
            }
            return o2.waypoint.getName().compareToIgnoreCase(o1.waypoint.getName());
        }
    }

    static abstract class Sort
    implements Comparator<WaypointManagerItemRight> {
        boolean ascending;

        Sort(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || o != null && this.getClass() == o.getClass();
        }

        public int hashCode() {
            return this.ascending ? 1 : 0;
        }
    }
}

