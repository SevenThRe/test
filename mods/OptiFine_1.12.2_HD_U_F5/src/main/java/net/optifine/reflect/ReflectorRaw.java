/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;

public class ReflectorRaw {
    private ReflectorRaw() {
    }

    public static Field getField(Class cls, Class fieldType) {
        try {
            Field[] fileds = cls.getDeclaredFields();
            for (int i = 0; i < fileds.length; ++i) {
                Field field = fileds[i];
                if (field.getType() != fieldType) continue;
                field.setAccessible(true);
                return field;
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Field[] getFields(Class cls, Class fieldType) {
        try {
            Field[] fields = cls.getDeclaredFields();
            return ReflectorRaw.getFields(fields, fieldType);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Field[] getFields(Field[] fields, Class fieldType) {
        try {
            ArrayList<Field> list = new ArrayList<Field>();
            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                if (field.getType() != fieldType) continue;
                field.setAccessible(true);
                list.add(field);
            }
            Field[] fs = list.toArray(new Field[list.size()]);
            return fs;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Field[] getFieldsAfter(Class cls, Field field, Class fieldType) {
        try {
            Field[] fields = cls.getDeclaredFields();
            List<Field> list = Arrays.asList(fields);
            int posStart = list.indexOf(field);
            if (posStart < 0) {
                return new Field[0];
            }
            List<Field> listAfter = list.subList(posStart + 1, list.size());
            Field[] fieldsAfter = listAfter.toArray(new Field[listAfter.size()]);
            return ReflectorRaw.getFields(fieldsAfter, fieldType);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Field[] getFields(Object obj, Field[] fields, Class fieldType, Object value) {
        try {
            ArrayList<Field> list = new ArrayList<Field>();
            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                if (field.getType() != fieldType) continue;
                boolean staticField = Modifier.isStatic(field.getModifiers());
                if (obj == null && !staticField || obj != null && staticField) continue;
                field.setAccessible(true);
                Object fieldVal = field.get(obj);
                if (fieldVal == value) {
                    list.add(field);
                    continue;
                }
                if (fieldVal == null || value == null || !fieldVal.equals(value)) continue;
                list.add(field);
            }
            Field[] fs = list.toArray(new Field[list.size()]);
            return fs;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Field getField(Class cls, Class fieldType, int index) {
        Field[] fields = ReflectorRaw.getFields(cls, fieldType);
        if (index < 0 || index >= fields.length) {
            return null;
        }
        return fields[index];
    }

    public static Field getFieldAfter(Class cls, Field field, Class fieldType, int index) {
        Field[] fields = ReflectorRaw.getFieldsAfter(cls, field, fieldType);
        if (index < 0 || index >= fields.length) {
            return null;
        }
        return fields[index];
    }

    public static Object getFieldValue(Object obj, Class cls, Class fieldType) {
        ReflectorField field = ReflectorRaw.getReflectorField(cls, fieldType);
        if (field == null) {
            return null;
        }
        if (!field.exists()) {
            return null;
        }
        return Reflector.getFieldValue(obj, field);
    }

    public static Object getFieldValue(Object obj, Class cls, Class fieldType, int index) {
        ReflectorField field = ReflectorRaw.getReflectorField(cls, fieldType, index);
        if (field == null) {
            return null;
        }
        if (!field.exists()) {
            return null;
        }
        return Reflector.getFieldValue(obj, field);
    }

    public static boolean setFieldValue(Object obj, Class cls, Class fieldType, Object value) {
        ReflectorField field = ReflectorRaw.getReflectorField(cls, fieldType);
        if (field == null) {
            return false;
        }
        if (!field.exists()) {
            return false;
        }
        return Reflector.setFieldValue(obj, field, value);
    }

    public static boolean setFieldValue(Object obj, Class cls, Class fieldType, int index, Object value) {
        ReflectorField field = ReflectorRaw.getReflectorField(cls, fieldType, index);
        if (field == null) {
            return false;
        }
        if (!field.exists()) {
            return false;
        }
        return Reflector.setFieldValue(obj, field, value);
    }

    public static ReflectorField getReflectorField(Class cls, Class fieldType) {
        Field field = ReflectorRaw.getField(cls, fieldType);
        if (field == null) {
            return null;
        }
        ReflectorClass rc = new ReflectorClass(cls);
        return new ReflectorField(rc, field.getName());
    }

    public static ReflectorField getReflectorField(Class cls, Class fieldType, int index) {
        Field field = ReflectorRaw.getField(cls, fieldType, index);
        if (field == null) {
            return null;
        }
        ReflectorClass rc = new ReflectorClass(cls);
        return new ReflectorField(rc, field.getName());
    }
}

