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
public class BulletBoss extends Weapon {
    
    public static int DISTANCE_FROM_BOSS = 5, MISSLE_SPEED=10;
    
    public BulletBoss(int x, int y, String path){
        super (x,y,path);
        this.y=y+h+DISTANCE_FROM_BOSS;
    }
    
}
