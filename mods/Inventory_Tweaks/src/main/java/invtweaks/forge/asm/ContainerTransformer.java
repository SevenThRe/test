/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package invtweaks.forge.asm;

import com.google.common.collect.Lists;
import invtweaks.forge.asm.ASMHelper;
import invtweaks.forge.asm.compatibility.CompatibilityConfigLoader;
import invtweaks.forge.asm.compatibility.ContainerInfo;
import invtweaks.forge.asm.compatibility.MethodInfo;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ContainerTransformer
implements IClassTransformer {
    private static final String VALID_INVENTORY_METHOD = "invtweaks$validInventory";
    private static final String VALID_CHEST_METHOD = "invtweaks$validChest";
    private static final String LARGE_CHEST_METHOD = "invtweaks$largeChest";
    private static final String SHOW_BUTTONS_METHOD = "invtweaks$showButtons";
    private static final String ROW_SIZE_METHOD = "invtweaks$rowSize";
    private static final String SLOT_MAP_METHOD = "invtweaks$slotMap";
    private static final String CONTAINER_CLASS_INTERNAL = "net/minecraft/inventory/Container";
    private static final String SLOT_MAPS_VANILLA_CLASS = "invtweaks/container/VanillaSlotMaps";
    private static final String ANNOTATION_CHEST_CONTAINER = "Linvtweaks/api/container/ChestContainer;";
    private static final String ANNOTATION_CHEST_CONTAINER_ROW_CALLBACK = "Linvtweaks/api/container/ChestContainer$RowSizeCallback;";
    private static final String ANNOTATION_CHEST_CONTAINER_LARGE_CALLBACK = "Linvtweaks/api/container/ChestContainer$IsLargeCallback;";
    private static final String ANNOTATION_INVENTORY_CONTAINER = "Linvtweaks/api/container/InventoryContainer;";
    private static final String ANNOTATION_IGNORE_CONTAINER = "Linvtweaks/api/container/IgnoreContainer;";
    private static final String ANNOTATION_CONTAINER_SECTION_CALLBACK = "Linvtweaks/api/container/ContainerSectionCallback;";
    private static List<String> uninterestingPackages = Lists.newArrayList((Object[])new String[]{"net.minecraft.", "net.minecraftforge.", "joptsimple.", "com.mojang.", "com.google.gson.", "io.netty.", "oshi.", "com.sun.jna.", "com.ibm.icu.", "org.slf4j.", "javassist.", "gnu.trove.", "paulscode.sound.", "com.jcraft.jogg.", "com.jcraft.jorbis.", "it.unimi.dsi.fastutil."});
    private static final Logger logger = LogManager.getLogger();
    @NotNull
    private static Map<String, ContainerInfo> standardClasses = new HashMap<String, ContainerInfo>();
    @NotNull
    private static Map<String, ContainerInfo> configClasses = new HashMap<String, ContainerInfo>();

    public ContainerTransformer() {
        ContainerTransformer.lateInit();
    }

    private static void transformContainer(@NotNull ClassNode clazz, @NotNull ContainerInfo info) {
        ASMHelper.generateBooleanMethodConst(clazz, SHOW_BUTTONS_METHOD, info.showButtons);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_INVENTORY_METHOD, info.validInventory);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, info.validChest);
        if (info.largeChestMethod != null) {
            if (info.largeChestMethod.isStatic) {
                ASMHelper.generateForwardingToStaticMethod(clazz, LARGE_CHEST_METHOD, info.largeChestMethod.methodName, info.largeChestMethod.methodType.getReturnType(), info.largeChestMethod.methodClass, info.largeChestMethod.methodType.getArgumentTypes()[0]);
            } else {
                ASMHelper.generateSelfForwardingMethod(clazz, LARGE_CHEST_METHOD, info.largeChestMethod.methodName, info.largeChestMethod.methodType.getReturnType());
            }
        } else {
            ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, info.largeChest);
        }
        if (info.rowSizeMethod != null) {
            if (info.rowSizeMethod.isStatic) {
                ASMHelper.generateForwardingToStaticMethod(clazz, ROW_SIZE_METHOD, info.rowSizeMethod.methodName, info.rowSizeMethod.methodType.getReturnType(), info.rowSizeMethod.methodClass, info.rowSizeMethod.methodType.getArgumentTypes()[0]);
            } else {
                ASMHelper.generateSelfForwardingMethod(clazz, ROW_SIZE_METHOD, info.rowSizeMethod.methodName, info.rowSizeMethod.methodType.getReturnType());
            }
        } else {
            ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, info.rowSize);
        }
        if (info.slotMapMethod.isStatic) {
            ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, info.slotMapMethod.methodName, info.slotMapMethod.methodType.getReturnType(), info.slotMapMethod.methodClass, info.slotMapMethod.methodType.getArgumentTypes()[0]);
        } else {
            ASMHelper.generateSelfForwardingMethod(clazz, SLOT_MAP_METHOD, info.slotMapMethod.methodName, info.slotMapMethod.methodType.getReturnType());
        }
    }

    private static void transformBaseContainer(@NotNull ClassNode clazz) {
        ASMHelper.generateBooleanMethodConst(clazz, SHOW_BUTTONS_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_INVENTORY_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, false);
        ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, (short)9);
        ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, "unknownContainerSlots", Type.getObjectType((String)"java/util/Map"), Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS), Type.getObjectType((String)CONTAINER_CLASS_INTERNAL));
    }

    private static void transformCreativeContainer(@NotNull ClassNode clazz) {
        ASMHelper.generateForwardingToStaticMethod(clazz, SHOW_BUTTONS_METHOD, "containerCreativeIsInventory", Type.BOOLEAN_TYPE, Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
        ASMHelper.generateForwardingToStaticMethod(clazz, VALID_INVENTORY_METHOD, "containerCreativeIsInventory", Type.BOOLEAN_TYPE, Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, false);
        ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, (short)9);
        ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, "containerCreativeSlots", Type.getObjectType((String)"java/util/Map"), Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
    }

    private static void transformHorseInventoryContainer(@NotNull ClassNode clazz) {
        ASMHelper.generateForwardingToStaticMethod(clazz, SHOW_BUTTONS_METHOD, "containerHorseIsInventory", Type.BOOLEAN_TYPE, Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
        ASMHelper.generateForwardingToStaticMethod(clazz, VALID_INVENTORY_METHOD, "containerHorseIsInventory", Type.BOOLEAN_TYPE, Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, true);
        ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, false);
        ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, (short)5);
        ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, "containerHorseSlots", Type.getObjectType((String)"java/util/Map"), Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS));
    }

    private static void transformInvTweaksObfuscation(@NotNull ClassNode clazz) {
        Type containertype = Type.getObjectType((String)CONTAINER_CLASS_INTERNAL);
        for (MethodNode method : clazz.methods) {
            if ("isValidChest".equals(method.name)) {
                ASMHelper.replaceSelfForwardingMethod(method, VALID_CHEST_METHOD, containertype);
                continue;
            }
            if ("isValidInventory".equals(method.name)) {
                ASMHelper.replaceSelfForwardingMethod(method, VALID_INVENTORY_METHOD, containertype);
                continue;
            }
            if ("showButtons".equals(method.name)) {
                ASMHelper.replaceSelfForwardingMethod(method, SHOW_BUTTONS_METHOD, containertype);
                continue;
            }
            if ("getSpecialChestRowSize".equals(method.name)) {
                ASMHelper.replaceSelfForwardingMethod(method, ROW_SIZE_METHOD, containertype);
                continue;
            }
            if ("getContainerSlotMap".equals(method.name)) {
                ASMHelper.replaceSelfForwardingMethod(method, SLOT_MAP_METHOD, containertype);
                continue;
            }
            if (!"isLargeChest".equals(method.name)) continue;
            ASMHelper.replaceSelfForwardingMethod(method, LARGE_CHEST_METHOD, containertype);
        }
    }

    private static void transformTextField(@NotNull ClassNode clazz) {
        for (MethodNode method : clazz.methods) {
            if (!"setFocused".equals(method.name) && !"setFocused".equals(method.name) || !"(Z)V".equals(method.desc)) continue;
            InsnList code = method.instructions;
            AbstractInsnNode returnNode = null;
            for (AbstractInsnNode insn : code) {
                if (insn.getOpcode() != 177) continue;
                returnNode = insn;
                break;
            }
            if (returnNode != null) {
                code.insertBefore(returnNode, (AbstractInsnNode)new VarInsnNode(21, 1));
                code.insertBefore(returnNode, (AbstractInsnNode)new MethodInsnNode(184, "invtweaks/forge/InvTweaksMod", "setTextboxModeStatic", "(Z)V", false));
                logger.info("InvTweaks: successfully transformed setFocused/setFocused");
                continue;
            }
            logger.fatal("InvTweaks: unable to find return in setFocused/setFocused");
        }
    }

    @NotNull
    public static MethodInfo getVanillaSlotMapInfo(String name) {
        return ContainerTransformer.getSlotMapInfo(Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS), name, true);
    }

    @NotNull
    private static MethodInfo getSlotMapInfo(Type mClass, String name, boolean isStatic) {
        return new MethodInfo(Type.getMethodType((Type)Type.getObjectType((String)"java/util/Map"), (Type[])new Type[]{Type.getObjectType((String)CONTAINER_CLASS_INTERNAL)}), mClass, name, isStatic);
    }

    private static void lateInit() {
        standardClasses.put("net.minecraft.inventory.ContainerPlayer", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerPlayerSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerMerchant", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerRepair", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerRepairSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerHopper", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerBeacon", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerBrewingStand", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerBrewingSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerWorkbench", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerWorkbenchSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerEnchantment", new ContainerInfo(false, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerEnchantmentSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerFurnace", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerFurnaceSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerDispenser", new ContainerInfo(true, false, true, 3, ContainerTransformer.getVanillaSlotMapInfo("containerChestDispenserSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerChest", new ContainerInfo(true, false, true, ContainerTransformer.getVanillaSlotMapInfo("containerChestDispenserSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerShulkerBox", new ContainerInfo(true, false, true, ContainerTransformer.getVanillaSlotMapInfo("containerChestDispenserSlots")));
        try {
            configClasses = CompatibilityConfigLoader.load("config/InvTweaksCompatibility.xml");
        }
        catch (FileNotFoundException ex) {
            configClasses = new HashMap<String, ContainerInfo>();
        }
        catch (Exception ex) {
            configClasses = new HashMap<String, ContainerInfo>();
            ex.printStackTrace();
        }
    }

    private static MethodNode findAnnotatedMethod(@NotNull ClassNode cn, @NotNull String annotationDesc) {
        for (MethodNode method : cn.methods) {
            if (method.visibleAnnotations == null) continue;
            for (AnnotationNode methodAnnotation : method.visibleAnnotations) {
                if (!annotationDesc.equals(methodAnnotation.desc)) continue;
                return method;
            }
        }
        return null;
    }

    private static byte[] doTransform(byte[] bytes, Consumer<ClassNode> transform) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode(262144);
        ClassWriter cw = new ClassWriter(3);
        cr.accept((ClassVisitor)cn, 0);
        transform.accept(cn);
        cn.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }

    @Nullable
    public byte[] transform(String name, String transformedName, @Nullable byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        if ("net.minecraft.client.gui.GuiTextField".equals(transformedName)) {
            return ContainerTransformer.doTransform(bytes, ContainerTransformer::transformTextField);
        }
        if ("net.minecraft.inventory.Container".equals(transformedName)) {
            return ContainerTransformer.doTransform(bytes, ContainerTransformer::transformBaseContainer);
        }
        if ("net.minecraft.client.gui.inventory.GuiContainerCreative$ContainerCreative".equals(transformedName)) {
            return ContainerTransformer.doTransform(bytes, ContainerTransformer::transformCreativeContainer);
        }
        if ("net.minecraft.inventory.ContainerHorseInventory".equals(transformedName)) {
            return ContainerTransformer.doTransform(bytes, ContainerTransformer::transformHorseInventoryContainer);
        }
        if ("invtweaks.InvTweaksObfuscation".equals(transformedName)) {
            return ContainerTransformer.doTransform(bytes, ContainerTransformer::transformInvTweaksObfuscation);
        }
        ContainerInfo standardInfo = standardClasses.get(transformedName);
        if (standardInfo != null) {
            return ContainerTransformer.doTransform(bytes, cn -> ContainerTransformer.transformContainer(cn, standardInfo));
        }
        ContainerInfo configInfo = configClasses.get(transformedName);
        if (configInfo != null) {
            return ContainerTransformer.doTransform(bytes, cn -> ContainerTransformer.transformContainer(cn, configInfo));
        }
        for (String uninterestingPackage : uninterestingPackages) {
            if (!transformedName.startsWith(uninterestingPackage)) continue;
            return bytes;
        }
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn2 = new ClassNode(262144);
        ClassWriter cw = new ClassWriter(3);
        cr.accept((ClassVisitor)cn2, 0);
        if (cn2.visibleAnnotations != null) {
            for (AnnotationNode annotation : cn2.visibleAnnotations) {
                if (annotation == null) continue;
                ContainerInfo apiInfo = null;
                if (ANNOTATION_CHEST_CONTAINER.equals(annotation.desc)) {
                    MethodNode large_method;
                    short rowSize = 9;
                    boolean isLargeChest = false;
                    boolean showButtons = true;
                    if (annotation.values != null) {
                        block12: for (int i = 0; i < annotation.values.size(); i += 2) {
                            String valueName = (String)annotation.values.get(i);
                            Object value = annotation.values.get(i + 1);
                            switch (valueName) {
                                case "rowSize": {
                                    rowSize = (short)((Integer)value).intValue();
                                    continue block12;
                                }
                                case "isLargeChest": {
                                    isLargeChest = (Boolean)value;
                                    continue block12;
                                }
                                case "showButtons": {
                                    showButtons = (Boolean)value;
                                }
                            }
                        }
                    }
                    apiInfo = new ContainerInfo(showButtons, false, true, isLargeChest, rowSize);
                    MethodNode row_method = ContainerTransformer.findAnnotatedMethod(cn2, ANNOTATION_CHEST_CONTAINER_ROW_CALLBACK);
                    if (row_method != null) {
                        apiInfo.rowSizeMethod = new MethodInfo(Type.getMethodType((String)row_method.desc), Type.getObjectType((String)cn2.name), row_method.name);
                    }
                    if ((large_method = ContainerTransformer.findAnnotatedMethod(cn2, ANNOTATION_CHEST_CONTAINER_LARGE_CALLBACK)) != null) {
                        apiInfo.largeChestMethod = new MethodInfo(Type.getMethodType((String)large_method.desc), Type.getObjectType((String)cn2.name), large_method.name);
                    }
                } else if (ANNOTATION_INVENTORY_CONTAINER.equals(annotation.desc)) {
                    boolean showOptions = true;
                    if (annotation.values != null) {
                        for (int i = 0; i < annotation.values.size(); i += 2) {
                            String valueName = (String)annotation.values.get(i);
                            Object value = annotation.values.get(i + 1);
                            if (!"showOptions".equals(valueName)) continue;
                            showOptions = (Boolean)value;
                        }
                    }
                    apiInfo = new ContainerInfo(showOptions, true, false);
                } else if (ANNOTATION_IGNORE_CONTAINER.equals(annotation.desc)) {
                    ContainerTransformer.transformBaseContainer(cn2);
                    cn2.accept((ClassVisitor)cw);
                    return cw.toByteArray();
                }
                if (apiInfo == null) continue;
                MethodNode method = ContainerTransformer.findAnnotatedMethod(cn2, ANNOTATION_CONTAINER_SECTION_CALLBACK);
                if (method != null) {
                    apiInfo.slotMapMethod = new MethodInfo(Type.getMethodType((String)method.desc), Type.getObjectType((String)cn2.name), method.name);
                }
                ContainerTransformer.transformContainer(cn2, apiInfo);
                cn2.accept((ClassVisitor)cw);
                return cw.toByteArray();
            }
        }
        return bytes;
    }
}

