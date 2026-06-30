/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Loader
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.integration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraftforge.fml.common.Loader;
import org.jetbrains.annotations.Nullable;

public class ItemListChecker {
    @Nullable
    private Method neiHidden = ItemListChecker.getNeiHidden();
    @Nullable
    private Method jeiShown = ItemListChecker.getJeiShown();
    private boolean visible = false;
    private boolean wasVisible = false;

    public boolean isVisible() {
        this.wasVisible = this.visible;
        this.visible = this.isNeiVisible() || this.isJeiVisible();
        return this.visible;
    }

    public boolean wasVisible() {
        return this.wasVisible;
    }

    private static Method getNeiHidden() {
        if (Loader.isModLoaded((String)"nei")) {
            try {
                Class<?> clientConfig = Class.forName("codechicken.nei.NEIClientConfig");
                if (clientConfig != null) {
                    return clientConfig.getMethod("isHidden", new Class[0]);
                }
            }
            catch (ClassNotFoundException | NoSuchMethodException | SecurityException ignored) {
                return null;
            }
        }
        return null;
    }

    private static Method getJeiShown() {
        if (Loader.isModLoaded((String)"jei")) {
            try {
                Class<?> clientConfig = Class.forName("mezz.jei.config.Config");
                if (clientConfig != null) {
                    return clientConfig.getMethod("isOverlayEnabled", new Class[0]);
                }
            }
            catch (ClassNotFoundException | NoSuchMethodException | SecurityException ignored) {
                return null;
            }
        }
        return null;
    }

    private boolean isNeiVisible() {
        if (this.neiHidden != null) {
            try {
                return (Boolean)this.neiHidden.invoke(null, new Object[0]) == false;
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                this.neiHidden = null;
                return false;
            }
        }
        return false;
    }

    private boolean isJeiVisible() {
        if (this.jeiShown != null) {
            try {
                return (Boolean)this.jeiShown.invoke(null, new Object[0]);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                this.jeiShown = null;
                return false;
            }
        }
        return false;
    }
}

