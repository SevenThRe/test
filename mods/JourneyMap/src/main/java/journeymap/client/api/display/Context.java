/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.api.display;

public interface Context {

    public static enum MapType implements Context
    {
        Any,
        Day,
        Night,
        Underground,
        Topo;

    }

    public static enum UI implements Context
    {
        Any,
        Fullscreen,
        Minimap,
        Webmap;

    }
}

