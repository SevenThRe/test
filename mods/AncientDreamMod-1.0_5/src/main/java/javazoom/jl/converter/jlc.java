/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import java.io.PrintWriter;
import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.Crc16;
import javazoom.jl.decoder.JavaLayerException;

public class jlc {
    public static void main(String[] stringArray) {
        long l = System.currentTimeMillis();
        int n = stringArray.length + 1;
        String[] stringArray2 = new String[n];
        stringArray2[0] = "jlc";
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray2[i + 1] = stringArray[i];
        }
        jlcArgs jlcArgs2 = new jlcArgs();
        if (!jlcArgs2.processArgs(stringArray2)) {
            System.exit(1);
        }
        Converter converter = new Converter();
        int n2 = jlcArgs2.verbose_mode ? jlcArgs2.verbose_level : 0;
        Converter.PrintWriterProgressListener printWriterProgressListener = new Converter.PrintWriterProgressListener(new PrintWriter(System.out, true), n2);
        try {
            converter.convert(jlcArgs2.filename, jlcArgs2.output_filename, printWriterProgressListener);
        }
        catch (JavaLayerException javaLayerException) {
            System.err.println("Convertion failure: " + javaLayerException);
        }
        System.exit(0);
    }

    static class jlcArgs {
        public int which_c = 0;
        public int output_mode;
        public boolean use_own_scalefactor = false;
        public float scalefactor = 32768.0f;
        public String output_filename;
        public String filename;
        public boolean verbose_mode = false;
        public int verbose_level = 3;

        public boolean processArgs(String[] stringArray) {
            this.filename = null;
            Crc16[] crc16Array = new Crc16[1];
            int n = stringArray.length;
            this.verbose_mode = false;
            this.output_mode = 0;
            this.output_filename = "";
            if (n < 2 || stringArray[1].equals("-h")) {
                return this.Usage();
            }
            for (int i = 1; i < n; ++i) {
                if (stringArray[i].charAt(0) == '-') {
                    if (stringArray[i].startsWith("-v")) {
                        this.verbose_mode = true;
                        if (stringArray[i].length() > 2) {
                            try {
                                String string = stringArray[i].substring(2);
                                this.verbose_level = Integer.parseInt(string);
                            }
                            catch (NumberFormatException numberFormatException) {
                                System.err.println("Invalid verbose level. Using default.");
                            }
                        }
                        System.out.println("Verbose Activated (level " + this.verbose_level + ")");
                        continue;
                    }
                    if (stringArray[i].equals("-p")) {
                        if (++i == n) {
                            System.out.println("Please specify an output filename after the -p option!");
                            System.exit(1);
                        }
                        this.output_filename = stringArray[i];
                        continue;
                    }
                    return this.Usage();
                }
                this.filename = stringArray[i];
                System.out.println("FileName = " + stringArray[i]);
                if (this.filename != null) continue;
                return this.Usage();
            }
            if (this.filename == null) {
                return this.Usage();
            }
            return true;
        }

        public boolean Usage() {
            System.out.println("JavaLayer Converter :");
            System.out.println("  -v[x]         verbose mode. ");
            System.out.println("                default = 2");
            System.out.println("  -p name    output as a PCM wave file");
            System.out.println("");
            System.out.println("  More info on http://www.javazoom.net");
            return false;
        }
    }
}

