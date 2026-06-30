/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.storage.WorldSavedData
 */
package journeymap.server.nbt;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class NBTWorldSaveDataHandler
extends WorldSavedData {
    private NBTTagCompound data = new NBTTagCompound();
    private String tagName;

    public NBTWorldSaveDataHandler(String tagName) {
        super(tagName);
        this.tagName = tagName;
    }

    public void func_76184_a(NBTTagCompound compound) {
        this.data = compound.func_74775_l(this.tagName);
    }

    public NBTTagCompound func_189551_b(NBTTagCompound compound) {
        compound.func_74782_a(this.tagName, (NBTBase)this.data);
        return compound;
    }

    public NBTTagCompound getData() {
        return this.data;
    }
}

