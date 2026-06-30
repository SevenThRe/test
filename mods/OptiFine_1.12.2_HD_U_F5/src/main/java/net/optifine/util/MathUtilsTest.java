/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  rk
 */
package net.optifine.util;

import net.optifine.util.MathUtils;

public class MathUtilsTest {
    public static void main(String[] args) throws Exception {
        OPER[] values = OPER.values();
        for (int i = 0; i < values.length; ++i) {
            OPER oper = values[i];
            MathUtilsTest.dbg("******** " + (Object)((Object)oper) + " ***********");
            MathUtilsTest.test(oper, false);
        }
    }

    private static void test(OPER oper, boolean fast) {
        double max;
        double min;
        rk.fastMath = fast;
        switch (oper) {
            case SIN: 
            case COS: {
                min = -rk.PI;
                max = rk.PI;
                break;
            }
            case ASIN: 
            case ACOS: {
                min = -1.0;
                max = 1.0;
                break;
            }
            default: {
                return;
            }
        }
        int count = 10;
        for (int i = 0; i <= count; ++i) {
            float res2;
            float res1;
            double val = min + (double)i * (max - min) / (double)count;
            switch (oper) {
                case SIN: {
                    res1 = (float)Math.sin(val);
                    res2 = rk.a((float)((float)val));
                    break;
                }
                case COS: {
                    res1 = (float)Math.cos(val);
                    res2 = rk.b((float)((float)val));
                    break;
                }
                case ASIN: {
                    res1 = (float)Math.asin(val);
                    res2 = MathUtils.asin((float)val);
                    break;
                }
                case ACOS: {
                    res1 = (float)Math.acos(val);
                    res2 = MathUtils.acos((float)val);
                    break;
                }
                default: {
                    return;
                }
            }
            MathUtilsTest.dbg(String.format("%.2f, Math: %f, Helper: %f, diff: %f", val, Float.valueOf(res1), Float.valueOf(res2), Float.valueOf(Math.abs(res1 - res2))));
        }
    }

    public static void dbg(String str) {
        System.out.println(str);
    }

    private static enum OPER {
        SIN,
        COS,
        ASIN,
        ACOS;

    }
}

