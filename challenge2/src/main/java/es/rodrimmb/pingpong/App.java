package es.rodrimmb.pingpong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        final String fileName = "submitInput.txt";
        String file = Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            Integer numberOfCases = Integer.valueOf(br.readLine());
            List<Tournament> tournaments = new ArrayList<>();
            for (int i = 0; i < numberOfCases; i++) {
                Integer numberOfMatches = Integer.valueOf(br.readLine());
                List<Match> matches = new ArrayList<>();
                for (int j = 0; j < numberOfMatches; j++) {
                    String line = br.readLine();
                    String[] info = line.split(" ");
                    matches.add(new Match(Integer.valueOf(info[0]), Integer.valueOf(info[1]),
                            Integer.valueOf(info[2])));
                }
                tournaments.add(new Tournament(matches));
            }
            outputResult(tournaments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputResult(final List<Tournament> tournaments) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            int index = 1;
            for (final Tournament tournament : tournaments) {
                String outputText = outputText(index, tournament.findBestPlayer());
                System.out.print(outputText);
                myWriter.write(outputText);
                index++;
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String outputText(final int index, final Integer winner) {
        return String.format("Case #%s: %s\n", index, winner);
    }

    public static final class Match {

        private final Integer player1;
        private final Integer player2;
        private final Integer result;

        public Match(final Integer player1, final Integer player2, final Integer result) {
            this.player1 = player1;
            this.player2 = player2;
            this.result = result;
        }

        public Integer winner() {
            if(result == 1) {
                return player1;
            } else {
                return player2;
            }
        }

        public boolean hasPlayer(final Integer player) {
            return player1.equals(player) || player2.equals(player);
        }
    }

    public static final class Tournament {

        private final List<Match> matches;

        public Tournament(final List<Match> matches) {
            this.matches = matches;
        }

        public Integer findBestPlayer() {
            Integer player = matches.get(0).winner();
            Integer winner = otherPlayerWinsPlayer(player);
            if(winner == null) {
                return player;
            }
            return winner;
        }

        private Integer otherPlayerWinsPlayer(final Integer player) {
            Integer winner = null;
            for (final Match match : matches) {
                if(match.hasPlayer(player) && !player.equals(match.winner())) {
                    winner = match.winner();
                    otherPlayerWinsPlayer(winner);
                }
            }
            return winner;
        }
    }

}
