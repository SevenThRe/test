/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public enum ez {
    ra("=="),
    ha("!="),
    ga("??"),
    ia("&&"),
    sa("||"),
    la(">="),
    ca("<="),
    za("->"),
    pa(">"),
    da("<"),
    qa("("),
    wa(")"),
    fa("["),
    oa("]"),
    ka("{"),
    ua("}"),
    xa(","),
    ta("="),
    aa("+"),
    ba("&"),
    ma("-"),
    h("*"),
    f("/"),
    d("%"),
    p("?"),
    u(":"),
    w(";"),
    a("if"),
    e("else"),
    n("elseif"),
    j("!"),
    i("return"),
    l("continue"),
    z("break"),
    s("for_each"),
    g("await"),
    t("loop"),
    r("ifelse"),
    x("this"),
    v("true"),
    m("false"),
    c(""),
    q(""),
    b(""),
    o(""),
    y("");

    public final String k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ ez(String string) {
        void a2;
        ez a3;
        a3.k = a2;
    }

    public String ALLATORIxDEMO() {
        ez a2;
        return a2.k;
    }

    public static ez ALLATORIxDEMO(String a2) {
        for (ez a3 : ez.values()) {
            if (!a3.ALLATORIxDEMO().equals(a2)) continue;
            return a3;
        }
        return null;
    }
}

