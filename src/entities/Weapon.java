package entities;

public abstract class Weapon extends Entity {
    
    public static int MISSILE_SPEED = 10;
    
    public Weapon(int x, int y, String path) {
        super(x, y, true, path);
    }
    
}
