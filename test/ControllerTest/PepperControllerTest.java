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
import View.GameFrame;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void testFire() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method = PepperController.class.getDeclaredMethod("fire", null);
        method.setAccessible(true);
        int s = pc.getBulletsArray().size();
        method.invoke(pc,null );
        method.invoke(pc,null );
        assertEquals(2, pc.getBulletsArray().size());
    }
    
    @Test
    public void testMove() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        /*if ((pepper.getX() + pepper.getDx() <= GameFrame.MAX_X - pepper.getWidth() - 20) && (pepper.getX() + pepper.getDx() >= 5)) {
            pepper.setX(pepper.getX() + pepper.getDx());
        }*/
        Method method = PepperController.class.getDeclaredMethod("move", null);
        method.setAccessible(true);
        //System.out.println(pc.getPepper().getX());
       // System.out.println(pc.getPepper().getDx());
        //System.out.println(GameFrame.MAX_X);
        //System.out.println(pc.getPepper().getWidth());
        pc.getPepper().setDx(Pepper.PEPPER_SPEED);
        
        
        int initial_pos= pc.getPepper().getX();
        method.invoke(pc,null );
        
        assertEquals(initial_pos+pc.getPepper().getDx(), pc.getPepper().getX());
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
