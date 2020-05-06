package es.rodrimmb.galdos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String fileName = "submitInput.txt";
        BookWords bookWords = new BookWords();
        String file = Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            Integer numberOfCases = Integer.valueOf(br.readLine());
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < numberOfCases; i++) {
                String line = br.readLine();
                if(isNumber(line)) {
                    Map.Entry<String, Integer> word = bookWords.getOrderWordsRanking()
                            .get(Integer.parseInt(line) - 1);
                    solution.add(word.getKey() + " " + word.getValue());
                } else {
                    Integer integer = bookWords.getWordsRanking().get(line);
                    Integer wordsRanking = bookWords.findWordsRankingPosition(line);
                    solution.add(integer + " #" + wordsRanking);
                }
            }
            outputResult(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputResult(final List<String> solutions) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            int index = 1;
            for (final String solution : solutions) {
                String outputText = outputText(index, solution);
                System.out.print(outputText);
                myWriter.write(outputText);
                index++;
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String outputText(final int index, final String winner) {
        return String.format("Case #%s: %s\n", index, winner);
    }

    private static boolean isNumber(final String line) {
        try {
            Integer.parseInt(line);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
