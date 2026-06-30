/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 */
package goblinbob.mobends.standard.previewer;

import goblinbob.mobends.standard.data.PlayerData;
import goblinbob.mobends.standard.previewer.BipedPreviewer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class PlayerPreviewer
extends BipedPreviewer<PlayerData> {
    private static PlayerData PREVIEW_DATA;
    private static boolean previewInProgress;

    public static void createPreviewData() {
        if (Minecraft.func_71410_x().field_71439_g == null) {
            PREVIEW_DATA = null;
            return;
        }
        PREVIEW_DATA = new PlayerData((AbstractClientPlayer)Minecraft.func_71410_x().field_71439_g);
    }

    public static void deletePreviewData() {
        PREVIEW_DATA = null;
    }

    public static PlayerData getPreviewData() {
        if (PREVIEW_DATA == null || PREVIEW_DATA.getEntity() == null) {
            PlayerPreviewer.createPreviewData();
        }
        return PREVIEW_DATA;
    }

    public static void updatePreviewData(float partialTicks) {
        PlayerData data = PlayerPreviewer.getPreviewData();
        if (data != null) {
            data.update(partialTicks);
        }
    }

    public static void updatePreviewDataClient() {
        PlayerData data = PlayerPreviewer.getPreviewData();
        if (data != null) {
            data.updateClient();
        }
    }

    public static boolean isPreviewInProgress() {
        return previewInProgress;
    }

    @Override
    public void prePreview(PlayerData data, String animationToPreview) {
        previewInProgress = true;
        data.overrideFlyingState(false);
        super.prePreview(data, animationToPreview);
        switch (animationToPreview) {
            case "flying": {
                data.overrideOnGroundState(false);
                data.overrideFlyingState(true);
                data.limbSwingAmount.override(Float.valueOf(0.0f));
                data.overrideStillness(true);
                break;
            }
        }
    }

    @Override
    protected void prepareForWalk(PlayerData data) {
        super.prepareForWalk(data);
        data.overrideFlyingState(false);
    }

    @Override
    protected void prepareForDefault(PlayerData data) {
        super.prepareForDefault(data);
        data.overrideFlyingState(false);
    }

    @Override
    public void postPreview(PlayerData data, String animationToPreview) {
        previewInProgress = false;
    }

    static {
        previewInProgress = false;
    }
}

