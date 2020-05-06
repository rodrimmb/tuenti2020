package es.rodrimmb.galdos;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WordsComparatorTest {

    @Test
    public void compareWords() {
        Map.Entry<String, Integer> entry1 = new Word("hola", 10);
        Map.Entry<String, Integer> entry2 = new Word("mola", 110);
        Map.Entry<String, Integer> entry3 = new Word("que", 100);
        Map.Entry<String, Integer> entry4 = new Word("queso", 100);
        Map.Entry<String, Integer> entry5 = new Word("querida", 100);

        List<Map.Entry<String, Integer>> entries = asList(entry1, entry2, entry3, entry4, entry5);

        entries.sort(new WordsComparator());

        assertThat(entries.size(), is(5));
        assertThat(entries.get(0).getKey(), is("mola"));
        assertThat(entries.get(0).getValue(), is(110));
        assertThat(entries.get(1).getKey(), is("que"));
        assertThat(entries.get(1).getValue(), is(100));
        assertThat(entries.get(2).getKey(), is("querida"));
        assertThat(entries.get(2).getValue(), is(100));
        assertThat(entries.get(3).getKey(), is("queso"));
        assertThat(entries.get(3).getValue(), is(100));
        assertThat(entries.get(4).getKey(), is("hola"));
        assertThat(entries.get(4).getValue(), is(10));
    }

    @Test
    public void compareWords2() {
        Map.Entry<String, Integer> entry1 = new Word("respiraderos", 10);
        Map.Entry<String, Integer> entry2 = new Word("respirado", 10);

        List<Map.Entry<String, Integer>> entries = asList(entry1, entry2);

        entries.sort(new WordsComparator());

        assertThat(entries.size(), is(2));
        assertThat(entries.get(0).getKey(), is("respiraderos"));
        assertThat(entries.get(0).getValue(), is(10));
        assertThat(entries.get(1).getKey(), is("respirado"));
        assertThat(entries.get(1).getValue(), is(10));
    }

    @Test
    public void compareWords3() {
        Map.Entry<String, Integer> entry1 = new Word("zumo", 10);
        Map.Entry<String, Integer> entry2 = new Word("ámbar", 10);
        Map.Entry<String, Integer> entry3 = new Word("doctor", 10);
        Map.Entry<String, Integer> entry4 = new Word("déjeme", 10);
        Map.Entry<String, Integer> entry5 = new Word("ejército", 10);
        Map.Entry<String, Integer> entry6 = new Word("ómnibus", 10);
        Map.Entry<String, Integer> entry7 = new Word("úlceras", 10);
        Map.Entry<String, Integer> entry8 = new Word("volveré", 10);
        Map.Entry<String, Integer> entry9 = new Word("ñoños", 10);
        Map.Entry<String, Integer> entry10 = new Word("baldomero", 10);
        Map.Entry<String, Integer> entry11 = new Word("ropa", 10);
        Map.Entry<String, Integer> entry12 = new Word("abrazo", 10);

        List<Map.Entry<String, Integer>> entries = asList(entry1, entry2, entry3, entry4, entry5,
                entry6, entry7, entry8, entry9, entry10, entry11, entry12);

        entries.sort(new WordsComparator());

        assertThat(entries.size(), is(12));
        assertThat(entries.get(0).getKey(), is("abrazo"));
        assertThat(entries.get(1).getKey(), is("baldomero"));
        assertThat(entries.get(2).getKey(), is("doctor"));
        assertThat(entries.get(3).getKey(), is("déjeme"));
        assertThat(entries.get(4).getKey(), is("ejército"));
        assertThat(entries.get(5).getKey(), is("ropa"));
        assertThat(entries.get(6).getKey(), is("volveré"));
        assertThat(entries.get(7).getKey(), is("zumo"));
        assertThat(entries.get(8).getKey(), is("ámbar"));
        assertThat(entries.get(9).getKey(), is("ñoños"));
        assertThat(entries.get(10).getKey(), is("ómnibus"));
        assertThat(entries.get(11).getKey(), is("úlceras"));
    }

    private class Word implements Map.Entry<String, Integer> {

        private final String word;
        private final Integer ocurrences;

        private Word(final String word, final Integer ocurrences) {
            this.word = word;
            this.ocurrences = ocurrences;
        }

        @Override
        public String getKey() {
            return word;
        }

        @Override
        public Integer getValue() {
            return ocurrences;
        }

        @Override
        public Integer setValue(final Integer value) {
            return 0;
        }
    }
}