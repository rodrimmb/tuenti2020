package es.rodrimmb.tuentistic;

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
            List<Long> solutions = new ArrayList<>();
            for (int i = 0; i < numberOfCases; i++) {
                Long numbertoAnalize = Long.valueOf(br.readLine());
                Long solution = calculateNumberOfSums(numbertoAnalize);
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

    private static Long calculateNumberOfSums(final Long numbertoAnalize) {
        if(numbertoAnalize > 59) {
            return numbertoAnalize/20;
        } else {
            return calculateResult(numbertoAnalize, 20, 10);
        }

    }

    private static Long calculateResult(final Long numbertoAnalize, final int divisor, final int maxRest) {
        if(divisor > 29) {
            return null;
        }
        Long sumsOfTwenty = numbertoAnalize/divisor;
        Long rest = numbertoAnalize % divisor;
        if(rest == 0) {
            return sumsOfTwenty;
        } else {
            if(rest >= maxRest) {
                return calculateResult(numbertoAnalize, divisor + 1, maxRest - 1);
            }
            if(sumsOfTwenty == 0) {
                return null;
            } else {
                return sumsOfTwenty;
            }
        }
    }
}
