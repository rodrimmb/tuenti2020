package es.rodrimmb.galdos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BookWords {

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
