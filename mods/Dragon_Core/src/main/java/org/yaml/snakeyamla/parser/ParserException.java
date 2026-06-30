/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.parser;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.error.MarkedYAMLException;

public class ParserException
extends MarkedYAMLException {
    private static final long serialVersionUID = -2349253802798398038L;

    public ParserException(String context, Mark contextMark, String problem, Mark problemMark) {
        super(context, contextMark, problem, problemMark, null, null);
    }
}

