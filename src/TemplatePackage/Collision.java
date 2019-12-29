package TemplatePackage;


import View.MainView;
import entities.Entity;
import java.awt.Rectangle;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public abstract class Collision {
    
    public void collision(MainView view, ArrayList<Entity> array, Entity entity2){

        for (int i=0; i<array.size(); i++){
            Rectangle rArray = array.get(i).getBounds();
            Entity entity = array.get(i);
            
            if (entity2.getBounds().intersects(rArray) && entity.isVisible()){
                differentTypeCollision(view, entity, entity2);
               
               
            }
        }
    }
    
    public void collision(MainView view, Entity bonus, Entity entity2){

        
            Rectangle rBonus = bonus.getBounds();
            Entity entity = bonus;
            
            if (entity2.getBounds().intersects(rBonus) && entity.isVisible())
                differentTypeCollision(view, entity, entity2);
        
    }
      
    public abstract void differentTypeCollision (MainView view, Entity entity, Entity entity2);
    
    

    
    
    
    
}
