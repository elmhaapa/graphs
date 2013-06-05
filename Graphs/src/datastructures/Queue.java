
package datastructures;

/**
 * Queue for integer tables. 
 * 
 */
public class Queue {
    private int[][] Q;
    private int head;
    private int tail;
    
    public Queue(int max_size) {
      Q = new int[max_size][2];
      head = 0;
      tail = 0;
    }
    
    /**
     * Enqueue integer array.
     * @param n new element (integer array).
     */
    public void enqueue(int[] n) {
        Q[tail] = n;
        if (tail == Q.length-1) {
            tail = 0;
        } else {
            tail++;
        }
    }
    /**
     * Returns true if queue empty
     * @return boolean
     */
    public boolean is_empty() {
        return head == tail;
    }
    /**
     * Returns the size of queue
     * @return size
     */
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
    /**
     * Get first element.
     * @return first element.
     */
    public int[] dequeue() {
        int[] n = Q[head];
        if (head == Q.length-1) {
            head = 0;
        } else {
            head++;
        }
        return n;
    }
    /**
     * If queue fills up we double the size.
     */
    private void double_up() {
        int[][] tmp = new int[Q.length*2][2];
        for (int i = 0; i < Q.length; ++i) {
            tmp[i] = Q[i];
        }
        Q = tmp;
    }
    
}
