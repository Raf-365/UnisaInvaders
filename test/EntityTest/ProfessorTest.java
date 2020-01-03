/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityTest;

import entities.Professor;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author User
 */
public class ProfessorTest {
    @Test
    public void testUpdateSpeed(){
        int s = Professor.getSpeed();
        Professor.updateSpeed();
        assertEquals(s+1,Professor.getSpeed());
    }
    
}
