/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.parser;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.error.MarkedYAMLException;

public class ParserException
extends MarkedYAMLException {
    private static final long serialVersionUID = -2349253802798398038L;

    public ParserException(String context, Mark contextMark, String problem, Mark problemMark) {
        super(context, contextMark, problem, problemMark, null, null);
    }
}

