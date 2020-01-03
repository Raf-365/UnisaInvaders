package entities;

import Controller.*;
import View.GameFrame;
import java.util.ArrayList;

public class Pepper extends Character {

    private int state;
    private boolean stopFiring;
    private ArrayList<Bullet> missilesArray;
    

    public static final int HEALTH_MAX = 5 , PEPPER_SPEED = 7, MALUS=-1, HEALTH1=1, HEALTH2=2;

    public Pepper(int x, int y, String path) {
        super(x, y, path, HEALTH_MAX);
        
        this.stopFiring = false;
    }

    public int getState() {
        return state;
    }
    
    public void move() {
        
        if ((getX() +getDx() <= GameFrame.MAX_X - getWidth() - 20) && (getX() + getDx() >= 5)) 
            setX(getX() + getDx());
        
    }
    
    public void fire(BulletController bulletController ) {
        bulletController.getBulletsArray().add(new Bullet(getX(), getY(), 
                "src/resources/missile2.png"));
    }

}
