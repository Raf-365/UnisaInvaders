/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;

import java.util.*;
import java.applet.*;
import java.net.*;

/**
 *
 * 
 */
public class SoundPlayerObserver implements Observer {

    private AudioClip clip;
    private AudioClip clip1;
    private AudioClip clip2;
    private AudioClip clip3;

    public SoundPlayerObserver(String audioClipName, String audioClipName1,String audioClipName2,String audioClipName3) {
    
            URL url = getClass().getResource(audioClipName);
            URL url1 = getClass().getResource(audioClipName1);
            URL url2 = getClass().getResource(audioClipName2);
            URL url3 = getClass().getResource(audioClipName3);
            clip = Applet.newAudioClip(url);
            clip1 = Applet.newAudioClip(url1);
            clip2 = Applet.newAudioClip(url2);
            clip3 = Applet.newAudioClip(url3);
    }

    @Override
    public void update(Observable subject, Object arg) {
        Pepper ale = (Pepper) subject;
        if (ale.getSt() == 1) {
            clip.play();
        } else if (ale.getSt() == 2) {
            clip1.play();
        } else if (ale.getSt() == 3){
            clip2.play();
        } else if (ale.getSt() == 4){
            clip3.play();
        }
    }
}
