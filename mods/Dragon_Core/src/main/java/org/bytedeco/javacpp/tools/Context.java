/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.tools.Declarator;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.TemplateMap;
import org.bytedeco.javacpp.tools.Type;

class Context {
    String namespace = null;
    String baseType = null;
    String cppName = null;
    String javaName = null;
    String constName = null;
    String constBaseName = null;
    boolean immutable = false;
    boolean inaccessible = false;
    boolean beanify = false;
    boolean objectify = false;
    boolean virtualize = false;
    Declarator variable = null;
    InfoMap infoMap = null;
    TemplateMap templateMap = null;
    List<String> usingList = null;
    Map<String, String> namespaceMap = null;

    Context() {
        this.usingList = new ArrayList<String>();
        this.namespaceMap = new HashMap<String, String>();
    }

    Context(Context c2) {
        this.namespace = c2.namespace;
        this.baseType = c2.baseType;
        this.cppName = c2.cppName;
        this.javaName = c2.javaName;
        this.constName = c2.constName;
        this.constBaseName = c2.constBaseName;
        this.immutable = c2.immutable;
        this.inaccessible = c2.inaccessible;
        this.beanify = c2.beanify;
        this.objectify = c2.objectify;
        this.virtualize = c2.virtualize;
        this.variable = c2.variable;
        this.infoMap = c2.infoMap;
        this.templateMap = c2.templateMap;
        this.usingList = c2.usingList;
        this.namespaceMap = c2.namespaceMap;
    }

    String[] qualify(String cppName) {
        return this.qualify(cppName, null);
    }

    String[] qualify(String cppName, String parameters) {
        String ns2;
        if (cppName == null || cppName.length() == 0) {
            return new String[0];
        }
        if (cppName.startsWith("::")) {
            return new String[]{cppName.substring(2)};
        }
        for (Map.Entry<String, String> e2 : this.namespaceMap.entrySet()) {
            cppName = cppName.replaceAll(e2.getKey() + "::", e2.getValue() + "::");
        }
        ArrayList<String> names = new ArrayList<String>();
        String string = ns2 = this.namespace != null ? this.namespace : "";
        while (ns2 != null) {
            String name;
            String string2 = name = ns2.length() > 0 ? ns2 + "::" + cppName : cppName;
            if (parameters != null && name.endsWith(parameters)) {
                name = name.substring(0, name.length() - parameters.length());
            }
            TemplateMap map = this.templateMap;
            while (map != null) {
                Object name2 = map.getName();
                if (parameters != null && name2 != null && ((String)name2).endsWith(parameters)) {
                    name2 = ((String)name2).substring(0, ((String)name2).length() - parameters.length());
                }
                if (name.equals(name2)) {
                    String args = "<";
                    String separator = "";
                    for (Type t2 : map.values()) {
                        if (t2 == null) continue;
                        args = args + separator + t2.cppName;
                        separator = ",";
                    }
                    names.add(name + args + (args.endsWith(">") ? " >" : ">") + (parameters != null ? parameters : ""));
                    break;
                }
                map = map.parent;
            }
            names.add(name);
            for (String s2 : this.usingList) {
                String prefix = this.infoMap.normalize(cppName, false, true);
                int i2 = s2.lastIndexOf("::") + 2;
                String ns22 = ns2.length() > 0 ? ns2 + "::" + s2.substring(0, i2) : s2.substring(0, i2);
                String suffix = s2.substring(i2);
                if (suffix.length() != 0 && !prefix.equals(suffix)) continue;
                names.add(ns22 + cppName);
            }
            int i3 = (ns2 = this.infoMap.normalize(ns2, false, true)).lastIndexOf("::");
            ns2 = i3 >= 0 ? ns2.substring(0, i3) : (ns2.length() > 0 ? "" : null);
        }
        return names.toArray(new String[names.size()]);
    }

    String shorten(String javaName) {
        if (this.javaName != null) {
            int lastDot = 0;
            String s1 = javaName;
            String s2 = this.javaName + '.';
            for (int i2 = 0; i2 < s1.length() && i2 < s2.length() && s1.charAt(i2) == s2.charAt(i2); ++i2) {
                if (s1.charAt(i2) != '.') continue;
                lastDot = i2;
            }
            if (lastDot > 0) {
                javaName = javaName.substring(lastDot + 1);
            }
        }
        return javaName;
    }
}

