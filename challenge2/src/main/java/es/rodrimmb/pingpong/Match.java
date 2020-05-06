package es.rodrimmb.pingpong;

public final class Match {

    private final Integer player1;
    private final Integer player2;
    private final Integer result;

    public Match(final Integer player1, final Integer player2, final Integer result) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }

    public Integer getPlayer1() {
        return player1;
    }

    public Integer getPlayer2() {
        return player2;
    }

    public Integer winner() {
        if(result == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    public boolean hasPlayer(final Integer player) {
        return player1.equals(player) || player2.equals(player);
    }
}
