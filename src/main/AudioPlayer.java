/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author antno
 */
public class AudioPlayer {
       // to store current position 
    Long currentFrame; 
    Clip clip; 

    // current status of clip 
    String status; 

    AudioInputStream audioInputStream;
    public AudioPlayer(String filepath)throws UnsupportedAudioFileException,IOException, LineUnavailableException{
        // create AudioInputStream object 
        
        audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
// create clip reference 
        clip = AudioSystem.getClip();
// open audioInputStream to the clip 
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        
      
    }
    public void stopClip(){
          clip.stop();
    }

}
