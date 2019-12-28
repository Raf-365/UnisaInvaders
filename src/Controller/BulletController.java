package Controller;
import ObserverPackage.Controller;
import entities.*;
import java.util.ArrayList;

public class BulletController implements Controller {

    ArrayList<Bullet> bulletsArray;

    public BulletController() {
        bulletsArray = new ArrayList<>(); 
    }

    public void deleteBullets(Bullet b) {
        bulletsArray.remove(b);
    }

    private void move() {    
        Bullet b;
        for (int i = 0; i < bulletsArray.size(); i++) {
            b = bulletsArray.get(i);
            if (b.isVisible() && b.getY() > 0) 
                b.setY(bulletsArray.get(i).getY() - Bullet.MISSILE_SPEED);
            else
                deleteBullets(b);
        }
    }

    public void update() {
        move();
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletsArray;
    }
}
