package Chess;

import Chess.piece.CoordinatesShift;

import java.util.Objects;

public class Coordinates {
    //Горизонталь
    public final File file;
    //Вертикаль
    public final Integer rank;

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift], this.rank + shift.rankShift);
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;

        if ((f < 0) || (f > 7)) {
            return false;
        }

        return (r >= 1) && (r <= 8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return file == that.file && rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        int result = file.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
