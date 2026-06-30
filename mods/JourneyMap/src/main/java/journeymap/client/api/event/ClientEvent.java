/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.api.event;

public class ClientEvent {
    public final Type type;
    public final int dimension;
    public final long timestamp;
    private boolean cancelled;

    public ClientEvent(Type type, int dimension) {
        this.type = type;
        this.dimension = dimension;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cancel() {
        if (this.type.cancellable) {
            this.cancelled = true;
        }
    }

    public static enum Type {
        DISPLAY_UPDATE(false),
        DEATH_WAYPOINT(true),
        MAPPING_STARTED(false),
        MAPPING_STOPPED(false);

        public final boolean cancellable;

        private Type(boolean cancellable) {
            this.cancellable = cancellable;
        }
    }
}

