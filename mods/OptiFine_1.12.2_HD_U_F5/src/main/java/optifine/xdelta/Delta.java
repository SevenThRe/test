/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import optifine.xdelta.ByteArraySeekableSource;
import optifine.xdelta.Checksum;
import optifine.xdelta.DebugDiffWriter;
import optifine.xdelta.DeltaException;
import optifine.xdelta.DiffWriter;
import optifine.xdelta.GDiffWriter;
import optifine.xdelta.RandomAccessFileSeekableSource;
import optifine.xdelta.SeekableSource;
import optifine.xdelta.SeekableSourceInputStream;

public class Delta {
    public static final int S = 16;
    public static final boolean debug = false;
    public static final int buff_size = 1024;

    public static void computeDelta(SeekableSource source, InputStream targetIS, int targetLength, DiffWriter output) throws IOException, DeltaException {
        int bytesRead;
        int sourceLength = (int)source.length();
        Checksum checksum = new Checksum();
        checksum.generateChecksums(new SeekableSourceInputStream(source), sourceLength);
        source.seek(0L);
        PushbackInputStream target = new PushbackInputStream(new BufferedInputStream(targetIS), 1024);
        boolean done = false;
        byte[] buf = new byte[16];
        long hashf = 0L;
        byte[] b2 = new byte[1];
        byte[] sourcebyte = new byte[16];
        if (targetLength <= 16 || sourceLength <= 16) {
            int readBytes;
            while ((readBytes = target.read(buf)) >= 0) {
                int i = 0;
                while (i < readBytes) {
                    output.addData(buf[i]);
                    ++i;
                }
            }
            return;
        }
        int targetidx = bytesRead = target.read(buf, 0, 16);
        long alternativehashf = hashf = Checksum.queryChecksum(buf, 16);
        boolean sourceOutofBytes = false;
        while (!done) {
            int index = checksum.findChecksumIndex(hashf);
            if (index != -1) {
                boolean match = true;
                int offset = index * 16;
                int length = 15;
                source.seek(offset);
                if (!sourceOutofBytes && source.read(sourcebyte, 0, 16) != -1) {
                    int ix = 0;
                    while (ix < 16) {
                        if (sourcebyte[ix] != buf[ix]) {
                            match = false;
                        }
                        ++ix;
                    }
                } else {
                    sourceOutofBytes = true;
                }
                if (match & !sourceOutofBytes) {
                    long start = System.currentTimeMillis();
                    boolean ok = true;
                    byte[] sourceBuff = new byte[1024];
                    byte[] targetBuff = new byte[1024];
                    int source_idx = 0;
                    int target_idx = 0;
                    boolean tCount = false;
                    do {
                        if ((source_idx = source.read(sourceBuff, 0, 1024)) == -1) {
                            sourceOutofBytes = true;
                            break;
                        }
                        target_idx = target.read(targetBuff, 0, source_idx);
                        if (target_idx == -1) break;
                        int read_idx = Math.min(source_idx, target_idx);
                        int i = 0;
                        do {
                            ++targetidx;
                            ++length;
                            ok = sourceBuff[i] == targetBuff[i];
                            ++i;
                            if (ok) continue;
                            b2[0] = targetBuff[i - 1];
                            if (target_idx == -1) continue;
                            target.unread(targetBuff, i, target_idx - i);
                        } while (i < read_idx && ok);
                        b2[0] = targetBuff[i - 1];
                    } while (ok && targetLength - targetidx > 0);
                    output.addCopy(offset, length);
                    if (targetLength - targetidx <= 15) {
                        buf[0] = b2[0];
                        int remaining = targetLength - targetidx;
                        int readStatus = target.read(buf, 1, remaining);
                        targetidx += remaining;
                        int ix = 0;
                        while (ix < remaining + 1) {
                            output.addData(buf[ix]);
                            ++ix;
                        }
                        done = true;
                        continue;
                    }
                    buf[0] = b2[0];
                    target.read(buf, 1, 15);
                    targetidx += 15;
                    alternativehashf = hashf = Checksum.queryChecksum(buf, 16);
                    continue;
                }
            }
            if (targetLength - targetidx > 0) {
                target.read(b2, 0, 1);
                ++targetidx;
                output.addData(buf[0]);
                alternativehashf = Checksum.incrementChecksum(alternativehashf, buf[0], b2[0]);
                int j = 0;
                while (j < 15) {
                    buf[j] = buf[j + 1];
                    ++j;
                }
                buf[15] = b2[0];
                hashf = Checksum.queryChecksum(buf, 16);
                continue;
            }
            int ix = 0;
            while (ix < 16) {
                output.addData(buf[ix]);
                ++ix;
            }
            done = true;
        }
    }

    public static void computeDelta(byte[] source, InputStream targetIS, int targetLength, DiffWriter output) throws IOException, DeltaException {
        Delta.computeDelta(new ByteArraySeekableSource(source), targetIS, targetLength, output);
    }

    public static void computeDelta(File sourceFile, File targetFile, DiffWriter output) throws IOException, DeltaException {
        int targetLength = (int)targetFile.length();
        RandomAccessFileSeekableSource source = new RandomAccessFileSeekableSource(new RandomAccessFile(sourceFile, "r"));
        FileInputStream targetIS = new FileInputStream(targetFile);
        try {
            try {
                Delta.computeDelta(source, (InputStream)targetIS, targetLength, output);
            }
            catch (IOException e) {
                throw e;
            }
            catch (DeltaException e) {
                throw e;
            }
        }
        finally {
            output.flush();
            source.close();
            ((InputStream)targetIS).close();
            output.close();
        }
    }

    public static void main(String[] argv) {
        Delta delta = new Delta();
        if (argv.length != 3) {
            System.err.println("usage Delta [-d] source target [output]");
            System.err.println("either -d or an output filename must be specified.");
            System.err.println("aborting..");
            return;
        }
        try {
            DiffWriter output = null;
            File sourceFile = null;
            File targetFile = null;
            if (argv[0].equals("-d")) {
                sourceFile = new File(argv[1]);
                targetFile = new File(argv[2]);
                output = new DebugDiffWriter();
            } else {
                sourceFile = new File(argv[0]);
                targetFile = new File(argv[1]);
                output = new GDiffWriter(new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(argv[2])))));
            }
            if (sourceFile.length() > Integer.MAX_VALUE || targetFile.length() > Integer.MAX_VALUE) {
                System.err.println("source or target is too large, max length is 2147483647");
                System.err.println("aborting..");
                return;
            }
            Delta.computeDelta(sourceFile, targetFile, output);
            output.flush();
            output.close();
        }
        catch (Exception e) {
            System.err.println("error while generating delta: " + e);
        }
    }
}

