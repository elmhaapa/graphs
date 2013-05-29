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
    private Node[][] map;
    private int[][] grid;

    /**
     * Initialize nodes in map. We are supposed to not need this in the end.
     */
    private void initialize_map() {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map.length; ++j) {
                map[i][j] = new Node(i, j);
                // map[i][j].g_value = euclidean(i, j, start.x, start.y);
            }
        }
    }

    /**
     * Gets shortest path between two points. (atm doesn't find the optimal)
     *
     * @param s_x starting point's x coordinate.
     * @param s_y starting point's y coordinate.
     * @param t_x target point's x
     * @param t_y target point's y
     * @return return a stack of nodes which is route from start to target.
     * first step at the top.
     */
    public Stack get_shortest_path(int s_x, int s_y, int t_x, int t_y, int[][] grid) {
        this.grid = grid;
        int size = grid.length;
        start = new Node(s_x, s_y, 0);
        map = new Node[size][size];
        initialize_map();
        open_set = new Priorityqueue(size * size);
        closed_set = new boolean[size][size];

        target = new Node(t_x, t_y);

        open_set.insert(start);
        start.opened = true;

        while (!open_set.is_empty()) {
            Node current = open_set.removemin();
            closed_set[current.x][current.y] = true;

            if (current.x == target.x && current.y == target.y) {
                target = current;
                break;
            }

            Stack neighbours = jump_neigbours(current);
            while (!neighbours.is_empty()) {
                Node n = neighbours.pop();
                Node jumped_node = jump(current.x, current.y, n.x, n.y);

                if (jumped_node != null) {

                    if (closed_set[jumped_node.x][jumped_node.y]) {
                        continue;
                    }
                    double h = euclidean(jumped_node.x, jumped_node.y, target.x, target.y);
                    double cost = current.g_value + euclidean(jumped_node.x, jumped_node.y, current.x, current.y);

                    if (!jumped_node.opened || cost < jumped_node.g_value) {
                        jumped_node.f_value = 2 * h + cost;
                        jumped_node.g_value = cost;
                        jumped_node.previous = current;

                        if (!jumped_node.opened) {
                            open_set.insert(jumped_node);
                            jumped_node.opened = true;
                        } else {
                            Node a = open_set.remove_middle(jumped_node);
                            open_set.insert(jumped_node);

                        }
                    }

                }
            }
        }

        // let's backtrace it:
        Stack s = new Stack(size * size);
        while (target.previous != null) {
            s.push(target);
            target = target.previous;
        }



        return s;
    }

    /**
     * Counts euclidean distance for heuristic
     *
     * @param x start x coordinate
     * @param y start y coordinate
     * @param tx target x coordinate
     * @param ty target y coordinate
     * @return returns distance
     */
    private double euclidean(int x, int y, int tx, int ty) {
        int dx = Math.abs(x - tx);
        int dy = Math.abs(y - ty);
        return 1 * Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Recursive function to find a jump node.
     *
     * @param cx this is current x coordinate
     * @param cy this is current y coordinate
     * @param nx this is next node x coordinate
     * @param ny this is next node y coordinate
     * @return Returns either jump node if found one or null.
     */
    private Node jump(int cx, int cy, int nx, int ny) {
        int dx = nx - cx;
        int dy = ny - cy;
        // We get the direction of the jump

        if (!check_is_node(nx, ny)) {
            return null;
        } else if (nx == target.x && ny == target.y) {
            return map[nx][ny];
        }

        // Check diagonal
        if (dx != 0 && dy != 0) {
            if (check_is_node(nx - dx, ny + dy) && !check_is_node(nx - dx, ny)
                    || check_is_node(nx + dx, ny - dy) && !check_is_node(nx, ny - dy)) {
                return map[nx][ny];
            }
        } else { // Horizontal / vertical
            if (dx != 0) {
                if (check_is_node(nx + dx, ny + 1) && !check_is_node(nx, ny + 1)
                        || check_is_node(nx + dx, ny - 1) && !check_is_node(nx, ny - 1)) {
                    return map[nx][ny];
                }
            } else {
                if (check_is_node(nx + 1, ny + dy) && !check_is_node(nx + 1, ny)
                        || check_is_node(nx - 1, ny + dy) && !check_is_node(nx - 1, ny)) {
                    return map[nx][ny];
                }
            }
        }

        // Moving diagonally
        if (dx != 0 && dy != 0) {
            Node a = jump(nx, ny, nx + dx, ny);
            Node b = jump(nx, ny, nx, ny + dy);
            if (a != null) {
                return a;
            } else if (b != null) {
                return b;
            }
        }

        // Movin vertical/horizontal
        if (check_is_node(nx + dx, ny) || check_is_node(nx, ny + dy)) {
            return jump(nx, ny, nx + dx, ny + dy);
        } else {
            return null;
        }


    }

    /**
     * This is supposed to be a function for finding neighbours but is mess atm.
     * skip this if reading.
     *
     * @param n node which neigbours we are looking for
     * @return stack of neighbours
     */
    private Stack jump_neigbours(Node n) {
        int x = n.x;
        int y = n.y;

        Stack ret = new Stack(10);

        if (n.previous != null) {
            int px = n.previous.x;
            int py = n.previous.y;
            int dx = (x - px) / Math.max(Math.abs(x - px), 1);
            int dy = (y - py) / Math.max(Math.abs(y - py), 1);

            // diagonally         
            if (dx != 0 && dy != 0) {
                if (check_is_node(x, y + dy)) {
                    create_n_stack__nodes(x, y + dy, ret);
                }
                if (check_is_node(x + dx, y)) {
                    create_n_stack__nodes(x + dx, y, ret);
                }
                if (check_is_node(x, y + dy) || check_is_node(x + dx, y)) {
                    create_n_stack__nodes(x + dx, y + dy, ret);
                }
                if (check_is_node(x - dx, y) && check_is_node(x, y + dy)) {
                    create_n_stack__nodes(x - dx, y + dy, ret);
                }
                if (check_is_node(x, y - dy) && check_is_node(x + dx, y)) {
                    create_n_stack__nodes(x + dx, y - dy, ret);
                }

            } else {
                if (dx == 0) {
                    if (check_is_node(x, y + dy)) {
                        if (check_is_node(x, y + dy)) {
                            create_n_stack__nodes(x, y + dy, ret);
                        }
                        if (!check_is_node(x + 1, y)) {
                            create_n_stack__nodes(x + 1, y + dy, ret);
                        }
                        if (!check_is_node(x - 1, y)) {
                            create_n_stack__nodes(x - 1, y + dy, ret);
                        }
                    }
                } else {
                    if (check_is_node(x + dx, y)) {
                        if (check_is_node(x + dx, y)) {
                            create_n_stack__nodes(x + dx, y, ret);
                        }
                        if (!check_is_node(x, y + 1)) {
                            create_n_stack__nodes(x + dx, y + 1, ret);
                        }
                        if (!check_is_node(x, y - 1)) {
                            create_n_stack__nodes(x + dx, y - 1, ret);
                        }
                    }
                }
            }

        } else {
            return get_neighbours(n);
        }
        return ret;

    }

    /**
     * Finds neigbours for node N
     *
     * @param n node to find neighbours
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
        /*
         * Above are vertical and horizontal nodes.
         * Below diagonal.
         */
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
     * We add given node from map to stack to be returned as neighbour. If node
     * is not in the map yet we create a new one.
     *
     * @param x coordinate of node
     * @param y coordinate of node
     * @param s stack for adding nodes.
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
