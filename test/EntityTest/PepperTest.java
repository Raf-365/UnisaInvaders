package EntityTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import Controller.BulletController;
import org.junit.*;
import entities.*;
import javax.swing.ImageIcon;
import static org.junit.Assert.*;

public class PepperTest {
    private Pepper p;
    private BulletController bc;
    @Before
    public void setUp() {
        p=new Pepper(0,0,"");
        bc= new BulletController();
    }
    
    
    @Test
    public void testChangeImage(){
        ImageIcon ii = new ImageIcon("src/resources/Pepper10.png");
        p.changeImage(10);
        assertEquals(ii.getImage(), p.getImage());

    }

    @Test
    public void testLoadImage(){
        ImageIcon ic = new ImageIcon("src/resources/Pepper20.png");
        p.loadImage("src/resources/Pepper20.png");
        assertEquals(ic.getImage(), p.getImage());
    }
   
    @Test
    public void testUpdateHealth(){
        int i = p.getHealth();
        p.updateHealth(1);
        assertEquals(i+1, p.getHealth());
    }
        
    @Test
    public void testFire() {
        p.fire(bc);
        p.fire(bc);
        assertEquals(2, bc.getBulletsArray().size());
    }
    
    @Test
    public void testMove() {
       int  initial_pos=p.getX();
        p.setDx(Pepper.PEPPER_SPEED);
        p.move();
        assertEquals(initial_pos+Pepper.PEPPER_SPEED, p.getX());
    }

}

