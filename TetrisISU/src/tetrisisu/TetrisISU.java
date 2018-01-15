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
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
/**
 *
 * @author Admin
 */
public class TetrisISU extends JFrame implements KeyListener{
    public JPanel BoardArea = new JPanel();
    //initilizes a new jpanel called boardarea
    public JPanel SideBoard = new JPanel();
    //initlizes a new jpanel called sideboard
    public JPanel[][] BoardArray = new JPanel[10][20];
    //makes a new jpanel array for the gameboard
    public JLabel GameTitle = new JLabel("TETRIS");
    public JLabel StatTitle = new JLabel("Statistics");
    //makes a new jlabel for the side screen where all the statistics are going to be displayed
    private JLabel ControlTitle = new JLabel("Controls");
    private JLabel Control1 = new JLabel("Rotate Piece: Up Arrow");
    private JLabel Control2 = new JLabel("Move Piece Left: Left Arrow");
    private JLabel Control3 = new JLabel("Move Piece Right: Right Arrow");
    private JLabel Control4 = new JLabel("Drop Piece: Down Arrow");
    public int score = 0;
    //makes an integer to store the score
    public JLabel ScoreDisplay = new JLabel("Score = " + score);
    //makes a new jlabel to display the score on the side board
    public static int level = 29;
    //makes an integer to store the level (set the level to 29 to hear the music change more quickly)
    public JLabel LevelDisplay = new JLabel("Level: " + level);
    //makes a jlabel to display the level
    Thread s1 = new soundTrack1();
    //makes a new thread for the playing of the first soundtrack
    //the reason that a new thread is used is so that the music can play without slowing down the game itself
    Thread s2 = new soundTrack2();
    //makes a new thread for the playing of the second soundtrack
    public int linesCompleted = 0;
    //makes a public integer to contain how many lines have been completed since the last level up
    public Point piecePosition;
    //makes a new point to contain the position of the refrence point of the current piece
    public int rotation;
    //makes an integer to contain the rotation
    public int totalclearline = 0;
    //makes a new public integer that contains the total number of lines that have been cleared
    public static int currentPiece, nextpiece;
    //makes 2 new integers to hold the indexes of the current and next piece
    public static boolean gameRunning = true;
    //
    public boolean Sound1 = true;
    public boolean Sound2 = false;
    
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
        BoardArea.setPreferredSize(new Dimension(500,1000));
        //sets the preffered size of the board area to 500 by 1000
        add(BoardArea);
        //adds the boardarea to the window
        
        
        SideBoard.setLayout(new BoxLayout(SideBoard, BoxLayout.Y_AXIS));
        //sets a boxlayout manager on the sideboard
        SideBoard.setBackground(Color.gray);
        //sets the background of the sideboard to gray
        SideBoard.setPreferredSize(new Dimension (250,1000));
        //sets the preffered size of the side board
        
        SideBoard.add(Box.createRigidArea(new Dimension(0,20)));
        
        GameTitle.setFont(new java.awt.Font("Tahoma", 0, 50));
        //sets the font of the statistic title
        GameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(GameTitle);
        //adds the title to the side board
        
        SideBoard.add(Box.createRigidArea(new Dimension(0,50)));
        //adds a spacer to the side board to spread the elements out
        
        StatTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        //sets the font of the statistic title
        StatTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(StatTitle);
        //adds the title to the side board
        
        LevelDisplay.setFont(new java.awt.Font("Tahoma", 0, 20));
        //sets the font type and size of the level display
        LevelDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(LevelDisplay);
        //adds the level display to the sideboard
        
        ScoreDisplay.setFont(new java.awt.Font("Tahoma", 0, 20));
        //sets the font type and size of the score display
        ScoreDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(ScoreDisplay);
        //adds the score display to the sideboard
        
        SideBoard.add(Box.createRigidArea(new Dimension(0,50)));
        //adds a spacer to the side board to spread the elements out
        
        ControlTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        //sets the font of the statistic title
        ControlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(ControlTitle);
        //adds the title to the side board
        
        Control1.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control1.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(Control1);
        //adds the title to the side board
        
        Control2.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control2.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(Control2);
        //adds the title to the side board
        
        Control3.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control3.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(Control3);
        //adds the title to the side board
        
        Control4.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control4.setAlignmentX(Component.CENTER_ALIGNMENT);
        ////centers the component on the sideboard
        SideBoard.add(Control4);
        //adds the title to the side board
        
        add(SideBoard);
        //adds the sideboard to the window
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
        //creates a random value
        int a = rand.nextInt(7);
        //creates a random number from 0 to 6
        nextpiece = a;
        //sets the next piece to be at a random index of the shapes point array.
        newPiece();
        //calls the new piece method
        music();
        //starts the music by calling the music method
    }

    private void music(){ 
        s1.start();
        //starts a thread declared and initlized in the global variable list.
    }
    
    
    private void newPiece(){
        piecePosition = new Point(4,0);
        //sets the inital point where pieces spawn
        rotation = 0;
        //sets the roation to 0
        ScoreDisplay.setText("Score = " + score);
        //refreshes the score display
        LevelDisplay.setText("Level = " + level);
        //refreshes the level display
        if(collision(piecePosition.x,piecePosition.y,rotation)){
        //checks to see if spawining a peice would cause it to collide with another piece
        //if it does then that means that the game is over
            gameRunning=false;
            //gameRunning is set to false, stopping all the while loops in the threads
            endGame();
            //the end game function is called
            
        } else{
            Random rand = new Random();
            int b = rand.nextInt(7);
            currentPiece = nextpiece;
            nextpiece = b;
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
        int temprotation=0;
        switch(rotation){
            case 0: 
                temprotation =1;
                break;
            case 1:
                temprotation = 2;
                break;
            case 2:
                temprotation = 3;
                break;
            case 3:
                temprotation = 0;
                break;
        }
        if(!collision(piecePosition.x,piecePosition.y,temprotation)){
            if(rotation>2){
                rotation = 0;
            }else{
                rotation++;
            }
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
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotationPos]){
            if((p.x+x)>9 || (p.y+y)>19 || (p.x+x)<0){
                return true; 
            }
            else if(BoardArray[p.x + x][p.y + y].getBackground() != Color.BLACK) {    
                return true;
            }
        }
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
                        //if there is no gap
				clearsingle(y);
                                //calls the clearsingle line method and passes the y coordinate of the line that is filled
				numClears += 1;
                                //adds one to the number of clears
			}
		}
                score+= 200*Math.pow(numClears,2);
                //adds 200 times, the number of clears that were preformed this method call, squared.
    }
    
    private void levelChecker(){
        if(linesCompleted>=(20-level)){
            //if the number of lines complete is greater than or equal to 20 minus the level
            //this results in a uniqe difficulty ramp
            linesCompleted = 0;
            //set lines completed to 0
            level++;
            //adds one to the level
            score += level*100;
            //adds 100 times the level to the current score
        }
        if(level>29 && level<31){
        //checks to see if the current level is between 29 and 31.
            s1.stop();
            //stops the thread playing sound track 1
            s2.start();
            //starts the thread that is set to play sound track 2
        }
    }
   
    public void endGame(){
        JOptionPane.showMessageDialog(null, "GAME OVER!  Final Score: " + score);
        //a joptionpane pops up with the message that the game is over and displays the final score
    }

    
}
