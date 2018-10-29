package algortihms;

import javafx.scene.paint.Color;
import model.Grid;
import model.Slice;
import model.Square;

/**
 *
 * @author Al-Hussein
 */
public class ManhattanDistance {

    private Grid grid;

    public ManhattanDistance(Grid grid) {
        this.grid = grid;
    }

    public  int getDistance() {
        int dist = 10000;
        for (Slice slice : grid.getListSlice()) {
            if (slice.getColor() == Color.RED) {
                for (Square square : slice.getListSquare()) {
                    int x = square.getRow() - grid.getHeight();
                    int y = square.getCol() - grid.getWeight();
                    dist = Math.min(dist, Math.abs(x) + Math.abs(y));
                }
                break;
            }
        }
        return dist;
    }

}
