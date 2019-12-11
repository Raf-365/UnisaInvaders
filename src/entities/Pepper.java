/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author stefa
 */
public class Pepper extends Character {

    private int dx, dy;
    private int state;
    private boolean stopFiring;
    private ArrayList<Bullet> missilesArray;

    public static final int HEALTH_MAX = 7 , PEPPER_SPEED = 7;

    public Pepper(int x, int y, String path) {
        super(x, y, path, HEALTH_MAX);
        this.stopFiring = false;
    }

    public int getState() {
        return state;
    }

    //public ArrayList<bullet> getMissiles(){return missilesArray;}
    /*private void fire(){
        missilesArray.add(new Bullet(x, y));
    }
    
    public void deleteMissile(Missile m){
        missilesArray.remove(m);
    }
     */
 /*public boolean isAlive(){
        if(health == 0)
            return false;
        else
            return true;
    }*/
 /*public void setState(int state) {       
        this.state = state;
        setChanged();
        notifyObservers();
    }*/
}
