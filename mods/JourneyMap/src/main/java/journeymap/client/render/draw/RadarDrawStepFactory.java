/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package journeymap.client.render.draw;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.data.DataCache;
import journeymap.client.model.EntityDTO;
import journeymap.client.properties.InGameMapProperties;
import journeymap.client.render.draw.DrawEntityStep;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.minimap.EntityDisplay;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class RadarDrawStepFactory {
    public List<DrawStep> prepareSteps(List<EntityDTO> entityDTOs, GridRenderer grid, InGameMapProperties mapProperties) {
        boolean showAnimals = mapProperties.showAnimals.get();
        boolean showPets = mapProperties.showPets.get();
        boolean showVillagers = mapProperties.showVillagers.get();
        EntityDisplay mobDisplay = (EntityDisplay)mapProperties.mobDisplay.get();
        EntityDisplay playerDisplay = (EntityDisplay)mapProperties.playerDisplay.get();
        boolean showMobHeading = mapProperties.showMobHeading.get();
        boolean showPlayerHeading = mapProperties.showPlayerHeading.get();
        boolean showEntityNames = mapProperties.showEntityNames.get();
        ArrayList<DrawStep> drawStepList = new ArrayList<DrawStep>();
        try {
            for (EntityDTO dto : entityDTOs) {
                try {
                    boolean isPet;
                    TextureImpl entityIcon = null;
                    TextureImpl locatorImg = null;
                    EntityLivingBase entityLiving = (EntityLivingBase)dto.entityLivingRef.get();
                    if (entityLiving == null || grid.getPixel(dto.posX, dto.posZ) == null) continue;
                    boolean bl = isPet = !Strings.isNullOrEmpty((String)dto.owner);
                    if (!showPets && isPet || !showAnimals && dto.passiveAnimal && (!isPet || !showPets) || !showVillagers && (dto.profession != null || dto.npc)) continue;
                    DrawEntityStep drawStep = DataCache.INSTANCE.getDrawEntityStep(entityLiving);
                    boolean isPlayer = entityLiving instanceof EntityPlayer;
                    if (isPlayer) {
                        locatorImg = EntityDisplay.getLocatorTexture(playerDisplay, showPlayerHeading);
                        entityIcon = EntityDisplay.getEntityTexture(playerDisplay, entityLiving.getUniqueID(), entityLiving.getName());
                        drawStep.update(playerDisplay, locatorImg, entityIcon, dto.color, showPlayerHeading, showEntityNames);
                        drawStepList.add(drawStep);
                        continue;
                    }
                    locatorImg = EntityDisplay.getLocatorTexture(mobDisplay, showMobHeading);
                    entityIcon = EntityDisplay.getEntityTexture(mobDisplay, dto.entityIconLocation);
                    EntityDisplay actualDisplay = mobDisplay;
                    if (!mobDisplay.isDots() && entityIcon == null) {
                        actualDisplay = mobDisplay.isLarge() ? EntityDisplay.LargeDots : EntityDisplay.SmallDots;
                        entityIcon = EntityDisplay.getEntityTexture(actualDisplay, dto.entityIconLocation);
                    }
                    drawStep.update(actualDisplay, locatorImg, entityIcon, dto.color, showMobHeading, showEntityNames);
                    drawStepList.add(drawStep);
                }
                catch (Exception e) {
                    Journeymap.getLogger().error("Exception during prepareSteps: " + LogFormatter.toString(e));
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Throwable during prepareSteps: " + LogFormatter.toString(t));
        }
        return drawStepList;
    }
}

