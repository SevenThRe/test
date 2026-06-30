/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

public class Info {
    String[] cppNames = null;
    String[] javaNames = null;
    String[] annotations = null;
    String[] cppTypes = null;
    String[] valueTypes = null;
    String[] pointerTypes = null;
    String[] linePatterns = null;
    boolean cast = false;
    boolean define = false;
    boolean enumerate = false;
    boolean flatten = false;
    boolean immutable = false;
    boolean beanify = false;
    boolean objectify = false;
    boolean translate = false;
    boolean skip = false;
    boolean skipDefaults = false;
    boolean purify = false;
    boolean virtualize = false;
    String base = null;
    String cppText = null;
    String javaText = null;

    public Info() {
    }

    public Info(String ... cppNames) {
        this.cppNames = cppNames;
    }

    public Info(Info i2) {
        this.cppNames = i2.cppNames != null ? (String[])i2.cppNames.clone() : null;
        this.javaNames = i2.javaNames != null ? (String[])i2.javaNames.clone() : null;
        this.annotations = i2.annotations != null ? (String[])i2.annotations.clone() : null;
        this.cppTypes = i2.cppTypes != null ? (String[])i2.cppTypes.clone() : null;
        this.valueTypes = i2.valueTypes != null ? (String[])i2.valueTypes.clone() : null;
        this.pointerTypes = i2.pointerTypes != null ? (String[])i2.pointerTypes.clone() : null;
        this.linePatterns = i2.linePatterns != null ? (String[])i2.linePatterns.clone() : null;
        this.cast = i2.cast;
        this.define = i2.define;
        this.enumerate = i2.enumerate;
        this.flatten = i2.flatten;
        this.immutable = i2.immutable;
        this.beanify = i2.beanify;
        this.objectify = i2.objectify;
        this.translate = i2.translate;
        this.skip = i2.skip;
        this.skipDefaults = i2.skipDefaults;
        this.purify = i2.purify;
        this.virtualize = i2.virtualize;
        this.base = i2.base;
        this.cppText = i2.cppText;
        this.javaText = i2.javaText;
    }

    public Info cppNames(String ... cppNames) {
        this.cppNames = cppNames;
        return this;
    }

    public Info javaNames(String ... javaNames) {
        this.javaNames = javaNames;
        return this;
    }

    public Info annotations(String ... annotations) {
        this.annotations = annotations;
        return this;
    }

    public Info cppTypes(String ... cppTypes) {
        this.cppTypes = cppTypes;
        return this;
    }

    public Info valueTypes(String ... valueTypes) {
        this.valueTypes = valueTypes;
        return this;
    }

    public Info pointerTypes(String ... pointerTypes) {
        this.pointerTypes = pointerTypes;
        return this;
    }

    public Info linePatterns(String ... linePatterns) {
        this.linePatterns = linePatterns;
        return this;
    }

    public Info cast() {
        this.cast = true;
        return this;
    }

    public Info cast(boolean cast) {
        this.cast = cast;
        return this;
    }

    public Info define() {
        this.define = true;
        return this;
    }

    public Info define(boolean define) {
        this.define = define;
        return this;
    }

    public Info enumerate() {
        this.enumerate = true;
        return this;
    }

    public Info enumerate(boolean enumerate) {
        this.enumerate = enumerate;
        return this;
    }

    public Info flatten() {
        this.flatten = true;
        return this;
    }

    public Info flatten(boolean flatten) {
        this.flatten = flatten;
        return this;
    }

    public Info immutable() {
        this.immutable = true;
        return this;
    }

    public Info immutable(boolean immutable) {
        this.immutable = immutable;
        return this;
    }

    public Info beanify() {
        this.beanify = true;
        return this;
    }

    public Info beanify(boolean beanify) {
        this.beanify = beanify;
        return this;
    }

    public Info objectify() {
        this.objectify = true;
        return this;
    }

    public Info objectify(boolean objectify) {
        this.objectify = objectify;
        return this;
    }

    public Info translate() {
        this.translate = true;
        return this;
    }

    public Info translate(boolean translate) {
        this.translate = translate;
        return this;
    }

    public Info skip() {
        this.skip = true;
        return this;
    }

    public Info skip(boolean skip) {
        this.skip = skip;
        return this;
    }

    public Info skipDefaults() {
        this.skipDefaults = true;
        return this;
    }

    public Info skipDefaults(boolean skipDefaults) {
        this.skipDefaults = skipDefaults;
        return this;
    }

    public Info purify() {
        this.purify = true;
        return this;
    }

    public Info purify(boolean purify) {
        this.purify = purify;
        return this;
    }

    public Info virtualize() {
        this.virtualize = true;
        return this;
    }

    public Info virtualize(boolean virtualize) {
        this.virtualize = virtualize;
        return this;
    }

    public Info base(String base) {
        this.base = base;
        return this;
    }

    public Info cppText(String cppText) {
        this.cppText = cppText;
        return this;
    }

    public Info javaText(String javaText) {
        this.javaText = javaText;
        return this;
    }
}

