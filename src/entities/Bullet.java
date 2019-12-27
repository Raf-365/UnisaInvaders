package entities;

public class Bullet extends Weapon {

    public static int DISTANCE_FROM_PEPPER = 5;

    public Bullet(int x, int y, String path) {
        super(x, y, path);
        this.y = y - h - DISTANCE_FROM_PEPPER;
    }
    
}
