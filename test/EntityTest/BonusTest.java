/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityTest;

import entities.Bonus;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class BonusTest {
  
    
    @Test
    public void testUpdateSpeed(){
        int s = Bonus.getSpeed();
        Bonus.updateSpeed();
        assertEquals(s+1,Bonus.getSpeed());
    }
    
}
