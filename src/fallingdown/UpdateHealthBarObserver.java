/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author antno
 */
public class UpdateHealthBarObserver implements Observer{
    private JProgressBar healthBar;

    public UpdateHealthBarObserver(JProgressBar healthBar) {
        this.healthBar = healthBar;
    }
    
     @Override
    public void update(Observable subject, Object arg) {
        Pepper ale = (Pepper) subject;
        if(ale.getSt()==1){
            SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        healthBar.setValue(ale.getHealth());
                        healthBar.setString(ale.getHealth().toString());
                    }
                });
        }
        
    }
}
