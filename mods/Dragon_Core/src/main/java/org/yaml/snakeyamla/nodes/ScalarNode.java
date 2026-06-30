/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.nodes;

import org.yaml.snakeyamla.DumperOptions;
import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.nodes.Node;
import org.yaml.snakeyamla.nodes.NodeId;
import org.yaml.snakeyamla.nodes.Tag;

public class ScalarNode
extends Node {
    private DumperOptions.ScalarStyle style;
    private String value;

    public ScalarNode(Tag tag, String value, Mark startMark, Mark endMark, DumperOptions.ScalarStyle style) {
        this(tag, true, value, startMark, endMark, style);
    }

    public ScalarNode(Tag tag, boolean resolved, String value, Mark startMark, Mark endMark, DumperOptions.ScalarStyle style) {
        super(tag, startMark, endMark);
        if (value == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = value;
        if (style == null) {
            throw new NullPointerException("Scalar style must be provided.");
        }
        this.style = style;
        this.resolved = resolved;
    }

    @Deprecated
    public ScalarNode(Tag tag, String value, Mark startMark, Mark endMark, Character style) {
        this(tag, value, startMark, endMark, DumperOptions.ScalarStyle.createStyle(style));
    }

    @Deprecated
    public ScalarNode(Tag tag, boolean resolved, String value, Mark startMark, Mark endMark, Character style) {
        this(tag, resolved, value, startMark, endMark, DumperOptions.ScalarStyle.createStyle(style));
    }

    @Deprecated
    public Character getStyle() {
        return this.style.getChar();
    }

    public DumperOptions.ScalarStyle getScalarStyle() {
        return this.style;
    }

    @Override
    public NodeId getNodeId() {
        return NodeId.scalar;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", value=" + this.getValue() + ")>";
    }

    public boolean isPlain() {
        return this.style == DumperOptions.ScalarStyle.PLAIN;
    }
}

