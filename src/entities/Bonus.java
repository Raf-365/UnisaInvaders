package entities;

import View.GameFrame;
import static entities.Book.BOOK_SPEED;
import static entities.Book.BOOK_SPEED_UPDATE;
import static entities.Book.OBSTACLE_SCALE;
import java.awt.Image;
import java.util.Random;

public class Bonus extends FallingObject {

    private int y0;
    private Image elem;
    public static final int BONUS_SPEED = 3, BONUS_SPEED_UPDATE = 1;
    private static int speed;

    public Bonus(int x, int y, String path) {
        super(x, y, path);
        speed = BONUS_SPEED;
        y0 = y;
        
    }

    public static int getSpeed() {
        return speed;
    }

    public static void updateSpeed() {
        speed += BONUS_SPEED_UPDATE;
    }
    
    public static void setSpeed(int x){
        speed=x;
    }

    public int getY0() {
        return this.y0;
    }

    private int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }
    
    
    
    
}
