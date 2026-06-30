/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.model;

import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import journeymap.client.model.MapType;

public class BlockDataArrays {
    private HashMap<MapType, Dataset> datasets = new HashMap(8);

    public void clearAll() {
        this.datasets.clear();
    }

    public Dataset get(MapType mapType) {
        Dataset dataset = this.datasets.get(mapType);
        if (dataset == null) {
            dataset = new Dataset();
            this.datasets.put(mapType, dataset);
        }
        return dataset;
    }

    public static boolean areIdentical(int[][] arr, int[][] arr2) {
        boolean match = true;
        for (int j = 0; j < arr.length; ++j) {
            int[] row = arr[j];
            int[] row2 = arr2[j];
            match = IntStream.range(0, row.length).map(i -> ~row[i] | row2[i]).allMatch(n -> n == -1);
            if (!match) break;
        }
        return match;
    }

    public static class DataArray<T> {
        private final HashMap<String, T[][]> map = new HashMap(4);
        private final Supplier<T[][]> initFn;

        protected DataArray(Supplier<T[][]> initFn) {
            this.initFn = initFn;
        }

        public boolean has(String name) {
            return this.map.containsKey(name);
        }

        public T[][] get(String name) {
            return this.map.computeIfAbsent(name, s -> this.initFn.get());
        }

        public T get(String name, int x, int z) {
            return this.get(name)[z][x];
        }

        public boolean set(String name, int x, int z, T value) {
            T[][] arr = this.get(name);
            T old = arr[z][x];
            arr[z][x] = value;
            return value != old;
        }

        public T[][] copy(String name) {
            T[][] src = this.get(name);
            T[][] dest = this.initFn.get();
            for (int i = 0; i < src.length; ++i) {
                System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
            }
            return dest;
        }

        public void copyTo(String srcName, String dstName) {
            this.map.put(dstName, this.copy(srcName));
        }

        public void clear(String name) {
            this.map.remove(name);
        }
    }

    public static class Dataset {
        private DataArray<Integer> ints;
        private DataArray<Float> floats;
        private DataArray<Boolean> booleans;
        private DataArray<Object> objects;

        Dataset() {
        }

        public Dataset(MapType mapType) {
        }

        protected void clear() {
            this.ints = null;
            this.floats = null;
            this.booleans = null;
            this.objects = null;
        }

        public DataArray<Integer> ints() {
            if (this.ints == null) {
                this.ints = new DataArray(() -> new Integer[16][16]);
            }
            return this.ints;
        }

        public DataArray<Float> floats() {
            if (this.floats == null) {
                this.floats = new DataArray(() -> new Float[16][16]);
            }
            return this.floats;
        }

        public DataArray<Boolean> booleans() {
            if (this.booleans == null) {
                this.booleans = new DataArray(() -> new Boolean[16][16]);
            }
            return this.booleans;
        }

        public DataArray<Object> objects() {
            if (this.objects == null) {
                this.objects = new DataArray(() -> new Object[16][16]);
            }
            return this.objects;
        }
    }
}

