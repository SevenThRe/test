/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import java.io.PrintStream;

public class JavaLayerException
extends Exception {
    private Throwable exception;

    public JavaLayerException() {
    }

    public JavaLayerException(String string) {
        super(string);
    }

    public JavaLayerException(String string, Throwable throwable) {
        super(string);
        this.exception = throwable;
    }

    public Throwable getException() {
        return this.exception;
    }

    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        if (this.exception == null) {
            super.printStackTrace(printStream);
        } else {
            this.exception.printStackTrace();
        }
    }
}

