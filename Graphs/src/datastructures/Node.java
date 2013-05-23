
package datastructures;

/**
 * Node class for path finding algorithms.
 * 
 */
public class Node {
    
    public int x;
    public int y;
    public int f_value;
    public int g_value;
    public Node previous;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        previous = null;
        f_value = 0;
    }

    public Node(int x, int y, int g) {
        this.x = x;
        this.y = y;
        g_value = g;
    }
    

}
