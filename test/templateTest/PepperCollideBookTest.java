/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateTest;

import ObserverPackage.CollisionEvent;
import TemplatePackage.PepperCollideBook;
import View.*;
import entities.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import org.junit.*;
import static org.junit.Assert.*;


public class PepperCollideBookTest{
    private MainView view;
    private Entity entity1; //Pepper
    private ArrayList<Book> booksArray;
    private PepperCollideBook collide;
   
    
    
    @Before
    public void setUp() {
    this.view= new MainView(new GameFrame());  
    this.entity1 = new Pepper(0,0,"src/Resources/Pepper20.png");
    this.booksArray= new ArrayList<>();
    booksArray.add(new Book(0,0,"18.png"));
    this.collide=new PepperCollideBook();
  
    }

    
    @Test
    public void testCollision(){        
        collide.collision(view, (ArrayList)booksArray, entity1); 
        assertFalse(booksArray.get(0).isVisible()); 
//Verifichiamo se dopo la collisione viene settato a False l'isVisible dell'oggetto che cade       
    }

}
