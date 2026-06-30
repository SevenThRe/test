/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import org.bytedeco.javacpp.tools.Context;
import org.bytedeco.javacpp.tools.Declaration;
import org.bytedeco.javacpp.tools.Declarator;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.TemplateMap;
import org.bytedeco.javacpp.tools.Type;

class DeclarationList
extends ArrayList<Declaration> {
    InfoMap infoMap = null;
    Context context = null;
    TemplateMap templateMap = null;
    ListIterator<Info> infoIterator = null;
    String spacing = null;
    DeclarationList inherited = null;

    DeclarationList() {
    }

    DeclarationList(DeclarationList inherited) {
        this.inherited = inherited;
    }

    String rescan(String lines) {
        if (this.spacing == null) {
            return lines;
        }
        String text = "";
        try (Scanner scanner = new Scanner(lines);){
            while (scanner.hasNextLine()) {
                text = text + this.spacing + scanner.nextLine();
                int newline = this.spacing.lastIndexOf(10);
                this.spacing = newline >= 0 ? this.spacing.substring(newline) : "\n";
            }
        }
        return text;
    }

    @Override
    public boolean add(Declaration decl) {
        return this.add(decl, null);
    }

    /*
     * WARNING - void declaration
     */
    public boolean add(Declaration decl, String fullName) {
        boolean add = true;
        if (this.templateMap != null && this.templateMap.empty() && !decl.custom && (decl.type != null || decl.declarator != null)) {
            if (this.infoIterator == null) {
                Type type = this.templateMap.type = decl.type;
                Declarator declarator = this.templateMap.declarator = decl.declarator;
                for (String name : new String[]{fullName, declarator != null ? declarator.cppName : type.cppName}) {
                    if (name == null) continue;
                    List<Info> infoList = this.infoMap.get(name);
                    boolean hasJavaName = false;
                    for (Info info : infoList) {
                        hasJavaName |= info.javaNames != null && info.javaNames.length > 0;
                    }
                    if (decl.function && !hasJavaName) continue;
                    this.infoIterator = infoList.size() > 0 ? infoList.listIterator() : null;
                    break;
                }
            }
            add = false;
        } else if (this.infoMap != null && !decl.incomplete && decl.type != null && decl.type.cppName != null && this.infoIterator == null) {
            void var5_8;
            String constName = null;
            Object var5_6 = null;
            List<Info> infoList = this.infoMap.get(decl.type.cppName);
            List<Info> constInfoList = this.infoMap.get("const " + decl.type.cppName);
            if (infoList != null && constInfoList != null && !infoList.equals(constInfoList)) {
                for (Info info : infoList) {
                    if (info.pointerTypes == null || info.pointerTypes.length <= 0) continue;
                    String string = info.pointerTypes[0].substring(info.pointerTypes[0].lastIndexOf(" ") + 1);
                    break;
                }
                for (Info info : constInfoList) {
                    if (info.pointerTypes == null || info.pointerTypes.length <= 0) continue;
                    constName = info.pointerTypes[0].substring(info.pointerTypes[0].lastIndexOf(" ") + 1);
                    break;
                }
            }
            if (constName != null && var5_8 != null && !constName.equals(var5_8)) {
                infoList.addAll(constInfoList);
                this.infoIterator = infoList.size() > 0 ? infoList.listIterator() : null;
                add = false;
            }
        }
        if (decl.declarator != null && decl.declarator.type != null) {
            Info info = this.infoMap.getFirst(decl.declarator.type.cppName);
            if (info != null && info.skip && info.valueTypes == null && info.pointerTypes == null) {
                add = false;
            } else if (decl.declarator.parameters != null) {
                for (Declarator d2 : decl.declarator.parameters.declarators) {
                    if (d2 == null || d2.type == null || (info = this.infoMap.getFirst(d2.type.cppName)) == null || !info.skip || info.valueTypes != null || info.pointerTypes != null) continue;
                    add = false;
                    break;
                }
            }
        }
        if (decl.type != null && decl.type.javaName.equals("Pointer")) {
            add = false;
        }
        if (!add) {
            return false;
        }
        ArrayList stack = new ArrayList();
        ListIterator<Declaration> listIterator = stack.listIterator();
        listIterator.add(decl);
        listIterator.previous();
        while (listIterator.hasNext()) {
            decl = (Declaration)listIterator.next();
            Declarator dcl = decl.declarator;
            if (dcl != null && dcl.definition != null) {
                listIterator.add(dcl.definition);
                listIterator.previous();
            }
            if (dcl == null || dcl.parameters == null || dcl.parameters.declarators == null) continue;
            for (Declarator d3 : dcl.parameters.declarators) {
                if (d3 == null || d3.definition == null) continue;
                listIterator.add(d3.definition);
                listIterator.previous();
            }
        }
        add = false;
        while (!stack.isEmpty()) {
            decl = (Declaration)stack.remove(stack.size() - 1);
            if (this.context != null) {
                boolean bl2 = decl.inaccessible = this.context.inaccessible && (!this.context.virtualize || decl.declarator == null || decl.declarator.type == null || !decl.declarator.type.virtual);
            }
            if (decl.text.length() == 0) {
                decl.inaccessible = true;
            }
            ListIterator listIterator2 = this.listIterator();
            boolean found = false;
            while (listIterator2.hasNext()) {
                Declaration d4 = (Declaration)listIterator2.next();
                if (d4 == null || d4.signature == null || d4.signature.length() <= 0 || !d4.signature.equals(decl.signature)) continue;
                if (d4.constMember && !decl.constMember || d4.inaccessible && !decl.inaccessible || d4.incomplete && !decl.incomplete) {
                    listIterator2.remove();
                    continue;
                }
                found = true;
            }
            if (this.inherited != null) {
                ListIterator listIterator3 = this.inherited.listIterator();
                while (listIterator3.hasNext()) {
                    Declaration d5 = (Declaration)listIterator3.next();
                    if (d5.signature.length() <= 0 || !d5.signature.equals(decl.signature) || d5.incomplete || !decl.incomplete) continue;
                    found = true;
                    break;
                }
            }
            if (found) continue;
            decl.text = this.rescan(decl.text);
            super.add(decl);
            add = true;
        }
        return add;
    }
}

