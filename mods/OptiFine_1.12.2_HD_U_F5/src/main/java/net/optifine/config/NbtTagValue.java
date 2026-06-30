/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fx
 *  fy
 *  fz
 *  gb
 *  gd
 *  ge
 *  gg
 *  gl
 *  gm
 *  gn
 *  org.apache.commons.lang3.StringEscapeUtils
 */
package net.optifine.config;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue {
    private String[] parents = null;
    private String name = null;
    private boolean negative = false;
    private int type = 0;
    private String value = null;
    private int valueFormat = 0;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_PATTERN = 1;
    private static final int TYPE_IPATTERN = 2;
    private static final int TYPE_REGEX = 3;
    private static final int TYPE_IREGEX = 4;
    private static final String PREFIX_PATTERN = "pattern:";
    private static final String PREFIX_IPATTERN = "ipattern:";
    private static final String PREFIX_REGEX = "regex:";
    private static final String PREFIX_IREGEX = "iregex:";
    private static final int FORMAT_DEFAULT = 0;
    private static final int FORMAT_HEX_COLOR = 1;
    private static final String PREFIX_HEX_COLOR = "#";
    private static final Pattern PATTERN_HEX_COLOR = Pattern.compile("^#[0-9a-f]{6}+$");

    public NbtTagValue(String tag, String value) {
        String[] names = Config.tokenize(tag, ".");
        this.parents = Arrays.copyOfRange(names, 0, names.length - 1);
        this.name = names[names.length - 1];
        if (value.startsWith("!")) {
            this.negative = true;
            value = value.substring(1);
        }
        if (value.startsWith(PREFIX_PATTERN)) {
            this.type = 1;
            value = value.substring(PREFIX_PATTERN.length());
        } else if (value.startsWith(PREFIX_IPATTERN)) {
            this.type = 2;
            value = value.substring(PREFIX_IPATTERN.length()).toLowerCase();
        } else if (value.startsWith(PREFIX_REGEX)) {
            this.type = 3;
            value = value.substring(PREFIX_REGEX.length());
        } else if (value.startsWith(PREFIX_IREGEX)) {
            this.type = 4;
            value = value.substring(PREFIX_IREGEX.length()).toLowerCase();
        } else {
            this.type = 0;
        }
        value = StringEscapeUtils.unescapeJava((String)value);
        if (this.type == 0 && PATTERN_HEX_COLOR.matcher(value).matches()) {
            this.valueFormat = 1;
        }
        this.value = value;
    }

    public boolean matches(fy nbt) {
        if (this.negative) {
            return !this.matchesCompound(nbt);
        }
        return this.matchesCompound(nbt);
    }

    public boolean matchesCompound(fy nbt) {
        if (nbt == null) {
            return false;
        }
        fy tagBase = nbt;
        for (int i = 0; i < this.parents.length; ++i) {
            String tag = this.parents[i];
            if ((tagBase = NbtTagValue.getChildTag((gn)tagBase, tag)) != null) continue;
            return false;
        }
        if (this.name.equals("*")) {
            return this.matchesAnyChild((gn)tagBase);
        }
        if ((tagBase = NbtTagValue.getChildTag((gn)tagBase, this.name)) == null) {
            return false;
        }
        return this.matchesBase((gn)tagBase);
    }

    private boolean matchesAnyChild(gn tagBase) {
        if (tagBase instanceof fy) {
            fy tagCompound = (fy)tagBase;
            Set nbtKeySet = tagCompound.c();
            for (String key : nbtKeySet) {
                gn nbtBase = tagCompound.c(key);
                if (!this.matchesBase(nbtBase)) continue;
                return true;
            }
        }
        if (tagBase instanceof ge) {
            ge tagList = (ge)tagBase;
            int count = tagList.c();
            for (int i = 0; i < count; ++i) {
                gn nbtBase = tagList.i(i);
                if (!this.matchesBase(nbtBase)) continue;
                return true;
            }
        }
        return false;
    }

    private static gn getChildTag(gn tagBase, String tag) {
        if (tagBase instanceof fy) {
            fy tagCompound = (fy)tagBase;
            return tagCompound.c(tag);
        }
        if (tagBase instanceof ge) {
            ge tagList = (ge)tagBase;
            if (tag.equals("count")) {
                return new gd(tagList.c());
            }
            int index = Config.parseInt(tag, -1);
            if (index < 0 || index >= tagList.c()) {
                return null;
            }
            return tagList.i(index);
        }
        return null;
    }

    public boolean matchesBase(gn nbtBase) {
        if (nbtBase == null) {
            return false;
        }
        String nbtValue = NbtTagValue.getNbtString(nbtBase, this.valueFormat);
        return this.matchesValue(nbtValue);
    }

    public boolean matchesValue(String nbtValue) {
        if (nbtValue == null) {
            return false;
        }
        switch (this.type) {
            case 0: {
                return nbtValue.equals(this.value);
            }
            case 1: {
                return this.matchesPattern(nbtValue, this.value);
            }
            case 2: {
                return this.matchesPattern(nbtValue.toLowerCase(), this.value);
            }
            case 3: {
                return this.matchesRegex(nbtValue, this.value);
            }
            case 4: {
                return this.matchesRegex(nbtValue.toLowerCase(), this.value);
            }
        }
        throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
    }

    private boolean matchesPattern(String str, String pattern) {
        return StrUtils.equalsMask(str, pattern, '*', '?');
    }

    private boolean matchesRegex(String str, String regex) {
        return str.matches(regex);
    }

    private static String getNbtString(gn nbtBase, int format) {
        if (nbtBase == null) {
            return null;
        }
        if (nbtBase instanceof gm) {
            gm nbtString = (gm)nbtBase;
            return nbtString.c_();
        }
        if (nbtBase instanceof gd) {
            gd i = (gd)nbtBase;
            if (format == 1) {
                return PREFIX_HEX_COLOR + StrUtils.fillLeft(Integer.toHexString(i.e()), 6, '0');
            }
            return Integer.toString(i.e());
        }
        if (nbtBase instanceof fx) {
            fx b2 = (fx)nbtBase;
            return Byte.toString(b2.g());
        }
        if (nbtBase instanceof gl) {
            gl s = (gl)nbtBase;
            return Short.toString(s.f());
        }
        if (nbtBase instanceof gg) {
            gg l = (gg)nbtBase;
            return Long.toString(l.d());
        }
        if (nbtBase instanceof gb) {
            gb f = (gb)nbtBase;
            return Float.toString(f.i());
        }
        if (nbtBase instanceof fz) {
            fz d2 = (fz)nbtBase;
            return Double.toString(d2.h());
        }
        return nbtBase.toString();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.parents.length; ++i) {
            String parent = this.parents[i];
            if (i > 0) {
                sb.append(".");
            }
            sb.append(parent);
        }
        if (sb.length() > 0) {
            sb.append(".");
        }
        sb.append(this.name);
        sb.append(" = ");
        sb.append(this.value);
        return sb.toString();
    }
}

