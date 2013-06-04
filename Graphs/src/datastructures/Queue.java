
package datastructures;

/**
 *
 * 
 */
public class Queue {
    private Node[] Q;
    private int head;
    private int tail;
    
    public Queue(int max_size) {
      Q = new Node[max_size];
      head = 0;
      tail = 0;
    }
    
    public void enqueue(Node n) {
        Q[tail] = n;
        if (tail == Q.length-1) {
            tail = 0;
        } else {
            tail++;
        }
    }
    public boolean is_empty() {
        return head == tail;
    }
    public int size() {
        int i = head;
        while (true) {
            if (i == tail) {
                return i;
            }
            i++;
            if (i == Q.length) {
                i = 0;
            }
        }
    }
    public Node dequeue() {
        Node n = Q[head];
        if (head == Q.length-1) {
            head = 0;
        } else {
            head++;
        }
        return n;
    }
    private void double_up() {
        Node[] tmp = new Node[Q.length*2];
        for (int i = 0; i < Q.length; ++i) {
            tmp[i] = Q[i];
        }
        Q = tmp;
    }
    
}
