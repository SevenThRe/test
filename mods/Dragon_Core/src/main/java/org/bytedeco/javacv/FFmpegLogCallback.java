/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import org.bytedeco.ffmpeg.avutil.LogCallback;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.tools.Logger;

public class FFmpegLogCallback
extends LogCallback {
    private static final Logger logger = Logger.create(FFmpegLogCallback.class);
    static final FFmpegLogCallback instance = (FFmpegLogCallback)new FFmpegLogCallback().retainReference();

    public static FFmpegLogCallback getInstance() {
        return instance;
    }

    public static void set() {
        avutil.setLogCallback(FFmpegLogCallback.getInstance());
    }

    public static int getLevel() {
        return avutil.av_log_get_level();
    }

    public static void setLevel(int level) {
        avutil.av_log_set_level(level);
    }

    @Override
    public void call(int level, BytePointer msg) {
        switch (level) {
            case 0: 
            case 8: 
            case 16: {
                logger.error(msg.getString());
                break;
            }
            case 24: {
                logger.warn(msg.getString());
                break;
            }
            case 32: {
                logger.info(msg.getString());
                break;
            }
            case 40: 
            case 48: 
            case 56: {
                logger.debug(msg.getString());
                break;
            }
            default: {
                assert (false);
                break;
            }
        }
    }
}

