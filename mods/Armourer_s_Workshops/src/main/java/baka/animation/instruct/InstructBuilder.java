/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.EnumHandSide
 */
package baka.animation.instruct;

import baka.animation.instruct.DamageInstruct;
import baka.animation.instruct.DelayInstruct;
import baka.animation.instruct.FinishInstruct;
import baka.animation.instruct.InstructBase;
import baka.animation.instruct.MoveInstruct;
import baka.animation.instruct.OrientInstruct;
import baka.animation.instruct.RotateInstruct;
import baka.animation.instruct.ScaleInstruct;
import baka.animation.instruct.SkillInstruct;
import baka.animation.instruct.SoundInstruct;
import baka.animation.instruct.StandInstruct;
import baka.animation.instruct.SwordTrailInstruct;
import baka.data.RotateEntry;
import baka.data.ScaleEntry;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHandSide;

public abstract class InstructBuilder {
    public static InstructBase from(ByteBuf buf) {
        byte type = buf.readByte();
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        switch (type) {
            case 0: {
                return new DamageInstruct(packetBuffer.readString(4096), packetBuffer.readFloat(), packetBuffer.readFloat());
            }
            case 1: {
                return new DelayInstruct(buf.readLong());
            }
            case 2: {
                return new FinishInstruct(packetBuffer.readString(4096));
            }
            case 3: {
                float x2 = buf.readFloat();
                float y2 = buf.readFloat();
                float z2 = buf.readFloat();
                return new MoveInstruct(x2, y2, z2);
            }
            case 4: {
                PacketBuffer buffer = new PacketBuffer(buf);
                int size = buffer.readInt();
                HashMap<String, RotateEntry> rotateEntryMap = new HashMap<String, RotateEntry>(size);
                for (int i2 = 0; i2 < size; ++i2) {
                    String part = buffer.readString(4096);
                    float x3 = buffer.readFloat();
                    float y3 = buffer.readFloat();
                    float z3 = buffer.readFloat();
                    float v2 = buffer.readFloat();
                    RotateEntry entry = new RotateEntry(x3, y3, z3, v2);
                    rotateEntryMap.put(part, entry);
                }
                return new RotateInstruct(rotateEntryMap);
            }
            case 5: {
                boolean enable = buf.readBoolean();
                boolean isLeft = buf.readBoolean();
                float length = buf.readFloat();
                float red = buf.readFloat();
                float green = buf.readFloat();
                float blue = buf.readFloat();
                return new SwordTrailInstruct(enable, isLeft ? EnumHandSide.LEFT : EnumHandSide.RIGHT, length, red, green, blue);
            }
            case 6: {
                PacketBuffer buffer = new PacketBuffer(buf);
                int size = buffer.readInt();
                HashMap<String, ScaleEntry> scaleEntryMap = new HashMap<String, ScaleEntry>(size);
                for (int i3 = 0; i3 < size; ++i3) {
                    String part = buffer.readString(4096);
                    float x4 = buffer.readFloat();
                    float y4 = buffer.readFloat();
                    float z4 = buffer.readFloat();
                    float v3 = buffer.readFloat();
                    ScaleEntry entry = new ScaleEntry(x4, y4, z4, v3);
                    scaleEntryMap.put(part, entry);
                }
                return new ScaleInstruct(scaleEntryMap);
            }
            case 7: {
                return new StandInstruct();
            }
            case 8: {
                PacketBuffer buffer = new PacketBuffer(buf);
                int size = buffer.readInt();
                HashMap<String, RotateEntry> rotateEntryMap = new HashMap<String, RotateEntry>(size);
                for (int i4 = 0; i4 < size; ++i4) {
                    String part = buffer.readString(4096);
                    float x5 = buffer.readFloat();
                    float y5 = buffer.readFloat();
                    float z5 = buffer.readFloat();
                    float v4 = buffer.readFloat();
                    RotateEntry entry = new RotateEntry(x5, y5, z5, v4);
                    rotateEntryMap.put(part, entry);
                }
                return new OrientInstruct(rotateEntryMap);
            }
            case 20: {
                PacketBuffer buffer = new PacketBuffer(buf);
                return new SoundInstruct(buffer.readString(4096), buffer.readString(4096));
            }
            case 21: {
                PacketBuffer buffer = new PacketBuffer(buf);
                return new SkillInstruct(buffer.readString(4096), buffer.readString(4096));
            }
        }
        return null;
    }
}

