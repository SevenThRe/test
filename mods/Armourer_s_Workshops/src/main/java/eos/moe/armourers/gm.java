/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Throwables
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.shader.Shader
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.client.shader.ShaderUniform
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.armourers;

import com.google.common.base.Throwables;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class gm {
    public gm() {
        gm a2;
    }

    public static void y() {
        if (Minecraft.getMinecraft().world != null) {
            EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;
            entityRenderer.loadShader(new ResourceLocation("armourers_workshops", "shaders/fade_in_blur.json"));
            Object object = Minecraft.getMinecraft().entityRenderer.getShaderGroup();
            try {
                String[] stringArray = new String[2];
                stringArray[0] = "listShaders";
                stringArray[1] = "listShaders";
                Field field = ReflectionHelper.findField(ShaderGroup.class, (String[])stringArray);
                object = ((List)field.get(object)).iterator();
                while (object.hasNext()) {
                    ShaderUniform shaderUniform = ((Shader)object.next()).getShaderManager().getShaderUniform("Progress");
                    if (shaderUniform == null) continue;
                    shaderUniform.set(1.0f);
                }
            }
            catch (IllegalAccessException | IllegalArgumentException exception) {
                Throwables.propagate((Throwable)exception);
            }
        }
    }

    public static void r() {
        if (Minecraft.getMinecraft().world != null) {
            Minecraft.getMinecraft().entityRenderer.stopUseShader();
        }
    }
}

