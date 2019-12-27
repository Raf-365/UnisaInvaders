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
import entities.Boss;
import entities.Entity;
import entities.Pepper;
import java.awt.Rectangle;

/**
 *
 * @author Brunello
 */
public class BulletPepperCollideBoss extends Collision {

    @Override
    public void differentTypeCollision(MainView view, Entity entity, Rectangle rArray, Entity entity2, PlayController playController) {

        
        if (entity2.isVisible()){
            entity.setVisible(false);
            view.addStates(5);
            view.removeState(5);
        }
    }
}
