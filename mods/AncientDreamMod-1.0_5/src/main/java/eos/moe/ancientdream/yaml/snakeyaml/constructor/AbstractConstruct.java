/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.constructor;

import eos.moe.ancientdream.yaml.snakeyaml.constructor.Construct;
import eos.moe.ancientdream.yaml.snakeyaml.error.YAMLException;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Node;

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

