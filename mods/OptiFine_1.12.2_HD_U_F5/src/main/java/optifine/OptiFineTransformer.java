/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.api.ITransformer
 *  cpw.mods.modlauncher.api.ITransformer$Target
 *  cpw.mods.modlauncher.api.ITransformerVotingContext
 *  cpw.mods.modlauncher.api.TransformerVoteResult
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.tree.ClassNode
 */
package optifine;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import optifine.AccessFixer;
import optifine.HashUtils;
import optifine.IOptiFineResourceLocator;
import optifine.IResourceProvider;
import optifine.Patcher;
import optifine.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

public class OptiFineTransformer
implements ITransformer<ClassNode>,
IResourceProvider,
IOptiFineResourceLocator {
    private static final Logger LOGGER = LogManager.getLogger();
    private ZipFile ofZipFile;
    private Map<String, String> patchMap = null;
    private Pattern[] patterns = null;
    public static final String PREFIX_SRG = "srg/";
    public static final String SUFFIX_CLASS = ".class";
    public static final String PREFIX_PATCH_SRG = "patch/srg/";
    public static final String SUFFIX_CLASS_XDELTA = ".class.xdelta";
    public static final String PREFIX_OPTIFINE = "optifine/";

    public OptiFineTransformer(ZipFile ofZipFile) {
        this.ofZipFile = ofZipFile;
        try {
            this.patchMap = Patcher.getConfigurationMap(ofZipFile);
            this.patterns = Patcher.getConfigurationPatterns(this.patchMap);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    public Set<ITransformer.Target> targets() {
        HashSet<ITransformer.Target> set = new HashSet<ITransformer.Target>();
        Object[] names = this.getResourceNames(PREFIX_SRG, SUFFIX_CLASS);
        Object[] namesPatch = this.getResourceNames(PREFIX_PATCH_SRG, SUFFIX_CLASS_XDELTA);
        names = (String[])Utils.addObjectsToArray(names, namesPatch);
        int i = 0;
        while (i < names.length) {
            Object name = names[i];
            name = Utils.removePrefix((String)name, new String[]{PREFIX_SRG, PREFIX_PATCH_SRG});
            if (!((String)(name = Utils.removeSuffix((String)name, new String[]{SUFFIX_CLASS, SUFFIX_CLASS_XDELTA}))).startsWith("net/optifine/")) {
                ITransformer.Target itt = ITransformer.Target.targetClass((String)name);
                set.add(itt);
            }
            ++i;
        }
        LOGGER.info("Targets: " + set.size());
        return set;
    }

    public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
        ByteArrayInputStream in;
        ClassNode classNodeNew;
        ClassNode classNode = input;
        String className = context.getClassName();
        String classNameZip = className.replace('.', '/');
        byte[] bytes = this.getOptiFineResource(PREFIX_SRG + classNameZip + SUFFIX_CLASS);
        if (bytes != null && (classNodeNew = this.loadClass(in = new ByteArrayInputStream(bytes))) != null) {
            this.debugClass(classNodeNew);
            AccessFixer.fixMemberAccess(classNode, classNodeNew);
            classNode = classNodeNew;
        }
        return classNode;
    }

    private void debugClass(ClassNode classNode) {
    }

    private ClassNode loadClass(InputStream in) {
        try {
            ClassReader cr = new ClassReader(in);
            ClassNode cn = new ClassNode(393216);
            cr.accept((ClassVisitor)cn, 0);
            return cn;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String[] getResourceNames(String prefix, String suffix) {
        ArrayList<String> list = new ArrayList<String>();
        Enumeration<? extends ZipEntry> entries = this.ofZipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            String name = zipEntry.getName();
            if (!name.startsWith(prefix) || !name.endsWith(suffix)) continue;
            list.add(name);
        }
        String[] names = list.toArray(new String[list.size()]);
        return names;
    }

    private byte[] getOptiFineResource(String name) {
        InputStream in;
        block3: {
            try {
                in = this.getOptiFineResourceStream(name);
                if (in != null) break block3;
                return null;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        byte[] bytes = Utils.readAll(in);
        in.close();
        return bytes;
    }

    @Override
    public synchronized InputStream getOptiFineResourceStream(String name) {
        InputStream in = this.getOptiFineResourceStreamZip(name = Utils.removePrefix(name, "/"));
        if (in != null) {
            return in;
        }
        in = this.getOptiFineResourceStreamPatched(name);
        if (in != null) {
            return in;
        }
        return null;
    }

    @Override
    public InputStream getResourceStream(String path) {
        path = Utils.ensurePrefix(path, "/");
        return this.getClass().getResourceAsStream(path);
    }

    public synchronized InputStream getOptiFineResourceStreamZip(String name) {
        if (this.ofZipFile == null) {
            return null;
        }
        ZipEntry ze = this.ofZipFile.getEntry(name = Utils.removePrefix(name, "/"));
        if (ze == null) {
            return null;
        }
        try {
            InputStream in = this.ofZipFile.getInputStream(ze);
            return in;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized byte[] getOptiFineResourceZip(String name) {
        InputStream in = this.getOptiFineResourceStreamZip(name);
        if (in == null) {
            return null;
        }
        try {
            byte[] bytes = Utils.readAll(in);
            return bytes;
        }
        catch (IOException e) {
            return null;
        }
    }

    public synchronized InputStream getOptiFineResourceStreamPatched(String name) {
        byte[] bytes = this.getOptiFineResourcePatched(name);
        if (bytes == null) {
            return null;
        }
        return new ByteArrayInputStream(bytes);
    }

    public synchronized byte[] getOptiFineResourcePatched(String name) {
        if (this.patterns == null || this.patchMap == null) {
            return null;
        }
        String patchName = "patch/" + (name = Utils.removePrefix(name, "/")) + ".xdelta";
        byte[] bytes = this.getOptiFineResourceZip(patchName);
        if (bytes == null) {
            return null;
        }
        try {
            byte[] md5Mod;
            String md5ModStr;
            String md5Str;
            byte[] bytesPatched = Patcher.applyPatch(name, bytes, this.patterns, this.patchMap, this);
            String fullMd5Name = "patch/" + name + ".md5";
            byte[] bytesMd5 = this.getOptiFineResourceZip(fullMd5Name);
            if (bytesMd5 != null && !(md5Str = new String(bytesMd5, "ASCII")).equals(md5ModStr = HashUtils.toHexString(md5Mod = HashUtils.getHashMd5(bytesPatched)))) {
                throw new IOException("MD5 not matching, name: " + name + ", saved: " + md5Str + ", patched: " + md5ModStr);
            }
            return bytesPatched;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

