/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.JsonToNBT
 *  net.minecraft.nbt.NBTException
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.MinecraftForge
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksItemTreeCategory;
import invtweaks.InvTweaksItemTreeItem;
import invtweaks.api.IItemTreeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class InvTweaksItemTreeLoader
extends DefaultHandler {
    private static final String ATTR_ID = "id";
    private static final String ATTR_DAMAGE = "damage";
    private static final String ATTR_RANGE_DMIN = "dmin";
    private static final String ATTR_RANGE_DMAX = "dmax";
    private static final String ATTR_OREDICT_NAME = "oreDictName";
    private static final String ATTR_DATA = "data";
    private static final String ATTR_TREE_VERSION = "treeVersion";
    private static final List<IItemTreeListener> onLoadListeners = new ArrayList<IItemTreeListener>();
    private static InvTweaksItemTree tree;
    @Nullable
    private static String treeVersion;
    private static int itemOrder;
    private static LinkedList<String> categoryStack;
    private static boolean treeLoaded;

    private static void init() {
        treeVersion = null;
        tree = new InvTweaksItemTree();
        itemOrder = 0;
        categoryStack = new LinkedList();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static synchronized InvTweaksItemTree load(@NotNull File file) throws Exception {
        InvTweaksItemTreeLoader.init();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse(file, (DefaultHandler)new InvTweaksItemTreeLoader());
        List<IItemTreeListener> list = onLoadListeners;
        synchronized (list) {
            treeLoaded = true;
            for (IItemTreeListener onLoadListener : onLoadListeners) {
                onLoadListener.onTreeLoaded(tree);
            }
        }
        MinecraftForge.EVENT_BUS.register((Object)tree);
        return tree;
    }

    public static synchronized boolean isValidVersion(@NotNull File file) throws Exception {
        InvTweaksItemTreeLoader.init();
        if (file.exists()) {
            treeVersion = null;
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            VersionLoader loader = new VersionLoader();
            parser.parse(file, (DefaultHandler)loader);
            return "1.12.2".equals(loader.version);
        }
        return false;
    }

    public static synchronized void addOnLoadListener(@NotNull IItemTreeListener listener) {
        onLoadListeners.add(listener);
        if (treeLoaded) {
            listener.onTreeLoaded(tree);
        }
    }

    public static synchronized boolean removeOnLoadListener(IItemTreeListener listener) {
        return onLoadListeners.remove(listener);
    }

    @Override
    public synchronized void startElement(String uri, String localName, String name, @NotNull Attributes attributes) throws SAXException {
        String rangeDMinAttr = attributes.getValue(ATTR_RANGE_DMIN);
        String newTreeVersion = attributes.getValue(ATTR_TREE_VERSION);
        String oreDictNameAttr = attributes.getValue(ATTR_OREDICT_NAME);
        if (attributes.getLength() == 0 || treeVersion == null || rangeDMinAttr != null) {
            if (treeVersion == null) {
                treeVersion = newTreeVersion;
            }
            if (categoryStack.isEmpty()) {
                tree.setRootCategory(new InvTweaksItemTreeCategory(name));
            } else {
                tree.addCategory(categoryStack.getLast(), new InvTweaksItemTreeCategory(name));
            }
            if (rangeDMinAttr != null) {
                String id = attributes.getValue(ATTR_ID);
                int rangeDMin = Integer.parseInt(rangeDMinAttr);
                int rangeDMax = Integer.parseInt(attributes.getValue(ATTR_RANGE_DMAX));
                for (int damage = rangeDMin; damage <= rangeDMax; ++damage) {
                    tree.addItem(name, new InvTweaksItemTreeItem(name + id + "-" + damage, id, damage, null, itemOrder++));
                }
            }
            categoryStack.add(name);
        } else if (attributes.getValue(ATTR_ID) != null) {
            String id = attributes.getValue(ATTR_ID);
            int damage = Short.MAX_VALUE;
            String extraDataAttr = attributes.getValue(ATTR_DATA);
            NBTTagCompound extraData = null;
            if (extraDataAttr != null) {
                try {
                    extraData = JsonToNBT.func_180713_a((String)extraDataAttr);
                }
                catch (NBTException e) {
                    throw new RuntimeException("Data attribute failed for tree entry '" + name + "'", e);
                }
            }
            if (attributes.getValue(ATTR_DAMAGE) != null) {
                damage = Integer.parseInt(attributes.getValue(ATTR_DAMAGE));
            }
            tree.addItem(categoryStack.getLast(), new InvTweaksItemTreeItem(name, id, damage, extraData, itemOrder++));
        } else if (oreDictNameAttr != null) {
            tree.registerOre(categoryStack.getLast(), name, oreDictNameAttr, itemOrder++);
        }
    }

    @Override
    public synchronized void endElement(String uri, String localName, @NotNull String name) throws SAXException {
        if (!categoryStack.isEmpty() && name.equals(categoryStack.getLast())) {
            categoryStack.removeLast();
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        InvTweaks.log.warn("Tree XML Warning: ", (Throwable)e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        InvTweaks.log.error("Tree XML Error: ", (Throwable)e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        InvTweaks.log.fatal("Tree XML Fatal Error: ", (Throwable)e);
    }

    static {
        treeLoaded = false;
    }

    private static class VersionLoader
    extends DefaultHandler {
        @Nullable
        String version;

        private VersionLoader() {
        }

        @Override
        public synchronized void startElement(String uri, String localName, String name, @NotNull Attributes attributes) throws SAXException {
            if (this.version == null) {
                this.version = attributes.getValue(InvTweaksItemTreeLoader.ATTR_TREE_VERSION);
            }
        }
    }
}

