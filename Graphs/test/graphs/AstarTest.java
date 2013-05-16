/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    /**
     * Test of get_shortest_path method, of class Astar.
     */
    @Test
    public void testGet_shortest_path() {
        System.out.println("get_shortest_path");
        int size = 10;
        int s_x = 0;
        int s_y = 0;
        int t_x = 3;
        int t_y = 3;
        Astar instance = new Astar();
        int expResult = 6;
        int result = 0;
        Stack s = instance.get_shortest_path(size, s_x, s_y, t_x, t_y);
        while ( !s.isEmpty() ) {
            result++;
            s.pop();
        }
        assertEquals(expResult, result);
        s = instance.get_shortest_path(size, 0, 0, 1, 3);
        expResult = 4;
        result = 0;
        while ( !s.isEmpty() ) {
            result++;
            s.pop();
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
}
