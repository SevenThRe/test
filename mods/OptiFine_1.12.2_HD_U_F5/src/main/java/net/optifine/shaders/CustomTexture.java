/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cds
 *  cdt
 */
package net.optifine.shaders;

import net.optifine.shaders.ICustomTexture;

public class CustomTexture
implements ICustomTexture {
    private int textureUnit = -1;
    private String path = null;
    private cds texture = null;

    public CustomTexture(int textureUnit, String path, cds texture) {
        this.textureUnit = textureUnit;
        this.path = path;
        this.texture = texture;
    }

    @Override
    public int getTextureUnit() {
        return this.textureUnit;
    }

    public String getPath() {
        return this.path;
    }

    public cds getTexture() {
        return this.texture;
    }

    @Override
    public int getTextureId() {
        return this.texture.b();
    }

    @Override
    public void deleteTexture() {
        cdt.a((int)this.texture.b());
    }

    public String toString() {
        return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
    }
}

