/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

public class WildcardPattern {
    private String pattern;

    public WildcardPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean matches(String check) {
        boolean startsWithWildcard = this.pattern.startsWith("*");
        boolean endsWithWildcard = this.pattern.endsWith("*");
        return this.pattern.equals("*") || startsWithWildcard && endsWithWildcard && check.contains(this.pattern.substring(1, this.pattern.length() - 1)) || startsWithWildcard && check.endsWith(this.pattern.substring(1)) || endsWithWildcard && check.startsWith(this.pattern.substring(0, this.pattern.length() - 1)) || check.equals(this.pattern);
    }
}

