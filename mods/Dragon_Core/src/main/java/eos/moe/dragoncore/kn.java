/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.xf;
import net.minecraft.util.text.ITextComponent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class kn
extends xf {
    private final ITextComponent ALLATORIxDEMO;

    public kn(ITextComponent a2) {
        super(a2.getFormattedText());
        kn a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public ITextComponent ALLATORIxDEMO() {
        kn a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "itextcomponent";
    }
}

