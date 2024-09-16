package com.yarvin.task3;

import java.util.*;

public class Main {

    public static void solve() {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        String t = scanner.nextLine();
        int k = scanner.nextInt();

        Map<Character, Integer> goodChar = new HashMap<>();
        for (char ch : t.toCharArray()) {
            goodChar.put(ch, goodChar.getOrDefault(ch, 0) + 1);
        }

        List<String> strings = new ArrayList<>();
        StringBuilder last = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            if (!goodChar.containsKey(s.charAt(i))) {
                if (last.length() > 0) {
                    strings.add(last.toString());
                }
                last = new StringBuilder();
            } else {
                last.append(s.charAt(i));
            }
        }
        if (last.length() > 0) {
            strings.add(last.toString());
        }

        for (int i = strings.size() - 1; i >= 0; --i) {
            s = strings.get(i);
            int n = s.length();
            Map<Character, Integer> has = new HashMap<>();

            for (int l = n - 1; l >= 0; --l) {
                has.put(s.charAt(l), has.getOrDefault(s.charAt(l), 0) + 1);

                if (l + k <= n - 1) {
                    char removedChar = s.charAt(l + k);
                    has.put(removedChar, has.get(removedChar) - 1);
                    if (has.get(removedChar) == 0) {
                        has.remove(removedChar);
                    }
                }

                if (has.size() == goodChar.size()) {
                    for (int j = l; j < Math.min(l + k, n); ++j) {
                        System.out.print(s.charAt(j));
                    }
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) {
        solve();
    }
}
