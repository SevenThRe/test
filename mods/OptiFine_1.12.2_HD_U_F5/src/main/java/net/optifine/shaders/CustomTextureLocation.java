/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  cdf
 *  cdm
 *  cdr
 *  cds
 *  nf
 */
package net.optifine.shaders;

import net.optifine.shaders.ICustomTexture;
import net.optifine.shaders.MultiTexID;

public class CustomTextureLocation
implements ICustomTexture {
    private int textureUnit = -1;
    private nf location;
    private int variant = 0;
    private cds texture;
    public static final int VARIANT_BASE = 0;
    public static final int VARIANT_NORMAL = 1;
    public static final int VARIANT_SPECULAR = 2;

    public CustomTextureLocation(int textureUnit, nf location, int variant) {
        this.textureUnit = textureUnit;
        this.location = location;
        this.variant = variant;
    }

    public cds getTexture() {
        if (this.texture == null) {
            cdr textureManager = bib.z().N();
            this.texture = textureManager.b(this.location);
            if (this.texture == null) {
                this.texture = new cdm(this.location);
                textureManager.a(this.location, this.texture);
                this.texture = textureManager.b(this.location);
            }
        }
        return this.texture;
    }

    @Override
    public int getTextureId() {
        cds tex = this.getTexture();
        if (this.variant != 0 && tex instanceof cdf) {
            cdf at = (cdf)tex;
            MultiTexID mtid = at.multiTex;
            if (mtid != null) {
                if (this.variant == 1) {
                    return mtid.norm;
                }
                if (this.variant == 2) {
                    return mtid.spec;
                }
            }
        }
        return tex.b();
    }

    @Override
    public int getTextureUnit() {
        return this.textureUnit;
    }

    @Override
    public void deleteTexture() {
    }

    public String toString() {
        return "textureUnit: " + this.textureUnit + ", location: " + this.location + ", glTextureId: " + (this.texture != null ? Integer.valueOf(this.texture.b()) : "");
    }
}

