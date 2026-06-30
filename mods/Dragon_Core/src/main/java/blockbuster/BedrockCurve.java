/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster;

import blockbuster.BedrockCurveType;
import blockbuster.math.MathUtils;
import blockbuster.math.Variable;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.Interpolations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockCurve {
    public BedrockCurveType type = BedrockCurveType.LINEAR;
    public MolangExpression[] nodes = new MolangExpression[]{MolangParser.ZERO, MolangParser.ONE, MolangParser.ZERO};
    public MolangExpression input;
    public MolangExpression range;
    public Variable variable;

    public double compute() {
        return this.computeCurve(this.input.get() / this.range.get());
    }

    private double computeCurve(double factor) {
        int length = this.nodes.length;
        if (length == 0) {
            return 0.0;
        }
        if (length == 1) {
            return this.nodes[0].get();
        }
        if (factor < 0.0) {
            factor = -(1.0 + factor);
        }
        factor = MathUtils.clamp(factor, 0.0, 1.0);
        if (this.type == BedrockCurveType.HERMITE) {
            if (length <= 3) {
                return this.nodes[length - 2].get();
            }
            int index = (int)(factor *= (double)(length - 3)) + 1;
            MolangExpression beforeFirst = this.getNode(index - 1);
            MolangExpression first = this.getNode(index);
            MolangExpression next = this.getNode(index + 1);
            MolangExpression afterNext = this.getNode(index + 2);
            return Interpolations.cubicHermite(beforeFirst.get(), first.get(), next.get(), afterNext.get(), factor % 1.0);
        }
        int index = (int)(factor *= (double)(length - 1));
        MolangExpression first = this.getNode(index);
        MolangExpression next = this.getNode(index + 1);
        return Interpolations.lerp(first.get(), next.get(), factor % 1.0);
    }

    private MolangExpression getNode(int index) {
        if (index < 0) {
            return this.nodes[0];
        }
        if (index >= this.nodes.length) {
            return this.nodes[this.nodes.length - 1];
        }
        return this.nodes[index];
    }

    public void fromJson(JsonObject object, MolangParser parser) throws MolangException {
        if (object.has("type")) {
            this.type = BedrockCurveType.fromString(object.get("type").getAsString());
        }
        if (object.has("input")) {
            this.input = parser.parseJson(object.get("input"));
        }
        if (object.has("horizontal_range")) {
            this.range = parser.parseJson(object.get("horizontal_range"));
        }
        if (object.has("nodes")) {
            JsonArray nodes = object.getAsJsonArray("nodes");
            MolangExpression[] result = new MolangExpression[nodes.size()];
            int c2 = result.length;
            for (int i2 = 0; i2 < c2; ++i2) {
                result[i2] = parser.parseJson(nodes.get(i2));
            }
            this.nodes = result;
        }
    }

    public JsonElement toJson() {
        JsonObject curve = new JsonObject();
        JsonArray nodes = new JsonArray();
        curve.addProperty("type", this.type.id);
        curve.add("nodes", (JsonElement)nodes);
        curve.add("input", this.input.toJson());
        curve.add("horizontal_range", this.range.toJson());
        for (MolangExpression expression : this.nodes) {
            nodes.add(expression.toJson());
        }
        return curve;
    }
}

