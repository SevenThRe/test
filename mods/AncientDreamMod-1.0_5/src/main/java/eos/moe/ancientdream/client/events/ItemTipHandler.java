/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderTooltipEvent$Color
 *  net.minecraftforge.client.event.RenderTooltipEvent$PostBackground
 *  net.minecraftforge.client.event.RenderTooltipEvent$PostText
 *  net.minecraftforge.client.event.RenderTooltipEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.ancientdream.client.events;

import eos.moe.ancientdream.client.utils.StringUtil;
import eos.moe.ancientdream.data.DataManager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemTipHandler {
    public static String replaceLast(String text, String strToReplace, String replaceWithThis) {
        return text.replaceFirst("(?s)" + strToReplace + "(?!.*?" + strToReplace + ")", replaceWithThis);
    }

    @SubscribeEvent
    public static void renderToolTipPre(RenderTooltipEvent.Pre e) {
        ItemStack stack = e.getStack();
        if (!stack.isEmpty()) {
            boolean rt = true;
            for (String line : e.getLines()) {
                if (!line.contains("\u00a70") && !line.contains("\u88c5\u5907\u8bc4\u5206")) continue;
                rt = false;
                break;
            }
            if (rt) {
                return;
            }
            if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isCreative()) {
                return;
            }
            e.setCanceled(true);
            int firstLine = -1;
            float firstPoints = 0.0f;
            float points = 0.0f;
            ArrayList<String> lines = new ArrayList<String>();
            for (int i = 0; i < e.getLines().size(); ++i) {
                String line = (String)e.getLines().get(i);
                if (line.contains("\u88c5\u5907\u8bc4\u5206")) {
                    if (firstLine == -1) {
                        firstLine = i;
                        points = firstPoints = StringUtil.matchValue(line);
                        lines.add(line);
                        continue;
                    }
                    points += StringUtil.matchValue(line);
                    continue;
                }
                if (line.contains("\u00a70")) continue;
                lines.add(line);
            }
            if (firstLine != -1) {
                String s = (String)lines.get(firstLine);
                lines.set(firstLine, ItemTipHandler.replaceLast(s, String.valueOf((int)firstPoints), String.valueOf((int)points)));
            }
            ItemTipHandler.drawHoveringText(e.getStack(), lines, e.getX(), e.getY(), e.getScreenWidth(), e.getScreenHeight(), e.getMaxWidth(), e.getFontRenderer());
        }
    }

    @SubscribeEvent
    public static void renderToolTipPost(RenderTooltipEvent.PostText e) {
        ItemTipHandler.drawOutfitPanel(e.getStack(), e.getLines(), e.getX(), e.getY(), e.getWidth(), e.getFontRenderer());
    }

    public static void drawHoveringText(@Nonnull ItemStack stack, List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
        if (!textLines.isEmpty()) {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int tooltipTextWidth = 0;
            for (String textLine : textLines) {
                int textLineWidth = font.getStringWidth(textLine);
                if (textLineWidth <= tooltipTextWidth) continue;
                tooltipTextWidth = textLineWidth;
            }
            boolean needsWrap = false;
            int titleLinesCount = 1;
            int tooltipX = mouseX + 12;
            if (tooltipX + tooltipTextWidth + 4 > screenWidth && (tooltipX = mouseX - 16 - tooltipTextWidth) < 4) {
                tooltipTextWidth = mouseX > screenWidth / 2 ? mouseX - 12 - 8 : screenWidth - 16 - mouseX;
                needsWrap = true;
            }
            if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth) {
                tooltipTextWidth = maxTextWidth;
                needsWrap = true;
            }
            if (needsWrap) {
                int wrappedTooltipWidth = 0;
                ArrayList<String> wrappedTextLines = new ArrayList<String>();
                for (int i = 0; i < textLines.size(); ++i) {
                    String textLine = textLines.get(i);
                    List wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                    if (i == 0) {
                        titleLinesCount = wrappedLine.size();
                    }
                    for (String line : wrappedLine) {
                        int lineWidth = font.getStringWidth(line);
                        if (lineWidth > wrappedTooltipWidth) {
                            wrappedTooltipWidth = lineWidth;
                        }
                        wrappedTextLines.add(line);
                    }
                }
                tooltipTextWidth = wrappedTooltipWidth;
                textLines = wrappedTextLines;
                tooltipX = mouseX > screenWidth / 2 ? mouseX - 16 - tooltipTextWidth : mouseX + 12;
            }
            int tooltipY = mouseY - 12;
            int tooltipHeight = 8;
            if (textLines.size() > 1) {
                tooltipHeight += (textLines.size() - 1) * 10;
                if (textLines.size() > titleLinesCount) {
                    tooltipHeight += 2;
                }
            }
            if (tooltipY < 4) {
                tooltipY = 4;
            } else if (tooltipY + tooltipHeight + 4 > screenHeight) {
                tooltipY = screenHeight - tooltipHeight - 4;
            }
            int zLevel = 300;
            int backgroundColor = -267386864;
            int borderColorStart = 0x505000FF;
            int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
            RenderTooltipEvent.Color colorEvent = new RenderTooltipEvent.Color(stack, textLines, tooltipX, tooltipY, font, backgroundColor, borderColorStart, borderColorEnd);
            MinecraftForge.EVENT_BUS.post((Event)colorEvent);
            backgroundColor = colorEvent.getBackground();
            borderColorStart = colorEvent.getBorderStart();
            borderColorEnd = colorEvent.getBorderEnd();
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 4), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 4), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 4), (int)(tooltipY - 3), (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 4), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3 + 1), (int)(tooltipX - 3 + 1), (int)(tooltipY + tooltipHeight + 3 - 1), (int)borderColorStart, (int)borderColorEnd);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX + tooltipTextWidth + 2), (int)(tooltipY - 3 + 1), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3 - 1), (int)borderColorStart, (int)borderColorEnd);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3 + 1), (int)borderColorStart, (int)borderColorStart);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 2), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3), (int)borderColorEnd, (int)borderColorEnd);
            MinecraftForge.EVENT_BUS.post((Event)new RenderTooltipEvent.PostBackground(stack, textLines, tooltipX, tooltipY, font, tooltipTextWidth, tooltipHeight));
            int tooltipTop = tooltipY;
            for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber) {
                String line = textLines.get(lineNumber);
                font.drawStringWithShadow(line, (float)tooltipX, (float)tooltipY, -1);
                if (lineNumber + 1 == titleLinesCount) {
                    tooltipY += 2;
                }
                tooltipY += 10;
            }
            ItemTipHandler.drawOutfitPanel(stack, textLines, tooltipX, tooltipTop, tooltipTextWidth, font);
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }

    public static void drawOutfitPanel(ItemStack stack, List<String> lines, int x, int y, int width, FontRenderer font) {
        String outfit;
        if (!stack.isEmpty() && (outfit = StringUtil.getRight(lines, "\u5957\u88c5\u6548\u679c")) != null && DataManager.outfits.containsKey(outfit)) {
            ArrayList strings = new ArrayList(DataManager.outfits.get(outfit));
            Integer orDefault = DataManager.outfitCounts.getOrDefault(outfit, 0);
            strings.replaceAll(l -> {
                for (int i = 0; i < 30 && l.contains("{"); ++i) {
                    l = i == orDefault ? l.replace("{" + i + "}", "\u9a53") : l.replace("{" + i + "}", "\u56c3");
                }
                return l;
            });
            int tooltipX = x + width + 5;
            int tooltipY = y;
            int tooltipTextWidth = 0;
            for (String string : strings) {
                tooltipTextWidth = Math.max(font.getStringWidth(string), tooltipTextWidth);
            }
            int tooltipHeight = strings.size() * 10;
            int zLevel = 300;
            int backgroundColor = -267386864;
            int borderColorStart = 0x505000FF;
            int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
            RenderTooltipEvent.Color colorEvent = new RenderTooltipEvent.Color(stack, lines, tooltipX, tooltipY, font, backgroundColor, borderColorStart, borderColorEnd);
            MinecraftForge.EVENT_BUS.post((Event)colorEvent);
            backgroundColor = colorEvent.getBackground();
            borderColorStart = colorEvent.getBorderStart();
            borderColorEnd = colorEvent.getBorderEnd();
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 4), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 4), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 4), (int)(tooltipY - 3), (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 4), (int)(tooltipY + tooltipHeight + 3), (int)backgroundColor, (int)backgroundColor);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3 + 1), (int)(tooltipX - 3 + 1), (int)(tooltipY + tooltipHeight + 3 - 1), (int)borderColorStart, (int)borderColorEnd);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX + tooltipTextWidth + 2), (int)(tooltipY - 3 + 1), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3 - 1), (int)borderColorStart, (int)borderColorEnd);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY - 3), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY - 3 + 1), (int)borderColorStart, (int)borderColorStart);
            GuiUtils.drawGradientRect((int)300, (int)(tooltipX - 3), (int)(tooltipY + tooltipHeight + 2), (int)(tooltipX + tooltipTextWidth + 3), (int)(tooltipY + tooltipHeight + 3), (int)borderColorEnd, (int)borderColorEnd);
            for (String string : strings) {
                font.drawStringWithShadow(string, (float)tooltipX, (float)tooltipY, -1);
                tooltipY += 10;
            }
        }
    }
}

