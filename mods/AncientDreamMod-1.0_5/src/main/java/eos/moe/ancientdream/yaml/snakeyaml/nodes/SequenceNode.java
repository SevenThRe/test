/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.nodes;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.CollectionNode;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Node;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.NodeId;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Tag;
import java.util.List;

public class SequenceNode
extends CollectionNode<Node> {
    private final List<Node> value;

    public SequenceNode(Tag tag, boolean resolved, List<Node> value, Mark startMark, Mark endMark, DumperOptions.FlowStyle flowStyle) {
        super(tag, startMark, endMark, flowStyle);
        if (value == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = value;
        this.resolved = resolved;
    }

    public SequenceNode(Tag tag, List<Node> value, DumperOptions.FlowStyle flowStyle) {
        this(tag, true, value, null, null, flowStyle);
    }

    @Deprecated
    public SequenceNode(Tag tag, List<Node> value, Boolean style) {
        this(tag, value, DumperOptions.FlowStyle.fromBoolean(style));
    }

    @Deprecated
    public SequenceNode(Tag tag, boolean resolved, List<Node> value, Mark startMark, Mark endMark, Boolean style) {
        this(tag, resolved, value, startMark, endMark, DumperOptions.FlowStyle.fromBoolean(style));
    }

    @Override
    public NodeId getNodeId() {
        return NodeId.sequence;
    }

    @Override
    public List<Node> getValue() {
        return this.value;
    }

    public void setListType(Class<? extends Object> listType) {
        for (Node node : this.value) {
            node.setType(listType);
        }
    }

    public String toString() {
        return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", value=" + this.getValue() + ")>";
    }
}

