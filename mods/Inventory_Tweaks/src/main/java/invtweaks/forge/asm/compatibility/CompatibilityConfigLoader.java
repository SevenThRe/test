/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.forge.asm.compatibility;

import invtweaks.forge.asm.compatibility.ContainerInfo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CompatibilityConfigLoader
extends DefaultHandler {
    private Map<String, ContainerInfo> config;

    public CompatibilityConfigLoader(Map<String, ContainerInfo> compatibilityConfig) {
        this.config = compatibilityConfig;
    }

    @NotNull
    public static Map<String, ContainerInfo> load(@NotNull String filePath) throws Exception {
        HashMap<String, ContainerInfo> config = new HashMap<String, ContainerInfo>();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse(new File(filePath), (DefaultHandler)new CompatibilityConfigLoader(config));
        return config;
    }

    @Override
    public void startElement(String uri, String localName, String qName, @NotNull Attributes attributes) throws SAXException {
        if ("chest".equals(qName) || "inventory".equals(qName)) {
            ContainerInfo info = new ContainerInfo();
            String className = attributes.getValue("class");
            if (className == null) {
                return;
            }
            if ("chest".equals(qName)) {
                info.validChest = true;
                String rowSizeAttr = attributes.getValue("row_size");
                if (rowSizeAttr != null) {
                    info.rowSize = Short.parseShort(rowSizeAttr);
                }
                info.largeChest = Boolean.parseBoolean(attributes.getValue("large_chest"));
                info.showButtons = !Boolean.parseBoolean(attributes.getValue("disable_buttons"));
            } else if ("inventory".equals(qName)) {
                info.validInventory = true;
                info.showButtons = !Boolean.parseBoolean(attributes.getValue("disable_buttons"));
            }
            this.config.put(className, info);
        }
    }
}

