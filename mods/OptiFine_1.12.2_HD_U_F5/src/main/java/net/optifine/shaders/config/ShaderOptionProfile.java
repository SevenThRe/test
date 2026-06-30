/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.util.ArrayList;
import net.optifine.Lang;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderProfile;

public class ShaderOptionProfile
extends ShaderOption {
    private ShaderProfile[] profiles = null;
    private ShaderOption[] options = null;
    private static final String NAME_PROFILE = "<profile>";
    private static final String VALUE_CUSTOM = "<custom>";

    public ShaderOptionProfile(ShaderProfile[] profiles, ShaderOption[] options) {
        super(NAME_PROFILE, "", ShaderOptionProfile.detectProfileName(profiles, options), ShaderOptionProfile.getProfileNames(profiles), ShaderOptionProfile.detectProfileName(profiles, options, true), null);
        this.profiles = profiles;
        this.options = options;
    }

    @Override
    public void nextValue() {
        super.nextValue();
        if (this.getValue().equals(VALUE_CUSTOM)) {
            super.nextValue();
        }
        this.applyProfileOptions();
    }

    public void updateProfile() {
        ShaderProfile prof = this.getProfile(this.getValue());
        if (prof != null && ShaderUtils.matchProfile(prof, this.options, false)) {
            return;
        }
        String val = ShaderOptionProfile.detectProfileName(this.profiles, this.options);
        this.setValue(val);
    }

    private void applyProfileOptions() {
        ShaderProfile prof = this.getProfile(this.getValue());
        if (prof == null) {
            return;
        }
        String[] opts = prof.getOptions();
        for (int i = 0; i < opts.length; ++i) {
            String name = opts[i];
            ShaderOption so = this.getOption(name);
            if (so == null) continue;
            String val = prof.getValue(name);
            so.setValue(val);
        }
    }

    private ShaderOption getOption(String name) {
        for (int i = 0; i < this.options.length; ++i) {
            ShaderOption so = this.options[i];
            if (!so.getName().equals(name)) continue;
            return so;
        }
        return null;
    }

    private ShaderProfile getProfile(String name) {
        for (int i = 0; i < this.profiles.length; ++i) {
            ShaderProfile prof = this.profiles[i];
            if (!prof.getName().equals(name)) continue;
            return prof;
        }
        return null;
    }

    @Override
    public String getNameText() {
        return Lang.get("of.shaders.profile");
    }

    @Override
    public String getValueText(String val) {
        if (val.equals(VALUE_CUSTOM)) {
            return Lang.get("of.general.custom", VALUE_CUSTOM);
        }
        return Shaders.translate("profile." + val, val);
    }

    @Override
    public String getValueColor(String val) {
        if (val.equals(VALUE_CUSTOM)) {
            return "\u00a7c";
        }
        return "\u00a7a";
    }

    @Override
    public String getDescriptionText() {
        String text = Shaders.translate("profile.comment", null);
        if (text != null) {
            return text;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.profiles.length; ++i) {
            String profText;
            String name = this.profiles[i].getName();
            if (name == null || (profText = Shaders.translate("profile." + name + ".comment", null)) == null) continue;
            sb.append(profText);
            if (profText.endsWith(". ")) continue;
            sb.append(". ");
        }
        return sb.toString();
    }

    private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts) {
        return ShaderOptionProfile.detectProfileName(profs, opts, false);
    }

    private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts, boolean def) {
        ShaderProfile prof = ShaderUtils.detectProfile(profs, opts, def);
        if (prof == null) {
            return VALUE_CUSTOM;
        }
        return prof.getName();
    }

    private static String[] getProfileNames(ShaderProfile[] profs) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < profs.length; ++i) {
            ShaderProfile prof = profs[i];
            list.add(prof.getName());
        }
        list.add(VALUE_CUSTOM);
        String[] names = list.toArray(new String[list.size()]);
        return names;
    }
}

