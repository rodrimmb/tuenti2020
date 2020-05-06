package es.rodrimmb.game;

public abstract class ShapeGame {

    private final String shape;

    protected ShapeGame(final String shape) {
        this.shape = shape;
    }

    public String shape() {
        return this.shape;
    }

    public abstract boolean wins(ShapeGame shapeGame);

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShapeGame)) {
            return false;
        }
        ShapeGame shapeGame = (ShapeGame) o;
        return this.shape.equals(shapeGame.shape());
    }
}
