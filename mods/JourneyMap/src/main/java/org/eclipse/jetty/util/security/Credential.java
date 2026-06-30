/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.util.security;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ServiceLoader;
import org.eclipse.jetty.util.TypeUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.security.CredentialProvider;
import org.eclipse.jetty.util.security.Password;
import org.eclipse.jetty.util.security.UnixCrypt;

public abstract class Credential
implements Serializable {
    private static final ServiceLoader<CredentialProvider> CREDENTIAL_PROVIDER_LOADER = ServiceLoader.load(CredentialProvider.class);
    private static final Logger LOG = Log.getLogger(Credential.class);
    private static final long serialVersionUID = -7760551052768181572L;

    public abstract boolean check(Object var1);

    public static Credential getCredential(String credential) {
        if (credential.startsWith("CRYPT:")) {
            return new Crypt(credential);
        }
        if (credential.startsWith("MD5:")) {
            return new MD5(credential);
        }
        for (CredentialProvider cp : CREDENTIAL_PROVIDER_LOADER) {
            Credential credentialObj;
            if (!credential.startsWith(cp.getPrefix()) || (credentialObj = cp.getCredential(credential)) == null) continue;
            return credentialObj;
        }
        return new Password(credential);
    }

    public static class MD5
    extends Credential {
        private static final long serialVersionUID = 5533846540822684240L;
        public static final String __TYPE = "MD5:";
        public static final Object __md5Lock = new Object();
        private static MessageDigest __md;
        private final byte[] _digest;

        MD5(String digest) {
            digest = digest.startsWith(__TYPE) ? digest.substring(__TYPE.length()) : digest;
            this._digest = TypeUtil.parseBytes(digest, 16);
        }

        public byte[] getDigest() {
            return this._digest;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean check(Object credentials) {
            try {
                byte[] digest = null;
                if (credentials instanceof char[]) {
                    credentials = new String((char[])credentials);
                }
                if (credentials instanceof Password || credentials instanceof String) {
                    Object object = __md5Lock;
                    synchronized (object) {
                        if (__md == null) {
                            __md = MessageDigest.getInstance("MD5");
                        }
                        __md.reset();
                        __md.update(credentials.toString().getBytes(StandardCharsets.ISO_8859_1));
                        digest = __md.digest();
                    }
                    if (digest == null || digest.length != this._digest.length) {
                        return false;
                    }
                    boolean digestMismatch = false;
                    for (int i = 0; i < digest.length; ++i) {
                        digestMismatch |= digest[i] != this._digest[i];
                    }
                    return !digestMismatch;
                }
                if (credentials instanceof MD5) {
                    return this.equals((MD5)credentials);
                }
                if (credentials instanceof Credential) {
                    return ((Credential)credentials).check(this);
                }
                LOG.warn("Can't check " + credentials.getClass() + " against MD5", new Object[0]);
                return false;
            }
            catch (Exception e) {
                LOG.warn(e);
                return false;
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof MD5) {
                MD5 md5 = (MD5)obj;
                if (this._digest.length != md5._digest.length) {
                    return false;
                }
                boolean digestMismatch = false;
                for (int i = 0; i < this._digest.length; ++i) {
                    digestMismatch |= this._digest[i] != md5._digest[i];
                }
                return !digestMismatch;
            }
            return false;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public static String digest(String password) {
            try {
                byte[] digest;
                Object object = __md5Lock;
                synchronized (object) {
                    if (__md == null) {
                        try {
                            __md = MessageDigest.getInstance("MD5");
                        }
                        catch (Exception e) {
                            LOG.warn(e);
                            return null;
                        }
                    }
                    __md.reset();
                    __md.update(password.getBytes(StandardCharsets.ISO_8859_1));
                    digest = __md.digest();
                }
                return __TYPE + TypeUtil.toString(digest, 16);
            }
            catch (Exception e) {
                LOG.warn(e);
                return null;
            }
        }
    }

    public static class Crypt
    extends Credential {
        private static final long serialVersionUID = -2027792997664744210L;
        public static final String __TYPE = "CRYPT:";
        private final String _cooked;

        Crypt(String cooked) {
            this._cooked = cooked.startsWith(__TYPE) ? cooked.substring(__TYPE.length()) : cooked;
        }

        @Override
        public boolean check(Object credentials) {
            if (credentials instanceof char[]) {
                credentials = new String((char[])credentials);
            }
            if (!(credentials instanceof String) && !(credentials instanceof Password)) {
                LOG.warn("Can't check " + credentials.getClass() + " against CRYPT", new Object[0]);
            }
            String passwd = credentials.toString();
            return this._cooked.equals(UnixCrypt.crypt(passwd, this._cooked));
        }

        public boolean equals(Object credential) {
            if (!(credential instanceof Crypt)) {
                return false;
            }
            Crypt c = (Crypt)credential;
            return this._cooked.equals(c._cooked);
        }

        public static String crypt(String user, String pw) {
            return __TYPE + UnixCrypt.crypt(pw, user);
        }
    }
}

