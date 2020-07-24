package com.hackerrank.graphs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

public class CountNumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        int[][] offsets = new int[][] {
           {-1, 0},
           {0, 1},
           {1, 0},
           {0, -1}
         };
         String[] dirs = new String[] { "U", "R", "D", "L"};
 
         Map<String, Integer> signs = new HashMap<>();    
         for(int i = 0; i < grid.length; i++) {
           for(int j = 0; j < grid[i].length; j++) {
             if(grid[i][j] == 0)
               continue;
             StringBuilder sb = new StringBuilder();
             LinkedList<int[]> queue = new LinkedList<>();    
             queue.offer(new int[] { i, j });
             while(queue.size() > 0) {
               int size = queue.size();
               for(int k = 0; k < size; k++) {
                 int[] point = queue.poll();
                 for(int l = 0; l < offsets.length; l++) {
                   int[] off = offsets[l];
                   int ni = point[0] + off[0], nj = point[1] + off[1];
                   if(ni < 0 || nj < 0 || ni >= grid.length || nj >= grid[ni].length)
                     continue;
                   if(grid[ni][nj] == 0)
                     continue;
                   //mark as visited
                   sb.append(dirs[l]);
                   queue.offer(new int[] { ni, nj});
                   grid[ni][nj] = 0;
                 }
                 sb.append("#");
               }
               sb.append("#");
             }
             //System.out.println(sb.toString());
             signs.put(sb.toString(), signs.getOrDefault(sb.toString(), 0) + 1);
           }
         }
         return signs.size();
         /*
               {1, 1, 1, 1},
               {0, 0, 0, 1},
               {0, 0, 0, 0},
               {1, 1, 1, 1},
               {1, 0, 0, 0}
       */
     }

     @Test
     public void TestSimpleCase() {
        int[][] grid = new int[][] {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 1}
          };
          assertEquals(1, numDistinctIslands(grid));
     }

     @Test
     public void TestSimpleCase2() {
        int[][] grid = new int[][] {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 1, 1, 1}
          };
          assertEquals(2, numDistinctIslands(grid));
     }

     @Test
     public void TestEdgeCase() {
        int[][] grid = new int[][] {
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0}
          };
          assertEquals(2, numDistinctIslands(grid));
     }

     @Test
     public void TestEdgeCase2() {
        int[][] grid = new int[][] {
            {0,0,0,0,1,1,1}, 
            {1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1},
            {1,1,1,1,1,1,0},
            {0,0,0,1,0,0,0}
          };
          assertEquals(2, numDistinctIslands(grid));
     }
}