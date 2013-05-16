/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PriorityqueueTest {
    
    public PriorityqueueTest() {
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
     * Test of insert method, of class Priorityqueue.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Priorityqueue instance = new Priorityqueue(5);
        for (int i = 5; i > 0; --i ) {
           instance.insert(new Node(0,0,i));
        }
        assertEquals(new Node(0,0,1).f_value, instance.removemin().f_value);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of removemin method, of class Priorityqueue.
     */
    @Test
    public void testRemovemin() {
        System.out.println("removemin");
        Priorityqueue instance = new Priorityqueue(5);
        for (int i = 5; i > 0; --i) {
            instance.insert(new Node(0,0,i));
        }
        
        assertEquals(1, instance.removemin().f_value);
        assertEquals(2, instance.removemin().f_value);
        assertEquals(3, instance.removemin().f_value);
        assertEquals(4, instance.removemin().f_value);
        assertEquals(5, instance.removemin().f_value);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }
}
