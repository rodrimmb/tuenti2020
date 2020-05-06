package es.rodrimmb.knight;

import java.util.List;

import static java.util.Arrays.asList;

public class Main {

    private static final String IP = "52.49.91.111";
    private static final int PORT = 2003;

    private static final List<String> COMMANDS = asList("2u1r", "2l1u", "2l1u", "2u1l", "2u1l", "1l2d", "1l2u",
            "2l1d", "2l1u", "1l2d", "1l2u", "1l2d", "1l2u", "2u1l", "1u2r", "2u1r", "2l1d", "2u1l", "1d2l", "2l1d",
            "2u1r", "2l1u", "1d2l", "2l1u", "1d2l", "2l1u", "1d2l", "2l1u", "1d2l", "2d1l", "2d1r", "2d1l", "2l1d",
            "2l1d", "2l1u", "2l1u", "2l1u", "2l1u", "2u1r", "2u1r", "2d1l", "2d1l", "2d1l", "2l1d", "2r1d", "1d2r",
            "2d1l", "2d1r", "2d1r", "2u1r", "2d1r", "2u1r", "2d1r");

    public static void main(String[] args) throws Exception {
        KnightClient knightClient = new KnightClient();
        knightClient.startConnection(IP, PORT);
        String firstRead = knightClient.firstRead();
        Board board = Board.generateInitialBoard(firstRead);

        Board oldBoard = board;
        Board newBoard = null;
        for (final String command : COMMANDS) {
            newBoard = oldBoard.updateBoard(command, knightClient.sendMessage(command));
            System.out.println(newBoard.draw());
            oldBoard = newBoard;
        }

        knightClient.stopConnection();
        System.out.println("Finalizada conexion");
    }
}
