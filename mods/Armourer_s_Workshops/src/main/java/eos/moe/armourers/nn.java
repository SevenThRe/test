/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityWitherSkeleton
 *  net.minecraft.entity.monster.EntityZombie
 */
package eos.moe.armourers;

import eos.moe.armourers.gd;
import eos.moe.armourers.ki;
import eos.moe.armourers.qg;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;

public class nn {
    public static Set<Class<? extends Entity>> j = new HashSet<Class<? extends Entity>>();

    public nn() {
        nn a2;
    }

    private static /* synthetic */ void r(Class<? extends Entity> a2) {
        Object object = Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(a2);
        if (object instanceof RenderLivingBase) {
            Object object2 = (RenderLivingBase)object;
            object = ((RenderLivingBase)object).layerRenderers;
            ki ki2 = new ki((RenderLivingBase)object2);
            object2 = new gd((RenderLivingBase)object2);
            Object object3 = object;
            object3.add(0, object2);
            object3.add(ki2);
            j.add(a2);
            System.out.println(new StringBuilder().insert(0, "\u5df2\u6ce8\u518c\u6e32\u67d3: ").append(a2).toString());
        }
    }

    public static void y() {
        Iterator iterator;
        Iterator iterator2 = iterator = Minecraft.getMinecraft().getRenderManager().getSkinMap().values().iterator();
        while (iterator2.hasNext()) {
            RenderPlayer renderPlayer = (RenderPlayer)iterator.next();
            List list = renderPlayer.layerRenderers;
            Object object = list.iterator();
            while (object.hasNext()) {
                if (!((LayerRenderer)object.next() instanceof qg)) continue;
                return;
            }
            object = new gd((RenderLivingBase)renderPlayer);
            iterator2 = iterator;
            list.add(0, object);
            list.add(new qg(renderPlayer));
        }
    }

    public static void r() {
        nn.y();
        nn.r(EntitySkeleton.class);
        nn.r(EntityPigZombie.class);
        nn.r(EntityZombie.class);
        nn.r(EntityWitherSkeleton.class);
        nn.r(EntityArmorStand.class);
    }
}

