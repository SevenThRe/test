/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.util;

import java.nio.ByteBuffer;
import org.eclipse.jetty.util.Utf8Appendable;

public class Utf8PartialBuilder {
    private final StringBuilder str = new StringBuilder();
    private final Utf8Appendable utf8 = new Utf8Appendable(this.str){

        @Override
        public int length() {
            return Utf8PartialBuilder.this.str.length();
        }
    };

    public String toPartialString(ByteBuffer buf) {
        if (buf == null) {
            return "";
        }
        this.utf8.append(buf);
        String ret = this.str.toString();
        this.str.setLength(0);
        return ret;
    }
}

