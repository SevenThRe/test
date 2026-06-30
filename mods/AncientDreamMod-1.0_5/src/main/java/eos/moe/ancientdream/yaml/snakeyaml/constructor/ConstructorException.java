/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.constructor;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.error.MarkedYAMLException;

public class ConstructorException
extends MarkedYAMLException {
    private static final long serialVersionUID = -8816339931365239910L;

    protected ConstructorException(String context, Mark contextMark, String problem, Mark problemMark, Throwable cause) {
        super(context, contextMark, problem, problemMark, cause);
    }

    protected ConstructorException(String context, Mark contextMark, String problem, Mark problemMark) {
        this(context, contextMark, problem, problemMark, (Throwable)null);
    }
}

