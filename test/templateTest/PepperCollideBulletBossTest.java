/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateTest;

import TemplatePackage.*;
import View.GameFrame;
import View.MainView;
import entities.Boss;
import entities.Bullet;
import entities.BulletBoss;
import entities.Entity;
import entities.Pepper;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


public class PepperCollideBulletBossTest {
    private MainView view;
    private Pepper entity1; //Pepper
    private ArrayList<BulletBoss> bulletArray;
    private PepperCollideBulletBoss collide; 
    
    
    @Before
    public void setUp() {
    this.view= new MainView(new GameFrame());  
    this.entity1 = new Pepper(0,0,"src/Resources/Pepper20.png");
    this.bulletArray= new ArrayList<>();
    BulletBoss pro=new BulletBoss(0,0,"src/Resources/missileBoss.png");
    bulletArray.add(pro);
    this.collide=new PepperCollideBulletBoss();    
    }
    
        @Test
    public void testCollision(){ 
        bulletArray.get(0).setX(0);
        bulletArray.get(0).setY(0);
        collide.collision(view, (ArrayList)bulletArray, entity1);  
        assertFalse(bulletArray.get(0).isVisible());
        
//Verifichiamo se dopo la collisione viene settato a False l'isVisible del proiettile del boss     
    }
    
    
    
    
}
