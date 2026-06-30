/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.component;

import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.client.ui.component.TextBox;
import journeymap.client.ui.component.TextBoxButton;
import journeymap.common.properties.config.CustomField;

public class TextFieldButton
extends TextBoxButton
implements IConfigFieldHolder<CustomField> {
    protected final CustomField field;

    public TextFieldButton(CustomField field) {
        super(field.get().toString());
        this.field = field;
        if (field.isNumber()) {
            this.textBox = new TextBox(this.displayString, this.fontRenderer, this.width, this.height, field.isNumber(), field.allowNeg());
            this.textBox.setClamp(field.getMinValue(), field.getMaxValue());
        } else {
            this.textBox = new TextBox(this.displayString, this.fontRenderer, this.width, this.height);
        }
        this.textBox.setY(this.textBox.getY() - 1);
        this.textBox.setHeight(this.textBox.getHeight() - 4);
    }

    public void setValue(Object value) {
        if (!this.field.get().equals(value)) {
            this.field.set(value);
            this.field.save();
        }
        this.textBox.setText(value);
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        boolean press = this.textBox.textboxKeyTyped(typedChar, keyCode);
        this.setValue(this.textBox.getText());
        return press;
    }

    @Override
    public CustomField getConfigField() {
        return this.field;
    }

    @Override
    public void refresh() {
        this.setValue(this.field.get());
    }
}

