/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  axw
 *  et
 */
package net.optifine.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;

public class ChunkUtils {
    private static ReflectorClass chunkClass = new ReflectorClass(axw.class);
    private static ReflectorField fieldHasEntities = ChunkUtils.findFieldHasEntities();
    private static ReflectorField fieldPrecipitationHeightMap = new ReflectorField(chunkClass, int[].class, 0);

    public static boolean hasEntities(axw chunk) {
        return Reflector.getFieldValueBoolean(chunk, fieldHasEntities, true);
    }

    public static int getPrecipitationHeight(axw chunk, et pos) {
        int cz;
        int[] precipitationHeightMap = (int[])Reflector.getFieldValue(chunk, fieldPrecipitationHeightMap);
        if (precipitationHeightMap == null || precipitationHeightMap.length != 256) {
            return -1;
        }
        int cx = pos.p() & 0xF;
        int ix = cx | (cz = pos.r() & 0xF) << 4;
        int y = precipitationHeightMap[ix];
        if (y >= 0) {
            return y;
        }
        et posPrep = chunk.f(pos);
        return posPrep.q();
    }

    private static ReflectorField findFieldHasEntities() {
        try {
            axw chunk = new axw(null, 0, 0);
            ArrayList<Field> listBoolFields = new ArrayList<Field>();
            ArrayList<Object> listBoolValuesPre = new ArrayList<Object>();
            Field[] fields = axw.class.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                if (field.getType() != Boolean.TYPE) continue;
                field.setAccessible(true);
                listBoolFields.add(field);
                listBoolValuesPre.add(field.get(chunk));
            }
            chunk.g(false);
            ArrayList<Object> listBoolValuesFalse = new ArrayList<Object>();
            for (Field field : listBoolFields) {
                listBoolValuesFalse.add(field.get(chunk));
            }
            chunk.g(true);
            ArrayList<Object> listBoolValuesTrue = new ArrayList<Object>();
            for (Field field : listBoolFields) {
                listBoolValuesTrue.add(field.get(chunk));
            }
            ArrayList<Field> listMatchingFields = new ArrayList<Field>();
            for (int i = 0; i < listBoolFields.size(); ++i) {
                Field field = (Field)listBoolFields.get(i);
                Boolean valFalse = (Boolean)listBoolValuesFalse.get(i);
                Boolean valTrue = (Boolean)listBoolValuesTrue.get(i);
                if (valFalse.booleanValue() || !valTrue.booleanValue()) continue;
                listMatchingFields.add(field);
                Boolean valPre = (Boolean)listBoolValuesPre.get(i);
                field.set(chunk, valPre);
            }
            if (listMatchingFields.size() == 1) {
                Field field = (Field)listMatchingFields.get(0);
                return new ReflectorField(field);
            }
        }
        catch (Exception e) {
            Config.warn(e.getClass().getName() + " " + e.getMessage());
        }
        Config.warn("Error finding Chunk.hasEntities");
        return new ReflectorField(new ReflectorClass(axw.class), "hasEntities");
    }
}

