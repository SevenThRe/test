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
        this.func_148122_a(width, height, top, bottom);
    }

    public void func_148122_a(int width, int height, int top, int bottom) {
        super.func_148122_a(width, height, top, bottom);
        this.scrollbarX = this.field_148155_a - this.hpad;
        this.listWidth = 150;
        this.field_148152_e = this.field_148155_a - 170;
    }

    protected int func_148127_b() {
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
            this.func_148145_f(-(sizeAfter * this.field_148149_f));
            this.func_148145_f(this.lastClickedIndex * this.field_148149_f);
        }
    }

    public void scrollTo(ISlot slot) {
        this.func_148145_f(-(this.currentSlots.size() * this.field_148149_f));
        this.func_148145_f(this.currentSlots.indexOf(slot) * this.field_148149_f);
    }

    public void func_178039_p() {
        super.func_178039_p();
    }

    protected void func_148144_a(int index, boolean doubleClick, int mouseX, int mouseY) {
    }

    public boolean func_148131_a(int slotIndex) {
        return false;
    }

    protected void func_148123_a() {
    }

    protected void func_192637_a(int slotIndex, int x, int y, int slotHeight, int mouseX, int mouseY, float partialTicks) {
        boolean selected = this.func_148124_c(mouseX, mouseY) == slotIndex;
        ISlot slot = this.getSlot(slotIndex);
        int j1 = this.func_148135_f();
        slot.func_192634_a(slotIndex, x, y, this.func_148139_c() + (j1 > 0 ? 0 : 15), slotHeight, mouseX, mouseY, selected, 0.0f);
        SlotMetadata tooltipMetadata = slot.getCurrentTooltip();
        if (tooltipMetadata != null && !Arrays.equals(tooltipMetadata.getTooltip(), this.lastTooltip)) {
            this.lastTooltipMetadata = tooltipMetadata;
            this.lastTooltip = tooltipMetadata.getTooltip();
            this.lastTooltipTime = System.currentTimeMillis();
        }
    }

    public int func_148139_c() {
        return this.listWidth;
    }

    public boolean func_148179_a(int mouseX, int mouseY, int mouseEvent) {
        int slotIndex;
        if (this.func_148141_e(mouseY) && (slotIndex = this.func_148124_c(mouseX, mouseY)) >= 0) {
            int i1 = this.field_148152_e;
            int j1 = this.field_148153_b + 4 - this.func_148148_g() + slotIndex * this.field_148149_f + this.field_148160_j;
            int relativeX = mouseX - i1;
            int relativeY = mouseY - j1;
            this.lastClickedIndex = -1;
            if (this.getSlot(slotIndex).func_148278_a(slotIndex, mouseX, mouseY, mouseEvent, relativeX, relativeY)) {
                this.func_148143_b(false);
                this.lastClickedIndex = slotIndex;
                this.lastPressed = this.getSlot(slotIndex).getLastPressed();
                this.updateSlots();
                return true;
            }
        }
        return false;
    }

    public int func_148124_c(int posX, int posY) {
        int i = this.field_148152_e;
        int j = this.field_148152_e + this.func_148139_c();
        int k = posY - this.field_148153_b - this.field_148160_j + (int)this.field_148169_q - 4;
        int l = k / this.field_148149_f;
        return posX < this.func_148137_d() && posX >= i && posX <= j && l >= 0 && k >= 0 && l < this.func_148127_b() ? l : -1;
    }

    public boolean func_148181_b(int x, int y, int mouseEvent) {
        boolean result = super.func_148181_b(x, y, mouseEvent);
        this.lastPressed = null;
        return result;
    }

    public GuiListExtended.IGuiListEntry func_148180_b(int index) {
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
        for (int slotIndex = 0; slotIndex < this.func_148127_b(); ++slotIndex) {
            if (!this.getSlot(slotIndex).keyTyped(c, i)) continue;
            this.lastClickedIndex = slotIndex;
            this.lastPressed = this.getSlot(slotIndex).getLastPressed();
            this.updateSlots();
            return true;
        }
        return false;
    }

    protected int func_148137_d() {
        return this.scrollbarX;
    }

    protected void drawContainerBackground(Tessellator tessellator) {
        this.parent.func_73733_a(0, this.field_148153_b, this.field_148155_a, this.field_148153_b + this.field_148158_l, -1072689136, -804253680);
    }

    protected int func_148138_e() {
        int contentHeight = super.func_148138_e();
        if (this.alignTop) {
            contentHeight = Math.max(this.field_148154_c - this.field_148153_b - 4, contentHeight);
        }
        return contentHeight;
    }

    public void setAlignTop(boolean alignTop) {
        this.alignTop = alignTop;
    }

    public void func_148128_a(int mouseXIn, int mouseYIn, float partialTicks) {
        if (this.field_178041_q) {
            this.field_148150_g = mouseXIn;
            this.field_148162_h = mouseYIn;
            this.func_148123_a();
            int i = this.func_148137_d();
            int j = i + 6;
            this.func_148121_k();
            GlStateManager.func_179140_f();
            GlStateManager.func_179106_n();
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            int k = this.field_148152_e;
            int l = this.field_148153_b + 4 - (int)this.field_148169_q;
            if (this.field_148165_u) {
                this.func_148129_a(k, l, tessellator);
            }
            this.func_192638_a(k, l, mouseXIn, mouseYIn, partialTicks);
            GlStateManager.func_179097_i();
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ZERO, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
            GlStateManager.func_179118_c();
            GlStateManager.func_179103_j((int)7425);
            GlStateManager.func_179090_x();
            int i1 = 4;
            int j1 = this.func_148135_f();
            if (j1 > 0) {
                int k1 = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
                int l1 = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - (k1 = MathHelper.func_76125_a((int)k1, (int)32, (int)(this.field_148154_c - this.field_148153_b - 8)))) / j1 + this.field_148153_b;
                if (l1 < this.field_148153_b) {
                    l1 = this.field_148153_b;
                }
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                bufferbuilder.func_181662_b((double)i, (double)this.field_148154_c, 0.0).func_187315_a(0.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j, (double)this.field_148154_c, 0.0).func_187315_a(1.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j, (double)this.field_148153_b, 0.0).func_187315_a(1.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)i, (double)this.field_148153_b, 0.0).func_187315_a(0.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                tessellator.func_78381_a();
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                bufferbuilder.func_181662_b((double)i, (double)(l1 + k1), 0.0).func_187315_a(0.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j, (double)(l1 + k1), 0.0).func_187315_a(1.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j, (double)l1, 0.0).func_187315_a(1.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)i, (double)l1, 0.0).func_187315_a(0.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                tessellator.func_78381_a();
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                bufferbuilder.func_181662_b((double)i, (double)(l1 + k1 - 1), 0.0).func_187315_a(0.0, 1.0).func_181669_b(192, 192, 192, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(j - 1), (double)(l1 + k1 - 1), 0.0).func_187315_a(1.0, 1.0).func_181669_b(192, 192, 192, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(j - 1), (double)l1, 0.0).func_187315_a(1.0, 0.0).func_181669_b(192, 192, 192, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)i, (double)l1, 0.0).func_187315_a(0.0, 0.0).func_181669_b(192, 192, 192, 255).func_181675_d();
                tessellator.func_78381_a();
            }
            this.func_148142_b(mouseXIn, mouseYIn);
            GlStateManager.func_179098_w();
            GlStateManager.func_179103_j((int)7424);
            GlStateManager.func_179141_d();
            GlStateManager.func_179084_k();
        }
    }

    protected void func_192638_a(int insideLeft, int insideTop, int mouseXIn, int mouseYIn, float partialTicks) {
        int i = this.func_148127_b();
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        int h = this.field_148154_c - 38;
        GL11.glEnable((int)3089);
        int yend = 38 + h;
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
        int factor = sr.func_78325_e();
        int bottomY = sr.func_78328_b() - yend;
        GL11.glScissor((int)0, (int)(bottomY * factor), (int)9999, (int)(h * factor));
        for (int j = 0; j < i; ++j) {
            int k = insideTop + j * this.field_148149_f + this.field_148160_j;
            int l = this.field_148149_f - 4;
            if (k > this.field_148154_c || k + l < this.field_148153_b) {
                this.func_192639_a(j, insideLeft, k, partialTicks);
            }
            if (this.field_148166_t && this.func_148131_a(j)) {
                int i1 = this.field_148152_e + (this.field_148155_a / 2 - this.func_148139_c() / 2);
                int j1 = this.field_148152_e + this.field_148155_a / 2 + this.func_148139_c() / 2;
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179090_x();
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                bufferbuilder.func_181662_b((double)i1, (double)(k + l + 2), 0.0).func_187315_a(0.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j1, (double)(k + l + 2), 0.0).func_187315_a(1.0, 1.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)j1, (double)(k - 2), 0.0).func_187315_a(1.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)i1, (double)(k - 2), 0.0).func_187315_a(0.0, 0.0).func_181669_b(128, 128, 128, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(i1 + 1), (double)(k + l + 1), 0.0).func_187315_a(0.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(j1 - 1), (double)(k + l + 1), 0.0).func_187315_a(1.0, 1.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(j1 - 1), (double)(k - 1), 0.0).func_187315_a(1.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                bufferbuilder.func_181662_b((double)(i1 + 1), (double)(k - 1), 0.0).func_187315_a(0.0, 0.0).func_181669_b(0, 0, 0, 255).func_181675_d();
                tessellator.func_78381_a();
                GlStateManager.func_179098_w();
            }
            this.func_192637_a(j, insideLeft, k, l, mouseXIn, mouseYIn, partialTicks);
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

