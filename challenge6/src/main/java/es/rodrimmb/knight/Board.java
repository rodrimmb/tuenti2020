package es.rodrimmb.knight;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Cell[][] board;

    private final int xSize;
    private final int ySize;

    //Esquina inferior izquierda (x0, y0)
    private final int xZero;
    private final int yZero;

    //Esquina superior izquierda
    private final int xPosition;
    private final int yPosition;


    public Board(final Cell[][] board, final int xSize, final int ySize, final int xZero, final int yZero,
                 final int xPosition, final int yPosition) {
        this.board = board;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xZero = xZero;
        this.yZero = yZero;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void setCell(Cell cell, int x, int y) {
        int finalX = x - getxZero();
        int finalY = y - getyZero();

        board[finalX][finalY] = cell == null ? new Cell("?", x, y) : cell;
    }

    public Cell getCell(int x, int y) {
        int finalX = x - getxZero();
        int finalY = y - getyZero();

        return board[finalX][finalY] == null ? new Cell("?", x, y) : board[finalX][finalY];
    }

    public List<Cell> getAllCells() {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                cells.add(board[x][y]);
            }
        }
        return cells;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public int getxZero() {
        return xZero;
    }

    public int getyZero() {
        return yZero;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public static Board generateInitialBoard(String boardStr) {
        Cell[][] board = new Cell[5][5];
        int x = -3;
        int y = 2;
        List<Cell> cells = obtainCells(x, y, boardStr);
        Board boardFinal = new Board(board, 5, 5, -3, -2, x, y);
        for (final Cell cell : cells) {
            boardFinal.setCell(cell, cell.getxPosition(), cell.getyPosition());
        }
        return boardFinal;
    }

    private static List<Cell> obtainCells(int x, int y, String boardStr) {
        int initialX = x;
        List<Cell> cells = new ArrayList<>();
        for (final char c : boardStr.toCharArray()) {
            String character = String.valueOf(c);
            if(character.equals("\n")) {
                y--;
                x = initialX;
            } else {
                Cell cell = new Cell(character, x, y);
                cells.add(cell);
                x++;
            }
        }
        return cells;
    }

    public Board updateBoard(final String movement, final String newBoardStr) {
        int xSize = increaseX(movement);
        int ySize = increaseY(movement);

        int valueX = movementX(movement);
        int valueY = movementY(movement);

        int finalX = Math.min(getxZero() + valueX, getxZero());
        int finalY = Math.min(getyZero() + valueY, getyZero());

        List<Cell> newCells = obtainCells(getxPosition() + valueX, getyPosition() + valueY, newBoardStr);
        Cell[][] newBoard = null;
        Board boardFinal = null;
        if(hasAllNewCells(newCells)) {
            newBoard = getBoard();
            boardFinal = new Board(
                    newBoard,
                    getxSize(), getySize(),
                    getxZero(), getyZero(), //Solo cambia cuando va hacia abajo o a la izquierda
                    getxPosition() + valueX, getyPosition() + valueY //Cambia siempre
            );
        } else {
            newBoard = new Cell[getxSize() + xSize][getySize() + ySize];
            boardFinal = new Board(
                    newBoard,
                    getxSize() + xSize, getySize() + ySize,
                    finalX, finalY, //Solo cambia cuando va hacia abajo o a la izquierda
                    getxPosition() + valueX, getyPosition() + valueY //Cambia siempre
            );
        }

        List<Cell> allCells = getAllCells();
        for (final Cell cell : allCells) {
            boardFinal.setCell(cell, cell.getxPosition(), cell.getyPosition());
        }

        for (final Cell cell : newCells) {
            boardFinal.setCell(cell, cell.getxPosition(), cell.getyPosition());
        }

        boardFinal.nonNullCells();

        return boardFinal;
    }

    private void nonNullCells() {
        for (int y = ySize - 1; y >= 0; y--) {
            for (int x = 0; x < xSize; x++) {
                if(board[x][y] == null) {
                    Cell cell = new Cell("?", x + getxZero(), y + getyZero());
                    setCell(cell, x + getxZero(), y + getyZero());
                }
            }
        }
    }

    private boolean hasAllNewCells(final List<Cell> newCells) {
        for (final Cell newCell : newCells) {
            try {
                getCell(newCell.getxPosition(), newCell.getyPosition());
            } catch (Throwable e) {
                return false;
            }
        }
        return true;
    }

    private int movementY(final String movement) {
        int movementValue = 0;
        if(movement.contains("u")) {
            movementValue = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("u") - 1)));
        }
        if(movement.contains("d")) {
            movementValue = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("d") - 1))) * -1;
        }
        return movementValue;
    }

    private int movementX(final String movement) {
        int movementValue = 0;
        if(movement.contains("l")) {
            movementValue = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("l") - 1))) * -1;
        }
        if(movement.contains("r")) {
            movementValue = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("r") - 1)));
        }
        return movementValue;
    }

    private int increaseY(final String movement) {
        int yIncrease = 0;
        if(movement.contains("u")) {
            yIncrease = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("u") - 1)));
        }
        if(movement.contains("d")) {
            yIncrease = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("d") - 1)));
        }
        return yIncrease;
    }

    private int increaseX(final String movement) {
        int xIncrease = 0;
        if(movement.contains("l")) {
            xIncrease = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("l") - 1)));
        }
        if(movement.contains("r")) {
            xIncrease = Integer.parseInt(String.valueOf(movement.charAt(movement.indexOf("r") - 1)));
        }
        return xIncrease;
    }

    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = ySize - 1; y >= 0; y--) {
            for (int x = 0; x < xSize; x++) {
                String cell = board[x][y] == null ? "N" : board[x][y].getContent();
                stringBuilder.append(cell);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
