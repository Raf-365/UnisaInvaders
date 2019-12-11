/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.BulletController;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import entities.*;
//import fallingdown.View;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import View.GameFrame;

/**
 *
 * @author antno
 */
public class PepperController{

    Pepper pepper; 
    BulletController bulletController;
    private boolean stopFiring;

    //private int w, h;
    public PepperController() {
        //w=view.getPepperImage().getWidth(null);
        //h=view.getPepperImage().getHeight(null);

        //int x = (GameFrame.MAX_X-w)/2;
        //int y = GameFrame.MAX_Y-h-GameFrame.INF_BORDER;
        bulletController = new BulletController();
        pepper = new Pepper(0, 0, "src/Resources/Pepper20.png");
        this.pepper = pepper;

        
    }

    public void setPepperInitialPosition() {
        pepper.setX((GameFrame.MAX_X - pepper.getWidth()) / 2);
        pepper.setY(GameFrame.MAX_Y - pepper.getHeight() - GameFrame.INF_BORDER);
    }

    public Pepper getPepper() {
        return this.pepper;
    }

    public void update() {
        move();
        bulletController.update();
    }

    private void move() {

        if ((pepper.getX() + pepper.getDx() <= GameFrame.MAX_X - pepper.getWidth() - 20) && (pepper.getX() + pepper.getDx() >= 5)) {
            pepper.setX(pepper.getX() + pepper.getDx());
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            //pepper.setX(pepper.getX()-pepper.getDx());
            pepper.setDx(-Pepper.PEPPER_SPEED);
        }

        if (key == KeyEvent.VK_D) {
            pepper.setDx(Pepper.PEPPER_SPEED);

        }

        if (key == KeyEvent.VK_SPACE && !stopFiring) {
            pepper.changeImage(10);
            fire();
            //setSt(3);
            stopFiring = true;
        }

    }

    private void fire() {
        bulletController.getBulletsArray().add(new Bullet(pepper.getX(), pepper.getY(), "src/resources/missile2.png"));
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletController.getBulletsArray();
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            pepper.setDx(0);
        }

        if (key == KeyEvent.VK_D) {
            pepper.setDx(0);
        }

        if (key == KeyEvent.VK_SPACE) {
            pepper.changeImage(20);
            stopFiring = false;
        }

    }
    
    public boolean isAlive(){
        if (pepper.getHealth() > 0)
            return true;
        else 
            return false;
                    
    }
    
    

    

}
