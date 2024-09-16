package com.yarvin.task6;

import java.util.*;

public class Main {

    public static List<Integer> getSplit(String s) {
        List<Integer> res = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ') {
                res.add(x);
                x = 0;
            } else {
                x = x * 10 + (s.charAt(i) - '0');
            }
        }
        if (!s.isEmpty() && s.charAt(s.length() - 1) != ' ') {
            res.add(x);
        }
        return res;
    }

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        int[] t = new int[n];
        int[] deg = new int[n];
        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < n; ++i) {
            String s = scanner.nextLine();
            List<Integer> numbers = getSplit(s);
            t[i] = numbers.get(0);

            for (int j = 1; j < numbers.size(); ++j) {
                edges.get(numbers.get(j) - 1).add(i);
                deg[i]++;
            }
        }

        int[] dp = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();


        for (int v = 0; v < n; ++v) {
            if (deg[v] == 0) {
                dq.add(v);
            }
        }


        while (!dq.isEmpty()) {
            int v = dq.poll();
            dp[v] += t[v];

            for (int to : edges.get(v)) {
                deg[to]--;
                dp[to] = Math.max(dp[to], dp[v]);
                if (deg[to] == 0) {
                    dq.add(to);
                }
            }
        }

        int ans = 0;
        for (int v = 0; v < n; ++v) {
            ans = Math.max(ans, dp[v]);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        solve();
    }
}

