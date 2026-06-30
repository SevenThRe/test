/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player.advanced;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class jlap {
    public static void main(String[] stringArray) {
        jlap jlap2 = new jlap();
        if (stringArray.length != 1) {
            jlap2.showUsage();
            System.exit(0);
        } else {
            try {
                jlap2.play(stringArray[0]);
            }
            catch (Exception exception) {
                System.err.println(exception.getMessage());
                System.exit(0);
            }
        }
    }

    public void play(String string) throws JavaLayerException, IOException {
        InfoListener infoListener = new InfoListener();
        jlap.playMp3(new File(string), infoListener);
    }

    public void showUsage() {
        System.out.println("Usage: jla <filename>");
        System.out.println("");
        System.out.println(" e.g. : java javazoom.jl.player.advanced.jlap localfile.mp3");
    }

    public static AdvancedPlayer playMp3(File file, PlaybackListener playbackListener) throws IOException, JavaLayerException {
        return jlap.playMp3(file, 0, Integer.MAX_VALUE, playbackListener);
    }

    public static AdvancedPlayer playMp3(File file, int n, int n2, PlaybackListener playbackListener) throws IOException, JavaLayerException {
        return jlap.playMp3(new BufferedInputStream(new FileInputStream(file)), n, n2, playbackListener);
    }

    public static AdvancedPlayer playMp3(InputStream inputStream, final int n, final int n2, PlaybackListener playbackListener) throws JavaLayerException {
        final AdvancedPlayer advancedPlayer = new AdvancedPlayer(inputStream);
        advancedPlayer.setPlayBackListener(playbackListener);
        new Thread(){

            public void run() {
                try {
                    advancedPlayer.play(n, n2);
                }
                catch (Exception exception) {
                    throw new RuntimeException(exception.getMessage());
                }
            }
        }.start();
        return advancedPlayer;
    }

    public class InfoListener
    extends PlaybackListener {
        public void playbackStarted(PlaybackEvent playbackEvent) {
            System.out.println("Play started from frame " + playbackEvent.getFrame());
        }

        public void playbackFinished(PlaybackEvent playbackEvent) {
            System.out.println("Play completed at frame " + playbackEvent.getFrame());
            System.exit(0);
        }
    }
}

