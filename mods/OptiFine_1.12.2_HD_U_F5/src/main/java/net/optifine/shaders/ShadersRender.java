/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amm
 *  awg
 *  bhz
 *  bib
 *  buk
 *  buq
 *  bus
 *  buu
 *  buy
 *  bve
 *  bxy
 *  bya
 *  byb
 *  cdp
 *  cdy
 *  cii
 *  fa
 *  nf
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 *  vg
 */
package net.optifine.shaders;

import java.nio.IntBuffer;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.ClippingHelperShadow;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ShadersRender {
    private static final nf END_PORTAL_TEXTURE = new nf("textures/entity/end_portal.png");

    public static void setFrustrumPosition(bxy frustum, double x, double y, double z) {
        frustum.a(x, y, z);
    }

    public static void setupTerrain(buy renderGlobal, vg viewEntity, double partialTicks, bxy camera, int frameCount, boolean playerSpectator) {
        renderGlobal.a(viewEntity, partialTicks, camera, frameCount, playerSpectator);
    }

    public static void beginTerrainSolid() {
        if (Shaders.isRenderingWorld) {
            Shaders.fogEnabled = true;
            Shaders.useProgram(Shaders.ProgramTerrain);
        }
    }

    public static void beginTerrainCutoutMipped() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTerrain);
        }
    }

    public static void beginTerrainCutout() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTerrain);
        }
    }

    public static void endTerrain() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTexturedLit);
        }
    }

    public static void beginTranslucent() {
        if (Shaders.isRenderingWorld) {
            if (Shaders.usedDepthBuffers >= 2) {
                bus.g((int)33995);
                Shaders.checkGLError("pre copy depth");
                GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)Shaders.renderWidth, (int)Shaders.renderHeight);
                Shaders.checkGLError("copy depth");
                bus.g((int)33984);
            }
            Shaders.useProgram(Shaders.ProgramWater);
        }
    }

    public static void endTranslucent() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramTexturedLit);
        }
    }

    public static void renderHand0(buq er, float par1, int par2) {
        if (!Shaders.isShadowPass) {
            boolean blockTranslucentMain = Shaders.isItemToRenderMainTranslucent();
            boolean blockTranslucentOff = Shaders.isItemToRenderOffTranslucent();
            if (!blockTranslucentMain || !blockTranslucentOff) {
                Shaders.readCenterDepth();
                Shaders.beginHand(false);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                Shaders.setSkipRenderHands(blockTranslucentMain, blockTranslucentOff);
                er.renderHand(par1, par2, true, false, false);
                Shaders.endHand();
                Shaders.setHandsRendered(!blockTranslucentMain, !blockTranslucentOff);
                Shaders.setSkipRenderHands(false, false);
            }
        }
    }

    public static void renderHand1(buq er, float par1, int par2) {
        if (!Shaders.isShadowPass && !Shaders.isBothHandsRendered()) {
            Shaders.readCenterDepth();
            bus.m();
            Shaders.beginHand(true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Shaders.setSkipRenderHands(Shaders.isHandRenderedMain(), Shaders.isHandRenderedOff());
            er.renderHand(par1, par2, true, false, true);
            Shaders.endHand();
            Shaders.setHandsRendered(true, true);
            Shaders.setSkipRenderHands(false, false);
        }
    }

    public static void renderItemFP(buu itemRenderer, float par1, boolean renderTranslucent) {
        Shaders.setRenderingFirstPersonHand(true);
        bus.a((boolean)true);
        if (renderTranslucent) {
            bus.c((int)519);
            GL11.glPushMatrix();
            IntBuffer drawBuffers = Shaders.activeDrawBuffers;
            Shaders.setDrawBuffers(Shaders.drawBuffersNone);
            Shaders.renderItemKeepDepthMask = true;
            itemRenderer.a(par1);
            Shaders.renderItemKeepDepthMask = false;
            Shaders.setDrawBuffers(drawBuffers);
            GL11.glPopMatrix();
        }
        bus.c((int)515);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        itemRenderer.a(par1);
        Shaders.setRenderingFirstPersonHand(false);
    }

    public static void renderFPOverlay(buq er, float par1, int par2) {
        if (!Shaders.isShadowPass) {
            Shaders.beginFPOverlay();
            er.renderHand(par1, par2, false, true, false);
            Shaders.endFPOverlay();
        }
    }

    public static void beginBlockDamage() {
        if (Shaders.isRenderingWorld) {
            Shaders.useProgram(Shaders.ProgramDamagedBlock);
            if (Shaders.ProgramDamagedBlock.getId() == Shaders.ProgramTerrain.getId()) {
                Shaders.setDrawBuffers(Shaders.drawBuffersColorAtt0);
                bus.a((boolean)false);
            }
        }
    }

    public static void endBlockDamage() {
        if (Shaders.isRenderingWorld) {
            bus.a((boolean)true);
            Shaders.useProgram(Shaders.ProgramTexturedLit);
        }
    }

    public static void renderShadowMap(buq entityRenderer, int pass, float partialTicks, long finishTimeNano) {
        if (Shaders.usedShadowDepthBuffers > 0 && --Shaders.shadowPassCounter <= 0) {
            bib mc = bib.z();
            mc.B.c("shadow pass");
            buy renderGlobal = mc.g;
            Shaders.isShadowPass = true;
            Shaders.shadowPassCounter = Shaders.shadowPassInterval;
            Shaders.preShadowPassThirdPersonView = mc.t.aw;
            mc.t.aw = 1;
            Shaders.checkGLError("pre shadow");
            GL11.glMatrixMode((int)5889);
            GL11.glPushMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glPushMatrix();
            mc.B.c("shadow clear");
            EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)Shaders.sfb);
            Shaders.checkGLError("shadow bind sfb");
            mc.B.c("shadow camera");
            entityRenderer.a(partialTicks, 2);
            Shaders.setCameraShadow(partialTicks);
            Shaders.checkGLError("shadow camera");
            Shaders.useProgram(Shaders.ProgramShadow);
            GL20.glDrawBuffers((IntBuffer)Shaders.sfbDrawBuffers);
            Shaders.checkGLError("shadow drawbuffers");
            GL11.glReadBuffer((int)0);
            Shaders.checkGLError("shadow readbuffer");
            EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36096, (int)3553, (int)Shaders.sfbDepthTextures.get(0), (int)0);
            if (Shaders.usedShadowColorBuffers != 0) {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)36160, (int)36064, (int)3553, (int)Shaders.sfbColorTextures.get(0), (int)0);
            }
            Shaders.checkFramebufferStatus("shadow fb");
            GL11.glClearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glClear((int)(Shaders.usedShadowColorBuffers != 0 ? 16640 : 256));
            Shaders.checkGLError("shadow clear");
            mc.B.c("shadow frustum");
            byb clippingHelper = ClippingHelperShadow.getInstance();
            mc.B.c("shadow culling");
            bya frustum = new bya(clippingHelper);
            vg viewEntity = mc.aa();
            double viewPosX = viewEntity.M + (viewEntity.p - viewEntity.M) * (double)partialTicks;
            double viewPosY = viewEntity.N + (viewEntity.q - viewEntity.N) * (double)partialTicks;
            double viewPosZ = viewEntity.O + (viewEntity.r - viewEntity.O) * (double)partialTicks;
            frustum.a(viewPosX, viewPosY, viewPosZ);
            bus.j((int)7425);
            bus.k();
            bus.c((int)515);
            bus.a((boolean)true);
            bus.a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            bus.r();
            mc.B.c("shadow prepareterrain");
            mc.N().a(cdp.g);
            mc.B.c("shadow setupterrain");
            int frameCount = 0;
            frameCount = entityRenderer.aj;
            entityRenderer.aj = frameCount + 1;
            renderGlobal.a(viewEntity, (double)partialTicks, (bxy)frustum, frameCount, mc.h.y());
            mc.B.c("shadow updatechunks");
            mc.B.c("shadow terrain");
            bus.n((int)5888);
            bus.G();
            bus.d();
            renderGlobal.a(amm.a, (double)partialTicks, 2, viewEntity);
            Shaders.checkGLError("shadow terrain solid");
            bus.e();
            renderGlobal.a(amm.b, (double)partialTicks, 2, viewEntity);
            Shaders.checkGLError("shadow terrain cutoutmipped");
            mc.N().b(cdp.g).b(false, false);
            renderGlobal.a(amm.c, (double)partialTicks, 2, viewEntity);
            Shaders.checkGLError("shadow terrain cutout");
            mc.N().b(cdp.g).a();
            bus.j((int)7424);
            bus.a((int)516, (float)0.1f);
            bus.n((int)5888);
            bus.H();
            bus.G();
            mc.B.c("shadow entities");
            if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
                Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, 0);
            }
            renderGlobal.a(viewEntity, (bxy)frustum, partialTicks);
            Shaders.checkGLError("shadow entities");
            bus.n((int)5888);
            bus.H();
            bus.a((boolean)true);
            bus.l();
            bus.q();
            bus.a((int)770, (int)771, (int)1, (int)0);
            bus.a((int)516, (float)0.1f);
            if (Shaders.usedShadowDepthBuffers >= 2) {
                bus.g((int)33989);
                Shaders.checkGLError("pre copy shadow depth");
                GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)Shaders.shadowMapWidth, (int)Shaders.shadowMapHeight);
                Shaders.checkGLError("copy shadow depth");
                bus.g((int)33984);
            }
            bus.l();
            bus.a((boolean)true);
            mc.N().a(cdp.g);
            bus.j((int)7425);
            Shaders.checkGLError("shadow pre-translucent");
            GL20.glDrawBuffers((IntBuffer)Shaders.sfbDrawBuffers);
            Shaders.checkGLError("shadow drawbuffers pre-translucent");
            Shaders.checkFramebufferStatus("shadow pre-translucent");
            if (Shaders.isRenderShadowTranslucent()) {
                mc.B.c("shadow translucent");
                renderGlobal.a(amm.d, (double)partialTicks, 2, viewEntity);
                Shaders.checkGLError("shadow translucent");
            }
            if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
                bhz.b();
                Reflector.call(Reflector.ForgeHooksClient_setRenderPass, 1);
                renderGlobal.a(viewEntity, (bxy)frustum, partialTicks);
                Reflector.call(Reflector.ForgeHooksClient_setRenderPass, -1);
                bhz.a();
                Shaders.checkGLError("shadow entities 1");
            }
            bus.j((int)7424);
            bus.a((boolean)true);
            bus.q();
            bus.l();
            GL11.glFlush();
            Shaders.checkGLError("shadow flush");
            Shaders.isShadowPass = false;
            mc.t.aw = Shaders.preShadowPassThirdPersonView;
            mc.B.c("shadow postprocess");
            if (Shaders.hasGlGenMipmap) {
                if (Shaders.usedShadowDepthBuffers >= 1) {
                    if (Shaders.shadowMipmapEnabled[0]) {
                        bus.g((int)33988);
                        bus.i((int)Shaders.sfbDepthTextures.get(0));
                        GL30.glGenerateMipmap((int)3553);
                        GL11.glTexParameteri((int)3553, (int)10241, (int)(Shaders.shadowFilterNearest[0] ? 9984 : 9987));
                    }
                    if (Shaders.usedShadowDepthBuffers >= 2 && Shaders.shadowMipmapEnabled[1]) {
                        bus.g((int)33989);
                        bus.i((int)Shaders.sfbDepthTextures.get(1));
                        GL30.glGenerateMipmap((int)3553);
                        GL11.glTexParameteri((int)3553, (int)10241, (int)(Shaders.shadowFilterNearest[1] ? 9984 : 9987));
                    }
                    bus.g((int)33984);
                }
                if (Shaders.usedShadowColorBuffers >= 1) {
                    if (Shaders.shadowColorMipmapEnabled[0]) {
                        bus.g((int)33997);
                        bus.i((int)Shaders.sfbColorTextures.get(0));
                        GL30.glGenerateMipmap((int)3553);
                        GL11.glTexParameteri((int)3553, (int)10241, (int)(Shaders.shadowColorFilterNearest[0] ? 9984 : 9987));
                    }
                    if (Shaders.usedShadowColorBuffers >= 2 && Shaders.shadowColorMipmapEnabled[1]) {
                        bus.g((int)33998);
                        bus.i((int)Shaders.sfbColorTextures.get(1));
                        GL30.glGenerateMipmap((int)3553);
                        GL11.glTexParameteri((int)3553, (int)10241, (int)(Shaders.shadowColorFilterNearest[1] ? 9984 : 9987));
                    }
                    bus.g((int)33984);
                }
            }
            Shaders.checkGLError("shadow postprocess");
            EXTFramebufferObject.glBindFramebufferEXT((int)36160, (int)Shaders.dfb);
            GL11.glViewport((int)0, (int)0, (int)Shaders.renderWidth, (int)Shaders.renderHeight);
            Shaders.activeDrawBuffers = null;
            mc.N().a(cdp.g);
            Shaders.useProgram(Shaders.ProgramTerrain);
            GL11.glMatrixMode((int)5888);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5889);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            Shaders.checkGLError("shadow end");
        }
    }

    public static void preRenderChunkLayer(amm blockLayerIn) {
        if (Shaders.isRenderBackFace(blockLayerIn)) {
            bus.r();
        }
        if (cii.f()) {
            GL11.glEnableClientState((int)32885);
            GL20.glEnableVertexAttribArray((int)Shaders.midTexCoordAttrib);
            GL20.glEnableVertexAttribArray((int)Shaders.tangentAttrib);
            GL20.glEnableVertexAttribArray((int)Shaders.entityAttrib);
        }
    }

    public static void postRenderChunkLayer(amm blockLayerIn) {
        if (cii.f()) {
            GL11.glDisableClientState((int)32885);
            GL20.glDisableVertexAttribArray((int)Shaders.midTexCoordAttrib);
            GL20.glDisableVertexAttribArray((int)Shaders.tangentAttrib);
            GL20.glDisableVertexAttribArray((int)Shaders.entityAttrib);
        }
        if (Shaders.isRenderBackFace(blockLayerIn)) {
            bus.q();
        }
    }

    public static void setupArrayPointersVbo() {
        int vertexSizeI = 14;
        GL11.glVertexPointer((int)3, (int)5126, (int)56, (long)0L);
        GL11.glColorPointer((int)4, (int)5121, (int)56, (long)12L);
        GL11.glTexCoordPointer((int)2, (int)5126, (int)56, (long)16L);
        cii.l((int)cii.r);
        GL11.glTexCoordPointer((int)2, (int)5122, (int)56, (long)24L);
        cii.l((int)cii.q);
        GL11.glNormalPointer((int)5120, (int)56, (long)28L);
        GL20.glVertexAttribPointer((int)Shaders.midTexCoordAttrib, (int)2, (int)5126, (boolean)false, (int)56, (long)32L);
        GL20.glVertexAttribPointer((int)Shaders.tangentAttrib, (int)4, (int)5122, (boolean)false, (int)56, (long)40L);
        GL20.glVertexAttribPointer((int)Shaders.entityAttrib, (int)3, (int)5122, (boolean)false, (int)56, (long)48L);
    }

    public static void beaconBeamBegin() {
        Shaders.useProgram(Shaders.ProgramBeaconBeam);
    }

    public static void beaconBeamStartQuad1() {
    }

    public static void beaconBeamStartQuad2() {
    }

    public static void beaconBeamDraw1() {
    }

    public static void beaconBeamDraw2() {
        bus.l();
    }

    public static void renderEnchantedGlintBegin() {
        Shaders.useProgram(Shaders.ProgramArmorGlint);
    }

    public static void renderEnchantedGlintEnd() {
        if (Shaders.isRenderingWorld) {
            if (Shaders.isRenderingFirstPersonHand() && Shaders.isRenderBothHands()) {
                Shaders.useProgram(Shaders.ProgramHand);
            } else {
                Shaders.useProgram(Shaders.ProgramEntities);
            }
        } else {
            Shaders.useProgram(Shaders.ProgramNone);
        }
    }

    public static boolean renderEndPortal(awg te, double x, double y, double z, float partialTicks, int destroyStage, float offset) {
        int lu;
        float du;
        if (!Shaders.isShadowPass && Shaders.activeProgram.getId() == 0) {
            return false;
        }
        bus.g();
        Config.getTextureManager().a(END_PORTAL_TEXTURE);
        bve tessellator = bve.a();
        buk bufferbuilder = tessellator.c();
        bufferbuilder.a(7, cdy.a);
        float col = 0.5f;
        float r = col * 0.15f;
        float g = col * 0.3f;
        float b2 = col * 0.4f;
        float u0 = 0.0f;
        float u1 = 0.2f;
        float v0 = u0;
        float v1 = u1;
        float dv = du = (float)(System.currentTimeMillis() % 100000L) / 100000.0f;
        float dy = offset;
        int lv = lu = 240;
        if (te.a(fa.d)) {
            bufferbuilder.b(x, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y + 1.0, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y + 1.0, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
        }
        if (te.a(fa.c)) {
            bufferbuilder.b(x, y + 1.0, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y + 1.0, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
        }
        if (te.a(fa.f)) {
            bufferbuilder.b(x + 1.0, y + 1.0, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y + 1.0, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
        }
        if (te.a(fa.e)) {
            bufferbuilder.b(x, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y + 1.0, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y + 1.0, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
        }
        if (te.a(fa.a)) {
            bufferbuilder.b(x, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y, z + 1.0).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
        }
        if (te.a(fa.b)) {
            bufferbuilder.b(x, y + (double)dy, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v0 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y + (double)dy, z + 1.0).a(r, g, b2, 1.0f).a((double)(u0 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x + 1.0, y + (double)dy, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v1 + dv)).a(lu, lv).d();
            bufferbuilder.b(x, y + (double)dy, z).a(r, g, b2, 1.0f).a((double)(u1 + du), (double)(v0 + dv)).a(lu, lv).d();
        }
        tessellator.b();
        bus.f();
        return true;
    }
}

