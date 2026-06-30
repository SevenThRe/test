/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.ui.fullscreen;

import java.io.IOException;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class MapChat
extends GuiChat {
    protected boolean hidden = false;
    protected int cursorCounter;

    public MapChat(String defaultText, boolean hidden) {
        super(defaultText);
        this.hidden = hidden;
    }

    public void func_146281_b() {
        super.func_146281_b();
        this.hidden = true;
    }

    public void close() {
        this.func_146281_b();
    }

    public void func_73876_c() {
        if (this.hidden) {
            return;
        }
        super.func_73876_c();
    }

    public void func_73869_a(char typedChar, int keyCode) throws IOException {
        if (this.hidden) {
            return;
        }
        if (keyCode == 1) {
            this.close();
        } else if (keyCode != 28 && keyCode != 156) {
            super.func_73869_a(typedChar, keyCode);
        } else {
            String s = this.field_146415_a.func_146179_b().trim();
            if (!s.isEmpty()) {
                this.func_175275_f(s);
            }
            this.field_146415_a.func_146180_a("");
            this.field_146297_k.field_71456_v.func_146158_b().func_146240_d();
        }
    }

    public void func_146274_d() throws IOException {
        if (this.hidden) {
            return;
        }
        super.func_146274_d();
    }

    public void func_73864_a(int par1, int par2, int par3) throws IOException {
        if (this.hidden) {
            return;
        }
        super.func_73864_a(par1, par2, par3);
    }

    public void func_73878_a(boolean par1, int par2) {
        if (this.hidden) {
            return;
        }
        super.func_73878_a(par1, par2);
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.func_179094_E();
        GL11.glTranslatef((float)0.0f, (float)((float)this.field_146295_m - 47.5f), (float)0.0f);
        if (this.field_146297_k != null && this.field_146297_k.field_71456_v != null && this.field_146297_k.field_71456_v.func_146158_b() != null) {
            int n;
            GuiNewChat getChatGUI = this.field_146297_k.field_71456_v.func_146158_b();
            if (this.hidden) {
                n = this.field_146297_k.field_71456_v.func_73834_c();
            } else {
                n = this.cursorCounter;
                this.cursorCounter = n + 1;
            }
            getChatGUI.func_146230_a(n);
        }
        GlStateManager.func_179121_F();
        if (this.hidden) {
            return;
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setText(String defaultText) {
        this.field_146415_a.func_146180_a(defaultText);
    }
}

