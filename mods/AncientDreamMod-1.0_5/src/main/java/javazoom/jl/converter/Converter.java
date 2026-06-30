/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javazoom.jl.converter.WaveFileObuffer;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Obuffer;

public class Converter {
    public synchronized void convert(String string, String string2) throws JavaLayerException {
        this.convert(string, string2, null, null);
    }

    public synchronized void convert(String string, String string2, ProgressListener progressListener) throws JavaLayerException {
        this.convert(string, string2, progressListener, null);
    }

    public void convert(String string, String string2, ProgressListener progressListener, Decoder.Params params) throws JavaLayerException {
        if (string2.length() == 0) {
            string2 = null;
        }
        try {
            InputStream inputStream = this.openInput(string);
            this.convert(inputStream, string2, progressListener, params);
            inputStream.close();
        }
        catch (IOException iOException) {
            throw new JavaLayerException(iOException.getLocalizedMessage(), iOException);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void convert(InputStream inputStream, String string, ProgressListener progressListener, Decoder.Params params) throws JavaLayerException {
        if (progressListener == null) {
            progressListener = PrintWriterProgressListener.newStdOut(0);
        }
        try {
            int n;
            if (!(inputStream instanceof BufferedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            int n2 = -1;
            if (inputStream.markSupported()) {
                inputStream.mark(-1);
                n2 = this.countFrames(inputStream);
                inputStream.reset();
            }
            progressListener.converterUpdate(1, n2, 0);
            Obuffer obuffer = null;
            Decoder decoder = new Decoder(params);
            Bitstream bitstream = new Bitstream(inputStream);
            if (n2 == -1) {
                n2 = Integer.MAX_VALUE;
            }
            long l = System.currentTimeMillis();
            try {
                for (n = 0; n < n2; ++n) {
                    try {
                        Obuffer obuffer2;
                        Header header = bitstream.readFrame();
                        if (header == null) {
                            break;
                        }
                        progressListener.readFrame(n, header);
                        if (obuffer == null) {
                            int n3 = header.mode() == 3 ? 1 : 2;
                            int n4 = header.frequency();
                            obuffer = new WaveFileObuffer(n3, n4, string);
                            decoder.setOutputBuffer(obuffer);
                        }
                        if ((obuffer2 = decoder.decodeFrame(header, bitstream)) != obuffer) {
                            throw new InternalError("Output buffers are different.");
                        }
                        progressListener.decodedFrame(n, header, obuffer);
                        bitstream.closeFrame();
                        continue;
                    }
                    catch (Exception exception) {
                        boolean bl;
                        boolean bl2 = bl = !progressListener.converterException(exception);
                        if (!bl) continue;
                        throw new JavaLayerException(exception.getLocalizedMessage(), exception);
                    }
                }
            }
            finally {
                if (obuffer != null) {
                    obuffer.close();
                }
            }
            int n5 = (int)(System.currentTimeMillis() - l);
            progressListener.converterUpdate(2, n5, n);
        }
        catch (IOException iOException) {
            throw new JavaLayerException(iOException.getLocalizedMessage(), iOException);
        }
    }

    protected int countFrames(InputStream inputStream) {
        return -1;
    }

    protected InputStream openInput(String string) throws IOException {
        File file = new File(string);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return bufferedInputStream;
    }

    public static class PrintWriterProgressListener
    implements ProgressListener {
        public static final int NO_DETAIL = 0;
        public static final int EXPERT_DETAIL = 1;
        public static final int VERBOSE_DETAIL = 2;
        public static final int DEBUG_DETAIL = 7;
        public static final int MAX_DETAIL = 10;
        private PrintWriter pw;
        private int detailLevel;

        public static PrintWriterProgressListener newStdOut(int n) {
            return new PrintWriterProgressListener(new PrintWriter(System.out, true), n);
        }

        public PrintWriterProgressListener(PrintWriter printWriter, int n) {
            this.pw = printWriter;
            this.detailLevel = n;
        }

        public boolean isDetail(int n) {
            return this.detailLevel >= n;
        }

        public void converterUpdate(int n, int n2, int n3) {
            if (this.isDetail(2)) {
                switch (n) {
                    case 2: {
                        if (n3 == 0) {
                            n3 = 1;
                        }
                        this.pw.println();
                        this.pw.println("Converted " + n3 + " frames in " + n2 + " ms (" + n2 / n3 + " ms per frame.)");
                    }
                }
            }
        }

        public void parsedFrame(int n, Header header) {
            if (n == 0 && this.isDetail(2)) {
                String string = header.toString();
                this.pw.println("File is a " + string);
            } else if (this.isDetail(10)) {
                String string = header.toString();
                this.pw.println("Prased frame " + n + ": " + string);
            }
        }

        public void readFrame(int n, Header header) {
            if (n == 0 && this.isDetail(2)) {
                String string = header.toString();
                this.pw.println("File is a " + string);
            } else if (this.isDetail(10)) {
                String string = header.toString();
                this.pw.println("Read frame " + n + ": " + string);
            }
        }

        public void decodedFrame(int n, Header header, Obuffer obuffer) {
            if (this.isDetail(10)) {
                String string = header.toString();
                this.pw.println("Decoded frame " + n + ": " + string);
                this.pw.println("Output: " + obuffer);
            } else if (this.isDetail(2)) {
                if (n == 0) {
                    this.pw.print("Converting.");
                    this.pw.flush();
                }
                if (n % 10 == 0) {
                    this.pw.print('.');
                    this.pw.flush();
                }
            }
        }

        public boolean converterException(Throwable throwable) {
            if (this.detailLevel > 0) {
                throwable.printStackTrace(this.pw);
                this.pw.flush();
            }
            return false;
        }
    }

    public static interface ProgressListener {
        public static final int UPDATE_FRAME_COUNT = 1;
        public static final int UPDATE_CONVERT_COMPLETE = 2;

        public void converterUpdate(int var1, int var2, int var3);

        public void parsedFrame(int var1, Header var2);

        public void readFrame(int var1, Header var2);

        public void decodedFrame(int var1, Header var2, Obuffer var3);

        public boolean converterException(Throwable var1);
    }
}

