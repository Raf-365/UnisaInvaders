/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;
import java.awt.EventQueue;
import javax.swing.*;
/**
 *
 * @author antno
 */
public class ThreadFalling extends JFrame{
    
    
    public ThreadFalling(){
        initUi();
    }
    
     private void initUi() {
         
         
        add(new PepperJPanel());
        setSize(1280, 720);
        this.setResizable(true);
        setTitle("UnisaInvaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
     
     
     public static void main(String[] args)  {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new ThreadFalling();
            ex.setVisible(true);
         
        });
        
    }
}
