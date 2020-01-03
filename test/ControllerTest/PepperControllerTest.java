package ControllerTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */

import Controller.PepperController;
import entities.Pepper;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import org.junit.*;
import static org.junit.Assert.*;

public class PepperControllerTest {
    private Pepper p;
    private PepperController pc;
    @Before
    public void setUp() {
        p=new Pepper(0,0,"");
        pc=new PepperController();
    }
    
    @Test
    public void testIsAlive(){
        assertTrue(pc.isAlive());
        pc.getPepper().updateHealth(-5);
        assertFalse(pc.isAlive());
    }
    
    @Test
    public void testKeyPressed() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        //Component source, int id, long when, int modifiers, int keyCode, char keyChar, int keyLocation
        Component c = new Component() {};
        KeyEvent fakeA = new KeyEvent(c,1,20,1,KeyEvent.VK_A,'c');
        pc.keyPressed(fakeA);
        assertEquals(-7, pc.getPepper().getDx());
        KeyEvent fakeD = new KeyEvent(c,1,20,1,KeyEvent.VK_D,'c');
        pc.keyPressed(fakeD);
        assertEquals(7, pc.getPepper().getDx());
        KeyEvent fakeSp = new KeyEvent(c,1,20,1,KeyEvent.VK_SPACE,'c');
        int s = pc.getBulletsArray().size();
        pc.keyPressed(fakeSp);
        assertEquals(1, pc.getBulletsArray().size());
        
    }
        
    @Test
    public void testKeyReleased(){
        Component c = new Component() {};
        KeyEvent fakeA = new KeyEvent(c,1,20,1,KeyEvent.VK_A,'c');
        pc.keyReleased(fakeA);
        assertEquals(0, pc.getPepper().getDx());
        KeyEvent fakeD = new KeyEvent(c,1,20,1,KeyEvent.VK_D,'c');
        pc.keyReleased(fakeD);
        assertEquals(0, pc.getPepper().getDx());
        KeyEvent fakeSp = new KeyEvent(c,1,20,1,KeyEvent.VK_SPACE,'c');
        pc.keyReleased(fakeSp);
        ImageIcon ii = new ImageIcon("src/resources/Pepper20.png");
        assertEquals(ii.getImage(), pc.getPepper().getImage());
        //assertEquals(6, pc.getPepper().getDx());
        
    }
}

