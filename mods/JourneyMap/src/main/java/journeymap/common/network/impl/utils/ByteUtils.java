/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.network.impl.utils;

public interface ByteUtils {
    public static String serialize(byte[] bytes) {
        return ByteUtils.serialize(bytes, ",");
    }

    public static String serialize(byte[] bytes, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b & 0xFF).append(delimiter);
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public static byte[] deserialize(String string) {
        return ByteUtils.deserialize(string, ",");
    }

    public static byte[] deserialize(String string, String delimiter) {
        String[] byteArray = string.split(delimiter);
        byte[] bytes = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; ++i) {
            bytes[i] = Byte.parseByte(byteArray[i]);
        }
        return bytes;
    }
}

