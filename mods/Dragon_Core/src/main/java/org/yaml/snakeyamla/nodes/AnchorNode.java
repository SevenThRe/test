/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.nodes;

import org.yaml.snakeyamla.nodes.Node;
import org.yaml.snakeyamla.nodes.NodeId;

public class AnchorNode
extends Node {
    private Node realNode;

    public AnchorNode(Node realNode) {
        super(realNode.getTag(), realNode.getStartMark(), realNode.getEndMark());
        this.realNode = realNode;
    }

    @Override
    public NodeId getNodeId() {
        return NodeId.anchor;
    }

    public Node getRealNode() {
        return this.realNode;
    }
}

