/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.render;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class ComparableBufferedImage
extends BufferedImage {
    private boolean changed = false;

    public ComparableBufferedImage(BufferedImage other) {
        super(other.getWidth(), other.getHeight(), other.getType());
        int width = other.getWidth();
        int height = other.getHeight();
        this.setRGB(0, 0, width, height, ComparableBufferedImage.getPixelData(other), 0, width);
    }

    public ComparableBufferedImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    @Override
    public synchronized void setRGB(int x, int y, int rgb) {
        if (!this.changed && super.getRGB(x, y) != rgb) {
            this.changed = true;
        }
        super.setRGB(x, y, rgb);
    }

    @Override
    public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
        super.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
    }

    public boolean isChanged() {
        return this.changed;
    }

    public void setChanged(boolean val) {
        this.changed = val;
    }

    public boolean identicalTo(BufferedImage other) {
        return ComparableBufferedImage.areIdentical(this.getPixelData(), ComparableBufferedImage.getPixelData(other));
    }

    public static boolean areIdentical(int[] pixels, int[] otherPixels) {
        return IntStream.range(0, pixels.length).map(i -> ~pixels[i] | otherPixels[i]).allMatch(n -> n == -1);
    }

    public int[] getPixelData() {
        return ComparableBufferedImage.getPixelData(this);
    }

    public ComparableBufferedImage copy() {
        return new ComparableBufferedImage(this);
    }

    public void copyTo(BufferedImage other) {
        other.setRGB(0, 0, this.getWidth(), this.getHeight(), this.getPixelData(), 0, this.getWidth());
    }

    public static int[] getPixelData(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] data = new int[width * height];
        image.getRGB(0, 0, width, height, data, 0, width);
        return data;
    }
}

