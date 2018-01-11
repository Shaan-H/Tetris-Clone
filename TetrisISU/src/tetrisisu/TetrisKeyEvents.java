/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class TetrisKeyEvents {
    
    private static volatile boolean wPressed = false;
    public static boolean isWPressed() {
        synchronized (TetrisKeyEvents.class) {
            return wPressed;
        }
    }
    
    public void Larrowkey(){
        
    }
    
    public void Rarrowkey(){
        
    }
    
    public void Uarrowkey(){
        
    }
    
    public void Darrowkey(){
        
    }
    
    public void Pkey(){
        
    }
}

class Keychecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

        char ch = event.getKeyChar();
   }
    
}