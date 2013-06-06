/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Node;
import datastructures.Minheap;
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
public class PriorityqueueTest {
    
    Minheap q;
    
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
        q = new Minheap(40);
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
        
        q.insert(new Node(1,2));
        q.insert(new Node(1,2));
        q.removemin();
        q.removemin();
        assertEquals(true, q.is_empty());
    }
    @Test
    public void testQueue() {
        
        for (int i = 20; i > 0; --i) {
            Node n = new Node(0, 0);
            n.f_value = i;
            q.insert(n);
        }
        
        for (int i = 1; i <= 20; ++i) {
            Node n = q.removemin();

            assertEquals(i, (int)n.f_value);
        } 
        
   //     assertEquals(1, q.removemin().f_value);
     //   assertEquals(2, q.removemin().f_value);
    }
    
    @Test
    public void testRemoveMiddle() {
        for (int i = 5; i > 0; --i) {
            Node p = new Node(i, i);
            p.f_value = i;
            q.insert(p);
        }
        Node n = q.remove_middle(new Node(2,2));
        assertEquals(2, n.x);
        assertEquals(1, q.removemin().x);
        assertEquals(3, q.removemin().x);
        assertEquals(4, q.removemin().x);
        assertEquals(5, q.removemin().x);
                
        
    }
    @Test
    public void testSize() {
        for (int i = 0; i < 100; ++i) {
            Node n = new Node(i, i);
            n.f_value = i;
            q.insert(n);
        }
    }
}