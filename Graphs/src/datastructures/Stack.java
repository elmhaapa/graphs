/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 * My stack implementation.
 * 
 */
public class Stack {
    
    private int size;
    private int max_size;
    private Node[] stack;
    
    public Stack(int m_size) {
        max_size = m_size;
        size = 0;
        stack = new Node[max_size];
    }
    
    
    
    public boolean is_empty() {
        return size == 0;
    }
    
    /**
     * Pushes node to stack.
     * @param n node
     */
    public void push(Node n) {
        size++;
        if (size > max_size-1) {
            double_up();
        }
        stack[size] = n;
    }
    /**
     * Retrieves a node from stack.
     * @return 
     */
    public Node pop() {
        size--;
        return stack[size+1];
    }
    public int size() {
        return size;
    }
    /**
     * We array fills up we need to double to size of stack (array).
     */
    private void double_up() {
        Node[] tmp = new Node[max_size*2];
        for (int i = 0; i < max_size; ++i) {
            tmp[i] = stack[i];
        }
        stack = tmp;
    }
    
}
