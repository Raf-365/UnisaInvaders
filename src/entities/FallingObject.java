/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author stefa
 */
public abstract class FallingObject extends Entity{
    private boolean checkCollision;

    public FallingObject(int x, int y) {
        super(x, y, true);
        checkCollision = false;
    }
    
    
    
    public boolean getCheckCollision(){return this.checkCollision;}
    
    public void setChekCollision(boolean check){this.checkCollision=check;}
    
    
    
}
