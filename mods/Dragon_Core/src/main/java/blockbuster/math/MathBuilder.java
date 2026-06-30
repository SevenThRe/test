/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.Constant;
import blockbuster.math.Group;
import blockbuster.math.IValue;
import blockbuster.math.Negate;
import blockbuster.math.Negative;
import blockbuster.math.Operation;
import blockbuster.math.Operator;
import blockbuster.math.Ternary;
import blockbuster.math.Variable;
import blockbuster.math.functions.Function;
import blockbuster.math.functions.classic.Abs;
import blockbuster.math.functions.classic.Exp;
import blockbuster.math.functions.classic.Ln;
import blockbuster.math.functions.classic.Mod;
import blockbuster.math.functions.classic.Pow;
import blockbuster.math.functions.classic.Sqrt;
import blockbuster.math.functions.limit.Clamp;
import blockbuster.math.functions.limit.Max;
import blockbuster.math.functions.limit.Min;
import blockbuster.math.functions.rounding.Ceil;
import blockbuster.math.functions.rounding.Floor;
import blockbuster.math.functions.rounding.Round;
import blockbuster.math.functions.rounding.Trunc;
import blockbuster.math.functions.string.StringContains;
import blockbuster.math.functions.string.StringEndsWith;
import blockbuster.math.functions.string.StringStartsWith;
import blockbuster.math.functions.trig.Acos;
import blockbuster.math.functions.trig.Asin;
import blockbuster.math.functions.trig.Atan;
import blockbuster.math.functions.trig.Atan2;
import blockbuster.math.functions.trig.Cos;
import blockbuster.math.functions.trig.Sin;
import blockbuster.math.functions.utility.DieRoll;
import blockbuster.math.functions.utility.DieRollInteger;
import blockbuster.math.functions.utility.HermiteBlend;
import blockbuster.math.functions.utility.Lerp;
import blockbuster.math.functions.utility.LerpRotate;
import blockbuster.math.functions.utility.Random;
import blockbuster.math.functions.utility.RandomInteger;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathBuilder {
    public Map<String, Variable> variables = new HashMap<String, Variable>();
    public Map<String, Class<? extends Function>> functions = new HashMap<String, Class<? extends Function>>();
    protected boolean strict = true;

    public MathBuilder() {
        this.register(new Variable("PI", Math.PI));
        this.register(new Variable("E", Math.E));
        this.functions.put("floor", Floor.class);
        this.functions.put("round", Round.class);
        this.functions.put("ceil", Ceil.class);
        this.functions.put("trunc", Trunc.class);
        this.functions.put("clamp", Clamp.class);
        this.functions.put("max", Max.class);
        this.functions.put("min", Min.class);
        this.functions.put("abs", Abs.class);
        this.functions.put("exp", Exp.class);
        this.functions.put("ln", Ln.class);
        this.functions.put("sqrt", Sqrt.class);
        this.functions.put("mod", Mod.class);
        this.functions.put("pow", Pow.class);
        this.functions.put("cos", Cos.class);
        this.functions.put("sin", Sin.class);
        this.functions.put("acos", Acos.class);
        this.functions.put("asin", Asin.class);
        this.functions.put("atan", Atan.class);
        this.functions.put("atan2", Atan2.class);
        this.functions.put("lerp", Lerp.class);
        this.functions.put("lerprotate", LerpRotate.class);
        this.functions.put("random", Random.class);
        this.functions.put("randomi", RandomInteger.class);
        this.functions.put("roll", DieRoll.class);
        this.functions.put("rolli", DieRollInteger.class);
        this.functions.put("hermite", HermiteBlend.class);
        this.functions.put("str_contains", StringContains.class);
        this.functions.put("str_starts", StringStartsWith.class);
        this.functions.put("str_ends", StringEndsWith.class);
    }

    public MathBuilder lenient() {
        this.strict = false;
        return this;
    }

    public void register(Variable variable) {
        this.variables.put(variable.getName(), variable);
    }

    public IValue parse(String expression) throws Exception {
        return this.parseSymbols(this.breakdownChars(this.breakdown(expression)));
    }

    public String[] breakdown(String expression) throws Exception {
        if (this.strict && !expression.matches("^[\\w\\d\\s_+-/*%^&|<>=!?:.,()\"'@~\\[\\]]+$")) {
            throw new Exception("Given expression '" + expression + "' contains illegal characters!");
        }
        String[] chars = expression.split("(?!^)");
        int left = 0;
        int right = 0;
        for (String s2 : chars) {
            if (s2.equals("(")) {
                ++left;
                continue;
            }
            if (!s2.equals(")")) continue;
            ++right;
        }
        if (left != right) {
            throw new Exception("Given expression '" + expression + "' has more uneven amount of parenthesis, there are " + left + " open and " + right + " closed!");
        }
        return chars;
    }

    public List<Object> breakdownChars(String[] chars) {
        ArrayList<Object> symbols = new ArrayList<Object>();
        String buffer = "";
        int len = chars.length;
        boolean string = false;
        block0: for (int i2 = 0; i2 < len; ++i2) {
            boolean longOperator;
            String s2 = chars[i2];
            boolean bl2 = longOperator = i2 < chars.length - 1 && this.isOperator(s2 + chars[i2 + 1]);
            if (s2.equals("\"")) {
                boolean bl3 = string = !string;
            }
            if (string) {
                buffer = buffer + s2;
                continue;
            }
            if (this.isOperator(s2) || longOperator || s2.equals(",")) {
                if (s2.equals("-")) {
                    boolean isOperatorBehind;
                    int size = symbols.size();
                    boolean isEmpty = buffer.trim().isEmpty();
                    boolean isFirst = size == 0 && isEmpty;
                    boolean bl4 = isOperatorBehind = size > 0 && (this.isOperator(symbols.get(size - 1)) || symbols.get(size - 1).equals(",")) && isEmpty;
                    if (isFirst || isOperatorBehind) {
                        buffer = buffer + s2;
                        continue;
                    }
                }
                if (!buffer.isEmpty()) {
                    symbols.add(buffer);
                    buffer = "";
                }
                if (longOperator) {
                    symbols.add(s2 + chars[i2 + 1]);
                    ++i2;
                    continue;
                }
                symbols.add(s2);
                continue;
            }
            if (s2.equals("(")) {
                if (!buffer.isEmpty()) {
                    symbols.add(buffer);
                    buffer = "";
                }
                int counter = 1;
                for (int j2 = i2 + 1; j2 < len; ++j2) {
                    String c2 = chars[j2];
                    if (c2.equals("(")) {
                        ++counter;
                    } else if (c2.equals(")")) {
                        --counter;
                    }
                    if (counter == 0) {
                        symbols.add(this.breakdownChars(buffer.split("(?!^)")));
                        i2 = j2;
                        buffer = "";
                        continue block0;
                    }
                    buffer = buffer + c2;
                }
                continue;
            }
            buffer = buffer + s2;
        }
        if (!buffer.isEmpty()) {
            symbols.add(buffer);
        }
        return this.trimSymbols(symbols);
    }

    private List<Object> trimSymbols(List<Object> symbols) {
        ArrayList<Object> newSymbols = new ArrayList<Object>();
        for (int i2 = 0; i2 < symbols.size(); ++i2) {
            Object value = symbols.get(i2);
            if (value instanceof String) {
                String string = ((String)value).trim();
                if (string.isEmpty()) continue;
                newSymbols.add(string);
                continue;
            }
            newSymbols.add(this.trimSymbols((List)value));
        }
        return newSymbols;
    }

    public IValue parseSymbols(List<Object> symbols) throws Exception {
        int lastOp;
        IValue ternary = this.tryTernary(symbols);
        if (ternary != null) {
            return ternary;
        }
        int size = symbols.size();
        if (size == 1) {
            return this.valueFromObject(symbols.get(0));
        }
        if (size == 2) {
            Object first = symbols.get(0);
            Object second = symbols.get(1);
            if ((this.isVariable(first) || first.equals("-")) && second instanceof List) {
                return this.createFunction((String)first, (List)second);
            }
        }
        int op2 = lastOp = this.seekLastOperator(symbols);
        while (op2 != -1) {
            int leftOp = this.seekLastOperator(symbols, op2 - 1);
            if (leftOp != -1) {
                Operation left = this.operationForOperator((String)symbols.get(leftOp));
                Operation right = this.operationForOperator((String)symbols.get(op2));
                if (right.value > left.value) {
                    IValue leftValue = this.parseSymbols(symbols.subList(0, leftOp));
                    IValue rightValue = this.parseSymbols(symbols.subList(leftOp + 1, size));
                    return new Operator(left, leftValue, rightValue);
                }
                if (left.value > right.value) {
                    Operation initial = this.operationForOperator((String)symbols.get(lastOp));
                    if (initial.value < left.value) {
                        IValue leftValue = this.parseSymbols(symbols.subList(0, lastOp));
                        IValue rightValue = this.parseSymbols(symbols.subList(lastOp + 1, size));
                        return new Operator(initial, leftValue, rightValue);
                    }
                    IValue leftValue = this.parseSymbols(symbols.subList(0, op2));
                    IValue rightValue = this.parseSymbols(symbols.subList(op2 + 1, size));
                    return new Operator(right, leftValue, rightValue);
                }
            }
            op2 = leftOp;
        }
        Operation operation = this.operationForOperator((String)symbols.get(lastOp));
        return new Operator(operation, this.parseSymbols(symbols.subList(0, lastOp)), this.parseSymbols(symbols.subList(lastOp + 1, size)));
    }

    protected int seekLastOperator(List<Object> symbols) {
        return this.seekLastOperator(symbols, symbols.size() - 1);
    }

    protected int seekLastOperator(List<Object> symbols, int offset) {
        for (int i2 = offset; i2 >= 0; --i2) {
            Object o2 = symbols.get(i2);
            if (!this.isOperator(o2)) continue;
            if (o2.equals("-")) {
                Object prev;
                Object next = i2 < symbols.size() - 1 ? symbols.get(i2 + 1) : null;
                Object object = prev = i2 > 0 ? symbols.get(i2 - 1) : null;
                if (next instanceof List && (this.isOperator(prev) || prev == null)) continue;
            }
            return i2;
        }
        return -1;
    }

    protected IValue tryTernary(List<Object> symbols) throws Exception {
        int question = -1;
        int questions = 0;
        int colon = -1;
        int colons = 0;
        int size = symbols.size();
        for (int i2 = 0; i2 < size; ++i2) {
            Object object = symbols.get(i2);
            if (!(object instanceof String)) continue;
            if (object.equals("?")) {
                if (question == -1) {
                    question = i2;
                }
                ++questions;
                continue;
            }
            if (!object.equals(":")) continue;
            if (colons + 1 == questions && colon == -1) {
                colon = i2;
            }
            ++colons;
        }
        if (questions == colons && question > 0 && question + 1 < colon && colon < size - 1) {
            return new Ternary(this.parseSymbols(symbols.subList(0, question)), this.parseSymbols(symbols.subList(question + 1, colon)), this.parseSymbols(symbols.subList(colon + 1, size)));
        }
        return null;
    }

    protected IValue createFunction(String first, List<Object> args) throws Exception {
        if (first.equals("!")) {
            return new Negate(this.parseSymbols(args));
        }
        if (first.startsWith("!") && first.length() > 1) {
            return new Negate(this.createFunction(first.substring(1), args));
        }
        if (first.equals("-")) {
            return new Negative(this.parseSymbols(args));
        }
        if (first.startsWith("-") && first.length() > 1) {
            return new Negative(this.createFunction(first.substring(1), args));
        }
        if (!this.functions.containsKey(first)) {
            throw new Exception("Function '" + first + "' couldn't be found!");
        }
        ArrayList<IValue> values = new ArrayList<IValue>();
        ArrayList<Object> buffer = new ArrayList<Object>();
        for (Object o2 : args) {
            if (o2.equals(",")) {
                values.add(this.parseSymbols(buffer));
                buffer.clear();
                continue;
            }
            buffer.add(o2);
        }
        if (!buffer.isEmpty()) {
            values.add(this.parseSymbols(buffer));
        }
        Class<? extends Function> function = this.functions.get(first);
        Constructor<? extends Function> ctor = function.getConstructor(IValue[].class, String.class);
        Function func = ctor.newInstance(values.toArray(new IValue[values.size()]), first);
        return func;
    }

    public IValue valueFromObject(Object object) throws Exception {
        if (object instanceof String) {
            String symbol = (String)object;
            if (symbol.startsWith("!")) {
                return new Negate(this.valueFromObject(symbol.substring(1)));
            }
            if (symbol.startsWith("\"") && symbol.endsWith("\"")) {
                return new Constant(symbol.substring(1, symbol.length() - 1));
            }
            if (this.isDecimal(symbol)) {
                return new Constant(Double.parseDouble(symbol));
            }
            if (this.isVariable(symbol)) {
                if (symbol.startsWith("-")) {
                    Variable value = this.getVariable(symbol = symbol.substring(1));
                    if (value != null) {
                        return new Negative(value);
                    }
                } else {
                    Variable value = this.getVariable(symbol);
                    if (value != null) {
                        return value;
                    }
                }
            }
        } else if (object instanceof List) {
            return new Group(this.parseSymbols((List)object));
        }
        throw new Exception("Given object couldn't be converted to value! " + object);
    }

    protected Variable getVariable(String name) {
        return this.variables.get(name);
    }

    protected Operation operationForOperator(String op2) throws Exception {
        for (Operation operation : Operation.values()) {
            if (!operation.sign.equals(op2)) continue;
            return operation;
        }
        throw new Exception("There is no such operator '" + op2 + "'!");
    }

    protected boolean isVariable(Object o2) {
        return o2 instanceof String && !this.isDecimal((String)o2) && !this.isOperator((String)o2);
    }

    protected boolean isOperator(Object o2) {
        return o2 instanceof String && this.isOperator((String)o2);
    }

    protected boolean isOperator(String s2) {
        return Operation.OPERATORS.contains(s2) || s2.equals("?") || s2.equals(":");
    }

    protected boolean isDecimal(String s2) {
        return s2.matches("^-?\\d+(\\.\\d+)?$");
    }
}

