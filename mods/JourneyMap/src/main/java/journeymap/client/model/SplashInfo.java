/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import journeymap.client.properties.ClientCategory;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.dialog.OptionsManager;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;

public class SplashInfo {
    public ArrayList<Line> lines = new ArrayList();

    public static class Line {
        public String label;
        public String action;

        public Line() {
        }

        public Line(String label, String action) {
            this.label = label;
            this.action = action;
        }

        public boolean hasAction() {
            return this.action != null && this.action.trim().length() > 0;
        }

        public void invokeAction(JmUI returnUi) {
            if (!this.hasAction()) {
                return;
            }
            try {
                Class<?> uiClass;
                String[] parts = this.action.split("#");
                String className = parts[0];
                String action = null;
                if (parts.length > 1) {
                    action = parts[1];
                }
                if ((uiClass = Class.forName("journeymap.client.ui." + className)).equals(OptionsManager.class) && action != null) {
                    Category category = ClientCategory.valueOf(action);
                    UIManager.INSTANCE.openOptionsManager(returnUi, category);
                    return;
                }
                if (action != null) {
                    String arg = parts.length == 3 ? parts[2] : null;
                    try {
                        Object instance = null;
                        instance = JmUI.class.isAssignableFrom(uiClass) ? UIManager.INSTANCE.open(uiClass, returnUi) : uiClass.newInstance();
                        if (arg == null) {
                            Method actionMethod = uiClass.getMethod(action, new Class[0]);
                            actionMethod.invoke(instance, new Object[0]);
                        } else {
                            Method actionMethod = uiClass.getMethod(action, String.class);
                            actionMethod.invoke(instance, arg);
                        }
                        return;
                    }
                    catch (Exception e) {
                        Journeymap.getLogger().warn("Couldn't perform action " + action + " on " + uiClass + ": " + e.getMessage());
                    }
                }
                if (JmUI.class.isAssignableFrom(uiClass)) {
                    UIManager.INSTANCE.open(uiClass, returnUi);
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Couldn't invoke action: " + this.action + ": " + LogFormatter.toString(t));
            }
        }
    }
}

