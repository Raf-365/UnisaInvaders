package Controller;

import ObserverPackage.CollisionEvent;
import entities.Professor;
import View.MainView;
import entities.Bonus;
import entities.Boss;
import entities.Pepper;
import static main.Game.SECONDS_BONUS_DISAPPEAR;
import static main.Game.SECONDS_PROFESSORS_DISAPPEAR;
import static main.Game.SECONDS_SHIELD_DISAPPEAR;
import static main.Game.SECONDS_SPEED_PROFESSORS_UPDATE;
import ObserverPackage.*;

public class PlayController  implements Controller,Observer {

    Pepper pepper;
    ProfessorController professorController;
    BonusController bonusController;
    PepperController pepperController;
    HudController hudController;
    BossController bossController;

    //HudControllerBoss hudControllerBoss;
    MainView mainView;
    static private PlayController instance = null;  //SINGLETON
    private boolean ingame, enabled = false;
    private boolean disableSpeedUpdateFlag = false, disappearProfessorFlag = false, shieldFlag = false;
    private long professorSpeedUpdateTime, professorDisappearTime, beforeTime, timeDiff, sleep, bonusSpeedUpdateTime, bonusSpeedTimeBefore = 0, bonusSpeedTimeAfter = 0;
    
    public static final int BOSS_NOT_VISIBLE = 8, BOSS_IS_VISIBLE= 9,LIFE_UPDATE = 1, SHIELD_UPDATE = 2, SOUND_BULLET= 5, SOUND_COLLISION = 6, PEPPER_DEATH = 7;
    
    
    
    private PlayController() {
        ingame = true;
        professorSpeedUpdateTime = System.currentTimeMillis();
        professorDisappearTime = professorSpeedUpdateTime;
        beforeTime = System.currentTimeMillis();
        initController();
        
    }

    public void setProfessorSpeedUpdateTime(long professorSpeedUpdateTime) {
        this.professorSpeedUpdateTime = professorSpeedUpdateTime;
    }

    public void setProfessorDisappearTime(long professorDisappearTime) {
        this.professorDisappearTime = professorDisappearTime;
    }

    public void setDisableSpeedUpdateFlag(boolean disableSpeedUpdateFlag) {
        this.disableSpeedUpdateFlag = disableSpeedUpdateFlag;
    }

    public void setDisappearProfessorFlag(boolean disappearProfessorFlag) {
        this.disappearProfessorFlag = disappearProfessorFlag;
    }

    public void initController() {
        
        professorController = new ProfessorController();
        bonusController = new BonusController();
        pepperController = new PepperController();
        hudController = new HudController(pepperController.getPepper().getHealth());
        bossController = new BossController();   
    }

    public void updateFallingObjectSpeed() {
        Professor.updateSpeed();
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
        bonusController.setObserverBonus(mainView);//aggiunge come osservatori di vita e scudo , la mainview
        pepperController.setObserverPepper(mainView);//aggiunge come osservatore di pepper, la view
        
    }/*Ricordiamo che tutte le entities (quindi i modelli) sono osservabili , quindi attraverso il metodo setView di PlayController sto aggiungendo alla LISTA DI OSSERVATORI DEI MODELLI la vista principale*/

    public static PlayController getPlayController() { //SINGLETON
        if (instance == null) {
            instance = new PlayController();
        }
        return instance;
    }

    public PepperController getPepperController() {
        return pepperController;
    }

    public ProfessorController getProfessorController() {
        return professorController;
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
        professorController.update();
        bonusController.update();
        pepperController.update();
        hudController.update();
        bossController.update();
    }

    public void updateView() {
        mainView.repaintComponents();
    }

    @Override
    public void update() {
        manageTime();
        updateEntities();
        updateView();
    }

    public void manageTime() {
        beforeTime = System.currentTimeMillis();
        
        /*AUMENTO DELLA VELOCITA' DEI LIBRI DOPO UN CERTO NUMERO DI SECONDI*/
        if (this.isEnabled() && !disableSpeedUpdateFlag
                && (beforeTime - professorSpeedUpdateTime) / 1000 > SECONDS_SPEED_PROFESSORS_UPDATE) {
            this.updateFallingObjectSpeed();

            professorSpeedUpdateTime = System.currentTimeMillis();
        }

        /*SCOMPARSA DEI BONUS UNA SOLA VOLTA*/
        if (this.isEnabled() && !disappearProfessorFlag
                && (beforeTime - professorDisappearTime) / 1000 > SECONDS_BONUS_DISAPPEAR) {
            disappearProfessorFlag = true;
            this.getBonusController().setDisappearBonusFlag(disappearProfessorFlag);
            disappearProfessorFlag = false;
        }

        /*SCOMPARSA DEI LIBRI DOPO UN CERTO NUMERO DI SECONDI E COMPARSA DEL BOSS*/
        if (this.isEnabled() && !disappearProfessorFlag
                && (beforeTime - professorDisappearTime) / 1000 > SECONDS_PROFESSORS_DISAPPEAR) {
            disappearProfessorFlag = true;
            this.getProfessorController().setDisappearProfessorFlag(disappearProfessorFlag);
            this.getBonusController().setDisappearBonusFlag(disappearProfessorFlag);
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
            getBossController().getBoss().addState(BOSS_NOT_VISIBLE);
            this.getBossController().getBoss().setVisible(false);
            this.resetBossController();
            this.getBossController().updateKilledBoss();
            this.getHudController().updateScore(HudController.KILLED_BOSS);
            this.getHudController().updateBonus(HudController.KILLED_BOSS);
            disappearProfessorFlag = false;
            this.getProfessorController().setDisappearProfessorFlag(disappearProfessorFlag);
            this.getBonusController().setDisappearBonusFlag(disappearProfessorFlag);
            this.getProfessorController().updateImageProfessorArray();
            disableSpeedUpdateFlag = false;
            professorSpeedUpdateTime = System.currentTimeMillis();
            professorDisappearTime = professorSpeedUpdateTime;
            if (this.getBossController().getMovementBoss() < Pepper.PEPPER_SPEED - 2) {
                this.getBossController().setMovementBoss(this.getBossController().getMovementBoss() + BossController.INCREASE_SPEED_BOSS);
            }
        }
    }

    @Override
    public void eventCollisionChanged(CollisionEvent collisionEvent) {
        // viene chiamato quando la mainview notifica playcontroller
        MainView source =(MainView)collisionEvent.getSource();
        if (collisionEvent.getState().contains(MainView.PEPPER_COLLIDE_PROFESSOR)){
             source.removeState(MainView.PEPPER_COLLIDE_PROFESSOR);
            
               if( !getBonusController().getProtectionFlag()) {
            //pepper collide con i libri e non ha una protezione
            getPepperController().updateHealthPepper(Pepper.MALUS);
            getHudController().updateScore(HudController.MALUS);
            getHudController().updateMalus(HudController.MALUS);
            //getPepperController().getPepper().addState(SOUND_COLLISION); //suono collisione 
            
            if (!getPepperController().isAlive()){
                getPepperController().getPepper().addState(PEPPER_DEATH); //morte  
            }
               }
               
        }

        if (collisionEvent.getState().contains(MainView.PEPPER_COLLIDE_LIFE)) { //pepper con la vita
            source.removeState(MainView.PEPPER_COLLIDE_LIFE);
            if (pepperController.getPepper().getHealth() < pepper.HEALTH_MAX - 1) {
                getPepperController().updateHealthPepper(Pepper.HEALTH2);
                
            } else if (pepperController.getPepper().getHealth() == pepper.HEALTH_MAX - 1) {
                getPepperController().updateHealthPepper(Pepper.HEALTH1);
            }
        }
        
        if (collisionEvent.getState().contains(MainView.PEPPER_COLLIDE_SHIELD)){ //pepper con lo scudo 
            getBonusController().setProtectionFlag(true);
            source.removeState(MainView.PEPPER_COLLIDE_SHIELD);
        

        }if (collisionEvent.getState().contains(MainView.BULLET_PEPPER_COLLIDE_PROFESSOR)) { //i colpi di pepper con i libri
            getHudController().updateScore(HudController.BONUS);
            getHudController().updateBonus(HudController.BONUS);
            source.removeState(MainView.BULLET_PEPPER_COLLIDE_PROFESSOR);
        }

        if (collisionEvent.getState().contains(MainView.BULLET_PEPPER_COLLIDE_BOSS)) {
           source.removeState(MainView.BULLET_PEPPER_COLLIDE_BOSS);
        if(getBossController().getBoss().isVisible()){
            
            getBossController().updateHealthBoss(Boss.MALUS);
            
             
        }
        }

        if (collisionEvent.getState().contains(MainView.PEPPER_COLLIDE_BULLET_BOSS)) { //bulletBoss & pepper
            source.removeState(MainView.PEPPER_COLLIDE_BULLET_BOSS);
            getPepperController().updateHealthPepper(Pepper.MALUS);
            getHudController().updateScore(HudController.MALUS);
            getHudController().updateMalus(HudController.MALUS);
            if (!getPepperController().isAlive()){
                getPepperController().getPepper().addState(PEPPER_DEATH); //morte
            }
        }
    }

}
