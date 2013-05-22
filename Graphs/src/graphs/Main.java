
package graphs;

import gui.Window;
import shortestPAlgorithms.Astar;
import datastructures.Point;
import java.util.Stack;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        /*
         * Some test to check how everything works.
         */
        int size_of_map = 10;
        Window a = new Window(size_of_map);
        Runner v = new Runner(size_of_map);
        Astar tahti = new Astar();
        int tahti_x = size_of_map-1;
        int tahti_y = size_of_map/2;
        a.change_color(tahti_x, tahti_y, 2);
        
        Stack<Point> s = tahti.get_shortest_path(size_of_map, tahti_x, tahti_y, 4, 3);
            
            while ( !s.isEmpty() ) {
                Point p = s.pop();
                a.change_color(p.x, p.y, 2);
            }
        /*
        while (true) {
            int t_x = v.get_target().x;
            int t_y = v.get_target().y;
            v.update_target();
            Point tn = v.get_target();
            a.change_color(t_x, t_y, 0);
            a.change_color(tn.x, tn.y, 1);
                      
            Thread.sleep(200);
        }
        */

    }
}
