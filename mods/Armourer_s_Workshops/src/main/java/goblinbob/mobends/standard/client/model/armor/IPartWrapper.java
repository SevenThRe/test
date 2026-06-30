/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 */
package goblinbob.mobends.standard.client.model.armor;

import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.client.model.armor.ArmorWrapper;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public interface IPartWrapper {
    public void apply(ArmorWrapper var1);

    public void deapply(ArmorWrapper var1);

    public void syncUp(BipedEntityData<?> var1);

    public IPartWrapper offsetInner(float var1, float var2, float var3);

    public IPartWrapper setParent(IModelPart var1);

    @FunctionalInterface
    public static interface ModelPartSetter {
        public void replacePart(ModelBiped var1, ModelRenderer var2);
    }

    @FunctionalInterface
    public static interface DataPartSelector {
        public ModelPartTransform selectPart(BipedEntityData<?> var1);
    }
}

