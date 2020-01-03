package entities;

import Controller.*;
import View.GameFrame;

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
    
    public void move(){
       
        if ((getX() + getDx() <= GameFrame.MAX_X - getWidth() - 20) 
                 && (getX() + getDx() >= 5)) 
            setX(getX() +getDx());
        
    }
    
    public void fire(BulletBossController bulletBossController) {
        for(int i=0; i<BossController.getNUM_FIRE(); i++){
            BulletBoss bulletBoss = new BulletBoss(0,0,"src/resources/missileBoss.png");
            bulletBoss.setX((getX()+ getWidth()/2)-bulletBoss.getWidth()/2);
            bulletBoss.setY(getY()+i*bulletBoss.getHeight()+5);
            bulletBossController.getBulletsArrayBoss().add(bulletBoss);
        }
    }
    
    public void followPepper(Pepper pepper){
        if(getX() < pepper.getX())
            setDx(BOSS_SPEED/2);            
        else if(getX() > pepper.getX())
            setDx(-BOSS_SPEED/2);
    }
}
