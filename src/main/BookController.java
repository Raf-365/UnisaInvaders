/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Book;
import java.util.Random;

/**
 *
 * @author stefa
 */
public class BookController {
    
    private Book[] imageArray = new Book[7];    
    
    public BookController(){
        for (int i = 0; i < 7; i++) 
            imageArray[i] = new Book((i * 70) * -1, 0, "src/Resources/Books.png");        
    }
    
    public Book[] getBooks(){return this.imageArray;}
    
    private int generateRandom(){
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X-160)+80;
    }
    
    public void update(){
        cycle();
    }
    
    private void cycle() {
        int x, y;
        
        for (int i = 0; i < 7; i++){
            Book book = imageArray[i];
            x=book.getX();
            y=book.getY();
            
            y += Book.BOOK_SPEED;
        
            if (y > GameFrame.MAX_Y) {        
                y = book.getY0() ;
                x = generateRandom();
            }
            
            book.setX(x);
            book.setY(y);
        }        
    }   
}
