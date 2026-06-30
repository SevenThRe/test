/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.scanner;

import org.yaml.snakeyamla.tokens.Token;

public interface Scanner {
    public boolean checkToken(Token.ID ... var1);

    public Token peekToken();

    public Token getToken();
}

