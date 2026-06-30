/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.scanner;

import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;

public interface Scanner {
    public boolean checkToken(Token.ID ... var1);

    public Token peekToken();

    public Token getToken();
}

