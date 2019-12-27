package entities;

public class Boss extends Character{
    
    private int state;
    private boolean stopFiring;
    public static final int HEALTH_MAX = 10, BOSS_SPEED = 7, MALUS=-1;

    public Boss(int x, int y, String path){
        super (x, y,path, HEALTH_MAX);
        this.stopFiring = false;
    }
    
    public int getState() {
        return state;
    }
    
    public static int getHealthMax(){return HEALTH_MAX;}
    
}
