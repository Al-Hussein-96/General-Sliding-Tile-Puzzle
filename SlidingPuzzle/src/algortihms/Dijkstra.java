/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algortihms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import model.Grid;

/**
 *
 * @author Al-Hussein
 */
public class Dijkstra {

    private Grid grid;
    Map<Grid, Grid> map = new HashMap<>();
    Map<Grid, Integer> cost = new HashMap<>();

    public Dijkstra(Grid grid) {
        this.grid = grid;
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
        Comparator<Grid> comparator = (Grid o1, Grid o2) -> {
            
            if(cost.get(o1) < cost.get(o2)) return 1;
            if(cost.get(o1) > cost.get(o2)) return -1;
            return 0;
            
           // return cost.get(o1) - o2.getManhattanDistance();
        };
        PriorityQueue<Grid> queue = new PriorityQueue<>(comparator);
        HashSet<Integer> hashSet = new HashSet<>();
        queue.offer(grid);
        hashSet.add(grid.hashCode());
        map.put(grid, null);
        cost.put(grid, 0);

        while (!queue.isEmpty()) {

            Grid cur = queue.poll();

            if (cur.isWinning()) {
                return cur;
            }

            List<Grid> AllMoves = cur.getPossibleMove();

            for (Grid u : AllMoves) {
                if (!cost.containsKey(u)) {
                    cost.put(u, Integer.MAX_VALUE);
                }
//                if (hashSet.contains(u.hashCode())) {
//                    continue;
//                }
//                hashSet.add(u.hashCode());
                int DistanceFromU = cost.get(cur) + (cur.getManhattanDistance() - u.getManhattanDistance());

                if (DistanceFromU < cost.get(u)) {
                    cost.replace(u, DistanceFromU);
                    map.put(u, cur);
                    queue.offer(u);
                }

            }
        }

        return null;
    }

}
