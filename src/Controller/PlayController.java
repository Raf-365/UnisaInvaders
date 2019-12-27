package Controller;

import ObserverPackage.Controller;
import Controller.HudController;
import Controller.BookController;
import ObserverPackage.CollisionEvent;
import View.GameFrame;
import entities.Book;
import View.MainView;
import entities.Bonus;
import entities.Boss;
import entities.Bullet;
import entities.Pepper;
import java.util.ArrayList;
import static main.Game.SECONDS_BONUS_DISAPPEAR;
import static main.Game.SECONDS_BOOK_DISAPPEAR;
import static main.Game.SECONDS_SHIELD_DISAPPEAR;
import static main.Game.SECONDS_SPEED_BOOKS_UPDATE;
import ObserverPackage.ControllerObserver;
import ObserverPackage.Observer;

public class PlayController extends Controller implements Observer {

    Pepper pepper;
    BookController bookController;
    BonusController bonusController;
    PepperController pepperController;
    HudController hudController;
    BossController bossController;

    //HudControllerBoss hudControllerBoss;
    MainView mainView;
    static private PlayController instance = null;  //SINGLETON
    private boolean ingame, enabled = false;
    private boolean disableSpeedUpdateFlag = false, disappearBookFlag = false, shieldFlag = false;
    private long bookSpeedUpdateTime, bookDisappearTime, beforeTime, timeDiff, sleep, bonusSpeedUpdateTime, bonusSpeedTimeBefore = 0, bonusSpeedTimeAfter = 0;

    private PlayController() {
        ingame = true;
        bookSpeedUpdateTime = System.currentTimeMillis();
        bookDisappearTime = bookSpeedUpdateTime;
        beforeTime = System.currentTimeMillis();
        initController();
        
    }

    public void setBookSpeedUpdateTime(long bookSpeedUpdateTime) {
        this.bookSpeedUpdateTime = bookSpeedUpdateTime;
    }

    public void setBookDisappearTime(long bookDisappearTime) {
        this.bookDisappearTime = bookDisappearTime;
    }

    public void setDisableSpeedUpdateFlag(boolean disableSpeedUpdateFlag) {
        this.disableSpeedUpdateFlag = disableSpeedUpdateFlag;
    }

    public void setDisappearBookFlag(boolean disappearBookFlag) {
        this.disappearBookFlag = disappearBookFlag;
    }

    public void initController() {
        
        bookController = new BookController();
        bonusController = new BonusController();
        pepperController = new PepperController();
        hudController = new HudController(pepperController.getPepper().getHealth());
        bossController = new BossController();

        
        
    }

    public void updateFallingObjectSpeed() {
        Book.updateSpeed();
        Bonus.updateSpeed();
    }

    public BossController getBossController() {
        return bossController;
    }

    public void resetBossController() {

        bossController.getBoss().updateHealth((Boss.getHealthMax()));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setView(MainView mainView) {
        this.mainView = mainView;
        bonusController.setObserverBonus(mainView);
        pepperController.setObserverPepper(mainView);
    }

    public static PlayController getPlayController() { //SINGLETON
        if (instance == null) {
            instance = new PlayController();
        }
        return instance;
    }

    public PepperController getPepperController() {
        return pepperController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public BonusController getBonusController() {
        return bonusController;
    }

    public HudController getHudController() {
        return hudController;
    }

    public boolean getIngame() {
        return ingame;
    }

    public void updateEntities() {
        bookController.update();
        bonusController.update();
        pepperController.update();
        hudController.update();
        bossController.update();
    }

    public void updateView() {
        mainView.repaintComponents(bookController.getBooks(), pepperController.getBulletsArray(),
                bossController.getBulletsArrayBoss());
    }

    public void update() {
        manageTime();
        updateEntities();
        updateView();
    }

    public void manageTime() {
        beforeTime = System.currentTimeMillis();
        
        /*AUMENTO DELLA VELOCITA' DEI LIBRI DOPO UN CERTO NUMERO DI SECONDI*/
        if (this.isEnabled() && !disableSpeedUpdateFlag
                && (beforeTime - bookSpeedUpdateTime) / 1000 > SECONDS_SPEED_BOOKS_UPDATE) {
            this.updateFallingObjectSpeed();

            bookSpeedUpdateTime = System.currentTimeMillis();
        }

        /*SCOMPARSA DEI BONUS UNA SOLA VOLTA*/
        if (this.isEnabled() && !disappearBookFlag
                && (beforeTime - bookDisappearTime) / 1000 > SECONDS_BONUS_DISAPPEAR) {
            disappearBookFlag = true;
            this.getBonusController().setDisappearBonusFlag(disappearBookFlag);
            disappearBookFlag = false;
        }

        /*SCOMPARSA DEI LIBRI DOPO UN CERTO NUMERO DI SECONDI E COMPARSA DEL BOSS*/
        if (this.isEnabled() && !disappearBookFlag
                && (beforeTime - bookDisappearTime) / 1000 > SECONDS_BOOK_DISAPPEAR) {
            disappearBookFlag = true;
            this.getBookController().setDisappearBookFlag(disappearBookFlag);
            this.getBonusController().setDisappearBonusFlag(disappearBookFlag);
            disableSpeedUpdateFlag = true;
        }

        /*COMPARSA DELLO SCUDO E SCOMPARSA DOPO UN CERTO NUMERO DI SECONDI*/
        if (this.isEnabled() && this.getBonusController().getProtectionFlag()) {
            if (!shieldFlag) {
                bonusSpeedTimeBefore = System.currentTimeMillis();
                shieldFlag = !shieldFlag;
            }
            if ((System.currentTimeMillis() - bonusSpeedTimeBefore) / 1000 < SECONDS_SHIELD_DISAPPEAR) {
                this.getPepperController().getPepper().changeImage(30);
                if (this.getPepperController().getFireFlag() == true) {
                    this.getPepperController().getPepper().changeImage(40);
                } else {
                    this.getPepperController().getPepper().changeImage(30);
                }
            } else {
                this.getBonusController().setProtectionFlag(false);
                this.getPepperController().getPepper().changeImage(20);
                this.getBonusController().setShieldDuration(SECONDS_SHIELD_DISAPPEAR);
                shieldFlag = false;
            }
        }

        /*AGGIORNAMENTO DELLE VARIABILI DOPO LA MORTE DEL BOSS*/
        if (this.isEnabled() && !this.getBossController().isAlive()) {
            this.getBossController().getBoss().setVisible(false);
            this.resetBossController();
            this.getBossController().updateKilledBoss();
            this.getHudController().updateScore(HudController.KILLED_BOSS);
            this.getHudController().updateBonus(HudController.KILLED_BOSS);
            disappearBookFlag = false;
            this.getBookController().setDisappearBookFlag(disappearBookFlag);
            this.getBonusController().setDisappearBonusFlag(disappearBookFlag);
            this.getBookController().updateImageBookArray();
            disableSpeedUpdateFlag = false;
            bookSpeedUpdateTime = System.currentTimeMillis();
            bookDisappearTime = bookSpeedUpdateTime;
            if (this.getBossController().getMovementBoss() < Pepper.PEPPER_SPEED - 2) {
                this.getBossController().setMovementBoss(this.getBossController().getMovementBoss() + BossController.INCREASE_SPEED_BOSS);
            }
        }
    }

    @Override
    public void eventCollisionChanged(CollisionEvent collisionEvent) {

        if (collisionEvent.getState().contains(1) && !getBonusController().getProtectionFlag()) {
            //pepper con i libri 
            
            getPepperController().updateHealthPepper(Pepper.MALUS);
            getHudController().updateScore(HudController.MALUS);
            getHudController().updateMalus(HudController.MALUS);
            getPepperController().getPepper().addState(6);
            getPepperController().getPepper().removeState(6);
        }

        if (collisionEvent.getState().contains(2)) { //pepper con la vita
            if (pepperController.getPepper().getHealth() < pepper.HEALTH_MAX - 1) {
                getPepperController().updateHealthPepper(Pepper.HEALTH2);
                
            } else if (pepperController.getPepper().getHealth() == pepper.HEALTH_MAX - 1) {
                getPepperController().updateHealthPepper(Pepper.HEALTH1);
            }
        }
        
        if (collisionEvent.getState().contains(3)) //pepper con lo scudo 
            getBonusController().setProtectionFlag(true);
        

        if (collisionEvent.getState().contains(4)) { //i colpi di pepper con i libri 
            getHudController().updateScore(HudController.BONUS);
            getHudController().updateBonus(HudController.BONUS);
        }

        if (collisionEvent.getState().contains(5) && getBossController().getBoss().isVisible()) {
            getBossController().updateHealthBoss(Boss.MALUS);
        }

        if (collisionEvent.getState().contains(6)) {
            getPepperController().updateHealthPepper(Pepper.MALUS);
            getHudController().updateScore(HudController.MALUS);
            getHudController().updateMalus(HudController.MALUS);
        }
    }

}
