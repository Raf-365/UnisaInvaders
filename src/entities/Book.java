/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;


/**
 *
 * @author steh
 */
public class Book extends FallingObject {


    public static final int OBSTACLE_SCALE = 50;
    
    
   
    public Book(){
        
           
    }
    
    public Book(int x, int y, String path) {
        super(x, y, path);
        image = image.getScaledInstance(OBSTACLE_SCALE, OBSTACLE_SCALE, Image.SCALE_SMOOTH);
        w = OBSTACLE_SCALE;
        h = OBSTACLE_SCALE;
    }



}
