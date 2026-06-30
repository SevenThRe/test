/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.config.Configuration
 */
package goblinbob.mobends.core.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public abstract class SharedProperty<T> {
    protected final String key;
    protected final String description;
    protected final T defaultValue;
    protected T value;

    public SharedProperty(String key, T defaultValue, String description) {
        this.key = key;
        this.description = description;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public String getKey() {
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract void writeToNBT(NBTTagCompound var1);

    public abstract void readFromNBT(NBTTagCompound var1);

    public abstract void updateWithConfig(Configuration var1, String var2);
}

