/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.CLongPointer;
import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.Adapter;
import org.bytedeco.javacpp.annotation.Allocator;
import org.bytedeco.javacpp.annotation.ArrayAllocator;
import org.bytedeco.javacpp.annotation.AsUtf16;
import org.bytedeco.javacpp.annotation.ByPtr;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByPtrRef;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.CriticalRegion;
import org.bytedeco.javacpp.annotation.Function;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoDeallocator;
import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.Raw;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.javacpp.annotation.ValueSetter;
import org.bytedeco.javacpp.annotation.Virtual;
import org.bytedeco.javacpp.tools.AdapterInformation;
import org.bytedeco.javacpp.tools.IndexedSet;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacpp.tools.MethodInformation;
import sun.misc.Unsafe;

public class Generator {
    static final String JNI_VERSION = "JNI_VERSION_1_6";
    static final List<Class> baseClasses = Arrays.asList(Loader.class, Pointer.class, BytePointer.class, ShortPointer.class, IntPointer.class, LongPointer.class, FloatPointer.class, DoublePointer.class, CharPointer.class, BooleanPointer.class, PointerPointer.class, BoolPointer.class, CLongPointer.class, SizeTPointer.class);
    final Logger logger;
    final java.util.Properties properties;
    final String encoding;
    PrintWriter out;
    PrintWriter out2;
    PrintWriter jniConfigOut;
    PrintWriter reflectConfigOut;
    Map<String, String> callbacks;
    IndexedSet<Class> functions;
    IndexedSet<Class> deallocators;
    IndexedSet<Class> arrayDeallocators;
    IndexedSet<Class> jclasses;
    Map<Class, Set<String>> members;
    Map<Class, Set<String>> virtualFunctions;
    Map<Class, Set<String>> virtualMembers;
    Map<Method, MethodInformation> annotationCache;
    boolean mayThrowExceptions;
    boolean usesAdapters;
    boolean passesStrings;
    boolean accessesEnums;

    public Generator(Logger logger, java.util.Properties properties) {
        this(logger, properties, null);
    }

    public Generator(Logger logger, java.util.Properties properties, String encoding) {
        this.logger = logger;
        this.properties = properties;
        this.encoding = encoding;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean generate(String sourceFilename, String jniConfigFilename, String reflectConfigFilename, String headerFilename, String loadSuffix, String baseLoadSuffix, String classPath, Class<?> ... classes) throws IOException {
        try {
            this.out = new PrintWriter(new Writer(){

                @Override
                public void write(char[] cbuf, int off, int len) {
                }

                @Override
                public void flush() {
                }

                @Override
                public void close() {
                }
            });
            this.reflectConfigOut = null;
            this.jniConfigOut = null;
            this.out2 = null;
            this.callbacks = new LinkedHashMap<String, String>();
            this.functions = new IndexedSet();
            this.deallocators = new IndexedSet();
            this.arrayDeallocators = new IndexedSet();
            this.jclasses = new IndexedSet();
            this.members = new LinkedHashMap<Class, Set<String>>();
            this.virtualFunctions = new LinkedHashMap<Class, Set<String>>();
            this.virtualMembers = new LinkedHashMap<Class, Set<String>>();
            this.annotationCache = new LinkedHashMap<Method, MethodInformation>();
            this.mayThrowExceptions = false;
            this.usesAdapters = false;
            this.passesStrings = false;
            if (baseLoadSuffix == null || baseLoadSuffix.isEmpty()) {
                for (Class cls : baseClasses) {
                    this.jclasses.index(cls);
                }
            }
            if (this.classes(true, true, true, true, loadSuffix, baseLoadSuffix, classPath, classes)) {
                File sourceFile = new File(sourceFilename);
                File sourceDir = sourceFile.getParentFile();
                if (sourceDir != null) {
                    sourceDir.mkdirs();
                }
                PrintWriter printWriter = this.out = this.encoding != null ? new PrintWriter(sourceFile, this.encoding) : new PrintWriter(sourceFile);
                if (headerFilename != null) {
                    this.logger.info("Generating " + headerFilename);
                    File headerFile = new File(headerFilename);
                    File headerDir = headerFile.getParentFile();
                    if (headerDir != null) {
                        headerDir.mkdirs();
                    }
                    PrintWriter printWriter2 = this.out2 = this.encoding != null ? new PrintWriter(headerFile, this.encoding) : new PrintWriter(headerFile);
                }
                if (jniConfigFilename != null) {
                    this.logger.info("Generating " + jniConfigFilename);
                    File jniConfigFile = new File(jniConfigFilename);
                    File jniConfigDir = jniConfigFile.getParentFile();
                    if (jniConfigDir != null) {
                        jniConfigDir.mkdirs();
                    }
                    PrintWriter printWriter3 = this.jniConfigOut = this.encoding != null ? new PrintWriter(jniConfigFile, this.encoding) : new PrintWriter(jniConfigFile);
                }
                if (reflectConfigFilename != null) {
                    this.logger.info("Generating " + reflectConfigFilename);
                    File reflectConfigFile = new File(reflectConfigFilename);
                    File reflectConfigDir = reflectConfigFile.getParentFile();
                    if (reflectConfigDir != null) {
                        reflectConfigDir.mkdirs();
                    }
                    this.reflectConfigOut = this.encoding != null ? new PrintWriter(reflectConfigFile, this.encoding) : new PrintWriter(reflectConfigFile);
                }
                boolean bl2 = this.classes(this.mayThrowExceptions, this.usesAdapters, this.passesStrings, this.accessesEnums, loadSuffix, baseLoadSuffix, classPath, classes);
                return bl2;
            }
            boolean bl3 = false;
            return bl3;
        }
        finally {
            if (this.out != null) {
                this.out.close();
            }
            if (this.out2 != null) {
                this.out2.close();
            }
            if (this.jniConfigOut != null) {
                this.jniConfigOut.close();
            }
            if (this.reflectConfigOut != null) {
                this.reflectConfigOut.close();
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    boolean classes(boolean handleExceptions, boolean defineAdapters, boolean convertStrings, boolean declareEnums, String loadSuffix, String baseLoadSuffix, String classPath, Class<?> ... classes) {
        Class<?>[] memberIterator;
        Object typeName;
        String name;
        Class<?> nativeDeallocator;
        Class<?> deallocator;
        String version = Generator.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "unknown";
        }
        String warning = "// Generated by JavaCPP version " + version + ": DO NOT EDIT THIS FILE";
        this.out.println(warning);
        this.out.println();
        if (this.out2 != null) {
            this.out2.println(warning);
            this.out2.println();
        }
        ClassProperties clsProperties = Loader.loadProperties(classes, this.properties, true);
        for (String s2 : clsProperties.get("platform.pragma")) {
            this.out.println("#pragma " + s2);
        }
        for (String s2 : clsProperties.get("platform.define")) {
            this.out.println("#define " + s2);
        }
        this.out.println();
        this.out.println("#ifdef _WIN32");
        this.out.println("    #define _JAVASOFT_JNI_MD_H_");
        this.out.println();
        this.out.println("    #define JNIEXPORT __declspec(dllexport)");
        this.out.println("    #define JNIIMPORT __declspec(dllimport)");
        this.out.println("    #define JNICALL __stdcall");
        this.out.println();
        this.out.println("    typedef int jint;");
        this.out.println("    typedef long long jlong;");
        this.out.println("    typedef signed char jbyte;");
        this.out.println("#elif defined(__GNUC__) && !defined(__ANDROID__)");
        this.out.println("    #define _JAVASOFT_JNI_MD_H_");
        this.out.println();
        this.out.println("    #define JNIEXPORT __attribute__((visibility(\"default\")))");
        this.out.println("    #define JNIIMPORT");
        this.out.println("    #define JNICALL");
        this.out.println();
        this.out.println("    typedef int jint;");
        this.out.println("    typedef long long jlong;");
        this.out.println("    typedef signed char jbyte;");
        this.out.println("#endif");
        this.out.println();
        this.out.println("#include <jni.h>");
        if (this.out2 != null) {
            this.out2.println("#include <jni.h>");
        }
        this.out.println();
        this.out.println("#ifdef __ANDROID__");
        this.out.println("    #include <android/log.h>");
        this.out.println("#elif defined(__APPLE__) && defined(__OBJC__)");
        this.out.println("    #include <TargetConditionals.h>");
        this.out.println("    #include <Foundation/Foundation.h>");
        this.out.println("#endif");
        this.out.println();
        this.out.println("#ifdef __linux__");
        this.out.println("    #include <malloc.h>");
        this.out.println("    #include <sys/types.h>");
        this.out.println("    #include <sys/stat.h>");
        this.out.println("    #include <sys/sysinfo.h>");
        this.out.println("    #include <fcntl.h>");
        this.out.println("    #include <unistd.h>");
        this.out.println("    #include <dlfcn.h>");
        this.out.println("    #include <link.h>");
        this.out.println("    #include <pthread.h>");
        this.out.println("#elif defined(__APPLE__)");
        this.out.println("    #include <sys/types.h>");
        this.out.println("    #include <sys/sysctl.h>");
        this.out.println("    #include <mach/mach_init.h>");
        this.out.println("    #include <mach/mach_host.h>");
        this.out.println("    #include <mach/task.h>");
        this.out.println("    #include <unistd.h>");
        this.out.println("    #include <dlfcn.h>");
        this.out.println("    #include <mach-o/dyld.h>");
        this.out.println("    #include <pthread.h>");
        this.out.println("#elif defined(_WIN32) && !defined(NO_WINDOWS_H)");
        this.out.println("    #define NOMINMAX");
        this.out.println("    #include <windows.h>");
        this.out.println("    #include <psapi.h>");
        this.out.println("#elif defined(_WIN32)");
        this.out.println("    extern \"C\" unsigned long __stdcall GetCurrentThreadId();");
        this.out.println("#endif");
        this.out.println();
        this.out.println("#if defined(__ANDROID__) || TARGET_OS_IPHONE");
        this.out.println("    #define NewWeakGlobalRef(obj) NewGlobalRef(obj)");
        this.out.println("    #define DeleteWeakGlobalRef(obj) DeleteGlobalRef(obj)");
        this.out.println("#endif");
        this.out.println();
        this.out.println("#include <limits.h>");
        this.out.println("#include <stddef.h>");
        this.out.println("#ifndef _WIN32");
        this.out.println("    #include <stdint.h>");
        this.out.println("#endif");
        this.out.println("#include <stdio.h>");
        this.out.println("#include <stdlib.h>");
        this.out.println("#include <string.h>");
        this.out.println("#include <exception>");
        this.out.println("#include <memory>");
        this.out.println("#include <new>");
        if (baseLoadSuffix == null || baseLoadSuffix.isEmpty()) {
            this.out.println();
            this.out.println("#if defined(NATIVE_ALLOCATOR) && defined(NATIVE_DEALLOCATOR)");
            this.out.println("    void* operator new(std::size_t size, const std::nothrow_t&) throw() {");
            this.out.println("        return NATIVE_ALLOCATOR(size);");
            this.out.println("    }");
            this.out.println("    void* operator new[](std::size_t size, const std::nothrow_t&) throw() {");
            this.out.println("        return NATIVE_ALLOCATOR(size);");
            this.out.println("    }");
            this.out.println("    void* operator new(std::size_t size) throw(std::bad_alloc) {");
            this.out.println("        return NATIVE_ALLOCATOR(size);");
            this.out.println("    }");
            this.out.println("    void* operator new[](std::size_t size) throw(std::bad_alloc) {");
            this.out.println("        return NATIVE_ALLOCATOR(size);");
            this.out.println("    }");
            this.out.println("    void operator delete(void* ptr) throw() {");
            this.out.println("        NATIVE_DEALLOCATOR(ptr);");
            this.out.println("    }");
            this.out.println("    void operator delete[](void* ptr) throw() {");
            this.out.println("        NATIVE_DEALLOCATOR(ptr);");
            this.out.println("    }");
            this.out.println("#endif");
        }
        this.out.println();
        this.out.println("#define jlong_to_ptr(a) ((void*)(uintptr_t)(a))");
        this.out.println("#define ptr_to_jlong(a) ((jlong)(uintptr_t)(a))");
        this.out.println();
        this.out.println("#if defined(_MSC_VER)");
        this.out.println("    #define JavaCPP_noinline __declspec(noinline)");
        this.out.println("    #define JavaCPP_hidden /* hidden by default */");
        this.out.println("#elif defined(__GNUC__)");
        this.out.println("    #define JavaCPP_noinline __attribute__((noinline)) __attribute__ ((unused))");
        this.out.println("    #define JavaCPP_hidden   __attribute__((visibility(\"hidden\"))) __attribute__ ((unused))");
        this.out.println("#else");
        this.out.println("    #define JavaCPP_noinline");
        this.out.println("    #define JavaCPP_hidden");
        this.out.println("#endif");
        this.out.println("#if __cplusplus >= 201103L || _MSC_VER >= 1900");
        this.out.println("    #define JavaCPP_override override");
        this.out.println("#else");
        this.out.println("    #define JavaCPP_override");
        this.out.println("#endif");
        this.out.println();
        if (loadSuffix == null) {
            loadSuffix = "";
            String p2 = clsProperties.getProperty("platform.library.static", "false").toLowerCase();
            if (p2.equals("true") || p2.equals("t") || p2.equals("")) {
                loadSuffix = "_" + clsProperties.getProperty("platform.library");
            }
        }
        if (classes != null) {
            List<String> exclude = clsProperties.get("platform.exclude");
            List[] include = new List[]{clsProperties.get("platform.include"), clsProperties.get("platform.cinclude")};
            for (int i2 = 0; i2 < include.length; ++i2) {
                if (include[i2] == null || include[i2].size() <= 0) continue;
                if (i2 == 1) {
                    this.out.println("extern \"C\" {");
                    if (this.out2 != null) {
                        this.out2.println("#ifdef __cplusplus");
                        this.out2.println("extern \"C\" {");
                        this.out2.println("#endif");
                    }
                }
                for (String s3 : include[i2]) {
                    if (exclude.contains(s3)) continue;
                    String line = "#include ";
                    if (!s3.startsWith("<") && !s3.startsWith("\"")) {
                        line = line + '\"';
                    }
                    line = line + s3;
                    if (!s3.endsWith(">") && !s3.endsWith("\"")) {
                        line = line + '\"';
                    }
                    this.out.println(line);
                    if (this.out2 == null) continue;
                    this.out2.println(line);
                }
                if (i2 == 1) {
                    this.out.println("}");
                    if (this.out2 != null) {
                        this.out2.println("#ifdef __cplusplus");
                        this.out2.println("}");
                        this.out2.println("#endif");
                    }
                }
                this.out.println();
            }
        }
        this.out.println("static JavaVM* JavaCPP_vm = NULL;");
        this.out.println("static bool JavaCPP_haveAllocObject = false;");
        this.out.println("static bool JavaCPP_haveNonvirtual = false;");
        this.out.println("static const char* JavaCPP_classNames[" + this.jclasses.size() + "] = {");
        Iterator<Class> classIterator = this.jclasses.iterator();
        int maxMemberSize = 0;
        while (classIterator.hasNext()) {
            Set<String> m3;
            Class c2 = classIterator.next();
            this.out.print("        \"" + c2.getName().replace('.', '/') + "\"");
            if (classIterator.hasNext()) {
                this.out.println(",");
            }
            if ((m3 = this.members.get(c2)) == null || m3.size() <= maxMemberSize) continue;
            maxMemberSize = m3.size();
        }
        this.out.println(" };");
        this.out.println("static jclass JavaCPP_classes[" + this.jclasses.size() + "] = { NULL };");
        this.out.println("static jfieldID JavaCPP_addressFID = NULL;");
        this.out.println("static jfieldID JavaCPP_positionFID = NULL;");
        this.out.println("static jfieldID JavaCPP_limitFID = NULL;");
        this.out.println("static jfieldID JavaCPP_capacityFID = NULL;");
        this.out.println("static jfieldID JavaCPP_deallocatorFID = NULL;");
        this.out.println("static jfieldID JavaCPP_ownerAddressFID = NULL;");
        if (declareEnums) {
            this.out.println("static jfieldID JavaCPP_booleanValueFID = NULL;");
            this.out.println("static jfieldID JavaCPP_byteValueFID = NULL;");
            this.out.println("static jfieldID JavaCPP_shortValueFID = NULL;");
            this.out.println("static jfieldID JavaCPP_intValueFID = NULL;");
            this.out.println("static jfieldID JavaCPP_longValueFID = NULL;");
        }
        this.out.println("static jmethodID JavaCPP_initMID = NULL;");
        this.out.println("static jmethodID JavaCPP_arrayMID = NULL;");
        this.out.println("static jmethodID JavaCPP_arrayOffsetMID = NULL;");
        this.out.println("static jfieldID JavaCPP_bufferPositionFID = NULL;");
        this.out.println("static jfieldID JavaCPP_bufferLimitFID = NULL;");
        this.out.println("static jfieldID JavaCPP_bufferCapacityFID = NULL;");
        this.out.println("static jmethodID JavaCPP_stringMID = NULL;");
        this.out.println("static jmethodID JavaCPP_getBytesMID = NULL;");
        this.out.println("static jmethodID JavaCPP_toStringMID = NULL;");
        this.out.println("#ifdef STRING_BYTES_CHARSET");
        this.out.println("#ifdef MODIFIED_UTF8_STRING");
        this.out.println("#pragma message (\"warning: STRING_BYTES_CHARSET and MODIFIED_UTF8_STRING are mutually exclusive.\")");
        this.out.println("#endif");
        this.out.println("static jobject JavaCPP_stringBytesCharset = NULL;");
        this.out.println("static jmethodID JavaCPP_stringWithCharsetMID = NULL;");
        this.out.println("static jmethodID JavaCPP_getBytesWithCharsetMID = NULL;");
        this.out.println("#endif");
        this.out.println();
        this.out.println("static inline void JavaCPP_log(const char* fmt, ...) {");
        this.out.println("    va_list ap;");
        this.out.println("    va_start(ap, fmt);");
        this.out.println("#ifdef __ANDROID__");
        this.out.println("    __android_log_vprint(ANDROID_LOG_ERROR, \"javacpp\", fmt, ap);");
        this.out.println("#elif defined(__APPLE__) && defined(__OBJC__)");
        this.out.println("    NSLogv([NSString stringWithUTF8String:fmt], ap);");
        this.out.println("#else");
        this.out.println("    vfprintf(stderr, fmt, ap);");
        this.out.println("    fprintf(stderr, \"\\n\");");
        this.out.println("#endif");
        this.out.println("    va_end(ap);");
        this.out.println("}");
        this.out.println();
        this.out.println("#if !defined(NO_JNI_DETACH_THREAD) && (defined(__linux__) || defined(__APPLE__))");
        this.out.println("    static pthread_key_t JavaCPP_current_env;");
        this.out.println("    static JavaCPP_noinline void JavaCPP_detach_env(void *data) {");
        this.out.println("        if (JavaCPP_vm) {");
        this.out.println("            JavaCPP_vm->DetachCurrentThread();");
        this.out.println("        }");
        this.out.println("    }");
        this.out.println("    static JavaCPP_noinline void JavaCPP_create_pthread_key(void) {");
        this.out.println("        pthread_key_create(&JavaCPP_current_env, JavaCPP_detach_env);");
        this.out.println("    }");
        this.out.println("#endif");
        this.out.println();
        if (baseLoadSuffix == null || baseLoadSuffix.isEmpty()) {
            this.out.println("static inline jboolean JavaCPP_trimMemory() {");
            this.out.println("#if defined(__linux__) && !defined(__ANDROID__)");
            this.out.println("    return (jboolean)malloc_trim(0);");
            this.out.println("#else");
            this.out.println("    return 0;");
            this.out.println("#endif");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jlong JavaCPP_physicalBytes() {");
            this.out.println("    jlong size = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    static int fd = open(\"/proc/self/statm\", O_RDONLY, 0);");
            this.out.println("    if (fd >= 0) {");
            this.out.println("        char line[256];");
            this.out.println("        char* s;");
            this.out.println("        int n;");
            this.out.println("        if ((n = pread(fd, line, sizeof(line), 0)) > 0 && (s = (char*)memchr(line, ' ', n)) != NULL) {");
            this.out.println("            size = (jlong)(atoll(s + 1) * getpagesize());");
            this.out.println("        }");
            this.out.println("        // no close(fd);");
            this.out.println("    }");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    task_basic_info info;");
            this.out.println("    mach_msg_type_number_t count = TASK_BASIC_INFO_COUNT;");
            this.out.println("    if (task_info(current_task(), TASK_BASIC_INFO, (task_info_t)&info, &count) == KERN_SUCCESS) {");
            this.out.println("        size = (jlong)info.resident_size;");
            this.out.println("    }");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    PROCESS_MEMORY_COUNTERS counters;");
            this.out.println("    if (GetProcessMemoryInfo(GetCurrentProcess(), &counters, sizeof(counters))) {");
            this.out.println("        size = (jlong)counters.WorkingSetSize;");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("    return size;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jlong JavaCPP_totalPhysicalBytes() {");
            this.out.println("    jlong size = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    struct sysinfo info;");
            this.out.println("    if (sysinfo(&info) == 0) {");
            this.out.println("        size = (jlong)info.totalram * info.mem_unit;");
            this.out.println("    }");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    size_t length = sizeof(size);");
            this.out.println("    sysctlbyname(\"hw.memsize\", &size, &length, NULL, 0);");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    MEMORYSTATUSEX status;");
            this.out.println("    status.dwLength = sizeof(status);");
            this.out.println("    if (GlobalMemoryStatusEx(&status)) {");
            this.out.println("        size = status.ullTotalPhys;");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("    return size;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jlong JavaCPP_availablePhysicalBytes() {");
            this.out.println("    jlong size = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    int fd = open(\"/proc/meminfo\", O_RDONLY, 0);");
            this.out.println("    if (fd >= 0) {");
            this.out.println("        char temp[4096];");
            this.out.println("        char *s;");
            this.out.println("        int n;");
            this.out.println("        if ((n = read(fd, temp, sizeof(temp))) > 0 && (s = (char*)memmem(temp, n, \"MemAvailable:\", 13)) != NULL) {");
            this.out.println("            size = (jlong)(atoll(s + 13) * 1024);");
            this.out.println("        }");
            this.out.println("        close(fd);");
            this.out.println("    }");
            this.out.println("    if (size == 0) {");
            this.out.println("        struct sysinfo info;");
            this.out.println("        if (sysinfo(&info) == 0) {");
            this.out.println("            size = (jlong)info.freeram * info.mem_unit;");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    vm_statistics_data_t info;");
            this.out.println("    mach_msg_type_number_t count = HOST_VM_INFO_COUNT;");
            this.out.println("    if (host_statistics(mach_host_self(), HOST_VM_INFO, (host_info_t)&info, &count) == KERN_SUCCESS) {");
            this.out.println("        size = (jlong)info.free_count * getpagesize();");
            this.out.println("    }");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    MEMORYSTATUSEX status;");
            this.out.println("    status.dwLength = sizeof(status);");
            this.out.println("    if (GlobalMemoryStatusEx(&status)) {");
            this.out.println("        size = status.ullAvailPhys;");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("    return size;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jint JavaCPP_totalProcessors() {");
            this.out.println("    jint total = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    total = sysconf(_SC_NPROCESSORS_CONF);");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    size_t length = sizeof(total);");
            this.out.println("    sysctlbyname(\"hw.logicalcpu_max\", &total, &length, NULL, 0);");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    SYSTEM_INFO info;");
            this.out.println("    GetSystemInfo(&info);");
            this.out.println("    total = info.dwNumberOfProcessors;");
            this.out.println("#endif");
            this.out.println("    return total;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jint JavaCPP_totalCores() {");
            this.out.println("    jint total = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    const int n = sysconf(_SC_NPROCESSORS_CONF);");
            this.out.println("    int pids[n], cids[n];");
            this.out.println("    for (int i = 0; i < n; i++) {");
            this.out.println("        int fd = 0, pid = 0, cid = 0;");
            this.out.println("        char temp[256];");
            this.out.println("        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/physical_package_id\", i);");
            this.out.println("        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {");
            this.out.println("            if (read(fd, temp, sizeof(temp)) > 0) {");
            this.out.println("                pid = atoi(temp);");
            this.out.println("            }");
            this.out.println("            close(fd);");
            this.out.println("        }");
            this.out.println("        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/core_id\", i);");
            this.out.println("        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {");
            this.out.println("            if (read(fd, temp, sizeof(temp)) > 0) {");
            this.out.println("                cid = atoi(temp);");
            this.out.println("            }");
            this.out.println("            close(fd);");
            this.out.println("        }");
            this.out.println("        bool found = false;");
            this.out.println("        for (int j = 0; j < total; j++) {");
            this.out.println("            if (pids[j] == pid && cids[j] == cid) {");
            this.out.println("                found = true;");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("        if (!found) {");
            this.out.println("            pids[total] = pid;");
            this.out.println("            cids[total] = cid;");
            this.out.println("            total++;");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    size_t length = sizeof(total);");
            this.out.println("    sysctlbyname(\"hw.physicalcpu_max\", &total, &length, NULL, 0);");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    SYSTEM_LOGICAL_PROCESSOR_INFORMATION *info = NULL;");
            this.out.println("    DWORD length = 0;");
            this.out.println("    BOOL success = GetLogicalProcessorInformation(info, &length);");
            this.out.println("    while (!success && GetLastError() == ERROR_INSUFFICIENT_BUFFER) {");
            this.out.println("        info = (SYSTEM_LOGICAL_PROCESSOR_INFORMATION*)realloc(info, length);");
            this.out.println("        success = GetLogicalProcessorInformation(info, &length);");
            this.out.println("    }");
            this.out.println("    if (success && info != NULL) {");
            this.out.println("        length /= sizeof(SYSTEM_LOGICAL_PROCESSOR_INFORMATION);");
            this.out.println("        for (DWORD i = 0; i < length; i++) {");
            this.out.println("            if (info[i].Relationship == RelationProcessorCore) {");
            this.out.println("                total++;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    free(info);");
            this.out.println("#endif");
            this.out.println("    return total;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline jint JavaCPP_totalChips() {");
            this.out.println("    jint total = 0;");
            this.out.println("#ifdef __linux__");
            this.out.println("    const int n = sysconf(_SC_NPROCESSORS_CONF);");
            this.out.println("    int pids[n];");
            this.out.println("    for (int i = 0; i < n; i++) {");
            this.out.println("        int fd = 0, pid = 0;");
            this.out.println("        char temp[256];");
            this.out.println("        sprintf(temp, \"/sys/devices/system/cpu/cpu%d/topology/physical_package_id\", i);");
            this.out.println("        if ((fd = open(temp, O_RDONLY, 0)) >= 0) {");
            this.out.println("            if (read(fd, temp, sizeof(temp)) > 0) {");
            this.out.println("                pid = atoi(temp);");
            this.out.println("            }");
            this.out.println("            close(fd);");
            this.out.println("        }");
            this.out.println("        bool found = false;");
            this.out.println("        for (int j = 0; j < total; j++) {");
            this.out.println("            if (pids[j] == pid) {");
            this.out.println("                found = true;");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("        if (!found) {");
            this.out.println("            pids[total] = pid;");
            this.out.println("            total++;");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    size_t length = sizeof(total);");
            this.out.println("    sysctlbyname(\"hw.packages\", &total, &length, NULL, 0);");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    SYSTEM_LOGICAL_PROCESSOR_INFORMATION *info = NULL;");
            this.out.println("    DWORD length = 0;");
            this.out.println("    BOOL success = GetLogicalProcessorInformation(info, &length);");
            this.out.println("    while (!success && GetLastError() == ERROR_INSUFFICIENT_BUFFER) {");
            this.out.println("        info = (SYSTEM_LOGICAL_PROCESSOR_INFORMATION*)realloc(info, length);");
            this.out.println("        success = GetLogicalProcessorInformation(info, &length);");
            this.out.println("    }");
            this.out.println("    if (success && info != NULL) {");
            this.out.println("        length /= sizeof(SYSTEM_LOGICAL_PROCESSOR_INFORMATION);");
            this.out.println("        for (DWORD i = 0; i < length; i++) {");
            this.out.println("            if (info[i].Relationship == RelationProcessorPackage) {");
            this.out.println("                total++;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    free(info);");
            this.out.println("#endif");
            this.out.println("    return total;");
            this.out.println("}");
            this.out.println();
            this.out.println("#if defined(__linux__) && !(defined(__ANDROID__) && defined(__arm__))");
            this.out.println("static int JavaCPP_dlcallback(dl_phdr_info *info, size_t size, void *data) {");
            this.out.println("    void *handle = dlopen(info->dlpi_name, RTLD_LAZY);");
            this.out.println("    if (handle != NULL) {");
            this.out.println("        void *address = dlsym(handle, ((char**)data)[0]);");
            this.out.println("        dlclose(handle);");
            this.out.println("        if (address != NULL) {");
            this.out.println("            ((void**)data)[1] = address;");
            this.out.println("            return 1;");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    return 0;");
            this.out.println("}");
            this.out.println("#endif");
            this.out.println();
            this.out.println("static JavaCPP_noinline jclass JavaCPP_getClass(JNIEnv* env, int i);");
            this.out.println("static inline void JavaCPP_loadGlobal(JNIEnv* env, jclass cls, const char* filename) {");
            this.out.println("#ifdef _WIN32");
            this.out.println("    HMODULE handle = LoadLibrary(filename);");
            this.out.println("    if (handle == NULL) {");
            this.out.println("        char temp[256];");
            this.out.println("        sprintf(temp, \"LoadLibrary() failed with 0x%lx\", GetLastError());");
            this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(UnsatisfiedLinkError.class) + "), temp);");
            this.out.println("    }");
            this.out.println("#else");
            this.out.println("    void *handle = dlopen(filename, RTLD_LAZY | RTLD_GLOBAL);");
            this.out.println("    if (handle == NULL) {");
            this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(UnsatisfiedLinkError.class) + "), dlerror());");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline void* JavaCPP_addressof(const char* name) {");
            this.out.println("    void *address = NULL;");
            this.out.println("#ifdef __linux__");
            this.out.println("    address = dlsym(RTLD_DEFAULT, name);");
            this.out.println("#if !(defined(__ANDROID__) && defined(__arm__))");
            this.out.println("    if (address == NULL) {");
            this.out.println("        void *data[] = { (char*)name, NULL };");
            this.out.println("        dl_iterate_phdr(JavaCPP_dlcallback, data);");
            this.out.println("        address = data[1];");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("    address = dlsym(RTLD_DEFAULT, name);");
            this.out.println("    if (address == NULL) {");
            this.out.println("        for (uint32_t i = 0; i < _dyld_image_count(); i++) {");
            this.out.println("            const char *libname = _dyld_get_image_name(i);");
            this.out.println("            if (libname != NULL) {");
            this.out.println("                void *handle = dlopen(libname, RTLD_LAZY);");
            this.out.println("                if (handle != NULL) {");
            this.out.println("                    address = dlsym(handle, name);");
            this.out.println("                    dlclose(handle);");
            this.out.println("                    if (address != NULL) {");
            this.out.println("                        break;");
            this.out.println("                    }");
            this.out.println("                }");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("#elif defined(_WIN32)");
            this.out.println("    HANDLE process = GetCurrentProcess();");
            this.out.println("    HMODULE *modules = NULL;");
            this.out.println("    DWORD length = 0, needed = 0;");
            this.out.println("    BOOL success = EnumProcessModules(process, modules, length, &needed);");
            this.out.println("    while (success && needed > length) {");
            this.out.println("        modules = (HMODULE*)realloc(modules, length = needed);");
            this.out.println("        success = EnumProcessModules(process, modules, length, &needed);");
            this.out.println("    }");
            this.out.println("    if (success && modules != NULL) {");
            this.out.println("        length = needed / sizeof(HMODULE);");
            this.out.println("        for (DWORD i = 0; i < length; i++) {");
            this.out.println("            address = (void*)GetProcAddress(modules[i], name);");
            this.out.println("            if (address != NULL) {");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    free(modules);");
            this.out.println("#endif");
            this.out.println("    return address;");
            this.out.println("}");
            this.out.println();
            this.out.println("static inline JavaVM* JavaCPP_getJavaVM() {");
            this.out.println("    return JavaCPP_vm;");
            this.out.println("}");
            this.out.println();
        }
        this.out.println("static JavaCPP_noinline jclass JavaCPP_getClass(JNIEnv* env, int i) {");
        this.out.println("    if (JavaCPP_classes[i] == NULL && env->PushLocalFrame(1) == 0) {");
        this.out.println("        jclass cls = env->FindClass(JavaCPP_classNames[i]);");
        this.out.println("        if (cls == NULL || env->ExceptionCheck()) {");
        this.out.println("            JavaCPP_log(\"Error loading class %s.\", JavaCPP_classNames[i]);");
        this.out.println("            return NULL;");
        this.out.println("        }");
        this.out.println("        JavaCPP_classes[i] = (jclass)env->NewWeakGlobalRef(cls);");
        this.out.println("        if (JavaCPP_classes[i] == NULL || env->ExceptionCheck()) {");
        this.out.println("            JavaCPP_log(\"Error creating global reference of class %s.\", JavaCPP_classNames[i]);");
        this.out.println("            return NULL;");
        this.out.println("        }");
        this.out.println("        env->PopLocalFrame(NULL);");
        this.out.println("    }");
        this.out.println("    return JavaCPP_classes[i];");
        this.out.println("}");
        this.out.println();
        this.out.println("static JavaCPP_noinline jfieldID JavaCPP_getFieldID(JNIEnv* env, int i, const char* name, const char* sig) {");
        this.out.println("    jclass cls = JavaCPP_getClass(env, i);");
        this.out.println("    if (cls == NULL) {");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    jfieldID fid = env->GetFieldID(cls, name, sig);");
        this.out.println("    if (fid == NULL || env->ExceptionCheck()) {");
        this.out.println("        JavaCPP_log(\"Error getting field ID of %s/%s\", JavaCPP_classNames[i], name);");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    return fid;");
        this.out.println("}");
        this.out.println();
        if (declareEnums) {
            this.out.println("static JavaCPP_noinline jfieldID JavaCPP_getFieldID(JNIEnv* env, const char* clsName, const char* name, const char* sig) {");
            this.out.println("    jclass cls = env->FindClass(clsName);");
            this.out.println("    if (cls == NULL || env->ExceptionCheck()) {");
            this.out.println("        JavaCPP_log(\"Error loading class %s.\", clsName);");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    jfieldID fid = env->GetFieldID(cls, name, sig);");
            this.out.println("    if (fid == NULL || env->ExceptionCheck()) {");
            this.out.println("        JavaCPP_log(\"Error getting field ID of %s/%s\", clsName, name);");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    return fid;");
            this.out.println("}");
            this.out.println();
        }
        this.out.println("static JavaCPP_noinline jmethodID JavaCPP_getMethodID(JNIEnv* env, int i, const char* name, const char* sig) {");
        this.out.println("    jclass cls = JavaCPP_getClass(env, i);");
        this.out.println("    if (cls == NULL) {");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    jmethodID mid = env->GetMethodID(cls, name, sig);");
        this.out.println("    if (mid == NULL || env->ExceptionCheck()) {");
        this.out.println("        JavaCPP_log(\"Error getting method ID of %s/%s\", JavaCPP_classNames[i], name);");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    return mid;");
        this.out.println("}");
        this.out.println();
        this.out.println("static JavaCPP_noinline jmethodID JavaCPP_getStaticMethodID(JNIEnv* env, int i, const char* name, const char* sig) {");
        this.out.println("    jclass cls = JavaCPP_getClass(env, i);");
        this.out.println("    if (cls == NULL) {");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    jmethodID mid = env->GetStaticMethodID(cls, name, sig);");
        this.out.println("    if (mid == NULL || env->ExceptionCheck()) {");
        this.out.println("        JavaCPP_log(\"Error getting static method ID of %s/%s\", JavaCPP_classNames[i], name);");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    return mid;");
        this.out.println("}");
        this.out.println();
        this.out.println("static JavaCPP_noinline jobject JavaCPP_createPointer(JNIEnv* env, int i, jclass cls = NULL) {");
        this.out.println("    if (cls == NULL && (cls = JavaCPP_getClass(env, i)) == NULL) {");
        this.out.println("        return NULL;");
        this.out.println("    }");
        this.out.println("    if (JavaCPP_haveAllocObject) {");
        this.out.println("        return env->AllocObject(cls);");
        this.out.println("    } else {");
        this.out.println("        jmethodID mid = env->GetMethodID(cls, \"<init>\", \"(Lorg/bytedeco/javacpp/Pointer;)V\");");
        this.out.println("        if (mid == NULL || env->ExceptionCheck()) {");
        this.out.println("            JavaCPP_log(\"Error getting Pointer constructor of %s, while VM does not support AllocObject()\", JavaCPP_classNames[i]);");
        this.out.println("            return NULL;");
        this.out.println("        }");
        this.out.println("        return env->NewObject(cls, mid, NULL);");
        this.out.println("    }");
        this.out.println("}");
        this.out.println();
        this.out.println("static JavaCPP_noinline void JavaCPP_initPointer(JNIEnv* env, jobject obj, const void* ptr, jlong size, void* owner, void (*deallocator)(void*)) {");
        this.out.println("    if (owner != NULL && deallocator != NULL) {");
        this.out.println("        jvalue args[4];");
        this.out.println("        args[0].j = ptr_to_jlong(ptr);");
        this.out.println("        args[1].j = size;");
        this.out.println("        args[2].j = ptr_to_jlong(owner);");
        this.out.println("        args[3].j = ptr_to_jlong(deallocator);");
        this.out.println("        if (JavaCPP_haveNonvirtual) {");
        this.out.println("            env->CallNonvirtualVoidMethodA(obj, JavaCPP_getClass(env, " + this.jclasses.index(Pointer.class) + "), JavaCPP_initMID, args);");
        this.out.println("        } else {");
        this.out.println("            env->CallVoidMethodA(obj, JavaCPP_initMID, args);");
        this.out.println("        }");
        this.out.println("    } else {");
        this.out.println("        env->SetLongField(obj, JavaCPP_addressFID, ptr_to_jlong(ptr));");
        this.out.println("        env->SetLongField(obj, JavaCPP_limitFID, (jlong)size);");
        this.out.println("        env->SetLongField(obj, JavaCPP_capacityFID, (jlong)size);");
        this.out.println("    }");
        this.out.println("}");
        this.out.println();
        if (handleExceptions || convertStrings) {
            this.out.println("#include <string>");
            this.out.println("static JavaCPP_noinline jstring JavaCPP_createStringFromBytes(JNIEnv* env, const char* ptr, size_t length) {");
            this.out.println("    if (ptr == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("#ifdef MODIFIED_UTF8_STRING");
            this.out.println("    return env->NewStringUTF(ptr);");
            this.out.println("#else");
            this.out.println("    jbyteArray bytes = env->NewByteArray(length < INT_MAX ? length : INT_MAX);");
            this.out.println("    env->SetByteArrayRegion(bytes, 0, length < INT_MAX ? length : INT_MAX, (signed char*)ptr);");
            this.out.println("#ifdef STRING_BYTES_CHARSET");
            this.out.println("    return (jstring)env->NewObject(JavaCPP_getClass(env, " + this.jclasses.index(String.class) + "), JavaCPP_stringWithCharsetMID, bytes, JavaCPP_stringBytesCharset);");
            this.out.println("#else");
            this.out.println("    return (jstring)env->NewObject(JavaCPP_getClass(env, " + this.jclasses.index(String.class) + "), JavaCPP_stringMID, bytes);");
            this.out.println("#endif // STRING_BYTES_CHARSET");
            this.out.println("#endif // MODIFIED_UTF8_STRING");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline jstring JavaCPP_createStringFromBytes(JNIEnv* env, const char* ptr) {");
            this.out.println("    if (ptr == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    return JavaCPP_createStringFromBytes(env, ptr, std::char_traits<char>::length(ptr));");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline jstring JavaCPP_createStringFromUTF16(JNIEnv* env, const unsigned short* ptr, size_t length) {");
            this.out.println("    if (ptr == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    return env->NewString(ptr, length);");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline jstring JavaCPP_createStringFromUTF16(JNIEnv* env, const unsigned short* ptr) {");
            this.out.println("    if (ptr == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    return JavaCPP_createStringFromUTF16(env, ptr, std::char_traits<unsigned short>::length(ptr));");
            this.out.println("}");
            this.out.println();
        }
        if (convertStrings) {
            this.out.println("static JavaCPP_noinline const char* JavaCPP_getStringBytes(JNIEnv* env, jstring str) {");
            this.out.println("    if (str == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("#ifdef MODIFIED_UTF8_STRING");
            this.out.println("    return env->GetStringUTFChars(str, NULL);");
            this.out.println("#else");
            this.out.println("#ifdef STRING_BYTES_CHARSET");
            this.out.println("    jbyteArray bytes = (jbyteArray)env->CallObjectMethod(str, JavaCPP_getBytesWithCharsetMID, JavaCPP_stringBytesCharset);");
            this.out.println("#else");
            this.out.println("    jbyteArray bytes = (jbyteArray)env->CallObjectMethod(str, JavaCPP_getBytesMID);");
            this.out.println("#endif // STRING_BYTES_CHARSET");
            this.out.println("    if (bytes == NULL || env->ExceptionCheck()) {");
            this.out.println("        JavaCPP_log(\"Error getting bytes from string.\");");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    jsize length = env->GetArrayLength(bytes);");
            this.out.println("    signed char* ptr = new (std::nothrow) signed char[length + 1];");
            this.out.println("    if (ptr != NULL) {");
            this.out.println("        env->GetByteArrayRegion(bytes, 0, length, ptr);");
            this.out.println("        ptr[length] = 0;");
            this.out.println("    }");
            this.out.println("    return (const char*)ptr;");
            this.out.println("#endif // MODIFIED_UTF8_STRING");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline void JavaCPP_releaseStringBytes(JNIEnv* env, jstring str, const char* ptr) {");
            this.out.println("#ifdef MODIFIED_UTF8_STRING");
            this.out.println("    if (str != NULL && ptr != NULL) {");
            this.out.println("        env->ReleaseStringUTFChars(str, ptr);");
            this.out.println("    }");
            this.out.println("#else");
            this.out.println("    delete[] ptr;");
            this.out.println("#endif");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline const unsigned short* JavaCPP_getStringUTF16(JNIEnv* env, jstring str) {");
            this.out.println("    if (str == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    const jsize length = env->GetStringLength(str);");
            this.out.println("    unsigned short* ptr = new (std::nothrow) unsigned short[length + 1];");
            this.out.println("    if (ptr != NULL) {");
            this.out.println("        env->GetStringRegion(str, 0, length, ptr);");
            this.out.println("        ptr[length] = 0;");
            this.out.println("    }");
            this.out.println("    return ptr;");
            this.out.println("}");
            this.out.println();
            this.out.println("static JavaCPP_noinline void JavaCPP_releaseStringUTF16(JNIEnv*, const unsigned short* ptr) {");
            this.out.println("    delete[] ptr;");
            this.out.println("}");
            this.out.println();
        }
        this.out.println("class JavaCPP_hidden JavaCPP_exception : public std::exception {");
        this.out.println("public:");
        this.out.println("    JavaCPP_exception(const char* str) throw() {");
        this.out.println("        if (str == NULL) {");
        this.out.println("            strcpy(msg, \"Unknown exception.\");");
        this.out.println("        } else {");
        this.out.println("            strncpy(msg, str, sizeof(msg));");
        this.out.println("            msg[sizeof(msg) - 1] = 0;");
        this.out.println("        }");
        this.out.println("    }");
        this.out.println("    virtual const char* what() const throw() { return msg; }");
        this.out.println("    char msg[1024];");
        this.out.println("};");
        this.out.println();
        if (handleExceptions) {
            this.out.println("#ifndef GENERIC_EXCEPTION_CLASS");
            this.out.println("#define GENERIC_EXCEPTION_CLASS std::exception");
            this.out.println("#endif");
            this.out.println("#ifndef GENERIC_EXCEPTION_TOSTRING");
            this.out.println("#define GENERIC_EXCEPTION_TOSTRING what()");
            this.out.println("#endif");
            this.out.println("static JavaCPP_noinline jthrowable JavaCPP_handleException(JNIEnv* env, int i) {");
            this.out.println("    jstring str = NULL;");
            this.out.println("    try {");
            this.out.println("        throw;");
            this.out.println("    } catch (GENERIC_EXCEPTION_CLASS& e) {");
            this.out.println("        str = JavaCPP_createStringFromBytes(env, e.GENERIC_EXCEPTION_TOSTRING);");
            this.out.println("    } catch (...) {");
            this.out.println("        str = JavaCPP_createStringFromBytes(env, \"Unknown exception.\");");
            this.out.println("    }");
            this.out.println("    jmethodID mid = JavaCPP_getMethodID(env, i, \"<init>\", \"(Ljava/lang/String;)V\");");
            this.out.println("    if (mid == NULL) {");
            this.out.println("        return NULL;");
            this.out.println("    }");
            this.out.println("    return (jthrowable)env->NewObject(JavaCPP_getClass(env, i), mid, str);");
            this.out.println("}");
            this.out.println();
        }
        try {
            deallocator = Class.forName(Pointer.class.getName() + "$Deallocator", false, Pointer.class.getClassLoader());
            nativeDeallocator = Class.forName(Pointer.class.getName() + "$NativeDeallocator", false, Pointer.class.getClassLoader());
        }
        catch (ClassNotFoundException ex2) {
            throw new RuntimeException(ex2);
        }
        if (defineAdapters) {
            this.out.println("static JavaCPP_noinline void* JavaCPP_getPointerOwner(JNIEnv* env, jobject obj) {");
            this.out.println("    if (obj != NULL) {");
            this.out.println("        jobject deallocator = env->GetObjectField(obj, JavaCPP_deallocatorFID);");
            this.out.println("        if (deallocator != NULL && env->IsInstanceOf(deallocator, JavaCPP_getClass(env, " + this.jclasses.index(nativeDeallocator) + "))) {");
            this.out.println("            return jlong_to_ptr(env->GetLongField(deallocator, JavaCPP_ownerAddressFID));");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    return NULL;");
            this.out.println("}");
            this.out.println();
            this.out.println("#include <vector>");
            this.out.println("template<typename P, typename T = P, typename A = std::allocator<T> > class JavaCPP_hidden VectorAdapter {");
            this.out.println("public:");
            this.out.println("    VectorAdapter(const P* ptr, typename std::vector<T,A>::size_type size, void* owner) : ptr((P*)ptr), size(size), owner(owner),");
            this.out.println("        vec2(ptr ? std::vector<T,A>((P*)ptr, (P*)ptr + size) : std::vector<T,A>()), vec(vec2) { }");
            this.out.println("    VectorAdapter(const std::vector<T,A>& vec) : ptr(0), size(0), owner(0), vec2(vec), vec(vec2) { }");
            this.out.println("    VectorAdapter(      std::vector<T,A>& vec) : ptr(0), size(0), owner(0), vec(vec) { }");
            this.out.println("    VectorAdapter(const std::vector<T,A>* vec) : ptr(0), size(0), owner(0), vec(*(std::vector<T,A>*)vec) { }");
            this.out.println("    void assign(P* ptr, typename std::vector<T,A>::size_type size, void* owner) {");
            this.out.println("        this->ptr = ptr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        vec.assign(ptr, ptr + size);");
            this.out.println("    }");
            this.out.println("    static void deallocate(void* owner) { operator delete(owner); }");
            this.out.println("    operator P*() {");
            this.out.println("        if (vec.size() > size) {");
            this.out.println("            ptr = (P*)(operator new(sizeof(P) * vec.size(), std::nothrow_t()));");
            this.out.println("        }");
            this.out.println("        if (ptr) {");
            this.out.println("            std::uninitialized_copy(vec.begin(), vec.end(), ptr);");
            this.out.println("        }");
            this.out.println("        size = vec.size();");
            this.out.println("        owner = ptr;");
            this.out.println("        return ptr;");
            this.out.println("    }");
            this.out.println("    operator const P*()        { size = vec.size(); return &vec[0]; }");
            this.out.println("    operator std::vector<T,A>&() { return vec; }");
            this.out.println("    operator std::vector<T,A>*() { return ptr ? &vec : 0; }");
            this.out.println("    P* ptr;");
            this.out.println("    typename std::vector<T,A>::size_type size;");
            this.out.println("    void* owner;");
            this.out.println("    std::vector<T,A> vec2;");
            this.out.println("    std::vector<T,A>& vec;");
            this.out.println("};");
            this.out.println();
            this.out.println("#include <string>");
            this.out.println("template<typename T = char> class JavaCPP_hidden StringAdapter {");
            this.out.println("public:");
            this.out.println("    StringAdapter(const          char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const signed   char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const unsigned char* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const       wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const unsigned short* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const   signed   int* ptr, typename std::basic_string<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("        str2(ptr ? (T*)ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0), str(str2) { }");
            this.out.println("    StringAdapter(const std::basic_string<T>& str) : ptr(0), size(0), owner(0), str2(str), str(str2) { }");
            this.out.println("    StringAdapter(      std::basic_string<T>& str) : ptr(0), size(0), owner(0), str(str) { }");
            this.out.println("    StringAdapter(const std::basic_string<T>* str) : ptr(0), size(0), owner(0), str(*(std::basic_string<T>*)str) { }");
            this.out.println("    void assign(char* ptr, typename std::basic_string<T>::size_type size, void* owner) {");
            this.out.println("        this->ptr = ptr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        str.assign(ptr ? ptr : \"\", ptr ? (size > 0 ? size : strlen((char*)ptr)) : 0);");
            this.out.println("    }");
            this.out.println("    void assign(const          char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }");
            this.out.println("    void assign(const signed   char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }");
            this.out.println("    void assign(const unsigned char* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((char*)ptr, size, owner); }");
            this.out.println("    void assign(wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) {");
            this.out.println("        this->ptr = ptr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        str.assign(ptr ? ptr : L\"\", ptr ? (size > 0 ? size : wcslen((wchar_t*)ptr)) : 0);");
            this.out.println("    }");
            this.out.println("    void assign(const        wchar_t* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }");
            this.out.println("    void assign(const unsigned short* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }");
            this.out.println("    void assign(const   signed   int* ptr, typename std::basic_string<T>::size_type size, void* owner) { assign((wchar_t*)ptr, size, owner); }");
            this.out.println("    static void deallocate(void* owner) { delete[] (T*)owner; }");
            this.out.println("    operator char*() {");
            this.out.println("        const char* data = str.data();");
            this.out.println("        if (str.size() > size) {");
            this.out.println("            ptr = new (std::nothrow) char[str.size()+1];");
            this.out.println("            if (ptr) memset(ptr, 0, str.size()+1);");
            this.out.println("        }");
            this.out.println("        if (ptr && memcmp(ptr, data, str.size()) != 0) {");
            this.out.println("            memcpy(ptr, data, str.size());");
            this.out.println("            if (size > str.size()) ptr[str.size()] = 0;");
            this.out.println("        }");
            this.out.println("        size = str.size();");
            this.out.println("        owner = ptr;");
            this.out.println("        return ptr;");
            this.out.println("    }");
            this.out.println("    operator       signed   char*() { return (signed   char*)(operator char*)(); }");
            this.out.println("    operator       unsigned char*() { return (unsigned char*)(operator char*)(); }");
            this.out.println("    operator const          char*() { size = str.size(); return                 str.c_str(); }");
            this.out.println("    operator const signed   char*() { size = str.size(); return (signed   char*)str.c_str(); }");
            this.out.println("    operator const unsigned char*() { size = str.size(); return (unsigned char*)str.c_str(); }");
            this.out.println("    operator wchar_t*() {");
            this.out.println("        const wchar_t* data = str.data();");
            this.out.println("        if (str.size() > size) {");
            this.out.println("            ptr = new (std::nothrow) wchar_t[str.size()+1];");
            this.out.println("            if (ptr) memset(ptr, 0, sizeof(wchar_t) * (str.size()+1));");
            this.out.println("        }");
            this.out.println("        if (ptr && memcmp(ptr, data, sizeof(wchar_t) * str.size()) != 0) {");
            this.out.println("            memcpy(ptr, data, sizeof(wchar_t) * str.size());");
            this.out.println("            if (size > str.size()) ptr[str.size()] = 0;");
            this.out.println("        }");
            this.out.println("        size = str.size();");
            this.out.println("        owner = ptr;");
            this.out.println("        return ptr;");
            this.out.println("    }");
            this.out.println("    operator     unsigned   short*() { return (unsigned short*)(operator wchar_t*)(); }");
            this.out.println("    operator       signed     int*() { return (  signed   int*)(operator wchar_t*)(); }");
            this.out.println("    operator const        wchar_t*() { size = str.size(); return                  str.c_str(); }");
            this.out.println("    operator const unsigned short*() { size = str.size(); return (unsigned short*)str.c_str(); }");
            this.out.println("    operator const   signed   int*() { size = str.size(); return (  signed   int*)str.c_str(); }");
            this.out.println("    operator         std::basic_string<T>&() { return str; }");
            this.out.println("    operator         std::basic_string<T>*() { return ptr ? &str : 0; }");
            this.out.println("    T* ptr;");
            this.out.println("    typename std::basic_string<T>::size_type size;");
            this.out.println("    void* owner;");
            this.out.println("    std::basic_string<T> str2;");
            this.out.println("    std::basic_string<T>& str;");
            this.out.println("};");
            this.out.println();
            this.out.println("template<typename P, typename T = P> class JavaCPP_hidden BasicStringAdapter {");
            this.out.println("public:");
            this.out.println("    BasicStringAdapter(const P* ptr, typename std::basic_string<T>::size_type size, void* owner) : str(str2) { assign(const_cast<P*>(ptr), size, owner); }");
            this.out.println();
            this.out.println("    BasicStringAdapter(const std::basic_string<T>& str) : size(0), owner(NULL), ptr(NULL), str2(str), str(str2) { }");
            this.out.println("    BasicStringAdapter(      std::basic_string<T>& str) : size(0), owner(NULL), ptr(NULL), str(str) { }");
            this.out.println("    BasicStringAdapter(const std::basic_string<T>* str) : size(0), owner(NULL), ptr(NULL), str(*const_cast<std::basic_string<T>*>(str)) { }");
            this.out.println();
            this.out.println("    static void deallocate(void* owner) { delete[] static_cast<T*>(owner); }");
            this.out.println();
            this.out.println("    operator P*() {");
            this.out.println("        const T* data = str.data();");
            this.out.println("        if (str.size() > size) {");
            this.out.println("            ptr = new (std::nothrow) T[str.size() + 1]();");
            this.out.println("        }");
            this.out.println("        if (ptr && memcmp(ptr, data, sizeof(T) * str.size()) != 0) {");
            this.out.println("            memcpy(ptr, data, sizeof(T) * str.size());");
            this.out.println("            if (size > str.size()) ptr[str.size()] = 0;");
            this.out.println("        }");
            this.out.println("        size = str.size();");
            this.out.println("        owner = ptr;");
            this.out.println("        return reinterpret_cast<P*>(ptr);");
            this.out.println("    }");
            this.out.println("    operator const P*() {");
            this.out.println("        size = str.size();");
            this.out.println("        return reinterpret_cast<const P*>(str.c_str());");
            this.out.println("    }");
            this.out.println();
            this.out.println("    operator std::basic_string<T>&() { return str; }");
            this.out.println("    operator std::basic_string<T>*() { return ptr ? &str : NULL; }");
            this.out.println();
            this.out.println("    void assign(P* ptr, typename std::basic_string<T>::size_type size, void* owner) {");
            this.out.println("        this->ptr = reinterpret_cast<T*>(ptr);");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        if (this->ptr) {");
            this.out.println("            str.assign(this->ptr, size > 0 ? size : std::char_traits<T>::length(this->ptr));");
            this.out.println("        } else {");
            this.out.println("            str.clear();");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println();
            this.out.println("    typename std::basic_string<T>::size_type size;");
            this.out.println("    void* owner;");
            this.out.println();
            this.out.println("private:");
            this.out.println("    T* ptr;");
            this.out.println("    std::basic_string<T> str2;");
            this.out.println("    std::basic_string<T>& str;");
            this.out.println("};");
            this.out.println();
            this.out.println("#ifdef SHARED_PTR_NAMESPACE");
            this.out.println("template<class T> class SharedPtrAdapter {");
            this.out.println("public:");
            this.out.println("    typedef SHARED_PTR_NAMESPACE::shared_ptr<T> S;");
            this.out.println("    SharedPtrAdapter(const T* ptr, size_t size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("            sharedPtr2(owner != NULL && owner != ptr ? *(S*)owner : S((T*)ptr)), sharedPtr(sharedPtr2) { }");
            this.out.println("    SharedPtrAdapter(const S& sharedPtr) : ptr(0), size(0), owner(0), sharedPtr2(sharedPtr), sharedPtr(sharedPtr2) { }");
            this.out.println("    SharedPtrAdapter(      S& sharedPtr) : ptr(0), size(0), owner(0), sharedPtr(sharedPtr) { }");
            this.out.println("    SharedPtrAdapter(const S* sharedPtr) : ptr(0), size(0), owner(0), sharedPtr(*(S*)sharedPtr) { }");
            this.out.println("    void assign(T* ptr, size_t size, void* owner) {");
            this.out.println("        this->ptr = ptr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        this->sharedPtr = owner != NULL && owner != ptr ? *(S*)owner : S((T*)ptr);");
            this.out.println("    }");
            this.out.println("    static void deallocate(void* owner) { delete (S*)owner; }");
            this.out.println("    operator typename SHARED_PTR_NAMESPACE::remove_const<T>::type*() {");
            this.out.println("        ptr = sharedPtr.get();");
            this.out.println("        if (owner == NULL || owner == ptr) {");
            this.out.println("            owner = new S(sharedPtr);");
            this.out.println("        }");
            this.out.println("        return (typename SHARED_PTR_NAMESPACE::remove_const<T>::type*)ptr;");
            this.out.println("    }");
            this.out.println("    operator S&() { return sharedPtr; }");
            this.out.println("    operator S*() { return &sharedPtr; }");
            this.out.println("    T* ptr;");
            this.out.println("    size_t size;");
            this.out.println("    void* owner;");
            this.out.println("    S sharedPtr2;");
            this.out.println("    S& sharedPtr;");
            this.out.println("};");
            this.out.println("#endif");
            this.out.println();
            this.out.println("#ifdef UNIQUE_PTR_NAMESPACE");
            this.out.println("template<class T, class D = UNIQUE_PTR_NAMESPACE::default_delete<T> > class UniquePtrAdapter {");
            this.out.println("public:");
            this.out.println("    typedef UNIQUE_PTR_NAMESPACE::unique_ptr<T,D> U;");
            this.out.println("    UniquePtrAdapter(const T* ptr, size_t size, void* owner) : ptr((T*)ptr), size(size), owner(owner),");
            this.out.println("            uniquePtr2(owner != NULL && owner != ptr ? U() : U((T*)ptr)),");
            this.out.println("            uniquePtr(owner != NULL && owner != ptr ? *(U*)owner : uniquePtr2) { }");
            this.out.println("    UniquePtrAdapter(U&& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr2(UNIQUE_PTR_NAMESPACE::move(uniquePtr)), uniquePtr(uniquePtr2) { }");
            this.out.println("    UniquePtrAdapter(const U& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr2(U(NULL, D())), uniquePtr((U&)uniquePtr) { }");
            this.out.println("    UniquePtrAdapter(      U& uniquePtr) : ptr(0), size(0), owner(0), uniquePtr2(U(NULL, D())), uniquePtr(uniquePtr) { }");
            this.out.println("    UniquePtrAdapter(const U* uniquePtr) : ptr(0), size(0), owner(0), uniquePtr2(U(NULL, D())), uniquePtr(*(U*)uniquePtr) { }");
            this.out.println("    void assign(T* ptr, size_t size, void* owner) {");
            this.out.println("        this->ptr = ptr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        this->uniquePtr = owner != NULL && owner != ptr ? *(U*)owner : U((T*)ptr);");
            this.out.println("    }");
            this.out.println("    static void deallocate(void* owner) { delete (U*)owner; }");
            this.out.println("    operator typename UNIQUE_PTR_NAMESPACE::remove_const<T>::type*() {");
            this.out.println("        ptr = uniquePtr.get();");
            this.out.println("        if (ptr == uniquePtr2.get() && (owner == NULL || owner == ptr)) {");
            this.out.println("            // only move the pointer if we actually own it through uniquePtr2");
            this.out.println("            owner = new U(UNIQUE_PTR_NAMESPACE::move(uniquePtr));");
            this.out.println("        }");
            this.out.println("        return (typename UNIQUE_PTR_NAMESPACE::remove_const<T>::type*)ptr;");
            this.out.println("    }");
            this.out.println("    operator U&() const { return uniquePtr; }");
            this.out.println("    operator U&&() { return UNIQUE_PTR_NAMESPACE::move(uniquePtr); }");
            this.out.println("    operator U*() { return &uniquePtr; }");
            this.out.println("    T* ptr;");
            this.out.println("    size_t size;");
            this.out.println("    void* owner;");
            this.out.println("    U uniquePtr2;");
            this.out.println("    U& uniquePtr;");
            this.out.println("};");
            this.out.println("#endif");
            this.out.println("");
            this.out.println("#if __cplusplus >= 201103L || _MSC_VER >= 1900");
            this.out.println("#include <utility>");
            this.out.println("template<class T> class MoveAdapter {");
            this.out.println("public:");
            this.out.println("    MoveAdapter(const T* ptr, size_t size, void* owner) : ptr(&movedPtr), size(size), owner(owner), movedPtr(std::move(*(T*)ptr)) { }");
            this.out.println("    MoveAdapter(const T& ptr) : ptr(&movedPtr), size(0), owner(0), movedPtr(std::move((T&)ptr)) { }");
            this.out.println("    MoveAdapter(T&& ptr) : ptr(&movedPtr), size(0), owner(0), movedPtr((T&&)ptr) { }");
            this.out.println("    void assign(T* ptr, size_t size, void* owner) {");
            this.out.println("        this->ptr = &this->movedPtr;");
            this.out.println("        this->size = size;");
            this.out.println("        this->owner = owner;");
            this.out.println("        this->movedPtr = std::move(*ptr);");
            this.out.println("    }");
            this.out.println("    static void deallocate(void* owner) { delete (T*)owner; }");
            this.out.println("    operator T*() {");
            this.out.println("        ptr = new T(std::move(movedPtr));");
            this.out.println("        owner = ptr;");
            this.out.println("        return ptr;");
            this.out.println("    }");
            this.out.println("    operator const T*() { return ptr; }");
            this.out.println("    operator T&&() { return std::move(movedPtr); }");
            this.out.println("    T* ptr;");
            this.out.println("    size_t size;");
            this.out.println("    void* owner;");
            this.out.println("    T movedPtr;");
            this.out.println("};");
            this.out.println("#endif");
            this.out.println();
        }
        if (!this.functions.isEmpty() || !this.virtualFunctions.isEmpty()) {
            this.out.println("#if !defined(NO_JNI_DETACH_THREAD) && (defined(__linux__) || defined(__APPLE__))");
            this.out.println("  static pthread_once_t JavaCPP_once = PTHREAD_ONCE_INIT;");
            this.out.println("#endif");
            this.out.println();
            this.out.println("static JavaCPP_noinline void JavaCPP_detach(bool detach) {");
            this.out.println("#if !defined(NO_JNI_DETACH_THREAD) && !defined(__linux__) && !defined(__APPLE__)");
            this.out.println("    if (detach && JavaCPP_vm->DetachCurrentThread() != JNI_OK) {");
            this.out.println("        JavaCPP_log(\"Could not detach the JavaVM from the current thread.\");");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("}");
            this.out.println();
            if (!loadSuffix.isEmpty()) {
                this.out.println("extern \"C\" {");
                this.out.println("JNIEXPORT jint JNICALL JNI_OnLoad" + loadSuffix + "(JavaVM* vm, void* reserved);");
                this.out.println("}");
            }
            this.out.println("static JavaCPP_noinline bool JavaCPP_getEnv(JNIEnv** env) {");
            this.out.println("    bool attached = false;");
            this.out.println("    JavaVM *vm = JavaCPP_vm;");
            this.out.println("    if (vm == NULL) {");
            if (this.out2 != null) {
                this.out.println("#if !defined(__ANDROID__) && !TARGET_OS_IPHONE");
                this.out.println("        int size = 1;");
                this.out.println("        if (JNI_GetCreatedJavaVMs(&vm, 1, &size) != JNI_OK || size == 0) {");
                this.out.println("#endif");
            }
            this.out.println("            JavaCPP_log(\"Could not get any created JavaVM.\");");
            this.out.println("            *env = NULL;");
            this.out.println("            return false;");
            if (this.out2 != null) {
                this.out.println("#if !defined(__ANDROID__) && !TARGET_OS_IPHONE");
                this.out.println("        }");
                this.out.println("#endif");
            }
            this.out.println("    }");
            this.out.println("#if !defined(NO_JNI_DETACH_THREAD) && (defined(__linux__) || defined(__APPLE__))");
            this.out.println("    pthread_once(&JavaCPP_once, JavaCPP_create_pthread_key);");
            this.out.println("    if ((*env = (JNIEnv *)pthread_getspecific(JavaCPP_current_env)) != NULL) {");
            this.out.println("        attached = true;");
            this.out.println("        goto done;");
            this.out.println("    }");
            this.out.println("#endif");
            this.out.println("    if (vm->GetEnv((void**)env, JNI_VERSION_1_6) != JNI_OK) {");
            this.out.println("        struct {");
            this.out.println("            JNIEnv **env;");
            this.out.println("            operator JNIEnv**() { return env; } // Android JNI");
            this.out.println("            operator void**() { return (void**)env; } // standard JNI");
            this.out.println("        } env2 = { env };");
            this.out.println("        JavaVMAttachArgs args;");
            this.out.println("        args.version = JNI_VERSION_1_6;");
            this.out.println("        args.group = NULL;");
            this.out.println("        char name[64] = {0};");
            this.out.println("#ifdef _WIN32");
            this.out.println("        sprintf(name, \"JavaCPP Thread ID %lu\", GetCurrentThreadId());");
            this.out.println("#elif defined(__APPLE__)");
            this.out.println("        sprintf(name, \"JavaCPP Thread ID %u\", pthread_mach_thread_np(pthread_self()));");
            this.out.println("#else");
            this.out.println("        sprintf(name, \"JavaCPP Thread ID %lu\", pthread_self());");
            this.out.println("#endif");
            this.out.println("        args.name = name;");
            this.out.println("        if (vm->AttachCurrentThread(env2, &args) != JNI_OK) {");
            this.out.println("            JavaCPP_log(\"Could not attach the JavaVM to the current thread.\");");
            this.out.println("            *env = NULL;");
            this.out.println("            goto done;");
            this.out.println("        }");
            this.out.println("#if !defined(NO_JNI_DETACH_THREAD) && (defined(__linux__) || defined(__APPLE__))");
            this.out.println("        pthread_setspecific(JavaCPP_current_env, *env);");
            this.out.println("#endif");
            this.out.println("        attached = true;");
            this.out.println("    }");
            this.out.println("    if (JavaCPP_vm == NULL) {");
            this.out.println("        if (JNI_OnLoad" + loadSuffix + "(vm, NULL) < 0) {");
            this.out.println("            JavaCPP_detach(attached);");
            this.out.println("            *env = NULL;");
            this.out.println("            goto done;");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("done:");
            this.out.println("    return attached;");
            this.out.println("}");
            this.out.println();
        }
        for (Class c3 : this.functions) {
            String[] typeName2 = this.cppTypeName(c3);
            String[] returnConvention = typeName2[0].split("\\(");
            String[] stringArray = new String[]{returnConvention[0] + (returnConvention.length > 2 ? "(*" : ""), returnConvention.length > 2 ? ")(" + returnConvention[2] : ""};
            if (returnConvention.length > 2) {
                returnConvention = Arrays.copyOfRange(returnConvention, 2, returnConvention.length);
            }
            returnConvention[1] = Generator.constValueTypeName(returnConvention[1]);
            String parameterDeclaration = typeName2[1].substring(1);
            String instanceTypeName = Generator.functionClassName(c3);
            this.out.println("struct JavaCPP_hidden " + instanceTypeName + " {");
            this.out.println("    " + instanceTypeName + "() : ptr(NULL), obj(NULL) { }");
            if (parameterDeclaration != null && parameterDeclaration.length() > 0) {
                this.out.println("    " + stringArray[0] + "operator()" + parameterDeclaration + stringArray[1] + ";");
            }
            this.out.println("    " + stringArray[0] + "(" + returnConvention[1] + "*ptr)" + parameterDeclaration + stringArray[1] + ";");
            this.out.println("    jobject obj; static jmethodID mid;");
            this.out.println("};");
            this.out.println("jmethodID " + instanceTypeName + "::mid = NULL;");
        }
        this.out.println();
        for (Class c2 : this.jclasses) {
            Set<String> functionList = this.virtualFunctions.get(c2);
            if (functionList == null) continue;
            Set<String> memberList = this.virtualMembers.get(c2);
            String[] stringArray = this.cppTypeName(c2);
            String valueTypeName2 = Generator.valueTypeName(stringArray);
            String subType = "JavaCPP_" + Generator.mangle(valueTypeName2);
            this.out.println("class JavaCPP_hidden " + subType + " : public " + valueTypeName2 + " {");
            this.out.println("public:");
            this.out.println("    jobject obj;");
            for (String s4 : functionList) {
                this.out.println("    static jmethodID " + s4 + ";");
            }
            this.out.println();
            for (String s3 : memberList) {
                this.out.println(s3);
            }
            this.out.println("};");
            for (String s3 : functionList) {
                this.out.println("jmethodID " + subType + "::" + s3 + " = NULL;");
            }
        }
        this.out.println();
        for (String s5 : this.callbacks.values()) {
            this.out.println(s5);
        }
        this.out.println();
        for (Class c2 : this.deallocators) {
            name = "JavaCPP_" + Generator.mangle(c2.getName());
            this.out.print("static void " + name + "_deallocate(void *p) { ");
            if (FunctionPointer.class.isAssignableFrom(c2)) {
                typeName = Generator.functionClassName(c2);
                if (this.callbacks.containsKey(typeName)) {
                    this.out.print("\n    int n = sizeof(" + (String)typeName + "_instances) / sizeof(" + (String)typeName + "_instances[0]);\n    for (int i = 0; i < n; i++) { if (" + (String)typeName + "_instances[i].obj == ((" + (String)typeName + "*)p)->obj) " + (String)typeName + "_instances[i].obj = NULL; }");
                }
                this.out.println("\n    JNIEnv *e; bool a = JavaCPP_getEnv(&e); if (e != NULL) e->DeleteWeakGlobalRef((jweak)((" + (String)typeName + "*)p)->obj); delete (" + (String)typeName + "*)p; JavaCPP_detach(a); }");
                continue;
            }
            if (this.virtualFunctions.containsKey(c2)) {
                typeName = this.cppTypeName(c2);
                String string = Generator.valueTypeName(typeName);
                String subType = "JavaCPP_" + Generator.mangle(string);
                this.out.println("JNIEnv *e; bool a = JavaCPP_getEnv(&e); if (e != NULL) e->DeleteWeakGlobalRef((jweak)((" + subType + "*)p)->obj); delete (" + subType + "*)p; JavaCPP_detach(a); }");
                continue;
            }
            typeName = this.cppTypeName(c2);
            this.out.println("delete (" + typeName[0] + typeName[1] + ")p; }");
        }
        for (Class c2 : this.arrayDeallocators) {
            name = "JavaCPP_" + Generator.mangle(c2.getName());
            typeName = this.cppTypeName(c2);
            this.out.println("static void " + name + "_deallocateArray(void* p) { delete[] (" + typeName[0] + typeName[1] + ")p; }");
        }
        this.out.println();
        this.out.println("static const char* JavaCPP_members[" + this.jclasses.size() + "][" + (maxMemberSize + 1) + "] = {");
        classIterator = this.jclasses.iterator();
        while (classIterator.hasNext()) {
            Iterator<String> memberIterator2;
            this.out.print("        { ");
            Set<String> m2 = this.members.get(classIterator.next());
            Iterator<String> iterator = memberIterator2 = m2 == null ? null : m2.iterator();
            if (memberIterator2 == null || !memberIterator2.hasNext()) {
                this.out.print("NULL");
            } else {
                while (memberIterator2.hasNext()) {
                    this.out.print("\"" + memberIterator2.next() + "\"");
                    if (!memberIterator2.hasNext()) continue;
                    this.out.print(", ");
                }
            }
            this.out.print(" }");
            if (!classIterator.hasNext()) continue;
            this.out.println(",");
        }
        this.out.println(" };");
        this.out.println("static int JavaCPP_offsets[" + this.jclasses.size() + "][" + (maxMemberSize + 1) + "] = {");
        classIterator = this.jclasses.iterator();
        while (classIterator.hasNext()) {
            this.out.print("        { ");
            Class c4 = classIterator.next();
            Set<String> m4 = this.members.get(c4);
            Class<?>[] classArray = memberIterator = m4 == null ? null : m4.iterator();
            if (memberIterator == null || !memberIterator.hasNext()) {
                this.out.print("-1");
            } else {
                while (memberIterator.hasNext()) {
                    typeName = this.cppTypeName(c4);
                    String string = Generator.valueTypeName(typeName);
                    String memberName = memberIterator.next();
                    if ("sizeof".equals(memberName)) {
                        void var20_37;
                        if ("void".equals(string)) {
                            String string2 = "void*";
                        }
                        this.out.print("sizeof(" + (String)var20_37 + ")");
                    } else {
                        this.out.print("offsetof(" + string + ", " + memberName + ")");
                    }
                    if (!memberIterator.hasNext()) continue;
                    this.out.print(", ");
                }
            }
            this.out.print(" }");
            if (!classIterator.hasNext()) continue;
            this.out.println(",");
        }
        this.out.println(" };");
        this.out.print("static int JavaCPP_memberOffsetSizes[" + this.jclasses.size() + "] = { ");
        classIterator = this.jclasses.iterator();
        while (classIterator.hasNext()) {
            Set<String> m2 = this.members.get(classIterator.next());
            this.out.print(m2 == null ? 1 : m2.size());
            if (!classIterator.hasNext()) continue;
            this.out.print(", ");
        }
        this.out.println(" };");
        this.out.println();
        this.out.println("extern \"C\" {");
        if (this.out2 != null) {
            this.out2.println();
            this.out2.println("#ifdef __cplusplus");
            this.out2.println("extern \"C\" {");
            this.out2.println("#endif");
            this.out2.println("JNIIMPORT int JavaCPP_init" + loadSuffix + "(int argc, const char *argv[]);");
            this.out.println();
            this.out.println("JNIEXPORT int JavaCPP_init" + loadSuffix + "(int argc, const char *argv[]) {");
            this.out.println("#if defined(__ANDROID__) || TARGET_OS_IPHONE");
            this.out.println("    return JNI_OK;");
            this.out.println("#else");
            this.out.println("    if (JavaCPP_vm != NULL) {");
            this.out.println("        return JNI_OK;");
            this.out.println("    }");
            this.out.println("    int err;");
            this.out.println("    JavaVM *vm;");
            this.out.println("    JNIEnv *env;");
            this.out.println("    int nOptions = 1 + (argc > 255 ? 255 : argc);");
            this.out.println("    JavaVMOption options[256] = { { NULL } };");
            this.out.println("    options[0].optionString = (char*)\"-Djava.class.path=" + classPath.replace('\\', '/') + "\";");
            this.out.println("    for (int i = 1; i < nOptions && argv != NULL; i++) {");
            this.out.println("        options[i].optionString = (char*)argv[i - 1];");
            this.out.println("    }");
            this.out.println("    JavaVMInitArgs vm_args = { JNI_VERSION_1_6, nOptions, options };");
            this.out.println("    return (err = JNI_CreateJavaVM(&vm, (void**)&env, &vm_args)) == JNI_OK && vm != NULL && (err = JNI_OnLoad" + loadSuffix + "(vm, NULL)) >= 0 ? JNI_OK : err;");
            this.out.println("#endif");
            this.out.println("}");
        }
        if (baseLoadSuffix != null && !baseLoadSuffix.isEmpty()) {
            this.out.println();
            this.out.println("JNIEXPORT jint JNICALL JNI_OnLoad" + baseLoadSuffix + "(JavaVM* vm, void* reserved);");
            this.out.println("JNIEXPORT void JNICALL JNI_OnUnload" + baseLoadSuffix + "(JavaVM* vm, void* reserved);");
        }
        this.out.println();
        this.out.println("JNIEXPORT jint JNICALL JNI_OnLoad" + loadSuffix + "(JavaVM* vm, void* reserved) {");
        if (baseLoadSuffix != null && !baseLoadSuffix.isEmpty()) {
            this.out.println("    if (JNI_OnLoad" + baseLoadSuffix + "(vm, reserved) == JNI_ERR) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
        }
        this.out.println("    JNIEnv* env;");
        this.out.println("    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {");
        this.out.println("        JavaCPP_log(\"Could not get JNIEnv for JNI_VERSION_1_6 inside JNI_OnLoad" + loadSuffix + "().\");");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    if (JavaCPP_vm == vm) {");
        this.out.println("        return env->GetVersion();");
        this.out.println("    }");
        this.out.println("    JavaCPP_vm = vm;");
        this.out.println("    JavaCPP_haveAllocObject = env->functions->AllocObject != NULL;");
        this.out.println("    JavaCPP_haveNonvirtual = env->functions->CallNonvirtualVoidMethodA != NULL;");
        this.out.println("    jmethodID putMemberOffsetMID = JavaCPP_getStaticMethodID(env, " + this.jclasses.index(Loader.class) + ", \"putMemberOffset\", \"(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/Class;\");");
        this.out.println("    if (putMemberOffsetMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    for (int i = 0; i < " + this.jclasses.size() + " && !env->ExceptionCheck(); i++) {");
        this.out.println("        for (int j = 0; j < JavaCPP_memberOffsetSizes[i] && !env->ExceptionCheck(); j++) {");
        this.out.println("            if (env->PushLocalFrame(3) == 0) {");
        this.out.println("                jvalue args[3];");
        this.out.println("                args[0].l = env->NewStringUTF(JavaCPP_classNames[i]);");
        this.out.println("                args[1].l = JavaCPP_members[i][j] == NULL ? NULL : env->NewStringUTF(JavaCPP_members[i][j]);");
        this.out.println("                args[2].i = JavaCPP_offsets[i][j];");
        this.out.println("                jclass cls = (jclass)env->CallStaticObjectMethodA(JavaCPP_getClass(env, " + this.jclasses.index(Loader.class) + "), putMemberOffsetMID, args);");
        this.out.println("                if (env->ExceptionCheck()) {");
        this.out.println("                    JavaCPP_log(\"Error putting member offsets for class %s.\", JavaCPP_classNames[i]);");
        this.out.println("                    return JNI_ERR;");
        this.out.println("                }");
        this.out.println("                JavaCPP_classes[i] = cls == NULL ? NULL : (jclass)env->NewWeakGlobalRef(cls);");
        this.out.println("                if (env->ExceptionCheck()) {");
        this.out.println("                    JavaCPP_log(\"Error creating global reference of class %s.\", JavaCPP_classNames[i]);");
        this.out.println("                    return JNI_ERR;");
        this.out.println("                }");
        this.out.println("                env->PopLocalFrame(NULL);");
        this.out.println("            }");
        this.out.println("        }");
        this.out.println("    }");
        this.out.println("    JavaCPP_addressFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Pointer.class) + ", \"address\", \"J\");");
        this.out.println("    if (JavaCPP_addressFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_positionFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Pointer.class) + ", \"position\", \"J\");");
        this.out.println("    if (JavaCPP_positionFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_limitFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Pointer.class) + ", \"limit\", \"J\");");
        this.out.println("    if (JavaCPP_limitFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_capacityFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Pointer.class) + ", \"capacity\", \"J\");");
        this.out.println("    if (JavaCPP_capacityFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_deallocatorFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Pointer.class) + ", \"deallocator\", \"" + Generator.signature(deallocator) + "\");");
        this.out.println("    if (JavaCPP_deallocatorFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_ownerAddressFID = JavaCPP_getFieldID(env, " + this.jclasses.index(nativeDeallocator) + ", \"ownerAddress\", \"J\");");
        this.out.println("    if (JavaCPP_ownerAddressFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        if (declareEnums) {
            this.out.println("    JavaCPP_booleanValueFID = JavaCPP_getFieldID(env, \"" + BooleanEnum.class.getName().replace('.', '/') + "\", \"value\", \"Z\");");
            this.out.println("    if (JavaCPP_booleanValueFID == NULL) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
            this.out.println("    JavaCPP_byteValueFID = JavaCPP_getFieldID(env, \"" + ByteEnum.class.getName().replace('.', '/') + "\", \"value\", \"B\");");
            this.out.println("    if (JavaCPP_byteValueFID == NULL) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
            this.out.println("    JavaCPP_shortValueFID = JavaCPP_getFieldID(env, \"" + ShortEnum.class.getName().replace('.', '/') + "\", \"value\", \"S\");");
            this.out.println("    if (JavaCPP_shortValueFID == NULL) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
            this.out.println("    JavaCPP_intValueFID = JavaCPP_getFieldID(env, \"" + IntEnum.class.getName().replace('.', '/') + "\", \"value\", \"I\");");
            this.out.println("    if (JavaCPP_intValueFID == NULL) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
            this.out.println("    JavaCPP_longValueFID = JavaCPP_getFieldID(env, \"" + LongEnum.class.getName().replace('.', '/') + "\", \"value\", \"J\");");
            this.out.println("    if (JavaCPP_longValueFID == NULL) {");
            this.out.println("        return JNI_ERR;");
            this.out.println("    }");
        }
        this.out.println("    JavaCPP_initMID = JavaCPP_getMethodID(env, " + this.jclasses.index(Pointer.class) + ", \"init\", \"(JJJJ)V\");");
        this.out.println("    if (JavaCPP_initMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_arrayMID = JavaCPP_getMethodID(env, " + this.jclasses.index(Buffer.class) + ", \"array\", \"()Ljava/lang/Object;\");");
        this.out.println("    if (JavaCPP_arrayMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_arrayOffsetMID = JavaCPP_getMethodID(env, " + this.jclasses.index(Buffer.class) + ", \"arrayOffset\", \"()I\");");
        this.out.println("    if (JavaCPP_arrayOffsetMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_bufferPositionFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Buffer.class) + ", \"position\", \"I\");");
        this.out.println("    if (JavaCPP_bufferPositionFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_bufferLimitFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Buffer.class) + ", \"limit\", \"I\");");
        this.out.println("    if (JavaCPP_bufferLimitFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_bufferCapacityFID = JavaCPP_getFieldID(env, " + this.jclasses.index(Buffer.class) + ", \"capacity\", \"I\");");
        this.out.println("    if (JavaCPP_bufferCapacityFID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_stringMID = JavaCPP_getMethodID(env, " + this.jclasses.index(String.class) + ", \"<init>\", \"([B)V\");");
        this.out.println("    if (JavaCPP_stringMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_getBytesMID = JavaCPP_getMethodID(env, " + this.jclasses.index(String.class) + ", \"getBytes\", \"()[B\");");
        this.out.println("    if (JavaCPP_getBytesMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_toStringMID = JavaCPP_getMethodID(env, " + this.jclasses.index(Object.class) + ", \"toString\", \"()Ljava/lang/String;\");");
        this.out.println("    if (JavaCPP_toStringMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("#ifdef STRING_BYTES_CHARSET");
        this.out.println("    jmethodID charsetForNameMID = JavaCPP_getStaticMethodID(env, " + this.jclasses.index(Charset.class) + ", \"forName\", \"(Ljava/lang/String;)Ljava/nio/charset/Charset;\");");
        this.out.println("    if (charsetForNameMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    jstring charsetName = env->NewStringUTF(STRING_BYTES_CHARSET);");
        this.out.println("    if (charsetName == NULL || env->ExceptionCheck()) {");
        this.out.println("        JavaCPP_log(\"Error creating java.lang.String from '%s'\", STRING_BYTES_CHARSET);");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_stringBytesCharset = env->CallStaticObjectMethod(JavaCPP_getClass(env, " + this.jclasses.index(Charset.class) + "), charsetForNameMID, charsetName);");
        this.out.println("    if (JavaCPP_stringBytesCharset == NULL || env->ExceptionCheck()) {");
        this.out.println("        JavaCPP_log(\"Error when calling Charset.forName() for '%s'\", STRING_BYTES_CHARSET);");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_stringBytesCharset = env->NewGlobalRef(JavaCPP_stringBytesCharset);");
        this.out.println("    if (JavaCPP_stringBytesCharset == NULL) {");
        this.out.println("        JavaCPP_log(\"Error creating global reference for java.nio.charset.Charset instance\");");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_stringWithCharsetMID = JavaCPP_getMethodID(env, " + this.jclasses.index(String.class) + ", \"<init>\", \"([BLjava/nio/charset/Charset;)V\");");
        this.out.println("    if (JavaCPP_stringWithCharsetMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("    JavaCPP_getBytesWithCharsetMID = JavaCPP_getMethodID(env, " + this.jclasses.index(String.class) + ", \"getBytes\", \"(Ljava/nio/charset/Charset;)[B\");");
        this.out.println("    if (JavaCPP_getBytesWithCharsetMID == NULL) {");
        this.out.println("        return JNI_ERR;");
        this.out.println("    }");
        this.out.println("#endif // STRING_BYTES_CHARSET");
        this.out.println("    return env->GetVersion();");
        this.out.println("}");
        this.out.println();
        if (this.out2 != null) {
            this.out2.println("JNIIMPORT int JavaCPP_uninit" + loadSuffix + "();");
            this.out2.println();
            this.out.println("JNIEXPORT int JavaCPP_uninit" + loadSuffix + "() {");
            this.out.println("#if defined(__ANDROID__) || TARGET_OS_IPHONE");
            this.out.println("    return JNI_OK;");
            this.out.println("#else");
            this.out.println("    JavaVM *vm = JavaCPP_vm;");
            this.out.println("    JNI_OnUnload" + loadSuffix + "(JavaCPP_vm, NULL);");
            this.out.println("    return vm->DestroyJavaVM();");
            this.out.println("#endif");
            this.out.println("}");
        }
        this.out.println();
        this.out.println("JNIEXPORT void JNICALL JNI_OnUnload" + loadSuffix + "(JavaVM* vm, void* reserved) {");
        this.out.println("    JNIEnv* env;");
        this.out.println("    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {");
        this.out.println("        JavaCPP_log(\"Could not get JNIEnv for JNI_VERSION_1_6 inside JNI_OnUnLoad" + loadSuffix + "().\");");
        this.out.println("        return;");
        this.out.println("    }");
        this.out.println("    for (int i = 0; i < " + this.jclasses.size() + "; i++) {");
        this.out.println("        env->DeleteWeakGlobalRef((jweak)JavaCPP_classes[i]);");
        this.out.println("        JavaCPP_classes[i] = NULL;");
        this.out.println("    }");
        this.out.println("#ifdef STRING_BYTES_CHARSET");
        this.out.println("    env->DeleteGlobalRef(JavaCPP_stringBytesCharset);");
        this.out.println("    JavaCPP_stringBytesCharset = NULL;");
        this.out.println("#endif");
        if (baseLoadSuffix != null && !baseLoadSuffix.isEmpty()) {
            this.out.println("    JNI_OnUnload" + baseLoadSuffix + "(vm, reserved);");
        }
        this.out.println("    JavaCPP_vm = NULL;");
        this.out.println("}");
        this.out.println();
        boolean supportedPlatform = false;
        LinkedHashSet allClasses = new LinkedHashSet();
        if (baseLoadSuffix == null || baseLoadSuffix.isEmpty()) {
            supportedPlatform = true;
            allClasses.addAll(baseClasses);
        }
        if (classes != null) {
            void var20_39;
            allClasses.addAll(Arrays.asList(classes));
            memberIterator = classes;
            int typeName2 = memberIterator.length;
            boolean bl2 = false;
            while (var20_39 < typeName2) {
                Class<?> cls = memberIterator[var20_39];
                supportedPlatform |= Loader.checkPlatform(cls, this.properties);
                ++var20_39;
            }
        }
        boolean didSomethingUseful = false;
        for (Class clazz : allClasses) {
            try {
                didSomethingUseful |= this.methods(clazz);
            }
            catch (NoClassDefFoundError e2) {
                this.logger.warn("Could not generate code for class " + clazz.getCanonicalName() + ": " + e2);
            }
        }
        this.out.println("}");
        this.out.println();
        if (this.out2 != null) {
            this.out2.println("#ifdef __cplusplus");
            this.out2.println("}");
            this.out2.println("#endif");
        }
        allClasses.addAll(this.jclasses.keySet());
        LinkedHashSet<Class> reflectClasses = new LinkedHashSet<Class>();
        reflectClasses.addAll(baseClasses);
        reflectClasses.add(BooleanEnum.class);
        reflectClasses.add(ByteEnum.class);
        reflectClasses.add(ShortEnum.class);
        reflectClasses.add(IntEnum.class);
        reflectClasses.add(LongEnum.class);
        reflectClasses.add(Charset.class);
        reflectClasses.add(Object.class);
        reflectClasses.add(Buffer.class);
        reflectClasses.add(String.class);
        reflectClasses.add(Unsafe.class);
        for (Class<?> cls : new LinkedHashSet(allClasses)) {
            while ((cls = cls.getEnclosingClass()) != null) {
                allClasses.add(cls);
            }
        }
        if (declareEnums) {
            allClasses.add(BooleanEnum.class);
            allClasses.add(ByteEnum.class);
            allClasses.add(ShortEnum.class);
            allClasses.add(IntEnum.class);
            allClasses.add(LongEnum.class);
        }
        allClasses.add(Unsafe.class);
        for (PrintWriter o2 : new PrintWriter[]{this.jniConfigOut, this.reflectConfigOut}) {
            if (o2 == null) continue;
            o2.println("[");
            String separator = "";
            for (Class<?> cls : allClasses) {
                o2.println(separator + "  {");
                o2.print("    \"name\" : \"" + cls.getName() + "\"");
                if (reflectClasses.contains(cls) || reflectClasses.contains(cls.getEnclosingClass())) {
                    o2.println(",");
                    o2.println("    \"allDeclaredConstructors\" : true,");
                    o2.println("    \"allPublicConstructors\" : true,");
                    o2.println("    \"allDeclaredMethods\" : true,");
                    o2.println("    \"allPublicMethods\" : true,");
                    o2.println("    \"allDeclaredFields\" : true,");
                    o2.println("    \"allPublicFields\" : true,");
                    o2.println("    \"allDeclaredClasses\" : true,");
                    o2.print("    \"allPublicClasses\" : true");
                } else if (LoadEnabled.class.isAssignableFrom(cls)) {
                    o2.println(",");
                    o2.println("    \"allDeclaredConstructors\" : true,");
                    o2.print("    \"allPublicConstructors\" : true");
                }
                o2.println();
                o2.print("  }");
                separator = "," + System.lineSeparator();
                cls = cls.getEnclosingClass();
            }
            o2.println();
            o2.println("]");
        }
        return supportedPlatform;
    }

    boolean methods(Class<?> cls) {
        if (!Loader.checkPlatform(cls, this.properties)) {
            return false;
        }
        Set<String> memberList = this.members.get(cls);
        if (!cls.isAnnotationPresent(Opaque.class) && cls != Loader.class && !FunctionPointer.class.isAssignableFrom(cls) && cls.getEnclosingClass() != Pointer.class) {
            if (memberList == null) {
                memberList = new LinkedHashSet<String>();
                this.members.put(cls, memberList);
            }
            if (!memberList.contains("sizeof")) {
                memberList.add("sizeof");
            }
        }
        boolean didSomething = false;
        for (Class<?> clazz : cls.getDeclaredClasses()) {
            if (!Pointer.class.isAssignableFrom(clazz) && !Pointer.class.equals(clazz.getEnclosingClass())) continue;
            didSomething |= this.methods(clazz);
        }
        Method[] methods = cls.getDeclaredMethods();
        MethodInformation[] methodInfos = new MethodInformation[methods.length];
        for (int i2 = 0; i2 < methods.length; ++i2) {
            methodInfos[i2] = this.methodInformation(methods[i2]);
        }
        for (Class<?> c3 = cls.getSuperclass(); c3 != null && c3 != Object.class && !Modifier.isAbstract(cls.getModifiers()); c3 = c3.getSuperclass()) {
            for (Method m2 : c3.getDeclaredMethods()) {
                if (!m2.isAnnotationPresent(Virtual.class)) continue;
                boolean found = false;
                String name = m2.getName();
                Object[] types = m2.getParameterTypes();
                for (Method m22 : methods) {
                    if (!name.equals(m22.getName()) || !Arrays.equals(types, m22.getParameterTypes())) continue;
                    found = true;
                    break;
                }
                if (found) continue;
                methods = Arrays.copyOf(methods, methods.length + 1);
                methods[methods.length - 1] = m2;
                methodInfos = Arrays.copyOf(methodInfos, methodInfos.length + 1);
                methodInfos[methods.length - 1] = this.methodInformation(m2);
                methodInfos[methods.length - 1].cls = cls;
            }
        }
        boolean[] blArray = new boolean[methods.length];
        Method[] functionMethods = Generator.functionMethods(cls, blArray);
        boolean firstCallback = true;
        for (int i3 = 0; i3 < methods.length; ++i3) {
            String callbackName;
            if (!Loader.checkPlatform(methods[i3].getAnnotation(Platform.class), this.properties)) continue;
            MethodInformation methodInfo = methodInfos[i3];
            String nativeName = Generator.mangle(cls.getName()) + "_" + Generator.mangle(methods[i3].getName());
            String string = callbackName = blArray[i3] && methodInfo.parameterTypes.length > 0 ? null : "JavaCPP_" + nativeName + "_callback";
            if (blArray[i3] && functionMethods == null) {
                this.logger.warn("No callback method call() or apply() has been not declared in \"" + cls.getCanonicalName() + "\". No code will be generated for callback allocator.");
                continue;
            }
            if (functionMethods != null) {
                for (Method functionMethod : functionMethods) {
                    if ((functionMethod == null || !blArray[i3]) && (!methods[i3].equals(functionMethod) || Modifier.isNative(methods[i3].getModifiers()))) continue;
                    this.functions.index(cls);
                    Name name = methods[i3].getAnnotation(Name.class);
                    if (name != null && name.value().length > 0 && name.value()[0].length() > 0) {
                        callbackName = name.value()[0];
                    }
                    this.callback(cls, functionMethod, callbackName, methodInfo.allocatorMax, firstCallback, null);
                    firstCallback = false;
                    didSomething = true;
                }
            }
            if (!(!Modifier.isNative(methods[i3].getModifiers()) && !Modifier.isAbstract(methods[i3].getModifiers()) || methodInfo.valueGetter || methodInfo.valueSetter || methodInfo.memberGetter || methodInfo.memberSetter || cls.isInterface() || !methods[i3].isAnnotationPresent(Virtual.class) && !methodInfo.allocator)) {
                this.callback(cls, methods[i3], methodInfo.memberName[0], methodInfo.allocatorMax, !methodInfo.allocator, methodInfo);
            }
            if (!Modifier.isNative(methods[i3].getModifiers())) continue;
            if (!(!methodInfo.memberGetter && !methodInfo.memberSetter || methodInfo.noOffset || memberList == null || Modifier.isStatic(methodInfo.modifiers) || memberList.contains(methodInfo.memberName[0]))) {
                memberList.add(methodInfo.memberName[0]);
            }
            didSomething = true;
            this.out.print("JNIEXPORT " + Generator.jniTypeName(methodInfo.returnType) + " JNICALL Java_" + nativeName);
            if (methodInfo.overloaded) {
                this.out.print("__" + Generator.mangle(Generator.signature(methodInfo.parameterTypes)));
            }
            if (Modifier.isStatic(methodInfo.modifiers)) {
                this.out.print("(JNIEnv* env, jclass cls");
            } else {
                this.out.print("(JNIEnv* env, jobject obj");
            }
            for (int j2 = 0; j2 < methodInfo.parameterTypes.length; ++j2) {
                this.out.print(", " + Generator.jniTypeName(methodInfo.parameterTypes[j2]) + " arg" + j2);
            }
            this.out.println(") {");
            if (blArray[i3]) {
                this.callbackAllocator(cls, callbackName, methodInfo.allocatorMax);
                continue;
            }
            if (!(Modifier.isStatic(methodInfo.modifiers) || !Pointer.class.isAssignableFrom(cls) || methodInfo.allocator || methodInfo.arrayAllocator || methodInfo.deallocator)) {
                String[] typeName = this.cppTypeName(cls);
                if ("void*".equals(typeName[0]) && !cls.isAnnotationPresent(Opaque.class)) {
                    typeName[0] = "char*";
                } else if (FunctionPointer.class.isAssignableFrom(cls)) {
                    this.functions.index(cls);
                    typeName[0] = Generator.functionClassName(cls) + "*";
                    typeName[1] = "";
                }
                this.out.println("    " + typeName[0] + " ptr" + typeName[1] + " = (" + typeName[0] + typeName[1] + ")jlong_to_ptr(env->GetLongField(obj, JavaCPP_addressFID));");
                this.out.println("    if (ptr == NULL) {");
                this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"This pointer address is NULL.\");");
                this.out.println("        return" + (methodInfo.returnType == Void.TYPE ? ";" : " 0;"));
                this.out.println("    }");
                if (FunctionPointer.class.isAssignableFrom(cls) && !methodInfo.valueGetter && !methodInfo.valueSetter) {
                    this.out.println("    if (ptr->ptr == NULL) {");
                    this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"This function pointer address is NULL.\");");
                    this.out.println("        return" + (methodInfo.returnType == Void.TYPE ? ";" : " 0;"));
                    this.out.println("    }");
                }
                if (!cls.isAnnotationPresent(Opaque.class)) {
                    this.out.println("    jlong position = env->GetLongField(obj, JavaCPP_positionFID);");
                    if (methodInfo.bufferGetter) {
                        this.out.println("    jlong limit = env->GetLongField(obj, JavaCPP_limitFID);");
                        this.out.println("    jlong capacity = env->GetLongField(obj, JavaCPP_capacityFID);");
                    } else {
                        this.out.println("    ptr += position;");
                    }
                }
            }
            this.parametersBefore(methodInfo);
            String returnPrefix = this.returnBefore(methodInfo);
            this.call(methodInfo, returnPrefix, false);
            this.returnAfter(methodInfo);
            this.parametersAfter(methodInfo);
            if (methodInfo.throwsException != null) {
                this.out.println("    if (exc != NULL) {");
                this.out.println("        env->Throw(exc);");
                this.out.println("    }");
            }
            if (methodInfo.returnType != Void.TYPE) {
                this.out.println("    return rarg;");
            }
            this.out.println("}");
        }
        this.out.println();
        return didSomething;
    }

    void parametersBefore(MethodInformation methodInfo) {
        int skipParameters;
        String adapterLine = "";
        AdapterInformation prevAdapterInfo = null;
        for (int j2 = skipParameters = methodInfo.parameterTypes.length > 0 && methodInfo.parameterTypes[0] == Class.class ? 1 : 0; j2 < methodInfo.parameterTypes.length; ++j2) {
            String s2;
            AdapterInformation adapterInfo;
            String[] stringArray;
            if (methodInfo.parameterTypes[j2].isPrimitive()) continue;
            Annotation passBy = this.by(methodInfo, j2);
            String cast = this.cast(methodInfo, j2);
            if (methodInfo.parameterRaw[j2]) {
                String[] stringArray2 = new String[1];
                stringArray = stringArray2;
                stringArray2[0] = "";
            } else {
                stringArray = this.cppTypeName(methodInfo, j2);
            }
            String[] typeName = stringArray;
            AdapterInformation adapterInformation = adapterInfo = methodInfo.parameterRaw[j2] ? null : this.adapterInformation(false, methodInfo, j2);
            if (Enum.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                this.accessesEnums = true;
                s2 = this.enumValueType(methodInfo.parameterTypes[j2]);
                if (s2 == null) continue;
                String S = Character.toUpperCase(s2.charAt(0)) + s2.substring(1);
                this.out.println("    if (arg" + j2 + " == NULL) {");
                this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"Enum for argument " + j2 + " is NULL.\");");
                this.out.println("        return" + (methodInfo.returnType == Void.TYPE ? ";" : " 0;"));
                this.out.println("    }");
                this.out.println("    " + typeName[0] + " val" + j2 + typeName[1] + " = (" + typeName[0] + typeName[1] + ")env->Get" + S + "Field(arg" + j2 + ", JavaCPP_" + s2 + "ValueFID);");
                continue;
            }
            if (FunctionPointer.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                this.functions.index(methodInfo.parameterTypes[j2]);
                if (methodInfo.parameterTypes[j2] == FunctionPointer.class) {
                    this.logger.warn("Method \"" + methodInfo.method + "\" has an abstract FunctionPointer parameter, but a concrete subclass is required. Compilation will most likely fail.");
                }
                typeName[0] = Generator.functionClassName(methodInfo.parameterTypes[j2]) + "*";
                typeName[1] = "";
            }
            if (typeName[0].length() == 0 || methodInfo.parameterRaw[j2]) {
                methodInfo.parameterRaw[j2] = true;
                typeName[0] = Generator.jniTypeName(methodInfo.parameterTypes[j2]);
                this.out.println("    " + typeName[0] + " ptr" + j2 + " = arg" + j2 + ";");
                continue;
            }
            if ("void*".equals(typeName[0]) && !methodInfo.parameterTypes[j2].isAnnotationPresent(Opaque.class)) {
                typeName[0] = "char*";
            }
            this.out.print("    " + typeName[0] + " ptr" + j2 + typeName[1] + " = ");
            if (Pointer.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                this.out.println("arg" + j2 + " == NULL ? NULL : (" + typeName[0] + typeName[1] + ")jlong_to_ptr(env->GetLongField(arg" + j2 + ", JavaCPP_addressFID));");
                if (j2 == 0 && FunctionPointer.class.isAssignableFrom(methodInfo.cls) && methodInfo.cls.isAnnotationPresent(Namespace.class) || passBy instanceof ByVal && ((ByVal)passBy).nullValue().length() == 0 || passBy instanceof ByRef && ((ByRef)passBy).nullValue().length() == 0) {
                    this.out.println("    if (ptr" + j2 + " == NULL) {");
                    this.out.println("        env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"Pointer address of argument " + j2 + " is NULL.\");");
                    this.out.println("        return" + (methodInfo.returnType == Void.TYPE ? ";" : " 0;"));
                    this.out.println("    }");
                }
                if (adapterInfo != null || prevAdapterInfo != null) {
                    this.out.println("    jlong size" + j2 + " = arg" + j2 + " == NULL ? 0 : env->GetLongField(arg" + j2 + ", JavaCPP_limitFID);");
                    this.out.println("    void* owner" + j2 + " = JavaCPP_getPointerOwner(env, arg" + j2 + ");");
                }
                if (!methodInfo.parameterTypes[j2].isAnnotationPresent(Opaque.class)) {
                    this.out.println("    jlong position" + j2 + " = arg" + j2 + " == NULL ? 0 : env->GetLongField(arg" + j2 + ", JavaCPP_positionFID);");
                    this.out.println("    ptr" + j2 + " += position" + j2 + ";");
                    if (adapterInfo != null || prevAdapterInfo != null) {
                        this.out.println("    size" + j2 + " -= position" + j2 + ";");
                    }
                }
            } else if (methodInfo.parameterTypes[j2] == String.class) {
                this.passesStrings = true;
                this.out.println(Generator.getStringData("arg" + j2, Generator.asUtf16(methodInfo, j2)));
                if (adapterInfo != null || prevAdapterInfo != null) {
                    this.out.println("    jlong size" + j2 + " = 0;");
                    this.out.println("    void* owner" + j2 + " = (void*)ptr" + j2 + ";");
                }
            } else if (methodInfo.parameterTypes[j2].isArray() && methodInfo.parameterTypes[j2].getComponentType().isPrimitive()) {
                this.out.print("arg" + j2 + " == NULL ? NULL : ");
                s2 = methodInfo.parameterTypes[j2].getComponentType().getName();
                if (methodInfo.criticalRegion || methodInfo.valueGetter || methodInfo.valueSetter || methodInfo.memberGetter || methodInfo.memberSetter) {
                    this.out.println("(j" + s2 + "*)env->GetPrimitiveArrayCritical(arg" + j2 + ", NULL);");
                } else {
                    s2 = Character.toUpperCase(s2.charAt(0)) + s2.substring(1);
                    this.out.println("env->Get" + s2 + "ArrayElements(arg" + j2 + ", NULL);");
                }
                if (adapterInfo != null || prevAdapterInfo != null) {
                    this.out.println("    jlong size" + j2 + " = arg" + j2 + " == NULL ? 0 : env->GetArrayLength(arg" + j2 + ");");
                    this.out.println("    void* owner" + j2 + " = (void*)ptr" + j2 + ";");
                }
            } else if (Buffer.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                this.out.println("arg" + j2 + " == NULL ? NULL : (" + typeName[0] + typeName[1] + ")env->GetDirectBufferAddress(arg" + j2 + ");");
                if (adapterInfo != null || prevAdapterInfo != null) {
                    this.out.println("    jlong size" + j2 + " = arg" + j2 + " == NULL ? 0 : env->GetIntField(arg" + j2 + ", JavaCPP_bufferLimitFID);");
                    this.out.println("    void* owner" + j2 + " = (void*)ptr" + j2 + ";");
                }
                if (methodInfo.parameterTypes[j2] != Buffer.class) {
                    String paramName = methodInfo.parameterTypes[j2].getSimpleName();
                    paramName = paramName.substring(0, paramName.length() - 6);
                    String paramNameLowerCase = Character.toLowerCase(paramName.charAt(0)) + paramName.substring(1);
                    this.out.println("    j" + paramNameLowerCase + "Array arr" + j2 + " = NULL;");
                    this.out.println("    jlong offset" + j2 + " = 0;");
                    this.out.println("    if (arg" + j2 + " != NULL && ptr" + j2 + " == NULL) {");
                    this.out.println("        arr" + j2 + " = (j" + paramNameLowerCase + "Array)env->CallObjectMethod(arg" + j2 + ", JavaCPP_arrayMID);");
                    this.out.println("        offset" + j2 + " = env->CallIntMethod(arg" + j2 + ", JavaCPP_arrayOffsetMID);");
                    this.out.println("        if (env->ExceptionOccurred() != NULL) {");
                    this.out.println("            return" + (methodInfo.returnType == Void.TYPE ? ";" : " 0;"));
                    this.out.println("        } else {");
                    if (methodInfo.criticalRegion) {
                        this.out.println("            ptr" + j2 + " = arr" + j2 + " == NULL ? NULL : (" + typeName[0] + typeName[1] + ")env->GetPrimitiveArrayCritical(arr" + j2 + ", NULL) + offset" + j2 + ";");
                    } else {
                        this.out.println("            ptr" + j2 + " = arr" + j2 + " == NULL ? NULL : env->Get" + paramName + "ArrayElements(arr" + j2 + ", NULL) + offset" + j2 + ";");
                    }
                    this.out.println("        }");
                    this.out.println("    }");
                }
                this.out.println("    jlong position" + j2 + " = arg" + j2 + " == NULL ? 0 : env->GetIntField(arg" + j2 + ", JavaCPP_bufferPositionFID);");
                this.out.println("    ptr" + j2 + " += position" + j2 + ";");
                if (adapterInfo != null || prevAdapterInfo != null) {
                    this.out.println("    size" + j2 + " -= position" + j2 + ";");
                }
            } else {
                this.out.println("arg" + j2 + ";");
                this.logger.warn("Method \"" + methodInfo.method + "\" has an unsupported parameter of type \"" + methodInfo.parameterTypes[j2].getCanonicalName() + "\". Compilation will most likely fail.");
            }
            if (adapterInfo != null) {
                this.usesAdapters = true;
                adapterLine = "    " + adapterInfo.name + " adapter" + j2 + "(";
                prevAdapterInfo = adapterInfo;
            }
            if (prevAdapterInfo != null) {
                if (!FunctionPointer.class.isAssignableFrom(methodInfo.cls)) {
                    adapterLine = adapterLine + cast;
                }
                adapterLine = adapterLine + "ptr" + j2 + ", size" + j2 + ", owner" + j2;
                if (--prevAdapterInfo.argc > 0) {
                    adapterLine = adapterLine + ", ";
                }
            }
            if (prevAdapterInfo == null || prevAdapterInfo.argc > 0) continue;
            this.out.println(adapterLine + ");");
            prevAdapterInfo = null;
        }
    }

    String returnBefore(MethodInformation methodInfo) {
        String returnPrefix = "";
        if (methodInfo.returnType == Void.TYPE) {
            if (methodInfo.allocator || methodInfo.arrayAllocator) {
                this.jclasses.index(methodInfo.cls);
                String[] typeName = this.cppTypeName(methodInfo.cls);
                returnPrefix = typeName[0] + " rptr" + typeName[1] + " = ";
            }
        } else {
            String[] stringArray;
            String cast = this.cast(methodInfo.returnType, methodInfo.annotations);
            if (methodInfo.returnRaw) {
                String[] stringArray2 = new String[1];
                stringArray = stringArray2;
                stringArray2[0] = "";
            } else {
                stringArray = this.cppCastTypeName(methodInfo.returnType, methodInfo.annotations);
            }
            String[] typeName = stringArray;
            Annotation returnBy = this.by(methodInfo.annotations);
            if (FunctionPointer.class.isAssignableFrom(methodInfo.cls) && !methodInfo.cls.isAnnotationPresent(Namespace.class) && methodInfo.valueGetter) {
                typeName = this.cppTypeName(methodInfo.cls);
            }
            if (methodInfo.valueSetter || methodInfo.memberSetter || methodInfo.noReturnGetter) {
                this.out.println("    jobject rarg = obj;");
            } else if (methodInfo.returnType.isPrimitive()) {
                this.out.println("    " + Generator.jniTypeName(methodInfo.returnType) + " rarg = 0;");
                returnPrefix = typeName[0] + " rval" + typeName[1] + " = " + cast;
                if (returnBy instanceof ByPtr || returnBy instanceof ByPtrRef) {
                    returnPrefix = returnPrefix + "*";
                }
            } else if (Enum.class.isAssignableFrom(methodInfo.returnType)) {
                this.accessesEnums = true;
                this.out.println("    jobject rarg = JavaCPP_createPointer(env, " + this.jclasses.index(methodInfo.returnType) + ");");
                returnPrefix = typeName[0] + " rval" + typeName[1] + " = " + cast;
            } else {
                String valueTypeName = Generator.valueTypeName(typeName);
                AdapterInformation adapterInfo = this.adapterInformation(false, valueTypeName, methodInfo.annotations);
                returnPrefix = "rptr = " + cast;
                if (typeName[0].length() == 0 || methodInfo.returnRaw) {
                    methodInfo.returnRaw = true;
                    typeName[0] = Generator.jniTypeName(methodInfo.returnType);
                    this.out.println("    " + typeName[0] + " rarg = NULL;");
                    this.out.println("    " + typeName[0] + " rptr;");
                } else if (Pointer.class.isAssignableFrom(methodInfo.returnType) || Buffer.class.isAssignableFrom(methodInfo.returnType) || methodInfo.returnType.isArray() && methodInfo.returnType.getComponentType().isPrimitive()) {
                    if (FunctionPointer.class.isAssignableFrom(methodInfo.returnType)) {
                        this.functions.index(methodInfo.returnType);
                        returnPrefix = "if (rptr != NULL) rptr->ptr = ";
                        if (methodInfo.method.isAnnotationPresent(Virtual.class)) {
                            returnPrefix = returnPrefix + "(" + typeName[0] + typeName[1] + ")&";
                        }
                        typeName[0] = Generator.functionClassName(methodInfo.returnType) + "*";
                        typeName[1] = "";
                        valueTypeName = Generator.valueTypeName(typeName);
                    }
                    if (returnBy instanceof ByVal || returnBy instanceof ByRef && ((ByRef)returnBy).value()) {
                        returnPrefix = returnPrefix + (Generator.noException(methodInfo.returnType, methodInfo.method) ? "new (std::nothrow) " : "new ") + valueTypeName + typeName[1] + "(";
                    } else if (returnBy instanceof ByRef) {
                        returnPrefix = returnPrefix + "&";
                    } else if (returnBy instanceof ByPtrPtr) {
                        if (cast.length() > 0) {
                            typeName[0] = typeName[0].substring(0, typeName[0].length() - 1);
                        }
                        returnPrefix = "rptr = NULL; " + typeName[0] + "* rptrptr" + typeName[1] + " = " + cast;
                    }
                    if (adapterInfo != null && methodInfo.returnType.isArray() && methodInfo.returnType.getComponentType().isPrimitive() && !typeName[0].startsWith("const ")) {
                        typeName[0] = "const " + typeName[0];
                    }
                    if (methodInfo.bufferGetter) {
                        this.out.println("    jobject rarg = NULL;");
                        this.out.println("    char* rptr;");
                    } else {
                        this.out.println("    " + Generator.jniTypeName(methodInfo.returnType) + " rarg = NULL;");
                        this.out.println("    " + typeName[0] + " rptr" + typeName[1] + ";");
                    }
                    if (FunctionPointer.class.isAssignableFrom(methodInfo.returnType)) {
                        this.out.println("    rptr = new (std::nothrow) " + valueTypeName + ";");
                        if (returnBy instanceof ByPtrPtr) {
                            String[] cpptypeName = this.cppTypeName(methodInfo.returnType, methodInfo.annotations);
                            returnPrefix = cpptypeName[0] + "* rptrptr" + cpptypeName[1] + " = ";
                        }
                    }
                } else if (methodInfo.returnType == String.class) {
                    if (returnBy instanceof ByPtrPtr) {
                        typeName[0] = valueTypeName;
                    }
                    if (!typeName[0].startsWith("const ")) {
                        typeName[0] = "const " + typeName[0];
                    }
                    this.out.println("    jstring rarg = NULL;");
                    this.out.println("    " + typeName[0] + " rptr;");
                    returnPrefix = returnBy instanceof ByRef ? "std::basic_string<" + valueTypeName + "> rstr(" : (returnBy instanceof ByPtrPtr ? "rptr = NULL; " + typeName[0] + "* rptrptr = (" + typeName[0] + "*)" : returnPrefix + "(" + typeName[0] + ")");
                } else {
                    this.logger.warn("Method \"" + methodInfo.method + "\" has unsupported return type \"" + methodInfo.returnType.getCanonicalName() + "\". Compilation will most likely fail.");
                }
                if (adapterInfo != null) {
                    this.usesAdapters = true;
                    String cast2 = adapterInfo.cast2.trim();
                    if (cast2.length() > 0 && !cast2.startsWith("(") && !cast2.endsWith(")")) {
                        cast2 = "(" + cast2 + ")";
                    }
                    returnPrefix = adapterInfo.name + " radapter(" + cast2;
                }
            }
        }
        if (methodInfo.throwsException != null) {
            this.out.println("    jthrowable exc = NULL;");
            this.out.println("    try {");
        }
        return returnPrefix;
    }

    void call(MethodInformation methodInfo, String returnPrefix, boolean secondCall) {
        String cast;
        boolean needSecondCall = false;
        String indent = secondCall ? "" : (methodInfo.throwsException != null ? "        " : "    ");
        String prefix = "(";
        String suffix = ")";
        int skipParameters = methodInfo.parameterTypes.length > 0 && methodInfo.parameterTypes[0] == Class.class ? 1 : 0;
        Index index = methodInfo.method.getAnnotation(Index.class);
        if (index == null && methodInfo.pairedMethod != null) {
            index = methodInfo.pairedMethod.getAnnotation(Index.class);
        }
        if (methodInfo.deallocator) {
            this.out.println(indent + "void* allocatedAddress = jlong_to_ptr(arg0);");
            this.out.println(indent + "void (*deallocatorAddress)(void*) = (void(*)(void*))jlong_to_ptr(arg1);");
            this.out.println(indent + "if (deallocatorAddress != NULL && allocatedAddress != NULL) {");
            this.out.println(indent + "    (*deallocatorAddress)(allocatedAddress);");
            this.out.println(indent + "}");
            return;
        }
        if (!FunctionPointer.class.isAssignableFrom(methodInfo.cls) && (methodInfo.valueGetter || methodInfo.valueSetter || methodInfo.memberGetter || methodInfo.memberSetter)) {
            boolean wantsPointer = false;
            int k2 = methodInfo.parameterTypes.length - 1;
            if ((methodInfo.valueSetter || methodInfo.memberSetter) && !(this.by(methodInfo, k2) instanceof ByRef) && this.adapterInformation(false, methodInfo, k2) == null && methodInfo.parameterTypes[k2] == String.class) {
                if (Generator.asUtf16(methodInfo, k2)) {
                    this.out.print(indent + "memcpy(");
                    suffix = ", (std::char_traits<unsigned short>::length(ptr" + k2 + ") + 1) * sizeof(unsigned short))";
                } else {
                    this.out.print(indent + "strcpy((char*)");
                }
                wantsPointer = true;
                prefix = ", ";
            } else if (k2 >= 1 && methodInfo.parameterTypes[0].isArray() && methodInfo.parameterTypes[0].getComponentType().isPrimitive() && (methodInfo.parameterTypes[1] == Integer.TYPE || methodInfo.parameterTypes[1] == Long.TYPE)) {
                this.out.print(indent + "memcpy(");
                wantsPointer = true;
                prefix = ", ";
                if (methodInfo.memberGetter || methodInfo.valueGetter) {
                    this.out.print("ptr0 + arg1, ");
                } else {
                    prefix = prefix + "ptr0 + arg1, ";
                }
                skipParameters = 2;
                suffix = " * sizeof(*ptr0)" + suffix;
            } else {
                this.out.print(indent + returnPrefix);
                prefix = methodInfo.valueGetter || methodInfo.memberGetter ? "" : " = ";
                suffix = "";
            }
            if (Modifier.isStatic(methodInfo.modifiers) || !Pointer.class.isAssignableFrom(methodInfo.cls)) {
                this.out.print(Generator.cppScopeName(methodInfo));
            } else if (methodInfo.memberGetter || methodInfo.memberSetter) {
                if (index != null) {
                    this.out.print("(*ptr)");
                    prefix = "." + methodInfo.memberName[0] + prefix;
                } else {
                    this.out.print("ptr->" + methodInfo.memberName[0]);
                }
            } else {
                cast = this.cast(methodInfo.returnType, methodInfo.annotations);
                if (index == null && cast.length() > 0) {
                    this.out.print("*(" + cast.substring(1, cast.length() - 1) + "*)&");
                }
                this.out.print(index != null ? "(*ptr)" : (methodInfo.dim > 0 || wantsPointer ? "ptr" : "*ptr"));
            }
        } else if (methodInfo.bufferGetter) {
            this.out.print(indent + returnPrefix + "ptr");
            prefix = "";
            suffix = "";
        } else {
            this.out.print(indent + returnPrefix);
            if (FunctionPointer.class.isAssignableFrom(methodInfo.cls)) {
                if (methodInfo.cls.isAnnotationPresent(Namespace.class)) {
                    this.out.print("(ptr0->*(ptr->ptr))");
                    skipParameters = 1;
                    if (methodInfo.valueGetter || methodInfo.valueSetter) {
                        prefix = methodInfo.valueGetter ? "" : " = ";
                        suffix = "";
                    }
                } else if (methodInfo.valueGetter || methodInfo.valueSetter) {
                    this.out.print("ptr->ptr");
                    prefix = methodInfo.valueGetter ? "" : " = ";
                    suffix = "";
                } else {
                    this.out.print("(*ptr->ptr)");
                }
            } else if (methodInfo.allocator) {
                String[] typeName = this.cppTypeName(methodInfo.cls);
                String valueTypeName = Generator.valueTypeName(typeName);
                if (this.virtualFunctions.containsKey(methodInfo.cls)) {
                    String subType;
                    valueTypeName = subType = "JavaCPP_" + Generator.mangle(valueTypeName);
                }
                if (methodInfo.cls == Pointer.class) {
                    prefix = "";
                    suffix = "";
                } else {
                    this.out.print((Generator.noException(methodInfo.cls, methodInfo.method) ? "new (std::nothrow) " : "new ") + valueTypeName + typeName[1]);
                    if (methodInfo.arrayAllocator) {
                        prefix = "[";
                        suffix = "]";
                    }
                }
            } else if (Modifier.isStatic(methodInfo.modifiers) || !Pointer.class.isAssignableFrom(methodInfo.cls)) {
                this.out.print(Generator.cppScopeName(methodInfo));
            } else {
                String name = methodInfo.memberName[0];
                String[] typeName = this.cppTypeName(methodInfo.cls);
                String valueTypeName = Generator.valueTypeName(typeName);
                if (this.virtualFunctions.containsKey(methodInfo.cls) && !secondCall) {
                    String subType = "JavaCPP_" + Generator.mangle(valueTypeName);
                    if (Modifier.isPublic(methodInfo.method.getModifiers())) {
                        this.out.print("(dynamic_cast<" + subType + "*>(ptr) != NULL ? ");
                        needSecondCall = true;
                    }
                    if (methodInfo.method.isAnnotationPresent(Virtual.class)) {
                        name = "super_" + methodInfo.name;
                    }
                    this.out.print("((" + subType + "*)ptr)->" + name);
                } else if (secondCall && methodInfo.method.getDeclaringClass() != methodInfo.cls) {
                    String[] typeName2 = this.cppTypeName(methodInfo.method.getDeclaringClass());
                    this.out.print("((" + typeName2[0] + typeName2[1] + ")ptr)->" + name);
                } else if (index != null) {
                    this.out.print("(*ptr)");
                    prefix = "." + name + prefix;
                } else {
                    String op2;
                    String string = op2 = name.startsWith("operator") ? name.substring(8).trim() : "";
                    if (methodInfo.parameterTypes.length > 0 && (op2.equals("=") || op2.equals("+") || op2.equals("-") || op2.equals("*") || op2.equals("/") || op2.equals("%") || op2.equals("==") || op2.equals("!=") || op2.equals("<") || op2.equals(">") || op2.equals("<=") || op2.equals(">="))) {
                        this.out.print("((*ptr)");
                        prefix = op2 + prefix;
                        suffix = suffix + ")";
                    } else {
                        this.out.print("ptr->" + name);
                    }
                }
            }
        }
        for (int j2 = skipParameters; j2 <= methodInfo.parameterTypes.length; ++j2) {
            AdapterInformation adapterInfo;
            if (j2 == skipParameters + methodInfo.dim) {
                if (methodInfo.memberName.length > 1) {
                    this.out.print(methodInfo.memberName[1]);
                }
                this.out.print(prefix);
                if (methodInfo.withEnv) {
                    this.out.print(Modifier.isStatic(methodInfo.modifiers) ? "env, cls" : "env, obj");
                    if (methodInfo.parameterTypes.length - skipParameters - methodInfo.dim > 0) {
                        this.out.print(", ");
                    }
                }
            }
            if (j2 == methodInfo.parameterTypes.length) break;
            if (j2 < skipParameters + methodInfo.dim) {
                if (index == null || index.function().length() == 0) {
                    this.out.print("[");
                } else {
                    this.out.print("." + index.function() + "(");
                }
            }
            Annotation passBy = this.by(methodInfo, j2);
            cast = this.cast(methodInfo, j2);
            AdapterInformation adapterInformation = adapterInfo = methodInfo.parameterRaw[j2] ? null : this.adapterInformation(false, methodInfo, j2);
            if (FunctionPointer.class.isAssignableFrom(methodInfo.cls) && !methodInfo.cls.isAnnotationPresent(Namespace.class) && methodInfo.valueSetter) {
                String[] typeName = this.cppTypeName(methodInfo.cls);
                cast = "(" + typeName[0] + typeName[1] + ")";
            }
            if (Enum.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                this.accessesEnums = true;
                this.out.print(cast + "val" + j2);
            } else if (("(void*)".equals(cast) || "(void *)".equals(cast)) && methodInfo.parameterTypes[j2] == Long.TYPE) {
                this.out.print("jlong_to_ptr(arg" + j2 + ")");
            } else if (methodInfo.parameterTypes[j2].isPrimitive()) {
                if (passBy instanceof ByPtr || passBy instanceof ByPtrRef) {
                    this.out.print("&");
                }
                this.out.print(cast + "arg" + j2);
            } else if (adapterInfo != null) {
                String cast2;
                cast = adapterInfo.cast.trim();
                if (cast.length() > 0 && !cast.startsWith("(") && !cast.endsWith(")")) {
                    cast = "(" + cast + ")";
                }
                if ((cast2 = adapterInfo.cast2.trim()).length() > 0 && !cast2.startsWith("(") && !cast2.endsWith(")")) {
                    cast2 = "(" + cast2 + ")";
                }
                this.out.print(cast + cast2 + "adapter" + j2);
                j2 += adapterInfo.argc - 1;
            } else if (FunctionPointer.class.isAssignableFrom(methodInfo.parameterTypes[j2]) && !(passBy instanceof ByVal) && !(passBy instanceof ByRef)) {
                if (passBy instanceof ByPtrRef) {
                    this.out.print(cast + "(ptr" + j2 + "->ptr)");
                } else {
                    this.out.print(cast + "(ptr" + j2 + " == NULL ? NULL : " + (passBy instanceof ByPtrPtr ? "&ptr" : "ptr") + j2 + "->ptr)");
                }
            } else if (passBy instanceof ByVal || passBy instanceof ByRef && methodInfo.parameterTypes[j2] != String.class) {
                String nullValue;
                boolean rvalue;
                boolean bl2 = rvalue = passBy instanceof ByRef ? ((ByRef)passBy).value() : false;
                String string = passBy instanceof ByVal ? ((ByVal)passBy).nullValue() : (nullValue = passBy instanceof ByRef ? ((ByRef)passBy).nullValue() : "");
                if (rvalue) {
                    this.out.print("std::move(");
                }
                this.out.print((nullValue.length() > 0 ? "ptr" + j2 + " == NULL ? " + nullValue + " : " : "") + "*" + cast + "ptr" + j2);
                if (rvalue) {
                    this.out.print(")");
                }
            } else if (passBy instanceof ByPtrPtr) {
                this.out.print(cast + "(arg" + j2 + " == NULL ? NULL : &ptr" + j2 + ")");
            } else {
                this.out.print(cast + "ptr" + j2);
            }
            if (j2 < skipParameters + methodInfo.dim) {
                if (index == null || index.function().length() == 0) {
                    this.out.print("]");
                    continue;
                }
                this.out.print(")");
                continue;
            }
            if (j2 >= methodInfo.parameterTypes.length - 1) continue;
            this.out.print(", ");
        }
        this.out.print(suffix);
        if (methodInfo.memberName.length > 2) {
            this.out.print(methodInfo.memberName[2]);
        }
        if (needSecondCall) {
            this.call(methodInfo, " : ", true);
            this.out.print(")");
        }
    }

    void returnAfter(MethodInformation methodInfo) {
        String suffix;
        String[] stringArray;
        String indent;
        String string = indent = methodInfo.throwsException != null ? "        " : "    ";
        if (methodInfo.returnRaw) {
            String[] stringArray2 = new String[1];
            stringArray = stringArray2;
            stringArray2[0] = "";
        } else {
            stringArray = this.cppCastTypeName(methodInfo.returnType, methodInfo.annotations);
        }
        String[] typeName = stringArray;
        Annotation returnBy = this.by(methodInfo.annotations);
        String valueTypeName = Generator.valueTypeName(typeName);
        AdapterInformation adapterInfo = this.adapterInformation(false, valueTypeName, methodInfo.annotations);
        String string2 = suffix = methodInfo.deallocator ? "" : ";";
        if (this.by(methodInfo.annotations) instanceof ByRef && methodInfo.returnType == String.class && adapterInfo == null) {
            this.out.print(");\n" + indent + "rptr = rstr.c_str()");
        }
        if (!methodInfo.returnType.isPrimitive() && adapterInfo != null) {
            suffix = ")" + suffix;
        }
        if (Pointer.class.isAssignableFrom(methodInfo.returnType) || methodInfo.returnType.isArray() && methodInfo.returnType.getComponentType().isPrimitive() || Buffer.class.isAssignableFrom(methodInfo.returnType) || methodInfo.returnType == String.class) {
            if ((returnBy instanceof ByVal || returnBy instanceof ByRef && ((ByRef)returnBy).value()) && adapterInfo == null) {
                suffix = ")" + suffix;
            } else if (returnBy instanceof ByPtrPtr) {
                this.out.println(suffix);
                suffix = "";
                this.out.println(indent + "if (rptrptr == NULL) {");
                this.out.println(indent + "    env->ThrowNew(JavaCPP_getClass(env, " + this.jclasses.index(NullPointerException.class) + "), \"Return pointer address is NULL.\");");
                this.out.println(indent + "} else {");
                if (FunctionPointer.class.isAssignableFrom(methodInfo.returnType)) {
                    this.out.println(indent + "    rptr->ptr = *rptrptr;");
                } else {
                    this.out.println(indent + "    rptr = *rptrptr;");
                }
                this.out.println(indent + "}");
            }
            if (adapterInfo != null && methodInfo.returnType.isArray() && methodInfo.returnType.getComponentType().isPrimitive() && !typeName[0].startsWith("const ")) {
                typeName[0] = "const " + typeName[0];
            }
        }
        this.out.println(suffix);
        if (methodInfo.returnType == Void.TYPE) {
            if (methodInfo.allocator || methodInfo.arrayAllocator) {
                this.out.println(indent + "jlong rcapacity = " + (methodInfo.arrayAllocator ? "arg0;" : "1;"));
                boolean noDeallocator = methodInfo.cls == Pointer.class || methodInfo.cls.isAnnotationPresent(NoDeallocator.class) || methodInfo.method.isAnnotationPresent(NoDeallocator.class);
                this.out.print(indent + "JavaCPP_initPointer(env, obj, rptr, rcapacity, rptr, ");
                if (noDeallocator) {
                    this.out.println("NULL);");
                } else if (methodInfo.arrayAllocator) {
                    this.out.println("&JavaCPP_" + Generator.mangle(methodInfo.cls.getName()) + "_deallocateArray);");
                    this.arrayDeallocators.index(methodInfo.cls);
                } else {
                    this.out.println("&JavaCPP_" + Generator.mangle(methodInfo.cls.getName()) + "_deallocate);");
                    this.deallocators.index(methodInfo.cls);
                }
                if (this.virtualFunctions.containsKey(methodInfo.cls)) {
                    typeName = this.cppTypeName(methodInfo.cls);
                    valueTypeName = Generator.valueTypeName(typeName);
                    String subType = "JavaCPP_" + Generator.mangle(valueTypeName);
                    this.out.println(indent + "((" + subType + "*)rptr)->obj = env->NewWeakGlobalRef(obj);");
                }
            }
        } else if (!(methodInfo.valueSetter || methodInfo.memberSetter || methodInfo.noReturnGetter)) {
            if (methodInfo.returnType.isPrimitive()) {
                this.out.println(indent + "rarg = (" + Generator.jniTypeName(methodInfo.returnType) + ")rval;");
            } else if (methodInfo.returnRaw) {
                this.out.println(indent + "rarg = rptr;");
            } else if (Enum.class.isAssignableFrom(methodInfo.returnType)) {
                this.accessesEnums = true;
                String s2 = this.enumValueType(methodInfo.returnType);
                if (s2 != null) {
                    String S = Character.toUpperCase(s2.charAt(0)) + s2.substring(1);
                    this.out.println(indent + "if (rarg != NULL) {");
                    this.out.println(indent + "    env->Set" + S + "Field(rarg, JavaCPP_" + s2 + "ValueFID, (j" + s2 + ")rval);");
                    this.out.println(indent + "}");
                }
            } else {
                boolean needInit = false;
                if (adapterInfo != null) {
                    this.out.println(indent + "rptr = radapter;");
                    if (methodInfo.returnType != String.class) {
                        this.out.println(indent + "jlong rcapacity = (jlong)radapter.size;");
                        if (Pointer.class.isAssignableFrom(methodInfo.returnType)) {
                            this.out.println(indent + "void* rowner = radapter.owner;");
                            this.out.println(indent + "void (*deallocator)(void*) = rowner != NULL ? &" + adapterInfo.name + "::deallocate : 0;");
                        } else {
                            this.out.println(indent + "void (*deallocator)(void*) = 0;");
                        }
                    }
                    needInit = true;
                } else if (returnBy instanceof ByVal || FunctionPointer.class.isAssignableFrom(methodInfo.returnType)) {
                    this.out.println(indent + "jlong rcapacity = 1;");
                    this.out.println(indent + "void* rowner = (void*)rptr;");
                    this.out.println(indent + "void (*deallocator)(void*) = &JavaCPP_" + Generator.mangle(methodInfo.returnType.getName()) + "_deallocate;");
                    this.deallocators.index(methodInfo.returnType);
                    needInit = true;
                }
                if (Pointer.class.isAssignableFrom(methodInfo.returnType)) {
                    this.out.print(indent);
                    if (!(returnBy instanceof ByVal)) {
                        if (Modifier.isStatic(methodInfo.modifiers) && methodInfo.parameterTypes.length > 0) {
                            for (int i2 = 0; i2 < methodInfo.parameterTypes.length; ++i2) {
                                String cast = this.cast(methodInfo, i2);
                                if (!Arrays.equals(methodInfo.parameterAnnotations[i2], methodInfo.annotations) || methodInfo.parameterTypes[i2] != methodInfo.returnType || returnBy instanceof ByPtrPtr || returnBy instanceof ByPtrRef) continue;
                                this.out.println("if (rptr == " + cast + "ptr" + i2 + ") {");
                                this.out.println(indent + "    rarg = arg" + i2 + ";");
                                this.out.print(indent + "} else ");
                            }
                        } else if (!Modifier.isStatic(methodInfo.modifiers) && methodInfo.cls == methodInfo.returnType) {
                            this.out.println("if (rptr == ptr) {");
                            this.out.println(indent + "    rarg = obj;");
                            this.out.print(indent + "} else ");
                        }
                    }
                    this.out.println("if (rptr != NULL) {");
                    this.out.println(indent + "    rarg = JavaCPP_createPointer(env, " + this.jclasses.index(methodInfo.returnType) + (methodInfo.parameterTypes.length > 0 && methodInfo.parameterTypes[0] == Class.class ? ", arg0);" : ");"));
                    this.out.println(indent + "    if (rarg != NULL) {");
                    if (needInit) {
                        this.out.println(indent + "        JavaCPP_initPointer(env, rarg, rptr, rcapacity, rowner, deallocator);");
                    } else {
                        this.out.println(indent + "        env->SetLongField(rarg, JavaCPP_addressFID, ptr_to_jlong(rptr));");
                    }
                    this.out.println(indent + "    }");
                    this.out.println(indent + "}");
                } else if (methodInfo.returnType == String.class) {
                    this.passesStrings = true;
                    this.out.println(indent + "if (rptr != NULL) {");
                    this.out.println(indent + "    rarg = " + Generator.createString("rptr", adapterInfo != null ? "radapter" : null, Generator.asUtf16(methodInfo.annotations)));
                    this.out.println(indent + "}");
                } else if (methodInfo.returnType.isArray() && methodInfo.returnType.getComponentType().isPrimitive()) {
                    if (adapterInfo == null && !(returnBy instanceof ByVal)) {
                        this.out.println(indent + "jlong rcapacity = rptr != NULL ? 1 : 0;");
                    }
                    String componentName = methodInfo.returnType.getComponentType().getName();
                    String componentNameUpperCase = Character.toUpperCase(componentName.charAt(0)) + componentName.substring(1);
                    this.out.println(indent + "if (rptr != NULL) {");
                    this.out.println(indent + "    rarg = env->New" + componentNameUpperCase + "Array(rcapacity < INT_MAX ? rcapacity : INT_MAX);");
                    this.out.println(indent + "    env->Set" + componentNameUpperCase + "ArrayRegion(rarg, 0, rcapacity < INT_MAX ? rcapacity : INT_MAX, (j" + componentName + "*)rptr);");
                    this.out.println(indent + "}");
                    if (adapterInfo != null) {
                        this.out.println(indent + "if (deallocator != 0 && rptr != NULL) {");
                        this.out.println(indent + "    (*(void(*)(void*))jlong_to_ptr(deallocator))((void*)rptr);");
                        this.out.println(indent + "}");
                    }
                } else if (Buffer.class.isAssignableFrom(methodInfo.returnType)) {
                    if (methodInfo.bufferGetter) {
                        this.out.println(indent + "jlong rposition = position;");
                        this.out.println(indent + "jlong rlimit = limit;");
                        this.out.println(indent + "jlong rcapacity = capacity;");
                    } else if (adapterInfo == null && !(returnBy instanceof ByVal)) {
                        this.out.println(indent + "jlong rcapacity = rptr != NULL ? 1 : 0;");
                    }
                    this.out.println(indent + "if (rptr != NULL) {");
                    this.out.println(indent + "    jlong rcapacityptr = rcapacity * sizeof(rptr[0]);");
                    this.out.println(indent + "    rarg = env->NewDirectByteBuffer((void*)rptr, rcapacityptr < INT_MAX ? rcapacityptr : INT_MAX);");
                    if (methodInfo.bufferGetter) {
                        this.out.println(indent + "    jlong rpositionptr = rposition * sizeof(rptr[0]);");
                        this.out.println(indent + "    jlong rlimitptr = rlimit * sizeof(rptr[0]);");
                        this.out.println(indent + "    env->SetIntField(rarg, JavaCPP_bufferPositionFID, rpositionptr < INT_MAX ? rpositionptr : INT_MAX);");
                        this.out.println(indent + "    env->SetIntField(rarg, JavaCPP_bufferLimitFID, rlimitptr < INT_MAX ? rlimitptr : INT_MAX);");
                    }
                    this.out.println(indent + "}");
                }
            }
        }
    }

    void parametersAfter(MethodInformation methodInfo) {
        int skipParameters;
        if (methodInfo.throwsException != null) {
            this.mayThrowExceptions = true;
            this.out.println("    } catch (...) {");
            this.out.println("        exc = JavaCPP_handleException(env, " + this.jclasses.index(methodInfo.throwsException) + ");");
            this.out.println("    }");
            this.out.println();
        }
        for (int j2 = skipParameters = methodInfo.parameterTypes.length > 0 && methodInfo.parameterTypes[0] == Class.class ? 1 : 0; j2 < methodInfo.parameterTypes.length; ++j2) {
            if (methodInfo.parameterRaw[j2] || Enum.class.isAssignableFrom(methodInfo.parameterTypes[j2])) continue;
            Annotation passBy = this.by(methodInfo, j2);
            String cast = this.cast(methodInfo, j2);
            String[] typeName = this.cppCastTypeName(methodInfo.parameterTypes[j2], methodInfo.parameterAnnotations[j2]);
            AdapterInformation adapterInfo = this.adapterInformation(true, methodInfo, j2);
            if ("void*".equals(typeName[0]) && !methodInfo.parameterTypes[j2].isAnnotationPresent(Opaque.class)) {
                typeName[0] = "char*";
            }
            String releaseArrayFlag = cast.contains(" const *") || cast.startsWith("(const ") ? "JNI_ABORT" : "0";
            if (Pointer.class.isAssignableFrom(methodInfo.parameterTypes[j2])) {
                if (adapterInfo != null) {
                    for (int k2 = 0; k2 < adapterInfo.argc; ++k2) {
                        this.out.println("    " + typeName[0] + " rptr" + (j2 + k2) + typeName[1] + " = " + cast + "adapter" + j2 + ";");
                        this.out.println("    jlong rsize" + (j2 + k2) + " = (jlong)adapter" + j2 + ".size" + (k2 > 0 ? k2 + 1 + ";" : ";"));
                        this.out.println("    void* rowner" + (j2 + k2) + " = adapter" + j2 + ".owner" + (k2 > 0 ? k2 + 1 + ";" : ";"));
                        this.out.println("    if (rptr" + (j2 + k2) + " != " + cast + "ptr" + (j2 + k2) + ") {");
                        this.out.println("        JavaCPP_initPointer(env, arg" + j2 + ", rptr" + (j2 + k2) + ", rsize" + (j2 + k2) + ", rowner" + (j2 + k2) + ", &" + adapterInfo.name + "::deallocate);");
                        this.out.println("    } else {");
                        this.out.println("        env->SetLongField(arg" + j2 + ", JavaCPP_limitFID, rsize" + (j2 + k2) + (!methodInfo.parameterTypes[j2].isAnnotationPresent(Opaque.class) ? " + position" + (j2 + k2) : "") + ");");
                        this.out.println("    }");
                    }
                    continue;
                }
                if (!(passBy instanceof ByPtrPtr) && !(passBy instanceof ByPtrRef) || methodInfo.valueSetter || methodInfo.memberSetter) continue;
                if (!methodInfo.parameterTypes[j2].isAnnotationPresent(Opaque.class)) {
                    this.out.println("    ptr" + j2 + " -= position" + j2 + ";");
                }
                this.out.println("    if (arg" + j2 + " != NULL) env->SetLongField(arg" + j2 + ", JavaCPP_addressFID, ptr_to_jlong(ptr" + j2 + "));");
                continue;
            }
            if (methodInfo.parameterTypes[j2] == String.class) {
                this.out.println("    " + Generator.releaseStringData("arg" + j2, "ptr" + j2, Generator.asUtf16(methodInfo, j2)));
                continue;
            }
            if (methodInfo.parameterTypes[j2].isArray() && methodInfo.parameterTypes[j2].getComponentType().isPrimitive()) {
                for (int k3 = 0; adapterInfo != null && k3 < adapterInfo.argc; ++k3) {
                    this.out.println("    " + typeName[0] + " rptr" + (j2 + k3) + typeName[1] + " = " + cast + "adapter" + j2 + ";");
                    this.out.println("    void* rowner" + (j2 + k3) + " = adapter" + j2 + ".owner" + (k3 > 0 ? k3 + 1 + ";" : ";"));
                    this.out.println("    if (rptr" + (j2 + k3) + " != " + cast + "ptr" + (j2 + k3) + ") {");
                    this.out.println("        " + adapterInfo.name + "::deallocate(rowner" + (j2 + k3) + ");");
                    this.out.println("    }");
                }
                this.out.print("    if (arg" + j2 + " != NULL) ");
                if (methodInfo.criticalRegion || methodInfo.valueGetter || methodInfo.valueSetter || methodInfo.memberGetter || methodInfo.memberSetter) {
                    this.out.println("env->ReleasePrimitiveArrayCritical(arg" + j2 + ", ptr" + j2 + ", " + releaseArrayFlag + ");");
                    continue;
                }
                String componentType = methodInfo.parameterTypes[j2].getComponentType().getName();
                String componentTypeUpperCase = Character.toUpperCase(componentType.charAt(0)) + componentType.substring(1);
                this.out.println("env->Release" + componentTypeUpperCase + "ArrayElements(arg" + j2 + ", (j" + componentType + "*)ptr" + j2 + ", " + releaseArrayFlag + ");");
                continue;
            }
            if (!Buffer.class.isAssignableFrom(methodInfo.parameterTypes[j2]) || methodInfo.parameterTypes[j2] == Buffer.class) continue;
            for (int k4 = 0; adapterInfo != null && k4 < adapterInfo.argc; ++k4) {
                this.out.println("    " + typeName[0] + " rptr" + (j2 + k4) + typeName[1] + " = " + cast + "adapter" + j2 + ";");
                this.out.println("    void* rowner" + (j2 + k4) + " = adapter" + j2 + ".owner" + (k4 > 0 ? k4 + 1 + ";" : ";"));
                this.out.println("    if (rptr" + (j2 + k4) + " != " + cast + "ptr" + (j2 + k4) + ") {");
                this.out.println("        " + adapterInfo.name + "::deallocate(rowner" + (j2 + k4) + ");");
                this.out.println("    }");
            }
            this.out.print("    if (arr" + j2 + " != NULL) ");
            String parameterSimpleName = methodInfo.parameterTypes[j2].getSimpleName();
            parameterSimpleName = parameterSimpleName.substring(0, parameterSimpleName.length() - 6);
            String parameterSimpleNameLowerCase = Character.toLowerCase(parameterSimpleName.charAt(0)) + parameterSimpleName.substring(1);
            if (methodInfo.criticalRegion) {
                this.out.println("env->ReleasePrimitiveArrayCritical(arr" + j2 + ", ptr" + j2 + " - position" + j2 + ", " + releaseArrayFlag + ");");
                continue;
            }
            this.out.println("env->Release" + parameterSimpleName + "ArrayElements(arr" + j2 + ", (j" + parameterSimpleNameLowerCase + "*)(ptr" + j2 + " - position" + j2 + "), " + releaseArrayFlag + ");");
        }
    }

    void callback(Class<?> cls, Method callbackMethod, String callbackName, int allocatorMax, boolean needDefinition, MethodInformation methodInfo) {
        String[] typeName;
        boolean throwsExceptions;
        Class<?> callbackReturnType = callbackMethod.getReturnType();
        Class<?>[] callbackParameterTypes = callbackMethod.getParameterTypes();
        Annotation[] callbackAnnotations = callbackMethod.getAnnotations();
        Annotation[][] callbackParameterAnnotations = callbackMethod.getParameterAnnotations();
        String instanceTypeName = Generator.functionClassName(cls);
        String[] callbackTypeName = this.cppFunctionTypeName(callbackMethod);
        String[] returnConvention = callbackTypeName[0].split("\\(");
        String[] returnType = new String[]{returnConvention[0] + (returnConvention.length > 2 ? "(*" : ""), returnConvention.length > 2 ? ")(" + returnConvention[2] : ""};
        if (returnConvention.length > 2) {
            returnConvention = Arrays.copyOfRange(returnConvention, 2, returnConvention.length);
        }
        returnConvention[1] = Generator.constValueTypeName(returnConvention[1]);
        String parameterDeclaration = callbackTypeName[1].substring(1);
        String fieldName = Generator.mangle(callbackMethod.getName()) + "__" + Generator.mangle(Generator.signature(callbackMethod.getParameterTypes()));
        String firstLine = "";
        if (methodInfo != null) {
            String[] stringArray;
            String nonconstParamDeclaration;
            String string = nonconstParamDeclaration = parameterDeclaration.endsWith(" const") ? parameterDeclaration.substring(0, parameterDeclaration.length() - 6) : parameterDeclaration;
            if (methodInfo.returnRaw) {
                String[] stringArray2 = new String[1];
                stringArray = stringArray2;
                stringArray2[0] = "";
            } else {
                stringArray = this.cppTypeName(methodInfo.cls);
            }
            String[] typeName2 = stringArray;
            String valueTypeName = Generator.valueTypeName(typeName2);
            String subType = "JavaCPP_" + Generator.mangle(valueTypeName);
            Set<String> memberList = this.virtualMembers.get(cls);
            if (memberList == null) {
                memberList = new LinkedHashSet<String>();
                this.virtualMembers.put(cls, memberList);
            }
            String member = "    ";
            if (methodInfo.arrayAllocator) {
                return;
            }
            if (methodInfo.allocator) {
                member = member + subType + nonconstParamDeclaration + " : " + valueTypeName + "(";
                for (int j2 = 0; j2 < callbackParameterTypes.length; ++j2) {
                    member = member + "arg" + j2;
                    if (j2 >= callbackParameterTypes.length - 1) continue;
                    member = member + ", ";
                }
                member = member + "), obj(NULL) { }";
            } else {
                Set<String> functionList = this.virtualFunctions.get(cls);
                if (functionList == null) {
                    functionList = new LinkedHashSet<String>();
                    this.virtualFunctions.put(cls, functionList);
                }
                String usingLine = "using " + valueTypeName + "::" + methodInfo.memberName[0] + ";";
                boolean needUsing = true;
                for (String s2 : memberList) {
                    if (!s2.split("\n", 2)[0].equals(member + usingLine)) continue;
                    needUsing = false;
                    break;
                }
                if (needUsing) {
                    member = member + usingLine + "\n    ";
                }
                member = member + "virtual " + returnType[0] + (returnConvention.length > 1 ? returnConvention[1] : "") + methodInfo.memberName[0] + parameterDeclaration + returnType[1] + " JavaCPP_override;\n    " + returnType[0] + "super_" + methodInfo.name + nonconstParamDeclaration + returnType[1] + " { ";
                if (methodInfo.method.getAnnotation(Virtual.class).value()) {
                    member = member + "throw JavaCPP_exception(\"Cannot call pure virtual function " + valueTypeName + "::" + methodInfo.memberName[0] + "().\"); }";
                } else {
                    member = member + (callbackReturnType != Void.TYPE ? "return " : "") + valueTypeName + "::" + methodInfo.memberName[0] + "(";
                    for (int j3 = 0; j3 < callbackParameterTypes.length; ++j3) {
                        member = member + "arg" + j3;
                        if (j3 >= callbackParameterTypes.length - 1) continue;
                        member = member + ", ";
                    }
                    member = member + "); }";
                }
                firstLine = returnType[0] + (returnConvention.length > 1 ? returnConvention[1] : "") + subType + "::" + methodInfo.memberName[0] + parameterDeclaration + returnType[1] + " {";
                functionList.add(fieldName);
            }
            memberList.add(member);
        } else if (callbackName != null) {
            int i2;
            this.callbacks.put(instanceTypeName, "static " + instanceTypeName + " " + instanceTypeName + "_instances[" + allocatorMax + "];");
            Convention convention = cls.getAnnotation(Convention.class);
            if (convention != null && !convention.extern().equals("C")) {
                this.out.println("extern \"" + convention.extern() + "\" {");
                if (this.out2 != null) {
                    this.out2.println("extern \"" + convention.extern() + "\" {");
                }
            }
            for (i2 = 0; i2 < allocatorMax; ++i2) {
                if (this.out2 != null) {
                    this.out2.println("JNIIMPORT " + returnType[0] + (returnConvention.length > 1 ? returnConvention[1] : "") + callbackName + (i2 > 0 ? Integer.valueOf(i2) : "") + parameterDeclaration + returnType[1] + ";");
                }
                this.out.println("JNIEXPORT " + returnType[0] + (returnConvention.length > 1 ? returnConvention[1] : "") + callbackName + (i2 > 0 ? Integer.valueOf(i2) : "") + parameterDeclaration + returnType[1] + " {");
                this.out.print((callbackReturnType != Void.TYPE ? "    return " : "    ") + instanceTypeName + "_instances[" + i2 + "](");
                for (int j4 = 0; j4 < callbackParameterTypes.length; ++j4) {
                    this.out.print("arg" + j4);
                    if (j4 >= callbackParameterTypes.length - 1) continue;
                    this.out.print(", ");
                }
                this.out.println(");");
                this.out.println("}");
            }
            if (convention != null && !convention.extern().equals("C")) {
                this.out.println("}");
                if (this.out2 != null) {
                    this.out2.println("}");
                }
            }
            this.out.println("static " + returnType[0] + "(" + (returnConvention.length > 1 ? returnConvention[1] : "") + "*" + callbackName + "s[" + allocatorMax + "])" + parameterDeclaration + returnType[1] + " = {");
            for (i2 = 0; i2 < allocatorMax; ++i2) {
                this.out.print("        " + callbackName + (i2 > 0 ? Integer.valueOf(i2) : ""));
                if (i2 + 1 >= allocatorMax) continue;
                this.out.println(",");
            }
            this.out.println(" };");
            firstLine = returnType[0] + instanceTypeName + "::operator()" + parameterDeclaration + returnType[1] + " {";
        }
        if (!needDefinition) {
            return;
        }
        this.out.println(firstLine);
        String returnPrefix = "";
        if (callbackReturnType != Void.TYPE) {
            this.out.println("    " + Generator.jniTypeName(callbackReturnType) + " rarg = 0;");
            returnPrefix = "rarg = ";
            if (callbackReturnType == String.class) {
                returnPrefix = returnPrefix + "(jstring)";
            }
        }
        String callbackReturnCast = this.cast(callbackReturnType, callbackAnnotations);
        Annotation returnBy = this.by(callbackAnnotations);
        String[] returnTypeName = this.cppTypeName(callbackReturnType, callbackAnnotations);
        String returnValueTypeName = Generator.valueTypeName(returnTypeName);
        AdapterInformation returnAdapterInfo = this.adapterInformation(false, returnValueTypeName, callbackAnnotations);
        boolean bl2 = throwsExceptions = !Generator.noException(cls, callbackMethod);
        if (throwsExceptions) {
            this.out.println("    jthrowable exc = NULL;");
        }
        this.out.println("    JNIEnv* env;");
        this.out.println("    bool attached = JavaCPP_getEnv(&env);");
        this.out.println("    if (env == NULL) {");
        this.out.println("        goto end;");
        this.out.println("    }");
        this.out.println("{");
        if (callbackParameterTypes.length > 0) {
            this.out.println("    jvalue args[" + callbackParameterTypes.length + "];");
            for (int j5 = 0; j5 < callbackParameterTypes.length; ++j5) {
                Annotation passBy = this.by(callbackParameterAnnotations[j5]);
                if (callbackParameterTypes[j5].isPrimitive()) {
                    this.out.println("    args[" + j5 + "]." + Generator.signature(callbackParameterTypes[j5]).toLowerCase() + " = (" + Generator.jniTypeName(callbackParameterTypes[j5]) + ")" + (passBy instanceof ByPtr || passBy instanceof ByPtrRef ? "*arg" : "arg") + j5 + ";");
                    continue;
                }
                if (Enum.class.isAssignableFrom(callbackParameterTypes[j5])) {
                    this.accessesEnums = true;
                    String s3 = this.enumValueType(callbackParameterTypes[j5]);
                    if (s3 == null) continue;
                    String S = Character.toUpperCase(s3.charAt(0)) + s3.substring(1);
                    this.out.println("    jobject obj" + j5 + " = JavaCPP_createPointer(env, " + this.jclasses.index(callbackParameterTypes[j5]) + ");");
                    this.out.println("    args[" + j5 + "].l = obj" + j5 + ";");
                    this.out.println("    if (obj" + j5 + " != NULL) {");
                    this.out.println("        env->Set" + S + "Field(obj" + j5 + ", JavaCPP_" + s3 + "ValueFID, (j" + s3 + ")arg" + j5 + ");");
                    this.out.println("    }");
                    continue;
                }
                typeName = this.cppTypeName(callbackParameterTypes[j5], callbackParameterAnnotations[j5]);
                String valueTypeName = Generator.valueTypeName(typeName);
                AdapterInformation adapterInfo = this.adapterInformation(false, valueTypeName, callbackParameterAnnotations[j5]);
                if (adapterInfo != null) {
                    this.usesAdapters = true;
                    this.out.println("    " + adapterInfo.name + " adapter" + j5 + "(arg" + j5 + ");");
                }
                if (Pointer.class.isAssignableFrom(callbackParameterTypes[j5]) || Buffer.class.isAssignableFrom(callbackParameterTypes[j5]) || callbackParameterTypes[j5].isArray() && callbackParameterTypes[j5].getComponentType().isPrimitive()) {
                    String cast = "(" + typeName[0] + typeName[1] + ")";
                    if (FunctionPointer.class.isAssignableFrom(callbackParameterTypes[j5])) {
                        this.functions.index(callbackParameterTypes[j5]);
                        typeName[0] = Generator.functionClassName(callbackParameterTypes[j5]) + "*";
                        typeName[1] = "";
                        valueTypeName = Generator.valueTypeName(typeName);
                    } else if (this.virtualFunctions.containsKey(callbackParameterTypes[j5])) {
                        String subType;
                        valueTypeName = subType = "JavaCPP_" + Generator.mangle(valueTypeName);
                    }
                    this.out.println("    " + Generator.jniTypeName(callbackParameterTypes[j5]) + " obj" + j5 + " = NULL;");
                    this.out.println("    " + typeName[0] + " ptr" + j5 + typeName[1] + " = NULL;");
                    if (FunctionPointer.class.isAssignableFrom(callbackParameterTypes[j5])) {
                        this.out.println("    ptr" + j5 + " = new (std::nothrow) " + valueTypeName + ";");
                        this.out.println("    if (ptr" + j5 + " != NULL) {");
                        this.out.println("        ptr" + j5 + "->ptr = " + cast + "&arg" + j5 + ";");
                        this.out.println("    }");
                    } else if (adapterInfo != null) {
                        this.out.println("    ptr" + j5 + " = adapter" + j5 + ";");
                    } else if (passBy instanceof ByVal && callbackParameterTypes[j5] != Pointer.class) {
                        this.out.println("    ptr" + j5 + (Generator.noException(callbackParameterTypes[j5], callbackMethod) ? " = new (std::nothrow) " : " = new ") + valueTypeName + typeName[1] + "(*" + cast + "&arg" + j5 + ");");
                    } else if (passBy instanceof ByVal || passBy instanceof ByRef) {
                        this.out.println("    ptr" + j5 + " = " + cast + "&arg" + j5 + ";");
                    } else if (passBy instanceof ByPtrPtr) {
                        this.out.println("    if (arg" + j5 + " == NULL) {");
                        this.out.println("        JavaCPP_log(\"Pointer address of argument " + j5 + " is NULL in callback for " + cls.getCanonicalName() + ".\");");
                        this.out.println("    } else {");
                        this.out.println("        ptr" + j5 + " = " + cast + "*arg" + j5 + ";");
                        this.out.println("    }");
                    } else {
                        this.out.println("    ptr" + j5 + " = " + cast + "arg" + j5 + ";");
                    }
                }
                boolean needInit = false;
                if (adapterInfo != null) {
                    if (callbackParameterTypes[j5] != String.class) {
                        this.out.println("    jlong size" + j5 + " = (jlong)adapter" + j5 + ".size;");
                        this.out.println("    void* owner" + j5 + " = adapter" + j5 + ".owner;");
                        this.out.println("    void (*deallocator" + j5 + ")(void*) = &" + adapterInfo.name + "::deallocate;");
                    }
                    needInit = true;
                } else if (passBy instanceof ByVal && callbackParameterTypes[j5] != Pointer.class || FunctionPointer.class.isAssignableFrom(callbackParameterTypes[j5])) {
                    this.out.println("    jlong size" + j5 + " = 1;");
                    this.out.println("    void* owner" + j5 + " = ptr" + j5 + ";");
                    this.out.println("    void (*deallocator" + j5 + ")(void*) = &JavaCPP_" + Generator.mangle(callbackParameterTypes[j5].getName()) + "_deallocate;");
                    this.deallocators.index(callbackParameterTypes[j5]);
                    needInit = true;
                }
                if (Pointer.class.isAssignableFrom(callbackParameterTypes[j5])) {
                    String s4 = "    obj" + j5 + " = JavaCPP_createPointer(env, " + this.jclasses.index(callbackParameterTypes[j5]) + ");";
                    adapterInfo = this.adapterInformation(true, valueTypeName, callbackParameterAnnotations[j5]);
                    if (adapterInfo != null || passBy instanceof ByPtrPtr || passBy instanceof ByPtrRef) {
                        this.out.println(s4);
                    } else {
                        this.out.println("    if (ptr" + j5 + " != NULL) { ");
                        this.out.println("    " + s4);
                        this.out.println("    }");
                    }
                    this.out.println("    if (obj" + j5 + " != NULL) { ");
                    if (needInit) {
                        this.out.println("        JavaCPP_initPointer(env, obj" + j5 + ", ptr" + j5 + ", size" + j5 + ", owner" + j5 + ", deallocator" + j5 + ");");
                    } else {
                        this.out.println("        env->SetLongField(obj" + j5 + ", JavaCPP_addressFID, ptr_to_jlong(ptr" + j5 + "));");
                    }
                    this.out.println("    }");
                    this.out.println("    args[" + j5 + "].l = obj" + j5 + ";");
                    continue;
                }
                if (callbackParameterTypes[j5] == String.class) {
                    this.passesStrings = true;
                    if (passBy instanceof ByPtrPtr) {
                        typeName[0] = valueTypeName;
                    }
                    if (!typeName[0].startsWith("const ")) {
                        typeName[0] = "const " + typeName[0];
                    }
                    if (adapterInfo != null) {
                        String adapter = "adapter" + j5;
                        this.out.println("    jstring obj" + j5 + " = " + Generator.createString("(" + typeName[0] + ") " + adapter, adapter, Generator.asUtf16(callbackParameterAnnotations[j5])));
                    } else {
                        this.out.println("    jstring obj" + j5 + " = " + Generator.createString("(" + typeName[0] + ") arg" + j5, null, Generator.asUtf16(callbackParameterAnnotations[j5])));
                    }
                    this.out.println("    args[" + j5 + "].l = obj" + j5 + ";");
                    continue;
                }
                if (callbackParameterTypes[j5].isArray() && callbackParameterTypes[j5].getComponentType().isPrimitive()) {
                    if (adapterInfo == null) {
                        this.out.println("    jlong size" + j5 + " = ptr" + j5 + " != NULL ? 1 : 0;");
                    }
                    String componentType = callbackParameterTypes[j5].getComponentType().getName();
                    String S = Character.toUpperCase(componentType.charAt(0)) + componentType.substring(1);
                    this.out.println("    if (ptr" + j5 + " != NULL) {");
                    this.out.println("        obj" + j5 + " = env->New" + S + "Array(size" + j5 + " < INT_MAX ? size" + j5 + " : INT_MAX);");
                    this.out.println("        env->Set" + S + "ArrayRegion(obj" + j5 + ", 0, size" + j5 + " < INT_MAX ? size" + j5 + " : INT_MAX, (j" + componentType + "*)ptr" + j5 + ");");
                    this.out.println("    }");
                    if (adapterInfo != null) {
                        this.out.println("    if (deallocator" + j5 + " != 0 && ptr" + j5 + " != NULL) {");
                        this.out.println("        (*(void(*)(void*))jlong_to_ptr(deallocator" + j5 + "))((void*)ptr" + j5 + ");");
                        this.out.println("    }");
                    }
                    this.out.println("    args[" + j5 + "].l = obj" + j5 + ";");
                    continue;
                }
                if (Buffer.class.isAssignableFrom(callbackParameterTypes[j5])) {
                    if (adapterInfo == null) {
                        this.out.println("    jlong size" + j5 + " = ptr" + j5 + " != NULL ? 1 : 0;");
                    }
                    this.out.println("    if (ptr" + j5 + " != NULL) {");
                    this.out.println("        jlong sizeptr = size" + j5 + " * sizeof(ptr" + j5 + "[0]);");
                    this.out.println("        obj" + j5 + " = env->NewDirectByteBuffer((void*)ptr" + j5 + ", sizeptr < INT_MAX ? sizeptr : INT_MAX);");
                    this.out.println("    }");
                    this.out.println("    args[" + j5 + "].l = obj" + j5 + ";");
                    continue;
                }
                this.logger.warn("Callback \"" + callbackMethod + "\" has unsupported parameter type \"" + callbackParameterTypes[j5].getCanonicalName() + "\". Compilation will most likely fail.");
            }
        }
        if (methodInfo != null) {
            this.out.println("    if (" + fieldName + " == NULL) {");
            this.out.println("        " + fieldName + " = JavaCPP_getMethodID(env, " + this.jclasses.index(cls) + ", \"" + methodInfo.method.getName() + "\", \"(" + Generator.signature(methodInfo.method.getParameterTypes()) + ")" + Generator.signature(methodInfo.method.getReturnType()) + "\");");
            this.out.println("    }");
            this.out.println("    jmethodID mid = " + fieldName + ";");
        } else if (callbackName != null) {
            this.out.println("    if (obj == NULL) {");
            this.out.println("        obj = JavaCPP_createPointer(env, " + this.jclasses.index(cls) + ");");
            this.out.println("        obj = obj == NULL ? NULL : env->NewGlobalRef(obj);");
            this.out.println("        if (obj == NULL) {");
            this.out.println("            JavaCPP_log(\"Error creating global reference of " + cls.getCanonicalName() + " instance for callback.\");");
            this.out.println("        } else {");
            this.out.println("            env->SetLongField(obj, JavaCPP_addressFID, ptr_to_jlong(this));");
            this.out.println("        }");
            this.out.println("        for (int i = 0; i < " + allocatorMax + "; i++) {");
            this.out.println("            if (this == &" + instanceTypeName + "_instances[i]) {");
            this.out.println("                ptr = " + callbackName + "s[i];");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
            this.out.println("    }");
            this.out.println("    if (mid == NULL) {");
            this.out.println("        mid = JavaCPP_getMethodID(env, " + this.jclasses.index(cls) + ", \"" + callbackMethod.getName() + "\", \"(" + Generator.signature(callbackMethod.getParameterTypes()) + ")" + Generator.signature(callbackMethod.getReturnType()) + "\");");
            this.out.println("    }");
        }
        this.out.println("    if (obj == NULL) {");
        this.out.println("        JavaCPP_log(\"Function pointer object is NULL in callback for " + cls.getCanonicalName() + ".\");");
        this.out.println("    } else if (mid == NULL) {");
        this.out.println("        JavaCPP_log(\"Error getting method ID of function caller \\\"" + callbackMethod + "\\\" for callback.\");");
        this.out.println("    } else {");
        String s5 = "Object";
        if (callbackReturnType.isPrimitive()) {
            s5 = callbackReturnType.getName();
            s5 = Character.toUpperCase(s5.charAt(0)) + s5.substring(1);
        }
        this.out.println("        " + returnPrefix + "env->Call" + s5 + "MethodA(obj, mid, " + (callbackParameterTypes.length == 0 ? "NULL);" : "args);"));
        if (throwsExceptions) {
            this.out.println("        if ((exc = env->ExceptionOccurred()) != NULL) {");
            this.out.println("            env->ExceptionClear();");
            this.out.println("        }");
        }
        this.out.println("    }");
        for (int j6 = 0; j6 < callbackParameterTypes.length; ++j6) {
            if (Pointer.class.isAssignableFrom(callbackParameterTypes[j6])) {
                typeName = this.cppTypeName(callbackParameterTypes[j6], callbackParameterAnnotations[j6]);
                Annotation passBy = this.by(callbackParameterAnnotations[j6]);
                String cast = this.cast(callbackParameterTypes[j6], callbackParameterAnnotations[j6]);
                String valueTypeName = Generator.valueTypeName(typeName);
                AdapterInformation adapterInfo = this.adapterInformation(true, valueTypeName, callbackParameterAnnotations[j6]);
                if ("void*".equals(typeName[0]) && !callbackParameterTypes[j6].isAnnotationPresent(Opaque.class)) {
                    typeName[0] = "char*";
                }
                if (adapterInfo != null || passBy instanceof ByPtrPtr || passBy instanceof ByPtrRef) {
                    this.out.println("    " + typeName[0] + " rptr" + j6 + typeName[1] + " = (" + typeName[0] + typeName[1] + ")jlong_to_ptr(env->GetLongField(obj" + j6 + ", JavaCPP_addressFID));");
                    if (adapterInfo != null) {
                        this.out.println("    jlong rsize" + j6 + " = env->GetLongField(obj" + j6 + ", JavaCPP_limitFID);");
                        this.out.println("    void* rowner" + j6 + " = JavaCPP_getPointerOwner(env, obj" + j6 + ");");
                    }
                    if (!callbackParameterTypes[j6].isAnnotationPresent(Opaque.class) && !FunctionPointer.class.isAssignableFrom(callbackParameterTypes[j6])) {
                        this.out.println("    jlong rposition" + j6 + " = env->GetLongField(obj" + j6 + ", JavaCPP_positionFID);");
                        this.out.println("    rptr" + j6 + " += rposition" + j6 + ";");
                        if (adapterInfo != null) {
                            this.out.println("    rsize" + j6 + " -= rposition" + j6 + ";");
                        }
                    }
                    if (adapterInfo != null) {
                        this.out.println("    adapter" + j6 + ".assign(rptr" + j6 + ", rsize" + j6 + ", rowner" + j6 + ");");
                    } else if (passBy instanceof ByPtrPtr) {
                        this.out.println("    if (arg" + j6 + " != NULL) {");
                        this.out.println("        *arg" + j6 + " = *" + cast + "&rptr" + j6 + ";");
                        this.out.println("    }");
                    } else if (passBy instanceof ByPtrRef) {
                        this.out.println("    arg" + j6 + " = " + cast + "rptr" + j6 + ";");
                    }
                }
            }
            if (callbackParameterTypes[j6].isPrimitive()) continue;
            this.out.println("    env->DeleteLocalRef(obj" + j6 + ");");
        }
        this.out.println("}");
        this.out.println("end:");
        if (callbackReturnType != Void.TYPE) {
            if ("void*".equals(returnTypeName[0]) && !callbackReturnType.isAnnotationPresent(Opaque.class)) {
                returnTypeName[0] = "char*";
            }
            if (Enum.class.isAssignableFrom(callbackReturnType)) {
                this.accessesEnums = true;
                s5 = this.enumValueType(callbackReturnType);
                if (s5 != null) {
                    String S = Character.toUpperCase(s5.charAt(0)) + s5.substring(1);
                    this.out.println("    if (rarg == NULL) {");
                    this.out.println("        JavaCPP_log(\"Enum for return is NULL in callback for " + cls.getCanonicalName() + ".\");");
                    this.out.println("    }");
                    this.out.println("    " + returnTypeName[0] + " rval" + returnTypeName[1] + " = (" + returnTypeName[0] + returnTypeName[1] + ")(rarg == NULL ? 0 : env->Get" + S + "Field(rarg, JavaCPP_" + s5 + "ValueFID));");
                }
            } else if (Pointer.class.isAssignableFrom(callbackReturnType)) {
                if (FunctionPointer.class.isAssignableFrom(callbackReturnType)) {
                    this.functions.index(callbackReturnType);
                    returnTypeName[0] = Generator.functionClassName(callbackReturnType) + "*";
                    returnTypeName[1] = "";
                }
                this.out.println("    " + returnTypeName[0] + " rptr" + returnTypeName[1] + " = rarg == NULL ? NULL : (" + returnTypeName[0] + returnTypeName[1] + ")jlong_to_ptr(env->GetLongField(rarg, JavaCPP_addressFID));");
                if (returnAdapterInfo != null) {
                    this.out.println("    jlong rsize = rarg == NULL ? 0 : env->GetLongField(rarg, JavaCPP_limitFID);");
                    this.out.println("    void* rowner = JavaCPP_getPointerOwner(env, rarg);");
                }
                if (!callbackReturnType.isAnnotationPresent(Opaque.class)) {
                    this.out.println("    jlong rposition = rarg == NULL ? 0 : env->GetLongField(rarg, JavaCPP_positionFID);");
                    this.out.println("    rptr += rposition;");
                    if (returnAdapterInfo != null) {
                        this.out.println("    rsize -= rposition;");
                    }
                }
            } else if (callbackReturnType == String.class) {
                this.passesStrings = true;
                this.out.println("    " + returnTypeName[0] + " rptr" + returnTypeName[1] + " = " + Generator.getStringData("rarg", Generator.asUtf16(callbackAnnotations)));
                if (returnAdapterInfo != null) {
                    this.out.println("    jlong rsize = 0;");
                    this.out.println("    void* rowner = (void*)rptr;");
                }
            } else if (Buffer.class.isAssignableFrom(callbackReturnType)) {
                this.out.println("    " + returnTypeName[0] + " rptr" + returnTypeName[1] + " = rarg == NULL ? NULL : (" + returnTypeName[0] + returnTypeName[1] + ")env->GetDirectBufferAddress(rarg);");
                if (returnAdapterInfo != null) {
                    this.out.println("    jlong rsize = rarg == NULL ? 0 : env->GetIntField(rarg, JavaCPP_bufferLimitFID);");
                    this.out.println("    void* rowner = (void*)rptr;");
                    this.out.println("    jlong rposition = rarg == NULL ? 0 : env->GetIntField(rarg, JavaCPP_bufferPositionFID);");
                    this.out.println("    rptr += rposition;");
                    this.out.println("    rsize -= rposition;");
                }
            } else if (!callbackReturnType.isPrimitive()) {
                this.logger.warn("Callback \"" + callbackMethod + "\" has unsupported return type \"" + callbackReturnType.getCanonicalName() + "\". Compilation will most likely fail.");
            }
        }
        this.passesStrings = true;
        if (throwsExceptions) {
            this.out.println("    if (exc != NULL) {");
            this.out.println("        jstring str = (jstring)env->CallObjectMethod(exc, JavaCPP_toStringMID);");
            this.out.println("        env->DeleteLocalRef(exc);");
            this.out.println("        const char *msg = JavaCPP_getStringBytes(env, str);");
            this.out.println("        JavaCPP_exception e(msg);");
            this.out.println("        JavaCPP_releaseStringBytes(env, str, msg);");
            this.out.println("        env->DeleteLocalRef(str);");
            this.out.println("        JavaCPP_detach(attached);");
            this.out.println("        throw e;");
            this.out.println("    } else {");
            this.out.println("        JavaCPP_detach(attached);");
            this.out.println("    }");
        } else {
            this.out.println("    JavaCPP_detach(attached);");
        }
        if (callbackReturnType != Void.TYPE) {
            if (callbackReturnType.isPrimitive()) {
                this.out.println("    return " + callbackReturnCast + (returnBy instanceof ByPtr || returnBy instanceof ByPtrRef ? "&rarg;" : "rarg;"));
            } else if (Enum.class.isAssignableFrom(callbackReturnType)) {
                this.out.println("    return " + callbackReturnCast + "rval;");
            } else if (returnAdapterInfo != null) {
                this.usesAdapters = true;
                this.out.println("    return " + returnAdapterInfo.name + "(" + callbackReturnCast + "rptr, rsize, rowner);");
            } else if (FunctionPointer.class.isAssignableFrom(callbackReturnType)) {
                this.functions.index(callbackReturnType);
                this.out.println("    return " + callbackReturnCast + "(rptr == NULL ? NULL : rptr->ptr);");
            } else if (returnBy instanceof ByVal || returnBy instanceof ByRef) {
                this.out.println("    if (rptr == NULL) {");
                this.out.println("        JavaCPP_log(\"Return pointer address is NULL in callback for " + cls.getCanonicalName() + ".\");");
                this.out.println("        static " + Generator.constValueTypeName((returnType[0] + returnType[1]).trim()) + " empty" + returnTypeName[1] + ";");
                this.out.println("        return empty;");
                this.out.println("    } else {");
                this.out.println("        return *" + callbackReturnCast + "rptr;");
                this.out.println("    }");
            } else if (returnBy instanceof ByPtrPtr) {
                this.out.println("    return " + callbackReturnCast + "&rptr;");
            } else {
                this.out.println("    return " + callbackReturnCast + "rptr;");
            }
        }
        this.out.println("}");
    }

    void callbackAllocator(Class<?> cls, String callbackName, int allocatorMax) {
        String[] typeName = this.cppTypeName(cls);
        String instanceTypeName = Generator.functionClassName(cls);
        this.out.println("    obj = env->NewWeakGlobalRef(obj);");
        this.out.println("    if (obj == NULL) {");
        this.out.println("        JavaCPP_log(\"Error creating global reference of " + cls.getCanonicalName() + " instance for callback.\");");
        this.out.println("        return;");
        this.out.println("    }");
        this.out.println("    " + instanceTypeName + "* rptr = new (std::nothrow) " + instanceTypeName + ";");
        this.out.println("    if (rptr != NULL) {");
        this.out.println("        rptr->obj = obj;");
        this.out.println("        JavaCPP_initPointer(env, obj, rptr, 1, rptr, &JavaCPP_" + Generator.mangle(cls.getName()) + "_deallocate);");
        this.deallocators.index(cls);
        if (callbackName != null) {
            this.out.println("        for (int i = 0; i < " + allocatorMax + "; i++) {");
            this.out.println("            if (" + instanceTypeName + "_instances[i].obj == NULL) {");
            this.out.println("                rptr->ptr = " + callbackName + "s[i];");
            this.out.println("                " + instanceTypeName + "_instances[i] = *rptr;");
            this.out.println("                break;");
            this.out.println("            }");
            this.out.println("        }");
        } else {
            this.out.println("        rptr->ptr = (" + typeName[0] + typeName[1] + ")jlong_to_ptr(arg0);");
        }
        this.out.println("    }");
        this.out.println("}");
    }

    static String functionClassName(Class<?> cls) {
        Name name = cls.getAnnotation(Name.class);
        return name != null ? name.value()[0] : "JavaCPP_" + Generator.mangle(cls.getName());
    }

    static Method[] functionMethods(Class<?> cls, boolean[] callbackAllocators) {
        if (!FunctionPointer.class.isAssignableFrom(cls)) {
            return null;
        }
        Method[] methods = cls.getDeclaredMethods();
        Method[] functionMethods = new Method[3];
        for (int i2 = 0; i2 < methods.length; ++i2) {
            String methodName = methods[i2].getName();
            int modifiers = methods[i2].getModifiers();
            Class<?>[] parameterTypes = methods[i2].getParameterTypes();
            Class<?> returnType = methods[i2].getReturnType();
            if (Modifier.isStatic(modifiers)) continue;
            if (callbackAllocators != null && methodName.startsWith("allocate") && Modifier.isNative(modifiers) && returnType == Void.TYPE && (parameterTypes.length == 0 || parameterTypes.length == 1 && (parameterTypes[0] == Integer.TYPE || parameterTypes[0] == Long.TYPE))) {
                callbackAllocators[i2] = true;
                continue;
            }
            if (methodName.startsWith("call") || methodName.startsWith("apply")) {
                functionMethods[0] = methods[i2];
                continue;
            }
            if (methodName.startsWith("get") && Modifier.isNative(modifiers) && cls.isAnnotationPresent(Namespace.class)) {
                functionMethods[1] = methods[i2];
                continue;
            }
            if (!methodName.startsWith("put") || !Modifier.isNative(modifiers) || !cls.isAnnotationPresent(Namespace.class)) continue;
            functionMethods[2] = methods[i2];
        }
        return functionMethods[0] != null || functionMethods[1] != null || functionMethods[2] != null ? functionMethods : null;
    }

    MethodInformation methodInformation(Method method) {
        String[] stringArray;
        MethodInformation info = new MethodInformation();
        info.cls = method.getDeclaringClass();
        info.method = method;
        info.annotations = method.getAnnotations();
        info.modifiers = method.getModifiers();
        info.returnType = method.getReturnType();
        info.name = method.getName();
        Name name = method.getAnnotation(Name.class);
        if (name != null) {
            stringArray = name.value();
        } else {
            String[] stringArray2 = new String[1];
            stringArray = stringArray2;
            stringArray2[0] = info.name;
        }
        info.memberName = stringArray;
        Index index = method.getAnnotation(Index.class);
        info.allocatorMax = Generator.allocatorMax(info.cls, info.method);
        info.dim = index != null ? index.value() : 0;
        info.parameterTypes = method.getParameterTypes();
        info.parameterAnnotations = method.getParameterAnnotations();
        info.criticalRegion = Generator.criticalRegion(info.cls, info.method);
        info.returnRaw = method.isAnnotationPresent(Raw.class);
        info.withEnv = info.returnRaw ? method.getAnnotation(Raw.class).withEnv() : false;
        info.parameterRaw = new boolean[info.parameterAnnotations.length];
        for (int i2 = 0; i2 < info.parameterAnnotations.length; ++i2) {
            for (int j2 = 0; j2 < info.parameterAnnotations[i2].length; ++j2) {
                if (!(info.parameterAnnotations[i2][j2] instanceof Raw)) continue;
                info.parameterRaw[i2] = true;
                info.withEnv |= ((Raw)info.parameterAnnotations[i2][j2]).withEnv();
            }
        }
        boolean canBeGetter = info.returnType != Void.TYPE || info.parameterTypes.length > 0 && info.parameterTypes[0].isArray() && info.parameterTypes[0].getComponentType().isPrimitive();
        boolean canBeSetter = (info.returnType == Void.TYPE || info.returnType == info.cls) && info.parameterTypes.length > 0;
        boolean canBeAllocator = !Modifier.isStatic(info.modifiers) && info.returnType == Void.TYPE;
        boolean canBeArrayAllocator = canBeAllocator && info.parameterTypes.length == 1 && (info.parameterTypes[0] == Integer.TYPE || info.parameterTypes[0] == Long.TYPE);
        boolean valueGetter = false;
        boolean valueSetter = false;
        boolean memberGetter = false;
        boolean memberSetter = false;
        boolean noReturnGetter = false;
        Method pairedMethod = null;
        for (Method method2 : info.cls.getDeclaredMethods()) {
            boolean parameterAsReturn2;
            int skipParameters2;
            MethodInformation info2 = this.annotationCache.get(method2);
            if (info2 == null) {
                info2 = new MethodInformation();
                this.annotationCache.put(method2, info2);
                info2.modifiers = method2.getModifiers();
                info2.returnType = method2.getReturnType();
                info2.name = method2.getName();
                info2.parameterTypes = method2.getParameterTypes();
                info2.annotations = method2.getAnnotations();
                info2.parameterAnnotations = method2.getParameterAnnotations();
            }
            int skipParameters = info.parameterTypes.length > 0 && info.parameterTypes[0] == Class.class ? 1 : 0;
            int n2 = skipParameters2 = info2.parameterTypes.length > 0 && info2.parameterTypes[0] == Class.class ? 1 : 0;
            if (method.equals(method2) || !Modifier.isNative(info2.modifiers)) continue;
            boolean canBeValueGetter = false;
            boolean canBeValueSetter = false;
            boolean canBeMemberGetter = false;
            boolean canBeMemberSetter = false;
            if (canBeGetter && "get".equals(info.name) && "put".equals(info2.name) && name == null) {
                canBeValueGetter = true;
            } else if (canBeSetter && "put".equals(info.name) && "get".equals(info2.name) && name == null) {
                canBeValueSetter = true;
            } else {
                if (!info2.name.equals(info.name)) continue;
                info.overloaded = true;
                canBeMemberGetter = canBeGetter;
                canBeMemberSetter = canBeSetter;
            }
            boolean sameIndexParameters = true;
            for (int j3 = 0; j3 < info.parameterTypes.length - skipParameters && j3 < info2.parameterTypes.length - skipParameters2; ++j3) {
                if (info.parameterTypes[j3 + skipParameters] == info2.parameterTypes[j3 + skipParameters2]) continue;
                sameIndexParameters = false;
            }
            if (!sameIndexParameters) continue;
            boolean parameterAsReturn = canBeValueGetter && info.parameterTypes.length > 0 && info.parameterTypes[0].isArray() && info.parameterTypes[0].getComponentType().isPrimitive();
            boolean bl2 = parameterAsReturn2 = canBeValueSetter && info2.parameterTypes.length > 0 && info2.parameterTypes[0].isArray() && info2.parameterTypes[0].getComponentType().isPrimitive();
            if (canBeGetter && info2.parameterTypes.length - (parameterAsReturn ? 0 : 1) == info.parameterTypes.length - skipParameters && (parameterAsReturn ? info.parameterTypes[info.parameterTypes.length - 1] : info.returnType) == info2.parameterTypes[info2.parameterTypes.length - 1] && (info2.returnType == Void.TYPE || info2.returnType == info.cls) && (info2.parameterAnnotations[info2.parameterAnnotations.length - 1].length == 0 || Arrays.equals(info2.parameterAnnotations[info2.parameterAnnotations.length - 1], info.annotations))) {
                pairedMethod = method2;
                valueGetter = canBeValueGetter;
                memberGetter = canBeMemberGetter;
                noReturnGetter = parameterAsReturn;
            } else if (canBeSetter && info.parameterTypes.length - (parameterAsReturn2 ? 0 : 1) == info2.parameterTypes.length - skipParameters2 && (parameterAsReturn2 ? info2.parameterTypes[info2.parameterTypes.length - 1] : info2.returnType) == info.parameterTypes[info.parameterTypes.length - 1] && (info.returnType == Void.TYPE || info.returnType == info.cls) && (info.parameterAnnotations[info.parameterAnnotations.length - 1].length == 0 || Arrays.equals(info.parameterAnnotations[info.parameterAnnotations.length - 1], info2.annotations))) {
                pairedMethod = method2;
                valueSetter = canBeValueSetter;
                memberSetter = canBeMemberSetter;
            }
            if (!memberGetter && !memberSetter) continue;
            for (int j4 = skipParameters; j4 < info.parameterTypes.length; ++j4) {
                if (method.isAnnotationPresent(Index.class) || pairedMethod != null && pairedMethod.isAnnotationPresent(Index.class) || info.parameterTypes[j4] == Integer.TYPE || info.parameterTypes[j4] == Long.TYPE) continue;
                memberGetter = false;
                if (j4 >= info.parameterTypes.length - 1) continue;
                memberSetter = false;
            }
        }
        Annotation behavior = this.behavior(info.annotations);
        if (canBeGetter && behavior instanceof ValueGetter) {
            info.valueGetter = true;
            info.noReturnGetter = noReturnGetter;
        } else if (canBeSetter && behavior instanceof ValueSetter) {
            info.valueSetter = true;
        } else if (canBeGetter && behavior instanceof MemberGetter) {
            info.memberGetter = true;
            info.noReturnGetter = noReturnGetter;
        } else if (canBeSetter && behavior instanceof MemberSetter) {
            info.memberSetter = true;
        } else if (canBeAllocator && behavior instanceof Allocator) {
            info.allocator = true;
        } else if (canBeArrayAllocator && behavior instanceof ArrayAllocator) {
            info.arrayAllocator = true;
            info.allocator = true;
        } else if (behavior == null) {
            if (info.returnType == Void.TYPE && "deallocate".equals(info.name) && !Modifier.isStatic(info.modifiers) && info.parameterTypes.length == 2 && info.parameterTypes[0] == Long.TYPE && info.parameterTypes[1] == Long.TYPE) {
                info.deallocator = true;
            } else if (canBeAllocator && "allocate".equals(info.name)) {
                info.allocator = true;
            } else if (canBeArrayAllocator && "allocateArray".equals(info.name)) {
                info.arrayAllocator = true;
                info.allocator = true;
            } else if (info.returnType.isAssignableFrom(ByteBuffer.class) && "asDirectBuffer".equals(info.name) && !Modifier.isStatic(info.modifiers) && info.parameterTypes.length == 0) {
                info.bufferGetter = true;
            } else if (valueGetter || !memberGetter && canBeGetter && "get".equals(info.name) && index != null) {
                info.valueGetter = true;
                info.noReturnGetter = noReturnGetter;
                info.pairedMethod = pairedMethod;
            } else if (valueSetter) {
                info.valueSetter = true;
                info.pairedMethod = pairedMethod;
            } else if (memberGetter) {
                info.memberGetter = true;
                info.noReturnGetter = noReturnGetter;
                info.pairedMethod = pairedMethod;
            } else if (memberSetter) {
                info.memberSetter = true;
                info.pairedMethod = pairedMethod;
            }
        } else if (!(behavior instanceof Function)) {
            this.logger.warn("Method \"" + method + "\" cannot behave like a \"" + behavior.annotationType().getSimpleName() + "\". No code will be generated.");
            return null;
        }
        if (name == null && info.pairedMethod != null && (name = info.pairedMethod.getAnnotation(Name.class)) != null) {
            info.memberName = name.value();
        }
        boolean bl3 = info.noOffset = info.cls.isAnnotationPresent(NoOffset.class) || method.isAnnotationPresent(NoOffset.class) || method.isAnnotationPresent(Index.class);
        if (!info.noOffset && info.pairedMethod != null) {
            boolean bl4 = info.noOffset = info.pairedMethod.isAnnotationPresent(NoOffset.class) || info.pairedMethod.isAnnotationPresent(Index.class);
        }
        if (info.parameterTypes.length == 0 || !info.parameterTypes[0].isArray()) {
            if (info.valueGetter || info.memberGetter) {
                info.dim = info.parameterTypes.length;
            } else if (info.memberSetter || info.valueSetter) {
                info.dim = info.parameterTypes.length - 1;
            }
            if ((info.valueGetter || info.valueSetter) && FunctionPointer.class.isAssignableFrom(info.cls) && info.cls.isAnnotationPresent(Namespace.class)) {
                --info.dim;
            }
        }
        Index index2 = pairedMethod != null ? pairedMethod.getAnnotation(Index.class) : null;
        info.throwsException = null;
        if (!(Generator.noException(info.cls, method) || !(this.by(info.annotations) instanceof ByVal && !Generator.noException(info.returnType, method) || index != null && index.function().length() > 0 || index2 != null && index2.function().length() > 0) && (info.deallocator || info.valueGetter || info.valueSetter || info.memberGetter || info.memberSetter || info.bufferGetter))) {
            Class<?>[] exceptions = method.getExceptionTypes();
            info.throwsException = exceptions.length > 0 ? exceptions[0] : RuntimeException.class;
        }
        return info;
    }

    static int allocatorMax(Class<?> cls, Method method) {
        try {
            Allocator a2 = Generator.allocator(cls, method);
            return a2 != null ? a2.max() : ((Integer)Allocator.class.getDeclaredMethod("max", new Class[0]).getDefaultValue()).intValue();
        }
        catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    static Allocator allocator(Class<?> cls, Method method) {
        Allocator a2 = method.getAnnotation(Allocator.class);
        while (a2 == null && cls != null && (a2 = cls.getAnnotation(Allocator.class)) == null) {
            Properties classProperties = cls.getAnnotation(Properties.class);
            if (Pointer.class.isAssignableFrom(cls) && classProperties != null) {
                Class c2;
                Class[] classArray = classProperties.inherit();
                int n2 = classArray.length;
                for (int i2 = 0; i2 < n2 && (a2 = Generator.allocator(c2 = classArray[i2], method)) == null; ++i2) {
                }
            }
            if (cls.getEnclosingClass() != null) {
                cls = cls.getEnclosingClass();
                continue;
            }
            cls = cls.getSuperclass();
        }
        return a2;
    }

    static boolean criticalRegion(Class<?> cls, Method method) {
        boolean criticalRegion;
        boolean bl2 = criticalRegion = baseClasses.contains(cls) || method.isAnnotationPresent(CriticalRegion.class);
        while (!criticalRegion && cls != null && !(criticalRegion = cls.isAnnotationPresent(CriticalRegion.class))) {
            Properties classProperties = cls.getAnnotation(Properties.class);
            if (Pointer.class.isAssignableFrom(cls) && classProperties != null) {
                Class c2;
                Class[] classArray = classProperties.inherit();
                int n2 = classArray.length;
                for (int i2 = 0; i2 < n2 && !(criticalRegion = Generator.criticalRegion(c2 = classArray[i2], method)); ++i2) {
                }
            }
            if (cls.getEnclosingClass() != null) {
                cls = cls.getEnclosingClass();
                continue;
            }
            cls = cls.getSuperclass();
        }
        return criticalRegion;
    }

    static boolean noException(Class<?> cls, Method method) {
        boolean noException;
        boolean bl2 = noException = baseClasses.contains(cls) || method.isAnnotationPresent(NoException.class);
        while (!noException && cls != null && !(noException = cls.isAnnotationPresent(NoException.class))) {
            Properties classProperties = cls.getAnnotation(Properties.class);
            if (Pointer.class.isAssignableFrom(cls) && classProperties != null) {
                Class c2;
                Class[] classArray = classProperties.inherit();
                int n2 = classArray.length;
                for (int i2 = 0; i2 < n2 && !(noException = Generator.noException(c2 = classArray[i2], method)); ++i2) {
                }
            }
            if (cls.getEnclosingClass() != null) {
                cls = cls.getEnclosingClass();
                continue;
            }
            cls = cls.getSuperclass();
        }
        return noException;
    }

    AdapterInformation adapterInformation(boolean out, MethodInformation methodInfo, int j2) {
        String valueTypeName;
        AdapterInformation adapter;
        if (out && (methodInfo.parameterTypes[j2] == String.class || methodInfo.valueSetter || methodInfo.memberSetter)) {
            return null;
        }
        String typeName = this.cast(methodInfo, j2);
        if (typeName != null && typeName.startsWith("(") && typeName.endsWith(")")) {
            typeName = typeName.substring(1, typeName.length() - 1);
        }
        if (typeName == null || typeName.length() == 0) {
            typeName = this.cppCastTypeName(methodInfo.parameterTypes[j2], methodInfo.parameterAnnotations[j2])[0];
        }
        if ((adapter = this.adapterInformation(out, valueTypeName = Generator.valueTypeName(typeName), methodInfo.parameterAnnotations[j2])) == null && methodInfo.pairedMethod != null && j2 == methodInfo.parameterTypes.length - 1 && (methodInfo.valueSetter || methodInfo.memberSetter)) {
            adapter = this.adapterInformation(out, valueTypeName, methodInfo.pairedMethod.getAnnotations());
        }
        return adapter;
    }

    AdapterInformation adapterInformation(boolean out, String valueTypeName, Annotation ... annotations) {
        AdapterInformation adapterInfo = null;
        boolean constant = false;
        String cast = "";
        String cast2 = "";
        for (Annotation a2 : annotations) {
            Cast c2;
            if (!(a2 instanceof Cast) || (c2 = (Cast)a2).value().length <= 0 || c2.value()[0].length() <= 0) continue;
            valueTypeName = Generator.constValueTypeName(c2.value()[0]);
        }
        for (Annotation a2 : annotations) {
            Adapter adapter;
            Adapter adapter2 = adapter = a2 instanceof Adapter ? (Adapter)a2 : a2.annotationType().getAnnotation(Adapter.class);
            if (adapter != null) {
                adapterInfo = new AdapterInformation();
                adapterInfo.name = adapter.value();
                adapterInfo.argc = adapter.argc();
                if (a2 == adapter) continue;
                try {
                    Class<? extends Annotation> cls = a2.annotationType();
                    if (cls.isAnnotationPresent(Const.class)) {
                        constant = true;
                    }
                    try {
                        String value = cls.getDeclaredMethod("value", new Class[0]).invoke((Object)a2, new Object[0]).toString();
                        if (value != null && value.length() > 0) {
                            valueTypeName = value;
                        }
                    }
                    catch (NoSuchMethodException e2) {
                        valueTypeName = null;
                    }
                    Cast c3 = cls.getAnnotation(Cast.class);
                    if (c3 != null && cast.length() == 0) {
                        cast = c3.value()[0];
                        if (valueTypeName != null) {
                            cast = cast + "< " + valueTypeName + " >";
                        }
                        if (c3.value().length > 1) {
                            cast = cast + c3.value()[1];
                        }
                        if (c3.value().length > 2) {
                            cast2 = c3.value()[2];
                        }
                    }
                }
                catch (Exception ex2) {
                    this.logger.warn("Could not invoke the value() method on annotation \"" + a2 + "\": " + ex2);
                }
                if (valueTypeName == null || valueTypeName.length() <= 0) continue;
                adapterInfo.name = adapterInfo.name + "< " + valueTypeName + " >";
                continue;
            }
            if (a2 instanceof Const) {
                constant = true;
                continue;
            }
            if (!(a2 instanceof Cast)) continue;
            Cast c4 = (Cast)a2;
            if (c4.value().length > 1) {
                cast = c4.value()[1];
            }
            if (c4.value().length <= 2) continue;
            cast2 = c4.value()[2];
        }
        if (adapterInfo != null) {
            adapterInfo.cast = cast;
            adapterInfo.cast2 = cast2;
            adapterInfo.constant = constant;
        }
        return out && constant ? null : adapterInfo;
    }

    String cast(MethodInformation methodInfo, int j2) {
        String cast = this.cast(methodInfo.parameterTypes[j2], methodInfo.parameterAnnotations[j2]);
        if ((cast == null || cast.length() == 0) && j2 == methodInfo.parameterTypes.length - 1 && (methodInfo.valueSetter || methodInfo.memberSetter) && methodInfo.pairedMethod != null) {
            cast = this.cast(methodInfo.pairedMethod.getReturnType(), methodInfo.pairedMethod.getAnnotations());
        }
        return cast;
    }

    String cast(Class<?> type, Annotation ... annotations) {
        String[] typeName = null;
        for (Annotation a2 : annotations) {
            if ((!(a2 instanceof Cast) || ((Cast)a2).value()[0].length() <= 0) && !(a2 instanceof Const)) continue;
            typeName = this.cppCastTypeName(type, annotations);
            break;
        }
        return typeName != null && typeName.length > 0 ? "(" + (String)typeName[0] + typeName[1] + ")" : "";
    }

    Annotation by(MethodInformation methodInfo, int j2) {
        Annotation passBy = this.by(methodInfo.parameterAnnotations[j2]);
        if (passBy == null && methodInfo.pairedMethod != null && (methodInfo.valueSetter || methodInfo.memberSetter) && j2 == methodInfo.parameterAnnotations.length - 1) {
            passBy = this.by(methodInfo.pairedMethod.getAnnotations());
        }
        return passBy;
    }

    Annotation by(Annotation ... annotations) {
        Annotation byAnnotation = null;
        for (Annotation a2 : annotations) {
            if (!(a2 instanceof ByPtr) && !(a2 instanceof ByPtrPtr) && !(a2 instanceof ByPtrRef) && !(a2 instanceof ByRef) && !(a2 instanceof ByVal)) continue;
            if (byAnnotation != null) {
                this.logger.warn("\"By\" annotation \"" + byAnnotation + "\" already found. Ignoring superfluous annotation \"" + a2 + "\".");
                continue;
            }
            byAnnotation = a2;
        }
        return byAnnotation;
    }

    Annotation behavior(Annotation ... annotations) {
        Annotation behaviorAnnotation = null;
        for (Annotation a2 : annotations) {
            if (!(a2 instanceof Function) && !(a2 instanceof Allocator) && !(a2 instanceof ArrayAllocator) && !(a2 instanceof ValueSetter) && !(a2 instanceof ValueGetter) && !(a2 instanceof MemberGetter) && !(a2 instanceof MemberSetter)) continue;
            if (behaviorAnnotation != null) {
                this.logger.warn("Behavior annotation \"" + behaviorAnnotation + "\" already found. Ignoring superfluous annotation \"" + a2 + "\".");
                continue;
            }
            behaviorAnnotation = a2;
        }
        return behaviorAnnotation;
    }

    String enumValueType(Class<?> type) {
        try {
            Field f2 = type.getField("value");
            if (!f2.getType().isPrimitive()) {
                this.logger.warn("Field \"value\" of enum type \"" + type.getCanonicalName() + "\" is not of a primitive type. Compilation will most likely fail.");
            }
            return f2.getType().getName();
        }
        catch (NoSuchFieldException ex2) {
            this.logger.warn("Field \"value\" missing from enum type \"" + type.getCanonicalName() + ". Compilation will most likely fail.");
            return null;
        }
    }

    static boolean asUtf16(MethodInformation methodInfo, int j2) {
        if (methodInfo.parameterAnnotations[j2].length == 0 && methodInfo.pairedMethod != null && j2 == methodInfo.parameterTypes.length - 1 && (methodInfo.valueSetter || methodInfo.memberSetter)) {
            return Generator.asUtf16(methodInfo.pairedMethod.getAnnotations());
        }
        return Generator.asUtf16(methodInfo.parameterAnnotations[j2]);
    }

    static boolean asUtf16(Annotation[] annotations) {
        if (annotations == null) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof AsUtf16)) continue;
            return true;
        }
        return false;
    }

    static String createString(String ptr, String adapter, boolean asUtf16) {
        return (asUtf16 ? "JavaCPP_createStringFromUTF16(env, " : "JavaCPP_createStringFromBytes(env, ") + ptr + (adapter != null ? ", " + adapter + ".size);" : ");");
    }

    static String getStringData(String str, boolean asUtf16) {
        return (asUtf16 ? "JavaCPP_getStringUTF16(env, " : "JavaCPP_getStringBytes(env, ") + str + ");";
    }

    static String releaseStringData(String str, String ptr, boolean asUtf16) {
        return (asUtf16 ? "JavaCPP_releaseStringUTF16(env, " : "JavaCPP_releaseStringBytes(env, " + str + ", ") + ptr + ");";
    }

    static String constValueTypeName(String ... typeName) {
        String type = typeName[0];
        if (type.endsWith("*") || type.endsWith("&")) {
            type = type.substring(0, type.length() - 1);
        }
        return type;
    }

    static String valueTypeName(String ... typeName) {
        String type = typeName[0];
        if (type.startsWith("const ")) {
            type = type.substring(6);
        }
        if (type.endsWith(" const")) {
            type = type.substring(0, type.length() - 6);
        }
        if (type.endsWith("*") || type.endsWith("&")) {
            type = type.substring(0, type.length() - 1);
        }
        if (type.endsWith(" const")) {
            type = type.substring(0, type.length() - 6);
        }
        return type;
    }

    static boolean constFunction(Class<?> classType, Method functionMethod) {
        if (classType.isAnnotationPresent(Const.class)) {
            return true;
        }
        if (!functionMethod.isAnnotationPresent(Const.class)) {
            return false;
        }
        for (Annotation a2 : functionMethod.getDeclaredAnnotations()) {
            if (!(a2 instanceof Const)) continue;
            boolean[] b2 = ((Const)a2).value();
            return b2.length > 2 && b2[2];
        }
        return false;
    }

    static boolean noexceptFunction(Class<?> classType, Method functionMethod) {
        if (classType.isAnnotationPresent(NoException.class)) {
            return true;
        }
        if (!functionMethod.isAnnotationPresent(NoException.class)) {
            return false;
        }
        for (Annotation a2 : functionMethod.getDeclaredAnnotations()) {
            if (!(a2 instanceof NoException)) continue;
            return ((NoException)a2).value();
        }
        return false;
    }

    String[] cppAnnotationTypeName(Class<?> type, Annotation ... annotations) {
        Annotation by2;
        String[] typeName = this.cppCastTypeName(type, annotations);
        String prefix = typeName[0];
        String suffix = typeName[1];
        boolean casted = false;
        for (Annotation a2 : annotations) {
            if ((!(a2 instanceof Cast) || ((Cast)a2).value()[0].length() <= 0) && !(a2 instanceof Const)) continue;
            casted = true;
            break;
        }
        if ((by2 = this.by(annotations)) instanceof ByVal) {
            prefix = Generator.constValueTypeName(typeName);
        } else if (by2 instanceof ByRef) {
            prefix = Generator.constValueTypeName(typeName) + "&";
        } else if (by2 instanceof ByPtrPtr && !casted) {
            prefix = prefix + "*";
        } else if (by2 instanceof ByPtrRef) {
            prefix = prefix + "&";
        } else if (by2 instanceof ByPtr && type.isPrimitive()) {
            prefix = prefix + "*";
        }
        typeName[0] = prefix;
        typeName[1] = suffix;
        return typeName;
    }

    String[] cppCastTypeName(Class<?> type, Annotation ... annotations) {
        String[] typeName = null;
        boolean warning = false;
        boolean adapter = false;
        for (Annotation a2 : annotations) {
            if (a2 instanceof Cast) {
                String[] stringArray;
                warning = typeName != null;
                String prefix = ((Cast)a2).value()[0];
                String suffix = "";
                int templateCount = 0;
                for (int i2 = 0; i2 < prefix.length(); ++i2) {
                    char c2 = prefix.charAt(i2);
                    if (c2 == '<') {
                        ++templateCount;
                        continue;
                    }
                    if (c2 == '>') {
                        --templateCount;
                        continue;
                    }
                    if (prefix.contains("decltype(") || templateCount != 0 || c2 != ')') continue;
                    suffix = prefix.substring(i2).trim();
                    prefix = prefix.substring(0, i2).trim();
                    break;
                }
                if (prefix.length() > 0) {
                    String[] stringArray2 = new String[2];
                    stringArray2[0] = prefix;
                    stringArray = stringArray2;
                    stringArray2[1] = suffix;
                } else {
                    stringArray = null;
                }
                typeName = stringArray;
                continue;
            }
            if (a2 instanceof Const) {
                Annotation by2;
                boolean[] b2 = ((Const)a2).value();
                if (b2.length == 1 && !b2[0] || b2.length > 1 && !b2[0] && !b2[1] || (warning = typeName != null)) continue;
                typeName = this.cppTypeName(type, annotations);
                if (typeName[0].contains("(*")) {
                    if (b2.length > 0 && b2[0] && !typeName[0].endsWith(" const")) {
                        typeName[0] = typeName[0] + " const";
                    }
                } else {
                    if (b2.length > 1 && b2[1] && !typeName[0].endsWith(" const")) {
                        typeName[0] = typeName[0] + " const";
                    }
                    if (b2.length > 0 && b2[0] && !typeName[0].startsWith("const ")) {
                        typeName[0] = "const " + typeName[0];
                    }
                }
                if ((by2 = this.by(annotations)) instanceof ByPtrPtr) {
                    typeName[0] = typeName[0] + "*";
                    continue;
                }
                if (!(by2 instanceof ByPtrRef)) continue;
                typeName[0] = typeName[0] + "&";
                continue;
            }
            if (!(a2 instanceof Adapter) && !a2.annotationType().isAnnotationPresent(Adapter.class)) continue;
            adapter = true;
        }
        if (warning && !adapter) {
            this.logger.warn("Without \"Adapter\", \"Cast\" and \"Const\" annotations are mutually exclusive.");
        }
        if (typeName == null) {
            typeName = this.cppTypeName(type, annotations);
        }
        return typeName;
    }

    String[] cppTypeName(MethodInformation methodInfo, int j2) {
        if (methodInfo.parameterAnnotations[j2].length == 0 && methodInfo.pairedMethod != null && j2 == methodInfo.parameterTypes.length - 1 && (methodInfo.valueSetter || methodInfo.memberSetter)) {
            return this.cppTypeName(methodInfo.pairedMethod.getReturnType(), methodInfo.pairedMethod.getAnnotations());
        }
        return this.cppTypeName(methodInfo.parameterTypes[j2], methodInfo.parameterAnnotations[j2]);
    }

    String[] cppTypeName(Class<?> type) {
        return this.cppTypeName(type, null);
    }

    String[] cppTypeName(Class<?> type, Annotation[] annotations) {
        String prefix = "";
        String suffix = "";
        if (type == Buffer.class || type == Pointer.class) {
            prefix = "void*";
        } else if (type == byte[].class || type == ByteBuffer.class || type == BytePointer.class) {
            prefix = "signed char*";
        } else if (type == short[].class || type == ShortBuffer.class || type == ShortPointer.class) {
            prefix = "short*";
        } else if (type == int[].class || type == IntBuffer.class || type == IntPointer.class) {
            prefix = "int*";
        } else if (type == long[].class || type == LongBuffer.class || type == LongPointer.class) {
            prefix = "jlong*";
        } else if (type == float[].class || type == FloatBuffer.class || type == FloatPointer.class) {
            prefix = "float*";
        } else if (type == double[].class || type == DoubleBuffer.class || type == DoublePointer.class) {
            prefix = "double*";
        } else if (type == char[].class || type == CharBuffer.class || type == CharPointer.class) {
            prefix = "unsigned short*";
        } else if (type == boolean[].class || type == BooleanPointer.class) {
            prefix = "unsigned char*";
        } else if (type == PointerPointer.class) {
            prefix = "void**";
        } else if (type == String.class) {
            prefix = Generator.asUtf16(annotations) ? "const unsigned short*" : "const char*";
        } else if (type == Byte.TYPE) {
            prefix = "signed char";
        } else if (type == Long.TYPE) {
            prefix = "jlong";
        } else if (type == Character.TYPE) {
            prefix = "unsigned short";
        } else if (type == Boolean.TYPE) {
            prefix = "unsigned char";
        } else if (type.isPrimitive()) {
            prefix = type.getName();
        } else if (FunctionPointer.class.isAssignableFrom(type)) {
            Method[] functionMethods = Generator.functionMethods(type, null);
            String[] prefixSuffix = this.cppFunctionTypeName(functionMethods);
            if (prefixSuffix != null) {
                return prefixSuffix;
            }
        } else {
            String scopedType = Generator.cppScopeName(type);
            if (scopedType.length() > 0) {
                prefix = scopedType + (Enum.class.isAssignableFrom(type) ? "" : "*");
            } else {
                this.logger.warn("The class " + type.getCanonicalName() + " does not map to any C++ type. Compilation will most likely fail.");
            }
        }
        return new String[]{prefix, suffix};
    }

    String[] cppFunctionTypeName(Method ... functionMethods) {
        String spaceName;
        Namespace namespace;
        Method functionMethod = null;
        if (functionMethods != null) {
            for (Method m2 : functionMethods) {
                if (m2 == null) continue;
                functionMethod = m2;
                break;
            }
        }
        if (functionMethod == null) {
            return null;
        }
        Class<?> type = functionMethod.getDeclaringClass();
        Convention convention = type.getAnnotation(Convention.class);
        String callingConvention = convention == null ? "" : convention.value() + " ";
        Namespace namespace2 = namespace = FunctionPointer.class.isAssignableFrom(type) ? type.getAnnotation(Namespace.class) : null;
        if (namespace != null && namespace.value().length() == 0) {
            namespace = null;
        }
        String string = spaceName = namespace == null ? "" : namespace.value();
        if (spaceName.length() > 0 && !spaceName.endsWith("::")) {
            spaceName = spaceName + "::";
        }
        Class<?> returnType = functionMethod.getReturnType();
        Class<?>[] parameterTypes = functionMethod.getParameterTypes();
        Annotation[] annotations = functionMethod.getAnnotations();
        Annotation[][] parameterAnnotations = functionMethod.getParameterAnnotations();
        String[] returnTypeName = this.cppAnnotationTypeName(returnType, annotations);
        AdapterInformation returnAdapterInfo = this.adapterInformation(false, Generator.valueTypeName(returnTypeName), annotations);
        String prefix = returnAdapterInfo != null && returnAdapterInfo.cast.length() > 0 ? returnAdapterInfo.cast : returnTypeName[0] + returnTypeName[1];
        prefix = prefix + " (" + callingConvention + spaceName + "*";
        String suffix = ")";
        if (functionMethod == functionMethods[0]) {
            int j2;
            suffix = suffix + "(";
            if (FunctionPointer.class.isAssignableFrom(type) && namespace != null && (parameterTypes.length == 0 || !Pointer.class.isAssignableFrom(parameterTypes[0]))) {
                this.logger.warn("First parameter of caller method call() or apply() for member function pointer " + type.getCanonicalName() + " is not a Pointer. Compilation will most likely fail.");
            }
            int n2 = j2 = namespace == null ? 0 : 1;
            while (j2 < parameterTypes.length) {
                String[] paramTypeName = this.cppAnnotationTypeName(parameterTypes[j2], parameterAnnotations[j2]);
                AdapterInformation paramAdapterInfo = this.adapterInformation(false, Generator.valueTypeName(paramTypeName), parameterAnnotations[j2]);
                if (paramAdapterInfo != null && paramAdapterInfo.constant) {
                    suffix = suffix + "const ";
                }
                suffix = paramAdapterInfo != null && paramAdapterInfo.cast.length() > 0 ? suffix + paramAdapterInfo.cast + " arg" + j2 : suffix + paramTypeName[0] + " arg" + j2 + paramTypeName[1];
                if (j2 < parameterTypes.length - 1) {
                    suffix = suffix + ", ";
                }
                ++j2;
            }
            suffix = suffix + ")";
        }
        if (Generator.constFunction(type, functionMethod)) {
            suffix = suffix + " const";
        }
        if (Generator.noexceptFunction(type, functionMethod)) {
            suffix = suffix + " noexcept";
        }
        return new String[]{prefix, suffix};
    }

    static String cppScopeName(MethodInformation methodInfo) {
        String spaceName;
        Namespace namespace;
        String scopeName = Generator.cppScopeName(methodInfo.cls);
        if (methodInfo.method.isAnnotationPresent(Virtual.class)) {
            String subType;
            scopeName = subType = "JavaCPP_" + Generator.mangle(scopeName);
        }
        if ((namespace = methodInfo.method.getAnnotation(Namespace.class)) == null && methodInfo.pairedMethod != null) {
            namespace = methodInfo.pairedMethod.getAnnotation(Namespace.class);
        }
        String string = spaceName = namespace == null ? "" : namespace.value();
        if (namespace != null && namespace.value().length() == 0 || spaceName.startsWith("::")) {
            scopeName = "";
        }
        if (scopeName.length() > 0 && !scopeName.endsWith("::")) {
            scopeName = scopeName + "::";
        }
        scopeName = scopeName + spaceName;
        if (spaceName.length() > 0 && !spaceName.endsWith("::")) {
            scopeName = scopeName + "::";
        }
        return scopeName + methodInfo.memberName[0];
    }

    static String cppScopeName(Class<?> type) {
        String scopeName = "";
        while (type != null) {
            String spaceName;
            Namespace namespace = type.getAnnotation(Namespace.class);
            String string = spaceName = namespace == null ? "" : namespace.value();
            if ((Enum.class.isAssignableFrom(type) || Pointer.class.isAssignableFrom(type)) && (!baseClasses.contains(type) || type.isAnnotationPresent(Name.class))) {
                String s2;
                Name name = type.getAnnotation(Name.class);
                if (name == null) {
                    s2 = type.getName();
                    int i2 = s2.lastIndexOf("$");
                    if (i2 < 0) {
                        i2 = s2.lastIndexOf(".");
                    }
                    s2 = s2.substring(i2 + 1);
                } else {
                    s2 = name.value()[0];
                }
                if (spaceName.length() > 0 && !spaceName.endsWith("::")) {
                    spaceName = spaceName + "::";
                }
                spaceName = spaceName + s2;
            }
            if (!(scopeName.length() <= 0 || scopeName.startsWith("class ") || scopeName.startsWith("struct ") || scopeName.startsWith("union ") || spaceName.endsWith("::"))) {
                spaceName = spaceName + "::";
            }
            scopeName = spaceName + scopeName;
            if (namespace != null && namespace.value().length() == 0 || spaceName.startsWith("::")) break;
            type = type.getEnclosingClass();
        }
        return scopeName;
    }

    static String jniTypeName(Class type) {
        if (type == Byte.TYPE) {
            return "jbyte";
        }
        if (type == Short.TYPE) {
            return "jshort";
        }
        if (type == Integer.TYPE) {
            return "jint";
        }
        if (type == Long.TYPE) {
            return "jlong";
        }
        if (type == Float.TYPE) {
            return "jfloat";
        }
        if (type == Double.TYPE) {
            return "jdouble";
        }
        if (type == Character.TYPE) {
            return "jchar";
        }
        if (type == Boolean.TYPE) {
            return "jboolean";
        }
        if (type == byte[].class) {
            return "jbyteArray";
        }
        if (type == short[].class) {
            return "jshortArray";
        }
        if (type == int[].class) {
            return "jintArray";
        }
        if (type == long[].class) {
            return "jlongArray";
        }
        if (type == float[].class) {
            return "jfloatArray";
        }
        if (type == double[].class) {
            return "jdoubleArray";
        }
        if (type == char[].class) {
            return "jcharArray";
        }
        if (type == boolean[].class) {
            return "jbooleanArray";
        }
        if (type.isArray()) {
            return "jobjectArray";
        }
        if (type == String.class) {
            return "jstring";
        }
        if (type == Class.class) {
            return "jclass";
        }
        if (type == Void.TYPE) {
            return "void";
        }
        return "jobject";
    }

    static String signature(Class ... types) {
        StringBuilder signature = new StringBuilder(2 * types.length);
        for (Class type : types) {
            if (type == Byte.TYPE) {
                signature.append("B");
                continue;
            }
            if (type == Short.TYPE) {
                signature.append("S");
                continue;
            }
            if (type == Integer.TYPE) {
                signature.append("I");
                continue;
            }
            if (type == Long.TYPE) {
                signature.append("J");
                continue;
            }
            if (type == Float.TYPE) {
                signature.append("F");
                continue;
            }
            if (type == Double.TYPE) {
                signature.append("D");
                continue;
            }
            if (type == Boolean.TYPE) {
                signature.append("Z");
                continue;
            }
            if (type == Character.TYPE) {
                signature.append("C");
                continue;
            }
            if (type == Void.TYPE) {
                signature.append("V");
                continue;
            }
            if (type.isArray()) {
                signature.append(type.getName().replace('.', '/'));
                continue;
            }
            signature.append("L").append(type.getName().replace('.', '/')).append(";");
        }
        return signature.toString();
    }

    static String mangle(String name) {
        StringBuilder mangledName = new StringBuilder(2 * name.length());
        for (int i2 = 0; i2 < name.length(); ++i2) {
            char c2 = name.charAt(i2);
            if (c2 >= '0' && c2 <= '9' || c2 >= 'A' && c2 <= 'Z' || c2 >= 'a' && c2 <= 'z') {
                mangledName.append(c2);
                continue;
            }
            if (c2 == '_') {
                mangledName.append("_1");
                continue;
            }
            if (c2 == ';') {
                mangledName.append("_2");
                continue;
            }
            if (c2 == '[') {
                mangledName.append("_3");
                continue;
            }
            if (c2 == '.' || c2 == '/') {
                mangledName.append("_");
                continue;
            }
            String code = Integer.toHexString(c2);
            mangledName.append("_0");
            switch (code.length()) {
                case 1: {
                    mangledName.append("0");
                }
                case 2: {
                    mangledName.append("0");
                }
                case 3: {
                    mangledName.append("0");
                }
            }
            mangledName.append(code);
        }
        return mangledName.toString();
    }

    static enum LongEnum {
        LONG;

        long value;
    }

    static enum IntEnum {
        INT;

        int value;
    }

    static enum ShortEnum {
        SHORT;

        short value;
    }

    static enum ByteEnum {
        BYTE;

        byte value;
    }

    static enum BooleanEnum {
        BOOLEAN;

        boolean value;
    }
}

