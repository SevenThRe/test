/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.configuration;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.configuration.CoreConfig;
import java.io.File;
import java.util.Collection;

public class CoreClientConfig
extends CoreConfig {
    private static final String[] emptyStringList = new String[0];
    private static final String CATEGORY_GENERAL = "General";
    private static final String PROP_APPLIED_PACKS = "AppliedPacks";
    private static final String CATEGORY_ANIMATED = "Animated";
    public String[] appliedPackKeys = new String[0];
    public boolean playAttack;
    public boolean playBow;

    public CoreClientConfig(File file) {
        super(file);
        this.load();
    }

    @Override
    public void save() {
        for (EntityBender<?> entityBender : EntityBenderRegistry.instance.getRegistered()) {
            this.configuration.get(CATEGORY_ANIMATED, entityBender.getKey(), true).setValue(entityBender.isAnimated());
        }
        this.configuration.save();
    }

    public void load() {
        this.appliedPackKeys = this.configuration.get(CATEGORY_GENERAL, PROP_APPLIED_PACKS, emptyStringList).getStringList();
        this.playAttack = this.configuration.get(CATEGORY_ANIMATED, "attack", true).getBoolean();
        this.playBow = this.configuration.get(CATEGORY_ANIMATED, "bow", true).getBoolean();
    }

    public String[] getAppliedPacks() {
        return this.appliedPackKeys;
    }

    public void setAppliedPacks(String[] packNames) {
        this.appliedPackKeys = packNames;
        this.configuration.get(CATEGORY_GENERAL, PROP_APPLIED_PACKS, emptyStringList).set(packNames);
    }

    public void setAppliedPacks(Collection<String> packNames) {
        this.setAppliedPacks(packNames.toArray(new String[0]));
    }

    public boolean isEntityAnimated(String alterEntryKey) {
        return this.configuration.get(CATEGORY_ANIMATED, alterEntryKey, true).getBoolean();
    }

    public boolean canPlayAttack() {
        return this.playAttack;
    }

    public boolean canPlayBow() {
        return this.playBow;
    }
}

