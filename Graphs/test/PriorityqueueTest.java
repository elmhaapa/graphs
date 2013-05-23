/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Node;
import datastructures.Priorityqueue;
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testIsEmpty() {
        Priorityqueue q = new Priorityqueue(5);
        q.insert(new Node(1,2));
        q.insert(new Node(1,2));
        q.removemin();
        q.removemin();
        assertEquals(true, q.isEmpty());
    }
    @Test
    public void testQueue() {
        Priorityqueue q = new Priorityqueue(20);
        for (int i = 20; i > 0; --i) {
            Node n = new Node(0, 0);
            n.f_value = i;
            q.insert(n);
        }
        /*
        for (int i = 1; i <= 20; ++i) {
            Node n = q.removemin();
            assertEquals(i, n.f_value);
        } */
        
        assertEquals(1, q.removemin().f_value);
        assertEquals(2, q.removemin().f_value);
    }
}