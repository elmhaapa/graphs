/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import shortestPAlgorithms.Astar;
import datastructures.Node;
import datastructures.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shortestPAlgorithms.JumpPointSearch;

/**
 * Tests for JPS
 *
 */
public class JumpPointSearchTest {

    private JumpPointSearch jps;
    private int startx;
    private int starty;
    private Astar a;
    private int[][] grid;

    public JumpPointSearchTest() {
    }

    /**
     * Returns random points for grid
     *
     * @param size of grid
     * @return tuple of points
     */
    private int[] random_points(int size) {
        int[] points = new int[2];
        points[0] = (int) ((Math.random() * 100) % size);
        points[1] = (int) ((Math.random() * 100) % size);
        if (points[0] == 0 && points[1] == 0) {
            return random_points(size);
        }
        return points;
    }

    /**
     * Builds grid of size*size
     * @param size length of one axis
     * @return grid
     */
    private int[][] build_grid(int size) {
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

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jps = new JumpPointSearch();
        a = new Astar();
        int size = 30;
        grid = new int[size][size];
        startx = size - 1;
        starty = size / 2;
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testShortestPath() {
        // We make path both with A* and JPS and see if length is the same.

        // Target is (0,0)       
        Stack star_stack = a.get_shortest_path(grid, startx, starty, 0, 0);
        Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 0, grid);

        assertEquals(star_stack.size(), jps_stack.size());
    }
    
    @Test
    public void testShortesPath2() {
        // This time we test paths with obstacles. It is possible that there is no path
        // at all so we must accept null aswell.
        int[][] grid_obstacles = build_grid(50);
        //(0,0) is the target
        Stack star_stack = a.get_shortest_path(grid_obstacles, startx, starty, 0, 0);
        Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 0, grid_obstacles);
        boolean correct_paths = (star_stack == null && jps_stack == null) || (jps_stack.size() == star_stack.size());
        assertEquals(true, correct_paths);
    }
    @Test
    public void correctLastStep() {
        // We test if the last step on our route is correct one.
        Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 0, grid);
        Node last_step = new Node(1,1);
        while(!jps_stack.is_empty()) {
            last_step = jps_stack.pop();
        }
        assertEquals(0, last_step.x);
        assertEquals(0, last_step.y);         
    }
    @Test
    public void correctLastStep2() {
        // Here we test if last step is correct with a grid that has obstacles
        // end point is (0,99)
        int[][] grid_obstacles = build_grid(100);
        Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 99, grid_obstacles);
        Node last_step = new Node(2,2);
        while (!jps_stack.is_empty()) {
            last_step = jps_stack.pop();
        }
        assertEquals(0, last_step.x);
        assertEquals(99, last_step.y);
    }
           

    @Test
    public void testTime() {
        // Let's assume JPS should take less time then A* and test it.
        // We run the algorithms few times and take time. Then we calculate average
        // and compare them.

        int i = 0;
        double jps_average;
        double star_average;
        long star_sum = 0;
        long jps_sum = 0;
        long startT;
        long finishT;
        while (i < 10) {
            startT = System.nanoTime();
            Astar a = new Astar();
            Stack star_stack = a.get_shortest_path(grid, startx, starty, 0, 0);
            finishT = System.nanoTime();
            star_sum = star_sum + (finishT - startT);

            startT = System.nanoTime();
            JumpPointSearch jps = new JumpPointSearch();
            Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 0, grid);
            finishT = System.nanoTime();
            jps_sum = jps_sum + (finishT - startT);
            i++;
        }
        star_average = (double) (star_sum / 10);
        jps_average = (double) (jps_sum / 10);
        assertEquals(true, (jps_average < star_average));
    }
}
