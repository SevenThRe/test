/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.lm;
import java.util.Collections;
import java.util.Stack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fm {
    private Stack<String> s;
    private int[] m;
    private Stack<Character> j;

    private /* synthetic */ boolean r(char a2) {
        return a2 == '+' || a2 == '-' || a2 == '*' || a2 == '/' || a2 == '(' || a2 == ')';
    }

    private /* synthetic */ String r(String a2, String a3, char a4) {
        String string = "";
        switch (a4) {
            case '+': {
                string = String.valueOf(lm.z(a2, a3));
                return string;
            }
            case '-': {
                string = String.valueOf(lm.y(a2, a3));
                return string;
            }
            case '*': {
                while (false) {
                }
                string = String.valueOf(lm.h(a2, a3));
                return string;
            }
            case '/': {
                string = String.valueOf(lm.r(a2, a3));
            }
        }
        return string;
    }

    public fm() {
        fm a2;
        fm fm2 = a2;
        fm fm3 = a2;
        fm2.s = new Stack();
        fm2.j = new Stack();
        int[] nArray = new int[8];
        nArray[0] = 0;
        nArray[1] = 3;
        nArray[2] = 2;
        nArray[3] = 1;
        nArray[4] = -1;
        nArray[5] = 1;
        nArray[6] = 0;
        nArray[7] = 2;
        fm2.m = nArray;
    }

    private /* synthetic */ void r(String a2) {
        fm a3;
        a3.j.push(Character.valueOf(','));
        a2 = ((String)a2).toCharArray();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = n4;
        while (n5 < ((Object)a2).length) {
            Object object = a2[n4];
            if (a3.r((char)object)) {
                if (n3 > 0) {
                    a3.s.push(new String((char[])a2, n2, n3));
                }
                char c2 = a3.j.peek().charValue();
                if (object == 41) {
                    fm fm2 = a3;
                    while (fm2.j.peek().charValue() != '(') {
                        fm fm3 = a3;
                        fm2 = fm3;
                        a3.s.push(String.valueOf(fm3.j.pop()));
                    }
                    a3.j.pop();
                } else {
                    Object object2 = object;
                    while (object2 != 40 && c2 != ',' && a3.r((char)object, c2)) {
                        fm fm4 = a3;
                        a3.s.push(String.valueOf(fm4.j.pop()));
                        c2 = fm4.j.peek().charValue();
                        object2 = object;
                    }
                    a3.j.push(Character.valueOf((char)object));
                }
                n3 = 0;
                n2 = n4 + 1;
            } else {
                ++n3;
            }
            n5 = ++n4;
        }
        if (n3 > 1 || n3 == 1 && !a3.r((char)a2[n2])) {
            a3.s.push(new String((char[])a2, n2, n3));
        }
        fm fm5 = a3;
        while (fm5.j.peek().charValue() != ',') {
            fm fm6 = a3;
            fm5 = fm6;
            a3.s.push(String.valueOf(fm6.j.pop()));
        }
    }

    public double y(String a2) {
        fm a3;
        Stack<String> stack = new Stack<String>();
        fm fm2 = a3;
        fm fm3 = fm2;
        fm2.r(a2);
        Collections.reverse(fm2.s);
        while (!fm3.s.isEmpty()) {
            String string = a3.s.pop();
            if (!a3.r(string.charAt(0))) {
                string = string.replace("~", "-");
                fm3 = a3;
                stack.push(string);
                continue;
            }
            String string2 = (String)stack.pop();
            a2 = (String)stack.pop();
            a2 = a2.replace("~", "-");
            string2 = string2.replace("~", "-");
            fm fm4 = a3;
            fm3 = fm4;
            a2 = fm4.r(a2, string2, string.charAt(0));
            stack.push(a2);
        }
        return Double.valueOf((String)stack.pop());
    }

    private static /* synthetic */ String r(String a2) {
        int n2;
        char[] cArray = a2.toCharArray();
        int n3 = n2 = 0;
        while (n3 < cArray.length) {
            if (cArray[n2] == '-') {
                if (n2 == 0) {
                    cArray[n2] = 126;
                } else {
                    char c2 = cArray[n2 - 1];
                    if (c2 == '+' || c2 == '-' || c2 == '*' || c2 == '/' || c2 == '(' || c2 == 'E' || c2 == 'e') {
                        cArray[n2] = 126;
                    }
                }
            }
            n3 = ++n2;
        }
        if (cArray[0] == '~' || cArray[1] == '(') {
            cArray[0] = 45;
            return new StringBuilder().insert(0, "0").append(new String(cArray)).toString();
        }
        return new String(cArray);
    }

    public boolean r(char a2, char a3) {
        fm a4;
        boolean bl = false;
        if (a4.m[a3 - 40] >= a4.m[a2 - 40]) {
            bl = true;
        }
        return bl;
    }

    public static int r(String a2) {
        return (int)fm.r(a2);
    }

    public static double r(String a2) {
        if (a2.length() == 1) {
            try {
                return Integer.parseInt(a2);
            }
            catch (Exception exception) {
                return 0.0;
            }
        }
        double d2 = 0.0;
        fm fm2 = new fm();
        try {
            a2 = fm.r(a2);
            d2 = fm2.y(a2);
            return d2;
        }
        catch (Exception exception) {
            return 0.0;
        }
    }
}

