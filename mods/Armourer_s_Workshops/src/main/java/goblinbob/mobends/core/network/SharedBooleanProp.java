/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.config.Configuration
 */
package goblinbob.mobends.core.network;

import goblinbob.mobends.core.network.SharedProperty;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public class SharedBooleanProp
extends SharedProperty<Boolean> {
    public SharedBooleanProp(String key, Boolean value, String description) {
        super(key, value, description);
    }

    @Override
    public Boolean getValue() {
        return (Boolean)super.getValue();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.func_74757_a(this.key, ((Boolean)this.value).booleanValue());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.value = tag.func_74767_n(this.key);
    }

    @Override
    public void updateWithConfig(Configuration configuration, String category) {
        this.value = configuration.get(category, this.key, ((Boolean)this.defaultValue).booleanValue(), this.description).getBoolean();
    }
}

