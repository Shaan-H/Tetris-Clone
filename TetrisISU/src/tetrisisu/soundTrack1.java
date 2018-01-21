/*
 * This defines the first soundtrack
 */
package tetrisisu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import static tetrisisu.TetrisISU.gameRunning;

//Imports the tools that java needs to run this program

public class soundTrack1 extends Thread{
    @Override //allows the method to override the parent class
    public void run(){ 
        while(gameRunning){
        //loops the song for as long as the game is running.
            try{
                File file = new File("TetrisMusic1.mp3"); //creates a new file
                FileInputStream fis = new FileInputStream(file);
                //makes a new file inputstream with the file
                BufferedInputStream bis = new BufferedInputStream(fis);
                //makes a new buffered input stream bis with the file input stream
                
                try{ //If an error occurs try and catch will display it in the console and prevents the game from crashing
                    AdvancedPlayer player = new AdvancedPlayer(bis); //creates a new AdvancedPlayer
                    player.play(); //Activates the player
                } catch(JavaLayerException ex) {}

            } catch(IOException e){}
        } 
    }
}
