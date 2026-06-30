/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  org.apache.logging.log4j.util.Strings
 */
package journeymap.common.version;

import com.google.common.base.Joiner;
import java.util.Arrays;
import journeymap.common.Journeymap;
import org.apache.logging.log4j.util.Strings;

public class Version
implements Comparable<Version> {
    public final int major;
    public final int minor;
    public final int micro;
    public final String patch;

    public Version(int major, int minor, int micro) {
        this(major, minor, micro, "");
    }

    public Version(int major, int minor, int micro, String patch2) {
        this.major = major;
        this.minor = minor;
        this.micro = micro;
        this.patch = patch2 != null ? patch2 : "";
    }

    public static Version from(String major, String minor, String micro, String patch2, Version defaultVersion) {
        Version result = null;
        try {
            if (!major.contains("@")) {
                result = new Version(Version.parseInt(major), Version.parseInt(minor), Version.parseInt(micro), patch2);
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().warn(String.format("Version had problems when parsed: %s, %s, %s, %s", major, minor, micro, patch2));
        }
        if (result == null) {
            if (defaultVersion == null) {
                defaultVersion = new Version(0, 0, 0);
            }
            result = defaultVersion;
        }
        return result;
    }

    public static Version from(String versionString, Version defaultVersion) {
        try {
            String patch2;
            String[] strings = versionString.split("(?<=\\d)(?=\\p{L})");
            String[] majorMinorMicro = strings[0].split("\\.");
            String string = patch2 = strings.length == 2 ? strings[1] : "";
            if (majorMinorMicro.length < 3) {
                majorMinorMicro = Arrays.copyOf(strings, 3);
            }
            return Version.from(majorMinorMicro[0], majorMinorMicro[1], majorMinorMicro[2], patch2, defaultVersion);
        }
        catch (Exception e) {
            Journeymap.getLogger().warn(String.format("Version had problems when parsed: %s", versionString));
            if (defaultVersion == null) {
                defaultVersion = new Version(0, 0, 0);
            }
            return defaultVersion;
        }
    }

    private static int parseInt(String number) {
        if (number == null) {
            return 0;
        }
        return Integer.parseInt(number);
    }

    public String toMajorMinorString() {
        return Joiner.on((String)".").join((Object)this.major, (Object)this.minor, new Object[0]);
    }

    public boolean isNewerThan(Version other) {
        return this.compareTo(other) > 0;
    }

    public boolean isRelease() {
        return Strings.isEmpty((CharSequence)this.patch);
    }

    public String toString() {
        return Joiner.on((String)".").join((Object)this.major, (Object)this.minor, new Object[]{this.micro + this.patch});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Version version = (Version)o;
        if (this.major != version.major) {
            return false;
        }
        if (this.micro != version.micro) {
            return false;
        }
        if (this.minor != version.minor) {
            return false;
        }
        return this.patch.equals(version.patch);
    }

    public int hashCode() {
        int result = this.major;
        result = 31 * result + this.minor;
        result = 31 * result + this.micro;
        result = 31 * result + this.patch.hashCode();
        return result;
    }

    @Override
    public int compareTo(Version other) {
        int result = Integer.compare(this.major, other.major);
        if (result == 0) {
            result = Integer.compare(this.minor, other.minor);
        }
        if (result == 0) {
            result = Integer.compare(this.micro, other.micro);
        }
        if (result == 0 && (result = this.patch.compareToIgnoreCase(other.patch)) != 0) {
            if (this.patch.equals("")) {
                result = 1;
            }
            if (other.patch.equals("")) {
                result = -1;
            }
        }
        return result;
    }
}

