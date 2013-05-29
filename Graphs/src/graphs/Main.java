package graphs;

import gui.Window;
import shortestPAlgorithms.Astar;
import datastructures.Node;
import datastructures.Priorityqueue;
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

    public static void main(String[] args) throws InterruptedException {
        /*
         * Some test to check how everything works.
         */

        int size_of_map = 30;
        int[][] grid = build_grid(size_of_map);
        Window a = new Window(grid);
        //     Runner v = new Runner(size_of_map);
        Astar tahti = new Astar();
        int tahti_x = size_of_map - 1;
        int tahti_y = size_of_map / 2;
        a.change_color(tahti_x, tahti_y, 2);

<<<<<<< HEAD
        int[] r_target_points = random_points(size_of_map);
        
        long startTime = System.currentTimeMillis();
        Stack s = tahti.get_shortest_path(grid, tahti_x, tahti_y,
                0, 0);        
        
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.println("A* MS: " + difference );
        
        while (!s.is_empty()) {
            Node p = s.pop();
            a.change_color(p.x, p.y, 2);
        }
        
        JumpPointSearch jps = new JumpPointSearch();
        startTime = System.currentTimeMillis();
        s = jps.get_shortest_path(tahti_x, tahti_y, 0, 0, grid);
        endTime = System.currentTimeMillis();
        difference = endTime - startTime;
        System.out.println("JPS MS: " + difference);
        
        while (!s.is_empty()) {
            Node p = s.pop();
            
            a.change_color(p.x, p.y, 3);        
        }
=======
        Stack s = tahti.get_shortest_path(size_of_map, tahti_x, tahti_y,
                (int) (100 * Math.random() % size_of_map), (int) (100 * Math.random() % size_of_map));
        // just some random numbers for testing
        
        while (!s.is_empty()) {
            Node p = s.pop();
            a.change_color(p.x, p.y, 2);
        }
        /*
         while (true) {
         int t_x = v.get_target().x;
         int t_y = v.get_target().y;
         v.update_target();
         Point tn = v.get_target();
         a.change_color(t_x, t_y, 0);
         a.change_color(tn.x, tn.y, 1);
                      
         Thread.sleep(200);
         }
         */
>>>>>>> 1c964feea2940c70d624811bf881f698ab326069

    }
}
