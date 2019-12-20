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
public class SoundObservable {
    private List<Integer> states;
    private List<SoundPlayerListener> listeners;

    public SoundObservable() {
        this.states = new ArrayList<Integer>();
        this.listeners = new ArrayList<SoundPlayerListener>();
    }
    public synchronized void addStates(int state){
        states.add(state);
        stateChanged();
    }
    public void addListener(SoundPlayerListener lis){
       
        listeners.add(lis);
    
    }
    public void removeListener(SoundPlayerListener lis){
        listeners.remove(lis);
    }
    public synchronized void removeState(int state){
        int ind=states.indexOf(state);
        states.remove(ind);
      
    }
    public List<Integer> getList(){
        return states;
    }            
     protected void stateChanged() {
        StateChangedEvent speedEvent =new StateChangedEvent(this, getList());
        for (SoundPlayerListener eachListener : listeners) {
            eachListener.stateChanged(speedEvent);
        }
    }
    
}
