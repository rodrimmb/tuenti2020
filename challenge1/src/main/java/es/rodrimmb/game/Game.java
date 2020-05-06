package es.rodrimmb.game;

public final class Game {

    private final ShapeGame shape1;
    private final ShapeGame shape2;

    public Game(final ShapeGame shape1, final ShapeGame shape2) {
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public String result() {
        if(shape1.equals(shape2)) {
            return "-";
        } else {
            return shape1.wins(shape2) ? shape1.shape() : shape2.shape();
        }
    }
}
