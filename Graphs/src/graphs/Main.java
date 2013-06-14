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

    /**
     * We calculate average time and return it as string for main to print.
     *
     * @param amount this is how many times we calc for one size
     * @param size this is for how large grids we calc. size*size is the amount
     * of nodes
     * @return
     */
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
        return "Keskiarvo (MS): a*: " + ((double) a_sum) / amount + " jps: " + ((double) jps_sum) / amount + " Suhde (noin): " + ((int) (a_sum) / (jps_sum));

    }

    public static void main(String[] args) throws InterruptedException {
        // We compare the JPS and A*
        int times = 20;
        if (args.length == 1) {
            try {
                times = Math.abs(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                // User didnt give number do nothing.
            }
        }
        for (int i = 1; i <= times; ++i) {
            System.out.println(i * 100 + " * " + i * 100 + " grid " + calc_average(20, i * 100));
        }

    }
}
