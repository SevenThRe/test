/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.model;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;
import journeymap.client.Constants;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.component.Button;
import net.minecraft.client.gui.FontRenderer;

public class SplashPerson {
    public final String name;
    public final String ign;
    public final String title;
    public Button button;
    public int width;
    public double moveX;
    public double moveY;
    private double moveDistance = 1.0;
    private Random r = new Random();

    public SplashPerson(String ign, String name, String titleKey) {
        this.ign = ign;
        this.name = name;
        this.title = titleKey != null ? Constants.getString(titleKey) : "";
    }

    public Button getButton() {
        return this.button;
    }

    public void setButton(Button button) {
        this.button = button;
        this.randomizeVector();
    }

    public TextureImpl getSkin() {
        return TextureCache.getPlayerSkin(null, this.ign);
    }

    public int getWidth(FontRenderer fr) {
        String[] nameParts;
        this.width = fr.getStringWidth(this.title);
        for (String part : nameParts = this.name.trim().split(" ")) {
            this.width = Math.max(this.width, fr.getStringWidth(part));
        }
        return this.width;
    }

    public void setWidth(int minWidth) {
        this.width = minWidth;
    }

    public void randomizeVector() {
        this.moveDistance = this.r.nextDouble() + 0.5;
        this.moveX = this.r.nextBoolean() ? this.moveDistance : -this.moveDistance;
        this.moveDistance = this.r.nextDouble() + 0.5;
        this.moveY = this.r.nextBoolean() ? this.moveDistance : -this.moveDistance;
    }

    public void adjustVector(Rectangle2D.Double screenBounds) {
        Rectangle2D.Double buttonBounds = this.button.getBounds();
        if (!screenBounds.contains(buttonBounds)) {
            int xMargin = this.button.getWidth();
            int yMargin = this.button.getHeight();
            if (buttonBounds.getMinX() <= (double)xMargin) {
                this.moveX = this.moveDistance;
            } else if (buttonBounds.getMaxX() >= screenBounds.getWidth() - (double)xMargin) {
                this.moveX = -this.moveDistance;
            }
            if (buttonBounds.getMinY() <= (double)yMargin) {
                this.moveY = this.moveDistance;
            } else if (buttonBounds.getMaxY() >= screenBounds.getHeight() - (double)yMargin) {
                this.moveY = -this.moveDistance;
            }
        }
        this.continueVector();
    }

    public void continueVector() {
        this.button.setX((int)Math.round((double)this.button.x + this.moveX));
        this.button.setY((int)Math.round((double)this.button.y + this.moveY));
    }

    public void avoid(List<SplashPerson> others) {
        for (SplashPerson other : others) {
            if (this == other || !(this.getDistance(other) <= (double)this.button.getWidth())) continue;
            this.randomizeVector();
            break;
        }
    }

    public double getDistance(SplashPerson other) {
        double px = this.button.getCenterX() - other.button.getCenterX();
        double py = this.button.getMiddleY() - other.button.getMiddleY();
        return Math.sqrt(px * px + py * py);
    }

    public static class Fake
    extends SplashPerson {
        private TextureImpl texture;

        public Fake(String name, String title, TextureImpl texture) {
            super(name, title, null);
            this.texture = texture;
        }

        @Override
        public TextureImpl getSkin() {
            return this.texture;
        }
    }
}

