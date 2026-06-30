/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.ServletInputStream;
import info.journeymap.shaded.org.javax.servlet.ServletOutputStream;
import java.io.IOException;

public interface WebConnection
extends AutoCloseable {
    public ServletInputStream getInputStream() throws IOException;

    public ServletOutputStream getOutputStream() throws IOException;
}

