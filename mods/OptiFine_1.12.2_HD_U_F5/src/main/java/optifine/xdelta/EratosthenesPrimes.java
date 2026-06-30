/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import optifine.xdelta.BitArray;

public class EratosthenesPrimes {
    static BitArray sieve;
    static int lastInit;

    static {
        lastInit = -1;
    }

    public static synchronized void reset() {
        sieve = null;
        lastInit = -1;
    }

    public static synchronized void init(int maxNumber) {
        if (maxNumber <= lastInit) {
            return;
        }
        int sqrt = (int)Math.ceil(Math.sqrt(maxNumber));
        lastInit = maxNumber;
        maxNumber >>= 1;
        sqrt >>= 1;
        ++sqrt;
        sieve = new BitArray(++maxNumber + 1);
        sieve.set(0, true);
        int i = 1;
        while (i <= sqrt) {
            if (!sieve.get(i)) {
                int currentPrime = (i << 1) + 1;
                int j = i * ((i << 1) + 2);
                while (j <= maxNumber) {
                    sieve.set(j, true);
                    j += currentPrime;
                }
            }
            ++i;
        }
    }

    public static synchronized int[] getPrimes(int maxNumber) {
        int primesNo = EratosthenesPrimes.primesCount(maxNumber);
        if (primesNo <= 0) {
            return new int[0];
        }
        if (maxNumber == 2) {
            return new int[]{2};
        }
        EratosthenesPrimes.init(maxNumber);
        int[] primes = new int[primesNo];
        int maxNumber_2 = maxNumber - 1 >> 1;
        int prime = 0;
        primes[prime++] = 2;
        int i = 1;
        while (i <= maxNumber_2) {
            if (!sieve.get(i)) {
                primes[prime++] = (i << 1) + 1;
            }
            ++i;
        }
        return primes;
    }

    public static synchronized int primesCount(int number) {
        if (number < 2) {
            return 0;
        }
        EratosthenesPrimes.init(number);
        int maxNumber_2 = number - 1 >> 1;
        int primesNo = 1;
        int i = 1;
        while (i <= maxNumber_2) {
            if (!sieve.get(i)) {
                ++primesNo;
            }
            ++i;
        }
        return primesNo;
    }

    public static synchronized int belowOrEqual(int number) {
        int maxNumber_2;
        if (number < 2) {
            return -1;
        }
        if (number == 2) {
            return 2;
        }
        EratosthenesPrimes.init(number);
        int i = maxNumber_2 = number - 1 >> 1;
        while (i > 0) {
            if (!sieve.get(i)) {
                return (i << 1) + 1;
            }
            --i;
        }
        return -1;
    }

    public static int below(int number) {
        return EratosthenesPrimes.belowOrEqual(number - 1);
    }
}

