/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplatePackage1;

import entities.*;

/**
 *
 * @author antno
 */
public class UpdateBulletBossControllerY extends UpdateMovement{
     @Override
    public void differentUpdateY (Weapon entity){
        //b.setY(bulletsArray.get(i).getY() + Bullet.MISSILE_SPEED);
        entity.setY(entity.getY()+entity.MISSILE_SPEED);
    }
}
