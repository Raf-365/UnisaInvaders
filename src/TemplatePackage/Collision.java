package TemplatePackage;


import Controller.PlayController;
import View.MainView;
import entities.Boss;
import entities.Entity;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brunello, che grande!
 */
public abstract class Collision {
    
    public void collision(MainView view, ArrayList<Entity> array, Entity entity2, PlayController playController ){

        for (int i=0; i<array.size(); i++){
            Rectangle rArray = array.get(i).getBounds();
            Entity entity = array.get(i);
            
            if (entity2.getBounds().intersects(rArray) && entity.isVisible())
                differentTypeCollision(view, entity, rArray, entity2, playController);
        }
    }
    
    public void collision(MainView view, Entity bonus, Entity entity2, PlayController playController ){

        
            Rectangle rBonus = bonus.getBounds();
            Entity entity = bonus;
            
            if (entity2.getBounds().intersects(rBonus) && entity.isVisible())
                differentTypeCollision(view, entity, rBonus, entity2, playController);
        
    }
    
      /*  
    public void collision(MainView view, ArrayList<Entity> array, Entity entity2, PlayController playController, AudioClip clip ){
        for (int i=0; i<array.size(); i++){
                   Rectangle rArray = array.get(i).getBounds();
                   Entity entity = array.get(i);

                   if (entity2.getBounds().intersects(rArray) && entity.isVisible()){
                       differentTypeCollision(view, entity, rArray, entity2, playController);
                       clip.play();
               }
        }

    }*/
   
    public abstract void differentTypeCollision (MainView view, Entity entity, Rectangle rArray, Entity entity2, PlayController playController);
    
    

    
    
    
    
}
