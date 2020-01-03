/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.ProfessorController;
import entities.FallingObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antno
 */
public class ProfessorControllerTest {
    private ProfessorController bc;
    


    @Before
    public void setUp() {
        bc= new ProfessorController();
       
    }
   

    /**
     * Test of update method, of class ProfessorController.
     */
    @Test
    public void testUpdate() {
        int y= bc.getProfessors().get(0).getY();
        bc.update();
        assertEquals(FallingObject.FALLING_OBJECT_SPEED, bc.getProfessors().get(0).getY());
        bc.setDisappearProfessorFlag(true);
        bc.getProfessors().get(0).setY(20000);
        bc.update();
        assertFalse(bc.getProfessors().get(0).isVisible());
        
    }
    
    @Test
    public void testCreateProfessorArray(){
        assertEquals(7, bc.getProfessors().size());
    
}
    
}
