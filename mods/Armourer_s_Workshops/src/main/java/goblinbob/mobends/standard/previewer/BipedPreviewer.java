/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 */
package goblinbob.mobends.standard.previewer;

import goblinbob.mobends.core.bender.BoneMetadata;
import goblinbob.mobends.core.bender.IPreviewer;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3fReadonly;
import goblinbob.mobends.standard.data.BipedEntityData;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;

public class BipedPreviewer<D extends BipedEntityData<?>>
implements IPreviewer<D> {
    private static final Vec3fReadonly VIEWPORT_ANCHOR = new Vec3fReadonly(0.0f, 1.3f, 0.0f);
    private static final Map<String, BoneMetadata> BONE_METADATA = new HashMap<String, BoneMetadata>(){
        {
            this.put("head", new BoneMetadata(-4.0f, -8.0f, -4.0f, 4.0f, 0.0f, 4.0f));
            this.put("body", new BoneMetadata(-4.0f, -12.0f, -2.0f, 4.0f, 0.0f, 2.0f));
            this.put("leftArm", new BoneMetadata(-1.0f, -2.0f, -2.0f, 3.0f, 4.0f, 2.0f));
            this.put("rightArm", new BoneMetadata(-3.0f, -2.0f, -2.0f, 1.0f, 4.0f, 2.0f));
        }
    };

    @Override
    public void prePreview(D data, String animationToPreview) {
        ((BipedEntityData)data).headYaw.override(Float.valueOf(0.0f));
        ((BipedEntityData)data).headPitch.override(Float.valueOf(0.0f));
        switch (animationToPreview) {
            case "walk": {
                this.prepareForWalk(data);
                break;
            }
            case "jump": {
                this.prepareForJump(data);
                break;
            }
            default: {
                this.prepareForDefault(data);
            }
        }
    }

    protected void prepareForWalk(D data) {
        float ticks = DataUpdateHandler.getTicks();
        ((BipedEntityData)data).limbSwing.override(Float.valueOf(ticks * 0.6f));
        ((EntityData)data).overrideOnGroundState(true);
        ((BipedEntityData)data).limbSwingAmount.override(Float.valueOf(1.0f));
        ((EntityData)data).overrideStillness(false);
    }

    protected void prepareForJump(D data) {
        float ticks = DataUpdateHandler.getTicks();
        float JUMP_DURATION = 10.0f;
        float WAIT_DURATION = 10.0f;
        float TOTAL_DURATION = 20.0f;
        float t2 = ticks % 20.0f;
        if (t2 <= 10.0f) {
            ((EntityData)data).overrideOnGroundState(false);
            double yOffset = Math.sin((double)(t2 / 10.0f) * Math.PI) * 0.8;
            GlStateManager.func_179137_b((double)0.0, (double)yOffset, (double)0.0);
        } else {
            ((EntityData)data).overrideOnGroundState(true);
        }
        ((BipedEntityData)data).limbSwingAmount.override(Float.valueOf(0.0f));
        ((EntityData)data).overrideStillness(true);
    }

    protected void prepareForDefault(D data) {
        ((EntityData)data).overrideOnGroundState(true);
        ((BipedEntityData)data).limbSwingAmount.override(Float.valueOf(0.0f));
        ((EntityData)data).overrideStillness(true);
    }

    @Override
    public void postPreview(D data, String animationToPreview) {
    }

    @Override
    public IVec3fRead getAnchorPoint() {
        return VIEWPORT_ANCHOR;
    }

    @Override
    public Map<String, BoneMetadata> getBoneMetadata() {
        return BONE_METADATA;
    }
}

