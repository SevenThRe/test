/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.ICrashCallable
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.armourers;

import eos.moe.armourers.al;
import eos.moe.armourers.bk;
import eos.moe.armourers.bm;
import eos.moe.armourers.cl;
import eos.moe.armourers.cm;
import eos.moe.armourers.en;
import eos.moe.armourers.f;
import eos.moe.armourers.gk;
import eos.moe.armourers.id;
import eos.moe.armourers.jn;
import eos.moe.armourers.kd;
import eos.moe.armourers.km;
import eos.moe.armourers.mi;
import eos.moe.armourers.nf;
import eos.moe.armourers.nn;
import eos.moe.armourers.pi;
import eos.moe.armourers.qd;
import eos.moe.armourers.te;
import eos.moe.armourers.tj;
import eos.moe.armourers.ve;
import eos.moe.armourers.vk;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zh;
import eos.moe.armourers.zl;
import java.io.File;
import java.io.InputStream;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ICrashCallable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Mod.EventBusSubscriber(modid="armourers_workshops", value={Side.CLIENT})
@SideOnly(value=Side.CLIENT)
public class jg
implements f {
    private static File m;
    public static File j;

    public static File r() {
        return m;
    }

    public static int r() {
        if (jg.r()) {
            return 4;
        }
        return 2;
    }

    private /* synthetic */ void y() {
        bm.r("Loading error model.");
        InputStream inputStream = null;
        try {
            jg a2;
            inputStream = a2.getClass().getClassLoader().getResourceAsStream("assets/armourers_workshops/skins/error.armour");
            yl yl2 = tj.r(inputStream);
            mi.v.r(yl2, "error", a2);
        }
        catch (Exception exception) {
            try {
                bm.r("Error to load error model.");
                exception.printStackTrace();
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(inputStream);
                throw throwable;
            }
            IOUtils.closeQuietly((InputStream)inputStream);
            return;
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        return;
    }

    public static boolean r() {
        return vk.g;
    }

    public void r(FMLInitializationEvent a2) {
        nf.r();
        pi.r();
        jn.r();
        id.r();
        al.z();
        zl.r();
        FMLCommonHandler.instance().bus().register((Object)new cm());
        kd.r();
    }

    public static qd r() {
        if (vk.t < 0) {
            return qd.m;
        }
        if (vk.t == 0) {
            return qd.c;
        }
        return qd.values()[vk.t];
    }

    public void r() {
        km.r();
        gk.z();
    }

    public void r(FMLPreInitializationEvent a2) {
        m = new File(a2.getSuggestedConfigurationFile().getParentFile(), "DragonArmourers");
        if (!m.exists()) {
            m.mkdirs();
        }
        a2 = a2.getSuggestedConfigurationFile().getParentFile().getParentFile();
        if (zh.t) {
            a2 = new File((File)a2, "resourcepacks");
        }
        if (!(j = new File(new File((File)a2, "DragonArmourers"), "skin-library")).exists()) {
            j.mkdirs();
        }
        te.r();
        vn.r();
        ve.y();
        vk.r(new File(m, "client.cfg"));
        bk.r();
    }

    @Override
    public void onBakedSkin(en a2) {
        kd.m = a2.r();
        bm.r("Error skin loaded.");
    }

    public jg() {
        jg a2;
    }

    public void r(FMLPostInitializationEvent a2) {
        jg a3;
        nn.r();
        a3.y();
        FMLCommonHandler.instance().registerCrashCallable((ICrashCallable)new cl(a3));
    }
}

