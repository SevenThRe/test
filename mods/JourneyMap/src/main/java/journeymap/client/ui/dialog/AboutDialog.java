/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.input.Mouse
 */
package journeymap.client.ui.dialog;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import journeymap.client.model.SplashInfo;
import journeymap.client.model.SplashPerson;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.dialog.FullscreenActions;
import journeymap.client.ui.dialog.OptionsManager;
import journeymap.common.Journeymap;
import journeymap.common.properties.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;

public class AboutDialog
extends JmUI {
    protected TextureImpl patreonLogo = TextureCache.getTexture(TextureCache.Patreon);
    protected TextureImpl discordLogo = TextureCache.getTexture(TextureCache.Discord);
    Button buttonClose;
    Button buttonOptions;
    Button buttonPatreon;
    Button buttonDiscord;
    Button buttonWebsite;
    Button buttonDownload;
    ButtonList peopleButtons;
    ButtonList devButtons;
    ButtonList logoButtons;
    ButtonList linkButtons;
    ButtonList bottomButtons;
    ButtonList infoButtons;
    private long lastPeopleMove;
    private List<SplashPerson> people = Arrays.asList(new SplashPerson("AlexDurrani", "Sikandar Durrani", "jm.common.splash_patreon"), new SplashPerson("_TheEndless_", "The Endless", "jm.common.splash_patreon"), new SplashPerson("eladjenkins", "eladjenkins", "jm.common.splash_patreon"));
    private List<SplashPerson> devs = Arrays.asList(new SplashPerson("mysticdrew", "mysticdrew", "jm.common.splash_developer"), new SplashPerson("techbrew", "techbrew", "jm.common.splash_developer"), new SplashPerson("gdude2002", "gdude2002", "jm.common.splash_developer"));
    private SplashInfo info;

    public AboutDialog(JmUI returnDisplay) {
        super(Constants.getString("jm.common.splash_title", Journeymap.JM_VERSION), returnDisplay);
    }

    @Override
    public void initGui() {
        Button button2;
        Journeymap.getClient().getCoreProperties().splashViewed.set(Journeymap.JM_VERSION.toString());
        if (this.info == null) {
            String bday;
            this.info = FileHandler.getMessageModel(SplashInfo.class, "splash");
            if (this.info == null) {
                this.info = new SplashInfo();
            }
            if ((bday = Constants.birthdayMessage()) != null) {
                this.info.lines.add(0, new SplashInfo.Line(bday, "dialog.FullscreenActions#tweet#" + bday));
                this.devs = new ArrayList<SplashPerson>(this.devs);
                this.devs.add(new SplashPerson.Fake("", "", TextureCache.getTexture(TextureCache.ColorPicker2)));
            }
            return;
        }
        this.buttonList.clear();
        FontRenderer fr = this.getFontRenderer();
        this.devButtons = new ButtonList();
        for (SplashPerson dev : this.devs) {
            button2 = new Button(dev.name);
            this.devButtons.add(button2);
            dev.setButton(button2);
        }
        this.devButtons.setWidths(20);
        this.devButtons.setHeights(20);
        this.devButtons.layoutDistributedHorizontal(0, 35, this.width, true);
        this.peopleButtons = new ButtonList();
        for (SplashPerson peep : this.people) {
            button2 = new Button(peep.name);
            this.peopleButtons.add(button2);
            peep.setButton(button2);
        }
        this.peopleButtons.setWidths(20);
        this.peopleButtons.setHeights(20);
        this.peopleButtons.layoutDistributedHorizontal(0, this.height - 65, this.width, true);
        this.infoButtons = new ButtonList();
        for (SplashInfo.Line line : this.info.lines) {
            button2 = new SplashInfoButton(line);
            button2.setDrawBackground(false);
            button2.setDefaultStyle(false);
            button2.setDrawFrame(false);
            button2.setHeight(fr.FONT_HEIGHT + 5);
            if (line.hasAction()) {
                button2.setTooltip(Constants.getString("jm.common.splash_action"));
            }
            this.infoButtons.add(button2);
        }
        this.infoButtons.equalizeWidths(fr);
        this.buttonList.addAll(this.infoButtons);
        this.buttonClose = new Button(Constants.getString("jm.common.close"));
        this.buttonClose.addClickListener(button -> {
            this.closeAndReturn();
            return true;
        });
        this.buttonOptions = new Button(Constants.getString("jm.common.options_button"));
        this.buttonOptions.addClickListener(button -> {
            if (this.returnDisplay != null && this.returnDisplay instanceof OptionsManager) {
                this.closeAndReturn();
            } else {
                UIManager.INSTANCE.openOptionsManager(this, new Category[0]);
            }
            return true;
        });
        this.bottomButtons = new ButtonList(this.buttonOptions);
        if (this.mc.world != null) {
            this.bottomButtons.add(this.buttonClose);
        }
        this.bottomButtons.equalizeWidths(fr);
        this.bottomButtons.setWidths(Math.max(100, this.buttonOptions.getWidth()));
        this.buttonList.addAll(this.bottomButtons);
        this.buttonWebsite = new Button("http://journeymap.info");
        this.buttonWebsite.setTooltip(Constants.getString("jm.common.website"));
        this.buttonWebsite.addClickListener(button -> {
            FullscreenActions.launchWebsite("");
            return true;
        });
        this.buttonDownload = new Button(Constants.getString("jm.common.download"));
        this.buttonDownload.setTooltip(Constants.getString("jm.common.download.tooltip"));
        this.buttonDownload.addClickListener(button -> {
            FullscreenActions.launchDownloadWebsite();
            return true;
        });
        this.linkButtons = new ButtonList(this.buttonWebsite, this.buttonDownload);
        this.linkButtons.equalizeWidths(fr);
        this.buttonList.addAll(this.linkButtons);
        int commonWidth = Math.max(this.bottomButtons.getWidth(0) / this.bottomButtons.size(), this.linkButtons.getWidth(0) / this.linkButtons.size());
        this.bottomButtons.setWidths(commonWidth);
        this.linkButtons.setWidths(commonWidth);
        this.buttonPatreon = new Button("");
        this.buttonPatreon.setDefaultStyle(false);
        this.buttonPatreon.setDrawBackground(false);
        this.buttonPatreon.setDrawFrame(false);
        this.buttonPatreon.setTooltip(Constants.getString("jm.common.patreon"), Constants.getString("jm.common.patreon.tooltip"));
        this.buttonPatreon.setWidth(this.patreonLogo.getWidth() / this.scaleFactor);
        this.buttonPatreon.setHeight(this.patreonLogo.getHeight() / this.scaleFactor);
        this.buttonPatreon.addClickListener(button -> {
            FullscreenActions.launchPatreon();
            return true;
        });
        this.buttonDiscord = new Button("");
        this.buttonDiscord.setDefaultStyle(false);
        this.buttonDiscord.setDrawBackground(false);
        this.buttonDiscord.setDrawFrame(false);
        this.buttonDiscord.setTooltip(Constants.getString("jm.common.discord"), Constants.getString("jm.common.discord.tooltip"));
        this.buttonDiscord.setWidth(this.discordLogo.getWidth() / this.scaleFactor);
        this.buttonDiscord.setHeight(this.discordLogo.getHeight() / this.scaleFactor);
        this.buttonDiscord.addClickListener(button -> {
            FullscreenActions.discord();
            return true;
        });
        this.logoButtons = new ButtonList(this.buttonDiscord, this.buttonPatreon);
        this.logoButtons.setLayout(ButtonList.Layout.Horizontal, ButtonList.Direction.LeftToRight);
        this.logoButtons.setHeights(Math.max(this.discordLogo.getHeight(), this.patreonLogo.getHeight()) / this.scaleFactor);
        this.logoButtons.setWidths(Math.max(this.discordLogo.getWidth(), this.patreonLogo.getWidth()) / this.scaleFactor);
        this.buttonList.addAll(this.logoButtons);
    }

    @Override
    protected void layoutButtons() {
        boolean movePeople;
        if (this.buttonList.isEmpty()) {
            this.initGui();
        }
        int mx = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int my = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        int hgap = 4;
        int vgap = 4;
        FontRenderer fr = this.getFontRenderer();
        int estimatedInfoHeight = this.infoButtons.getHeight(4);
        int estimatedButtonsHeight = (this.buttonClose.getHeight() + 4) * 3 + 4;
        ((Object)((Object)this)).getClass();
        int centerHeight = this.height - 35 - estimatedButtonsHeight;
        int lineHeight = (int)((double)fr.FONT_HEIGHT * 1.4);
        int bx = this.width / 2;
        int by = 0;
        boolean bl = movePeople = System.currentTimeMillis() - this.lastPeopleMove > 20L;
        if (movePeople) {
            this.lastPeopleMove = System.currentTimeMillis();
        }
        Rectangle2D.Double screenBounds = new Rectangle2D.Double(0.0, 0.0, this.width, this.height);
        if (!this.devButtons.isEmpty()) {
            for (SplashPerson dev : this.devs) {
                if (dev.getButton().mouseOver(mx, my)) {
                    dev.randomizeVector();
                }
                this.drawPerson(by, lineHeight, dev);
                if (!movePeople) continue;
                dev.avoid(this.devs);
                dev.adjustVector(screenBounds);
            }
        }
        if (!this.peopleButtons.isEmpty()) {
            for (SplashPerson peep : this.people) {
                if (peep.getButton().mouseOver(mx, my)) {
                    peep.randomizeVector();
                }
                this.drawPerson(by, lineHeight, peep);
                if (!movePeople) continue;
                peep.avoid(this.devs);
                peep.adjustVector(screenBounds);
            }
        }
        if (!this.infoButtons.isEmpty()) {
            ((Object)((Object)this)).getClass();
            int topY = by = 35 + (centerHeight - estimatedInfoHeight) / 2;
            by = (int)((double)by + (double)lineHeight * 1.5);
            this.infoButtons.layoutCenteredVertical(bx - ((Button)this.infoButtons.get(0)).getWidth() / 2, by + this.infoButtons.getHeight(0) / 2, true, 0);
            int listX = this.infoButtons.getLeftX() - 10;
            int listY = topY - 5;
            int listWidth = this.infoButtons.getRightX() + 10 - listX;
            int listHeight = this.infoButtons.getBottomY() + 5 - listY;
            DrawUtil.drawGradientRect(listX - 1, listY - 1, listWidth + 2, listHeight + 2, 0xC0C0C0, 0.8f, 0xC0C0C0, 0.8f);
            DrawUtil.drawGradientRect(listX, listY, listWidth, listHeight, 0x404040, 1.0f, 0, 1.0f);
            DrawUtil.drawLabel(Constants.getString("jm.common.splash_whatisnew"), bx, topY, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.0f, 65535, 1.0f, 1.0, true);
        }
        int rowHeight = this.buttonOptions.height + 4;
        by = this.height - rowHeight - 4;
        this.bottomButtons.layoutCenteredHorizontal(bx, by, true, 4);
        this.linkButtons.layoutCenteredHorizontal(bx, by -= rowHeight, true, 4);
        this.logoButtons.layoutCenteredHorizontal(bx, by -= 4 + this.logoButtons.getHeight(), true, 6);
        DrawUtil.drawImage(this.patreonLogo, this.buttonPatreon.getX(), this.buttonPatreon.getY(), false, 1.0f / (float)this.scaleFactor, 0.0);
        DrawUtil.drawImage(this.discordLogo, this.buttonDiscord.getX(), this.buttonDiscord.getY(), false, 1.0f / (float)this.scaleFactor, 0.0);
    }

    protected int drawPerson(int by, int lineHeight, SplashPerson person) {
        float scale = 1.0f;
        Button button = person.getButton();
        int imgSize = (int)((float)person.getSkin().getWidth() * scale);
        int imgY = button.getY() - 2;
        int imgX = button.getCenterX() - imgSize / 2;
        GlStateManager.enableAlpha();
        if (!(person instanceof SplashPerson.Fake)) {
            DrawUtil.drawGradientRect(imgX - 1, imgY - 1, imgSize + 2, imgSize + 2, 0, 0.4f, 0, 0.8f);
            DrawUtil.drawImage(person.getSkin(), 1.0f, imgX, imgY, false, scale, 0.0);
        } else {
            float size = Math.min((float)person.getSkin().getWidth() * scale, 24.0f * scale);
            DrawUtil.drawQuad(person.getSkin(), 0xFFFFFF, 1.0f, imgX, imgY, size, size, false, 0.0);
        }
        by = imgY + imgSize + 4;
        String name = person.name.trim();
        String name2 = null;
        boolean twoLineName = name.contains(" ");
        if (twoLineName) {
            String[] parts = person.name.split(" ");
            name = parts[0];
            name2 = parts[1];
        }
        DrawUtil.drawLabel(name, button.getCenterX(), by, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.0f, 0xFFFFFF, 1.0f, scale, true);
        by += lineHeight;
        if (name2 != null) {
            DrawUtil.drawLabel(name2, button.getCenterX(), by, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.0f, 0xFFFFFF, 1.0f, scale, true);
            by += lineHeight;
        }
        DrawUtil.drawLabel(person.title, button.getCenterX(), by, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.0f, 65280, 1.0f, scale, true);
        return by += lineHeight;
    }

    protected void actionPerformed(GuiButton guibutton) {
    }

    @Override
    protected void keyTyped(char c, int i) {
        switch (i) {
            case 1: {
                this.closeAndReturn();
            }
        }
    }

    class SplashInfoButton
    extends Button {
        final SplashInfo.Line infoLine;

        public SplashInfoButton(SplashInfo.Line infoLine) {
            super(infoLine.label);
            this.infoLine = infoLine;
        }

        @Override
        public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
            boolean pressed = super.mousePressed(minecraft, mouseX, mouseY, false);
            if (pressed) {
                this.infoLine.invokeAction(AboutDialog.this);
            }
            return this.checkClickListeners();
        }
    }
}

