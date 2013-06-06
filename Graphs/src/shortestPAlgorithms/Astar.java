/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestPAlgorithms;

import datastructures.Stack;
import datastructures.Node;
import datastructures.Priorityqueue;

/**
 * A* shortest path algorithm
 */
public class Astar {

    private boolean closed_set[][];
    private Node target;
    private Priorityqueue open_set;
    private int size;
    private Node[][] map;
    private int[][] grid;
    private int nv;

    /**
     * Gets shortest path between two points using a* shortest path algorithm.
     *
     * @param size size of the map (size * size = number of nodes)
     * @param s_x Starting point x coordinate.
     * @param s_y Starting point y coordinate.
     * @param t_x Target point x coordinate.
     * @param t_y Target point y coordinate.
     * @return Return stack of Node objects which is route from start to target.
     */
    public Stack get_shortest_path(int[][] grid, int s_x, int s_y, int t_x, int t_y) {
        size = grid.length;
        this.grid = grid;

        map = new Node[size][size];

        target = new Node(t_x, t_y);

        Node start = new Node(s_x, s_y, 0);
        map[s_x][s_y] = start;
        map[t_x][t_y] = target;
        start.f_value = 0;


        open_set = new Priorityqueue(size * size);
        closed_set = new boolean[size][size];


        open_set.insert(start);
        start.opened = true;

        while (!open_set.is_empty()) {

            Node current = open_set.removemin();
            nv++;
            // Node with smallest f_value.
            closed_set[current.x][current.y] = true;


            if (current.x == target.x && current.y == target.y) {
                target = current;
                break;
            }

            Stack neighbours = get_neighbours(current);
            evaluate_neighbours(neighbours, current);


        }

        // Next we backtrack the route.
        Stack s = new Stack(size * size);

        while (target.previous != null) {
            s.push(new Node(target.x, target.y));
            target = target.previous;
        }
//        System.out.println("a* nv: " + nv);
        return s;
    }

    /**
     * We take stack of neighbours of current node and the node itself and
     * evaluate them and add them to priority queue.
     * @param neighbours Stack of neighbours of current node.
     * @param current node.
     */
    private void evaluate_neighbours(Stack neighbours, Node current) {
        while (!neighbours.is_empty()) {
            Node n = neighbours.pop();

            if (closed_set[n.x][n.y]) {
                continue;
            }
            double cost = current.g_value + 1;
            if (n.y != current.y && n.x != current.x) {
                // it's diagonal step so the cost is sqrt(2)
                cost = current.g_value + Math.sqrt(2);
            }

            if (!n.opened || cost < n.g_value) {
                // If node is either not in priorityqueue yet or it's g_value should be updated.
                if (!n.opened) {
                    n.previous = current;
                    n.g_value = cost;
                    n.f_value = 2 * cost + manhattan_h(n.x, n.y, target.x, target.y);
                    open_set.insert(n);
                    n.opened = true;
                } else {
                    n = open_set.remove_middle(n);
                    n.previous = current;
                    n.g_value = cost;
                    n.f_value = 2 * cost + manhattan_h(n.x, n.y, target.x, target.y);
                    open_set.insert(n);
                }
            }
        }
    }

    /**
     * Counts Manhattan heuristic for a*.
     */
    private int manhattan_h(int x, int y, int tx, int ty) {
        int dx = Math.abs(x - tx);
        int dy = Math.abs(y - ty);
        return  (dx + dy);
    }

    /**
     * Returns all neighbours for node N
     *
     * @param n node for returning neighbours
     * @return returns stack of neighbours.
     */
    private Stack get_neighbours(Node n) {
        Stack ret = new Stack(8);
        if (check_is_node(n.x + 1, n.y)) {
            create_n_stack__nodes(n.x + 1, n.y, ret);
        }
        if (check_is_node(n.x, n.y + 1)) {
            create_n_stack__nodes(n.x, n.y + 1, ret);
        }
        if (check_is_node(n.x - 1, n.y)) {
            create_n_stack__nodes(n.x - 1, n.y, ret);
        }
        if (check_is_node(n.x, n.y - 1)) {
            create_n_stack__nodes(n.x, n.y - 1, ret);
        }

        if (check_is_node(n.x - 1, n.y - 1)) {
            create_n_stack__nodes(n.x - 1, n.y - 1, ret);
        }
        if (check_is_node(n.x - 1, n.y + 1)) {
            create_n_stack__nodes(n.x - 1, n.y + 1, ret);
        }
        if (check_is_node(n.x + 1, n.y - 1)) {
            create_n_stack__nodes(n.x + 1, n.y - 1, ret);
        }
        if (check_is_node(n.x + 1, n.y + 1)) {
            create_n_stack__nodes(n.x + 1, n.y + 1, ret);
        }
        return ret;

    }

    /**
     * If node we need is in map already push it stack. If not create it and
     * push stack.
     *
     * @param x coordinate of node
     * @param y coordinate of node
     * @param s stack of neighbours.
     */
    private void create_n_stack__nodes(int x, int y, Stack s) {
        if (map[x][y] == null) {
            map[x][y] = new Node(x, y);
        }
        s.push(map[x][y]);
    }

    /**
     * Checks if coordinates are in bounds of grid and is traversable node.
     * Returns true if in bounds.
     */
    private boolean check_is_node(int x, int y) {
        if (x >= 0 && y >= 0 && x < closed_set.length && y < closed_set.length && grid[x][y] != 9) {
            return true;
        }
        return false;
    }
}
