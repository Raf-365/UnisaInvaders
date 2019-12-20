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
public abstract class Weapon extends Entity {
    private boolean checkCollision;

    public Weapon(int x, int y, String path) {
        super(x, y, true, path);
      this.checkCollision=false;
    }
 


    public boolean getCheckCollision() {
        return this.checkCollision;
    }

    public void setCheckCollision(boolean check) {
        this.checkCollision = check;
    }
}
