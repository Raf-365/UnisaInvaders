/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;


/**
 *
 * @author marcopreziosi
 */
public class Boss extends Character{
    
    private int state;
    private boolean stopFiring;
   
    
    public static final int HEALTH_MAX = 10, BOSS_SPEED = 7;
    
    
    public Boss(int x, int y, String path){
        super (x, y,path, HEALTH_MAX);
        this.stopFiring = false;
    }
    
    public int getState() {
        return state;
    }
    
    public static int getHealthMax(){return HEALTH_MAX;}
    
}
