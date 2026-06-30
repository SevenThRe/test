/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package blockbuster.math.molang;

import blockbuster.math.Constant;
import blockbuster.math.IValue;
import blockbuster.math.MathBuilder;
import blockbuster.math.Variable;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.expressions.MolangAssignment;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangMultiStatement;
import blockbuster.math.molang.expressions.MolangValue;
import blockbuster.math.molang.functions.AcosDegrees;
import blockbuster.math.molang.functions.AsinDegrees;
import blockbuster.math.molang.functions.Atan2Degrees;
import blockbuster.math.molang.functions.AtanDegrees;
import blockbuster.math.molang.functions.CosDegrees;
import blockbuster.math.molang.functions.SinDegrees;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;

public class MolangParser
extends MathBuilder {
    public static final MolangExpression ZERO = new MolangValue(null, new Constant(0.0));
    public static final MolangExpression ONE = new MolangValue(null, new Constant(1.0));
    public static final String RETURN = "return ";
    private MolangMultiStatement currentStatement;
    private boolean registerAsGlobals;

    public MolangParser() {
        this.functions.put("cos", CosDegrees.class);
        this.functions.put("sin", SinDegrees.class);
        this.functions.put("acos", AcosDegrees.class);
        this.functions.put("asin", AsinDegrees.class);
        this.functions.put("atan", AtanDegrees.class);
        this.functions.put("atan2", Atan2Degrees.class);
        this.remap("abs", "math.abs");
        this.remap("ceil", "math.ceil");
        this.remap("clamp", "math.clamp");
        this.remap("cos", "math.cos");
        this.remap("exp", "math.exp");
        this.remap("floor", "math.floor");
        this.remap("lerp", "math.lerp");
        this.remap("lerprotate", "math.lerprotate");
        this.remap("ln", "math.ln");
        this.remap("max", "math.max");
        this.remap("min", "math.min");
        this.remap("mod", "math.mod");
        this.remap("pow", "math.pow");
        this.remap("random", "math.random");
        this.remap("round", "math.round");
        this.remap("sin", "math.sin");
        this.remap("sqrt", "math.sqrt");
        this.remap("trunc", "math.trunc");
        this.remap("acos", "math.acos");
        this.remap("asin", "math.asin");
        this.remap("atan", "math.atan");
        this.remap("atan2", "math.atan2");
        this.remap("randomi", "math.random_integer");
        this.remap("roll", "math.die_roll");
        this.remap("rolli", "math.die_roll_integer");
        this.remap("hermite", "math.hermite_blend");
        this.remapVar("PI", "math.pi");
    }

    public void remap(String old, String newName) {
        this.functions.put(newName, this.functions.remove(old));
    }

    public void remapVar(String old, String newName) {
        this.variables.put(newName, this.variables.remove(old));
    }

    public void setValue(String name, double value) {
        Variable variable = this.getVariable(name);
        if (variable != null) {
            variable.set(value);
        }
    }

    @Override
    protected Variable getVariable(String name) {
        Variable variable;
        Variable variable2 = variable = this.currentStatement == null ? null : this.currentStatement.locals.get(name);
        if (variable == null) {
            variable = super.getVariable(name);
        }
        if (variable == null) {
            variable = new Variable(name, 0.0);
            this.register(variable);
        }
        return variable;
    }

    public MolangExpression parseJson(JsonElement element) throws MolangException {
        if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isString()) {
                try {
                    return new MolangValue(this, new Constant(Double.parseDouble(primitive.getAsString())));
                }
                catch (Exception exception) {
                    return this.parseExpression(primitive.getAsString());
                }
            }
            return new MolangValue(this, new Constant(primitive.getAsDouble()));
        }
        return ZERO;
    }

    public MolangExpression parseGlobalJson(JsonElement element) throws MolangException {
        this.registerAsGlobals = true;
        MolangExpression expression = this.parseJson(element);
        this.registerAsGlobals = false;
        return expression;
    }

    public MolangExpression parseExpression(String expression) throws MolangException {
        MolangMultiStatement result;
        ArrayList<String> lines = new ArrayList<String>();
        for (String split : expression.toLowerCase().trim().split(";")) {
            if (split.trim().isEmpty()) continue;
            lines.add(split);
        }
        if (lines.size() == 0) {
            throw new MolangException("Molang expression cannot be blank!");
        }
        this.currentStatement = result = new MolangMultiStatement(this);
        try {
            for (String line : lines) {
                result.expressions.add(this.parseOneLine(line));
            }
        }
        catch (Exception e2) {
            this.currentStatement = null;
            throw e2;
        }
        this.currentStatement = null;
        return result;
    }

    protected MolangExpression parseOneLine(String expression) throws MolangException {
        if ((expression = expression.trim()).startsWith(RETURN)) {
            try {
                return new MolangValue(this, this.parse(expression.substring(RETURN.length()))).addReturn();
            }
            catch (Exception e2) {
                throw new MolangException("Couldn't parse return '" + expression + "' expression!");
            }
        }
        try {
            List<Object> symbols = this.breakdownChars(this.breakdown(expression));
            if (symbols.size() >= 3 && symbols.get(0) instanceof String && this.isVariable(symbols.get(0)) && symbols.get(1).equals("=")) {
                String name = (String)symbols.get(0);
                symbols = symbols.subList(2, symbols.size());
                Variable variable = null;
                if (!(this.registerAsGlobals || this.variables.containsKey(name) || this.currentStatement.locals.containsKey(name))) {
                    variable = new Variable(name, 0.0);
                    this.currentStatement.locals.put(name, variable);
                } else {
                    variable = this.getVariable(name);
                }
                return new MolangAssignment(this, variable, this.parseSymbolsMolang(symbols));
            }
            return new MolangValue(this, this.parseSymbolsMolang(symbols));
        }
        catch (Exception e3) {
            throw new MolangException("Couldn't parse '" + expression + "' expression!");
        }
    }

    private IValue parseSymbolsMolang(List<Object> symbols) throws MolangException {
        try {
            return this.parseSymbols(symbols);
        }
        catch (Exception e2) {
            e2.printStackTrace();
            throw new MolangException("Couldn't parse an expression!");
        }
    }

    @Override
    protected boolean isOperator(String s2) {
        return super.isOperator(s2) || s2.equals("=");
    }
}

