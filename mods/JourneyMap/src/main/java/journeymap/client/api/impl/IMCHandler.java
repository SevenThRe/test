/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.event.FMLInterModComms$IMCEvent
 *  net.minecraftforge.fml.common.event.FMLInterModComms$IMCMessage
 */
package journeymap.client.api.impl;

import journeymap.common.Journeymap;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class IMCHandler {
    public static void handle(FMLInterModComms.IMCEvent event) {
        try {
            for (FMLInterModComms.IMCMessage message : event.getMessages()) {
                String key = message.key.toLowerCase();
                key.getClass();
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error processing IMCEvent: " + t, t);
        }
    }
}

