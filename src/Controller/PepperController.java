package Controller;

import entities.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import View.GameFrame;
import View.MainView;
import ObserverPackage.Observer;

public class PepperController  implements Controller{

    Pepper pepper; 
    BulletController bulletController;
    BonusController bonusController;
    private boolean stopFiring, fireFlag;
 
    
    
    public PepperController() {
        bonusController = new BonusController();
        bulletController = new BulletController();
        pepper = new Pepper(0, 0, "src/Resources/Pepper20.png");
        int x = (GameFrame.MAX_X - pepper.getWidth()) / 2;
        int y = GameFrame.MAX_Y - pepper.getHeight() - 40;
        pepper.setX(x);        
        pepper.setY(y);  
        
    }

    public Pepper getPepper() {
        return this.pepper;
    }

    @Override
    public void update() {
        pepper.move();
        bulletController.update();
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT)) 
            pepper.setDx(-Pepper.PEPPER_SPEED);

        if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT))  
            pepper.setDx(Pepper.PEPPER_SPEED);
            
     
        if (key == KeyEvent.VK_SPACE && !stopFiring) {
            pepper.addState(PlayController.SOUND_BULLET);
            fireFlag=true;
            pepper.changeImage(10);
            pepper.fire(bulletController);
            stopFiring = true;
        }
    }
    
    public boolean getFireFlag(){
        return fireFlag;
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletController.getBulletsArray();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT)) 
            pepper.setDx(0);
        if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT)) 
            pepper.setDx(0);
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
    
    
     public void updateHealthPepper(int malus){
        pepper.updateHealth(malus);
    }

     public void setObserverPepper(MainView view){
         pepper.addObserver((Observer) view);
     }
     
     
}
