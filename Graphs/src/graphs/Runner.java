/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;


public class Runner {

    private int[][] graph;
    static private Point target;


    public Runner(int size) {
        graph = new int[size][size];
        target = new Point(0, 0);
    }

    public void update_target() {
        int x = target.x;
        int y = target.y;
        int l = graph.length-1;
        if (y < l && x == 0) {
            target.y++;
        } else if (y == l && x < l) {
            target.x++;
        } else if (x == l && y > 0) {
            target.y--;
        } else if (y == 0 && x > 0) {
            target.x--;
        }
    }
    public Point get_target() {
        return target;
    }

}