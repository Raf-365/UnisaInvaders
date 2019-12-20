/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.*;

/**
 *
 * @author stefa
 */
public abstract class Character extends Entity {

    protected int health;
    protected int dx = 0;

    public Character(int x, int y, String path, int health) {
        super(x, y, true, path);
        this.health = health;
        //this.missilesArray = new ArrayList<Missile>();
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDx() {
        return dx;
    }

    public int getHealth() {
        return this.health;
    }

    public void updateHealth(int value) {
        health += value;
    }

}
