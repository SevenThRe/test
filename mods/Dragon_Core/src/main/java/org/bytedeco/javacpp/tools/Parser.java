/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.tools.Attribute;
import org.bytedeco.javacpp.tools.BuildEnabled;
import org.bytedeco.javacpp.tools.Context;
import org.bytedeco.javacpp.tools.Declaration;
import org.bytedeco.javacpp.tools.DeclarationList;
import org.bytedeco.javacpp.tools.Declarator;
import org.bytedeco.javacpp.tools.DocTag;
import org.bytedeco.javacpp.tools.EncodingFileWriter;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacpp.tools.Parameters;
import org.bytedeco.javacpp.tools.ParserException;
import org.bytedeco.javacpp.tools.TemplateMap;
import org.bytedeco.javacpp.tools.Token;
import org.bytedeco.javacpp.tools.TokenIndexer;
import org.bytedeco.javacpp.tools.Tokenizer;
import org.bytedeco.javacpp.tools.Type;

public class Parser {
    final Logger logger;
    final Properties properties;
    final String encoding;
    InfoMap infoMap = null;
    InfoMap leafInfoMap = null;
    TokenIndexer tokens = null;
    String lineSeparator = null;

    public Parser(Logger logger, Properties properties) {
        this(logger, properties, null, null);
    }

    public Parser(Logger logger, Properties properties, String encoding, String lineSeparator) {
        this.logger = logger;
        this.properties = properties;
        this.encoding = encoding;
        this.lineSeparator = lineSeparator;
    }

    Parser(Parser p2, String text) {
        this.logger = p2.logger;
        this.properties = p2.properties;
        this.encoding = p2.encoding;
        this.infoMap = p2.infoMap;
        Token t2 = p2.tokens != null ? p2.tokens.get() : Token.EOF;
        this.tokens = new TokenIndexer(this.infoMap, new Tokenizer(text, t2.file, t2.lineNumber).tokenize(), false);
        this.lineSeparator = p2.lineSeparator;
    }

    String translate(String text) {
        Info info2;
        Info info = this.infoMap.getFirst(text);
        if (info != null && info.javaNames != null && info.javaNames.length > 0) {
            return info.javaNames[0];
        }
        int namespace = text.lastIndexOf("::");
        if (namespace >= 0) {
            Info info22 = this.infoMap.getFirst(text.substring(0, namespace));
            String localName = text.substring(namespace + 2);
            if (info22 != null && info22.pointerTypes != null) {
                text = info22.pointerTypes[0] + "." + localName;
            } else if (localName.length() > 0 && Character.isJavaIdentifierStart(localName.charAt(0))) {
                for (char c2 : localName.toCharArray()) {
                    if (Character.isJavaIdentifierPart(c2)) continue;
                    localName = null;
                    break;
                }
                if (localName != null) {
                    text = localName;
                }
            }
        }
        int castStart = text.lastIndexOf(40);
        int castEnd = text.indexOf(41, castStart);
        if (castStart >= 0 && castStart < castEnd && (info2 = this.infoMap.getFirst(text.substring(castStart + 1, castEnd))) != null && info2.valueTypes != null && info2.valueTypes.length > 0) {
            text = text.substring(0, castStart + 1) + info2.valueTypes[0] + text.substring(castEnd);
        }
        return text;
    }

    void containers(Context context, DeclarationList declList) throws ParserException {
        ArrayList<String> basicContainers = new ArrayList<String>();
        for (Info info : this.infoMap.get("basic/containers")) {
            basicContainers.addAll(Arrays.asList(info.cppTypes));
        }
        for (String containerName : basicContainers) {
            LinkedHashSet<Info> infoSet = new LinkedHashSet<Info>();
            infoSet.addAll(this.leafInfoMap.get("const " + containerName));
            infoSet.addAll(this.leafInfoMap.get(containerName));
            for (Info info : infoSet) {
                int i2;
                int j2;
                boolean purify;
                Type valueType;
                Type indexType;
                Declaration decl = new Declaration();
                if (info == null || info.skip || !info.define || !info.cppNames[0].contains(containerName)) {
                    if (info == null || info.javaText == null) continue;
                    decl.type = new Type(info.pointerTypes[0]);
                    decl.text = info.javaText;
                    declList.add(decl);
                    continue;
                }
                int dim = containerName.toLowerCase().endsWith("optional") || containerName.toLowerCase().endsWith("variant") || containerName.toLowerCase().endsWith("tuple") || containerName.toLowerCase().endsWith("pair") ? 0 : 1;
                boolean constant = info.cppNames[0].startsWith("const ");
                boolean resizable = !constant;
                Type containerType = new Parser(this, info.cppNames[0]).type(context);
                Type firstType = null;
                Type secondType = null;
                if (containerType.arguments == null || containerType.arguments.length == 0 || containerType.arguments[0] == null || containerType.arguments[containerType.arguments.length - 1] == null) continue;
                if (containerType.arguments.length > 1 && containerType.arguments[1].javaName.length() > 0) {
                    resizable = false;
                    indexType = containerType.arguments[0];
                    valueType = containerType.arguments[1];
                } else {
                    resizable &= containerType.arguments.length == 1;
                    indexType = new Type();
                    indexType.value = true;
                    indexType.cppName = "size_t";
                    indexType.javaName = "long";
                    valueType = containerType.arguments[0];
                }
                String indexFunction = "(function = \"at\")";
                String iteratorType = "iterator";
                String keyVariable = "first";
                String valueVariable = "second";
                boolean dict = false;
                boolean list = resizable;
                boolean tuple = false;
                if (valueType.javaName == null || valueType.javaName.length() == 0 || containerName.toLowerCase().endsWith("bitset")) {
                    indexFunction = "";
                    valueType.javaName = "boolean";
                    resizable = false;
                } else if (containerName.toLowerCase().endsWith("dict")) {
                    indexFunction = "(function = \"operator []\")";
                    iteratorType = "Iterator";
                    keyVariable = "key()";
                    valueVariable = "value()";
                    dict = true;
                } else if (containerName.toLowerCase().endsWith("list") || containerName.toLowerCase().endsWith("optional") || containerName.toLowerCase().endsWith("variant") || containerName.toLowerCase().endsWith("tuple") || containerName.toLowerCase().endsWith("set")) {
                    if (containerType.arguments.length > 1) {
                        valueType = indexType;
                    }
                    indexType = null;
                    resizable = false;
                    list = containerName.toLowerCase().endsWith("list");
                    tuple = containerName.toLowerCase().endsWith("tuple");
                } else if (!constant && !resizable) {
                    indexFunction = "";
                }
                while (valueType.cppName.startsWith(containerName) && this.leafInfoMap.get(valueType.cppName, false).size() == 0) {
                    ++dim;
                    valueType = valueType.arguments[0];
                }
                int valueTemplate = valueType.cppName.indexOf("<");
                if (containerName.toLowerCase().endsWith("pair")) {
                    firstType = containerType.arguments[0];
                    secondType = containerType.arguments[1];
                } else if (valueTemplate >= 0 && valueType.cppName.substring(0, valueTemplate).toLowerCase().endsWith("pair")) {
                    firstType = valueType.arguments[0];
                    secondType = valueType.arguments[1];
                }
                LinkedHashSet<Type> typeSet = new LinkedHashSet<Type>();
                typeSet.addAll(Arrays.asList(firstType, secondType, indexType, valueType));
                typeSet.addAll(Arrays.asList(containerType.arguments));
                for (Type type : typeSet) {
                    Info info2;
                    if (type == null) continue;
                    if (type.annotations == null || type.annotations.length() == 0) {
                        type.annotations = (type.constValue ? "@Const " : "") + (type.indirections == 0 && !type.value ? "@ByRef " : "");
                    }
                    if ((info2 = this.infoMap.getFirst(type.cppName)) == null || !info2.cast || type.annotations.contains("@Cast") || type.javaName.contains("@Cast")) continue;
                    String cast = type.cppName;
                    if (type.constValue && !cast.startsWith("const ")) {
                        cast = "const " + cast;
                    }
                    if (type.indirections > 0) {
                        for (int i3 = 0; i3 < type.indirections; ++i3) {
                            cast = cast + "*";
                        }
                    } else if (!type.value) {
                        cast = cast + "*";
                    }
                    if (type.reference) {
                        cast = cast + "&";
                    }
                    if (type.rvalue) {
                        cast = cast + "&&";
                    }
                    if (type.constPointer && !cast.endsWith(" const")) {
                        cast = cast + " const";
                    }
                    type.annotations = "@Cast(\"" + cast + "\") " + type.annotations;
                }
                String arrayBrackets = "";
                for (int i4 = 0; i4 < dim - 1; ++i4) {
                    arrayBrackets = arrayBrackets + "[]";
                }
                int annotation = containerType.javaName.lastIndexOf(32);
                containerType.annotations = containerType.annotations + containerType.javaName.substring(0, annotation + 1);
                containerType.javaName = containerType.javaName.substring(annotation + 1);
                decl.type = new Type(containerType.javaName);
                decl.text = decl.text + (dim == 0 ? "\n@NoOffset " : "\n") + "@Name(\"" + containerType.cppName + "\") public static class " + containerType.javaName + " extends Pointer {\n    static { Loader.load(); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public " + containerType.javaName + "(Pointer p) { super(p); }\n";
                boolean bl2 = purify = info != null && info.purify;
                if (!constant && !purify && (dim == 0 || containerType.arguments.length == 1 && indexType != null) && firstType != null && secondType != null) {
                    String[] stringArray;
                    String[] stringArray2;
                    if (firstType.javaNames != null) {
                        stringArray2 = firstType.javaNames;
                    } else {
                        String[] stringArray3 = new String[1];
                        stringArray2 = stringArray3;
                        stringArray3[0] = firstType.javaName;
                    }
                    String[] firstNames = stringArray2;
                    if (secondType.javaNames != null) {
                        stringArray = secondType.javaNames;
                    } else {
                        String[] stringArray4 = new String[1];
                        stringArray = stringArray4;
                        stringArray4[0] = secondType.javaName;
                    }
                    String[] secondNames = stringArray;
                    String brackets = arrayBrackets + (dim > 0 ? "[]" : "");
                    for (int n2 = 0; n2 < firstNames.length || n2 < secondNames.length; ++n2) {
                        decl.text = decl.text + "    public " + containerType.javaName + "(" + firstNames[Math.min(n2, firstNames.length - 1)] + brackets + " firstValue, " + secondNames[Math.min(n2, secondNames.length - 1)] + brackets + " secondValue) { this(" + (dim > 0 ? "Math.min(firstValue.length, secondValue.length)" : "") + "); put(firstValue, secondValue); }\n";
                    }
                } else if (resizable && !purify && firstType == null && secondType == null) {
                    String[] stringArray;
                    if (valueType.javaNames != null) {
                        stringArray = valueType.javaNames;
                    } else {
                        String[] stringArray5 = new String[1];
                        stringArray = stringArray5;
                        stringArray5[0] = valueType.javaName;
                    }
                    for (String javaName : stringArray) {
                        if (dim < 2 && !javaName.equals("int") && !javaName.equals("long")) {
                            decl.text = decl.text + "    public " + containerType.javaName + "(" + javaName + " value) { this(1); put(0, value); }\n";
                        }
                        decl.text = decl.text + "    public " + containerType.javaName + "(" + javaName + arrayBrackets + " ... array) { this(array.length); put(array); }\n";
                    }
                } else if (indexType == null && dim == 0 && !constant && !purify) {
                    int n3 = 0;
                    String valueNames = "";
                    String valueNames2 = "";
                    String separator = "";
                    for (Type type : containerType.arguments) {
                        String[] stringArray;
                        if (tuple) {
                            valueNames = valueNames + separator + type.annotations + type.javaName + " value" + n3;
                            valueNames2 = valueNames2 + separator + "value" + n3;
                            separator = ", ";
                            ++n3;
                            continue;
                        }
                        if (type.javaNames != null) {
                            stringArray = type.javaNames;
                        } else {
                            String[] stringArray6 = new String[1];
                            stringArray = stringArray6;
                            stringArray6[0] = type.javaName;
                        }
                        for (String javaName : stringArray) {
                            decl.text = decl.text + "    public " + containerType.javaName + "(" + javaName + " value) { this(); put(value); }\n";
                        }
                    }
                    if (tuple) {
                        decl.text = decl.text + "    public " + containerType.javaName + "(" + valueNames + ") { allocate(" + valueNames2 + "); }\n    private native void allocate(" + valueNames + ");\n";
                    }
                }
                if (!purify) {
                    decl.text = decl.text + "    public " + containerType.javaName + "()       { allocate();  }\n" + (!resizable ? "" : "    public " + containerType.javaName + "(long n) { allocate(n); }\n") + "    private native void allocate();\n" + (!resizable ? "" : "    private native void allocate(@Cast(\"size_t\") long n);\n") + (constant ? "\n\n" : "    public native @Name(\"operator =\") @ByRef " + containerType.javaName + " put(@ByRef " + containerType.annotations + containerType.javaName + " x);\n\n");
                }
                for (int i5 = 0; i5 < dim; ++i5) {
                    String indexAnnotation = i5 > 0 ? "@Index(" + (i5 > 1 ? "value = " + i5 + ", " : "") + "function = \"at\") " : "";
                    String indices = "";
                    String indices2 = "";
                    String separator2 = "";
                    for (j2 = 0; indexType != null && j2 < i5; ++j2) {
                        indices = indices + separator2 + indexType.annotations + indexType.javaName + " " + (char)(105 + j2);
                        indices2 = indices2 + separator2 + (char)(105 + j2);
                        separator2 = ", ";
                    }
                    decl.text = decl.text + "    public boolean empty(" + indices + ") { return size(" + indices2 + ") == 0; }\n    public native " + indexAnnotation + "long size(" + indices + ");\n" + (!resizable ? "" : "    public void clear(" + indices + ") { resize(" + indices2 + separator2 + "0); }\n    public native " + indexAnnotation + "void resize(" + indices + separator2 + "@Cast(\"size_t\") long n);\n");
                }
                String params = "";
                String separator = "";
                for (i2 = 0; indexType != null && i2 < dim; ++i2) {
                    params = params + separator + indexType.annotations + indexType.javaName + " " + (char)(105 + i2);
                    separator = ", ";
                }
                if ((dim == 0 || indexType != null) && firstType != null && secondType != null) {
                    int i6;
                    String indexAnnotation = dim == 0 ? "@MemberGetter " : "@Index(" + (dim > 1 ? "value = " + dim + ", " : "") + "function = \"at\") ";
                    decl.text = decl.text + "\n    " + indexAnnotation + "public native " + firstType.annotations + firstType.javaName + " first(" + params + "); public native " + containerType.javaName + " first(" + params + separator + firstType.javaName.substring(firstType.javaName.lastIndexOf(32) + 1) + " first);\n    " + indexAnnotation + "public native " + secondType.annotations + secondType.javaName + " second(" + params + ");  public native " + containerType.javaName + " second(" + params + separator + secondType.javaName.substring(secondType.javaName.lastIndexOf(32) + 1) + " second);\n";
                    for (i6 = 1; !constant && firstType.javaNames != null && i6 < firstType.javaNames.length; ++i6) {
                        decl.text = decl.text + "    @MemberSetter @Index" + indexFunction + " public native " + containerType.javaName + " first(" + params + separator + firstType.annotations + firstType.javaNames[i6] + " first);\n";
                    }
                    for (i6 = 1; !constant && secondType.javaNames != null && i6 < secondType.javaNames.length; ++i6) {
                        decl.text = decl.text + "    @MemberSetter @Index" + indexFunction + " public native " + containerType.javaName + " second(" + params + separator + secondType.annotations + secondType.javaNames[i6] + " second);\n";
                    }
                } else {
                    if (indexType != null) {
                        decl.text = decl.text + "\n    @Index" + indexFunction + " public native " + valueType.annotations + valueType.javaName + " get(" + params + ");\n";
                        if (!constant) {
                            decl.text = decl.text + "    public native " + containerType.javaName + " put(" + params + separator + valueType.javaName.substring(valueType.javaName.lastIndexOf(32) + 1) + " value);\n";
                        }
                        for (i2 = 1; !constant && valueType.javaNames != null && i2 < valueType.javaNames.length; ++i2) {
                            decl.text = decl.text + "    @ValueSetter @Index" + indexFunction + " public native " + containerType.javaName + " put(" + params + separator + valueType.annotations + valueType.javaNames[i2] + " value);\n";
                        }
                    } else if (dim == 0) {
                        int n4 = 0;
                        Type[] i7 = containerType.arguments;
                        int separator2 = i7.length;
                        for (j2 = 0; j2 < separator2; ++j2) {
                            Type type = i7[j2];
                            if (containerType.arguments.length == 1 && !tuple) {
                                decl.text = decl.text + "\n    @Name(\"value\") public native " + type.annotations + type.javaName + " get();\n";
                            } else {
                                int namespace = containerName.lastIndexOf("::");
                                String ns2 = containerName.substring(0, namespace);
                                decl.text = decl.text + "    public " + type.annotations + type.javaName + " get" + n4 + "() { return get" + n4 + "(this); }\n    @Namespace @Name(\"" + ns2 + "::get<" + n4 + ">\") public static native " + type.annotations + type.javaName + " get" + n4 + "(@ByRef " + containerType.javaName + " container);\n";
                            }
                            if (!constant && !tuple) {
                                decl.text = decl.text + "    @ValueSetter public native " + containerType.javaName + " put(" + type.annotations + type.javaName + " value);\n";
                            }
                            for (int i8 = 1; !constant && !tuple && type.javaNames != null && i8 < type.javaNames.length; ++i8) {
                                decl.text = decl.text + "    @ValueSetter public native " + containerType.javaName + " put(" + type.annotations + type.javaNames[i8] + " value);\n";
                            }
                            ++n4;
                        }
                    }
                    if (dim == 1 && !containerName.toLowerCase().endsWith("bitset") && containerType.arguments.length >= 1 && containerType.arguments[containerType.arguments.length - 1].javaName.length() > 0) {
                        decl.text = decl.text + "\n";
                        if (!constant) {
                            if (list) {
                                decl.text = decl.text + "    public native @ByVal Iterator insert(@ByVal Iterator pos, " + valueType.annotations + valueType.javaName + " value);\n    public native @ByVal Iterator erase(@ByVal Iterator pos);\n";
                            } else if (indexType == null) {
                                decl.text = decl.text + "    public native void insert(" + valueType.annotations + valueType.javaName + " value);\n    public native void erase(" + valueType.annotations + valueType.javaName + " value);\n";
                            } else if (!dict) {
                                decl.text = decl.text + "    public native void erase(@ByVal Iterator pos);\n";
                            }
                        }
                        if (!(indexType == null || indexType.annotations.contains("@Const") || indexType.annotations.contains("@Cast") || indexType.value)) {
                            indexType.annotations = indexType.annotations + "@Const ";
                        }
                        if (!valueType.annotations.contains("@Const") && !valueType.value) {
                            valueType.annotations = valueType.annotations + "@Const ";
                        }
                        decl.text = decl.text + "    public native @ByVal Iterator begin();\n    public native @ByVal Iterator end();\n    @NoOffset @Name(\"" + iteratorType + "\") public static class Iterator extends Pointer {\n        public Iterator(Pointer p) { super(p); }\n        public Iterator() { }\n\n        public native @Name(\"operator ++\") @ByRef Iterator increment();\n        public native @Name(\"operator ==\") boolean equals(@ByRef Iterator it);\n" + (containerType.arguments.length > 1 && indexType != null ? "        public native @Name(\"operator *()." + keyVariable + "\") @MemberGetter " + indexType.annotations + indexType.javaName + " first();\n        public native @Name(\"operator *()." + valueVariable + "\") @MemberGetter " + valueType.annotations + valueType.javaName + " second();\n" : "        public native @Name(\"operator *\") " + valueType.annotations + valueType.javaName + " get();\n") + "    }\n";
                    }
                    if (resizable) {
                        int i9;
                        valueType.javaName = valueType.javaName.substring(valueType.javaName.lastIndexOf(32) + 1);
                        decl.text = decl.text + "\n    public " + valueType.javaName + arrayBrackets + "[] get() {\n";
                        String indent = "        ";
                        String indices = "";
                        String args = "";
                        String brackets = arrayBrackets;
                        separator = "";
                        for (i9 = 0; i9 < dim; ++i9) {
                            char c2 = (char)(105 + i9);
                            decl.text = decl.text + indent + (i9 == 0 ? valueType.javaName + brackets + "[] " : "") + "array" + indices + " = new " + valueType.javaName + "[size(" + args + ") < Integer.MAX_VALUE ? (int)size(" + args + ") : Integer.MAX_VALUE]" + brackets + ";\n" + indent + "for (int " + c2 + " = 0; " + c2 + " < array" + indices + ".length; " + c2 + "++) {\n";
                            indent = indent + "    ";
                            indices = indices + "[" + c2 + "]";
                            args = args + separator + c2;
                            brackets = brackets.length() < 2 ? "" : brackets.substring(2);
                            separator = ", ";
                        }
                        decl.text = decl.text + indent + "array" + indices + " = get(" + args + ");\n";
                        for (i9 = 0; i9 < dim; ++i9) {
                            indent = indent.substring(4);
                            decl.text = decl.text + indent + "}\n";
                        }
                        decl.text = decl.text + "        return array;\n    }\n    @Override public String toString() {\n        return java.util.Arrays." + (dim < 2 ? "toString" : "deepToString") + "(get());\n    }\n";
                    }
                }
                if (!constant && (dim == 0 || containerType.arguments.length == 1 && indexType != null) && firstType != null && secondType != null) {
                    String[] stringArray;
                    String[] stringArray7;
                    if (firstType.javaNames != null) {
                        stringArray7 = firstType.javaNames;
                    } else {
                        String[] stringArray8 = new String[1];
                        stringArray7 = stringArray8;
                        stringArray8[0] = firstType.javaName;
                    }
                    String[] firstNames = stringArray7;
                    if (secondType.javaNames != null) {
                        stringArray = secondType.javaNames;
                    } else {
                        String[] stringArray9 = new String[1];
                        stringArray = stringArray9;
                        stringArray9[0] = secondType.javaName;
                    }
                    String[] secondNames = stringArray;
                    String brackets = arrayBrackets + (dim > 0 ? "[]" : "");
                    for (int n5 = 0; n5 < firstNames.length || n5 < secondNames.length; ++n5) {
                        int i10;
                        String firstName = firstNames[Math.min(n5, firstNames.length - 1)];
                        String secondName = secondNames[Math.min(n5, secondNames.length - 1)];
                        firstName = firstName.substring(firstName.lastIndexOf(32) + 1);
                        secondName = secondName.substring(secondName.lastIndexOf(32) + 1);
                        decl.text = decl.text + "\n    public " + containerType.javaName + " put(" + firstName + brackets + " firstValue, " + secondName + brackets + " secondValue) {\n";
                        String indent = "        ";
                        String indices = "";
                        String args = "";
                        separator = "";
                        for (i10 = 0; i10 < dim; ++i10) {
                            char c3 = (char)(105 + i10);
                            decl.text = decl.text + indent + "for (int " + c3 + " = 0; " + c3 + " < firstValue" + indices + ".length && " + c3 + " < secondValue" + indices + ".length; " + c3 + "++) {\n";
                            indent = indent + "    ";
                            indices = indices + "[" + c3 + "]";
                            args = args + separator + c3;
                            separator = ", ";
                        }
                        decl.text = decl.text + indent + "first(" + args + separator + "firstValue" + indices + ");\n" + indent + "second(" + args + separator + "secondValue" + indices + ");\n";
                        for (i10 = 0; i10 < dim; ++i10) {
                            indent = indent.substring(4);
                            decl.text = decl.text + indent + "}\n";
                        }
                        decl.text = decl.text + "        return this;\n    }\n";
                    }
                } else if (resizable && firstType == null && secondType == null) {
                    String[] stringArray;
                    boolean first = true;
                    if (valueType.javaNames != null) {
                        stringArray = valueType.javaNames;
                    } else {
                        String[] stringArray10 = new String[1];
                        stringArray = stringArray10;
                        stringArray10[0] = valueType.javaName;
                    }
                    for (String javaName : stringArray) {
                        int i11;
                        javaName = javaName.substring(javaName.lastIndexOf(32) + 1);
                        decl.text = decl.text + "\n";
                        if (dim < 2) {
                            if (first) {
                                decl.text = decl.text + "    public " + javaName + " pop_back() {\n        long size = size();\n        " + javaName + " value = get(size - 1);\n        resize(size - 1);\n        return value;\n    }\n";
                            }
                            decl.text = decl.text + "    public " + containerType.javaName + " push_back(" + javaName + " value) {\n        long size = size();\n        resize(size + 1);\n        return put(size, value);\n    }\n    public " + containerType.javaName + " put(" + javaName + " value) {\n        if (size() != 1) { resize(1); }\n        return put(0, value);\n    }\n";
                        }
                        decl.text = decl.text + "    public " + containerType.javaName + " put(" + javaName + arrayBrackets + " ... array) {\n";
                        String indent = "        ";
                        String indices = "";
                        String args = "";
                        separator = "";
                        for (i11 = 0; i11 < dim; ++i11) {
                            char c4 = (char)(105 + i11);
                            decl.text = decl.text + indent + "if (size(" + args + ") != array" + indices + ".length) { resize(" + args + separator + "array" + indices + ".length); }\n" + indent + "for (int " + c4 + " = 0; " + c4 + " < array" + indices + ".length; " + c4 + "++) {\n";
                            indent = indent + "    ";
                            indices = indices + "[" + c4 + "]";
                            args = args + separator + c4;
                            separator = ", ";
                        }
                        decl.text = decl.text + indent + "put(" + args + separator + "array" + indices + ");\n";
                        for (i11 = 0; i11 < dim; ++i11) {
                            indent = indent.substring(4);
                            decl.text = decl.text + indent + "}\n";
                        }
                        decl.text = decl.text + "        return this;\n    }\n";
                        first = false;
                    }
                }
                if (info != null && info.javaText != null) {
                    declList.spacing = "\n    ";
                    decl.text = decl.text + declList.rescan(info.javaText) + "\n";
                    declList.spacing = null;
                }
                decl.text = decl.text + "}\n";
                declList.add(decl);
            }
        }
    }

    TemplateMap template(Context context) throws ParserException {
        if (!this.tokens.get().match(Token.TEMPLATE)) {
            return null;
        }
        TemplateMap map = new TemplateMap(context.templateMap);
        this.tokens.next().expect(Character.valueOf('<'));
        Token token = this.tokens.next();
        while (!token.match(Token.EOF)) {
            if (token.match(Token.CLASS, Token.TYPENAME)) {
                Token t2 = this.tokens.next();
                if (t2.match("...")) {
                    map.variadic = true;
                    t2 = this.tokens.next();
                }
                if (t2.match(5)) {
                    String key = t2.value;
                    map.put(key, map.get(key));
                    token = this.tokens.next();
                }
            } else if (token.match(5)) {
                String key;
                Type type = this.type(context);
                Token t3 = this.tokens.get();
                if (t3.match(5)) {
                    key = t3.value;
                    map.put(key, map.get(key));
                    token = this.tokens.next();
                } else if (type != null) {
                    key = type.cppName;
                    map.put(key, map.get(key));
                }
            }
            if (!token.match(Character.valueOf(','), Character.valueOf('>'))) {
                int count = 0;
                token = this.tokens.get();
                while (!(token.match(Token.EOF) || count == 0 && token.match(Character.valueOf(','), Character.valueOf('>')))) {
                    if (token.match(Character.valueOf('<'), Character.valueOf('('))) {
                        ++count;
                    } else if (token.match(Character.valueOf('>'), Character.valueOf(')'))) {
                        --count;
                    }
                    token = this.tokens.next();
                }
            }
            if (token.expect(Character.valueOf(','), Character.valueOf('>')).match(Character.valueOf('>'))) {
                if (!this.tokens.next().match(Token.TEMPLATE)) break;
                this.tokens.next().expect(Character.valueOf('<'));
            }
            token = this.tokens.next();
        }
        return map;
    }

    Type[] templateArguments(Context context) throws ParserException {
        if (!this.tokens.get().match(Character.valueOf('<'))) {
            return null;
        }
        ArrayList<Type> arguments = new ArrayList<Type>();
        Token token = this.tokens.next();
        while (!token.match(Token.EOF)) {
            Type type = this.type(context);
            arguments.add(type);
            token = this.tokens.get();
            if (!token.match(Character.valueOf(','), Character.valueOf('>'))) {
                int count = 0;
                token = this.tokens.get();
                while (!(token.match(Token.EOF) || count == 0 && token.match(Character.valueOf(','), Character.valueOf('>')))) {
                    if (token.match(Character.valueOf('<'), Character.valueOf('('))) {
                        ++count;
                    } else if (token.match(Character.valueOf('>'), Character.valueOf(')'))) {
                        --count;
                    }
                    for (int i2 = 0; i2 < type.indirections; ++i2) {
                        type.cppName = type.cppName + "*";
                    }
                    type.indirections = 0;
                    type.cppName = type.cppName + token;
                    if (token.match(Token.CONST, Token.__CONST)) {
                        type.cppName = type.cppName + " ";
                    }
                    token = this.tokens.next();
                }
                if (type.cppName.endsWith("*")) {
                    type.javaName = "PointerPointer";
                    type.annotations = type.annotations + "@Cast(\"" + type.cppName + "*\") ";
                }
            }
            if (token.expect(Character.valueOf(','), Character.valueOf('>')).match(Character.valueOf('>'))) break;
            token = this.tokens.next();
        }
        return arguments.toArray(new Type[arguments.size()]);
    }

    Type type(Context context) throws ParserException {
        return this.type(context, false);
    }

    /*
     * WARNING - void declaration
     */
    Type type(Context context, boolean definition) throws ParserException {
        Type type = new Type();
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        Token token = this.tokens.get();
        while (!token.match(Token.EOF)) {
            block87: {
                block80: {
                    block94: {
                        int backIndex;
                        block95: {
                            block93: {
                                block92: {
                                    block91: {
                                        block90: {
                                            block89: {
                                                block88: {
                                                    block85: {
                                                        block86: {
                                                            block84: {
                                                                block83: {
                                                                    block82: {
                                                                        String simpleName;
                                                                        block81: {
                                                                            block79: {
                                                                                if (!token.match("::")) break block79;
                                                                                Info info = this.infoMap.getFirst(type.cppName, false);
                                                                                if (info != null && info.pointerTypes != null && info.pointerTypes.length > 0 && !type.cppName.contains("::") && token.spacing.length() > 0) break;
                                                                                type.cppName = type.cppName + token;
                                                                                break block80;
                                                                            }
                                                                            if (token.match(Token.DECLTYPE)) {
                                                                                type.cppName = type.cppName + token.toString() + this.tokens.next().expect(Character.valueOf('('));
                                                                                int count = 1;
                                                                                token = this.tokens.next();
                                                                                while (!token.match(Token.EOF)) {
                                                                                    if (token.match(Character.valueOf('('))) {
                                                                                        ++count;
                                                                                    } else if (token.match(Character.valueOf(')'))) {
                                                                                        --count;
                                                                                    }
                                                                                    type.cppName = type.cppName + token;
                                                                                    if (count == 0) break;
                                                                                    token = this.tokens.next();
                                                                                }
                                                                                this.tokens.next();
                                                                                break;
                                                                            }
                                                                            if (!token.match(Character.valueOf('<'))) break block81;
                                                                            type.arguments = this.templateArguments(context);
                                                                            type.cppName = type.cppName + "<";
                                                                            String separator = "";
                                                                            for (Type t2 : type.arguments) {
                                                                                String s2;
                                                                                if (t2 == null) continue;
                                                                                type.cppName = type.cppName + separator;
                                                                                Info info = this.infoMap.getFirst(t2.cppName);
                                                                                String string = s2 = info != null && info.cppTypes != null ? info.cppTypes[0] : t2.cppName;
                                                                                if (t2.constValue && !s2.startsWith("const ")) {
                                                                                    s2 = "const " + s2;
                                                                                }
                                                                                int n2 = s2.indexOf(40);
                                                                                for (int i2 = 0; i2 < t2.indirections; ++i2) {
                                                                                    s2 = n2 >= 0 ? s2.substring(0, n2) + "*" + s2.substring(n2) : s2 + "*";
                                                                                }
                                                                                if (t2.reference) {
                                                                                    s2 = n2 >= 0 ? s2.substring(0, n2) + "&" + s2.substring(n2) : s2 + "&";
                                                                                }
                                                                                if (t2.rvalue) {
                                                                                    s2 = n2 >= 0 ? s2.substring(0, n2) + "&&" + s2.substring(n2) : s2 + "&&";
                                                                                }
                                                                                if (t2.constPointer && !s2.endsWith(" const")) {
                                                                                    s2 = s2 + " const";
                                                                                }
                                                                                type.cppName = type.cppName + s2;
                                                                                separator = ",";
                                                                            }
                                                                            type.cppName = type.cppName + (type.cppName.endsWith(">") ? " >" : ">");
                                                                            break block80;
                                                                        }
                                                                        if (!token.match(Token.CONST, Token.__CONST, Token.CONSTEXPR)) break block82;
                                                                        int template = type.cppName.lastIndexOf(60);
                                                                        String string = simpleName = template >= 0 ? type.cppName.substring(0, template) : type.cppName;
                                                                        if (!simpleName.trim().contains(" ") || type.simple) {
                                                                            type.constValue = true;
                                                                        } else {
                                                                            type.constPointer = true;
                                                                        }
                                                                        if (token.match(Token.CONSTEXPR)) {
                                                                            type.constExpr = true;
                                                                        }
                                                                        break block80;
                                                                    }
                                                                    if (token.match(Character.valueOf('*'))) {
                                                                        ++type.indirections;
                                                                        this.tokens.next();
                                                                        break;
                                                                    }
                                                                    if (token.match(Character.valueOf('&'))) {
                                                                        type.reference = true;
                                                                        this.tokens.next();
                                                                        break;
                                                                    }
                                                                    if (token.match("&&")) {
                                                                        type.rvalue = true;
                                                                        this.tokens.next();
                                                                        break;
                                                                    }
                                                                    if (!token.match(Character.valueOf('~'))) break block83;
                                                                    type.cppName = type.cppName + "~";
                                                                    type.destructor = true;
                                                                    break block80;
                                                                }
                                                                if (!token.match(Token.STATIC)) break block84;
                                                                type.staticMember = true;
                                                                break block80;
                                                            }
                                                            if (!token.match(Token.OPERATOR)) break block85;
                                                            if (type.cppName.length() != 0) break block86;
                                                            type.operator = true;
                                                            this.tokens.next();
                                                            break block87;
                                                        }
                                                        if (!type.cppName.endsWith("::")) break;
                                                        type.operator = true;
                                                        this.tokens.next();
                                                        break;
                                                    }
                                                    if (!token.match(Token.USING)) break block88;
                                                    type.using = true;
                                                    break block80;
                                                }
                                                if (!token.match(Token.FRIEND)) break block89;
                                                type.friend = true;
                                                break block80;
                                            }
                                            if (!token.match(Token.TYPEDEF)) break block90;
                                            type.typedef = true;
                                            break block80;
                                        }
                                        if (!token.match(Token.VIRTUAL)) break block91;
                                        type.virtual = true;
                                        break block80;
                                    }
                                    if (!token.match(Token.ENUM, Token.EXPLICIT, Token.EXTERN, Token.INLINE, Token.CLASS, Token.FINAL, Token.INTERFACE, Token.__INTERFACE, Token.MUTABLE, Token.NAMESPACE, Token.STRUCT, Token.UNION, Token.TYPENAME, Token.REGISTER, Token.THREAD_LOCAL, Token.VOLATILE)) break block92;
                                    token = this.tokens.next();
                                    break block87;
                                }
                                if (!token.match(this.infoMap.getFirst((String)"basic/types").cppTypes) || this.tokens.get(1).match(Character.valueOf('<')) || type.cppName.length() != 0 && !type.simple) break block93;
                                type.cppName = type.cppName + token.value + " ";
                                type.simple = true;
                                break block80;
                            }
                            if (!token.match(5, "[[")) break block94;
                            backIndex = this.tokens.index;
                            Attribute attr = this.attribute();
                            if (attr == null || !attr.annotation && !token.match("[[")) break block95;
                            type.annotations = type.annotations + attr.javaName;
                            attributes.add(attr);
                            break block87;
                        }
                        this.tokens.index = backIndex;
                        if (type.cppName.length() == 0 || type.cppName.endsWith("::") || type.cppName.endsWith("~")) {
                            type.cppName = type.cppName + token.value;
                        } else if (type.cppName.endsWith("::template")) {
                            type.cppName = type.cppName + " " + token.value;
                        } else {
                            Info info = this.infoMap.getFirst(this.tokens.get((int)1).value);
                            if (info != null && info.annotations != null || !this.tokens.get(1).match(Character.valueOf('*'), Character.valueOf('&'), 5, Token.CONST, Token.__CONST, Token.CONSTEXPR, Token.FINAL)) {
                                break;
                            }
                        }
                        break block80;
                    }
                    if (!token.match(Character.valueOf('}'))) break;
                    type.anonymous = true;
                    this.tokens.next();
                    break;
                }
                this.tokens.next();
            }
            token = this.tokens.get();
        }
        if (attributes.size() > 0) {
            type.attributes = attributes.toArray(new Attribute[attributes.size()]);
        }
        type.cppName = type.cppName.trim();
        if (this.tokens.get().match("...")) {
            this.tokens.next();
            if (this.tokens.get().match(5)) {
                this.tokens.next();
            }
            return null;
        }
        if (type.operator) {
            token = this.tokens.get();
            while (!token.match(Token.EOF, Character.valueOf('('), Character.valueOf(';'))) {
                type.cppName = type.cppName + token;
                token = this.tokens.next();
            }
        }
        if (type.cppName.endsWith("*")) {
            ++type.indirections;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&")) {
            type.reference = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&&")) {
            type.rvalue = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 2);
        }
        if (context.templateMap != null) {
            String[] types = type.cppName.split("::");
            String separator = "";
            type.cppName = "";
            ArrayList<Type> arguments = new ArrayList<Type>();
            for (String string : types) {
                Type t2 = context.templateMap.get(string);
                type.cppName = type.cppName + separator + (t2 != null ? t2.cppName : string);
                if (t2 != null && t2.arguments != null) {
                    arguments.addAll(Arrays.asList(t2.arguments));
                }
                separator = "::";
            }
            if (arguments.size() > 0) {
                type.arguments = arguments.toArray(new Type[arguments.size()]);
            }
        }
        if (type.cppName.startsWith("const ")) {
            type.constValue = true;
            type.cppName = type.cppName.substring(6);
        }
        if (type.cppName.endsWith(" const")) {
            type.constPointer = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 6);
        }
        if (type.cppName.endsWith("*")) {
            ++type.indirections;
            if (type.reference) {
                type.constValue = false;
            }
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&")) {
            type.reference = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&&")) {
            type.rvalue = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 2);
        }
        if (type.cppName.endsWith(" const")) {
            type.constValue = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 6);
        }
        Info info = null;
        String shortName = type.cppName;
        String[] names = context.qualify(type.cppName);
        if (definition && names.length > 0) {
            String constName = type.constValue ? "const " + names[0] : names[0];
            constName = type.constPointer ? constName + " const" : constName;
            info = this.infoMap.getFirst(constName, false);
            type.cppName = names[0];
        } else {
            int template2;
            String groupName = context.cppName;
            String groupName2 = groupName;
            int n2 = template2 = groupName2 != null ? groupName2.lastIndexOf(60) : -1;
            if (template2 >= 0) {
                groupName2 = groupName2.substring(0, template2);
                template2 = groupName2.indexOf(60);
                if (!groupName2.contains(">") && template2 >= 0) {
                    groupName2 = groupName2.substring(0, template2);
                }
            }
            for (String name : names) {
                if (groupName2 != null && groupName2.endsWith("::" + shortName) && name.equals(groupName + "::" + shortName)) continue;
                String constName = type.constValue ? "const " + name : name;
                constName = type.constPointer ? constName + " const" : constName;
                info = this.infoMap.getFirst(constName, false);
                if (info != null) {
                    type.cppName = name;
                    break;
                }
                if (this.infoMap.getFirst(constName) == null) continue;
                type.cppName = name;
            }
        }
        if (info != null && info.cppTypes != null && info.cppTypes.length > 0) {
            type.cppName = info.cppTypes[0];
        }
        if (type.cppName.startsWith("const ")) {
            type.constValue = true;
            type.cppName = type.cppName.substring(6);
        }
        if (type.cppName.endsWith(" const")) {
            type.constPointer = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 6);
        }
        if (type.cppName.endsWith("*")) {
            ++type.indirections;
            if (type.reference) {
                type.constValue = false;
            }
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&")) {
            type.reference = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 1);
        }
        if (type.cppName.endsWith("&&")) {
            type.rvalue = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 2);
        }
        if (type.cppName.endsWith(" const")) {
            type.constValue = true;
            type.cppName = type.cppName.substring(0, type.cppName.length() - 6);
        }
        int namespace = type.cppName.lastIndexOf("::");
        int template = type.cppName.lastIndexOf(60);
        String string = type.javaName = namespace >= 0 && template < 0 ? type.cppName.substring(namespace + 2) : type.cppName;
        if (info != null) {
            if (type.indirections == 0 && !type.reference && info.valueTypes != null && info.valueTypes.length > 0) {
                type.javaName = info.valueTypes[0];
                type.javaNames = info.valueTypes;
                type.value = true;
            } else if (info.pointerTypes != null && info.pointerTypes.length > 0) {
                type.javaName = info.pointerTypes[0];
                type.javaNames = info.pointerTypes;
            } else if (info.javaNames != null && info.javaNames.length > 0) {
                type.javaName = info.javaNames[0];
                type.javaNames = info.javaNames;
            }
        }
        if (type.operator) {
            if (type.constValue && !type.constExpr) {
                type.annotations = type.annotations + "@Const ";
            }
            if (type.indirections == 0 && !type.reference && !type.value) {
                type.annotations = type.annotations + "@ByVal ";
            } else if (type.indirections == 0 && type.reference && !type.value) {
                type.annotations = type.annotations + "@ByRef ";
            }
            if (info != null && info.cast) {
                type.annotations = type.annotations + "@Cast(\"" + type.cppName + (!type.value ? "*" : "") + "\") ";
            }
            type.annotations = type.annotations + "@Name(\"operator " + (type.constValue && !type.constExpr ? "const " : "") + type.cppName + (type.indirections > 0 ? "*" : (type.reference ? "&" : "")) + "\") ";
        }
        if (info != null && info.annotations != null) {
            for (String s3 : info.annotations) {
                type.annotations = type.annotations + s3 + " ";
            }
        }
        if (context.cppName != null && type.javaName.length() > 0) {
            void var11_37;
            void var11_35;
            int namespace2;
            int template2;
            String cppName = type.cppName;
            String string2 = context.cppName;
            int n3 = template2 = string2 != null ? string2.lastIndexOf(60) : -1;
            if (template < 0 && template2 >= 0) {
                String string3 = string2.substring(0, template2);
                template2 = string3.indexOf(60);
                if (!string3.contains(">") && template2 >= 0) {
                    String string4 = string3.substring(0, template2);
                }
            } else if (template >= 0 && template2 < 0) {
                cppName = cppName.substring(0, template);
                namespace = cppName.lastIndexOf("::");
            }
            int n4 = namespace2 = var11_35 != null ? var11_35.lastIndexOf("::") : -1;
            if (namespace < 0 && namespace2 >= 0) {
                String string5 = var11_35.substring(namespace2 + 2);
            } else if (namespace >= 0 && namespace2 < 0) {
                cppName = cppName.substring(namespace + 2);
            }
            if (cppName.equals(var11_37) || var11_37.startsWith(cppName + "<")) {
                type.constructor = !type.destructor && !type.operator && type.indirections == 0 && !type.reference && this.tokens.get().match(Character.valueOf('('), Character.valueOf(':'));
            }
            type.javaName = context.shorten(type.javaName);
        }
        return type;
    }

    /*
     * WARNING - void declaration
     */
    Declarator declarator(Context context, String defaultName, int infoNumber, boolean useDefaults, int varNumber, boolean arrayAsPointer, boolean pointerAsArray) throws ParserException {
        String originalName;
        String[] infoList;
        Info info;
        Attribute convention;
        boolean fieldPointer;
        Declaration definition;
        Info groupInfo;
        int indirections2;
        int[] dims;
        Attribute attr;
        String cast;
        String precast;
        int count;
        String[] type;
        Declarator dcl;
        boolean using;
        boolean typedef;
        block176: {
            Token token;
            block170: {
                typedef = this.tokens.get().match(Token.TYPEDEF);
                using = this.tokens.get().match(Token.USING);
                if (using && defaultName != null) {
                    this.tokens.next().expect(5);
                    this.tokens.next().expect(Character.valueOf('='));
                    this.tokens.next();
                }
                dcl = new Declarator();
                type = this.type(context);
                if (type == null) {
                    return null;
                }
                typedef |= type.typedef;
                count = 0;
                int number = 0;
                Token token2 = this.tokens.get();
                while (number < varNumber && !token2.match(Token.EOF)) {
                    if (token2.match(Character.valueOf('('), Character.valueOf('['), Character.valueOf('{'))) {
                        ++count;
                    } else if (token2.match(Character.valueOf(')'), Character.valueOf(']'), Character.valueOf('}'))) {
                        --count;
                    } else if (token2.match("]]")) {
                        count -= 2;
                    } else if (count <= 0) {
                        if (token2.match(Character.valueOf(','))) {
                            ++number;
                        } else if (token2.match(Character.valueOf(';'))) {
                            this.tokens.next();
                            return null;
                        }
                    }
                    token2 = this.tokens.next();
                }
                precast = null;
                cast = type.cppName;
                if (varNumber == 0 && type.indirections > 0) {
                    dcl.indirections += type.indirections;
                    for (int i2 = 0; i2 < type.indirections; ++i2) {
                        cast = cast + "*";
                    }
                }
                if (type.constValue) {
                    cast = "const " + cast;
                }
                if (type.constPointer) {
                    dcl.constPointer = true;
                }
                if (varNumber == 0 && type.reference) {
                    dcl.reference = true;
                    cast = cast + "&";
                }
                if (varNumber == 0 && type.rvalue) {
                    dcl.rvalue = true;
                    cast = cast + "&&";
                }
                Token token3 = this.tokens.get();
                while (!token3.match(Token.EOF)) {
                    if (token3.match(Character.valueOf('*'))) {
                        ++dcl.indirections;
                    } else if (token3.match(Character.valueOf('&'))) {
                        dcl.reference = true;
                    } else if (token3.match("&&")) {
                        dcl.rvalue = true;
                    } else {
                        if (!token3.match(Token.CONST, Token.__CONST, Token.CONSTEXPR)) break;
                        dcl.constPointer = true;
                    }
                    cast = cast + token3;
                    token3 = this.tokens.next();
                }
                ArrayList<Attribute> attributes = new ArrayList<Attribute>();
                if (type.attributes != null) {
                    attributes.addAll(Arrays.asList(type.attributes));
                }
                int backIndex = this.tokens.index;
                attr = this.attribute();
                while (attr != null && attr.annotation) {
                    type.annotations = type.annotations + attr.javaName;
                    attributes.add(attr);
                    backIndex = this.tokens.index;
                    attr = this.attribute();
                }
                attr = null;
                this.tokens.index = backIndex;
                for (Attribute a2 : attributes) {
                    if (a2.javaName != null && a2.javaName.contains("@Name ") && a2.arguments.length() > 0 && Character.isJavaIdentifierStart(a2.arguments.charAt(0))) {
                        attr = a2;
                        for (char c2 : a2.arguments.toCharArray()) {
                            if (Character.isJavaIdentifierPart(c2)) continue;
                            attr = null;
                            break;
                        }
                    }
                    if (attr == null) continue;
                    type.annotations = type.annotations.replace("@Name ", "");
                    break;
                }
                count = 0;
                while (this.tokens.get().match(Character.valueOf('(')) && this.tokens.get(1).match(Character.valueOf('('))) {
                    this.tokens.next();
                    ++count;
                }
                dims = new int[256];
                indirections2 = 0;
                dcl.cppName = "";
                groupInfo = null;
                definition = new Declaration();
                fieldPointer = false;
                convention = null;
                for (Attribute a3 : attributes) {
                    if (!a3.annotation || a3.javaName.length() != 0 || a3.arguments.length() != 0) continue;
                    convention = a3;
                }
                if ((!this.tokens.get().match(Character.valueOf('(')) || using && !this.tokens.get(1).match(Character.valueOf('*')) && (!this.tokens.get(2).match("::") || !this.tokens.get(3).match(Character.valueOf('*')))) && (!typedef || !this.tokens.get(1).match(Character.valueOf('(')))) break block170;
                if (this.tokens.get().match(Character.valueOf('('))) {
                    this.tokens.next();
                }
                token = this.tokens.get();
                while (!token.match(Token.EOF)) {
                    block175: {
                        block172: {
                            block173: {
                                int backIndex2;
                                block174: {
                                    block171: {
                                        if (!token.match(Token.CONST, Token.__CONST, Token.CONSTEXPR)) break block171;
                                        dcl.constPointer = true;
                                        break block172;
                                    }
                                    if (!token.match(5, "::")) break block173;
                                    backIndex2 = this.tokens.index;
                                    Attribute attr2 = this.attribute();
                                    if (attr2 == null || !attr2.annotation) break block174;
                                    type.annotations = type.annotations + attr2.javaName;
                                    attributes.add(attr2);
                                    convention = attr2;
                                    break block175;
                                }
                                this.tokens.index = backIndex2;
                                dcl.cppName = dcl.cppName + token;
                                break block172;
                            }
                            if (token.match(Character.valueOf('*'))) {
                                ++indirections2;
                                if (dcl.cppName.endsWith("::")) {
                                    dcl.cppName = dcl.cppName.substring(0, dcl.cppName.length() - 2);
                                    for (String name : context.qualify(dcl.cppName)) {
                                        groupInfo = this.infoMap.getFirst(name, false);
                                        if (groupInfo != null) {
                                            dcl.cppName = name;
                                            break;
                                        }
                                        if (this.infoMap.getFirst(name) == null) continue;
                                        dcl.cppName = name;
                                    }
                                    definition.text = definition.text + "@Namespace(\"" + dcl.cppName + "\") ";
                                } else if (convention != null || dcl.cppName.length() > 0) {
                                    definition.text = definition.text + "@Convention(\"" + (convention != null ? convention.cppName : dcl.cppName) + "\") ";
                                    convention = null;
                                }
                                dcl.cppName = "";
                            } else if (token.match(Character.valueOf('['))) {
                                Token n2 = this.tokens.get(1);
                                try {
                                    dims[dcl.indices++] = n2.match(1) ? Integer.decode(n2.value) : -1;
                                }
                                catch (NumberFormatException e2) {
                                    dims[dcl.indices] = -1;
                                }
                            } else if (token.match(Character.valueOf('('), Character.valueOf(')'))) break;
                        }
                        this.tokens.next();
                    }
                    token = this.tokens.get();
                }
                if (!this.tokens.get().match(Character.valueOf(')'))) break block176;
                this.tokens.next();
                break block176;
            }
            if (this.tokens.get().match(5, "::")) {
                token = this.tokens.get();
                while (!token.match(Token.EOF)) {
                    if (dcl.cppName.length() > 1 && token.match(Character.valueOf('*'))) {
                        dcl.cppName = dcl.cppName.substring(0, dcl.cppName.length() - 2);
                        for (String name : context.qualify(dcl.cppName)) {
                            groupInfo = this.infoMap.getFirst(name, false);
                            if (groupInfo != null) {
                                dcl.cppName = name;
                                break;
                            }
                            if (this.infoMap.getFirst(name) == null) continue;
                            dcl.cppName = name;
                        }
                        definition.text = definition.text + "@Namespace(\"" + dcl.cppName + "\") ";
                        token = this.tokens.get();
                        while (!token.match(Token.EOF) && token.match(Character.valueOf('*'))) {
                            ++indirections2;
                            token = this.tokens.next();
                        }
                        dcl.cppName = token.match(5) ? token.toString() : "";
                        fieldPointer = groupInfo != null;
                    } else if (token.match("::")) {
                        dcl.cppName = dcl.cppName + token;
                    } else if (token.match(Token.OPERATOR)) {
                        dcl.operator = true;
                        if (!this.tokens.get(1).match(5) || this.tokens.get(1).match(Token.NEW, Token.DELETE)) {
                            dcl.cppName = dcl.cppName + "operator " + this.tokens.next();
                            token = this.tokens.next();
                            while (!token.match(Token.EOF, Character.valueOf('('))) {
                                dcl.cppName = dcl.cppName + token;
                                token = this.tokens.next();
                            }
                            break;
                        }
                    } else if (token.match(Character.valueOf('<'))) {
                        dcl.cppName = dcl.cppName + token;
                        int count2 = 0;
                        token = this.tokens.next();
                        while (!token.match(Token.EOF)) {
                            dcl.cppName = dcl.cppName + token;
                            if (count2 != 0 || !token.match(Character.valueOf('>'))) {
                                if (token.match(Character.valueOf('<'))) {
                                    ++count2;
                                } else if (token.match(Character.valueOf('>'))) {
                                    --count2;
                                }
                                token = this.tokens.next();
                                continue;
                            }
                            break;
                        }
                    } else {
                        if (!token.match(5) || dcl.cppName.length() != 0 && !dcl.cppName.endsWith("::")) break;
                        dcl.cppName = dcl.cppName + token;
                    }
                    token = this.tokens.next();
                }
            }
        }
        if (dcl.cppName.length() == 0) {
            dcl.cppName = defaultName;
        }
        boolean bracket = false;
        Token token = this.tokens.get();
        while (!token.match(Token.EOF)) {
            if (!bracket && token.match(Character.valueOf('['))) {
                bracket = true;
                Token n3 = this.tokens.get(1);
                try {
                    dims[dcl.indices++] = n3.match(1) ? Integer.decode(n3.value) : -1;
                }
                catch (NumberFormatException e3) {
                    dims[dcl.indices] = -1;
                }
            } else {
                if (!bracket) break;
                if (bracket && token.match(Character.valueOf(']'))) {
                    bracket = false;
                }
            }
            token = this.tokens.next();
        }
        while (dcl.indices > 0 && indirections2 > 0) {
            dims[dcl.indices++] = -1;
            --indirections2;
        }
        if (arrayAsPointer && dcl.indices > 0) {
            ++dcl.indirections;
            String dimCast = "";
            for (int i3 = 1; i3 < dcl.indices; ++i3) {
                if (dims[i3] <= 0) continue;
                dimCast = dimCast + "[" + dims[i3] + "]";
            }
            if (!dimCast.isEmpty()) {
                cast = dims[0] != -1 ? cast + "(* /*[" + dims[0] + "]*/ )" : cast + "(*)";
                cast = cast + dimCast;
            } else {
                cast = cast + "*";
            }
        }
        if (pointerAsArray && dcl.indirections > (type.anonymous ? 0 : 1)) {
            dims[dcl.indices++] = -1;
            --dcl.indirections;
            cast = cast.substring(0, cast.length() - 1);
        }
        if (this.tokens.get().match(Character.valueOf(':'))) {
            type.annotations = type.annotations + "@NoOffset ";
            this.tokens.next().expect(1, 5);
            this.tokens.next().expect(Character.valueOf(','), Character.valueOf(';'));
            if (dcl.cppName == null) {
                dcl.cppName = "";
            }
        }
        dcl.parameters = this.parameters(context, infoNumber, useDefaults);
        if (type.cppName.equals("void") && indirections2 == 1 && !typedef && this.tokens.get(1).match(Character.valueOf('('))) {
            this.tokens.next().expect(Character.valueOf('('));
            this.tokens.next().expect(5);
            this.type(context);
            indirections2 = 0;
        } else if (indirections2 == 1 && !typedef && this.tokens.get(1).match(Character.valueOf('['))) {
            this.tokens.next().expect(Character.valueOf('['));
            this.tokens.next().expect(5);
            this.tokens.next().expect(Character.valueOf(']'));
            ++dcl.indirections;
            --indirections2;
        }
        int infoLength = 1;
        boolean valueType = false;
        boolean needCast = arrayAsPointer && dcl.indices > 1;
        boolean implicitConst = false;
        Info constInfo = this.infoMap.getFirst("const " + type.cppName, false);
        Info info2 = info = type.constValue && (dcl.indirections == 0 || dcl.indirections < 2 && !dcl.reference) ? constInfo : this.infoMap.getFirst(type.cppName, false);
        if (!(typedef && dcl.parameters == null || constInfo != null && (constInfo.cppTypes == null || constInfo.cppTypes.length <= 0) || info != null && (info.cppTypes == null || info.cppTypes.length <= 0))) {
            String[] type2 = type;
            if (info != null) {
                type2 = new Parser(this, info.cppTypes[0]).type(context);
            }
            infoList = this.infoMap.get(type2.cppName);
            for (Info info3 : infoList) {
                if (type2.arguments == null || info3.annotations == null) continue;
                type.constPointer = type2.arguments[0].constPointer;
                type.constValue = type2.arguments[0].constValue;
                type.simple = type2.arguments[0].simple;
                type.indirections = type2.arguments[0].indirections;
                type.reference = type2.arguments[0].reference;
                type.rvalue = type2.arguments[0].rvalue;
                type.value = type2.arguments[0].value;
                type.annotations = type2.arguments[0].annotations;
                type.cppName = type2.arguments[0].cppName;
                type.javaName = type2.arguments[0].javaName;
                dcl.indirections = 1;
                dcl.reference = false;
                dcl.rvalue = false;
                if (context.virtualize) {
                    needCast = true;
                    precast = cast;
                }
                cast = type.cppName + "*";
                if (type.constValue && !cast.startsWith("const ")) {
                    cast = "const " + cast;
                }
                if (type.indirections > 0) {
                    dcl.indirections += type.indirections;
                    for (int i4 = 0; i4 < type.indirections; ++i4) {
                        cast = cast + "*";
                    }
                }
                if (type.reference) {
                    dcl.reference = true;
                    cast = cast + "&";
                }
                if (type.rvalue) {
                    dcl.rvalue = true;
                    cast = cast + "&&";
                }
                if (type.constPointer && !cast.endsWith(" const")) {
                    cast = cast + " const";
                }
                String[] i4 = info3.annotations;
                int n2 = i4.length;
                for (int i2 = 0; i2 < n2; ++i2) {
                    String s2 = i4[i2];
                    type.annotations = type.annotations + s2 + " ";
                }
                info = this.infoMap.getFirst(type.cppName, false);
                break;
            }
        }
        if (!(using && defaultName == null || info == null)) {
            valueType = (info.enumerate || info.valueTypes != null) && (type.constValue && dcl.indirections == 0 && dcl.reference || dcl.indirections == 0 && !dcl.reference || info.pointerTypes == null);
            boolean bl2 = implicitConst = info.cppNames[0].startsWith("const ") && !info.define;
            infoLength = valueType ? (info.valueTypes != null ? info.valueTypes.length : 1) : (info.pointerTypes != null ? info.pointerTypes.length : 1);
            int n3 = dcl.infoNumber = infoNumber < 0 ? 0 : infoNumber % infoLength;
            type.javaName = valueType ? (info.valueTypes != null ? info.valueTypes[dcl.infoNumber] : type.javaName) : (info.pointerTypes != null ? info.pointerTypes[dcl.infoNumber] : type.javaName);
            type.javaName = context.shorten(type.javaName);
            needCast |= info.cast && !type.cppName.equals(type.javaName);
        }
        if (!valueType || context.virtualize) {
            if (!valueType && dcl.indirections == 0 && !dcl.reference) {
                type.annotations = type.annotations + (dcl.rvalue ? "@ByRef(true) " : "@ByVal ");
            } else if (dcl.indirections == 0 && dcl.reference) {
                if (type.javaName.contains("@ByPtrPtr ")) {
                    type.javaName = type.javaName.replace("@ByPtrPtr ", "@ByPtrRef ");
                } else {
                    type.annotations = type.annotations + "@ByRef ";
                }
            } else if (!type.javaName.contains("@ByPtrRef ") && dcl.indirections == 1 && dcl.reference) {
                type.annotations = type.annotations + "@ByPtrRef ";
            } else if (!(type.javaName.contains("@ByPtrPtr ") || dcl.indirections != 2 || dcl.reference || infoNumber < 0 && !type.javaName.equals("PointerPointer"))) {
                type.annotations = type.annotations + "@ByPtrPtr ";
                needCast |= type.cppName.equals("void");
            } else if (dcl.indirections >= 2) {
                dcl.infoNumber += infoLength;
                needCast = true;
                if (type.javaName.contains("@ByPtrRef ") || dcl.reference) {
                    type.annotations = type.annotations + "@ByRef ";
                } else if (type.javaName.contains("@ByPtrPtr ") || dcl.indirections >= 3) {
                    type.annotations = type.annotations + "@ByPtrPtr ";
                }
                type.javaName = "PointerPointer";
            }
            if (!needCast && !type.javaName.contains("@Cast")) {
                if (type.constValue && !implicitConst) {
                    type.annotations = "@Const " + type.annotations;
                }
                if (type.constPointer) {
                    // empty if block
                }
            }
        }
        if (needCast || valueType && dcl.rvalue && !type.annotations.contains("@") && !type.javaName.contains("@")) {
            if (dcl.indirections == 0 && dcl.reference) {
                cast = cast.replace('&', '*');
            }
            if (!valueType && dcl.indirections == 0 && dcl.rvalue) {
                cast = cast.replace("&&", "*");
            }
            if (valueType && type.constValue && dcl.reference) {
                cast = cast.substring(0, cast.length() - 1);
            }
            if (type.constValue && !cast.startsWith("const ")) {
                cast = "const " + cast;
            }
            type.annotations = precast != null ? "@Cast({\"" + cast + "\", \"" + precast + "\"}) " + type.annotations : (!valueType && dcl.indirections == 0 && !dcl.reference && !dcl.rvalue ? type.annotations + "@Cast(\"" + cast + "*\") " : "@Cast(\"" + cast + "\") " + type.annotations);
        }
        info = null;
        String string = dcl.javaName = attr != null ? attr.arguments : dcl.cppName;
        if (defaultName == null) {
            for (String string2 : context.qualify(dcl.cppName)) {
                info = this.infoMap.getFirst(string2, false);
                if (info != null) {
                    dcl.cppName = string2;
                    break;
                }
                if (this.infoMap.getFirst(string2) == null) continue;
                dcl.cppName = string2;
            }
        }
        String string3 = originalName = fieldPointer ? groupInfo.pointerTypes[0] : dcl.javaName;
        if (attr == null && defaultName == null && info != null && info.javaNames != null && info.javaNames.length > 0 && (dcl.operator || !info.cppNames[0].contains("<") || context.templateMap != null && context.templateMap.type == null)) {
            dcl.javaName = info.javaNames[0];
        }
        if (info != null && info.annotations != null) {
            void var35_65;
            infoList = info.annotations;
            int n4 = infoList.length;
            boolean bl3 = false;
            while (var35_65 < n4) {
                String s3 = infoList[var35_65];
                if (!type.annotations.contains(s3)) {
                    type.annotations = type.annotations + s3 + " ";
                }
                ++var35_65;
            }
        }
        dcl.type = type;
        dcl.signature = dcl.javaName;
        if (dcl.parameters != null || fieldPointer) {
            if (dcl.parameters != null) {
                dcl.infoNumber = Math.max(dcl.infoNumber, dcl.parameters.infoNumber);
            }
            if (dcl.parameters != null && indirections2 == 0 && !using && !typedef) {
                dcl.signature = dcl.signature + dcl.parameters.signature;
            } else {
                void var35_78;
                String string4;
                void var35_76;
                void var35_71;
                if (convention != null) {
                    definition.text = definition.text + "@Convention(\"" + convention.cppName + "\") ";
                }
                String cppType = "";
                if (dcl.type != null) {
                    void var35_67;
                    String s4 = dcl.type.cppName;
                    if (dcl.type.constValue && !s4.startsWith("const ")) {
                        s4 = "const " + s4;
                    }
                    boolean bl4 = false;
                    while (var35_67 < dcl.indirections) {
                        s4 = s4 + "*";
                        ++var35_67;
                    }
                    if (dcl.reference) {
                        s4 = s4 + "&";
                    }
                    if (dcl.rvalue) {
                        s4 = s4 + "&&";
                    }
                    if (dcl.type.constPointer && !s4.endsWith(" const")) {
                        s4 = s4 + " const";
                    }
                    cppType = cppType + s4;
                }
                cppType = cppType + " (*)(";
                String separator = "";
                if (dcl.parameters != null) {
                    for (Declarator d2 : dcl.parameters.declarators) {
                        if (d2 == null) continue;
                        String s22 = d2.type.cppName;
                        if (d2.type.constValue && !s22.startsWith("const ")) {
                            s22 = "const " + s22;
                        }
                        for (int i7 = 0; i7 < d2.indirections; ++i7) {
                            s22 = s22 + "*";
                        }
                        if (d2.reference) {
                            s22 = s22 + "&";
                        }
                        if (d2.rvalue) {
                            s22 = s22 + "&&";
                        }
                        if (d2.type.constPointer && !s22.endsWith(" const")) {
                            s22 = s22 + " const";
                        }
                        cppType = cppType + separator + s22;
                        separator = ", ";
                    }
                }
                if ((info = this.infoMap.getFirst(cppType = cppType + ")")) == null) {
                    info = this.infoMap.getFirst(dcl.cppName);
                }
                Object var35_69 = null;
                if (originalName != null) {
                    String string5 = Character.toUpperCase(originalName.charAt(0)) + originalName.substring(1);
                }
                if (info != null && info.pointerTypes != null && info.pointerTypes.length > 0) {
                    String string6 = info.pointerTypes[infoNumber < 0 ? 0 : infoNumber % info.pointerTypes.length];
                } else if (typedef) {
                    String string7 = originalName;
                } else if (dcl.parameters != null && dcl.parameters.signature.length() > 0) {
                    String string8 = (String)var35_71 + dcl.parameters.signature;
                } else if (!type.javaName.equals("void")) {
                    String s5 = type.javaName.trim();
                    int n6 = s5.lastIndexOf(32);
                    if (n6 > 0) {
                        s5 = s5.substring(n6 + 1);
                    }
                    String string9 = Character.toUpperCase(s5.charAt(0)) + s5.substring(1) + "_" + (String)var35_71;
                }
                if (info != null && info.annotations != null) {
                    for (String s2 : info.annotations) {
                        definition.text = definition.text + s2 + " ";
                    }
                }
                if (var35_76 == null) {
                    String string10 = "null";
                }
                if (!(string4 = var35_78.substring(var35_78.lastIndexOf(32) + 1)).equals("Pointer") && !string4.equals("long")) {
                    definition.type = new Type(string4);
                    for (Info info23 : this.infoMap.get("function/pointers")) {
                        if (info23 == null || info23.annotations == null) continue;
                        for (String s6 : info23.annotations) {
                            definition.text = definition.text + s6 + " ";
                        }
                    }
                    definition.text = definition.text + (this.tokens.get().match(Token.CONST, Token.__CONST, Token.CONSTEXPR) ? "@Const " : "") + "public static class " + string4 + " extends FunctionPointer {\n    static { Loader.load(); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public    " + string4 + "(Pointer p) { super(p); }\n" + (groupInfo != null ? "" : "    protected " + string4 + "() { allocate(); }\n    private native void allocate();\n");
                    definition.text = fieldPointer ? definition.text + "    public native " + type.annotations + type.javaName + " get(" + groupInfo.pointerTypes[0] + " o);\n    public native " + string4 + " put(" + groupInfo.pointerTypes[0] + " o, " + type.annotations + type.javaName + " v);\n}\n" : definition.text + "    public native " + type.annotations + type.javaName + " call" + (groupInfo != null ? "(" + groupInfo.pointerTypes[0] + " o" + (dcl.parameters.list.charAt(1) == ')' ? ")" : ", " + dcl.parameters.list.substring(1)) : dcl.parameters.list) + ";\n}\n";
                }
                definition.signature = string4;
                definition.declarator = new Declarator();
                definition.declarator.parameters = dcl.parameters;
                if (info == null || !info.skip) {
                    dcl.definition = definition;
                }
                dcl.indirections = indirections2;
                if (pointerAsArray && dcl.indirections > 1) {
                    dims[dcl.indices++] = -1;
                    --dcl.indirections;
                }
                if (!fieldPointer) {
                    dcl.parameters = null;
                }
                if (dcl.indirections > 1) {
                    int n7 = cppType.indexOf(40);
                    type.annotations = "@Cast(\"" + cppType.substring(0, n7 + 1) + "*" + cppType.substring(n7 + 1) + "\") ";
                    type.javaName = "PointerPointer";
                } else {
                    type.annotations = info != null && info.cast ? "@Cast(\"" + cppType + "\") " : (dcl.constPointer ? "@Const " : "");
                    type.javaName = string4;
                }
            }
        }
        if (dcl.cppName != null) {
            int template;
            String string11;
            String localName = dcl.cppName;
            if (context.namespace != null && localName.startsWith(context.namespace + "::")) {
                localName = dcl.cppName.substring(context.namespace.length() + 2);
            }
            String string12 = string11 = (template = localName.lastIndexOf(60)) >= 0 ? localName.substring(0, template) : localName;
            if (!(localName.equals(dcl.javaName) || string11.contains("::") && context.javaName != null)) {
                type.annotations = type.annotations + "@Name(\"" + localName + "\") ";
            }
        }
        while (this.tokens.get().match(Character.valueOf(')')) && count > 0) {
            this.tokens.next();
            --count;
        }
        return dcl;
    }

    String commentDoc(String s2, int startIndex) {
        if (startIndex < 0 || startIndex > s2.length()) {
            return s2;
        }
        StringBuilder sb2 = new StringBuilder(s2);
        for (int index = s2.indexOf("/**", startIndex); index < sb2.length(); ++index) {
            char c2 = sb2.charAt(index);
            String ss2 = sb2.substring(index + 1);
            if (c2 == '`' && ss2.startsWith("``") && sb2.length() - index > 3) {
                sb2.replace(index, index + 3, "<pre>{@code" + (Character.isWhitespace(sb2.charAt(index + 3)) ? "" : " "));
                index = sb2.indexOf("```", index);
                if (index < 0) break;
                sb2.replace(index, index + 3, "}</pre>");
                continue;
            }
            if (c2 == '`') {
                sb2.replace(index, index + 1, "{@code ");
                index = sb2.indexOf("`", index);
                if (index < 0) break;
                sb2.replace(index, index + 1, "}");
                continue;
            }
            if ((c2 == '\\' || c2 == '@') && ss2.startsWith("code")) {
                sb2.replace(index, index + 5, "<pre>{@code" + (Character.isWhitespace(sb2.charAt(index + 5)) ? "" : " "));
                index = sb2.indexOf(c2 + "endcode", index);
                if (index < 0) break;
                sb2.replace(index, index + 8, "}</pre>");
                continue;
            }
            if ((c2 == '\\' || c2 == '@') && ss2.startsWith("verbatim")) {
                sb2.replace(index, index + 9, "<pre>{@literal" + (Character.isWhitespace(sb2.charAt(index + 9)) ? "" : " "));
                index = sb2.indexOf(c2 + "endverbatim", index);
                if (index < 0) break;
                sb2.replace(index, index + 12, "}</pre>");
                continue;
            }
            if (c2 == '\n' && ss2.length() > 0 && ss2.charAt(0) == '\n') {
                int n2;
                for (n2 = 0; n2 < ss2.length() && ss2.charAt(n2) == '\n'; ++n2) {
                }
                String indent = "";
                while (n2 < ss2.length() && Character.isWhitespace(ss2.charAt(n2))) {
                    indent = indent + ss2.charAt(n2);
                    ++n2;
                }
                sb2.insert(index + 1, indent + "<p>");
                continue;
            }
            if (c2 == '\\' || c2 == '@') {
                boolean tagFound = false;
                for (DocTag tag : DocTag.docTags) {
                    Matcher matcher = tag.pattern.matcher(ss2);
                    if (!matcher.lookingAt()) continue;
                    StringBuffer sbuf = new StringBuffer();
                    matcher.appendReplacement(sbuf, tag.replacement);
                    if (sbuf.charAt(0) == '@' && !Character.isWhitespace(sb2.charAt(index + matcher.end() + 1))) {
                        sbuf.append(' ');
                    }
                    sb2.replace(index + matcher.start(), index + 1 + matcher.end(), sbuf.toString());
                    index += sbuf.length() - 1;
                    tagFound = true;
                    break;
                }
                if (tagFound) continue;
                sb2.setCharAt(index, '\\');
                continue;
            }
            if (c2 == '*' && ss2.charAt(0) == '/' && (index = sb2.indexOf("/**", index)) < 0) break;
        }
        return sb2.toString();
    }

    String commentBefore() throws ParserException {
        String comment = "";
        this.tokens.raw = true;
        while (this.tokens.index > 0 && this.tokens.get(-1).match(4)) {
            --this.tokens.index;
        }
        boolean closeComment = false;
        int startDoc = -1;
        Token token = this.tokens.get();
        while (token.match(4)) {
            block12: {
                String s2;
                block13: {
                    block11: {
                        s2 = token.value;
                        if (!s2.startsWith("/**") && !s2.startsWith("/*!") && !s2.startsWith("///") && !s2.startsWith("//!")) break block11;
                        if (s2.startsWith("//") && s2.contains("*/") && startDoc >= 0) {
                            s2 = s2.replace("*/", "* /");
                        }
                        if (s2.length() > 3 && s2.charAt(3) == '<') break block12;
                        if (s2.length() >= 3 && (s2.startsWith("///") || s2.startsWith("//!")) && !s2.startsWith("////") && !s2.startsWith("///*")) {
                            String lastComment = comment.trim();
                            int n2 = lastComment.indexOf(10);
                            while (!lastComment.startsWith("/*") && n2 > 0) {
                                lastComment = n2 + 1 < lastComment.length() ? lastComment.substring(n2 + 1).trim() : "";
                                n2 = lastComment.indexOf(10);
                            }
                            s2 = (comment.length() == 0 || comment.contains("*/") || !lastComment.startsWith("/*") ? "/**" : " * ") + s2.substring(3);
                            closeComment = true;
                        } else if (s2.length() > 3 && !s2.startsWith("///")) {
                            s2 = "/**" + s2.substring(3);
                        }
                        break block13;
                    }
                    if (closeComment && !comment.endsWith("*/")) {
                        closeComment = false;
                        comment = comment + " */";
                    }
                }
                if (startDoc < 0 && s2.startsWith("/**")) {
                    startDoc = comment.length();
                }
                comment = comment + token.spacing + s2;
                if (startDoc >= 0 && comment.endsWith("*/")) {
                    comment = this.commentDoc(comment, startDoc);
                    startDoc = -1;
                }
            }
            token = this.tokens.next();
        }
        if (closeComment && !comment.endsWith("*/")) {
            comment = comment + " */";
        }
        this.tokens.raw = false;
        return this.commentDoc(comment, startDoc);
    }

    String commentAfter() throws ParserException {
        String comment = "";
        this.tokens.raw = true;
        while (this.tokens.index > 0 && this.tokens.get(-1).match(4)) {
            --this.tokens.index;
        }
        boolean closeComment = false;
        int startDoc = -1;
        Token token = this.tokens.get();
        while (token.match(4)) {
            String s2 = token.value;
            String spacing = token.spacing;
            int n2 = spacing.lastIndexOf(10) + 1;
            if ((s2.startsWith("/**") || s2.startsWith("/*!") || s2.startsWith("///") || s2.startsWith("//!")) && (s2.length() <= 3 || s2.charAt(3) == '<')) {
                if (s2.length() > 4 && (s2.startsWith("///") || s2.startsWith("//!"))) {
                    String lastComment = comment.trim();
                    int n22 = lastComment.indexOf(10);
                    while (!lastComment.startsWith("/*") && n22 > 0) {
                        lastComment = n22 + 1 < lastComment.length() ? lastComment.substring(n22 + 1).trim() : "";
                        n22 = lastComment.indexOf(10);
                    }
                    s2 = (comment.length() == 0 || comment.contains("*/") || !lastComment.startsWith("/*") ? "/**" : " * ") + s2.substring(4);
                    closeComment = true;
                } else if (s2.length() > 4) {
                    s2 = "/**" + s2.substring(4);
                }
                if (startDoc < 0 && s2.startsWith("/**")) {
                    startDoc = comment.length();
                }
                comment = comment + spacing.substring(0, n2) + s2;
                if (startDoc >= 0 && comment.endsWith("*/")) {
                    comment = this.commentDoc(comment, startDoc);
                    startDoc = -1;
                }
            }
            token = this.tokens.next();
        }
        if (closeComment && !comment.endsWith("*/")) {
            comment = comment + " */";
        }
        if (comment.length() > 0) {
            comment = comment + "\n";
        }
        this.tokens.raw = false;
        return this.commentDoc(comment, startDoc);
    }

    Attribute attribute() throws ParserException {
        return this.attribute(false);
    }

    Attribute attribute(boolean explicit) throws ParserException {
        int count;
        boolean brackets = false;
        if (this.tokens.get().match("[[")) {
            brackets = true;
            this.tokens.next();
        } else if (!this.tokens.get().match(5) || this.tokens.get(1).match(Character.valueOf('<'))) {
            return null;
        }
        Attribute attr = new Attribute();
        attr.cppName = this.tokens.get().value;
        Info info = this.infoMap.getFirst(attr.cppName);
        boolean keyword = attr.cppName.equals("__attribute__");
        attr.annotation = info != null && info.annotations != null && info.javaNames == null && info.valueTypes == null && info.pointerTypes == null;
        if (attr.annotation) {
            for (String s2 : info.annotations) {
                attr.javaName = attr.javaName + s2 + " ";
            }
        }
        if (!brackets && explicit && !attr.annotation && !keyword) {
            return null;
        }
        int n2 = count = this.tokens.next().match(Character.valueOf('(')) ? 1 : 0;
        if (this.tokens.get().match("]]")) {
            brackets = false;
            this.tokens.next();
        }
        if (!brackets && count <= 0) {
            return attr;
        }
        if (keyword) {
            attr.cppName = attr.cppName + this.tokens.get().spacing + this.tokens.get();
        }
        Token token = this.tokens.next();
        while (!token.match(Token.EOF) && (brackets || count > 0)) {
            if (token.match(Character.valueOf('('))) {
                ++count;
            } else if (token.match(Character.valueOf(')'))) {
                --count;
            } else if (token.match("]]")) {
                brackets = false;
            } else if (info == null || !info.skip) {
                attr.arguments = attr.arguments + token.value;
            }
            if (keyword) {
                attr.cppName = attr.cppName + token.spacing + token;
            }
            token = this.tokens.next();
        }
        if (keyword) {
            attr.annotation = true;
            info = this.infoMap.getFirst(attr.cppName);
            if (info != null && info.annotations != null) {
                for (String s3 : info.annotations) {
                    attr.javaName = attr.javaName + s3 + " ";
                }
            }
        }
        return attr;
    }

    String body() throws ParserException {
        String text = "";
        if (!this.tokens.get().match(Character.valueOf('{'))) {
            return null;
        }
        int count = 1;
        boolean catchBlock = false;
        Token token = this.tokens.next();
        while (!token.match(Token.EOF) && count > 0) {
            if (token.match(Character.valueOf('{'))) {
                if (catchBlock) {
                    catchBlock = false;
                } else {
                    ++count;
                }
            } else if (token.match(Character.valueOf('}'))) {
                --count;
            }
            if (count == 0 && this.tokens.get(1).match("catch")) {
                ++count;
                catchBlock = true;
            }
            if (count > 0) {
                text = text + token.spacing + token;
            }
            token = this.tokens.next();
        }
        return text;
    }

    Parameters parameters(Context context, int infoNumber, boolean useDefaults) throws ParserException {
        int backIndex = this.tokens.index;
        if (!this.tokens.get().match(Character.valueOf('('))) {
            return null;
        }
        int count = 0;
        Parameters params = new Parameters();
        ArrayList<Declarator> dcls = new ArrayList<Declarator>();
        params.list = "(";
        params.names = "(";
        int lastVarargs = -1;
        Token token = this.tokens.next();
        while (!token.match(Token.EOF)) {
            if (token.match("...")) {
                token = this.tokens.next();
            }
            String spacing = token.spacing;
            if (token.match(Character.valueOf(')'))) {
                params.list = params.list + spacing + ")";
                params.names = params.names + ")";
                this.tokens.next();
                break;
            }
            Declarator dcl = this.declarator(context, "arg" + count++, infoNumber, useDefaults, 0, true, false);
            boolean hasDefault = !this.tokens.get().match(Character.valueOf(','), Character.valueOf(')'));
            Token defaultToken = null;
            String defaultValue = "";
            if (dcl != null && hasDefault) {
                String s2;
                int n2;
                defaultToken = this.tokens.get();
                int count2 = 0;
                token = this.tokens.next();
                token.spacing = "";
                while (!(token.match(Token.EOF) || count2 == 0 && token.match(Character.valueOf(','), Character.valueOf(')'), Character.valueOf('}')))) {
                    if (token.match(Character.valueOf('('), Character.valueOf('{'))) {
                        ++count2;
                    } else if (token.match(Character.valueOf(')'), Character.valueOf('}'))) {
                        --count2;
                    }
                    Object cppName = token.value;
                    if (context.templateMap != null) {
                        String[] types = ((String)cppName).split("::");
                        String separator = "";
                        cppName = "";
                        String[] stringArray = types;
                        int n3 = stringArray.length;
                        for (int i2 = 0; i2 < n3; ++i2) {
                            String t2 = stringArray[i2];
                            Type t22 = context.templateMap.get(t2);
                            cppName = (String)cppName + separator + (t22 != null ? t22.cppName : t2);
                            separator = "::";
                        }
                    }
                    for (String name : context.qualify((String)cppName)) {
                        if (this.infoMap.getFirst(name, false) != null) {
                            cppName = name;
                            break;
                        }
                        if (this.infoMap.getFirst(name) == null) continue;
                        cppName = name;
                    }
                    if (token.match(5)) {
                        while (this.tokens.get(1).equals("::")) {
                            this.tokens.next();
                            Token t3 = this.tokens.next();
                            cppName = (String)cppName + "::" + t3.spacing + t3;
                        }
                    }
                    defaultValue = defaultValue + token.spacing + (cppName != null && ((String)cppName).length() > 0 ? cppName : token);
                    token = this.tokens.next();
                }
                for (String name : context.qualify(defaultValue)) {
                    if (this.infoMap.getFirst(name, false) != null) {
                        defaultValue = name;
                        break;
                    }
                    if (this.infoMap.getFirst(name) == null) continue;
                    defaultValue = name;
                }
                if ((n2 = (s2 = dcl.type.annotations).indexOf("@ByVal ")) < 0) {
                    n2 = s2.indexOf("@ByRef ");
                }
                if (n2 >= 0) {
                    Info info;
                    if (!defaultValue.startsWith(dcl.type.cppName)) {
                        defaultValue = dcl.type.cppName + "(" + defaultValue + ")";
                    }
                    if ((info = this.infoMap.getFirst(defaultValue)) != null && info.skip) {
                        if (useDefaults) {
                            this.tokens.index = backIndex;
                            return this.parameters(context, infoNumber, false);
                        }
                    } else {
                        defaultValue = defaultValue.replaceAll("\"", "\\\\\"").replaceAll("\n(\\s*)", "\"\n$1 + \"").replaceAll("\\(\\{\\}\\)", "{}");
                        s2 = s2.substring(0, n2 + 6) + "(nullValue = \"" + defaultValue + "\")" + s2.substring(n2 + 6);
                    }
                }
                dcl.type.annotations = s2;
            }
            if (!(dcl == null || dcl.type.javaName.equals("void") || hasDefault && useDefaults)) {
                if (lastVarargs >= 0) {
                    params.list = params.list.substring(0, lastVarargs) + "[]" + params.list.substring(lastVarargs + 3);
                }
                int n4 = params.list.length();
                Info info = this.infoMap.getFirst(dcl.javaName);
                String paramName = info != null && info.javaNames != null && info.javaNames.length > 0 ? info.javaNames[0] : dcl.javaName;
                params.infoNumber = Math.max(params.infoNumber, dcl.infoNumber);
                params.list = params.list + (count > 1 ? "," : "") + spacing + dcl.type.annotations + dcl.type.javaName + " " + paramName;
                lastVarargs = params.list.indexOf("...", n4);
                if (hasDefault && !dcl.type.annotations.contains("(nullValue = ")) {
                    params.list = params.list + "/*" + defaultToken + defaultValue + "*/";
                }
                params.signature = params.signature + '_';
                for (char c2 : dcl.type.javaName.substring(dcl.type.javaName.lastIndexOf(32) + 1).toCharArray()) {
                    params.signature = params.signature + (Character.isJavaIdentifierPart(c2) ? c2 : (char)'_');
                }
                params.names = params.names + (count > 1 ? ", " : "") + paramName;
                if (dcl.javaName.startsWith("arg")) {
                    try {
                        count = Integer.parseInt(dcl.javaName.substring(3)) + 1;
                    }
                    catch (NumberFormatException numberFormatException) {
                        // empty catch block
                    }
                }
            }
            if (!hasDefault || !useDefaults) {
                dcls.add(dcl);
            }
            if (this.tokens.get().expect(Character.valueOf(','), Character.valueOf(')')).match(Character.valueOf(','))) {
                this.tokens.next();
            }
            token = this.tokens.get();
        }
        if (context.templateMap == null && dcls.size() == 1 && (dcls.get(0) == null || ((Declarator)dcls.get((int)0)).type == null || ((Declarator)dcls.get((int)0)).type.cppName == null || ((Declarator)dcls.get((int)0)).type.cppName.length() == 0)) {
            this.tokens.index = backIndex;
            return null;
        }
        params.declarators = dcls.toArray(new Declarator[dcls.size()]);
        return params;
    }

    static String incorporateConstAnnotation(String annotations, int constValueIndex, boolean constValue) {
        int start = annotations.indexOf("@Const");
        int end = annotations.indexOf("@", start + 1);
        if (end == -1) {
            end = annotations.length();
        }
        String prefix = annotations.substring(0, start);
        String constAnnotation = annotations.substring(start, end);
        String suffix = " " + annotations.substring(end, annotations.length());
        String boolPatternStr = "(true|false)";
        Pattern boolPattern = Pattern.compile(boolPatternStr);
        Matcher matcher = boolPattern.matcher(constAnnotation);
        boolean[] constArray = new boolean[]{true, false, false};
        int index = 0;
        while (matcher.find()) {
            constArray[index++] = Boolean.parseBoolean(matcher.group(1));
        }
        constArray[constValueIndex] = constValue;
        String incorporatedConstAnnotation = "@Const({" + constArray[0] + ", " + constArray[1] + ", " + constArray[2] + "})";
        return prefix + incorporatedConstAnnotation + suffix;
    }

    boolean function(Context context, DeclarationList declList) throws ParserException {
        String localName;
        int backIndex = this.tokens.index;
        String spacing = this.tokens.get().spacing;
        String modifiers = "public native ";
        int startIndex = this.tokens.index;
        Type type = this.type(context);
        Parameters params = this.parameters(context, 0, false);
        Declarator dcl = new Declarator();
        Declaration decl = new Declaration();
        if (type.javaName.length() == 0) {
            this.tokens.index = backIndex;
            return false;
        }
        if (context.javaName == null && !type.operator && params != null) {
            while (!this.tokens.get().match(Character.valueOf(':'), Character.valueOf('{'), Character.valueOf(';'), Token.EOF)) {
                this.tokens.next();
            }
            Token token = this.tokens.get();
            while (!token.match(Token.EOF) && this.attribute() != null) {
                token = this.tokens.get();
            }
            if (this.tokens.get().match(Character.valueOf(':'))) {
                int count = 0;
                Token token2 = this.tokens.next();
                while (!token2.match(Token.EOF)) {
                    if (token2.match(Character.valueOf('('))) {
                        ++count;
                    } else if (token2.match(Character.valueOf(')'))) {
                        --count;
                    }
                    if (count == 0 && !token2.match(5, Character.valueOf('>')) && this.tokens.get(1).match(Character.valueOf('{'))) {
                        this.tokens.next();
                        break;
                    }
                    if (count == 0 && token2.match(Character.valueOf(';'))) break;
                    token2 = this.tokens.next();
                }
            }
            if (this.tokens.get().match("->")) {
                this.tokens.next();
                type = this.type(context);
            }
            if (this.tokens.get().match(Character.valueOf('{'))) {
                this.body();
            } else {
                while (!this.tokens.get().match(Character.valueOf(';'), Token.EOF)) {
                    this.tokens.next();
                }
            }
            decl.text = spacing;
            decl.function = true;
            declList.add(decl);
            return true;
        }
        if ((type.constructor || type.destructor || type.operator) && params != null) {
            dcl.type = type;
            dcl.parameters = params;
            dcl.cppName = type.cppName;
            dcl.javaName = type.javaName.substring(type.javaName.lastIndexOf(32) + 1);
            if (type.operator) {
                dcl.cppName = "operator " + (dcl.type.constValue ? "const " : "") + dcl.type.cppName + (dcl.type.indirections > 0 ? "*" : (dcl.type.reference ? "&" : ""));
                dcl.javaName = "as" + Character.toUpperCase(dcl.javaName.charAt(0)) + dcl.javaName.substring(1);
            }
            dcl.signature = dcl.javaName + params.signature;
        } else {
            this.tokens.index = startIndex;
            dcl = this.declarator(context, null, -1, false, 0, false, false);
            type = dcl.type;
        }
        if (dcl.cppName == null || type.javaName.length() == 0 || dcl.parameters == null) {
            this.tokens.index = backIndex;
            return false;
        }
        int namespace = dcl.cppName.lastIndexOf("::");
        if (context.namespace != null && namespace < 0) {
            dcl.cppName = context.namespace + "::" + dcl.cppName;
        }
        Info info = null;
        Info fullInfo = null;
        String fullname = dcl.cppName;
        String fullname2 = dcl.cppName;
        if (dcl.parameters != null) {
            fullname = fullname + "(";
            fullname2 = fullname2 + "(";
            String separator = "";
            for (Declarator d2 : dcl.parameters.declarators) {
                if (d2 == null) continue;
                String s2 = d2.type.cppName;
                String s22 = d2.type.cppName;
                if (d2.type.constValue && !s2.startsWith("const ")) {
                    s2 = "const " + s2;
                }
                if (d2.indirections > 0) {
                    for (int i2 = 0; i2 < d2.indirections; ++i2) {
                        s2 = s2 + "*";
                        s22 = s22 + "*";
                    }
                }
                if (d2.reference) {
                    s2 = s2 + "&";
                    s22 = s22 + "&";
                }
                if (d2.rvalue) {
                    s2 = s2 + "&&";
                    s22 = s22 + "&&";
                }
                if (d2.type.constPointer && !s2.endsWith(" const")) {
                    s2 = s2 + " const";
                }
                fullname = fullname + separator + s2;
                fullname2 = fullname2 + separator + s22;
                separator = ", ";
            }
            fullInfo = this.infoMap.getFirst(fullname = fullname + ")", false);
            info = fullInfo;
            if (info == null) {
                fullname2 = fullname2 + ")";
                info = this.infoMap.getFirst(fullname2, false);
            }
        }
        if (info == null) {
            if (type.constructor) {
                int namespace2;
                String name = dcl.cppName;
                int template2 = name.lastIndexOf(60);
                if (template2 >= 0) {
                    name = name.substring(0, template2);
                }
                if ((namespace2 = name.lastIndexOf("::")) >= 0) {
                    name = name.substring(namespace2 + 2);
                }
                info = fullInfo = this.infoMap.getFirst(dcl.cppName + "::" + name);
            }
            if (info == null) {
                info = this.infoMap.getFirst(dcl.cppName);
            }
            if (!(type.constructor || type.destructor || type.operator || context.templateMap != null && !context.templateMap.full())) {
                this.infoMap.put(info != null ? new Info(info).cppNames(fullname).javaNames(null) : new Info(fullname));
            }
        }
        if ((localName = dcl.cppName).startsWith(context.namespace + "::")) {
            localName = dcl.cppName.substring(context.namespace.length() + 2);
        }
        int localNamespace = 0;
        int templateCount = 0;
        for (int i3 = 0; i3 < localName.length(); ++i3) {
            char c2 = localName.charAt(i3);
            if (c2 == '<') {
                ++templateCount;
                continue;
            }
            if (c2 == '>') {
                --templateCount;
                continue;
            }
            if (templateCount != 0 || !localName.substring(i3).startsWith("::")) continue;
            localNamespace = i3;
            break;
        }
        if (type.friend || this.tokens.get().match("&&") || context.javaName == null && localNamespace > 0 || info != null && info.skip) {
            while (!this.tokens.get().match(Character.valueOf(':'), Character.valueOf('{'), Character.valueOf(';'), Token.EOF)) {
                this.tokens.next();
            }
            Token token = this.tokens.get();
            while (!token.match(Token.EOF) && this.attribute() != null) {
                token = this.tokens.get();
            }
            if (this.tokens.get().match(Character.valueOf(':'))) {
                int count = 0;
                Token token3 = this.tokens.next();
                while (!token3.match(Token.EOF)) {
                    if (token3.match(Character.valueOf('('))) {
                        ++count;
                    } else if (token3.match(Character.valueOf(')'))) {
                        --count;
                    }
                    if (count == 0 && !token3.match(5, Character.valueOf('>')) && this.tokens.get(1).match(Character.valueOf('{'))) {
                        this.tokens.next();
                        break;
                    }
                    if (count == 0 && token3.match(Character.valueOf(';'))) break;
                    token3 = this.tokens.next();
                }
            }
            if (this.tokens.get().match("->")) {
                this.tokens.next();
                type = this.type(context);
            }
            if (this.tokens.get().match(Character.valueOf('{'))) {
                this.body();
            } else {
                while (!this.tokens.get().match(Character.valueOf(';'), Token.EOF)) {
                    this.tokens.next();
                }
            }
            decl.text = spacing;
            decl.function = true;
            declList.add(decl);
            return true;
        }
        if (type.staticMember || context.javaName == null) {
            modifiers = "public " + (info != null && info.objectify || context.objectify ? "" : "static ") + "native ";
            if (this.tokens.isCFile) {
                modifiers = "@NoException " + modifiers;
            }
        }
        ArrayList<Declarator> prevDcl = new ArrayList<Declarator>();
        boolean first = true;
        for (int n2 = -2; n2 < Integer.MAX_VALUE; ++n2) {
            boolean useDefaults;
            decl = new Declaration();
            this.tokens.index = startIndex;
            boolean bl2 = useDefaults = (info == null || !info.skipDefaults) && n2 % 2 != 0;
            if ((type.constructor || type.destructor || type.operator) && params != null) {
                type = this.type(context);
                params = this.parameters(context, n2 / 2, useDefaults);
                dcl = new Declarator();
                dcl.type = type;
                dcl.parameters = params;
                dcl.cppName = type.cppName;
                dcl.javaName = type.javaName.substring(type.javaName.lastIndexOf(32) + 1);
                if (type.operator) {
                    dcl.cppName = "operator " + (dcl.type.constValue ? "const " : "") + dcl.type.cppName + (dcl.type.indirections > 0 ? "*" : (dcl.type.reference ? "&" : ""));
                    dcl.javaName = "as" + Character.toUpperCase(dcl.javaName.charAt(0)) + dcl.javaName.substring(1);
                }
                dcl.signature = dcl.javaName + params.signature;
                Token token = this.tokens.get();
                while (!token.match(Token.EOF)) {
                    Attribute attr = this.attribute();
                    if (attr != null && attr.annotation) {
                        dcl.type.annotations = dcl.type.annotations + attr.javaName;
                    } else if (attr == null) break;
                    token = this.tokens.get();
                }
                if (this.tokens.get().match(Character.valueOf(':'))) {
                    int count = 0;
                    Object token4 = this.tokens.next();
                    while (!((Token)token4).match(Token.EOF)) {
                        if (((Token)token4).match(Character.valueOf('('))) {
                            ++count;
                        } else if (((Token)token4).match(Character.valueOf(')'))) {
                            --count;
                        }
                        if (count == 0 && !((Token)token4).match(5, Character.valueOf('>')) && this.tokens.get(1).match(Character.valueOf('{'))) {
                            this.tokens.next();
                        } else if (count != 0 || !((Token)token4).match(Character.valueOf(';'))) {
                            token4 = this.tokens.next();
                            continue;
                        }
                        break;
                    }
                }
            } else {
                dcl = this.declarator(context, null, n2 / 2, (info == null || !info.skipDefaults) && n2 % 2 != 0, 0, false, false);
                type = dcl.type;
                namespace = dcl.cppName.lastIndexOf("::");
                if (context.namespace != null && namespace < 0) {
                    dcl.cppName = context.namespace + "::" + dcl.cppName;
                }
            }
            String parameters = fullname.substring(dcl.cppName.length());
            for (String name : context.qualify(dcl.cppName, parameters)) {
                if (this.infoMap.getFirst(name, false) != null) {
                    dcl.cppName = name;
                    break;
                }
                if (this.infoMap.getFirst(name) == null) continue;
                dcl.cppName = name;
            }
            String localName2 = dcl.cppName;
            if (context.namespace != null && localName2.startsWith(context.namespace + "::")) {
                localName2 = dcl.cppName.substring(context.namespace.length() + 2);
            }
            if (localName2.endsWith(parameters)) {
                localName2 = localName2.substring(0, localName2.length() - parameters.length());
            }
            if (fullInfo != null && fullInfo.javaNames != null && fullInfo.javaNames.length > 0) {
                String simpleName;
                dcl.javaName = fullInfo.javaNames[0];
                dcl.signature = dcl.javaName + dcl.parameters.signature;
                int template = localName2.lastIndexOf(60);
                String string = simpleName = template >= 0 ? localName2.substring(0, template) : localName2;
                if (!(localName2.equals(dcl.javaName) || simpleName.contains("::") && context.javaName != null)) {
                    type.annotations = type.annotations.replaceAll("@Name\\(.*\\) ", "");
                    type.annotations = type.annotations + "@Name(\"" + localName2 + "\") ";
                }
            }
            Object token = this.tokens.get();
            while (!((Token)token).match(Token.EOF)) {
                Attribute attr;
                if (((Token)token).match(Token.CONST, Token.__CONST, Token.CONSTEXPR)) {
                    decl.constMember = true;
                    token = this.tokens.next();
                } else if (((Token)token).match(Token.OVERRIDE)) {
                    type.virtual = true;
                }
                if (((Token)token).match(Character.valueOf('&'), "&&")) {
                    token = this.tokens.next();
                }
                if ((attr = this.attribute()) != null && attr.annotation) {
                    dcl.type.annotations = dcl.type.annotations + attr.javaName;
                } else if (attr == null) break;
                token = this.tokens.get();
            }
            if (this.tokens.get().match("->")) {
                this.tokens.next();
                type = this.type(context);
            }
            if (this.tokens.get().match(Character.valueOf('{'))) {
                this.body();
            } else {
                if (this.tokens.get().match(Character.valueOf('='))) {
                    token = this.tokens.next().expect("0", Token.DELETE, Token.DEFAULT);
                    if (((Token)token).match("0")) {
                        decl.abstractMember = true;
                    } else if (((Token)token).match(Token.DELETE)) {
                        decl.text = spacing;
                        declList.add(decl);
                        return true;
                    }
                    this.tokens.next().expect(Character.valueOf(';'));
                }
                this.tokens.next();
            }
            if (!decl.constMember && context.constName != null) {
                decl.text = spacing;
                declList.add(decl);
                return true;
            }
            if (decl.constMember && type.virtual && context.virtualize) {
                type.annotations = type.annotations.contains("@Const") ? Parser.incorporateConstAnnotation(type.annotations, 2, true) : type.annotations + "@Const({false, false, true}) ";
            }
            if (type.virtual && context.virtualize) {
                modifiers = "@Virtual" + (decl.abstractMember ? "(true) " : " ") + (context.inaccessible ? "protected native " : "public native ");
            }
            decl.declarator = dcl;
            if (context.namespace != null && context.javaName == null) {
                decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
            }
            if (fullInfo != null && fullInfo.annotations != null) {
                for (String s3 : fullInfo.annotations) {
                    type.annotations = type.annotations + s3 + " ";
                }
            }
            decl.text = type.constructor && params != null ? decl.text + "public " + context.shorten(context.javaName) + dcl.parameters.list + " { super((Pointer)null); allocate" + params.names + "; }\n" + type.annotations + "private native void allocate" + dcl.parameters.list + ";\n" : decl.text + modifiers + type.annotations + context.shorten(type.javaName) + " " + dcl.javaName + dcl.parameters.list + ";\n";
            decl.signature = dcl.signature;
            if (useDefaults) {
                decl.text = decl.text.replaceAll("@Override ", "");
            }
            if (info != null && info.javaText != null) {
                if (!first) break;
                decl.signature = decl.text = info.javaText;
                decl.custom = true;
            }
            String comment = this.commentAfter();
            if (first) {
                declList.spacing = spacing;
                decl.text = comment + decl.text;
            }
            decl.function = true;
            boolean found = false;
            for (Declarator d3 : prevDcl) {
                found |= dcl.signature.equals(d3.signature);
            }
            if (dcl.javaName.length() > 0 && !found && (!type.destructor || info != null && info.javaText != null)) {
                if (declList.add(decl, fullname)) {
                    first = false;
                }
                if (type.virtual && context.virtualize) {
                    break;
                }
            } else if (found && n2 / 2 > 0 && n2 % 2 == 0 && n2 / 2 > Math.max(dcl.infoNumber, dcl.parameters.infoNumber)) break;
            prevDcl.add(dcl);
        }
        declList.spacing = null;
        return true;
    }

    boolean variable(Context context, DeclarationList declList) throws ParserException {
        Info info2;
        int backIndex = this.tokens.index;
        String spacing = this.tokens.get().spacing;
        String modifiers = "public static native ";
        String setterType = "void ";
        Declarator dcl = this.declarator(context, null, -1, false, 0, false, true);
        Declaration decl = new Declaration();
        String cppName = dcl.cppName;
        String javaName = dcl.javaName;
        Attribute attr = this.attribute();
        if (attr != null && attr.annotation) {
            dcl.type.annotations = dcl.type.annotations + attr.javaName;
        }
        if (cppName == null || javaName == null || !this.tokens.get().match(Character.valueOf('('), Character.valueOf('['), Character.valueOf('='), Character.valueOf(','), Character.valueOf(':'), Character.valueOf(';'), Character.valueOf('{'))) {
            this.tokens.index = backIndex;
            return false;
        }
        if (!dcl.type.staticMember && context.javaName != null) {
            modifiers = "public native ";
            setterType = context.shorten(context.javaName) + " ";
        }
        int namespace = cppName.lastIndexOf("::");
        if (context.namespace != null && namespace < 0) {
            cppName = context.namespace + "::" + cppName;
        }
        Info info = this.infoMap.getFirst(cppName);
        Info info3 = info2 = context.variable != null ? this.infoMap.getFirst(context.variable.cppName) : null;
        if (dcl.cppName.length() == 0 || info != null && info.skip || info2 != null && info2.skip) {
            decl.text = spacing;
            declList.add(decl);
            while (!this.tokens.get().match(Token.EOF, Character.valueOf(';'))) {
                this.tokens.next();
            }
            this.tokens.next();
            return true;
        }
        if (info == null) {
            info2 = this.infoMap.getFirst(dcl.cppName);
            this.infoMap.put(info2 != null ? new Info(info2).cppNames(cppName) : new Info(cppName));
        }
        boolean first = true;
        Declarator metadcl = context.variable;
        for (int n2 = 0; n2 < Integer.MAX_VALUE; ++n2) {
            String indices;
            decl = new Declaration();
            this.tokens.index = backIndex;
            dcl = this.declarator(context, null, -1, false, n2, false, true);
            if (dcl == null || dcl.cppName == null) break;
            decl.declarator = dcl;
            cppName = dcl.cppName;
            namespace = cppName.lastIndexOf("::");
            if (context.namespace != null && namespace < 0) {
                cppName = context.namespace + "::" + cppName;
            }
            info = this.infoMap.getFirst(cppName);
            namespace = cppName.lastIndexOf("::");
            String shortName = cppName;
            if (namespace >= 0) {
                shortName = cppName.substring(namespace + 2);
            }
            javaName = dcl.javaName;
            if (metadcl == null || metadcl.indices == 0 || dcl.indices == 0) {
                String rawType;
                boolean hasSetter;
                indices = "";
                for (int i2 = 0; i2 < (metadcl == null || metadcl.indices == 0 ? dcl.indices : metadcl.indices); ++i2) {
                    if (i2 > 0) {
                        indices = indices + ", ";
                    }
                    indices = indices + "int " + (char)(105 + i2);
                }
                if (context.namespace != null && context.javaName == null) {
                    decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
                }
                String nameAnnotation = "";
                if (metadcl != null && metadcl.cppName != null && metadcl.cppName.length() > 0) {
                    nameAnnotation = metadcl.indices == 0 ? "@Name(\"" + metadcl.cppName + "." + shortName + "\") " : "@Name({\"" + metadcl.cppName + "\", \"." + shortName + "\"}) ";
                    javaName = metadcl.javaName + "_" + shortName;
                }
                boolean beanify = context.beanify && indices.isEmpty();
                String capitalizedJavaName = null;
                if (beanify) {
                    if (nameAnnotation.length() == 0) {
                        nameAnnotation = "@Name(\"" + shortName + "\") ";
                    }
                    capitalizedJavaName = javaName.substring(0, 1).toUpperCase() + javaName.substring(1);
                    javaName = "get" + capitalizedJavaName;
                }
                if (nameAnnotation.length() > 0) {
                    dcl.type.annotations = dcl.type.annotations.replaceAll("@Name\\(.*\\) ", "");
                    decl.text = decl.text + nameAnnotation;
                }
                dcl.type.annotations = dcl.type.annotations.replace("@ByVal ", "@ByRef ");
                boolean bl2 = hasSetter = (!dcl.type.constValue || dcl.indirections != 0) && !dcl.constPointer && !dcl.type.constExpr && !context.immutable;
                if (!hasSetter || beanify) {
                    decl.text = decl.text + "@MemberGetter ";
                }
                decl.text = decl.text + modifiers + dcl.type.annotations + dcl.type.javaName + " " + javaName + "(" + indices + ");";
                if (hasSetter) {
                    if (indices.length() > 0) {
                        indices = indices + ", ";
                    }
                    if (beanify) {
                        decl.text = decl.text + "\n" + nameAnnotation + "@MemberSetter " + modifiers + setterType + "set" + capitalizedJavaName + "(" + indices + dcl.type.annotations + dcl.type.javaName + " setter);";
                    } else {
                        String javaTypeWithoutAnnotations = dcl.type.javaName.substring(dcl.type.javaName.lastIndexOf(" ") + 1);
                        decl.text = decl.text + " " + modifiers + setterType + javaName + "(" + indices + javaTypeWithoutAnnotations + " setter);";
                    }
                }
                decl.text = decl.text + "\n";
                if ((dcl.type.constValue || dcl.constPointer || dcl.type.constExpr) && dcl.type.staticMember && indices.length() == 0 && ("byte".equals(rawType = dcl.type.javaName.substring(dcl.type.javaName.lastIndexOf(32) + 1)) || "short".equals(rawType) || "int".equals(rawType) || "long".equals(rawType) || "float".equals(rawType) || "double".equals(rawType) || "char".equals(rawType) || "boolean".equals(rawType))) {
                    decl.text = decl.text + "public static final " + rawType + " " + javaName + " = " + javaName + "();\n";
                }
            }
            if (dcl.indices > 0) {
                boolean hasSetter;
                this.tokens.index = backIndex;
                dcl = this.declarator(context, null, -1, false, n2, true, false);
                indices = "";
                for (int i3 = 0; i3 < (metadcl == null ? 0 : metadcl.indices); ++i3) {
                    if (i3 > 0) {
                        indices = indices + ", ";
                    }
                    indices = indices + "int " + (char)(105 + i3);
                }
                if (context.namespace != null && context.javaName == null) {
                    decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
                }
                if (metadcl != null && metadcl.cppName.length() > 0) {
                    decl.text = decl.text + (metadcl.indices == 0 ? "@Name(\"" + metadcl.cppName + "." + shortName + "\") " : "@Name({\"" + metadcl.cppName + "\", \"." + shortName + "\"}) ");
                    dcl.type.annotations = dcl.type.annotations.replaceAll("@Name\\(.*\\) ", "");
                    javaName = metadcl.javaName + "_" + shortName;
                }
                this.tokens.index = backIndex;
                Declarator dcl2 = this.declarator(context, null, -1, false, n2, false, false);
                boolean bl3 = hasSetter = !dcl.type.constValue && !dcl.constPointer && dcl2.indirections >= 2 && !dcl2.type.constExpr && !context.immutable;
                if (!hasSetter) {
                    decl.text = decl.text + "@MemberGetter ";
                }
                decl.text = decl.text + modifiers + dcl.type.annotations.replace("@ByVal ", "@ByRef ") + dcl.type.javaName + " " + javaName + "(" + indices + ");";
                if (hasSetter) {
                    if (indices.length() > 0) {
                        indices = indices + ", ";
                    }
                    decl.text = decl.text + " " + modifiers + setterType + javaName + "(" + indices + dcl.type.javaName + " setter);";
                }
                decl.text = decl.text + "\n";
            }
            decl.signature = dcl.signature + "_";
            if (info != null && info.javaText != null) {
                decl.signature = decl.text = info.javaText;
                decl.declarator = null;
                decl.custom = true;
            }
            while (!this.tokens.get().match(Token.EOF, Character.valueOf(';'))) {
                this.tokens.next();
            }
            this.tokens.next();
            String comment = this.commentAfter();
            if (first) {
                first = false;
                declList.spacing = spacing;
                decl.text = comment + decl.text;
            }
            decl.variable = true;
            declList.add(decl);
        }
        declList.spacing = null;
        return true;
    }

    boolean macro(Context context, DeclarationList declList) throws ParserException {
        String macroName;
        int backIndex = this.tokens.index;
        if (!this.tokens.get().match(Character.valueOf('#'))) {
            return false;
        }
        this.tokens.raw = true;
        String spacing = this.tokens.get().spacing;
        Token keyword = this.tokens.next();
        if (keyword.spacing.indexOf(10) >= 0) {
            Declaration decl = new Declaration();
            decl.text = spacing + "// #";
            declList.add(decl);
            this.tokens.raw = false;
            return true;
        }
        this.tokens.next();
        int beginIndex = this.tokens.index;
        Token token = this.tokens.get();
        while (!token.match(Token.EOF) && token.spacing.indexOf(10) < 0) {
            token = this.tokens.next();
        }
        int endIndex = this.tokens.index;
        while (this.tokens.get(-1).match(4)) {
            --this.tokens.index;
        }
        int lastIndex = this.tokens.index;
        Declaration decl = new Declaration();
        if (keyword.match(Token.DEFINE) && beginIndex < endIndex) {
            this.tokens.index = beginIndex;
            macroName = this.tokens.get().value;
            Token first = this.tokens.next();
            boolean hasArgs = first.spacing.length() == 0 && first.match(Character.valueOf('('));
            List<Info> infoList = this.infoMap.get(macroName);
            for (Info info : infoList.size() > 0 ? infoList : Arrays.asList(new Info[]{null})) {
                if (info != null && info.skip) break;
                if (info == null && (hasArgs || beginIndex + 1 == endIndex) || info != null && info.cppText == null && info.cppTypes != null && info.cppTypes.length == 0) {
                    info = new Info(macroName).cppText("");
                    this.tokens.index = backIndex;
                    Token token2 = this.tokens.get();
                    while (this.tokens.index < endIndex) {
                        info.cppText = info.cppText + (token2.match("\n") ? token2 : token2.spacing + token2);
                        token2 = this.tokens.next();
                    }
                    this.infoMap.put(info);
                    break;
                }
                if (info != null && info.cppText == null && info.cppTypes != null && info.cppTypes.length > (hasArgs ? 0 : 1)) {
                    ArrayList<Declarator> prevDcl = new ArrayList<Declarator>();
                    for (int n2 = -1; n2 < Integer.MAX_VALUE; ++n2) {
                        int count = 1;
                        this.tokens.index = beginIndex + 2;
                        String params = "(";
                        Token token3 = this.tokens.get();
                        while (hasArgs && this.tokens.index < lastIndex && count < info.cppTypes.length) {
                            if (token3.match(5)) {
                                String type = info.cppTypes[count];
                                Object name = token3.value;
                                if (((String)name).equals("...")) {
                                    name = "arg" + count;
                                }
                                params = params + type + " " + (String)name;
                                if (++count < info.cppTypes.length) {
                                    params = params + ", ";
                                }
                            } else if (token3.match(Character.valueOf(')'))) break;
                            token3 = this.tokens.next();
                        }
                        while (count < info.cppTypes.length) {
                            String type = info.cppTypes[count];
                            String name = "arg" + count;
                            params = params + type + " " + name;
                            if (++count >= info.cppTypes.length) continue;
                            params = params + ", ";
                        }
                        params = params + ")";
                        Declarator dcl = new Parser(this, info.cppTypes[0] + " " + macroName + params).declarator(context, null, n2, false, 0, false, false);
                        for (int i2 = 0; i2 < info.cppNames.length; ++i2) {
                            if (!macroName.equals(info.cppNames[i2]) || info.javaNames == null) continue;
                            macroName = "@Name(\"" + info.cppNames[0] + "\") " + info.javaNames[i2];
                            break;
                        }
                        boolean found = false;
                        for (Declarator d2 : prevDcl) {
                            found |= dcl.signature.equals(d2.signature);
                        }
                        if (!found) {
                            decl.text = decl.text + "public static native " + dcl.type.annotations + dcl.type.javaName + " " + macroName + dcl.parameters.list + ";\n";
                            decl.signature = dcl.signature;
                        } else if (found && n2 > 0) break;
                        prevDcl.add(dcl);
                    }
                } else if (lastIndex > beginIndex + 1 && (info == null || info.cppText == null && (info.cppTypes == null || info.cppTypes.length == 1))) {
                    int i3;
                    String value = "";
                    String cppType = "int";
                    String type = "int";
                    String cat = "";
                    this.tokens.index = beginIndex + 1;
                    Token prevToken = new Token();
                    boolean translate = true;
                    Token token4 = this.tokens.get();
                    while (this.tokens.index < lastIndex) {
                        if (token4.match(3)) {
                            cppType = "const char*";
                            type = "String";
                            cat = " + ";
                            break;
                        }
                        if (token4.match(2)) {
                            cppType = "double";
                            type = "double";
                            cat = "";
                            break;
                        }
                        if (token4.match(1) && token4.value.endsWith("L")) {
                            cppType = "long long";
                            type = "long";
                            cat = "";
                            break;
                        }
                        if (prevToken.match(5, Character.valueOf('>')) && token4.match(5, Character.valueOf('(')) || token4.match(Character.valueOf('{'), Character.valueOf('}'))) {
                            translate = false;
                        } else if (token4.match(5)) {
                            Info info2 = this.infoMap.getFirst(token4.value);
                            if (info == null && info2 != null && info2.cppTypes != null) {
                                info = info2;
                            }
                        }
                        prevToken = token4;
                        token4 = this.tokens.next();
                    }
                    if (info != null) {
                        if (info.cppTypes != null && info.cppTypes.length > 0) {
                            Declarator dcl = new Parser(this, info.cppTypes[0]).declarator(context, null, -1, false, 0, false, true);
                            if (!dcl.type.javaName.equals("int")) {
                                cppType = dcl.type.cppName;
                                type = dcl.type.annotations + (info.pointerTypes != null ? info.pointerTypes[0] : dcl.type.javaName);
                            }
                        }
                        for (int i4 = 0; i4 < info.cppNames.length; ++i4) {
                            if (!macroName.equals(info.cppNames[i4]) || info.javaNames == null) continue;
                            macroName = "@Name(\"" + info.cppNames[0] + "\") " + info.javaNames[i4];
                            break;
                        }
                        translate = info.translate;
                    }
                    this.tokens.index = beginIndex + 1;
                    if (translate) {
                        token = this.tokens.get();
                        while (this.tokens.index < lastIndex) {
                            value = value + token.spacing;
                            if (!type.equals("String") || !token.match("L")) {
                                value = value + token + (this.tokens.index + 1 < lastIndex && token.value.trim().length() > 0 ? cat : "");
                            }
                            token = this.tokens.next();
                        }
                        value = this.translate(value);
                        if (type.equals("int")) {
                            if (value.contains("(String)")) {
                                cppType = "const char*";
                                type = "String";
                            } else if (value.contains("(float)") || value.contains("(double)")) {
                                cppType = "double";
                                type = "double";
                            } else if (value.contains("(long)")) {
                                cppType = "long long";
                                type = "long";
                            } else {
                                try {
                                    String trimmedValue = value.trim();
                                    long longValue = Long.parseLong(trimmedValue);
                                    if (longValue > Integer.MAX_VALUE && longValue >>> 32 == 0L) {
                                        value = value.substring(0, value.length() - trimmedValue.length()) + "(int)" + trimmedValue + "L";
                                    } else if (longValue > Integer.MAX_VALUE || longValue < Integer.MIN_VALUE) {
                                        cppType = "long long";
                                        type = "long";
                                        value = value + "L";
                                    }
                                }
                                catch (NumberFormatException trimmedValue) {}
                            }
                        }
                    } else {
                        if (info != null && info.annotations != null) {
                            for (String s2 : info.annotations) {
                                decl.text = decl.text + s2 + " ";
                            }
                        }
                        decl.text = decl.text + "public static native @MemberGetter " + type + " " + macroName + "();\n";
                        value = " " + macroName + "()";
                    }
                    if ((i3 = type.lastIndexOf(32)) >= 0) {
                        type = type.substring(i3 + 1);
                    }
                    if (value.length() > 0) {
                        decl.text = decl.text + "public static final " + type + " " + macroName + " =" + value + ";\n";
                    }
                    decl.signature = macroName;
                    if (info == null || !Arrays.asList(info.cppNames).contains(macroName)) {
                        this.infoMap.put(new Info(macroName).define(true).cppTypes(cppType).pointerTypes(type).translate(translate));
                    }
                }
                if (info == null || info.javaText == null) continue;
                decl.signature = decl.text = info.javaText;
                decl.custom = true;
                break;
            }
        } else if (keyword.match(Token.UNDEF)) {
            this.tokens.index = beginIndex;
            macroName = this.tokens.get().value;
            List<Info> infoList = this.infoMap.get(macroName);
            for (Info info : infoList) {
                if (info != null && info.skip) break;
                if (info == null || info.cppText == null || info.cppTypes != null || info.define) continue;
                infoList.remove(info);
                break;
            }
        }
        if (decl.text.length() == 0) {
            this.tokens.index = beginIndex;
            int n3 = spacing.lastIndexOf(10) + 1;
            decl.text = decl.text + "// " + spacing.substring(n3) + "#" + keyword.spacing + keyword;
            Token token5 = this.tokens.get();
            while (this.tokens.index < lastIndex) {
                decl.text = decl.text + (token5.match("\n") ? "\n// " : token5.spacing + token5.toString().replace("\n", "\n//"));
                token5 = this.tokens.next();
            }
            spacing = spacing.substring(0, n3);
        }
        if (decl.text.length() > 0) {
            this.tokens.index = lastIndex;
            String comment = this.commentAfter();
            decl.text = comment + decl.text;
        }
        this.tokens.raw = false;
        declList.spacing = spacing;
        declList.add(decl);
        declList.spacing = null;
        return true;
    }

    boolean typedef(Context context, DeclarationList declList) throws ParserException {
        String usingDefName;
        String spacing = this.tokens.get().spacing;
        String string = usingDefName = this.tokens.get().match(Token.USING) && this.tokens.get(1).match(5) && this.tokens.get(2).match(Character.valueOf('=')) ? this.tokens.get((int)1).value : null;
        if (!this.tokens.get().match(Token.TYPEDEF) && !this.tokens.get(1).match(Token.TYPEDEF) && usingDefName == null) {
            return false;
        }
        Declaration decl = new Declaration();
        int backIndex = this.tokens.index;
        for (int n2 = 0; n2 < Integer.MAX_VALUE; ++n2) {
            decl = new Declaration();
            this.tokens.index = backIndex;
            Declarator dcl = this.declarator(context, usingDefName, -1, false, n2, true, false);
            if (dcl == null) break;
            if (usingDefName != null) {
                dcl.cppName = usingDefName;
            }
            if (this.attribute() == null) {
                this.tokens.next();
            }
            String typeName = dcl.type.cppName;
            String defName = dcl.cppName;
            if (defName == null) {
                dcl.cppName = defName = typeName;
            }
            if (dcl.javaName == null) {
                dcl.javaName = dcl.cppName;
            }
            int namespace = defName.lastIndexOf("::");
            if (context.namespace != null && namespace < 0) {
                defName = context.namespace + "::" + defName;
            }
            Info info = this.infoMap.getFirst(defName);
            if (dcl.definition != null) {
                decl = dcl.definition;
                if (usingDefName != null) {
                    decl.text = decl.text.replace(decl.signature, usingDefName);
                    decl.type.javaName = decl.type.cppName = usingDefName;
                    decl.signature = decl.type.cppName;
                }
                if (dcl.javaName.length() > 0 && context.javaName != null) {
                    dcl.javaName = context.javaName + "." + dcl.javaName;
                }
                if (info == null || !info.skip) {
                    info = info != null ? new Info(info).cppNames(defName) : new Info(defName);
                    this.infoMap.put(info.valueTypes(dcl.javaName).pointerTypes((dcl.indirections > 0 ? "@ByPtrPtr " : "") + dcl.javaName));
                }
            } else if (typeName.equals("void")) {
                if (!(info != null && info.skip || dcl.javaName.equals("Pointer"))) {
                    if (dcl.indirections > 0) {
                        decl.text = decl.text + "@Namespace @Name(\"void\") ";
                        info = info != null ? new Info(info).cppNames(defName) : new Info(defName);
                        this.infoMap.put(info.valueTypes(dcl.javaName).pointerTypes("@ByPtrPtr " + dcl.javaName));
                    } else if (context.namespace != null && context.javaName == null) {
                        decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
                    }
                    decl.type = new Type(dcl.javaName);
                    decl.text = decl.text + "@Opaque public static class " + dcl.javaName + " extends Pointer {\n    /** Empty constructor. Calls {@code super((Pointer)null)}. */\n    public " + dcl.javaName + "() { super((Pointer)null); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public " + dcl.javaName + "(Pointer p) { super(p); }\n}";
                }
            } else {
                info = this.infoMap.getFirst(typeName);
                if (info == null || !info.skip) {
                    Info info2 = info = info != null ? new Info(info).cppNames(defName) : new Info(defName);
                    if (info.cppTypes == null && info.annotations != null) {
                        String s2 = typeName;
                        if (dcl.type.constValue && !s2.startsWith("const ")) {
                            s2 = "const " + s2;
                        }
                        if (dcl.type.indirections > 0) {
                            for (int i2 = 0; i2 < dcl.type.indirections; ++i2) {
                                s2 = s2 + "*";
                            }
                        }
                        if (dcl.type.reference) {
                            s2 = s2 + "&";
                        }
                        if (dcl.type.rvalue) {
                            s2 = s2 + "&&";
                        }
                        if (dcl.type.constPointer && !s2.endsWith(" const")) {
                            s2 = s2 + " const";
                        }
                        info.cppNames(defName, s2).cppTypes(s2);
                    }
                    if (info.valueTypes == null && dcl.indirections > 0) {
                        String[] stringArray;
                        if (info.pointerTypes != null) {
                            stringArray = info.pointerTypes;
                        } else {
                            String[] stringArray2 = new String[1];
                            stringArray = stringArray2;
                            stringArray2[0] = typeName;
                        }
                        info.valueTypes(stringArray);
                        info.pointerTypes("PointerPointer");
                    } else if (info.pointerTypes == null) {
                        info.pointerTypes(typeName);
                    }
                    if (info.annotations == null) {
                        if (!(dcl.type.annotations == null || dcl.type.annotations.length() <= 0 || dcl.type.annotations.startsWith("@ByVal ") || dcl.type.annotations.startsWith("@Cast(") || dcl.type.annotations.startsWith("@Const "))) {
                            info.annotations(dcl.type.annotations.trim());
                        } else {
                            info.cast(!dcl.cppName.equals(info.pointerTypes[0]) && !info.pointerTypes[0].contains("@Cast"));
                        }
                    }
                    this.infoMap.put(info);
                }
            }
            if (info != null && info.javaText != null) {
                decl.signature = decl.text = info.javaText;
                decl.custom = true;
            }
            String comment = this.commentAfter();
            decl.text = comment + decl.text;
            declList.spacing = spacing;
            declList.add(decl);
            declList.spacing = null;
        }
        return true;
    }

    boolean using(Context context, DeclarationList declList) throws ParserException {
        int template2;
        if (!this.tokens.get().match(Token.USING)) {
            return false;
        }
        String spacing = this.tokens.get().spacing;
        boolean namespace = this.tokens.get(1).match(Token.NAMESPACE);
        Declarator dcl = this.declarator(context, null, -1, false, 0, true, false);
        this.tokens.next();
        context.usingList.add(dcl.type.cppName + (namespace ? "::" : ""));
        Declaration decl = new Declaration();
        if (dcl.definition != null) {
            decl = dcl.definition;
        }
        String cppName = dcl.type.cppName;
        String baseType = context.baseType;
        int template = cppName.lastIndexOf(60);
        int n2 = template2 = baseType != null ? baseType.lastIndexOf(60) : -1;
        if (template < 0 && template2 >= 0 && cppName.startsWith(baseType.substring(0, template2))) {
            cppName = baseType + cppName.substring(template2);
        }
        Info info = this.infoMap.getFirst(cppName);
        if (!context.inaccessible && info != null && info.javaText != null) {
            decl.signature = decl.text = info.javaText;
            decl.declarator = dcl;
            decl.custom = true;
        }
        String comment = this.commentAfter();
        decl.text = comment + decl.text;
        declList.spacing = spacing;
        declList.add(decl);
        declList.spacing = null;
        return true;
    }

    boolean group(Context context, DeclarationList declList) throws ParserException {
        Info constructorInfo;
        int namespace2;
        Info info;
        int backIndex = this.tokens.index;
        String spacing = this.tokens.get().spacing;
        boolean typedef = this.tokens.get().match(Token.TYPEDEF) || this.tokens.get(1).match(Token.TYPEDEF);
        boolean foundGroup = false;
        boolean friend = false;
        Context ctx = new Context(context);
        Object[] prefixes = new Token[]{Token.CLASS, Token.INTERFACE, Token.__INTERFACE, Token.STRUCT, Token.UNION};
        Token token = this.tokens.get();
        while (!token.match(Token.EOF)) {
            if (token.match(prefixes)) {
                foundGroup = true;
                ctx.inaccessible = token.match(Token.CLASS);
                break;
            }
            if (token.match(Token.FRIEND)) {
                friend = true;
                if (!this.tokens.get(1).match(prefixes)) {
                    foundGroup = true;
                    break;
                }
            } else if (!token.match(5)) break;
            token = this.tokens.next();
        }
        if (!foundGroup || !this.tokens.next().match(5, Character.valueOf('{'), "::")) {
            this.tokens.index = backIndex;
            return false;
        }
        if (!(this.tokens.get().match(Character.valueOf('{')) || !this.tokens.get(1).match(5) || this.tokens.get(1).match(Token.FINAL) || !typedef && this.tokens.get(2).match(Character.valueOf(';')))) {
            this.tokens.next();
        }
        Type type = this.type(context, true);
        ArrayList<Type> baseClasses = new ArrayList<Type>();
        Declaration decl = new Declaration();
        decl.text = type.annotations;
        String name = type.javaName;
        boolean anonymous = !typedef && type.cppName.length() == 0;
        boolean derivedClass = false;
        boolean skipBase = false;
        if (type.cppName.length() > 0 && this.tokens.get().match(Character.valueOf(':'))) {
            derivedClass = true;
            Token token2 = this.tokens.next();
            while (!token2.match(Token.EOF)) {
                boolean accessible;
                boolean bl2 = accessible = !ctx.inaccessible;
                if (!token2.match(Token.VIRTUAL)) {
                    if (token2.match(Token.PRIVATE, Token.PROTECTED, Token.PUBLIC)) {
                        accessible = token2.match(Token.PUBLIC);
                        this.tokens.next();
                    }
                    Type t2 = this.type(context);
                    Info info2 = this.infoMap.getFirst(t2.cppName);
                    if (info2 != null && info2.skip) {
                        skipBase = true;
                    }
                    if (accessible) {
                        baseClasses.add(t2);
                    }
                    if (this.tokens.get().expect(Character.valueOf(','), Character.valueOf('{')).match(Character.valueOf('{'))) break;
                }
                token2 = this.tokens.next();
            }
        }
        if (typedef && type.indirections > 0) {
            while (!this.tokens.get().match(Character.valueOf(';'), Token.EOF)) {
                this.tokens.next();
            }
        }
        if (!this.tokens.get().match(Character.valueOf('{'), Character.valueOf(','), Character.valueOf(';'))) {
            this.tokens.index = backIndex;
            return false;
        }
        int startIndex = this.tokens.index;
        ArrayList<Declarator> variables = new ArrayList<Declarator>();
        String originalName = type.cppName;
        String body = this.body();
        boolean hasBody = body != null && body.length() > 0;
        Attribute attr = this.attribute(true);
        while (attr != null && attr.annotation) {
            type.annotations = type.annotations + attr.javaName;
            attr = this.attribute(true);
        }
        if (!this.tokens.get().match(Character.valueOf(';'))) {
            if (typedef) {
                Token token3 = this.tokens.get();
                while (!token3.match(Character.valueOf(';'), Token.EOF)) {
                    int indirections = 0;
                    while (token3.match(Character.valueOf('*'))) {
                        ++indirections;
                        token3 = this.tokens.next();
                    }
                    if (token3.match(5)) {
                        String name2 = token3.value;
                        if (indirections > 0) {
                            this.infoMap.put(new Info(name2).cast().valueTypes(name).pointerTypes("PointerPointer"));
                        } else {
                            if (type.cppName.equals(originalName)) {
                                type.javaName = type.cppName = token3.value;
                                name = type.cppName;
                                Info info3 = this.infoMap.getFirst(name);
                                if (info3 != null && info3.annotations != null) {
                                    for (String s2 : info3.annotations) {
                                        decl.text = decl.text + s2 + " ";
                                    }
                                }
                            }
                            if (!name2.equals(name)) {
                                this.infoMap.put(new Info(name2).cast().pointerTypes(name));
                            }
                        }
                    }
                    token3 = this.tokens.next();
                }
                decl.text = decl.text + token3.spacing;
            } else {
                int n2;
                int index;
                for (index = this.tokens.index - 1; index >= 0 && this.tokens.preprocess(index, 0) == this.tokens.index; --index) {
                }
                for (n2 = 0; n2 < Integer.MAX_VALUE; ++n2) {
                    this.tokens.index = index;
                    Declarator dcl = this.declarator(context, null, -1, false, n2, false, true);
                    if (dcl == null || dcl.cppName == null) break;
                    anonymous = true;
                    variables.add(dcl);
                }
                if ((n2 = spacing.lastIndexOf(10)) >= 0) {
                    decl.text = decl.text + spacing.substring(0, n2);
                }
            }
        }
        int namespace = type.cppName.lastIndexOf("::");
        if (context.namespace != null && namespace < 0) {
            type.cppName = context.namespace + "::" + type.cppName;
            originalName = context.namespace + "::" + originalName;
        }
        if (((info = this.infoMap.getFirst(type.cppName)) == null || info.base == null) && skipBase || info != null && info.skip) {
            decl.text = "";
            declList.add(decl);
            return true;
        }
        if (info != null && info.pointerTypes != null && info.pointerTypes.length > 0) {
            type.javaName = context.constName != null ? context.constName : info.pointerTypes[0].substring(info.pointerTypes[0].lastIndexOf(" ") + 1);
            name = context.shorten(type.javaName);
        } else if (info == null && !friend) {
            if (type.javaName.length() > 0 && context.javaName != null) {
                type.javaName = context.javaName + "." + type.javaName;
            }
            info = new Info(type.cppName).pointerTypes(type.javaName);
            this.infoMap.put(info);
        }
        Type base = new Type("Pointer");
        Iterator it2 = baseClasses.iterator();
        while (it2.hasNext()) {
            Type next = (Type)it2.next();
            Info nextInfo = this.infoMap.getFirst(next.cppName);
            if (nextInfo != null && nextInfo.flatten) continue;
            base = next;
            it2.remove();
            break;
        }
        String casts = "";
        if (baseClasses.size() > 0) {
            for (Type t3 : baseClasses) {
                if (t3.javaName.equals("Pointer")) continue;
                casts = casts + "    public " + t3.javaName + " as" + t3.javaName + "() { return as" + t3.javaName + "(this); }\n    @Namespace public static native @Name(\"static_cast<" + t3.cppName + "*>\") " + t3.javaName + " as" + t3.javaName + "(" + type.javaName + " pointer);\n";
            }
        }
        decl.signature = type.javaName;
        this.tokens.index = startIndex;
        String shortName = name.substring(name.lastIndexOf(46) + 1);
        String fullName = context.namespace != null ? context.namespace + "::" + name : name;
        String cppName = type.cppName;
        for (Object prefix : prefixes) {
            if (info == null || !info.cppNames[0].startsWith(((Token)prefix).value + " ")) continue;
            cppName = ((Token)prefix).value + " " + cppName;
            break;
        }
        if (name.length() > 0 && !hasBody) {
            if (!this.tokens.get().match(Character.valueOf(';'))) {
                this.tokens.next();
                this.tokens.next();
            }
            this.tokens.next();
            if (friend) {
                decl.text = "";
                declList.add(decl);
                return true;
            }
            if (info != null && info.base != null) {
                String string = base.javaName = context.constName != null ? context.constBaseName : info.base;
            }
            if (name.equals("Pointer")) {
                return true;
            }
            if (!fullName.equals(cppName)) {
                decl.text = decl.text + "@Name(\"" + (context.javaName == null || namespace < 0 ? cppName : cppName.substring(namespace + 2)) + "\") ";
            } else if (context.namespace != null && context.javaName == null) {
                decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
            }
            decl.type = new Type(name);
            decl.text = decl.text + "@Opaque public static class " + name + " extends " + base.javaName + " {\n    /** Empty constructor. Calls {@code super((Pointer)null)}. */\n    public " + name + "() { super((Pointer)null); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public " + name + "(Pointer p) { super(p); }\n}";
            decl.type = type;
            decl.incomplete = true;
            String comment = this.commentAfter();
            decl.text = comment + decl.text;
            declList.spacing = spacing;
            declList.add(decl);
            declList.spacing = null;
            return true;
        }
        if (this.tokens.get().match(Character.valueOf('{'))) {
            this.tokens.next();
        }
        if (type.cppName.length() > 0) {
            ctx.namespace = type.cppName;
            ctx.cppName = originalName;
        }
        if (!anonymous) {
            ctx.javaName = type.javaName;
        }
        if (info != null) {
            if (info.virtualize) {
                ctx.virtualize = true;
            }
            if (info.immutable) {
                ctx.immutable = true;
            }
            if (info.beanify) {
                ctx.beanify = true;
            }
        }
        ctx.baseType = base.cppName;
        DeclarationList declList2 = new DeclarationList();
        if (variables.size() == 0) {
            this.declarations(ctx, declList2);
        } else {
            for (Declarator var : variables) {
                if (context.variable != null) {
                    var.cppName = context.variable.cppName + "." + var.cppName;
                    var.javaName = context.variable.javaName + "_" + var.javaName;
                }
                ctx.variable = var;
                this.declarations(ctx, declList2);
            }
        }
        String modifiers = "public static ";
        String constructors = "";
        String inheritedConstructors = "";
        boolean implicitConstructor = true;
        boolean arrayConstructor = false;
        boolean defaultConstructor = false;
        boolean longConstructor = false;
        boolean pointerConstructor = false;
        boolean abstractClass = info != null && info.purify && !ctx.virtualize;
        boolean allPureConst = true;
        boolean haveVariables = false;
        for (Declaration d2 : declList2) {
            if (d2.declarator != null && d2.declarator.type != null && d2.declarator.type.using && decl.text != null) {
                defaultConstructor |= d2.text.contains("private native void allocate();");
                longConstructor |= d2.text.contains("private native void allocate(long");
                pointerConstructor |= d2.text.contains("private native void allocate(Pointer");
                implicitConstructor &= !d2.text.contains("private native void allocate(");
                String baseType = d2.declarator.type.cppName;
                baseType = baseType.substring(0, baseType.lastIndexOf("::"));
                int template = baseType.lastIndexOf(60);
                int template2 = base.cppName.lastIndexOf(60);
                if (template < 0 && template2 >= 0 && baseType.equals(base.cppName.substring(0, template2))) {
                    baseType = base.cppName;
                }
                List<Info> infoList = this.infoMap.get(baseType);
                String[] pointerTypes = null;
                for (Info info2 : infoList) {
                    if (info2 == null || info2.pointerTypes == null || info2.pointerTypes.length <= 0) continue;
                    pointerTypes = info2.pointerTypes;
                    break;
                }
                int namespace22 = baseType.lastIndexOf("::");
                inheritedConstructors = inheritedConstructors + d2.text.replace(pointerTypes != null ? " " + pointerTypes[0].substring(pointerTypes[0].lastIndexOf(46) + 1) : (namespace22 >= 0 ? " " + baseType.substring(namespace22 + 2) : " " + baseType), " " + shortName) + "\n";
                d2.text = "";
            } else if (d2.declarator != null && d2.declarator.type != null && d2.declarator.type.constructor) {
                implicitConstructor = false;
                Declarator[] paramDcls = d2.declarator.parameters.declarators;
                String t4 = paramDcls.length > 0 ? paramDcls[0].type.javaName : null;
                arrayConstructor |= paramDcls.length == 1 && (t4.equals("int") || t4.equals("long") || t4.equals("float") || t4.equals("double")) && !d2.inaccessible;
                boolean defaultConstructor2 = (paramDcls.length == 0 || paramDcls.length == 1 && t4.equals("void")) && !d2.inaccessible;
                boolean longConstructor2 = paramDcls.length == 1 && t4.equals("long") && !d2.inaccessible;
                boolean pointerConstructor2 = paramDcls.length == 1 && t4.equals("Pointer") && !d2.inaccessible;
                int n3 = d2.text.indexOf("private native void allocate");
                if (defaultConstructor && defaultConstructor2 || longConstructor && longConstructor2 || pointerConstructor && pointerConstructor2 || n3 >= 0 && inheritedConstructors.contains(d2.text.substring(n3))) {
                    d2.text = "";
                }
                defaultConstructor |= defaultConstructor2;
                longConstructor |= longConstructor2;
                pointerConstructor |= pointerConstructor2;
            }
            abstractClass |= d2.abstractMember;
            allPureConst &= d2.constMember && d2.abstractMember;
            haveVariables |= d2.variable;
        }
        if (allPureConst && ctx.virtualize) {
            modifiers = "@Const " + modifiers;
        }
        if (!anonymous) {
            if (!fullName.equals(cppName)) {
                decl.text = decl.text + "@Name(\"" + (context.javaName == null || namespace < 0 ? cppName : cppName.substring(namespace + 2)) + "\") ";
            } else if (context.namespace != null && context.javaName == null) {
                decl.text = decl.text + "@Namespace(\"" + context.namespace + "\") ";
            }
            if ((!implicitConstructor || derivedClass) && haveVariables) {
                decl.text = decl.text + "@NoOffset ";
            }
            if (info != null && info.base != null) {
                base.javaName = context.constName != null ? context.constBaseName : info.base;
            }
            decl.text = decl.text + modifiers + "class " + shortName + " extends " + base.javaName + " {\n    static { Loader.load(); }\n";
            if (!(!implicitConstructor || info != null && info.purify || abstractClass && !ctx.virtualize)) {
                constructors = constructors + "    /** Default native constructor. */\n    public " + shortName + "() { super((Pointer)null); allocate(); }\n    /** Native array allocator. Access with {@link Pointer#position(long)}. */\n    public " + shortName + "(long size) { super((Pointer)null); allocateArray(size); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public " + shortName + "(Pointer p) { super(p); }\n    private native void allocate();\n    private native void allocateArray(long size);\n    @Override public " + shortName + " position(long position) {\n        return (" + shortName + ")super.position(position);\n    }\n    @Override public " + shortName + " getPointer(long i) {\n        return new " + shortName + "((Pointer)this).offsetAddress(i);\n    }\n";
            } else {
                if (!(info != null && info.purify || abstractClass && !ctx.virtualize)) {
                    constructors = constructors + inheritedConstructors;
                }
                if (!pointerConstructor) {
                    constructors = constructors + "    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public " + shortName + "(Pointer p) { super(p); }\n";
                }
                if (!(!defaultConstructor || info != null && info.purify || abstractClass && !ctx.virtualize || arrayConstructor)) {
                    constructors = constructors + "    /** Native array allocator. Access with {@link Pointer#position(long)}. */\n    public " + shortName + "(long size) { super((Pointer)null); allocateArray(size); }\n    private native void allocateArray(long size);\n    @Override public " + shortName + " position(long position) {\n        return (" + shortName + ")super.position(position);\n    }\n    @Override public " + shortName + " getPointer(long i) {\n        return new " + shortName + "((Pointer)this).offsetAddress(i);\n    }\n";
                }
            }
            if (info == null || !info.skipDefaults) {
                decl.text = decl.text + constructors;
            }
            declList.spacing = spacing;
            decl.text = declList.rescan(decl.text + casts + "\n");
            declList.spacing = null;
        }
        for (Type base2 : baseClasses) {
            Info baseInfo = this.infoMap.getFirst(base2.cppName);
            if (baseInfo == null || !baseInfo.flatten || baseInfo.javaText == null) continue;
            String text = baseInfo.javaText;
            int start = text.indexOf(123);
            int n4 = 0;
            while (n4 < 2) {
                char c2 = text.charAt(start);
                if (c2 == '\n') {
                    ++n4;
                } else if (!Character.isWhitespace((int)c2)) {
                    n4 = 0;
                }
                ++start;
            }
            int end = text.lastIndexOf(125);
            decl.text = decl.text + text.substring(start, end).replace(base2.javaName, type.javaName);
            decl.custom = true;
        }
        for (Declaration d2 : declList2) {
            if (!d2.inaccessible && (d2.declarator == null || d2.declarator.type == null || !d2.declarator.type.constructor || !abstractClass || info != null && info.virtualize)) {
                decl.text = decl.text + d2.text;
            }
            if (d2.inaccessible || d2.declarator == null || d2.declarator.type == null || !d2.declarator.type.constructor) continue;
            inheritedConstructors = inheritedConstructors + d2.text;
        }
        String constructorName = originalName;
        int template2 = constructorName.lastIndexOf(60);
        if (template2 >= 0) {
            constructorName = constructorName.substring(0, template2);
            template2 = constructorName.indexOf(60);
            if (!constructorName.contains(">") && template2 >= 0) {
                constructorName = constructorName.substring(0, template2);
            }
        }
        if ((namespace2 = constructorName.lastIndexOf("::")) >= 0) {
            constructorName = constructorName.substring(namespace2 + 2);
        }
        if ((constructorInfo = this.infoMap.getFirst(type.cppName + "::" + constructorName)) == null) {
            constructorInfo = new Info(type.cppName + "::" + constructorName);
            this.infoMap.put(constructorInfo);
        }
        if (constructorInfo.javaText == null && inheritedConstructors.length() > 0) {
            constructorInfo.javaText(inheritedConstructors);
        }
        if (!anonymous) {
            decl.text = decl.text + this.tokens.get().spacing + '}';
        }
        Token token4 = this.tokens.next();
        while (!token4.match(Token.EOF)) {
            if (token4.match(Character.valueOf(';'))) {
                decl.text = decl.text + token4.spacing;
                break;
            }
            token4 = this.tokens.next();
        }
        this.tokens.next();
        decl.type = type;
        if (info != null && info.javaText != null) {
            decl.signature = decl.text = info.javaText;
            decl.custom = true;
        } else if (info != null && info.flatten) {
            info.javaText = decl.text;
        }
        declList.add(decl);
        return true;
    }

    boolean enumeration(Context context, DeclarationList declList) throws ParserException {
        boolean enumerate;
        String comment;
        int backIndex = this.tokens.index;
        String enumSpacing = this.tokens.get().spacing;
        boolean typedef = this.tokens.get().match(Token.TYPEDEF);
        boolean foundEnum = false;
        Token token = this.tokens.get();
        while (!token.match(Token.EOF)) {
            if (token.match(Token.ENUM)) {
                foundEnum = true;
                break;
            }
            if (!token.match(5)) break;
            token = this.tokens.next();
        }
        if (!foundEnum) {
            this.tokens.index = backIndex;
            return false;
        }
        boolean enumClass = false;
        String enumType = "enum";
        if (this.tokens.get(1).match(Token.CLASS, Token.STRUCT)) {
            enumClass = true;
            enumType = enumType + " " + this.tokens.next();
        }
        if (typedef && !this.tokens.get(1).match(Character.valueOf('{')) && this.tokens.get(2).match(5)) {
            this.tokens.next();
        }
        int count = 0;
        boolean longenum = false;
        String cppType = "int";
        String javaType = "int";
        String separator = "";
        String enumPrefix = "public static final " + javaType;
        String countPrefix = "";
        String enumerators = "";
        String enumerators2 = "";
        HashMap<String, String> enumeratorMap = new HashMap<String, String>();
        String extraText = "";
        String name = "";
        Token token2 = this.tokens.next().expect(5, Character.valueOf('{'), Character.valueOf(':'), Character.valueOf(';'));
        if (token2.match(5)) {
            Attribute attr = this.attribute(true);
            while (attr != null && attr.annotation) {
                attr = this.attribute(true);
            }
            name = this.tokens.get().value;
            token2 = this.tokens.next();
        }
        if (token2.match(Character.valueOf(':'))) {
            token2 = this.tokens.next();
            Type type = this.type(context);
            cppType = type.cppName;
            javaType = type.javaName;
            enumPrefix = "public static final " + javaType;
            token2 = this.tokens.get();
        }
        if (typedef || !token2.match(Character.valueOf(';'))) {
            if (!token2.match(Character.valueOf('{'))) {
                this.tokens.index = backIndex;
                return false;
            }
            token2 = this.tokens.next();
            while (!token2.match(Token.EOF, Character.valueOf('}'))) {
                comment = this.commentBefore();
                if (this.macro(context, declList)) {
                    Declaration macroDecl = (Declaration)declList.remove(declList.size() - 1);
                    extraText = extraText + comment + macroDecl.text;
                    if (separator.equals(",") && !macroDecl.text.trim().startsWith("//")) {
                        separator = ";";
                        enumPrefix = "public static final " + javaType;
                    }
                } else {
                    Token enumerator = this.tokens.get();
                    String cppName = enumerator.value;
                    if (cppName == null || cppName.length() == 0) {
                        this.tokens.next().spacing = token2.spacing;
                    } else {
                        Info info;
                        String javaName = cppName;
                        if (enumClass) {
                            cppName = name + "::" + cppName;
                        }
                        if (context.namespace != null) {
                            cppName = context.namespace + "::" + cppName;
                        }
                        if ((info = this.infoMap.getFirst(cppName)) != null && info.javaNames != null && info.javaNames.length > 0) {
                            javaName = info.javaNames[0];
                        } else if (info == null) {
                            info = new Info(cppName).cppText("").translate();
                            this.infoMap.put(info);
                        }
                        String spacing2 = "";
                        if (this.tokens.next().match(Character.valueOf('='))) {
                            spacing2 = this.tokens.get().spacing;
                            if (spacing2.length() > 0 && spacing2.charAt(0) == ' ') {
                                spacing2 = spacing2.substring(1);
                            }
                            countPrefix = "";
                            int count2 = 0;
                            Token prevToken = new Token();
                            boolean translate = info != null ? info.translate : true;
                            token2 = this.tokens.next();
                            while (!token2.match(Token.EOF, Character.valueOf('#'), Character.valueOf(','), Character.valueOf('}')) || count2 > 0) {
                                if (token2.match(1) && token2.value.endsWith("L")) {
                                    longenum = true;
                                }
                                countPrefix = countPrefix + token2.spacing + token2;
                                if (token2.match(Character.valueOf('('))) {
                                    ++count2;
                                } else if (token2.match(Character.valueOf(')'))) {
                                    --count2;
                                }
                                if (prevToken.match(5) && token2.match(Character.valueOf('(')) || token2.match(Character.valueOf('{'), Character.valueOf('}'))) {
                                    translate = false;
                                }
                                prevToken = token2;
                                token2 = this.tokens.next();
                            }
                            try {
                                count = Integer.parseInt(countPrefix.trim());
                                countPrefix = "";
                            }
                            catch (NumberFormatException e2) {
                                count = 0;
                                if (translate) {
                                    if ((countPrefix = this.translate(countPrefix)).length() > 0 && countPrefix.charAt(0) == ' ') {
                                        countPrefix = countPrefix.substring(1);
                                    }
                                }
                                if (separator.equals(",")) {
                                    separator = ";";
                                }
                                String annotations = "";
                                if (!javaName.equals(cppName)) {
                                    annotations = annotations + "@Name(\"" + cppName + "\") ";
                                } else if (context.namespace != null && context.javaName == null) {
                                    annotations = annotations + "@Namespace(\"" + context.namespace + "\") ";
                                }
                                extraText = extraText + "\n" + annotations + "public static native @MemberGetter " + javaType + " " + javaName + "();\n";
                                enumPrefix = "public static final " + javaType;
                                countPrefix = javaName + "()";
                            }
                        }
                        if (extraText.length() > 0 && !extraText.endsWith("\n") && enumPrefix.length() > 0) {
                            extraText = extraText + comment + "\n";
                            comment = "";
                        }
                        String text = separator + extraText + enumPrefix + comment;
                        String text2 = separator + comment;
                        comment = this.commentAfter();
                        if (comment.length() == 0 && this.tokens.get().match(Character.valueOf(','))) {
                            this.tokens.next();
                            comment = this.commentAfter();
                        }
                        String spacing = enumerator.spacing;
                        if (comment.length() > 0) {
                            text = text + spacing + comment;
                            text2 = text2 + spacing + comment;
                            int newline = spacing.lastIndexOf(10);
                            if (newline >= 0) {
                                spacing = spacing.substring(newline + 1);
                            }
                        }
                        if (spacing.length() == 0 && !text.endsWith(",")) {
                            spacing = " ";
                        }
                        String cast = javaType.equals("byte") || javaType.equals("short") ? "(" + javaType + ")(" : "";
                        text = text + spacing + javaName + spacing2 + " = " + cast + countPrefix;
                        text2 = text2 + spacing + javaName + spacing2 + "(" + cast + countPrefix;
                        if (enumeratorMap.containsKey(countPrefix)) {
                            text2 = text2 + ".value";
                        }
                        if (countPrefix.trim().length() > 0) {
                            if (count > 0) {
                                text = text + " + " + count;
                                text2 = text2 + " + " + count;
                            }
                        } else {
                            text = text + count;
                            text2 = text2 + count;
                        }
                        if (javaType.equals("boolean") && (!countPrefix.equals("true") && !countPrefix.equals("false") || count > 0)) {
                            text = text + " != 0";
                            text2 = text2 + " != 0";
                        }
                        if (cast.length() > 0) {
                            text = text + ")";
                            text2 = text2 + ")";
                        }
                        ++count;
                        if (info == null || !info.skip) {
                            enumerators = enumerators + text;
                            enumerators2 = enumerators2 + text2 + ")";
                            enumeratorMap.put(javaName, text);
                            separator = ",";
                            enumPrefix = "";
                            extraText = "";
                        }
                    }
                }
                token2 = this.tokens.get();
            }
        }
        if (longenum) {
            enumerators = enumerators.replace(" " + javaType, " long");
            cppType = "long long";
            javaType = "long";
        }
        comment = this.commentBefore();
        Declaration decl = new Declaration();
        token2 = this.tokens.get();
        while (!token2.match(Character.valueOf(';'), Token.EOF)) {
            int indirections = 0;
            while (token2.match(Character.valueOf('*'))) {
                ++indirections;
                token2 = this.tokens.next();
            }
            if (token2.match(5)) {
                String name1 = name;
                String name2 = token2.value;
                if (typedef && indirections == 0 || name == null || name.length() == 0) {
                    name = name2;
                    if (name1 != null && name1.length() > 0) {
                        name2 = name1;
                    }
                }
                String cppName2 = context.namespace != null ? context.namespace + "::" + name2 : name2;
                Info info2 = this.infoMap.getFirst(cppType);
                if (indirections > 0) {
                    this.infoMap.put(new Info(info2).cast().cppNames(cppName2).valueTypes(info2.pointerTypes).pointerTypes("PointerPointer"));
                } else {
                    this.infoMap.put(new Info(info2).cast().cppNames(cppName2));
                }
            }
            token2 = this.tokens.next();
        }
        String cppName = context.namespace != null ? context.namespace + "::" + name : name;
        Info info = this.infoMap.getFirst(cppName);
        Info info2 = this.infoMap.getFirst(null);
        boolean bl2 = info != null ? info.enumerate : (enumerate = info2 != null ? info2.enumerate : false);
        if (info != null && info.skip) {
            decl.text = enumSpacing;
        } else {
            String javaName;
            int newline;
            if (info != null && info.cppTypes != null && info.cppTypes.length > 0) {
                cppType = info.cppTypes[0];
                String javaType2 = this.infoMap.getFirst((String)cppType).valueTypes[0];
                enumerators = enumerators.replace(" " + javaType, " " + javaType2);
                javaType = javaType2;
            }
            String enumSpacing2 = (newline = enumSpacing.lastIndexOf(10)) < 0 ? enumSpacing : enumSpacing.substring(newline + 1);
            String string = javaName = info != null && info.valueTypes != null && info.valueTypes.length > 0 ? info.valueTypes[0] : name;
            if (enumerate && javaName != null && javaName.length() > 0 && !javaName.equals(javaType) && enumerators.length() > 0 && enumerators2.length() > 0) {
                int i2;
                String shortName = javaName.substring(javaName.lastIndexOf(46) + 1);
                String fullName = context.namespace != null ? context.namespace + "::" + shortName : shortName;
                String annotations = "";
                if (!fullName.equals(cppName)) {
                    annotations = annotations + "@Name(\"" + cppName + "\") ";
                } else if (context.namespace != null && context.javaName == null) {
                    annotations = annotations + "@Namespace(\"" + context.namespace + "\") ";
                }
                decl.text = decl.text + enumSpacing + annotations + "public enum " + shortName + " {" + enumerators2 + token2.expect((Object[])new Object[]{Character.valueOf((char)';')}).spacing + ";" + (comment.length() > 0 && comment.charAt(0) == ' ' ? comment.substring(1) : comment) + "\n\n" + enumSpacing2 + "    public final " + javaType + " value;\n" + enumSpacing2 + "    private " + shortName + "(" + javaType + " v) { this.value = v; }\n" + enumSpacing2 + "    private " + shortName + "(" + shortName + " e) { this.value = e.value; }\n" + enumSpacing2 + "    public " + shortName + " intern() { for (" + shortName + " e : values()) if (e.value == value) return e; return this; }\n" + enumSpacing2 + "    @Override public String toString() { return intern().name(); }\n" + enumSpacing2 + "}";
                info2 = new Info(this.infoMap.getFirst(cppType)).cppNames(cppName);
                info2.valueTypes = Arrays.copyOf(info2.valueTypes, info2.valueTypes.length + 1);
                for (i2 = 1; i2 < info2.valueTypes.length; ++i2) {
                    info2.valueTypes[i2] = "@Cast(\"" + cppName + "\") " + info2.valueTypes[i2 - 1];
                }
                info2.valueTypes[0] = context.javaName != null && context.javaName.length() > 0 ? context.javaName + "." + javaName : javaName;
                info2.pointerTypes = Arrays.copyOf(info2.pointerTypes, info2.pointerTypes.length);
                for (i2 = 0; i2 < info2.pointerTypes.length; ++i2) {
                    info2.pointerTypes[i2] = "@Cast(\"" + cppName + "*\") " + info2.pointerTypes[i2];
                }
                this.infoMap.put(info2.cast(false).enumerate());
            } else {
                decl.text = decl.text + enumSpacing + "/** " + enumType + " " + cppName + " */\n" + enumSpacing2 + enumerators + token2.expect((Object[])new Object[]{Character.valueOf((char)';')}).spacing + ";";
                if (cppName.length() > 0) {
                    info2 = this.infoMap.getFirst(cppType);
                    this.infoMap.put(new Info(info2).cast().cppNames(cppName));
                }
                decl.text = decl.text + extraText + comment;
            }
        }
        declList.add(decl);
        this.tokens.next();
        return true;
    }

    boolean namespace(Context context, DeclarationList declList) throws ParserException {
        if (!this.tokens.get().match(Token.NAMESPACE)) {
            return false;
        }
        Declaration decl = new Declaration();
        String spacing = this.tokens.get().spacing;
        String name = null;
        this.tokens.next();
        if (this.tokens.get().match(5)) {
            name = this.tokens.get().value;
            this.tokens.next();
        }
        if (this.tokens.get().match(Character.valueOf('='))) {
            if (this.tokens.next().match("::")) {
                this.tokens.next();
            }
            Type type = this.type(context);
            context.namespaceMap.put(name, type.cppName);
            this.tokens.get().expect(Character.valueOf(';'));
            this.tokens.next();
            return true;
        }
        this.tokens.get().expect(Character.valueOf('{'));
        this.tokens.next();
        if (this.tokens.get().spacing.indexOf(10) < 0) {
            this.tokens.get().spacing = spacing;
        }
        context = new Context(context);
        context.namespace = name == null ? context.namespace : (context.namespace != null ? context.namespace + "::" + name : name);
        this.declarations(context, declList);
        decl.text = decl.text + this.tokens.get().expect((Object[])new Object[]{Character.valueOf((char)'}')}).spacing;
        this.tokens.next();
        declList.add(decl);
        return true;
    }

    boolean extern(Context context, DeclarationList declList) throws ParserException {
        if (!this.tokens.get().match(Token.EXTERN) || !this.tokens.get(1).match(3)) {
            return false;
        }
        String spacing = this.tokens.get().spacing;
        Declaration decl = new Declaration();
        this.tokens.next().expect("\"C\"", "\"C++\"");
        if (!this.tokens.next().match(Character.valueOf('{'))) {
            this.tokens.get().spacing = spacing;
            declList.add(decl);
            return true;
        }
        this.tokens.next();
        this.declarations(context, declList);
        this.tokens.get().expect(Character.valueOf('}'));
        this.tokens.next();
        declList.add(decl);
        return true;
    }

    void declarations(Context context, DeclarationList declList) throws ParserException {
        Token token = this.tokens.get();
        while (!token.match(Token.EOF, Character.valueOf('}'))) {
            if (token.match(Token.PRIVATE, Token.PROTECTED, Token.PUBLIC) && this.tokens.get(1).match(Character.valueOf(':'))) {
                context.inaccessible = !token.match(Token.PUBLIC);
                this.tokens.next();
                this.tokens.next();
            } else {
                Context ctx = context;
                String comment = this.commentBefore();
                token = this.tokens.get();
                String spacing = token.spacing;
                TemplateMap map = this.template(ctx);
                if (map != null) {
                    token = this.tokens.get();
                    token.spacing = spacing;
                    ctx = new Context(ctx);
                    ctx.templateMap = map;
                }
                Declaration decl = new Declaration();
                if (comment != null && comment.length() > 0) {
                    decl.inaccessible = ctx.inaccessible;
                    decl.text = comment;
                    decl.comment = true;
                    declList.add(decl);
                }
                int startIndex = this.tokens.index;
                declList.infoMap = this.infoMap;
                declList.context = ctx;
                declList.templateMap = map;
                declList.infoIterator = null;
                declList.spacing = null;
                do {
                    Info info;
                    if (map != null && declList.infoIterator != null && declList.infoIterator.hasNext()) {
                        info = declList.infoIterator.next();
                        if (info == null) continue;
                        Type type = new Parser(this, info.cppNames[0]).type(context);
                        if (type.arguments == null) continue;
                        int count = 0;
                        for (Map.Entry entry : map.entrySet()) {
                            entry.setValue(null);
                            if (count >= type.arguments.length) continue;
                            Type t2 = type.arguments[count++];
                            String s2 = t2.cppName;
                            if (t2.constValue && !s2.startsWith("const ")) {
                                s2 = "const " + s2;
                            }
                            if (t2.indirections > 0) {
                                for (int i2 = 0; i2 < t2.indirections; ++i2) {
                                    s2 = s2 + "*";
                                }
                            }
                            if (t2.reference) {
                                s2 = s2 + "&";
                            }
                            if (t2.rvalue) {
                                s2 = s2 + "&&";
                            }
                            if (t2.constPointer && !s2.endsWith(" const")) {
                                s2 = s2 + " const";
                            }
                            t2.cppName = s2;
                            entry.setValue(t2);
                        }
                        this.tokens.index = startIndex;
                    } else if (declList.infoIterator != null && declList.infoIterator.hasNext()) {
                        info = declList.infoIterator.next();
                        if (info == null) continue;
                        if (info.cppNames != null && info.cppNames.length > 0 && info.cppNames[0].startsWith("const ") && info.pointerTypes != null && info.pointerTypes.length > 0) {
                            ctx = new Context(ctx);
                            ctx.constName = info.pointerTypes[0].substring(info.pointerTypes[0].lastIndexOf(" ") + 1);
                            ctx.constBaseName = info.base != null ? info.base : "Pointer";
                        }
                        this.tokens.index = startIndex;
                    }
                    if (!(this.tokens.get().match(Character.valueOf(';')) || this.macro(ctx, declList) || this.extern(ctx, declList) || this.namespace(ctx, declList) || this.enumeration(ctx, declList) || this.group(ctx, declList) || this.typedef(ctx, declList) || this.using(ctx, declList) || this.function(ctx, declList) || this.variable(ctx, declList))) {
                        spacing = this.tokens.get().spacing;
                        if (this.attribute() != null) {
                            this.tokens.get().spacing = spacing;
                        } else {
                            throw new ParserException(token.file + ":" + token.lineNumber + ":" + (token.text != null ? "\"" + token.text + "\": " : "") + "Could not parse declaration at '" + token + "'");
                        }
                    }
                    while (this.tokens.get().match(Character.valueOf(';')) && !this.tokens.get().match(Token.EOF)) {
                        this.tokens.next();
                    }
                } while (declList.infoIterator != null && declList.infoIterator.hasNext());
            }
            token = this.tokens.get();
        }
        String comment = this.commentBefore() + (this.tokens.get().match(Token.EOF) ? this.tokens.get().spacing : "");
        Declaration decl = new Declaration();
        if (comment != null && comment.length() > 0) {
            decl.text = comment;
            decl.comment = true;
            declList.add(decl);
        }
    }

    void parse(Context context, DeclarationList declList, String[] includePath, String include, boolean isCFile) throws IOException, ParserException {
        Info info;
        ArrayList<Token> tokenList = new ArrayList<Token>();
        Object file = null;
        String filename = include;
        if (filename == null || filename.length() == 0) {
            return;
        }
        if (filename.startsWith("<") && filename.endsWith(">")) {
            filename = filename.substring(1, filename.length() - 1);
        } else {
            String[] f2 = new File(filename);
            if (f2.exists()) {
                file = f2;
            }
        }
        if (file == null && includePath != null) {
            for (String path : includePath) {
                File f3 = new File(path, filename).getCanonicalFile();
                if (!f3.exists()) continue;
                file = f3;
                break;
            }
        }
        if (file == null) {
            file = new File(filename);
        }
        if ((info = this.infoMap.getFirst(((File)file).getName())) != null && info.skip && info.linePatterns == null) {
            return;
        }
        if (!((File)file).exists()) {
            throw new FileNotFoundException("Could not parse \"" + file + "\": File does not exist");
        }
        this.logger.info("Parsing " + file);
        Token token = new Token();
        token.type = 4;
        token.value = "\n// Parsed from " + include + "\n\n";
        tokenList.add(token);
        Tokenizer tokenizer = new Tokenizer((File)file, this.encoding);
        if (info != null && info.linePatterns != null) {
            tokenizer.filterLines(info.linePatterns, info.skip);
        }
        while (!(token = tokenizer.nextToken()).isEmpty()) {
            if (token.type == -1) {
                token.type = 4;
            }
            tokenList.add(token);
        }
        if (this.lineSeparator == null) {
            this.lineSeparator = tokenizer.lineSeparator;
        }
        tokenizer.close();
        token = new Token(Token.EOF);
        token.spacing = "\n";
        token.file = file;
        token.lineNumber = ((Token)tokenList.get((int)(tokenList.size() - 1))).lineNumber;
        tokenList.add(token);
        this.tokens = new TokenIndexer(this.infoMap, tokenList.toArray(new Token[tokenList.size()]), isCFile);
        context.objectify = context.objectify | (info != null && info.objectify);
        this.declarations(context, declList);
    }

    public File[] parse(String outputDirectory, String[] classPath, Class cls) throws IOException, ParserException {
        return this.parse(new File(outputDirectory), classPath, cls);
    }

    public File[] parse(File outputDirectory, String[] classPath, Class cls) throws IOException, ParserException {
        boolean isCFile;
        ClassProperties allProperties = Loader.loadProperties(cls, this.properties, true);
        ClassProperties clsProperties = Loader.loadProperties(cls, this.properties, false);
        ArrayList<String> cIncludes = new ArrayList<String>();
        cIncludes.addAll(clsProperties.get("platform.cinclude"));
        cIncludes.addAll(allProperties.get("platform.cinclude"));
        ArrayList<String> clsIncludes = new ArrayList<String>();
        clsIncludes.addAll(clsProperties.get("platform.include"));
        clsIncludes.addAll(clsProperties.get("platform.cinclude"));
        ArrayList<String> allIncludes = new ArrayList<String>();
        allIncludes.addAll(allProperties.get("platform.include"));
        allIncludes.addAll(allProperties.get("platform.cinclude"));
        List<String> allTargets = allProperties.get("target");
        List<String> allGlobals = allProperties.get("global");
        List<String> clsTargets = clsProperties.get("target");
        List<String> clsGlobals = clsProperties.get("global");
        List<String> clsHelpers = clsProperties.get("helper");
        String target = clsTargets.get(clsTargets.size() - 1);
        String global = clsGlobals.get(clsGlobals.size() - 1);
        List<Class> allInherited = allProperties.getInheritedClasses();
        this.infoMap = new InfoMap();
        for (Class c2 : allInherited) {
            try {
                InfoMapper infoMapper = (InfoMapper)c2.newInstance();
                if (infoMapper instanceof BuildEnabled) {
                    ((BuildEnabled)((Object)infoMapper)).init(this.logger, this.properties, this.encoding);
                }
                infoMapper.map(this.infoMap);
            }
            catch (ClassCastException | IllegalAccessException | InstantiationException infoMapper) {}
        }
        this.leafInfoMap = new InfoMap();
        try {
            InfoMapper infoMapper = (InfoMapper)cls.newInstance();
            if (infoMapper instanceof BuildEnabled) {
                ((BuildEnabled)((Object)infoMapper)).init(this.logger, this.properties, this.encoding);
            }
            infoMapper.map(this.leafInfoMap);
        }
        catch (ClassCastException | IllegalAccessException | InstantiationException infoMapper) {
            // empty catch block
        }
        this.infoMap.putAll(this.leafInfoMap);
        String version = Parser.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "unknown";
        }
        int n2 = global.lastIndexOf(46);
        String text = "";
        String header = "// Targeted by JavaCPP version " + version + ": DO NOT EDIT THIS FILE\n\n";
        String targetHeader = header + "package " + target + ";\n\n";
        String globalHeader = header + (n2 >= 0 ? "package " + global.substring(0, n2) + ";\n\n" : "");
        List<Info> infoList = this.leafInfoMap.get(null);
        boolean objectify = false;
        for (Info info : infoList) {
            objectify |= info != null && info.objectify;
            if (info.javaText == null || !info.javaText.startsWith("import")) continue;
            text = text + info.javaText + "\n";
        }
        if (!target.equals(global) && !targetHeader.equals(globalHeader)) {
            globalHeader = globalHeader + "import " + target + ".*;\n\n";
        }
        text = text + "import java.nio.*;\nimport org.bytedeco.javacpp.*;\nimport org.bytedeco.javacpp.annotation.*;\n\n";
        for (int i2 = 0; i2 < allTargets.size(); ++i2) {
            if (target.equals(allTargets.get(i2))) continue;
            text = allTargets.get(i2).equals(allGlobals.get(i2)) ? text + "import static " + allTargets.get(i2) + ".*;\n" : text + "import " + allTargets.get(i2) + ".*;\nimport static " + allGlobals.get(i2) + ".*;\n";
        }
        if (allTargets.size() > 1) {
            text = text + "\n";
        }
        String globalText = globalHeader + text + "public class " + global.substring(n2 + 1) + " extends " + (clsHelpers.size() > 0 && clsIncludes.size() > 0 ? clsHelpers.get(0) : cls.getCanonicalName()) + " {\n    static { Loader.load(); }\n";
        String targetPath = target.replace('.', File.separatorChar);
        String globalPath = global.replace('.', File.separatorChar);
        File targetFile = new File(outputDirectory, targetPath);
        File globalFile = new File(outputDirectory, globalPath + ".java");
        this.logger.info("Targeting " + globalFile);
        Context context = new Context();
        context.infoMap = this.infoMap;
        context.objectify = objectify;
        String[] includePath = classPath;
        n2 = globalPath.lastIndexOf(File.separatorChar);
        if (n2 >= 0) {
            includePath = (String[])classPath.clone();
            int i3 = 0;
            while (i3 < includePath.length) {
                int n3 = i3++;
                includePath[n3] = includePath[n3] + File.separator + globalPath.substring(0, n2);
            }
        }
        List<String> paths = allProperties.get("platform.includepath");
        for (String s2 : allProperties.get("platform.includeresource")) {
            for (File f2 : Loader.cacheResources(s2)) {
                paths.add(f2.getCanonicalPath());
            }
        }
        if (clsIncludes.size() == 0) {
            this.logger.info("Nothing targeted for " + globalFile);
            return null;
        }
        String[] includePaths = paths.toArray(new String[paths.size() + includePath.length]);
        System.arraycopy(includePath, 0, includePaths, paths.size(), includePath.length);
        DeclarationList declList = new DeclarationList();
        for (String include : allIncludes) {
            if (clsIncludes.contains(include)) continue;
            isCFile = cIncludes.contains(include);
            this.parse(context, declList, includePaths, include, isCFile);
        }
        declList = new DeclarationList(declList);
        if (clsIncludes.size() > 0) {
            this.containers(context, declList);
            for (String include : clsIncludes) {
                if (!allIncludes.contains(include)) continue;
                isCFile = cIncludes.contains(include);
                this.parse(context, declList, includePaths, include, isCFile);
            }
        }
        if (declList.size() == 0) {
            this.logger.info("Nothing targeted for " + globalFile);
            return null;
        }
        File targetDir = targetFile;
        File globalDir = globalFile.getParentFile();
        if (!target.equals(global)) {
            targetDir.mkdirs();
        }
        if (globalDir != null) {
            globalDir.mkdirs();
        }
        ArrayList<File> outputFiles = new ArrayList<File>(Arrays.asList(globalFile));
        try (EncodingFileWriter out = this.encoding != null ? new EncodingFileWriter(globalFile, this.encoding, this.lineSeparator) : new EncodingFileWriter(globalFile, this.lineSeparator);){
            ((Writer)out).append(globalText);
            for (Info info : infoList) {
                if (info.javaText == null || info.javaText.startsWith("import")) continue;
                ((Writer)out).append(info.javaText + "\n");
            }
            Declaration prevd = null;
            for (Declaration d2 : declList) {
                if (!target.equals(global) && d2.type != null && d2.type.javaName != null && d2.type.javaName.length() > 0) {
                    String shortName = d2.type.javaName.substring(d2.type.javaName.lastIndexOf(46) + 1);
                    File javaFile = new File(targetDir, shortName + ".java");
                    if (prevd != null && !prevd.comment) {
                        ((Writer)out).append(prevd.text);
                    }
                    ((Writer)out).append("\n// Targeting " + globalDir.toPath().relativize(javaFile.toPath()) + "\n\n");
                    this.logger.info("Targeting " + javaFile);
                    String javaText = targetHeader + text + "import static " + global + ".*;\n" + (prevd != null && prevd.comment ? prevd.text : "") + d2.text.replace("public static class " + shortName + " ", "@Properties(inherit = " + cls.getCanonicalName() + ".class)\npublic class " + shortName + " ") + "\n";
                    outputFiles.add(javaFile);
                    Files.write(javaFile.toPath(), this.encoding != null ? javaText.getBytes(this.encoding) : javaText.getBytes(), new OpenOption[0]);
                    prevd = null;
                    continue;
                }
                if (prevd != null) {
                    ((Writer)out).append(prevd.text);
                }
                prevd = d2;
            }
            if (prevd != null) {
                ((Writer)out).append(prevd.text);
            }
            ((Writer)out).append("\n}\n").close();
        }
        return outputFiles.toArray(new File[outputFiles.size()]);
    }
}

