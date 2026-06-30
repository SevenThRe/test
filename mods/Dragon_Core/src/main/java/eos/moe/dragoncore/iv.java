/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class iv
extends hl {
    private final File k;
    private YamlConfiguration ALLATORIxDEMO;

    public iv(File a2) {
        iv a3;
        a3.k = a2;
        a3.f();
    }

    public void f() {
        iv a2;
        a2.ALLATORIxDEMO();
        a2.ALLATORIxDEMO = new YamlConfiguration();
        try {
            byte[] a3 = Files.readAllBytes(a2.k.toPath());
            a2.ALLATORIxDEMO.loadFromStringIgnoreException(new String(a3, StandardCharsets.UTF_8));
            for (String a4 : a2.ALLATORIxDEMO.getKeys(true)) {
                super.ALLATORIxDEMO(a4, new xf(String.valueOf(a2.ALLATORIxDEMO.get(a4))));
            }
        }
        catch (NoSuchFileException a3) {
        }
        catch (Exception a5) {
            a5.printStackTrace();
        }
    }

    public void c() {
        iv a2;
        String a3 = a2.ALLATORIxDEMO.saveToString();
        try {
            FileUtils.writeByteArrayToFile((File)a2.k, (byte[])a3.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException a4) {
            a4.printStackTrace();
        }
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        iv a4;
        super.ALLATORIxDEMO(a2, a3);
        a4.ALLATORIxDEMO.set(a2, a3.c());
        a4.c();
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        iv a4;
        return super.ALLATORIxDEMO(a2, a3);
    }
}

