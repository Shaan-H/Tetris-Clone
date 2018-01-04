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
        System.out.println("WhatEver");
        setSize(325,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout(0,0,0);
        setLayout(layout);
        
        GridLayout layout1 = new GridLayout(20,10,0,0);
        BoardArea.setLayout(layout1);
        for(int x=0;x<20;x++){
            for(int y=0;y<10;y++){
                BoardArray[x][y] = new JPanel();
                BoardArray[x][y].setBackground(Color.black);
                BoardArray[x][y].setSize(25,25);
                BoardArray[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
                BoardArea.add(BoardArray[x][y]);
            }
        }
        
        add(BoardArea);
        BoardArea.setSize(250,500);
        add(SideBoard);
        SideBoard.setBackground(Color.gray);
        SideBoard.setSize(75,500);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        TetrisISU frame = new TetrisISU();
        System.out.println("hello");
        //runs the layout class
    }
        
    
}
