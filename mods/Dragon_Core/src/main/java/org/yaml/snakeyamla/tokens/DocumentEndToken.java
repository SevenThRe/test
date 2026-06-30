/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.tokens;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.tokens.Token;

public final class DocumentEndToken
extends Token {
    public DocumentEndToken(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.DocumentEnd;
    }
}

