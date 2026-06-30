/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cdf
 *  cep
 */
package net.optifine.shaders;

import net.optifine.shaders.ShadersTex;

public class DefaultTexture
extends cdf {
    public DefaultTexture() {
        this.a(null);
    }

    public void a(cep resourcemanager) {
        int[] aint = ShadersTex.createAIntImage(1, -1);
        ShadersTex.setupTexture(this.getMultiTexID(), aint, 1, 1, false, false);
    }
}

