
package graphs;

/*
 * Node class for path finding algorithms.
 * way too many constructors atm.
 */
public class Node {
    
    public int x;
    public int y;
    public int f_value;
    public Node previous;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        previous = null;
        f_value = 0;
    }
    public Node(int x, int y, Node p) {
        this.x = x;
        this.y = y;
        previous = p;
        f_value = 0;
    }
    public Node(int x, int y, int f) {
        this.x = x;
        this.y = y;
        f_value = f;
    }
    public Node(int x, int y, Node p, int f) {
        this.x = x;
        this.y = y;
        previous = p;
        f_value = f;
    }
}
