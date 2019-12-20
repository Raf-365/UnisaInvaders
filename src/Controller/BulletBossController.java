/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.*;
import java.util.ArrayList;

/**
 *
 * @author marcopreziosi
 */
public class BulletBossController {
        ArrayList<BulletBoss> bulletsArrayBoss;

    public BulletBossController() {
        bulletsArrayBoss = new ArrayList<>(); //(0,0, "src/resources/missile2.png");

    }

    public void deleteBullets(BulletBoss b) {
        bulletsArrayBoss.remove(b);
    }

    private void move() {
        BulletBoss b;

        for (int i = 0; i < bulletsArrayBoss.size(); i++) {
            b = bulletsArrayBoss.get(i);
            if (b.isVisible() && b.getY() > 0) {
                b.setY(bulletsArrayBoss.get(i).getY() + Bullet.MISSILE_SPEED);
            } else {
                deleteBullets(b);
            }

        }
    }

    public void update() {
        move();
    }

    public ArrayList<BulletBoss> getBulletsArrayBoss() {
        return bulletsArrayBoss;
    }
}
