/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player.advanced;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class PlaybackEvent {
    public static int STOPPED = 1;
    public static int STARTED = 2;
    private AdvancedPlayer source;
    private int frame;
    private int id;

    public PlaybackEvent(AdvancedPlayer advancedPlayer, int n, int n2) {
        this.id = n;
        this.source = advancedPlayer;
        this.frame = n2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int n) {
        this.id = n;
    }

    public int getFrame() {
        return this.frame;
    }

    public void setFrame(int n) {
        this.frame = n;
    }

    public AdvancedPlayer getSource() {
        return this.source;
    }

    public void setSource(AdvancedPlayer advancedPlayer) {
        this.source = advancedPlayer;
    }
}

