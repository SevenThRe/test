/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.tokens;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.TagTuple;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;

public final class TagToken
extends Token {
    private final TagTuple value;

    public TagToken(TagTuple value, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.value = value;
    }

    public TagTuple getValue() {
        return this.value;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Tag;
    }
}

