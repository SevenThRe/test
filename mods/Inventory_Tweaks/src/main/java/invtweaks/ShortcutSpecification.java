/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

public class ShortcutSpecification {
    private Action action;
    private Target target;
    private Scope scope;

    public ShortcutSpecification(Action a, Target t, Scope s) {
        this.action = a;
        this.target = t;
        this.scope = s;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action_) {
        this.action = action_;
    }

    public Target getTarget() {
        return this.target;
    }

    public void setTarget(Target target_) {
        this.target = target_;
    }

    public Scope getScope() {
        return this.scope;
    }

    public void setScope(Scope scope_) {
        this.scope = scope_;
    }

    public static enum Scope {
        EVERYTHING,
        ALL_ITEMS,
        ONE_STACK,
        ONE_ITEM;

    }

    public static enum Target {
        UP,
        DOWN,
        HOTBAR_SLOT,
        UNSPECIFIED;

    }

    public static enum Action {
        MOVE,
        DROP;

    }
}

