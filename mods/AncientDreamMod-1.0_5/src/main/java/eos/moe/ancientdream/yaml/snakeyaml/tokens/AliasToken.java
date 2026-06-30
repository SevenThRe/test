/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.tokens;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;

public final class AliasToken
extends Token {
    private final String value;

    public AliasToken(String value, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Alias;
    }
}

