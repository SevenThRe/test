/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 */
package blockbuster.components;

import blockbuster.components.IComponentBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import net.minecraft.client.renderer.BufferBuilder;

public interface IComponentRenderBase
extends IComponentBase {
    public void render(BedrockEmitter var1, BedrockParticle var2, BufferBuilder var3, float var4);

    public void renderOnScreen(BedrockParticle var1, int var2, int var3, float var4, float var5);

    public void preRender(BedrockEmitter var1, float var2);

    public void postRender(BedrockEmitter var1, float var2);
}

