/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import View.GameFrame;

/**
 *
 * @author stefa
 */
public class Book extends FallingObject {

    private int y0;
    private Image elem;
    public static final int DAMAGE_VALUE = -1, OBSTACLE_SCALE = 50, BOOK_SPEED = 3, BOOK_SPEED_UPDATE=1;
    
    private static int speed; //statica perché tutte le istanze devono avere lo stesso valore

    public Book(){
        
    }
    
    
    public Book(int x, int y, String path) {
        super(x, y, path);
        //speed=BOOK_SPEED;
        y0 = y;
        image = image.getScaledInstance(OBSTACLE_SCALE, OBSTACLE_SCALE, Image.SCALE_SMOOTH);
        w = OBSTACLE_SCALE;
        h = OBSTACLE_SCALE;
    }

    public static void setSpeed(int speed) {
        Book.speed = speed;
    }
    
    public static int getSpeed(){return speed;}
    
    public static void updateSpeed(){speed+=BOOK_SPEED_UPDATE;}

    public int getY0() {return this.y0;}

    private int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    } 
}
