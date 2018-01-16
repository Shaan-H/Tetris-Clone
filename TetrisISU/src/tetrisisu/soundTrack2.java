/*
 * This defines the second soundtrack
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

public class soundTrack2 extends Thread{ //Refer to soundTrack1
    @Override 
    public void run(){
            try{
                File file = new File("TetrisMusic2.mp3");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                try{
                    AdvancedPlayer player = new AdvancedPlayer(bis);
                    player.play();
                } catch(JavaLayerException ex) {}

            } catch(IOException e){} 
    }
}
