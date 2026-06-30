/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.core.data;

import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.bender.PreviewHelper;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.data.LivingEntityData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class EntityDatabase {
    public static EntityDatabase instance = new EntityDatabase();
    protected final Map<Integer, LivingEntityData<?>> entryMap = new HashMap();

    private LivingEntityData<?> get(Integer identifier) {
        return this.entryMap.get(identifier);
    }

    public <T extends LivingEntityData<E>, E extends EntityLivingBase> T get(E entity) {
        return this.get(entity.getEntityId());
    }

    public <T extends LivingEntityData<E>, E extends EntityLivingBase> T getOrMake(IEntityDataFactory<E> dataCreationFunction, E entity) {
        int entityId = entity.getEntityId();
        Object data = this.get(entityId);
        if (data == null) {
            data = (LivingEntityData)dataCreationFunction.createEntityData(entity);
            this.add(entityId, (LivingEntityData<?>)data);
        }
        return data;
    }

    private void add(int identifier, LivingEntityData<?> data) {
        this.entryMap.put(identifier, data);
    }

    public void add(Entity entity, LivingEntityData<?> data) {
        this.add(entity.getEntityId(), data);
    }

    public void updateClient() {
        Iterator<Map.Entry<Integer, LivingEntityData<?>>> it = this.entryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, LivingEntityData<?>> entry = it.next();
            LivingEntityData<?> entityData = entry.getValue();
            Object entityInData = entityData.getEntity();
            Entity entity = Minecraft.getMinecraft().world.getEntityByID(entry.getKey().intValue());
            if (!(PreviewHelper.isPreviewEntity(entityInData) || entity != null && entityInData == entity)) {
                EntityBenderRegistry.instance.clearCache(entityInData);
                it.remove();
                continue;
            }
            entityData.updateClient();
        }
    }

    public void updateRender(float partialTicks) {
        for (EntityData entityData : this.entryMap.values()) {
            entityData.update(partialTicks);
        }
    }

    public void refresh() {
        this.entryMap.clear();
    }

    public void onTicksRestart() {
        this.entryMap.values().forEach(data -> data.onTicksRestart());
    }
}

