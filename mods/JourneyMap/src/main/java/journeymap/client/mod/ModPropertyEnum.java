/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.IBlockState
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.mod;

import java.lang.reflect.Method;
import java.util.Collection;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.Logger;

@ParametersAreNonnullByDefault
public class ModPropertyEnum<T> {
    private static final Logger logger = Journeymap.getLogger();
    private final boolean valid;
    private final PropertyEnum propertyEnum;
    private final Method method;

    public ModPropertyEnum(PropertyEnum propertyEnum, Method method, Class<T> returnType) {
        this.valid = propertyEnum != null && method != null;
        this.propertyEnum = propertyEnum;
        this.method = method;
    }

    public ModPropertyEnum(PropertyEnum propertyEnum, String methodName, Class<T> returnType, Class<?>[] methodArgTypes) {
        this(propertyEnum, ModPropertyEnum.lookupMethod(propertyEnum, methodName, (Class[])methodArgTypes), returnType);
    }

    public ModPropertyEnum(String declaringClassName, String propertyEnumStaticFieldName, String methodName, Class<T> returnType) {
        this(declaringClassName, propertyEnumStaticFieldName, methodName, returnType, new Class[0]);
    }

    public ModPropertyEnum(String declaringClassName, String propertyEnumStaticFieldName, String methodName, Class<T> returnType, Class<?>[] methodArgTypes) {
        this(ModPropertyEnum.lookupPropertyEnum(declaringClassName, propertyEnumStaticFieldName), methodName, returnType, methodArgTypes);
    }

    public ModPropertyEnum(String declaringClassName, String propertyEnumStaticFieldName, Method method, Class<T> returnType) {
        this(ModPropertyEnum.lookupPropertyEnum(declaringClassName, propertyEnumStaticFieldName), method, returnType);
    }

    public static PropertyEnum lookupPropertyEnum(String declaringClassName, String propertyEnumStaticFieldName) {
        try {
            Class<?> declaringClass = Class.forName(declaringClassName);
            return (PropertyEnum)ReflectionHelper.findField(declaringClass, (String[])new String[]{propertyEnumStaticFieldName}).get(declaringClass);
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error reflecting PropertyEnum on %s.%s: %s", (Object)declaringClassName, (Object)propertyEnumStaticFieldName, (Object)LogFormatter.toPartialString(e));
            return null;
        }
    }

    public static Method lookupMethod(PropertyEnum propertyEnum, String methodName, Class ... methodArgTypes) {
        if (propertyEnum != null) {
            return ModPropertyEnum.lookupMethod(propertyEnum.func_177699_b().getName(), methodName, methodArgTypes);
        }
        return null;
    }

    public static Method lookupMethod(String declaringClassName, String methodName, Class ... methodArgTypes) {
        try {
            Class<?> declaringClass = Class.forName(declaringClassName);
            return ReflectionHelper.findMethod(declaringClass, (String)methodName, null, (Class[])methodArgTypes);
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error reflecting method %s.%s(): %s", (Object)declaringClassName, (Object)methodName, (Object)LogFormatter.toPartialString(e));
            return null;
        }
    }

    public PropertyEnum getPropertyEnum() {
        return this.propertyEnum;
    }

    public boolean isValid() {
        return this.valid;
    }

    @Nullable
    public T getValue(IBlockState blockState, Object ... args2) {
        if (this.valid) {
            try {
                Comparable enumValue = (Comparable)blockState.func_177228_b().get((Object)this.propertyEnum);
                if (enumValue != null) {
                    return (T)this.method.invoke((Object)enumValue, args2);
                }
            }
            catch (Exception e) {
                logger.error("Error using mod PropertyEnum: " + LogFormatter.toPartialString(e));
            }
        }
        return null;
    }

    @Nullable
    public static <T> T getFirstValue(Collection<ModPropertyEnum<T>> modPropertyEnums, IBlockState blockState, Object ... args2) {
        for (ModPropertyEnum<T> modPropertyEnum : modPropertyEnums) {
            T result = modPropertyEnum.getValue(blockState, args2);
            if (result == null) continue;
            return result;
        }
        return null;
    }
}

