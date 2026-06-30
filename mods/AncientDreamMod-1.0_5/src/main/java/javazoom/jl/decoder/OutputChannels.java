/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

public class OutputChannels {
    public static final int BOTH_CHANNELS = 0;
    public static final int LEFT_CHANNEL = 1;
    public static final int RIGHT_CHANNEL = 2;
    public static final int DOWNMIX_CHANNELS = 3;
    public static final OutputChannels LEFT = new OutputChannels(1);
    public static final OutputChannels RIGHT = new OutputChannels(2);
    public static final OutputChannels BOTH = new OutputChannels(0);
    public static final OutputChannels DOWNMIX = new OutputChannels(3);
    private int outputChannels;

    public static OutputChannels fromInt(int n) {
        switch (n) {
            case 1: {
                return LEFT;
            }
            case 2: {
                return RIGHT;
            }
            case 0: {
                return BOTH;
            }
            case 3: {
                return DOWNMIX;
            }
        }
        throw new IllegalArgumentException("Invalid channel code: " + n);
    }

    private OutputChannels(int n) {
        this.outputChannels = n;
        if (n < 0 || n > 3) {
            throw new IllegalArgumentException("channels");
        }
    }

    public int getChannelsOutputCode() {
        return this.outputChannels;
    }

    public int getChannelCount() {
        int n = this.outputChannels == 0 ? 2 : 1;
        return n;
    }

    public boolean equals(Object object) {
        boolean bl = false;
        if (object instanceof OutputChannels) {
            OutputChannels outputChannels = (OutputChannels)object;
            bl = outputChannels.outputChannels == this.outputChannels;
        }
        return bl;
    }

    public int hashCode() {
        return this.outputChannels;
    }
}

