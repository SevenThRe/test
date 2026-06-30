/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.ITextComponent$Serializer
 *  net.minecraft.util.text.Style
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.util.text.event.ClickEvent
 *  net.minecraft.util.text.event.ClickEvent$Action
 *  net.minecraft.util.text.event.HoverEvent
 *  net.minecraft.util.text.event.HoverEvent$Action
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  org.apache.commons.lang3.StringUtils
 */
package journeymap.client.waypoint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import journeymap.client.model.Waypoint;
import journeymap.common.Journeymap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.apache.commons.lang3.StringUtils;

public class WaypointParser {
    public static String[] QUOTES = new String[]{"'", "\""};
    public static Pattern PATTERN = Pattern.compile("(\\w+\\s*:\\s*-?[\\w\\d\\s'\"]+,\\s*)+(\\w+\\s*:\\s*-?[\\w\\d\\s'\"]+)", 2);
    private static Pattern TEXT_BETWEEN_QUOTES = Pattern.compile("\".*?\"|'.*?'|`.*`");

    public static List<String> getWaypointStrings(String line) {
        ArrayList<String> list = null;
        String[] candidates = StringUtils.substringsBetween((String)line, (String)"[", (String)"]");
        if (candidates != null) {
            for (String candidate : candidates) {
                if (!PATTERN.matcher(candidate).find() || WaypointParser.parse(candidate) == null) continue;
                if (list == null) {
                    list = new ArrayList<String>(1);
                }
                list.add("[" + candidate + "]");
            }
        }
        return list;
    }

    public static List<Waypoint> getWaypoints(String line) {
        ArrayList<Waypoint> list = null;
        String[] candidates = StringUtils.substringsBetween((String)line, (String)"[", (String)"]");
        if (candidates != null) {
            for (String candidate : candidates) {
                Waypoint waypoint;
                if (!PATTERN.matcher(candidate).find() || (waypoint = WaypointParser.parse(candidate)) == null) continue;
                if (list == null) {
                    list = new ArrayList<Waypoint>(1);
                }
                list.add(waypoint);
            }
        }
        return list;
    }

    public static Waypoint parse(String original) {
        String[] quotedVals = null;
        String raw = original.replaceAll("[\\[\\]]", "");
        for (String quoteChar : QUOTES) {
            if (!raw.contains(quoteChar) || (quotedVals = StringUtils.substringsBetween((String)raw, (String)quoteChar, (String)quoteChar)) == null) continue;
            for (int i = 0; i < quotedVals.length; ++i) {
                raw = raw.replaceAll(TEXT_BETWEEN_QUOTES.pattern(), "__TEMP_" + i);
            }
        }
        Integer x = null;
        Integer y = 63;
        Integer z = null;
        Integer dim = 0;
        String name = null;
        for (String part : raw.split(",")) {
            String[] prop;
            if (!part.contains(":") || (prop = part.split(":")).length != 2) continue;
            String key = prop[0].trim().toLowerCase();
            String val = prop[1].trim();
            try {
                if ("x".equals(key)) {
                    x = Integer.parseInt(val);
                    continue;
                }
                if ("y".equals(key)) {
                    y = Math.max(0, Math.min(255, Integer.parseInt(val)));
                    continue;
                }
                if ("z".equals(key)) {
                    z = Integer.parseInt(val);
                    continue;
                }
                if ("dim".equals(key)) {
                    dim = Integer.parseInt(val);
                    continue;
                }
                if (!"name".equals(key)) continue;
                name = val;
            }
            catch (Exception e) {
                Journeymap.getLogger().warn("Bad format in waypoint text part: " + part + ": " + e);
            }
        }
        if (x != null && z != null) {
            if (name != null && quotedVals != null) {
                for (int i = 0; i < quotedVals.length; ++i) {
                    String val = quotedVals[i];
                    name = name.replaceAll("__TEMP_" + i, val);
                }
            }
            if (name == null) {
                name = String.format("%s,%s", x, z);
            }
            Random r = new Random();
            Waypoint waypoint = new Waypoint(name, new BlockPos(x.intValue(), y.intValue(), z.intValue()), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), Waypoint.Type.Normal, dim);
            return waypoint;
        }
        return null;
    }

    public static void parseChatForWaypoints(ClientChatReceivedEvent event, String unformattedText) {
        List<String> matches = WaypointParser.getWaypointStrings(unformattedText);
        if (matches != null) {
            boolean changed = false;
            if (event.getMessage() instanceof TextComponentTranslation) {
                Object[] formatArgs = ((TextComponentTranslation)event.getMessage()).func_150271_j();
                for (int i = 0; i < formatArgs.length && !matches.isEmpty(); ++i) {
                    ITextComponent result;
                    Object arg;
                    if (formatArgs[i] instanceof ITextComponent) {
                        arg = (ITextComponent)formatArgs[i];
                        result = WaypointParser.addWaypointMarkup(arg.func_150260_c(), matches);
                        if (result == null) continue;
                        formatArgs[i] = result;
                        changed = true;
                        continue;
                    }
                    if (!(formatArgs[i] instanceof String) || (result = WaypointParser.addWaypointMarkup((String)(arg = (String)formatArgs[i]), matches)) == null) continue;
                    formatArgs[i] = result;
                    changed = true;
                }
                if (changed) {
                    event.setMessage((ITextComponent)new TextComponentTranslation(((TextComponentTranslation)event.getMessage()).func_150268_i(), formatArgs));
                }
            } else if (event.getMessage() instanceof TextComponentString) {
                ITextComponent result = WaypointParser.addWaypointMarkup(event.getMessage().func_150260_c(), matches);
                if (result != null) {
                    event.setMessage(result);
                    changed = true;
                }
            } else {
                Journeymap.getLogger().warn("No implementation for handling waypoints in ITextComponent " + event.getMessage().getClass());
            }
            if (!changed) {
                Journeymap.getLogger().warn(String.format("Matched waypoint in chat but failed to update message for %s : %s\n%s", event.getMessage().getClass(), event.getMessage().func_150254_d(), ITextComponent.Serializer.func_150696_a((ITextComponent)event.getMessage())));
            }
        }
    }

    private static ITextComponent addWaypointMarkup(String text, List<String> matches) {
        ArrayList<TextComponentString> newParts = new ArrayList<TextComponentString>();
        int index = 0;
        boolean matched = false;
        Iterator<String> iterator2 = matches.iterator();
        while (iterator2.hasNext()) {
            String match = iterator2.next();
            if (!text.contains(match)) continue;
            int start = text.indexOf(match);
            if (start > index) {
                newParts.add(new TextComponentString(text.substring(index, start)));
            }
            matched = true;
            TextComponentString textComponentString = new TextComponentString(match);
            Style chatStyle = textComponentString.func_150256_b();
            chatStyle.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jm wpedit " + match));
            TextComponentString hover = new TextComponentString("JourneyMap: ");
            hover.func_150256_b().func_150238_a(TextFormatting.YELLOW);
            TextComponentString hover2 = new TextComponentString("Click to create Waypoint.\nCtrl+Click to view on map.");
            hover2.func_150256_b().func_150238_a(TextFormatting.AQUA);
            hover.func_150257_a((ITextComponent)hover2);
            chatStyle.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)hover));
            chatStyle.func_150238_a(TextFormatting.AQUA);
            newParts.add(textComponentString);
            index = start + match.length();
            iterator2.remove();
        }
        if (!matched) {
            return null;
        }
        if (index < text.length() - 1) {
            newParts.add(new TextComponentString(text.substring(index, text.length())));
        }
        if (!newParts.isEmpty()) {
            TextComponentString replacement = new TextComponentString("");
            for (ITextComponent iTextComponent : newParts) {
                replacement.func_150257_a(iTextComponent);
            }
            return replacement;
        }
        return null;
    }
}

