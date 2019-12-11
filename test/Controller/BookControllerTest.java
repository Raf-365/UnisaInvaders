/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.GameFrame;
import entities.Book;
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
    
    public BookControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of update method, of class BookController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        BookController instance = new BookController();
        Book[] arrayBooks = instance.getBooks();
        Book first = arrayBooks[0];
        first.setY(GameFrame.MAX_Y-(Book.BOOK_SPEED-1));
        //Book second = arrayBooks[6];
        System.out.println("position before test:"+first.getX()+","+first.getY());
        instance.update();
        assertEquals(first.getY0(),first.getY());
        
        
    }
    
}
