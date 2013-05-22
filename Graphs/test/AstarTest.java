/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import datastructures.Point;
import java.util.Stack;
import shortestPAlgorithms.Astar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Blackstorm
 */
public class AstarTest {

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
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void shortestPath() {
        Astar a = new Astar();
        Stack<Point> s = a.get_shortest_path(10, 0, 0, 3, 3);
        int steps = 0;
        while ( !s.isEmpty() ) {
            s.pop();
            steps++;
        }
     assertEquals(6, steps); 
    }
    @Test
    public void correctLastStep() {
        Astar a = new Astar();
        Stack<Point> s = a.get_shortest_path(10, 0, 0, 1, 2);
        Point lastStep = s.pop();
        while ( !s.isEmpty() ) {
            lastStep = s.pop();
        }
        assertEquals(1, lastStep.x);
        assertEquals(2, lastStep.y);
    }
}
