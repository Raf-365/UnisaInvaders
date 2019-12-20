/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.BulletController;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import entities.*;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import View.GameFrame;
import audio.*;
/**
 *
 * @author antno
 */
public class PepperController{

    Pepper pepper; 
    BulletController bulletController;
    BonusController bonusController;
    private boolean stopFiring, fireFlag;
    private SoundObservable a;
    
    public PepperController() {
        bonusController = new BonusController();
        bulletController = new BulletController();
        pepper = new Pepper(0, 0, "src/Resources/Pepper20.png");
        int x = (GameFrame.MAX_X - pepper.getWidth()) / 2;
        int y = GameFrame.MAX_Y - pepper.getHeight() - 40;
        pepper.setX(x);        
        pepper.setY(y);   
        a=new SoundObservable();
        a.addListener(new SoundPlayerPepperShoots("shot.wav"));
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

        if ((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT)) {
            //pepper.setX(pepper.getX()-pepper.getDx());
            pepper.setDx(-Pepper.PEPPER_SPEED);
        }

        if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT))  {
            pepper.setDx(Pepper.PEPPER_SPEED);

        }

        if (key == KeyEvent.VK_SPACE && !stopFiring) {
          
            fireFlag=true;
            pepper.changeImage(10);
            fire();
            a.addStates(1);
            stopFiring = true;
        }

    }
    
    public boolean getFireFlag(){
        return fireFlag;
    }

    private void fire() {
        
        bulletController.getBulletsArray().add(new Bullet(pepper.getX(), pepper.getY(), 
                "src/resources/missile2.png"));
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletController.getBulletsArray();
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT)) {
            pepper.setDx(0);
        }

        if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT)) {
            pepper.setDx(0);
        }

        if (key == KeyEvent.VK_SPACE) {
           
            fireFlag = false;
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
