package unisainvaders;

import javax.swing.*;

public class HealthBar extends JProgressBar {

    HealthBar(){
        super();
        setMaximum(100);
        setBorderPainted(true);
    }

    public void reduce(int dx){
        setValue(getValue()-dx);
    }

    public void increment(int dx){
        setValue(getValue()+dx);
    }
}
