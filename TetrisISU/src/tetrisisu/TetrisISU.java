/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import static javax.swing.BorderFactory.createRaisedBevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;

/**
 *
 * @author Admin
 */
public class TetrisISU extends JFrame implements KeyListener{
    public JPanel BoardArea = new JPanel();
    public JPanel SideBoard = new JPanel();
    public JPanel[][] BoardArray = new JPanel[10][20];
    public JPanel[][] PreviewArea = new JPanel[5][5];
    public JLabel StatTitle = new JLabel("Stats");
    public int score = 0;
    public JLabel ScoreDisplay = new JLabel("Score = " + score);
    public static int level = 0;
    public JLabel LevelDisplay = new JLabel("Level: " + level);
    
    public int linesCompleted = 0;
    public Point piecePosition;
    public int rotation;
    public int totalclearline;
    public static int currentPiece, nextpiece;
    public static boolean gameRunning = true;
    
    public static void main(String[] args) {
        TetrisISU frame = new TetrisISU();
        //runs the layout class
        new Thread(){
            @Override public void run(){
                while(gameRunning){
                    try{
                        Thread.sleep(1000-10*((level-1))); 
                        //The number of milliseconds that it takes for one game cycle
                        //goes down as the level increases
                           frame.moveDown();
                           //calls a function from the instace of the game that was created
                    } catch(InterruptedException e){}
                    //prevents an interruptedException error from showing up
                }
            }
	}.start();
        //runs the program
        
    }
    
    public TetrisISU(){
    //sets the look and adds the elements to the window
        setSize(new Dimension(766,1039));
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
        //adds the boardarea to the window
        
        BoardArea.setPreferredSize(new Dimension(500,1000));
        //sets the preffered size of the board area to 500 by 1000
        System.out.println(BoardArea.getPreferredSize());
        SideBoard.setLayout(new BoxLayout(SideBoard, BoxLayout.PAGE_AXIS));
        add(SideBoard);
        //adds the sideboard to the window
        SideBoard.setBackground(Color.gray);
        //sets the background of the sideboard to gray
        SideBoard.setPreferredSize(new Dimension (250,1000));
        //sets the preffered size of the side board
        SideBoard.add(LevelDisplay);
        SideBoard.add(ScoreDisplay);
        LevelDisplay.setFont(new java.awt.Font("Tahoma", 0, 36));
        LevelDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addKeyListener(this);
        setVisible(true);
        //setResizable(false);
        setLocationRelativeTo(null);
        //centers the window
        setFocusable(true);
        
        start();
        //initilizes the game by calling the start method
    }
    
    private void start(){
        Random rand = new Random();
        int a = rand.nextInt(7);
        //creates a random number from 0 to 6
        nextpiece = a;
        System.out.println(nextpiece);
        //sets the next piece to be at a random index of the shapes point array.
        newPiece();
        
        
    }
    
    private void newPiece(){
        piecePosition = new Point(4,0);
        rotation = 0;
        ScoreDisplay.setText("Score = " + score);
        LevelDisplay.setText("Level = " + level);
        if(collision(piecePosition.x,piecePosition.y,rotation)){
            gameRunning=false;
            endGame();
            
        } else{
            Random rand = new Random();
            int b = rand.nextInt(7);
            currentPiece = nextpiece;
            nextpiece = b;
            System.out.println("Current Piece: " + currentPiece + " Next Peice: " + nextpiece);
            drawPiece();
        }
        
    }
    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getExtendedKeyCode()== VK_UP ) {
            rotate();
        }
        if (evt.getExtendedKeyCode() == VK_DOWN) {
            moveDown();
            score+=10;
            ScoreDisplay.setText("Score = " + score);
        }
        if (evt.getExtendedKeyCode() == VK_LEFT) {
            moveLR(-1);
        }
        if (evt.getExtendedKeyCode() == VK_RIGHT) {
            moveLR(1);
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    public void moveLR(int x){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        if(!collision(piecePosition.x + x,piecePosition.y,rotation)){
            piecePosition.x += x;
            
        }
        drawPiece();
    }
    
    public void moveDown(){
        System.out.println(piecePosition.y+1);
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        if(!collision(piecePosition.x,piecePosition.y+1, rotation)){
            piecePosition.y++;
            drawPiece();
        } else{
            PinToBoard();
        }
        
    }
    
    public void rotate(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        if(rotation>2){
            rotation = 0;
        }else{
            rotation++;
        }
        drawPiece();
    }
    
    public void drawPiece(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Shapes.ShapesColors[currentPiece]);
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(createRaisedBevelBorder());
        }
    }
    
    public boolean collision(int x, int y, int rotationPos){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            if((p.x+x)>9 || (p.y+y)>19 || (p.x+x)<0){
                System.out.println("True1");
                System.out.println(p.y+y);
                return true; 
            }
            else if(BoardArray[p.x + x][p.y + y].getBackground() != Color.BLACK) {    
                System.out.println("True2");
                return true;
            }
        }
        System.out.println("False");
        return false;
    }
    
    public void PinToBoard(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Shapes.ShapesColors[currentPiece]);
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(createRaisedBevelBorder());
        }
        clearmultiple();
        newPiece();
    }
    
    
    public void clearsingle(int line){
        for(int y = line; y>0; y--){    
            for(int x=0; x<=9; x++){
                BoardArray[x][line].setBackground(BoardArray[x][line-1].getBackground());
                BoardArray[x][line].setBorder(BoardArray[x][line-1].getBorder());
            }
            line--;
        }
        linesCompleted++;
        levelChecker();
    }
    
    public void clearmultiple(){
        boolean gap;
		int numClears = 0;
		
		for (int y = 0; y < 20; y++) {
			gap = false;
			for (int x = 0; x < 10; x++) {
				if (BoardArray[x][y].getBackground().equals(Color.black)) {
					gap = true;
                                        break;
				}
			}
			if (!gap) {
				clearsingle(y);
				numClears += 1;
			}
		}
                score+= 200*Math.pow(numClears,2);
    }
    
    private void levelChecker(){
        if(linesCompleted>=(20-level)){
            linesCompleted = 0;
            level++;
            score += level*1000;
        }
    }
   
    public void endGame(){
        JOptionPane.showMessageDialog(null, "GAME OVER!  Final Score: " + score);
    }

    
}
