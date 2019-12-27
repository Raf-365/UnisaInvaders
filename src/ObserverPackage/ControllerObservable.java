/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObserverPackage;

import java.util.ArrayList;

/**
 *
 * @author marcopreziosi
 */
public class ControllerObservable {

    private ArrayList<Integer> states;
    private ArrayList<ControllerObserver> listeners;

    public ControllerObservable() {
        this.states = new ArrayList<Integer>();
        this.listeners = new ArrayList<ControllerObserver>();
    }

    public synchronized void addStates(int state) {
        states.add(state);
        stateChanged();
    }

    public void addListener(ControllerObserver listener) {
        listeners.add(listener);
    }

    public void removeListener(ControllerObserver listener) {
        listeners.remove(listener);
    }

    public synchronized void removeState(int state) {
        int ind = states.indexOf(state);
        states.remove(ind);

    }

    public ArrayList<Integer> getList() {
        return states;
    }

    public ArrayList<ControllerObserver> getListeners() {
        return listeners;
    }

    protected void stateChanged() {
        CollisionEvent collisionEvent = new CollisionEvent(this, getList());
       
        for (ControllerObserver eachListener : listeners) 
            eachListener.eventCollisionChanged(collisionEvent);
        
    }

}
