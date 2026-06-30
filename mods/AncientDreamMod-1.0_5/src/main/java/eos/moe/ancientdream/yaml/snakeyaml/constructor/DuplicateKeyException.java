/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.constructor;

import eos.moe.ancientdream.yaml.snakeyaml.constructor.ConstructorException;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;

public class DuplicateKeyException
extends ConstructorException {
    protected DuplicateKeyException(Mark contextMark, Object key, Mark problemMark) {
        super("while constructing a mapping", contextMark, "found duplicate key " + String.valueOf(key), problemMark);
    }
}

