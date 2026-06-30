/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package eos.moe.dragoncore.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class EventLoader
extends Event {
    public EventLoader() {
        EventLoader a2;
    }

    public static class ServerFileEvent
    extends Event {
        private String fileName;
        private byte[] bytes;

        public ServerFileEvent(String a2, byte[] a3) {
            ServerFileEvent a4;
            a4.fileName = a2;
            a4.bytes = a3;
        }

        public String getFileName() {
            ServerFileEvent a2;
            return a2.fileName;
        }

        public byte[] getBytes() {
            ServerFileEvent a2;
            return a2.bytes;
        }
    }
}

