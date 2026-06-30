/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.constructor;

import org.yaml.snakeyamla.constructor.Construct;
import org.yaml.snakeyamla.error.YAMLException;
import org.yaml.snakeyamla.nodes.Node;

public abstract class AbstractConstruct
implements Construct {
    @Override
    public void construct2ndStep(Node node, Object data) {
        if (node.isTwoStepsConstruction()) {
            throw new IllegalStateException("Not Implemented in " + this.getClass().getName());
        }
        throw new YAMLException("Unexpected recursive structure for Node: " + node);
    }
}

