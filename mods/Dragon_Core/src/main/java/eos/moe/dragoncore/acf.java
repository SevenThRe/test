/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.Serializable;

public class acf
implements Serializable {
    private static final long serialVersionUID = 8818573781391239129L;
    public float x;
    public float y;

    public acf() {
        acf a2;
    }

    public acf(float a2, float a3) {
        acf a4;
        a4.set(a2, a3);
    }

    public void set(float a2, float a3) {
        a.x = a2;
        a.y = a3;
    }

    public float lengthSquared() {
        acf a2;
        return a2.x * a2.x + a2.y * a2.y;
    }

    public acf translate(float a2, float a3) {
        acf a4;
        a4.x += a2;
        a4.y += a3;
        return a4;
    }

    public acf negate(acf a2) {
        acf a3;
        if (a2 == null) {
            a2 = new acf();
        }
        a2.x = -a3.x;
        a2.y = -a3.y;
        return a2;
    }

    public static float dot(acf a2, acf a3) {
        return a2.x * a3.x + a2.y * a3.y;
    }

    public static acf add(acf a2, acf a3, acf a4) {
        if (a4 == null) {
            return new acf(a2.x + a3.x, a2.y + a3.y);
        }
        a4.set(a2.x + a3.x, a2.y + a3.y);
        return a4;
    }

    public static acf sub(acf a2, acf a3, acf a4) {
        if (a4 == null) {
            return new acf(a2.x - a3.x, a2.y - a3.y);
        }
        a4.set(a2.x - a3.x, a2.y - a3.y);
        return a4;
    }

    public String toString() {
        acf a2;
        StringBuilder a3 = new StringBuilder(64);
        a3.append("Vector2f[");
        a3.append(a2.x);
        a3.append(", ");
        a3.append(a2.y);
        a3.append(']');
        return a3.toString();
    }

    public final float getX() {
        acf a2;
        return a2.x;
    }

    public final float getY() {
        acf a2;
        return a2.y;
    }

    public final void setX(float a2) {
        a.x = a2;
    }

    public final void setY(float a2) {
        a.y = a2;
    }

    public boolean equals(Object a2) {
        acf a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        acf a4 = (acf)a2;
        return a3.x == a4.x && a3.y == a4.y;
    }
}

