/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package goblinbob.mobends.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class UIScissorHelper {
    public static final UIScissorHelper INSTANCE = new UIScissorHelper();
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    public void setUIBounds(int uiX, int uiY, int uiWidth, int uiHeight) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.func_71410_x());
        int scaledWidth = scaledResolution.func_78326_a();
        int scaledHeight = scaledResolution.func_78328_b();
        this.x = uiX * Minecraft.func_71410_x().field_71443_c / scaledWidth;
        this.y = (scaledHeight - uiY - uiHeight) * Minecraft.func_71410_x().field_71440_d / scaledHeight;
        this.width = uiWidth * Minecraft.func_71410_x().field_71443_c / scaledWidth;
        this.height = uiHeight * Minecraft.func_71410_x().field_71440_d / scaledHeight;
    }

    public void enable() {
        GL11.glEnable((int)3089);
        GL11.glScissor((int)this.x, (int)this.y, (int)this.width, (int)this.height);
    }

    public void disable() {
        GL11.glDisable((int)3089);
    }
}

