package es.rodrimmb.game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String fileName = "submitInput.txt";
        String file = Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            Integer numberOfInputs = Integer.valueOf(br.readLine());
            List<Game> games = createGames(br, numberOfInputs);
            calculateAllGames(games);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<Game> createGames(final BufferedReader br, final int numberOfInputs)
            throws IOException {
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < numberOfInputs; i++) {
            String text = br.readLine();
            String[] textShapes = text.split(" ");
            try {
                ShapeGame shape1 = ShapeFactory.shape(textShapes[0]);
                ShapeGame shape2 = ShapeFactory.shape(textShapes[1]);
                Game game = new Game(shape1, shape2);
                games.add(game);
            } catch (ShapeGameException e) {
                System.out.println(e.getMessage());
            }
        }
        return games;
    }

    private static void calculateAllGames(final List<Game> games) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            int index = 1;
            for (final Game game : games) {
                String outputText = outputText(index, game.result());
                System.out.print(outputText);
                myWriter.write(outputText);
                index++;
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String outputText(final int index, final String result) {
        return String.format("Case #%s: %s\n", index, result);
    }
}
