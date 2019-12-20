/*
 *una classe per (libro che colpisce peppere boss che  colpisce pepper
 */
package audio;

import java.util.*;
import java.applet.*;
import java.net.URL;
/**
 *
 * @author antno
 */
public class SoundPlayerCollision implements SoundPlayerListener{
     private AudioClip clip;
private AudioClip clip2;
private AudioClip clip3;
private AudioClip clip4;
private AudioClip clip5;
    public SoundPlayerCollision(String audioClipName,String audioClipName2,String audioClipName3,String audioClipName4,String audioClipName5) {
    
            URL url = getClass().getResource(audioClipName);
            URL url2 = getClass().getResource(audioClipName2);
            URL url3 = getClass().getResource(audioClipName3);
           URL url4 = getClass().getResource(audioClipName4);
           URL url5 = getClass().getResource(audioClipName5);
           
            clip = Applet.newAudioClip(url);
            clip2 = Applet.newAudioClip(url2);
            clip3 = Applet.newAudioClip(url2);
            clip4 = Applet.newAudioClip(url2);
            clip5 = Applet.newAudioClip(url2);
    }

  
     @Override
    public void stateChanged(StateChangedEvent event) {
        if (event.getState().contains(1)) {
            //When the boss's missil hits pepper 
                clip.play();
          
            SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(1);
        }else if(event.getState().contains(2)){
            //when books hits pepper
            clip2.play();
            SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(2);
        }else if (event.getState().contains(3)) {//when Pepper gets a Bonus
            
                clip3.play();
         SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(3);
        }else  if (event.getState().contains(4)) {
            //When the boss is shooted
                clip4.play();
          SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(4);
            
        }else if(event.getState().contains(5)){
            //when the boss dies
            clip5.play();
            SoundObservable x=(SoundObservable)event.getSource();
         x.removeState(5);
        }  

}
    


}
