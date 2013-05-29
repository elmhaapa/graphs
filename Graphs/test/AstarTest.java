/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import datastructures.Node;
import datastructures.Stack;
import shortestPAlgorithms.Astar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class AstarTest {

    Astar a;
<<<<<<< HEAD
    int[][] grid;
=======
>>>>>>> 1c964feea2940c70d624811bf881f698ab326069
    public AstarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a = new Astar();
<<<<<<< HEAD
        grid = new int[30][30];
=======
>>>>>>> 1c964feea2940c70d624811bf881f698ab326069
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void shortestPath() {
<<<<<<< HEAD
        Stack s = a.get_shortest_path(grid, 0, 0, 3, 3);
=======
        Stack s = a.get_shortest_path(10, 0, 0, 3, 3);
>>>>>>> 1c964feea2940c70d624811bf881f698ab326069
        int steps = 0;
        while ( !s.is_empty() ) {
            s.pop();
            steps++;
        }
     assertEquals(6, steps); 
    }
    
    @Test
    public void shortestPath2() {
        int x = (int) (100*Math.random() % 30);
        int y = (int) (100*Math.random() % 30);
<<<<<<< HEAD
        Stack s = a.get_shortest_path(grid, 0, 0, x, y);
=======
        Stack s = a.get_shortest_path(30, 0, 0, x, y);
>>>>>>> 1c964feea2940c70d624811bf881f698ab326069
        int exp_result = x + y;
        int steps = 0;
        while (!s.is_empty()) {
            s.pop();
            steps++;        
        }
        assertEquals(exp_result, steps);
    }
    @Test
    public void correctLastStep() {
        Astar a = new Astar();
        Stack s = a.get_shortest_path(grid, 0, 0, 1, 2);
        Node lastStep = s.pop();
        while ( !s.is_empty() ) {
            lastStep = s.pop();
        }
        assertEquals(1, lastStep.x);
        assertEquals(2, lastStep.y);
    }
}
