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

    public void onGuiClosed() {
        super.onGuiClosed();
        this.hidden = true;
    }

    public void close() {
        this.onGuiClosed();
    }

    public void updateScreen() {
        if (this.hidden) {
            return;
        }
        super.updateScreen();
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (this.hidden) {
            return;
        }
        if (keyCode == 1) {
            this.close();
        } else if (keyCode != 28 && keyCode != 156) {
            super.keyTyped(typedChar, keyCode);
        } else {
            String s = this.inputField.getText().trim();
            if (!s.isEmpty()) {
                this.sendChatMessage(s);
            }
            this.inputField.setText("");
            this.mc.ingameGUI.getChatGUI().resetScroll();
        }
    }

    public void handleMouseInput() throws IOException {
        if (this.hidden) {
            return;
        }
        super.handleMouseInput();
    }

    public void mouseClicked(int par1, int par2, int par3) throws IOException {
        if (this.hidden) {
            return;
        }
        super.mouseClicked(par1, par2, par3);
    }

    public void confirmClicked(boolean par1, int par2) {
        if (this.hidden) {
            return;
        }
        super.confirmClicked(par1, par2);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GL11.glTranslatef((float)0.0f, (float)((float)this.height - 47.5f), (float)0.0f);
        if (this.mc != null && this.mc.ingameGUI != null && this.mc.ingameGUI.getChatGUI() != null) {
            int n;
            GuiNewChat getChatGUI = this.mc.ingameGUI.getChatGUI();
            if (this.hidden) {
                n = this.mc.ingameGUI.getUpdateCounter();
            } else {
                n = this.cursorCounter;
                this.cursorCounter = n + 1;
            }
            getChatGUI.drawChat(n);
        }
        GlStateManager.popMatrix();
        if (this.hidden) {
            return;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setText(String defaultText) {
        this.inputField.setText(defaultText);
    }
}

