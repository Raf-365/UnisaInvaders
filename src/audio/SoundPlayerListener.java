/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;
import java.util.*;
/**
 *
 * @author antno
 */
public interface SoundPlayerListener extends EventListener{
    //nice
    public void stateChanged(StateChangedEvent event);
}
