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
//imports all the packages that are required for the program
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
    //creates a new label which contains the title of the game
    public JLabel StatTitle = new JLabel("Statistics");
    //makes a new jlabel for the side screen where all the statistics are going to be displayed
    private final JLabel ControlTitle = new JLabel("Controls");
    //declares a jlabel containing a subheading for the controls section
    private final JLabel Control1 = new JLabel("Rotate Piece: Up Arrow");
    //declares a jlabel that contains one of the controls
    private final JLabel Control2 = new JLabel("Move Piece Left: Left Arrow");
    //declares a jLabel that contains one of the controls
    private final JLabel Control3 = new JLabel("Move Piece Right: Right Arrow");
    //declares a jLabel that contains one of the controls
    private final JLabel Control4 = new JLabel("Drop Piece: Down Arrow");
    //declares a jLabel that contains one of the controls
    public int score = 0;
    //makes an integer to store the score
    public JLabel ScoreDisplay = new JLabel("Score = " + score);
    //makes a new jlabel to display the score on the side board
    public static int level = 1;
    //makes an integer to store the level (set the level to 29 and complete one line to hear the music change more quickly)
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
    //declares a boolean which toggles the thread that moves the pieces down.
    
    public static void main(String[] args) {
        TetrisISU frame = new TetrisISU();
        //runs the layout class
        new Thread(){
            @Override public void run(){
            //overrides methods that are built into the thread object
                while(gameRunning){
                //checks to see if the game is still running before continuing with the loop
                    try{
                        frame.moveDown();
                        //calls a function from the instace of the game that was created
                        Thread.sleep(1000-10*((level-1))); 
                        //The number of milliseconds that it takes for one game cycle
                        //goes down as the level increases
                        //this makes a difficulty modifier which decreases the time by 1 percent every level   
                    } catch(InterruptedException e){}
                    //prevents the program from crashing if an exeption is encountered
                }
            }
	}.start();
        //runs the program
        
    }
    
    public TetrisISU(){
    //sets the look and adds the elements to the window
        setSize(new Dimension(756,1029));
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
        //creates a rigid area to space out the different sections and adds it to the sideboard
        
        GameTitle.setFont(new java.awt.Font("Tahoma", 0, 50));
        //sets the font of the game title
        GameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(GameTitle);
        //adds the title to the side board
        
        SideBoard.add(Box.createRigidArea(new Dimension(0,50)));
        //adds a spacer to the side board to spread the elements out
        
        StatTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        //sets the font of the statistic title
        StatTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
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
        //centers the component on the sideboard
        SideBoard.add(Control1);
        //adds the title to the side board
        
        Control2.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control2.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(Control2);
        //adds the title to the side board
        
        Control3.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control3.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(Control3);
        //adds the title to the side board
        
        Control4.setFont(new java.awt.Font("Tahoma", 0, 15));
        //sets the font of the statistic title
        Control4.setAlignmentX(Component.CENTER_ALIGNMENT);
        //centers the component on the sideboard
        SideBoard.add(Control4);
        //adds the title to the side board
        
        add(SideBoard);
        //adds the sideboard to the window
        addKeyListener(this);
        //adds a keylistener to the window
        setVisible(true);
        //setResizable(false);
        setLocationRelativeTo(null);
        //centers the window in the screen
        setFocusable(true);
        //sets the window to focusable in order to allow the keylistener to work
        setResizable(false);
        //prevents the window from being resized.
        
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
        //starting this thread plays the first soundtrack
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
        //if there is no collision then:
            Random rand = new Random();
            //create a new random value
            int b = rand.nextInt(7);
            //creates a value from 0 to 6
            currentPiece = nextpiece;
            //sets the current piece to equal the nextpeice
            nextpiece = b;
            //assigns a new piece to the nextpiece
            drawPiece();
            //calls the drawpiece method
        }
        
    }
    
    /**
     *
     * @param evt
     * when the keylistener picks up a keypress it calls this method and passes it an object with all the information about the keypress
     */
    @Override
    //overrides a method from the superclass
    public void keyPressed(KeyEvent evt) {
        if (evt.getExtendedKeyCode()== VK_UP ) {
        //if the key pressed was the arrow up key then:
            rotate();
            //call the rotate method
        }
        if (evt.getExtendedKeyCode() == VK_DOWN) {
        //if the key pressed was the arrow down key then:
            moveDown();
            //call the moveDown method
            score+=10;
            //add 10 points to the score
            ScoreDisplay.setText("Score = " + score);
            //refresh the score label
        }
        if (evt.getExtendedKeyCode() == VK_LEFT) {
        //if the key pressed was the arrow left key then:
            moveLR(-1);
            //call the moveLR method, passing it the value of -1
        }
        if (evt.getExtendedKeyCode() == VK_RIGHT) {
        //if the key pressed was the arrow right key then:
            moveLR(1);
            //call the moveLR method, passing it the value of 1
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {}
    //ensures that nothing happens when a key is typed

    @Override
    public void keyReleased(KeyEvent ke) {}
    //ensures that nothing happens when a key is relesed
    
    /**
     *
     * @param x the argument passed indicates whether the piece should be moved to left of right with -1 being left and 1 being right
     * 
     */
    public void moveLR(int x){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
        //iterates through all the points in the current shape with an enhanced for loop
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            //sets all the board arrays back to the original black so that the current piece's colors do not interfere with the collision testing
            //does this by adding the x and y coordinants of the piceposition which is a refrece positoin and adding that to the x and y values of the shapes points
            //and looking at those indexes in the boardarray
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
            //creates the line borders on the board tiles that the piece resides on so that if the piece moves left or right,
            //it looks the same as all the other empty tiles
        }
        if(!collision(piecePosition.x + x,piecePosition.y,rotation)){
        //if there are no collisions with the proposed move of the piece then:
        //gets the current position of the refrence point of the piece and adds the proposed move to it
            piecePosition.x += x;
            //sets the x coordiant of the refrence point equivilent to itself plus the proposed movement
            
        }
        drawPiece();
        //calls the drawpiece method
    }
    
    public void moveDown(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
        //iterates through all the points in the current shape with an enhanced for loop
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            //sets all the board arrays back to the original black so that the current piece's colors do not interfere with the collision testing
            //does this by adding the x and y coordinants of the piceposition which is a refrece positoin and adding that to the x and y values of the shapes points
            //and looking at those indexes in the boardarray
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
            //creates the line borders on the board tiles that the piece resides on so that if the piece moves left or right,
            //it looks the same as all the other empty tiles
        }
        if(!collision(piecePosition.x,piecePosition.y+1, rotation)){
        //checks to see if the proposed move down would result in a collision by calling the collision method and giving it the x and y values of the refrence positon
        //plus the proposed move as well as the current rotation
            piecePosition.y++;
            //adds one to the piece postion if there is no collision resulting in that move
            drawPiece();
            //draw the piece
        } else{
            PinToBoard();
            //if there is a collision then pin the piece to the board
        }
        
    }
    
    public void rotate(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
        //iterates through all the points in the current shape with an enhanced for loop
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Color.black);
            //sets all the board arrays back to the original black so that the current piece's colors do not interfere with the collision testing
            //does this by adding the x and y coordinants of the piceposition which is a refrece positoin and adding that to the x and y values of the shapes points
            //and looking at those indexes in the boardarray
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(BorderFactory.createLineBorder(Color.gray));
            //creates the line borders on the board tiles that the piece resides on so that if the piece moves left or right,
            //it looks the same as all the other empty tiles
        }
        int temprotation=0;
        //declares the temprotation int and set the value at 0
        switch(rotation){
        //creates a switch based on the current rotation
            case 0: 
            //if rotation is 0 then:
                temprotation =1;
                //set temprotation to 1
                break;
                //break the case statement
            case 1:
            //if rotation is 1 then:
                temprotation = 2;
                //set temprotation to 2
                break;
                //break the case statement
            case 2:
            //if rotation is 2 then:
                temprotation = 3;
                //set temprotation to 3
                break;
                //break the case statement
            case 3:
            //if rotation is 3 then:
                temprotation = 0;
                //set temprotation to 0
                break;
                //break the case statement
        }
        if(!collision(piecePosition.x,piecePosition.y,temprotation)){
        //checks to see if there would be a collision at the current coordinants with the new roation
            rotation = temprotation;
            //if there is no collision, then set rotation to equal temprotation
        }
        drawPiece();
        //draws the piece
    }
    
    public void drawPiece(){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotation]){
        //iterates through all the points in the current shape with an enhanced for loop
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBackground(Shapes.ShapesColors[currentPiece]);
            //colors all the boxes at the current peice's coordinants with the color at the index of the currentpiece
            //does this by adding the x and y coordinants of the piceposition which is a refrece positoin and adding that to the x and y values of the shapes points
            //and looking at those indexes in the boardarray
            BoardArray[p.x + piecePosition.x][p.y +piecePosition.y].setBorder(createRaisedBevelBorder());
            //sets all the borders of the tiles that would currently be occupied by the piece with a beveled border
        }
    }
    
    public boolean collision(int x, int y, int rotationPos){
        for (Point p : tetrisisu.Shapes.TetrisShapes[currentPiece][rotationPos]){
        //iterates through all the points of the current shape using an enhanced for loop
            if((p.x+x)>9 || (p.y+y)>19 || (p.x+x)<0){
            //if any of the positions, after the transformation, would be outside of the board then:
                return true; 
                //return true to the place that called the method
            }
            else if(BoardArray[p.x + x][p.y + y].getBackground() != Color.BLACK) {
            //or if the positions after the transformation would endup in a space that is already occupied by a piece 
            //and therefore the color of the tile is not black then:
                return true;
                //return true to the place that called the method
            }
        }
        return false;
        //if none of the the above conditions are met then return false to the place that called the method because there is no collision
    }
    
    public void PinToBoard(){
        drawPiece();
        //calls the drawpiece method
        clearmultiple();
        //calls the clearmultiple method to check if lines can be cleared
        newPiece();
        //calls the newpiece method to create another piece
    }
    
    
    public void clearsingle(int line){
        for(int y = line; y>0; y--){
        //iterates through all 20 lines starting at the the line that is passed to the method through the line argument, and moving up the board.
            for(int x=0; x<=9; x++){
            //iterates through all 10 colomns
                BoardArray[x][y].setBackground(BoardArray[x][y-1].getBackground());
                //replaces the color of the background of the current line with the color of the background above it 
                BoardArray[x][y].setBorder(BoardArray[x][y-1].getBorder());
                //replaces the border of the current line with the border of the line above it
            }
        }
        linesCompleted++;
        //adds one to the number of lines cleared
        levelChecker();
        //calls the level checker method
    }
    
    public void clearmultiple(){
        boolean gap;
        //initilizes a boolean called gap
        int numClears = 0;
        //sets a counter for how many lines were cleared this method call
	for (int y = 0; y < 20; y++) {
        //iterates through all 20 rows
            gap = false;
            //sets gap to false as we have to look for a gap, otherwise if the line is gap free, then gap is already set to false so no more work is nessecary
            for (int x = 0; x < 10; x++) {
                if (BoardArray[x][y].getBackground().equals(Color.black)) {
                //looks for a black square which would mean that there is a gap
                    gap = true;
                    //if there is a gap then set the gap boolean to true
                    break;
                    //break the inner for loop
                }
            }
            if (!gap) {
                //if there is no gap then:
                clearsingle(y);
                //calls the clearsingle line method and passes the y coordinate of the line that is filled 
                numClears += 1;
                //adds one to the number of clears
            }
	}
        score+= 200*Math.pow(numClears,2);
        //adds 200 multiplied by, the number of clears that were preformed this method call, squared.
    }
    
    private void levelChecker(){
        if(linesCompleted>=(20-level)){
            //if the number of lines complete is greater than or equal to 20 minus the level
            //this results in a uniqe difficulty modifier
            linesCompleted = 0;
            //set lines completed to 0
            level++;
            //adds one to the level
            score += level*100;
            //adds 100 times the level to the current score
        }
        if(level==30){
        //checks to see if the current level is 30 and if it is then:
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
