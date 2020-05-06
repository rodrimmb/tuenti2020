package es.rodrimmb.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    ShapeGame rock;
    ShapeGame paper;
    ShapeGame scissors;

    @Before
    public void setUp() throws Exception {
        rock = ShapeFactory.shape("R");
        paper = ShapeFactory.shape("P");
        scissors = ShapeFactory.shape("S");
    }

    @Test
    public void sameShape() {
        Game game = new Game(rock, rock);

        assertEquals(game.result(), "-");
    }

    @Test
    public void rockWinsScissors() {
        Game game = new Game(rock, scissors);

        assertEquals(game.result(), "R");
    }

    @Test
    public void scissorsWinsPaper() {
        Game game = new Game(scissors, paper);

        assertEquals(game.result(), "S");
    }

    @Test
    public void paperWinsRock() {
        Game game = new Game(paper, rock);

        assertEquals(game.result(), "P");
    }

    @Test
    public void rockWinsChangeOrder() {
        Game game = new Game(scissors, rock);

        assertEquals(game.result(), "R");
    }

    @Test
    public void scissorsWinsPaperChangeOrder() {
        Game game = new Game(paper, scissors);

        assertEquals(game.result(), "S");
    }

    @Test
    public void paperWinsRockChangeOrder() {
        Game game = new Game(rock, paper);

        assertEquals(game.result(), "P");
    }
}