package es.rodrimmb.pingpong;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TournamentTest {

    @Test
    public void threeMatches() {
        Match math1 = new Match(3, 2, 1);
        Match math2 = new Match(1, 3, 0);
        Match math3 = new Match(3, 2, 1);
        Tournament tournament = new Tournament(asList(math1, math2, math3));

        Integer winner = tournament.findBestPlayer();

        assertThat(winner, is(3));
    }

    @Test
    public void name() {
        Match math1 = new Match(3, 2, 1);
        Match math2 = new Match(1, 3, 0);
        Match math3 = new Match(3, 2, 1);
        Match math4 = new Match(4, 3, 1);
        Tournament tournament = new Tournament(asList(math1, math2, math3, math4));

        Integer winner = tournament.findBestPlayer();

        assertThat(winner, is(4));
    }
}