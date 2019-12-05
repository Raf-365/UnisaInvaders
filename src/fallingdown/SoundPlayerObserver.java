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
 * @author antno
 */
public class SoundPlayerObserver implements Observer {

    private AudioClip clip;
    private AudioClip clip1;

    public SoundPlayerObserver(String audioClipName, String audioClipName1) {
    
            URL url = getClass().getResource(audioClipName);
            URL url1 = getClass().getResource(audioClipName1);
            clip = Applet.newAudioClip(url);
            clip1 = Applet.newAudioClip(url1);

    }

    @Override
    public void update(Observable subject, Object arg) {
        Pepper ale = (Pepper) subject;
        if (ale.getSt() == 1) {
            clip.play();
        } else if (ale.getSt() == 2) {
            clip1.play();
        }
    }
}
