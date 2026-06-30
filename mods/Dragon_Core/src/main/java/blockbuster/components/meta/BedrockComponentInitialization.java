/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.meta;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentEmitterInitialize;
import blockbuster.components.IComponentEmitterUpdate;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.math.IValue;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangAssignment;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangMultiStatement;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;

public class BedrockComponentInitialization
extends BedrockComponentBase
implements IComponentEmitterInitialize,
IComponentEmitterUpdate {
    public MolangExpression creation = MolangParser.ZERO;
    public MolangExpression update = MolangParser.ZERO;
    public MolangExpression particleUpdate = MolangParser.ZERO;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("creation_expression")) {
            this.creation = parser.parseGlobalJson(element.get("creation_expression"));
        }
        if (element.has("per_update_expression")) {
            this.update = parser.parseGlobalJson(element.get("per_update_expression"));
        }
        if (element.has("particle_update_expression")) {
            this.particleUpdate = parser.parseGlobalJson(element.get("particle_update_expression"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isZero(this.creation)) {
            object.add("creation_expression", this.creation.toJson());
        }
        if (!MolangExpression.isZero(this.update)) {
            object.add("per_update_expression", this.update.toJson());
        }
        if (!MolangExpression.isZero(this.particleUpdate)) {
            object.add("particle_update_expression", this.particleUpdate.toJson());
        }
        return object;
    }

    @Override
    public void apply(BedrockEmitter emitter) {
        emitter.initialValues.clear();
        this.creation.get();
        this.cacheInitialValues(this.creation, emitter);
        if (emitter.variables != null) {
            for (Map.Entry<String, IValue> entry : emitter.variables.entrySet()) {
                emitter.initialValues.put(entry.getKey(), entry.getValue().get().doubleValue());
            }
        }
    }

    @Override
    public void update(BedrockEmitter emitter) {
        this.update.get();
        this.cacheInitialValues(this.update, emitter);
        emitter.replaceVariables();
    }

    private void cacheInitialValues(MolangExpression e2, BedrockEmitter emitter) {
        if (e2 instanceof MolangMultiStatement) {
            MolangMultiStatement statement = (MolangMultiStatement)e2;
            for (MolangExpression expression : statement.expressions) {
                if (!(expression instanceof MolangAssignment)) continue;
                this.cacheInitialValue((MolangAssignment)expression, emitter);
            }
        } else if (e2 instanceof MolangAssignment) {
            this.cacheInitialValue((MolangAssignment)e2, emitter);
        }
    }

    private void cacheInitialValue(MolangAssignment assignment, BedrockEmitter emitter) {
        emitter.initialValues.put(assignment.variable.getName(), assignment.variable.get().doubleValue());
    }
}

