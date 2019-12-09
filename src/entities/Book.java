/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fallingdown.PepperJPanel;
import java.util.Random;

/**
 *
 * @author stefa
 */
public class Book extends FallingObject{
    
    private int y0;

    private static final int DAMAGE_VALUE=-1, OBSTACLE_SCALE=50, OBSTACLE_SPEED=3;
    
    public Book(int x, int y) {
        super(x, y);
        y0=y;
    }
    
    private int generateRandom(){
        Random random = new Random();
        return random.nextInt(PepperJPanel.B_WIDTH-160)+80;
    }
    
    public void update(){
        cycle();
    }
    
    private void cycle() {
        y += OBSTACLE_SPEED;

        if (y > PepperJPanel.B_HEIGHT) {        
            y =y0 ;
            x = generateRandom();
        }
    }    
}
