/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class TetrisISU extends JFrame{
    public JPanel BoardArea = new JPanel();
    public JPanel SideBoard = new JPanel();
    public JPanel[][] BoardArray = new JPanel[20][10];
    public JPanel[][] PreviewArea = new JPanel[5][5];
    public JLabel StatTitle = new JLabel("Stats");
    public JLabel ScoreDisplay = new JLabel("Score = 0");
    public JLabel LevelDisplay = new JLabel("0");
    public int level = 0;
    public int score = 0;
    
    public TetrisISU(){
        setSize(new Dimension(450,550));
        //sets the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets the operation of when the user hits the x button
        FlowLayout layout = new FlowLayout(0,0,0);
        //makes a new layout to display elements in a row
        setLayout(layout);
        //sets the layout to the window
        
        GridLayout layout1 = new GridLayout(20,10,0,0);
        //makes a new gridlayout with 20 rows and 10 columns
        BoardArea.setLayout(layout1);
        //sets the layout to the tiles
        for(int x=0;x<20;x++){
            for(int y=0;y<10;y++){
                BoardArray[x][y] = new JPanel();
                //creates a new jpanel
                BoardArray[x][y].setBackground(Color.black);
                //sets the background of the jpanels to black
                BoardArray[x][y].setPreferredSize(new Dimension (25,25));
                //sets a preffered size for each of the panels
                BoardArray[x][y].setMaximumSize(new Dimension (25,25));
                //sets a maximum size
                BoardArray[x][y].setMinimumSize(new Dimension (25,25));
                //sets a minimum size
                BoardArray[x][y].setBorder(BorderFactory.createLineBorder(Color.gray));
                //makes a grey border around the panel
                BoardArea.add(BoardArray[x][y]);
                //adds the jpanel to the board area
            }
        }
        //iterates through all 200 jpanels setting them all to look the same and adding them to the board area
        
        add(BoardArea);
        //
        BoardArea.setPreferredSize(new Dimension(250,500));
        add(SideBoard);
        SideBoard.setBackground(Color.gray);
        SideBoard.setPreferredSize(new Dimension (175,500));
        System.out.println(BoardArray[5][5].getSize());
        setVisible(true);
    }
    
    public static void main(String[] args) {
        TetrisISU frame = new TetrisISU();
        System.out.println("hello");
        //runs the layout class
    }
        
    
}
