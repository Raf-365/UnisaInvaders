/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Book;

/**
 *
 * @author stefa
 */
public class MainController {
    BookController bookController;
    PepperController pepperController;
    MainView mainView;
    static private MainController instance=null;  //SINGLETON
    private boolean ingame;
    
    private MainController(){
        ingame=true;
        bookController=new BookController();
        pepperController=new PepperController();
    }
    
    public void setView(MainView mainView){this.mainView=mainView;}
    
    public static MainController getController(){ //SINGLETON
        if (instance==null)
            instance=new MainController();
        return instance;	
    }
    
    public PepperController getPepperController(){return pepperController;}
    
    public boolean getIngame(){return ingame;}
    
    public void updateEntities(){
        bookController.update(); 
        pepperController.update();
    }
    
    public void updateView(){        
        mainView.repaintBooks(bookController.getBooks());
        
    } 
    
    public void update(){
        updateEntities();
        updateView();
    }
}
