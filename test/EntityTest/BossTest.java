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
import Controller.BossController;
import Controller.BulletBossController;
import org.junit.*;
import entities.*;
import static org.junit.Assert.*;

public class BossTest {
    private Boss b;
    private BulletBossController bulletBossController;
    private Pepper p;
    
    @Before
    public void setUp() {   
        b=new Boss(0,0,"");
        p=new Pepper(0,0,"");
        bulletBossController= new BulletBossController();
    }
    
    @Test
    public void testFire() {
        b.fire(bulletBossController);
        assertEquals(BossController.getNUM_FIRE(), bulletBossController.getBulletsArrayBoss().size());
    }
    
    @Test
    public void testFollowPepper() {
        p.setX(10);
        b.followPepper(p);
        
        assertEquals(Boss.BOSS_SPEED/2, b.getDx()); //Controllo il dx del boss
    }

}

