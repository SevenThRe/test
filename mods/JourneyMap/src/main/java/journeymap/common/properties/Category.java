/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 */
package journeymap.common.properties;

import com.google.common.base.Objects;

public class Category
implements Comparable<Category> {
    public static final Category Inherit = new Category("Inherit", 0, "", "");
    public static final Category Hidden = new Category("Hidden", 0, "", "");
    String name;
    String label;
    String tooltip;
    int order;

    public Category(String name, int order, String label, String tooltip) {
        this.name = name;
        this.order = order;
        this.label = label;
        this.tooltip = tooltip;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label == null ? this.getName() : this.label;
    }

    public String getTooltip() {
        return this.tooltip == null ? this.getLabel() : this.tooltip;
    }

    public int getOrder() {
        return this.order;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category)o;
        return Objects.equal((Object)this.getName(), (Object)category.getName());
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.getName()});
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Category o) {
        int result = Integer.compare(this.order, o.order);
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }
}

