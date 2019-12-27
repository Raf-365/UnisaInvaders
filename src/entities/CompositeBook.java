/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import View.GameFrame;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author marcopreziosi
 */
public class CompositeBook extends Book {

    private ArrayList<Book> booksArray;
    private ArrayList<Integer> prof;
    private static final int NUM_BOOKS=7;
    
    
    
    public CompositeBook() {
        booksArray = new ArrayList<>();
        
        prof = new ArrayList<>();
        
        for (int i=1; i<22; i++)
            prof.add(i);
        createBookArray();
    }

    public void addBook(Book book) {
        booksArray.add(book);
    }

    public void removeBook(Book book) {
        booksArray.remove(book);
    }

    public ArrayList<Book> getBooksArray() {
        return booksArray;
    }

    public void setBooksY(ArrayList<Integer> yArray) {

        for (int i = 0; i < yArray.size(); i++) {
            booksArray.get(i).setY(yArray.get(i));
        }
    }

    public void setBooksX(ArrayList<Integer> xArray) {

        for (int i = 0; i < xArray.size(); i++) {
            booksArray.get(i).setX(xArray.get(i));
        }
    }

    public ArrayList<Integer> getBooksY() {

        ArrayList<Integer> yArray = new ArrayList<>();
        
        for (int i = 0; i < booksArray.size(); i++) {
            yArray.add(booksArray.get(i).getY());
        }
        
        return yArray;
    }

    public ArrayList<Integer> getBooksX() {

        ArrayList<Integer> xArray = new ArrayList<>();
        
        for (int i = 0; i < booksArray.size(); i++) {
            xArray.add(booksArray.get(i).getX());
        }
        return xArray;
    }
    
    
    private void createBookArray(){ 
        for (int i = 0; i < NUM_BOOKS; i++) 
            booksArray.add(new Book(generateRandom(), (i * 70) * -1, "src/resources/" + Integer.toString(prof.get(i))+ ".png"));

    }
    
    public int generateRandom() {
        Random random = new Random();
        return random.nextInt(GameFrame.MAX_X - 160) + 80;
    }

    public ArrayList<Integer> getProf() {
        return prof;
    }
    
    
    public ArrayList<Integer> getY0Array(){
        
        ArrayList <Integer> y0 = new ArrayList<>();

        for (int i=0; i<booksArray.size(); i++){
            y0.add(i, booksArray.get(i).getY0());
    }
    return y0;
    }
    
    
    public void setVisibleBooksArray(ArrayList<Boolean> visibleArray){
        
        for (int i=0; i<visibleArray.size(); i++)
            booksArray.get(i).setVisible(visibleArray.get(i));
        
        
    }

}
