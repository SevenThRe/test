/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.tokens;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.tokens.Token;

public final class AnchorToken
extends Token {
    private final String value;

    public AnchorToken(String value, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Anchor;
    }
}

