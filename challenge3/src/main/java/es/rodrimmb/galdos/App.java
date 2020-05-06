package es.rodrimmb.galdos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class App {

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

    private static class BookWords {

        private final static String BOOK = "pg17013.txt";

        private LinkedHashMap<String, Integer> wordsRanking;
        private List<Map.Entry<String, Integer>> wordsRankingOrder;

        public BookWords() {
            this.wordsRanking = new LinkedHashMap<>();
            loadWords();
            shortRanking();
        }

        public LinkedHashMap<String, Integer> getWordsRanking() {
            return wordsRanking;
        }

        public List<Map.Entry<String, Integer>> getOrderWordsRanking() {
            return wordsRankingOrder;
        }

        public Integer findWordsRankingPosition(final String word) {
            for (final Map.Entry<String, Integer> entry : wordsRankingOrder) {
                if(entry.getKey().equals(word)) {
                    return wordsRankingOrder.indexOf(entry) + 1;
                }
            }
            return 0;
        }

        private void loadWords() {
            String file = Thread.currentThread().getContextClassLoader().getResource(BOOK).getFile();
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                Stream<String> lines = br.lines();
                lines.forEach(l -> {
                    Line line = new Line(l);
                    for (final String word : line.getWords()) {
                        if(wordsRanking.containsKey(word)) {
                            Integer count = wordsRanking.get(word);
                            count++;
                            wordsRanking.replace(word, count);
                        } else {
                            wordsRanking.put(word, 1);
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void shortRanking() {
            wordsRankingOrder = new ArrayList<>(wordsRanking.entrySet());
            wordsRankingOrder.sort(new WordsComparator());
        }
    }

    private static class Line {

        private static final List<String> VALID_LETERS = asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "á", "é", "í", "ó", "ú", "ü");
        private static final int WORD_SIZE = 3;

        private final String value;

        public Line(final String value) {
            this.value = validateLine(value);
        }

        public String value() {
            return value;
        }

        public List<String> getWords() {
            List<String> words = new ArrayList<>();
            for (final String word : value.split(" ")) {
                if(!word.isEmpty() && word.length() >= WORD_SIZE) {
                    words.add(word);
                }
            }
            return words;
        }

        private String validateLine(final String line) {
            String lowerCase = line.toLowerCase();
            StringBuilder finalLine = new StringBuilder();
            for (final char c : lowerCase.toCharArray()) {
                if(isValidCharacter(String.valueOf(c))) {
                    finalLine.append(c);
                } else {
                    finalLine.append(" ");
                }
            }
            return finalLine.toString();
        }

        private boolean isValidCharacter(final String c) {
            return VALID_LETERS.contains(c);
        }
    }

    private static class WordsComparator implements Comparator<Map.Entry<String, Integer>> {

        private static final List<String> ORDER_LETTERS = asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "á", "é", "í", "ñ", "ó", "ú", "ü");

        @Override
        public int compare(final Map.Entry<String, Integer> word1, final Map.Entry<String, Integer> word2) {
            if(word1.getValue().equals(word2.getValue())) {
                int letters = bigerWord(word1.getKey(), word2.getKey());
                for (int i = 0; i < letters; i++) {
                    if(word1.getKey().length() < i+1) {
                        return -1;
                    }
                    if(word2.getKey().length() < i+1) {
                        return 1;
                    }
                    int value1 = ORDER_LETTERS.indexOf(String.valueOf(word1.getKey().charAt(i))) + 1;
                    int value2 = ORDER_LETTERS.indexOf(String.valueOf(word2.getKey().charAt(i))) + 1;
                    if(value1 != value2) {
                        return value1 - value2;
                    }
                }
                return 0;
            } else {
                return word2.getValue() - word1.getValue();
            }
        }

        private int bigerWord(final String key, final String key1) {
            return Math.max(key.length(), key1.length());
        }
    }
}
