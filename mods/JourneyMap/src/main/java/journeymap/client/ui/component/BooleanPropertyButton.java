/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.component;

import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.client.ui.component.OnOffButton;
import journeymap.common.properties.config.BooleanField;

public class BooleanPropertyButton
extends OnOffButton
implements IConfigFieldHolder<BooleanField> {
    final BooleanField booleanField;

    public BooleanPropertyButton(String labelOn, String labelOff, BooleanField field) {
        super(labelOn, labelOff, field != null && field.get() != false);
        this.booleanField = field;
    }

    public BooleanField getField() {
        return this.booleanField;
    }

    @Override
    public void toggle() {
        if (this.isEnabled()) {
            if (this.booleanField != null) {
                this.setToggled(this.booleanField.toggleAndSave());
            } else {
                this.setToggled(this.toggled == false);
            }
        }
    }

    @Override
    public void refresh() {
        if (this.booleanField != null) {
            this.setToggled(this.booleanField.get());
        }
    }

    public void setValue(Boolean value) {
        if (this.booleanField == null) {
            this.toggled = value;
        } else {
            this.booleanField.set(value);
            this.booleanField.save();
        }
    }

    @Override
    public BooleanField getConfigField() {
        return this.booleanField;
    }
}

