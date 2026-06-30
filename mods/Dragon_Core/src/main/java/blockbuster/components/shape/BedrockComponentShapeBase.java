/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.shape;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.components.shape.ShapeDirection;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class BedrockComponentShapeBase
extends BedrockComponentBase
implements IComponentParticleInitialize {
    public MolangExpression[] offset = new MolangExpression[]{MolangParser.ZERO, MolangParser.ZERO, MolangParser.ZERO};
    public ShapeDirection direction = ShapeDirection.OUTWARDS;
    public boolean surface = false;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        JsonArray array;
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("offset") && (array = element.getAsJsonArray("offset")).size() >= 3) {
            this.offset[0] = parser.parseJson(array.get(0));
            this.offset[1] = parser.parseJson(array.get(1));
            this.offset[2] = parser.parseJson(array.get(2));
        }
        if (element.has("direction")) {
            JsonArray array2;
            JsonElement direction = element.get("direction");
            if (direction.isJsonPrimitive()) {
                String name = direction.getAsString();
                this.direction = name.equals("inwards") ? ShapeDirection.INWARDS : ShapeDirection.OUTWARDS;
            } else if (direction.isJsonArray() && (array2 = direction.getAsJsonArray()).size() >= 3) {
                this.direction = new ShapeDirection.Vector(parser.parseJson(array2.get(0)), parser.parseJson(array2.get(1)), parser.parseJson(array2.get(2)));
            }
        }
        if (element.has("surface_only")) {
            this.surface = element.get("surface_only").getAsBoolean();
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        JsonArray offset = new JsonArray();
        for (MolangExpression expression : this.offset) {
            offset.add(expression.toJson());
        }
        object.add("offset", (JsonElement)offset);
        if (this.direction != ShapeDirection.OUTWARDS) {
            object.add("direction", this.direction.toJson());
        }
        if (this.surface) {
            object.addProperty("surface_only", Boolean.valueOf(true));
        }
        return object;
    }
}

