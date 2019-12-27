/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BonusController;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class BonusControllerTest {
    private BonusController bc;
    
    
    @Before
    public void setUp() {
       bc= new BonusController();
       
       
    }
    
    @Test
    public void testCreateBonusArray(){
        assertEquals(1, bc.getShield().size());
    
    }
    
    

}
