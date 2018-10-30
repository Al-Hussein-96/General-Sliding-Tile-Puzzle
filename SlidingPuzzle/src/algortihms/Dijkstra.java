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
    Map<Integer, Integer> g = new HashMap<>();
    Map<Integer, Integer> cost = new HashMap<>();
    private int NumberOfGrids = 0;

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

            if (cost.get(o1.hashCode()) < cost.get(o2.hashCode())) {
                return -1;
            }
            if (cost.get(o1.hashCode()) > cost.get(o2.hashCode())) {
                return +1;
            }
            return 0;

            // return cost.get(o1) - o2.getManhattanDistance();
        };
        PriorityQueue<Grid> queue = new PriorityQueue<>(comparator);
        HashSet<Integer> hashSet = new HashSet<>();
        queue.offer(grid);

        hashSet.add(grid.hashCode()); /// mark it visited
        map.put(grid, null);         /// for path
        System.out.println("grid manh :" + grid.getManhattanDistance());
        cost.put(grid.hashCode(), grid.getManhattanDistance()); /// cost : 0
        g.put(grid.hashCode(), 0);    /// number of movment : 0
        while (!queue.isEmpty()) {
            this.NumberOfGrids++;
            Grid cur = queue.poll();
            if (cur.isWinning()) {
                return cur;
            }
            //  System.out.println("Values: " + cost.get(cur.hashCode()) + " , " + g.get(cur.hashCode()));
            List<Grid> AllMoves = cur.getPossibleMove();

            for (Grid u : AllMoves) {

                if (!hashSet.contains(u.hashCode())) {
                    g.put(u.hashCode(), g.get(cur.hashCode()) + 1);
                    cost.put(u.hashCode(), g.get(u.hashCode()) + u.getManhattanDistance());
                    hashSet.add(u.hashCode());
                    map.put(u, cur);
                    queue.offer(u);

                } else {
                    int G = g.get(u.hashCode()) + u.getManhattanDistance();
                    // System.out.println("G: " + G + " last: " + cost.get(u.hashCode()));
                    if (G < cost.get(u.hashCode())) {
                      //  System.out.println("Hello World");
                        cost.replace(u.hashCode(), G);
                        g.replace(u.hashCode(), g.get(cur.hashCode()) + 1);
                        map.put(u, cur);
                        queue.offer(u);
                    }
                }

//                if (!cost.containsKey(u)) {
//                    cost.put(u, Integer.MAX_VALUE);
//                    g.put(Integer.SIZE, Integer.MIN_VALUE)
//                }
////                if (hashSet.contains(u.hashCode())) {
////                    continue;
////                }
////                hashSet.add(u.hashCode());
//                System.out.println("Key: " + g.containsKey(cur.hashCode()));
//                int DistanceFromU = g.get(cur.hashCode()) + u.getManhattanDistance() + 1;
//
//                if (DistanceFromU < cost.get(u)) {
//                    cost.replace(u, DistanceFromU);
//                    g.replace(u.hashCode(), g.get(cur.hashCode()) + 1);
//                    map.put(u, cur);
//                    queue.offer(u);
//                }
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
