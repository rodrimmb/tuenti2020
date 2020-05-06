package es.rodrimmb.tuentistic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TuentisticCalculatorTest {

    TuentisticCalculator tuentisticCalculator;

    @Before
    public void setUp() throws Exception {
        tuentisticCalculator = new TuentisticCalculator();
    }

    @Test
    public void onlySumsOf20() {
        Long numberToAnalize = 20L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(1L));
    }

    @Test
    public void onlySumsOf20_2() {
        Long numberToAnalize = 80L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(4L));
    }

    @Test
    public void imposibleSumOf20() {
        Long numberToAnalize = 35L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(nullValue()));
    }

    @Test
    public void numberLowerThan20() {
        Long numberToAnalize = 2L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(nullValue()));
    }

    @Test
    public void sumsOfNotAll20s() {
        Long numberToAnalize = 20L + 22L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(2L));
    }

    @Test
    public void sumsOf50() {
        Long numberToAnalize = 50L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(2L));
    }

    @Test
    public void sumsOf370() {
        Long numberToAnalize = 370L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(18L));
    }

    @Test
    public void sumsOf1835() {
        Long numberToAnalize = 1835L;

        Long solution = tuentisticCalculator.calculateNumberOfSums(numberToAnalize);

        assertThat(solution, is(91L));
    }
}