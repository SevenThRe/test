/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Loader
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.mod;

import java.util.HashMap;
import java.util.Map;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.mod.IBlockSpritesProxy;
import journeymap.client.mod.IModBlockHandler;
import journeymap.client.mod.impl.Bibliocraft;
import journeymap.client.mod.impl.BiomesOPlenty;
import journeymap.client.mod.impl.Streams;
import journeymap.client.mod.impl.TerraFirmaCraft;
import journeymap.client.mod.vanilla.VanillaBlockColorProxy;
import journeymap.client.mod.vanilla.VanillaBlockHandler;
import journeymap.client.mod.vanilla.VanillaBlockSpriteProxy;
import journeymap.client.model.BlockMD;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Logger;

public enum ModBlockDelegate {
    INSTANCE;

    private final Logger logger = Journeymap.getLogger();
    private final HashMap<String, Class<? extends IModBlockHandler>> handlerClasses = new HashMap();
    private final HashMap<String, IModBlockHandler> handlers = new HashMap(10);
    private VanillaBlockHandler commonBlockHandler;
    private IBlockColorProxy defaultBlockColorProxy;
    private IBlockSpritesProxy defaultBlockSpritesProxy;

    private ModBlockDelegate() {
        this.reset();
    }

    public void reset() {
        this.commonBlockHandler = new VanillaBlockHandler();
        this.defaultBlockColorProxy = new VanillaBlockColorProxy();
        this.defaultBlockSpritesProxy = new VanillaBlockSpriteProxy();
        this.handlerClasses.clear();
        this.handlerClasses.put("BiblioCraft", Bibliocraft.class);
        this.handlerClasses.put("BiomesOPlenty", BiomesOPlenty.class);
        this.handlerClasses.put("terrafirmacraft", TerraFirmaCraft.class);
        this.handlerClasses.put("tfc2", TerraFirmaCraft.class);
        this.handlerClasses.put("streams", Streams.class);
        for (Map.Entry<String, Class<? extends IModBlockHandler>> entry : this.handlerClasses.entrySet()) {
            String modId = entry.getKey();
            Class<? extends IModBlockHandler> handlerClass = entry.getValue();
            if (!Loader.isModLoaded((String)modId) && !Loader.isModLoaded((String)modId.toLowerCase())) continue;
            modId = modId.toLowerCase();
            try {
                this.handlers.put(modId, handlerClass.newInstance());
                this.logger.info("Custom modded block handling enabled for " + modId);
            }
            catch (Exception e) {
                this.logger.error(String.format("Couldn't initialize modded block handler for %s: %s", modId, LogFormatter.toPartialString(e)));
            }
        }
    }

    public void initialize(BlockMD blockMD) {
        if (this.commonBlockHandler == null) {
            this.reset();
        }
        blockMD.setBlockSpritesProxy(this.defaultBlockSpritesProxy);
        blockMD.setBlockColorProxy(this.defaultBlockColorProxy);
        this.initialize(this.commonBlockHandler, blockMD);
        IModBlockHandler modBlockHandler = this.handlers.get(blockMD.getBlockDomain().toLowerCase());
        if (modBlockHandler != null) {
            modBlockHandler.initialize(blockMD);
        }
        this.commonBlockHandler.postInitialize(blockMD);
    }

    private void initialize(IModBlockHandler handler, BlockMD blockMD) {
        try {
            handler.initialize(blockMD);
        }
        catch (Throwable t) {
            this.logger.error(String.format("Couldn't initialize IModBlockHandler '%s' for %s: %s", handler.getClass(), blockMD, LogFormatter.toPartialString(t)));
        }
    }

    public IModBlockHandler getCommonBlockHandler() {
        return this.commonBlockHandler;
    }

    public IBlockSpritesProxy getDefaultBlockSpritesProxy() {
        return this.defaultBlockSpritesProxy;
    }

    public IBlockColorProxy getDefaultBlockColorProxy() {
        return this.defaultBlockColorProxy;
    }
}

