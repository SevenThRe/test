/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.slf4j.helpers;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class Util {
    private static ClassContextSecurityManager SECURITY_MANAGER;
    private static boolean SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED;

    private Util() {
    }

    public static String safeGetSystemProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("null input");
        }
        String result = null;
        try {
            result = System.getProperty(key);
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return result;
    }

    public static boolean safeGetBooleanSystemProperty(String key) {
        String value = Util.safeGetSystemProperty(key);
        if (value == null) {
            return false;
        }
        return value.equalsIgnoreCase("true");
    }

    private static ClassContextSecurityManager getSecurityManager() {
        if (SECURITY_MANAGER != null) {
            return SECURITY_MANAGER;
        }
        if (SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED) {
            return null;
        }
        SECURITY_MANAGER = Util.safeCreateSecurityManager();
        SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = true;
        return SECURITY_MANAGER;
    }

    private static ClassContextSecurityManager safeCreateSecurityManager() {
        try {
            return new ClassContextSecurityManager();
        }
        catch (SecurityException sm) {
            return null;
        }
    }

    public static Class<?> getCallingClass() {
        int i;
        ClassContextSecurityManager securityManager = Util.getSecurityManager();
        if (securityManager == null) {
            return null;
        }
        Class<?>[] trace2 = securityManager.getClassContext();
        String thisClassName = Util.class.getName();
        for (i = 0; i < trace2.length && !thisClassName.equals(trace2[i].getName()); ++i) {
        }
        if (i >= trace2.length || i + 2 >= trace2.length) {
            throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
        }
        return trace2[i + 2];
    }

    public static final void report(String msg, Throwable t) {
        System.err.println(msg);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }

    public static final void report(String msg) {
        System.err.println("SLF4J: " + msg);
    }

    static {
        SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = false;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static final class ClassContextSecurityManager
    extends SecurityManager {
        private ClassContextSecurityManager() {
        }

        @Override
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }
}

