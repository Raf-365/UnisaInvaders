/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author marcopreziosi
 */
public class BulletController extends JPanel {

    ArrayList<Bullet> bulletsArray;

    public BulletController() {
        bulletsArray = new ArrayList<>(); //(0,0, "src/resources/missile2.png");

    }

    public void deleteBullets(Bullet b) {
        bulletsArray.remove(b);
    }

    private void move() {
        Bullet b;

        for (int i = 0; i < bulletsArray.size(); i++) {
            b = bulletsArray.get(i);
            if (b.isVisible() && b.getY() > 0) {
                b.setY(bulletsArray.get(i).getY() - Bullet.MISSILE_SPEED);
            } else {
                deleteBullets(b);
            }

        }
    }

    public void update() {
        move();
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletsArray;
    }

}
