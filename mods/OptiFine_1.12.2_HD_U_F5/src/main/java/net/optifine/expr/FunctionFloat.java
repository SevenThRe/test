/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.expr;

import net.optifine.expr.ConstantFloat;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionFloat;
import net.optifine.shaders.uniform.Smoother;

public class FunctionFloat
implements IExpressionFloat {
    private FunctionType type;
    private IExpression[] arguments;
    private int smoothId = -1;

    public FunctionFloat(FunctionType type, IExpression[] arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    @Override
    public float eval() {
        IExpression[] args = this.arguments;
        switch (this.type) {
            case SMOOTH: {
                float valFadeDown;
                IExpression expr0 = args[0];
                if (expr0 instanceof ConstantFloat) break;
                float valRaw = FunctionFloat.evalFloat(args, 0);
                float valFadeUp = args.length > 1 ? FunctionFloat.evalFloat(args, 1) : 1.0f;
                float f = valFadeDown = args.length > 2 ? FunctionFloat.evalFloat(args, 2) : valFadeUp;
                if (this.smoothId < 0) {
                    this.smoothId = Smoother.getNextId();
                }
                float valSmooth = Smoother.getSmoothValue(this.smoothId, valRaw, valFadeUp, valFadeDown);
                return valSmooth;
            }
        }
        return this.type.evalFloat(this.arguments);
    }

    private static float evalFloat(IExpression[] exprs, int index) {
        IExpressionFloat ef = (IExpressionFloat)exprs[index];
        float val = ef.eval();
        return val;
    }

    public String toString() {
        return "" + (Object)((Object)this.type) + "()";
    }
}

