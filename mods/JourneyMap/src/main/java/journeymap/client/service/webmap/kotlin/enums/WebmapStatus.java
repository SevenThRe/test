/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.service.webmap.kotlin.enums;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2={"Ljourneymap/client/service/webmap/kotlin/enums/WebmapStatus;", "", "status", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "READY", "DISABLED", "NO_WORLD", "STARTING", "journeymap"})
public final class WebmapStatus
extends Enum<WebmapStatus> {
    public static final /* enum */ WebmapStatus READY;
    public static final /* enum */ WebmapStatus DISABLED;
    public static final /* enum */ WebmapStatus NO_WORLD;
    public static final /* enum */ WebmapStatus STARTING;
    private static final /* synthetic */ WebmapStatus[] $VALUES;
    @NotNull
    private final String status;

    static {
        WebmapStatus[] webmapStatusArray = new WebmapStatus[4];
        WebmapStatus[] webmapStatusArray2 = webmapStatusArray;
        webmapStatusArray[0] = READY = new WebmapStatus("ready");
        webmapStatusArray[1] = DISABLED = new WebmapStatus("disabled");
        webmapStatusArray[2] = NO_WORLD = new WebmapStatus("no_world");
        webmapStatusArray[3] = STARTING = new WebmapStatus("starting");
        $VALUES = webmapStatusArray;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    private WebmapStatus(String status) {
        this.status = status;
    }

    public static WebmapStatus[] values() {
        return (WebmapStatus[])$VALUES.clone();
    }

    public static WebmapStatus valueOf(String string) {
        return Enum.valueOf(WebmapStatus.class, string);
    }
}

