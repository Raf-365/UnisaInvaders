/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityTest;

import entities.Book;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author User
 */
public class BookTest {
    @Test
    public void testUpdateSpeed(){
        int s = Book.getSpeed();
        Book.updateSpeed();
        assertEquals(s+1,Book.getSpeed());
    }
    
}
