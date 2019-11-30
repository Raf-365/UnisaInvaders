/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperMoving;
import java.awt.EventQueue;
import javax.swing.*;

/**
 *
 * @author stefa
 */
public class PepperJFrame extends JFrame{
    
    public PepperJFrame(){        
        initUI();        
    }
    
    private void initUI() {

        add(new PepperJPanel());

        setSize(700, 500);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            PepperJFrame ex = new PepperJFrame();
            ex.setVisible(true);
        });
    }
    
}