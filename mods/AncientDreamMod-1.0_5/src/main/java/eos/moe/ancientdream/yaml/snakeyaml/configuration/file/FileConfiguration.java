/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.io.Files
 *  org.apache.commons.lang3.Validate
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.Configuration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.InvalidConfigurationException;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.MemoryConfiguration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.FileConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.commons.lang3.Validate;

public abstract class FileConfiguration
extends MemoryConfiguration {
    @Deprecated
    public static final boolean UTF8_OVERRIDE;
    @Deprecated
    public static final boolean UTF_BIG;
    @Deprecated
    public static final boolean SYSTEM_UTF;

    public FileConfiguration() {
    }

    public FileConfiguration(Configuration defaults) {
        super(defaults);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void save(File file) throws IOException {
        Validate.notNull((Object)file, (String)"File cannot be null", (Object[])new Object[0]);
        Files.createParentDirs((File)file);
        String data = this.saveToString();
        try (OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(file), UTF8_OVERRIDE && !UTF_BIG ? Charsets.UTF_8 : Charset.defaultCharset());){
            writer.write(data);
        }
    }

    public void save(String file) throws IOException {
        Validate.notNull((Object)file, (String)"File cannot be null", (Object[])new Object[0]);
        this.save(new File(file));
    }

    public abstract String saveToString();

    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull((Object)file, (String)"File cannot be null", (Object[])new Object[0]);
        FileInputStream stream = new FileInputStream(file);
        this.load(new InputStreamReader((InputStream)stream, UTF8_OVERRIDE && !UTF_BIG ? Charsets.UTF_8 : Charset.defaultCharset()));
    }

    @Deprecated
    public void load(InputStream stream) throws IOException, InvalidConfigurationException {
        Validate.notNull((Object)stream, (String)"Stream cannot be null", (Object[])new Object[0]);
        this.load(new InputStreamReader(stream, UTF8_OVERRIDE ? Charsets.UTF_8 : Charset.defaultCharset()));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader input = reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);){
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        }
        this.loadFromString(builder.toString());
    }

    public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull((Object)file, (String)"File cannot be null", (Object[])new Object[0]);
        this.load(new File(file));
    }

    public abstract void loadFromString(String var1) throws InvalidConfigurationException;

    protected abstract String buildHeader();

    @Override
    public FileConfigurationOptions options() {
        if (this.options == null) {
            this.options = new FileConfigurationOptions(this);
        }
        return (FileConfigurationOptions)this.options;
    }

    static {
        byte[] testBytes = Base64Coder.decode("ICEiIyQlJicoKSorLC0uLzAxMjM0NTY3ODk6Ozw9Pj9AQUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVpbXF1eX2BhYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ent8fX4NCg==");
        String testString = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\r\n";
        Charset defaultCharset = Charset.defaultCharset();
        String resultString = new String(testBytes, defaultCharset);
        boolean trueUTF = defaultCharset.name().contains("UTF");
        UTF8_OVERRIDE = !" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\r\n".equals(resultString) || defaultCharset.equals(Charset.forName("US-ASCII"));
        SYSTEM_UTF = trueUTF || UTF8_OVERRIDE;
        UTF_BIG = trueUTF && UTF8_OVERRIDE;
    }
}

