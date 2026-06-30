/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.core.kumo.variable;

import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.kumo.variable.IKumoVariable;
import goblinbob.mobends.core.kumo.variable.KumoVariableEntry;
import java.util.HashMap;
import net.minecraft.entity.EntityLivingBase;

public class KumoVariableRegistry {
    public static final KumoVariableRegistry instance = new KumoVariableRegistry();
    private EntityData<?> tempData;
    private HashMap<String, KumoVariableEntry> variables = new HashMap();

    public void provideTemporaryData(EntityData<?> tempData) {
        this.tempData = tempData;
    }

    private static void registerVariable(String key, IKumoVariable variable) {
        KumoVariableRegistry.instance.variables.put(key, new KumoVariableEntry(variable, key));
    }

    static {
        KumoVariableRegistry.registerVariable("ticks", DataUpdateHandler::getTicks);
        KumoVariableRegistry.registerVariable("random", Math::random);
        KumoVariableRegistry.registerVariable("ticksAfterPunch", () -> {
            if (KumoVariableRegistry.instance.tempData instanceof LivingEntityData) {
                return ((LivingEntityData)KumoVariableRegistry.instance.tempData).getTicksAfterAttack();
            }
            return 0.0;
        });
        KumoVariableRegistry.registerVariable("health", () -> {
            Object entity = KumoVariableRegistry.instance.tempData.getEntity();
            if (entity instanceof EntityLivingBase) {
                return ((EntityLivingBase)entity).getHealth();
            }
            return 0.0;
        });
    }
}

