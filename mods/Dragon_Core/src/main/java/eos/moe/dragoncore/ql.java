/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.ITextComponent$Serializer
 *  net.minecraft.util.text.TextComponentString
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.en;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.kn;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ql {
    public ql() {
        ql a2;
    }

    @i(f={"\u538b\u7f29\u6570\u636e", "String_compress"})
    public static String c(String a2) {
        return Base64.getEncoder().encodeToString(ql.ALLATORIxDEMO(a2.getBytes(StandardCharsets.UTF_8)));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] ALLATORIxDEMO(byte[] a2) {
        ByteArrayOutputStream a3 = new ByteArrayOutputStream();
        GZIPOutputStream a4 = null;
        try {
            a4 = new GZIPOutputStream(a3);
            a4.write(a2);
            a4.close();
        }
        catch (IOException a5) {
            a5.printStackTrace();
            IOUtils.closeQuietly((OutputStream)a3);
            IOUtils.closeQuietly((OutputStream)a4);
            byte[] byArray = null;
            return byArray;
        }
        finally {
            IOUtils.closeQuietly((OutputStream)a3);
            IOUtils.closeQuietly((OutputStream)a4);
        }
        return a3.toByteArray();
    }

    @i(f={"\u5408\u5e76\u6587\u672c", "String_Concat"})
    public static String c(String ... a2) {
        return String.join((CharSequence)"", a2);
    }

    @i(f={"\u5408\u5e76\u52a0\u5165\u6587\u672c", "String_Join"})
    public static String ALLATORIxDEMO(String ... a2) {
        return String.join((CharSequence)"", a2);
    }

    @i(f={"\u53d6\u6587\u672c\u5bbd\u5ea6", "String_Get_Width"})
    public static int ALLATORIxDEMO(v a2, String a3, boolean a4) {
        int a5 = 0;
        if (a2 instanceof qg) {
            qg a6 = (qg)a2;
            for (v a7 : a6.ALLATORIxDEMO()) {
                a5 = Math.max(ql.ALLATORIxDEMO(a7.c(), a3, a4), a5);
            }
        } else {
            a5 = Math.max(ql.ALLATORIxDEMO(a2.c(), a3, a4), a5);
        }
        return a5;
    }

    public static int ALLATORIxDEMO(String a2, String a3, boolean a4) {
        if (a4) {
            a2 = a2.replace("&", "\u00a7");
        }
        List<String> a5 = sj.ALLATORIxDEMO(a2, 0, a3);
        int a6 = 0;
        for (String a7 : a5) {
            a6 = Math.max(a6, ol.ALLATORIxDEMO(a7, a3, false));
        }
        return a6;
    }

    @i(f={"\u53d6\u6587\u672c\u9ad8\u5ea6", "String_Get_Height"})
    public static int f(String a2, String a3) {
        return sj.ALLATORIxDEMO(a2, 0, a3).size() * 10;
    }

    @i(f={"\u6309\u5bbd\u5ea6\u5206\u5272", "String_SplitWithWidth"})
    public static qg ALLATORIxDEMO(v a2, String a3, int a4) {
        if (a2 instanceof kn) {
            return new qg(sj.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a4, a3).stream().map(kn::new).collect(Collectors.toList()));
        }
        return new qg(sj.ALLATORIxDEMO(a2.c(), a4, a3).stream().map(xf::new).collect(Collectors.toList()));
    }

    @i(f={"\u53d6\u6587\u672c\u957f\u5ea6", "String_Get_Length"})
    public static int ALLATORIxDEMO(String a2) {
        return a2.length();
    }

    @i(f={"\u5230\u6574\u6570", "String_ToInt"})
    public static String ALLATORIxDEMO(double a2) {
        return String.valueOf((long)a2);
    }

    @i(f={"\u622a\u53d6\u6587\u672c", "String_SubString"})
    public static String ALLATORIxDEMO(String a2, int a3, v a4) {
        a3 = Math.min(a3, a2.length());
        int a5 = a4 == wk.k ? a2.length() : (int)a4.ALLATORIxDEMO();
        return a2.substring(a3, Math.min(a5, a2.length()));
    }

    @i(f={"\u662f\u5426\u5b58\u5728\u524d\u7f00", "\u6709\u524d\u7f00", "String_HasPrefix"})
    public static boolean f(String a2, String a3) {
        return a2.startsWith(a3);
    }

    @i(f={"\u662f\u5426\u5b58\u5728\u540e\u7f00", "\u6709\u540e\u7f00", "String_HasSuffix"})
    public static boolean c(String a2, String a3) {
        return a2.endsWith(a3);
    }

    @i(f={"\u662f\u5426\u5305\u542b", "\u5305\u542b", "String_Contains"})
    public static boolean ALLATORIxDEMO(String a2, String a3) {
        return a2.contains(a3);
    }

    @i(f={"\u53d6\u7d22\u5f15", "String_IndexOf"})
    public static int c(String a2, String a3) {
        return a2.indexOf(a3);
    }

    @i(f={"\u5012\u53d6\u7d22\u5f15", "String_LastIndexOf"})
    public static int ALLATORIxDEMO(String a2, String a3) {
        return a2.lastIndexOf(a3);
    }

    @i(f={"\u53d6\u7d22\u5f15\u6587\u672c", "String_CharAt"})
    public static String ALLATORIxDEMO(String a2, int a3) {
        return String.valueOf(a2.charAt(a3));
    }

    @i(f={"\u66ff\u6362", "String_Replace"})
    public static String f(String a2, String a3, String a4) {
        return a2.replace(a3, a4);
    }

    @i(f={"\u66ff\u6362\u9996\u6b21", "String_ReplaceFirst"})
    public static String c(String a2, String a3, String a4) {
        return a2.replaceFirst(a3, a4);
    }

    @i(f={"\u66ff\u6362\u6b63\u5219", "String_ReplaceAll"})
    public static String ALLATORIxDEMO(String a2, String a3, String a4) {
        return a2.replaceAll(a3, a4);
    }

    @i(f={"\u53bb\u989c\u8272", "String_StripColor"})
    public static String ALLATORIxDEMO(String a2) {
        return en.ALLATORIxDEMO(a2);
    }

    @i(f={"\u5206\u5272", "String_Split"})
    public static v ALLATORIxDEMO(String a2, String a3) {
        return new qg(Arrays.asList(a2.split(a3)), false);
    }

    @i(f={"\u683c\u5f0f\u6570\u5b57", "Decimal_Format"})
    public static String ALLATORIxDEMO(double a2, String a3) {
        return new DecimalFormat(a3).format(a2);
    }

    @i(f={"toTellraw"})
    public static kn ALLATORIxDEMO(String a2) {
        try {
            ITextComponent a3 = ITextComponent.Serializer.jsonToComponent((String)a2);
            a3 = a3 == null ? new TextComponentString("") : a3;
            return new kn(a3);
        }
        catch (Exception a4) {
            return new kn((ITextComponent)new TextComponentString(""));
        }
    }
}

