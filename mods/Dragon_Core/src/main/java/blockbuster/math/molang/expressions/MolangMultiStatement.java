/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.molang.expressions;

import blockbuster.math.Variable;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class MolangMultiStatement
extends MolangExpression {
    public List<MolangExpression> expressions = new ArrayList<MolangExpression>();
    public Map<String, Variable> locals = new HashMap<String, Variable>();

    public MolangMultiStatement(MolangParser context) {
        super(context);
    }

    @Override
    public double get() {
        double value = 0.0;
        for (MolangExpression expression : this.expressions) {
            value = expression.get();
            if (!(expression instanceof MolangValue) || !((MolangValue)expression).returns) continue;
            break;
        }
        return value;
    }

    public String toString() {
        StringJoiner builder = new StringJoiner("; ");
        for (MolangExpression expression : this.expressions) {
            builder.add(expression.toString());
        }
        return builder.toString();
    }
}

