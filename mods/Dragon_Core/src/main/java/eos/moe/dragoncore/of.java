/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.ForgeHooks
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fg;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xk;
import java.awt.image.BufferedImage;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class of {
    private static long ALLATORIxDEMO;

    public of() {
        of a2;
    }

    @i(f={"\u53d6\u7269\u54c1\u4f7f\u7528\u65f6\u95f4", "Item_Use_Count"})
    public static int w() {
        EntityPlayerSP a2 = Minecraft.func_71410_x().field_71439_g;
        return a2.func_184587_cr() ? a2.func_184605_cv() : 0;
    }

    @i(f={"\u53d6\u7269\u54c1\u6700\u5927\u4f7f\u7528\u65f6\u95f4", "Item_Use_Count_Max"})
    public static int z() {
        EntityPlayerSP a2 = Minecraft.func_71410_x().field_71439_g;
        return a2.func_184587_cr() ? a2.func_184607_cu().func_77988_m() : 0;
    }

    @i(f={"\u53d6\u6d3b\u8dc3\u7269\u54c1", "Item_Use"})
    public static v ALLATORIxDEMO() {
        EntityPlayerSP a2 = Minecraft.func_71410_x().field_71439_g;
        return a2.func_184587_cr() ? new xk(a2.func_184607_cu()) : new xk(ItemStack.field_190927_a);
    }

    @i(f={"\u677e\u5f00\u4f7f\u7528", "Item_Use_Stop"})
    public static void f() {
        EntityPlayerSP a2 = Minecraft.func_71410_x().field_71439_g;
        Minecraft.func_71410_x().field_71442_b.func_78766_c((EntityPlayer)a2);
    }

    @i(f={"\u73a9\u5bb6\u540d", "\u53d6\u73a9\u5bb6\u540d", "Player_Get_Name"})
    public static String c() {
        return Minecraft.func_71410_x().field_71439_g.func_70005_c_();
    }

    @i(f={"\u73a9\u5bb6\u8840\u91cf", "\u53d6\u73a9\u5bb6\u8840\u91cf", "Player_Get_Health"})
    public static double e() {
        return Minecraft.func_71410_x().field_71439_g.func_110143_aJ();
    }

    @i(f={"\u73a9\u5bb6\u6700\u5927\u8840\u91cf", "\u53d6\u73a9\u5bb6\u6700\u5927\u8840\u91cf", "Player_Get_MaxHealth"})
    public static double j() {
        return Minecraft.func_71410_x().field_71439_g.func_110138_aP();
    }

    @i(f={"\u73a9\u5bb6\u7b49\u7ea7", "\u53d6\u73a9\u5bb6\u7b49\u7ea7", "Player_Get_Level"})
    public static int k() {
        return Minecraft.func_71410_x().field_71439_g.field_71068_ca;
    }

    @i(f={"\u73a9\u5bb6\u7ecf\u9a8c", "\u53d6\u73a9\u5bb6\u7ecf\u9a8c", "Player_Get_Exp"})
    public static double i() {
        return Minecraft.func_71410_x().field_71439_g.field_71106_cc;
    }

    @i(f={"\u73a9\u5bb6\u5750\u6807x", "\u53d6\u73a9\u5bb6\u5750\u6807x", "Player_Get_PosX"})
    public static double n() {
        return Minecraft.func_71410_x().field_71439_g.field_70165_t;
    }

    @i(f={"\u73a9\u5bb6\u5750\u6807y", "\u53d6\u73a9\u5bb6\u5750\u6807y", "Player_Get_PosY"})
    public static double y() {
        return Minecraft.func_71410_x().field_71439_g.field_70163_u;
    }

    @i(f={"\u73a9\u5bb6\u5750\u6807z", "\u53d6\u73a9\u5bb6\u5750\u6807z", "Player_Get_PosZ"})
    public static double h() {
        return Minecraft.func_71410_x().field_71439_g.field_70161_v;
    }

    @i(f={"\u73a9\u5bb6motionx"})
    public static double s() {
        return Minecraft.func_71410_x().field_71439_g.field_70159_w;
    }

    @i(f={"\u73a9\u5bb6motiony"})
    public static double w() {
        return Minecraft.func_71410_x().field_71439_g.field_70181_x;
    }

    @i(f={"\u73a9\u5bb6motionz"})
    public static double z() {
        return Minecraft.func_71410_x().field_71439_g.field_70179_y;
    }

    @i(f={"\u73a9\u5bb6\u79fb\u52a8\u901f\u5ea6"})
    public static double k() {
        return Minecraft.func_71410_x().field_71439_g.field_71075_bZ.func_75094_b();
    }

    @i(f={"\u73a9\u5bb6\u98de\u884c\u901f\u5ea6"})
    public static double d() {
        return Minecraft.func_71410_x().field_71439_g.field_71075_bZ.func_75093_a();
    }

    @i(f={"\u73a9\u5bb6\u521b\u9020\u6a21\u5f0f"})
    public static boolean x() {
        return Minecraft.func_71410_x().field_71439_g.field_71075_bZ.field_75098_d;
    }

    @i(f={"\u73a9\u5bb6\u662f\u5426\u6b7b\u4ea1"})
    public static boolean f() {
        return Minecraft.func_71410_x().field_71439_g.field_70128_L;
    }

    @i(f={"\u5012\u5730\u4f53\u9a8c\u5361"})
    public static void c() {
        Minecraft.func_71410_x().field_71439_g.func_70606_j(0.0f);
    }

    @i(f={"\u73a9\u5bb6\u5f53\u524d\u4eba\u79f0"})
    public static int d() {
        return Minecraft.func_71410_x().field_71474_y.field_74320_O;
    }

    @i(f={"player_preparePlayerToSpawn"})
    public static void ALLATORIxDEMO() {
        Minecraft.func_71410_x().field_71439_g.func_70065_x();
    }

    @i(f={"player_death_time"})
    public static double x() {
        return Minecraft.func_71410_x().field_71439_g.field_70725_aQ;
    }

    @i(f={"player_hurt_time"})
    public static double f() {
        return Minecraft.func_71410_x().field_71439_g.field_70737_aN;
    }

    @i(f={"CollisionBoxes1"})
    public static double c(double a2, double a3, double a4) {
        WorldClient a5 = Minecraft.func_71410_x().field_71441_e;
        if (a5 != null) {
            EntityPlayerSP a6 = Minecraft.func_71410_x().field_71439_g;
            List a7 = a5.func_184144_a((Entity)a6, a6.func_174813_aQ().func_72317_d(a2, a3, a4));
            return a7.size();
        }
        return 1.0;
    }

    @i(f={"CollisionBoxes2"})
    public static double ALLATORIxDEMO(double a2, double a3, double a4) {
        WorldClient a5 = Minecraft.func_71410_x().field_71441_e;
        if (a5 != null) {
            EntityPlayerSP a6 = Minecraft.func_71410_x().field_71439_g;
            List a7 = a5.func_184144_a((Entity)a6, a6.func_174813_aQ().func_72314_b(a2, a3, a4));
            return a7.size();
        }
        return 1.0;
    }

    @i(f={"CollisionBoxes3"})
    public static boolean ALLATORIxDEMO(double a2, double a3, double a4) {
        WorldClient a5 = Minecraft.func_71410_x().field_71441_e;
        if (a5 != null) {
            EntityPlayerSP a6 = Minecraft.func_71410_x().field_71439_g;
            return a5.func_72953_d(a6.func_174813_aQ().func_72314_b(a2, a3, a4));
        }
        return true;
    }

    @i(f={"\u73a9\u5bb6yaw", "\u53d6\u73a9\u5bb6yaw", "Player_Get_Yaw"})
    public static double c() {
        return Minecraft.func_71410_x().field_71439_g.field_70177_z;
    }

    @i(f={"\u73a9\u5bb6pitch", "\u53d6\u73a9\u5bb6pitch", "Player_Get_Pitch"})
    public static double ALLATORIxDEMO() {
        return Minecraft.func_71410_x().field_71439_g.field_70125_A;
    }

    @i(f={"\u73a9\u5bb6\u662f\u5426\u98de\u884c", "Player_IsFlying"})
    public static boolean c() {
        return Minecraft.func_71410_x().field_71439_g.field_71075_bZ.field_75100_b;
    }

    @i(f={"\u73a9\u5bb6\u9971\u98df\u5ea6", "\u53d6\u73a9\u5bb6\u9971\u98df\u5ea6", "Player_Get_Food"})
    public static int x() {
        return Minecraft.func_71410_x().field_71439_g.func_71024_bL().func_75116_a();
    }

    @i(f={"\u73a9\u5bb6\u6c27\u6c14\u503c", "\u53d6\u73a9\u5bb6\u6c27\u6c14\u503c", "Player_Get_Air"})
    public static int f() {
        return Minecraft.func_71410_x().field_71439_g.func_70086_ai();
    }

    @i(f={"\u73a9\u5bb6\u62a4\u7532\u503c", "\u53d6\u73a9\u5bb6\u62a4\u7532\u503c", "Player_Get_Armor"})
    public static int c() {
        return ForgeHooks.getTotalArmorValue((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
    }

    @i(f={"\u73a9\u5bb6\u624b\u6301\u69fd", "\u53d6\u73a9\u5bb6\u624b\u6301\u69fd", "Player_Get_CurrentItem"})
    public static int ALLATORIxDEMO() {
        return Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70461_c;
    }

    @i(f={"\u73a9\u5bb6\u662f\u5426\u5728\u6c34\u4e2d", "\u662f\u5426\u5728\u6c34\u4e2d", "Player_InWater"})
    public static boolean ALLATORIxDEMO() {
        return Minecraft.func_71410_x().field_71439_g.func_70090_H();
    }

    @i(f={"\u53d6\u73a9\u5bb6\u5934\u50cf", "Player_Head_Texture"})
    public static String ALLATORIxDEMO() {
        EntityPlayerSP a2 = Minecraft.func_71410_x().field_71439_g;
        TextureManager a3 = Minecraft.func_71410_x().func_110434_K();
        ResourceLocation a4 = new ResourceLocation("dragoncore", "player_head_texture.png");
        ITextureObject a5 = a3.func_110581_b(a4);
        if (a2 == null) {
            return "unknown.png";
        }
        if (a5 == null || System.currentTimeMillis() - ALLATORIxDEMO > 5000L) {
            if (a5 instanceof AbstractTexture) {
                ((AbstractTexture)a5).func_147631_c();
            }
            BufferedImage a6 = fg.ALLATORIxDEMO((AbstractClientPlayer)a2);
            int a7 = a6.getWidth() / 8;
            BufferedImage a8 = new BufferedImage(a7, a7, 6);
            for (int a9 = a7; a9 < a7 * 2; ++a9) {
                for (int a10 = a7; a10 < a7 * 2; ++a10) {
                    int a11 = a6.getRGB(a9, a10);
                    a8.setRGB(a9 - a7, a10 - a7, a11);
                }
            }
            DynamicTexture a12 = new DynamicTexture(a8);
            a3.func_110579_a(a4, (ITextureObject)a12);
        }
        return "player_head_texture.png";
    }
}

