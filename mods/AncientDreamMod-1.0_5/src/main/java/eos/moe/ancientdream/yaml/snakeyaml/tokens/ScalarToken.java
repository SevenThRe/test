/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.tokens;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;

public final class ScalarToken
extends Token {
    private final String value;
    private final boolean plain;
    private final DumperOptions.ScalarStyle style;

    public ScalarToken(String value, Mark startMark, Mark endMark, boolean plain) {
        this(value, plain, startMark, endMark, DumperOptions.ScalarStyle.PLAIN);
    }

    public ScalarToken(String value, boolean plain, Mark startMark, Mark endMark, DumperOptions.ScalarStyle style) {
        super(startMark, endMark);
        this.value = value;
        this.plain = plain;
        if (style == null) {
            throw new NullPointerException("Style must be provided.");
        }
        this.style = style;
    }

    public boolean getPlain() {
        return this.plain;
    }

    public String getValue() {
        return this.value;
    }

    public DumperOptions.ScalarStyle getStyle() {
        return this.style;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Scalar;
    }
}

