/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.ArrayList;
import java.util.Stack;
/**
 * Astar shortest  path algorithm
 */
public class Astar {

    // Closed set
    private boolean grid[][];
    
    private Node target;
    
    // Open set
    private Priorityqueue q;
    
    /**
     * Gets shortest path between two points using a* shortest path algorithm.
     * @param size size of the map (square root atm) FIX THIS!
     * @param s_x Starting point x coordinate.
     * @param s_y Starting point y coordinate.
     * @param t_x Target point x coordinate.
     * @param t_y Target point y coordinate.
     * @return Return collection of Point objects which is route from start to target.
     */
    public Stack<Point> get_shortest_path(int size, int s_x, int s_y, int t_x, int t_y) {
        target = new Node(t_x, t_y);
        Node start = new Node(s_x, s_y);

        q = new Priorityqueue(size*size);
        grid = new boolean[size][size];


        q.insert(start);

        while ( !q.isEmpty() ) {
            
            Node current = q.removemin();

            if (current.x == target.x && current.y == target.y) {
                target = current;
                break;
            }
            
            for (Node n : get_neighbours(current)) {
                n.previous = current;
                grid[n.x][n.y] = true;
                n.f_value = manhattan_h(n.x, n.x, target.x, target.y);
                q.insert(n);
            }

        }
        
        // Let's collect the route to a stack and return it
        Stack<Point> s = new Stack<Point>();
        
        while (target.previous != null) {
            s.add(new Point(target.x, target.y));
            target = target.previous;
        }
        
        return s;
    }
    /*
     * Counts manhattan heuristic for astar.
     */
    private int manhattan_h(int x, int y, int tx, int ty) {
        int dx = Math.abs(x - tx);
        int dy = Math.abs(y - ty);
        return 1 * (dx + dy);
    }

    private ArrayList<Node> get_neighbours(Node n) {
        ArrayList<Node> ret = new ArrayList<Node>();
        if (check_not_visited(n.x + 1, n.y)) {
            ret.add(new Node(n.x + 1, n.y));
        }
        if (check_not_visited(n.x, n.y + 1)) {
            ret.add(new Node(n.x, n.y + 1));
        }
        if (check_not_visited(n.x - 1, n.y)) {
            ret.add(new Node(n.x - 1, n.y));
        }
        if (check_not_visited(n.x, n.y - 1)) {
            ret.add(new Node(n.x, n.y - 1));
        }
        return ret;

    }
    
    /*
     * Checks if coordinates are in bounds of grid and if node is visited.
     * Returns true if in bounds and not visited.
     */
    private boolean check_not_visited(int x, int y) {
        if (x >= 0 && y >= 0 && x < grid.length && y < grid.length && !grid[x][y]) {
            return true;
        }
        return false;
    }
}
