/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  org.apache.commons.lang3.StringUtils
 */
package blockbuster.components.appearance;

import blockbuster.BedrockSchemeJsonAdapter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.Constant;
import blockbuster.math.MathUtils;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangValue;
import blockbuster.utils.Interpolations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class Tint {
    public static Solid parseColor(JsonElement element, MolangParser parser) throws MolangException {
        JsonArray array;
        MolangExpression r2 = MolangParser.ONE;
        MolangExpression g2 = MolangParser.ONE;
        MolangExpression b2 = MolangParser.ONE;
        MolangExpression a2 = MolangParser.ONE;
        if (element.isJsonPrimitive()) {
            String hex = element.getAsString();
            if (hex.startsWith("#") && (hex.length() == 7 || hex.length() == 9)) {
                boolean hasAlpha = hex.length() == 9;
                try {
                    int color = Integer.parseInt(hex.substring(hasAlpha ? 3 : 1), 16);
                    float hr2 = (float)(color >> 16 & 0xFF) / 255.0f;
                    float hg2 = (float)(color >> 8 & 0xFF) / 255.0f;
                    float hb2 = (float)(color & 0xFF) / 255.0f;
                    float ha2 = hasAlpha ? (float)Integer.parseInt(hex.substring(1, 3), 16) / 255.0f : 1.0f;
                    r2 = new MolangValue(parser, new Constant(hr2));
                    g2 = new MolangValue(parser, new Constant(hg2));
                    b2 = new MolangValue(parser, new Constant(hb2));
                    a2 = new MolangValue(parser, new Constant(ha2));
                }
                catch (Exception exception) {}
            }
        } else if (element.isJsonArray() && ((array = element.getAsJsonArray()).size() == 3 || array.size() == 4)) {
            r2 = parser.parseJson(array.get(0));
            g2 = parser.parseJson(array.get(1));
            b2 = parser.parseJson(array.get(2));
            if (array.size() == 4) {
                a2 = parser.parseJson(array.get(3));
            }
        }
        return new Solid(r2, g2, b2, a2);
    }

    public static Tint parseGradient(JsonObject color, MolangParser parser) throws MolangException {
        JsonElement gradient = color.get("gradient");
        MolangExpression expression = MolangParser.ZERO;
        ArrayList<Gradient.ColorStop> colorStops = new ArrayList<Gradient.ColorStop>();
        boolean equal = true;
        if (gradient.isJsonObject()) {
            for (Map.Entry entry : gradient.getAsJsonObject().entrySet()) {
                Solid stopColor = Tint.parseColor((JsonElement)entry.getValue(), parser);
                colorStops.add(new Gradient.ColorStop(Float.parseFloat((String)entry.getKey()), stopColor));
            }
            colorStops.sort((a2, b2) -> Float.compare(a2.stop, b2.stop));
            equal = false;
        } else if (gradient.isJsonArray()) {
            JsonArray colors = gradient.getAsJsonArray();
            int i2 = 0;
            for (JsonElement jsonElement : colors) {
                colorStops.add(new Gradient.ColorStop((float)i2 / (float)(colors.size() - 1), Tint.parseColor(jsonElement, parser)));
                ++i2;
            }
        }
        float range = ((Gradient.ColorStop)colorStops.get((int)(colorStops.size() - 1))).stop;
        DecimalFormat floatPrecision = new DecimalFormat("#.######");
        floatPrecision.setRoundingMode(RoundingMode.HALF_EVEN);
        for (Gradient.ColorStop colorStop : colorStops) {
            colorStop.stop /= range;
            colorStop.stop = Float.valueOf(floatPrecision.format(colorStop.stop)).floatValue();
        }
        if (color.has("interpolant")) {
            expression = parser.parseJson(color.get("interpolant"));
        }
        return new Gradient(colorStops, range, expression, equal);
    }

    public abstract void compute(BedrockParticle var1);

    public abstract JsonElement toJson();

    public static class Gradient
    extends Tint {
        public List<ColorStop> stops;
        public MolangExpression interpolant;
        public float range = 1.0f;
        public boolean equal;

        public Gradient(List<ColorStop> stops, float range, MolangExpression interpolant, boolean equal) {
            this.stops = stops;
            this.range = range;
            this.interpolant = interpolant;
            this.equal = equal;
        }

        public Gradient() {
            this.stops = new ArrayList<ColorStop>();
            this.stops.add(new ColorStop(0.0f, new Solid(new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)), new MolangValue(null, new Constant(1.0)))));
            this.stops.add(new ColorStop(1.0f, new Solid(new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(0.0)), new MolangValue(null, new Constant(1.0)))));
            this.interpolant = MolangParser.ZERO;
            this.equal = false;
        }

        public void sort() {
            this.stops.sort((a2, b2) -> Float.compare(a2.stop, b2.stop));
        }

        @Override
        public void compute(BedrockParticle particle) {
            ColorStop prev;
            int length = this.stops.size();
            if (length == 0) {
                particle.a = 1.0f;
                particle.b = 1.0f;
                particle.g = 1.0f;
                particle.r = 1.0f;
                return;
            }
            if (length == 1) {
                this.stops.get((int)0).color.compute(particle);
                return;
            }
            double factor = this.interpolant.get();
            if ((factor = MathUtils.clamp(factor, 0.0, 1.0)) < (double)(prev = this.stops.get(0)).getStop(this.range)) {
                prev.color.compute(particle);
                return;
            }
            for (int i2 = 1; i2 < length; ++i2) {
                ColorStop stop = this.stops.get(i2);
                if ((double)stop.getStop(this.range) > factor) {
                    prev.color.compute(particle);
                    stop.color.lerp(particle, (float)(factor - (double)prev.getStop(this.range)) / (stop.getStop(this.range) - prev.getStop(this.range)));
                    return;
                }
                prev = stop;
            }
            prev.color.compute(particle);
        }

        @Override
        public JsonElement toJson() {
            JsonArray color;
            JsonObject object = new JsonObject();
            if (this.equal) {
                JsonArray gradient = new JsonArray();
                for (ColorStop stop : this.stops) {
                    gradient.add(stop.color.toHexJson());
                }
                color = gradient;
            } else {
                JsonObject gradient = new JsonObject();
                for (ColorStop stop : this.stops) {
                    gradient.add(String.valueOf(stop.getStop(this.range)), stop.color.toHexJson());
                }
                color = gradient;
            }
            if (!BedrockSchemeJsonAdapter.isEmpty((JsonElement)color)) {
                object.add("gradient", (JsonElement)color);
            }
            if (!MolangExpression.isZero(this.interpolant)) {
                object.add("interpolant", this.interpolant.toJson());
            }
            return object;
        }

        public static class ColorStop {
            public float stop;
            public Solid color;

            public ColorStop(float stop, Solid color) {
                this.stop = stop;
                this.color = color;
            }

            public float getStop(float range) {
                return this.stop * range;
            }
        }
    }

    public static class Solid
    extends Tint {
        public MolangExpression r;
        public MolangExpression g;
        public MolangExpression b;
        public MolangExpression a;

        public Solid(MolangExpression r2, MolangExpression g2, MolangExpression b2, MolangExpression a2) {
            this.r = r2;
            this.g = g2;
            this.b = b2;
            this.a = a2;
        }

        public Solid() {
            this.r = MolangParser.ONE;
            this.g = MolangParser.ONE;
            this.b = MolangParser.ONE;
            this.a = MolangParser.ONE;
        }

        public boolean isConstant() {
            return MolangExpression.isExpressionConstant(this.r) && MolangExpression.isExpressionConstant(this.g) && MolangExpression.isExpressionConstant(this.b) && MolangExpression.isExpressionConstant(this.a);
        }

        @Override
        public void compute(BedrockParticle particle) {
            particle.r = (float)this.r.get();
            particle.g = (float)this.g.get();
            particle.b = (float)this.b.get();
            particle.a = (float)this.a.get();
        }

        @Override
        public JsonElement toJson() {
            JsonArray array = new JsonArray();
            if (MolangExpression.isOne(this.r) && MolangExpression.isOne(this.g) && MolangExpression.isOne(this.b) && MolangExpression.isOne(this.a)) {
                return array;
            }
            array.add(this.r.toJson());
            array.add(this.g.toJson());
            array.add(this.b.toJson());
            array.add(this.a.toJson());
            return array;
        }

        public JsonElement toHexJson() {
            int r2 = (int)(this.r.get() * 255.0) & 0xFF;
            int g2 = (int)(this.g.get() * 255.0) & 0xFF;
            int b2 = (int)(this.b.get() * 255.0) & 0xFF;
            int a2 = (int)(this.a.get() * 255.0) & 0xFF;
            String hex = "#";
            if (a2 < 255) {
                hex = hex + StringUtils.leftPad((String)Integer.toHexString(a2), (int)2, (String)"0").toUpperCase();
            }
            hex = hex + StringUtils.leftPad((String)Integer.toHexString(r2), (int)2, (String)"0").toUpperCase();
            hex = hex + StringUtils.leftPad((String)Integer.toHexString(g2), (int)2, (String)"0").toUpperCase();
            hex = hex + StringUtils.leftPad((String)Integer.toHexString(b2), (int)2, (String)"0").toUpperCase();
            return new JsonPrimitive(hex);
        }

        public void lerp(BedrockParticle particle, float factor) {
            particle.r = Interpolations.lerp(particle.r, (float)this.r.get(), factor);
            particle.g = Interpolations.lerp(particle.g, (float)this.g.get(), factor);
            particle.b = Interpolations.lerp(particle.b, (float)this.b.get(), factor);
            particle.a = Interpolations.lerp(particle.a, (float)this.a.get(), factor);
        }
    }
}

