/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import journeymap.client.api.event.DisplayUpdateEvent;

class DisplayUpdateEventThrottle {
    private final Queue fullscreenQueue = new Queue(1000L);
    private final Queue minimapQueue = new Queue(2000L);
    private final Queue[] queues = new Queue[]{this.fullscreenQueue, this.minimapQueue};
    private final ArrayList<DisplayUpdateEvent> readyEvents = new ArrayList(3);
    private final Comparator<DisplayUpdateEvent> comparator = new Comparator<DisplayUpdateEvent>(){

        @Override
        public int compare(DisplayUpdateEvent o1, DisplayUpdateEvent o2) {
            return Long.compare(o1.timestamp, o2.timestamp);
        }
    };

    DisplayUpdateEventThrottle() {
    }

    public void add(DisplayUpdateEvent event) {
        switch (event.uiState.ui) {
            case Fullscreen: {
                this.fullscreenQueue.offer(event);
                break;
            }
            case Minimap: {
                this.minimapQueue.offer(event);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Can't throttle events for UI." + event.uiState.ui);
            }
        }
    }

    public Iterator<DisplayUpdateEvent> iterator() {
        long now = System.currentTimeMillis();
        for (Queue queue : this.queues) {
            if (queue.lastEvent == null || now < queue.releaseTime) continue;
            this.readyEvents.add(queue.remove());
        }
        if (this.readyEvents.size() > 0) {
            Collections.sort(this.readyEvents, this.comparator);
        }
        return this.readyEvents.iterator();
    }

    public boolean isReady() {
        long now = System.currentTimeMillis();
        for (Queue queue : this.queues) {
            if (queue.lastEvent == null || now < queue.releaseTime) continue;
            return true;
        }
        return false;
    }

    class Queue {
        private final long delay;
        private DisplayUpdateEvent lastEvent;
        private boolean throttleNext;
        private long releaseTime;

        Queue(long delay) {
            this.delay = delay;
        }

        void offer(DisplayUpdateEvent event) {
            if (this.releaseTime == 0L && this.lastEvent != null) {
                this.releaseTime = System.currentTimeMillis() + this.delay;
            }
            this.lastEvent = event;
        }

        DisplayUpdateEvent remove() {
            DisplayUpdateEvent event = this.lastEvent;
            this.lastEvent = null;
            this.releaseTime = 0L;
            return event;
        }
    }
}

