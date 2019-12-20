/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import View.GameFrame;
import static entities.Book.BOOK_SPEED;
import static entities.Book.BOOK_SPEED_UPDATE;
import static entities.Book.OBSTACLE_SCALE;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author marcopreziosi
 */
public class Bonus extends FallingObject {
    
    private int y0;
    public static final int BONUS_SPEED = 3, BONUS_SPEED_UPDATE=1;
    
    private static int speed;
    
     public Bonus(int x, int y, String path) {
        super(x, y, path);
        speed=BONUS_SPEED;
        y0 = y;
        //image = image.getScaledInstance(OBSTACLE_SCALE, OBSTACLE_SCALE, Image.SCALE_SMOOTH);
      
    }
      public static int getSpeed(){return speed;}
      
      public static void updateSpeed(){speed+=BONUS_SPEED_UPDATE;}
      
      public int getY0() {return this.y0;}
        
}
