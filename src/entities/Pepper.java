package entities;

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

}
