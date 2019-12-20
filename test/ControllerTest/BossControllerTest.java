/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BossController;
import entities.Pepper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class BossControllerTest {
    private BossController bc;
    @Before
    public void setUp() {
        bc=new BossController();
    }
    
    @Test
    public void testUpdateKilledBoss(){
        bc.updateKilledBoss();
        assertEquals(1,bc.getNumBossKilled());
        
    }
    
    @Test
    public void testUpdate(){
        
        
    }
   @Test
   public void testfollowPepper() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
       Method method = BossController.class.getDeclaredMethod("followPepper", null);
       method.setAccessible(true);
       bc.getBoss().setX(30);
       bc.pepper.setX(40);
       method.invoke(bc,null );
       assertEquals(3, bc.getBoss().getDx());
       bc.getBoss().setX(40);
       bc.pepper.setX(30);
       method.invoke(bc,null );
       assertEquals(-3, bc.getBoss().getDx());
   }
   
   @Test
    public void testIsAlive(){
        assertTrue(bc.isAlive());
        bc.getBoss().updateHealth(-10);
        assertFalse(bc.isAlive());
    }
    
}
