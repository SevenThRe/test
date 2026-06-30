/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.ancientdream.client.gui.guild;

import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.gui.component.TButton;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.io.IOException;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuildDonation
extends BaseGui {
    private ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/donation.png");
    private GuiTextField textField;
    private String type = "money";

    public GuildDonation() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.addComponent(new TButton(119.0f, 182.0f, 0.0f, 254.0f, 0.0f, 230.5f, 68.5f, 21.0f, 389.0f, 275.0f, btn -> {
            Utils.chat("/guild donation " + this.type + " " + this.textField.func_146179_b());
            MessageSender.sendOpenManager();
            Utils.playSound();
        }).setTexture(this.BG));
        this.addComponent(new TButton(202.0f, 182.0f, 68.5f, 254.0f, 68.5f, 230.5f, 68.5f, 21.0f, 389.0f, 275.0f, btn -> {
            MessageSender.sendOpenManager();
            Utils.playSound();
        }).setTexture(this.BG));
    }

    public void func_73866_w_() {
        this.setSize(389, 219);
        this.textField = new GuiTextField(0, this.field_146289_q, 37, 142, 323, 20);
        this.textField.func_146195_b(true);
        this.textField.func_146203_f(999);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, (double)this.xSize, 275.0, this.BG);
        if ("money".equals(this.type)) {
            RenderUtils.drawColor(108.0, 67.5, 52.0, 52.0, -2130706433);
        } else {
            RenderUtils.drawColor(226.0, 67.5, 52.0, 52.0, -2130706433);
        }
        this.drawComponents((float)mouseX - this.offsetX, (float)mouseY - this.offsetY);
        this.textField.func_146194_f();
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (Utils.onArea(108.0f, 67.5f, 53.0f, 53.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.type = "money";
            Utils.playSound();
        } else if (Utils.onArea(227.0f, 67.5f, 53.0f, 53.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.type = "point";
            Utils.playSound();
        }
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        super.func_73869_a(typedChar, keyCode);
        this.textField.func_146201_a(typedChar, keyCode);
        if (keyCode == 1) {
            MessageSender.sendOpenManager();
            Keyboard.enableRepeatEvents((boolean)false);
        }
    }
}

