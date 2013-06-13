package graphs;

import gui.Window;
import shortestPAlgorithms.Astar;
import datastructures.Node;
import datastructures.Minheap;
import datastructures.Stack;
import shortestPAlgorithms.JumpPointSearch;

public class Main {

    private static int[] random_points(int size) {
        int[] points = new int[2];
        points[0] = (int) ((Math.random() * 100) % size);
        points[1] = (int) ((Math.random() * 100) % size);
        if (points[0] == 0 && points[1] == 0) {
            return random_points(size);
        }
        return points;
    }

    /**
     * Constructs 2d int grid for pathfinding algorithms. Removes size-1 nodes
     * from grid marking them 9.
     *
     * @param size actual size will be size*size
     * @return int[][] where 0 represents node and 9 don't.
     */
    private static int[][] build_grid(int size) {
        int[][] grid = new int[size][size];

        for (int i = 0; i < size - 1; ++i) {
            boolean set = false;
            while (!set) {
                int[] points = random_points(size);
                if (grid[points[0]][points[1]] != 9) {
                    grid[points[0]][points[1]] = 9;
                    set = true;
                }
            }
        }

        return grid;
    }

    private static String calc_average(int amount, int size) {
        int i = 0;
        long a_sum = 0;
        long jps_sum = 0;

        int sx = size - 1;
        int sy = size / 2;
        int tx = 0;
        int ty = 0;
        long startTime;
        long endTime;
        
        while (i < amount) {
            int[][] grid = build_grid(size);
            startTime = System.currentTimeMillis();
            Astar star = new Astar();
            Stack s = star.get_shortest_path(grid, sx, sy, tx, ty);
            endTime = System.currentTimeMillis();
            a_sum = a_sum + (endTime - startTime);

            startTime = System.currentTimeMillis();
            JumpPointSearch jps = new JumpPointSearch();
            Stack b = jps.get_shortest_path(sx, sy, tx, ty, grid);
            endTime = System.currentTimeMillis();
            jps_sum = jps_sum + (endTime - startTime);
            i++;

        }
        return "Keskiarvo (MS): a*: " + ((double) a_sum) / amount + " jps: " + ((double) jps_sum) / amount + " Suhde (noin): "+ ((int) (a_sum) / (jps_sum));

    }

    public static void main(String[] args) throws InterruptedException {
        /*
         * Some test to check how everything works.
         */


        /*

        int size_of_map = 40;
        int[][] grid = build_grid(size_of_map);
        Window a = new Window(grid);
        //     Runner v = new Runner(size_of_map);
        Astar tahti = new Astar();
        int tahti_x = size_of_map - 1;
        int tahti_y = size_of_map / 2;
        a.change_color(tahti_x, tahti_y, 2);

        //     int[] r_target_points = random_points(size_of_map);

        long startTime = System.currentTimeMillis();
        Stack s = tahti.get_shortest_path(grid, tahti_x, tahti_y,
                0, 0);

        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.println("A* MS: " + difference + " Size: " + s.size());

        while (!s.is_empty()) {
            Node p = s.pop();
            a.change_color(p.x, p.y, 2);
        }

        JumpPointSearch jps = new JumpPointSearch();
        startTime = System.currentTimeMillis();
        s = jps.get_shortest_path(tahti_x, tahti_y, 0, 0, grid);
        endTime = System.currentTimeMillis();
        difference = endTime - startTime;
        System.out.println("JPS MS: " + difference + " Size :" + s.size());

        while (!s.is_empty()) {
            Node p = s.pop();
            //   Thread.sleep(500);
            a.change_color(p.x, p.y, 3);
        }
        */
        for (int i = 1; i < 7; ++i) {
            System.out.println(i*100 + " * " + i*100 + " grid " + calc_average(20,i*100));
        }


        /*
         int[][] t = new int[][] { {1,1}, {1,3}, {1,5}, {3,5}, {5,5}, {5,3}, {5,1}, {3,1}};
        
         for (int i = 0; i < t.length; ++i) {
         System.out.println((3-t[i][0]) + " " + (3- t[i][1]));
         }
         */
    }
}
