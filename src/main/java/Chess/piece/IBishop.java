package Chess.piece;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {

    default Set<CoordinatesShift> getBishopMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        //С нижнего лева, до верхнего права
        for (int i = -7; i < 7; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, i));
        }

        //С верхнего лева, до нижнего права
        for (int i = -7; i < 7; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, -i));
        }
        return result;
    }


}




