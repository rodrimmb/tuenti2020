package es.rodrimmb.game;

public class ShapeFactory {

    public static ShapeGame shape(String shape) throws ShapeGameException {
        switch (shape) {
            case Rock.SHAPE:
                return new Rock();
            case Scissors.SHAPE:
                return new Scissors();
            case Paper.SHAPE:
                return new Paper();
            default:
                throw new ShapeGameException(String.format("The shape %s does not exist, it should be R, P or S", shape));
        }
    }
}
