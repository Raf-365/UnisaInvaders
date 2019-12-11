/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
/**
 *
 * @author User
 */
import Controller.BulletController;
import Controller.PepperController;
import entities.Bullet;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;


public class BulletControllerTest {
    private BulletController bc;
    @Before
    public void setUp() {
        bc = new BulletController();
    }
    
    @Test
    public void testDeleteBullets(){
        Bullet b = new Bullet(0,0,"");
        bc.getBulletsArray().add(b);
        int s = bc.getBulletsArray().size();
        bc.deleteBullets(b);       
        assertEquals(0,bc.getBulletsArray().size());
    }
    
    @Test
    public void testUpdate(){
        Bullet b = new Bullet(0,20,"");
        b.setVisible(true);
        bc.getBulletsArray().add(b);
        int before = bc.getBulletsArray().get(0).getY();
        bc.update();
        assertEquals(before-Bullet.MISSILE_SPEED, bc.getBulletsArray().get(0).getY());
        
        
        
    }
}
