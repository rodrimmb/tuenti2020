package es.rodrimmb.game;

public final class Scissors extends ShapeGame {

    public final static String SHAPE = "S";

    protected Scissors() {
        super(SHAPE);
    }

    @Override
    public boolean wins(final ShapeGame shapeGame) {
        return shapeGame.shape().equals(Paper.SHAPE);
    }
}
