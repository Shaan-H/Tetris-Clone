/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class TetrisISU extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("TetrisBoard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        TetrisISU ti = new TetrisISU();
        frame.add(ti);
        frame.setVisible(true);
    }
    
}
