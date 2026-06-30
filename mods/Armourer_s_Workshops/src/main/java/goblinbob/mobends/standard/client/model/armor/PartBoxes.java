/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 */
package goblinbob.mobends.standard.client.model.armor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class PartBoxes {
    protected HashMap<ModelRenderer, List<ModelBox>> modelToBoxesMap = new HashMap();

    public void put(ModelRenderer renderer, ModelBox box) {
        if (!this.modelToBoxesMap.containsKey(renderer)) {
            this.modelToBoxesMap.put(renderer, new LinkedList());
        }
        this.modelToBoxesMap.get(renderer).add(box);
    }

    public void clear() {
        this.modelToBoxesMap.clear();
    }

    public void clearRenderer(ModelRenderer renderer) {
        this.modelToBoxesMap.remove(renderer);
    }

    public Set<Map.Entry<ModelRenderer, List<ModelBox>>> entrySet() {
        return this.modelToBoxesMap.entrySet();
    }

    public Set<ModelRenderer> keySet() {
        return this.modelToBoxesMap.keySet();
    }
}

