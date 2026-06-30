/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.ModelPart;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPartExtended
extends ModelPart {
    protected IModelPart extension;

    public ModelPartExtended(ModelBase model, boolean register, int texOffsetX, int texOffsetY) {
        super(model, register, texOffsetX, texOffsetY);
    }

    public ModelPartExtended(ModelBase model, boolean register) {
        super(model, register);
    }

    public ModelPartExtended(ModelBase model, int texOffsetX, int texOffsetY) {
        super(model, texOffsetX, texOffsetY);
    }

    public ModelPartExtended setExtension(IModelPart modelPart) {
        this.extension = modelPart;
        return this;
    }

    @Override
    public void renderPart(float scale) {
        if (!this.isShowing()) {
            return;
        }
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyCharacterTransform(scale);
        GlStateManager.func_179148_o((int)this.field_78811_r);
        if (this.extension != null) {
            this.extension.renderJustPart(scale);
        }
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                childModel.func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderJustPart(float scale) {
        if (!this.isShowing()) {
            return;
        }
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyLocalTransform(scale);
        GlStateManager.func_179148_o((int)this.field_78811_r);
        if (this.extension != null) {
            this.extension.renderJustPart(scale);
        }
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                childModel.func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderPartSkin(float scale) {
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyCharacterTransform(scale);
        this.drawSkin(scale);
        if (this.extension != null) {
            this.extension.renderJustPartSkin(scale);
        }
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                if (!(childModel instanceof IModelPart)) continue;
                IModelPart model = (IModelPart)childModel;
                model.renderPartSkin(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderJustPartSkin(float scale) {
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyLocalTransform(scale);
        this.drawSkin(scale);
        if (this.extension != null) {
            this.extension.renderJustPartSkin(scale);
        }
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                if (!(childModel instanceof IModelPart)) continue;
                IModelPart model = (IModelPart)childModel;
                model.renderPartSkin(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void applyCharacterTransform(float scale) {
        super.applyCharacterTransform(scale);
    }

    @Override
    public void applyPostTransform(float scale) {
        if (this.extension != null) {
            this.extension.propagateTransform(scale);
        }
    }

    @Override
    public void propagateTransform(float scale) {
        super.propagateTransform(scale);
        this.applyPostTransform(scale);
    }

    @Override
    public void setName(String name) {
        this.name = name;
        if (this.extension != null) {
            this.extension.setName(name);
        }
    }

    @Override
    public void setRenderEntity(Entity renderEntity) {
        this.renderEntity = renderEntity;
        if (this.extension != null) {
            this.extension.setRenderEntity(renderEntity);
        }
    }
}

