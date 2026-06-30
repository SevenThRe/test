/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.properties;

import journeymap.client.properties.InGameMapProperties;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.BooleanField;
import net.minecraftforge.fml.client.FMLClientHandler;

public class FullMapProperties
extends InGameMapProperties {
    public final BooleanField showKeys = new BooleanField(Category.Inherit, "jm.common.show_keys", true);

    @Override
    public void postLoad(boolean isNew) {
        super.postLoad(isNew);
        if (isNew && FMLClientHandler.instance().getClient() != null && FMLClientHandler.instance().getClient().field_71466_p.func_82883_a()) {
            this.fontScale.set(2);
        }
    }

    @Override
    public String getName() {
        return "fullmap";
    }
}

