/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.utils;

import info.journeymap.shaded.kotlin.spark.utils.CollectionUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class StringUtils {
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; ++i) {
            if (Character.isWhitespace(cs.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !StringUtils.isBlank(cs);
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(String str) {
        return StringUtils.hasLength((CharSequence)str);
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        return sb.toString();
    }

    public static String deleteAny(String inString, String charsToDelete) {
        if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(charsToDelete)) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inString.length(); ++i) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) != -1) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
    }

    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath = newPath + FOLDER_SEPARATOR;
            }
            return newPath + relativePath;
        }
        return relativePath;
    }

    public static String cleanPath(String path) {
        int i;
        if (path == null) {
            return null;
        }
        String pathToUse = StringUtils.replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            pathToUse = pathToUse.substring(prefixIndex + 1);
        }
        if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
            prefix = prefix + FOLDER_SEPARATOR;
            pathToUse = pathToUse.substring(1);
        }
        String[] pathArray = StringUtils.delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
        LinkedList<String> pathElements = new LinkedList<String>();
        int tops = 0;
        for (i = pathArray.length - 1; i >= 0; --i) {
            String element = pathArray[i];
            if (CURRENT_PATH.equals(element)) continue;
            if (TOP_PATH.equals(element)) {
                ++tops;
                continue;
            }
            if (tops > 0) {
                --tops;
                continue;
            }
            pathElements.add(0, element);
        }
        for (i = 0; i < tops; ++i) {
            pathElements.add(0, TOP_PATH);
        }
        return prefix + StringUtils.collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
    }

    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }

    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return StringUtils.delimitedListToStringArray(str, delimiter, null);
    }

    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[]{str};
        }
        ArrayList<String> result = new ArrayList<String>();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); ++i) {
                result.add(StringUtils.deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int delPos;
            int pos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(StringUtils.deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                result.add(StringUtils.deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return StringUtils.toStringArray(result);
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (!it.hasNext()) continue;
            sb.append(delim);
        }
        return sb.toString();
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim, "", "");
    }

    public static String toString(byte[] bytes, String encoding) {
        String str;
        if (encoding != null && Charset.isSupported(encoding)) {
            try {
                str = new String(bytes, encoding);
            }
            catch (UnsupportedEncodingException e) {
                str = new String(bytes);
            }
        } else {
            str = new String(bytes);
        }
        return str;
    }

    public static String removeLeadingAndTrailingSlashesFrom(String string) {
        String trimmed = string;
        if (trimmed.endsWith(FOLDER_SEPARATOR) || trimmed.endsWith(WINDOWS_FOLDER_SEPARATOR)) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }
        if (trimmed.startsWith(FOLDER_SEPARATOR)) {
            trimmed = trimmed.substring(1);
        }
        return trimmed;
    }
}

