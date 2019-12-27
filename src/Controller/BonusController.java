package Controller;
import ObserverPackage.Controller;
import ObserverPackage.Observer;
import View.GameFrame;
import View.MainView;
import entities.*;
import java.util.ArrayList;
import java.util.Random;
import main.Game;

public class BonusController extends Controller {

    
    //private ArrayList<Bonus> lifeArray, shieldArray;
    private Bonus life, shield;
    private int i;
    private static final int NUM_BONUS = 1;
    private static final double SHIELD_REDUCTION=0.012;
    private boolean disappearBonusFlag = false, protectionFlag=false; //falg per la scomparsa dei libri dopo tot secondi
    private boolean shieldEnabled = false;
    private double shieldSeconds=Game.SECONDS_SHIELD_DISAPPEAR;

    
    public BonusController() {
        
        createBonusArray();
        
    }

    public void createBonusArray() {
        
            life = new Bonus(generateRandomLife(), ((i + 3) * 70) * -1, "src/Resources/vita.png");
            shield = new Bonus(generateRandomShield(), (i * 70) * -1, "src/Resources/shield.png");
           

        
    }
    
    public void setObserverBonus(MainView view){
        life.addObserver((Observer) view);
        shield.addObserver((Observer) view);
    }
  
    
    private void reduceShieldDuration(){
        shieldSeconds -= SHIELD_REDUCTION;
    }

    public void setShieldDuration(double shieldEnabled) {
        this.shieldSeconds = shieldEnabled;
    }

    public double getShieldDuration() {
        return shieldSeconds;
    }

    public void setDisappearBonusFlag(boolean flag) {
        this.disappearBonusFlag = flag;
    }

    public void setProtectionFlag(boolean flag) {
        this.protectionFlag = flag;
    }

    public boolean getProtectionFlag() {
        return protectionFlag;
    }

    public Bonus getLife() {
        return life;
    }

    public Bonus getShield() {
        return shield;
    }

    private int generateRandomLife() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }

    private int generateRandomShield() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }

    public void update() {
        
        if (i % 2 == 0) {
            lifeCycle();
            
            i++;
        } else {
            shieldCycle();
            i++;
        }
  
        if(protectionFlag)
            reduceShieldDuration();
    }

    private void lifeCycle() {
        
        int x,y;
        
           
            x = life.getX();
            y = life.getY();
            y += life.getSpeed();

            
            if (y > GameFrame.MAX_Y) {
                if (!disappearBonusFlag) {
                    y = life.getY0();
                    x = generateRandomLife();
                   
                    life.setX(x);
                    life.setY(y);
                    
                    life.addState(1); //aggiornamento bonus 
                    life.removeState(1);
                    //book.setVisible(true);
                } else {
                    life.addState(3);
                    life.removeState(3);
                    //life.setVisible(false); 
                }
            } else {
                life.addState(1);
                life.removeState(1);
                life.setY(y);
            }
        
    }

    private void shieldCycle() {
        int x, y;
        
            
           
            x = shield.getX();
            y = shield.getY();
            y += shield.getSpeed();

            if (y > GameFrame.MAX_Y + 100) {
                if (!disappearBonusFlag) {
                    y = shield.getY0();
                    x = generateRandomShield();
                    shield.setX(x);
                    shield.setY(y);
                    shield.addState(2); //aggiornamento bonus 
                    shield.removeState(2);
                    
                } else {
                    shield.addState(4);
                    shield.removeState(4);
                }
            } else {
                shield.addState(2); //aggiornamento bonus 
                shield.removeState(2);
                shield.setY(y);
            }
               
        
    }
}
