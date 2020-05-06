package es.rodrimmb.game;

public final class Paper extends ShapeGame {

    public final static String SHAPE = "P";

    protected Paper() {
        super(SHAPE);
    }

    @Override
    public boolean wins(final ShapeGame shapeGame) {
        return shapeGame.shape().equals(Rock.SHAPE);
    }
}
