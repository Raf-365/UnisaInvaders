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
public class PepperCollideBook extends Collision {

 

    @Override
    public void differentTypeCollision(MainView view, Entity entity,  Entity entity2) {
        entity.setVisible(false);
        view.addStates(MainView.PEPPER_COLLIDE_BOOK); //1 è lo stato di Pepper che collide con i libri
        
        
    }
                                                                
}
