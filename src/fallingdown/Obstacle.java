/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * 
 */
public class Obstacle extends JPanel implements CollidableObjects{
    private ImageIcon image;
    private Image elem;
    private int x, y, w, h;
    private final int y0;
    protected boolean visible2;
    private boolean checkCollis;
    private static final int DAMAGE_VALUE=-1, OBSTACLE_SCALE=50, OBSTACLE_SPEED=3;   
    

    public Obstacle(int y){
        image=new ImageIcon("src/Resources/Books.png");
        elem= image.getImage().getScaledInstance(OBSTACLE_SCALE, OBSTACLE_SCALE, Image.SCALE_SMOOTH);
        
        //w = elem.getWidth(null);
        //h = elem.getHeight(null);
        
        visible2 = true;
        x=generateRandom();
        this.y=y;
        this.y0=y;
        checkCollis=false;
    }
    
    public boolean getCheckCollision() {
        return checkCollis;
    }
 

    public void setCheckCollision(boolean checkCollis) {
        this.checkCollis = checkCollis;
    }
    
    public static int getDamage(){
        return DAMAGE_VALUE;
    }     
    
    private int generateRandom(){
        Random random = new Random();
        return random.nextInt(PepperJPanel.B_WIDTH-160)+80;
    }
    
 
    
     
    private void cycle() {
        y += OBSTACLE_SPEED;

        if (y > PepperJPanel.B_HEIGHT) {        
            y =y0 ;
            x = generateRandom();
        }
    }
    
    public void update(){
            cycle();
    }
        
    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, 30, 30);
    }
        
        
    public void setVisibles (Boolean visible) { 
        this.visible2 = visible; 
    }
    
    public boolean isVisibles() {
        return visible2;
    }
        
}
    
  
    


