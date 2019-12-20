/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.GameFrame;
import entities.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author marcopreziosi
 */
public class BonusController {
    
    private ArrayList<Bonus> lifeArray, shieldArray;
    private int i;
    
    private static final int NUM_BONUS=1;
    private boolean disappearBonusFlag=false, protectionFlag; //falg per la scomparsa dei bonus dopo tot secondi
    
    public BonusController(){
        lifeArray = new ArrayList<>();
        shieldArray = new ArrayList<>();
        createBonusArray();
        
    }
    public void createBonusArray(){
        for (int i = 0; i<NUM_BONUS; i++){
            lifeArray.add(new Bonus(generateRandomLife(), ((i+3)*70) * -1, "src/Resources/vita.png" ));
            shieldArray.add(new Bonus(generateRandomShield(), (i*70) * -1, "src/Resources/shield.png" ));
        }
    }
    
 public void setDisappearBonusFlag(boolean flag){
        this.disappearBonusFlag=flag;
    }
 
 
    public void setProtectionFlag (boolean flag){
        this.protectionFlag=flag;
    }
    
    public boolean getProtectionFlag (){
        return protectionFlag;
    }
 
    
     public ArrayList<Bonus> getLife() {
        return lifeArray;
    }
      public ArrayList<Bonus> getShield() {
        return shieldArray;
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
        
        if (i%2 == 0){
            lifeCycle();
            i++;
        }
        else {    
        shieldCycle();
        i++;
        }
    }
       
        private void lifeCycle() {
        int x, y;
        for (int i = 0; i < lifeArray.size(); i++) {
            Bonus bonus = lifeArray.get(i);
            x = bonus.getX();
            y = bonus.getY();

            y += bonus.getSpeed();

            if (y > GameFrame.MAX_Y) {
                if (!disappearBonusFlag) {
                    y = bonus.getY0();
                    x = generateRandomLife();
                    bonus.setX(x);
                    bonus.setY(y);
                    //book.setVisible(true);
                } else 
                    bonus.setVisible(false);
                
            } else 
                bonus.setY(y);
            
        }
    }
        
    private void shieldCycle() {
        int x, y;
        for (int i = 0; i < shieldArray.size(); i++) {
            Bonus shield = shieldArray.get(i);
            x = shield.getX();
            y = shield.getY();

            y += shield.getSpeed();

            if (y > GameFrame.MAX_Y+100) {
                if (!disappearBonusFlag) {
                    y = shield.getY0();
                    x = generateRandomShield();
                    shield.setX(x);
                    shield.setY(y);
                    //book.setVisible(true);
                } else 
                    shield.setVisible(false);
                
            } else 
                shield.setY(y);
            
        }
    }
        
        
}
