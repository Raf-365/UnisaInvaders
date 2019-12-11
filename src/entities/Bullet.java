/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author marcopreziosi
 */
public class Bullet extends Weapon {

    public static int DISTANCE_FROM_PEPPER = 5, MISSILE_SPEED = 10;

    public Bullet(int x, int y, String path) {
        super(x, y, path);
        this.y = y - h - DISTANCE_FROM_PEPPER;
    }

}
