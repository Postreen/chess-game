package Chess;

import Chess.board.Board;
import Chess.board.BoardFactory;
import Chess.board.Move;
import Chess.piece.King;
import Chess.piece.Piece;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {

    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input() {
        while (true) {
            System.out.println("Введите координаты (пример а2)");

            String line = scanner.nextLine();

            if (line.length() != 2) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);


            if (!Character.isLetter(fileChar)) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            if (!Character.isDigit(rankChar)) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            int rank = Character.getNumericValue(rankChar);
            if (rank < 1 || rank > 8) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            File file = File.fromChar(fileChar);
            if (file == null) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            return new Coordinates(file, rank);
        }
    }

    public static Coordinates inputPieceCoordinateForColor(Color color, Board board) {
        while (true) {

            System.out.println("Введите координаты фигуры для перемещения");

            Coordinates coordinates = input();

            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Фигуры по данным координатам нет");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if (piece.color != color) {
                System.out.println("Нельзя двигать чужую фигуру");
                continue;
            }

            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (availableMoveSquares.size() == 0) {
                System.out.println("Эта фигура заблокирована");
                continue;
            }

            return coordinates;
        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {

            System.out.println("Введите ваш ход для выбранной фигуры");

            Coordinates input = input();

            if (!coordinates.contains(input)) {
                System.out.println("Не допустимое для хода поле");
            }

            return input;
        }

    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {

        while (true) {
            Coordinates sourseCoordinates = InputCoordinates.inputPieceCoordinateForColor(color, board);

            Piece piece = board.getPiece(sourseCoordinates);
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            renderer.render(board, piece);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);

            //Проверить, является ли король под шахом после хода
            Move move = new Move(sourseCoordinates, targetCoordinates);

            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Ваш король под атакой");
                continue;
            }

            return move;
        }
    }

    private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copy = (new BoardFactory()).copy(board);
        copy.makeMove(move);

        //Поиск короля
        Piece king = copy.getPiecesByColor(color).stream()
                .filter(piece -> piece instanceof King)
                .findFirst()
                .orElse(null);


        return copy.isSquareAttackedByColor(king.coordinates, color.opposite());
    }

}
