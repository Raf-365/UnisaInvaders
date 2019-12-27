package entities;

public class BulletBoss extends Weapon {
    
    public static int DISTANCE_FROM_BOSS = 5;/**/
    
    public BulletBoss(int x, int y, String path){
        super (x,y,path);
        this.y=y+h+DISTANCE_FROM_BOSS;/*Verrà generato il proiettile del boss ad una distanza di DISTANCE_FROM_BOSS+h dal centro del boss */
    }
    
}
