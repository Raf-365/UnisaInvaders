/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateTest;


import TemplatePackage.PepperCollideLife;
import TemplatePackage.PepperCollideShield;
import View.GameFrame;
import View.MainView;
import entities.Bonus;
import entities.Entity;
import entities.Pepper;

import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


public class PepperCollideBonusTest {
    private MainView view;
    private Entity entity1; //Pepper
    private Bonus bonus1; //Istanza di Life
    private Bonus bonus2; //Istanza di scudo
    private PepperCollideLife collideLife;
    private PepperCollideShield collideShield;
   
    
    
    @Before
    public void setUp() {
    this.view= new MainView(new GameFrame());  
    this.entity1 = new Pepper(0,0,"src/Resources/Pepper20.png");
    this.bonus1 = new Bonus(0,0,"src/Resources/vita.png");
    this.bonus2 = new Bonus(0,0,"src/Resources/shield.png");
    this.collideLife=new PepperCollideLife();   
    this.collideShield= new PepperCollideShield();
    }


    @Test
    public void testCollision(){
        collideLife.collision(view, bonus1, entity1);
        assertFalse(bonus1.isVisible());    
    }
    
    @Test
    public void testCollisionShield(){
        collideShield.collision(view, bonus2, entity1);
        assertFalse(bonus2.isVisible());  
    }
    
    
    
}
