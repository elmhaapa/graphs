/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * For generating simple gui to see what's happening.
 */
public class Window {

    private int grid_size;
    private Dimension box_size = new Dimension(15, 15);
    private JPanel[][] grid;
    private int[][] map;
    private Color[] colors =  { Color.darkGray, Color.red, Color.ORANGE, Color.CYAN };

    public Window(int[][] map) {
        this.grid_size = map.length;
        grid = new JPanel[grid_size][grid_size];
        this.map = map;
        JFrame frame = new JFrame("Graphs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(create_content());
        frame.pack();
        frame.setVisible(true);       
    }

    private JPanel create_content() {
        JPanel totalGUI = new JPanel();
        JPanel main_panel = new JPanel(new GridLayout(grid_size, grid_size, 4, 4));
        add_boxes(main_panel);
        totalGUI.add(main_panel);
        return totalGUI;
        
    }

    private void add_boxes(JPanel m) {
        for (int i = 0; i < grid_size; ++i) {
            for (int j = 0; j < grid_size; ++j) {            
                grid[i][j] = new JPanel();
                if (map[i][j] == 9) {
                    grid[i][j].setBackground(Color.white);
                } else {
                    grid[i][j].setBackground(Color.darkGray);
                }
                grid[i][j].setMinimumSize(box_size);
                grid[i][j].setMaximumSize(box_size);
                grid[i][j].setPreferredSize(box_size);
                
                m.add(grid[i][j]);
            }
        }
    }
    public void change_color(int x, int y, int c) {
        grid[x][y].setBackground(colors[c]);
    }

    
}
