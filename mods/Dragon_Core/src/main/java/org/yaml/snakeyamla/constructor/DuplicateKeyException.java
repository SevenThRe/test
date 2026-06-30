/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.constructor;

import org.yaml.snakeyamla.constructor.ConstructorException;
import org.yaml.snakeyamla.error.Mark;

public class DuplicateKeyException
extends ConstructorException {
    protected DuplicateKeyException(Mark contextMark, Object key, Mark problemMark) {
        super("while constructing a mapping", contextMark, "found duplicate key " + String.valueOf(key), problemMark);
    }
}

