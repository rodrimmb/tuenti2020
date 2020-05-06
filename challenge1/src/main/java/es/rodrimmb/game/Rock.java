package es.rodrimmb.game;

public final class Rock extends ShapeGame {

    public final static String SHAPE = "R";

    protected Rock() {
        super(SHAPE);
    }

    @Override
    public boolean wins(final ShapeGame shapeGame) {
        return shapeGame.shape().equals(Scissors.SHAPE);
    }
}
