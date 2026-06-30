/*
 * Decompiled with CFR 0.152.
 */
package blockbuster;

public enum BedrockCurveType {
    LINEAR("linear"),
    HERMITE("catmull_rom");

    public final String id;

    public static BedrockCurveType fromString(String type) {
        for (BedrockCurveType t2 : BedrockCurveType.values()) {
            if (!t2.id.equals(type)) continue;
            return t2;
        }
        return LINEAR;
    }

    private BedrockCurveType(String id2) {
        this.id = id2;
    }
}

