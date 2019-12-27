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
            cycle(life, PlayController.LIFE_UPDATE);
            
            i++;
        } else {
            cycle(shield, PlayController.SHIELD_UPDATE);
            i++;
        }
  
        if(protectionFlag)
            reduceShieldDuration();
    }

    private void cycle(Bonus bonus, int a) {
        
        int x,y;
        
           
            x = bonus.getX();
            y = bonus.getY();
            y += bonus.getSpeed();

            
            if (y > GameFrame.MAX_Y) {
                if (!disappearBonusFlag) {
                    y = bonus.getY0();
                    x = generateRandomLife();
                   
                    bonus.setX(x);
                    bonus.setY(y);
                    
                    bonus.addState(a); //aggiornamento bonus  //1 3 1 life || 2 4 2 shield
                    bonus.removeState(a);
                    //book.setVisible(true);
                } else {
                    /*
                    bonus.addState(b);
                    bonus.removeState(b); 
                    */
                    //life.setVisible(false); 
                }
            } else {
                bonus.addState(a);
                bonus.removeState(a);
                bonus.setY(y);
            }
        
    }

}
