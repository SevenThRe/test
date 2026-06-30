/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.composer;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.error.MarkedYAMLException;

public class ComposerException
extends MarkedYAMLException {
    private static final long serialVersionUID = 2146314636913113935L;

    protected ComposerException(String context, Mark contextMark, String problem, Mark problemMark) {
        super(context, contextMark, problem, problemMark);
    }
}

