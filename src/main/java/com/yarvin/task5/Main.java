package com.yarvin.task5;
import java.util.*;
import java.io.*;

public class Main {
    
    public static int diffTime(String startTime, String currentTime) {
        int startSeconds = parseTimeToSeconds(startTime);
        int currentSeconds = parseTimeToSeconds(currentTime);
        int diff;

        if (currentSeconds >= startSeconds) {
            diff = (currentSeconds - startSeconds) / 60;
        } else {
            diff = ((24 * 60 * 60) + (currentSeconds - startSeconds)) / 60;
        }

        return diff;
    }


    public static int parseTimeToSeconds(String time) {
        String[] parts = time.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        int ss = Integer.parseInt(parts[2]);
        return hh * 3600 + mm * 60 + ss;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String startTime = br.readLine().trim();
        int n = Integer.parseInt(br.readLine().trim());


        Map<String, Team> teams = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            String line = br.readLine().trim();


            int firstQuote = line.indexOf('"');
            int secondQuote = line.indexOf('"', firstQuote + 1);
            String teamName = line.substring(firstQuote + 1, secondQuote);


            String rest = line.substring(secondQuote + 1).trim();
            String[] parts = rest.split(" ");


            String queryTime = parts[0];
            char serverId = parts[1].charAt(0);
            String result = parts[2];


            teams.putIfAbsent(teamName, new Team(teamName));
            Team team = teams.get(teamName);

            if (result.equals("PONG")) {
                continue;
            }

            if (team.hackedServers.contains(serverId)) {
                continue;
            }

            if (result.equals("ACCESSED")) {

                team.serversHacked += 1;


                int minutes = diffTime(startTime, queryTime);
                int deniedAttempts = team.deniedAttempts.getOrDefault(serverId, 0);
                int penalty = minutes + 20 * deniedAttempts;


                team.penaltyTime += penalty;


                team.hackedServers.add(serverId);
            } else if (result.equals("DENIED") || result.equals("FORBIDEN")) {

                team.deniedAttempts.put(serverId, team.deniedAttempts.getOrDefault(serverId, 0) + 1);
            }
        }


        List<Team> teamList = new ArrayList<>(teams.values());


        teamList.sort(new Comparator<Team>() {
            public int compare(Team a, Team b) {
                if (a.serversHacked != b.serversHacked) {
                    return Integer.compare(b.serversHacked, a.serversHacked);
                }
                if (a.penaltyTime != b.penaltyTime) {
                    return Integer.compare(a.penaltyTime, b.penaltyTime);
                }
                return a.teamName.compareTo(b.teamName);
            }
        });


        List<String> output = new ArrayList<>();
        int place = 1;
        for (int i = 0; i < teamList.size(); ++i) {
            if (i > 0) {
                Team prev = teamList.get(i - 1);
                Team current = teamList.get(i);
                if (prev.serversHacked != current.serversHacked || prev.penaltyTime != current.penaltyTime) {
                    place = i + 1;
                }
            }

            Team current = teamList.get(i);
            output.add(place + " \"" + current.teamName + "\" " + current.serversHacked + " " + current.penaltyTime);
        }


        for (String line : output) {
            System.out.println(line);
        }
    }


    static class Team {
        String teamName;
        int serversHacked;
        int penaltyTime;
        Map<Character, Integer> deniedAttempts;
        Set<Character> hackedServers;

        Team(String teamName) {
            this.teamName = teamName;
            this.serversHacked = 0;
            this.penaltyTime = 0;
            this.deniedAttempts = new HashMap<>();
            this.hackedServers = new HashSet<>();
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
