package es.rodrimmb.knight;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void initialBoard() {
        String initialBoard = "..#.#\n.#..#\n..KP#\n.#..#\n..###";
        Board board = Board.generateInitialBoard(initialBoard);

        String boardStr = board.draw();

        assertThat(boardStr, is(initialBoard + "\n"));
        assertThat(board.getCell(-1, 0).isKinight(), is(true));
        assertThat(board.getCell(0, 0).isPrinces(), is(true));
    }

    @Test
    public void moveKinightUpdateBoard() {
        String initialBoard = "..#.#\n.#..#\n..KP#\n.#..#\n..###";
        Board board = Board.generateInitialBoard(initialBoard);
        // 2 up 1 right
        String secondBoard = ".#.##\n.#.##\n.#K##\n#..##\n..P##";
        Board finalBoard = board.updateBoard("2u1r", secondBoard);

        String solutionBoard = "?.#.##\n?.#.##\n..#K##\n.#..##\n...P##\n.#..#?\n..###?\n";
        assertThat(finalBoard.draw(), is(solutionBoard));
        assertThat(finalBoard.getCell(0, 0).isPrinces(), is(true));
        assertThat(finalBoard.getCell(0, 2).isKinight(), is(true));
    }

    @Test
    public void moveKinightLeftDown() {
        String initialBoard = "..#.#\n.#..#\n..KP#\n.#..#\n..###";
        Board board = Board.generateInitialBoard(initialBoard);
        // 2 down 1 left
        String secondBoard = "#...P\n#.#..\n..K##\n#####\n#####";
        Board finalBoard = board.updateBoard("2d1l", secondBoard);

        String solutionBoard = "?..#.#\n?.#..#\n#...P#\n#.#..#\n..K###\n#####?\n#####?\n";
        assertThat(finalBoard.draw(), is(solutionBoard));
        assertThat(finalBoard.getCell(0, 0).isPrinces(), is(true));
        assertThat(finalBoard.getCell(-2, -2).isKinight(), is(true));
    }

    @Test
    public void returnToPosition() {
        String initialBoard = "..#.#\n.#..#\n..KP#\n.#..#\n..###";
        Board board = Board.generateInitialBoard(initialBoard);
        // 2 up 1 right
        String firstMove = ".#.##\n.#.##\n.#K##\n#..##\n..P##";
        Board firstMoveBoard = board.updateBoard("2u1r", firstMove);

        Board finalBoard = firstMoveBoard.updateBoard("2d1l", initialBoard);

        String solutionBoard = "?.#.##\n?.#.##\n..#.##\n.#..##\n..KP##\n.#..#?\n..###?\n";
        assertThat(finalBoard.draw(), is(solutionBoard));
        assertThat(finalBoard.getCell(0, 0).isPrinces(), is(true));
        assertThat(finalBoard.getCell(-1, 0).isKinight(), is(true));
    }
}