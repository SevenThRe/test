/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.io.Files
 */
package journeymap.client.ui.option;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.Constants;
import journeymap.client.properties.ClientCategory;
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.component.TextFieldButton;
import journeymap.client.ui.option.ButtonListSlot;
import journeymap.client.ui.option.CategorySlot;
import journeymap.client.ui.option.LocationFormat;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.common.Journeymap;
import journeymap.common.properties.Category;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.ConfigField;
import journeymap.common.properties.config.CustomField;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import journeymap.common.properties.config.StringField;

public class OptionSlotFactory {
    protected static final Charset UTF8 = Charset.forName("UTF-8");
    protected static BufferedWriter docWriter;
    protected static File docFile;
    protected static boolean generateDocs;

    public static List<CategorySlot> getSlots(Map<Category, List<SlotMetadata>> toolbars) {
        CategorySlot categorySlot;
        HashMap<Category, List<SlotMetadata>> mergedMap = new HashMap<Category, List<SlotMetadata>>();
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.MiniMap1, Journeymap.getClient().getMiniMapProperties1());
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.MiniMap2, Journeymap.getClient().getMiniMapProperties2());
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.FullMap, Journeymap.getClient().getFullMapProperties());
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.WebMap, Journeymap.getClient().getWebMapProperties());
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.Waypoint, Journeymap.getClient().getWaypointProperties());
        OptionSlotFactory.addSlots(mergedMap, ClientCategory.Advanced, Journeymap.getClient().getCoreProperties());
        ArrayList<CategorySlot> categories = new ArrayList<CategorySlot>();
        for (Map.Entry<Category, List<SlotMetadata>> entry : mergedMap.entrySet()) {
            Category category = entry.getKey();
            categorySlot = new CategorySlot(category);
            for (SlotMetadata slotMetadata : entry.getValue()) {
                categorySlot.add(new ButtonListSlot(categorySlot).add(slotMetadata));
            }
            if (toolbars.containsKey(category)) {
                ButtonListSlot toolbarSlot = new ButtonListSlot(categorySlot);
                for (SlotMetadata toolbar : toolbars.get(category)) {
                    toolbarSlot.add(toolbar);
                }
                categorySlot.add(toolbarSlot);
            }
            categories.add(categorySlot);
        }
        Collections.sort(categories);
        int count = 0;
        for (CategorySlot categorySlot2 : categories) {
            count += categorySlot2.size();
        }
        if (generateDocs) {
            OptionSlotFactory.ensureDocFile();
            for (ScrollListPane.ISlot iSlot : categories) {
                categorySlot = (CategorySlot)iSlot;
                if (categorySlot.category == ClientCategory.MiniMap2) continue;
                OptionSlotFactory.doc(categorySlot);
                OptionSlotFactory.docTable(true);
                categorySlot.sort();
                for (SlotMetadata slotMetadata : categorySlot.getAllChildMetadata()) {
                    OptionSlotFactory.doc(slotMetadata, categorySlot.getCategory() == ClientCategory.Advanced);
                }
                OptionSlotFactory.docTable(false);
            }
            OptionSlotFactory.endDoc();
        }
        return categories;
    }

    protected static void addSlots(HashMap<Category, List<SlotMetadata>> mergedMap, Category inheritedCategory, PropertiesBase properties) {
        Class<?> propertiesClass = properties.getClass();
        HashMap<Category, List<SlotMetadata>> slots = OptionSlotFactory.buildSlots(null, inheritedCategory, propertiesClass, properties);
        for (Map.Entry<Category, List<SlotMetadata>> entry : slots.entrySet()) {
            Category category = entry.getKey();
            if (category == Category.Inherit) {
                category = inheritedCategory;
            }
            List<Object> slotMetadataList = null;
            if (mergedMap.containsKey(category)) {
                slotMetadataList = mergedMap.get(category);
            } else {
                slotMetadataList = new ArrayList();
                mergedMap.put(category, slotMetadataList);
            }
            slotMetadataList.addAll((Collection)entry.getValue());
        }
    }

    protected static HashMap<Category, List<SlotMetadata>> buildSlots(HashMap<Category, List<SlotMetadata>> map, Category inheritedCategory, Class<? extends PropertiesBase> propertiesClass, PropertiesBase properties) {
        if (map == null) {
            map = new HashMap();
        }
        for (ConfigField<?> configField : properties.getConfigFields().values()) {
            if (configField.getCategory() == Category.Hidden) continue;
            SlotMetadata slotMetadata = null;
            if (configField instanceof BooleanField) {
                slotMetadata = OptionSlotFactory.getBooleanSlotMetadata((BooleanField)configField);
            } else if (configField instanceof IntegerField) {
                slotMetadata = OptionSlotFactory.getIntegerSlotMetadata((IntegerField)configField);
            } else if (configField instanceof StringField) {
                slotMetadata = OptionSlotFactory.getStringSlotMetadata((StringField)configField);
            } else if (configField instanceof EnumField) {
                slotMetadata = OptionSlotFactory.getEnumSlotMetadata((EnumField)configField);
            } else if (configField instanceof CustomField) {
                slotMetadata = OptionSlotFactory.getTextSlotMetadata((CustomField)configField);
            }
            if (slotMetadata != null) {
                List<SlotMetadata<Object>> list;
                slotMetadata.setOrder(configField.getSortOrder());
                Category category = configField.getCategory();
                if (category == Category.Inherit) {
                    category = inheritedCategory;
                }
                if ((list = map.get(category)) == null) {
                    list = new ArrayList<SlotMetadata<Object>>();
                    map.put(category, list);
                }
                list.add(slotMetadata);
                continue;
            }
            Journeymap.getLogger().warn(String.format("Unable to create config gui for %s in %s", properties.getClass().getSimpleName(), configField));
        }
        return map;
    }

    static String getTooltip(ConfigField configField) {
        String tooltip;
        String tooltipKey = configField.getKey() + ".tooltip";
        if (tooltipKey.equals(tooltip = Constants.getString(tooltipKey))) {
            tooltip = null;
        }
        return tooltip;
    }

    static SlotMetadata<Boolean> getBooleanSlotMetadata(BooleanField field) {
        String name = Constants.getString(field.getKey());
        String tooltip = OptionSlotFactory.getTooltip(field);
        String defaultTip = Constants.getString("jm.config.default", field.getDefaultValue());
        boolean advanced = field.getCategory() == ClientCategory.Advanced;
        CheckBox button = new CheckBox(name, field);
        SlotMetadata<Boolean> slotMetadata = new SlotMetadata<Boolean>(button, name, tooltip, defaultTip, field.getDefaultValue(), advanced);
        slotMetadata.setMasterPropertyForCategory(field.isCategoryMaster());
        if (field.isCategoryMaster()) {
            button.setLabelColors(65535, null, null);
        }
        return slotMetadata;
    }

    static SlotMetadata<Integer> getIntegerSlotMetadata(IntegerField field) {
        String name = Constants.getString(field.getKey());
        String tooltip = OptionSlotFactory.getTooltip(field);
        String defaultTip = Constants.getString("jm.config.default_numeric", field.getMinValue(), field.getMaxValue(), field.getDefaultValue());
        boolean advanced = field.getCategory() == ClientCategory.Advanced;
        IntSliderButton button = new IntSliderButton(field, name + " : ", "", field.getMinValue(), field.getMaxValue(), true);
        button.setDefaultStyle(false);
        button.setDrawBackground(false);
        SlotMetadata<Integer> slotMetadata = new SlotMetadata<Integer>(button, name, tooltip, defaultTip, field.getDefaultValue(), advanced);
        return slotMetadata;
    }

    static SlotMetadata<String> getStringSlotMetadata(StringField field) {
        try {
            String name = Constants.getString(field.getKey());
            String tooltip = OptionSlotFactory.getTooltip(field);
            boolean advanced = field.getCategory() == ClientCategory.Advanced;
            ListPropertyButton button = null;
            String defaultTip = null;
            if (LocationFormat.IdProvider.class.isAssignableFrom(field.getValuesProviderClass())) {
                button = new LocationFormat.Button(field);
                defaultTip = Constants.getString("jm.config.default", ((LocationFormat.Button)button).getLabel(field.getDefaultValue()));
            } else {
                button = new ListPropertyButton<String>(field.getValidValues(), name, field);
                defaultTip = Constants.getString("jm.config.default", field.getDefaultValue());
            }
            button.setDefaultStyle(false);
            button.setDrawBackground(false);
            SlotMetadata<String> slotMetadata = new SlotMetadata<String>(button, name, tooltip, defaultTip, field.getDefaultValue(), advanced);
            slotMetadata.setValueList(field.getValidValues());
            return slotMetadata;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SlotMetadata getTextSlotMetadata(CustomField field) {
        try {
            String name = Constants.getString(field.getKey());
            String tooltip = OptionSlotFactory.getTooltip(field);
            boolean advanced = field.getCategory() == ClientCategory.Advanced;
            TextFieldButton button = null;
            String defaultTip = null;
            button = new TextFieldButton(field);
            defaultTip = Constants.getString("jm.config.default", field.getDefaultValue());
            SlotMetadata<Object> slotMetadata = new SlotMetadata<Object>(button, name, tooltip, defaultTip, field.getDefaultValue(), advanced);
            return slotMetadata;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static SlotMetadata<Enum> getEnumSlotMetadata(EnumField field) {
        try {
            String name = Constants.getString(field.getKey());
            String tooltip = OptionSlotFactory.getTooltip(field);
            boolean advanced = field.getCategory() == ClientCategory.Advanced;
            ListPropertyButton button = new ListPropertyButton(field.getValidValues(), name, field);
            String defaultTip = Constants.getString("jm.config.default", field.getDefaultValue());
            button.setDefaultStyle(false);
            button.setDrawBackground(false);
            SlotMetadata<Object> slotMetadata = new SlotMetadata<Object>(button, name, tooltip, defaultTip, field.getDefaultValue(), advanced);
            slotMetadata.setValueList(Arrays.asList(field.getValidValues()));
            return slotMetadata;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void ensureDocFile() {
        if (docFile == null) {
            docFile = new File(Constants.JOURNEYMAP_DIR, "journeymap-options-wiki.txt");
            try {
                if (docFile.exists()) {
                    docFile.delete();
                }
                Files.createParentDirs((File)docFile);
                docWriter = Files.newWriter((File)docFile, (Charset)UTF8);
                docWriter.append(String.format("<!-- Generated %s -->", new Date()));
                docWriter.newLine();
                docWriter.append("=== Overview ===");
                docWriter.newLine();
                docWriter.append("{{version|5.0.0|page}}");
                docWriter.newLine();
                docWriter.append("This page lists all of the available options which can be configured in-game using the JourneyMap [[Options Manager]].");
                docWriter.append("(Note: All of this information can also be obtained from the tooltips within the [[Options Manager]] itself.) <br clear/> <br clear/>");
                docWriter.newLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void doc(CategorySlot categorySlot) {
        try {
            docWriter.newLine();
            docWriter.append(String.format("==%s==", categorySlot.getCategory().getName().replace("Preset 1", "Preset (1 and 2)")));
            docWriter.newLine();
            docWriter.append(String.format("''%s''", categorySlot.getMetadata().iterator().next().tooltip.replace("Preset 1", "Preset (1 and 2)")));
            docWriter.newLine();
            docWriter.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void docTable(boolean start) {
        try {
            if (start) {
                docWriter.append("{| class=\"wikitable\" style=\"cellpadding=\"4\"");
                docWriter.newLine();
                docWriter.append("! scope=\"col\" | Option");
                docWriter.newLine();
                docWriter.append("! scope=\"col\" | Purpose");
                docWriter.newLine();
                docWriter.append("! scope=\"col\" | Range / Default Value");
                docWriter.newLine();
                docWriter.append("|-");
                docWriter.newLine();
            } else {
                docWriter.append("|}");
                docWriter.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void doc(SlotMetadata slotMetadata, boolean advanced) {
        try {
            String color = advanced ? "red" : "black";
            docWriter.append(String.format("| style=\"text-align:right; white-space: nowrap; font-weight:bold; padding:6px; color:%s\" | %s", color, slotMetadata.getName()));
            docWriter.newLine();
            docWriter.append(String.format("| %s ", slotMetadata.tooltip));
            if (slotMetadata.getValueList() != null) {
                docWriter.append(String.format("<br/><em>Choices available:</em> <code>%s</code>", Joiner.on((String)", ").join((Iterable)slotMetadata.getValueList())));
            }
            docWriter.newLine();
            docWriter.append(String.format("| <code>%s</code>", slotMetadata.range.replace("[", "").replace("]", "").trim()));
            docWriter.newLine();
            docWriter.append("|-");
            docWriter.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void endDoc() {
        try {
            docFile = null;
            docWriter.flush();
            docWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        generateDocs = false;
    }
}

