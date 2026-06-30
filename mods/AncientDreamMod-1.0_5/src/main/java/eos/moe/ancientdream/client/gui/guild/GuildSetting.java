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

public class GuildSetting
extends BaseGui {
    private String texture;
    private String message;
    private GuiTextField textField;
    private static final ResourceLocation res = new ResourceLocation("ancientdream", "gui/guild/setting.png");
    private String hoverTexture;

    public GuildSetting(String texture, String message) {
        this.texture = texture;
        this.message = message;
        Keyboard.enableRepeatEvents((boolean)true);
        this.addComponent(new TButton(119.0f, 186.0f, 0.0f, 21.0f, 0.0f, 0.0f, 68.5f, 21.0f, 137.0f, 42.0f, btn -> MessageSender.sendChangeGuildData(this.texture, this.textField.func_146179_b())).setTexture(new ResourceLocation("ancientdream", "gui/guild/settingbtn.png")));
        this.addComponent(new TButton(202.0f, 186.0f, 68.5f, 21.0f, 68.5f, 0.0f, 68.5f, 21.0f, 137.0f, 42.0f, btn -> MessageSender.sendOpenManager()).setTexture(new ResourceLocation("ancientdream", "gui/guild/settingbtn.png")));
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.setSize(390, 223);
        this.textField = new GuiTextField(0, this.field_146289_q, 37, 145, 323, 20);
        this.textField.func_146195_b(true);
        this.textField.func_146203_f(999);
        this.textField.func_146180_a(this.message);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, res);
        this.hoverTexture = null;
        for (int i = 0; i < 6; ++i) {
            double x = 25.0f + 59.5f * (float)i;
            double y = 71.0;
            RenderUtils.drawTexture(x, y, 44.0, 44.0, new ResourceLocation("ancientdream", "gui/guild/head" + i + ".png"));
            if (this.texture.equals("head" + i + ".png")) {
                RenderUtils.drawColor(x, y, 44.0, 44.0, -2130706433);
            }
            if (!Utils.onArea(x, y, 44.0, 44.0, (double)((float)mouseX - this.offsetX), (double)((float)mouseY - this.offsetY))) continue;
            this.hoverTexture = "head" + i + ".png";
            RenderUtils.drawColor(x, y, 44.0, 44.0, -2130706433);
        }
        this.textField.func_146194_f();
        this.drawComponents((float)mouseX - this.offsetX, (float)mouseY - this.offsetY);
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.hoverTexture != null) {
            this.texture = this.hoverTexture;
        }
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        super.func_73869_a(typedChar, keyCode);
        this.textField.func_146201_a(typedChar, keyCode);
        if (keyCode == 1) {
            Keyboard.enableRepeatEvents((boolean)false);
            MessageSender.sendOpenManager();
        }
    }
}

