/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

public class UriPath {
    /*
     * Enabled aggressive block sorting
     */
    public static String canonical(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }
        int end = path.length();
        int start = path.lastIndexOf(47, end);
        block8: while (end > 0) {
            switch (end - start) {
                case 2: {
                    if (path.charAt(start + 1) == '.') break block8;
                    break;
                }
                case 3: {
                    if (path.charAt(start + 1) == '.' && path.charAt(start + 2) == '.') break block8;
                }
            }
            end = start;
            start = path.lastIndexOf(47, end - 1);
        }
        if (start >= end) {
            return path;
        }
        StringBuilder buf = new StringBuilder(path);
        int delStart = -1;
        int delEnd = -1;
        int skip = 0;
        block9: while (end > 0) {
            switch (end - start) {
                case 2: {
                    if (buf.charAt(start + 1) != '.') {
                        if (skip <= 0 || --skip != 0) break;
                        int n = delStart = start >= 0 ? start : 0;
                        if (delStart <= 0 || delEnd != buf.length() || buf.charAt(delEnd - 1) != '.') break;
                        ++delStart;
                        break;
                    }
                    if (start < 0 && buf.length() > 2 && buf.charAt(1) == '/' && buf.charAt(2) == '/') break;
                    if (delEnd < 0) {
                        delEnd = end;
                    }
                    if ((delStart = start) < 0 || delStart == 0 && buf.charAt(delStart) == '/') {
                        ++delStart;
                        if (delEnd >= buf.length() || buf.charAt(delEnd) != '/') break;
                        ++delEnd;
                        break;
                    }
                    if (end == buf.length()) {
                        ++delStart;
                    }
                    end = start--;
                    while (start >= 0 && buf.charAt(start) != '/') {
                        --start;
                    }
                    continue block9;
                }
                case 3: {
                    if (buf.charAt(start + 1) != '.' || buf.charAt(start + 2) != '.') {
                        if (skip <= 0 || --skip != 0) break;
                        int n = delStart = start >= 0 ? start : 0;
                        if (delStart <= 0 || delEnd != buf.length() || buf.charAt(delEnd - 1) != '.') break;
                        ++delStart;
                        break;
                    }
                    delStart = start;
                    if (delEnd < 0) {
                        delEnd = end;
                    }
                    ++skip;
                    end = start--;
                    while (start >= 0 && buf.charAt(start) != '/') {
                        --start;
                    }
                    continue block9;
                }
                default: {
                    if (skip <= 0 || --skip != 0) break;
                    int n = delStart = start >= 0 ? start : 0;
                    if (delEnd != buf.length() || buf.charAt(delEnd - 1) != '.') break;
                    ++delStart;
                }
            }
            if (skip <= 0 && delStart >= 0 && delEnd >= delStart) {
                buf.delete(delStart, delEnd);
                delEnd = -1;
                delStart = -1;
                if (skip > 0) {
                    delEnd = end;
                }
            }
            end = start--;
            while (start >= 0 && buf.charAt(start) != '/') {
                --start;
            }
        }
        if (skip > 0) {
            return null;
        }
        if (delEnd >= 0) {
            buf.delete(delStart, delEnd);
        }
        return buf.toString();
    }
}

