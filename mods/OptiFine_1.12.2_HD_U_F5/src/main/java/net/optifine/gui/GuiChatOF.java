/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bkn
 *  bls
 */
package net.optifine.gui;

import net.optifine.shaders.Shaders;

public class GuiChatOF
extends bkn {
    private static final String CMD_RELOAD_SHADERS = "/reloadShaders";
    private static final String CMD_RELOAD_CHUNKS = "/reloadChunks";

    public GuiChatOF(bkn guiChat) {
        super(bls.getGuiChatText((bkn)guiChat));
    }

    public void f(String msg) {
        if (this.checkCustomCommand(msg)) {
            this.j.q.d().a(msg);
            return;
        }
        super.f(msg);
    }

    private boolean checkCustomCommand(String msg) {
        if (msg == null) {
            return false;
        }
        if ((msg = msg.trim()).equals(CMD_RELOAD_SHADERS)) {
            if (Config.isShaders()) {
                Shaders.uninit();
                Shaders.loadShaderPack();
            }
            return true;
        }
        if (msg.equals(CMD_RELOAD_CHUNKS)) {
            this.j.g.a();
            return true;
        }
        return false;
    }

    public void a(String ... newCompletions) {
        String prefix = bls.getGuiChatText((bkn)this);
        if (CMD_RELOAD_SHADERS.startsWith(prefix)) {
            newCompletions = (String[])Config.addObjectToArray(newCompletions, CMD_RELOAD_SHADERS);
        }
        if (CMD_RELOAD_CHUNKS.startsWith(prefix)) {
            newCompletions = (String[])Config.addObjectToArray(newCompletions, CMD_RELOAD_CHUNKS);
        }
        super.a(newCompletions);
    }
}

