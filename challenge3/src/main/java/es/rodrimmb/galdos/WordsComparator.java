package es.rodrimmb.galdos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class WordsComparator implements Comparator<Map.Entry<String, Integer>> {

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
