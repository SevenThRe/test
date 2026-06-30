/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.nodes;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Node;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Tag;
import java.util.List;

public abstract class CollectionNode<T>
extends Node {
    private DumperOptions.FlowStyle flowStyle;

    public CollectionNode(Tag tag, Mark startMark, Mark endMark, DumperOptions.FlowStyle flowStyle) {
        super(tag, startMark, endMark);
        this.setFlowStyle(flowStyle);
    }

    @Deprecated
    public CollectionNode(Tag tag, Mark startMark, Mark endMark, Boolean flowStyle) {
        this(tag, startMark, endMark, DumperOptions.FlowStyle.fromBoolean(flowStyle));
    }

    public abstract List<T> getValue();

    public DumperOptions.FlowStyle getFlowStyle() {
        return this.flowStyle;
    }

    public void setFlowStyle(DumperOptions.FlowStyle flowStyle) {
        if (flowStyle == null) {
            throw new NullPointerException("Flow style must be provided.");
        }
        this.flowStyle = flowStyle;
    }

    @Deprecated
    public void setFlowStyle(Boolean flowStyle) {
        this.setFlowStyle(DumperOptions.FlowStyle.fromBoolean(flowStyle));
    }

    public void setEndMark(Mark endMark) {
        this.endMark = endMark;
    }
}

