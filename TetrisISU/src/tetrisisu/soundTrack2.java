/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import static tetrisisu.TetrisISU.gameRunning;

/**
 *
 * @author Admin
 */
public class soundTrack2 extends Thread{
    @Override 
    public void run(){
        while(gameRunning){
            try{
                File file = new File("DayNnight.mp3");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                try{
                    AdvancedPlayer player = new AdvancedPlayer(bis);
                    player.play();
                } catch(JavaLayerException ex) {}

            } catch(IOException e){} 
        }
    }
}
