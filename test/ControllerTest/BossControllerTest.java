/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BossController;
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
         //perchÃ¨ nel metodo update di bossController, la chiamata a Move Ã¨ condizionata
         //al fatto che questo attributo sia True
         bc.update();//ci assicuriamo che move venga chiamato perchÃ¨ esso inizializza Pepper
         bc.getPepper().setX(bc.getBoss().getX()+5);
         bc.update();//il boss si deve muovere verso destra
         assertEquals(bc.getMovementBoss(), bc.getBoss().getDx());
         bc.getPepper().setX(bc.getBoss().getX()-5);
         bc.update();//il boss si deve muovere verso sinistra
         assertEquals(-bc.getMovementBoss(), bc.getBoss().getDx());   
    }
    
    
   @Test
    public void testIsAlive(){
        assertTrue(bc.isAlive());
        bc.getBoss().updateHealth(-10);//La vita del Boss Ã¨ di 10 appena viene generato
        assertFalse(bc.isAlive());
    }
    
}
