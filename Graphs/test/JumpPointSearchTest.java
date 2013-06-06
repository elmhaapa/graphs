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
        star_average = (double)(star_sum / 10);
        jps_average = (double)(jps_sum / 10);
        assertEquals(true, (jps_average < star_average));
    }
}
