/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.serialization;

import info.journeymap.shaded.kotlin.spark.serialization.Serializer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

class DefaultSerializer
extends Serializer {
    DefaultSerializer() {
    }

    @Override
    public boolean canProcess(Object element) {
        return true;
    }

    @Override
    public void process(OutputStream outputStream, Object element) throws IOException {
        try {
            outputStream.write(element.toString().getBytes("utf-8"));
        }
        catch (UnsupportedEncodingException e) {
            throw new IOException(e);
        }
    }
}

