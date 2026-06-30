/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.http;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import net.optifine.http.HttpPipelineConnection;
import net.optifine.http.HttpPipelineRequest;
import net.optifine.http.HttpResponse;

public class HttpPipelineReceiver
extends Thread {
    private HttpPipelineConnection httpPipelineConnection = null;
    private static final Charset ASCII = Charset.forName("ASCII");
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";
    private static final char CR = '\r';
    private static final char LF = '\n';

    public HttpPipelineReceiver(HttpPipelineConnection httpPipelineConnection) {
        super("HttpPipelineReceiver");
        this.httpPipelineConnection = httpPipelineConnection;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            HttpPipelineRequest currentRequest = null;
            try {
                currentRequest = this.httpPipelineConnection.getNextRequestReceive();
                InputStream in = this.httpPipelineConnection.getInputStream();
                HttpResponse resp = this.readResponse(in);
                this.httpPipelineConnection.onResponseReceived(currentRequest, resp);
            }
            catch (InterruptedException e) {
                return;
            }
            catch (Exception e) {
                this.httpPipelineConnection.onExceptionReceive(currentRequest, e);
            }
        }
    }

    private HttpResponse readResponse(InputStream in) throws IOException {
        String line;
        String statusLine = this.readLine(in);
        String[] parts = Config.tokenize(statusLine, " ");
        if (parts.length < 3) {
            throw new IOException("Invalid status line: " + statusLine);
        }
        String http = parts[0];
        int status = Config.parseInt(parts[1], 0);
        String message = parts[2];
        LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();
        while ((line = this.readLine(in)).length() > 0) {
            int pos = line.indexOf(":");
            if (pos <= 0) continue;
            String key = line.substring(0, pos).trim();
            String val = line.substring(pos + 1).trim();
            headers.put(key, val);
        }
        byte[] body = null;
        String lenStr = (String)headers.get(HEADER_CONTENT_LENGTH);
        if (lenStr != null) {
            int len = Config.parseInt(lenStr, -1);
            if (len > 0) {
                body = new byte[len];
                this.readFull(body, in);
            }
        } else {
            String enc = (String)headers.get("Transfer-Encoding");
            if (Config.equals(enc, "chunked")) {
                body = this.readContentChunked(in);
            }
        }
        return new HttpResponse(status, statusLine, headers, body);
    }

    private byte[] readContentChunked(InputStream in) throws IOException {
        int len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do {
            String line = this.readLine(in);
            String[] parts = Config.tokenize(line, "; ");
            len = Integer.parseInt(parts[0], 16);
            byte[] buf = new byte[len];
            this.readFull(buf, in);
            baos.write(buf);
            this.readLine(in);
        } while (len != 0);
        return baos.toByteArray();
    }

    private void readFull(byte[] buf, InputStream in) throws IOException {
        int len;
        for (int pos = 0; pos < buf.length; pos += len) {
            len = in.read(buf, pos, buf.length - pos);
            if (len >= 0) continue;
            throw new EOFException();
        }
    }

    private String readLine(InputStream in) throws IOException {
        int i;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int prev = -1;
        boolean hasCRLF = false;
        while ((i = in.read()) >= 0) {
            baos.write(i);
            if (prev == 13 && i == 10) {
                hasCRLF = true;
                break;
            }
            prev = i;
        }
        byte[] bytes = baos.toByteArray();
        String str = new String(bytes, ASCII);
        if (hasCRLF) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
}

