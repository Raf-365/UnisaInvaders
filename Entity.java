/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author stefa
 */
public abstract class Entity {
    protected int x, y;
    
    protected boolean visible;

    public Entity(int x, int y, boolean visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
    }
    
   
    
    public int getX(){return this.x;}
    
    public int getY(){return this.y;}

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    public void setVisible(boolean visible) {this.visible = visible;}
    
    public boolean isVisible(){return visible;}
    
    //public Rectangle getBounds(){return new Rectangle(x, y, w, h);}
    
    //public int getWidth() {return w;}
    
    //public int getHeight() {return h;}
    
}
