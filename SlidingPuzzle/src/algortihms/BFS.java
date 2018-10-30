package algortihms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import model.Grid;

/**
 *
 * @author Al-Hussein
 */
public class BFS {

    private Grid grid;
    Map<Grid, Grid> map = new HashMap<>();
    private int NumberOfGrids = 0;

    public BFS(Grid grid) {
        this.grid = grid;
        //  this.grid.printGrid();
    }

    public List<Grid> path(Grid finalGrid) {
        List<Grid> grids = new ArrayList<>();
        grids.add(finalGrid);
        while (map.get(finalGrid) != null) {
            grids.add(map.get(finalGrid));
            finalGrid = map.get(finalGrid);
        }
        Collections.reverse(grids);
        return grids;
    }

    public Grid Solve() {
        Queue<Grid> queue = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();

        queue.offer(grid);
        hashSet.add(grid.hashCode());
        map.put(grid, null);

        while (!queue.isEmpty()) {
            this.NumberOfGrids++;

            //     System.out.println("Size: " + queue.size());
            Grid cur = queue.poll();
            // System.out.println("New Grid: ");
            // cur.printGrid();
            if (cur.isWinning()) {
                return cur;
            }

            List<Grid> AllMoves = cur.getPossibleMove();
            for (Grid u : AllMoves) {
                if (hashSet.contains(u.hashCode())) {
                    continue;
                }
                hashSet.add(u.hashCode());
                map.put(u, cur);
                queue.offer(u);
            }
        }

        return null;
    }

    public int getNumberOfGrids() {
        return NumberOfGrids;
    }

    public void setNumberOfGrids(int NumberOfGrids) {
        this.NumberOfGrids = NumberOfGrids;
    }

}
