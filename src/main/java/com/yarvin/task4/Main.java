package com.yarvin.task4;

import java.util.*;

public class Main {

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        long r = scanner.nextLong();

        List<Integer> primes = new ArrayList<>();
        int MAXN = (int) 1e7;
        boolean[] p = new boolean[MAXN + 1];
        Arrays.fill(p, true);
        p[0] = p[1] = false;

        for (int i = 2; i <= MAXN; ++i) {
            if (!p[i]) continue;
            primes.add(i);
            for (int j = 2 * i; j <= MAXN; j += i) {
                p[j] = false;
            }
        }

        long ans = 0;
        for (int prime : primes) {
            long x = prime;
            long R = r;
            long L = l - 1;
            int degIn = 0;

            while (R / x > 0) {
                degIn++;
                R /= x;
            }

            int degOut = 0;

            while (L / x > 0) {
                degOut++;
                L /= x;
            }

            for (int deg = degOut + 1; deg <= degIn; ++deg) {
                if (deg > 1 && deg + 1 <= MAXN && p[deg + 1]) {
                    ans++;
                }
            }

            if (prime > r) break;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        solve();
    }
}
