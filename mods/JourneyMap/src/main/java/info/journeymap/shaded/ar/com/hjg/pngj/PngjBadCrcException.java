/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.PngjInputException;

public class PngjBadCrcException
extends PngjInputException {
    private static final long serialVersionUID = 1L;

    public PngjBadCrcException(String message, Throwable cause) {
        super(message, cause);
    }

    public PngjBadCrcException(String message) {
        super(message);
    }

    public PngjBadCrcException(Throwable cause) {
        super(cause);
    }
}

