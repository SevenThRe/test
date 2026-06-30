/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.opengl.Display
 */
package journeymap.client.task.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import journeymap.client.JourneymapClient;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.task.main.MappingMonitorTask;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class ExpireTextureTask
implements IMainThreadTask {
    private static final int MAX_FAILS = 5;
    private static String NAME = "Tick." + MappingMonitorTask.class.getSimpleName();
    private static Logger LOGGER = Journeymap.getLogger();
    private final List<TextureImpl> textures;
    private final int textureId;
    private volatile int fails;

    private ExpireTextureTask(int textureId) {
        this.textures = null;
        this.textureId = textureId;
    }

    private ExpireTextureTask(TextureImpl texture) {
        this.textures = new ArrayList<TextureImpl>();
        this.textures.add(texture);
        this.textureId = -1;
    }

    private ExpireTextureTask(Collection<TextureImpl> textureCollection) {
        this.textures = new ArrayList<TextureImpl>(textureCollection);
        this.textureId = -1;
    }

    public static void queue(int textureId) {
        if (textureId != -1) {
            Journeymap.getClient().queueMainThreadTask(new ExpireTextureTask(textureId));
        }
    }

    public static void queue(TextureImpl texture) {
        Journeymap.getClient().queueMainThreadTask(new ExpireTextureTask(texture));
    }

    public static void queue(Collection<TextureImpl> textureCollection) {
        Journeymap.getClient().queueMainThreadTask(new ExpireTextureTask(textureCollection));
    }

    @Override
    public IMainThreadTask perform(Minecraft mc, JourneymapClient jm) {
        boolean success = this.deleteTextures();
        if (!success && this.textures != null && !this.textures.isEmpty()) {
            ++this.fails;
            LOGGER.warn("ExpireTextureTask.perform() couldn't delete textures: " + this.textures + ", fails: " + this.fails);
            if (this.fails <= 5) {
                return this;
            }
        }
        return null;
    }

    private boolean deleteTextures() {
        if (this.textureId != -1) {
            return this.deleteTexture(this.textureId);
        }
        ListIterator<TextureImpl> iter = this.textures.listIterator();
        while (iter.hasNext()) {
            TextureImpl texture = (TextureImpl)((Object)iter.next());
            if (texture == null) {
                iter.remove();
                continue;
            }
            if (!this.deleteTexture(texture)) break;
            iter.remove();
        }
        return this.textures.isEmpty();
    }

    private boolean deleteTexture(TextureImpl texture) {
        boolean success = false;
        if (texture.isBound()) {
            try {
                if (Display.isCurrent()) {
                    GlStateManager.func_179150_h((int)texture.func_110552_b());
                    texture.clear();
                    success = true;
                }
            }
            catch (LWJGLException t) {
                LOGGER.warn("Couldn't delete texture " + (Object)((Object)texture) + ": " + (Object)((Object)t));
                success = false;
            }
        } else {
            texture.clear();
            success = true;
        }
        return success;
    }

    private boolean deleteTexture(int textureId) {
        try {
            if (Display.isCurrent()) {
                GlStateManager.func_179150_h((int)textureId);
                return true;
            }
        }
        catch (LWJGLException t) {
            LOGGER.warn("Couldn't delete textureId " + textureId + ": " + (Object)((Object)t));
        }
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

