/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bhe
 *  bia
 *  bib
 *  bus
 *  org.lwjgl.opengl.GL11
 *  vg
 */
package net.optifine.render;

import org.lwjgl.opengl.GL11;

public class CloudRenderer {
    private bib mc;
    private boolean updated = false;
    private boolean renderFancy = false;
    int cloudTickCounter;
    private bhe cloudColor;
    float partialTicks;
    private boolean updateRenderFancy = false;
    private int updateCloudTickCounter = 0;
    private bhe updateCloudColor = new bhe(-1.0, -1.0, -1.0);
    private double updatePlayerX = 0.0;
    private double updatePlayerY = 0.0;
    private double updatePlayerZ = 0.0;
    private int glListClouds = -1;

    public CloudRenderer(bib mc) {
        this.mc = mc;
        this.glListClouds = bia.a((int)1);
    }

    public void prepareToRender(boolean renderFancy, int cloudTickCounter, float partialTicks, bhe cloudColor) {
        this.renderFancy = renderFancy;
        this.cloudTickCounter = cloudTickCounter;
        this.partialTicks = partialTicks;
        this.cloudColor = cloudColor;
    }

    public boolean shouldUpdateGlList() {
        boolean belowClouds;
        if (!this.updated) {
            return true;
        }
        if (this.renderFancy != this.updateRenderFancy) {
            return true;
        }
        if (this.cloudTickCounter >= this.updateCloudTickCounter + 20) {
            return true;
        }
        if (Math.abs(this.cloudColor.b - this.updateCloudColor.b) > 0.003) {
            return true;
        }
        if (Math.abs(this.cloudColor.c - this.updateCloudColor.c) > 0.003) {
            return true;
        }
        if (Math.abs(this.cloudColor.d - this.updateCloudColor.d) > 0.003) {
            return true;
        }
        vg rve = this.mc.aa();
        boolean belowCloudsPrev = this.updatePlayerY + (double)rve.by() < 128.0 + (double)(this.mc.t.ofCloudsHeight * 128.0f);
        boolean bl = belowClouds = rve.n + (double)rve.by() < 128.0 + (double)(this.mc.t.ofCloudsHeight * 128.0f);
        return belowClouds != belowCloudsPrev;
    }

    public void startUpdateGlList() {
        GL11.glNewList((int)this.glListClouds, (int)4864);
    }

    public void endUpdateGlList() {
        GL11.glEndList();
        this.updateRenderFancy = this.renderFancy;
        this.updateCloudTickCounter = this.cloudTickCounter;
        this.updateCloudColor = this.cloudColor;
        this.updatePlayerX = this.mc.aa().m;
        this.updatePlayerY = this.mc.aa().n;
        this.updatePlayerZ = this.mc.aa().o;
        this.updated = true;
        bus.I();
    }

    public void renderGlList() {
        vg entityliving = this.mc.aa();
        double exactPlayerX = entityliving.m + (entityliving.p - entityliving.m) * (double)this.partialTicks;
        double exactPlayerY = entityliving.n + (entityliving.q - entityliving.n) * (double)this.partialTicks;
        double exactPlayerZ = entityliving.o + (entityliving.r - entityliving.o) * (double)this.partialTicks;
        double dc = (float)(this.cloudTickCounter - this.updateCloudTickCounter) + this.partialTicks;
        float cdx = (float)(exactPlayerX - this.updatePlayerX + dc * 0.03);
        float cdy2 = (float)(exactPlayerY - this.updatePlayerY);
        float cdz = (float)(exactPlayerZ - this.updatePlayerZ);
        bus.G();
        if (this.renderFancy) {
            bus.c((float)(-cdx / 12.0f), (float)(-cdy2), (float)(-cdz / 12.0f));
        } else {
            bus.c((float)(-cdx), (float)(-cdy2), (float)(-cdz));
        }
        bus.s((int)this.glListClouds);
        bus.H();
        bus.I();
    }

    public void reset() {
        this.updated = false;
    }
}

