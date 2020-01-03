package Controller;
import ObserverPackage.Controller;
import View.GameFrame;
import entities.*;
import java.util.ArrayList;

public class BossController implements Controller{
    Boolean playControllerReady;
    Boss boss;
    BulletBossController bulletBossController;
    PlayController playController;
    Pepper pepper;
    private boolean stopTiming;
    private long timeFire;
    private int numBossKilled, movementBoss;
    private static final int SECONDS_BOSS_FIRE = 1, NUM_FIRE=3;
    public static final int INCREASE_SPEED_BOSS = 1;
    
    public BossController(){
        bulletBossController = new BulletBossController(); 
        playControllerReady=false;
        boss = new Boss(0, 0, "src/Resources/boss.png");
        boss.setVisible(false);
        boss.setX((((GameFrame.MAX_X - boss.getWidth()) / 2)));
        boss.setY(50);
        this.stopTiming=false;
        this.numBossKilled=0;
        this.movementBoss=0;
        
        movementBoss=Boss.BOSS_SPEED/2;
    }

    public static int getNUM_FIRE() {
        return NUM_FIRE;
    }

    
    public Boolean getPlayControllerReady() {
        return playControllerReady;
    }

    public Pepper getPepper() {
        return pepper;
    }

    public int getMovementBoss() {
        return movementBoss;
    }
    
    public void setMovementBoss(int movementBoss) {
        this.movementBoss = movementBoss;
    }

    public int getNumBossKilled() {
        return numBossKilled;
    }
    
    public Boss getBoss(){
        return this.boss;
    }
    
    @Override
    public void update(){
        if(boss.isVisible()){
        if(!playControllerReady){
        this.playController = PlayController.getPlayController();
        this.pepper = playController.getPepperController().getPepper();
        this.playControllerReady=true;
        }        
            boss.followPepper(pepper);
            boss.move();  
                    
        /*SPARO DEL BOSS OGNI TOT SECONDI*/
        if(!stopTiming){
            timeFire = System.currentTimeMillis();
            stopTiming = !stopTiming;
        }
        long beforeTime = System.currentTimeMillis();
        
        if((beforeTime - timeFire)/1000 > SECONDS_BOSS_FIRE/2){
            boss.fire(bulletBossController);
            stopTiming = !stopTiming;
        }
               
        }
        bulletBossController.update();
    }
    
   /* private void followPepper(){
        if(boss.getX() < pepper.getX())
            boss.setDx(movementBoss);            
        else if(boss.getX() > pepper.getX())
            boss.setDx(-movementBoss);
    }*/
    

    

    public ArrayList<BulletBoss> getBulletsArrayBoss() {
        return bulletBossController.getBulletsArrayBoss();
    }
    
    public void updateKilledBoss(){
        numBossKilled += 1;
    }
    
    public boolean isAlive(){
        if (boss.getHealth() > 0)
            return true;
        else 
            return false;
    }    
    
    public void updateHealthBoss(int malus){
        boss.updateHealth(malus);
    }
  
}
