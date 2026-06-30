/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.resolver;

import eos.moe.ancientdream.yaml.snakeyaml.nodes.Tag;
import java.util.regex.Pattern;

final class ResolverTuple {
    private final Tag tag;
    private final Pattern regexp;

    public ResolverTuple(Tag tag, Pattern regexp) {
        this.tag = tag;
        this.regexp = regexp;
    }

    public Tag getTag() {
        return this.tag;
    }

    public Pattern getRegexp() {
        return this.regexp;
    }

    public String toString() {
        return "Tuple tag=" + this.tag + " regexp=" + this.regexp;
    }
}

