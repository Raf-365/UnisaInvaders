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
 * @author Brunello
 */
public class PepperCollideBook extends Collision {

    @Override
    public void differentTypeCollision(MainView view, Entity entity, Rectangle rArray, Entity entity2, PlayController playController) {


        
            view.addStates(1); //1 è lo stato di Pepper che collide con i libri
            
            view.removeState(1);
            entity.setVisible(false);
            
           

        if (!playController.getPepperController().isAlive()) {
            MainView.setIngame(false);
        }
    }
                                                                
}
