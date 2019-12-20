/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 *
 * @author antno
 */
public class SoundPlayerBossShoot {
     private AudioClip clip;

    public SoundPlayerBossShoot(String audioClipName) {
    
            URL url = getClass().getResource(audioClipName);
           
            clip = Applet.newAudioClip(url);
            
    }

  
    public void stateChanged(StateChangedEvent event) {
        if (event.getState().contains(6)) {//when the boss  shoot
            
                clip.play();
          
            SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(6);
        }
}
}
