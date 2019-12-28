package Controller;
import ObserverPackage.Controller;
import ObserverPackage.Observer;
import View.GameFrame;
import View.MainView;
import entities.*;
import java.util.Random;
import main.Game;

public class BonusController implements Controller {

    
    private Bonus life, shield;
    private int i;
    private static final double SHIELD_REDUCTION=0.012;
    private boolean disappearBonusFlag , protectionFlag; //flag per la scomparsa dei libri dopo tot secondi
    private boolean shieldEnabled ;
    private double shieldSeconds;

    
    public BonusController() {
        this.i=0;
        this.disappearBonusFlag= false;
        this.protectionFlag=false;
        this.shieldEnabled=false;
        this.shieldSeconds=Game.SECONDS_SHIELD_DISAPPEAR;
        createBonusArray();
        
    }

    private void createBonusArray() {          
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

    @Override
    public void update() {
        
            cycle(life, PlayController.LIFE_UPDATE);            
            cycle(shield, PlayController.SHIELD_UPDATE);
  
        if(protectionFlag)
            reduceShieldDuration();
    }

    private void cycle(Bonus bonus, int a) {
        
        int x,y;

            x = bonus.getX();
            y = bonus.getY();
            y += Bonus.getSpeed();
            
            if (y > GameFrame.MAX_Y) {
                if (!disappearBonusFlag) {
                    y = bonus.getY0();
                    x = generateRandomLife();
                   
                    bonus.setX(x);
                    bonus.setY(y);                    
                    //bonus.addState(a);

                } else {
                    bonus.setVisible(false);
                }
            } else {
                bonus.addState(a);
                bonus.setY(y);
            }
        
    }

}
