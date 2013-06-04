
package datastructures;

/**
 * Node class for path finding algorithms.
 * 
 */
public class Node {
    
    public int x;
    public int y;
    public double f_value;
    public double g_value;
    public double h_value;
    public Node previous;
    public boolean opened;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        previous = null;
        f_value = 0;
        g_value = 0;
        h_value = 0;
    }

    public Node(int x, int y, int g) {
        this.x = x;
        this.y = y;
        g_value = g;
        h_value = 0;
        f_value = 0;
    }
    

}
