package es.rodrimmb.galdos;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Line {

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
