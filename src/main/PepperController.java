/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import entities.*;
//import fallingdown.View;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author antno
 */
public class PepperController extends JPanel{
    Pepper pepper;
    private int w, h;

    public PepperController() {
        //w=view.getPepperImage().getWidth(null);
        //h=view.getPepperImage().getHeight(null);
        
        //int x = (GameFrame.MAX_X-w)/2;
        //int y = GameFrame.MAX_Y-h-GameFrame.INF_BORDER;
        pepper=new Pepper(0, 0, "src/Resources/Pepper20.png");
        
        this.pepper = pepper;
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
    
    public Pepper getPepper(){return this.pepper;}
    
    public void update(){
        move();
    }
    
    private void move() {        
        if((pepper.getX()+pepper.getDx()<=GameFrame.MAX_X-w-20) && (pepper.getX()+pepper.getDx() >= 5))
                pepper.setX(pepper.getX()+pepper.getDx());   
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {           
            pepper.setX(pepper.getX()-pepper.getDx());
        }

        if (key == KeyEvent.VK_RIGHT) {
            pepper.setX(pepper.getX()-pepper.getDx());
     
        }
        
     /*   if (key == KeyEvent.VK_SPACE && !stopFiring){ 
            changeImage(10);
            fire(); 
            setSt(3);
            stopFiring=true;
        }*/

    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pepper.setX(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            pepper.setX(0);
        }
        
       /* if (key == KeyEvent.VK_SPACE){ 
            changeImage(20);   
            stopFiring=false;
        }*/

    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            PepperController.this.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
             PepperController.this.keyPressed(e);
        }
        
    }       
        
}
