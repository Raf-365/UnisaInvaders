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
import java.util.Observable;
/**
 *
 * @author stefa
 */
public class Pepper extends Observable implements CollidableObjects {

    private int dx;
    private int dy;
    private int x = 40;
    private int y = 670;
    private int w;
    private int h;
    private Image image;
    private int st;

    public Pepper() {
        loadImage();
    }
    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
         setChanged();
        notifyObservers();
    }

    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/Resources/Pepper2.png");
        
        image = ii.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH); 
        
        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {        
        if( (x+dx<=1690) && (x+dx >= -80)){
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

    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
    
    @Override
        public Rectangle getBounds() {
            
        return new Rectangle(x+70, y+70, 150, 400);
        
    }
}