/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Stack;
import datastructures.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StackTest {
    
    public StackTest() {
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
    public void test_push_pop() {
        Node t1 = new Node(1, 1);
        Node t2 = new Node(2, 2);
        Stack s = new Stack(10);
        s.push(t1);
        s.push(t2);
        assertEquals(2, s.pop().x);
        assertEquals(1, s.pop().x);
    }
    @Test
    public void test_is_empty() {
        Node t1 = new Node(1, 1);
        Stack s = new Stack(10);
        s.push(t1);
        assertEquals(false, s.is_empty());
        s.pop();
        assertEquals(true, s.is_empty());
    }
    
    @Test
    public void test_double_up() {
        Stack s = new Stack(2);
        for (int i = 0; i < 20; ++i) {
            s.push(new Node(i,i));
        }
        
    }
}
