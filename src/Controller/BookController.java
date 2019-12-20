/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Book;
import entities.Entity.*;
import java.awt.Rectangle;
import java.util.Random;
import View.GameFrame;
import java.util.ArrayList;

/**
 *
 * @author stefa
 */
public class BookController {

    private ArrayList<Book> bookArray;
    private static final int NUM_BOOKS=7, 
            DAMAGE_VALUE = -1, OBSTACLE_SCALE = 50;// OBSTACLE_SPEED = 3;
    public static final int EASY_BOOK = 3, MEDIUM_BOOK = 5, HARD_BOOK = 7;
    private boolean disappearBookFlag=false; //falg per la scomparsa dei libri dopo tot secondi
    private PlayController playController;
    
    public BookController() {
        bookArray = new ArrayList<>();
        createBookArray();        
    }
    
    public void createBookArray(){        
        for (int i = 0; i < NUM_BOOKS; i++) 
            bookArray.add(new Book(generateRandom(), (i * 70) * -1, "src/Resources/Books.png"));
        
    }
    
    public void setDisappearBookFlag(boolean flag){
        this.disappearBookFlag=flag;
    }

    public ArrayList<Book> getBooks() {
        return bookArray;
    }

    private int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }

    public void update() {
        cycle();
    }

    private void cycle() {
        int x, y;
        for (int i = 0; i < bookArray.size(); i++) {
            Book book = bookArray.get(i);
            x = book.getX();
            y = book.getY();

            y += Book.getSpeed();

            if (y > GameFrame.MAX_Y) {
                if (!disappearBookFlag) { //VARIABILE PERFAR SCOMPARIRE I LIBRI QUANDO APPARE IL BOSS
                    y = book.getY0();
                    x = generateRandom();
                    book.setX(x);
                    book.setY(y);
                    //book.setVisible(true);
                } else 
                    book.setVisible(false);                
            } else 
                book.setY(y);            
        }
        
        /*ATTENDERE LA SCOMPARSA DI TUTTI I LIBRI PRIMA DI FAR COMPARIRE IL BOSS*/
        boolean flag=true;
        if(disappearBookFlag){
            for (int i = 0; i < bookArray.size(); i++) {
                Book book = bookArray.get(i);
                if(book.isVisible())
                    flag=false;
            }

            if(flag){
                playController = PlayController.getPlayController();
                playController.getBossController().getBoss().setVisible(true);
            } 
        }
    }
}
