/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algortihms;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import model.Grid;

/**
 *
 * @author Al-Hussein
 */
public class Dijkstra {

    private Grid grid;

    public Dijkstra(Grid grid) {
        this.grid = grid;
    }

    public boolean Solve() {
        PriorityQueue<Grid> queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 1;
            }
        });
        HashSet<Integer> hashSet = new HashSet<>();

        queue.offer(grid);

        while (!queue.isEmpty()) {
            System.out.println("Size: " + queue.size());
            Grid cur = queue.poll();
            System.out.println("New Grid: ");
            cur.printGrid();
            if (cur.isWinning()) {
                return true;
            }
            if (hashSet.contains(cur.hashCode())) {
                continue;
            }
            hashSet.add(cur.hashCode());

            List<Grid> AllMoves = cur.getPossibleMove();
            System.out.println("Size: " + AllMoves.size());
            for (Grid u : AllMoves) {
                queue.offer(u);
            }
        }

        return false;
    }

}
