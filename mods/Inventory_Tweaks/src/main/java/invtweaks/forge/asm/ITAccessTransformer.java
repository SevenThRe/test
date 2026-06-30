/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.asm.transformers.AccessTransformer
 */
package invtweaks.forge.asm;

import java.io.IOException;
import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;

public class ITAccessTransformer
extends AccessTransformer {
    public ITAccessTransformer() throws IOException {
        super("invtweaks_at.cfg");
    }
}

