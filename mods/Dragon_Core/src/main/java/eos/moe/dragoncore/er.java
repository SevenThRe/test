/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.yaml.snakeyamla.configuration.InvalidConfigurationException;
import org.yaml.snakeyamla.configuration.MemorySection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class er {
    public String b;
    private List<Vec3i> o;
    public AxisAlignedBB y;
    public int k;
    private static Map<String, er> ALLATORIxDEMO = new HashMap<String, er>();

    public er(List<Vec3i> a2) {
        er a3;
        a3.o = a2;
    }

    public String ALLATORIxDEMO() {
        return null;
    }

    public static er ALLATORIxDEMO(String a2) {
        Object a3;
        if (a2 == null) {
            return null;
        }
        if (a2.contains("item:")) {
            a2 = a2.substring(0, a2.indexOf("item:"));
        }
        if (!ALLATORIxDEMO.containsKey(a2)) {
            a3 = new YamlConfiguration();
            try {
                ((YamlConfiguration)a3).loadFromString(a2);
                int a4 = 0;
                int a5 = 0;
                ArrayList a6 = new ArrayList();
                ArrayList<String> a7 = new ArrayList<String>();
                ImmutableList a8 = ((MemorySection)a3).getStringList("structural");
                if (a8.isEmpty()) {
                    a8 = ImmutableList.of((Object)"o");
                }
                for (int a9 = 0; a9 < a8.size(); ++a9) {
                    String a10 = (String)a8.get(a9);
                    int a11 = a10.indexOf("o");
                    if (a11 >= 0) {
                        a4 = a11;
                        a5 = a9;
                    }
                    if (a10.contains("=")) {
                        a6.add(new ArrayList(a7));
                        a7.clear();
                        continue;
                    }
                    a7.add(a10);
                }
                a6.add(new ArrayList(a7));
                ArrayList<Vec3i> a12 = new ArrayList<Vec3i>();
                for (int a13 = 0; a13 < a6.size(); ++a13) {
                    List a14 = (List)a6.get(a13);
                    for (int a15 = 0; a15 < a14.size(); ++a15) {
                        String[] a16 = ((String)a14.get(a15)).split("");
                        for (int a17 = 0; a17 < a16.length; ++a17) {
                            if (a16[a17].equals("x")) continue;
                            a12.add(new Vec3i(a17 - a4, a13, a15 - a5));
                        }
                    }
                }
                er a18 = new er(a12);
                a18.b = ((MemorySection)a3).getString("model");
                a18.k = ((MemorySection)a3).getInt("light");
                a18.y = new AxisAlignedBB(((MemorySection)a3).getDouble("x1", 0.0), ((MemorySection)a3).getDouble("y1", 0.0), ((MemorySection)a3).getDouble("z1", 0.0), ((MemorySection)a3).getDouble("x2", 1.0), ((MemorySection)a3).getDouble("y2", 1.0), ((MemorySection)a3).getDouble("z2", 1.0));
                ALLATORIxDEMO.put(a2, a18);
            }
            catch (InvalidConfigurationException a19) {
                ALLATORIxDEMO.put(a2, null);
            }
        }
        if ((a3 = ALLATORIxDEMO.get(a2)) != null) {
            // empty if block
        }
        return a3;
    }

    public List<BlockPos> ALLATORIxDEMO(BlockPos a2, float a4) {
        er a5;
        if (a4 == 0.0f) {
            return a5.o.stream().map(a3 -> new BlockPos(a2.getX() + -a3.getX(), a2.getY() + a3.getY(), a2.getZ() + -a3.getZ())).collect(Collectors.toList());
        }
        if (a4 == 90.0f) {
            return a5.o.stream().map(a3 -> new BlockPos(a2.getX() + a3.getZ(), a2.getY() + a3.getY(), a2.getZ() + -a3.getX())).collect(Collectors.toList());
        }
        if (a4 == 180.0f) {
            return a5.o.stream().map(a3 -> new BlockPos(a2.getX() + a3.getX(), a2.getY() + a3.getY(), a2.getZ() + a3.getZ())).collect(Collectors.toList());
        }
        if (a4 == 270.0f) {
            return a5.o.stream().map(a3 -> new BlockPos(a2.getX() + -a3.getZ(), a2.getY() + a3.getY(), a2.getZ() + a3.getX())).collect(Collectors.toList());
        }
        return ImmutableList.of((Object)a2);
    }
}

