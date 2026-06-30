/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.util.math.ChunkPos
 */
package journeymap.client.api;

import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.DisplayType;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.util.UIState;
import net.minecraft.util.math.ChunkPos;

@ParametersAreNonnullByDefault
public interface IClientAPI {
    public static final String API_OWNER = "journeymap";
    public static final String API_VERSION = "1.4";

    @Nullable
    public UIState getUIState(Context.UI var1);

    public void subscribe(String var1, EnumSet<ClientEvent.Type> var2);

    public void show(Displayable var1) throws Exception;

    public void remove(Displayable var1);

    public void removeAll(String var1, DisplayType var2);

    public void removeAll(String var1);

    public boolean exists(Displayable var1);

    public boolean playerAccepts(String var1, DisplayType var2);

    public void requestMapTile(String var1, int var2, Context.MapType var3, ChunkPos var4, ChunkPos var5, @Nullable Integer var6, int var7, boolean var8, Consumer<BufferedImage> var9);

    public void toggleDisplay(@Nullable Integer var1, Context.MapType var2, Context.UI var3, boolean var4);

    public void toggleWaypoints(@Nullable Integer var1, Context.MapType var2, Context.UI var3, boolean var4);

    public boolean isDisplayEnabled(@Nullable Integer var1, Context.MapType var2, Context.UI var3);

    public boolean isWaypointsEnabled(@Nullable Integer var1, Context.MapType var2, Context.UI var3);
}

