/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.DecoderErrors;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Equalizer;
import javazoom.jl.decoder.FrameDecoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.LayerIDecoder;
import javazoom.jl.decoder.LayerIIDecoder;
import javazoom.jl.decoder.LayerIIIDecoder;
import javazoom.jl.decoder.Obuffer;
import javazoom.jl.decoder.OutputChannels;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.decoder.SynthesisFilter;

public class Decoder
implements DecoderErrors {
    private static final Params DEFAULT_PARAMS = new Params();
    private Obuffer output;
    private SynthesisFilter filter1;
    private SynthesisFilter filter2;
    private LayerIIIDecoder l3decoder;
    private LayerIIDecoder l2decoder;
    private LayerIDecoder l1decoder;
    private int outputFrequency;
    private int outputChannels;
    private Equalizer equalizer = new Equalizer();
    private Params params;
    private boolean initialized;

    public Decoder() {
        this(null);
    }

    public Decoder(Params params) {
        if (params == null) {
            params = DEFAULT_PARAMS;
        }
        this.params = params;
        Equalizer equalizer = this.params.getInitialEqualizerSettings();
        if (equalizer != null) {
            this.equalizer.setFrom(equalizer);
        }
    }

    public static Params getDefaultParams() {
        return (Params)DEFAULT_PARAMS.clone();
    }

    public void setEqualizer(Equalizer equalizer) {
        if (equalizer == null) {
            equalizer = Equalizer.PASS_THRU_EQ;
        }
        this.equalizer.setFrom(equalizer);
        float[] fArray = this.equalizer.getBandFactors();
        if (this.filter1 != null) {
            this.filter1.setEQ(fArray);
        }
        if (this.filter2 != null) {
            this.filter2.setEQ(fArray);
        }
    }

    public Obuffer decodeFrame(Header header, Bitstream bitstream) throws DecoderException {
        if (!this.initialized) {
            this.initialize(header);
        }
        int n = header.layer();
        this.output.clear_buffer();
        FrameDecoder frameDecoder = this.retrieveDecoder(header, bitstream, n);
        frameDecoder.decodeFrame();
        this.output.write_buffer(1);
        return this.output;
    }

    public void setOutputBuffer(Obuffer obuffer) {
        this.output = obuffer;
    }

    public int getOutputFrequency() {
        return this.outputFrequency;
    }

    public int getOutputChannels() {
        return this.outputChannels;
    }

    public int getOutputBlockSize() {
        return 2304;
    }

    protected DecoderException newDecoderException(int n) {
        return new DecoderException(n, null);
    }

    protected DecoderException newDecoderException(int n, Throwable throwable) {
        return new DecoderException(n, throwable);
    }

    protected FrameDecoder retrieveDecoder(Header header, Bitstream bitstream, int n) throws DecoderException {
        FrameDecoder frameDecoder = null;
        switch (n) {
            case 3: {
                if (this.l3decoder == null) {
                    this.l3decoder = new LayerIIIDecoder(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l3decoder;
                break;
            }
            case 2: {
                if (this.l2decoder == null) {
                    this.l2decoder = new LayerIIDecoder();
                    this.l2decoder.create(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l2decoder;
                break;
            }
            case 1: {
                if (this.l1decoder == null) {
                    this.l1decoder = new LayerIDecoder();
                    this.l1decoder.create(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l1decoder;
            }
        }
        if (frameDecoder == null) {
            throw this.newDecoderException(513, null);
        }
        return frameDecoder;
    }

    private void initialize(Header header) throws DecoderException {
        int n;
        float f = 32700.0f;
        int n2 = header.mode();
        int n3 = header.layer();
        int n4 = n = n2 == 3 ? 1 : 2;
        if (this.output == null) {
            this.output = new SampleBuffer(header.frequency(), n);
        }
        float[] fArray = this.equalizer.getBandFactors();
        this.filter1 = new SynthesisFilter(0, f, fArray);
        if (n == 2) {
            this.filter2 = new SynthesisFilter(1, f, fArray);
        }
        this.outputChannels = n;
        this.outputFrequency = header.frequency();
        this.initialized = true;
    }

    public static class Params
    implements Cloneable {
        private OutputChannels outputChannels = OutputChannels.BOTH;
        private Equalizer equalizer = new Equalizer();

        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException cloneNotSupportedException) {
                throw new InternalError(this + ": " + cloneNotSupportedException);
            }
        }

        public void setOutputChannels(OutputChannels outputChannels) {
            if (outputChannels == null) {
                throw new NullPointerException("out");
            }
            this.outputChannels = outputChannels;
        }

        public OutputChannels getOutputChannels() {
            return this.outputChannels;
        }

        public Equalizer getInitialEqualizerSettings() {
            return this.equalizer;
        }
    }
}

