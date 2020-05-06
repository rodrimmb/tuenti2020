package es.rodrimmb.knight;

public class Cell {

    private static final String PRINCESS = "P";
    private static final String VALID = ".";
    private static final String KNIGHT = "K";
    private static final String INVALID = "#";

    private final String content;
    private final Integer xPosition;
    private final Integer yPosition;

    public Cell(final String content, final Integer xPosition, final Integer yPosition) {
        this.content = content;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public String getContent() {
        return content;
    }

    public boolean isPrinces() {
        return PRINCESS.equals(content);
    }

    public boolean isValidCell() {
        return VALID.equals(content);
    }

    public boolean isKinight() {
        return KNIGHT.endsWith(content);
    }
}
