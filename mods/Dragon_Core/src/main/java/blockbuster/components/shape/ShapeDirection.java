/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Vector3d
 */
package blockbuster.components.shape;

import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

public abstract class ShapeDirection {
    public static final ShapeDirection INWARDS = new Inwards(-1.0f);
    public static final ShapeDirection OUTWARDS = new Inwards(1.0f);

    public abstract void applyDirection(BedrockParticle var1, double var2, double var4, double var6);

    public abstract JsonElement toJson();

    public static class Vector
    extends ShapeDirection {
        public MolangExpression x;
        public MolangExpression y;
        public MolangExpression z;

        public Vector(MolangExpression x2, MolangExpression y2, MolangExpression z2) {
            this.x = x2;
            this.y = y2;
            this.z = z2;
        }

        @Override
        public void applyDirection(BedrockParticle particle, double x2, double y2, double z2) {
            particle.speed.set((float)this.x.get(), (float)this.y.get(), (float)this.z.get());
            if (particle.speed.length() <= 0.0f) {
                particle.speed.set(0.0f, 0.0f, 0.0f);
            } else {
                particle.speed.normalize();
            }
        }

        @Override
        public JsonElement toJson() {
            JsonArray array = new JsonArray();
            array.add(this.x.toJson());
            array.add(this.y.toJson());
            array.add(this.z.toJson());
            return array;
        }
    }

    private static class Inwards
    extends ShapeDirection {
        private float factor;

        public Inwards(float factor) {
            this.factor = factor;
        }

        @Override
        public void applyDirection(BedrockParticle particle, double x2, double y2, double z2) {
            Vector3d vector = new Vector3d(particle.position);
            vector.sub((Tuple3d)new Vector3d(x2, y2, z2));
            if (vector.length() <= 0.0) {
                vector.set(0.0, 0.0, 0.0);
            } else {
                vector.normalize();
                vector.scale((double)this.factor);
            }
            particle.speed.set((Tuple3d)vector);
        }

        @Override
        public JsonElement toJson() {
            return new JsonPrimitive(this.factor < 0.0f ? "inwards" : "outwards");
        }
    }
}

