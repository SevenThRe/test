/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.beans.PropertyChangeListener;
import java.beans.beancontext.BeanContextSupport;
import java.util.Arrays;
import org.bytedeco.javacv.BaseChildSettings;

public class BaseSettings
extends BeanContextSupport
implements Comparable<BaseSettings> {
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcSupport.addPropertyChangeListener(listener);
        for (Object s2 : this.toArray()) {
            if (s2 instanceof BaseChildSettings) {
                ((BaseChildSettings)s2).addPropertyChangeListener(listener);
                continue;
            }
            if (!(s2 instanceof BaseSettings)) continue;
            ((BaseSettings)s2).addPropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcSupport.removePropertyChangeListener(listener);
        for (Object s2 : this.toArray()) {
            if (s2 instanceof BaseChildSettings) {
                ((BaseChildSettings)s2).removePropertyChangeListener(listener);
                continue;
            }
            if (!(s2 instanceof BaseSettings)) continue;
            ((BaseSettings)s2).addPropertyChangeListener(listener);
        }
    }

    @Override
    public int compareTo(BaseSettings o2) {
        return this.getName().compareTo(o2.getName());
    }

    protected String getName() {
        return "";
    }

    @Override
    public Object[] toArray() {
        Object[] a2 = super.toArray();
        Arrays.sort(a2);
        return a2;
    }

    @Override
    public Object[] toArray(Object[] a2) {
        a2 = super.toArray(a2);
        Arrays.sort(a2);
        return a2;
    }
}

