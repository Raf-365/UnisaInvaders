

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import org.junit.*;
import entities.Entity;
import entities.*;
import View.*;
import Controller.*;
import main.*;
import javax.swing.ImageIcon;
import static org.junit.Assert.*;
public class PepperTest {
    private Pepper p;
    @Before
    public void setUp() {
        p=new Pepper(0,0,"");
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
}
