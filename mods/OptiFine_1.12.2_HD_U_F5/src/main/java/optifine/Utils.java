/*
 * Decompiled with CFR 0.152.
 */
package optifine;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Utils {
    public static final String MAC_OS_HOME_PREFIX = "Library/Application Support";
    private static final char[] hexTable = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private Utils() {
    }

    public static File getWorkingDirectory() {
        return Utils.getWorkingDirectory("minecraft");
    }

    public static File getWorkingDirectory(String applicationName) {
        String userHome = System.getProperty("user.home", ".");
        File workingDirectory = null;
        switch (Utils.getPlatform()) {
            case LINUX: 
            case SOLARIS: {
                workingDirectory = new File(userHome, String.valueOf('.') + applicationName + '/');
                break;
            }
            case WINDOWS: {
                String applicationData = System.getenv("APPDATA");
                if (applicationData != null) {
                    workingDirectory = new File(applicationData, "." + applicationName + '/');
                    break;
                }
                workingDirectory = new File(userHome, String.valueOf('.') + applicationName + '/');
                break;
            }
            case MACOS: {
                workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
                break;
            }
            default: {
                workingDirectory = new File(userHome, String.valueOf(applicationName) + '/');
            }
        }
        if (!workingDirectory.exists() && !workingDirectory.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + workingDirectory);
        }
        return workingDirectory;
    }

    public static OS getPlatform() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        }
        if (osName.contains("mac")) {
            return OS.MACOS;
        }
        if (osName.contains("solaris")) {
            return OS.SOLARIS;
        }
        if (osName.contains("sunos")) {
            return OS.SOLARIS;
        }
        if (osName.contains("linux")) {
            return OS.LINUX;
        }
        if (osName.contains("unix")) {
            return OS.LINUX;
        }
        return OS.UNKNOWN;
    }

    public static int find(byte[] buf, byte[] pattern) {
        return Utils.find(buf, 0, pattern);
    }

    public static int find(byte[] buf, int index, byte[] pattern) {
        int i = index;
        while (i < buf.length - pattern.length) {
            boolean found = true;
            int pos = 0;
            while (pos < pattern.length) {
                if (pattern[pos] != buf[i + pos]) {
                    found = false;
                    break;
                }
                ++pos;
            }
            if (found) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public static byte[] readAll(InputStream is) throws IOException {
        int len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) >= 0) {
            baos.write(buf, 0, len);
        }
        is.close();
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static void dbg(String str) {
        System.out.println(str);
    }

    public static String[] tokenize(String str, String delim) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tok = new StringTokenizer(str, delim);
        while (tok.hasMoreTokens()) {
            String token = tok.nextToken();
            list.add(token);
        }
        String[] tokens = list.toArray(new String[list.size()]);
        return tokens;
    }

    public static String getExceptionStackTrace(Throwable e) {
        StringWriter swr = new StringWriter();
        PrintWriter pwr = new PrintWriter(swr);
        e.printStackTrace(pwr);
        pwr.close();
        try {
            swr.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return swr.getBuffer().toString();
    }

    public static void copyFile(File fileSrc, File fileDest) throws IOException {
        if (fileSrc.getCanonicalPath().equals(fileDest.getCanonicalPath())) {
            return;
        }
        FileInputStream fin = new FileInputStream(fileSrc);
        FileOutputStream fout = new FileOutputStream(fileDest);
        Utils.copyAll(fin, fout);
        fout.flush();
        fin.close();
        fout.close();
    }

    public static void copyAll(InputStream is, OutputStream os) throws IOException {
        int len;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) >= 0) {
            os.write(buf, 0, len);
        }
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "OptiFine", 1);
    }

    public static void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", 0);
    }

    public static String readFile(File file) throws IOException {
        return Utils.readFile(file, "ASCII");
    }

    public static String readFile(File file, String encoding) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        return Utils.readText(fin, encoding);
    }

    public static String readText(InputStream in, String encoding) throws IOException {
        String line;
        InputStreamReader inr = new InputStreamReader(in, encoding);
        BufferedReader br = new BufferedReader(inr);
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        inr.close();
        in.close();
        return sb.toString();
    }

    public static String[] readLines(InputStream in, String encoding) throws IOException {
        String str = Utils.readText(in, encoding);
        String[] strs = Utils.tokenize(str, "\n\r");
        return strs;
    }

    public static void centerWindow(Component c2, Component par) {
        Rectangle parRect;
        if (c2 == null) {
            return;
        }
        Rectangle rect = c2.getBounds();
        if (par != null && par.isVisible()) {
            parRect = par.getBounds();
        } else {
            Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
            parRect = new Rectangle(0, 0, scrDim.width, scrDim.height);
        }
        int newX = parRect.x + (parRect.width - rect.width) / 2;
        int newY = parRect.y + (parRect.height - rect.height) / 2;
        if (newX < 0) {
            newX = 0;
        }
        if (newY < 0) {
            newY = 0;
        }
        c2.setBounds(newX, newY, rect.width, rect.height);
    }

    public static String byteArrayToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        int i = 0;
        while (i < bytes.length) {
            byte b2 = bytes[i];
            buf.append(hexTable[b2 >> 4 & 0xF]);
            buf.append(hexTable[b2 & 0xF]);
            ++i;
        }
        return buf.toString();
    }

    public static String arrayToCommaSeparatedString(Object[] arr2) {
        if (arr2 == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        int i = 0;
        while (i < arr2.length) {
            Object val = arr2[i];
            if (i > 0) {
                buf.append(", ");
            }
            if (val == null) {
                buf.append("null");
            } else if (val.getClass().isArray()) {
                buf.append("[");
                if (val instanceof Object[]) {
                    Object[] valObjArr = (Object[])val;
                    buf.append(Utils.arrayToCommaSeparatedString(valObjArr));
                } else {
                    int ai = 0;
                    while (ai < Array.getLength(val)) {
                        if (ai > 0) {
                            buf.append(", ");
                        }
                        buf.append(Array.get(val, ai));
                        ++ai;
                    }
                }
                buf.append("]");
            } else {
                buf.append(arr2[i]);
            }
            ++i;
        }
        return buf.toString();
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

    public static String removePrefix(String str, String[] prefixes) {
        if (str == null || prefixes == null) {
            return str;
        }
        int strLen = str.length();
        int i = 0;
        while (i < prefixes.length) {
            String prefix = prefixes[i];
            if ((str = Utils.removePrefix(str, prefix)).length() != strLen) break;
            ++i;
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

    public static String removeSuffix(String str, String[] suffixes) {
        if (str == null || suffixes == null) {
            return str;
        }
        int strLen = str.length();
        int i = 0;
        while (i < suffixes.length) {
            String suffix = suffixes[i];
            if ((str = Utils.removeSuffix(str, suffix)).length() != strLen) break;
            ++i;
        }
        return str;
    }

    public static String ensurePrefix(String str, String prefix) {
        if (str == null || prefix == null) {
            return str;
        }
        if (!str.startsWith(prefix)) {
            str = String.valueOf(prefix) + str;
        }
        return str;
    }

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null) {
            return false;
        }
        return o1.equals(o2);
    }

    public static int parseInt(String str, int defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return defVal;
        }
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
        int i = 0;
        while (i < tokens.size()) {
            String token = (String)tokens.get(i);
            if (token.length() > 0) {
                int foundPos = str.indexOf(token, currPos);
                if (foundPos >= 0) {
                    currPos = foundPos + token.length();
                } else {
                    return false;
                }
            }
            ++i;
        }
        return true;
    }

    public static Object[] addObjectToArray(Object[] arr2, Object obj) {
        if (arr2 == null) {
            throw new NullPointerException("The given array is NULL");
        }
        int arrLen = arr2.length;
        int newLen = arrLen + 1;
        Object[] newArr = (Object[])Array.newInstance(arr2.getClass().getComponentType(), newLen);
        System.arraycopy(arr2, 0, newArr, 0, arrLen);
        newArr[arrLen] = obj;
        return newArr;
    }

    public static Object[] addObjectToArray(Object[] arr2, Object obj, int index) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr2));
        list.add(index, obj);
        Object[] newArr = (Object[])Array.newInstance(arr2.getClass().getComponentType(), list.size());
        return list.toArray(newArr);
    }

    public static Object[] addObjectsToArray(Object[] arr2, Object[] objs) {
        if (arr2 == null) {
            throw new NullPointerException("The given array is NULL");
        }
        if (objs.length == 0) {
            return arr2;
        }
        int arrLen = arr2.length;
        int newLen = arrLen + objs.length;
        Object[] newArr = (Object[])Array.newInstance(arr2.getClass().getComponentType(), newLen);
        System.arraycopy(arr2, 0, newArr, 0, arrLen);
        System.arraycopy(objs, 0, newArr, arrLen, objs.length);
        return newArr;
    }

    public static Object[] removeObjectFromArray(Object[] arr2, Object obj) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr2));
        list.remove(obj);
        Object[] newArr = Utils.collectionToArray(list, arr2.getClass().getComponentType());
        return newArr;
    }

    public static Object[] collectionToArray(Collection coll, Class elementClass) {
        if (coll == null) {
            return null;
        }
        if (elementClass == null) {
            return null;
        }
        if (elementClass.isPrimitive()) {
            throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + elementClass);
        }
        Object[] array = (Object[])Array.newInstance(elementClass, coll.size());
        return coll.toArray(array);
    }

    public static enum OS {
        LINUX,
        SOLARIS,
        WINDOWS,
        MACOS,
        UNKNOWN;

    }
}

