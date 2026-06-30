/*
 * Decompiled with CFR 0.152.
 */
package blockbuster;

public enum BedrockMaterial {
    OPAQUE("particles_opaque"),
    ALPHA("particles_alpha"),
    BLEND("particles_blend");

    public final String id;

    public static BedrockMaterial fromString(String material) {
        for (BedrockMaterial mat : BedrockMaterial.values()) {
            if (!mat.id.equals(material)) continue;
            return mat;
        }
        return OPAQUE;
    }

    private BedrockMaterial(String id2) {
        this.id = id2;
    }
}

