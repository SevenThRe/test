/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InnerClassNode
 *  org.objectweb.asm.tree.MethodNode
 */
package optifine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

public class AccessFixer {
    public static void fixMemberAccess(ClassNode classOld, ClassNode classNew) {
        List fieldsOld = classOld.fields;
        List fieldsNew = classNew.fields;
        Map<String, FieldNode> mapFieldsOld = AccessFixer.getMapFields(fieldsOld);
        for (FieldNode fieldNew : fieldsNew) {
            String idNew = fieldNew.name;
            FieldNode fieldOld = mapFieldsOld.get(idNew);
            if (fieldOld == null || fieldNew.access == fieldOld.access) continue;
            fieldNew.access = AccessFixer.combineAccess(fieldNew.access, fieldOld.access);
        }
        List methodsOld = classOld.methods;
        List methodsNew = classNew.methods;
        Map<String, MethodNode> mapMethodsOld = AccessFixer.getMapMethods(methodsOld);
        for (MethodNode methodNew : methodsNew) {
            String idNew = String.valueOf(methodNew.name) + methodNew.desc;
            MethodNode methodOld = mapMethodsOld.get(idNew);
            if (methodOld == null || methodNew.access == methodOld.access) continue;
            methodNew.access = AccessFixer.combineAccess(methodNew.access, methodOld.access);
        }
        List innerClassesOld = classOld.innerClasses;
        List innerClassesNew = classNew.innerClasses;
        Map<String, InnerClassNode> mapInnerClassesOld = AccessFixer.getMapInnerClasses(innerClassesOld);
        for (InnerClassNode innerClassNew : innerClassesNew) {
            int accessNew;
            String idNew = innerClassNew.name;
            InnerClassNode innerClassOld = mapInnerClassesOld.get(idNew);
            if (innerClassOld == null || innerClassNew.access == innerClassOld.access) continue;
            innerClassNew.access = accessNew = AccessFixer.combineAccess(innerClassNew.access, innerClassOld.access);
        }
        if (classNew.access != classOld.access) {
            int accessClassNew;
            classNew.access = accessClassNew = AccessFixer.combineAccess(classNew.access, classOld.access);
        }
    }

    private static int combineAccess(int access, int access2) {
        if (access == access2) {
            return access;
        }
        int MASK_ACCESS = 7;
        int accessClean = access & ~MASK_ACCESS;
        if (!AccessFixer.isSet(access, 16) || !AccessFixer.isSet(access2, 16)) {
            accessClean &= 0xFFFFFFEF;
        }
        if (AccessFixer.isSet(access, 1) || AccessFixer.isSet(access2, 1)) {
            return accessClean | 1;
        }
        if (AccessFixer.isSet(access, 4) || AccessFixer.isSet(access2, 4)) {
            return accessClean | 4;
        }
        if (AccessFixer.isSet(access, 2) || AccessFixer.isSet(access2, 2)) {
            return accessClean | 2;
        }
        return accessClean;
    }

    private static boolean isSet(int access, int flag) {
        return (access & flag) != 0;
    }

    public static Map<String, FieldNode> getMapFields(List<FieldNode> fields) {
        LinkedHashMap<String, FieldNode> map = new LinkedHashMap<String, FieldNode>();
        for (FieldNode fieldNode : fields) {
            String id = fieldNode.name;
            map.put(id, fieldNode);
        }
        return map;
    }

    public static Map<String, MethodNode> getMapMethods(List<MethodNode> methods) {
        LinkedHashMap<String, MethodNode> map = new LinkedHashMap<String, MethodNode>();
        for (MethodNode methodNode : methods) {
            String id = String.valueOf(methodNode.name) + methodNode.desc;
            map.put(id, methodNode);
        }
        return map;
    }

    public static Map<String, InnerClassNode> getMapInnerClasses(List<InnerClassNode> innerClasses) {
        LinkedHashMap<String, InnerClassNode> map = new LinkedHashMap<String, InnerClassNode>();
        for (InnerClassNode innerClassNode : innerClasses) {
            String id = innerClassNode.name;
            map.put(id, innerClassNode);
        }
        return map;
    }
}

