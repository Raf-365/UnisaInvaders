/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 *
 */
public class Missile {
    
    private int dx;
    private int dy;
    private int x ;
    private int y ;
    private int w;
    private int h;
    private Image image;
    private boolean visible;
    
    private static int DISTANCE_FROM_PEPPER = 5, MISSILE_SPEED=10;
    
    public Missile(int x, int y){
        loadImage();
        this.x=x;
        this.y=y-h-DISTANCE_FROM_PEPPER;
    }
    
    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/resources/missile2.png");
        image = ii.getImage(); 
        
        visible=true;
            
        w = image.getWidth(null);
        h = image.getHeight(null);       
    }
    
    public void move() {
        y -= MISSILE_SPEED;
    }

    public int getX() {return x;}

    public int getY() {return y;}    

    public int getWidth() {return w;}    

    public int getHeight() {return h;}

    public Image getImage() {return image;}
    
    public Rectangle getBounds() {return new Rectangle(x, y, w, h);}
    
    public boolean isVisible() {return visible;}

    public void setVisible(Boolean visible) {this.visible = visible;}
}

