/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.util.ByteArrayBuffer
 */
package goblinbob.mobends.core.animation.keyframe;

import goblinbob.mobends.core.animation.keyframe.Bone;
import goblinbob.mobends.core.animation.keyframe.Keyframe;
import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.util.ByteArrayBuffer;

public class BinaryAnimationLoader {
    private static final byte HAS_POSITION = 1;
    private static final byte HAS_ROTATION = 2;
    private static final byte HAS_SCALE = 4;

    public static KeyframeAnimation loadFromBinaryInputStream(InputStream stream) throws IOException {
        KeyframeAnimation animation = new KeyframeAnimation();
        animation.bones = new HashMap<String, Bone>();
        DataInputStream dataInputStream = new DataInputStream(stream);
        int version = dataInputStream.readInt();
        int amountOfKeyframes = dataInputStream.readInt();
        int amountOfBones = dataInputStream.readInt();
        for (int i2 = 0; i2 < amountOfBones; ++i2) {
            ByteArrayBuffer buffer = new ByteArrayBuffer(16);
            byte character = dataInputStream.readByte();
            while (character != 0) {
                buffer.append((int)character);
                character = dataInputStream.readByte();
            }
            String boneName = new String(buffer.toByteArray(), "UTF-8");
            Bone bone = new Bone();
            bone.keyframes = new ArrayList<Keyframe>();
            for (int j2 = 0; j2 < amountOfKeyframes; ++j2) {
                Keyframe frame = new Keyframe();
                byte flags = dataInputStream.readByte();
                frame.position = (flags & 1) != 0 ? new float[]{dataInputStream.readFloat(), dataInputStream.readFloat(), dataInputStream.readFloat()} : new float[]{0.0f, 0.0f, 0.0f};
                frame.rotation = (flags & 2) != 0 ? new float[]{dataInputStream.readFloat(), dataInputStream.readFloat(), dataInputStream.readFloat(), dataInputStream.readFloat()} : new float[]{0.0f, 0.0f, 0.0f, 1.0f};
                frame.scale = (flags & 4) != 0 ? new float[]{dataInputStream.readFloat(), dataInputStream.readFloat(), dataInputStream.readFloat()} : new float[]{1.0f, 1.0f, 1.0f};
                bone.keyframes.add(frame);
            }
            animation.bones.put(boneName, bone);
        }
        return animation;
    }
}

