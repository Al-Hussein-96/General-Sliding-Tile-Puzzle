/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algortihms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Grid;

/**
 *
 * @author Al-Hussein
 */
public class DFS {
      private Grid grid;

    public DFS(Grid grid) {
        this.grid = grid;
    }

    public boolean Solve() {
        Queue<Grid> queue = new LinkedList<>();
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
