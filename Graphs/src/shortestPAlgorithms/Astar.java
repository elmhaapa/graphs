/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestPAlgorithms;
import datastructures.Stack;
import datastructures.Node;
import datastructures.Priorityqueue;
import java.util.ArrayList;

/**
 * Astar shortest  path algorithm
 */
public class Astar {

    // Closed set
    private boolean closed_set[][];
    
    private Node target;
    
    // Open set
    private Priorityqueue open_set;
    
    /**
     * Gets shortest path between two points using a* shortest path algorithm.
     * @param size size of the map (size * size = number of nodes)
     * @param s_x Starting point x coordinate.
     * @param s_y Starting point y coordinate.
     * @param t_x Target point x coordinate.
     * @param t_y Target point y coordinate.
     * @return Return collection of Point objects which is route from start to target.
     */
    public Stack get_shortest_path(int size, int s_x, int s_y, int t_x, int t_y) {
        target = new Node(t_x, t_y);
        Node start = new Node(s_x, s_y, 0);

        open_set = new Priorityqueue(size*size);
        closed_set = new boolean[size][size];


        open_set.insert(start);

        while ( !open_set.isEmpty() ) {
            
            Node current = open_set.removemin();

            if (current.x == target.x && current.y == target.y) {
                target = current;
                break;
            }
            
            Stack neigbours = get_neighbours(current);
            while ( !neigbours.is_empty() ) {
                Node n = neigbours.pop();
                n.previous = current;
                closed_set[n.x][n.y] = true;
                n.g_value = current.g_value + 1;
                n.f_value = n.g_value + manhattan_h(n.x, n.x, target.x, target.y);
                open_set.insert(n);
            }

        }
        
        // Let's collect the route to a stack and return it
        Stack s = new Stack(size*size);
        
        while (target.previous != null) {
            s.push(new Node(target.x, target.y));
            target = target.previous;
        }
        
        return s;
    }
    /**
     * Counts manhattan heuristic for astar.
     */
    private int manhattan_h(int x, int y, int tx, int ty) {
        int dx = Math.abs(x - tx);
        int dy = Math.abs(y - ty);
        return 1 * (dx + dy);
    }

    // OMA TIETORAKENNE TÄHÄN
    private Stack get_neighbours(Node n) {
        Stack ret = new Stack(4);
        if (check_not_visited(n.x + 1, n.y)) {
            ret.push( new Node(n.x + 1, n.y) );
        }
        if (check_not_visited(n.x, n.y + 1)) {
            ret.push( new Node(n.x, n.y + 1) );
        }
        if (check_not_visited(n.x - 1, n.y)) {
            ret.push( new Node(n.x - 1, n.y) );
        }
        if (check_not_visited(n.x, n.y - 1)) {
            ret.push( new Node(n.x, n.y - 1) );
        }
        return ret;

    }
    
    /**
     * Checks if coordinates are in bounds of grid and if node is visited.
     * Returns true if in bounds and not visited.
     */
    private boolean check_not_visited(int x, int y) {
        if (x >= 0 && y >= 0 && x < closed_set.length && y < closed_set.length && !closed_set[x][y]) {
            return true;
        }
        return false;
    }
}
