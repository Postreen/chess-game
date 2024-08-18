package Chess;

import Chess.board.Board;
import Chess.board.BoardFactory;

public class Main {


    public static void main(String[] args) {
//        Board board = new Board();
//        board.setupDefaultPiecesPositions();


        Board board = ((new BoardFactory())).fromFEN(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
                        //  "rnb1kbnr/pppppppp/8/8/6Pq/5P2/PPPPP2P/RNBQKBNR w KQkq - 0 1"
        );

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
//        renderer.render(board);
//
//
        Game game = new Game(board);
        game.gameLoop();
    }

}
