package unisainvaders;

import javax.swing.*;
import java.awt.*;

public class PepperView extends JPanel{
    HealthBar healthBar;
    PepperView(){
        this.healthBar = new HealthBar();
        initUI();
        add(healthBar);
    }

    private void initUI(){
        this.healthBar.setBounds(100,30,100,30);
    }
}
