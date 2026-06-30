/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import javazoom.jl.decoder.JavaLayerHook;

public class JavaLayerUtils {
    private static JavaLayerHook hook = null;

    public static Object deserialize(InputStream inputStream, Class clazz) throws IOException {
        if (clazz == null) {
            throw new NullPointerException("cls");
        }
        Object object = JavaLayerUtils.deserialize(inputStream, clazz);
        if (!clazz.isInstance(object)) {
            throw new InvalidObjectException("type of deserialized instance not of required class.");
        }
        return object;
    }

    public static Object deserialize(InputStream inputStream) throws IOException {
        Object object;
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            object = objectInputStream.readObject();
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new InvalidClassException(classNotFoundException.toString());
        }
        return object;
    }

    public static Object deserializeArray(InputStream inputStream, Class clazz, int n) throws IOException {
        int n2;
        if (clazz == null) {
            throw new NullPointerException("elemType");
        }
        if (n < -1) {
            throw new IllegalArgumentException("length");
        }
        Object object = JavaLayerUtils.deserialize(inputStream);
        Class<?> clazz2 = object.getClass();
        if (!clazz2.isArray()) {
            throw new InvalidObjectException("object is not an array");
        }
        Class<?> clazz3 = clazz2.getComponentType();
        if (clazz3 != clazz) {
            throw new InvalidObjectException("unexpected array component type");
        }
        if (n != -1 && (n2 = Array.getLength(object)) != n) {
            throw new InvalidObjectException("array length mismatch");
        }
        return object;
    }

    public static Object deserializeArrayResource(String string, Class clazz, int n) throws IOException {
        InputStream inputStream = JavaLayerUtils.getResourceAsStream(string);
        if (inputStream == null) {
            throw new IOException("unable to load resource '" + string + "'");
        }
        Object object = JavaLayerUtils.deserializeArray(inputStream, clazz, n);
        return object;
    }

    public static void serialize(OutputStream outputStream, Object object) throws IOException {
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        if (object == null) {
            throw new NullPointerException("obj");
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
    }

    public static synchronized void setHook(JavaLayerHook javaLayerHook) {
        hook = javaLayerHook;
    }

    public static synchronized JavaLayerHook getHook() {
        return hook;
    }

    public static synchronized InputStream getResourceAsStream(String string) {
        InputStream inputStream = null;
        if (hook != null) {
            inputStream = hook.getResourceAsStream(string);
        } else {
            Class<JavaLayerUtils> clazz = JavaLayerUtils.class;
            inputStream = clazz.getResourceAsStream(string);
        }
        return inputStream;
    }
}

