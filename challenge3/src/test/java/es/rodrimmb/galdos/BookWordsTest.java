package es.rodrimmb.galdos;

import org.junit.Test;

import java.util.LinkedHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookWordsTest {

    @Test
    public void totalNumberOfWords() {
        BookWords bookWords = new BookWords();

        LinkedHashMap<String, Integer> wordsRanking = bookWords.getWordsRanking();

        assertThat(wordsRanking.size(), is(29900));
    }
}