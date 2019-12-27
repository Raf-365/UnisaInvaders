package entities;

public class BulletBoss extends Weapon {
    
    public static int DISTANCE_FROM_BOSS = 5, MISSLE_SPEED=10;
    
    public BulletBoss(int x, int y, String path){
        super (x,y,path);
        this.y=y+h+DISTANCE_FROM_BOSS;
    }
    
}
