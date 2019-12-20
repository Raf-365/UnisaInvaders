/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTest;
/**
 *
 * @author User
 */
import Controller.BookController;
import Controller.HudController;
import Controller.HudController;
import org.junit.*;
import static org.junit.Assert.*;
public class HudControllerTest {
    private HudController hc;
    @Before
    public void setUp() {
        hc = new HudController(0);
    }
    
    @Test
    public void testUpdate(){
        float score = hc.getScore();
        hc.update();
        assertEquals(score+0.01, hc.getScore(),0.01);
        
        
    }
    
    @Test
    public void testUpdateScore(){
        float score = hc.getScore();
        hc.updateScore(0);
        assertEquals(score+score, hc.getScore(),0);
        
    }
    
    @Test
    public void testUpdateHealth(){
        int health = hc.getHealth();
        hc.updateHealth(health);
        assertEquals(health+health, hc.getHealth());
        
    }
    
    @Test
    public void testUpdateBonus(){
        int bonus = hc.getBonus();
        hc.updateBonus(bonus);
        assertEquals(bonus+bonus, hc.getBonus());
    }
    
    @Test
    public void testupdateMalus(){
        int malus = hc.getMalus();
        hc.updateMalus(malus);
        assertEquals(malus+malus, hc.getMalus());
        
    }
    
}
