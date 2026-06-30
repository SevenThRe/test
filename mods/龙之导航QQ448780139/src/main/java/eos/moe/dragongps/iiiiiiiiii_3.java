/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragongps;

import eos.moe.dragongps.IIiiIIIIiI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 * Renamed from eos.moe.dragongps.IiiIIIIiiI
 */
public class iiiiiiiiii_3 {
    public int iiIiiiiIII;
    public int IIIIIIiiII;
    public int[] IiiIIIIiii;
    public byte[] IiiiIIIiII;
    public static final int iIIIIIiIIi = 1;
    public int IIiiIIiIIi = 0;
    public byte[] IiIiiIiiIi;
    public int IIIIIIIIIi = 1;
    public int iiiIIiIIiI = 0;
    public ArrayList IiiiIIIiIi;
    public int iiiIIIIiiI;
    public int IIiiIIIiIi;
    public int IiIiIiiIiI;
    public int[] iiIiiiIiIi;
    public int iiIiIIiiii = 0;
    public byte[] iiIiiIiiiI = new byte[256];
    public int[] IiIIIiiiii;
    public boolean IiiIiIIIIi;
    public int IiIiiIIIii;
    public int iIiiiIIIIi;
    public short[] IiIiIIiiIi;
    public int iIIIIIiIiI = 0;
    public boolean iiiiIIiiIi;
    public int iIiIIIIiII;
    public Rectangle IIIiIIiIII;
    public int IIIiiiIIii;
    public int IIIIiiiIii;
    public int IIiiiIIiii;
    public static final int iiIiiIiIIi = 0;
    public BufferedImage IIiIIIIIIi;
    public byte[] IIIIiiiIII;
    public BufferedImage IIiIIiiIiI;
    public static final int IiIIiiIIiI = 2;
    public int iIIIiIIIIi;
    public static final int iIiIIiIiiI = 4096;
    public int iiIIIiIiII;
    public BufferedInputStream IIiiIiiIII;
    public boolean iIIiIIiIii;
    public int iIIIIiiIII;
    public boolean IIiIiIIIiI = false;
    public int IIiiiiiIIi;

    public int[] IIIiiiIiii(int IIiiiiiIIi) {
        int IIiiiiiIIi2 = 3 * IIiiiiiIIi;
        int[] IIiiiiiIIi3 = null;
        byte[] IIiiiiiIIi4 = new byte[IIiiiiiIIi2];
        int IIiiiiiIIi5 = 0;
        try {
            iiiiiiiiii_3 IIiiiiiIIi6;
            IIiiiiiIIi5 = IIiiiiiIIi6.IIiiIiiIII.read(IIiiiiiIIi4);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        if (IIiiiiiIIi5 < IIiiiiiIIi2) {
            IIiiiiiIIi6.iIIIIiiIII = 1;
        } else {
            IIiiiiiIIi3 = new int[256];
            int IIiiiiiIIi7 = 0;
            int IIiiiiiIIi8 = 0;
            while (IIiiiiiIIi7 < IIiiiiiIIi) {
                int IIiiiiiIIi9 = IIiiiiiIIi4[IIiiiiiIIi8++] & 0xFF;
                int IIiiiiiIIi10 = IIiiiiiIIi4[IIiiiiiIIi8++] & 0xFF;
                int IIiiiiiIIi11 = IIiiiiiIIi4[IIiiiiiIIi8++] & 0xFF;
                IIiiiiiIIi3[IIiiiiiIIi7++] = 0xFF000000 | IIiiiiiIIi9 << 16 | IIiiiiiIIi10 << 8 | IIiiiiiIIi11;
            }
        }
        return IIiiiiiIIi3;
    }

    public int IIIiiiIiii(String IIiiiiiIIi) {
        iiiiiiiiii_3 IIiiiiiIIi2;
        IIiiiiiIIi2.iIIIIiiIII = 0;
        try {
            IIiiiiiIIi = IIiiiiiIIi.trim().toLowerCase();
            if (IIiiiiiIIi.contains("file:") || IIiiiiiIIi.indexOf(":/") > 0) {
                URL IIiiiiiIIi3 = new URL(IIiiiiiIIi);
                IIiiiiiIIi2.IIiiIiiIII = new BufferedInputStream(IIiiiiiIIi2.IIIiiiIiii(IIiiiiiIIi3));
            } else {
                IIiiiiiIIi2.IIiiIiiIII = new BufferedInputStream(new FileInputStream(IIiiiiiIIi));
            }
            IIiiiiiIIi2.iIIIIiiIII = IIiiiiiIIi2.IIIiiiIiii(IIiiiiiIIi2.IIiiIiiIII);
        }
        catch (IOException IIiiiiiIIi4) {
            IIiiiiiIIi2.iIIIIiiIII = 2;
        }
        return IIiiiiiIIi2.iIIIIiiIII;
    }

    public int IIIiiiIiii(int IIiiiiiIIi) {
        iiiiiiiiii_3 IIiiiiiIIi2;
        IIiiiiiIIi2.iiiIIiIIiI = -1;
        if (IIiiiiiIIi >= 0 && IIiiiiiIIi < IIiiiiiIIi2.IIIiiiIIii) {
            IIiiiiiIIi2.iiiIIiIIiI = ((IIiiIIIIiI)IIiiiiiIIi2.IiiiIIIiIi.get((int)IIiiiiiIIi)).IIiiiiiIIi;
        }
        return IIiiiiiIIi2.iiiIIiIIiI;
    }

    public int IIIiiiIiii(BufferedInputStream IIiiiiiIIi) {
        iiiiiiiiii_3 IIiiiiiIIi2;
        IIiiiiiIIi2.IIIiiiIiii();
        if (IIiiiiiIIi != null) {
            IIiiiiiIIi2.IIiiIiiIII = IIiiiiiIIi;
            IIiiiiiIIi2.iiiIIIIIIi();
            if (!IIiiiiiIIi2.IIIiiiIiii()) {
                IIiiiiiIIi2.IIiiiiIiII();
                if (IIiiiiiIIi2.IIIiiiIIii < 0) {
                    IIiiiiiIIi2.iIIIIiiIII = 1;
                }
            }
        } else {
            IIiiiiiIIi2.iIIIIiiIII = 2;
        }
        try {
            IIiiiiiIIi.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return IIiiiiiIIi2.iIIIIiiIII;
    }

    public void IiiIIIiIII() {
        int IIiiiiiIIi;
        iiiiiiiiii_3 IIiiiiiIIi2;
        int[] IIiiiiiIIi3 = ((DataBufferInt)IIiiiiiIIi2.IIiIIIIIIi.getRaster().getDataBuffer()).getData();
        if (IIiiiiiIIi2.IIiiIIiIIi > 0) {
            if (IIiiiiiIIi2.IIiiIIiIIi == 3) {
                IIiiiiiIIi = IIiiiiiIIi2.IIIiiiIIii - 2;
                IIiiiiiIIi2.IIiIIiiIiI = IIiiiiiIIi > 0 ? IIiiiiiIIi2.IIIiiiIiii(IIiiiiiIIi - 1) : null;
            }
            if (IIiiiiiIIi2.IIiIIiiIiI != null) {
                int[] IIiiiiiIIi4 = ((DataBufferInt)IIiiiiiIIi2.IIiIIiiIiI.getRaster().getDataBuffer()).getData();
                System.arraycopy(IIiiiiiIIi4, 0, IIiiiiiIIi3, 0, IIiiiiiIIi2.iIiiiIIIIi * IIiiiiiIIi2.IIiiiiiIIi);
                if (IIiiiiiIIi2.IIiiIIiIIi == 2) {
                    Graphics2D IIiiiiiIIi5 = IIiiiiiIIi2.IIiIIIIIIi.createGraphics();
                    Color IIiiiiiIIi6 = null;
                    IIiiiiiIIi6 = IIiiiiiIIi2.IIiIiIIIiI ? new Color(0, 0, 0, 0) : new Color(IIiiiiiIIi2.iIIIiIIIIi);
                    IIiiiiiIIi5.setColor(IIiiiiiIIi6);
                    IIiiiiiIIi5.setComposite(AlphaComposite.Src);
                    IIiiiiiIIi5.fill(IIiiiiiIIi2.IIIiIIiIII);
                    IIiiiiiIIi5.dispose();
                }
            }
        }
        IIiiiiiIIi = 1;
        int IIiiiiiIIi7 = 8;
        int IIiiiiiIIi8 = 0;
        for (int IIiiiiiIIi9 = 0; IIiiiiiIIi9 < IIiiiiiIIi2.IIiiiIIiii; ++IIiiiiiIIi9) {
            int IIiiiiiIIi10 = IIiiiiiIIi9;
            if (IIiiiiiIIi2.iiiiIIiiIi) {
                if (IIiiiiiIIi8 >= IIiiiiiIIi2.IIiiiIIiii) {
                    switch (++IIiiiiiIIi) {
                        case 2: {
                            IIiiiiiIIi8 = 4;
                            break;
                        }
                        case 3: {
                            IIiiiiiIIi8 = 2;
                            IIiiiiiIIi7 = 4;
                            break;
                        }
                        case 4: {
                            IIiiiiiIIi8 = 1;
                            IIiiiiiIIi7 = 2;
                        }
                    }
                }
                IIiiiiiIIi10 = IIiiiiiIIi8;
                IIiiiiiIIi8 += IIiiiiiIIi7;
            }
            if ((IIiiiiiIIi10 += IIiiiiiIIi2.iiIiiiiIII) >= IIiiiiiIIi2.IIiiiiiIIi) continue;
            int IIiiiiiIIi11 = IIiiiiiIIi10 * IIiiiiiIIi2.iIiiiIIIIi;
            int IIiiiiiIIi12 = IIiiiiiIIi11 + IIiiiiiIIi2.IIIIiiiIii;
            int IIiiiiiIIi13 = IIiiiiiIIi12 + IIiiiiiIIi2.IIIIIIiiII;
            if (IIiiiiiIIi11 + IIiiiiiIIi2.iIiiiIIIIi < IIiiiiiIIi13) {
                IIiiiiiIIi13 = IIiiiiiIIi11 + IIiiiiiIIi2.iIiiiIIIIi;
            }
            int IIiiiiiIIi14 = IIiiiiiIIi9 * IIiiiiiIIi2.IIIIIIiiII;
            while (IIiiiiiIIi12 < IIiiiiiIIi13) {
                int IIiiiiiIIi15;
                int IIiiiiiIIi16;
                if ((IIiiiiiIIi16 = IIiiiiiIIi2.iiIiiiIiIi[IIiiiiiIIi15 = IIiiiiiIIi2.IiIiiIiiIi[IIiiiiiIIi14++] & 0xFF]) != 0) {
                    IIiiiiiIIi3[IIiiiiiIIi12] = IIiiiiiIIi16;
                }
                ++IIiiiiiIIi12;
            }
        }
    }

    public void IiIiiIiIIi() {
        iiiiiiiiii_3 IIiiiiiIIi;
        do {
            IIiiiiiIIi.iIIiiiIIiI();
        } while (IIiiiiiIIi.iIIIIIiIiI > 0 && !IIiiiiiIIi.IIIiiiIiii());
    }

    public void iiIiiiIiIi() {
        iiiiiiiiii_3 IIiiiiiIIi;
        do {
            IIiiiiiIIi.iIIiiiIIiI();
            if (IIiiiiiIIi.iiIiiIiiiI[0] != 1) continue;
            int IIiiiiiIIi2 = IIiiiiiIIi.iiIiiIiiiI[1] & 0xFF;
            int IIiiiiiIIi3 = IIiiiiiIIi.iiIiiIiiiI[2] & 0xFF;
            IIiiiiiIIi.IIIIIIIIIi = IIiiiiiIIi3 << 8 | IIiiiiiIIi2;
        } while (IIiiiiiIIi.iIIIIIiIiI > 0 && !IIiiiiiIIi.IIIiiiIiii());
    }

    public BufferedImage IIIiiiIiii() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return IIiiiiiIIi.IIIiiiIiii(0);
    }

    public void iiiIIIIIIi() {
        iiiiiiiiii_3 IIiiiiiIIi;
        String IIiiiiiIIi2 = "";
        for (int IIiiiiiIIi3 = 0; IIiiiiiIIi3 < 6; ++IIiiiiiIIi3) {
            IIiiiiiIIi2 = IIiiiiiIIi2 + (char)IIiiiiiIIi.IiiiiiIIIi();
        }
        if (!IIiiiiiIIi2.startsWith("GIF")) {
            IIiiiiiIIi.iIIIIiiIII = 1;
            return;
        }
        IIiiiiiIIi.IIiiiIIiiI();
        if (IIiiiiiIIi.IiiIiIIIIi && !IIiiiiiIIi.IIIiiiIiii()) {
            IIiiiiiIIi.IiIIIiiiii = IIiiiiiIIi.IIIiiiIiii(IIiiiiiIIi.iiiIIIIiiI);
            IIiiiiiIIi.IIiiIIIiIi = IIiiiiiIIi.IiIIIiiiii[IIiiiiiIIi.IiIiIiiIiI];
        }
    }

    public int IiiiiiIIIi() {
        int IIiiiiiIIi = 0;
        try {
            iiiiiiiiii_3 IIiiiiiIIi2;
            IIiiiiiIIi = IIiiiiiIIi2.IIiiIiiIII.read();
        }
        catch (IOException IIiiiiiIIi3) {
            IIiiiiiIIi2.iIIIIiiIII = 1;
        }
        return IIiiiiiIIi;
    }

    public int IIiiiIIiiI() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return IIiiiiiIIi.IIIiiiIIii;
    }

    public iiiiiiiiii_3() {
        iiiiiiiiii_3 IIiiiiiIIi;
    }

    public void IIiiiiIiII() {
        iiiiiiiiii_3 IIiiiiiIIi;
        boolean IIiiiiiIIi2 = false;
        block10: while (!IIiiiiiIIi2 && !IIiiiiiIIi.IIIiiiIiii()) {
            int IIiiiiiIIi3 = IIiiiiiIIi.IiiiiiIIIi();
            switch (IIiiiiiIIi3) {
                case 44: {
                    IIiiiiiIIi.iIIiiiIIiI();
                    continue block10;
                }
                case 33: {
                    IIiiiiiIIi3 = IIiiiiiIIi.IiiiiiIIIi();
                    switch (IIiiiiiIIi3) {
                        case 249: {
                            IIiiiiiIIi.IiiiiiIIiI();
                            continue block10;
                        }
                        case 255: {
                            IIiiiiiIIi.iIIiiiIIiI();
                            String IIiiiiiIIi4 = "";
                            for (int IIiiiiiIIi5 = 0; IIiiiiiIIi5 < 11; ++IIiiiiiIIi5) {
                                IIiiiiiIIi4 = IIiiiiiIIi4 + (char)IIiiiiiIIi.iiIiiIiiiI[IIiiiiiIIi5];
                            }
                            if (IIiiiiiIIi4.equals("NETSCAPE2.0")) {
                                IIiiiiiIIi.iiIiiiIiIi();
                                continue block10;
                            }
                            IIiiiiiIIi.IiIiiIiIIi();
                            continue block10;
                        }
                    }
                    IIiiiiiIIi.IiIiiIiIIi();
                    continue block10;
                }
                case 59: {
                    IIiiiiiIIi2 = true;
                    continue block10;
                }
                case 0: {
                    continue block10;
                }
            }
            IIiiiiiIIi.iIIIIiiIII = 1;
        }
    }

    public void iiIiiiIIIi() {
        int IIiiiiiIIi;
        iiiiiiiiii_3 IIiiiiiIIi2;
        int IIiiiiiIIi3 = -1;
        int IIiiiiiIIi4 = IIiiiiiIIi2.IIIIIIiiII * IIiiiiiIIi2.IIiiiIIiii;
        if (IIiiiiiIIi2.IiIiiIiiIi == null || IIiiiiiIIi2.IiIiiIiiIi.length < IIiiiiiIIi4) {
            IIiiiiiIIi2.IiIiiIiiIi = new byte[IIiiiiiIIi4];
        }
        if (IIiiiiiIIi2.IiIiIIiiIi == null) {
            IIiiiiiIIi2.IiIiIIiiIi = new short[4096];
        }
        if (IIiiiiiIIi2.IiiiIIIiII == null) {
            IIiiiiiIIi2.IiiiIIIiII = new byte[4096];
        }
        if (IIiiiiiIIi2.IIIIiiiIII == null) {
            IIiiiiiIIi2.IIIIiiiIII = new byte[4097];
        }
        int IIiiiiiIIi5 = IIiiiiiIIi2.IiiiiiIIIi();
        int IIiiiiiIIi6 = 1 << IIiiiiiIIi5;
        int IIiiiiiIIi7 = IIiiiiiIIi6 + 1;
        int IIiiiiiIIi8 = IIiiiiiIIi6 + 2;
        int IIiiiiiIIi9 = IIiiiiiIIi3;
        int IIiiiiiIIi10 = IIiiiiiIIi5 + 1;
        int IIiiiiiIIi11 = (1 << IIiiiiiIIi10) - 1;
        for (IIiiiiiIIi = 0; IIiiiiiIIi < IIiiiiiIIi6; ++IIiiiiiIIi) {
            IIiiiiiIIi2.IiIiIIiiIi[IIiiiiiIIi] = 0;
            IIiiiiiIIi2.IiiiIIIiII[IIiiiiiIIi] = (byte)IIiiiiiIIi;
        }
        int IIiiiiiIIi12 = 0;
        int IIiiiiiIIi13 = 0;
        int IIiiiiiIIi14 = 0;
        int IIiiiiiIIi15 = 0;
        int IIiiiiiIIi16 = 0;
        int IIiiiiiIIi17 = 0;
        int IIiiiiiIIi18 = 0;
        int IIiiiiiIIi19 = 0;
        while (IIiiiiiIIi19 < IIiiiiiIIi4) {
            if (IIiiiiiIIi14 == 0) {
                if (IIiiiiiIIi17 < IIiiiiiIIi10) {
                    if (IIiiiiiIIi16 == 0) {
                        IIiiiiiIIi16 = IIiiiiiIIi2.iIIiiiIIiI();
                        if (IIiiiiiIIi16 <= 0) break;
                        IIiiiiiIIi12 = 0;
                    }
                    IIiiiiiIIi18 += (IIiiiiiIIi2.iiIiiIiiiI[IIiiiiiIIi12] & 0xFF) << IIiiiiiIIi17;
                    IIiiiiiIIi17 += 8;
                    ++IIiiiiiIIi12;
                    --IIiiiiiIIi16;
                    continue;
                }
                IIiiiiiIIi = IIiiiiiIIi18 & IIiiiiiIIi11;
                IIiiiiiIIi18 >>= IIiiiiiIIi10;
                IIiiiiiIIi17 -= IIiiiiiIIi10;
                if (IIiiiiiIIi > IIiiiiiIIi8 || IIiiiiiIIi == IIiiiiiIIi7) break;
                if (IIiiiiiIIi == IIiiiiiIIi6) {
                    IIiiiiiIIi10 = IIiiiiiIIi5 + 1;
                    IIiiiiiIIi11 = (1 << IIiiiiiIIi10) - 1;
                    IIiiiiiIIi8 = IIiiiiiIIi6 + 2;
                    IIiiiiiIIi9 = IIiiiiiIIi3;
                    continue;
                }
                if (IIiiiiiIIi9 == IIiiiiiIIi3) {
                    IIiiiiiIIi2.IIIIiiiIII[IIiiiiiIIi14++] = IIiiiiiIIi2.IiiiIIIiII[IIiiiiiIIi];
                    IIiiiiiIIi9 = IIiiiiiIIi;
                    IIiiiiiIIi15 = IIiiiiiIIi;
                    continue;
                }
                int IIiiiiiIIi20 = IIiiiiiIIi;
                if (IIiiiiiIIi == IIiiiiiIIi8) {
                    IIiiiiiIIi2.IIIIiiiIII[IIiiiiiIIi14++] = (byte)IIiiiiiIIi15;
                    IIiiiiiIIi = IIiiiiiIIi9;
                }
                while (IIiiiiiIIi > IIiiiiiIIi6) {
                    IIiiiiiIIi2.IIIIiiiIII[IIiiiiiIIi14++] = IIiiiiiIIi2.IiiiIIIiII[IIiiiiiIIi];
                    IIiiiiiIIi = IIiiiiiIIi2.IiIiIIiiIi[IIiiiiiIIi];
                }
                IIiiiiiIIi15 = IIiiiiiIIi2.IiiiIIIiII[IIiiiiiIIi] & 0xFF;
                if (IIiiiiiIIi8 >= 4096) {
                    IIiiiiiIIi2.IIIIiiiIII[IIiiiiiIIi14++] = (byte)IIiiiiiIIi15;
                    continue;
                }
                IIiiiiiIIi2.IIIIiiiIII[IIiiiiiIIi14++] = (byte)IIiiiiiIIi15;
                IIiiiiiIIi2.IiIiIIiiIi[IIiiiiiIIi8] = (short)IIiiiiiIIi9;
                IIiiiiiIIi2.IiiiIIIiII[IIiiiiiIIi8] = (byte)IIiiiiiIIi15;
                if ((++IIiiiiiIIi8 & IIiiiiiIIi11) == 0 && IIiiiiiIIi8 < 4096) {
                    ++IIiiiiiIIi10;
                    IIiiiiiIIi11 += IIiiiiiIIi8;
                }
                IIiiiiiIIi9 = IIiiiiiIIi20;
            }
            IIiiiiiIIi2.IiIiiIiiIi[IIiiiiiIIi13++] = IIiiiiiIIi2.IIIIiiiIII[--IIiiiiiIIi14];
            ++IIiiiiiIIi19;
        }
        for (IIiiiiiIIi19 = IIiiiiiIIi13; IIiiiiiIIi19 < IIiiiiiIIi4; ++IIiiiiiIIi19) {
            IIiiiiiIIi2.IiIiiIiiIi[IIiiiiiIIi19] = 0;
        }
    }

    public void IiiiiiIIIi() {
        iiiiiiiiii_3 IIiiiiiIIi;
        IIiiiiiIIi.IIiiIIiIIi = IIiiiiiIIi.iiIiIIiiii;
        IIiiiiiIIi.IIIiIIiIII = new Rectangle(IIiiiiiIIi.IIIIiiiIii, IIiiiiiIIi.iiIiiiiIII, IIiiiiiIIi.IIIIIIiiII, IIiiiiiIIi.IIiiiIIiii);
        IIiiiiiIIi.IIiIIiiIiI = IIiiiiiIIi.IIiIIIIIIi;
        IIiiiiiIIi.iIIIiIIIIi = IIiiiiiIIi.IIiiIIIiIi;
        boolean IIiiiiiIIi2 = false;
        boolean IIiiiiiIIi3 = false;
        boolean IIiiiiiIIi4 = false;
        IIiiiiiIIi.IiiIIIIiii = null;
    }

    public void IIiiiIIiiI() {
        iiiiiiiiii_3 IIiiiiiIIi;
        IIiiiiiIIi.iIiiiIIIIi = IIiiiiiIIi.IIIiiiIiii();
        IIiiiiiIIi.IIiiiiiIIi = IIiiiiiIIi.IIIiiiIiii();
        int IIiiiiiIIi2 = IIiiiiiIIi.IiiiiiIIIi();
        IIiiiiiIIi.IiiIiIIIIi = (IIiiiiiIIi2 & 0x80) != 0;
        IIiiiiiIIi.iiiIIIIiiI = 2 << (IIiiiiiIIi2 & 7);
        IIiiiiiIIi.IiIiIiiIiI = IIiiiiiIIi.IiiiiiIIIi();
        IIiiiiiIIi.iIiIIIIiII = IIiiiiiIIi.IiiiiiIIIi();
    }

    public Dimension IIIiiiIiii() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return new Dimension(IIiiiiiIIi.iIiiiIIIIi, IIiiiiiIIi.IIiiiiiIIi);
    }

    private /* synthetic */ InputStream IIIiiiIiii(URL IIiiiiiIIi) throws IOException {
        int IIiiiiiIIi2;
        InputStream IIiiiiiIIi3 = IIiiiiiIIi.openStream();
        ByteArrayOutputStream IIiiiiiIIi4 = new ByteArrayOutputStream();
        byte[] IIiiiiiIIi5 = new byte[1024];
        while ((IIiiiiiIIi2 = IIiiiiiIIi3.read(IIiiiiiIIi5)) > -1) {
            IIiiiiiIIi4.write(IIiiiiiIIi5, 0, IIiiiiiIIi2);
        }
        IIiiiiiIIi4.flush();
        return new ByteArrayInputStream(IIiiiiiIIi4.toByteArray());
    }

    public void IiiiiiIIiI() {
        iiiiiiiiii_3 IIiiiiiIIi;
        IIiiiiiIIi.IiiiiiIIIi();
        int IIiiiiiIIi2 = IIiiiiiIIi.IiiiiiIIIi();
        IIiiiiiIIi.iiIiIIiiii = (IIiiiiiIIi2 & 0x1C) >> 2;
        if (IIiiiiiIIi.iiIiIIiiii == 0) {
            IIiiiiiIIi.iiIiIIiiii = 1;
        }
        IIiiiiiIIi.IIiIiIIIiI = (IIiiiiiIIi2 & 1) != 0;
        IIiiiiiIIi.iiiIIiIIiI = IIiiiiiIIi.IIIiiiIiii() * 10;
        IIiiiiiIIi.iiIIIiIiII = IIiiiiiIIi.IiiiiiIIIi();
        IIiiiiiIIi.IiiiiiIIIi();
    }

    public boolean IIIiiiIiii() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return IIiiiiiIIi.iIIIIiiIII != 0;
    }

    public int IiiiiiIIiI() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return IIiiiiiIIi.IIIIIIIIIi;
    }

    public void iIIiiiIIiI() {
        iiiiiiiiii_3 IIiiiiiIIi;
        IIiiiiiIIi.IIIIiiiIii = IIiiiiiIIi.IIIiiiIiii();
        IIiiiiiIIi.iiIiiiiIII = IIiiiiiIIi.IIIiiiIiii();
        IIiiiiiIIi.IIIIIIiiII = IIiiiiiIIi.IIIiiiIiii();
        IIiiiiiIIi.IIiiiIIiii = IIiiiiiIIi.IIIiiiIiii();
        int IIiiiiiIIi2 = IIiiiiiIIi.IiiiiiIIIi();
        IIiiiiiIIi.iIIiIIiIii = (IIiiiiiIIi2 & 0x80) != 0;
        IIiiiiiIIi.iiiiIIiiIi = (IIiiiiiIIi2 & 0x40) != 0;
        IIiiiiiIIi.IiIiiIIIii = 2 << (IIiiiiiIIi2 & 7);
        if (IIiiiiiIIi.iIIiIIiIii) {
            IIiiiiiIIi.IiiIIIIiii = IIiiiiiIIi.IIIiiiIiii(IIiiiiiIIi.IiIiiIIIii);
            IIiiiiiIIi.iiIiiiIiIi = IIiiiiiIIi.IiiIIIIiii;
        } else {
            IIiiiiiIIi.iiIiiiIiIi = IIiiiiiIIi.IiIIIiiiii;
            if (IIiiiiiIIi.IiIiIiiIiI == IIiiiiiIIi.iiIIIiIiII) {
                IIiiiiiIIi.IIiiIIIiIi = 0;
            }
        }
        int IIiiiiiIIi3 = 0;
        if (IIiiiiiIIi.IIiIiIIIiI) {
            IIiiiiiIIi3 = IIiiiiiIIi.iiIiiiIiIi[IIiiiiiIIi.iiIIIiIiII];
            IIiiiiiIIi.iiIiiiIiIi[IIiiiiiIIi.iiIIIiIiII] = 0;
        }
        if (IIiiiiiIIi.iiIiiiIiIi == null) {
            IIiiiiiIIi.iIIIIiiIII = 1;
        }
        if (IIiiiiiIIi.IIIiiiIiii()) {
            return;
        }
        IIiiiiiIIi.iiIiiiIIIi();
        IIiiiiiIIi.IiIiiIiIIi();
        if (IIiiiiiIIi.IIIiiiIiii()) {
            return;
        }
        ++IIiiiiiIIi.IIIiiiIIii;
        IIiiiiiIIi.IIiIIIIIIi = new BufferedImage(IIiiiiiIIi.iIiiiIIIIi, IIiiiiiIIi.IIiiiiiIIi, 3);
        IIiiiiiIIi.IiiIIIiIII();
        IIiiiiiIIi.IiiiIIIiIi.add(new IIiiIIIIiI(IIiiiiiIIi.IIiIIIIIIi, IIiiiiiIIi.iiiIIiIIiI));
        if (IIiiiiiIIi.IIiIiIIIiI) {
            IIiiiiiIIi.iiIiiiIiIi[IIiiiiiIIi.iiIIIiIiII] = IIiiiiiIIi3;
        }
        IIiiiiiIIi.IiiiiiIIIi();
    }

    public int IIIiiiIiii(InputStream IIiiiiiIIi) {
        iiiiiiiiii_3 IIiiiiiIIi2;
        IIiiiiiIIi2.IIIiiiIiii();
        if (IIiiiiiIIi != null) {
            if (!(IIiiiiiIIi instanceof BufferedInputStream)) {
                IIiiiiiIIi = new BufferedInputStream(IIiiiiiIIi);
            }
            IIiiiiiIIi2.IIiiIiiIII = (BufferedInputStream)IIiiiiiIIi;
            IIiiiiiIIi2.iiiIIIIIIi();
            if (!IIiiiiiIIi2.IIIiiiIiii()) {
                IIiiiiiIIi2.IIiiiiIiII();
                if (IIiiiiiIIi2.IIIiiiIIii < 0) {
                    IIiiiiiIIi2.iIIIIiiIII = 1;
                }
            }
        } else {
            IIiiiiiIIi2.iIIIIiiIII = 2;
        }
        try {
            IIiiiiiIIi.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return IIiiiiiIIi2.iIIIIiiIII;
    }

    public int iIIiiiIIiI() {
        int IIiiiiiIIi;
        iiiiiiiiii_3 IIiiiiiIIi2;
        IIiiiiiIIi2.iIIIIIiIiI = IIiiiiiIIi2.IiiiiiIIIi();
        if (IIiiiiiIIi2.iIIIIIiIiI > 0) {
            try {
                int IIiiiiiIIi3 = 0;
                for (IIiiiiiIIi = 0; IIiiiiiIIi < IIiiiiiIIi2.iIIIIIiIiI && (IIiiiiiIIi3 = IIiiiiiIIi2.IIiiIiiIII.read(IIiiiiiIIi2.iiIiiIiiiI, IIiiiiiIIi, IIiiiiiIIi2.iIIIIIiIiI - IIiiiiiIIi)) != -1; IIiiiiiIIi += IIiiiiiIIi3) {
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            if (IIiiiiiIIi < IIiiiiiIIi2.iIIIIIiIiI) {
                IIiiiiiIIi2.iIIIIiiIII = 1;
            }
        }
        return IIiiiiiIIi;
    }

    public void IIIiiiIiii() {
        IIiiiiiIIi.iIIIIiiIII = 0;
        IIiiiiiIIi.IIIiiiIIii = 0;
        IIiiiiiIIi.IiiiIIIiIi = new ArrayList();
        IIiiiiiIIi.IiIIIiiiii = null;
        IIiiiiiIIi.IiiIIIIiii = null;
    }

    public int IIIiiiIiii() {
        iiiiiiiiii_3 IIiiiiiIIi;
        return IIiiiiiIIi.IiiiiiIIIi() | IIiiiiiIIi.IiiiiiIIIi() << 8;
    }

    public BufferedImage IIIiiiIiii(int IIiiiiiIIi) {
        iiiiiiiiii_3 IIiiiiiIIi2;
        BufferedImage IIiiiiiIIi3 = null;
        if (IIiiiiiIIi >= 0 && IIiiiiiIIi < IIiiiiiIIi2.IIIiiiIIii) {
            IIiiiiiIIi3 = ((IIiiIIIIiI)IIiiiiiIIi2.IiiiIIIiIi.get((int)IIiiiiiIIi)).IIiIiIIIiI;
        }
        return IIiiiiiIIi3;
    }
}

