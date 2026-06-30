/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.lifetime;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentEmitterUpdate;
import blockbuster.math.Constant;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class BedrockComponentLifetime
extends BedrockComponentBase
implements IComponentEmitterUpdate {
    public static final MolangExpression DEFAULT_ACTIVE = new MolangValue(null, new Constant(10.0));
    public MolangExpression activeTime = DEFAULT_ACTIVE;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has(this.getPropertyName())) {
            this.activeTime = parser.parseJson(element.get(this.getPropertyName()));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isConstant(this.activeTime, 10.0)) {
            object.add(this.getPropertyName(), this.activeTime.toJson());
        }
        return object;
    }

    protected String getPropertyName() {
        return "active_time";
    }

    @Override
    public int getSortingIndex() {
        return -10;
    }
}

