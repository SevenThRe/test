/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Team
 */
package journeymap.client.render.draw;

import com.google.common.cache.CacheLoader;
import java.awt.geom.Point2D;
import java.lang.ref.WeakReference;
import journeymap.client.data.DataCache;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.minimap.EntityDisplay;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public class DrawEntityStep
implements DrawStep {
    static final Integer labelBg = 0;
    static final int labelBgAlpha = 180;
    static final Integer labelFg = 0xFFFFFF;
    static final int labelFgAlpha = 225;
    boolean useDots;
    int elevationOffset;
    int color;
    boolean hideSneaks;
    boolean showHeading = true;
    boolean showName = true;
    Minecraft minecraft = Minecraft.func_71410_x();
    TextureImpl entityTexture;
    TextureImpl locatorTexture;
    WeakReference<EntityLivingBase> entityLivingRef;
    String customName;
    String playerTeamName;
    Point2D screenPosition;
    float drawScale = 1.0f;

    private DrawEntityStep(EntityLivingBase entityLiving) {
        this.entityLivingRef = new WeakReference<EntityLivingBase>(entityLiving);
        this.hideSneaks = Journeymap.getClient().getCoreProperties().hideSneakingEntities.get();
    }

    public void update(EntityDisplay entityDisplay, TextureImpl locatorTexture, TextureImpl entityTexture, int color, boolean showHeading, boolean showName) {
        EntityLivingBase entityLiving = (EntityLivingBase)this.entityLivingRef.get();
        if (showName && entityLiving != null) {
            this.customName = DataCache.INSTANCE.getEntityDTO((EntityLivingBase)entityLiving).customName;
        }
        this.useDots = entityDisplay.isDots();
        this.color = color;
        this.locatorTexture = locatorTexture;
        this.entityTexture = entityTexture;
        this.drawScale = entityDisplay == EntityDisplay.SmallIcons ? 0.6666667f : 1.0f;
        this.showHeading = showHeading;
        this.showName = showName;
        if (entityLiving instanceof EntityPlayer) {
            Team team = entityLiving.func_96124_cp();
            this.playerTeamName = team != null && showName ? ScorePlayerTeam.func_96667_a((Team)entityLiving.func_96124_cp(), (String)entityLiving.func_70005_c_()) : null;
        }
    }

    @Override
    public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
        if (pass == DrawStep.Pass.Tooltip) {
            return;
        }
        EntityLivingBase entityLiving = (EntityLivingBase)this.entityLivingRef.get();
        if (pass == DrawStep.Pass.Object) {
            if (entityLiving == null || entityLiving.field_70128_L || entityLiving.func_98034_c((EntityPlayer)this.minecraft.field_71439_g) || !entityLiving.field_70175_ag || this.hideSneaks && entityLiving.func_70093_af()) {
                this.screenPosition = null;
                return;
            }
            this.screenPosition = gridRenderer.getPixel(entityLiving.field_70165_t, entityLiving.field_70161_v);
        }
        if (this.screenPosition != null) {
            double heading = entityLiving.field_70759_as;
            double drawX = this.screenPosition.getX() + xOffset;
            double drawY = this.screenPosition.getY() + yOffset;
            float alpha = 1.0f;
            if (entityLiving.field_70163_u > this.minecraft.field_71439_g.field_70163_u) {
                alpha = 1.0f - Math.max(0.1f, (float)((entityLiving.field_70163_u - this.minecraft.field_71439_g.field_70163_u) / 32.0));
            }
            if (entityLiving instanceof EntityPlayer) {
                this.drawPlayer(pass, drawX, drawY, gridRenderer, alpha, heading, fontScale, rotation);
            } else {
                this.drawCreature(pass, drawX, drawY, gridRenderer, alpha, heading, fontScale, rotation);
            }
        }
    }

    private void drawPlayer(DrawStep.Pass pass, double drawX, double drawY, GridRenderer gridRenderer, float alpha, double heading, double fontScale, double rotation) {
        EntityLivingBase entityLiving = (EntityLivingBase)this.entityLivingRef.get();
        if (entityLiving == null) {
            return;
        }
        if (pass == DrawStep.Pass.Object) {
            if (this.locatorTexture != null) {
                DrawUtil.drawColoredEntity(drawX, drawY, this.locatorTexture, this.color, alpha, this.drawScale, this.showHeading ? heading : -rotation);
            }
            if (this.entityTexture != null) {
                if (this.useDots) {
                    boolean flip = false;
                    this.elevationOffset = (int)(DataCache.getPlayer().posY - entityLiving.field_70163_u);
                    if (this.elevationOffset < -1 || this.elevationOffset > 1) {
                        flip = this.elevationOffset < -1;
                        DrawUtil.drawColoredEntity(drawX, drawY, this.entityTexture, this.color, alpha, this.drawScale, flip ? -rotation + 180.0 : -rotation);
                    }
                } else {
                    DrawUtil.drawColoredEntity(drawX, drawY, this.entityTexture, this.color, alpha, this.drawScale, -rotation);
                }
            }
        }
        if (pass == DrawStep.Pass.Text) {
            int labelOffset = this.entityTexture == null ? 0 : (rotation == 0.0 ? -this.entityTexture.getHeight() / 2 : this.entityTexture.getHeight() / 2);
            Point2D labelPoint = gridRenderer.shiftWindowPosition((int)drawX, (int)drawY, 0, -labelOffset);
            if (this.playerTeamName != null && this.showName) {
                DrawUtil.drawLabel(this.playerTeamName, labelPoint.getX(), labelPoint.getY(), DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.8f, 0xFFFFFF, 1.0f, fontScale, false, rotation);
            } else if (this.showName) {
                DrawUtil.drawLabel(entityLiving.func_70005_c_(), labelPoint.getX(), labelPoint.getY(), DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.8f, 65280, 1.0f, fontScale, false, rotation);
            }
        }
    }

    private void drawCreature(DrawStep.Pass pass, double drawX, double drawY, GridRenderer gridRenderer, float alpha, double heading, double fontScale, double rotation) {
        int labelOffset;
        EntityLivingBase entityLiving = (EntityLivingBase)this.entityLivingRef.get();
        if (entityLiving == null) {
            return;
        }
        if (pass == DrawStep.Pass.Object && this.locatorTexture != null) {
            DrawUtil.drawColoredEntity(drawX, drawY, this.locatorTexture, this.color, alpha, this.drawScale, this.showHeading ? heading : -rotation);
        }
        int n = this.entityTexture == null ? 8 : (labelOffset = rotation == 0.0 ? this.entityTexture.getHeight() : -this.entityTexture.getHeight());
        if (pass == DrawStep.Pass.Text && this.showName && this.customName != null) {
            Point2D labelPoint = gridRenderer.shiftWindowPosition(drawX, drawY, 0, labelOffset);
            DrawUtil.drawCenteredLabel(this.customName, labelPoint.getX(), labelPoint.getY(), labelBg, 180.0f, (Integer)0xFFFFFF, 225.0f, fontScale, rotation);
        }
        if (pass == DrawStep.Pass.Object && this.entityTexture != null) {
            if (this.useDots) {
                boolean flip = false;
                this.elevationOffset = (int)(DataCache.getPlayer().posY - entityLiving.field_70163_u);
                if (this.elevationOffset < -1 || this.elevationOffset > 1) {
                    flip = this.elevationOffset < -1;
                    DrawUtil.drawColoredEntity(drawX, drawY, this.entityTexture, this.color, alpha, this.drawScale, flip ? -rotation + 180.0 : -rotation);
                }
            } else {
                DrawUtil.drawEntity(drawX, drawY, -rotation, this.entityTexture, alpha, this.drawScale, 0.0);
            }
        }
    }

    @Override
    public int getDisplayOrder() {
        return this.customName != null ? 1 : 0;
    }

    @Override
    public String getModId() {
        return "journeymap";
    }

    public static class SimpleCacheLoader
    extends CacheLoader<EntityLivingBase, DrawEntityStep> {
        public DrawEntityStep load(EntityLivingBase entityLiving) throws Exception {
            return new DrawEntityStep(entityLiving);
        }
    }
}

