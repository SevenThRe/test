/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.Yaml;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.Configuration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.InvalidConfigurationException;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.FileConfiguration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConstructor;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlRepresenter;
import eos.moe.ancientdream.yaml.snakeyaml.error.YAMLException;
import eos.moe.ancientdream.yaml.snakeyaml.representer.Representer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang3.Validate;

public class YamlConfiguration
extends FileConfiguration {
    protected static final String COMMENT_PREFIX = "# ";
    protected static final String BLANK_CONFIG = "{}\n";
    private final DumperOptions yamlOptions = new DumperOptions();
    private final Representer yamlRepresenter = new YamlRepresenter();
    private final Yaml yaml = new Yaml(new YamlConstructor(), this.yamlRepresenter, this.yamlOptions);

    @Override
    public String saveToString() {
        this.yamlOptions.setIndent(this.options().indent());
        this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yamlOptions.setAllowUnicode(SYSTEM_UTF);
        this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        String header = this.buildHeader();
        String dump = this.yaml.dump(this.getValues(false));
        if (dump.equals(BLANK_CONFIG)) {
            dump = "";
        }
        return header + dump;
    }

    @Override
    public void loadFromString(String contents) throws InvalidConfigurationException {
        Map input;
        Validate.notNull((Object)contents, (String)"Contents cannot be null", (Object[])new Object[0]);
        try {
            input = (Map)this.yaml.load(contents);
        }
        catch (YAMLException e) {
            throw new InvalidConfigurationException(e);
        }
        catch (ClassCastException e) {
            throw new InvalidConfigurationException("Top level is not a Map.");
        }
        String header = this.parseHeader(contents);
        if (header.length() > 0) {
            this.options().header(header);
        }
        if (input != null) {
            this.convertMapsToSections(input, this);
        }
    }

    protected void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
        for (Map.Entry<?, ?> entry : input.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof Map) {
                this.convertMapsToSections((Map)value, section.createSection(key));
                continue;
            }
            section.set(key, value);
        }
    }

    protected String parseHeader(String input) {
        String[] lines = input.split("\r?\n", -1);
        StringBuilder result = new StringBuilder();
        boolean readingHeader = true;
        boolean foundHeader = false;
        for (int i = 0; i < lines.length && readingHeader; ++i) {
            String line = lines[i];
            if (line.startsWith(COMMENT_PREFIX)) {
                if (i > 0) {
                    result.append("\n");
                }
                if (line.length() > COMMENT_PREFIX.length()) {
                    result.append(line.substring(COMMENT_PREFIX.length()));
                }
                foundHeader = true;
                continue;
            }
            if (foundHeader && line.length() == 0) {
                result.append("\n");
                continue;
            }
            if (!foundHeader) continue;
            readingHeader = false;
        }
        return result.toString();
    }

    @Override
    protected String buildHeader() {
        FileConfiguration filedefaults;
        String defaultsHeader;
        Configuration def;
        String header = this.options().header();
        if (this.options().copyHeader() && (def = this.getDefaults()) != null && def instanceof FileConfiguration && (defaultsHeader = (filedefaults = (FileConfiguration)def).buildHeader()) != null && defaultsHeader.length() > 0) {
            return defaultsHeader;
        }
        if (header == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] lines = header.split("\r?\n", -1);
        boolean startedHeader = false;
        for (int i = lines.length - 1; i >= 0; --i) {
            builder.insert(0, "\n");
            if (!startedHeader && lines[i].length() == 0) continue;
            builder.insert(0, lines[i]);
            builder.insert(0, COMMENT_PREFIX);
            startedHeader = true;
        }
        return builder.toString();
    }

    @Override
    public YamlConfigurationOptions options() {
        if (this.options == null) {
            this.options = new YamlConfigurationOptions(this);
        }
        return (YamlConfigurationOptions)this.options;
    }

    public static YamlConfiguration loadConfiguration(File file) {
        Validate.notNull((Object)file, (String)"File cannot be null", (Object[])new Object[0]);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        }
        catch (FileNotFoundException fileNotFoundException) {
        }
        catch (InvalidConfigurationException | IOException ex) {
            YamlConfiguration.log(Level.SEVERE, "Cannot load " + file, ex);
        }
        return config;
    }

    @Deprecated
    public static YamlConfiguration loadConfiguration(InputStream stream) {
        Validate.notNull((Object)stream, (String)"Stream cannot be null", (Object[])new Object[0]);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(stream);
        }
        catch (IOException ex) {
            YamlConfiguration.log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        catch (InvalidConfigurationException ex) {
            YamlConfiguration.log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        return config;
    }

    public static YamlConfiguration loadConfiguration(Reader reader) {
        Validate.notNull((Object)reader, (String)"Stream cannot be null", (Object[])new Object[0]);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(reader);
        }
        catch (IOException ex) {
            YamlConfiguration.log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        catch (InvalidConfigurationException ex) {
            YamlConfiguration.log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        return config;
    }

    @Override
    public boolean isColor(String path) {
        return false;
    }

    public static void log(Level level, String str, Exception ex) {
        ex.printStackTrace();
    }
}

