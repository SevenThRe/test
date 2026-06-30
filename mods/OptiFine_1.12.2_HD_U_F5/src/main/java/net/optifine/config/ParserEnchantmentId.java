/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  alk
 */
package net.optifine.config;

import net.optifine.config.IParserInt;

public class ParserEnchantmentId
implements IParserInt {
    @Override
    public int parse(String str, int defVal) {
        alk en = alk.b((String)str);
        if (en == null) {
            return defVal;
        }
        return alk.b((alk)en);
    }
}

