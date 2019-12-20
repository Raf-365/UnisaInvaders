/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.GameFrame;
import audio.SoundObservable;
import audio.SoundPlayerPepperShoots;
import entities.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 *
 * @author marcopreziosi
 */
public class BossController {
    private SoundObservable a;
    Boss boss;
    BulletBossController bulletBossController;
    PlayController playController;
    private Pepper pepper;
    private boolean stopFiring, stopTiming=false;
    private long timeFire;
    private int numBossKilled=0;
    private static final int SECONDS_BOSS_FIRE = 1, NUM_FIRE=3;

    public Pepper getPepper() {
        return pepper;
    }
    
    
    
    public BossController(){
        pepper=new Pepper(0,0,"");
        bulletBossController = new BulletBossController();        
        boss = new Boss(0, 0, "src/Resources/boss.png");
        boss.setVisible(false);
        int x = (((GameFrame.MAX_X - boss.getWidth()) / 2));
        int y = (50);
        boss.setX(x);
        boss.setY(y);
         a=new SoundObservable();
        a.addListener(new SoundPlayerPepperShoots("shot.wav"));
    }

    public int getNumBossKilled() {
        return numBossKilled;
    }
    
    public Boss getBoss(){
        return this.boss;
    }
    
    public void update(){
        if(boss.isVisible())
            move();
        bulletBossController.update();
    }
    
    private void followPepper(){//FOLLOWPEPPER SETTA SOLO LA VELOCITA'
        if(boss.getX() < pepper.getX())
            boss.setDx(Boss.BOSS_SPEED/2);            
        
        else if(boss.getX() > pepper.getX())
            boss.setDx(-Boss.BOSS_SPEED/2);
    }
    
    private void move(){
        //System.out.println(Integer.toString(boss.getX()));
        playController = PlayController.getPlayController();
        pepper = playController.getPepperController().getPepper();
        //System.out.println(Integer.toString(pepper.getX()));
        
        if((boss.getX() - pepper.getX() < 7) && (boss.getX() - pepper.getX() > -7))
            boss.setDx(0);
        else
            followPepper();
        
        if ((boss.getX() + boss.getDx() <= GameFrame.MAX_X - boss.getWidth() - 20) 
                 && (boss.getX() + boss.getDx() >= 5)) 
            boss.setX(boss.getX() + boss.getDx());//SE STA NELLA PARTE GIUSTA LO FACCIO MUOVERE
        
        /*SPARO DEL BOSS OGNI TOT SECONDI*/
        if(!stopTiming){
            timeFire = System.currentTimeMillis();
            stopTiming = !stopTiming;
        }
        long beforeTime = System.currentTimeMillis();
        //beforetime. move viene chiamato in update e sarò sempre ad aggiornare beforetime e quando sarà maggiore di tot sparo
        if((beforeTime - timeFire)/1000 > SECONDS_BOSS_FIRE/2){
            fire();
            stopTiming = !stopTiming;
        }
    }
    
    
    private void fire() {
        //System.out.println(Integer.toString(boss.getX()+ boss.getWidth()/2) + "\n"+ Integer.toString( boss.getWidth()));
        for(int i=0; i<NUM_FIRE; i++){
            BulletBoss bulletBoss = new BulletBoss(0,0,"src/resources/missileBoss.png");
            bulletBoss.setX((boss.getX()+ boss.getWidth()/2)-bulletBoss.getWidth()/2);
            bulletBoss.setY(boss.getY()+i*bulletBoss.getHeight()+5);
            bulletBossController.getBulletsArrayBoss().add(bulletBoss);
        }
        a.addStates(6);
    }

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
}
