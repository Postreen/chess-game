package Chess.piece;

import Chess.Color;
import Chess.Coordinates;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop{


    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getBishopMoves();
    }


}
