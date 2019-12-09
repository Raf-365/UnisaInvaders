/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import fallingdown.Missile;
import java.util.*;
/**
 *
 * @author stefa
 */
public abstract class Character extends Entity{
    protected int health;
    protected int dx=7;
    protected ArrayList<Missile> missilesArray;

    public Character(int x, int y, int health) {
        super(x, y, true);
        this.health = health;
        this.missilesArray = new ArrayList<Missile>();
    }
    
    public int getDx(){
        return dx;
    }
    
    public int getHealth(){return this.health;}
    
    public void updateHealth(int value) {
        health+=value;
    }
      public boolean isAlive(){
        if(health == 0)
            return false;
        else
            return true;
    }
    
}
