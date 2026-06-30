/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.network.impl.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import journeymap.common.network.impl.utils.AsyncCallback;

public class CallbackService {
    private final Map<UUID, CallbackWrapper> callbackMap = new HashMap<UUID, CallbackWrapper>();
    private static CallbackService INSTANCE;

    private CallbackService() {
    }

    public static CallbackService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CallbackService();
        }
        return INSTANCE;
    }

    public void saveCallback(UUID id, AsyncCallback callback) {
        this.callbackMap.put(id, new CallbackWrapper(callback));
    }

    public AsyncCallback getCallback(UUID id) {
        CallbackWrapper callbackWrapper = this.callbackMap.get(id);
        if (callbackWrapper != null) {
            return callbackWrapper.getCallback();
        }
        return null;
    }

    public void removeCallback(UUID id) {
        this.callbackMap.remove(id);
    }

    private class CallbackWrapper {
        AsyncCallback callback;

        public CallbackWrapper(AsyncCallback callback) {
            this.callback = callback;
        }

        public AsyncCallback getCallback() {
            return this.callback;
        }
    }
}

