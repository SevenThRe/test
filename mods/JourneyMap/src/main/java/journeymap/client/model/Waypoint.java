/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.annotations.Since
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.util.Strings
 */
package journeymap.client.model;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import java.awt.Color;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;
import journeymap.client.Constants;
import journeymap.client.cartography.color.RGB;
import journeymap.client.model.WaypointGroup;
import journeymap.client.properties.WaypointProperties;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.waypoint.WaypointGroupStore;
import journeymap.client.waypoint.WaypointParser;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.util.Strings;

public class Waypoint
implements Serializable {
    public static final int VERSION = 3;
    public static final Gson GSON = new GsonBuilder().setVersion(3.0).create();
    protected static final String ICON_NORMAL = "waypoint-normal.png";
    protected static final String ICON_DEATH = "waypoint-death.png";
    @Since(value=1.0)
    protected String id;
    @Since(value=1.0)
    protected String name;
    @Since(value=3.0)
    protected String groupName;
    @Since(value=2.0)
    protected String displayId;
    @Since(value=1.0)
    protected String icon;
    @Since(value=1.0)
    protected int x;
    @Since(value=1.0)
    protected int y;
    @Since(value=1.0)
    protected int z;
    @Since(value=1.0)
    protected int r;
    @Since(value=1.0)
    protected int g;
    @Since(value=1.0)
    protected int b;
    @Since(value=1.0)
    protected boolean enable;
    @Since(value=1.0)
    protected Type type;
    @Since(value=1.0)
    protected String origin;
    @Since(value=1.0)
    protected TreeSet<Integer> dimensions;
    @Since(value=2.0)
    protected boolean persistent;
    protected transient WaypointGroup group;
    protected transient boolean dirty;
    protected transient Minecraft mc = FMLClientHandler.instance().getClient();

    public Waypoint() {
    }

    public Waypoint(Waypoint original) {
        this(original.name, original.x, original.y, original.z, original.enable, original.r, original.g, original.b, original.type, original.origin, original.dimensions.first(), original.dimensions);
        this.x = original.x;
        this.y = original.y;
        this.z = original.z;
    }

    public Waypoint(journeymap.client.api.display.Waypoint modWaypoint) {
        this(modWaypoint.getName(), modWaypoint.getPosition(), modWaypoint.getColor() == null ? Color.WHITE : new Color(modWaypoint.getColor()), Type.Normal, modWaypoint.getDimension());
        int[] prim = modWaypoint.getDisplayDimensions();
        ArrayList<Integer> dims = new ArrayList<Integer>(prim.length);
        for (int aPrim : prim) {
            dims.add(aPrim);
        }
        this.setDimensions(dims);
        this.setOrigin(modWaypoint.getModId());
        this.displayId = modWaypoint.getId();
        this.setPersistent(modWaypoint.isPersistent());
        if (modWaypoint.getGroup() != null) {
            this.setGroupName(modWaypoint.getGroup().getName());
        }
    }

    public Waypoint(String name, BlockPos pos, Color color, Type type, Integer currentDimension) {
        this(name, pos.getX(), pos.getY(), pos.getZ(), true, color.getRed(), color.getGreen(), color.getBlue(), type, "journeymap", currentDimension, Arrays.asList(currentDimension));
    }

    public Waypoint(String name, int x, int y, int z, boolean enable, int red, int green, int blue, Type type, String origin, Integer currentDimension, Collection<Integer> dimensions) {
        if (name == null) {
            name = Waypoint.createName(x, z);
        }
        if (dimensions == null || dimensions.size() == 0) {
            dimensions = new TreeSet<Integer>();
            dimensions.add(FMLClientHandler.instance().getClient().player.world.provider.getDimension());
        }
        this.dimensions = new TreeSet<Integer>(dimensions);
        this.dimensions.add(currentDimension);
        this.name = name;
        this.setLocation(x, y, z, currentDimension);
        this.r = red;
        this.g = green;
        this.b = blue;
        this.enable = enable;
        this.type = type;
        this.origin = origin;
        this.persistent = true;
        switch (type) {
            case Normal: {
                this.icon = ICON_NORMAL;
                break;
            }
            case Death: {
                this.icon = ICON_DEATH;
            }
        }
    }

    public static Waypoint of(EntityPlayer player) {
        BlockPos blockPos = new BlockPos(MathHelper.floor((double)player.posX), MathHelper.floor((double)player.posY), MathHelper.floor((double)player.posZ));
        return Waypoint.at(blockPos, Type.Normal, FMLClientHandler.instance().getClient().player.world.provider.getDimension());
    }

    public static Waypoint at(BlockPos blockPos, Type type, int dimension) {
        String name;
        if (type == Type.Death) {
            Date now = new Date();
            WaypointProperties properties = Journeymap.getClient().getWaypointProperties();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(properties.timeFormat.get() + " " + properties.dateFormat.get());
            String timeDate = simpleDateFormat.format(now);
            name = String.format("%s %s", Constants.getString("jm.waypoint.deathpoint"), timeDate);
        } else {
            name = Waypoint.createName(blockPos.getX(), blockPos.getZ());
        }
        Waypoint waypoint = new Waypoint(name, blockPos, Color.white, type, dimension);
        waypoint.setRandomColor();
        return waypoint;
    }

    private static String createName(int x, int z) {
        return String.format("%s, %s", x, z);
    }

    public static Waypoint fromString(String json) {
        return (Waypoint)GSON.fromJson(json, Waypoint.class);
    }

    public Waypoint setLocation(int x, int y, int z, int currentDimension) {
        this.x = currentDimension == -1 ? x * 8 : x;
        this.y = y;
        this.z = currentDimension == -1 ? z * 8 : z;
        this.updateId();
        return this.setDirty();
    }

    public String updateId() {
        String oldId = this.id;
        this.id = String.format("%s_%s,%s,%s", this.name, this.x, this.y, this.z);
        return oldId;
    }

    public boolean isDeathPoint() {
        return this.type == Type.Death;
    }

    public TextureImpl getTexture() {
        return this.isDeathPoint() ? TextureCache.getTexture(TextureCache.Deathpoint) : TextureCache.getTexture(TextureCache.Waypoint);
    }

    public ChunkPos getChunkCoordIntPair() {
        return new ChunkPos(this.x >> 4, this.z >> 4);
    }

    public Waypoint setGroup(WaypointGroup group) {
        this.setOrigin(group.getOrigin());
        this.groupName = group.getName();
        this.group = group;
        return this.setDirty();
    }

    public Waypoint setGroupName(String groupName) {
        WaypointGroup group = WaypointGroupStore.INSTANCE.get(this.origin, groupName);
        this.setGroup(group);
        return this;
    }

    public WaypointGroup getGroup() {
        if (this.group == null) {
            if (Strings.isEmpty((CharSequence)this.origin) || Strings.isEmpty((CharSequence)this.groupName)) {
                this.setGroup(WaypointGroup.DEFAULT);
            } else {
                this.setGroup(WaypointGroupStore.INSTANCE.get(this.origin, this.groupName));
            }
        }
        return this.group;
    }

    public Waypoint setRandomColor() {
        return this.setColor(RGB.randomColor());
    }

    public Integer getColor() {
        return RGB.toInteger(this.r, this.g, this.b);
    }

    public Waypoint setColor(Integer color) {
        int[] c = RGB.ints(color);
        this.r = c[0];
        this.g = c[1];
        this.b = c[2];
        return this.setDirty();
    }

    public Integer getSafeColor() {
        if (this.r + this.g + this.b >= 100) {
            return this.getColor();
        }
        return 0x404040;
    }

    public Collection<Integer> getDimensions() {
        return this.dimensions;
    }

    public Waypoint setDimensions(Collection<Integer> dims) {
        this.dimensions = new TreeSet<Integer>(dims);
        return this.setDirty();
    }

    public boolean isTeleportReady() {
        return this.y >= 0 && this.isInPlayerDimension();
    }

    public boolean isInPlayerDimension() {
        return this.dimensions.contains(FMLClientHandler.instance().getClient().player.world.provider.getDimension());
    }

    public String getId() {
        return this.displayId != null ? this.getGuid() : this.id;
    }

    public String getGuid() {
        return this.origin + ":" + this.displayId;
    }

    public String getName() {
        return this.name;
    }

    public Waypoint setName(String name) {
        this.name = name;
        return this.setDirty();
    }

    public String getIcon() {
        return this.icon;
    }

    public Waypoint setIcon(String icon) {
        this.icon = icon;
        return this.setDirty();
    }

    public int getX() {
        if (this.mc != null && this.mc.player != null && this.mc.player.dimension == -1) {
            return this.x / 8;
        }
        return this.x;
    }

    public double getBlockCenteredX() {
        return (double)this.getX() + 0.5;
    }

    public int getY() {
        return this.y;
    }

    public double getBlockCenteredY() {
        return (double)this.getY() + 0.5;
    }

    public int getZ() {
        if (this.mc != null && this.mc.player != null && this.mc.player.dimension == -1) {
            return this.z / 8;
        }
        return this.z;
    }

    public double getBlockCenteredZ() {
        return (double)this.getZ() + 0.5;
    }

    public Vec3d getPosition() {
        return new Vec3d(this.getBlockCenteredX(), this.getBlockCenteredY(), this.getBlockCenteredZ());
    }

    public BlockPos getBlockPos() {
        return new BlockPos(this.getX(), this.getY(), this.getZ());
    }

    public int getR() {
        return this.r;
    }

    public Waypoint setR(int r) {
        this.r = r;
        return this.setDirty();
    }

    public int getG() {
        return this.g;
    }

    public Waypoint setG(int g) {
        this.g = g;
        return this.setDirty();
    }

    public int getB() {
        return this.b;
    }

    public Waypoint setB(int b) {
        this.b = b;
        return this.setDirty();
    }

    public boolean isEnable() {
        return this.enable;
    }

    public Waypoint setEnable(boolean enable) {
        if (enable != this.enable) {
            this.enable = enable;
            this.setDirty();
        }
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public Waypoint setType(Type type) {
        this.type = type;
        return this.setDirty();
    }

    public String getOrigin() {
        return this.origin;
    }

    public Waypoint setOrigin(String origin) {
        this.origin = origin;
        return this.setDirty();
    }

    public String getFileName() {
        String fileName = this.id.replaceAll("[\\\\/:\"*?<>|]", "_").concat(".json");
        if (fileName.equals("waypoint_groups.json")) {
            fileName = "_" + fileName;
        }
        return fileName;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public Waypoint setDirty() {
        return this.setDirty(true);
    }

    public Waypoint setDirty(boolean dirty) {
        if (this.isPersistent()) {
            this.dirty = dirty;
        }
        return this;
    }

    public boolean isPersistent() {
        return this.persistent;
    }

    public Waypoint setPersistent(boolean persistent) {
        this.persistent = persistent;
        this.dirty = persistent;
        return this;
    }

    public String toChatString() {
        boolean useName = !this.getName().equals(String.format("%s, %s", this.getX(), this.getZ()));
        return this.toChatString(useName);
    }

    public String toChatString(boolean useName) {
        String format;
        String result;
        boolean useDim = this.dimensions.first() != 0;
        ArrayList<String> parts = new ArrayList<String>();
        ArrayList<Object> args2 = new ArrayList<Object>();
        if (useName) {
            parts.add("name:\"%s\"");
            args2.add(this.getName().replaceAll("\"", " "));
        }
        parts.add("x:%s, y:%s, z:%s");
        args2.add(this.getX());
        args2.add(this.getY());
        args2.add(this.getZ());
        if (useDim) {
            parts.add("dim:%s");
            args2.add(this.dimensions.first());
        }
        if (WaypointParser.parse(result = String.format(format = "[" + Joiner.on((String)", ").join(parts) + "]", args2.toArray())) == null) {
            Journeymap.getLogger().warn("Couldn't produce parsable chat string from Waypoint: " + this);
            if (useName) {
                return this.toChatString(false);
            }
        }
        return result;
    }

    public String toString() {
        return GSON.toJson((Object)this);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Waypoint waypoint = (Waypoint)o;
        if (this.b != waypoint.b) {
            return false;
        }
        if (this.enable != waypoint.enable) {
            return false;
        }
        if (this.g != waypoint.g) {
            return false;
        }
        if (this.r != waypoint.r) {
            return false;
        }
        if (this.x != waypoint.x) {
            return false;
        }
        if (this.y != waypoint.y) {
            return false;
        }
        if (this.z != waypoint.z) {
            return false;
        }
        if (!this.dimensions.equals(waypoint.dimensions)) {
            return false;
        }
        if (!this.icon.equals(waypoint.icon)) {
            return false;
        }
        if (!this.id.equals(waypoint.id)) {
            return false;
        }
        if (!this.name.equals(waypoint.name)) {
            return false;
        }
        if (this.origin != waypoint.origin) {
            return false;
        }
        return this.type == waypoint.type;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public static enum Type {
        Normal,
        Death;

    }
}

