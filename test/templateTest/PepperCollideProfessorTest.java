/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateTest;

import TemplatePackage.PepperCollideProfessor;
import View.*;
import entities.*;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;


public class PepperCollideProfessorTest {
    private MainView view;
    private Entity entity1; //Pepper
    private ArrayList<Professor> professorsArray;
    private PepperCollideProfessor collide;
   
    
    
    @Before
    public void setUp() {
    this.view= new MainView(new GameFrame());  
    this.entity1 = new Pepper(0,0,"src/Resources/Pepper20.png");
    this.professorsArray = new ArrayList<>();
    professorsArray.add(new Professor(0,0,"18.png"));
    this.collide=new PepperCollideProfessor();
  
    }

    
    @Test
    public void testCollision(){        
        collide.collision(view, (ArrayList) professorsArray, entity1);
        assertFalse(professorsArray.get(0).isVisible());
//Verifichiamo se dopo la collisione viene settato a False l'isVisible dell'oggetto che cade       
    }

}