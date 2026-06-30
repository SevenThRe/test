/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.expr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;

public class TestExpressions {
    public static void main(String[] args) throws Exception {
        ExpressionParser ep = new ExpressionParser(null);
        while (true) {
            try {
                InputStreamReader ir;
                BufferedReader br;
                String line;
                while ((line = (br = new BufferedReader(ir = new InputStreamReader(System.in))).readLine()).length() > 0) {
                    IExpression expr = ep.parse(line);
                    if (expr instanceof IExpressionFloat) {
                        IExpressionFloat ef = (IExpressionFloat)expr;
                        float val = ef.eval();
                        System.out.println("" + val);
                    }
                    if (!(expr instanceof IExpressionBool)) continue;
                    IExpressionBool eb = (IExpressionBool)expr;
                    boolean val = eb.eval();
                    System.out.println("" + val);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
    }
}

