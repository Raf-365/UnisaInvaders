/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplatePackage1;



import entities.*;
import java.util.ArrayList;

/**
 *
 * @author antno
 */
public abstract class UpdateMovement {
      public void move(ArrayList<Weapon> bulletsArray) {
        Weapon b;
        for (int i = 0; i < bulletsArray.size(); i++) {
            b = bulletsArray.get(i);
            if (b.isVisible() && b.getY() > 0) 
                differentUpdateY(b);
            else 
                bulletsArray.remove(b);
        }
       
    }
      public abstract void differentUpdateY(Weapon entity);
}
