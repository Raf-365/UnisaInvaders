package entities;

public class Bullet extends Weapon {

    public static int DISTANCE_FROM_PEPPER = 5, MISSILE_SPEED = 10;

    public Bullet(int x, int y, String path) {
        super(x, y, path);
        this.y = y - h - DISTANCE_FROM_PEPPER;
    }
    
}
