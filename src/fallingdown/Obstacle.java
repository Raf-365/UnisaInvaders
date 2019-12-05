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
 * @author antno
 */
public class Obstacle extends JPanel implements CollidableObjects{
    private ImageIcon image;
    private Image elem;
    private int x, y;
    private final int y0;
    protected boolean visible2;
    private boolean checkCollis;

    public boolean getCheckCollis() {
        return checkCollis;
    }

 

    public void setCheckCollis(boolean checkCollis) {
        this.checkCollis = checkCollis;
    }
    
    
    
    
    public Obstacle(int y){
    image=new ImageIcon("src/Resources/Books.png");
    elem= image.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    visible2 = true;
    x=generateRandom();
    this.y=y;
    this.y0=y;
    checkCollis=false;
    }
    
    
    
    
    private int generateRandom(){
    Random random = new Random();
  
    return random.nextInt(PepperJPanel.B_WIDTH-160)+80;
    }
    
 
    public void drawObstacle(Graphics g) {
        
        if(isVisibles()){
            
        g.drawImage(elem, x, y, this);
        
        } else if (y<=0){
            setVisibles(true);
        }
  
    }
     
 private void cycle() {


        y += 2;

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
        return new Rectangle(x+15, y+15, 30, 30);
        }
        
        
    public void setVisibles (Boolean visible) { 
        this.visible2 = visible; 
    }
    
    public boolean isVisibles() {
        return visible2;
    }
        

        }
    
  
    


