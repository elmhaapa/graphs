/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestPAlgorithms;

import datastructures.Priorityqueue;
import datastructures.Node;
import datastructures.Stack;
import datastructures.Queue;

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

 
    /*
    private void initialize_map() {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map.length; ++j) {
                map[i][j] = new Node(i, j);
                // map[i][j].g_value = euclidean(i, j, start.x, start.y);
            }
        }
    }
    */

    /**
     * Shortest path between two points.
     *
     * @param s_x start x coordinate
     * @param s_y start y coordinate
     * @param t_x target x coordinate
     * @param t_y target y coordinate
     * @param grid integer array representation of map. 0 is a node.
     * @return Stack of nodes representing optimal path. Top node first step.
     */
    public Stack get_shortest_path(int s_x, int s_y, int t_x, int t_y, int[][] grid) {
        this.grid = grid;
        map = new Node[grid.length][grid.length];
      //  initialize_map();
        int size = grid.length;
        open_set = new Priorityqueue(size * size);
        closed_set = new boolean[size][size];

        map[s_x][s_y] = new Node(s_x,s_y);
        map[t_x][t_y] = new Node(t_x,t_y);
        start = map[s_x][s_y];
        target = map[t_x][t_y];


        open_set.insert(start);
        start.opened = true;

        while (!open_set.is_empty()) {
            Node node = open_set.removemin();
            closed_set[node.x][node.y] = true;

            if (node.x == target.x && node.y == target.y) {
                target = node;
                break;
            }

            identify_successor(node);
        }

        Stack s = new Stack(size * size);
        while (target.previous != null) {
            s.push(target);
            target = target.previous;
        }


        return s;
    }

    /**
     * Takes node with lowest f-value and pushes it's successors into priority
     * queue.
     *
     * @param node current node
     */
    private void identify_successor(Node node) {
        int x = node.x;
        int y = node.y;
        Queue neighbours = find_neighbours(node);


        while (!neighbours.is_empty()) {
            int[] neighbour = neighbours.dequeue();

            int[] jump_coord = jump(neighbour[0], neighbour[1], x, y);

            if (jump_coord != null && is_walkable(jump_coord[0], jump_coord[1])) {

                int jx = jump_coord[0];
                int jy = jump_coord[1];

                if (map[jx][jy] == null) {
                    map[jx][jy] = new Node(jx, jy);
                }
                Node jump_point = map[jx][jy];

                if (closed_set[jx][jy]) {
                    continue;
                }

                // distance
                double d = euclidean(Math.abs(jx - x), Math.abs(jy - y));
                double ng = node.g_value + d;

                if (!jump_point.opened || ng < jump_point.g_value) {
                    jump_point.g_value = ng;
                    jump_point.h_value = euclidean(Math.abs(jx - target.x), Math.abs(jy - target.y));
                    jump_point.f_value = jump_point.g_value + jump_point.h_value;
                    jump_point.previous = node;

                    if (!jump_point.opened) {
                        open_set.insert(jump_point);
                        jump_point.opened = true;
                    } else {
                        open_set.remove_middle(jump_point);
                        open_set.insert(jump_point);
                    }
                }
            }

        }

    }

    /**
     * Calculates Euclidean heuristic.
     *
     * @param dx
     * @param dy
     * @return
     */
    private double euclidean(int dx, int dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     *
     * @param x
     * @param y
     * @param px
     * @param py
     * @return
     */
    private int[] jump(int x, int y, int px, int py) {

        int dx = x - px;
        int dy = y - py;

        if (!is_walkable(x, y)) {
            return null;
        } else if (x == target.x && y == target.y) {
            return new int[]{x, y};
        }

        // check for forced neighbors
        // along the diagonal
        if (!(dx == 0 && dy == 0)) {
            if ((is_walkable(x - dx, y + dy) && !is_walkable(x - dx, y))
                    || (is_walkable(x + dx, y - dy) && !is_walkable(x, y - dy))) {
                return new int[]{x, y};
            }
        } else {
            // horizontal/vertical
            if (dx != 0) {
                if ((is_walkable(x + dx, y + 1) && !is_walkable(x, y + 1))
                        || (is_walkable(x + dx, y - 1) && !is_walkable(x, y - 1))) {
                    return new int[]{x, y};
                }
            } else {
                if ((is_walkable(x + 1, y + dy) && !is_walkable(x + 1, y))
                        || (is_walkable(x - 1, y + dy) && !is_walkable(x - 1, y))) {
                    return new int[]{x, y};
                }
            }
        }

        // when moving diagonal, check vertical/horizontal jump points
        if (!(dx == 0 && dy == 0)) {
            int[] jx = jump(x + dx, y, x, y);
            int[] jy = jump(x, y + dy, x, y);
            if (jx != null || jy != null) {
                return new int[]{x, y};
            }
        }
        
        if (dx == 0 && dy == 0) {
            return new int[]{x, y};
        }
        


        //  moving diagonally, must make sure one of the vert/hor is open to allow path
        if (is_walkable(x + dx, y) || is_walkable(x, y + dy)) {
            return jump(x + dx, y + dy, x, y);
        } else {
            return null;
        }

    }

    /**
     * Finds neighbours for current node. We can use pruning rules of JPS if
     * this is not start node.
     *
     * @param n current node.
     * @return Queue of nodes.
     */
    private Queue find_neighbours(Node n) {
        
        Queue neighbours = new Queue(9);
   
        int x = n.x;
        int y = n.y;
        Node parent = n.previous;

        // directed pruning can ignore neighbours unless forced.
        if (parent != null) {
            int px = parent.x;
            int py = parent.y;
            // normalized direction of travel
            int dx = (x - px) / Math.max(Math.abs(x - px), 1);
            int dy = (y - py) / Math.max(Math.abs(y - py), 1);

            // search diagonally
            if (dx != 0 && dy != 0) {
                if (is_walkable(x, y + dy)) {
                    neighbours.enqueue(new int[]{x, y + dy});
                }
                if (is_walkable(x + dx, y)) {
                    neighbours.enqueue(new int[]{x + dx, y});
                }
                if (is_walkable(x, y + dy) || is_walkable(x + dx, y)) {
                    if (is_walkable(x + dx, y + dy)) {
                        neighbours.enqueue(new int[]{x + dx, y + dy});
                    }
                }
                if (!is_walkable(x - dx, y) && is_walkable(x, y + dy)) {
                    if (is_walkable(x - dx, y + dy)) {
                        neighbours.enqueue(new int[]{x - dx, y + dy});
                    }
                }
                if (!is_walkable(x, y - dy) && is_walkable(x + dx, y)) {
                    if (is_walkable(x + dx, y - dy)) {
                        neighbours.enqueue(new int[]{x + dx, y - dy});
                    }
                }
            } else {
                // horizontal vertical
                if (dx == 0) {
                    if (is_walkable(x, y + dy)) {
                        if (is_walkable(x, y + dy)) {
                            neighbours.enqueue(new int[]{x, y + dy});
                        }
                        if (!is_walkable(x + 1, y)) {
                            if (is_walkable(x + 1, y + dy)) {
                                neighbours.enqueue(new int[]{x - 1, y + dy});
                            }
                        }
                        if (!is_walkable(x - 1, y)) {
                            if (is_walkable(x - 1, y + dy)) {
                                neighbours.enqueue(new int[]{x - 1, y + dy});
                            }
                        }
                    }
                } else {
                    if (is_walkable(x + dx, y)) {
                        if (is_walkable(x + dx, y)) {
                            neighbours.enqueue(new int[]{x + dx, y});
                        }
                        if (!is_walkable(x, y + 1)) {
                            if (is_walkable(x + dx, y + 1)) {
                                neighbours.enqueue(new int[]{x + dx, y + 1});
                            }
                        }
                        if (!is_walkable(x, y - 1)) {
                            if (is_walkable(x + dx, y - 1)) {
                                neighbours.enqueue(new int[]{x + dx, y - 1});
                            }
                        }
                    }
                }
            }
        } else {
            // There was no parent. This is start node. Return all neighbours.
            return get_neighbours(n);
        }

        return neighbours;
    }

    /**
     * Check if given coordinates are on the map and is a node.
     *
     * @param x coordinate
     * @param y coordinate
     * @return true if is walkable square (node).
     */
    private boolean is_walkable(int x, int y) {
        if (x >= 0 && y >= 0 && x < closed_set.length && y < closed_set.length && grid[x][y] != 9) {
            return true;
        }
        return false;
    }

    /**
     * Unlike find_neighbours this return all 8 neighbours if they are on the
     * map and are nodes.
     *
     * @param n current node.
     * @return Queue of all possible neighbours.
     */
    private Queue get_neighbours(Node n) {
   //     int[][] ret = new int[8][2];
        
       Queue ret = new Queue(9);
        if (is_walkable(n.x + 1, n.y)) {
            ret.enqueue(new int[]{n.x + 1, n.y});
        }
        if (is_walkable(n.x, n.y + 1)) {
            ret.enqueue(new int[]{n.x, n.y + 1});
        }
        if (is_walkable(n.x - 1, n.y)) {
            ret.enqueue(new int[]{n.x - 1, n.y});
        }
        if (is_walkable(n.x, n.y - 1)) {
            ret.enqueue(new int[]{n.x, n.y - 1});
        }
        /*
         * Above are vertical and horizontal nodes.
         * Below diagonal.
         */
        if (is_walkable(n.x - 1, n.y - 1)) {
            ret.enqueue(new int[]{n.x - 1, n.y - 1});
        }
        if (is_walkable(n.x - 1, n.y + 1)) {
            ret.enqueue(new int[]{n.x - 1, n.y + 1});
        }
        if (is_walkable(n.x + 1, n.y - 1)) {
            ret.enqueue(new int[]{n.x + 1, n.y - 1});
        }
        if (is_walkable(n.x + 1, n.y + 1)) {
            ret.enqueue(new int[]{n.x + 1, n.y + 1});
        }
        return ret;

    }
}
