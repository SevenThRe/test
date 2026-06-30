/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui;

import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.data.DataManager;
import eos.moe.ancientdream.data.PlayerSkillData;
import eos.moe.ancientdream.network.send.MessageSender;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class SkillMenuGui
extends GuiScreen {
    private static ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/skill/bg.png");
    private static ResourceLocation SCROLL_BAR = new ResourceLocation("ancientdream", "gui/skill/bar.png");
    private static ResourceLocation SCROLLBAR_BACKGROUND = new ResourceLocation("ancientdream", "gui/skill/scrollbar_background.png");
    private static ResourceLocation SKILLSLOT = new ResourceLocation("ancientdream", "gui/skill/skillslot.png");
    private static ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/skill/btn.png");
    private static ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/skill/btn1.png");
    private static ResourceLocation BUTT1 = new ResourceLocation("ancientdream", "gui/skill/butt1.png");
    private static ResourceLocation BUTT2 = new ResourceLocation("ancientdream", "gui/skill/butt2.png");
    private static ResourceLocation BUTT3 = new ResourceLocation("ancientdream", "gui/skill/butt3.png");
    private static ResourceLocation BUTT4 = new ResourceLocation("ancientdream", "gui/skill/butt4.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private SkillMenuData selectSkill;
    private SkillMenuData currentDragSkill;
    private List<SkillMenuData> skills;
    private List<SkillMenuData.TextData> texts;
    private float scrollDistance;
    private Point dragScrollBarPotion;
    private float maxDistance = 500.0f;
    private SkillMenuData.ButtonData b1;
    private SkillMenuData.ButtonData b2;

    public SkillMenuGui(int maxDistance, List<SkillMenuData.TextData> texts, List<SkillMenuData> skills, SkillMenuData.ButtonData b1, SkillMenuData.ButtonData b2) {
        this.maxDistance = maxDistance;
        this.skills = skills;
        this.texts = texts;
        this.b1 = b1;
        this.b2 = b2;
    }

    public void initGui() {
        super.initGui();
        this.xSize = 399.0f;
        this.ySize = 318.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ArrayList<PlayerSkillData> bindSkills;
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.bindTexture(BACKGROUND);
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, (double)this.xSize, (double)this.ySize, new ResourceLocation[0]);
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox((int)this.offsetX + 32, (int)this.offsetY + 36, 146.0f, 243.0f);
        for (SkillMenuData skill : this.skills) {
            this.bindSkillTexture(skill);
            RenderUtils.drawTexture((double)(skill.getX() + 32.0f), (double)(skill.getY() + 36.0f - this.scrollDistance * this.maxDistance), 0.0, 0.0, 26.0, 26.0, 26.0, 26.0, new ResourceLocation[0]);
        }
        GL11.glDisable((int)3089);
        String[] array = new String[]{"Q", "R", "F", "G", "V"};
        for (int i = 0; i < 5; ++i) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(228.0f + (float)i * 31.5f), (float)258.0f, (float)0.0f);
            GlStateManager.scale((float)2.0f, (float)2.0f, (float)2.0f);
            RenderUtils.drawText(array[i], 0.0, 0.0, false, true);
            GlStateManager.popMatrix();
        }
        for (SkillMenuData.TextData text : this.texts) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(text.getX() - (text.isCenter() ? (float)this.fontRenderer.getStringWidth(text.getText()) / 2.0f : 0.0f)), (float)text.getY(), (float)0.0f);
            GlStateManager.scale((float)text.getScale(), (float)text.getScale(), (float)text.getScale());
            RenderUtils.drawText("\u00a7f" + text.getText(), 0.0, 0.0, false, true);
            GlStateManager.popMatrix();
        }
        RenderUtils.bindTexture(SCROLL_BAR);
        RenderUtils.drawTexture((double)181.8f, (double)(36.0f + this.scrollDistance * 224.0f), 0.0, 0.0, 7.0, 18.0, 7.0, 18.0, new ResourceLocation[0]);
        if (this.selectSkill != null) {
            for (int i = 0; i < this.selectSkill.getInfo().size(); ++i) {
                RenderUtils.drawText(this.selectSkill.getInfo().get(i), 222.0, 44 + i * 11, false, true);
            }
            if (this.selectSkill.isCanUpgrade() && this.selectSkill.getLevel() < this.selectSkill.getMaxLevel()) {
                RenderUtils.drawTexture((double)this.b1.x, (double)this.b1.y, 0.0, 0.0, 52.0, 16.0, 52.0, 16.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BUTT1, BUTT2);
            }
            if (this.selectSkill.getLevel() > 0) {
                RenderUtils.drawTexture((double)this.b2.x, (double)this.b2.y, 0.0, 0.0, 52.0, 16.0, 52.0, 16.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BUTT3, BUTT4);
            }
        }
        if ((bindSkills = new ArrayList<PlayerSkillData>(DataManager.playerData.bindSkills)).size() == 5) {
            for (int i = 0; i < 5; ++i) {
                PlayerSkillData data = (PlayerSkillData)bindSkills.get(i);
                if (data == null || data.getKey() == null) continue;
                RenderUtils.bindTexture(new ResourceLocation("ancientdream", data.getTexture()));
                RenderUtils.drawTexture((double)(218.0f + (float)i * 31.0f), 254.0, 0.0, 0.0, 26.0, 26.0, 26.0, 26.0, new ResourceLocation[0]);
            }
        }
        if (this.currentDragSkill != null) {
            this.bindSkillTexture(this.currentDragSkill);
            RenderUtils.drawTexture((double)((float)mouseX - this.offsetX - 10.0f), (double)((float)mouseY - this.offsetY - 10.0f), 0.0, 0.0, 26.0, 26.0, 26.0, 26.0, new ResourceLocation[0]);
        }
        GlStateManager.popMatrix();
        if (this.dragScrollBarPotion != null) {
            int y = mouseY - this.dragScrollBarPotion.y;
            this.scrollDistance = (float)y / 224.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
    }

    public void bindSkillTexture(SkillMenuData skill) {
        if (skill.getLevel() == 0) {
            RenderUtils.bindTexture(new ResourceLocation("ancientdream", skill.getTexture1()));
        } else if (skill.isCanUpgrade()) {
            RenderUtils.bindTexture(new ResourceLocation("ancientdream", skill.getTexture2()));
        } else {
            RenderUtils.bindTexture(new ResourceLocation("ancientdream", skill.getTexture3()));
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (new Rectangle((int)this.offsetX + 180, (int)(this.offsetY + 36.0f + this.scrollDistance * 224.0f), 9, 17).contains(mouseX, mouseY)) {
            this.dragScrollBarPotion = new Point(mouseX, (int)((float)mouseY - this.scrollDistance * 224.0f));
        } else {
            ArrayList<PlayerSkillData> bindSkills;
            block0: for (SkillMenuData skill2 : this.skills) {
                if (!new Rectangle((int)(this.offsetX + skill2.getX() + 32.0f), (int)(this.offsetY + skill2.getY() + 36.0f - this.scrollDistance * this.maxDistance), 26, 26).contains(mouseX, mouseY)) continue;
                Utils.playSound();
                this.selectSkill = skill2;
                if (skill2.getLevel() == 0) break;
                for (String s : skill2.getInfo()) {
                    if (!s.contains("\u4e3b\u52a8\u6280\u80fd")) continue;
                    this.currentDragSkill = skill2;
                    break block0;
                }
            }
            if ((bindSkills = new ArrayList<PlayerSkillData>(DataManager.playerData.bindSkills)).size() == 5) {
                for (int i = 0; i < 5; ++i) {
                    if (!new Rectangle((int)this.offsetX + 216 + i * 32, (int)this.offsetY + 253, 32, 32).contains(mouseX, mouseY)) continue;
                    this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0f, 1.0f);
                    int finalI = i;
                    if (mouseButton == 0) {
                        this.selectSkill = this.skills.stream().filter(skill -> skill.getKey().equals(((PlayerSkillData)bindSkills.get(finalI)).getKey())).findFirst().orElse(null);
                        break;
                    }
                    if (mouseButton != 1) break;
                    ((PlayerSkillData)bindSkills.get(finalI)).setKey(null);
                    MessageSender.sendBindSkill(i, "");
                    Utils.playSound();
                    break;
                }
            }
            if (this.selectSkill != null && this.selectSkill.isCanUpgrade() && this.selectSkill.getLevel() < this.selectSkill.getMaxLevel() && new Rectangle((int)(this.offsetX + this.b1.x), (int)(this.offsetY + this.b1.y), 52, 16).contains(mouseX, mouseY)) {
                MessageSender.sendUpgradeSkill(this.selectSkill.getKey());
                Utils.playSound();
            }
            if (this.selectSkill != null && this.selectSkill.getLevel() > 0 && new Rectangle((int)(this.offsetX + this.b2.x), (int)(this.offsetY + this.b2.y), 52, 16).contains(mouseX, mouseY)) {
                MessageSender.sendDowngradeSkill(this.selectSkill.getKey());
                Utils.playSound();
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.dragScrollBarPotion = null;
        if (this.currentDragSkill != null) {
            for (int i = 0; i < 5; ++i) {
                if (!new Rectangle((int)this.offsetX + 216 + i * 32, (int)this.offsetY + 253, 32, 32).contains(mouseX, mouseY)) continue;
                Utils.playSound();
                MessageSender.sendBindSkill(i, this.currentDragSkill.getKey());
                break;
            }
        }
        this.currentDragSkill = null;
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        if (i != 0) {
            if (i > 1) {
                i = 1;
            }
            if (i < -1) {
                i = -1;
            }
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance - (float)i * 0.1f));
        }
    }

    public void setSkills(List<SkillMenuData> skills) {
        this.skills = skills;
        if (this.selectSkill != null) {
            this.selectSkill = skills.stream().filter(s -> s.getKey().equals(this.selectSkill.getKey())).findFirst().orElse(null);
        }
    }

    public void setTexts(List<SkillMenuData.TextData> texts) {
        this.texts = texts;
    }

    public static class SkillMenuData {
        private String key;
        private String name;
        private List<String> info;
        private int level;
        private int maxLevel;
        private boolean canUpgrade;
        private float x;
        private float y;
        private String texture1;
        private String texture2;
        private String texture3;

        public SkillMenuData(ConfigurationSection section) {
            this.key = section.getName();
            this.name = section.getString("name");
            this.info = section.getStringList("info");
            this.level = section.getInt("level");
            this.maxLevel = section.getInt("maxLevel");
            this.canUpgrade = section.getBoolean("canUpgrade");
            this.x = (float)section.getDouble("x");
            this.y = (float)section.getDouble("y");
            if (this.x == 0.0f && this.y == 0.0f) {
                this.x = -999.0f;
            }
            this.texture1 = "gui/skill/" + section.getString("texture1", "default.png");
            this.texture2 = "gui/skill/" + section.getString("texture2", "default.png");
            this.texture3 = "gui/skill/" + section.getString("texture3", "default.png");
        }

        public String getKey() {
            return this.key;
        }

        public String getName() {
            return this.name;
        }

        public List<String> getInfo() {
            return this.info;
        }

        public int getLevel() {
            return this.level;
        }

        public int getMaxLevel() {
            return this.maxLevel;
        }

        public boolean isCanUpgrade() {
            return this.canUpgrade;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public String getTexture1() {
            return this.texture1;
        }

        public String getTexture2() {
            return this.texture2;
        }

        public String getTexture3() {
            return this.texture3;
        }

        public static class ButtonData {
            private float x;
            private float y;

            public ButtonData(double x, double y) {
                this.x = (float)x;
                this.y = (float)y;
            }
        }

        public static class TextData {
            private String text;
            private float x;
            private float y;
            private float scale;
            private boolean center;

            public TextData(ConfigurationSection section) {
                this.text = section.getString("text", "").replace("&", "\u00a7");
                this.x = (float)section.getDouble("x");
                this.y = (float)section.getDouble("y");
                this.scale = (float)section.getDouble("scale", 1.0);
                this.center = section.getBoolean("center", false);
            }

            public String getText() {
                return this.text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public float getX() {
                return this.x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return this.y;
            }

            public void setY(float y) {
                this.y = y;
            }

            public float getScale() {
                return this.scale;
            }

            public void setScale(float scale) {
                this.scale = scale;
            }

            public boolean isCenter() {
                return this.center;
            }

            public void setCenter(boolean center) {
                this.center = center;
            }
        }
    }
}

