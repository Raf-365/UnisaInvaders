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
public class BulletPepperCollideProfessor extends Collision {

    @Override
    public void differentTypeCollision(MainView view, Entity entity, Entity entity2) {
        
        entity.setVisible(false);
        entity2.setVisible(false);
        view.addStates(MainView.BULLET_PEPPER_COLLIDE_PROFESSOR);
        view.getClip3().play();
       
    }
}
