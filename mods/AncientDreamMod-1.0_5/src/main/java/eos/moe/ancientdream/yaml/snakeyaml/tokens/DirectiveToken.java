/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.tokens;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.error.YAMLException;
import eos.moe.ancientdream.yaml.snakeyaml.tokens.Token;
import java.util.List;

public final class DirectiveToken<T>
extends Token {
    private final String name;
    private final List<T> value;

    public DirectiveToken(String name, List<T> value, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.name = name;
        if (value != null && value.size() != 2) {
            throw new YAMLException("Two strings must be provided instead of " + String.valueOf(value.size()));
        }
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public List<T> getValue() {
        return this.value;
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.Directive;
    }
}

