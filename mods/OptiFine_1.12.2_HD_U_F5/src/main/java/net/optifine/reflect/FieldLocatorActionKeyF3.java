/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 */
package net.optifine.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import net.optifine.reflect.IFieldLocator;
import net.optifine.reflect.ReflectorRaw;

public class FieldLocatorActionKeyF3
implements IFieldLocator {
    @Override
    public Field getField() {
        Class<bib> mcClass = bib.class;
        Field fieldRenderChunksMany = this.getFieldRenderChunksMany();
        if (fieldRenderChunksMany == null) {
            Config.log("(Reflector) Field not present: " + mcClass.getName() + ".actionKeyF3 (field renderChunksMany not found)");
            return null;
        }
        Field fieldActionkeyF3 = ReflectorRaw.getFieldAfter(bib.class, fieldRenderChunksMany, Boolean.TYPE, 0);
        if (fieldActionkeyF3 == null) {
            Config.log("(Reflector) Field not present: " + mcClass.getName() + ".actionKeyF3");
            return null;
        }
        return fieldActionkeyF3;
    }

    private Field getFieldRenderChunksMany() {
        bib mc = bib.z();
        boolean oldRenderChunksMany = mc.H;
        Field[] fields = bib.class.getDeclaredFields();
        mc.H = true;
        Field[] fieldsTrue = ReflectorRaw.getFields(mc, fields, Boolean.TYPE, Boolean.TRUE);
        mc.H = false;
        Field[] fieldsFalse = ReflectorRaw.getFields(mc, fields, Boolean.TYPE, Boolean.FALSE);
        mc.H = oldRenderChunksMany;
        HashSet<Field> setTrue = new HashSet<Field>(Arrays.asList(fieldsTrue));
        HashSet<Field> setFalse = new HashSet<Field>(Arrays.asList(fieldsFalse));
        HashSet<Field> setFields = new HashSet<Field>(setTrue);
        setFields.retainAll(setFalse);
        Field[] fs = setFields.toArray(new Field[setFields.size()]);
        if (fs.length != 1) {
            return null;
        }
        return fs[0];
    }
}

