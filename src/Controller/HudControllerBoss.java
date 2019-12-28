/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * 
 */
public class HudControllerBoss {
    private int health;
         
    
    public HudControllerBoss(int health){
        this.health=health;
    }
    public int getHealth() {
        return health;
    }
     
    public void updateHealth(int health) {
        this.health += health;
    }
    
}
