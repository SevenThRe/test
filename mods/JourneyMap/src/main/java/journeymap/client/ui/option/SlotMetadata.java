/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.option;

import java.util.ArrayList;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.properties.config.ConfigField;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

public class SlotMetadata<T>
implements Comparable<SlotMetadata> {
    protected final Button button;
    protected final String range;
    protected final T defaultValue;
    protected final ValueType valueType;
    protected String name;
    protected String tooltip;
    protected boolean advanced;
    protected String[] tooltipLines;
    protected List valueList;
    protected boolean master;
    protected int order;

    public SlotMetadata(Button button) {
        this(button, false);
    }

    public SlotMetadata(Button button, int order) {
        this(button, false);
        this.order = order;
    }

    public SlotMetadata(Button button, boolean advanced) {
        this(button, button.getDisplayString(), button.getUnformattedTooltip(), null, null, advanced);
    }

    public SlotMetadata(Button button, String name, String tooltip, boolean advanced) {
        this(button, name, tooltip, null, null, advanced);
    }

    public SlotMetadata(Button button, String name, String tooltip) {
        this(button, name, tooltip, null, null, false);
    }

    public SlotMetadata(Button button, String name, String tooltip, int order) {
        this(button, name, tooltip, null, null, false);
        this.order = order;
    }

    public SlotMetadata(Button button, String name, String tooltip, String range, T defaultValue, boolean advanced) {
        this.button = button;
        this.name = name;
        this.tooltip = tooltip;
        this.range = range;
        this.defaultValue = defaultValue;
        this.advanced = advanced;
        this.valueType = defaultValue == null && range == null && !advanced ? ValueType.Toolbar : (defaultValue instanceof Boolean ? ValueType.Boolean : (defaultValue instanceof Integer ? ValueType.Integer : ValueType.Set));
    }

    public boolean isMasterPropertyForCategory() {
        return this.master;
    }

    public void setMasterPropertyForCategory(boolean master) {
        this.master = master;
    }

    public Button getButton() {
        return this.button;
    }

    public String getName() {
        return this.name;
    }

    public String getRange() {
        return this.range;
    }

    public boolean isAdvanced() {
        return this.advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public String[] getTooltipLines() {
        return this.tooltipLines;
    }

    public boolean isMaster() {
        return this.master;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public boolean isToolbar() {
        return this.valueType == ValueType.Toolbar;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List getValueList() {
        return this.valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    public void updateFromButton() {
        if (this.button != null) {
            this.name = this.button.getDisplayString();
            this.tooltip = this.button.getUnformattedTooltip();
            this.tooltipLines = null;
        }
    }

    public String[] getTooltip() {
        String bidiColor;
        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
        String string = bidiColor = fontRenderer.func_78260_a() ? "%2$s%1$s" : "%1$s%2$s";
        if (this.tooltipLines == null) {
            ArrayList<TextComponentTranslation> lines = new ArrayList<TextComponentTranslation>(4);
            if (this.tooltip != null || this.range != null || this.defaultValue != null || this.advanced) {
                TextFormatting nameColor = this.isToolbar() ? TextFormatting.GREEN : (this.advanced ? TextFormatting.RED : TextFormatting.AQUA);
                lines.add(new TextComponentTranslation("jm.config.tooltip_format", new Object[]{nameColor, this.name}));
                if (this.tooltip != null) {
                    lines.addAll(this.getWordWrappedLines(TextFormatting.YELLOW.toString(), this.tooltip));
                }
                if (this.button != null && this.button instanceof IntSliderButton) {
                    lines.addAll(this.getWordWrappedLines(TextFormatting.GRAY.toString() + TextFormatting.ITALIC.toString(), Constants.getString("jm.config.control_arrowkeys")));
                }
                if (this.range != null) {
                    lines.add(new TextComponentTranslation("jm.config.tooltip_format", new Object[]{TextFormatting.WHITE, this.range}));
                }
            }
            if (!lines.isEmpty()) {
                ArrayList<String> stringLines = new ArrayList<String>();
                for (TextComponentTranslation line : lines) {
                    stringLines.add(line.func_150260_c().trim());
                }
                this.tooltipLines = stringLines.toArray(new String[stringLines.size()]);
            }
        }
        return this.tooltipLines;
    }

    protected List<TextComponentTranslation> getWordWrappedLines(String color, String original) {
        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
        ArrayList<TextComponentTranslation> list = new ArrayList<TextComponentTranslation>();
        int max = fontRenderer.func_78260_a() ? 170 : 250;
        for (Object line : fontRenderer.func_78271_c(original, max)) {
            list.add(new TextComponentTranslation("jm.config.tooltip_format", new Object[]{color, line}));
        }
        return list;
    }

    public void resetToDefaultValue() {
        if (this.button != null) {
            if (this.button instanceof IConfigFieldHolder) {
                try {
                    Object configField = ((IConfigFieldHolder)((Object)this.button)).getConfigField();
                    if (configField != null) {
                        ((ConfigField)configField).setToDefault();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.button.refresh();
        }
    }

    public boolean hasConfigField() {
        return this.button != null && this.button instanceof IConfigFieldHolder && ((IConfigFieldHolder)((Object)this.button)).getConfigField() != null;
    }

    public PropertiesBase getProperties() {
        if (this.hasConfigField()) {
            return ((ConfigField)((IConfigFieldHolder)((Object)this.button)).getConfigField()).getOwner();
        }
        return null;
    }

    @Override
    public int compareTo(SlotMetadata other) {
        int result = Boolean.compare(this.isToolbar(), other.isToolbar());
        if (result == 0) {
            result = Integer.compare(this.order, other.order);
        }
        if (result == 0) {
            result = Boolean.compare(other.master, this.master);
        }
        if (result == 0) {
            result = this.valueType.compareTo(other.valueType);
        }
        if (result == 0) {
            result = this.name.compareTo(other.name);
        }
        return result;
    }

    public static enum ValueType {
        Boolean,
        Set,
        Integer,
        Toolbar;

    }
}

