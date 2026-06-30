/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksHandlerAutoRefill;
import invtweaks.InvTweaksHandlerShortcuts;
import invtweaks.InvTweaksItemTreeLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksConfigManager {
    private static final Logger log = InvTweaks.log;
    private final Minecraft mc;
    @Nullable
    private InvTweaksConfig config = null;
    private long storedConfigLastModified = 0L;
    @Nullable
    private InvTweaksHandlerAutoRefill autoRefillHandler = null;
    @Nullable
    private InvTweaksHandlerShortcuts shortcutsHandler = null;

    public InvTweaksConfigManager(Minecraft mc_) {
        this.mc = mc_;
    }

    private static long computeConfigLastModified() {
        return InvTweaksConst.CONFIG_RULES_FILE.lastModified() + InvTweaksConst.CONFIG_TREE_FILE.lastModified();
    }

    private static void backupFile(@NotNull File file) {
        File newFile = new File(file.getParentFile(), file.getName() + ".bak");
        log.warn("Backing up file: %1$s to %2$s", (Object)file.getAbsolutePath(), (Object)newFile.getAbsolutePath());
        if (newFile.exists()) {
            log.warn("New file %1$s already exists, deleting old.", (Object)newFile.getAbsolutePath());
            newFile.delete();
        }
        file.renameTo(newFile);
    }

    private static void showConfigErrors(@NotNull InvTweaksConfig config) {
        List<String> invalid = config.getInvalidKeywords();
        if (invalid.size() > 0) {
            String error = I18n.func_135052_a((String)"invtweaks.loadconfig.invalidkeywords", (Object[])new Object[0]) + ": ";
            for (String keyword : config.getInvalidKeywords()) {
                error = error + keyword + " ";
            }
            InvTweaks.logInGameStatic(error);
        }
    }

    public boolean makeSureConfigurationIsLoaded() {
        try {
            if (this.config != null && this.config.refreshProperties()) {
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
                if (this.config.getProperty("enableConfigLoadedMesssage").equals("true")) {
                    InvTweaks.logInGameStatic("invtweaks.propsfile.loaded");
                }
            }
        }
        catch (IOException e) {
            InvTweaks.logInGameErrorStatic("invtweaks.loadconfig.refresh.error", e);
        }
        long configLastModified = InvTweaksConfigManager.computeConfigLastModified();
        if (this.config != null) {
            return this.storedConfigLastModified == configLastModified || this.loadConfig();
        }
        this.storedConfigLastModified = configLastModified;
        return this.loadConfig();
    }

    @Nullable
    public InvTweaksConfig getConfig() {
        return this.config;
    }

    @Nullable
    public InvTweaksHandlerAutoRefill getAutoRefillHandler() {
        return this.autoRefillHandler;
    }

    @Nullable
    public InvTweaksHandlerShortcuts getShortcutsHandler() {
        return this.shortcutsHandler;
    }

    private boolean loadConfig() {
        File configDir = InvTweaksConst.MINECRAFT_CONFIG_DIR;
        if (!configDir.exists()) {
            configDir.mkdir();
        }
        try {
            if (!InvTweaksItemTreeLoader.isValidVersion(InvTweaksConst.CONFIG_TREE_FILE)) {
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
            }
        }
        catch (Exception e) {
            log.warn("Failed to check item tree version: " + e.getMessage());
        }
        if (InvTweaksConst.OLD_CONFIG_TREE_FILE.exists()) {
            if (InvTweaksConst.CONFIG_RULES_FILE.exists()) {
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
            }
            InvTweaksConst.OLD_CONFIG_TREE_FILE.renameTo(InvTweaksConst.CONFIG_TREE_FILE);
        } else if (InvTweaksConst.OLDER_CONFIG_RULES_FILE.exists()) {
            if (InvTweaksConst.CONFIG_RULES_FILE.exists()) {
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_RULES_FILE);
            }
            InvTweaksConst.OLDER_CONFIG_RULES_FILE.renameTo(InvTweaksConst.CONFIG_RULES_FILE);
        }
        if (!InvTweaksConst.CONFIG_RULES_FILE.exists() && this.extractFile(InvTweaksConst.DEFAULT_CONFIG_FILE, InvTweaksConst.CONFIG_RULES_FILE)) {
            InvTweaks.logInGameStatic(InvTweaksConst.CONFIG_RULES_FILE + " " + I18n.func_135052_a((String)"invtweaks.loadconfig.filemissing", (Object[])new Object[0]));
        }
        if (!InvTweaksConst.CONFIG_TREE_FILE.exists() && this.extractFile(InvTweaksConst.DEFAULT_CONFIG_TREE_FILE, InvTweaksConst.CONFIG_TREE_FILE)) {
            InvTweaks.logInGameStatic(InvTweaksConst.CONFIG_TREE_FILE + " " + I18n.func_135052_a((String)"invtweaks.loadconfig.filemissing", (Object[])new Object[0]));
        }
        this.storedConfigLastModified = InvTweaksConfigManager.computeConfigLastModified();
        String error = null;
        Exception errorException = null;
        try {
            if (this.config == null) {
                this.config = new InvTweaksConfig(InvTweaksConst.CONFIG_RULES_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.autoRefillHandler = new InvTweaksHandlerAutoRefill(this.mc, this.config);
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
            }
            this.config.load();
            this.shortcutsHandler.loadShortcuts();
            if (this.config.getProperty("enableConfigLoadedMesssage").equals("true")) {
                InvTweaks.logInGameStatic("invtweaks.loadconfig.done");
            }
            InvTweaksConfigManager.showConfigErrors(this.config);
        }
        catch (FileNotFoundException e) {
            error = "Config file not found";
            errorException = e;
        }
        catch (Exception e) {
            error = "Error while loading config";
            errorException = e;
        }
        if (error != null) {
            log.error(error);
            InvTweaks.logInGameErrorStatic(error, errorException);
            try {
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_TREE_FILE);
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_RULES_FILE);
                InvTweaksConfigManager.backupFile(InvTweaksConst.CONFIG_PROPS_FILE);
                this.extractFile(InvTweaksConst.DEFAULT_CONFIG_FILE, InvTweaksConst.CONFIG_RULES_FILE);
                this.extractFile(InvTweaksConst.DEFAULT_CONFIG_TREE_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.config = new InvTweaksConfig(InvTweaksConst.CONFIG_RULES_FILE, InvTweaksConst.CONFIG_TREE_FILE);
                this.autoRefillHandler = new InvTweaksHandlerAutoRefill(this.mc, this.config);
                this.shortcutsHandler = new InvTweaksHandlerShortcuts(this.mc, this.config);
                this.config.load();
                this.shortcutsHandler.loadShortcuts();
            }
            catch (Exception e) {
                this.config = null;
                this.autoRefillHandler = null;
                this.shortcutsHandler = null;
                if (e.getCause() == null) {
                    e.initCause(errorException);
                }
                throw new Error("InvTweaks config load failed", e);
            }
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean extractFile(@NotNull ResourceLocation resource, @NotNull File destination) {
        try {
            Throwable throwable = null;
            try (InputStream input = this.mc.func_110442_L().func_110536_a(resource).func_110527_b();){
                FileUtils.copyInputStreamToFile((InputStream)input, (File)destination);
                boolean bl = true;
                return bl;
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("[16] The mod won't work, because " + destination + " creation failed!");
                log.error("Cannot create " + destination + " file: " + e.getMessage());
                boolean bl2 = false;
                return bl2;
            }
            catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            }
        }
        catch (IOException e2) {
            InvTweaks.logInGameStatic("[15] The mod won't work, because " + resource + " extraction failed!");
            log.error("Cannot extract " + resource + " file: " + e2.getMessage());
            return false;
        }
    }
}

