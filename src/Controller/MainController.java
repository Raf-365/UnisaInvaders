/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HudController;
import Controller.BookController;
import entities.Book;
import View.MainView;

/**
 *
 * @author stefa
 */
public class MainController {

    BookController bookController;
    PepperController pepperController;
    HudController hudController;
    MainView mainView;
    static private MainController instance = null;  //SINGLETON
    private boolean ingame;

    private MainController() {
        ingame = true;

        bookController = new BookController();
        pepperController = new PepperController();
        pepperController.setPepperInitialPosition();
        hudController = new HudController(pepperController.getPepper().getHealth());
    }

    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    public static MainController getController() { //SINGLETON
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public PepperController getPepperController() {
        return pepperController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public HudController getHudController() {
        return hudController;
    }

    public boolean getIngame() {
        return ingame;
    }

    public void updateEntities() {
        bookController.update();
        pepperController.update();
        hudController.update();

    }

    public void updateView() {
        mainView.repaintComponents(bookController.getBooks(), pepperController.getBulletsArray());

    }

    public void update() {
        updateEntities();
        updateView();

    }
}
