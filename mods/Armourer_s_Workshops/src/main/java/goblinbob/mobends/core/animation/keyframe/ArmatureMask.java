/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.animation.keyframe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArmatureMask {
    private Mode mode;
    private List<String> includedParts;
    private List<String> excludedParts;

    public ArmatureMask(Mode mode) {
        this.mode = mode;
        this.includedParts = new ArrayList<String>();
        this.excludedParts = new ArrayList<String>();
    }

    public void include(String bone) {
        this.includedParts.add(bone);
    }

    public void includeAll(Collection<String> bones) {
        this.includedParts.addAll(bones);
    }

    public void exclude(String bone) {
        this.excludedParts.add(bone);
    }

    public void excludeAll(Collection<String> bones) {
        this.excludedParts.addAll(bones);
    }

    public boolean doesAllow(String bone) {
        switch (this.mode) {
            case INCLUDE_ONLY: {
                return this.includedParts.contains(bone);
            }
            case EXCLUDE_ONLY: {
                return !this.excludedParts.contains(bone);
            }
        }
        return true;
    }

    public static enum Mode {
        INCLUDE_ONLY,
        EXCLUDE_ONLY;

    }
}

