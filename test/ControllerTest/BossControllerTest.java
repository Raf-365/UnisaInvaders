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
         bc.getBoss().setVisible(true);//settiamo l'attributo visible del boss a true
         //perchè nel metodo update di bossController, la chiamata a Move è condizionata
         //al fatto che questo attributo sia True
         bc.update();//ci assicuriamo che move venga chiamato perchè esso inizializza Pepper
         bc.getPepper().setX(bc.getBoss().getX()+5);
         bc.update();//il boss si deve muovere verso destra
         assertEquals(bc.getMovementBoss(), bc.getBoss().getDx());
         bc.getPepper().setX(bc.getBoss().getX()-5);
         bc.update();//il boss si deve muovere verso sinistra
         assertEquals(-bc.getMovementBoss(), bc.getBoss().getDx());   
    }
    
    
   @Test
   public void testfire() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
       Method method = BossController.class.getDeclaredMethod("fire", null);
       method.setAccessible(true);
       int size=bc.getBulletsArrayBoss().size();
       method.invoke(bc, null);//chiamo il metodo fire,ora accessibile, sulla istanza di BossController
       assertEquals(size+3, bc.getBulletsArrayBoss().size());//vengono creati alla invocazione 3 bulletBoss
   }
   
   @Test
    public void testIsAlive(){
        assertTrue(bc.isAlive());
        bc.getBoss().updateHealth(-10);//La vita del Boss è di 10 appena viene generato
        assertFalse(bc.isAlive());
    }
    
}
