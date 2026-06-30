/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.events;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.events.Event;
import eos.moe.ancientdream.yaml.snakeyaml.events.ImplicitTuple;
import eos.moe.ancientdream.yaml.snakeyaml.events.NodeEvent;

public final class ScalarEvent
extends NodeEvent {
    private final String tag;
    private final DumperOptions.ScalarStyle style;
    private final String value;
    private final ImplicitTuple implicit;

    public ScalarEvent(String anchor, String tag, ImplicitTuple implicit, String value, Mark startMark, Mark endMark, DumperOptions.ScalarStyle style) {
        super(anchor, startMark, endMark);
        this.tag = tag;
        this.implicit = implicit;
        if (value == null) {
            throw new NullPointerException("Value must be provided.");
        }
        this.value = value;
        if (style == null) {
            throw new NullPointerException("Style must be provided.");
        }
        this.style = style;
    }

    @Deprecated
    public ScalarEvent(String anchor, String tag, ImplicitTuple implicit, String value, Mark startMark, Mark endMark, Character style) {
        this(anchor, tag, implicit, value, startMark, endMark, DumperOptions.ScalarStyle.createStyle(style));
    }

    public String getTag() {
        return this.tag;
    }

    public DumperOptions.ScalarStyle getScalarStyle() {
        return this.style;
    }

    @Deprecated
    public Character getStyle() {
        return this.style.getChar();
    }

    public String getValue() {
        return this.value;
    }

    public ImplicitTuple getImplicit() {
        return this.implicit;
    }

    @Override
    protected String getArguments() {
        return super.getArguments() + ", tag=" + this.tag + ", " + this.implicit + ", value=" + this.value;
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.Scalar;
    }

    public boolean isPlain() {
        return this.style == DumperOptions.ScalarStyle.PLAIN;
    }
}

