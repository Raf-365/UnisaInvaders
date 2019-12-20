/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;

import Controller.BookController;
import View.GameFrame;
import entities.Book;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antno
 */
public class BookControllerTest {
    private BookController bc;
    private ArrayList<Book> bookArray;


    @Before
    public void setUp() {
        bc= new BookController();
       
    }
   

    /**
     * Test of update method, of class BookController.
     */
    @Test
    public void testUpdate() {
        int y= bc.getBooks().get(0).getY();
        bc.update();
        assertEquals(0, bc.getBooks().get(0).getY());
        bc.setDisappearBookFlag(true);
        bc.getBooks().get(0).setY(20000);
        bc.update();
        assertFalse(bc.getBooks().get(0).isVisible());
        
    }
    
    @Test
    public void testCreateBookArray(){
        assertEquals(7, bc.getBooks().size());
    
}
    
}
