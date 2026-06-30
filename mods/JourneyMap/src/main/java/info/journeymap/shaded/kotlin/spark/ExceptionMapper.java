/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.ExceptionHandlerImpl;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper {
    private static ExceptionMapper defaultInstance;
    private Map<Class<? extends Exception>, ExceptionHandlerImpl> exceptionMap = new HashMap<Class<? extends Exception>, ExceptionHandlerImpl>();

    public static synchronized ExceptionMapper getInstance() {
        if (defaultInstance == null) {
            defaultInstance = new ExceptionMapper();
        }
        return defaultInstance;
    }

    public void map(Class<? extends Exception> exceptionClass, ExceptionHandlerImpl handler) {
        this.exceptionMap.put(exceptionClass, handler);
    }

    public ExceptionHandlerImpl getHandler(Class<? extends Exception> exceptionClass) {
        if (!this.exceptionMap.containsKey(exceptionClass)) {
            Class<? extends Exception> superclass = exceptionClass.getSuperclass();
            do {
                if (!this.exceptionMap.containsKey(superclass)) continue;
                ExceptionHandlerImpl handler = this.exceptionMap.get(superclass);
                this.exceptionMap.put(exceptionClass, handler);
                return handler;
            } while ((superclass = superclass.getSuperclass()) != null);
            this.exceptionMap.put(exceptionClass, null);
            return null;
        }
        return this.exceptionMap.get(exceptionClass);
    }

    public ExceptionHandlerImpl getHandler(Exception exception) {
        return this.getHandler(exception.getClass());
    }

    public void clear() {
        this.exceptionMap.clear();
    }
}

