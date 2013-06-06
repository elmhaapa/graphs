/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Queue;
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
public class QueueTest {
    private Queue q;
    
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        q = new Queue(10);
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
    public void testEnqueue() {
        q.enqueue(new int[] {1,1});
        q.enqueue(new int[] {1,1});
    }
    @Test
    public void testDequeue() {
        q.enqueue(new int[] {1,2});
        q.enqueue(new int[] {3,4});
        assertEquals(1,q.dequeue()[0]);
        assertEquals(4,q.dequeue()[1]);
        
    }
    @Test
    public void isEmpty() {
        assertEquals(true, q.is_empty());
        q.enqueue(new int[] {1,2});
        q.enqueue(new int[] {1,2});
        assertEquals(false, q.is_empty());
        q.dequeue();
        q.dequeue();
        assertEquals(true, q.is_empty());
     
    }
    @Test
    public void testSize() {
        assertEquals(0,q.size());
        q.enqueue(new int[] {1,2});
        q.enqueue(new int[] {1,2});
        q.enqueue(new int[] {1,2});
        assertEquals(3,q.size());
        
    }
    @Test
    public void doulbeUp() {
        for (int i = 0; i < 30; ++i) {
            q.enqueue(new int[] {1,2});
        }
    }
}
