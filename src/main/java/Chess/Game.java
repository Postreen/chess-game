package Chess;

import Chess.board.Board;
import Chess.board.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private final Board board;

    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );

    public Game(Board board) {
        this.board = board;
    }


    public void gameLoop() {
        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("Ход белых");
            } else {
                System.out.println("Ход черных");
            }


            //input
            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            //move
            board.makeMove(move);

            //pass move
            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);
        System.out.println("Партия закончена победа = " + colorToMove.opposite());
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }
        return GameState.ONGOING;
    }
}
