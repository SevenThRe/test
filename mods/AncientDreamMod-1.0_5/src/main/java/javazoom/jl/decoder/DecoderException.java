/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.DecoderErrors;
import javazoom.jl.decoder.JavaLayerException;

public class DecoderException
extends JavaLayerException
implements DecoderErrors {
    private int errorcode = 512;

    public DecoderException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DecoderException(int n, Throwable throwable) {
        this(DecoderException.getErrorString(n), throwable);
        this.errorcode = n;
    }

    public int getErrorCode() {
        return this.errorcode;
    }

    public static String getErrorString(int n) {
        return "Decoder errorcode " + Integer.toHexString(n);
    }
}

