/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package goblinbob.mobends.core.network;

import goblinbob.mobends.core.network.SharedProperty;
import java.util.LinkedList;
import net.minecraft.nbt.NBTTagCompound;

public class SharedConfig {
    private LinkedList<SharedProperty<?>> properties = new LinkedList();

    public void addProperty(SharedProperty<?> property) {
        this.properties.add(property);
    }

    public Iterable<SharedProperty<?>> getProperties() {
        return this.properties;
    }

    public void writeToNBT(NBTTagCompound tag) {
        for (SharedProperty sharedProperty : this.properties) {
            sharedProperty.writeToNBT(tag);
        }
    }

    public void readFromNBT(NBTTagCompound tag) {
        for (SharedProperty sharedProperty : this.properties) {
            sharedProperty.readFromNBT(tag);
        }
    }
}

