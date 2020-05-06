package es.rodrimmb.pingpong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

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
}
