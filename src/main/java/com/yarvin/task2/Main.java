package com.yarvin.task2;

import java.util.*;

public class Main {

    public static void solve(int n, int[] a) {
        int[] result = Arrays.copyOf(a, a.length);

        if (result[0] == -1) {
            result[0] = 1;
        }

        for (int i = 1; i < n; ++i) {
            if (result[i] == -1) {
                result[i] = result[i - 1] + 1;
            } else if (result[i] <= result[i - 1]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        System.out.print(result[0] + " ");
        for (int i = 1; i < n; ++i) {
            System.out.print((result[i] - result[i - 1]) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }

        solve(n, a);
    }
}
