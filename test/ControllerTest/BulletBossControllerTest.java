/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BulletBossController;
import entities.BulletBoss;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class BulletBossControllerTest {
     private BulletBossController bbc;
     private BulletBoss bb;
    @Before
    public void setUp() {
        bbc= new BulletBossController();
        bb= new BulletBoss(0,0,"");
        
    }
    
    @Test
    public void testDeleteBullets(){
        bbc.getBulletsArrayBoss().add(bb);
        bbc.deleteBullets(bb);
        Assert.assertEquals(0, bbc.getBulletsArrayBoss().size());
    }
    
    @Test
    public void testUpdate(){
        bbc.getBulletsArrayBoss().add(bb);
        bbc.getBulletsArrayBoss().get(0).setVisible(true);
        bbc.getBulletsArrayBoss().get(0).setY(100);
        bbc.update();
        Assert.assertEquals(110, bbc.getBulletsArrayBoss().get(0).getY());
        bbc.getBulletsArrayBoss().get(0).setY(-10);
        bbc.update();
        Assert.assertEquals(0, bbc.getBulletsArrayBoss().size());
    }
    
}
