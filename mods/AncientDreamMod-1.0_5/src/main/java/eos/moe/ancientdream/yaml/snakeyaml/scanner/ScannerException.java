/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.scanner;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.error.MarkedYAMLException;

public class ScannerException
extends MarkedYAMLException {
    private static final long serialVersionUID = 4782293188600445954L;

    public ScannerException(String context, Mark contextMark, String problem, Mark problemMark, String note) {
        super(context, contextMark, problem, problemMark, note);
    }

    public ScannerException(String context, Mark contextMark, String problem, Mark problemMark) {
        this(context, contextMark, problem, problemMark, (String)null);
    }
}

