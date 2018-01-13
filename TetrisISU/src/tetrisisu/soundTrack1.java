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

public class soundTrack1 extends Thread{ //Refer to soundTrack1
    @Override 
    public void run(){ //allows the method to override the parent class
        while(gameRunning){ //Will activate while the game is running
            try{
                File file = new File("TetrisMusic1.mp3"); //creates a new file
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                try{
                    AdvancedPlayer player = new AdvancedPlayer(bis); //creates a new AdvancedPlayer
                    player.play(); //Activates the player
                } catch(JavaLayerException ex) {}

            } catch(IOException e){} 
        }
    }
}
