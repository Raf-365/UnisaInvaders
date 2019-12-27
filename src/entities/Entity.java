    package entities;

import ObserverPackage.CollisionEvent;
import ObserverPackage.Controller;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import ObserverPackage.ControllerObserver;
import ObserverPackage.Observer;

public abstract class Entity {

    protected int x, y, w, h;
    protected boolean visible;
    protected Image image;
    protected ArrayList<Observer> observers;
    private ArrayList<Integer> states; 

    
    
    public Entity(){
        
    }
    
    public Entity(int x, int y, boolean visible, String path) {
        this.x = x;
        this.y = y;
        this.visible = visible;
        loadImage(path);
        
        this.states = new ArrayList<Integer>();
        this.observers = new ArrayList<Observer>();
    }

    public void loadImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void changeImage(int num) {
        ImageIcon ii = new ImageIcon("src/resources/Pepper" + num + ".png");
        image = ii.getImage();
    }
    
    public void changeImageBook(int num) {
        ImageIcon ii = new ImageIcon("src/resources/" + num + ".png");
        image = ii.getImage();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public Image getImage() {
        return image;
    }
    
    
     public synchronized void addState(int state) {
        states.add(state);
        stateChanged();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public synchronized void removeState(int state) {
        int ind = states.indexOf(state);
        states.remove(ind);

    }

    public ArrayList<Integer> getList() {
        return states;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    protected void stateChanged() {
        CollisionEvent collisionEvent = new CollisionEvent(this, getList());
       
        for (Observer eachListener : observers) 
            eachListener.eventCollisionChanged(collisionEvent);
        
    }

    
    
    
    
}
