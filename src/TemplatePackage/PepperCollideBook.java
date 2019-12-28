/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplatePackage;

import Controller.HudController;
import Controller.PlayController;
import View.MainView;
import entities.Bonus;
import entities.Entity;
import entities.Pepper;
import java.awt.Rectangle;

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
