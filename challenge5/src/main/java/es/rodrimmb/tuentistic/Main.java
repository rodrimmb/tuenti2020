package es.rodrimmb.tuentistic;

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
            TuentisticCalculator tuentisticCalculator = new TuentisticCalculator();
            List<Long> solutions = new ArrayList<>();
            for (int i = 0; i < numberOfCases; i++) {
                Long numbertoAnalize = Long.valueOf(br.readLine());
                Long solution = tuentisticCalculator.calculateNumberOfSums(numbertoAnalize);
                solutions.add(solution);
            }
            outputResult(solutions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputResult(final List<Long> solutions) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            int index = 1;
            for (final Long solution : solutions) {
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

    private static String outputText(final int index, final Long solution) {
        if(solution != null) {
            return String.format("Case #%s: %s\n", index, solution);
        } else {
            return String.format("Case #%s: IMPOSSIBLE\n", index);
        }
    }
}
