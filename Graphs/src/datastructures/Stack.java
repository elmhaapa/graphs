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
    
    public void push(Node n) {
        size++;
        if (size > max_size-1) {
            double_up();
        }
        stack[size] = n;
    }
    
    public Node pop() {
        size--;
        return stack[size+1];
    }
    
    private void double_up() {
        Node[] tmp = new Node[max_size*2];
        for (int i = 0; i < max_size; ++i) {
            tmp[i] = stack[i];
        }
        stack = tmp;
    }
    
}
