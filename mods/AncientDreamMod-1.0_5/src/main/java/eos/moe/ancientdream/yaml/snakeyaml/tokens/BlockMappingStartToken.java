/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.tokens;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;

public final class BlockMappingStartToken
extends Token {
    public BlockMappingStartToken(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.BlockMappingStart;
    }
}

