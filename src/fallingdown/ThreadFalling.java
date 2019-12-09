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

 */
public class ThreadFalling extends JFrame{
    
   public static final int MAX_X=1480, MAX_Y=920;
   public static boolean flagBottone=false;
   public PepperJPanel mainPanel;
   
    public ThreadFalling(){
        initUi();
    }
    
     private void initUi() {
        mainPanel = new PepperJPanel();
        add(mainPanel);
        setSize(MAX_X, MAX_Y);
        this.setResizable(false);
        setTitle("UnisaInvader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
     
     
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new ThreadFalling();
            ex.setVisible(true);
        });
        
        
    }
}
 