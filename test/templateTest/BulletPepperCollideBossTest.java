/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateTest;

import TemplatePackage.*;
import View.GameFrame;
import View.*;
import entities.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BulletPepperCollideBossTest {
    private MainView view;
    private Boss entity1; //Boss
    private ArrayList<Bullet> bulletArray;
    private BulletPepperCollideBoss collide;
    
    @Before
    public void setUp() {
    this.view= new MainView(new GameFrame());  
    this.entity1 = new Boss(0,0,"src/Resources/boss.png");
    this.bulletArray= new ArrayList<>();
    Bullet pro=new Bullet(0,0,"src/Resources/missile2.png");
    bulletArray.add(pro);
    this.collide=new BulletPepperCollideBoss();
    
    }
    
    @Test
    public void testCollision(){ 
        bulletArray.get(0).setX(0);
        bulletArray.get(0).setY(0);
        collide.collision(view, (ArrayList)bulletArray, entity1);  
        System.out.println("XPeper="+entity1.getX()+"YPepper="+entity1.getY()+"PepperVisible:"+entity1.isVisible()+"Bounds"+entity1.getBounds()); 
        System.out.print("XBullet="+bulletArray.get(0).getX()+"YBullet="+bulletArray.get(0).getY()+"BulletVisible:"+bulletArray.get(0).isVisible()+"Bounds"+bulletArray.get(0).getBounds());
        assertFalse(bulletArray.get(0).isVisible());    
        
//Verifichiamo se dopo la collisione viene settato a False l'isVisible del proiettile di Pepper     
    }
    
}
