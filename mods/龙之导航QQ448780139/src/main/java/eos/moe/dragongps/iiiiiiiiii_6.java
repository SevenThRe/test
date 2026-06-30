/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_0;
import eos.moe.dragongps.iiiiiiiiii_10;
import eos.moe.dragongps.iiiiiiiiii_12;
import eos.moe.dragongps.iiiiiiiiii_15;
import eos.moe.dragongps.iiiiiiiiii_19;
import eos.moe.dragongps.iiiiiiiiii_2;
import eos.moe.dragongps.iiiiiiiiii_8;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*
 * Renamed from eos.moe.dragongps.IiiiIiIIii
 */
public class iiiiiiiiii_6
implements IMessage,
IMessageHandler<iiiiiiiiii_6, IMessage> {
    private int iiIIIiIiII;
    public static ConcurrentHashMap<String, iiiiiiiiii_12> IIiiIiiIII;
    public static ConcurrentHashMap<String, iiiiiiiiii_10> iIIiIIiIii;
    public static String iIIIIiiIII;
    public static List<iiiiiiiiii_19> IIiIiIIIiI;
    private String IIiiiiiIIi;

    static {
        iIIiIIiIii = new ConcurrentHashMap();
        IIiiIiiIII = new ConcurrentHashMap();
        IIiIiIIIiI = new ArrayList<iiiiiiiiii_19>();
        iIIIIiiIII = "";
    }

    private static /* synthetic */ void IIIiiiIiii(String IIiiiiiIIi, String IIiiiiiIIi2, Vec3d IIiiiiiIIi3, int IIiiiiiIIi4, List<iiiiiiiiii_2> IIiiiiiIIi5) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        Vec3d IIiiiiiIIi6 = new Vec3d(0.0, 1.0, 0.0).normalize();
        Vec3d IIiiiiiIIi7 = new Vec3d(1.0, 0.0, 0.0).normalize();
        int IIiiiiiIIi8 = 0;
        ArrayList<iiiiiiiiii_8> IIiiiiiIIi9 = new ArrayList<iiiiiiiiii_8>();
        for (iiiiiiiiii_2 IIiiiiiIIi10 : IIiiiiiIIi5) {
            Vec3d IIiiiiiIIi11 = IIiiiiiIIi10.IIiiiiiIIi;
            Vec3d IIiiiiiIIi12 = IIiiiiiIIi10.iIIIIiiIII;
            Vec3d IIiiiiiIIi13 = new Vec3d(IIiiiiiIIi12.x - IIiiiiiIIi11.x, IIiiiiiIIi12.y - IIiiiiiIIi11.y, IIiiiiiIIi12.z - IIiiiiiIIi11.z);
            Vec3d IIiiiiiIIi14 = new Vec3d(IIiiiiiIIi13.x, IIiiiiiIIi13.y, IIiiiiiIIi13.z).normalize();
            double IIiiiiiIIi15 = Math.toDegrees(Math.acos(IIiiiiiIIi13.add(0.0, -IIiiiiiIIi13.y, 0.0).normalize().dotProduct(IIiiiiiIIi7)));
            if (IIiiiiiIIi13.z > 0.0) {
                IIiiiiiIIi15 = 360.0 - IIiiiiiIIi15;
            }
            double IIiiiiiIIi16 = Math.toDegrees(Math.acos(IIiiiiiIIi13.normalize().dotProduct(IIiiiiiIIi6)));
            int IIiiiiiIIi17 = (int)(IIiiiiiIIi11.distanceTo(IIiiiiiIIi12) / 1.2) + 1;
            for (int IIiiiiiIIi18 = 0; IIiiiiiIIi18 < IIiiiiiIIi17; ++IIiiiiiIIi18) {
                Vec3d IIiiiiiIIi19 = IIiiiiiIIi11.add(IIiiiiiIIi14.scale((double)IIiiiiiIIi18 * 1.2 + 1.0));
                if (!(IIiiiiiIIi19.distanceTo(IIiiiiiIIi12) >= 1.2)) continue;
                iiiiiiiiii_8 IIiiiiiIIi20 = new iiiiiiiiii_8(IIiiiiiIIi10.IIiIiIIIiI, IIiiiiiIIi19.x, IIiiiiiIIi19.y, IIiiiiiIIi19.z);
                IIiiiiiIIi20.iIIiIIiIii = (float)IIiiiiiIIi15 + 90.0f;
                IIiiiiiIIi20.iIiIIiIiiI = (float)IIiiiiiIIi16;
                IIiiiiiIIi20.iIIIIiiIII = ++IIiiiiiIIi8;
                IIiiiiiIIi9.add(IIiiiiiIIi20);
            }
        }
        IIiiIiiIII.put(IIiiiiiIIi, new iiiiiiiiii_12(IIiiiiiIIi9, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4));
    }

    public iiiiiiiiii_6(int IIiiiiiIIi, String IIiiiiiIIi2) {
        iiiiiiiiii_6 IIiiiiiIIi3;
        IIiiiiiIIi3.iiIIIiIiII = IIiiiiiIIi;
        IIiiiiiIIi3.IIiiiiiIIi = IIiiiiiIIi2;
    }

    public IMessage onMessage(iiiiiiiiii_6 IIiiiiiIIi, MessageContext IIiiiiiIIi2) {
        return null;
    }

    public void toBytes(ByteBuf IIiiiiiIIi) {
        iiiiiiiiii_6 IIiiiiiIIi2;
        PacketBuffer IIiiiiiIIi3 = new PacketBuffer(IIiiiiiIIi);
        IIiiiiiIIi3.writeInt(IIiiiiiIIi2.iiIIIiIiII);
        IIiiiiiIIi3.writeString(IIiiiiiIIi2.IIiiiiiIIi);
    }

    public iiiiiiiiii_6() {
        iiiiiiiiii_6 IIiiiiiIIi;
    }

    public void fromBytes(ByteBuf IIiiiiiIIi3) {
        try {
            PacketBuffer IIiiiiiIIi4 = new PacketBuffer(IIiiiiiIIi3);
            int IIiiiiiIIi5 = IIiiiiiIIi4.readInt();
            switch (IIiiiiiIIi5) {
                case 1: {
                    ArrayList<String> IIiiiiiIIi6 = new ArrayList<String>();
                    String IIiiiiiIIi7 = IIiiiiiIIi4.readString(30000);
                    int IIiiiiiIIi8 = IIiiiiiIIi4.readInt();
                    for (int IIiiiiiIIi9 = 0; IIiiiiiIIi9 < IIiiiiiIIi8; ++IIiiiiiIIi9) {
                        IIiiiiiIIi6.add(IIiiiiiIIi4.readString(30000));
                    }
                    String IIiiiiiIIi10 = IIiiiiiIIi4.readString(30000);
                    String IIiiiiiIIi11 = IIiiiiiIIi4.readString(30000);
                    double IIiiiiiIIi12 = IIiiiiiIIi4.readDouble();
                    double IIiiiiiIIi13 = IIiiiiiIIi4.readDouble();
                    double IIiiiiiIIi14 = IIiiiiiIIi4.readDouble();
                    int IIiiiiiIIi15 = IIiiiiiIIi4.readInt();
                    int IIiiiiiIIi16 = IIiiiiiIIi4.readInt();
                    iiiiiiiiii_10 IIiiiiiIIi17 = new iiiiiiiiii_10(IIiiiiiIIi6, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi12, IIiiiiiIIi13, IIiiiiiIIi14, IIiiiiiIIi15, IIiiiiiIIi16);
                    iIIiIIiIii.put(IIiiiiiIIi7, IIiiiiiIIi17);
                    break;
                }
                case 2: {
                    iIIiIIiIii.remove(IIiiiiiIIi4.readString(30000));
                    break;
                }
                case 3: {
                    iIIiIIiIii.clear();
                    break;
                }
                case 4: {
                    String IIiiiiiIIi18 = IIiiiiiIIi4.readString(30000);
                    String IIiiiiiIIi19 = IIiiiiiIIi4.readString(30000);
                    int IIiiiiiIIi20 = IIiiiiiIIi4.readInt();
                    int IIiiiiiIIi21 = IIiiiiiIIi4.readInt();
                    ArrayList<iiiiiiiiii_2> IIiiiiiIIi22 = new ArrayList<iiiiiiiiii_2>();
                    Vec3d IIiiiiiIIi23 = null;
                    for (int IIiiiiiIIi24 = 0; IIiiiiiIIi24 < IIiiiiiIIi21; ++IIiiiiiIIi24) {
                        String IIiiiiiIIi25 = IIiiiiiIIi4.readString(30000);
                        double IIiiiiiIIi26 = IIiiiiiIIi4.readDouble();
                        double IIiiiiiIIi27 = IIiiiiiIIi4.readDouble();
                        double IIiiiiiIIi28 = IIiiiiiIIi4.readDouble();
                        double IIiiiiiIIi29 = IIiiiiiIIi4.readDouble();
                        double IIiiiiiIIi30 = IIiiiiiIIi4.readDouble();
                        double IIiiiiiIIi31 = IIiiiiiIIi4.readDouble();
                        iiiiiiiiii_2 IIiiiiiIIi32 = new iiiiiiiiii_2(IIiiiiiIIi25, IIiiiiiIIi26, IIiiiiiIIi27, IIiiiiiIIi28, IIiiiiiIIi29, IIiiiiiIIi30, IIiiiiiIIi31);
                        IIiiiiiIIi22.add(IIiiiiiIIi32);
                        IIiiiiiIIi23 = IIiiiiiIIi32.iIIIIiiIII;
                    }
                    iiiiiiiiii_6.IIIiiiIiii(IIiiiiiIIi18, IIiiiiiIIi19, IIiiiiiIIi23, IIiiiiiIIi20, IIiiiiiIIi22);
                    break;
                }
                case 5: {
                    IIiiIiiIII.remove(IIiiiiiIIi4.readString(30000));
                    break;
                }
                case 6: {
                    IIiiIiiIII.clear();
                    break;
                }
                case 7: {
                    iIIIIiiIII = IIiiiiiIIi4.readString(30000);
                    break;
                }
                case 8: {
                    iiiiiiiiii_0.IIIiiiIiii(true);
                    break;
                }
                case 9: {
                    iiiiiiiiii_0.IIIiiiIiii(false);
                    break;
                }
                case 10: {
                    int IIiiiiiIIi33;
                    iiiiiiiiii_15.iiIIIiIiII = true;
                    String IIiiiiiIIi34 = IIiiiiiIIi4.readString(30000);
                    String IIiiiiiIIi35 = IIiiiiiIIi4.readString(30000);
                    ArrayList<String> IIiiiiiIIi36 = new ArrayList<String>();
                    int IIiiiiiIIi37 = IIiiiiiIIi4.readInt();
                    for (IIiiiiiIIi33 = 0; IIiiiiiIIi33 < IIiiiiiIIi37; ++IIiiiiiIIi33) {
                        IIiiiiiIIi36.add(IIiiiiiIIi4.readString(30000));
                    }
                    IIiiiiiIIi33 = -1;
                    for (int IIiiiiiIIi38 = 0; IIiiiiiIIi38 < IIiIiIIIiI.size(); ++IIiiiiiIIi38) {
                        if (!IIiIiIIIiI.get(IIiiiiiIIi38).iIIiiiIIiI().equals(IIiiiiiIIi34)) continue;
                        IIiiiiiIIi33 = IIiiiiiIIi38;
                        break;
                    }
                    if (IIiiiiiIIi33 == -1) {
                        IIiIiIIIiI.add(new iiiiiiiiii_19(IIiiiiiIIi34, IIiiiiiIIi35, IIiiiiiIIi36));
                        break;
                    }
                    IIiIiIIIiI.set(IIiiiiiIIi33, new iiiiiiiiii_19(IIiiiiiIIi34, IIiiiiiIIi35, IIiiiiiIIi36));
                    break;
                }
                case 11: {
                    String IIiiiiiIIi39 = IIiiiiiIIi4.readString(30000);
                    IIiIiIIIiI.removeIf(IIiiiiiIIi2 -> IIiiiiiIIi2.iIIiiiIIiI().equals(IIiiiiiIIi39));
                    break;
                }
                case 12: {
                    IIiIiIIIiI.clear();
                    break;
                }
                case 13: {
                    iiiiiiiiii_15.iiIIIiIiII = true;
                    Set<String> IIiiiiiIIi40 = IIiIiIIIiI.stream().map(iiiiiiiiii_19::iIIiiiIIiI).collect(Collectors.toSet());
                    IIiIiIIIiI.clear();
                    int IIiiiiiIIi41 = IIiiiiiIIi4.readInt();
                    for (int IIiiiiiIIi42 = 0; IIiiiiiIIi42 < IIiiiiiIIi41; ++IIiiiiiIIi42) {
                        String IIiiiiiIIi43 = IIiiiiiIIi4.readString(30000);
                        String IIiiiiiIIi44 = IIiiiiiIIi4.readString(30000);
                        ArrayList<String> IIiiiiiIIi45 = new ArrayList<String>();
                        int IIiiiiiIIi46 = IIiiiiiIIi4.readInt();
                        for (int IIiiiiiIIi47 = 0; IIiiiiiIIi47 < IIiiiiiIIi46; ++IIiiiiiIIi47) {
                            IIiiiiiIIi45.add(IIiiiiiIIi4.readString(30000));
                        }
                        IIiIiIIIiI.add(new iiiiiiiiii_19(IIiiiiiIIi43, IIiiiiiIIi44, IIiiiiiIIi45));
                    }
                    Set IIiiiiiIIi48 = IIiIiIIIiI.stream().map(iiiiiiiiii_19::iIIiiiIIiI).collect(Collectors.toSet());
                    IIiiiiiIIi40.removeAll(IIiiiiiIIi48);
                    IIiiiiiIIi40.forEach(IIiiiiiIIi -> {
                        IIiiIiiIII.remove(IIiiiiiIIi);
                        iIIiIIiIii.remove(IIiiiiiIIi);
                    });
                    break;
                }
            }
        }
        catch (Throwable IIiiiiiIIi49) {
            IIiiiiiIIi49.printStackTrace();
        }
    }
}

