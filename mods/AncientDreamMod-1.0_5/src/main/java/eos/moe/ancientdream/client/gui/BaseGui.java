/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 */
package eos.moe.ancientdream.client.gui;

import eos.moe.ancientdream.client.gui.component.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public abstract class BaseGui
extends GuiScreen {
    protected float offsetX;
    protected float offsetY;
    protected float xSize;
    protected float ySize;
    protected List<Component> componentList = new ArrayList<Component>();

    public void setSize(int x, int y) {
        this.xSize = x;
        this.ySize = y;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        this.draw(mouseX, mouseY);
        GlStateManager.popMatrix();
    }

    public abstract void draw(int var1, int var2);

    public void drawComponents(float mouseX, float mouseY) {
        for (Component component : this.componentList) {
            component.draw(mouseX, mouseY);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        for (Component component : this.componentList) {
            component.onClick(mouseX, mouseY);
        }
    }

    public void addComponent(Component component) {
        this.componentList.add(component);
    }
}

