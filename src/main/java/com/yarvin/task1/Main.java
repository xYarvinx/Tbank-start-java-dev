package com.yarvin.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s + ",";

        List<int[]> numbers = new ArrayList<>();
        int last = 0;
        int now = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ',') {
                if (last == 0) {
                    numbers.add(new int[]{now, now});
                } else {
                    numbers.add(new int[]{last, now});
                }
                last = 0;
                now = 0;
                continue;
            }
            if (s.charAt(i) == '-') {
                last = now;
                now = 0;
                continue;
            }
            now = now * 10 + (s.charAt(i) - '0');
        }

        for (int[] range : numbers) {
            for (int x = range[0]; x <= range[1]; ++x) {
                System.out.print(x + " ");
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
