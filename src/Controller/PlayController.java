/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HudController;
import Controller.BookController;
import entities.Book;
import View.MainView;
import entities.Bonus;
import entities.Boss;
import entities.Bullet;
import java.util.ArrayList;

/**
 *
 * @author stefa
 */
public class PlayController {
/*questo controller ha il compito di sincronizzare e rendere coerenti la vista principale, con tutti 
     i modelli e per fare ciò sfrutta i controller singoli relativi ad ogni modello.E' un singleton*/
    BookController bookController;
    BonusController bonusController;
    PepperController pepperController;
    HudController hudController;
    BossController bossController;
    HudControllerBoss hudControllerBoss;
    MainView mainView;
    static private PlayController instance = null;  //SINGLETON
    private boolean ingame, enabled=false;
    

    private PlayController() {
        ingame = true;
        initController();
    }
    
    public void initController(){
        bookController = new BookController();
        bonusController = new BonusController();
        pepperController = new PepperController();
        hudController = new HudController(pepperController.getPepper().getHealth());
        bossController = new BossController();
        hudControllerBoss = new HudControllerBoss(bossController.getBoss().getHealth());
    }
    
    public void updateFallingObjectSpeed(){
        Book.updateSpeed();//questo metodo serve per aumentare la velocità degli ostacoli
        Bonus.updateSpeed();
    }

    public BossController getBossController() {
        return bossController;
    }

    public HudControllerBoss getHudControllerBoss() {
        return hudControllerBoss;
    }
    
    public void resetBossController(){
        bossController.getBoss().updateHealth((Boss.getHealthMax()));
    }
    
    public void setEnabled(boolean enabled){this.enabled=enabled;}
    
    public boolean isEnabled(){return enabled;}

    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    public static PlayController getPlayController() { //SINGLETON
        if (instance == null) 
            instance = new PlayController();
        
        return instance;
    }

    public PepperController getPepperController() {return pepperController;}

    public BookController getBookController() {return bookController;}
    
    public BonusController getBonusController() {return bonusController;}
    
    public HudController getHudController() {return hudController;}

    public boolean getIngame() {return ingame;}

    public void updateEntities() {//aggiorno tutti i model mediante i relativi controller
        bookController.update();
        bonusController.update();
        pepperController.update();
        hudController.update();
        bossController.update();
    }

    public void updateView() {
        mainView.repaintComponents(bookController.getBooks(), pepperController.getBulletsArray(), 
                bossController.getBulletsArrayBoss(), bonusController.getLife(), bonusController.getShield());
    }

    public void update() {
        updateEntities();
        updateView();
    }
}
