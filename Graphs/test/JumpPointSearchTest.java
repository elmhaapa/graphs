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
        int size = 100;
        grid = new int[size][size];
        startx = size-1;
        starty = size/2;
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
        
        long startT = System.nanoTime();
        Stack star_stack = a.get_shortest_path(grid, startx, starty, 0, 0);
        long finishT = System.nanoTime();
        long star_time = finishT - startT;
        
        startT = System.nanoTime();
        Stack jps_stack = jps.get_shortest_path(startx, starty, 0, 0, grid);
        finishT = System.nanoTime();
        long jps_time = finishT - startT;
        
        assertEquals(true, (jps_time <= star_time));
    }
}
