/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Observable;
/**
 *
 * 
 */
public class Pepper extends Observable implements CollidableObjects {

    private int dx;
    private int dy;
    private int x = 40;
    private int y = 430;
    private int w;
    private int h;
    private Image image;
    private int st;
    private Integer health;
    private ArrayList<Missile> missilesArray;
    private boolean  stopFiring;

    public Pepper() {
        loadImage();
        missilesArray = new ArrayList<>();
    }
    public int getSt() {
        return st;
    }

    public ArrayList<Missile> getMissiles(){return missilesArray;}
    
        private void fire(){
        missilesArray.add(new Missile(x, y));
    }
    
    public void deleteMissile(Missile m){
        missilesArray.remove(m);
    }
    
    
    public void changeImage(int num){
        ImageIcon ii = new ImageIcon("src/resources/Pepper"+num+".png");
        
        //image = ii.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
       image = ii.getImage();
    }
    
        
    public void setSt(int st) {
        this.st = st;
        setChanged();
        notifyObservers();
    }

    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/Resources/Pepper20.png");
        
        //image = ii.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH); 
        image = ii.getImage();
        stopFiring=false;
        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {        
        if( (x+dx<=1140) && (x+dx >= 15)){
        x += dx;
        }
        
        y += dy;
    }

    public int getX() {        
        return x;
    }

    public int getY() {        
        return y;
    }
    
    public int getWidth() {        
        return w;
    }
    
    public int getHeight() {        
        return h;
    }    

    public Image getImage() {        
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -7;

        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 7;
     
        }
        
        if (key == KeyEvent.VK_SPACE && !stopFiring){ 
            changeImage(10);
            fire(); 
            setSt(3);
            stopFiring=true;
        }

    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_SPACE){ 
            changeImage(20);   
            stopFiring=false;
        }

    }
    
    @Override
        public Rectangle getBounds() {
            
        return new Rectangle(x+40, y+30, 150, 300);
        
    }
}