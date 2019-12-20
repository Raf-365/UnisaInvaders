/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fallingdown.CollidableObjects;
import fallingdown.Missile;
import fallingdown.ThreadFalling;
import java.util.ArrayList;

/**
 *
 * @author stefa
 */
public class Pepper extends Character {
    
    private int state;
    private boolean stopFiring;
    
    private static final int HEALTH_MAX = 5;

    public Pepper(int x, int y) {
        super(x, y, HEALTH_MAX);
        this.stopFiring = false;
    }
    
   // public int getState() {return state;}
    
    public ArrayList<Missile> getMissiles(){return missilesArray;}
    
    private void fire(){
        missilesArray.add(new Missile(x, y));
    }
    
    public void deleteMissile(Missile m){
        missilesArray.remove(m);
    }
    
  
    
    /*public void setState(int state) {       
        this.state = state;
        setChanged();
        notifyObservers();
    }*/
    
}
