/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StrUtils {
    public static boolean equalsMask(String str, String mask, char wildChar, char wildCharSingle) {
        String startTok;
        if (mask == null || str == null) {
            return mask == str;
        }
        if (mask.indexOf(wildChar) < 0) {
            if (mask.indexOf(wildCharSingle) < 0) {
                return mask.equals(str);
            }
            return StrUtils.equalsMaskSingle(str, mask, wildCharSingle);
        }
        ArrayList<String> tokens = new ArrayList<String>();
        String wildCharStr = "" + wildChar;
        if (mask.startsWith(wildCharStr)) {
            tokens.add("");
        }
        StringTokenizer tok = new StringTokenizer(mask, wildCharStr);
        while (tok.hasMoreElements()) {
            tokens.add(tok.nextToken());
        }
        if (mask.endsWith(wildCharStr)) {
            tokens.add("");
        }
        if (!StrUtils.startsWithMaskSingle(str, startTok = (String)tokens.get(0), wildCharSingle)) {
            return false;
        }
        String endTok = (String)tokens.get(tokens.size() - 1);
        if (!StrUtils.endsWithMaskSingle(str, endTok, wildCharSingle)) {
            return false;
        }
        int currPos = 0;
        for (int i = 0; i < tokens.size(); ++i) {
            String token = (String)tokens.get(i);
            if (token.length() <= 0) continue;
            int foundPos = StrUtils.indexOfMaskSingle(str, token, currPos, wildCharSingle);
            if (foundPos >= 0) {
                currPos = foundPos + token.length();
                continue;
            }
            return false;
        }
        return true;
    }

    private static boolean equalsMaskSingle(String str, String mask, char wildCharSingle) {
        if (str == null || mask == null) {
            return str == mask;
        }
        if (str.length() != mask.length()) {
            return false;
        }
        for (int i = 0; i < mask.length(); ++i) {
            char maskChar = mask.charAt(i);
            if (maskChar == wildCharSingle || str.charAt(i) == maskChar) continue;
            return false;
        }
        return true;
    }

    private static int indexOfMaskSingle(String str, String mask, int startPos, char wildCharSingle) {
        if (str == null || mask == null) {
            return -1;
        }
        if (startPos < 0 || startPos > str.length()) {
            return -1;
        }
        if (str.length() < startPos + mask.length()) {
            return -1;
        }
        int i = startPos;
        while (i + mask.length() <= str.length()) {
            String subStr = str.substring(i, i + mask.length());
            if (StrUtils.equalsMaskSingle(subStr, mask, wildCharSingle)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    private static boolean endsWithMaskSingle(String str, String mask, char wildCharSingle) {
        if (str == null || mask == null) {
            return str == mask;
        }
        if (str.length() < mask.length()) {
            return false;
        }
        String subStr = str.substring(str.length() - mask.length(), str.length());
        return StrUtils.equalsMaskSingle(subStr, mask, wildCharSingle);
    }

    private static boolean startsWithMaskSingle(String str, String mask, char wildCharSingle) {
        if (str == null || mask == null) {
            return str == mask;
        }
        if (str.length() < mask.length()) {
            return false;
        }
        String subStr = str.substring(0, mask.length());
        return StrUtils.equalsMaskSingle(subStr, mask, wildCharSingle);
    }

    public static boolean equalsMask(String str, String[] masks, char wildChar) {
        for (int i = 0; i < masks.length; ++i) {
            String mask = masks[i];
            if (!StrUtils.equalsMask(str, mask, wildChar)) continue;
            return true;
        }
        return false;
    }

    public static boolean equalsMask(String str, String mask, char wildChar) {
        String startTok;
        if (mask == null || str == null) {
            return mask == str;
        }
        if (mask.indexOf(wildChar) < 0) {
            return mask.equals(str);
        }
        ArrayList<String> tokens = new ArrayList<String>();
        String wildCharStr = "" + wildChar;
        if (mask.startsWith(wildCharStr)) {
            tokens.add("");
        }
        StringTokenizer tok = new StringTokenizer(mask, wildCharStr);
        while (tok.hasMoreElements()) {
            tokens.add(tok.nextToken());
        }
        if (mask.endsWith(wildCharStr)) {
            tokens.add("");
        }
        if (!str.startsWith(startTok = (String)tokens.get(0))) {
            return false;
        }
        String endTok = (String)tokens.get(tokens.size() - 1);
        if (!str.endsWith(endTok)) {
            return false;
        }
        int currPos = 0;
        for (int i = 0; i < tokens.size(); ++i) {
            String token = (String)tokens.get(i);
            if (token.length() <= 0) continue;
            int foundPos = str.indexOf(token, currPos);
            if (foundPos >= 0) {
                currPos = foundPos + token.length();
                continue;
            }
            return false;
        }
        return true;
    }

    public static String[] split(String str, String separators) {
        if (str == null || str.length() <= 0) {
            return new String[0];
        }
        if (separators == null) {
            return new String[]{str};
        }
        ArrayList<String> tokens = new ArrayList<String>();
        int startPos = 0;
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (!StrUtils.equals(ch, separators)) continue;
            tokens.add(str.substring(startPos, i));
            startPos = i + 1;
        }
        tokens.add(str.substring(startPos, str.length()));
        return tokens.toArray(new String[tokens.size()]);
    }

    private static boolean equals(char ch, String matches) {
        for (int i = 0; i < matches.length(); ++i) {
            if (matches.charAt(i) != ch) continue;
            return true;
        }
        return false;
    }

    public static boolean equalsTrim(String a2, String b2) {
        if (a2 != null) {
            a2 = a2.trim();
        }
        if (b2 != null) {
            b2 = b2.trim();
        }
        return StrUtils.equals(a2, (Object)b2);
    }

    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        return string.trim().length() <= 0;
    }

    public static String stringInc(String str) {
        int val = StrUtils.parseInt(str, -1);
        if (val == -1) {
            return "";
        }
        String test = "" + ++val;
        if (test.length() > str.length()) {
            return "";
        }
        return StrUtils.fillLeft("" + val, str.length(), '0');
    }

    public static int parseInt(String s, int defVal) {
        if (s == null) {
            return defVal;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static boolean isFilled(String string) {
        return !StrUtils.isEmpty(string);
    }

    public static String addIfNotContains(String target, String source) {
        for (int i = 0; i < source.length(); ++i) {
            if (target.indexOf(source.charAt(i)) >= 0) continue;
            target = target + source.charAt(i);
        }
        return target;
    }

    public static String fillLeft(String s, int len, char fillChar) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= len) {
            return s;
        }
        StringBuffer buf = new StringBuffer();
        int bufLen = len - s.length();
        while (buf.length() < bufLen) {
            buf.append(fillChar);
        }
        return buf.toString() + s;
    }

    public static String fillRight(String s, int len, char fillChar) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= len) {
            return s;
        }
        StringBuffer buf = new StringBuffer(s);
        while (buf.length() < len) {
            buf.append(fillChar);
        }
        return buf.toString();
    }

    public static boolean equals(Object a2, Object b2) {
        if (a2 == b2) {
            return true;
        }
        if (a2 != null && a2.equals(b2)) {
            return true;
        }
        return b2 != null && b2.equals(a2);
    }

    public static boolean startsWith(String str, String[] prefixes) {
        if (str == null) {
            return false;
        }
        if (prefixes == null) {
            return false;
        }
        for (int i = 0; i < prefixes.length; ++i) {
            String prefix = prefixes[i];
            if (!str.startsWith(prefix)) continue;
            return true;
        }
        return false;
    }

    public static boolean endsWith(String str, String[] suffixes) {
        if (str == null) {
            return false;
        }
        if (suffixes == null) {
            return false;
        }
        for (int i = 0; i < suffixes.length; ++i) {
            String suffix = suffixes[i];
            if (!str.endsWith(suffix)) continue;
            return true;
        }
        return false;
    }

    public static String removePrefix(String str, String prefix) {
        if (str == null || prefix == null) {
            return str;
        }
        if (str.startsWith(prefix)) {
            str = str.substring(prefix.length());
        }
        return str;
    }

    public static String removeSuffix(String str, String suffix) {
        if (str == null || suffix == null) {
            return str;
        }
        if (str.endsWith(suffix)) {
            str = str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    public static String replaceSuffix(String str, String suffix, String suffixNew) {
        if (str == null || suffix == null) {
            return str;
        }
        if (!str.endsWith(suffix)) {
            return str;
        }
        if (suffixNew == null) {
            suffixNew = "";
        }
        str = str.substring(0, str.length() - suffix.length());
        return str + suffixNew;
    }

    public static String replacePrefix(String str, String prefix, String prefixNew) {
        if (str == null || prefix == null) {
            return str;
        }
        if (!str.startsWith(prefix)) {
            return str;
        }
        if (prefixNew == null) {
            prefixNew = "";
        }
        str = str.substring(prefix.length());
        return prefixNew + str;
    }

    public static int findPrefix(String[] strs, String prefix) {
        if (strs == null || prefix == null) {
            return -1;
        }
        for (int i = 0; i < strs.length; ++i) {
            String str = strs[i];
            if (!str.startsWith(prefix)) continue;
            return i;
        }
        return -1;
    }

    public static int findSuffix(String[] strs, String suffix) {
        if (strs == null || suffix == null) {
            return -1;
        }
        for (int i = 0; i < strs.length; ++i) {
            String str = strs[i];
            if (!str.endsWith(suffix)) continue;
            return i;
        }
        return -1;
    }

    public static String[] remove(String[] strs, int start, int end) {
        if (strs == null) {
            return strs;
        }
        if (end <= 0 || start >= strs.length) {
            return strs;
        }
        if (start >= end) {
            return strs;
        }
        ArrayList<String> list = new ArrayList<String>(strs.length);
        for (int i = 0; i < strs.length; ++i) {
            String str = strs[i];
            if (i >= start && i < end) continue;
            list.add(str);
        }
        String[] strsNew = list.toArray(new String[list.size()]);
        return strsNew;
    }

    public static String removeSuffix(String str, String[] suffixes) {
        String suffix;
        if (str == null || suffixes == null) {
            return str;
        }
        int strLen = str.length();
        for (int i = 0; i < suffixes.length && (str = StrUtils.removeSuffix(str, suffix = suffixes[i])).length() == strLen; ++i) {
        }
        return str;
    }

    public static String removePrefix(String str, String[] prefixes) {
        String prefix;
        if (str == null || prefixes == null) {
            return str;
        }
        int strLen = str.length();
        for (int i = 0; i < prefixes.length && (str = StrUtils.removePrefix(str, prefix = prefixes[i])).length() == strLen; ++i) {
        }
        return str;
    }

    public static String removePrefixSuffix(String str, String[] prefixes, String[] suffixes) {
        str = StrUtils.removePrefix(str, prefixes);
        str = StrUtils.removeSuffix(str, suffixes);
        return str;
    }

    public static String removePrefixSuffix(String str, String prefix, String suffix) {
        return StrUtils.removePrefixSuffix(str, new String[]{prefix}, new String[]{suffix});
    }

    public static String getSegment(String str, String start, String end) {
        if (str == null || start == null || end == null) {
            return null;
        }
        int posStart = str.indexOf(start);
        if (posStart < 0) {
            return null;
        }
        int posEnd = str.indexOf(end, posStart);
        if (posEnd < 0) {
            return null;
        }
        return str.substring(posStart, posEnd + end.length());
    }

    public static String addSuffixCheck(String str, String suffix) {
        if (str == null || suffix == null) {
            return str;
        }
        if (str.endsWith(suffix)) {
            return str;
        }
        return str + suffix;
    }

    public static String addPrefixCheck(String str, String prefix) {
        if (str == null || prefix == null) {
            return str;
        }
        if (str.endsWith(prefix)) {
            return str;
        }
        return prefix + str;
    }

    public static String trim(String str, String chars) {
        if (str == null || chars == null) {
            return str;
        }
        str = StrUtils.trimLeading(str, chars);
        str = StrUtils.trimTrailing(str, chars);
        return str;
    }

    public static String trimLeading(String str, String chars) {
        if (str == null || chars == null) {
            return str;
        }
        int len = str.length();
        for (int pos = 0; pos < len; ++pos) {
            char ch = str.charAt(pos);
            if (chars.indexOf(ch) >= 0) continue;
            return str.substring(pos);
        }
        return "";
    }

    public static String trimTrailing(String str, String chars) {
        int posStart;
        char ch;
        int pos;
        if (str == null || chars == null) {
            return str;
        }
        for (pos = posStart = str.length(); pos > 0 && chars.indexOf(ch = str.charAt(pos - 1)) >= 0; --pos) {
        }
        if (pos == posStart) {
            return str;
        }
        return str.substring(0, pos);
    }
}

