package es.rodrimmb.tuentistic;

import java.util.List;

import static java.util.Arrays.asList;

public class TuentisticCalculator {

    private static final List<Long> VALID_SUMS = asList(20L,21L,22L,23L,24L,25L,26L,27L,28L,29L);

    public Long calculateNumberOfSums(final Long numbertoAnalize) {
        if(numbertoAnalize > 59) {
            return numbertoAnalize/20;
        } else {
            return calculateResult(numbertoAnalize, 20, 10);
        }

    }

    private Long calculateResult(final Long numbertoAnalize, final int divisor, final int maxRest) {
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
