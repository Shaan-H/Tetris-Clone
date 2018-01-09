/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class TetrisISU extends JFrame{
    public JPanel BoardArea = new JPanel();
    public JPanel SideBoard = new JPanel();
    public JPanel[][] BoardArray = new JPanel[10][20];
    public JPanel[][] PreviewArea = new JPanel[5][5];
    public JLabel StatTitle = new JLabel("Stats");
    public JLabel ScoreDisplay = new JLabel("Score = 0");
    public JLabel LevelDisplay = new JLabel("0");
    public JButton Test = new JButton("TEEST");
    public static int level = 0;
    public int score = 0;
    public int linesCompleted = 0;
    public Point piecePosition;
    public int rotation;
    public static int currentpiece, nextpiece;
    
    public static void main(String[] args) {
        TetrisISU frame = new TetrisISU();
        //runs the layout class
        new Thread(){
            @Override public void run(){
                while(true){
                    try{
                        Thread.sleep(1000-50*(level-1)); //This value can be modified to make the game easier or harder.
                           frame.moveDown();
                    } catch(InterruptedException e){}
                }
            }
	}.start();
        Random rand = new Random();
        int a = rand.nextInt(6) + 0;
        nextpiece = a;
    }
    
    public TetrisISU(){
        setSize(new Dimension(441,538));
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
                BoardArray[y][x] = new JPanel();
                //creates a new jpanel
                BoardArray[y][x].setBackground(Color.black);
                //sets the background of the jpanels to black
                BoardArray[y][x].setPreferredSize(new Dimension (50,50));
                //sets a preffered size for each of the panels
                BoardArray[y][x].setMaximumSize(new Dimension (50,50));
                //sets a maximum size
                BoardArray[y][x].setMinimumSize(new Dimension (50,50));
                //sets a minimum size
                BoardArray[y][x].setBorder(BorderFactory.createLineBorder(Color.gray));
                //makes a grey border around the panel
                BoardArea.add(BoardArray[y][x]);
                //adds the jpanel to the board area
            }
        }
        //iterates through all 200 jpanels setting them all to look the same and adding them to the board area
        add(BoardArea);
        //
        
        BoardArea.setPreferredSize(new Dimension(500,1000));
        System.out.println(BoardArea.getPreferredSize());
        add(SideBoard);
        SideBoard.setBackground(Color.gray);
        SideBoard.setPreferredSize(new Dimension (250,1000));
        SideBoard.add(Test);
        Test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });
        System.out.println(this.getPreferredSize());
        setVisible(true);
        //setResizable(false);
        setLocationRelativeTo(null);
        //centers the window
        start();
        //initilizes the game by calling the start method
    }
    
    private void start(){
        newPiece();
        
        
    }
    
    private void newPiece(){
        piecePosition = new Point(5,0);
        rotation = 0;
        Random rand = new Random();
        int b = rand.nextInt(6) + 0;
        currentpiece = nextpiece;
        nextpiece = b;
        testmove();
    }
    
    public void moveLR(int x){
        if(!collision(piecePosition.x + x,piecePosition.y,rotation)){
            piecePosition.x += x;
        }
        repaint();
    }
    
    public void moveDown(){
        if(!collision(piecePosition.x,piecePosition.y+1, rotation)){
            piecePosition.y+=1;
        } else{
            PinToBoard();
        }
        repaint();
        testmove();
    }
    
    public void testmove(){
        BoardArray[piecePosition.x][piecePosition.y].setBackground(Color.yellow);
        System.out.println(piecePosition.y);
    }
    
    public boolean collision(int x, int y, int rotationPos){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentpiece][rotation]){
			if(BoardArray[p.x + x][p.y + y].getBackground() != Color.BLACK || p.x>10 || p.y>10){
                            
                            return true;
                        }
                        
                System.out.println(p.y+y);        
		}
        
        return false;
    }
    
    public void PinToBoard(){
        
        newPiece();
    }
    
    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());
        System.out.println(SideBoard.getHeight());
        System.out.println(SideBoard.getWidth());
        System.out.println(BoardArea.getHeight());
        System.out.println(BoardArea.getWidth());
        System.out.println(BoardArray[1][2].getHeight());
        System.out.println(BoardArray[1][2].getWidth());
    } 
    
        
    
}
