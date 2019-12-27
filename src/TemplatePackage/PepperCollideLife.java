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
public class PepperCollideLife extends Collision {


    @Override
    public void differentTypeCollision(MainView view, Entity entity,  Entity entity2) {

        view.addStates(MainView.PEPPER_COLLIDE_LIFE); 
        view.removeState(MainView.PEPPER_COLLIDE_LIFE);
        entity.setVisible(false);
    }

}
