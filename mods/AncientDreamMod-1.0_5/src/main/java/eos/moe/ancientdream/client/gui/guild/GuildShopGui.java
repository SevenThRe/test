/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui.guild;

import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuildShopGui
extends GuiScreen {
    private ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/shop.png");
    private ResourceLocation BAR = new ResourceLocation("ancientdream", "gui/guild/scroll_bar.png");
    private ResourceLocation SLOT = new ResourceLocation("ancientdream", "gui/guild/slot.png");
    private ResourceLocation SLOT1 = new ResourceLocation("ancientdream", "gui/guild/slot_2.png");
    private ResourceLocation BUY = new ResourceLocation("ancientdream", "gui/guild/buy.png");
    private ResourceLocation BUY1 = new ResourceLocation("ancientdream", "gui/guild/buy_2.png");
    private ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/guild/5_2.png");
    private String type = "\u65e0\u5206\u7c7b";
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private ItemData selectItem;
    private ItemData hoverItem;
    private ShopData data;
    private Point scrollPoint;
    private float maxScrollDistance;
    private float scrollDistance;

    public GuildShopGui(ShopData data) {
        this.data = data;
    }

    public void setShopData(ShopData data) {
        this.data = data;
        if (this.selectItem != null) {
            this.selectItem = data.items.stream().filter(i -> ((ItemData)i).key.equals(this.selectItem.key)).findFirst().orElse(null);
        }
    }

    public void initGui() {
        super.initGui();
        this.xSize = 390.0f;
        this.ySize = 307.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        this.hoverItem = null;
        GlStateManager.disableLighting();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, this.BG);
        RenderUtils.drawText(this.data.money, 162.0, 278.0, false, true);
        RenderUtils.drawText(this.data.points, 44.0, 278.0, false, true);
        RenderUtils.drawText(this.data.score, 272.0, 278.0, false, true);
        GlStateManager.pushMatrix();
        for (int i = 0; i < this.data.types.size(); ++i) {
            int fontWidth = RenderUtils.getTextWidth(null, (String)this.data.types.get(i));
            RenderUtils.drawTexture(17.0, 45.0, fontWidth + 10, 12.5, this.BTN);
            if (((String)this.data.types.get(i)).equals(this.type)) {
                RenderUtils.drawText("\u00a7f" + (String)this.data.types.get(i), 17.0f + (float)(fontWidth + 10) / 2.0f, 48.0, true, true);
            } else {
                RenderUtils.drawText("\u00a7e" + (String)this.data.types.get(i), 17.0f + (float)(fontWidth + 10) / 2.0f, 48.0, true, true);
            }
            GlStateManager.translate((float)(fontWidth + 12), (float)0.0f, (float)0.0f);
        }
        GlStateManager.popMatrix();
        RenderUtils.drawTexture(253.0, 75.0f + this.scrollDistance * 153.0f, 10.0, 22.0, this.BAR);
        int count = (int)this.data.items.stream().filter(d -> ((ItemData)d).type.equals(this.type)).count();
        float maxScroll = Math.max(0.0f, (float)count / 2.0f * 48.0f - 153.0f);
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox(this.offsetX, this.offsetY + 75.0f, 270.0f, 172.0f);
        int j = 0;
        for (ItemData itemData : this.data.items) {
            if (!itemData.type.equals(this.type)) continue;
            GlStateManager.pushMatrix();
            float y = (float)(75 + 48 * (j / 2)) - this.scrollDistance * maxScroll;
            if (j % 2 != 0) {
                GlStateManager.translate((float)112.0f, (float)0.0f, (float)0.0f);
            }
            RenderUtils.drawTexture(28.0, y, 110.0, 47.0, this.selectItem == itemData ? this.SLOT1 : this.SLOT);
            this.drawItemStack(itemData.itemStack, 37, (int)(y + 7.0f));
            RenderUtils.drawText(itemData.money, 68.0, y + 5.0f, false, true);
            RenderUtils.drawText(itemData.points, 68.0, y + 15.0f, false, true);
            RenderUtils.drawText(itemData.score, 68.0, y + 25.0f, false, true);
            GlStateManager.popMatrix();
            if (Utils.onArea(0.0f, 75.0f, 270.0f, 171.0f, mouseX, mouseY) && Utils.onArea(28 + (j % 2 != 0 ? 112 : 0), y, 110.0f, 40.0f, mouseX, mouseY)) {
                this.hoverItem = itemData;
            }
            ++j;
        }
        GL11.glDisable((int)3089);
        if (this.selectItem != null) {
            RenderUtils.drawText("\u00a76\u8d2d\u4e70\u9700\u6c42:", 265.0, 110.0, false, true);
            RenderUtils.drawText(this.selectItem.money, 268.0, 125.0, false, true);
            RenderUtils.drawText(this.selectItem.points, 268.0, 135.0, false, true);
            RenderUtils.drawText(this.selectItem.score, 268.0, 145.0, false, true);
            RenderUtils.drawText(this.selectItem.amount, 265.0, 160.0, false, true);
            List<String> strings = Utils.splitString(this.selectItem.info, 93);
            for (int i = 0; i < strings.size(); ++i) {
                RenderUtils.drawText(strings.get(i), 265.0, 175 + i * 11, false, true);
            }
            this.drawItemStack(this.selectItem.itemStack, 300, 75);
            if (Utils.onArea(300.0f, 75.0f, 24.0f, 24.0f, mouseX, mouseY)) {
                this.hoverItem = this.selectItem;
            }
            RenderUtils.drawTexture(263.0, 230.0, 100.0, 19.0, mouseX, mouseY, this.BUY, this.BUY1);
        }
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        if (this.hoverItem != null) {
            this.renderToolTip(this.hoverItem.itemStack, (int)((float)mouseX + this.offsetX), (int)((float)mouseY + this.offsetY));
        }
        if (this.scrollPoint != null) {
            int y = (int)((float)mouseY + this.offsetY - (float)this.scrollPoint.y);
            this.scrollDistance = (float)y / 153.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        if (Utils.onArea(253.0f, 75.0f + this.scrollDistance * 153.0f, 11.0f, 22.0f, mouseX, mouseY)) {
            this.scrollPoint = new Point((int)((float)mouseX + this.offsetX), (int)((float)mouseY + this.offsetY - this.scrollDistance * 153.0f));
            return;
        }
        if (this.hoverItem != null) {
            Utils.playSound();
            this.selectItem = this.hoverItem;
        }
        int x = 17;
        for (int i = 0; i < this.data.types.size(); ++i) {
            int fontWidth = RenderUtils.getTextWidth(null, (String)this.data.types.get(i));
            if (Utils.onArea(x, 45.0f, fontWidth + 10, 12.5f, mouseX, mouseY)) {
                this.type = (String)this.data.types.get(i);
                System.out.println("\u67e5\u770b\u5217\u8868 " + (String)this.data.types.get(i));
                Utils.playSound();
                return;
            }
            x += fontWidth + 12;
        }
        if (this.selectItem != null && Utils.onArea(263.0f, 230.0f, 100.0f, 19.0f, mouseX, mouseY)) {
            Utils.playSound();
            Utils.chat("/guild buy " + this.selectItem.key + " 1 1");
            System.out.println("\u8d2d\u4e70\u7269\u54c1 " + this.selectItem.key);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.scrollPoint = null;
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == 1) {
            MessageSender.sendOpenManager();
        }
    }

    private void drawItemStack(ItemStack stack, int x, int y) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)32.0f);
        GlStateManager.scale((float)1.5f, (float)1.5f, (float)1.5f);
        this.zLevel = 200.0f;
        this.itemRender.zLevel = 200.0f;
        FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) {
            font = this.fontRenderer;
        }
        this.itemRender.renderItemAndEffectIntoGUI(stack, 0, 0);
        this.itemRender.renderItemOverlayIntoGUI(font, stack, 0, 0, null);
        this.zLevel = 0.0f;
        this.itemRender.zLevel = 0.0f;
        GlStateManager.popMatrix();
    }

    public static class ItemData {
        private ItemStack itemStack;
        private String key;
        private String type;
        private String money;
        private String points;
        private String score;
        private String amount;
        private String info;

        public ItemData(PacketBuffer buffer) {
            this.key = buffer.readString(32768);
            this.type = buffer.readString(32768);
            try {
                this.itemStack = buffer.readItemStack();
            }
            catch (IOException e) {
                this.itemStack = new ItemStack(Items.APPLE);
            }
            this.money = buffer.readString(32768);
            this.points = buffer.readString(32768);
            this.score = buffer.readString(32768);
            this.amount = buffer.readString(32768);
            this.info = buffer.readString(32768);
        }

        public ItemData() {
        }
    }

    public static class ShopData {
        private List<String> types;
        private String money;
        private String points;
        private String score;
        private List<ItemData> items;

        public ShopData(PacketBuffer buffer) {
            int i;
            this.money = buffer.readString(32768);
            this.points = buffer.readString(32768);
            this.score = buffer.readString(32768);
            int size = buffer.readInt();
            this.types = new ArrayList<String>();
            for (i = 0; i < size; ++i) {
                this.types.add(buffer.readString(32768));
            }
            size = buffer.readInt();
            this.items = new ArrayList<ItemData>();
            for (i = 0; i < size; ++i) {
                ItemData itemData = new ItemData(buffer);
                this.items.add(itemData);
            }
        }

        public ShopData() {
        }
    }
}

