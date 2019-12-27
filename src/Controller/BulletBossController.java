package Controller;

import ObserverPackage.Controller;
import entities.*;
import java.util.ArrayList;

public class BulletBossController extends Controller{
        ArrayList<BulletBoss> bulletsArrayBoss;

    public BulletBossController() {
        bulletsArrayBoss = new ArrayList<>(); 
    }

    public void deleteBullets(BulletBoss b) {
        bulletsArrayBoss.remove(b);
    }

    private void move() {
        BulletBoss b;
        for (int i = 0; i < bulletsArrayBoss.size(); i++) {
            b = bulletsArrayBoss.get(i);
            if (b.isVisible() && b.getY() > 0) 
                b.setY(bulletsArrayBoss.get(i).getY() + Bullet.MISSILE_SPEED);
            else 
                deleteBullets(b);
        }
    }

    public void update() {
        move();
    }

    public ArrayList<BulletBoss> getBulletsArrayBoss() {
        return bulletsArrayBoss;
    }
}
