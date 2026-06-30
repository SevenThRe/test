/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.composer;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.error.MarkedYAMLException;

public class ComposerException
extends MarkedYAMLException {
    private static final long serialVersionUID = 2146314636913113935L;

    protected ComposerException(String context, Mark contextMark, String problem, Mark problemMark) {
        super(context, contextMark, problem, problemMark);
    }
}

