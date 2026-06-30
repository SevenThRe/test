/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.gson.annotations.Since
 *  net.minecraftforge.fml.common.FMLLog
 */
package journeymap.client.ui.theme;

import com.google.common.base.Strings;
import com.google.gson.annotations.Since;
import java.awt.Color;
import net.minecraftforge.fml.common.FMLLog;

public class Theme {
    public static final double VERSION = 2.0;
    @Since(value=2.0)
    public int schema;
    @Since(value=1.0)
    public String author;
    @Since(value=1.0)
    public String name;
    @Since(value=1.0)
    public String directory;
    @Since(value=1.0)
    public Container container = new Container();
    @Since(value=1.0)
    public Control control = new Control();
    @Since(value=1.0)
    public Fullscreen fullscreen = new Fullscreen();
    @Since(value=1.0)
    public ImageSpec icon = new ImageSpec();
    @Since(value=1.0)
    public Minimap minimap = new Minimap();

    public static String toHexColor(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static String toHexColor(int rgb) {
        return Theme.toHexColor(new Color(rgb));
    }

    private static int getColor(String hexColor) {
        if (!Strings.isNullOrEmpty((String)hexColor)) {
            try {
                int color = Integer.parseInt(hexColor.replaceFirst("#", ""), 16);
                return color;
            }
            catch (Exception e) {
                FMLLog.warning((String)("Journeymap theme has an invalid color string: " + hexColor), (Object[])new Object[0]);
            }
        }
        return 0xFFFFFF;
    }

    public String toString() {
        if (Strings.isNullOrEmpty((String)this.name)) {
            return "???";
        }
        return this.name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Theme theme = (Theme)o;
        if (this.directory != null ? !this.directory.equals(theme.directory) : theme.directory != null) {
            return false;
        }
        return !(this.name != null ? !this.name.equals(theme.name) : theme.name != null);
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.directory != null ? this.directory.hashCode() : 0);
        return result;
    }

    public static class DefaultPointer {
        @Since(value=1.0)
        public String directory;
        @Since(value=1.0)
        public String filename;
        @Since(value=1.0)
        public String name;

        protected DefaultPointer() {
        }

        public DefaultPointer(Theme theme) {
            this.name = theme.name;
            this.filename = theme.name;
            this.directory = theme.directory;
        }
    }

    public static class LabelSpec
    implements Cloneable {
        @Since(value=2.0)
        public int margin = 2;
        @Since(value=2.0)
        public ColorSpec background = new ColorSpec("#000000", 0.0f);
        @Since(value=2.0)
        public ColorSpec foreground = new ColorSpec();
        @Since(value=2.0)
        public ColorSpec highlight = new ColorSpec();
        @Since(value=1.0)
        public boolean shadow = false;

        public LabelSpec clone() {
            LabelSpec clone = new LabelSpec();
            clone.margin = this.margin;
            clone.background = this.background.clone();
            clone.foreground = this.foreground.clone();
            clone.highlight = this.highlight.clone();
            return clone;
        }
    }

    public static class Minimap {
        @Since(value=1.0)
        public MinimapCircle circle = new MinimapCircle();
        @Since(value=1.0)
        public MinimapSquare square = new MinimapSquare();

        public static class MinimapSquare
        extends MinimapSpec {
            @Since(value=1.0)
            public ImageSpec topLeft = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec top = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec topRight = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec right = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec bottomRight = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec bottom = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec bottomLeft = new ImageSpec();
            @Since(value=1.0)
            public ImageSpec left = new ImageSpec();
        }

        public static class MinimapCircle
        extends MinimapSpec {
            @Since(value=1.0)
            public ImageSpec rim256 = new ImageSpec(256, 256);
            @Since(value=1.0)
            public ImageSpec mask256 = new ImageSpec(256, 256);
            @Since(value=1.0)
            public ImageSpec rim512 = new ImageSpec(512, 512);
            @Since(value=1.0)
            public ImageSpec mask512 = new ImageSpec(512, 512);
            @Since(value=2.0)
            public boolean rotates = false;
        }

        public static abstract class MinimapSpec {
            @Since(value=1.0)
            public int margin;
            @Since(value=2.0)
            public LabelSpec labelTop = new LabelSpec();
            @Since(value=2.0)
            public boolean labelTopInside = false;
            @Since(value=2.0)
            public LabelSpec labelBottom = new LabelSpec();
            @Since(value=2.0)
            public boolean labelBottomInside = false;
            @Since(value=1.0)
            public LabelSpec compassLabel = new LabelSpec();
            @Since(value=1.0)
            public ImageSpec compassPoint = new ImageSpec();
            @Since(value=1.0)
            public int compassPointLabelPad;
            @Since(value=1.0)
            public double compassPointOffset;
            @Since(value=1.0)
            public boolean compassShowNorth = true;
            @Since(value=1.0)
            public boolean compassShowSouth = true;
            @Since(value=1.0)
            public boolean compassShowEast = true;
            @Since(value=1.0)
            public boolean compassShowWest = true;
            @Since(value=1.0)
            public double waypointOffset;
            @Since(value=2.0)
            public ColorSpec reticle = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec reticleHeading = new ColorSpec();
            @Since(value=1.0)
            public double reticleThickness = 2.25;
            @Since(value=1.0)
            public double reticleHeadingThickness = 2.5;
            @Since(value=2.0)
            public int reticleOffsetOuter = 16;
            @Since(value=2.0)
            public int reticleOffsetInner = 16;
            @Since(value=2.0)
            public ColorSpec frame = new ColorSpec();
            @Since(value=1.0)
            public String prefix = "";
        }
    }

    public static class ImageSpec
    extends ColorSpec {
        @Since(value=1.0)
        public int width;
        @Since(value=1.0)
        public int height;

        public ImageSpec() {
        }

        public ImageSpec(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static class ColorSpec
    implements Cloneable {
        @Since(value=2.0)
        public String color = "#ffffff";
        private transient Integer _color;
        @Since(value=2.0)
        public float alpha = 1.0f;

        public ColorSpec() {
        }

        public ColorSpec(String color, float alpha) {
            this.color = color;
            this.alpha = alpha;
        }

        public int getColor() {
            if (this._color == null) {
                this._color = Theme.getColor(this.color);
            }
            return this._color;
        }

        public ColorSpec clone() {
            ColorSpec clone = new ColorSpec();
            clone.color = this.color;
            clone.alpha = this.alpha;
            return clone;
        }
    }

    public static class Fullscreen {
        @Since(value=2.0)
        public ColorSpec background = new ColorSpec();
        @Since(value=1.0)
        public LabelSpec statusLabel = new LabelSpec();
    }

    public static class Control {
        @Since(value=1.0)
        public ButtonSpec button = new ButtonSpec();
        @Since(value=1.0)
        public ButtonSpec toggle = new ButtonSpec();

        public static class ButtonSpec {
            @Since(value=1.0)
            public boolean useThemeImages;
            @Since(value=1.0)
            public int width;
            @Since(value=1.0)
            public int height;
            @Since(value=1.0)
            public String prefix = "";
            @Since(value=1.0)
            public String tooltipOnStyle = "";
            @Since(value=1.0)
            public String tooltipOffStyle = "";
            @Since(value=1.0)
            public String tooltipDisabledStyle = "";
            @Since(value=2.0)
            public ColorSpec iconOn = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec iconOff = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec iconHoverOn = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec iconHoverOff = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec iconDisabled = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec buttonOn = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec buttonOff = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec buttonHoverOn = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec buttonHoverOff = new ColorSpec();
            @Since(value=2.0)
            public ColorSpec buttonDisabled = new ColorSpec();
        }
    }

    public static class Container {
        @Since(value=1.0)
        public Toolbar toolbar = new Toolbar();

        public static class Toolbar {
            @Since(value=1.0)
            public ToolbarSpec horizontal = new ToolbarSpec();
            @Since(value=1.0)
            public ToolbarSpec vertical = new ToolbarSpec();

            public static class ToolbarSpec {
                @Since(value=1.0)
                public boolean useThemeImages;
                @Since(value=1.0)
                public String prefix = "";
                @Since(value=1.0)
                public int margin;
                @Since(value=1.0)
                public int padding;
                @Since(value=1.0)
                public ImageSpec begin;
                @Since(value=1.0)
                public ImageSpec inner;
                @Since(value=1.0)
                public ImageSpec end;
            }
        }
    }
}

