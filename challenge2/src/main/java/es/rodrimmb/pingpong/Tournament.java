package es.rodrimmb.pingpong;

import java.util.*;

public final class Tournament {

    private final List<Match> matches;

    public Tournament(final List<Match> matches) {
        this.matches = matches;
    }

    public Integer findBestPlayer() {
        Integer player = matches.get(0).winner();
        Integer winner = otherPlayerWinsPlayer(player);
        if(winner == null) {
            return player;
        }
        return winner;
    }

    private Integer otherPlayerWinsPlayer(final Integer player) {
        Integer winner = null;
        for (final Match match : matches) {
            if(match.hasPlayer(player) && !player.equals(match.winner())) {
                winner = match.winner();
                otherPlayerWinsPlayer(winner);
            }
        }
        return winner;
    }
}
