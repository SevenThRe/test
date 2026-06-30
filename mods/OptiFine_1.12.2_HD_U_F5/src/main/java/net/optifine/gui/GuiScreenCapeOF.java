/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bja
 *  blk
 *  cey
 *  com.mojang.authlib.exceptions.InvalidCredentialsException
 */
package net.optifine.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import net.optifine.Lang;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;

public class GuiScreenCapeOF
extends GuiScreenOF {
    private final blk parentScreen;
    private String title;
    private String message;
    private long messageHideTimeMs;
    private String linkUrl;
    private GuiButtonOF buttonCopyLink;

    public GuiScreenCapeOF(blk parentScreenIn) {
        this.parentScreen = parentScreenIn;
    }

    public void b() {
        int i = 0;
        this.title = cey.a((String)"of.options.capeOF.title", (Object[])new Object[0]);
        this.n.add(new GuiButtonOF(210, this.l / 2 - 155, this.m / 6 + 24 * ((i += 2) >> 1), 150, 20, cey.a((String)"of.options.capeOF.openEditor", (Object[])new Object[0])));
        this.n.add(new GuiButtonOF(220, this.l / 2 - 155 + 160, this.m / 6 + 24 * (i >> 1), 150, 20, cey.a((String)"of.options.capeOF.reloadCape", (Object[])new Object[0])));
        this.buttonCopyLink = new GuiButtonOF(230, this.l / 2 - 100, this.m / 6 + 24 * ((i += 6) >> 1), 200, 20, cey.a((String)"of.options.capeOF.copyEditorLink", (Object[])new Object[0]));
        this.buttonCopyLink.m = this.linkUrl != null;
        this.b(this.buttonCopyLink);
        this.n.add(new GuiButtonOF(200, this.l / 2 - 100, this.m / 6 + 24 * ((i += 4) >> 1), cey.a((String)"gui.done", (Object[])new Object[0])));
    }

    protected void a(bja button) {
        if (button.l) {
            if (button.k == 200) {
                this.j.a(this.parentScreen);
            }
            if (button.k == 210) {
                try {
                    String userName = this.j.K().e().getName();
                    String userId = this.j.K().e().getId().toString().replace("-", "");
                    String accessToken = this.j.K().d();
                    Random r1 = new Random();
                    Random r2 = new Random(System.identityHashCode(new Object()));
                    BigInteger random1Bi = new BigInteger(128, r1);
                    BigInteger random2Bi = new BigInteger(128, r2);
                    BigInteger serverBi = random1Bi.xor(random2Bi);
                    String serverId = serverBi.toString(16);
                    this.j.X().joinServer(this.j.K().e(), accessToken, serverId);
                    String urlStr = "https://optifine.net/capeChange?u=" + userId + "&n=" + userName + "&s=" + serverId;
                    boolean opened = Config.openWebLink(new URI(urlStr));
                    if (opened) {
                        this.showMessage(Lang.get("of.message.capeOF.openEditor"), 10000L);
                    } else {
                        this.showMessage(Lang.get("of.message.capeOF.openEditorError"), 10000L);
                        this.setLinkUrl(urlStr);
                    }
                }
                catch (InvalidCredentialsException e) {
                    Config.showGuiMessage(cey.a((String)"of.message.capeOF.error1", (Object[])new Object[0]), cey.a((String)"of.message.capeOF.error2", (Object[])new Object[]{e.getMessage()}));
                    Config.warn("Mojang authentication failed");
                    Config.warn(((Object)((Object)e)).getClass().getName() + ": " + e.getMessage());
                }
                catch (Exception e) {
                    Config.warn("Error opening OptiFine cape link");
                    Config.warn(e.getClass().getName() + ": " + e.getMessage());
                }
            }
            if (button.k == 220) {
                this.showMessage(Lang.get("of.message.capeOF.reloadCape"), 15000L);
                if (this.j.h != null) {
                    long delayMs = 15000L;
                    long reloadTimeMs = System.currentTimeMillis() + delayMs;
                    this.j.h.setReloadCapeTimeMs(reloadTimeMs);
                }
            }
            if (button.k == 230 && this.linkUrl != null) {
                GuiScreenCapeOF.e((String)this.linkUrl);
            }
        }
    }

    private void showMessage(String msg, long timeMs) {
        this.message = msg;
        this.messageHideTimeMs = System.currentTimeMillis() + timeMs;
        this.setLinkUrl(null);
    }

    public void a(int mouseX, int mouseY, float partialTicks) {
        this.c();
        this.a(this.q, this.title, this.l / 2, 20, 0xFFFFFF);
        if (this.message != null) {
            this.a(this.q, this.message, this.l / 2, this.m / 6 + 60, 0xFFFFFF);
            if (System.currentTimeMillis() > this.messageHideTimeMs) {
                this.message = null;
                this.setLinkUrl(null);
            }
        }
        super.a(mouseX, mouseY, partialTicks);
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        this.buttonCopyLink.m = linkUrl != null;
    }
}

