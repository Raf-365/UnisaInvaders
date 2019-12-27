/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BonusController;
import View.GameFrame;
import entities.FallingObject;
import static org.junit.Assert.*;
import org.junit.*;

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
    public void testUpdate() {
        int y= bc.getLife().getY();
        bc.update();       
        assertEquals(-207, bc.getLife().getY());
        /*La posizione y dell'istanza di Bonus "life" presente in BonusController
        viene settata così: (i + 3) * 70) * -1 
        quindi quando verrà creato BonusController i sarà uguale a zero e la suo posizione iniziale pari a -210
        dopo un'esecuzione del metodo update esso verrà aumentata di 3 (valore di speed in FallingObject)
        */
        bc.setDisappearBonusFlag(true);
        bc.getLife().setY(GameFrame.MAX_Y+1);
        bc.update();
        assertFalse(bc.getLife().isVisible());
        
    }
    
    

}
