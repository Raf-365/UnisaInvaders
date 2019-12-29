/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplatePackage;

import View.MainView;
import entities.Entity;

/**
 *
 * 
 */
public class BulletPepperCollideBoss extends Collision{


    @Override
    public void differentTypeCollision(MainView view, Entity entity,  Entity entity2) {
  
        if (entity2.isVisible()){
            entity.setVisible(false);
            view.addStates(MainView.BULLET_PEPPER_COLLIDE_BOSS);
            
        }    
    }
}
