/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestPAlgorithms;

import datastructures.Priorityqueue;
import datastructures.Node;
import datastructures.Stack;

/**
 * Jump point search for finding shortest path between two points.
 *
 */
public class JumpPointSearch {

    private boolean[][] closed_set;
    private Priorityqueue open_set;
    private Node start;
    private Node target;

    public JumpPointSearch(int size) {
        closed_set = new boolean[size][size];
        open_set = new Priorityqueue(size * size);

    }
/**
 * Gets shortest path between two points. atm does nothing.
 * @param s_x starting point's x coordinate.
 * @param s_y starting point's y coordinate.
 * @param t_x target point's x 
 * @param t_y target point's y 
 * @return return a stack of nodes which is route from start to target. first 
 * step at the top.
 */
    public Stack get_shortest_path(int s_x, int s_y, int t_x, int t_y) {
        start = new Node(s_x, s_y, 0);
        target = new Node(t_x, t_y);
        open_set.insert(start);

        while (!open_set.is_empty()) {
            Node current = open_set.removemin();
            
            Stack neighbours = get_neighbours(current);
            while (!neighbours.is_empty()) {
                Node n = neighbours.pop();
                // TÄHÄN Node jumped_node = jump(n);
            }
        }
        
        
        return new Stack(3);
    }

    
    // FIXAA NÄÄ !
    private Stack get_neighbours(Node n) {
        Stack ret = new Stack(4);
        if (check_not_visited(n.x + 1, n.y)) {
            ret.push(new Node(n.x + 1, n.y));
        }
        if (check_not_visited(n.x, n.y + 1)) {
            ret.push(new Node(n.x, n.y + 1));
        }
        if (check_not_visited(n.x - 1, n.y)) {
            ret.push(new Node(n.x - 1, n.y));
        }
        if (check_not_visited(n.x, n.y - 1)) {
            ret.push(new Node(n.x, n.y - 1));
        }
        return ret;

    }

    private boolean check_not_visited(int x, int y) {
        if (x >= 0 && y >= 0 && x < closed_set.length && y < closed_set.length && !closed_set[x][y]) {
            return true;
        }
        return false;
    }
}
